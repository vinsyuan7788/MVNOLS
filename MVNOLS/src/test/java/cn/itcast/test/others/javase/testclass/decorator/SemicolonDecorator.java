package cn.itcast.test.others.javase.testclass.decorator;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 	This is the decorator class for class (method) enhancement
 *  1. Decorator class extends decorated class|interface (i.e., BufferedReader)
 *     -- If extending a decorated class, add a constructor
 *     -- If there is any "super" statement, it only makes sure the compilation is not erroneous (the "super" statement is not actually working)
 * 	2. Maintain a reference for decorated class (i.e., BufferedReeader) inside decorator class (i.e., SemicolonDecorator)
 * 	   -- Use the constructor for decorated class object assignment: polymorphism
 * 	   -- Override the method that needs to enhanced (with decorated class method invocation)
 */
public class SemicolonDecorator extends BufferedReader {

	/**	
	 * 	Maintain a reference for decorated class inside decorator class	
	 */
	private BufferedReader bufferedReader;
	
	/*	This is the line number with initial value 1 for enhancement	 */
	int lineNumber = 1;
	
	/**	
	 * 	Add a constructor that assigns the object to the reference of decorated class	 
	 */
	public SemicolonDecorator (BufferedReader bufferedReader) {
		super(bufferedReader);
		this.bufferedReader = bufferedReader;
	}
	
	/**
	 * 	Override the method that needs to enhanced (with decorated class method invocation)	
	 *  1. Add the semicolon at the end of each line
	 * 	@throws Exception
	 */
	@Override
	public String readLine() throws IOException {
		String line = bufferedReader.readLine();
		if (line == null) {
			return null;
		} else {
			line = line + ";";
			lineNumber++;
			return line;
		}
	}
}
