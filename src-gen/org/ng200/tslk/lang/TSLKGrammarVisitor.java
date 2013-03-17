// Generated from /home/nick/workspaces/tslk/TSLK/src-gen/org/ng200/tslk/lang/TSLKGrammar.g4 by ANTLR 4.0
package org.ng200.tslk.lang;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface TSLKGrammarVisitor<T> extends ParseTreeVisitor<T> {
	T visitBody(TSLKGrammarParser.BodyContext ctx);

	T visitBreakStmt(TSLKGrammarParser.BreakStmtContext ctx);

	T visitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx);

	T visitPathCall(TSLKGrammarParser.PathCallContext ctx);

	T visitAtomString(TSLKGrammarParser.AtomStringContext ctx);

	T visitAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx);

	T visitAssignExpr(TSLKGrammarParser.AssignExprContext ctx);

	T visitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx);

	T visitTableBlock(TSLKGrammarParser.TableBlockContext ctx);

	T visitSubExpr(TSLKGrammarParser.SubExprContext ctx);

	T visitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx);

	T visitContinueStmt(TSLKGrammarParser.ContinueStmtContext ctx);

	T visitReturnStmt(TSLKGrammarParser.ReturnStmtContext ctx);

	T visitNormalStmt(TSLKGrammarParser.NormalStmtContext ctx);

	T visitFuncCall(TSLKGrammarParser.FuncCallContext ctx);

	T visitIfBlock(TSLKGrammarParser.IfBlockContext ctx);

	T visitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx);

	T visitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx);

	T visitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx);

	T visitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx);

	T visitTablenode(TSLKGrammarParser.TablenodeContext ctx);

	T visitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx);

	T visitForBlock(TSLKGrammarParser.ForBlockContext ctx);

	T visitDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx);

	T visitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx);
}