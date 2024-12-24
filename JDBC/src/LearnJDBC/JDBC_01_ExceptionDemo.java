package LearnJDBC;

import java.util.Scanner;

public class JDBC_01_ExceptionDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value:");
		
		
//		int divindent = sc.nextInt();
//		int diviser = sc.nextInt();
//		int getArray = sc.nextInt();
		
//Program 1
//		Using multiple catch block
		
//		try {
//			int arr[] = new int[5];
//			int result = arr[getArray] = divindent / diviser;
//			System.out.println(arr[getArray]);
//		} catch (ArithmeticException e) {
//			System.out.println(e.getMessage());
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println(e.getMessage());
//		} 
		
//Program 2
//		Using multiple Exception in single line
//		try {
//			int arr[] = new int[5];
//			int result = arr[getArray] = divindent / diviser;
//			System.out.println(arr[getArray]);
//		} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
//			System.out.println(e.getMessage());
//		}
		
		
//Program 3
//		Write a program to use throw keyword
		
		int age = sc.nextInt();
		
		if(age < 18) {
			throw new RuntimeException("You are not elegable for vote");
		}
		else {
			System.out.println("You are elegable for vote");
		}
		
		
		divisionDemo(10, 2);
		
	}

//Program 4
//	 Write a program to use throws keyword
	
	static void divisionDemo(int divindent, int diviser) throws ArithmeticException{
		System.out.println("The result is: " + divindent / diviser);
	}

}
