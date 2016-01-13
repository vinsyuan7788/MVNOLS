package cn.itcast.test.others.javase.testclass.annotation.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.itcast.test.others.javase.testclass.annotation.FieldAnnotationForInteger;
import cn.itcast.test.others.javase.testclass.annotation.FieldAnnotationForString;

/**
 * 	This is an utility class to parse the field annotation for DI (Dependency Injection):
 *  1. If there is desired field annoation, then do the injection through reflection
 *  2. Otherwise do nothing
 * 	3. This code explains why anntation DI does not need setter|getter: due to reflection
 */
public class FieldAnnotationParser {

	/**
	 * 	This is the method for DI: value injection for fields
	 * @param instance
	 * @throws Exception
	 */
	public static void inject (Object instance) throws Exception {
		
		/*	Get all the fields from the instance	*/
		Field[] fields = instance.getClass().getDeclaredFields();
		
		/*	Traverse all fields	 */
		for (Field field : fields) {
			
			/*	Set accessible to ALL fields (including the private fields)	 */
			field.setAccessible(true);
			
			/*	If there is desired field annoation, then do the injection through reflection	*/
			if (field.isAnnotationPresent(FieldAnnotationForString.class)) {
				FieldAnnotationForString fieldAnnotationForString = field.getAnnotation(FieldAnnotationForString.class);
				field.set(instance, fieldAnnotationForString.value());

				/**	
				 * 	Below codes are to invoke the corresponding setter to set the value for the fields through reflection
				 * 	Since using reflection can directly manipulate the fields instead of using setter|getter, the codes here is ignorable	
				 */
//				String fieldName = field.getName();
//				String methodName = "set" + fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
//				Method method = instance.getClass().getMethod(methodName, String.class);
//				method.invoke(instance, fieldAnnotationForString.value());
			
			/*	If there is desired field annoation, then do the injection through reflection	*/
			} else if (field.isAnnotationPresent(FieldAnnotationForInteger.class)) {
				FieldAnnotationForInteger fieldAnnotationForInteger = field.getAnnotation(FieldAnnotationForInteger.class);
				field.set(instance, fieldAnnotationForInteger.value());
				
				/**	
				 * 	Below codes are to invoke the corresponding setter to set the value for the fields through reflection
				 * 	Since using reflection can directly manipulate the fields instead of using setter|getter, the codes here is ignorable	
				 */
//				String fieldName = field.getName();
//				String methodName = "set" + fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
//				Method method = instance.getClass().getMethod(methodName, Integer.class);
//				method.invoke(instance, fieldAnnotationForInteger.value());
			}
			
			/*	Otherwise do nothing	*/
		}
	}
}
