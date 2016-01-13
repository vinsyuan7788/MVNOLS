package cn.itcast.test.others.javase.testclass.proxy.dynamics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.itcast.test.others.javase.testclass.proxy.dynamics.advice.AfterAdvice;
import cn.itcast.test.others.javase.testclass.proxy.dynamics.advice.BeforeAdvice;

/**
 * 	This is a class that offers custom enhancement content and target & returns the proxy object: serving as a dynamic proxy
 * 	1. Create necessary advices: refer to "advice" package
 *  2. Maintain the references for target class & necessary advices
 * 	3. Offer a constructor for target class reference: require to assign a target object
 *     -- Offering a setter is not feasible since target method may be invoked before the setter
 *  4. Provide the setter for the advice references
 *  5. Write a method to generate dynamic proxy object
 *  
 *  Dynamic proxy: get the proxy object during runtime, namely the running of the program (e.g., use "Proxy.newProxyInstance()" to get the object)
 *  -- Using "InvocationHandler" & "Proxy.newProxyInstance()"
 */
public class DynamicProxy {

	/**
	 * 	Maintain the references for target class & necessary advices
	 */
	private Object target;
	private BeforeAdvice beforeAdvice;
	private AfterAdvice afterAdvice;
	
	/**
	 * 	Offer a constructor for target class reference: require to assign a target object
	 *  -- Offering a setter is not feasible since target method may be invoked before the setter
	 * 	@param target
	 */
	public DynamicProxy (Object target) {
		this.target = target;
		this.beforeAdvice = null;
		this.afterAdvice = null;
	}

	/**
	 * 	Provide the setter for the advice references
	 * 	@param beforeAdvice
	 */
	public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
		this.beforeAdvice = beforeAdvice;
	}

	public void setAfterAdvice(AfterAdvice afterAdvice) {
		this.afterAdvice = afterAdvice;
	}

	/**
	 * 	Write a method to generate dynamic proxy object
	 * 	@return
	 * 	@throws Exception
	 */
	public Object createProxy () throws Exception {
		
		/*	Get the class loader	 */
		ClassLoader classLoader = this.getClass().getClassLoader();
		
		/*	Get the interfaces implementd by the target class	*/
		Class[] interfaces = target.getClass().getInterfaces(); 
		
		/*	Get the InvocationHandler object  */
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				/*	Invoke the before advice if there is any  */
				if (beforeAdvice != null) {
					beforeAdvice.beforeAdvice();
				}
				
				/*	Invoke the target class' method itself	 */
				Object returnResult = method.invoke(target, args);
				
				/*	Invoke the after advice if there is any   */
				if (afterAdvice != null) {
					afterAdvice.afterAdvice();
				}
				
				/*	Return the return result of target class' method  */
				return returnResult;
			}
		};
		
		/*	Create the dynamic proxy object * return	*/
		Object proxyObject = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
		return proxyObject;
	}
}
