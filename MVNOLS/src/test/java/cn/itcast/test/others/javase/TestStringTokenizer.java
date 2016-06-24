package cn.itcast.test.others.javase;

import java.util.StringTokenizer;

import org.junit.Test;

/**
 * 	This is the class to perform testing regarding StringTokenizer
 * 	1. Can be used to parse a string (customed by programmer or read from a text file, etc.)
 * 
 * 	Exemplary application scenario:
 * 	1. Count the word in a text file
 * 
 * @author Vince Xu Yuan
 */
public class TestStringTokenizer {

	@Test
	public void testStringTokenizer () throws Exception {
		
		/*	Get a string & parse the string using " " with StringTokenizer	*/
		String line = "I love China & I love the world!";
		StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
		
		/*	Count the total remaining tokens after current position: current position is 0 by default & can move to next position by "nextToken()"	 */
		System.out.println("The total of words: " + stringTokenizer.countTokens());
		
		/*	See if there is at least one token after current position: current position is 0 by default & can move to next position by "nextToken()"	*/
		System.out.println("At least one token after current position? " + stringTokenizer.hasMoreTokens() + "\n");
		
		/*	Get the parsed words in the string	 */
		while (stringTokenizer.hasMoreTokens()) {
			String nextWord = stringTokenizer.nextToken();
			System.out.println("The parsed word in the string: " + nextWord);
			System.out.println("The total remaining words: " + stringTokenizer.countTokens());
			System.out.println("At least one word after current position? " + stringTokenizer.hasMoreTokens());
		}

	}
}
