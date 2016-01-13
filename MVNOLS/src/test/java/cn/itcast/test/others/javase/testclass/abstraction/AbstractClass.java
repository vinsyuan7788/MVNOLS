package cn.itcast.test.others.javase.testclass.abstraction;

/**
 * 	This is an abstract class be extended by EagerSingleton and LazySingleton class
 * 	1. This class extracts 2 common methods for its subclasses
 */
public abstract class AbstractClass {

	public abstract void talk () throws Exception;
	
	public void walk () throws Exception {
		System.out.println("AbstractClass.talk()...");
	}
}
