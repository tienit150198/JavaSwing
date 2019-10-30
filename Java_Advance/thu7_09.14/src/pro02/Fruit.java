package pro02;

import java.util.Scanner;

public class Fruit {
	int id, quantity;
	String name, origin;
	double price, amount;
	final Scanner scn = new Scanner(System.in);

	public Fruit() {
		super();
	}

	public Fruit(int id, String name, double price, String origin) {
		super();
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.price = price;
	}

	public Fruit(int id, int quantity, String name, String origin, double price, double amount) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.name = name;
		this.origin = origin;
		this.price = price;
		this.amount = amount;
	}

	public void display() {
		System.out.println(name + "\t\t" + quantity + "\t" + price + "\t\t" + amount);
	}

	public void display(int number) {
		System.out.println(number + ". " + name + "\t\t" + quantity + "\t" + price + "\t\t" + amount);
	}

	public void displayShop(int number) {
		System.out.println(number + "\t" + name + "\t\t" + origin + "\t" + price);
	}

	public double getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOrigin() {
		return origin;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void input() {
		System.out.print("Enter id: ");
		id = Integer.parseInt(scn.nextLine());
		System.out.print("Enter name: ");
		name = scn.nextLine();
		System.out.print("Enter price: ");
		price = Double.parseDouble(scn.nextLine());

//		System.out.print("Enter quantity: ");
//		quantity = Integer.parseInt(scn.nextLine());
		System.out.print("Enter origin: ");
		origin = scn.nextLine();

		System.out.println("\nAdd Fruit succesfully!\n");
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", quantity=" + quantity + ", name=" + name + ", origin=" + origin + ", price="
				+ price + ", amount=" + amount + "]";
	}

}
