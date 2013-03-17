// Generated from /home/nick/workspaces/tslk/TSLK/src-gen/org/ng200/tslk/lang/TSLKGrammar.g4 by ANTLR 4.0
package org.ng200.tslk.lang;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class TSLKGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements TSLKGrammarVisitor<T> {
	@Override public T visitBody(TSLKGrammarParser.BodyContext ctx) { return visitChildren(ctx); }

	@Override public T visitBreakStmt(TSLKGrammarParser.BreakStmtContext ctx) { return visitChildren(ctx); }

	@Override public T visitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitPathCall(TSLKGrammarParser.PathCallContext ctx) { return visitChildren(ctx); }

	@Override public T visitAtomString(TSLKGrammarParser.AtomStringContext ctx) { return visitChildren(ctx); }

	@Override public T visitAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx) { return visitChildren(ctx); }

	@Override public T visitAssignExpr(TSLKGrammarParser.AssignExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitTableBlock(TSLKGrammarParser.TableBlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitSubExpr(TSLKGrammarParser.SubExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx) { return visitChildren(ctx); }

	@Override public T visitContinueStmt(TSLKGrammarParser.ContinueStmtContext ctx) { return visitChildren(ctx); }

	@Override public T visitReturnStmt(TSLKGrammarParser.ReturnStmtContext ctx) { return visitChildren(ctx); }

	@Override public T visitNormalStmt(TSLKGrammarParser.NormalStmtContext ctx) { return visitChildren(ctx); }

	@Override public T visitFuncCall(TSLKGrammarParser.FuncCallContext ctx) { return visitChildren(ctx); }

	@Override public T visitIfBlock(TSLKGrammarParser.IfBlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx) { return visitChildren(ctx); }

	@Override public T visitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx) { return visitChildren(ctx); }

	@Override public T visitTablenode(TSLKGrammarParser.TablenodeContext ctx) { return visitChildren(ctx); }

	@Override public T visitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx) { return visitChildren(ctx); }

	@Override public T visitForBlock(TSLKGrammarParser.ForBlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx) { return visitChildren(ctx); }

	@Override public T visitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx) { return visitChildren(ctx); }
}