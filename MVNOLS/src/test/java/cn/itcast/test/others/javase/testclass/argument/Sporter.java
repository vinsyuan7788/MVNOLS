package cn.itcast.test.others.javase.testclass.argument;

import java.util.Arrays;

/**
 * 	This is the class to test variable-argument
 * @author Vince Xu Yuan
 */
public class Sporter {

	/**
	 * 	This is a static to report the running situation of a sporter
	 *  1. If there are multiple argumetns, variable-arguments MUST be put at the end
	 * @param weather
	 * @param miles
	 * @param locations
	 * @throws Exception
	 */
	public static void run(String weather, double miles, String... locations) throws Exception {
		System.out.println("Today's weahter: " + weather);
		System.out.println("Running miles: " + miles);
		System.out.println("Where have the sporter been: " + Arrays.toString(locations));
	}
}
