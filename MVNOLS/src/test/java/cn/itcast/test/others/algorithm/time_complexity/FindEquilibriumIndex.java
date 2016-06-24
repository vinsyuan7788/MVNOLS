package cn.itcast.test.others.algorithm.time_complexity;

import java.util.ArrayList;
import java.util.List;

/**
A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e. 
A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.

For example, consider the following array A consisting of N = 8 elements:

  A[0] = -1
  A[1] =  3
  A[2] = -4
  A[3] =  5
  A[4] =  1
  A[5] = -6
  A[6] =  2
  A[7] =  1
  
P = 1 is an equilibrium index of this array, because:
A[0] = −1 = A[2] + A[3] + A[4] + A[5] + A[6] + A[7]

P = 3 is an equilibrium index of this array, because:
A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]

P = 7 is also an equilibrium index, because:
A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0

and there are no elements with indices greater than 7.
P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.

Write a function that, given a zero-indexed array A consisting of N integers, returns all of its equilibrium indices. 
The function should return null list if no equilibrium index exists.

@author codility
*/
public class FindEquilibriumIndex {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
		System.out.println("The Equilibrium Index(es): " + findEquilibriumIndex(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * @param A
	 * @return
	 */
	private static List<Integer> findEquilibriumIndex (int[] A) {
		
		/*	Initialize array size, total sum, left sum, and a list to store equilibrium indice	*/
		int N = A.length;
		int totalSum = 0;
		int leftSum = 0;
		List<Integer> allEquilibriumIndice = new ArrayList<Integer>();
		
		/*	Compute the total sum of the array	*/
		for (int i = 0; i < N; i++) {
			totalSum += A[i];
		}
		
		/*	Get the equilibrium indice	*/ 
		for (int p = 0; p < N; p++) {
			totalSum -= A[p];
			
			if (leftSum == totalSum) {
				allEquilibriumIndice.add(p);
			}
			
			leftSum += A[p];
		}
		
		return allEquilibriumIndice;
	}
}
