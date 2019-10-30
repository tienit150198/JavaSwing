package prob2;

import java.text.DecimalFormat;

public class Worker extends Person {
	double bSalary;
	double rate;

	public Worker() {
		super();
	}

	public Worker(String name) {
		super(name);
	}

	public Worker(String name, double bSalary, double rate) {
		super(name);
		this.bSalary = bSalary;
		this.rate = rate;
	}

	public Worker(double bSalary, double rate) {
		super();
		this.bSalary = bSalary;
		this.rate = rate;
	}

	@Override
	public void display() {
		System.out.println("-------------------------------------");
		System.out.println("Name = " + name);

		DecimalFormat df = new DecimalFormat("#.000");

		System.out.println("Salary = " + df.format(getSalary()));
	}

	@Override
	public double getSalary() {
		return (double) bSalary * rate;
	}

}
