package cn.itcast.test.others.javaweb.testclass.bean;

/**
 * 	This is the JavaBean class for testing of conversion from Java object to JSON object
 * 	1. Refer to "TestJavaToJSON.java"
 * @author Vince Xu Yuan
 */
public class Student {

	/*	Here are the instance variables	 */
	private Integer studentId;
	private String studentName;
	private Gender gender;
	
	/*	Enumeration class	*/
	public enum Gender {
		MALE, FEMALE
	}

	/*	Here are the getters & setters	*/
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/*	Here is "toString()" method	 */
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", gender=" + gender + "]";
	}
}
