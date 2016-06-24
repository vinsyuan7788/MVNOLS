package cn.itcast.test.others.javase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

/**
 * 	This class is to perform testing regarding collection and map
 * 	1. Traverse way of list, set and map
 * 	2. "Collections" class
 *     -- "Collection" is an interface
 *     -- "Collections.synchronizedList(Collection<T> collection)": to return a thread-safe collection
 * 
 * 	Collections (List & Set): length-variable array
 * 	1. The collection length (which is in heap memory) is variable even it is instantiated
 * 
 *  List:
 *  1. Element is duplicable & ordered
 *  2. ArrayList, LinkedList, Vector
 *  Set:
 *  1. Element is unduplicable & unordered
 *  2. HashSet, TreeSet
 *  Map:
 *  1. Element is key-value pair
 *  2. HashMap, LinkedHashMap, TreeMap
 *   
 *  ArrayXXX: ArrayList
 *  1. Sorted & ordered
 *  Linkedxx: LinkedList, LinkedHashMap
 *  1. Ordered but unsorted
 *  TreeXXX: TreeSet, TreeMap
 *  1. Sorted but unordered  
 *  HashXXX: HashSet, HashMap, Hashtable
 *  1. Unsorted & unordered
 */
public class TestCollectionAndMap {

	/** 
	 * 	Test List
	 * 	1. Using generic type (generics) in collection type (dynamic-array-like): List
	 * 	   (for interview question)
	 * 	2. <>: Short, Integer, Long, Double, Float, Character, Byte, Boolean, String, etc.
	 */
	@Test
	public void testList () {
		
		/*	Define a list (3 kinds of definition share similar methods)	 */
		List<String> list = new ArrayList<String>();	// backed by array: relativly better at query but worse at insert & remove
//		List<String> list = new LinkedList<String>();	// backed by doubly-linked list: relatively better at insert & remove but worse at query
//		List<String> list = new Vector<String>();		// thread security

		/*	Add some string into the list	*/
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ccc");
	
		/*	Output by using 3 traversing way: for loop; enhanced for loop; iterator	*/
		System.out.println("Use for loop to traverse a list:");
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			System.out.println(s);
		}
		
		System.out.println("Use foreach to traverse a list: (similar as forall in PL/SQL)");
		for (String string : list) {
			System.out.println(string);
		}
		
		System.out.println("Use iterator to traverse a list:");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	/** 
	 * 	Test Set
	 * 	1. Using generic type (generics) in collection type (dynamic-array-like): Set
	 * 	   (for interview question)
	 * 	2. <>: Short, Integer, Long, Double, Float, Character, Byte, Boolean, String, etc.
	 */
	@Test
	public void testSet () {
		
		/*	Define a set (2 kinds of definition share similar methods)	*/
		Set<String> set = new HashSet<String>();
//		Set<String> set = new TreeSet<String>();
		
		/* 	Add some string into the set	*/
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		set.add("ccc");
		
		/* 	Output by using 2 traversing way: enhanced for loop; iterator	*/
		System.out.println("Use foreach to traverse a set: (similar as forall in PL/SQL)");
		for (String string : set) {
			System.out.println(string);
		}
		
		System.out.println("Use iterator to traverse a set:");
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	/** 
	 * 	Test Map:
	 * 	1. Using generic type (generics) in Map type (dynamic-array-like): Map
	 * 	   (for interview question)
	 *  2. <>: Short, Integer, Long, Double, Float, Character, Byte, Boolean, String, etc.
	 */
	@Test
	public void testMap () {
		
		/* 	Define a map	*/
		Map<String, String> map = new HashMap<String, String>();
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		Map<String, String> map = new TreeMap<String, String>();
//		Map<String, String> map = new Hashtable<String, String>();
		
		/* 	Add some string into the map: put(key, value)	*/
		map.put("aaa", "111");
		map.put("bbb", "222");
		map.put("ccc", "222");
		map.put("ccc", "333");
		map.put("ddd", "222");
		
		/* 	Output by using 2 traversing way: ket set; entry set	*/
		System.out.println("Use key set + foreach to traverse a map:");
		Set<String> KeySet = map.keySet();		// return a set (***) such that it can use foreach
		for (String key : KeySet) {
			String value = map.get(key);
			System.out.println(key + " " + value);
		}
		
		System.out.println("Use entry set + foreach to traverse a map:");
		Set<Entry<String, String>> EntrySet = map.entrySet();	// return a set (***) such that it can use foreach
		for (Entry<String, String> entry : EntrySet) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + " " + value);
		}
	}
	
	/**
	 * 	Test Collections class
	 */
	@Test
	public void testCollectionsClass () throws Exception {
		
		/*	Instantiate a integer list: each element is between 10 and 99	*/
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add((int) (Math.random()*90+10));
		integerList.add(new Random().nextInt(90)+10);
		integerList.add((int) (Math.random()*90+10));
		integerList.add(new Random().nextInt(90)+10);
		integerList.add((int) (Math.random()*90+10));
		System.out.println("The original integer list: " + integerList.toString());
		int elementForIndex = integerList.get(1);
		System.out.println("The element for index: " + elementForIndex);
		
		/*	Reverse the integer elements: by Collections class	*/
		Collections.reverse(integerList);
		System.out.println("The reverse integer array: " + integerList.toString());
		
		/*	Sort the integer array in ascending order	*/
		Collections.sort(integerList);
		System.out.println("The ascending integer array: " + integerList.toString());
		
		/*	Binary search the integer list: list must be sorted in ASCENDING order	*/
		int indexInAscOrder = Collections.binarySearch(integerList, elementForIndex);
		if (indexInAscOrder < 0) {
			System.out.println("The element does not exist.");
		} else {
			System.out.println("The index of the element in ascending order after binary search: " + indexInAscOrder);
		}
		
		/*	Sort the integer array in descending order	*/
		Collections.sort(integerList, new DescComparator());
		System.out.println("The descending integer array: " +integerList.toString());
		
		/*	Binary search the integer array: array must be sorted in DESCENDING order	*/
		int indexInDescOrder = Collections.binarySearch(integerList, elementForIndex, new DescComparator());
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
		 *  or 
		 *  "return o1.compareTo(o2)"
		 *  
		 * 	Hence for sorting in descending order, just flip over the return result, namely
		 *  1. if o1 < o2: return 1
		 * 	2. if o1 > o2: return -1
		 * 	3. if o1 == o2: return 0
		 *  or 
		 *  "return o2.compareTo(o1)"
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	}
}
