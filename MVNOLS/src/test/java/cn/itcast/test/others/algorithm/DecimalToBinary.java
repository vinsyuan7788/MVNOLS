package cn.itcast.test.others.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 	Write a program to convert decimal number to binary format using numeric operations.
 * 
 * @author Vince Xu Yuan
 */
public class DecimalToBinary {

	/**
	 * 	This is the main function to execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*	Show binary result	*/
		System.out.print("The binary format: ");
		toBinaryFormat(25);
	}

	/**
	 * 	This is the method to convert the decimal number to binary format using numeric operations
	 * @param number
	 * @return
	 */
	private static void toBinaryFormat(int number) {
		
		/*	Initialize a list to store the binary digits	*/
		List<Integer> binaryDigits = new ArrayList<Integer>();
		
		/*	Convert to binary digits & save them into the list	*/
		while (number != 0) {
			binaryDigits.add(number % 2);
			number = number / 2;
		}
		
		/*	Output the binary result	*/
		for (int i = binaryDigits.size() - 1; i >= 0; i--) {
			System.out.print(binaryDigits.get(i));
		}
	}
}
