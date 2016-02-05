package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.math.Captcha;

/**
 * 	This is the class to perform testing regarding math
 * 	-- Math.random()
 *  -- new Random().nextInt(int n)
 * @author Vince Xu Yuan
 */
public class TestMath {

	/**
	 * 	Test generate captcha with random sampling from an array 
	 * @throws Exception
	 */
	@Test
	public void testGenerateCaptcha () throws Exception {
		
		/*	Randomly generate a captcha	 */
		String captcha = Captcha.getCaptcha();
		
		/*	Output the captcha	*/
		System.out.println("The captcha: " + captcha);
	}
}
