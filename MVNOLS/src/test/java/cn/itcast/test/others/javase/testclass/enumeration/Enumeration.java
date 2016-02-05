package cn.itcast.test.others.javase.testclass.enumeration;

import java.util.Random;

/**
 * 	This is a class to use enumeration class
 * @author Vince Xu Yuan
 */
public class Enumeration {
	
	/**
	 * 	This is the static method to traverse the enumeration value
	 * @throws Exception
	 */
	public static void traverseLight () throws Exception {
		
		/*	Get all enumeration value from the enumeration class	*/
		Light[] lights = Light.values();
		
		/*	Traverse all enumeration value	*/
		for (Light light : lights) {
			System.out.println("Current light: " + light);
			System.out.println("Name of current light: " + light.name());
			System.out.println("Value of current light: " + light.value);
			System.out.println("Ordinal of current light: " + light.ordinal());
			System.out.println("Return result of the methods of current light: " + light.flash());
		}
	}
	
	/**
	 * 	This is the static method to apply "swtich...case..." on enumeration values
	 * @throws Exception
	 */
	public static void SwitchCase (Light light) throws Exception {
		
		/*	
		 * 	Apply "switch...case..." on enumeration values
		 * 	1. Here the case cannot specify "Light.RED", etc.: becuase switch(light) has specified that the enumeration values MUST come from Light enumeration class
		 *     -- In case that put the object of an enumeration class (e.g., Light) on switch but put other enumeration values (e.g., Gender.MAN, etc.) on case, which will bring compliation confusion: complier will not know what enumration (Light or Gender) should be enumerated	
		 */
		switch (light) {
			case RED:
				System.out.println("The light is red.");
				break;
			case YELLOW:
				System.out.println("The light is yellow.");
				break;
			case GREEN:
				System.out.println("The light is green.");
				break;
			default:
				break;
		}
	}
	
	/**
	 * 	This is an enumeration class Light
	 * 	1. Enumberation is sub-class (can be public|private|etc.): hence can have (since sub-class is still a class) 
	 *     -- Constructors (MUST be private)
	 *     -- Static|instance variables
	 *     -- Static|instance|abstract methods
	 *  2. Enumeration value is the object of current class created by a privatized constructor
	 *  3. Enumberation can be used in "switch...case..."
	 * @author Vince Xu Yuan
	 */
	public enum Light {

		/*	
		 * 	Declare enumeration values, which are equivalent to:
		 * 	-- public static final Gender MAN = new Gender();
		 *  -- public static final Gender WOMAN = new Gender();	
		 *  -- private Gender () {};
		 *     -- Namely use a privatized no-argument constructor to instantiate 2 objects of current class
		 *     -- If the no-argument constructor is replaced by with-argument constructor, should assign an argument to enumeration value
		 *     -- If there is an abstract method, then enumeration value MUST override it
		 *     -- Enumeration MUST lie at the top inside the enumeration class
		 */
		RED("red") {
			@Override
			public String flash() throws Exception {
				return "Red light is flashing...";
			}
		}, 
		
		YELLOW("yellow") {
			@Override
			public String flash() throws Exception {
				return "Yellow light is flashing...";
			}
		},
		
		GREEN("green") {
			@Override
			public String flash() throws Exception {
				return "Green light is flashing...";
			}
		};
		
		/*	Declare static|instance variables	*/
		private static final String[] light = {"RED", "YELLOW", "GREEN"};
		public static final String DEFAULT_COLOR = "GREEN";
		public String value;
		
		/*	Declare constructor: MUST be private	*/
		private Light(String value) {
			this.value = value;
		}
		
		/*	Declare static|instance|abstract methods	*/
		/**
		 * 	This is a static method to randomly get a light
		 * @return
		 * @throws Exception
		 */
		public static String getLight () throws Exception {
			return light[new Random().nextInt(light.length)]; 
		}

		/**
		 * 	This is a dynamic method to get the value of instance variable
		 * @return
		 */
		public String getValue() {
			return value;
		}
		
		/**
		 * 	This is an abstract method to be overridden (by enumeration value)
		 * @throws Exception
		 */
		public abstract String flash () throws Exception;
	}
}

