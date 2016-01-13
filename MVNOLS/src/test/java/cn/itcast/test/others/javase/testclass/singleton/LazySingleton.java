package cn.itcast.test.others.javase.testclass.singleton;

import cn.itcast.test.others.javase.testclass.abstraction.AbstractClass;
import cn.itcast.test.others.javase.testinterface.Run;

/**
 * 	This is a class for lazy singleton pattern
 * 	1. Firstly privatize the class constructor
 *     -- Make sure others cannot new an object
 * 	2. ONLY Declare the reference for current class (don't create the instance)
 *  3. Offer a public static getter to get this unique object
 *     -- Use double-checked locking pattern
 *     -- Before getting the object, do a predication:
 *        -- If the object is not instantiated, then create an instance
 *        -- If the object has been instantiated, then directly return the instance
 */
public class LazySingleton extends AbstractClass implements Run {

	/**	
	 * 	ONLY Declare the reference for current class	
	 */
	private static volatile LazySingleton INSTANCE;
	
	/**	
	 * 	Privatize the class constructor	 
	 */
	private LazySingleton () {}

	/**	
	 * 	Offer a public static getter to get this unique object: this should be used on or after J2SE 5.0	
	 */
	public static LazySingleton getLazySingleton() {
		
		/**
		 * 	Here is double-checked locking pattern
		 */
        if(INSTANCE == null){
            synchronized (LazySingleton.class) {
                /*	Before the instance is created, this predication will be done twice, however once the instance is created, this synchronized predication will never be done 	 */
                if(INSTANCE == null){ 
                	INSTANCE = new LazySingleton();
                 }
             } 
       }
       return INSTANCE;
	}
	
	/*	Other methods that are usable	*/
	@Override
	public void run () throws Exception {
		System.out.println("LazySingleton.run()...");
	}

	@Override
	public void talk() throws Exception {
		System.out.println("LazySingleton.talk()...");
	}
}
