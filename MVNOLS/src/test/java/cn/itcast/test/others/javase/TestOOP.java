package cn.itcast.test.others.javase;

/**
 * 	This class is to perfor testing regarding OOP (Object-Oriented Programming)
 * 
 * 	Features on OOP:
 * 	1. Encapsulation: privatization for protection (e.g. JavaBean)
 *     -- "private", getter|setter
 * 	   -- Declare the variables of a class as private: "prviate"
 * 	   -- Provide public setter and getter methods to modify and view the variables values: "public"
 *     -- For high cohesion & low coupling
 * 	2. Abstraction: extraction of common features
 *     -- superclass(MAY use "abstract" (cannot co-exist with "static"|"private"|"final") OR NOT)|superinterface
 *     -- When to use: know there is a class, but some (or even no) class method is hard to describe in general
 *     -- Details:
 *     	  -- An abstract class can have either abstract method or non-abstract method
 *        -- An abstract class can have no abstract method
 *        -- An abstract class cannot be instantiated, but it has constructors for its child classes
 *     	  -- If a method in a class does not have method body, then it should be described as "abstract".
 *        -- If there is an abstract method, then corresponding class must be abstract.
 *        -- All classes that extends the abstract class must override the abstract methods.
 * 	3. Inheritance: subclass can gain the ability of superclass & enhance the abliity by themselves
 *     -- "extends", "implements", "super" 
 *     -- Gain the ability of superclass: may override the method
 *     -- Enhance the abliity by themselves: may overload the method
 * 	4. Polymorphism: superclass can refer to the object of any subclass
 *     -- "extends", "implements", "super", "instanceof"
 *        -- Come from inheritance; improves the code scalability
 *        -- "instanceof": for the conversion from parent type to child type 
 *     -- Reference|Argument type -- superclass; Return obejct -- subclass
 *     -- E.g.: in IOP (Interface-Oriented Programming): interface can refer to different implementation subclasses according to different situation, so the codes can be decoupled
 */
public class TestOOP {

}
