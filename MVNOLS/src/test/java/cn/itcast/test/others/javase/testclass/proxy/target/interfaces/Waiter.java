package cn.itcast.test.others.javase.testclass.proxy.target.interfaces;

/**
 * 	This is an interface to regulate the action of a waiter: serving as a target that needs to be enhanced
 */
public interface Waiter {

	public void serve() throws Exception;
	public void checkOut() throws Exception;
}
