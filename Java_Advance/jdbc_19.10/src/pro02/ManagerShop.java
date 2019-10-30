package pro02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerShop {
	static Scanner scn = new Scanner(System.in);
	ArrayList<Customer> listCustomer;

	public ManagerShop() {
		listCustomer = new ArrayList<>();

		listCustomer.add(new Customer(LocalDate.now(), "Adam", "issue1", true));
		listCustomer.add(new Customer(LocalDate.now(), "Eva", "issue2", false));
		listCustomer.add(new Customer(LocalDate.now(), "Eva", "issue3", false));
		listCustomer.add(new Customer(LocalDate.now(), "Eva", "issue4", true));
	}

	private int menu() {
		System.out.println("1. A list of all available customers in the system");
		System.out.println("2. Whether a given customer’s name has any issue or not");
		System.out.println("3. Record/Add an issue for a customer");
		System.out.println("4. A list of all solved issues and unsolved issues");
		System.out.println("5. Update an issue’s status from unsolved to solved only");
		System.out.println("6. A list of all issues on a specific date");
		System.out.print("Your choice: ");
		int choice = Validate.checkLimitInput(1, 6);
		return choice;
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				printAllCustomer();
				break;
			case 2:
				checkCustomer();
				break;
			case 3:
				addIssue();
				break;
			case 4:
				showAllIssue();
				break;
			case 5:
				
				updateIssue();
				break;
			case 6:
				printAllCustomerInDate();
				break;

			}
		}
	}

	private void showAllIssue() {
		System.out.println("issue slove");
		for (Customer customer : listCustomer) {
			if(customer.isStatus())
				customer.display();
		}
		System.out.println("-------------------------");
		System.out.println("issue slove");
		for (Customer customer : listCustomer) {
			if(!customer.isStatus())
				customer.display();
		}
		System.out.println("-------------------------");
	}

	private void printAllCustomer() {
		System.out.println("--------------------------");
		listCustomer.forEach(x -> x.display());
		System.out.println("--------------------------");
	}

	private void checkCustomer() {
		System.out.print("Enter name customer: ");
		String name = Validate.checkInputString();

		int found = Validate.findNameCustomer(listCustomer, name);
		if (found == -1) {
			System.out.println("Customer not found in system!");
			return;
		}

		listCustomer.get(found).display();
	}

	private void addIssue() {
		System.out.print("Enter name customer: ");
		String name = Validate.checkInputString();

		int found = Validate.findNameCustomer(listCustomer, name);
		if (found == -1) {
			System.out.println("Customer not found in system!");
			return;
		}
		System.out.println("Enter issue: ");
		String issue = Validate.checkInputString();

		listCustomer.get(found).setIssue(issue);
		System.err.println("add issue successfully!");
	}

	private void updateIssue() {
		System.out.print("Enter name customer: ");
		String name = Validate.checkInputString();

		for (Customer customer : listCustomer) {
			if(customer.getName().trim().equalsIgnoreCase(name)) {
				customer.setStatus(true);
			}
		}
		
		System.err.println("Update issue's status succesfully!!!");
	}
	
	public static LocalDate dateInput(String userInput) {

	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    LocalDate date = LocalDate.parse(userInput, dateFormat);

	    return date ;
	}

	private void printAllCustomerInDate() {
		LocalDate date;
		
		System.out.println("Enter date (format:yyyy/MM/dd): ");
		String sDate = Validate.checkInputString();
		
		date = dateInput(sDate);
		
		for (Customer customer : listCustomer) {
			if(customer.getDate().isEqual(date)) {
				customer.display();
			}
		}
		
	}

}
