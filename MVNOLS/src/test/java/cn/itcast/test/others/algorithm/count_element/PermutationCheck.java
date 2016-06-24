package cn.itcast.test.others.algorithm.count_element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
A non-empty zero-indexed array A consisting of N integers is given.

A permutation is a sequence containing each element from 1 to N once, and only once.

For example, array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
is a permutation, but array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
is not a permutation, because value 2 is missing.

The goal is to check whether array A is a permutation.

Write a function:

class Solution { public int solution(int[] A); }

that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.

For example, given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
the function should return 1.

Given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
the function should return 0.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class PermutationCheck {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given some input	*/
		int[] A = {4, 1, 3, 2};
		int[] B = {4, 1, 2};
		
		/*	Display the result	*/
		System.out.println("Is permutation? " + (solution(A)==1?true:false));
		System.out.println("Is permutation? " + (solution(B)==1?true:false));
		System.out.println("Is permutation? " + (solution2(A)==1?true:false));
		System.out.println("Is permutation? " + (solution2(B)==1?true:false));
	}

	/**
	 * 	This is the method for solution (100%/100%)
	 *  -- Using hash map to count the array elements
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Define a map to store the counts	*/
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
        /*	Count the array elements & store the result in the defined map	*/
        for (int i = 0; i <= A.length - 1; i++) {
        	if (!counts.containsKey(A[i])) {
        		counts.put(A[i], 1);
        	} else {
        		counts.put(A[i], counts.get(A[i]) + 1);
        	}
        }
        
        /*	Define the key set for permutation array & compare with the key set from the defined map	*/
        Set<Integer> permurationKeySet = new HashSet<Integer>();
        for (int i = 1; i <= A.length; i++) {
        	permurationKeySet.add(i);
        }
        return (counts.keySet().equals(permurationKeySet)?1:0);
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/100%)
	 * 	-- Using hash map to count the array elements
	 * @param A
	 * @return
	 */
	private static int solution2(int[] A) {
		
		/*	Define a map to store the counts	*/
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        
        /*	Initialize the count as 0 for each number	*/
        for (int i = 1; i <= A.length; i++) {
            counts.put(i, 0);   
        }
        
        /*	Loop to predicate if the map contains the element in array	*/
        for (int i = 0; i <= A.length - 1; i++) {
        	
        	/*	If there is any element contained, add 1 to its count	*/
            if (counts.containsKey(A[i])) {
                counts.put(A[i], counts.get(A[i]) + 1);
            }
        }
        
        /*	Predicate if all elements are contained	 */
        if (!counts.containsValue(0)) {
            return 1;		// Return 1 if all elements are contained
        } else {
            return 0;		// Return 0 if there is any element not contained
        }
	}
		
	/**
	 * 	Here is another solution
	 *  -- Not workable if there are repeated elements causing the actual sum equals total sum. E.g., [1, 4, 1]
	 * @param A
	 * @return
	 */
//	private static int solution3(int[] A) {
//		
//		/*	Initialize 2 parameters to store the total sum & actual sum	 */
//		int totalSum = (1 + A.length) * A.length / 2;
//		int actualSum = 0;
//		
//		/*	Compute the actual sum	*/
//		for (int i = 0; i <= A.length - 1; i++) {
//			actualSum += A[i];
//		}
//		
//		/*	If the total sum equals actual sum, return 1, otherwise return 0	*/
//		return (totalSum==actualSum?1:0);
//	}
}
