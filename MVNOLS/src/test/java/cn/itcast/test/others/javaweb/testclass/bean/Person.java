package cn.itcast.test.others.javaweb.testclass.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 	This is the JavaBean class for HttpSessionBindingListener & HttpSessionActivationListener
 * 	1. These 2 listeners MUST be implemented by JavaBean
 *     -- NOT recommend since it binds the JavaBean with Java EE components (hence this class will be unworkable in no-JavaEE environment)
 *     -- Serializable is necessary for serialization (for HttpSessionActivationListener)	
 * 	2. No need to configure these 2 listeners  
 * 	3. The rest of listeners refers to "testclass.web.listener" package
 * 
 *  Serialization & Passivation: 
 *  Serialization: store the state of an object in a file or other medium
 *  -- Used for file storage or tranmission: in the environment with distributed cache
 *  -- Deserialization is the reverse process of serialization
 *  Passivation: store the state of an ServletSession (which has implemented Serializable) object in a file or other medium
 *  -- If other objects (e.g, JavaBean objects) want to go with session, they MUST implement Serializble interface as well
 *  -- Activation is the reverse process of passivation
 */
public class Person implements HttpSessionBindingListener, HttpSessionActivationListener, Serializable {

	/**
	 * 	This method is executed when session is passivated (or JavaBean object is serialized)
	 */
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("I was just passivated with session's serialization! :)");
	}

	/**
	 * 	This method is executed when session is activated (or JavaBean object is deserialized)
	 */
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("I was just activated with sesssion's activation! :)");
	}
	
	/**
	 * 	This method is executed when session adds this JavaBean object
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("The session just added me! :)");
	}

	/**
	 * 	This method is executed when session removes this JavaBean object
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("The session just removed me! :(");
	}

}
