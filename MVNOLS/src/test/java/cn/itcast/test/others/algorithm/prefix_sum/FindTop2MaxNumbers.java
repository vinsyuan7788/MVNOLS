package cn.itcast.test.others.algorithm.prefix_sum;

/**
 * 	Write a program to find top two maximum numbers in the given array. 
 * 	You should not use any sorting functions. 
 * 	You should iterate the array only once. 
 * 	You should not use any kind of collections in java. 
 * 
 * @author Vince Xu Yuan
 */
public class FindTop2MaxNumbers {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given a number array	*/
		int[] numbers = {5,34,78,2,45,1,99,23};
		
		/*	Find the top 2 maximum numbers	*/
		findTop2MaxNumbers(numbers);
	}

	/**
	 * 	This is the method to find the top 2 maximum numbers
	 * @param numbers
	 */
	private static void findTop2MaxNumbers(int[] numbers) {
		
		/*	Define 2 parameters	 */
		int firstMaxNumber = numbers[0];
		int secondMaxNumber = numbers[0];
		
		/*	Use one iteration to find the 2 maximum numbers	 */
		for (int number : numbers) {
			if (number > secondMaxNumber) {
				secondMaxNumber = number;
			}
			if (number > firstMaxNumber) {
				secondMaxNumber = firstMaxNumber;
				firstMaxNumber = number;
			} 
		}
		
		/*	Output the result	*/
		System.out.println("The top 1 maximum number: " + firstMaxNumber);
		System.out.println("The top 2 maximum number: " + secondMaxNumber);
	}
}
