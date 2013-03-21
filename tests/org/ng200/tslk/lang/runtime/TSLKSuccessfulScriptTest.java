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

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.ng200.tslk.lang.TSLKGrammarLexer;
import org.ng200.tslk.lang.TSLKGrammarParser;

@RunWith(Parameterized.class)
public class TSLKSuccessfulScriptTest {
	private File file;
	private TSLKGrammarLexer lexer;
	private CommonTokenStream tokens;
	private TSLKGrammarParser parser;
	private TSLKObject[] expectedResults;

	public TSLKSuccessfulScriptTest(File file, TSLKObject[] expectedResults) {
		super();
		this.file = file;
		this.expectedResults = expectedResults;
	}

	@Parameters
	public static Collection<Object[]> data() {
		ArrayList<Object[]> args = new ArrayList<Object[]>();
		for (TSLKSuccessfulScriptTests.Test t : TSLKSuccessfulScriptTests.testClasses)
			args.add(t.getArguments());
		return args;
	}

	@Before
	public void setUp() {
		try {
			lexer = new TSLKGrammarLexer(new ANTLRInputStream(
					new FileInputStream(file)));
			tokens = new CommonTokenStream(lexer);
			parser = new TSLKGrammarParser(tokens);
		} catch (IOException e) {
			e.printStackTrace();
			fail("IO exception while reading file!");
		}
	}

	@Test
	public void test() {
		ParseTree tree = parser.body();
		TSLKInstance instance = new TSLKInstance();
		TSLKRuntimeVisitor visitor = new TSLKRuntimeVisitor(instance);
		TSLKFunctionReportResult reporter = new TSLKFunctionReportResult(
				instance);
		visitor.getVariables().getLast().put("reportResult", reporter);
		visitor.visit(tree);
		TSLKObject[] result = reporter.getResults();
		if (result.length != expectedResults.length)
			fail("The script returned " + result.length
					+ " results, but it should have returned "
					+ expectedResults.length + "!");
		for (int i = 0; i < expectedResults.length; i++)
			if (!result[i].equals(expectedResults[i]))
				fail("The received result does not match the expected result! "
						+ result[i] + " is not equal to " + expectedResults[i]
						+ "!");
	}
}
