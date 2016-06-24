package cn.itcast.test.others.algorithm.stack_and_queue;

import java.util.Stack;

/**
You are given two non-empty zero-indexed arrays A and B consisting of N integers. Arrays A and B represent N voracious fish in a river, ordered downstream along the flow of the river.

The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially upstream of fish Q. Initially, each fish has a unique position.

Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. All its elements are unique. Array B contains the directions of the fish. It contains only 0s and/or 1s, where:

0 represents a fish flowing upstream,
1 represents a fish flowing downstream.
If two fish move in opposite directions and there are no other (living) fish between them, they will eventually meet each other. Then only one fish can stay alive − the larger fish eats the smaller one. More precisely, we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them. After they meet:

If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
We assume that all the fish are flowing at the same speed. That is, fish moving in the same direction never meet. The goal is to calculate the number of fish that will stay alive.

For example, consider arrays A and B such that:

  A[0] = 4    B[0] = 0
  A[1] = 3    B[1] = 1
  A[2] = 2    B[2] = 0
  A[3] = 1    B[3] = 0
  A[4] = 5    B[4] = 0
Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish number 4 and is eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay alive.

Write a function:

class Solution { public int solution(int[] A, int[] B); }
that, given two non-empty zero-indexed arrays A and B consisting of N integers, returns the number of fish that will stay alive.

For example, given the arrays shown above, the function should return 2, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [0..1,000,000,000];
each element of array B is an integer that can have one of the following values: 0, 1;
the elements of A are all distinct.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

@author codility
 */
public class Fish {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameters	*/
		int[] A = {4, 3, 2, 1, 5};		// Case 1
		int[] B = {0, 1, 0, 0, 0};		// Result: 2
//		int[] A = {4, 3, 2, 1, 5};		// Case 2
//		int[] B = {0, 0, 1, 0, 0};		// Result: 3
//		int[] A = {4, 3, 2, 1, 5};		// Case 3
//		int[] B = {1, 1, 0, 1, 1};		// Result: 4
//		int[] A = {5, 3, 4, 1, 2};		// Case 4
//		int[] B = {1, 1, 0, 1, 1};		// Result: 3
//		int[] A = {4, 2, 3, 1, 5};		// Case 5
//		int[] B = {1, 1, 0, 1, 1};		// Result: 4
//		int[] A = {4, 3, 2, 1, 5};		// Case 6
//		int[] B = {1, 1, 0, 1, 0};		// Result: 1
//		int[] A = {2, 3, 4, 5, 1};		// Case 7
//		int[] B = {1, 1, 0, 1, 0};		// Result: 2
//		int[] A = {1, 2, 3, 4, 5};		// Case 8
//		int[] B = {1, 1, 1, 1, 0};		// Result: 1
//		int[] A = {1, 5, 3, 2, 4};		// Case 9
//		int[] B = {1, 1, 1, 1, 0};		// Result: 2
//		int[] A = {1, 2, 3, 4, 5};		// Case 10
//		int[] B = {0, 0, 0, 0, 1};		// Result: 5
		
		/*	Display the result	*/
		System.out.println("The number of surviving fish: " + solution(A, B));
	}
	
	/**
	 * 	This is the method for solution: (100%/100%)
	 *  -- Using a stack to record the surviving fishes
	 * @param A
	 * @param B
	 * @return
	 */
	private static int solution(int[] A, int[] B) {
		
		/*	Define a stack to store surviving fishes	*/
		Stack<Integer> stack = new Stack<Integer>();
		
		/*	Loop for each fish	*/
		for (int i = 0; i <= A.length - 1; i++) {
			
			/*	If the stack contains no fish, then the 1st fish is assumed to survive	*/
			if (stack.empty()) {
				stack.push(i);
				
			/*	If the stack contains at least 1 fish	*/
			} else {
				
				/*	If current fish is streaming the same direction as the previous fish, add current fish to the stack		*/
				if (B[i] == B[stack.peek()]) {
					stack.push(i);
				} 
				
				/*	If the current fish is streaming an opposite direction against the previous fish	*/
				if (B[i] != B[stack.peek()]) {
					
					/*	If curernt fish is downstreaming & previous fish is upstreaming: add current fish to the stack	 */
					if (B[i] == 1 && B[stack.peek()] == 0) {
						stack.push(i);
					}
					
					/*	If current fish is upstreaming & previous fish is downstreaming (******)	 */
					if (B[i] == 0 && B[stack.peek()] == 1) {
						
						/*	If the size of current fish is smaller, then current fish will be eaten, so no need to add to the stack	 */
						if (A[i] < A[stack.peek()]) continue;
						
						/*	If the size of current fish is larger	*/
						if (A[i] > A[stack.peek()]) {
							
							/*
							 * 	Previous fish will be eaten, hence previous fish needs to be popped out from the stack (******)
							 * 	This pop-out process will should be a loop, continuing while:
							 * 	1. The stack is not empty, which means there are fishes that can be popped out
							 * 	   -- If the stack becoms empty, it means all previous fishes are eaten by current fish
							 *     -- In this case, the current fish will become the 1st fish in the stack, so add it to the stack 
							 *        -- E.g., Case 8
							 *  2. The stream direction of the current fish and the most recent fish should be opposite
							 *     -- If the current fish and the most recent fish have the same direction, it means those fishes having opposite directions in-between are all eaten and popped out from the stack
							 *     -- In this case, this current fish survive, so add it to the stack 
							 *        -- E.g., Case 1
							 * 	3. The size of current fish is larger than the most recent fish
							 *     -- If the current fish is smaller than the most recent fish when they have opposite stream directions, it means this current fish will be eaten by the most recent fish
							 *     -- In this case, no need to add the current fish to the stack 
							 *        -- E.g., Case 9
							 *  Hence we have 2 situations that need to add the stack:
							 *  1. The stack is empty
							 *  2. The directions of current fish and the most recent fish are the same
							 */
							while (!stack.empty() && B[i] != B[stack.peek()] && A[i] > A[stack.peek()]) stack.pop();
							if (stack.empty() || (!stack.empty() && B[i] == B[stack.peek()])) stack.push(i);
						}
					}
				}
			}
		}
		
		/*	Return the number of surviving fishes	*/
		return stack.size();
	}
}
