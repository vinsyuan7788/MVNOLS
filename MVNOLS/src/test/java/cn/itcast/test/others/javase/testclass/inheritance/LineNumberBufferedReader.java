package cn.itcast.test.others.javase.testclass.inheritance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 	This is a class for inheritance to enhance a (method of a) class
 * 	
 * 	To enhance a (method of a) class:
 * 	1. Inheritance: 
 *     -- Extends the class & override the method that needs to be enhanced
 *     -- Simplicity but unflexibility (may bring a large inheritance hierarchy if there is a lot of demands)
 *        -- The enhanced content is hard-coded
 *        -- The decorated classes are invariable|unchangeable
 *  2. Decorator pattern: refer to "decorator" package
 *  3. Proxy pattern: refer to "proxy" package
 */
public class LineNumberBufferedReader extends BufferedReader {

	/*	This is the line number with initial value 1 for enhancement	 */
	private int lineNumber = 1;
	
	/*	Here inherits one of the parent constructors	*/
	public LineNumberBufferedReader(Reader in) {
		super(in);
	}
	
	/**
	 * 	Override the "readLine()" method for enhancement: 
	 * 	1. Add the line number ahead of each line	
	 * 	2. The enhanced content is hard-coded 
	 */
	@Override
	public String readLine() throws IOException {
		
		/*	If the line is null (i.e., read nothing from the file), then return null	*/
		String line = super.readLine();
		if (line == null) {
			return null;
			
		/*	Otherwise process what is read from reader	*/
		} else {
			line = lineNumber + " " + line;
			lineNumber++;
			return line;	
		}
	}
}
