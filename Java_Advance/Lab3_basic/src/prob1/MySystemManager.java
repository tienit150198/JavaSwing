package prob1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySystemManager {
	public static class Singleton {
		public static final MySystemManager instance = new MySystemManager();
	}

	public static MySystemManager getInstance() {
		return Singleton.instance;
	}

	private List<IntPhoneNumber> Iphonenumbers;
	private static final Scanner scn = new Scanner(System.in);

	private MySystemManager() {
		Iphonenumbers = new ArrayList<>();
	}

	private IntPhoneNumber input(boolean flag) {

		int area;
		String number, countryCode = "";

		if (flag) {
			System.out.print("Enter country code: ");
			countryCode = scn.nextLine();
		}
		System.out.print("Enter area code: ");
		area = Integer.parseInt(scn.nextLine());
		System.out.print("Enter number: ");
		number = scn.nextLine();

		IntPhoneNumber IphoneNumber = new IntPhoneNumber(countryCode, area, number);

		return IphoneNumber;
	}

	private void inputLocalPhone() {
		Iphonenumbers.add(input(false));
	}

	private void inputInterPhone() {
		Iphonenumbers.add(input(true));
	}

	private void displayAllPhone() {
		System.out.println("\nList of phone number:");
		System.out.println("----------------------------------------");
		Iphonenumbers.forEach(x -> x.display());
	}

	private int menu() {
		System.out.print("Type of phone number ? (1 – local phone, 2 – Inter phone number, 0 - exit): ");
		int choice = checkLimitInput(0, 2);
		return choice;
	}

	private int checkLimitInput(int _min, int _max) {
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

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				inputLocalPhone();
				break;
			case 2:
				inputInterPhone();
				break;
			default:
				displayAllPhone();
				return;
			}
		}
	}

}
