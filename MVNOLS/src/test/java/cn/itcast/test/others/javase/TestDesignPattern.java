package cn.itcast.test.others.javase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.decorator.LineNumberDecorator;
import cn.itcast.test.others.javase.testclass.decorator.SemicolonDecorator;
import cn.itcast.test.others.javase.testclass.factory.configuration.ConfigurationFactory;
import cn.itcast.test.others.javase.testclass.factory.singleton.LazySingletonFactory;
import cn.itcast.test.others.javase.testclass.factory.subject.Person;
import cn.itcast.test.others.javase.testclass.inheritance.LineNumberBufferedReader;
import cn.itcast.test.others.javase.testclass.inheritance.LineNumberSemicolonBufferedReader;
import cn.itcast.test.others.javase.testclass.inheritance.SemicolonBufferedReader;
import cn.itcast.test.others.javase.testclass.observer.Employee;
import cn.itcast.test.others.javase.testclass.observer.Student;
import cn.itcast.test.others.javase.testclass.observer.subject.WeatherStation;
import cn.itcast.test.others.javase.testclass.proxy.dynamics.DynamicProxy;
import cn.itcast.test.others.javase.testclass.proxy.dynamics.advice.AfterAdvice;
import cn.itcast.test.others.javase.testclass.proxy.dynamics.advice.BeforeAdvice;
import cn.itcast.test.others.javase.testclass.proxy.statics.WaiterProxy;
import cn.itcast.test.others.javase.testclass.proxy.target.FemaleWaiter;
import cn.itcast.test.others.javase.testclass.proxy.target.MaleWaiter;
import cn.itcast.test.others.javase.testclass.proxy.target.interfaces.Waiter;
import cn.itcast.test.others.javase.testclass.singleton.EagerSingleton;
import cn.itcast.test.others.javase.testclass.singleton.LazySingleton;
import cn.itcast.test.others.javase.testclass.template.CustomClass;

/**
 * 	This class is to perform testing regarding design pattern
 * 	1. Creational pattern: to create instance in a manner suitable to some situation
 * 	   -- Singleton: to make sure there is ONLY one object|instance in the (heap) memory
 *     -- Factory: to provide class object|instance
 * 	2. Behavioral pattern: to realize the commmunication pattern between objects
 *     -- Template: to provide template & custom codes to another object to solve one type of problem
 *     -- Observer: to observe an event that raised by another object (& do corresponding processing if the event is raised)
 * 	3. Structural pattern: to realize the relationship between entities
 *     -- Inheritance: to enhance (method(s) of) a specific class with hard-coded content by another class
 *     -- Decorator: to enhance (method(s) of) decorated|decorator classes with hard-coded content by another class
 * 	   -- Proxy: to enhance (method(s) of) any class with custom-coded content by another class
 *  4. Concurrency pattern: to deal with multi-threaded programming paradigm
 *     -- Double-checked locking: to reduce the overhead of acquiring a lock (refer to "javase.testclass.singleton.LazySingleton.java")
 *     -- Thread-local: to have (global) resource local to each thread
 *  5. Architectural pattern: to solve soft architecture
 *     -- IOC: to pass the object creation to a external component where objects can be injected using configuration
 *     -- MVC: to improve the program robustness, scalability & flexibility
 * 
 * 	Pattern: a fixed procedure to resolve one type of problem
 * 	1. Hence no need to understand: if there is a demand, then use this pattern  
 * 
 * 	For the some pattern involving 2 classes|entities: observer, decorator, static proxy, etc.
 * 	1. Active (enhancing|decorator|proxy, etc.) class extends passive (enhanced|decorated|target, etc.) class
 *     -- so can make use of inheritance
 *  2. Active class maintains a reference for (the parent of) passive class
 *     -- so can make use of polymorphism
 *  3. Introduce a class|interface as the parent of the passive classes
 *     -- this parent class can be abstract class
 */
public class TestDesignPattern {

	/*---------------------------- 	Test Creational Pattern	 ---------------------------------*/
	/**
	 * 	Test singleton
	 * 	1. Eager singleton
	 * 	2. Lazy singleton: usually with double-checked locking pattern
	 * 	@throws Exception
	 * 
	 * 	In singleton, thread-sharable instance (non-static) variables in classes will raise thread-safety problem
	 *  -- To resolve this: 
	 *     -- Avoid using thread-sharable instance (non-static) variables
	 *     -- Using ThreadLocal<> class
	 *     -- Using multiton for necessary classes: NOT recommended
	 *     
	 * 	Singleton pattern is typically applied in the design of Java frameworks (e.g., Spring, etc.)
	 */
	@Test
	public void testSingleton () throws Exception {
			
		/*	Get 2 EagerSingleton objects & See if they are the same objects (same reference)	 */
		EagerSingleton es1 = EagerSingleton.getEagerSingleton();
		EagerSingleton es2 = EagerSingleton.getEagerSingleton();
		System.out.println("Is it true that es1 and es2 have the same reference? " + (es1 == es2));
		
		/*	Invoke the method	*/
		es1.run();
		es2.run();
		
		/*	Get 2 LazySingleton objects & See if they are the same objects (same reference in heap memory)	 */
		LazySingleton ls1 = LazySingleton.getLazySingleton();
		LazySingleton ls2 = LazySingleton.getLazySingleton();
		System.out.println("Is it true that ls1 and ls2 have the same reference? " + (ls1 == ls2));	// True: only one instance in heap memory
		
		/*	Invoke the method	*/
		ls1.walk();
		ls2.talk();
	}

	/**
	 * 	Test factory: (with reflection)
	 * 	1. factory pattern usually adopts reflection
	 * 	@throws Exception
	 * 
	 * 	Factory pattern is typically applied in the design of Java frameworks (e.g., Spring, etc.)
	 *  -- Create instance from configuration file
	 */
	@Test
	public void testFactory () throws Exception {
		
		/*	Get an object from configuation object facotry & output it	*/
		Person person = (Person) ConfigurationFactory.getInstance();
		System.out.println("Person instance from configuration factory: " + person);
		
		/*	Get 2 objects from lazy singleton factory & See if they are the same objects (same reference in heap memory)	*/
		Person person1 = (Person) LazySingletonFactory.getInstance(Person.class);
		Person person2 = (Person) LazySingletonFactory.getInstance(Person.class);
		System.out.println("Is it true that Person instance from lazy singleton factory is singleton? " + (person1 == person2));
	}
	
	/*---------------------------- 	Test Behavioral Pattern	 ---------------------------------*/
	/**
	 * 	Test template
	 * 	1. Need a new class to extend the template class to override the custom method(s)
	 * 	2. New an instance of the new class to invoke the template method(s)
	 * 	@throws Exception
	 * 
	 * 	Template pattern is typically applied to improve the code reusability
	 */
	@Test
	public void testTemplate () throws Exception {
		
		/*	Create an instance of the custom class that extends the template class	 */
		CustomClass customClass = new CustomClass();
		
		/*	Directly invoke the template method	 */
		customClass.getElapseTime();
		
	}
	
	/**
	 * 	Test observer: IOP & polymorphism
	 * 	1. Share the similar mechanism as Listener
	 * 	@throws Exception
	 * 
	 * 	Observer pattern is typically applied in Android design, etc.
	 */
	@Test
	public void testObserver () throws Exception {
		
		/*	New a subject class object & add|register the observer objects that require notification	 */
		WeatherStation weatherStation = new WeatherStation();
		weatherStation.addObserver(new Employee("Tommy"));
		weatherStation.addObserver(new Employee("Vince"));
		weatherStation.addObserver(new Student("Creatrol"));
		weatherStation.addObserver(new Student("Michael"));
		
		/*	Execute the method that contains notification method	*/
		weatherStation.startWorking();
	}
	
	/*---------------------------- 	Test Structural Pattern	 ---------------------------------*/
	/**
	 * 	Test inheritance: for class (method) enhancement
     *  1. Extends the class & override the method that needs to be enhanced
     *  2. Simplicity but unflexibility (may bring a large inheritance hierarchy if there is a lot of demands)
     *     -- The enhancement content is hard-coded
	 *     -- The enhanced classes are invariable|unchangeable
	 * 	@throws Exception
	 */
	@Test
	public void testInheritance () throws Exception {
		
		/*	Get the file from the disk	*/
		File file = new File("D:\\MouseAdapters.java");
		
		/*	Set up a buffer reader to read a file	*/
		LineNumberBufferedReader lnbr = new LineNumberBufferedReader(new FileReader(file));
		SemicolonBufferedReader sbr = new SemicolonBufferedReader(new FileReader(file));
		LineNumberSemicolonBufferedReader lnsbr = new LineNumberSemicolonBufferedReader(new FileReader(file));
		
		/*	Output the reading outcome to console	*/
		String line = null;
		System.out.println("This is the output from LineNumberBufferedReader:");
		while ((line = lnbr.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
		System.out.println("This is the output from SemicolonBufferedReader:");
		line = null;
		while ((line = sbr.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
		System.out.println("This is the output from LineNumberSemicolonBufferedReader:");
		line = null;
		while ((line = lnsbr.readLine()) != null) {
			System.out.println(line);
		}
	}
	
	/**
	 * 	Test decorator: for class (method) enhancement
	 * 	1. The enhancement content is hard-coded
	 *  2. The decorated class is varialbe|changeable:
	 *     -- Can be the decorated class
	 *     -- Can be one of the decorator classes
	 *  3. Decorator class extends the docrated class: mutual decoration & inheritance (*****)
	 * 	@throws Exception
	 * 
	 * 	Decorator pattern is typically applied in some java.io packages (e.g., BufferedReader, etc.)
	 */
	@Test
	public void testDecorator () throws Exception {
		
		/*	Get the file from the disk	*/
		File file = new File("D:\\MouseAdapters.java");
		
		/*	Set up a buffer reader to read a file	*/
		LineNumberDecorator lnd = new LineNumberDecorator(new BufferedReader(new FileReader(file)));
		SemicolonDecorator sd = new SemicolonDecorator(new BufferedReader(new FileReader(file)));
		LineNumberDecorator lnsd = new LineNumberDecorator(new SemicolonDecorator(new BufferedReader(new FileReader(file))));
		
		/*	Output the reading outcome to console	*/
		String line = null;
		System.out.println("This is the output from LineNumberDecorator:");
		while ((line = lnd.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
		System.out.println("This is the output from SemicolonDecorator:");
		line = null;
		while ((line = sd.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
		System.out.println("This is the output from LineNumberDecorator & SemicolonDecorator:");
		line = null;
		while ((line = lnsd.readLine()) != null) {
			System.out.println(line);
		}
	}
	
	/**
	 * 	Test proxy:
	 *  1. Dynamic proxy (*****): get the proxy object during runtime, namely the running of the program (e.g., use "Proxy.newProxyInstance()" to get the object)
	 *     -- Using "InvocationHandler" & "Proxy.newProxyInstance()"
	 *     -- The enhancement content is custom-coded
	 *     -- The enhanced class is varialbe|changeable: can be any class that implements any interface
	 * 	2. Static proxy: get the proxy object before runtime, namely the running of the program (e.g., use "new" to get the object)
	 *     -- Without using "InvocationHandler" & "Proxy.newProxyInstance()"
	 *     -- The enhancement content is hard-coded & ONLY serves for one interface, not good for interface scalability
	 *     -- The enhanced class is varialbe|changeable: can be any class that implements some specific interface
	 *  @throws Exception
	 *  
	 *  Proxy (typically dynamic proxy) pattern is widely applied in the design of Java frameworks (e.g., Spring, MyBatis, Hibernate, etc.)
	 */
	@Test
	public void testProxy () throws Exception {
		
		/*	Test dynamic proxy: enhance one target with customizing both before advice and after advice	 */
		DynamicProxy dynamicProxy = new DynamicProxy(new MaleWaiter());
		dynamicProxy.setBeforeAdvice(new BeforeAdvice() {
			
			@Override
			public void beforeAdvice() throws Exception {
				System.out.println("Welcome!");
			}
		});
		dynamicProxy.setAfterAdvice(new AfterAdvice() {
			
			@Override
			public void afterAdvice() throws Exception {
				System.out.println("Have a good day!");
				System.out.println();
			}
		});
		Waiter waiterProxy = (Waiter) dynamicProxy.createProxy();
		waiterProxy.serve();
		
		/*	Test dynamic proxy: enhance another target with customizing only after advice	*/
		dynamicProxy = new DynamicProxy(new FemaleWaiter());
		dynamicProxy.setAfterAdvice(new AfterAdvice() {
			
			@Override
			public void afterAdvice() throws Exception {
				System.out.println("Welcome for your coming again!");
				System.out.println();
			}
		});
		waiterProxy = (Waiter) dynamicProxy.createProxy();
		waiterProxy.checkOut();
		
		/*	Test static proxy	*/
		WaiterProxy proxy = new WaiterProxy(new FemaleWaiter());
		proxy.serve();
	}
}
