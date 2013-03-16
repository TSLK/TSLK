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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKNumberTest {
	TSLKInstance instance = new TSLKInstance();

	@Test
	public void testAddNumber() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(51)),
				new TSLKNumber(instance, new BigDecimal(15))
						.add(new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(52)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.add(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(51.3)),
				new TSLKNumber(instance, new BigDecimal(15))
						.add(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(51.7)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.add(new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(new TSLKString(instance, "15.7test"), new TSLKNumber(
				instance, new BigDecimal(15.7)).add(new TSLKString(instance,
				"test")));
	}

	@Test
	public void testAnd() {
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(0b0100010)),
				new TSLKNumber(instance, new BigDecimal(0b1101110))
						.and(new TSLKNumber(instance, new BigDecimal(0b0100011))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b00000000)),
				new TSLKNumber(instance, new BigDecimal(0b11111111))
						.and(new TSLKNumber(instance,
								new BigDecimal(0b00000000))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b00000000)),
				new TSLKNumber(instance, new BigDecimal(0b00000000))
						.and(new TSLKNumber(instance,
								new BigDecimal(0b11111111))));
		assertEquals(new TSLKBoolean(instance, true),
				new TSLKNumber(instance, new BigDecimal(0b00010011))
						.and(new TSLKBoolean(instance, true)));
		assertEquals(new TSLKBoolean(instance, false),
				new TSLKNumber(instance, new BigDecimal(0b00000000))
						.and(new TSLKBoolean(instance, true)));
		assertEquals(new TSLKBoolean(instance, false),
				new TSLKNumber(instance, new BigDecimal(0b00000000))
						.and(new TSLKBoolean(instance, false)));
		assertEquals(new TSLKBoolean(instance, false),
				new TSLKNumber(instance, new BigDecimal(0b00000001))
						.and(new TSLKBoolean(instance, false)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeBoth() {
		new TSLKNumber(instance, new BigDecimal(2512.3)).and(new TSLKNumber(
				instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeLeft() {
		new TSLKNumber(instance, new BigDecimal(2512.3)).and(new TSLKNumber(
				instance, new BigDecimal(45)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeRight() {
		new TSLKNumber(instance, new BigDecimal(2512)).and(new TSLKNumber(
				instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testAndFailureString() {
		new TSLKNumber(instance, new BigDecimal(2512)).and(new TSLKString(
				instance, "foo"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testAndFailureTable() {
		new TSLKNumber(instance, new BigDecimal(2512)).and(new TSLKTable(
				instance));
	}

	@Test
	public void testDivide() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.081081081)),
				new TSLKNumber(instance, new BigDecimal(3))
						.divide(new TSLKNumber(instance, new BigDecimal(37))));
		assertEquals(
				new BigDecimal(3, TSLKNumber.DEFAULT).divide(
						new BigDecimal(37), TSLKNumber.DEFAULT).toString(),
				new TSLKNumber(instance, new BigDecimal(3)).divide(
						new TSLKNumber(instance, new BigDecimal(37)))
						.toString());
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.432506887)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.divide(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.41322314)),
				new TSLKNumber(instance, new BigDecimal(15))
						.divide(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.436111111)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.divide(new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivideNaN() {
		new TSLKNumber(instance, new BigDecimal(15.7)).divide(new TSLKString(
				instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivideNull() {
		new TSLKNumber(instance, new BigDecimal(15.7)).divide(null);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testDivideZero() {
		new TSLKNumber(instance, new BigDecimal(15.7)).divide(new TSLKNumber(
				instance, new BigDecimal(0)));
	}

	@Test
	public void testEquals() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.equals((Object) new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.equals((Object) new TSLKNumber(instance, new BigDecimal(
						55.5555))));
	}

	@Test
	public void testEqualsTSLKObject() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.equals(new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.equals(new TSLKNumber(instance, new BigDecimal(55.5555))));
	}

	@Test
	public void testExponentiate() {
		// TODO: expnentiation
	}

	@Test
	public void testIsLess() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(-15.6))
				.isLess(new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.isLess(new TSLKNumber(instance, new BigDecimal(55.55555))));
	}

	@Test
	public void testIsLessOrEqual() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(-15.6))
				.isLessOrEqual(new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.isLessOrEqual(new TSLKNumber(instance,
						new BigDecimal(55.55555))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.isLessOrEqual(new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.isLessOrEqual(new TSLKNumber(instance, new BigDecimal(55.5555))));
	}

	@Test
	public void testIsMore() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.isMore(new TSLKNumber(instance, new BigDecimal(-15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.55555))
				.isMore(new TSLKNumber(instance, new BigDecimal(55.5555))));
	}

	@Test
	public void testIsMoreOrEqual() {
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.isMoreOrEqual(new TSLKNumber(instance, new BigDecimal(-15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.55555))
				.isMoreOrEqual(new TSLKNumber(instance, new BigDecimal(55.5555))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(15.6))
				.isMoreOrEqual(new TSLKNumber(instance, new BigDecimal(15.6))));
		assertTrue(new TSLKNumber(instance, new BigDecimal(55.5555))
				.isMoreOrEqual(new TSLKNumber(instance, new BigDecimal(55.5555))));
	}

	@Test
	public void testIsWhole() {
		assertTrue(TSLKNumber.isWhole(new BigDecimal(176)));
		assertTrue(TSLKNumber.isWhole(new BigDecimal(-176)));
		assertTrue(!TSLKNumber.isWhole(new BigDecimal(176.6)));
		assertTrue(!TSLKNumber.isWhole(new BigDecimal(-176.6)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testLength() {
		new TSLKNumber(instance, new BigDecimal(15.7)).length();
	}

	@Test
	public void testMinus() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(-15)),
				new TSLKNumber(instance, new BigDecimal(15)).minus());
		assertEquals(new TSLKNumber(instance, new BigDecimal(-15.6)),
				new TSLKNumber(instance, new BigDecimal(15.6)).minus());
	}

	@Test
	public void testMultiply() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(540)),
				new TSLKNumber(instance, new BigDecimal(15))
						.multiply(new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(569.91)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.multiply(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(544.5)),
				new TSLKNumber(instance, new BigDecimal(15))
						.multiply(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(565.2)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.multiply(new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMultiplyNaN() {
		new TSLKNumber(instance, new BigDecimal(15.7)).multiply(new TSLKString(
				instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMultiplyNull() {
		new TSLKNumber(instance, new BigDecimal(15.7)).multiply(null);
	}

	@Test
	public void testOr() {
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(0b1101111)),
				new TSLKNumber(instance, new BigDecimal(0b1101110))
						.or(new TSLKNumber(instance, new BigDecimal(0b0100011))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(0b11111111)),
				new TSLKNumber(instance, new BigDecimal(0b11111111))
						.or(new TSLKNumber(instance, new BigDecimal(0b00000000))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(0b11111111)),
				new TSLKNumber(instance, new BigDecimal(0b00000000))
						.or(new TSLKNumber(instance, new BigDecimal(0b11111111))));
		assertEquals(new TSLKBoolean(instance, true), new TSLKNumber(instance,
				new BigDecimal(0b00010011)).or(new TSLKBoolean(instance, true)));
		assertEquals(new TSLKBoolean(instance, true), new TSLKNumber(instance,
				new BigDecimal(0b00000000)).or(new TSLKBoolean(instance, true)));
		assertEquals(new TSLKBoolean(instance, false),
				new TSLKNumber(instance, new BigDecimal(0b00000000))
						.or(new TSLKBoolean(instance, false)));
		assertEquals(new TSLKBoolean(instance, true),
				new TSLKNumber(instance, new BigDecimal(0b00000001))
						.or(new TSLKBoolean(instance, false)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeBoth() {
		new TSLKNumber(instance, new BigDecimal(2512.3)).or(new TSLKNumber(
				instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeLeft() {
		new TSLKNumber(instance, new BigDecimal(2512.3)).or(new TSLKNumber(
				instance, new BigDecimal(45)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeRight() {
		new TSLKNumber(instance, new BigDecimal(2512)).or(new TSLKNumber(
				instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testOrFailureString() {
		new TSLKNumber(instance, new BigDecimal(2512)).or(new TSLKString(
				instance, "foo"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testOrFailureTable() {
		new TSLKNumber(instance, new BigDecimal(2512)).or(new TSLKTable(
				instance));
	}

	@Test
	public void testRemainder() {
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(6)),
				new TSLKNumber(instance, new BigDecimal(80))
						.remainder(new TSLKNumber(instance, new BigDecimal(37))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(6.2)),
				new TSLKNumber(instance, new BigDecimal(80.2))
						.remainder(new TSLKNumber(instance, new BigDecimal(37))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainderNaN() {
		new TSLKNumber(instance, new BigDecimal(15.7))
				.remainder(new TSLKString(instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainderNull() {
		new TSLKNumber(instance, new BigDecimal(15.7)).remainder(null);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testRemainderZero() {
		new TSLKNumber(instance, new BigDecimal(15.7))
				.remainder(new TSLKNumber(instance, new BigDecimal(0)));
	}

	@Test
	public void testSubtract() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(-21)),
				new TSLKNumber(instance, new BigDecimal(15))
						.subtract(new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(-20.6)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.subtract(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(
				new TSLKNumber(instance, new BigDecimal(-21.3)),
				new TSLKNumber(instance, new BigDecimal(15))
						.subtract(new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(-20.3)),
				new TSLKNumber(instance, new BigDecimal(15.7))
						.subtract(new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testSubtractTypeFailureNaN() {
		new TSLKNumber(instance, new BigDecimal(15.7)).subtract(new TSLKString(
				instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testSubtractTypeFailureNull() {
		new TSLKNumber(instance, new BigDecimal(15.7)).subtract(null);
	}

}
