package cn.itcast.test.others.algorithm.time_complexity;

/**
 * 	You are given an array of numbers. 
 * 	Find out the array index or position where sum of numbers preceeding the index is equals to sum of numbers succeeding the index. 
 * 
 * @author Vince Xu Yuan
 */
public class FindMiddleIndex {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given an array of numbers	*/
		int[] numbers = {2, 4, 4, 5, 4, 1};
//		int[] numbers = {2, 1, 3};
//		int[] numbers = {3, 1, 2};
		
		/*	Find out the array index	*/
		try {
			System.out.println("The array index: " + getMiddleIndex(numbers));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 	This is the method to get the middle index
	 * @param numbers
	 * @return
	 * @throws Exception 
	 */
	private static int getMiddleIndex(int[] numbers) throws Exception {
		
		/*	Initialize necessary parameters	 */
		int startIndex = 0;
		int endIndex = numbers.length - 1;
		int leftSum = 0;
		int rightSum = 0;
		
		/*	Loop to add the left sum & right sum respectively until the result comes out	*/
		while (true) {
			
			/*	If the leftSum is smaller, then add the left part, otherwise add the right part	 */
			if (leftSum > rightSum) {
				rightSum += numbers[endIndex];
				endIndex--;
			} else {
				leftSum += numbers[startIndex];
				startIndex++;
			}
			
			/*	When the startIndex > endIndex, if the result comes out as expected, then break, otherwise throw an exception	 */
			if (startIndex > endIndex) {
				if (leftSum == rightSum) {
					break;
				} else {
					throw new Exception("Please pass proper array to match the requirement");
				}
			}
			
		}
		
		/*	Return the middle index	 */
		return endIndex;
	}
}
