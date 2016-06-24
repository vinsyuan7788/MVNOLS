package cn.itcast.test.others.javase.testclass.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 	This is a list generator to get a list with generics
 * @author Xu (Vince) Yuan
 * @param <T>
 */
public class Generics<T> {

	/**
	 * 	This is the constructor
	 */
	public List<T> generateArrayList() {
		return new ArrayList<T>();
	}
}
