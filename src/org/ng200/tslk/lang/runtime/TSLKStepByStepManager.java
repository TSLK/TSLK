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

import java.util.HashSet;

import org.antlr.v4.runtime.ParserRuleContext;

public class TSLKStepByStepManager {
	enum State {
		NONE, STEP, STEPIN, STEPOUT, STEPOVER
	}

	private State state = State.NONE;
	private HashSet<Integer> breakpoints = new HashSet<Integer>();
	private boolean enabled = false;

	public boolean canGoStepOver() {
		return state == State.STEP;
	}

	public boolean canGoStepUntilStepIn() {
		return state == State.STEP;
	}

	public boolean canGoStepUntilStepOut() {
		return state == State.STEP;
	}

	public void goStepOver() {
		if (canGoStepUntilStepIn())
			state = State.STEPOVER;
		else
			throw new RuntimeException(
					"The caller of the debugger didn't check if it is possible to change the state!");
	}

	public void goStepUntilStepIn() {
		if (canGoStepUntilStepIn())
			state = State.STEPIN;
		else
			throw new RuntimeException(
					"The caller of the debugger didn't check if it is possible to change the state!");
	}

	public void goStepUntilStepOut() {
		if (canGoStepUntilStepOut())
			state = State.STEPOUT;
		else
			throw new RuntimeException(
					"The caller of the debugger didn't check if it is possible to change the state!");
	}

	public void setBreakpoints(HashSet<Integer> breakpoints) {
		this.breakpoints = breakpoints;
	}

	public void step(ParserRuleContext ctx) {
		if (!enabled)
			return;
		for (int i = ctx.start.getLine(); i < ctx.stop.getLine(); i++)
			if (breakpoints.contains(i))
				state = State.STEP;
		while (state == State.STEP)
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new TSLKThrowableHacks.TSLKInterupt(e);
			}
	}

	public void stepIn(ParserRuleContext ctx) {
		if (state == State.STEPIN)
			state = State.STEP;
	}

	public void stepOut(ParserRuleContext ctx) {
		if (state == State.STEPOUT)
			state = State.STEP;
	}
}
