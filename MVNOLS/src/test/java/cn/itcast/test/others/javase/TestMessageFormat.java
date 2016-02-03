package cn.itcast.test.others.javase;

import java.text.MessageFormat;

import org.junit.Test;

/**
 * 	This is the class to perform testing regarding message format
 * 	1. MessageFormat class is used to concatenate a message
 * @author Vince Xu Yuan
 */
public class TestMessageFormat {

	/**
	 * 	Test MessageFormat class
	 * @throws Exception
	 */
	@Test
	public void testMessageFormat () throws Exception {
		
		/*
		 * 	Provide the message pattern & arguments for the template
		 * 	1. {0}, {1}, ...: these are place-holders to be filled with arguments
		 * 	2. The number of place-holders MUST be equal to the number of arguments 
		 */
		String pattern = "{0}, {1} and {2} come from the same university";
		Object[] arguments = new Object[]{"Vince", "Violet", "Marry"};
		
		/*	Get the complete message	*/
		String message = MessageFormat.format(pattern, arguments);
		System.out.println("The complete message: " + message);
	}
}
