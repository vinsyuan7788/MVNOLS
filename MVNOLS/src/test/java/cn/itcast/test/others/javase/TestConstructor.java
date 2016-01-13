package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.constructor.Student;

/**
 * 	This is the class to perform testing regarding constructor
 */
public class TestConstructor {

	/**
	 * 	Test the copy constructor
	 */
	@Test
	public void testCopyConstructor () {
		
		Student student1 = new Student(1, "Vince");
		System.out.println("This is the student1: " + student1);
		
		Student student1Copy = new Student(student1);
		System.out.println("This is the student1 copy: " + student1Copy);
	}
}
