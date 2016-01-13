package cn.itcast.test.others.javase.testclass.template;

/**
 * 	This is the class to extend the template class, so that user can override the custom method
 */
public class CustomClass extends Template {

	/**
	 * 	Here overrides the method that can be customized	
	 */
	@Override
	public void customMethod () throws Exception {
		for (int i = 0; i < 200; i++) {
			System.out.println("This is the custom method: " + i);
		};
	}

}
