package pro01;


import java.util.Scanner;

public class Validate {
	private final static Scanner scn = new Scanner(System.in);

	public int checkLimitInput(int _min, int _max) {
		int choice;

		while (true) {
			try {
				choice = Integer.parseInt(scn.nextLine());
				if (choice < _min || choice > _max) {
					throw new NumberFormatException();
				}
				return choice;
			} catch (NumberFormatException e) {
				System.err.println("range received from " + _min + " to " + _max);
			}
		}
	}

	public static String checkInputString() {
		String res;
		while (true) {
			res = scn.nextLine();
			if (res.trim().equals("")) {
				System.err.println("Please enter string not empty");
			} else
				break;
		}
		return res;
	}

	public static Double checkInputDouble() {
		Double res;
		while (true) {
			try {
				res = Double.parseDouble(scn.nextLine());
				return res;
			} catch (Exception e) {
				System.err.println("Please reinput double");
			}
		}
	}
}
