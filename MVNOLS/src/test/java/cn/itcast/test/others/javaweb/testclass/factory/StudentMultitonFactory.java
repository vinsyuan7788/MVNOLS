package cn.itcast.test.others.javaweb.testclass.factory;

import java.util.Random;

import cn.itcast.test.others.javaweb.testclass.json.bean.Student;
import cn.itcast.test.others.javaweb.testclass.json.bean.Student.Gender;

/**
 * 	This is a multiton factory to randomly generate a student instance
 * @author Vince Xu Yuan
 */
public class StudentMultitonFactory {
	
	/*	This is the pool for student name	*/
	private static String[] studentNames = {"Tommy", "Kelly", "Jhin", "Trundle", "Teemo"};
	/*	This is the pool for gender	 */
	private static Gender[] studentGenders = Student.Gender.values();
	
	/**
	 * 	This is a static method to randomly generate a student instance
	 * @return
	 * @throws Exception
	 */
	public static Student getInstance () throws Exception {
		
		/*	Instantiate a Student object	*/
		Student student = new Student();
		student.setStudentId(new Random().nextInt(Integer.MAX_VALUE));
		student.setStudentName(studentNames[new Random().nextInt(studentNames.length)]);
		student.setGender(studentGenders[new Random().nextInt(studentGenders.length)]);
		
		/*	Return the student object	*/
		return student;
	}
}
