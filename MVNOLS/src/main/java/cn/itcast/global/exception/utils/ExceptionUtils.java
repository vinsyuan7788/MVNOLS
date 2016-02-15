package cn.itcast.global.exception.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.annotation.Resource;

import cn.itcast.global.utils.FileUtils;


/**
 * 	This is the utility class for exception processing
 * @author Vince Xu Yuan
 */
public class ExceptionUtils {

	/*	IOP: IOC & DI	*/
	@Resource
	private FileUtils fileUtils;
	
	/*	
	 * 	Here specifies the log file name: 
	 * 	1. Can use "Timer" class or Quartz jars|framework to periodically generate the names for log files
	 */
	private static final String LOG_FILE_NAME = "exception.log";
	
	/**
	 * 	This is a static method to print out the exception trace to logs
	 * @param exception
	 */
	public final void printToLogs (Exception exception) {
		
		/*	Try the output the exception trace to logs	*/
		try {
			/*	Get a File instance from the real path	*/
			File logFile = new File(fileUtils.getRealPath("/log/" + LOG_FILE_NAME));
			
			/*	Generate the parent directory if it does not exist	 */
			FileUtils.createParentDirectory(logFile);
			
			/*	Get the PrintStream instance	*/
			PrintStream printStream = new PrintStream(new FileOutputStream(logFile, true));
			
			/*	Print out the exceptions to log	 */
			exception.printStackTrace(printStream);
		
		/*	If fail, throw a runtime exception with the cause: this should not happen	*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
