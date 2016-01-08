package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.EagerSingleton;
import cn.itcast.test.others.javase.testclass.LazySingleton;
import cn.itcast.test.others.javase.testclass.Template;

/**
 * 	This class is to perform testing regarding design pattern
 * 	1. Singleton pattern: to make sure there is ONLY one instance in the (heap) memory
 * 	2. Template pattern: to provide a template codes to solve one type of problems
 * 
 * 	Pattern: a fixed procedure to resolve one type of problem
 * 	1. Hence no need to understand: if there is a demand, then use this pattern  
 */
public class TestDesignPattern {

	/**
	 * 	Test singleton pattern
	 * 	1. Eager singleton pattern
	 * 	2. Lazy singleton pattern
	 * 	@throws Exception
	 * 
	 * 	Recommendation: eager singleton pattern
	 * 	1. Because lazy singleton pattern cannot ensure thread-security so far
	 * 
	 * 	Singleton pattern is typically applied in Spring framework
	 */
	@Test
	public void testSingletonPattern () throws Exception {
			
		/*	Get 2 EagerSingleton objects	 */
		EagerSingleton es1 = EagerSingleton.getEagerSingleton();
		EagerSingleton es2 = EagerSingleton.getEagerSingleton();
		
		/*	See if they are the same objects (same reference)	*/
		System.out.println("Is it true that es1 and es2 have the same reference? " + (es1 == es2));
		
		/*	Invoke the method	*/
		es1.run();
		es2.run();
		
		/*	Get 2 LazySingleton objects	 */
		LazySingleton ls1 = LazySingleton.getLazySingleton();
		LazySingleton ls2 = LazySingleton.getLazySingleton();
		
		/*	See if they are the same objects (same reference)	*/
		System.out.println("Is it true that ls1 and ls2 have the same reference? " + (ls1 == ls2));
		
		/*	Invoke the method	*/
		ls1.walk();
		ls2.talk();
	}
	
	/**
	 * 	Test template pattern
	 * 	1. Need a new class to extend the template class
	 * 	2. New an instance of the new class to invoke the template methods
	 */
	@Test
	public void testTemplatePattern () throws Exception {
		
		/*	Create an instance of the new class that extends the template class	 */
		MyTemplate myTemplate = new MyTemplate();
		
		/*	Directly invoke the template method	 */
		myTemplate.getElapseTime();
		
	}
	
	/*	This is the class to extend the template class, so that user can customize the codes for fillableCode() by overriding it	*/
	class MyTemplate extends Template {

		@Override
		public void customizedCodes () throws Exception {
			for (int i = 0; i < 200; i++) {};
		}
	}
}
