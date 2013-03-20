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

import java.math.BigDecimal;

import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKBoolean extends TSLKObject {

	private boolean value;

	public TSLKBoolean(TSLKInstance instance, boolean value) {
		super(instance, Type.BOOLEAN);
		this.value = value;
	}

	@Override
	public boolean equals(TSLKObject obj) {
		if (obj == null)
			return false;
		return (obj instanceof TSLKBoolean && ((TSLKBoolean) obj).getValue() == this.value)
				|| (obj instanceof TSLKNumber && (((TSLKNumber) obj).getValue()
						.compareTo(BigDecimal.ZERO) == 0) != this.value)
				|| obj.toString().equals(this.toString());
	}

	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't index a boolean!");
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return ((Boolean) value).hashCode();
	}

	@Override
	public TSLKObject length() {
		throw new TSLKTypeMismatchException("Booleans don't have length!");
	}

	@Override
	public TSLKObject minus() {
		throw new TSLKTypeMismatchException("Can't invert a boolean!");
	}

	@Override
	public TSLKObject not() {
		return new TSLKBoolean(getTSLKInstance(), !value);
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("Can't index a boolean!");

	}

	@Override
	public String toString() {
		return value ? "true" : "false";
	}
}
