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
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

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
	public TSLKObject add(TSLKObject obj) {
		if (!(obj instanceof TSLKTable))
			throw new TSLKTypeMismatchException(
					"The union operation can only be performed on two tables!");
		LinkedHashMap<Integer, TSLKObject> union = new LinkedHashMap<Integer, TSLKObject>(
				children);
		union.putAll(((TSLKTable) obj).getChildren());
		return new TSLKTable(getTSLKInstance(), union);
	}

	@Override
	public TSLKObject and(TSLKObject obj) {
		if (!(obj instanceof TSLKTable))
			throw new TSLKTypeMismatchException(
					"The intersection operation can only be performed on two tables!");
		LinkedHashMap<Integer, TSLKObject> intersect = new LinkedHashMap<Integer, TSLKObject>();
		TSLKTable table = ((TSLKTable) obj);
		for (Integer i : table.getChildren().keySet())
			if (children.containsKey(i)) {
				if (table.getChildren().get(i).equals(children.get(i)))
					intersect.put(i, children.get(i));
				else
					throw new TSLKRuntimeException(
							"The intersection operation values keys,"
									+ " but the values should be equal!");
			}
		return new TSLKTable(getTSLKInstance(), intersect);
	}

	@Override
	public TSLKObject divide(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't divide a table!");
	}

	@Override
	public boolean equals(TSLKObject obj) {
		// TODO: equals
		return false;
	}

	@Override
	public TSLKObject exponentiate(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't exponentiate a set!");
	}

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
	public boolean isLess(TSLKObject obj) {
		throw new TSLKRuntimeException(
				"Can't compare sets for anything more than equality!");
	}

	@Override
	public boolean isLessOrEqual(TSLKObject obj) {
		return equals(obj);
	}

	@Override
	public boolean isMore(TSLKObject obj) {
		throw new TSLKRuntimeException(
				"Can't compare sets for anything more than equality!");
	}

	@Override
	public boolean isMoreOrEqual(TSLKObject obj) {
		return equals(obj);
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
	public TSLKObject multiply(TSLKObject obj) {
		throw new TSLKRuntimeException("Can't multiply a table!");
	}

	@Override
	public TSLKObject not() {
		throw new TSLKRuntimeException(
				"Can't perform the not operation on a table!");
	}

	@Override
	public TSLKObject or(TSLKObject obj) {
		return add(obj);
	}

	@Override
	public TSLKObject remainder(TSLKObject obj) {
		throw new TSLKRuntimeException(
				"Can't divide a table - therefore, no remainder exists!");
	}

	@Override
	public void setAtIndex(TSLKObject index, TSLKObject value) {
		children.put(index.hashCode(), value);
	}

	@Override
	public TSLKObject subtract(TSLKObject obj) {
		if (!(obj instanceof TSLKTable))
			throw new TSLKTypeMismatchException(
					"The set subtraction operation can only be performed on two tables!");
		LinkedHashMap<Integer, TSLKObject> subtract = new LinkedHashMap<Integer, TSLKObject>(
				children);
		for (Integer i : ((TSLKTable) obj).getChildren().keySet())
			if (subtract.containsKey(i))
				subtract.remove(i);
		return new TSLKTable(getTSLKInstance(), subtract);
	}

	@Override
	public String toString() {
		return children.toString();
	}

}
