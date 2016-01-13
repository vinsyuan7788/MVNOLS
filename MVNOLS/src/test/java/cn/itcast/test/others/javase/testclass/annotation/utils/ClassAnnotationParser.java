package cn.itcast.test.others.javase.testclass.annotation.utils;

import cn.itcast.test.others.javase.testclass.annotation.ClassAnnotation;
import cn.itcast.test.others.javase.testclass.annotation.bean.Person;
import cn.itcast.test.others.javase.testclass.factory.singleton.LazySingletonFactory;

/**
 * 	This is an utility class to parse the class annotation
 * 	1. If there is a @ClassAnnotation & "singleton=true": return a singleton instance
 *  2. If there is a @ClassAnnotation & "singleton=false": return a multiton instance
 *  3. If there is no @ClassAnnotation: return null
 * @author Administrator
 *
 */
public class ClassAnnotationParser {

	/**
	 * 	This is the method to get instance
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object getInstance (Class clazz) throws Exception {
		
		/*	If there is a @ClassAnnotation, get the annotation & continue	*/
		if (clazz.isAnnotationPresent(ClassAnnotation.class)) {
			ClassAnnotation classAnnotation = (ClassAnnotation) clazz.getAnnotation(ClassAnnotation.class);
			
			/*	If "singleton=true": return a singleton instance	*/
			if (classAnnotation.singleton() == true) {
				return LazySingletonFactory.getInstance(clazz);
				
			/*	If "singleton=false": return a multiton instance	*/
			} else {
				return (Person) clazz.newInstance();
			}
			
		/*	If there is no @ClassAnnotation: return null	*/
		} else {
			return null;
		}
	}
}
