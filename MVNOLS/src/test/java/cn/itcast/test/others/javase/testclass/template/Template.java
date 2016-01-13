package cn.itcast.test.others.javase.testclass.template;

/**
 * 	This is a class for template pattern
 *	1. Write the codes to solve one kind of problem
 *     -- e.g. calculate the system elapse time
 *  2. Extract the parts that is changable|variable to an abstract method
 *     -- hence the corresponding class will be abstract as well
 *  3. User can write a new class that extends this abstract|template class to customize the changable method
 */
public abstract class Template {

	/**	
	 * 	This method is the template to resolve one kind of problem: timing
	 *  1. Use "final" to make sure the template codes are unchangable  
	 */
	public final void getElapseTime () throws Exception {
		
		long startTime = System.nanoTime();
//		long startTime = System.currentTimeMillis();
		
		customMethod();
		
		long endTime = System.nanoTime();
//		long endTime = System.currentTimeMillis();
		
		System.out.println("The elapse time: " + (endTime - startTime) + "ns");
	}
	
	/**
	 * 	This method can be overrided in custom when this class is extended	
	 */
	public abstract void customMethod () throws Exception;
}
