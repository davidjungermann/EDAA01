package test;

import java.util.Scanner;

public class FactorialMain {

	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Input value to calculate factorial of: ");
		long val = factorial(scan.nextInt());
		if (val > 0) {
			System.out.println("The value is: " + val);
		} else {
			System.out.println("The value is too large");
		}

	}

	public static long factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}

	}

}
