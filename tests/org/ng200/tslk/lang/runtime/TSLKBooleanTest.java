package org.ng200.tslk.lang.runtime;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKBooleanTest {
	private TSLKInstance instance = new TSLKInstance();
	private TSLKBoolean True = new TSLKBoolean(instance, true);
	private TSLKBoolean False = new TSLKBoolean(instance, false);
	private TSLKBinaryOperatorService binop;

	@Before
	public void setUp() {
		binop = instance.getBinaryOperatorService();
	}

	public void testAdd() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(1)),
				binop.add(False, False));
		assertEquals(new TSLKNumber(instance, new BigDecimal(1)),
				binop.add(True, False));
		assertEquals(new TSLKNumber(instance, new BigDecimal(1)),
				binop.add(False, True));
		assertEquals(new TSLKNumber(instance, new BigDecimal(2)),
				binop.add(True, True));
	}

	@Test
	public void testAnd() {
		assertEquals(true, ((TSLKBoolean) binop.and(True, True)).getValue());
		assertEquals(false, ((TSLKBoolean) binop.and(True, False)).getValue());
		assertEquals(false, ((TSLKBoolean) binop.and(False, True)).getValue());
		assertEquals(false, ((TSLKBoolean) binop.and(False, False)).getValue());
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivide() {
		binop.divide(True, False);
	}

	@Test
	public void testEqualsTSLKObject() {
		assertTrue(True.equals(True));
		assertTrue(False.equals(False));
		assertTrue(True.equals(new TSLKString(instance, "true")));
		assertTrue(False.equals(new TSLKString(instance, "false")));
		assertTrue(True.equals(new TSLKNumber(instance, new BigDecimal(1))));
		assertTrue(True.equals(new TSLKNumber(instance, new BigDecimal(-1))));
		assertTrue(True.equals(new TSLKNumber(instance, new BigDecimal(-10))));
		assertTrue(True.equals(new TSLKNumber(instance, new BigDecimal(10))));
		assertTrue(False.equals(new TSLKNumber(instance, new BigDecimal(0))));
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testExponentiate() {
		binop.exponentiate(True, False);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testGetAtIndex() {
		True.getAtIndex(False);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsLess() {
		binop.isLess(False, True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsLessOrEqual() {
		binop.isLessOrEqual(False, True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsMore() {
		binop.isMore(False, True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsMoreOrEqual() {
		binop.isMoreOrEqual(False, True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testLength() {
		True.length();
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMinus() {
		True.minus();
	}

	public void testMultiply() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0)),
				binop.multiply(True, False));
	}

	public void testNot() {
		assertEquals(False, True.not());
		assertEquals(True, False.not());
	}

	@Test
	public void testOr() {
		assertEquals(true, ((TSLKBoolean) binop.or(True, True)).getValue());
		assertEquals(true, ((TSLKBoolean) binop.or(True, False)).getValue());
		assertEquals(true, ((TSLKBoolean) binop.or(False, True)).getValue());
		assertEquals(false, ((TSLKBoolean) binop.or(False, False)).getValue());
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainder() {
		binop.remainder(False, True);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testSetAtIndex() {
		True.getAtIndex(False);
	}

	public void testSubtract() {
		assertEquals(new TSLKNumber(instance, new BigDecimal(0)),
				binop.subtract(False, False));
		assertEquals(new TSLKNumber(instance, new BigDecimal(1)),
				binop.subtract(True, False));
		assertEquals(new TSLKNumber(instance, new BigDecimal(-1)),
				binop.subtract(False, True));
		assertEquals(new TSLKNumber(instance, new BigDecimal(0)),
				binop.subtract(True, True));
	}

	@Test
	public void testToString() {
		assertEquals("true", True.toString());
		assertEquals("false", False.toString());
	}

}
