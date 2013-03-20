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
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;

public class TSLKTable extends TSLKObject {
	private HashMap<Integer, TSLKObject> children;

	public TSLKTable(TSLKInstance instance) {
		super(instance, Type.TABLE);
		children = new HashMap<Integer, TSLKObject>();
	}

	public TSLKTable(TSLKInstance instance,
			LinkedHashMap<Integer, TSLKObject> children) {
		super(instance, Type.TABLE);
		this.children = children;
	}

	@Override
	public boolean equals(TSLKObject obj) {
		// TODO: equals
		return false;
	}

	// @Override
	// public TSLKObject exponentiate(TSLKObject obj) {
	// throw new TSLKRuntimeException("Can't exponentiate a set!");
	// }

	@Override
	public TSLKObject getAtIndex(TSLKObject obj) {
		return children.get(obj.hashCode());
	}

	public HashMap<Integer, TSLKObject> getChildren() {
		return children;
	}

	@Override
	public int hashCode() {
		return children.hashCode();
	}

	@Override
	public TSLKObject length() {
		return new TSLKNumber(getTSLKInstance(),
				new BigDecimal(children.size()));
	}

	@Override
	public TSLKObject minus() {
		// TODO: reverse list
		return null;
	}

	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException(
				"Can't perform the not operation on a table!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		children.put(index.hashCode(), value);
	}

	@Override
	public String toString() {
		return children.toString();
	}

}
