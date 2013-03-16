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
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.ng200.tslk.lang.TSLKGrammarLexer;
import org.ng200.tslk.lang.TSLKGrammarParser;
import org.ng200.tslk.lang.runtime.TSLKInstance;
import org.ng200.tslk.lang.runtime.TSLKRuntimeVisitor;

public class Test {

	/**
	 * Basic class for testing the interpreter.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TSLKGrammarLexer lexer = new TSLKGrammarLexer(new ANTLRInputStream(
				new FileInputStream("test.tslk")));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TSLKGrammarParser parser = new TSLKGrammarParser(tokens);
		ParseTree tree = parser.body();
		TSLKInstance instance = new TSLKInstance();
		TSLKRuntimeVisitor visitor = new TSLKRuntimeVisitor(instance);
		visitor.visit(tree);
	}

}
