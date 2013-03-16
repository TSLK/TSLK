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

import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface TSLKGrammarListener extends ParseTreeListener {
	void enterAssignExpr(TSLKGrammarParser.AssignExprContext ctx);

	void enterAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx);

	void enterAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx);

	void enterAtomNumber(TSLKGrammarParser.AtomNumberContext ctx);

	void enterAtomString(TSLKGrammarParser.AtomStringContext ctx);

	void enterBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx);

	void enterBody(TSLKGrammarParser.BodyContext ctx);

	void enterBreakExpr(TSLKGrammarParser.BreakExprContext ctx);

	void enterContinueExpr(TSLKGrammarParser.ContinueExprContext ctx);

	void enterDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx);

	void enterForBlock(TSLKGrammarParser.ForBlockContext ctx);

	void enterFuncBlock(TSLKGrammarParser.FuncBlockContext ctx);

	void enterFuncCall(TSLKGrammarParser.FuncCallContext ctx);

	void enterIfBlock(TSLKGrammarParser.IfBlockContext ctx);

	void enterLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx);

	void enterModifyExpr(TSLKGrammarParser.ModifyExprContext ctx);

	void enterPathCall(TSLKGrammarParser.PathCallContext ctx);

	void enterReturnExpr(TSLKGrammarParser.ReturnExprContext ctx);

	void enterStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx);

	void enterSubExpr(TSLKGrammarParser.SubExprContext ctx);

	void enterTableBlock(TSLKGrammarParser.TableBlockContext ctx);

	void enterTablenode(TSLKGrammarParser.TablenodeContext ctx);

	void enterUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx);

	void enterWhileBlock(TSLKGrammarParser.WhileBlockContext ctx);

	void exitAssignExpr(TSLKGrammarParser.AssignExprContext ctx);

	void exitAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx);

	void exitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx);

	void exitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx);

	void exitAtomString(TSLKGrammarParser.AtomStringContext ctx);

	void exitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx);

	void exitBody(TSLKGrammarParser.BodyContext ctx);

	void exitBreakExpr(TSLKGrammarParser.BreakExprContext ctx);

	void exitContinueExpr(TSLKGrammarParser.ContinueExprContext ctx);

	void exitDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx);

	void exitForBlock(TSLKGrammarParser.ForBlockContext ctx);

	void exitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx);

	void exitFuncCall(TSLKGrammarParser.FuncCallContext ctx);

	void exitIfBlock(TSLKGrammarParser.IfBlockContext ctx);

	void exitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx);

	void exitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx);

	void exitPathCall(TSLKGrammarParser.PathCallContext ctx);

	void exitReturnExpr(TSLKGrammarParser.ReturnExprContext ctx);

	void exitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx);

	void exitSubExpr(TSLKGrammarParser.SubExprContext ctx);

	void exitTableBlock(TSLKGrammarParser.TableBlockContext ctx);

	void exitTablenode(TSLKGrammarParser.TablenodeContext ctx);

	void exitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx);

	void exitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx);
}
