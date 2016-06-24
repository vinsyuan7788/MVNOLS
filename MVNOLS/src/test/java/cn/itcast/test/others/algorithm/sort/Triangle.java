package cn.itcast.test.others.algorithm.sort;

import java.util.Arrays;

/**
A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

A[P] + A[Q] > A[R],
A[Q] + A[R] > A[P],
A[R] + A[P] > A[Q].
For example, consider array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
Triplet (0, 2, 4) is triangular.

Write a function:

class Solution { public int solution(int[] A); }

that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

For example, given array A such that:

  A[0] = 10    A[1] = 2    A[2] = 5
  A[3] = 1     A[4] = 8    A[5] = 20
the function should return 1, as explained above. Given array A such that:

  A[0] = 10    A[1] = 50    A[2] = 5
  A[3] = 1
the function should return 0.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class Triangle {

	/**
	 * 	This is the main function for exectuion
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameters	*/
		int[] A = {10 ,2, 5, 1, 8, 20};
		
		/*	Display the result	*/
		System.out.println("Triangle exists? " + (solution(A)==1?true:false));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
        
		/*	If there is any special case, return 0	*/
        if (A == null || A.length < 3) {
            return 0;
        }
        
        /*	Otherwise, sort the array and find the triangle	 */
        Arrays.sort(A);
        for (int i = 0; i <= A.length - 3; i++) {
        	/*
        	 * 	1. If A[i] <= 0, then A[i] + A[i+1] <= A[i+2], only need to consider the situation where A[i] > 0
        	 * 	2. "A[i] + A[i+1] > A[i+2]" will cause overflow, hence here should be "A[i] > A[i+2] - A[i+1]"
        	 */
            if (A[i] > 0 && (A[i] > A[i+2] - A[i+1] )) {	
            	return 1;									// If found the triangle, return 1
            }
        }
        
        /*	If there is no triangle found, then return 0	*/
        return 0;
    }
}
