package pro02;

import java.util.Scanner;

import pro03.Validate;

public class Test {
	final static Scanner scn = new Scanner(System.in);

	public static Integer convertStringToInt() {
		Integer res;
		while (true) {
			try {
				res = Integer.parseInt(Validate.checkInputString());
				return res;
			} catch (NumberFormatException e) {
				System.err.println("please input number interger");
			} catch (Exception e) {
				System.err.println("String input errr - please reinput string");
			}
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter String:");
		System.out.println(convertStringToInt());
	}
}
