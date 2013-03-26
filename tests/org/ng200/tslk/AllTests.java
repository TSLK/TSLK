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
package org.ng200.tslk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.ng200.tslk.lang.runtime.TSLKBooleanTest;
import org.ng200.tslk.lang.runtime.TSLKNumberTest;
import org.ng200.tslk.lang.runtime.TSLKScriptTester;

@RunWith(Suite.class)
@SuiteClasses({ TSLKBooleanTest.class, TSLKNumberTest.class,
		TSLKScriptTester.class })
public class AllTests {

}
