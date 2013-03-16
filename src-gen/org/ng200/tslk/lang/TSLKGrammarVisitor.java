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

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface TSLKGrammarVisitor<T> extends ParseTreeVisitor<T> {
	T visitAssignExpr(TSLKGrammarParser.AssignExprContext ctx);

	T visitAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx);

	T visitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx);

	T visitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx);

	T visitAtomString(TSLKGrammarParser.AtomStringContext ctx);

	T visitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx);

	T visitBody(TSLKGrammarParser.BodyContext ctx);

	T visitBreakExpr(TSLKGrammarParser.BreakExprContext ctx);

	T visitContinueExpr(TSLKGrammarParser.ContinueExprContext ctx);

	T visitDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx);

	T visitForBlock(TSLKGrammarParser.ForBlockContext ctx);

	T visitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx);

	T visitFuncCall(TSLKGrammarParser.FuncCallContext ctx);

	T visitIfBlock(TSLKGrammarParser.IfBlockContext ctx);

	T visitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx);

	T visitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx);

	T visitPathCall(TSLKGrammarParser.PathCallContext ctx);

	T visitReturnExpr(TSLKGrammarParser.ReturnExprContext ctx);

	T visitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx);

	T visitSubExpr(TSLKGrammarParser.SubExprContext ctx);

	T visitTableBlock(TSLKGrammarParser.TableBlockContext ctx);

	T visitTablenode(TSLKGrammarParser.TablenodeContext ctx);

	T visitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx);

	T visitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx);
}
