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

import java.util.ArrayList;

import org.ng200.tslk.lang.TSLKGrammarParser.ExprContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncCallContext;

public class TSLKFunctionReportResult extends TSLKFunction {
	private ArrayList<TSLKObject> results = new ArrayList<TSLKObject>();

	public TSLKFunctionReportResult(TSLKInstance instance) {
		super(instance, null, null);
	}

	@Override
	public TSLKObject visit(FuncCallContext ctx, TSLKRuntimeVisitor visitor) {
		for (ExprContext expr : ctx.args)
			results.add(visitor.visitExpr(expr));
		return null;
	}

	public TSLKObject[] getResults() {
		return results.toArray(new TSLKObject[results.size()]);
	}
}
