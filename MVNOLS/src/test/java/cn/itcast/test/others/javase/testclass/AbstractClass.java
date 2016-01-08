package cn.itcast.test.others.javase.testclass;

/**
 * 	This is an abstract class be extended by EagerSingleton and LazySingleton class
 */
public abstract class AbstractClass {

	public abstract void talk () throws Exception;
	
	public void walk () throws Exception {
		System.out.println("AbstractClass.talk()...");
	}
}
