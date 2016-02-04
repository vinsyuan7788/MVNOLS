package cn.itcast.test.others.javase;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import cn.itcast.global.utils.ArrayUtils;

/**
 * 	This class is to perform testing regarding array
 * 	1. Array to String and to List
 * 	2. "Arrays" & "Collections" class:
 *     -- "Array" is the hardware for load-balancing
 *     -- "Collection" is an interface
 * 	3. Multiple-dimensional array
 * 
 * 	Array: the length is invariable
 * 	1. Once an array is instantiated, the length (which is in heap memory) is fixed.
 * 	2. The address of array elements (not objects) in heap memory is continuous. 
 */
public class TestArray {

	/**
	 * 	Test String array
	 */
	@Test
	public void testStringArray () throws Exception {

		/*	Instantiate a string array dynamically	*/
		String[] hobbyArray = new String[5];
		hobbyArray[0] = "Movies";
		hobbyArray[1] = "Sports";
		hobbyArray[2] = "Programing";
		hobbyArray[3] = "Music";
		hobbyArray[4] = "Thinking";
		System.out.println("The hobby array: " + Arrays.toString(hobbyArray));
		
		/*	Convert the array to string	 */
		String hobbyString = ArrayUtils.toString(hobbyArray);
		System.out.println("The hobby string: " + hobbyString);
		
		/*	Convert the array to list	*/
		List<String> hobbyList = Arrays.asList(hobbyArray);
		System.out.println("The hobby list: " + hobbyList);
	}
	
	/**
	 * 	Test integer array
	 * 	1. If the integer array is "int[]", then "sort(integerArray, myComparator)" is unable to use due to generic type of implemented Comparator class
	 */
	@Test
	public void testIntegerArray () throws Exception {
		
		/*	Instantiate a integer array statically: each element is between 10 and 99	*/
		Integer[] integerArray = new Integer[]{(int) (Math.random()*90+10), new Random().nextInt(90)+10, (int) (Math.random()*90+10), new Random().nextInt(90)+10, (int) (Math.random()*90+10)};
//		Integer[] integerArray = {(int) (Math.random()*90+10), new Random().nextInt(90)+10, (int) (Math.random()*90+10), new Random().nextInt(90)+10, (int) (Math.random()*90+10)};
		System.out.println("The original integer array: " + Arrays.toString(integerArray));
		int elementForIndex = integerArray[1];
		System.out.println("The element for index: " + elementForIndex);
		
		/*	Reverse the integer elements: by Collections class	*/
		List<Integer> integerList = Arrays.asList(integerArray);
		Collections.reverse(integerList);
		System.out.println("The reverse integer array: " + Arrays.toString(integerList.toArray()));
		
		/*	Sort the integer array in ascending order	*/
		Arrays.sort(integerArray);
		System.out.println("The ascending integer array: " + Arrays.toString(integerArray));
		
		/*	Binary search the integer array: array must be sorted in ASCENDING order	*/
		int indexInAscOrder = Arrays.binarySearch(integerArray, elementForIndex);
		if (indexInAscOrder < 0) {
			System.out.println("The element does not exist.");
		} else {
			System.out.println("The index of the element in ascending order after binary search: " + indexInAscOrder);
		}
		
		/*	Sort the integer array in descending order	*/
		Arrays.sort(integerArray, new DescComparator());
		System.out.println("The descending integer array: " + Arrays.toString(integerArray));
		
		/*	Binary search the integer array: array must be sorted in DESCENDING order	*/
		int indexInDescOrder = Arrays.binarySearch(integerArray, elementForIndex, new DescComparator());
		if (indexInDescOrder < 0) {
			System.out.println("The element does not exist.");
		} else {
			System.out.println("The index of the element in descending order after binary search: " + indexInDescOrder);
		}
	}
	
	/*	This is an inner class for array sorting in descending order	*/
	class DescComparator implements Comparator<Integer> {

		/**
		 * 	By default: for sorting in ascending order
		 * 	1. if o1 < o2: return -1
		 * 	2. if o1 > o2: return 1
		 * 	3. if o1 == o2: return 0
		 * 	Hence for sorting in descending order, just flip over the return result
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			
			if (o1 < o2) { 
				return 1;
	        } else if(o1 > o2) {
	        	return -1;
	        } else {
	        	return 0;
			}
		}
	}
}
