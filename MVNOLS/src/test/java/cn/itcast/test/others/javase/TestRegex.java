package cn.itcast.test.others.javase;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.regex.Captcha;

/**
 * 	This is the class to perform testing regarding regex (regular expression)
 * 	1. Regex rules
 * 	2. Regex for matching & splitting, etc.
 * 	3. Pattern class & Matcher class
 * @author Vince Xu Yuan
 */
public class TestRegex {

	/**
	 * 	This is the testing regarding pre-defined characters of regex
	 * @throws Exception
	 */
	@Test
	public void testPredefinedCharacters () throws Exception {
		
		/*	A character for pre-defined character predication	*/
		String character = "&";
		
		/*
		 * 	Pre-defined characters:
		 * 	.: any character
		 * 	\d: any number [0-9]
		 * 	\D: any non-number [^0-9]
		 * 	\s: blank character [ \t\n\x0B\f\r]
		 * 	\S: non-blank character [^\s]
		 * 	\w: word character [a-zA-Z_0-9]
		 * 	\W: non-word character [^\w]
		 * 
		 * 	Note: 
		 * 	1. To express slash in implementation, need to add another slash before slash, so it is, e.g., "\\d" (slash slash d) to make "\d" readable by Java compiler
		 * 	2. For \t, \n, etc., need to add a slash before slash as well for the same reason in implementation
		 */
		System.out.println("Any character: " + (character.matches(".")));
		System.out.println("Number character: " + (character.matches("\\d")));
		System.out.println("Non-number character: " + (character.matches("\\D")));
		System.out.println("Blank character: " + (character.matches("\\s")));
		System.out.println("Non-blank character: " + (character.matches("\\S")));
		System.out.println("Word character: " + (character.matches("\\w")));
		System.out.println("Non-word character: " + (character.matches("\\W")));
	}
	
	/**
	 * 	This is the testing regarding character class of regex
	 * @throws Exception
	 */
	@Test
	public void testCharacterClass () throws Exception {
		
		/*	A string for character class predication	*/
		String character = "2";
		
		/*
		 * 	Character class:
		 * 	[abc123]: only appear a, b, c, 1, 2 or 3
		 *  [^abc123]: only appears any character except a, b, c, 1, 2 and 3
		 *  [a-zA-Z0-9] or [a-z[A-Z[0-9]]]: on appears between a and z or between A and Z or between 0 and 9
		 *  [a-z&&[def]]: only appears in the intersection of a-z and def (namely [def])
		 */
		System.out.println("A, B, C, 1, 2 or 3: " + character.matches("[abc123]"));
		System.out.println("Any character except A, B, C, 1, 2 and 3: " + character.matches("[^abc123]"));
		System.out.println("Any character in a-z or A-Z or 0-9: " + character.matches("[a-zA-Z0-9]"));
//		System.out.println("Any character in a-z or A-Z or 0-9: " + character.matches("[a-z[A-Z[0-9]]]"));
		System.out.println("Any character in the intersection of a-z and def: " + character.matches("[def]"));
//		System.out.println("Any character in the intersection of a-z and def: " + character.matches("[a-z&&[def]]"));
	}

	/**
	 * 	This is the testing regarding greediness of regex
	 * @throws Exception
	 */
	@Test
	public void testGreediness () throws Exception {
		
		/*	A string for greediness predication	 */
		String string = "12AB6789";
		
		/*
		 * 	Greediness:
		 * 	X?: X appears none or once 
		 * 	X*: X appears none or multiple times
		 * 	X+: X appears once or multiple times
		 * 	X{n}: X appears n times
		 * 	X{n,}: X appears at least n times
		 * 	X{n,m}: X appears at least n times but no more than m times
		 */
		System.out.println("Number appears none or once: " + (string.matches("\\d?")));
		System.out.println("Number appears none or multiple times: " + (string.matches("[0-9]*")));
		System.out.println("Number appears once or multiple times: " + (string.matches("[0-9]+")));
		System.out.println("Number appears 3 times: " + (string.matches("\\d{3}")));
		System.out.println("Number appears at least 3 times: " + (string.matches("[0-9]{3,}")));
		System.out.println("Number appears at least 3 times & no more than 9 times: " + (string.matches("[0-9]{3,9}")));
		System.out.println("Word appears none or once: " + (string.matches("\\w?")));
		System.out.println("Word appears none or multiple times: " + (string.matches("[a-zA-Z_0-9]*")));
		System.out.println("Word appears once or multiple times: " + (string.matches("[a-zA-Z_0-9]+")));
		System.out.println("Word appears 3 times: " + (string.matches("\\w{3}")));
		System.out.println("Word appears at least 3 times: " + (string.matches("[a-zA-Z_0-9]{3,}")));
		System.out.println("Word appears at least 3 times & no more than 9 times: " + (string.matches("[a-zA-Z_0-9]{3,9}")));
		System.out.println("Non-blank character appears none or once: " + (string.matches("\\S?")));
		System.out.println("Non-blank appears none or multiple times: " + (string.matches("[^ \\t\\n\\x0B\\f\\r]*")));
		System.out.println("Non-blank appears once or multiple times: " + (string.matches("[^ \\t\\n\\x0B\\f\\r]+")));
		System.out.println("Non-blank appears 3 times: " + (string.matches("\\S{3}")));
		System.out.println("Non-blank appears at least 3 times: " + (string.matches("[^ \\t\\n\\x0B\\f\\r]{3,}")));
		System.out.println("Non-blank appears at least 3 times & no more than 9 times: " + (string.matches("[^ \\t\\n\\x0B\\f\\r]{3,9}")));
	}
	
	/**
	 * 	Test the regex matching 
	 * 	1. Here do not take email as example since in differnt industrial field, the rules vary a lot
	 *     -- In e-comercce, the email is no need to verify in the back-end because even the format is right, the email may be invalid for activation
	 *     -- If necessary can verify the email with JS or JQuery, etc. (which is the stategy adopted in this MVNOLS project) 
	 * @throws Exception
	 */
	@Test
	public void testRegexMatch () throws Exception {
		
		/*	Get a captcha	*/
		String captcha = Captcha.getCaptcha();
		
		/*	Specify a regex for predication	 */
		String regex = "[1-9]\\d{4,10}";
		
		/*	
		 * 	Output the captcha & predicate if it is legit. The captcha is legit only if
		 * 	1. Start from 1 to 9
		 * 	2. Length must be between 5 and 11
		 * 	3. Must consist of ONLY number
		 */
		System.out.println("Captcha: " + captcha);
		System.out.println(captcha.matches(regex)?"Legit captcha":"Illegit captcha");
	}
	
	/**
	 * 	Test the regex split
	 * @throws Exception
	 */
	@Test
	public void testRegexSplit () throws Exception {
		
		/*	Strings to be split by regex	*/
		String string1 = "We   are  the       champion";
		String string2 = "Wesupsupsupsupsupsuparejugjugjugthemidmidchampion";
		
		/*	Specify the regex for splitting strings	 */
		String regex1 = " +";
		String regex2 = "(.+)\\1+";
		
		/*	Split the string by blank & repeated words	*/
		String[] stringArray1 = string1.split(regex1);
		System.out.println("The elements in stringArray1: " + Arrays.toString(stringArray1));
		String[] stringArray2 = string2.split(regex2);
		System.out.println("The elements in stringArray2: " + Arrays.toString(stringArray2));
	}
	
	/**
	 * 	Test the pattern matching
	 * @throws Exception
	 */
	@Test
	public void testPatterMatch () throws Exception {
		
		/*	Get a captcha	*/
		String captcha = Captcha.getCaptcha();
		
		/*	Specify a Pattern object for matching	 */
		Pattern pattern = Pattern.compile("[1-9]\\d{4,10}");
		
		/*	
		 * 	Output the captcha & predicate if it is legit. The captcha is legit only if
		 * 	1. Start from 1 to 9
		 * 	2. Length must be between 5 and 11
		 * 	3. Must consist of ONLY number
		 */
		System.out.println("Captcha: " + captcha);
		System.out.println(pattern.matcher(captcha).matches()?"Legit captcha":"Illegit captcha");
	}
	
	/**
	 * 	Test the pattern split
	 * @throws Exception
	 */
	@Test
	public void testPatternSplit () throws Exception {
		
		/*	Strings to be split by regex	*/
		String string1 = "We   are  the       champion";
		String string2 = "Wesupsupsupsupsupsuparejugjugjugthemidmidchampion";
		
		/*	Specify the pattern objects for splitting strings	 */
		Pattern pattern1 = Pattern.compile(" +");
		Pattern pattern2 = Pattern.compile("(.+)\\1+");
		
		/*	Split the string by blank & repeated words	*/
		String[] stringArray1 = pattern1.split(string1);
		System.out.println("The elements in stringArray1: " + Arrays.toString(stringArray1));
		String[] stringArray2 = pattern2.split(string2);
		System.out.println("The elements in stringArray2: " + Arrays.toString(stringArray2));
	}
}
