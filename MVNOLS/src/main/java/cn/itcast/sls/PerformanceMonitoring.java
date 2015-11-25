package cn.itcast.sls;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 	This is an aspect class for performance monitoring
 * 	1. AOP configuration in Spring is necessary to make the aspect class work
 *     -- In this project the configuration is in "spring/aop.xml"
 */
public class PerformanceMonitoring {

	/**
	 * 	This is an around advice: with argument "ProceedingJoinPoint proceedingJoinPoint"
	 * 	1. Can control if target method is executed by "joinPoint.proceed()"
	 * 	2. Strongly recommended to return (the result of) "joinPoint.proceed()" to ensure the point cut (method) can be reached
	 *     -- As shown in the Spring official documentation 
	 *     -- Otherwise point cut (method) may be shortcut to other advice (after-returning|after-finally method) 
	 * @param proceedingJoinPoint
	 * @throws Throwable
	 */
	public Object computeRunningTime (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("Joint point (method) name: " + proceedingJoinPoint.getSignature().getName());
		System.out.println("Target class: "  + proceedingJoinPoint.getTarget().getClass());	
		
		long beginTime = System.nanoTime();
		Object targetMethod = proceedingJoinPoint.proceed();
		long endTime = System.nanoTime();
		System.out.println("Time cost of " + proceedingJoinPoint.getSignature().getName() + " in " + proceedingJoinPoint.getTarget().getClass() + ": " + (endTime - beginTime) + "ns");
		
		return targetMethod;
	}
	
	/**
	 * 	This is a before advice: with argument "JoinPoint joinPoint"
	 * 	1. Executed before target method
	 * @param joinPoint
	 * @throws Exception
	 */
	public void beforeAdvice (JoinPoint joinPoint) throws Exception {	
		
		System.out.println("PerformanceMonitoring.beforeAdvice()...Joint point (method) name: " + joinPoint.getSignature().getName());
		System.out.println("PerformanceMonitoring.beforeAdvice()...Target class: "  + joinPoint.getTarget().getClass());	
		System.out.println("PerformanceMonitoring.beforeAdvice()...");
	}
	
	/**
	 * 	This is an after-returning advice: with argument "JoinPoint joinPoint" & "Object returnValue" (not required, but commonly-used)
	 *  1. Executed after target method, if target method is executed normally
	 *  2. When target method throws an exception, after return advice will NOT be executed
	 *  3. There is another argument "Object returnValue" that needs to be configured in Spring 
	 *     -- "Object returnValue" can accept the return value of target method
	 * @param joinPoint
	 * @param returnValue
	 * @throws Exception
	 */
	public void afterReturningAdvice (JoinPoint joinPoint, Object returnValue) throws Exception {
		
		System.out.println("PerformanceMonitoring.afterReturningAdvice()...Joint point (method) name: " + joinPoint.getSignature().getName());
		System.out.println("PerformanceMonitoring.afterReturningAdvice()...Target class: "  + joinPoint.getTarget().getClass());
		System.out.println("PerformanceMonitoring.afterReturningAdvice()...Target method return value: " + returnValue);
		System.out.println("PerformanceMonitoring.afterReturningAdvice()...");
	}
	
	/**
	 * 	This is an after-throwing advice: with argument "JoinPoint joinPoint" & "Throwable throwable"
	 * 	1. Executed if target method throws an exception
	 * 	2. "Throwable throwable" is used to accept the thrown exception from target method
	 * 	3. There is another argument "Throwable throwable" that needs to be configured in Spring
	 *     -- "Throwable throwable" can accept the exception message of target method
	 * @param joinPoint
	 * @param throwable
	 * @throws Exception
	 */
	public void afterThrowingAdvice (JoinPoint joinPoint, Throwable throwable) throws Exception {
		
		System.out.println("PerformanceMonitoring.afgterThrowingAdvice()...Joint point (method) name: " + joinPoint.getSignature().getName());
		System.out.println("PerformanceMonitoring.afgterThrowingAdvice()...Target class: "  + joinPoint.getTarget().getClass());
		System.out.println("PerformanceMonitoring.afgterThrowingAdvice()...The exception thrown by target method: " + throwable.getMessage());	
		System.out.println("PerformanceMonitoring.afgterThrowingAdvice()...");
	}
	
	/**
	 * 	This is an after finally advice: with no argument
	 *  1. Executed no matter if there is an exception when target method is executed
	 *  2. If point cut (i.e. target method) is never found, then finally advice is NOT executed as well
	 *     -- e.g. target method is prohibited by around advice
	 * @throws Exception
	 */
	public void afterFinallyAdvice () throws Exception {
		
		System.out.println("PerformanceMonitoring.afterFinallyAdvice()...");
	}
}
