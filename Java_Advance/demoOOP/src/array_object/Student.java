package array_object;

import java.util.Scanner;

public class Student implements Comparable<Student> {
	@Override
	public int compareTo(Student o) {
//		return o.id - this.id;
		return this.name.compareTo(o.name);	// 1, 0, -1
	}
	// a , b
	
	//
	final static Scanner scn = new Scanner(System.in);
	int id;
	String name;

	public Student() {
		super();
	}

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	int getId() {
		return id;
	}

	String getName() {
		return name;
	}

	void setId(int id) {
		this.id = id;
	}

	void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

	public void input() {
		System.out.println("Enter id: ");
		id = Integer.parseInt(scn.nextLine());
		System.out.println("Enter name: ");
		name = scn.nextLine();

	}

	public void display() {
		System.out.println(id + " - " + name);
	}

	
}
