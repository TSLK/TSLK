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

public class TSLKRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 58263979740780013L;

	public TSLKRuntimeException() {
		super();
	}

	public TSLKRuntimeException(String string) {
		super(string);
	}

	public TSLKRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TSLKRuntimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TSLKRuntimeException(Throwable cause) {
		super(cause);
	}

}
