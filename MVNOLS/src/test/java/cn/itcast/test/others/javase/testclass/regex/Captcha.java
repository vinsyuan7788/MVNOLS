package cn.itcast.test.others.javase.testclass.regex;

import java.util.Random;

/**
 * 	This is the class to randomly generate a captcha
 * @author Vince Xu Yuan
 */
public class Captcha {
	
	/*	This is the object array to sample the number for each digit	 */
	private static Object[] objectArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A'};

	/**
	 * 	This is the static method to return a captcha
	 * 	1. The length of captcha is between 6 and 9
	 * 	2. The captcha may consist of number and letter (A)
	 * @return
	 * @throws Exception
	 */
	public static String getCaptcha () throws Exception {
		
		/*	Randomly decide the number of digits for captcha  */
		int numberOfDigits = (int) (Math.random()*4 + 6);
		
		/*	Get the captcha	 */
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < numberOfDigits; i++) {
			stringBuffer.append(objectArray[new Random().nextInt(objectArray.length)]);
		}
		return stringBuffer.toString();
	}
}
