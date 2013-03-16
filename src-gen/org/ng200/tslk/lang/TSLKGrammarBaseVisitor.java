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

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class TSLKGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T>
		implements TSLKGrammarVisitor<T> {
	@Override
	public T visitAssignExpr(TSLKGrammarParser.AssignExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitAtomBooleanFalse(TSLKGrammarParser.AtomBooleanFalseContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitAtomBooleanTrue(TSLKGrammarParser.AtomBooleanTrueContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitAtomNumber(TSLKGrammarParser.AtomNumberContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitAtomString(TSLKGrammarParser.AtomStringContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitBinaryOperator(TSLKGrammarParser.BinaryOperatorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitBody(TSLKGrammarParser.BodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitBreakExpr(TSLKGrammarParser.BreakExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitContinueExpr(TSLKGrammarParser.ContinueExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitDynamicChildCall(TSLKGrammarParser.DynamicChildCallContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitForBlock(TSLKGrammarParser.ForBlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitFuncBlock(TSLKGrammarParser.FuncBlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitFuncCall(TSLKGrammarParser.FuncCallContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitIfBlock(TSLKGrammarParser.IfBlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitLocalAssignExpr(TSLKGrammarParser.LocalAssignExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitModifyExpr(TSLKGrammarParser.ModifyExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitPathCall(TSLKGrammarParser.PathCallContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitReturnExpr(TSLKGrammarParser.ReturnExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitStaticChildCall(TSLKGrammarParser.StaticChildCallContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitSubExpr(TSLKGrammarParser.SubExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitTableBlock(TSLKGrammarParser.TableBlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitTablenode(TSLKGrammarParser.TablenodeContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitUnaryOperator(TSLKGrammarParser.UnaryOperatorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public T visitWhileBlock(TSLKGrammarParser.WhileBlockContext ctx) {
		return visitChildren(ctx);
	}
}
