package cn.itcast.test.others.javase;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.enumeration.Enumeration;
import cn.itcast.test.others.javase.testclass.enumeration.Enumeration.Light;

/**
 * 	This is the class to perform testing regarding enumeration
 * 	1. To see what numeration class exactly is, do the decompilation for the enumeration class
 *     -- Enumeration is a sub-class (can be public|private|etc.): hence can have (since sub-class is still a class)
 *        -- Constructors (MUST be private)
 *        -- Static|instance variables
 *        -- Static|instance|abstract methods
 *        -- Details refer to "javase.testclass.enumeration" package
 *     -- Enumeration value is the object of current class created by a privatized constructordescrbied 
 *        -- It is described by "public static final [EnumerationClassName] [EnumerationValue]", refer to "javase.testclass.enumeration" package
 *  2. Enumberation can be used in "switch...case..."
 * 	
 * 	When to use enumeration:
 *  1. When the data value need to be assigned within some range, consider enumeration
 *     -- E.g., week day, traffic light, rainbow color, gender, season, etc.
 *  
 * @author Vince Xu Yuan
 */
public class TestEnumeration {

	/**
	 * 	Test invoke the members of the public enumeration class
	 * 	1. If the enumeration is public, then its public enumration values, variables, methods are all invokable
	 *     -- If the enumeration value has implemented abstract methods, the methods are invokable as well
	 *  2. If the enumeration is private, then the enumertaion & everything regarding the enumertaion are NOT invokable
	 * @throws Exception
	 */
	@Test
	public void testInvokePublicEnumerationClass () throws Exception {
		
		/*	Invoke the public enumeration class	*/
		Light red = Enumeration.Light.RED;
		String string = red.flash();
		String value = red.getValue();
		Light[] enumerationValues = Enumeration.Light.values();
		String defaultColor = Enumeration.Light.DEFAULT_COLOR;
		String light = Enumeration.Light.getLight();
		
		/*	Output the results	*/
		System.out.println("The red light: " + red);
		System.out.println("The red light name:" + red.name());
		System.out.println("The red light value: " + red.value);
		System.out.println("The red light ordinal: " + red.ordinal());
		System.out.println("The string from \"flash()\" of enumeration value: " + string);
		System.out.println("The result of \"getValue()\": " + value);
		System.out.println("All enumeration values: " + Arrays.toString(enumerationValues));
		System.out.println("The default color: " + defaultColor);
		System.out.println("The light obtain by \"getLight()\": " + light);
	}
	
	/**
	 * 	Test traverse the enumeration values of the enumeration class
	 * @throws Exception
	 */
	@Test
	public void testTraverseEnumerationValues () throws Exception {
		
		/*	Traverse the enumeration values	 */
		Enumeration.traverseLight();
	}
	
	/**
	 * 	Test "switch...case..." on enumeration values
	 * @throws Exception
	 */
	@Test
	public void testSwitchCase () throws Exception {
		
		/*	Randomly pick an enumeration value	 */
		Light[] lights = Enumeration.Light.values();
		Light light = lights[new Random().nextInt(lights.length)];
		
		/*	Test "switch...case..." on enumeration values	*/ 
		Enumeration.SwitchCase(light);
	}
}
