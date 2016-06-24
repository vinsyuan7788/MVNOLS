package cn.itcast.test.others.algorithm.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

For example, number 9 has binary representation 1001 and contains a binary gap of length 2. 
The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. 
The number 20 has binary representation 10100 and contains one binary gap of length 1. 
The number 15 has binary representation 1111 and has no binary gaps.

Write a function that, given a positive integer N, returns the length of its longest binary gap. 
The function should return 0 if N doesn't contain a binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.

Assume that:

N is an integer within the range [1..2,147,483,647].
Complexity:

expected worst-case time complexity is O(log(N));
expected worst-case space complexity is O(1).

@author codility
 */
public class FindLongestBinaryGap {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		System.out.println("Using the 1st method: ");
		System.out.println("The longest binary gap: " + findLongestBinaryGap(1041));
		System.out.println("The longest binary gap: " + findLongestBinaryGap(9));
		System.out.println("The longest binary gap: " + findLongestBinaryGap(529));
		System.out.println("The longest binary gap: " + findLongestBinaryGap(20));
		System.out.println("The longest binary gap: " + findLongestBinaryGap(15));
		System.out.println("The longest binary gap: " + findLongestBinaryGap(1024));
		
		System.out.println("Using the 2nd method: ");
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(1041));
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(9));
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(529));
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(20));
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(15));
		System.out.println("The longest binary gap: " + findLongestBinaryGap2(1024));
	}
	
	/**
	 * 	This is the method for solution (100%/NA)
	 *  -- Using operator for binary operation
	 * @param N
	 * @return
	 */
	private static int findLongestBinaryGap (int N) {
		
		/*	Define parameters for zero counter, right most binary bit & longest binary gap	*/
		int countOfZero = -1;			// Set to -1 hence countOfZero began to count the zeros after meeting the first "1"
		int rightMostBinaryBit = 0;
		int longestBinaryGap = 0;
		
		/*	Loop to count the zeros for the longest binary gap using logical operator	*/
		while (N > 0) {
			rightMostBinaryBit = N & 1;
			N = N >> 1;
			if (rightMostBinaryBit == 0 && countOfZero >= 0) {
				countOfZero++;
			}
			if (rightMostBinaryBit == 1) {
				longestBinaryGap = (countOfZero > longestBinaryGap ? countOfZero : longestBinaryGap);
				countOfZero = 0;
			}
		}
		
		/*	Return the longest binary gap	*/
		return longestBinaryGap;
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/NA)
	 * 	-- Using list to store the indice of "1" & binary gaps
	 * @param N
	 * @return
	 */
	private static int findLongestBinaryGap2 (int N) {
		
		/*	Initialize 2 lists to store the indice of binary '1' and all binary gaps respectively	*/
		List<Integer> indiceOfOne = new ArrayList<Integer>();
		List<Integer> binaryGaps = new ArrayList<Integer>();
		
		/*	Convert the decimal number to binary number & store the indice of binary '1'	*/
		String binaryString = Integer.toBinaryString(N);
		for (int i = 0; i <= binaryString.length() - 1; i++) {
			if (Integer.parseInt(binaryString.charAt(i) + "") == 1) {
				indiceOfOne.add(i);
			}
		}
		
		/*	If there is only one "1" existing in the binary string, then return 0: e.g., 1024	*/
		if (indiceOfOne.size() == 1) {
			return 0;
		}
		
		/*	Compute & store all binary gaps, and find the longest binary gap	*/
		for (int i = 0; i <= indiceOfOne.size() - 2; i++) {
			binaryGaps.add(indiceOfOne.get(i+1) - indiceOfOne.get(i) - 1);
		}
		return Collections.max(binaryGaps);
	}
}
