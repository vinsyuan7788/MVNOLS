package cn.itcast.test.others.algorithm.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
A non-empty zero-indexed array A consisting of N integers is given. The array contains an odd number of elements, and each element of the array can be paired with another element that has the same value, except for one element that is left unpaired.

For example, in array A such that:

  A[0] = 9  A[1] = 3  A[2] = 9
  A[3] = 3  A[4] = 9  A[5] = 7
  A[6] = 9
the elements at indexes 0 and 2 have value 9,
the elements at indexes 1 and 3 have value 3,
the elements at indexes 4 and 6 have value 9,
the element at index 5 has value 7 and is unpaired.
Write a function:

int solution(int A[]);

that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.

For example, given array A such that:

  A[0] = 9  A[1] = 3  A[2] = 9
  A[3] = 3  A[4] = 9  A[5] = 7
  A[6] = 9
the function should return 7, as explained in the example above.

Assume that:

N is an odd integer within the range [1..1,000,000];
each element of array A is an integer within the range [1..1,000,000,000];
all but one of the values in A occur an even number of times.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 
@author codility
 */
public class OddOccurrencesInArray {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an array that satisfies the assumption	*/
		int[] A = {9, 3, 9, 3, 9, 7, 9};
		
		/*	Display the result	*/
		System.out.println("The unpaied value: " + solution(A));
		System.out.println("The unpaied value: " + solution2(A));
		System.out.println("The unpaied value: " + solution3(A));
	}

	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Using operator arithmetic to handle odd/even element ocurrence problem
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Define an parameter for unpaired value	*/
		int unpairedValue = 0;
		
		/*	
		 * 	Loop to XOR each element to find out the unpaired one
		 * 	-- If there are even number of identical elements E, XOR them will get 0
		 *  -- If there are odd number of identical elements E, XOR them will get E	(namely the unpaired value in this problem)
		 */
		for (int i = 0; i <= A.length - 1; i++) {
			unpairedValue ^= A[i];
		}
		
		/*	Return the unpaired value	*/
		return unpairedValue;
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/100%)
	 * 	-- Using hash map for array element occurence coucnting
	 * @param A
	 * @return
	 */
	private static int solution2(int[] A) {
		
		/*	Define a map to record the array elements & an integer to record the unpaired value	 */
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		int unpairedValue = 0;
		
		/*	Use the map to keep track the elements in array	 */
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
			} else {
				counts.remove(A[i]);
			}
		}
		
		/*	Get the key set from the map & the only left key is the unpaired value	*/
		Set<Integer> keySet = counts.keySet();
		for (Integer key : keySet) {
			unpairedValue = key;
		}
		return unpairedValue;
	}
	
	/**
	 * 	This is the 3rd method for solution (100%/25%)
	 * @param A
	 * @return
	 */
	private static int solution3(int[] A) {
		
		/*	Here is to retain the unpaired value in the array: similar to bubble sort	*/
//		for (int i = 0; i <= A.length - 2; i++) {
//			for (int j = i + 1; j <= A.length - 1; j++) {
//				if (A[i] != 0 && A[j] != 0) {
//					if (A[i] == A[j]) {
//						A[i] = 0;		// Due to the assumption that each element of array A is an integer within the range [1..1,000,000,000]
//						A[j] = 0;		// Due to the assumption that each element of array A is an integer within the range [1..1,000,000,000]
//					}
//				}
//			}
//		}
		
		/*	Here is to retain the unpaired value in the array: similar to selection sort	*/
		for (int i = 0; i <= A.length - 2; i++) {
			int selectedIndex = i;
			for (int j = selectedIndex + 1; j <= A.length - 1; j++) {
				if (A[j] != 0 && A[selectedIndex] != 0) {
					if (A[j] == A[selectedIndex]) {
						A[j] = 0;
						A[selectedIndex] = 0;
					}
				}
			}
		}
		
		/*	Here is to get the unpaired value in the array	*/
		int unpairedValue = 0;
		for (int i : A) {
			if (i != 0) {
				unpairedValue = i;
			}
		}
		return unpairedValue;
	}
}
