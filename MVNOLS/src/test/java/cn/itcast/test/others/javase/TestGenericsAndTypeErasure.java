package cn.itcast.test.others.javase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.generics.Generics;
import cn.itcast.test.others.javase.testclass.generics.GenericsUpperBound;
import cn.itcast.test.others.javase.testclass.generics.classes.A;
import cn.itcast.test.others.javase.testclass.generics.classes.B;
import cn.itcast.test.others.javase.testclass.generics.classes.C;

/**
 * 	This class is to perform testing regarding generics & type erasure
 * 
 * 
 * @author Xu (Vince) Yuan
 */
public class TestGenericsAndTypeErasure {

	/**
	 * 	Test the generics
	 */
	@Test
	public void testGenerics () {
		
		/*	Get a list from a method with generics	*/
		List<Integer> intList = new Generics<Integer>().generateArrayList();
		intList.add(1);
		intList.add(2);
		
		/*	Get a list from a method with generics	*/
		List<String> strList = new Generics<String>().generateArrayList();
		strList.add("aaa");
		strList.add("bbb");
		
		/*	Display the result	*/
		System.out.println("1st list: " + intList);
		System.out.println("2nd list: " + strList);
	}
	
	/**
	 * 	Test the upper bound of generics with type erasure
	 */
	@Test
	public void testUpperBound () {
		
		/*	listA cannot be initiated since A exceeds the upper bound B	 */
//		List<A> listA = new GenericsUpperBound<A>().generateArrayList();
//		System.out.println(listA.size());
		
		List<B> listB = new GenericsUpperBound<B>().generateArrayList();
		System.out.println("The class of listB: " + listB.getClass());
		
		List<C> listC = new GenericsUpperBound<C>().generateArrayList();
		System.out.println("The class of listC: " + listC.getClass());
	}
	
	/**
	 * 	Test the lower bound of generics with type erasure
	 */
	@Test
	public void testLowerBound () {
	
		/*	listC cannot be initiated since C exceeds the lower bound B	 */
		List<? super Integer> listA = new ArrayList<Number>();
		System.out.println("The class of listA: " + listA.getClass());
		
		List<? super B> listB = new ArrayList<B>();
		System.out.println("The class of listB: " + listB.getClass());
		
//		List<? super B> listC = new ArrayList<C>();
//		listC.add(new C());
	}
	
	/**
	 * 	Test type erasure
	 */
	@Test
	public void testTypeErasure () {
		
		/*	Get 2 lists	*/
		List<Integer> li = new ArrayList<Integer>();
		List<Float> lf = new ArrayList<Float>();
		
		/*	Return true: means the data type of the list is erased	*/
		if (li.getClass() == lf.getClass()) { 
			System.out.println("The class of li: " + li.getClass());
			System.out.println("The class of lf: " + lf.getClass());
		    System.out.println("2 classes are equal? " + true);
		}
	}
	
	
}
