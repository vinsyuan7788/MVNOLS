package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.annotation.bean.Person;
import cn.itcast.test.others.javase.testclass.annotation.utils.ClassAnnotationParser;
import cn.itcast.test.others.javase.testclass.annotation.utils.FieldAnnotationParser;
import cn.itcast.test.others.javase.testclass.annotation.utils.MethodAnnotationParser;

/**
 * 	This is the class to perform testing regarding annotation
 */
public class TestAnnotation {

	/**
	 * 	Test the annotation
	 * @throws Exception
	 */
	@Test
	public void testAnnotation () throws Exception {
		
		/*	Create 2 instances & do the injection & get the method names	*/
		Person person1 = (Person) ClassAnnotationParser.getInstance(Person.class);
		Person person2 = (Person) ClassAnnotationParser.getInstance(Person.class);
		FieldAnnotationParser.inject(person1);
		String methodNames = MethodAnnotationParser.getMethodNames(Person.class);
		
		/*	See if the instance is singleton or not: can change the result through @ClassAnnotation in "annotation.bean.Person.java", & output other information	*/
		System.out.println("Is it true that Person instance is singleton? " + (person1 == person2));
		System.out.println("The person instance: " + person1);
		System.out.println("The method with method annotation: " + methodNames);
	}
}
