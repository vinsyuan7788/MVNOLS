package cn.itcast.test.others.javase;

/**
 * 	This is the class to perform testing regarding exception
 * 	1. Unchecked exception: the uncheckable exception by JVM
 *     -- Error, RuntimeException
 *     -- Unchecked exception is unprocessable
 *  2. Checked exception: the checkable exception by JVM
 *     -- All Exception classees except Error, RuntimeException: OutOfMemoryError, IllegalArgumentException, etc.
 *     -- Checked excepton is processable
 *     
 *  To process checked exception:
 *  1. Use "try...catch...finally..." to process the exception
 *     -- E.g., print out some message, do some corresponding resolution, etc.
 *  2. Use "throws Exception" on the method or "throw new RuntimeException(Throwable e)" inside the method
 *     -- This is better since the codes will be more brief & whoever invokes the method with thrown-out exception can choose if want to process the exception or not
 *     -- If the exception is not processed all the time, then it will be finally printed to failure trace 
 *  
 *  In actual implementation:
 *  1. Use a global exception resolver to process the exception:
 *     -- E.g., outpput some readable information to front-end
 *  2. If there is an exception, try catch it
 *     -- If it is checked exception, throw it out using using RuntimeException
 *     -- If it is other exception, throw it out using custom exception class
 *        -- Custom exception class can be created by extending Exception class
 *  3. Do the exception logging to record necessary exception
 *     -- Can be realized in global exception resolver or using AOP
 *  
 * @author Vince Xu Yuan
 */
public class TestException {

}
