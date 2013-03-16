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

public class TSLKString extends TSLKObject {
	private String value;

	public TSLKString(TSLKInstance instance, String value) {
		super(instance, Type.STRING);
		this.value = value;
	}

	@Override
	public TSLKObject add(TSLKObject obj) {
		return new TSLKString(getTSLKInstance(), this.toString()
				+ obj.toString());
	}

	@Override
	public TSLKObject and(TSLKObject obj) {
		throw new TSLKTypeMismatchException(
				"Can't perform bitwise operations on a string!");
	}

	@Override
	public TSLKObject divide(TSLKObject obj) {
		throw new TSLKTypeMismatchException(
				"Can't perform division on a string!");
	}

	@Override
	public boolean equals(TSLKObject obj) {
		return this.toString().compareTo(obj.toString()) == 0;
	}

	@Override
	public TSLKObject exponentiate(TSLKObject obj) {
		throw new TSLKTypeMismatchException("Can't exponentiate a string!");
	}

	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		if (obj instanceof TSLKNumber
				&& TSLKNumber.isWhole(((TSLKNumber) obj).getValue())) {
			int index = ((TSLKNumber) obj).getValue().intValue();
			return new TSLKString(getTSLKInstance(), new String(
					new char[] { toString().charAt(index) }));
		}
		throw new TSLKRuntimeException(
				"Strings can only be indexed by a whole number!");
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean isLess(TSLKObject obj) {
		return this.toString().compareTo(obj.toString()) < 0;
	}

	@Override
	public boolean isLessOrEqual(TSLKObject obj) {
		return this.toString().compareTo(obj.toString()) <= 0;
	}

	@Override
	public boolean isMore(TSLKObject obj) {
		return this.toString().compareTo(obj.toString()) > 0;
	}

	@Override
	public boolean isMoreOrEqual(TSLKObject obj) {
		return this.toString().compareTo(obj.toString()) >= 0;
	}

	@Override
	public TSLKObject length() {
		return new TSLKNumber(getTSLKInstance(), new BigDecimal(this.toString()
				.length()));
	}

	@Override
	public TSLKObject minus() {
		// TODO: Reverse string
		return null;
	}

	@Override
	public TSLKObject multiply(TSLKObject obj) {
		// TODO: implement a repeat function
		return null;
	}

	@Override
	public TSLKObject not() {
		throw new TSLKTypeMismatchException(
				"Can't perform bitwise operations on a string!");
	}

	@Override
	public TSLKObject or(TSLKObject obj) {
		throw new TSLKTypeMismatchException(
				"Can't perform bitwise operations on a string!");
	}

	@Override
	public TSLKObject remainder(TSLKObject obj) {
		throw new TSLKTypeMismatchException(
				"Can't perform a remainder calculation operation on a string!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("You can't change a string directly!");
	}

	@Override
	public TSLKObject subtract(TSLKObject obj) {
		throw new TSLKTypeMismatchException(
				"Can't perform subtraction on a string!");
	}

	@Override
	public String toString() {
		return value;
	}
}
