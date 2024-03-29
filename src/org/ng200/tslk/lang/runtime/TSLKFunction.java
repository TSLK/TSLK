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

import java.util.HashMap;

import org.ng200.tslk.lang.TSLKGrammarParser.BodyContext;
import org.ng200.tslk.lang.TSLKGrammarParser.FuncCallContext;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKFunction extends TSLKObject {

	private BodyContext body;
	private String[] names;

	public TSLKFunction(TSLKInstance instance, String[] names, BodyContext body) {
		super(instance, Type.FUNCTION);
		this.names = names;
		this.body = body;
	}

	@Override
	public boolean equals(TSLKObject obj) {
		if (!(obj instanceof TSLKFunction))
			return false;
		return body.equals(((TSLKFunction) obj).getBody());
	}

	public String[] getArguments() {
		return names;
	}

	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't index a function!");
	}

	public BodyContext getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}

	@Override
	public TSLKObject length() {
		throw new TSLKTypeMismatchException("Functions have no length!");
	}

	@Override
	public TSLKObject minus() {
		throw new TSLKTypeMismatchException("Can't invert functions!");
	}

	@Override
	public TSLKObject not() {
		throw new TSLKTypeMismatchException(
				"Can't perform bitwise operations on a function!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("Can't index a function!");
	}

	@Override
	public String toString() {
		return "function";
	}

	public TSLKObject visit(FuncCallContext ctx, TSLKRuntimeVisitor visitor) {
		TSLKObject returned = null;
		try {
			getTSLKInstance().getStepByStepManager().stepIn(ctx);
			visitor.getVariables().addFirst(new HashMap<String, TSLKObject>());
			for (int i = 0; i < ctx.args.size(); i++)
				visitor.getVariables()
						.peekFirst()
						.put(getArguments()[i],
								visitor.visitExpr(ctx.args.get(i)));
			TSLKObject result = visitor.visitBody(getBody());
			if (result != null) {
				if (result.getClass() == TSLKReturnInterruptObject.class)
					return ((TSLKInterruptObject) result).getPayload();
				else if (result.getType() == Type.INTERRUPT)
					throw new TSLKRuntimeException(
							"Unexpected break or continue - return should be used to exit a function!");
			}
		} finally {
			visitor.getVariables().removeFirst();
			getTSLKInstance().getStepByStepManager().stepOut(ctx);
		}
		return returned;
	}
}
