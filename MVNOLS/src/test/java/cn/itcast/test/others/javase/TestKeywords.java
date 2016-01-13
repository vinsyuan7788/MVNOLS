package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.singleton.EagerSingleton;
import cn.itcast.test.others.javase.testclass.singleton.LazySingleton;
import cn.itcast.test.others.javase.testinterface.Run;

/**
 * 	This class is to perform testing regarding keywords
 * 	
 * 	Modifiers: keywords to change the meaning of definition
 * 	1. Access modifier: public|private|protected
 *     -- Change the method accessibility|visibility
 *  2. Non-access modifier: static|final|abstract|synchronized|volatile
 *     -- Achieve specific functionalities 
 */
public class TestKeywords {

	/**
	 * 	Test "final"
	 */
	@Test
	public void testFinal () throws Exception {
		
		final int a = 2;
		System.out.println(a);
		//	a = 3;					// cannot be reassigned since it is finalized
		//	System.out.println(a);
	}
	
	/**
	 * 	Test "instanceof"
	 * 	1. Used to predicate if an instance belongs to a specified class|interface
	 * 	   -- Premise: the instance's class must extend|implement or be extended|implemented by the specified class|interface 
	 */
	@Test
	public void testInstanceof () throws Exception {
		
		EagerSingleton es = EagerSingleton.getEagerSingleton();
		Run ls = LazySingleton.getLazySingleton();
		
		if (es instanceof EagerSingleton) {
			System.out.println("es is an instance of EagerSingleton.");
		}
		if (es instanceof Run) {
			System.out.println("es is an instance of Run.");
		}
		if (ls instanceof LazySingleton) {
			System.out.println("ls is an instance of EagerSingleton.");
		}
		if (ls instanceof Run) {
			System.out.println("ls is an instance of Run.");
		}
	}
}
