package cn.itcast.test.others.algorithm;

/**
 * 	Write a program to reverse a number using numeric operations.
 * 
 * @author Vince Xu Yuan
 */
public class ReverseNumber {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Show the reverse number	 */
		System.out.println("Reverse Number: " + reverseNumber(12345));
	}

	/**
	 * 	This is the method to reverse a number using numeric operations
	 * @param number
	 * @return
	 */
	private static int reverseNumber(int number) {
		
		/*	Initialize a necessary parameter	*/
		int reverseNumber = 0;
		
		/*	While the number is not 0, get the remainder and expand it 10 times & shrink the number 10 times each time, until the number becomes 0 */
		while (number != 0) {
			reverseNumber = (number % 10) + (reverseNumber * 10); 
			number = number / 10;
		}
		
		/*	Return the reverse number	 */
		return reverseNumber;
	}
}
