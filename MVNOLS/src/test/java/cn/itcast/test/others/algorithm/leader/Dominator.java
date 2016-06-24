package cn.itcast.test.others.algorithm.leader;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**

Task description
A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.

For example, consider array A such that

A[0] = 3    A[1] = 4    A[2] =  3
A[3] = 2    A[4] = 3    A[5] = -1
A[6] = 3    A[7] = 3
The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.

Write a function

class Solution { public int solution(int[] A); }
that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
For example, given array A such that

A[0] = 3    A[1] = 4    A[2] =  3
A[3] = 2    A[4] = 3    A[5] = -1
A[6] = 3    A[7] = 3
the function may return 0, 2, 4, 6 or 7, as explained above.

Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class Dominator {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] A = {3, 4, 3, 2, 3, -1, 3, 3};
//		int[] A = {3, 4, 3, 2, 8, -1, 7, 3};
		
		System.out.println("The index of dominator: " + solution(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Using Map to trace the count of elements
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Define a map to count the elements, 2 parameters to store the dominator and its index	*/
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		Integer dominator = null;
		int index = 0;
		
		/*	Count the elements in array	 */
		for (int i = 0; i<= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
			} else {
				counts.put(A[i], counts.get(A[i]) + 1);
			}
		}
		
		/*	Find the dominator	*/
		Set<Entry<Integer, Integer>> entrySet = counts.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() > A.length / 2) dominator = entry.getKey();
		}
		
		/*	Return (one of) the index of the dominator	*/
		if (dominator == null) {
			return -1;
		} else {
			for (int i = 0; i <= A.length - 1; i++) {
				if (A[i] == dominator) {
					index = i;
					break;
				}
			}
			return index;
		}
	}
}
