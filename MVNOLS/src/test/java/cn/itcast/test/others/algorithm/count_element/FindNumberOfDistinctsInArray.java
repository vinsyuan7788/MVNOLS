package cn.itcast.test.others.algorithm.count_element;

import java.util.HashMap;
import java.util.Map;

/**
Write a function

class Solution { public int solution(int[] A); }

that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
For example, given array A consisting of six elements such that:

A[0] = 2    A[1] = 1    A[2] = 1
A[3] = 2    A[4] = 3    A[5] = 1
the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.

Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class FindNumberOfDistinctsInArray {

	/**
	 * 	This is main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an input	*/
		int[] A = {2, 1, 1, 2, 3, 1, 5, 3};
		
		/*	Display the result	*/
		System.out.println("The number of distincts: " + solution(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Using hash map for element counting
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Initialize a map to store the counts of array elements 	*/
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		/*	Compute the counts for the array elements	*/
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
			} else {
				counts.put(A[i], counts.get(A[i]) + 1);
			}
		}
		
		/*	Return the number of distincts	*/
		return counts.keySet().size();
	}
}
