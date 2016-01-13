package cn.itcast.test.others.javase.testclass.argument;

import java.util.Arrays;

/**
 * 	This is a class for variable-argument testing
 */
public class Person {

	/**	
	 * 	This is the instance variable to accept a person's hobbies	
	 * 	1. Variable-argument is treated as an array
	 */
	private String[] hobbyArray;
	
	/*	Other instance variables  */
	private String name;
	private Integer age;

	/*	Constructor to construct a person	*/
	public Person (String name, Integer age) {
		this.hobbyArray = null;
		this.name = name;
		this.age = age;
	}
	
	/**
	 * 	This is the setter to set a person's hobbies
	 * @param hobbies
	 */
	public void setHobbyArray(String... hobbies) {
		this.hobbyArray = hobbies;
	}

	/*	Here is an overrided instance method for console output   */
	@Override
	public String toString() {
		return "Person [hobbyArray=" + Arrays.toString(hobbyArray) + ", name="
				+ name + ", age=" + age + "]";
	}
}
