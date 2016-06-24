package cn.itcast.test.others.algorithm.map;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 	You want to compute the phone bills by yourself. Below are the rules for fee calculation
 * 	1. If the phone duration is shorter than 5 mins, then its fee is 3 * the seconds that you spent
 * 	2. If the phone duration is equal or longer than 5 mins, then its fee is 150 * the minutes you spent
 *     -- E.g., if the phone call lasts for 5 mins, then its fee is 150 * 5 = 750
 *     -- But if the phone call lasts for 5 mins and 1 second, then fee is 150 * 6 = 900
 *  3. For the phone number that has the longest total calling duration, the fee can be waived (which means you don't
 *     need to pay all the fees associated with that phone number). If there are more phone numbers having the same longest number,
 *     then only the one with minimum numeric value can be waived
 *     
 *  The bill text is formed as "hh:mm:ss,nnn-nnn-nnn\nhh:mm:ss,nnn-nnn-nnn...", so for example,
 *  for "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090", namely
 *  
 *  	00:01:07,400-234-090
 *  	00:05:01,701-080-080
 *  	00:05:00,400-234-090
 *  
 *  You just need to pay 150 * 6 = 900 to 701-080-080, since the fee associated with 400-234-090 is waived
 *  
 *  Now write a function to return the total fees. Assuming:
 *  1. Each phone number can have one or multiple calling records
 *  2. No leading 0 appears in the phone number
 *  
 * @author GoldCoastITSolution (GCI) on codility
 */
public class PhoneBill {

	/**
	 * 	This is the main function for execution	
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameter	*/
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";		// Test string provided by GCI
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n00:06:07,500-600-700";
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n00:01:06,701-080-080";
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n11:59:59,567-893-987\n00:40:43,978-798-321";
		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n11:00:00,567-893-987\n00:40:43,978-798-321\n01:00:00,567-893-987";
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n11:59:59,567-893-987\n00:40:43,978-798-321\n11:59:59,566-893-987";
//		String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:00,400-234-090\n00:05:01,701-080-080\n00:05:01,701-080-080\n00:05:01,701-080-080\n00:05:01,701-080-080";
		
		/*	Display the result	*/
		System.out.println("Total fees: " + solution(S));
	}
	
	private static int solution(String S) {
		
		/*	Here is to split the bill text, initialize a map to store all the records for each phone number and a parameter to store the total fees	*/
		String[] split = S.split("\n");
		Map<Integer, List<Integer>> phoneRecords = new HashMap<Integer, List<Integer>>();
		int totalFees = 0;
		
		try {
			/*	This part is to store the phoneRecords map for each line of the bill text	*/
			for (String string : split) {
				
				/*	Process the phone number string & duration string	*/
				String[] split2 = string.split(",");
				Integer seconds = (int) ((new SimpleDateFormat("hh:mm:ss").parse(split2[0]).getTime() - 18e6) / 1000);
				String[] split3 = split2[1].split("-");
				StringBuffer stringBuffer = new StringBuffer(split3[0]);
				stringBuffer.append(split3[1]).append(split3[2]);
				Integer phone = Integer.valueOf(stringBuffer.toString());
				
				/*	Store all the phone records corresponding to phone numbers	*/
				if (!phoneRecords.containsKey(phone)) {
					List<Integer> records = new ArrayList<Integer>();
					records.add(seconds);
					phoneRecords.put(phone, records);
				} else {
					List<Integer> records = phoneRecords.get(phone);
					records.add(seconds);
					phoneRecords.put(phone, records);
				}
			}	
			
			/*	This part is to compute the total duration for each phone number	*/
			System.out.println("Original phone record map:");									// Test output
			Map<Integer, Integer> phoneTotalDuration = new HashMap<Integer, Integer>();
			Set<Entry<Integer, List<Integer>>> entrySet = phoneRecords.entrySet();
			for (Entry<Integer, List<Integer>> entry : entrySet) {

				System.out.println(entry.getKey() + ", " + entry.getValue());	// Test output
				int totalDuration = 0;
				List<Integer> records = entry.getValue();
				for (Integer seconds : records) {
					totalDuration += seconds;
				}
				phoneTotalDuration.put(entry.getKey(), totalDuration);
			
			}
			
			/*	This part is to find the phone number whose duration is the longest & eliminate it	*/
			System.out.println("\nTotal duration map:");							// Test output
			List<Integer> longestDurationPhoneNumbers = new ArrayList<Integer>();
			Set<Entry<Integer, Integer>> entrySet2 = phoneTotalDuration.entrySet();
			for (Entry<Integer, Integer> entry : entrySet2) {

				System.out.println(entry.getKey() + ", " + entry.getValue());	// Test output				
				if (entry.getValue().equals(Collections.max(phoneTotalDuration.values()))) {
					longestDurationPhoneNumbers.add(entry.getKey());
				}
			}
			phoneRecords.remove(Collections.min(longestDurationPhoneNumbers));
			
			/*	This part is to compute the total fees on the bill text		*/
			System.out.println("\nRemaining phone record map:");				// Test output
			for (Entry<Integer, List<Integer>> entry : entrySet) {
				
				System.out.println(entry.getKey() + ", " + entry.getValue());	// Test output
				List<Integer> records = entry.getValue();
				for (Integer seconds : records) {
					if (seconds < 300) {
						totalFees += 3 * seconds;
					}
					if (seconds >= 300) {
						if (seconds % 60 == 0) {
							totalFees += 150 * (seconds / 60);
						} 
						if (seconds % 60 > 0) {
							totalFees += 150 * (seconds / 60 + 1);
						}
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*	Return the total result	 */
		System.out.println();  													// Test output
		return totalFees;
	}
}
