package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.argument.Person;
import cn.itcast.test.others.javase.testclass.argument.Sporter;

/**
 * 	This is a class to perform testing regarding vararg (variable argument)
 * 
 * 	Variable-argument: allow to call a method with any number of arguments in the same type
 *  1. Variable-argument is treated as an array
 *  2. If there are multiple arguments, variable-argument MUST be put at the end
 */
public class TestArgument {

	/**
	 * 	Test variable argument
	 * @throws Exception
	 */
	@Test
	public void testVariableArgument () throws Exception {
		
		Person person1 = new Person("Vince", 25);
		person1.setHobbyArray("IT", "Reading", "Learning", "Eating");
		System.out.println("Current person information: " + person1);
		
		Person person2 = new Person("Vince", 25);
		person2.setHobbyArray("Movie", "Music");
		System.out.println("Current person information: " + person2);
		
		Person person3 = new Person("Vince", 25);
		System.out.println("Current person information: " + person3);
	}
	
	/**
	 * 	Test the position of variable argument
	 * @throws Exception
	 */
	@Test
	public void testPostionOfVararg () throws Exception {
		
		/*	Specify necessary parameters	*/
		String weather = "Sunny";
		double miles = 50;
		String[] locations = {"Park", "Super Market", "Square", "Shoping Street"};
		
		/*	Report the runnning situation of a sporter	*/
		Sporter.run(weather, miles, locations);
	}
}
