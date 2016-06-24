package cn.itcast.test.others.algorithm.time_complexity;

import java.util.LinkedList;
import java.util.List;

/**
Write a function:

class Solution { public int solution(int A, int B, int K); }
that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }
For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.

Assume that:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.
Complexity:

expected worst-case time complexity is O(1);
expected worst-case space complexity is O(1).

@author codility
 */
public class CountDivisibleIntegersWithinSomeRange {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		int A = 6;
		int B = 11;
		int K = 2;
		
		System.out.println("The number of divisible integers: " + solution(A, B, K));
		System.out.println("The number of divisible integers: " + solution2(A, B, K));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Pure math problem
	 * @param A
	 * @param B
	 * @param K
	 * @return
	 */
	private static int solution(int A, int B, int K) {
		
	   if (A % K == 0) {
	        return (B - A) / K + 1;
	    } else {
	        return (B - (A - A % K )) / K;
	    }
	}
	
	/**
	 * 	This is the 2nd method for solution (100%/0%)
	 * 	-- Using list to store the divisible integers
	 * @param A
	 * @param B
	 * @param K
	 * @return
	 */
	private static int solution2(int A, int B, int K) {
		
		/*	Initialize an array to store all integers within [A, B]	*/
		int[] integers = new int[B - A + 1];
		for (int i = A; i <= B; i++) {
			integers[i-A] = i;
		}
		
		/*	Find out all divisible integers & return	*/
		List<Integer> divisibleIntegers = new LinkedList<Integer>();
		for (int integer : integers) {
			if (integer % K == 0) {
				divisibleIntegers.add(integer);
			}
		}
		return divisibleIntegers.size();
	}
}
