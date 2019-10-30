package pro05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ManagerDoctor {
	static Scanner scn = new Scanner(System.in);
	List<Doctor> doctors;

	public ManagerDoctor() {
		doctors = new ArrayList<>();
		doctors.add(new Doctor(1, "Zeroes", "1", "Heart"));
		doctors.add(new Doctor(1, "Zero", "2", "Heart"));
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
		System.out.println("1. Add Doctor");
		System.out.println("2. Update Doctor");
		System.out.println("3. Delete Doctor");
		System.out.println("4. Search Doctor");
		System.out.println("5. Display Doctor List");
		System.out.println("6. Exit");
		System.out.print("Your choice: ");
		int choice = checkLimitInput(1, 6);
		return choice;
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				addDoctor();
				break;
			case 2:
				updateDoctor();
				break;
			case 3:
				deleteDoctor();
				break;
			case 4:
				searchDoctor();
				break;
			case 5:
				displayAll();
				break;
			case 6:
				return;
			}
		}
	}

	private void displayAll() {
		Collections.sort(doctors);
		System.out.println("----------------------");
		doctors.forEach(x -> x.display());
		System.out.println("----------------------");
	}

	private void deleteDoctor() {
		String code;
		System.out.print("Enter code: ");
		code = scn.nextLine();
		boolean check = false;
		for (Doctor doctor : doctors) {
			if (doctor.getCode().equals(code)) {
				Doctor doctorTmp = doctor;
				check = true;
				doctorTmp.input();

				doctors.remove(doctor);
				doctors.add(doctorTmp);
				System.out.println("delete doctor successfully!");
				break;
			}
		}
		if (!check) {
			System.out.println("Not found doctor");
		}

	}

	private void searchDoctor() {
		String name;
		System.out.println("Enter name: ");
		name = scn.nextLine();

		boolean check = false;
		for (Doctor doctor : doctors) {
			if (doctor.getName().equals(name)) {
				doctor.display();
				check = true;
			}
		}
		if (!check)
			System.out.println("Not found " + name);
		System.out.println("\n");
	}

	boolean checkAllInfo(List<Doctor> doctors, Doctor a) {
		for (Doctor doctor : doctors) {
			if (doctor.equals(a)) {
				return false;
			}
		}
		return true;
	}

	private void addDoctor() {
		Doctor doctor = new Doctor();
		doctor.input();

		if (!checkAllInfo(doctors, doctor)) {
			doctors.add(doctor);
			System.out.println("\nAdd successfully!\n");
		} else
			System.out.println("Doctor exists");
	}

	private void updateDoctor() {
		String code;
		System.out.print("Enter code: ");
		code = scn.nextLine();
		boolean check = false;
		for (Doctor doctor : doctors) {
			if (doctor.getCode().equals(code)) {
				Doctor doctorTmp;
				check = true;

				doctorTmp = changeDoctor(doctor.getCode());

				if (doctorTmp.equals(doctor)) {
					System.out.println("No change");
				} else {
					doctors.remove(doctor);
					doctors.add(doctorTmp);
					System.out.println("Update successfully!");
					break;
				}
			}
		}
		if (!check) {
			System.out.println("Not found doctor");
		}
	}

	private Doctor changeDoctor(String code) {
		String name, specialization;
		int avaibility;
		System.out.print("Enter name: ");
		name = scn.nextLine();
		System.out.print("Enter specialization: ");
		specialization = scn.nextLine();
		System.out.print("Enter avaibility: ");
		avaibility = scn.nextInt();

		return new Doctor(avaibility, name, code, specialization);
	}
}
