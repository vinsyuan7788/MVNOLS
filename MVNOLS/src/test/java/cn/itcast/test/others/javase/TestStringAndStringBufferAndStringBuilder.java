package cn.itcast.test.others.javase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.Test;

/**
 * 	This is a class to perfrom testing regarding String
 * 	1. String is immutable, while StringBuilder|StringBuffer is not
 *     -- Pros: Can be applied in synchronization: refer to "TestThread.java"
 *     -- Cons: String is more stack-memory-consuming & less time-efficient in some situation: refer to "testStringBufferAndStringBuilderMutability()"
 *  2. StringBuffer is synchronized|thread-safe, while StringBuilder is not
 * @author Vince Xu Yuan
 */
public class TestStringAndStringBufferAndStringBuilder {

	/**
	 * 	Test the string immutability
	 * 	1. Once a String object is instantiated, it is unchanged
	 * @throws Exception
	 */
	@Test
	public void testStringImutability () throws Exception {
		
		/*	Create a String object & concatenate a string	*/
		String originalString = new String("abc");
		originalString.concat("d");
		
		/*	The original string is unchanged	*/
		System.out.println("The original string: " + originalString);
		
		/*	Need another reference to receive the change of string	*/ 
		String newString = originalString.concat("e");
		System.out.println("The new string: " + newString);
	}
	
	/**
	 * 	Test the StringBuffer & StringBuilder mutability: using a loop concatnation case
	 * 	1. In this case, StringBuffer|StringBuilder is more efficient
	 * @throws Exception
	 */
	@Test
	public void testStringBufferAndStringBuilderMutability () throws Exception {
		
		/*	Create a StringBuffer & StringBuilder & String object respectively	*/
		StringBuffer stringBuffer = new StringBuffer();
		StringBuilder stringBuilder = new StringBuilder();
		String string = "";
		
		/*	Read the input from console	 */
		while (true) {
			
			/*	If the input is acceptable, then output & break the loop	*/
			try {
				System.out.println("Please input an integer greater than 0: (Recommendation: 10000)");
				int inputInteger = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
				
				/*	If the input is not greater than 0, output a message & continue the loop	*/
				if (inputInteger <= 0) {
					System.out.println("The input integer MUST be greater than 0.");
					continue;
				}
				
				/*	Loop to concatenate a string: in this case, StringBuffer|StringBuilder is more efficient	*/
				long startTimeForStringBuffer = System.nanoTime();
				for (int i = 0; i < inputInteger; i++) {
					if (i > 0) {
						stringBuffer.append(", ");
					}
					stringBuffer.append("a");
				}
				long endTimeForStringBuffer = System.nanoTime();
				
				long startTimeForStringBuilder = System.nanoTime();
				for (int i = 0; i < inputInteger; i++) {
					if (i > 0) {
						stringBuilder.append(", ");
					}
					stringBuilder.append("a");
				}
				long endTimeForStringBuilder = System.nanoTime();
				
				String tempString = "";
				long startTimeForString = System.nanoTime();
				for (int i = 0; i < inputInteger; i++) {
					if (i > 0) {
						tempString = string.concat(", ");
					}
					string = tempString.concat("a");
				}
				long endTimeForString = System.nanoTime();
				
				/*	Output the information to console 	*/
				System.out.println("The string from StringBuffer: " + stringBuffer.toString());
				System.out.println("The string length: " + stringBuffer.toString().length());
				System.out.println("The elapse time: " + (endTimeForStringBuffer - startTimeForStringBuffer) + " ns");
				System.out.println("The order of maginitude of elapse time: " + String.valueOf(endTimeForStringBuffer - startTimeForStringBuffer).length());
				System.out.println();
				System.out.println("The string from StringBuilder: " + stringBuilder.toString());
				System.out.println("The string length: " + stringBuilder.toString().length());
				System.out.println("The elapse time: " + (endTimeForStringBuilder - startTimeForStringBuilder) + " ns");
				System.out.println("The order of maginitude of elapse time: " + String.valueOf(endTimeForStringBuilder - startTimeForStringBuilder).length());
				System.out.println();
				System.out.println("The string from String: " + string);
				System.out.println("The string length: " + string.length());
				System.out.println("The elapse time: " + (endTimeForString - startTimeForString) + " ns");
				System.out.println("The order of maginitude of elapse time: " + String.valueOf(endTimeForString - startTimeForString).length());
				
				/*	Break the loop	*/
				break;
				
			/*	If the input is acceptable, output a message & let the loop continue 	*/
			} catch (NumberFormatException e) {
				System.out.println("Your input is NOT an integer!");
			}
		}
	}
	
	/**
	 * 	Test the upper case conversion of the first letter
	 * @throws Exception
	 */
	@Test
	public void testFirstLetterUpperCaseConverstion () throws Exception {
		
		/*	Get the setter name for the corersponding field name	*/
		String fieldName = "xxxx";
		String setterName = "set" + fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
		
		/*	Output the information	*/
		System.out.println("The field name: " + fieldName);
		System.out.println("The setter name: " + setterName);
	}
}
