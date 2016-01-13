package cn.itcast.test.others.javase.testclass.constructor;

/**
 * 	This is the class to perform copy constructor
 */
public class Student {
		
	/*	Instance variable	*/
	private int id;
	private String name;
	
	/*	Constructor with arguments	 */
	public Student (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 	Copy constructor  
	 */
	public Student (Student student) {
		this.id = student.id;
		this.name = student.name;
	}

	/*	This is an overrided method to output the class to console	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
