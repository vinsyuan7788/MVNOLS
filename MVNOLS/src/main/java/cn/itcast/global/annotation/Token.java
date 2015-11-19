package cn.itcast.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 	This is the token annotation for avoidance of duplicate submission
 *	1. This is the 1st method: 
 *     -- (1) In TokenValidator, parse the annotation & do the duplication subsmission processing in preHandler()
 *     -- (2) Configure the TokenValidator in "SpringMVC.xml"
 *     -- (3) Add annotation on necessary action methods
 *     -- (4) In (JSP) view, add <input type = "hidden" name = "token" value = "${token}">
 *  2. In this case, this method is adopted.
 *     -- other methods refers to "cn.itcast.global.interceptor.TokenValidator.java"
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

	boolean saveToken() default false;
	boolean validateToken() default false;
}
