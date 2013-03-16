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
import org.ng200.tslk.lang.TSLKGrammarParser.BreakExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ContinueExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.DynamicChildCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ForBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.IfBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.LocalAssignExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ModifyExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.PathCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.PathContext;
import org.ng200.tslk.lang.TSLKGrammarParser.ReturnExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.StaticChildCallContext;
import org.ng200.tslk.lang.TSLKGrammarParser.SubExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.TableBlockContext;
import org.ng200.tslk.lang.TSLKGrammarParser.TablenodeContext;
import org.ng200.tslk.lang.TSLKGrammarParser.UnaryOperatorContext;
import org.ng200.tslk.lang.TSLKGrammarParser.WhileBlockContext;
import org.ng200.tslk.lang.TSLKGrammarVisitor;
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
		switch (ctx.o.getType()) {
		case TSLKGrammarLexer.AND:
			return visitExpr(ctx.l).and(visitExpr(ctx.r));
		case TSLKGrammarLexer.OR:
			return visitExpr(ctx.l).or(visitExpr(ctx.r));
		case TSLKGrammarLexer.DIV:
			return visitExpr(ctx.l).divide(visitExpr(ctx.r));
		case TSLKGrammarLexer.MUL:
			return visitExpr(ctx.l).multiply(visitExpr(ctx.r));
		case TSLKGrammarLexer.REM:
			return visitExpr(ctx.l).remainder(visitExpr(ctx.r));
		case TSLKGrammarLexer.PLUS:
			return visitExpr(ctx.l).add(visitExpr(ctx.r));
		case TSLKGrammarLexer.MINUS:
			return visitExpr(ctx.l).subtract(visitExpr(ctx.r));
		case TSLKGrammarLexer.LESS:
			return new TSLKBoolean(instance, visitExpr(ctx.l).isLess(
					visitExpr(ctx.r)));
		case TSLKGrammarLexer.LESSEQ:
			return new TSLKBoolean(instance, visitExpr(ctx.l).isLessOrEqual(
					visitExpr(ctx.r)));
		case TSLKGrammarLexer.MORE:
			return new TSLKBoolean(instance, visitExpr(ctx.l).isMore(
					visitExpr(ctx.r)));
		case TSLKGrammarLexer.MOREEQ:
			return new TSLKBoolean(instance, visitExpr(ctx.l).isMoreOrEqual(
					visitExpr(ctx.r)));
		case TSLKGrammarLexer.EQ:
			return new TSLKBoolean(instance, visitExpr(ctx.l).equals(
					visitExpr(ctx.r)));
		case TSLKGrammarLexer.NOTEQ:
			return new TSLKBoolean(instance, !visitExpr(ctx.l).equals(
					visitExpr(ctx.r)));
		}
		return null;
	}

	@Override
	public TSLKObject visitBody(BodyContext ctx) {
		for (ExprContext expr : ctx.expr()) {
			instance.getStepByStepManager().step(ctx);
			visitExpr(expr);
		}
		return null;
	}

	@Override
	public TSLKObject visitBreakExpr(BreakExprContext ctx) {
		throw new TSLKThrowableHacks.TSLKBreakThrowable();
	}

	@Override
	public TSLKObject visitContinueExpr(ContinueExprContext ctx) {
		throw new TSLKThrowableHacks.TSLKContinueThrowable();
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
		if (ctx instanceof ReturnExprContext)
			return visitReturnExpr((ReturnExprContext) ctx);
		if (ctx instanceof ContinueExprContext)
			return visitContinueExpr((ContinueExprContext) ctx);
		if (ctx instanceof ReturnExprContext)
			return visitBreakExpr((BreakExprContext) ctx);
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
				try {
					visitBody(ctx.forbody);
				} catch (TSLKThrowableHacks.TSLKContinueThrowable t) {
					continue;
				}
			}
		} catch (TSLKThrowableHacks.TSLKBreakThrowable t) {
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
		TSLKObject returned = null;
		variables.addFirst(new HashMap<String, TSLKObject>());
		if (isTrue(ctx.ifexpr)) {
			try {
				visitBody(ctx.ifbody);
			} catch (TSLKThrowableHacks.TSLKReturnThrowable t) {
				returned = t.getReturnObject();
			}
		} else {
			for (int i = 0; i < ctx.elifexprs.size(); i++) {
				if (isTrue(ctx.elifexprs.get(i))) {
					try {
						visitBody(ctx.elifbodies.get(i));
					} catch (TSLKThrowableHacks.TSLKReturnThrowable t) {
						returned = t.getReturnObject();
					}
					variables.removeFirst();
					return returned;
				}
			}
			if (ctx.elsebody != null)
				try {
					visitBody(ctx.elsebody);
				} catch (TSLKThrowableHacks.TSLKReturnThrowable t) {
					returned = t.getReturnObject();
				}
		}
		variables.removeFirst();
		return returned;
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
	public TSLKObject visitReturnExpr(ReturnExprContext ctx) {
		throw new TSLKThrowableHacks.TSLKReturnThrowable(visitExpr(ctx.e));
	}

	@Override
	public TSLKObject visitStaticChildCall(StaticChildCallContext ctx) {
		String name = ctx.name.getText();
		if (ctx.l == null) {
			for (HashMap<String, TSLKObject> map : variables) {
				TSLKObject o = map.get(name);
				if (o != null)
					return o;
			}
			return null;
		}
		TSLKObject left = visitPath(ctx.l);
		if (left != null)
			return left.getAtIndex(new TSLKString(instance, name));
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
		return null;
	}

	@Override
	public TSLKObject visitWhileBlock(WhileBlockContext ctx) {
		variables.addFirst(new HashMap<String, TSLKObject>());
		try {
			while (isTrue(ctx.whileexpr)) {
				try {
					visitBody(ctx.whilebody);
				} catch (TSLKThrowableHacks.TSLKContinueThrowable t) {
					continue;
				}
			}
		} catch (TSLKThrowableHacks.TSLKBreakThrowable t) {
		} finally {
			variables.removeLast();
		}
		return null;
	}

}
