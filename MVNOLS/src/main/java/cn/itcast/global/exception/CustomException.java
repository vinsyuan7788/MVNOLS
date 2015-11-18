package cn.itcast.global.exception;

/**
 * 	This is a custom exception class for global exception resolver:
 * 	1. Need to extend Exception & offer the constructors
 *  2. When try...catch... in programming, manually throw CustomException out
 *     -- Exception is thrown from DAO to Service to Action to DS
 */
public class CustomException extends Exception {

	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
