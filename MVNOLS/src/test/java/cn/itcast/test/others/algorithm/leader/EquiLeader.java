package cn.itcast.test.others.algorithm.leader;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
A non-empty zero-indexed array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

For example, given array A such that:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
The goal is to count the number of equi leaders.

Write a function:

class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.

For example, given:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
the function should return 2, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class EquiLeader {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] A = {4, 3, 4, 4, 4, 2};
//		int[] A = {1, 2, 3, 4, 4, 5};
//		int[] A = {2, 2};
		
		System.out.println("The number of equi-leader: " + solution(A));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Using a map to keep track of the counts of the elements in array
	 * @param A
	 * @return
	 */
	private static int solution(int[] A) {
		
		/*	Define a map to keep tract the element count, 2 parameter to store the leader and the number of equi-leader	 */
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		Integer leader = null;
		int numOfEquiLeader = 0;
		
		/*	Count the elements in array	 */
		for (int i = 0; i <= A.length - 1; i++) {
			if (!counts.containsKey(A[i])) {
				counts.put(A[i], 1);
			} else {
				counts.put(A[i], counts.get(A[i]) + 1);
			}
		}
		
		/*	Get the leader of the array	 */
		Set<Entry<Integer, Integer>> entrySet = counts.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() > A.length / 2) leader = entry.getKey();
		}
		
		/*	
		 * 	Find & return the number of equi-leader from the leader (*****)	
		 * 	1. If the leaders in the left part and right part are not the same, or the leaders do not even exist, it means there is no equi-leader.
		 *     In this case, for whe whole array there is no leader
		 *     -- Hence if there is no leader for an array, then no need to find equi-leader
		 *  2. Otherwise if we can find a equi-leader for an array, then this equi-leader is defnitely the leader for the array 
		 *     -- Hence if there is a leader for an array, then find the equi-leader
		 */
		if (leader == null) return 0;
		int numOfLeaderInLeftPart = 0;
		int numOfLeaderInRightPart = 0;
		for (int i = 0; i <= A.length - 2; i++) {
			
			/*	Accumulate the number of leader in the left-part array	*/
			if (A[i] == leader) numOfLeaderInLeftPart++;
			
			/*	Get the number of leader in the right-part array	*/
			numOfLeaderInRightPart = counts.get(leader) - numOfLeaderInLeftPart;
			
			/*	See if the leader on each part meets the requirement. If yes, then the leader is equi-leader, hence accumulate the number of equi-leader	*/
			if (numOfLeaderInLeftPart > ((i - 0) + 1) / 2 && numOfLeaderInRightPart > ((A.length - 1) - (i + 1) + 1) / 2) {
				numOfEquiLeader++;
			}
		}
		return numOfEquiLeader;
	}
}
