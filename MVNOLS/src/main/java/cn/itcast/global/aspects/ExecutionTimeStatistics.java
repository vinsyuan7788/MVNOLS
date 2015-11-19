package cn.itcast.global.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 	This is an aspect to get the statistics of execution time for target method
 */
public class ExecutionTimeStatistics {

	/**
	 * This is an around advice:
	 * @throws Throwable 
	 */
	public void saveToken (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long beginTime = System.nanoTime();
		proceedingJoinPoint.proceed();
		long endTime = System.nanoTime();
		System.out.println("Time cost of " + proceedingJoinPoint.getSignature().getName() + " in " + proceedingJoinPoint.getTarget().getClass() + ": " + (endTime - beginTime) + "ns");
	}
	
	/**
	 * 	This is a before advice:
	 * 	1. Executed before target method
	 * @param joinPoint
	 * @throws Exception
	 */
	public void beforeAdvice (JoinPoint joinPoint) throws Exception {	
		
		System.out.println("ExecutionTimeStatistics.beforeAdvice()...");
		
	}
	
	/**
	 * 	This is an after-returning advice
	 *  1. Executed after target method, if target method is executed normally
	 *  2. When target method throws an exception, after return advice will NOT be executed
	 *  3. There is another argument "Object returnValue" that needs to be configured in Spring 
	 *     -- "Object returnValue" can accept the return value of target method
	 * @param joinPoint
	 * @throws Exception
	 */
	public void afterReturningAdvice (JoinPoint joinPoint) throws Exception {
		
		System.out.println("ExecutionTimeStatistics.afterReturningAdvice()...");
		
	}
	
	/**
	 * 	This is an after-throwing advice
	 * 	1. Executed if target method throws an exception
	 * 	2. "Throwable throwable" is used to accept the thrown exception from target method
	 * @param joinPoint
	 * @param throwable
	 * @throws Exception
	 */
	public void afterThrowingAdvice (JoinPoint joinPoint, Throwable throwable) throws Exception {
		
		System.out.println("ExecutionTimeStatistics.afgterThrowingAdvice()...");
		
	}
	
	/**
	 * 	This is an after finally advice:
	 *  1. Executed no matter if there is an exception when target method is executed
	 *  2. If target method is not executed, then finally advice is NOT executed as well
	 *     -- e.g. target method is prohibited by around advice
	 * @throws Exception
	 */
	public void afterFinallyAdvice () throws Exception {
		
		System.out.println("ExecutionTimeStatistics.afterFinallyAdvice()...");
	}
}
