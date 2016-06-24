package cn.itcast.test.others.algorithm.sort;

import java.util.Arrays;

/**
A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class FindMaxProductOfTriplet {
	
	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] A = {-3, 1, 2, -2, 5, 6};
		
		System.out.println("The maximum product: " + solution(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
				
		/*	If there is any special case, return 0	*/
		if (A.length < 3) {
			return 0;
		}
		
		/*	Otherwise sort the array & get the max product in general situation	*/
		Arrays.sort(A);
		int maxProduct = A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
		
		/*	If the sorted array are all positive or negative elements	*/
		if (A[0] >= 0 || A[A.length - 1] <= 0) {
			return maxProduct;
		
		/*	Otherwise the sorted array consists of both positive and negative numbers	*/
		} else {
			
			/*	If there is only 1 negative number	*/
			if (A[1] >= 0) {
				return maxProduct;
				
			/*	Otherwise: if there are at least 2 negative numbers	 */
			} else {
				return (A[0] * A[1] * A[A.length - 1] > maxProduct?A[0] * A[1] * A[A.length - 1]:maxProduct);
			}
		} 
	}
}
