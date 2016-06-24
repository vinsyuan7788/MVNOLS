package cn.itcast.test.others.algorithm.count_element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 	Write a program to read words from a file. 
 * 	Count the repeated or duplicated words. 
 * 	Sort it by maximum repeated or duplicated word count. 
 * 	
 * @author Vince Xu Yuan
 */
public class FindRepeatedWordsFromFile {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Show the counting result	*/
		List<Entry<String, Integer>> list = sortByValue(getWordCount("D:\\example.java"));
		for (Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	/**
	 * 	This is the method to get the word count
	 * @param filePath
	 * @return
	 */
	private static Map<String, Integer> getWordCount (String filePath) {
		
		/*	Initialize buffered reader for reading file & a map for word count	 */
		BufferedReader bufferedReader = null;
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		
		try {
			/*	Read the files line by line	 */
			bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				
				/*	Parse each line that is read from file	*/
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
				while (stringTokenizer.hasMoreTokens()) {
					String temporaryWord = stringTokenizer.nextToken().toLowerCase();
                    if(wordCount.containsKey(temporaryWord)){
                    	wordCount.put(temporaryWord, wordCount.get(temporaryWord)+1);
                    } else {
                    	wordCount.put(temporaryWord, 1);
                    }
				}
			}
		
		/*	Process the exception	*/
		} catch (Exception e) {
			e.printStackTrace();
			
		/*	Finally close the I/O stream	*/
		} finally {
			try {
				if (bufferedReader != null) bufferedReader.close();
			} catch (Exception e) {}
		}
		
		/*	Return the wordCount map	*/
		return wordCount;
	}
	
	/**
	 * 	This is the method to sort the entry (key-value pair) using Collections
	 * @param wordCount
	 * @return
	 */
	private static List<Entry<String, Integer>> sortByValue (Map<String, Integer> wordCount) {
		
		/*	Convert the entry set in the map to list for sorting	 */
		Set<Entry<String, Integer>> entrySet = wordCount.entrySet();
		List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>(entrySet);

		/*	Sort the list	*/
		Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				 return (o2.getValue()).compareTo(o1.getValue()); 
			}
		});
		
		/*	Return the list	 */
		return entryList;
	}
}
