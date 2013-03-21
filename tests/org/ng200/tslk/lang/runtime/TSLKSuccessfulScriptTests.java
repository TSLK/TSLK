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

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;

public class TSLKSuccessfulScriptTests {
	public static File baseDir = new File("tslk-tests/success");
	public static ArrayList<Test> testClasses = new ArrayList<Test>();

	public static abstract class Test {
		public Object[] getArguments() {
			return new Object[] { getFile(), getExpectedResults() };
		}

		public abstract File getFile();

		public abstract TSLKObject[] getExpectedResults();

	}

	static {
		testClasses.add(new Test() {
			@Override
			public File getFile() {
				return new File(baseDir, "primesieve.tslk");
			}

			@Override
			public TSLKObject[] getExpectedResults() {
				int limit = 10000;
				TSLKInstance instance = new TSLKInstance();
				ArrayList<TSLKObject> results = new ArrayList<TSLKObject>();
				BitSet compounds = new BitSet(limit);
				for (int i = 2; i < limit; i++) {
					if (compounds.get(i))
						continue;
					results.add(new TSLKNumber(instance, new BigDecimal(i)));
					for (int j = 2; i * j < limit; j++)
						compounds.set(i * j);
				}
				return results.toArray(new TSLKObject[results.size()]);
			}
		});
	}
}
