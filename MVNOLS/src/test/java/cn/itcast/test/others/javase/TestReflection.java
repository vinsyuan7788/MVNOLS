package cn.itcast.test.others.javase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.reflection.Person;

/**
 *  This class is to perfrom testing regarding reflection operation
 *  
 *  Reflection mechanism: (*******)
 *  1. document -- (1.save) --> .java -- (2.compile) -> .class -- (3.JVM) --> stores in the method area --> get object of Class, Field, Constructor, Method
 *  2. Stack & heap refers to "testMemory.java"
 *  
 *  Reflection is applied in server (such as Tomcat)
 */
public class TestReflection {
	
	/**
	 *  Use reflection to get the class object without using "new" (for interview question)
	 * 	@throws Exception
	 */
	@Test
	public void testGetClassObject () throws Exception {
		
		/*	Get the Class object	*/
		Class classObject = Class.forName("cn.itcast.test.others.javase.testclass.reflection.Person");
//		Class classObject1 = Person.class;
//		Class classObject2 = new Person().getClass();
		
		/*	Get the Person class object through constructor with no pass-in argument	*/
		Person p = (Person) classObject.newInstance();
		p.setName("Vince");
		System.out.println(p.getName());
	}
	
	/**
	 *  Use reflection to get the constructor without using new (for interview question)
	 * 	@throws Exception
	 */
	@Test
	public void testGetConstructor () throws Exception {
		
		/*	Get the Class class	 */
		Class classObject = Class.forName("cn.itcast.test.others.javase.testclass.reflection.Person");
		
		/*  Get the constructor with pass-in arguments	*/
		// classObject.getConstructors();  // get all constructors
		Constructor constructor = classObject.getConstructor(String.class, String.class);
		
		/*  Get the class object using constructor	*/
		Person p = (Person) constructor.newInstance("Violet", "200"); // needs type casting
		System.out.println(p.getId() + " " + p.getName());
		
	}
	
	/**
	 *  Use reflection to operate instance variable, such as "name"
	 * 	@throws Exception
	 */
	@Test
	public void testGetInstanceVariable () throws Exception {
		
		/*  Get the Class class	 */
		Class classObject = Class.forName("cn.itcast.test.others.javase.testclass.reflection.Person");
		
		/* 	Get the class object  */
		Person p = (Person) classObject.newInstance();	// needs type casting	
		
		/*	Get the attribute|instance variable "name"  */
		// classObject.getDeclaredFields(); // get all the instance variable
		Field f = classObject.getDeclaredField("name");
		
		/*	(*****) Allow to access private instance variables	 */
		f.setAccessible(true);
		
		/* 	Operate the instance variable	*/
		f.set(p, "Vince");
		System.out.println(f.get(p));
	}
	
	/**
	 *  Use reflection to operate instance method, such as "setName()"
	 * 	@throws Exception
	 */
	@Test
	public void testGetInstanceMethod () throws Exception {
	
		/* 	Get the Class class	 */
		Class classObject = Class.forName("cn.itcast.test.others.javase.testclass.reflection.Person");
		
		/*  Get the class object	*/
		Person p = (Person) classObject.newInstance();	// needs type casting
		
		/* 	Get the method "setName()"	*/
		// classObject.getDeclaredMethods();	// get all the methods
		Method m = classObject.getDeclaredMethod("setName", String.class);
		
		/* 	Operate the instance method	 */
		m.invoke(p, "Violet");
		// m.invoke(null, "violet");			// if m is a static method
		System.out.println(p.getName());
	}
}
