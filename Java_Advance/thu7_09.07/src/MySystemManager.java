import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySystemManager {
	public static class Singleton {
		public static final MySystemManager instance = new MySystemManager();
	}

	protected static final Scanner scn = new Scanner(System.in);

	public static MySystemManager getInstance() {
		return Singleton.instance;
	}

	List<Contact> listContact;

	private MySystemManager() {
		listContact = new ArrayList<>();
	}

	public void listStudentFindByName(List<Contact> listContact2) {

		System.out.print("Enter name: ");
		String name = scn.nextLine();

		for (Contact contact : listContact2) {
			if (contact.getName().contains(name)) {
				contact.display();
			}
		}
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

	private void deleteContact() {
		int id;
		System.out.println("Enter id delete: ");
		id = Integer.parseInt(scn.nextLine());
		boolean ok = false;
		for (Contact contact : listContact) {
			if (contact.getThisId() == id) {
				listContact.remove(contact);
				ok = true;
			}
		}

		if (!ok) {
			System.out.println("No found contact");
		}
	}

	private void displayContact() {
		if (listContact.isEmpty()) {
			System.out.println("contact list is empty, please add");
			return;
		}
		listContact.forEach(x -> x.display());
	}

	private Contact input() {
		String name, firstName, lastName, group, address, phone;

		System.out.println("Enter name: ");
		name = scn.nextLine();

		// set firstName, lastName
		String split[] = name.split(" ");
		firstName = split[0].trim();
		lastName = split[1].trim();

		System.out.println("Enter group: ");
		group = scn.nextLine();
		System.out.println("Enter address: ");
		address = scn.nextLine();
		System.out.println("Enter phone: ");
		phone = scn.nextLine();

		return new Contact(name, firstName, lastName, group, address, phone);
	}

	private void inputContact() {
		listContact.add(input());
	}

	private int menu() {
		System.out.println("1. Add a contact");
		System.out.println("2. Display all contact");
		System.out.println("3. Delete a contact");
		System.out.println("4. Search a contact");
		System.out.println("5. Exit");
		System.out.print("Your choice: ");
		int choice = checkLimitInput(1, 5);
		return choice;
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				inputContact();
				break;
			case 2:
				displayContact();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				listStudentFindByName(listContact);
				break;
			case 5:
				return;
			}
		}
	}

}
