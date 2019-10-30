package pro05;

import java.util.Scanner;

public class Doctor implements Comparable<Doctor> {
	int avaibility;
	String name, code, specialization;
	final Scanner scn = new Scanner(System.in);

	public Doctor() {
		super();
	}

	public Doctor(int avaibility, String name, String code, String specialization) {
		super();
		this.avaibility = avaibility;
		this.name = name;
		this.code = code;
		this.specialization = specialization;
	}

	public void display() {
		System.out.println(code + " - " + name + " - " + specialization + " - " + avaibility);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (avaibility != other.avaibility)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (scn == null) {
			if (other.scn != null)
				return false;
		} else if (!scn.equals(other.scn))
			return false;
		if (specialization == null) {
			if (other.specialization != null)
				return false;
		} else if (!specialization.equals(other.specialization))
			return false;
		return true;
	}

	int getAvaibility() {
		return avaibility;
	}

	String getCode() {
		return code;
	}

	String getName() {
		return name;
	}

	Scanner getScn() {
		return scn;
	}

	String getSpecialization() {
		return specialization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avaibility;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scn == null) ? 0 : scn.hashCode());
		result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
		return result;
	}

	public void input() {
		System.out.print("Enter Code: ");
		code = scn.nextLine();
		System.out.print("Enter name: ");
		name = scn.nextLine();
		System.out.print("Enter specialization: ");
		specialization = scn.nextLine();
		System.out.print("Enter avaibility: ");
		avaibility = scn.nextInt();

	}

	void setAvaibility(int avaibility) {
		this.avaibility = avaibility;
	}

	void setCode(String code) {
		this.code = code;
	}

	void setName(String name) {
		this.name = name;
	}

	void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", code=" + code + ", specialization=" + specialization + ", avaibility="
				+ avaibility + ", scn=" + scn + "]";
	}

	@Override
	public int compareTo(Doctor o) {
		return (this.name.compareTo(o.name));
	}

}
