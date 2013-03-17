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
	public TSLKObject add(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject and(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject divide(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public boolean equals(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject exponentiate(TSLKObject obj) {
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
	public boolean isLess(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public boolean isLessOrEqual(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public boolean isMore(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public boolean isMoreOrEqual(TSLKObject obj) {
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
	public TSLKObject multiply(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject or(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject remainder(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public TSLKObject subtract(TSLKObject obj) {
		throw new TSLKRuntimeException("This method should never be called!");
	}

	@Override
	public String toString() {
		throw new TSLKRuntimeException("This method should never be called!");
	}

}
