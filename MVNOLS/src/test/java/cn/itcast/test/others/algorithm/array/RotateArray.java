package cn.itcast.test.others.algorithm.array;

/**
A zero-indexed array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is also moved to the first place.

For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal is to rotate array A K times; that is, each element of A will be shifted to the right by K indexes.

Write a function:

class Solution { public int[] solution(int[] A, int K); }

that, given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.

For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].

Assume that:

N and K are integers within the range [0..100];
each element of array A is an integer within the range [−1,000..1,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

@author codility
 */
public class RotateArray {

	/**
	 * 	This is the main function for execution 
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameters	*/
		int[] A = {3, 8, 9, 7, 6};
		int K = 3;
		
		/*	Display the result	*/
		int[] newArray = solution(A, K);
		System.out.println("Rotated Array (to right): ");
		for (int i : newArray) {
			System.out.print(i);
		}
		
		/*	If rotate the array to left	 */
		int[] A2 = {3, 8, 9, 7, 6};
		int K2 = 3;
		int[] newArray2 = leftShift(A2, K2);
		System.out.println("\rRotated Array (to left): ");
		for (int i : newArray2) {
			System.out.print(i);
		}
	}
	
	/**
	 * 	This is the method for solution (100%/NA)
	 * @param A
	 * @param K
	 * @return
	 */
	private static int[] solution(int[] A, int K) {
		
		/*	If there is any special case, return A directly	*/
		if (A == null || A.length == 0) {
			return A;
		}
		
		/*	Otherwise process K	*/
		if (K >= A.length) K = K % A.length;
		
		/*	Rotate & return the array	*/
		for (int k = 0; k < K; k++) {
			int temp = A[A.length - 1];
			for (int i = A.length - 2; i >= 0; i--) {
				A[i+1] = A[i];
			}
			A[0] = temp;
		}
		return A;
	}
	
	/**
	 * 	Here is the method if want to rotate the array to left	
	 * @param A
	 * @param K
	 * @return
	 */
	private static int[] leftShift(int[] A, int K) {
		
		/*	If there is any special case, return A directly	*/
		if (A == null || A.length == 0) {
			return A;
		}
		
		/*	Otherwise process K	*/
		if (K >= A.length) K = K % A.length;
		
		/*	Rotate & return the array	*/
		for (int k = 0; k < K; k++) {
			int temp = A[0];
			for (int i = 1; i <= A.length - 1; i++) {
				A[i-1] = A[i];
			}
			A[A.length - 1] = temp;
		}
		return A;
	}
}
