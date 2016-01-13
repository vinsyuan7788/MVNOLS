package cn.itcast.test.others.javase.testclass.factory.subject;

/**
 * 	This is the subject class for Factory class, which should following some rules
 * 	1. Offer a no-argument constructor, which is offered by default
 */
public class Person {
	
	/*	Here are the instance variables  */
	private int id;
	private String name;

	/*	This is the method for output this class to console	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
}

