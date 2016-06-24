package cn.itcast.test.others.javase.testclass.generics;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.test.others.javase.testclass.generics.classes.B;

/**
 * 	This is a list generator to get a list with generics with upper bound
 * 	-- If <T super B> is not allowed to occur in class level
 * 
 * @author Xu (Vince) Yuan
 * @param <T>
 */
public class GenericsUpperBound <T extends B> {

	/**
	 * 	This is the constructor
	 */
	public List<T> generateArrayList() {
		return new ArrayList<T>();
	}
}
