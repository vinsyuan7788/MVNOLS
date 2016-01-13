package cn.itcast.test.others.javase.testclass.assertion;

/**
 * 	This is a class for assertion
 * 	1. Assert should be used for input validation to a private method
 * 	2. To make assert effective: should enable assertion firstly
 */
public class Computer {

	/*	Instance variables	*/
	private int price;
	private String name;

	/*	This is a PRIVATE setter	*/
	private void setPrice(int price) {
		
		/**
		 * 	Use assert & customize error message	
		 */
		assert (price > 0): "The price must be greater than 0";
		this.price = price;
	}

	/*	This is the overrided method for console output	 */
	@Override
	public String toString() {
		return "Computer [price=" + price + ", name=" + name + "]";
	}
	
	/*	Main function	*/
	public static void main(String[] args) {
		
		Computer computer = new Computer();
		computer.setPrice(-1);
		System.out.println("The computer: " + computer);
	}
}
