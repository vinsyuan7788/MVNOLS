package cn.itcast.test.others.javase.testclass.proxy.statics;

import cn.itcast.test.others.javase.testclass.proxy.target.interfaces.Waiter;

/**
 * 	This is an static proxy class to enhance the target class|interface (method)
 * 	1. Implement the interface that target class implements
 *  2. Maintain a (parent|common) reference for all targets: IOP|AOP(Abstraction-Oriented Programming)
 * 	3. Offer a constructor for custom target object assignment: require to assign a target object
 *     -- Offering a setter is not feasible since target method may be invoked before the setter
 * 	4. Override the necessary method to customize the enhancement content (with target class method invocation)
 * 
 * 	Static proxy: get the proxy object before runtime, namely the running of the program (e.g., use "new" to get the object)
 *  -- Without using "InvocationHandler" & "Proxy.newProxyInstance()"
 *  -- ONLY serves for one interface (e.g., Waiter), not good for interface scalability
 */
public class WaiterProxy implements Waiter {

	/**
	 * 	Maintain a (parent|common) reference for all targets: IOP|AOP(Abstraction-Oriented Programming)
	 */
	private Waiter waiter;
	
	/**	
	 * 	Offering a constructor for custom target object assignment: require to assign a target object
	 *  -- Offering a setter is not feasible since target method may be invoked before the setter
	 */
	public WaiterProxy (Waiter waiter) {
		this.waiter = waiter;
	}
	
	/**
	 * 	Override the necessary method to customize the enhancement content (with target class method invocation)
	 * 	@throws Exception
	 */
	@Override
	public void serve() throws Exception {
		
		System.out.println("Welcome!");
		waiter.serve();
		System.out.println("Have a good day!");
		System.out.println();
	}

	@Override
	public void checkOut() throws Exception {
		
		waiter.serve();
		System.out.println("Welcome your coming again!");
		System.out.println();
	}
}
