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

import org.ng200.tslk.lang.TSLKGrammarParser.FuncCallContext;

public class TSLKFunctionPrintln extends TSLKFunction {

	public TSLKFunctionPrintln(TSLKInstance instance) {
		super(instance, null, null);
	}

	@Override
	public TSLKObject visit(FuncCallContext ctx, TSLKRuntimeVisitor visitor) {
		System.out.println(visitor.visitExpr(ctx.args.get(0)).toString());
		return null;
	}
}
