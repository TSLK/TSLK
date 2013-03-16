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

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TSLKGrammarBaseListener implements TSLKGrammarListener {
	@Override
	public void enterAssignExpr(TSLKGrammarParser.AssignExprContext ctx) {
	}

	@Override
	public void enterAtomBooleanFalse(
			TSLKGrammarParser.AtomBooleanFalseContext ctx) {
	}

	@Override
	public void enterAtomBooleanTrue(
			TSLKGrammarParser.AtomBooleanTrueContext ctx) {
	}

	@Override
	public void enterAtomNumber(TSLKGrammarParser.AtomNumberContext ctx) {
	}

	@Override
	public void enterAtomString(TSLKGrammarParser.AtomStringContext ctx) {
	}

	@Override
	public void enterBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx) {
	}

	@Override
	public void enterBody(TSLKGrammarParser.BodyContext ctx) {
	}

	@Override
	public void enterBreakExpr(TSLKGrammarParser.BreakExprContext ctx) {
	}

	@Override
	public void enterContinueExpr(TSLKGrammarParser.ContinueExprContext ctx) {
	}

	@Override
	public void enterDynamicChildCall(
			TSLKGrammarParser.DynamicChildCallContext ctx) {
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void enterForBlock(TSLKGrammarParser.ForBlockContext ctx) {
	}

	@Override
	public void enterFuncBlock(TSLKGrammarParser.FuncBlockContext ctx) {
	}

	@Override
	public void enterFuncCall(TSLKGrammarParser.FuncCallContext ctx) {
	}

	@Override
	public void enterIfBlock(TSLKGrammarParser.IfBlockContext ctx) {
	}

	@Override
	public void enterLocalAssignExpr(
			TSLKGrammarParser.LocalAssignExprContext ctx) {
	}

	@Override
	public void enterModifyExpr(TSLKGrammarParser.ModifyExprContext ctx) {
	}

	@Override
	public void enterPathCall(TSLKGrammarParser.PathCallContext ctx) {
	}

	@Override
	public void enterReturnExpr(TSLKGrammarParser.ReturnExprContext ctx) {
	}

	@Override
	public void enterStaticChildCall(
			TSLKGrammarParser.StaticChildCallContext ctx) {
	}

	@Override
	public void enterSubExpr(TSLKGrammarParser.SubExprContext ctx) {
	}

	@Override
	public void enterTableBlock(TSLKGrammarParser.TableBlockContext ctx) {
	}

	@Override
	public void enterTablenode(TSLKGrammarParser.TablenodeContext ctx) {
	}

	@Override
	public void enterUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx) {
	}

	@Override
	public void enterWhileBlock(TSLKGrammarParser.WhileBlockContext ctx) {
	}

	@Override
	public void exitAssignExpr(TSLKGrammarParser.AssignExprContext ctx) {
	}

	@Override
	public void exitAtomBooleanFalse(
			TSLKGrammarParser.AtomBooleanFalseContext ctx) {
	}

	@Override
	public void exitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx) {
	}

	@Override
	public void exitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx) {
	}

	@Override
	public void exitAtomString(TSLKGrammarParser.AtomStringContext ctx) {
	}

	@Override
	public void exitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx) {
	}

	@Override
	public void exitBody(TSLKGrammarParser.BodyContext ctx) {
	}

	@Override
	public void exitBreakExpr(TSLKGrammarParser.BreakExprContext ctx) {
	}

	@Override
	public void exitContinueExpr(TSLKGrammarParser.ContinueExprContext ctx) {
	}

	@Override
	public void exitDynamicChildCall(
			TSLKGrammarParser.DynamicChildCallContext ctx) {
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void exitForBlock(TSLKGrammarParser.ForBlockContext ctx) {
	}

	@Override
	public void exitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx) {
	}

	@Override
	public void exitFuncCall(TSLKGrammarParser.FuncCallContext ctx) {
	}

	@Override
	public void exitIfBlock(TSLKGrammarParser.IfBlockContext ctx) {
	}

	@Override
	public void exitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx) {
	}

	@Override
	public void exitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx) {
	}

	@Override
	public void exitPathCall(TSLKGrammarParser.PathCallContext ctx) {
	}

	@Override
	public void exitReturnExpr(TSLKGrammarParser.ReturnExprContext ctx) {
	}

	@Override
	public void exitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx) {
	}

	@Override
	public void exitSubExpr(TSLKGrammarParser.SubExprContext ctx) {
	}

	@Override
	public void exitTableBlock(TSLKGrammarParser.TableBlockContext ctx) {
	}

	@Override
	public void exitTablenode(TSLKGrammarParser.TablenodeContext ctx) {
	}

	@Override
	public void exitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx) {
	}

	@Override
	public void exitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx) {
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
	}

	@Override
	public void visitTerminal(TerminalNode node) {
	}
}