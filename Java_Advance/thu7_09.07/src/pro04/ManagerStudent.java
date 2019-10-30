package pro04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ManagerStudent {
	static Scanner scn = new Scanner(System.in);
	List<Student> students;

	public ManagerStudent() {
		students = new ArrayList<>();
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

	private int menu() {
		System.out.println("1. Create");
		System.out.println("2. Find and Sort");
		System.out.println("3. Update/Delete");
		System.out.println("4. Report");
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
				create();
				break;
			case 2:
				findAndSort();
				break;
			case 3:
				updateAndDelete();
				break;
			case 4:
				report();
				break;
			case 5:
				return;
			}
		}
	}

	private void create() {
		int id;
		String name, course, language;

		System.out.println("Enter id: ");
		id = InputVariable.inputInteger();
		System.out.println("Enter name: ");
		name = InputVariable.inputString();
		System.out.println("Enter course: ");
		course = InputVariable.inputString();
		System.out.println("Enter language: ");
		language = InputVariable.inputString();

		students.add(new Student(id, name, course, language));
		System.out.println("Add successfully!");
	}

	public ArrayList<Student> listStudentFindByName(List<Student> listContact2) {
		ArrayList<Student> listContactFindByName = new ArrayList<Student>();

		System.out.print("Enter name: ");
		String name = scn.nextLine();

		for (Student student : listContact2) {
			if (student.getName().contains(name)) {
				listContactFindByName.add(student);
			}
		}
		return listContactFindByName;
	}

	private void findAndSort() {
		ArrayList<Student> listContactFindByName = listStudentFindByName(students);
		if (listContactFindByName.isEmpty()) {
			System.err.println("Not found student");
			return;
		}

		System.out.println("found " + listContactFindByName.size() + " student");

		System.out.println("You want sort student list? (Y/N)");
		char c = InputVariable.inputCharacter();

		if (checkChooseYN(c)) {
			Collections.sort(listContactFindByName);
			System.out.println(" ------------------------------------------ ");
			listContactFindByName.forEach(x -> x.display());
			System.out.println(" ------------------------------------------ ");
		}
	}

	private boolean checkChooseYN(char x) {
		return (x == 'y' || x == 'Y');
	}

	private void updateAndDelete() {

	}

	private void report() {

	}
}
