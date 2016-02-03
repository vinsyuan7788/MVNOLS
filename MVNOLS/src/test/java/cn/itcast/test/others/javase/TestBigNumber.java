package cn.itcast.test.others.javase;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

/**
 * 	This is the perform testing regarding big number
 * 	1. BigInteger & BigDecimal can be used to ensure the precision of number calculation
 *  2. BigInteger & BigDecimal can represent the calculated result in any order of magnitude
 * @author Vince Xu Yuan
 */
public class TestBigNumber {

	/**
	 * 	There are problems regarding number calculation
	 * 	1. Double-type calculation is not precise
	 * @throws Exception
	 */
	@Test
	public void testNumberCalculation () throws Exception {
		
		/*	Double subtraction result is not precise	 */
		double d1 = 2.0;
		double d2 = 1.1;
		System.out.println("The double subtraction result: " + (d1 - d2));
		
		/*	Double multiplication result is not precise	 */
		double d3 = 3.0;
		System.out.println("The double subtraction result: " + (d3 * d2));
	}
	
	/**
	 * 	BigInteger & BigDecimal can: 
	 * 	1. Address the calculation inaccuracy
	 * 	2. Represent the number with any order of magnitude
	 * @throws Exception
	 */
	@Test
	public void testBigNumber () throws Exception {
		
		/*	Use BigDecimal to do precise calculation	*/
		BigDecimal bd1 = new BigDecimal("2.0");
		BigDecimal bd2 = new BigDecimal(1.1);				
		BigDecimal bd3 = new BigDecimal(String.valueOf(3.0)); 	// Can use "String.valueOf()" to convert to string to get BigDecimal object
//		BigDecimal bd3 = BigDecimal.valueOf(3.0);				// Can use BigDecimal.valueOf() to get BigDecimal object
		System.out.println("The subtraction result: " + bd1.subtract(bd2).doubleValue());
		System.out.println("The multiplication result: " + bd3.multiply(bd2).doubleValue());
		
		/*	Use BigInteger to do 1000 factorial	 */
		BigInteger sum = new BigInteger(String.valueOf(1));
//		BigInteger sum = BigInteger.valueOf(1);					// Can use "BigInteger.valueOf()" to get BigInteger object
		for (int i = 1; i <= 1000; i++) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		System.out.println("The 1000 factorial result: " + sum);
	}
}
