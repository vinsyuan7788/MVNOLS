package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.timer.CustomTimer;

/**
 * 	This is the class to perform testing regarding timer
 * 	1. Perform the testing of "Timer" class
 * 	2. Perform the testing of "Quartz" jars|framework
 * @author Vince Xu Yuan
 */
public class TestTimer {

	/**
	 * 	Test "Timer" class
	 * @throws Exception
	 */
	@Test
	public void testTimer () throws Exception {
		CustomTimer.main(null);
	}
	
	/**
	 * 	Test "Quartz" jars|framework
	 * @throws Exception
	 */
	@Test
	public void testQuartz () throws Exception {
		
	}
}
