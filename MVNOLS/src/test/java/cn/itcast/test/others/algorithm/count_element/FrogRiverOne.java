package cn.itcast.test.others.algorithm.count_element;

import java.util.HashMap;
import java.util.Map;

/**
A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.

You are given a zero-indexed array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.

The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.

For example, you are given integer X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.

Write a function:

class Solution { public int solution(int X, int[] A); }

that, given a non-empty zero-indexed array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

If the frog is never able to jump to the other side of the river, the function should return −1.

For example, given X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
the function should return 6, as explained above.

Assume that:

N and X are integers within the range [1..100,000];
each element of array A is an integer within the range [1..X].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(X), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class FrogRiverOne {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an input	*/
		int X = 5;
		int[] A = {1, 3, 1, 4, 2, 3, 5, 4};
		
		/*	Display the result	*/
		System.out.println("The minimum time: " + solution(X, A));
		System.out.println("The minimum time: " + solution2(X, A));
		System.out.println("The minimum time: " + solution3(X, A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 *  -- Using another array to store the counts of each element in array A
	 *     -- However if the element value in array A involves negative integer, this method is not that implementable as the 2nd method
	 * @param X
	 * @param A
	 * @return
	 */
	private static int solution(int X, int[] A) {
		
		/*	Define an array & a parameter to keep track X	*/
		int[] count = new int[X + 1];
		int steps = 0;
		
		/*	Initialize the defined array 	*/
		for (int i = 0; i <= count.length - 1; i++) {
			count[i] = 0;
		}
		
		/*	Use the defined parameter steps to keep track X, once the steps meet X, return the earliest time	*/
		for (int i = 0; i <= A.length - 1; i++) {
			count[A[i]]++;
			if (count[A[i]] == 1) steps++;
			if (steps == X) return i;
		}
		
		/*	Otherwise return -1	 */
		return -1;
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/100%)
	 *  -- Using hash map to store the counts of each element in array A
	 * @param X
	 * @param A
	 * @return
	 */
	private static int solution2(int X, int[] A) {	
		
		/*	Define one map to store the count of elements in A & one parameter to keep track X	 */
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		int steps = 0;	
		
		/*	Use the defined parameter steps to keep track X, once the steps meet X, return the earliest time	*/
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
				steps++;
			} else {
				counts.put(A[i], counts.get(A[i] + 1));
			}
			if (steps == X) return i;
		}
		
		/*	Otherwise return -1	 */
		return -1;
	}
	
	/**
	 * 	This is the 3rd method for solution (100%/40%)
	 *  -- Using hash map to store the counts of each element in array A
	 * @param X
	 * @param A
	 * @return
	 */
	private static int solution3(int X, int[] A) {
		
		/*	Define one map to store the counting	 */
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		/*	Initialize all the numbers within X to 0 count	 */
		for (int i = 1; i <= X; i++) {
			counts.put(i, 0);
		}
		
		/*	Loop to predicate if the numbers within X appear in the array	*/
		for (int i = 0; i <= A.length - 1; i++) {
			
			/*	If there is any number within X appear in the array, add 1 to its count	 */
			if (counts.containsKey(A[i])) {
				counts.put(A[i], counts.get(A[i]) + 1);
			}
			
			/*	If all the numbers within X occur, then return the minimum time	*/
			if (!counts.containsValue(0)) {
				return i;
			}
		}
		
		/*	If there is any number within X not occuring, return -1	 */
		return -1;
	}
}
