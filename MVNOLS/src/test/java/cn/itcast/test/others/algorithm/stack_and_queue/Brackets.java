package cn.itcast.test.others.algorithm.stack_and_queue;

import java.util.Stack;

/**
A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

S is empty;
S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, the string "{[()()]}" is properly nested but "([)()]" is not.

Write a function:

class Solution { public int solution(String S); }
that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

Assume that:

N is an integer within the range [0..200,000];
string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).

@author codility
 */
public class Brackets {

	/**
	 * 	This is the main function for execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Given input parameter	*/
		String S = "{[()()]}";
//		String S = "([)()]";
		
		/*	Display the result	*/
		System.out.println("Is the string properly nested? " + (solution(S)==1?true:false));
	}
	
	/**
	 * 	This is the method for solution (100%/100%)
	 * 	-- Using stack to trace the brackets
	 * @param S
	 * @return
	 */
	private static int solution(String S) {
		
		/*	Break the string into character array	*/
		char[] brackets = S.toCharArray();
		
		/*	If the number of the elements in character array is odd, return 0	*/ 
		if (brackets.length % 2 != 0) return 0;
		
		/*	Otherwise define a stack to trace the brackets	*/
		Stack<Character> stack = new Stack<Character>();
		
		/*	For each element in the character array	 */
		for (char c : brackets) {
			
			/*	If it is left bracket, then push it into stack	*/
			if (c == '{' || c == '[' || c == '(') stack.push(c);
			
			/*	
			 * 	If it is right bracket
			 * 	1. If it is an empty stack, it means right bracket does not have the corresponding left bracket to match
			 *     -- Hence return 0
			 *  2. If we can peek the corresponding left bracket that is stored in stack
			 *     -- Pop out the corresponding left bracket
			 *  3. Otherwise push the right bracket into stack
			 */
			if (c == '}') {
				if (stack.empty()) {
					return 0;
				} else if (stack.peek() == '{') {
					stack.pop();
				} else {
					stack.push(c);
				}
			}
			
			if (c == ']') {
				if (stack.empty()) {
					return 0;
				} else if (stack.peek() == '[') {
					stack.pop();
				} else {
					stack.push(c);
				}
			}
			
			if (c == ')') {
				if (stack.empty()) {
					return 0;
				} else if (stack.peek() == '(') {
					stack.pop();
				} else {
					stack.push(c);
				}
			}
		}
		
		/*	If the stack is empty, it means each right bracket find its matched left bracket, so return 1	*/
		if (stack.empty()) {
			return 1;
			
		/*	Otherwise there is at least unmatched right bracket left, so return 0	*/
		} else {
			return 0;
		}
	}
}
