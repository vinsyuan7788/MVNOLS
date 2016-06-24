package cn.itcast.test.others.algorithm;

/**
 * 	A perfect number is a positive integer that is equal to the sum of its proper positive divisors, that is,
 * 	the sum of its positive divisors excluding the number itself. 
 * 	Equivalently, a perfect number is a number that is half the sum of all of its positive divisors. 
 * 	The first perfect number is 6, because 1, 2 and 3 are its proper positive divisors, and 1 + 2 + 3 = 6. 
 * 	Equivalently, the number 6 is equal to half the sum of all its positive divisors: 
 * 				( 1 + 2 + 3 + 6 ) / 2 = 6. 
 * 
 * @author Vince Xu Yuan
 */
public class FindPerfectNumber {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Show if a number is perfect number	*/
		int number = 28;
		System.out.println("Is " + number + " a perfect number? " + isPerfectNumber(28));
	}

	/**
	 * 	This is the method to predicate if a number is perfect
	 * @param number
	 * @return
	 */
	private static boolean isPerfectNumber(int number) {
		
		/*	Initialize the sum of divisors	*/
		int sumOfDivisors = 0;
		
		/*	From 1 to number/2, find all divisors & add them into the sum 	*/
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0) {
				sumOfDivisors += i;
			}
		}
		
		/*	If the sum of divisors equals the number, return true, otherwise return false	*/
		if (sumOfDivisors == number) {
			return true;
		} else {
			return false;
		}
	}
}
