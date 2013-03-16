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
package org.ng200.tslk.lang.runtime.exceptions;

public class TSLKTypeMismatchException extends TSLKRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137789301837146242L;

	public TSLKTypeMismatchException() {
		super();
	}

	public TSLKTypeMismatchException(String string) {
		super(string);
	}

	public TSLKTypeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public TSLKTypeMismatchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TSLKTypeMismatchException(Throwable cause) {
		super(cause);
	}

}
