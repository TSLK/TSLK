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
package org.ng200.tslk.lang;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class TSLKGrammarParser extends Parser {
	public static class AssignExprContext extends ExprContext {
		public PathContext varpath;
		public Token o;
		public ExprContext val;

		public AssignExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitAssignExpr(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterAssignExpr(this);
		}

		public TerminalNode EQ() {
			return getToken(TSLKGrammarParser.EQ, 0);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitAssignExpr(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}
	}

	public static class AtomBooleanFalseContext extends ExprContext {
		public AtomBooleanFalseContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitAtomBooleanFalse(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterAtomBooleanFalse(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitAtomBooleanFalse(this);
		}

		public TerminalNode FALSE() {
			return getToken(TSLKGrammarParser.FALSE, 0);
		}
	}

	public static class AtomBooleanTrueContext extends ExprContext {
		public AtomBooleanTrueContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitAtomBooleanTrue(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterAtomBooleanTrue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitAtomBooleanTrue(this);
		}

		public TerminalNode TRUE() {
			return getToken(TSLKGrammarParser.TRUE, 0);
		}
	}

	public static class AtomNumberContext extends ExprContext {
		public AtomNumberContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitAtomNumber(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterAtomNumber(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitAtomNumber(this);
		}

		public TerminalNode NUMBER() {
			return getToken(TSLKGrammarParser.NUMBER, 0);
		}
	}

	public static class AtomStringContext extends ExprContext {
		public AtomStringContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitAtomString(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterAtomString(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitAtomString(this);
		}

		public TerminalNode STRING() {
			return getToken(TSLKGrammarParser.STRING, 0);
		}
	}

	public static class BinaryOperatorContext extends ExprContext {
		public ExprContext l;
		public Token o;
		public ExprContext r;

		public BinaryOperatorContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitBinaryOperator(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode AND() {
			return getToken(TSLKGrammarParser.AND, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterBinaryOperator(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitBinaryOperator(this);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}
	}

	public static class BodyContext extends ParserRuleContext {
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitBody(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterBody(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitBody(this);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_body;
		}

		public List<TerminalNode> SEMI() {
			return getTokens(TSLKGrammarParser.SEMI);
		}

		public TerminalNode SEMI(int i) {
			return getToken(TSLKGrammarParser.SEMI, i);
		}
	}

	public static class BreakExprContext extends ExprContext {
		public BreakExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitBreakExpr(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode BREAK() {
			return getToken(TSLKGrammarParser.BREAK, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterBreakExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitBreakExpr(this);
		}
	}

	public static class ContinueExprContext extends ExprContext {
		public ContinueExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitContinueExpr(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode CONTINUE() {
			return getToken(TSLKGrammarParser.CONTINUE, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterContinueExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitContinueExpr(this);
		}
	}

	public static class DynamicChildCallContext extends PathContext {
		public PathContext l;
		public ExprContext r;

		public DynamicChildCallContext(PathContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitDynamicChildCall(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterDynamicChildCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitDynamicChildCall(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode LBRACKET() {
			return getToken(TSLKGrammarParser.LBRACKET, 0);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}

		public TerminalNode RBRACKET() {
			return getToken(TSLKGrammarParser.RBRACKET, 0);
		}
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;

		public ExprContext() {
		}

		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}

		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}

		@Override
		public int getRuleIndex() {
			return RULE_expr;
		}
	}

	public static class ForBlockContext extends ExprContext {
		public ExprContext initexpr;
		public ExprContext whileexpr;
		public ExprContext increxpr;
		public BodyContext forbody;

		public ForBlockContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitForBlock(this);
			else
				return visitor.visitChildren(this);
		}

		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public TerminalNode DO() {
			return getToken(TSLKGrammarParser.DO, 0);
		}

		public TerminalNode END() {
			return getToken(TSLKGrammarParser.END, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterForBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitForBlock(this);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public TerminalNode FOR() {
			return getToken(TSLKGrammarParser.FOR, 0);
		}

		public List<TerminalNode> SEMI() {
			return getTokens(TSLKGrammarParser.SEMI);
		}

		public TerminalNode SEMI(int i) {
			return getToken(TSLKGrammarParser.SEMI, i);
		}
	}

	public static class FuncBlockContext extends ExprContext {
		public Token GENERAL_ID;
		public List<Token> args = new ArrayList<Token>();
		public BodyContext funcbody;

		public FuncBlockContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitFuncBlock(this);
			else
				return visitor.visitChildren(this);
		}

		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(TSLKGrammarParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(TSLKGrammarParser.COMMA, i);
		}

		public TerminalNode END() {
			return getToken(TSLKGrammarParser.END, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterFuncBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitFuncBlock(this);
		}

		public TerminalNode FUNC() {
			return getToken(TSLKGrammarParser.FUNC, 0);
		}

		public List<TerminalNode> GENERAL_ID() {
			return getTokens(TSLKGrammarParser.GENERAL_ID);
		}

		public TerminalNode GENERAL_ID(int i) {
			return getToken(TSLKGrammarParser.GENERAL_ID, i);
		}

		public TerminalNode LPAREN() {
			return getToken(TSLKGrammarParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TSLKGrammarParser.RPAREN, 0);
		}
	}

	public static class FuncCallContext extends PathContext {
		public PathContext l;
		public ExprContext expr;
		public List<ExprContext> args = new ArrayList<ExprContext>();

		public FuncCallContext(PathContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitFuncCall(this);
			else
				return visitor.visitChildren(this);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(TSLKGrammarParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(TSLKGrammarParser.COMMA, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterFuncCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitFuncCall(this);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public TerminalNode LPAREN() {
			return getToken(TSLKGrammarParser.LPAREN, 0);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TSLKGrammarParser.RPAREN, 0);
		}
	}

	public static class IfBlockContext extends ExprContext {
		public ExprContext ifexpr;
		public BodyContext ifbody;
		public ExprContext expr;
		public List<ExprContext> elifexprs = new ArrayList<ExprContext>();
		public BodyContext body;
		public List<BodyContext> elifbodies = new ArrayList<BodyContext>();
		public BodyContext elsebody;

		public IfBlockContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitIfBlock(this);
			else
				return visitor.visitChildren(this);
		}

		public List<BodyContext> body() {
			return getRuleContexts(BodyContext.class);
		}

		public BodyContext body(int i) {
			return getRuleContext(BodyContext.class, i);
		}

		public List<TerminalNode> ELSE() {
			return getTokens(TSLKGrammarParser.ELSE);
		}

		public TerminalNode ELSE(int i) {
			return getToken(TSLKGrammarParser.ELSE, i);
		}

		public TerminalNode END() {
			return getToken(TSLKGrammarParser.END, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterIfBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitIfBlock(this);
		}

		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}

		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class, i);
		}

		public List<TerminalNode> IF() {
			return getTokens(TSLKGrammarParser.IF);
		}

		public TerminalNode IF(int i) {
			return getToken(TSLKGrammarParser.IF, i);
		}

		public List<TerminalNode> THEN() {
			return getTokens(TSLKGrammarParser.THEN);
		}

		public TerminalNode THEN(int i) {
			return getToken(TSLKGrammarParser.THEN, i);
		}
	}

	public static class LocalAssignExprContext extends ExprContext {
		public Token varid;
		public Token o;
		public ExprContext val;

		public LocalAssignExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitLocalAssignExpr(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterLocalAssignExpr(this);
		}

		public TerminalNode EQ() {
			return getToken(TSLKGrammarParser.EQ, 0);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitLocalAssignExpr(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode GENERAL_ID() {
			return getToken(TSLKGrammarParser.GENERAL_ID, 0);
		}

		public TerminalNode LOCAL() {
			return getToken(TSLKGrammarParser.LOCAL, 0);
		}
	}

	public static class ModifyExprContext extends ExprContext {
		public PathContext varpath;
		public Token o;
		public ExprContext val;

		public ModifyExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitModifyExpr(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode DIVEQ() {
			return getToken(TSLKGrammarParser.DIVEQ, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterModifyExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitModifyExpr(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode MINUSEQ() {
			return getToken(TSLKGrammarParser.MINUSEQ, 0);
		}

		public TerminalNode MULEQ() {
			return getToken(TSLKGrammarParser.MULEQ, 0);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}

		public TerminalNode PLUSEQ() {
			return getToken(TSLKGrammarParser.PLUSEQ, 0);
		}

		public TerminalNode REM() {
			return getToken(TSLKGrammarParser.REM, 0);
		}
	}

	public static class PathCallContext extends ExprContext {
		public PathCallContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitPathCall(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterPathCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitPathCall(this);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}
	}

	public static class PathContext extends ParserRuleContext {
		public int _p;

		public PathContext() {
		}

		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public PathContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}

		public void copyFrom(PathContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}

		@Override
		public int getRuleIndex() {
			return RULE_path;
		}
	}

	public static class ReturnExprContext extends ExprContext {
		public ExprContext e;

		public ReturnExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitReturnExpr(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterReturnExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitReturnExpr(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode RETURN() {
			return getToken(TSLKGrammarParser.RETURN, 0);
		}
	}

	public static class StaticChildCallContext extends PathContext {
		public PathContext l;
		public Token name;

		public StaticChildCallContext(PathContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitStaticChildCall(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode DOT() {
			return getToken(TSLKGrammarParser.DOT, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterStaticChildCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitStaticChildCall(this);
		}

		public TerminalNode GENERAL_ID() {
			return getToken(TSLKGrammarParser.GENERAL_ID, 0);
		}

		public PathContext path() {
			return getRuleContext(PathContext.class, 0);
		}
	}

	public static class SubExprContext extends ExprContext {
		public ExprContext e;

		public SubExprContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitSubExpr(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterSubExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitSubExpr(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(TSLKGrammarParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TSLKGrammarParser.RPAREN, 0);
		}
	}

	public static class TableBlockContext extends ExprContext {
		public TablenodeContext tablenode;
		public List<TablenodeContext> vals = new ArrayList<TablenodeContext>();

		public TableBlockContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitTableBlock(this);
			else
				return visitor.visitChildren(this);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(TSLKGrammarParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(TSLKGrammarParser.COMMA, i);
		}

		public TerminalNode END() {
			return getToken(TSLKGrammarParser.END, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterTableBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitTableBlock(this);
		}

		public TerminalNode TABLE() {
			return getToken(TSLKGrammarParser.TABLE, 0);
		}

		public List<TablenodeContext> tablenode() {
			return getRuleContexts(TablenodeContext.class);
		}

		public TablenodeContext tablenode(int i) {
			return getRuleContext(TablenodeContext.class, i);
		}
	}

	public static class TablenodeContext extends ParserRuleContext {
		public Token key;
		public ExprContext val;

		public TablenodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitTablenode(this);
			else
				return visitor.visitChildren(this);
		}

		public TerminalNode COLON() {
			return getToken(TSLKGrammarParser.COLON, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterTablenode(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitTablenode(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode GENERAL_ID() {
			return getToken(TSLKGrammarParser.GENERAL_ID, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_tablenode;
		}

		public TerminalNode STRING() {
			return getToken(TSLKGrammarParser.STRING, 0);
		}
	}

	public static class UnaryOperatorContext extends ExprContext {
		public Token o;
		public ExprContext e;

		public UnaryOperatorContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitUnaryOperator(this);
			else
				return visitor.visitChildren(this);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterUnaryOperator(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitUnaryOperator(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode LEN() {
			return getToken(TSLKGrammarParser.LEN, 0);
		}

		public TerminalNode MINUS() {
			return getToken(TSLKGrammarParser.MINUS, 0);
		}

		public TerminalNode NOT() {
			return getToken(TSLKGrammarParser.NOT, 0);
		}
	}

	public static class WhileBlockContext extends ExprContext {
		public ExprContext whileexpr;
		public BodyContext whilebody;

		public WhileBlockContext(ExprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TSLKGrammarVisitor)
				return ((TSLKGrammarVisitor<? extends T>) visitor)
						.visitWhileBlock(this);
			else
				return visitor.visitChildren(this);
		}

		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public TerminalNode DO() {
			return getToken(TSLKGrammarParser.DO, 0);
		}

		public TerminalNode END() {
			return getToken(TSLKGrammarParser.END, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).enterWhileBlock(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TSLKGrammarListener)
				((TSLKGrammarListener) listener).exitWhileBlock(this);
		}

		public ExprContext expr() {
			return getRuleContext(ExprContext.class, 0);
		}

		public TerminalNode WHILE() {
			return getToken(TSLKGrammarParser.WHILE, 0);
		}
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int NUMBER = 1, FUNC = 2, FOR = 3, WHILE = 4, IF = 5,
			ELSE = 6, THEN = 7, DO = 8, END = 9, OR = 10, AND = 11, LOCAL = 12,
			TABLE = 13, RETURN = 14, BREAK = 15, CONTINUE = 16, TRUE = 17,
			FALSE = 18, PLUSEQ = 19, MINUSEQ = 20, MULEQ = 21, DIVEQ = 22,
			REMEQ = 23, POWEQ = 24, LESSEQ = 25, LESS = 26, MOREEQ = 27,
			MORE = 28, EQ = 29, NOTEQ = 30, PLUS = 31, MINUS = 32, MUL = 33,
			DIV = 34, REM = 35, NOT = 36, LEN = 37, POW = 38, DOT = 39,
			COMMA = 40, SEMI = 41, COLON = 42, LPAREN = 43, RPAREN = 44,
			LBRACKET = 45, RBRACKET = 46, LBRACE = 47, RBRACE = 48,
			STRING = 49, SLCOMMENT = 50, MLCOMMENT = 51, WS = 52,
			GENERAL_ID = 53;
	public static final String[] tokenNames = { "<INVALID>", "NUMBER",
			"'function'", "'for'", "'while'", "'if'", "'else'", "'then'",
			"'do'", "'end'", "'or'", "'and'", "'local'", "'table'", "'return'",
			"'break'", "'continue'", "'true'", "'false'", "'+='", "'-='",
			"'*='", "'/='", "'%='", "'^='", "'<='", "'<'", "'>='", "'>'",
			"'='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", "'not'", "'#'",
			"'^'", "'.'", "','", "';'", "':'", "'('", "')'", "'['", "']'",
			"'{'", "'}'", "STRING", "SLCOMMENT", "MLCOMMENT", "WS",
			"GENERAL_ID" };
	public static final int RULE_body = 0, RULE_expr = 1, RULE_tablenode = 2,
			RULE_path = 3;
	public static final String[] ruleNames = { "body", "expr", "tablenode",
			"path" };
	public static final String _serializedATN = "\2\3\67\u00b1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\7\2\16\n\2\f"
			+ "\2\16\2\21\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3>\n\3\f\3\16\3A\13"
			+ "\3\3\3\3\3\5\3E\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13"
			+ "\3\5\3S\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3]\n\3\f\3\16\3`\13\3\5"
			+ "\3b\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3p\n\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3"
			+ "\u0084\n\3\f\3\16\3\u0087\13\3\3\4\3\4\5\4\u008b\n\4\3\4\3\4\3\5\3\5\3"
			+ "\5\3\5\3\5\3\5\5\5\u0095\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"
			+ "\5\3\5\3\5\7\5\u00a4\n\5\f\5\16\5\u00a7\13\5\5\5\u00a9\n\5\3\5\7\5\u00ac"
			+ "\n\5\f\5\16\5\u00af\13\5\3\5\4O\u00a5\6\2\4\6\b\2\b\4\"\"&\'\4\25\30%"
			+ "%\3#%\3!\"\3\33 \4\63\63\67\67\u00d1\2\17\3\2\2\2\4o\3\2\2\2\6\u008a\3"
			+ "\2\2\2\b\u0094\3\2\2\2\n\13\5\4\3\2\13\f\7+\2\2\f\16\3\2\2\2\r\n\3\2\2"
			+ "\2\16\21\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\17\3\2\2\2"
			+ "\22\23\b\3\1\2\23\24\t\2\2\2\24p\5\4\3\2\25\26\7\20\2\2\26p\5\4\3\2\27"
			+ "\30\7\16\2\2\30\31\7\67\2\2\31\32\7\37\2\2\32p\5\4\3\2\33\34\5\b\5\2\34"
			+ "\35\7\37\2\2\35\36\5\4\3\2\36p\3\2\2\2\37 \5\b\5\2 !\t\3\2\2!\"\5\4\3"
			+ "\2\"p\3\2\2\2#$\7\5\2\2$%\5\4\3\2%&\7+\2\2&\'\5\4\3\2\'(\7+\2\2()\5\4"
			+ "\3\2)*\7\n\2\2*+\5\2\2\2+,\7\13\2\2,p\3\2\2\2-.\7\6\2\2./\5\4\3\2/\60"
			+ "\7\n\2\2\60\61\5\2\2\2\61\62\7\13\2\2\62p\3\2\2\2\63\64\7\7\2\2\64\65"
			+ "\5\4\3\2\65\66\7\t\2\2\66?\5\2\2\2\678\7\b\2\289\7\7\2\29:\5\4\3\2:;\7"
			+ "\t\2\2;<\5\2\2\2<>\3\2\2\2=\67\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@"
			+ "D\3\2\2\2A?\3\2\2\2BC\7\b\2\2CE\5\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2"
			+ "FG\7\13\2\2Gp\3\2\2\2HI\7\4\2\2IR\7-\2\2JO\7\67\2\2KL\7*\2\2LN\7\67\2"
			+ "\2MK\3\2\2\2NQ\3\2\2\2OP\3\2\2\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2RJ\3\2\2"
			+ "\2RS\3\2\2\2ST\3\2\2\2TU\7.\2\2UV\5\2\2\2VW\7\13\2\2Wp\3\2\2\2Xa\7\17"
			+ "\2\2Y^\5\6\4\2Z[\7*\2\2[]\5\6\4\2\\Z\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3"
			+ "\2\2\2_b\3\2\2\2`^\3\2\2\2aY\3\2\2\2ab\3\2\2\2bc\3\2\2\2cp\7\13\2\2dp"
			+ "\7\21\2\2ep\7\22\2\2fp\7\3\2\2gp\7\63\2\2hp\7\23\2\2ip\7\24\2\2jp\5\b"
			+ "\5\2kl\7-\2\2lm\5\4\3\2mn\7.\2\2np\3\2\2\2o\22\3\2\2\2o\25\3\2\2\2o\27"
			+ "\3\2\2\2o\33\3\2\2\2o\37\3\2\2\2o#\3\2\2\2o-\3\2\2\2o\63\3\2\2\2oH\3\2"
			+ "\2\2oX\3\2\2\2od\3\2\2\2oe\3\2\2\2of\3\2\2\2og\3\2\2\2oh\3\2\2\2oi\3\2"
			+ "\2\2oj\3\2\2\2ok\3\2\2\2p\u0085\3\2\2\2qr\6\3\2\3rs\7(\2\2s\u0084\5\4"
			+ "\3\2tu\6\3\3\3uv\t\4\2\2v\u0084\5\4\3\2wx\6\3\4\3xy\t\5\2\2y\u0084\5\4"
			+ "\3\2z{\6\3\5\3{|\t\6\2\2|\u0084\5\4\3\2}~\6\3\6\3~\177\7\f\2\2\177\u0084"
			+ "\5\4\3\2\u0080\u0081\6\3\7\3\u0081\u0082\7\r\2\2\u0082\u0084\5\4\3\2\u0083"
			+ "q\3\2\2\2\u0083t\3\2\2\2\u0083w\3\2\2\2\u0083z\3\2\2\2\u0083}\3\2\2\2"
			+ "\u0083\u0080\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086"
			+ "\3\2\2\2\u0086\5\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089\t\7\2\2\u0089"
			+ "\u008b\7,\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2"
			+ "\2\2\u008c\u008d\5\4\3\2\u008d\7\3\2\2\2\u008e\u008f\b\5\1\2\u008f\u0095"
			+ "\7\67\2\2\u0090\u0091\7/\2\2\u0091\u0092\5\4\3\2\u0092\u0093\7\60\2\2"
			+ "\u0093\u0095\3\2\2\2\u0094\u008e\3\2\2\2\u0094\u0090\3\2\2\2\u0095\u00ad"
			+ "\3\2\2\2\u0096\u0097\6\5\b\3\u0097\u0098\7)\2\2\u0098\u00ac\7\67\2\2\u0099"
			+ "\u009a\6\5\t\3\u009a\u009b\7/\2\2\u009b\u009c\5\4\3\2\u009c\u009d\7\60"
			+ "\2\2\u009d\u00ac\3\2\2\2\u009e\u009f\6\5\n\3\u009f\u00a8\7-\2\2\u00a0"
			+ "\u00a5\5\4\3\2\u00a1\u00a2\7*\2\2\u00a2\u00a4\5\4\3\2\u00a3\u00a1\3\2"
			+ "\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6"
			+ "\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a0\3\2\2\2\u00a8\u00a9\3\2"
			+ "\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\7.\2\2\u00ab\u0096\3\2\2\2\u00ab"
			+ "\u0099\3\2\2\2\u00ab\u009e\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2"
			+ "\2\2\u00ad\u00ae\3\2\2\2\u00ae\t\3\2\2\2\u00af\u00ad\3\2\2\2\22\17?DO"
			+ "R^ao\u0083\u0085\u008a\u0094\u00a5\u00a8\u00ab\u00ad";

	public static final ATN _ATN = ATNSimulator.deserialize(_serializedATN
			.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}

	public TSLKGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA,
				_sharedContextCache);
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER)
						| (1L << FUNC) | (1L << FOR) | (1L << WHILE)
						| (1L << IF) | (1L << LOCAL) | (1L << TABLE)
						| (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE)
						| (1L << TRUE) | (1L << FALSE) | (1L << MINUS)
						| (1L << NOT) | (1L << LEN) | (1L << LPAREN)
						| (1L << LBRACKET) | (1L << STRING) | (1L << GENERAL_ID))) != 0)) {
					{
						{
							setState(8);
							expr(0);
							setState(9);
							match(SEMI);
						}
					}
					setState(15);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(109);
				switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
				case 1: {
					_localctx = new UnaryOperatorContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(17);
					((UnaryOperatorContext) _localctx).o = _input.LT(1);
					_la = _input.LA(1);
					if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS)
							| (1L << NOT) | (1L << LEN))) != 0))) {
						((UnaryOperatorContext) _localctx).o = (Token) _errHandler
								.recoverInline(this);
					}
					consume();
					setState(18);
					((UnaryOperatorContext) _localctx).e = expr(23);
				}
					break;

				case 2: {
					_localctx = new ReturnExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(19);
					match(RETURN);
					setState(20);
					((ReturnExprContext) _localctx).e = expr(12);
				}
					break;

				case 3: {
					_localctx = new LocalAssignExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(21);
					match(LOCAL);
					setState(22);
					((LocalAssignExprContext) _localctx).varid = match(GENERAL_ID);
					setState(23);
					((LocalAssignExprContext) _localctx).o = match(EQ);
					setState(24);
					((LocalAssignExprContext) _localctx).val = expr(9);
				}
					break;

				case 4: {
					_localctx = new AssignExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(25);
					((AssignExprContext) _localctx).varpath = path(0);
					setState(26);
					((AssignExprContext) _localctx).o = match(EQ);
					setState(27);
					((AssignExprContext) _localctx).val = expr(8);
				}
					break;

				case 5: {
					_localctx = new ModifyExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(29);
					((ModifyExprContext) _localctx).varpath = path(0);
					setState(30);
					((ModifyExprContext) _localctx).o = _input.LT(1);
					_la = _input.LA(1);
					if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUSEQ)
							| (1L << MINUSEQ) | (1L << MULEQ) | (1L << DIVEQ) | (1L << REM))) != 0))) {
						((ModifyExprContext) _localctx).o = (Token) _errHandler
								.recoverInline(this);
					}
					consume();
					setState(31);
					((ModifyExprContext) _localctx).val = expr(7);
				}
					break;

				case 6: {
					_localctx = new ForBlockContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(33);
					match(FOR);
					setState(34);
					((ForBlockContext) _localctx).initexpr = expr(0);
					setState(35);
					match(SEMI);
					setState(36);
					((ForBlockContext) _localctx).whileexpr = expr(0);
					setState(37);
					match(SEMI);
					setState(38);
					((ForBlockContext) _localctx).increxpr = expr(0);
					setState(39);
					match(DO);
					setState(40);
					((ForBlockContext) _localctx).forbody = body();
					setState(41);
					match(END);
				}
					break;

				case 7: {
					_localctx = new WhileBlockContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(43);
					match(WHILE);
					setState(44);
					((WhileBlockContext) _localctx).whileexpr = expr(0);
					setState(45);
					match(DO);
					setState(46);
					((WhileBlockContext) _localctx).whilebody = body();
					setState(47);
					match(END);
				}
					break;

				case 8: {
					_localctx = new IfBlockContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(49);
					match(IF);
					setState(50);
					((IfBlockContext) _localctx).ifexpr = expr(0);
					setState(51);
					match(THEN);
					setState(52);
					((IfBlockContext) _localctx).ifbody = body();
					setState(61);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
					while (_alt != 2 && _alt != -1) {
						if (_alt == 1) {
							{
								{
									setState(53);
									match(ELSE);
									setState(54);
									match(IF);
									setState(55);
									((IfBlockContext) _localctx).expr = expr(0);
									((IfBlockContext) _localctx).elifexprs
											.add(((IfBlockContext) _localctx).expr);
									setState(56);
									match(THEN);
									setState(57);
									((IfBlockContext) _localctx).body = body();
									((IfBlockContext) _localctx).elifbodies
											.add(((IfBlockContext) _localctx).body);
								}
							}
						}
						setState(63);
						_errHandler.sync(this);
						_alt = getInterpreter()
								.adaptivePredict(_input, 1, _ctx);
					}
					setState(66);
					_la = _input.LA(1);
					if (_la == ELSE) {
						{
							setState(64);
							match(ELSE);
							setState(65);
							((IfBlockContext) _localctx).elsebody = body();
						}
					}

					setState(68);
					match(END);
				}
					break;

				case 9: {
					_localctx = new FuncBlockContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(70);
					match(FUNC);
					setState(71);
					match(LPAREN);
					setState(80);
					_la = _input.LA(1);
					if (_la == GENERAL_ID) {
						{
							setState(72);
							((FuncBlockContext) _localctx).GENERAL_ID = match(GENERAL_ID);
							((FuncBlockContext) _localctx).args
									.add(((FuncBlockContext) _localctx).GENERAL_ID);
							setState(77);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input, 3,
									_ctx);
							while (_alt != 1 && _alt != -1) {
								if (_alt == 1 + 1) {
									{
										{
											setState(73);
											match(COMMA);
											setState(74);
											((FuncBlockContext) _localctx).GENERAL_ID = match(GENERAL_ID);
											((FuncBlockContext) _localctx).args
													.add(((FuncBlockContext) _localctx).GENERAL_ID);
										}
									}
								}
								setState(79);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,
										3, _ctx);
							}
						}
					}

					setState(82);
					match(RPAREN);
					setState(83);
					((FuncBlockContext) _localctx).funcbody = body();
					setState(84);
					match(END);
				}
					break;

				case 10: {
					_localctx = new TableBlockContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(86);
					match(TABLE);
					setState(95);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER)
							| (1L << FUNC) | (1L << FOR) | (1L << WHILE)
							| (1L << IF) | (1L << LOCAL) | (1L << TABLE)
							| (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE)
							| (1L << TRUE) | (1L << FALSE) | (1L << MINUS)
							| (1L << NOT) | (1L << LEN) | (1L << LPAREN)
							| (1L << LBRACKET) | (1L << STRING) | (1L << GENERAL_ID))) != 0)) {
						{
							setState(87);
							((TableBlockContext) _localctx).tablenode = tablenode();
							((TableBlockContext) _localctx).vals
									.add(((TableBlockContext) _localctx).tablenode);
							setState(92);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == COMMA) {
								{
									{
										setState(88);
										match(COMMA);
										setState(89);
										((TableBlockContext) _localctx).tablenode = tablenode();
										((TableBlockContext) _localctx).vals
												.add(((TableBlockContext) _localctx).tablenode);
									}
								}
								setState(94);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
						}
					}

					setState(97);
					match(END);
				}
					break;

				case 11: {
					_localctx = new BreakExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(98);
					match(BREAK);
				}
					break;

				case 12: {
					_localctx = new ContinueExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(99);
					match(CONTINUE);
				}
					break;

				case 13: {
					_localctx = new AtomNumberContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(100);
					match(NUMBER);
				}
					break;

				case 14: {
					_localctx = new AtomStringContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(101);
					match(STRING);
				}
					break;

				case 15: {
					_localctx = new AtomBooleanTrueContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(102);
					match(TRUE);
				}
					break;

				case 16: {
					_localctx = new AtomBooleanFalseContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(103);
					match(FALSE);
				}
					break;

				case 17: {
					_localctx = new PathCallContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(104);
					path(0);
				}
					break;

				case 18: {
					_localctx = new SubExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(105);
					match(LPAREN);
					setState(106);
					((SubExprContext) _localctx).e = expr(0);
					setState(107);
					match(RPAREN);
				}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
				while (_alt != 2 && _alt != -1) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(129);
							switch (getInterpreter().adaptivePredict(_input, 8,
									_ctx)) {
							case 1: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(111);
								if (!(24 >= _localctx._p))
									throw new FailedPredicateException(this,
											"24 >= $_p");
								setState(112);
								((BinaryOperatorContext) _localctx).o = match(POW);
								setState(113);
								((BinaryOperatorContext) _localctx).r = expr(25);
							}
								break;

							case 2: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(114);
								if (!(22 >= _localctx._p))
									throw new FailedPredicateException(this,
											"22 >= $_p");
								setState(115);
								((BinaryOperatorContext) _localctx).o = _input
										.LT(1);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL)
										| (1L << DIV) | (1L << REM))) != 0))) {
									((BinaryOperatorContext) _localctx).o = (Token) _errHandler
											.recoverInline(this);
								}
								consume();
								setState(116);
								((BinaryOperatorContext) _localctx).r = expr(23);
							}
								break;

							case 3: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(117);
								if (!(21 >= _localctx._p))
									throw new FailedPredicateException(this,
											"21 >= $_p");
								setState(118);
								((BinaryOperatorContext) _localctx).o = _input
										.LT(1);
								_la = _input.LA(1);
								if (!(_la == PLUS || _la == MINUS)) {
									((BinaryOperatorContext) _localctx).o = (Token) _errHandler
											.recoverInline(this);
								}
								consume();
								setState(119);
								((BinaryOperatorContext) _localctx).r = expr(22);
							}
								break;

							case 4: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(120);
								if (!(20 >= _localctx._p))
									throw new FailedPredicateException(this,
											"20 >= $_p");
								setState(121);
								((BinaryOperatorContext) _localctx).o = _input
										.LT(1);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESSEQ)
										| (1L << LESS)
										| (1L << MOREEQ)
										| (1L << MORE) | (1L << EQ) | (1L << NOTEQ))) != 0))) {
									((BinaryOperatorContext) _localctx).o = (Token) _errHandler
											.recoverInline(this);
								}
								consume();
								setState(122);
								((BinaryOperatorContext) _localctx).r = expr(21);
							}
								break;

							case 5: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(123);
								if (!(19 >= _localctx._p))
									throw new FailedPredicateException(this,
											"19 >= $_p");
								setState(124);
								((BinaryOperatorContext) _localctx).o = match(OR);
								setState(125);
								((BinaryOperatorContext) _localctx).r = expr(20);
							}
								break;

							case 6: {
								_localctx = new BinaryOperatorContext(
										new ExprContext(_parentctx,
												_parentState, _p));
								((BinaryOperatorContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_expr);
								setState(126);
								if (!(18 >= _localctx._p))
									throw new FailedPredicateException(this,
											"18 >= $_p");
								setState(127);
								((BinaryOperatorContext) _localctx).o = match(AND);
								setState(128);
								((BinaryOperatorContext) _localctx).r = expr(19);
							}
								break;
							}
						}
					}
					setState(133);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return 24 >= _localctx._p;

		case 1:
			return 22 >= _localctx._p;

		case 2:
			return 21 >= _localctx._p;

		case 3:
			return 20 >= _localctx._p;

		case 4:
			return 19 >= _localctx._p;

		case 5:
			return 18 >= _localctx._p;
		}
		return true;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public String getGrammarFileName() {
		return "TSLKGrammar.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String[] getTokenNames() {
		return tokenNames;
	}

	public final PathContext path(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PathContext _localctx = new PathContext(_ctx, _parentState, _p);
		PathContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_path);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(146);
				switch (_input.LA(1)) {
				case GENERAL_ID: {
					_localctx = new StaticChildCallContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(141);
					((StaticChildCallContext) _localctx).name = match(GENERAL_ID);
				}
					break;
				case LBRACKET: {
					_localctx = new DynamicChildCallContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(142);
					match(LBRACKET);
					setState(143);
					((DynamicChildCallContext) _localctx).r = expr(0);
					setState(144);
					match(RBRACKET);
				}
					break;
				default:
					throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				while (_alt != 2 && _alt != -1) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(169);
							switch (getInterpreter().adaptivePredict(_input,
									14, _ctx)) {
							case 1: {
								_localctx = new StaticChildCallContext(
										new PathContext(_parentctx,
												_parentState, _p));
								((StaticChildCallContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_path);
								setState(148);
								if (!(5 >= _localctx._p))
									throw new FailedPredicateException(this,
											"5 >= $_p");
								setState(149);
								match(DOT);
								setState(150);
								((StaticChildCallContext) _localctx).name = match(GENERAL_ID);
							}
								break;

							case 2: {
								_localctx = new DynamicChildCallContext(
										new PathContext(_parentctx,
												_parentState, _p));
								((DynamicChildCallContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_path);
								setState(151);
								if (!(4 >= _localctx._p))
									throw new FailedPredicateException(this,
											"4 >= $_p");
								setState(152);
								match(LBRACKET);
								setState(153);
								((DynamicChildCallContext) _localctx).r = expr(0);
								setState(154);
								match(RBRACKET);
							}
								break;

							case 3: {
								_localctx = new FuncCallContext(
										new PathContext(_parentctx,
												_parentState, _p));
								((FuncCallContext) _localctx).l = _prevctx;
								pushNewRecursionContext(_localctx, _startState,
										RULE_path);
								setState(156);
								if (!(3 >= _localctx._p))
									throw new FailedPredicateException(this,
											"3 >= $_p");
								{
									setState(157);
									match(LPAREN);
									setState(166);
									_la = _input.LA(1);
									if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER)
											| (1L << FUNC)
											| (1L << FOR)
											| (1L << WHILE)
											| (1L << IF)
											| (1L << LOCAL)
											| (1L << TABLE)
											| (1L << RETURN)
											| (1L << BREAK)
											| (1L << CONTINUE)
											| (1L << TRUE)
											| (1L << FALSE)
											| (1L << MINUS)
											| (1L << NOT)
											| (1L << LEN)
											| (1L << LPAREN)
											| (1L << LBRACKET)
											| (1L << STRING) | (1L << GENERAL_ID))) != 0)) {
										{
											setState(158);
											((FuncCallContext) _localctx).expr = expr(0);
											((FuncCallContext) _localctx).args
													.add(((FuncCallContext) _localctx).expr);
											setState(163);
											_errHandler.sync(this);
											_alt = getInterpreter()
													.adaptivePredict(_input,
															12, _ctx);
											while (_alt != 1 && _alt != -1) {
												if (_alt == 1 + 1) {
													{
														{
															setState(159);
															match(COMMA);
															setState(160);
															((FuncCallContext) _localctx).expr = expr(0);
															((FuncCallContext) _localctx).args
																	.add(((FuncCallContext) _localctx).expr);
														}
													}
												}
												setState(165);
												_errHandler.sync(this);
												_alt = getInterpreter()
														.adaptivePredict(
																_input, 12,
																_ctx);
											}
										}
									}

									setState(168);
									match(RPAREN);
								}
							}
								break;
							}
						}
					}
					setState(173);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	private boolean path_sempred(PathContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return 5 >= _localctx._p;

		case 7:
			return 4 >= _localctx._p;

		case 8:
			return 3 >= _localctx._p;
		}
		return true;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext) _localctx, predIndex);

		case 3:
			return path_sempred((PathContext) _localctx, predIndex);
		}
		return true;
	}

	public final TablenodeContext tablenode() throws RecognitionException {
		TablenodeContext _localctx = new TablenodeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tablenode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(136);
				switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
				case 1: {
					setState(134);
					((TablenodeContext) _localctx).key = _input.LT(1);
					_la = _input.LA(1);
					if (!(_la == STRING || _la == GENERAL_ID)) {
						((TablenodeContext) _localctx).key = (Token) _errHandler
								.recoverInline(this);
					}
					consume();
					setState(135);
					match(COLON);
				}
					break;
				}
				setState(138);
				((TablenodeContext) _localctx).val = expr(0);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}
}
