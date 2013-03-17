package org.ng200.tslk.lang.runtime;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKBooleanTest {
	private TSLKInstance instance = new TSLKInstance();
	private TSLKBoolean True = new TSLKBoolean(instance, true);
	private TSLKBoolean False = new TSLKBoolean(instance, false);

	@Test(expected = TSLKTypeMismatchException.class)
	public void testAdd() {
		True.add(False);
	}

	@Test
	public void testAnd() {
		assertEquals(true, ((TSLKBoolean) True.and(True)).getValue());
		assertEquals(false, ((TSLKBoolean) True.and(False)).getValue());
		assertEquals(false, ((TSLKBoolean) False.and(False)).getValue());
		assertEquals(false, ((TSLKBoolean) False.and(True)).getValue());
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testDivide() {
		True.divide(False);
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
		True.exponentiate(False);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testGetAtIndex() {
		True.getAtIndex(False);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsLess() {
		False.isLess(True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsLessOrEqual() {
		False.isLessOrEqual(True);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsMore() {
		True.isMore(False);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testIsMoreOrEqual() {
		True.isMoreOrEqual(False);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testLength() {
		True.length();
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMinus() {
		True.minus();
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testMultiply() {
		True.multiply(False);
	}

	public void testNot() {
		assertEquals(False, True.not());
		assertEquals(True, False.not());
	}

	@Test
	public void testOr() {
		assertEquals(true, ((TSLKBoolean) True.or(True)).getValue());
		assertEquals(true, ((TSLKBoolean) True.or(False)).getValue());
		assertEquals(false, ((TSLKBoolean) False.or(False)).getValue());
		assertEquals(true, ((TSLKBoolean) False.or(True)).getValue());
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testRemainder() {
		True.remainder(False);
	}

	@Test(expected = TSLKRuntimeException.class)
	public void testSetAtIndex() {
		True.getAtIndex(False);
	}

	@Test(expected = TSLKTypeMismatchException.class)
	public void testSubtract() {
		True.subtract(False);
	}

	@Test
	public void testToString() {
		assertEquals("true", True.toString());
		assertEquals("false", False.toString());
	}

}
