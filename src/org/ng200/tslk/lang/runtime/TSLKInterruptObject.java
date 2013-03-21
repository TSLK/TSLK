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

import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;

public class TSLKInterruptObject extends TSLKObject {
	private final TSLKObject payload;

	public TSLKInterruptObject(TSLKInstance instance, TSLKObject payload) {
		super(instance, Type.INTERRUPT);
		this.payload = payload;
	}

	public TSLKObject getPayload() {
		return payload;
	}


	@Override
	public boolean equals(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}


	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public int hashCode() {
		throw new TSLKRuntimeException("This method should never be called!");
	}


	@Override
	public TSLKObject length() {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject minus() {
		throw new TSLKRuntimeException("This method should never be called!");
	}


	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException("This method should never be called!");
	}


	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("This method should never be called!");
	}


	@Override
	public String toString() {
		throw new TSLKRuntimeException("This method should never be called!");
	}

}
