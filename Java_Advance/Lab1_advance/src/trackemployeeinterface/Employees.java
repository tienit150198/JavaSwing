package trackemployeeinterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Employees implements TrackingInt {
	final private Scanner scn = new Scanner(System.in);
	private String name, jobTitle, organization, sex;
	private LocalDate birthday;

	public Employees() {
		super();
	}

	public Employees(String name, String jobTitle, String organization, String sex, LocalDate birthday) {
		super();
		this.name = name;
		this.jobTitle = jobTitle;
		this.organization = organization;
		this.sex = sex;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employees other = (Employees) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}

	@Override
	public void input() {
		System.out.print("enter name: ");
		name = scn.nextLine();
		System.out.print("enter sex: ");
		sex = checkValidSex();

		System.out.print("enter birthday (dd/MM/yyyy): ");
		birthday = this.checkValidBirthday();

		System.out.print("enter job title: ");
		jobTitle = scn.nextLine();
	}

	public String checkValidSex() {
		String userInput;
		while (true) {
			try {
				userInput = scn.nextLine().trim().toLowerCase();
				if (!userInput.toLowerCase().equals("nam") && !userInput.toLowerCase().equals("nữ")) {
					throw new Exception();
				}
				return userInput;
			} catch (Exception e) {
				System.err.println("gender is invalid, please enter again");
				System.out.println("Enter: ");
			}
		}
	}

	public LocalDate checkValidBirthday() { // DateTimeParseException
		String userInput;
		while (true) {
			try {

				userInput = scn.nextLine();
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(userInput, dateTimeFormatter);

				return date;
			} catch (DateTimeParseException e) {
				System.err.println("enter the birthday error, please enter again");
				System.out.println("Enter: ");
			}
		}

	}

	@Override
	public void display() {
		System.out.println("-----------------------------------");
		System.out.println("Name's employee: " + name);
		System.out.println("Sex's employee: " + sex);
		System.out.println("Birthday's employee: " + birthday);
		System.out.println("Job title of employee: " + jobTitle);
		System.out.println("Oranization's employee: " + organization);

		System.out.println();
	}

	public static void main(String[] args) {
		Employees ep = new Employees();

		ep.input();
		ep.display();
	}
}
