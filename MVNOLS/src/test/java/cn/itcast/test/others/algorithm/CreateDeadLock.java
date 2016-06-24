package cn.itcast.test.others.algorithm;

/**
 *  Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. 
 *  Deadlocks can occur in Java when the synchronized keyword causes the executing thread to block while waiting to get the lock, associated with the specified object. 
 *  Since the thread might already hold locks associated with other objects, two threads could each be waiting for the other to release a lock. 
 *  In such case, they will end up waiting forever. 
 *  
 * @author Vince Xu Yuan
 *
 */
public class CreateDeadLock {

	/*	Specify 2 strings as mutex & sharable resources	 */
	String str1 = "Java";
    String str2 = "UNIX";
    
    /*	Specify one thread	 */ 
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
        	
        	/*	Forever wait the mutex str1 and str2 subsequently	*/
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };
    
    /*	Specify another thread	*/
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
        	
        	/*	Forever wait the mutex str2 and str1 subsequently	*/
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };
     
    /**
     * 	This is the main function to execute the deadlock 
     * @param args
     */
    public static void main(String args[]){
    	CreateDeadLock cdl = new CreateDeadLock();
        cdl.trd1.start();
        cdl.trd2.start();
    }
}
