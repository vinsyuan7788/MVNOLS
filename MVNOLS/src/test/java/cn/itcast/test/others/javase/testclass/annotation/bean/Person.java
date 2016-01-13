package cn.itcast.test.others.javase.testclass.annotation.bean;

import cn.itcast.test.others.javase.testclass.annotation.ClassAnnotation;
import cn.itcast.test.others.javase.testclass.annotation.FieldAnnotationForInteger;
import cn.itcast.test.others.javase.testclass.annotation.FieldAnnotationForString;
import cn.itcast.test.others.javase.testclass.annotation.MethodAnnotation;

/**
 * 	This is a JavaBean class for DI (dependency injection) through annotation
 */
@ClassAnnotation(singleton=true)
public class Person {
	
	/*	Instance variables	*/
	@FieldAnnotationForString("Tommy")
	private String name;
	@FieldAnnotationForInteger(22)
	private Integer age;
	@FieldAnnotationForString("Male")
	private String gender;
	
	/*	Setters & getters	*/
	@MethodAnnotation(getName=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@MethodAnnotation(getName=true)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@MethodAnnotation(getName=true)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/*	For console output	*/
	@Override
	@MethodAnnotation(getName=true)
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender
				+ "]";
	}
}
