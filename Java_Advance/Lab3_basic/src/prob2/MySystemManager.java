package prob2;

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

	protected static final Scanner scn = new Scanner(System.in);
	List<Worker> listWorkers;

	private MySystemManager() {
		listWorkers = new ArrayList<>();
	}

	private Worker input() {
		String name;
		double salaryBasic, rate;

		System.out.print("Enter name: ");
		name = scn.nextLine();
		System.out.print("Enter salary basic: ");
		salaryBasic = Double.parseDouble(scn.nextLine());
		System.out.print("Enter rate: ");
		rate = Double.parseDouble(scn.nextLine());

		return new Worker(name, salaryBasic, rate);
	}

	private void inputWorker() {
		listWorkers.add(input());
	}

	private void displayWorker() {
		if (listWorkers.isEmpty()) {
			System.out.println("workers list is empty, please add");
			return;
		}
		listWorkers.forEach(x -> x.display());
	}

	private int menu() {
		System.out.println("1. Input information Worker");
		System.out.println("2. Display Workers list");
		System.out.println("0. Exit");
		System.out.print("Your choice: ");
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
				inputWorker();
				break;
			case 2:
				displayWorker();
				break;
			default:
				return;
			}
		}
	}

}
