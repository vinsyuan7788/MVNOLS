package cn.itcast.test.others.javase;

import org.junit.Test;

/**
 * 	This class is to perform testing regarding thread
 * 
 * 	The 1st way for thread creation:
 * 	1. Customize a class that extends "Thread" class
 * 	2. Override the "run()" method
 * 
 * `The 2nd way for thread creation:
 * 	1. Customize a class that implements "Runnable" interface
 *  2. Override the "run()" method
 *  3. new a thread with the object of the custom class that implements "Runnable"
 *     -- e.g.: Thread xxx = new Thread(new <? implements Runnable>);
 * 
 * 	One Java application has at least 2 threads
 * 	1. One for main function: main thread that exeuctes all the thread obejcts
 * 	2. One for garbage collection
 * 	3. Other custom thread objects
 * 
 * 	Thread-safety problem:
 * 	1. There are at least 2 threads
 * 	2. There is one sharable resource
 * 
 * 	Dead lock:
 * 	1. There are at least 2 threads
 * 	2. There are more than one sharable resource
 */
public class TestThread {

	/**
	 * 	Test custom threads
	 * 	@throws Exception
	 */
	@Test
	public void testCustomThreads () throws Exception {
		
		/*	This is the job done by custom thread 1  */
		CustomThread customThread1 = new CustomThread();		// Each new create a new thread object
		System.out.println("Custom thread 1's default name: " + customThread1.getName());
		customThread1.setName("custom thread 1");
		System.out.println("Custom thread 1's new name: " + customThread1.getName());
		customThread1.start();									// Start the execution of the custom thread
		
		/*	This is the job done by custom thread 2	 */
		CustomThread customThread2 = new CustomThread();		// Each new create a new thread object
		System.out.println("Custom thread 2's default name: " + customThread2.getName());
		customThread2.setName("custom thread 2");
		System.out.println("Custom thread 2's new name: " + customThread2.getName());
		customThread2.start();									// Start the execution of the custom thread
		
		/*	This is the job done by main thread	 */
		Thread mainThread = Thread.currentThread();
		System.out.println("Main thread's default name: " + mainThread.getName());
		mainThread.setName("Main thread");
		System.out.println("Main thread's new name: " + mainThread.getName());
		for (int i = 0; i < 100; i++) {
			System.out.println(mainThread.getName() + ": " + i);
		}
	}
	
	/**
	 * 	Test thread safety
	 * 	1. Use synchronized block or function to resolve thread safety problem
	 * 	2. Recommend synchronized block: since it can specify the mutex & what blocks to be synchronized (more fine-grained)
	 *	@throws Exception
	 */
	@Test
	public void testThreadSafety () throws Exception {
		
		/*	Create 3 thread objects for ticket sales	 */
		SellTicket sellTicket1 = new SellTicket("Ticket Window 1");
		SellTicket sellTicket2 = new SellTicket("Ticket Window 2");
		SellTicket sellTicket3 = new SellTicket("Ticket Window 3");
		
		/*	Start these 3 threads	*/
		sellTicket1.start();
		sellTicket2.start();
		sellTicket3.start();
		
		/*	Create 2 thread objects for deposit withdraw	*/
		Thread wife = new Thread(new BankAccount(), "wife");
		Thread husband = new Thread(new BankAccount(), "husband");
		
		/*	Start these 2 threads	*/
		wife.start();
		husband.start();
	}
	
	/**
	 * 	Test join
	 * 	1. The thread A executing "join()"|The thread B invoking "join()" will have A wait until the new incoming thread B is completed execution
	 * 	@throws Exception
	 */
	@Test
	public void testJoin () throws Exception {
		new Mom("Mom").start();
	}
}

/*	Customize a class that extends Thread to override "run()" method	*/
class CustomThread extends Thread {

	/*	Specify what the custom thread does: just specify and not invoke it	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}
}

/*	Customize a class that extends Thread to override "run()" method for ticket sales	*/
class SellTicket extends Thread {

	/*	This is the sharable resource for all possible threads	*/
	static int ticketNum = 50;
	
	/*	Override one of the constructors in Thread	*/
	public SellTicket(String name) {
		super(name);
	}
	
	/*	Specify what the custom thread does: just specify and not invoke it	 */
	@Override
	public void run() {
		
		while (true) {
			/**	
			 * 	Sychronize block to resolve thread safety: 
			 * 	1. Mutex argument MUST be unique & sharable for all threads	 
			 * 	2. one string is the simplest mutex: e.g. "mutex"|"abc"|"hello", etc.
			 *     -- Due to string immutability
			 */
			synchronized ("mutex") {
				if (ticketNum > 0) {
					System.out.println(Thread.currentThread().getName() + " sold the No." + ticketNum + " ticket");
					ticketNum--;
				} else {
					System.out.println("The tickets sold up");
					break;
				}
			}	
		}
	}
}

/*	Customize a class that implements Runnable to override "run()" method for deposit withdraw	*/
class BankAccount implements Runnable {
	
	/*	This is the sharable resource for all possible threads	*/
	static int currentDeposit = 5000;
	
	/*	Specify what the custom thread does: just specify and not invoke it	 */
	@Override
	public void run() {
		try {
			getMoney();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**	
	 * 	Synchronized method to resolve thread safety
	 * 	1. For static method: the mutex is "BankAccount.class" (unique & sharable) by default & unchangably
	 * 	2. For instance method: the mutex is the object that "this" keyword refers to (not unique & unsharable) by default & unchangably
	 */
	private static synchronized void getMoney () throws Exception {
		
		while (true) {
			if (currentDeposit > 0) {
				System.out.println(Thread.currentThread().getName() + " withdrawn 1000 bucks");
				currentDeposit -= 1000;
			} else {
				System.out.println("Current deposit has become 0");
				break;
			}
		}
	}
}

/*	Customize a class that extends Thread to override "run()" method for ticket sales	*/
class Mom extends Thread {

	/*	Override one of the constructors in Thread	*/
	public Mom(String name) {
		super(name);
	}
	
	/*	Specify what the custom thread does: just specify and not invoke it	 */
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " is cooking...");
		System.out.println(Thread.currentThread().getName() + " needs salt");
		
		/*	Mom asks son to get the salt: Mom executes join()|Son invokes join() to have mom wait until the new incoming thread (son) is completed execution	 */
		Son son = new Son("Son");
		son.start();
		try {
			son.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " gets the salt");
		System.out.println(Thread.currentThread().getName() + " finishes cooking");
		System.out.println("The whole family has dinner together");
	}
}

/*	Customize a class that extends Thread to override "run()" method for ticket sales	*/
class Son extends Thread {

	/*	Override one of the constructors in Thread	*/
	public Son(String name) {
		super(name);
	}
	
	/*	Specify what the custom thread does: just specify and not invoke it	 */
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " goes downstairs...");
		System.out.println(Thread.currentThread().getName() + " arrives in market...");
		System.out.println(Thread.currentThread().getName() + " buys the salt");
		System.out.println(Thread.currentThread().getName() + " returns home & gives the salt");
	}
}
