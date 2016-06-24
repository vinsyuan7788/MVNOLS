package cn.itcast.test.others.algorithm.count_element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 	Given any 2 arrays, find out the intersection.
 * 	E.g., given A = {1, 2, 2, 4, 4, 5, 5, 6, 7, 10};
 *			    B = {2, 2, 4, 5, 5, 5, 7};
 *	      return {2, 4, 5, 7}
 *	      
 * @author Xu (Vince) Yuan
 */
public class Intersection {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameters	*/
		int[] A = {1, 2, 2, 4, 4, 5, 5, 6, 7, 10};
		int[] B = {2, 2, 4, 5, 5, 5, 7};
		int[] C = {2, 2, 4, 5, 5, 5, 7};
		int[] D = {1, 2, 2, 4, 4, 5, 5, 6, 7, 10};
			
		/*	Display the result	*/
		System.out.println("The intersection: ");
		for (int i = 0; i <= intersection(A, B).length - 1; i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(intersection(A, B)[i]);
		}
		System.out.println("\rThe intersection: ");
		for (int i = 0; i <= intersection(A, B).length - 1; i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(intersection(A, B)[i]);
		}
	}
	
	/**
	 * 	This is the method for solution
	 * 	1. If we want to get the union, then change 2 parts, refer to the comments below
	 * 	2. If we want to get the complementary, then change 1 part, refer to the comments below
	 * @param A
	 * @param B
	 * @return
	 */
	private static int[] intersection (int[] A, int[] B) {
		
		/*	Initialize a map for element counting	*/
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		
		/*	Count the element in array A	*/
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);							// Set its count as 1 to avoid duplicate
			}
		}
		
		/*	
		 * 	If B contains the sane element, then set the corresponding count to 0	
		 * 	1. If we want to get the union, then the "if" statement here should be:
		 * 		if (!counts.containsKey(B[i])) {				// Add a "!" at the beginning
		 *			counts.put(B[i], 0);						// Set its count to 1 if the element does not exist
		 *		}
		 */
		for (int i = 0; i <= B.length - 1; i++) {
			if (counts.containsKey(B[i])) {
				counts.put(B[i], 0);							// Set its count to 0 if the element exists
			}
		}
		
		/*	Initialize a list to store intersected elements	 */
		List<Integer> intersectElements = new ArrayList<Integer>();
		
		/*	
		 * 	Get the entry set from the map & put the elements whose value is 0 to the list	
		 * 	1. If we want to get the union, then the "if" statement here should be:
		 * 		if (entry.getValue() == 1) {					// Change the value from 0 to 1
		 *			intersectElements.add(entry.getKey());
		 *		}
		 * 	2. If we want to get the complementary, then the "if" statement here should be:
		 * 		if (entry.getValue() == 1) {					// Change the value from 0 to 1
		 *			intersectElements.add(entry.getKey());
		 *		}
		 */
		Set<Entry<Integer, Integer>> entrySet = counts.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() == 0) {
				intersectElements.add(entry.getKey());
			}
		}
		
		/*	Convert the list to array for return	*/
		Object[] integers = intersectElements.toArray();
		int[] intArray = new int[integers.length];
		for (int i = 0; i <= integers.length - 1; i++) {
			intArray[i] = (int) integers[i];
		}
		return intArray;
	}
}
