package cn.itcast.test.others.algorithm.count_element;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 	Write a program to find out duplicate or repeated characters in a string, 
 * 	and calculate the count of repeatation. 
 * 
 * @author Vince Xu Yuan
 */
public class FindRepeatedCharactersInString {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Show the result	 */
		findRepeatedCharacters("I love china & I love the world!");
	}

	/**
	 * 	This is the method to find the repeated characters
	 * @param string
	 */
	private static void findRepeatedCharacters(String string) {
		
		/*	Instantiate a map for character count	*/
		Map<Character, Integer> characterCount = new HashMap<Character, Integer>();
		
		/*	Convert the string to character array & see if each character is contained in the map: if yes, then its value + 1, if no, then its value is set to 1	*/
		char[] characters = string.toCharArray();
		for (char character : characters) {
			if (characterCount.containsKey(character)) {
				characterCount.put(character, characterCount.get(character) + 1);
			} else {
				characterCount.put(character, 1);
			}
		}
		
		/*	Output the result	*/
		Set<Entry<Character, Integer>> entrySet = characterCount.entrySet();
		for (Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue() > 1 && !Character.isWhitespace(entry.getKey())) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
