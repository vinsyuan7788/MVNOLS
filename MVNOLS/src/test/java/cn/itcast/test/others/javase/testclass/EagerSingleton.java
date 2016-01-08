package cn.itcast.test.others.javase.testclass;

import cn.itcast.test.others.javase.testinterface.Run;

/**
 * 	This is a class for eager singleton pattern
 * 	1. Firstly privatize the class constructor:
 *     -- Make sure others cannot new an object
 * 	2. Declare the reference for current class & create a static and final instance inside the class:
 *     -- Make sure there is only one object in the memory
 *  3. Offer a public static getter to get this unique object
 */
public class EagerSingleton extends AbstractClass implements Run {
	
	/*	Create a static class object inside the class	 */
	private static final EagerSingleton INSTANCE = new EagerSingleton();
	
	/*	Privatize the class constructor	 */
	private EagerSingleton () {}

	/*	Offer a public static getter to get this unique object	*/
	public static EagerSingleton getEagerSingleton() {
		return INSTANCE;
	}
	
	/*	Other methods that are usable	*/
	@Override
	public void run () throws Exception {
		System.out.println("EagerSingleton.run()...");
	}

	@Override
	public void talk() throws Exception {
		System.out.println("EagerSingleton.talk()...");
	}
}
