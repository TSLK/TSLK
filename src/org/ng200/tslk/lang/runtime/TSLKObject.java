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

public abstract class TSLKObject {
	enum Type {
		NUMBER, STRING, BOOLEAN, TABLE, UNKNOWN, FUNCTION, LIST, INTERRUPT, NULL
	}

	private TSLKInstance instance;

	private Type type = Type.UNKNOWN;

	public TSLKObject(TSLKInstance instance, Type type) {
		this.instance = instance;
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TSLKObject))
			return false;
		return this.equals((TSLKObject) obj);
	}

	public abstract boolean equals(TSLKObject obj);

	public abstract TSLKObject getAtIndex(TSLKObject obj);

	public TSLKInstance getTSLKInstance() {
		return instance;
	}

	public Type getType() {
		return type;
	}

	@Override
	public abstract int hashCode();

	public abstract TSLKObject length();

	public abstract TSLKObject minus();

	public abstract TSLKObject not();

	public abstract void setAtIndex(TSLKObject index, TSLKObject value);

	@Override
	public abstract String toString();

}
