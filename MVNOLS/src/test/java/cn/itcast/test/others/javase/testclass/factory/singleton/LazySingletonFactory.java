package cn.itcast.test.others.javase.testclass.factory.singleton;

/**
 * 	This is a class for singleton factory to return a singleton instance of any class
 * 	1. ONLY Declare the reference for Object class
 *  2. Privatize the setter 
 *  3. Offer a public static getter to get this unique object: this should be used on or after J2SE 5.0
 *     -- Use double-checked locking pattern
 *     -- Use reflection: "Class clazz.newIntasnce()"
 */
public class LazySingletonFactory {

	/**	
	 * 	ONLY Declare the reference for Object class	
	 */
	private static volatile Object INSTANCE;
	
	/**	
	 * 	Privatize the setter 
	 */
	private static void setINSTANCE(Object instance) {
		INSTANCE = instance;
	}

	/**	
	 * 	Offer a public static getter to get this unique object: this should be used on or after J2SE 5.0	
	 */
	public static Object getInstance (Class clazz) throws Exception {
		
		/**
		 * 	Here is double-checked locking pattern
		 */
        if(INSTANCE == null){
            synchronized (LazySingletonFactory.class) {
                /*	Before the instance is created, this predication will be done twice, however once the instance is created, this synchronized predication will never be done 	 */
                if(INSTANCE == null){ 
                	setINSTANCE(clazz.newInstance());
                 }
             } 
       }
       return INSTANCE;
	}
}
