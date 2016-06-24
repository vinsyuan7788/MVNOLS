package cn.itcast.test.others.algorithm.time_complexity;

import java.util.Arrays;

/**
A non-empty zero-indexed array A consisting of N integers is given. Array A represents numbers on a tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part and the sum of the second part.

For example, consider array A such that:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
We can split this tape in four places:

P = 1, difference = |3 − 10| = 7 
P = 2, difference = |4 − 9| = 5 
P = 3, difference = |6 − 7| = 1 
P = 4, difference = |10 − 3| = 7 
Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty zero-indexed array A of N integers, returns the minimal difference that can be achieved.

For example, given:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
the function should return 1, as explained above.

Assume that:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class FindMinimumDifferenceInArray {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an input array	*/
		int[] A = {3, 1, 2, 4, 3};
		
		/*	Display the result	*/
		System.out.println("The minimum difference: " + solution(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Initialize the sum on the left side, the total sum, and a new array to store the differences	*/
        int leftSum = 0;
        int totalSum = 0;
        int[] absDifferences = new int[A.length - 1];
        
        /*	Compute the total sum	*/
        for (int i = 0; i <= A.length - 1; i++) {
            totalSum += A[i];   
        }   
        
        /*	Compute the left sum & right sum, and store the differences	 */
        for (int i = 0; i <= A.length - 2; i++) {
            totalSum -= A[i];
            leftSum += A[i];
            absDifferences[i] = Math.abs(totalSum - leftSum);
        }
        
        /*	Find the minimum value in the difference array	*/
        Arrays.sort(absDifferences);
        return absDifferences[0];
	}
}
