package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.assertion.Computer;

/**
 * 	This is a class to perform testing regarding assertion
 *  1. Assertion is disabled by default:
 *     -- Should enable assertion to use it
 * 	2. If the assertion fails, throw an AssertionError
 *     -- Should be used during development and debug, not after being released|launched
 *  3. Should NOT be used for input validation to a public method
 *     -- Better option: IllegalArgumenetException, etc.
 */
public class TestAssertion {

	/**
	 * 	Test assertion
	 * @throws Exception
	 */
	@Test
	public void testAssertion () throws Exception {
		Computer.main(null);
	}
}
