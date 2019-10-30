package trackemployeeinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TrackingEmployeeAndOrganization {
	final static Scanner scn = new Scanner(System.in);
	private static List<Employees> employees;
	private static Map<String, Integer> organizations;

	public TrackingEmployeeAndOrganization() {
		employees = new ArrayList<>();
		organizations = new HashMap<>();
	}

	public void inputEmployee() {
		if (organizations.isEmpty()) {
			System.out.println("organizations is employee, please enter organization\n");
			return;
		}

		Employees employee = new Employees();
		employee.input();

		System.out.print("enter organization's employee: ");
		String organization = checkAvailableOrganization();
		employee.setOrganization(organization);
		if (employees.contains(employee)) {
			System.out.println("employee is exitst");
			return;
		}

		organizations.put(organization, organizations.get(organization) + 1); // update number of employee
		employees.add(employee);
	}

	public void inputOrganizations() {

		System.out.print("Enter organization name: ");
		String name = scn.nextLine().trim();

		if (organizations.containsKey(name)) {
			System.out.println("Organization is exists");
			return;
		}
		organizations.put(name, 0);
	}

	public void displayListEmployee() {
		if (employees.isEmpty()) {
			System.out.println("employee is empty\n");
			return;
		}
		employees.forEach(employee -> employee.display());
	}

	public void displayOrganizations() {
		if (organizations.isEmpty()) {
			System.out.println("organization is employee\n");
			return;
		}
		organizations.forEach((name, number) -> {
			System.out.println("Organization Name : " + name + "\tNumber of Employees: " + number);
			System.out.println("Enter: ");
		});
	}

	public String checkAvailableOrganization() { // error: NoSuchElementException - no line found
		String userInput;
		while (true) {
			userInput = scn.nextLine();
			try {
				if (!organizations.containsKey(userInput)) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.err.println("organization doesn't exists, please enter again");
				System.out.println("Enter: ");
			}
		}
		return userInput;
	}

	private static int checkInputLimit(int _min, int _max) {
		int choice;
		while (true) {
			try {
				choice = Integer.parseInt(scn.nextLine());
				if (choice < _min || choice > _max)
					throw new NumberFormatException();
				return choice;
			} catch (NumberFormatException e) {
				System.err.println("choice number " + _min + " to " + _max + " , please enter again");
				System.out.print("Enter again: ");
			}

		}

	}

	public static int menu() {
		int choice;
		System.out.println("\n1. Enter employee");
		System.out.println("2. Display employee");
		System.out.println("3. Enter Organization");
		System.out.println("4. Display Organization");
		System.out.println("5. Exit");
		System.out.print("Enter: ");
		choice = checkInputLimit(1, 5);

		return choice;
	}

	public static void main(String[] args) {
		TrackingEmployeeAndOrganization obj = new TrackingEmployeeAndOrganization();

		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				obj.inputEmployee();
				break;
			case 2:
				obj.displayListEmployee();
				break;
			case 3:
				obj.inputOrganizations();
				break;
			case 4:
				obj.displayOrganizations();
				break;
			case 5:
				scn.close();
				System.exit(0);
			}
		}

	}
}
