package cn.itcast.test.others.algorithm.time_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  You have got a range of numbers between 1 to N, where the common difference is d and one of the number is repeated. 
 *  You need to write a program to find out the duplicate number.
 *  
 * @author Vince Xu Yuan
 */
public class FindDuplicate {

	/**
	 * 	This is the main funciton to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Firstly get a range of numbers between 1 to N, where the common difference is d & one of the number is repeated	 */
		int N = 30;				// Specify N
		int d = 1;				// Specify d
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= N; i = i + d) {
			numbers.add(i);
		}
		numbers.add(numbers.get(new Random().nextInt(numbers.size() - 1)));
		
		/*	Find out the duplicate number	*/
		System.out.println("The duplicate: " + getDuplicateNumber(numbers, N, d));
	}

	/**
	 * 	This is the method to get duplicate number
	 * 	-- Pure math problem
	 * @param numbers
	 * @return
	 */
	private static int getDuplicateNumber(List<Integer> numbers, int N, int d) {
		
		/*	Get the sum of the numbers	*/
		int sumOfRepeatedNumbers = 0;
		for (Integer number : numbers) {
			sumOfRepeatedNumbers += number;
		}
		
		/*	Get the sum of the unrepeated numbers between 1 and N	*/
		int unrepeatedSize = numbers.size() - 1;
		int sumOfUnrepeatedNumbers = unrepeatedSize * 1 + (unrepeatedSize * (unrepeatedSize - 1) * d) / 2;
		
		/*	Get the duplicate number	*/
		return (sumOfRepeatedNumbers - sumOfUnrepeatedNumbers);
	}
}
