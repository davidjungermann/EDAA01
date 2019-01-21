package test;

import java.util.Scanner;

public class PowerTest {

	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Input base followed by exponent to calculate the power of the two numbers: ");
		double val = power(scan.nextDouble(), scan.nextInt());
		System.out.println("The value is: " + val);
	}

	public static double recPower(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			double p = power(x, (n / 2));
			if (n % 2 == 0) {
				return p * p;
			} else {
				return x * p * p;
			}

		}

	}

	private static double power(double x , int n) {
		if(n < 0) {
			return 1.0/recPower(x, -n);
		} else {
			return recPower(x, n);
		}
	}

}
