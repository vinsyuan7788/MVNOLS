package cn.itcast.test.others.algorithm.count_element;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer (greater than 0) that does not occur in A.

For example, given:

  A[0] = 1
  A[1] = 3
  A[2] = 6
  A[3] = 4
  A[4] = 1
  A[5] = 2
the function should return 5.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class FindMinimalMissingPositiveIntegerInArray {

	/**
	 * 	This is the main function for execution 
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an input	*/
		int[] A = {1, 3, 6, 4, 1, 2};
//		int[] A = {3};
		
		/*	Display the result	*/
		System.out.println("The minimum missing integer: " + solution(A));
		System.out.println("The minimum missing integer: " + solution2(A));
	}

	/**
	 * 	This is the method for solution (100%/100%)
	 *  -- Using hash map, entry set and entry to count the elments in an array A
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Initialize a map to store the integers and their counts	 */
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		int missingMinimalPositiveInteger = 1;
		
		/*	Record the count of each integer in array 	*/
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
			} else {
				counts.put(A[i], counts.get(A[i]) + 1);
			}
		}
		
		/*	Find out all missing integers whose count should be 0	*/
		Set<Integer> keySet = counts.keySet();
		while (keySet.contains(missingMinimalPositiveInteger)) {
        	missingMinimalPositiveInteger++;
        }
		
		/*	Return the minimal missing integer	*/
		return missingMinimalPositiveInteger;
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/100%)
	 * 	-- Using hash set to store the counts of each element in an array A
	 * @param A 
	 * @return
	 */
	private static int solution2(int[] A) {
		
		/*	Define an integer parameter & a hash set to record the elements in array A	*/
		Set<Integer> hashSet = new HashSet<Integer>();
        int missingMinimalPositiveInteger = 1;

        /*	Record the count of each integer in array 	*/
        for (int i = 0 ; i <= A.length - 1; i++) {
        	hashSet.add(A[i]);          
        }
        
        /*	Find out the minimal missing positive integer	*/
        while (hashSet.contains(missingMinimalPositiveInteger)) {
        	missingMinimalPositiveInteger++;
        }
        
        /*	Return the result	*/
        return missingMinimalPositiveInteger;
	}
}
