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

import org.junit.Before;
import org.junit.Test;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKNumberTest {
	private TSLKInstance instance = new TSLKInstance();
	private TSLKBinaryOperatorService binop;

	@Before
	public void setUp() {
		binop = instance.getBinaryOperatorService();
	}

	@Test
	public void testAddNumber() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(51)), binop.add(
				new TSLKNumber(instance, new BigDecimal(15)), new TSLKNumber(
						instance, new BigDecimal(36))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(52)), binop.add(
				new TSLKNumber(instance, new BigDecimal(15.7)), new TSLKNumber(
						instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(51.3)), binop.add(
				new TSLKNumber(instance, new BigDecimal(15)), new TSLKNumber(
						instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(51.7)), binop.add(
				new TSLKNumber(instance, new BigDecimal(15.7)), new TSLKNumber(
						instance, new BigDecimal(36))));
		assertEquals(new TSLKString(instance, "15.7test"), binop.add(
				new TSLKNumber(instance, new BigDecimal(15.7)), new TSLKString(
						instance, "test")));
	}

	@Test
	public void testAnd() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b0100010)),
				binop.and(new TSLKNumber(instance, new BigDecimal(0b1101110)),
						new TSLKNumber(instance, new BigDecimal(0b0100011))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b00000000)),
				binop.and(new TSLKNumber(instance, new BigDecimal(0b11111111)),
						new TSLKNumber(instance, new BigDecimal(0b00000000))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b00000000)),
				binop.and(new TSLKNumber(instance, new BigDecimal(0b00000000)),
						new TSLKNumber(instance, new BigDecimal(0b11111111))));
		assertEquals(new TSLKBoolean(instance, true), binop.and(new TSLKNumber(
				instance, new BigDecimal(0b00010011)), new TSLKBoolean(
				instance, true)));
		assertEquals(new TSLKBoolean(instance, false), binop.and(
				new TSLKNumber(instance, new BigDecimal(0b00000000)),
				new TSLKBoolean(instance, true)));
		assertEquals(new TSLKBoolean(instance, false), binop.and(
				new TSLKNumber(instance, new BigDecimal(0b00000000)),
				new TSLKBoolean(instance, false)));
		assertEquals(new TSLKBoolean(instance, false), binop.and(
				new TSLKNumber(instance, new BigDecimal(0b00000001)),
				new TSLKBoolean(instance, false)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeBoth() {
		binop.and(new TSLKNumber(instance, new BigDecimal(2512.3)),
				(new TSLKNumber(instance, new BigDecimal(45.78))));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeLeft() {
		binop.and(new TSLKNumber(instance, new BigDecimal(2512.3)),
				new TSLKNumber(instance, new BigDecimal(45)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testAndFailureNonWholeRight() {
		binop.and(new TSLKNumber(instance, new BigDecimal(2512)),
				new TSLKNumber(instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testAndFailureString() {
		binop.and(new TSLKNumber(instance, new BigDecimal(2512)),
				new TSLKString(instance, "foo"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testAndFailureTable() {
		binop.and(new TSLKNumber(instance, new BigDecimal(2512)),
				new TSLKTable(instance));
	}

	@Test
	public void testDivide() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.081081081)),
				binop.divide(new TSLKNumber(instance, new BigDecimal(3)),
						new TSLKNumber(instance, new BigDecimal(37))));
		assertEquals(
				new BigDecimal(3, TSLKNumber.DEFAULT).divide(
						new BigDecimal(37), TSLKNumber.DEFAULT).toString(),
				binop.divide(new TSLKNumber(instance, new BigDecimal(3)),
						new TSLKNumber(instance, new BigDecimal(37)))
						.toString());
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.432506887)),
				binop.divide(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.41322314)),
				binop.divide(new TSLKNumber(instance, new BigDecimal(15)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0.436111111)),
				binop.divide(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivideNaN() {
		binop.divide(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKString(instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivideNull() {
		binop.divide(new TSLKNumber(instance, new BigDecimal(15.7)), null);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testDivideZero() {
		binop.divide(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKNumber(instance, new BigDecimal(0)));
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
		assertTrue(binop.isLess(
				new TSLKNumber(instance, new BigDecimal(-15.6)),
				new TSLKNumber(instance, new BigDecimal(15.6))).getValue());
		assertTrue(binop.isLess(
				new TSLKNumber(instance, new BigDecimal(55.5555)),
				new TSLKNumber(instance, new BigDecimal(55.55555))).getValue());
	}

	@Test
	public void testIsLessOrEqual() {
		assertTrue(binop.isLessOrEqual(
				new TSLKNumber(instance, new BigDecimal(-15.6)),
				new TSLKNumber(instance, new BigDecimal(15.6))).getValue());
		assertTrue(binop.isLessOrEqual(
				new TSLKNumber(instance, new BigDecimal(55.5555)),
				new TSLKNumber(instance, new BigDecimal(55.55555))).getValue());
		assertTrue(binop.isLessOrEqual(
				new TSLKNumber(instance, new BigDecimal(15.6)),
				new TSLKNumber(instance, new BigDecimal(15.6))).getValue());
		assertTrue(binop.isLessOrEqual(
				new TSLKNumber(instance, new BigDecimal(55.5555)),
				new TSLKNumber(instance, new BigDecimal(55.5555))).getValue());
	}

	@Test
	public void testIsMore() {
		assertTrue(binop.isMore(new TSLKNumber(instance, new BigDecimal(15.6)),
				new TSLKNumber(instance, new BigDecimal(-15.6))).getValue());
		assertTrue(binop.isMore(
				new TSLKNumber(instance, new BigDecimal(55.55555)),
				new TSLKNumber(instance, new BigDecimal(55.5555))).getValue());
	}

	@Test
	public void testIsMoreOrEqual() {
		assertTrue(binop.isMoreOrEqual(
				new TSLKNumber(instance, new BigDecimal(15.6)),
				new TSLKNumber(instance, new BigDecimal(-15.6))).getValue());
		assertTrue(binop.isMoreOrEqual(
				new TSLKNumber(instance, new BigDecimal(55.55555)),
				new TSLKNumber(instance, new BigDecimal(55.5555))).getValue());
		assertTrue(binop.isMoreOrEqual(
				new TSLKNumber(instance, new BigDecimal(15.6)),
				new TSLKNumber(instance, new BigDecimal(15.6))).getValue());
		assertTrue(binop.isMoreOrEqual(
				new TSLKNumber(instance, new BigDecimal(55.5555)),
				new TSLKNumber(instance, new BigDecimal(55.5555))).getValue());
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
				binop.multiply(new TSLKNumber(instance, new BigDecimal(15)),
						new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(569.91)),
				binop.multiply(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(544.5)),
				binop.multiply(new TSLKNumber(instance, new BigDecimal(15)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(565.2)),
				binop.multiply(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMultiplyNaN() {
		binop.multiply(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKString(instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMultiplyNull() {
		binop.multiply(new TSLKNumber(instance, new BigDecimal(15.7)), null);
	}

	@Test
	public void testOr() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b1101111)),
				binop.or(new TSLKNumber(instance, new BigDecimal(0b1101110)),
						new TSLKNumber(instance, new BigDecimal(0b0100011))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b11111111)),
				binop.or(new TSLKNumber(instance, new BigDecimal(0b11111111)),
						new TSLKNumber(instance, new BigDecimal(0b00000000))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0b11111111)),
				binop.or(new TSLKNumber(instance, new BigDecimal(0b00000000)),
						new TSLKNumber(instance, new BigDecimal(0b11111111))));
		assertEquals(new TSLKBoolean(instance, true), binop.or(new TSLKNumber(
				instance, new BigDecimal(0b00010011)), new TSLKBoolean(
				instance, true)));
		assertEquals(new TSLKBoolean(instance, true), binop.or(new TSLKNumber(
				instance, new BigDecimal(0b00000000)), new TSLKBoolean(
				instance, true)));
		assertEquals(new TSLKBoolean(instance, false), binop.or(new TSLKNumber(
				instance, new BigDecimal(0b00000000)), new TSLKBoolean(
				instance, false)));
		assertEquals(new TSLKBoolean(instance, true), binop.or(new TSLKNumber(
				instance, new BigDecimal(0b00000001)), new TSLKBoolean(
				instance, false)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeBoth() {
		binop.or(new TSLKNumber(instance, new BigDecimal(2512.3)),
				new TSLKNumber(instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeLeft() {
		binop.or(new TSLKNumber(instance, new BigDecimal(2512.3)),
				new TSLKNumber(instance, new BigDecimal(45)));
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testOrFailureNonWholeRight() {
		binop.or(new TSLKNumber(instance, new BigDecimal(2512)),
				new TSLKNumber(instance, new BigDecimal(45.78)));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testOrFailureString() {
		binop.or(new TSLKNumber(instance, new BigDecimal(2512)),
				new TSLKString(instance, "foo"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testOrFailureTable() {
		binop.or(new TSLKNumber(instance, new BigDecimal(2512)), new TSLKTable(
				instance));
	}

	@Test
	public void testRemainder() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(6)),
				binop.remainder(new TSLKNumber(instance, new BigDecimal(80)),
						new TSLKNumber(instance, new BigDecimal(37))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(6.2)),
				binop.remainder(new TSLKNumber(instance, new BigDecimal(80.2)),
						new TSLKNumber(instance, new BigDecimal(37))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainderNaN() {
		binop.remainder(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKString(instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainderNull() {
		binop.remainder(new TSLKNumber(instance, new BigDecimal(15.7)), null);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testRemainderZero() {
		binop.remainder(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKNumber(instance, new BigDecimal(0)));
	}

	@Test
	public void testSubtract() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(-21)),
				binop.subtract(new TSLKNumber(instance, new BigDecimal(15)),
						new TSLKNumber(instance, new BigDecimal(36))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(-20.6)),
				binop.subtract(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(-21.3)),
				binop.subtract(new TSLKNumber(instance, new BigDecimal(15)),
						new TSLKNumber(instance, new BigDecimal(36.3))));
		assertEquals(new TSLKNumber(instance, new BigDecimal(-20.3)),
				binop.subtract(new TSLKNumber(instance, new BigDecimal(15.7)),
						new TSLKNumber(instance, new BigDecimal(36))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testSubtractTypeFailureNaN() {
		binop.subtract(new TSLKNumber(instance, new BigDecimal(15.7)),
				new TSLKString(instance, "test"));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testSubtractTypeFailureNull() {
		binop.subtract(new TSLKNumber(instance, new BigDecimal(15.7)), null);
	}

}
