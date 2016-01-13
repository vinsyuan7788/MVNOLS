package cn.itcast.test.others.javase.testclass.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 	This is an annotation class to get the method name
 * 	1. @Target() specifies what scope the annoation is applied on
 *     -- ElementType.TYPE: applied on class, interface or enum
 *     -- ElementType.FIELD: applied on field
 *     -- ElementType.METHOD: applied on method
 *     -- ElementType.PARAMETER: applied on argument
 * 	2. @Retention() specifies when the annotation is read by JVM
 *     -- RetentionPolicy.RUNTIME: read during runtime
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {
	boolean getName() default false;
}
