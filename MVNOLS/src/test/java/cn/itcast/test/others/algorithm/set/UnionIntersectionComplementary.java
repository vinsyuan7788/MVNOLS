package cn.itcast.test.others.algorithm.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.chainsaw.Main;
import org.junit.Test;

/**
 * 	Here is to realize Union, Intersection & Complementary
 * @author Xu (Vince) Yuan
 */
public class UnionIntersectionComplementary {
	
	/**
	 * 	Test Union, Intersection & Complementary
	 */
	@Test
	public void unionIntersectionComplementary () {
		
		Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>(){{
            add(1);
            add(2);
            add(2);
            add(4);
        }};

        Set<Integer> set2 = new HashSet<Integer>(){{
            add(2);
            add(2);
        }};

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("Union：" + result);
        
        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("Intersection：" + result);
        
        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("Complementary：" + result);
	}
}
