package cn.itcast.test.others.javase.testclass.proxy.target;

import cn.itcast.test.others.javase.testclass.proxy.target.interfaces.Waiter;

/**
 * 	This is a class that implements Waiter interface: serving as a target that needs to be enhanced
 */
public class MaleWaiter implements Waiter {
	
	@Override
	public void serve() {
		System.out.println("Male waiter is serving...");
	}

	@Override
	public void checkOut() throws Exception {
		System.out.println("Hi maam, let me check out for you");
	}
}
