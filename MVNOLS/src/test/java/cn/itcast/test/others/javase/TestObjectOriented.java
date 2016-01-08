package cn.itcast.test.others.javase;

/**
 * 	This class is to perfor testing regarding object-oriented
 * 
 * 	Features on object-oriented:
 * 	1. Encapsulation: (e.g. JavaBean)
 * 	   -- Declare the variables of a class as private: "prviate"
 * 	   -- Provide public setter and getter methods to modify and view the variables values: "public"
 * 	2. Inheritance: "extends", "implements", "super" 
 * 	3. Abstraction: "abstract" (cannot co-exist with "static"|"private"|"final") 
 *     -- When to use: know there is a class, but some (or even no) class method is hard to describe in general
 *     -- Details:
 *     	  -- An abstract class can have either abstract method or non-abstract method
 *        -- An abstract class can have no abstract method
 *        -- An abstract class cannot be instantiated, but it has constructors for its child classes
 *     	  -- If a method in a class does not have method body, then it should be described as "abstract".
 *        -- If there is an abstract method, then corresponding class must be abstract.
 *        -- All classes that extends the abstract class must override the abstract methods.
 * 	4. Polymorphism: improves the code scalability
 *     -- Reference|Argument type can be the parent class
 *     -- "instanceof": for the conversion from parent type to child type 
 */
public class TestObjectOriented {

}
