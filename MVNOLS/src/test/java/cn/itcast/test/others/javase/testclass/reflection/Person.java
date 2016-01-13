package cn.itcast.test.others.javase.testclass.reflection;

/**
 *  This is a JavaBean class for reflection operation
 *  1. For a JavaBean, getter|setter is the most important
 */
public class Person {

		/*	Here are the instance variables	 */
		private String name;
		private String id;
		
		/*	Here is the constructors  */
		public Person() {}
		
		public Person(String name, String id) {
			this.name = name;
			this.id = id;
		}
		
		/**	
		 * 	Here are the instance methods
		 * 	1. They are typically getters|setters	
		 */
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
}
