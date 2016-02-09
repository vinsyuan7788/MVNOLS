package cn.itcast.global.exception.bean;

/**
 * 	This is a custom exception class for global exception resolver:
 * 	1. Need to extend Exception & offer the constructors
 *  2. When try...catch... in programming, manually throw CustomException out
 *     -- Exception is thrown from DAO to Service to Action to DS (DispatcherServlet)
 *     
 *  When to use CustomException class:
 *  1. when the exception is raised by custom cause or thrown with custom message
 *     -- E.g., the exception is caused by the interception of interceptors (refer to "global/interceptor/LoginValidator.java" or "global/interceptor/TokenValidator.java"), etc.
 *  2. To distinguish from runtime exception so that we can only print the runtime exception trace to log files (avoiding the custom exception information)
 *     -- E.g., refer to "global/exception/GlobalExceptionResolver.java"
 *     
 * @author Vince Xu Yuan
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 7198701311679082881L;

	public CustomException() {
		super();
	}

	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}
}
