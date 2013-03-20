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
import java.math.MathContext;
import java.math.RoundingMode;

import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKNumber extends TSLKObject {

	private BigDecimal value;
	public static final MathContext DEFAULT = new MathContext(8,
			RoundingMode.HALF_EVEN);

	public static boolean isWhole(BigDecimal bd) {
		if (bd.scale() <= 0)
			return true;
		boolean ret;
		try {
			bd.toBigIntegerExact();
			ret = true;
		} catch (ArithmeticException ex) {
			ret = false;
		}
		return ret;
	}

	public TSLKNumber(TSLKInstance instance, BigDecimal value) {
		super(instance, Type.NUMBER);
		this.value = new BigDecimal(value.toString(), DEFAULT)
				.stripTrailingZeros(); // XXX: Probable performance problem
	}

	@Override
	public boolean equals(TSLKObject obj) {
		if (obj == null)
			return false;
		if (obj instanceof TSLKNumber)
			return value.compareTo(((TSLKNumber) obj).getValue()) == 0;
		throw new TSLKTypeMismatchException("Can't compare a number with "
				+ obj.getType().name().toLowerCase());
	}

	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't index a number!");
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public TSLKObject length() {
		throw new TSLKRuntimeException("Numbers have no length!");
	}

	@Override
	public TSLKObject minus() {
		return new TSLKNumber(getTSLKInstance(), value.negate());
	}

	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException(
				"The not operation can not be done on numbers in TSLK -"
						+ " use a boolean or array of booleans instead!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("Can't index a number!");
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
