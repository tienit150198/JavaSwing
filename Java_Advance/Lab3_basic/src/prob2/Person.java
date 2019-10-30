package prob2;

public abstract class Person {
	protected String name;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "name = " + name + " ";
	}

	public abstract void display();

	public abstract double getSalary();
}
