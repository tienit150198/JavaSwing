package trackemployeeinterface;

import java.util.Scanner;

public class Organizations implements TrackingInt {
	final private Scanner scn = new Scanner(System.in);
	private String name;
	private int numberOfEmployee;

	public Organizations() {
		super();
		this.numberOfEmployee = 0;
	}

	public Organizations(String name) {
		super();
		this.name = name;
		this.numberOfEmployee = 0;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfEmployee() {
		return numberOfEmployee;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increaseEmployee() {
		this.numberOfEmployee++;
	}

	@Override
	public void input() {
		System.out.print("Enter organization name: ");
		name = scn.nextLine();

		scn.close();
	}

	@Override
	public void display() {
		System.out.println("Organization name: " + name);
		System.out.println("Number of Employees: " + numberOfEmployee);
	}

}
