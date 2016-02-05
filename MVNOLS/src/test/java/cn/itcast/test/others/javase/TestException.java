package cn.itcast.test.others.javase;

/**
 * 	This is the class to perform testing regarding exception
 * 	
 *  To process exception:
 *  1. Use "try...catch...finally..." to process the exception
 *     -- E.g., print out some message, do some corresponding resolution, etc.
 *  2. Use "throws Exception" on the method or "throw new RuntimeException(Throwable e)" inside the method
 *     -- This is better since the codes will be more brief & whoever invokes the method with thrown-out exception can choose if want to process the exception or not
 *     -- If the exception is not processed all the time, then it will be finally printed to failure trace 
 *  
 *  In actual implementation:
 *  1. Create a custom exception class by extending Exception class
 *  2. If there is an exception, can throw out the the custom exception
 *     -- Whotever invokes the method with thrown-out exception, can choose if want to process the exception or not
 *     -- In the MVNOLS project, due to global exception resolver, all the exception will be process eventually
 *  
 * @author Vince Xu Yuan
 */
public class TestException {

}
