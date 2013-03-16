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

	/*
	 * These classes ugly hacks that make me cringe. Basically, when "return",
	 * "continue" or "break" are called, one of the exceptions is thrown and the
	 * function is responsible for catching it. Basically, abuse of Java's
	 * exceptions...
	 */
	public static class TSLKReturnThrowable extends RuntimeException {
		/**
	 * 
	 */
		private static final long serialVersionUID = 8716311273520296718L;
		private TSLKObject ret;

		public TSLKReturnThrowable(TSLKObject ret) {
			this.ret = ret;
		}

		public TSLKObject getReturnObject() {
			return ret;
		}
	}

	public static class TSLKBreakThrowable extends RuntimeException {

		/**
			 * 
			 */
		private static final long serialVersionUID = -486938514767778939L;

	}

	public static class TSLKContinueThrowable extends RuntimeException {

		/**
			 * 
			 */
		private static final long serialVersionUID = -5186309429177577500L;

	}

}
