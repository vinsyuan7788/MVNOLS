package cn.itcast.test.others.javase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 	This class is to perform testing regarding stack memory & heap memory
 * 	1. Reference: the variable in stack memory refers to the object|instance in heap memory
 * 
 * 	JVM memory rough composition: stack, heap & method area:
 * 	1. Stack: to store variables
 *     -- FILO 
 *     -- Automatically-assigned by JVM|OS
 * 	2. Heap: to store objects|instances
 *     -- FIFO 
 *     -- Manually-assiend by programmer
 *  3. Method Area: to store compiled java files
 *     -- Refer to "testReflection.java"
 */
public class TestMemory {

	/**
	 * 	Test the difference between "==" and "equals()" for String objects
	 */
	@Test
	public void testReferenceAndValueComparisonForString () throws Exception {
		
		/*	Create 2 null string references	 */
		String string1 = null;
		String string2 = null;
		String string3 = null;
		
		/*	Do the comparison between the strings with same reference and value	*/
		string1 = "This is a test String";
		string2 = "This is a test String";
		System.out.println("1st comparison:");
		if (string1 == string2) {
			System.out.println("string1 and string2 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
		
		/*	Do the comparison between the strings with different references and same value	*/
		string1 = "This is a test String";
		string2 = new String("This is a test String");
		System.out.println("2nd comparison:");
		if (string1 == string2) {
			System.out.println("string1 and string2 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
		
		/*	Do the comparison between the strings with different references and same value	*/
		string1 = new String("This is a test String");
		string2 = new String("This is a test String");
		System.out.println("3rd comparison:");
		if (string1 == string2) {
			System.out.println("string1 and string2 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
		
		/*	Do the comparison between the strings with different references and same value	*/
		string1 = new StringBuffer("This is a test String").toString();
		string2 = new String("This is a test String");
		string3 = "This is a test String";
		System.out.println("4th comparison:");
		if (string1 == string2) {
			System.out.println("string1 and string2 have the same reference");
		} else {
			System.out.println("string1 and string2 have different references");
		}
		if (string1.equals(string2)) {
			System.out.println("string1 and string2 have the same value");
		} else {
			System.out.println("string1 and string2 have difference values");
		}
		if (string1 == string3) {
			System.out.println("string1 and string3 have the same reference");
		} else {
			System.out.println("string1 and string3 have different references");
		}
		if (string1.equals(string3)) {
			System.out.println("string1 and string3 have the same value");
		} else {
			System.out.println("string1 and string3 have difference values");
		}
		if (string2 == string3) {
			System.out.println("string2 and string3 have the same reference");
		} else {
			System.out.println("string2 and string3 have different references");
		}
		if (string2.equals(string3)) {
			System.out.println("string2 and string3 have the same value");
		} else {
			System.out.println("string2 and string3 have difference values");
		}
	}
	
	/**
	 * 	Test the "==" for Integer objects
	 * 	1. If the integer value is within the range between -128 and 127, the obejcts will be stored in IntegerCache class
	 * 	   so that the value can be referenced repeatedly & improve the reusabilty
	 *  2. If the integer value is out of range (-128, 127), the JVM will assign a new reference to the integer object
	 */
	@Test
	public void testReferenceAndValueComparisonForInteger () throws Exception {
		
		/*	Create 2 null integer references  */
		Integer a = null;
		Integer b = null;
		
		/*	Compare the references of value with range [-200, 199) 	 */
		int sameReferenceCount = 0;
		int diffReferenceCount = 0;
		List<Integer> sameReferenceValues = new ArrayList<Integer>();
		for (a = -200, b = -200; a < 200 && b < 200; a++, b++) {
			if (a == b) {
				sameReferenceCount += 1;
				sameReferenceValues.add(a);
			} else {
				diffReferenceCount += 1;
			}	
		}
		System.out.println("The same reference count: " + sameReferenceCount);
		System.out.println("The different reference count: " + diffReferenceCount);
		System.out.println("Total counts: " + (sameReferenceCount + diffReferenceCount));
		System.out.println("The range of values for same reference: [" + sameReferenceValues.get(0) + ", " + sameReferenceValues.get(sameReferenceValues.size()-1) + "]");
		System.out.println("The values out of above range have different references");
	}
}
