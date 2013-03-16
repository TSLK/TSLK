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

import javax.naming.OperationNotSupportedException;

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
	public TSLKObject add(TSLKObject obj) {
		if (obj instanceof TSLKNumber)
			return new TSLKNumber(getTSLKInstance(),
					value.add(((TSLKNumber) obj).getValue()));
		return new TSLKString(getTSLKInstance(), this.toString() + obj);
	}

	@Override
	public TSLKObject and(TSLKObject obj) {
		if (!isWhole(value))
			throw new TSLKRuntimeException(
					"Bitwise operations can only be done on whole numbers and booleans!");
		if (obj instanceof TSLKBoolean)
			return new TSLKBoolean(getTSLKInstance(), !getValue().equals(
					BigDecimal.ZERO)
					&& (((TSLKBoolean) obj).getValue()));
		if ((obj instanceof TSLKNumber && !isWhole(((TSLKNumber) obj)
				.getValue())) || !(obj instanceof TSLKNumber))
			throw new TSLKTypeMismatchException(
					"Bitwise operations can only be done on whole numbers and booleans!");
		return new TSLKNumber(getTSLKInstance(), new BigDecimal(
				value.longValue() & ((TSLKNumber) obj).getValue().longValue()));
	}

	@Override
	public TSLKObject divide(TSLKObject obj) {
		if (obj instanceof TSLKNumber) {
			if (((TSLKNumber) obj).getValue().compareTo(BigDecimal.ZERO) == 0)
				throw new TSLKRuntimeException("Can't divide by zero!");
			return new TSLKNumber(getTSLKInstance(), value.divide(
					((TSLKNumber) obj).getValue(), 16, RoundingMode.HALF_UP));
		}
		if (obj == null)
			throw new TSLKTypeMismatchException(
					"Can't divide a number by null.");
		throw new TSLKTypeMismatchException("Can't divide a number by a "
				+ obj.getType().name().toLowerCase() + ".");
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
	public TSLKObject exponentiate(TSLKObject obj) {
		throw new RuntimeException(new OperationNotSupportedException(
				"Putting a number to a power is not yet supported."));
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
	public boolean isLess(TSLKObject obj) {
		if (obj == null)
			return false;
		if (obj instanceof TSLKNumber)
			return value.compareTo(((TSLKNumber) obj).getValue()) < 0;
		throw new TSLKTypeMismatchException("Can't compare a number with "
				+ obj.getType().name().toLowerCase());
	}

	@Override
	public boolean isLessOrEqual(TSLKObject obj) {
		if (obj == null)
			return false;
		if (obj instanceof TSLKNumber)
			return value.compareTo(((TSLKNumber) obj).getValue()) <= 0;
		throw new TSLKTypeMismatchException("Can't compare a number with "
				+ obj.getType().name().toLowerCase());
	}

	@Override
	public boolean isMore(TSLKObject obj) {
		if (obj == null)
			return true;
		if (obj instanceof TSLKNumber)
			return value.compareTo(((TSLKNumber) obj).getValue()) > 0;
		throw new TSLKTypeMismatchException("Can't compare a number with "
				+ obj.getType().name().toLowerCase());
	}

	@Override
	public boolean isMoreOrEqual(TSLKObject obj) {
		if (obj == null)
			return true;
		if (obj instanceof TSLKNumber)
			return value.compareTo(((TSLKNumber) obj).getValue()) >= 0;
		throw new TSLKTypeMismatchException("Can't compare a number with "
				+ obj.getType().name().toLowerCase());
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
	public TSLKObject multiply(TSLKObject obj) {
		if (obj instanceof TSLKNumber)
			return new TSLKNumber(getTSLKInstance(),
					value.multiply(((TSLKNumber) obj).getValue()));
		if (obj == null)
			throw new TSLKTypeMismatchException(
					"Can't multiply a number by null.");
		throw new TSLKTypeMismatchException("Can't multiply a number by a "
				+ obj.getType().name().toLowerCase() + ".");
	}

	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException(
				"The not operation can not be done on numbers in TSLK -"
						+ " use a boolean or array of booleans instead!");
	}

	@Override
	public TSLKObject or(TSLKObject obj) {
		if (!isWhole(value))
			throw new TSLKRuntimeException(
					"Bitwise operations can only be done on whole numbers and booleans!");
		if (obj instanceof TSLKBoolean)
			return new TSLKBoolean(getTSLKInstance(), !getValue().equals(
					BigDecimal.ZERO)
					|| (((TSLKBoolean) obj).getValue()));
		if ((obj instanceof TSLKNumber && !isWhole(((TSLKNumber) obj)
				.getValue())) || !(obj instanceof TSLKNumber))
			throw new TSLKTypeMismatchException(
					"Bitwise operations can only be done on whole numbers and booleans!");
		return new TSLKNumber(getTSLKInstance(), new BigDecimal(
				value.longValue() | ((TSLKNumber) obj).getValue().longValue()));
	}

	@Override
	public TSLKObject remainder(TSLKObject obj) {
		if (obj instanceof TSLKNumber) {
			if (((TSLKNumber) obj).getValue().compareTo(BigDecimal.ZERO) == 0)
				throw new TSLKRuntimeException(
						"Can't divide by zero - so there is no remainder either!");
			return new TSLKNumber(getTSLKInstance(),
					value.remainder(((TSLKNumber) obj).getValue()));
		}
		if (obj == null)
			throw new TSLKTypeMismatchException(
					"Can't divide a number by null - so there is no remainder either!");
		throw new TSLKTypeMismatchException("Can't divide a number by a "
				+ obj.getType().name().toLowerCase()
				+ " - so there is no remainder either!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("Can't index a number!");
	}

	@Override
	public TSLKObject subtract(TSLKObject obj) {
		if (obj instanceof TSLKNumber)
			return new TSLKNumber(getTSLKInstance(),
					value.subtract(((TSLKNumber) obj).getValue()));
		if (obj == null)
			throw new TSLKTypeMismatchException(
					"Can't subtract null from a number.");
		throw new TSLKTypeMismatchException("Can't subtract a "
				+ obj.getType().name().toLowerCase() + " from a number.");
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
