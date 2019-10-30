import java.util.Scanner;

public class Person implements PersonInt {
	protected static Scanner scn = new Scanner(System.in);
	protected String id, name;
	protected int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

	@Override
	public void input() {
		System.out.println("Nhập id: ");
		id = scn.nextLine();
		System.out.println("Nhập tên: ");
		name = scn.nextLine();
		System.out.println("Nhập tuổi: ");
		age = Integer.parseInt(scn.nextLine());
	}

	@Override
	public void display() {
		System.out.println(id + " - " + name + " - " + age);
	}

}
