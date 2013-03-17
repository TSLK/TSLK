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

public class TSLKThrowableHacks {
	/*
	 * Oh god. This exists because ANTLR doesn't allow me to add throws
	 * clauses... Basically, RuntimeException that contains an
	 * InterruptedException. I hate myself.
	 */
	public static class TSLKInterupt extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2965293488942914057L;
		private InterruptedException exception;

		public TSLKInterupt(InterruptedException exception) {
			this.exception = exception;
		}

		public InterruptedException getException() {
			return exception;
		}
	}
}
