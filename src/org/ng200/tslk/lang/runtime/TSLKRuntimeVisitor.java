/*******************************************************************************
 * Copyright (c) 2013 Nick Guletskii.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nick Guletskii - initial API and implementation
 ******************************************************************************/
package org.ng200.tslk.lang.runtime;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.ng200.tslk.lang.TSLKGrammarLexer;
import org.ng200.tslk.lang.TSLKGrammarParser.AssignExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.AtomBooleanFalseContext;
import org.ng200.tslk.lang.TSLKGrammarParser.AtomBooleanTrueContext;
import org.ng200.tslk.lang.TSLKGrammarParser.AtomNumberContext;
import org.ng200.tslk.lang.TSLKGrammarParser.AtomStringContext;
import org.ng200.tslk.lang.TSLKGrammarParser.BinaryOperatorContext;
import org.ng200.tslk.lang.TSLKGrammarParser.BodyContext;
import org.ng200.tslk.lang.TSLKGrammarParser.BreakStmtContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ContinueStmtContext;
import org.ng200.tslk.lang.TSLKGrammarParser.DynamicChildCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ForBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.IfBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.LocalAssignExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ModifyExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.NormalStmtContext;
import org.ng200.tslk.lang.TSLKGrammarParser.PathCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.PathContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ReturnStmtContext;
import org.ng200.tslk.lang.TSLKGrammarParser.StaticChildCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.StmtContext;
import org.ng200.tslk.lang.TSLKGrammarParser.SubExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.TableBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.TablenodeContext;
import org.ng200.tslk.lang.TSLKGrammarParser.UnaryOperatorContext;
import org.ng200.tslk.lang.TSLKGrammarParser.WhileBlockContext;
import org.ng200.tslk.lang.TSLKGrammarVisitor;
import org.ng200.tslk.lang.runtime.TSLKObject.Type;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;

public class TSLKRuntimeVisitor extends AbstractParseTreeVisitor<TSLKObject>
		implements TSLKGrammarVisitor<TSLKObject> {
	private LinkedList<HashMap<String, TSLKObject>> variables = new LinkedList<HashMap<String, TSLKObject>>();
	private TSLKInstance instance;

	public TSLKRuntimeVisitor(TSLKInstance instance) {
		super();
		this.instance = instance;
		variables.add(new HashMap<String, TSLKObject>());
		variables.peekFirst().put("println", new TSLKFunctionPrintln(instance));
	}

	public LinkedList<HashMap<String, TSLKObject>> getVariables() {
		return variables;
	}

	private boolean isTrue(ExprContext ctx) {
		TSLKObject obj = null;
		return (obj = visitExpr(ctx)) != null
				&& new TSLKBoolean(instance, true).equals(obj);
	}

	@Override
	public TSLKObject visitAssignExpr(AssignExprContext ctx) {
		TSLKObject val = visitExpr(ctx.val);
		if (ctx.varpath.getClass() == StaticChildCallContext.class) {
			StaticChildCallContext cctx = ((StaticChildCallContext) ctx.varpath);
			String name = cctx.name.getText();
			if (cctx.l != null)
				visitPath(cctx.l).setAtIndex(new TSLKString(instance, name),
						val);
			else {
				for (HashMap<String, TSLKObject> map : variables)
					if (map.containsKey(name))
						return map.put(name, val);
				return variables.peekLast().put(name, val);
			}
		} else if (ctx.varpath.getClass() == DynamicChildCallContext.class) {
			DynamicChildCallContext cctx = ((DynamicChildCallContext) ctx.varpath);
			TSLKObject obj = visitExpr(cctx.r);
			if (cctx.l != null)
				visitPath(cctx.l).setAtIndex(obj, val);
			else {
				for (HashMap<String, TSLKObject> map : variables)
					if (map.containsKey(obj.toString()))
						return map.put(obj.toString(), val);
				return variables.peekLast().put(obj.toString(), val);
			}
		} else
			throw new TSLKRuntimeException(
					"Can't assign to the return value of a function!");
		return val;
	}

	@Override
	public TSLKObject visitAtomBooleanFalse(AtomBooleanFalseContext ctx) {
		return new TSLKBoolean(instance, false);
	}

	@Override
	public TSLKObject visitAtomBooleanTrue(AtomBooleanTrueContext ctx) {
		return new TSLKBoolean(instance, true);
	}

	@Override
	public TSLKObject visitAtomNumber(AtomNumberContext ctx) {
		return new TSLKNumber(instance, new BigDecimal(ctx.getText()));
	}

	@Override
	public TSLKObject visitAtomString(AtomStringContext ctx) {
		return new TSLKString(instance, ctx.getText().substring(1,
				ctx.getText().length() - 1));
	}

	@Override
	public TSLKObject visitBinaryOperator(BinaryOperatorContext ctx) {
		TSLKObject l = visitExpr(ctx.l);
		TSLKObject r = visitExpr(ctx.r);
		// if()
		switch (ctx.o.getType()) {
		case TSLKGrammarLexer.AND:
			return l == null ? new TSLKBoolean(instance, false) : l.and(r);
		case TSLKGrammarLexer.OR:
			return l == null ? new TSLKBoolean(instance, new TSLKBoolean(
					instance, true).equals(r)) : l.or(r);
		case TSLKGrammarLexer.DIV:
			if (l == null)
				throw new TSLKRuntimeException(
						"Can't perform arithmetic on null!");
			return l.divide(r);
		case TSLKGrammarLexer.MUL:
			if (l == null)
				throw new TSLKRuntimeException(
						"Can't perform arithmetic on null!");
			return l.multiply(r);
		case TSLKGrammarLexer.REM:
			if (l == null)
				throw new TSLKRuntimeException(
						"Can't perform arithmetic on null!");
			return l.remainder(r);
		case TSLKGrammarLexer.PLUS:
			if (l == null)
				throw new TSLKRuntimeException(
						"Can't perform arithmetic on null!");
			return l.add(r);
		case TSLKGrammarLexer.MINUS:
			if (l == null)
				throw new TSLKRuntimeException(
						"Can't perform arithmetic on null!");
			return l.subtract(r);
		case TSLKGrammarLexer.LESS:
			return l == null ? new TSLKBoolean(instance, r != null)
					: new TSLKBoolean(instance, l.isLess(r));
		case TSLKGrammarLexer.LESSEQ:
			return l == null ? new TSLKBoolean(instance, true)
					: new TSLKBoolean(instance, l.isLessOrEqual(r));
		case TSLKGrammarLexer.MORE:
			return l == null ? new TSLKBoolean(instance, false)
					: new TSLKBoolean(instance, l.isMore(r));
		case TSLKGrammarLexer.MOREEQ:
			return l == null ? new TSLKBoolean(instance, r == null)
					: new TSLKBoolean(instance, l.isMoreOrEqual(r));
		case TSLKGrammarLexer.EQEQ:
			return l == null ? new TSLKBoolean(instance, r == null)
					: new TSLKBoolean(instance, l.equals(r));
		case TSLKGrammarLexer.NOTEQ:
			return l == null ? new TSLKBoolean(instance, r != null)
					: new TSLKBoolean(instance, !l.equals(r));
		}
		throw new TSLKRuntimeException("Unknown binary operator!");
	}

	@Override
	public TSLKObject visitBody(BodyContext ctx) {
		TSLKObject ret;
		for (StmtContext expr : ctx.stmt()) {
			instance.getStepByStepManager().step(ctx);
			ret = visitStmt(expr);
			if (ret != null && ret.getType() == Type.INTERRUPT)
				return ret;
		}
		return null;
	}

	public TSLKObject visitStmt(StmtContext ctx) {
		if (ctx instanceof NormalStmtContext)
			return visitExpr(((NormalStmtContext) ctx).expr());
		if (ctx instanceof ReturnStmtContext)
			return visitReturnStmt((ReturnStmtContext) ctx);
		if (ctx instanceof ContinueStmtContext)
			return visitContinueStmt((ContinueStmtContext) ctx);
		if (ctx instanceof ReturnStmtContext)
			return visitBreakStmt((BreakStmtContext) ctx);
		throw new TSLKRuntimeException("Unknown statement!");
	}

	@Override
	public TSLKObject visitBreakStmt(BreakStmtContext ctx) {
		return new TSLKBreakInterruptObject(instance);
	}

	@Override
	public TSLKObject visitContinueStmt(ContinueStmtContext ctx) {
		return new TSLKContinueInterruptObject(instance);
	}

	@Override
	public TSLKObject visitDynamicChildCall(DynamicChildCallContext ctx) {
		if (ctx.l == null) {
			TSLKObject r = visitExpr(ctx.r);
			for (HashMap<String, TSLKObject> map : variables)
				if (map.containsKey(r.toString()))
					return map.get(r.toString());
			return null;
		}
		TSLKObject left = visitPath(ctx.l);
		if (left != null)
			return left.getAtIndex(visitExpr(ctx.r));
		return null;
	}

	public TSLKObject visitExpr(ExprContext ctx) {
		if (ctx instanceof BinaryOperatorContext)
			return visitBinaryOperator((BinaryOperatorContext) ctx);
		if (ctx instanceof AssignExprContext)
			return visitAssignExpr((AssignExprContext) ctx);
		if (ctx instanceof LocalAssignExprContext)
			return visitLocalAssignExpr((LocalAssignExprContext) ctx);
		if (ctx instanceof ModifyExprContext)
			return visitModifyExpr((ModifyExprContext) ctx);
		if (ctx instanceof UnaryOperatorContext)
			return visitUnaryOperator((UnaryOperatorContext) ctx);
		if (ctx instanceof ForBlockContext)
			return visitForBlock((ForBlockContext) ctx);
		if (ctx instanceof WhileBlockContext)
			return visitWhileBlock((WhileBlockContext) ctx);
		if (ctx instanceof IfBlockContext)
			return visitIfBlock((IfBlockContext) ctx);
		if (ctx instanceof FuncBlockContext)
			return visitFuncBlock((FuncBlockContext) ctx);
		if (ctx instanceof PathCallContext)
			return visitPathCall((PathCallContext) ctx);
		if (ctx instanceof AtomNumberContext)
			return visitAtomNumber((AtomNumberContext) ctx);
		if (ctx instanceof AtomStringContext)
			return visitAtomString((AtomStringContext) ctx);
		if (ctx instanceof SubExprContext)
			return visitExpr(((SubExprContext) ctx).e);
		if (ctx instanceof TableBlockContext)
			return visitTableBlock((TableBlockContext) ctx);
		if (ctx instanceof AtomBooleanTrueContext)
			return visitAtomBooleanTrue((AtomBooleanTrueContext) ctx);
		if (ctx instanceof AtomBooleanFalseContext)
			return visitAtomBooleanFalse((AtomBooleanFalseContext) ctx);
		throw new TSLKRuntimeException("Unknown expression!");
	}

	@Override
	public TSLKObject visitForBlock(ForBlockContext ctx) {
		try {
			variables.addFirst(new HashMap<String, TSLKObject>());
			for (visitExpr(ctx.initexpr); isTrue(ctx.whileexpr); visitExpr(ctx.increxpr)) {
				TSLKObject result = visitBody(ctx.forbody);
				if (result != null && result instanceof TSLKInterruptObject) {
					if (result.getClass() == TSLKBreakInterruptObject.class)
						break;
					if (result.getClass() == TSLKContinueInterruptObject.class)
						continue;
					return result;
				}
			}
		} finally {
			variables.removeFirst();
		}
		return null;
	}

	@Override
	public TSLKObject visitFuncBlock(FuncBlockContext ctx) {
		String args[] = new String[ctx.args.size()];
		for (int i = 0; i < args.length; i++)
			args[i] = ctx.args.get(i).getText();
		return new TSLKFunction(instance, args, ctx.funcbody);
	}

	@Override
	public TSLKObject visitFuncCall(FuncCallContext ctx) {
		TSLKObject left = visitPath(ctx.l);
		if (left == null)
			throw new TSLKRuntimeException("Attempt to call null");
		if (!(left instanceof TSLKFunction))
			throw new TSLKRuntimeException("Attempt to call a "
					+ left.getType().name().toLowerCase());
		TSLKFunction func = (TSLKFunction) left;
		return func.visit(ctx, this);
	}

	@Override
	public TSLKObject visitIfBlock(IfBlockContext ctx) {
		try {
			variables.addFirst(new HashMap<String, TSLKObject>());
			if (isTrue(ctx.ifexpr))
				return visitBody(ctx.ifbody);
			else {
				for (int i = 0; i < ctx.elifexprs.size(); i++)
					if (isTrue(ctx.elifexprs.get(i)))
						return visitBody(ctx.elifbodies.get(i));
				if (ctx.elsebody != null)
					return visitBody(ctx.elsebody);
			}
		} finally {
			variables.removeFirst();
		}
		return null;
	}

	@Override
	public TSLKObject visitLocalAssignExpr(LocalAssignExprContext ctx) {
		TSLKObject ret = visitExpr(ctx.val);
		variables.peekFirst().put(ctx.varid.getText(), ret);
		return ret;
	}

	@Override
	public TSLKObject visitModifyExpr(ModifyExprContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	public TSLKObject visitPath(PathContext ctx) {
		if (ctx.getClass() == StaticChildCallContext.class)
			return visitStaticChildCall((StaticChildCallContext) ctx);
		if (ctx.getClass() == DynamicChildCallContext.class)
			return visitDynamicChildCall((DynamicChildCallContext) ctx);
		if (ctx.getClass() == FuncCallContext.class)
			return visitFuncCall((FuncCallContext) ctx);
		return null;
	}

	@Override
	public TSLKObject visitPathCall(PathCallContext ctx) {
		return visitPath(ctx.path());
	}

	@Override
	public TSLKObject visitReturnStmt(ReturnStmtContext ctx) {
		return new TSLKReturnInterruptObject(instance, visitExpr(ctx.e));
	}

	@Override
	public TSLKObject visitStaticChildCall(StaticChildCallContext ctx) {
		String name = ctx.name.getText();
		if (ctx.l != null) {
			TSLKObject left = visitPath(ctx.l);
			if (left == null)
				return null;
			return left.getAtIndex(new TSLKString(instance, name));
		}
		for (HashMap<String, TSLKObject> map : variables) {
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}
		return null;
	}

	@Override
	public TSLKObject visitSubExpr(SubExprContext ctx) {
		return visitExpr(ctx.e);
	}

	@Override
	public TSLKObject visitTableBlock(TableBlockContext ctx) {
		TSLKTable table = new TSLKTable(instance);
		int i = 0;
		for (TablenodeContext ectx : ctx.vals) {
			table.setAtIndex(
					(ectx.key == null) ? new TSLKNumber(instance,
							new BigDecimal(i)) : new TSLKString(instance,
							ectx.key.getText()), visitExpr(ectx.val));
			if (ectx.key == null)
				i++;
		}
		return table;
	}

	@Override
	public TSLKObject visitTablenode(TablenodeContext ctx) {
		throw new TSLKRuntimeException(
				"A table node can't be visited on its own!");
	}

	@Override
	public TSLKObject visitUnaryOperator(UnaryOperatorContext ctx) {
		TSLKObject obj = visitExpr(ctx.e);
		switch (ctx.o.getType()) {
		case TSLKGrammarLexer.NOT:
			return obj == null ? new TSLKBoolean(instance, true) : obj.not();
		case TSLKGrammarLexer.MINUS:
			return obj == null ? null : obj.minus();
		case TSLKGrammarLexer.LEN:
			return obj == null ? null : obj.length();
		}
		throw new TSLKRuntimeException("Unknown unary operator!");
	}

	@Override
	public TSLKObject visitWhileBlock(WhileBlockContext ctx) {
		try {
			variables.addFirst(new HashMap<String, TSLKObject>());
			while (isTrue(ctx.whileexpr)) {
				TSLKObject result = visitBody(ctx.whilebody);
				if (result != null && result instanceof TSLKInterruptObject) {
					if (result.getClass() == TSLKBreakInterruptObject.class)
						break;
					if (result.getClass() == TSLKContinueInterruptObject.class)
						continue;
					return result;
				}
			}
		} finally {
			variables.removeLast();
		}
		return null;
	}

	@Override
	public TSLKObject visitNormalStmt(NormalStmtContext ctx) {
		throw new TSLKRuntimeException(
				"The normal statement should be handled by the expression visitor!");
	}

}
