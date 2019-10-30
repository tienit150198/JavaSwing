package pro02;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ManagerShop {
	static Scanner scn = new Scanner(System.in);
	List<Fruit> listFruits;
	Hashtable<String, List<Fruit>> orders;

	public ManagerShop() {
		orders = new Hashtable<>();
		listFruits = new FileService().readFruit("files/fruit.dat");
	}

	private void addFruit() {
		Fruit fruit = new Fruit();
		fruit.input();
		listFruits.add(fruit);
	}

	private void addFruit(String customer, List<Fruit> listFruitTmp) {
		if (orders.containsKey(customer)) {
			listFruitTmp.addAll(orders.get(customer));
		}

		orders.put(customer, listFruitTmp);

		System.out.println("\nAdd Fruit succesfully!\n");
	}

	private void addFruitIntoOrders(List<Fruit> listFruitTmp) {
		System.out.println("-------------------------");
		System.out.println("Produtct\tQuantity\tPrice\tAmount");
		listFruitTmp.forEach(fruit -> fruit.display());
		System.out.println("-------------------------");

		System.out.print("Enter your name: ");
		String name = scn.nextLine();


		addFruit(name, listFruitTmp);
	}

	private boolean checkChooseYN(char x) {
		return (x == 'y' || x == 'Y');
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

	private void displayOrders(String customerName, List<Fruit> fruits) {
		double total = 0;
		System.out.println("-------------------------");
		System.out.println("Customer: " + customerName);
		System.out.println("Produtct\tQuantity\tPrice\tAmount");
		int number = 0;

		for (Fruit fruit : fruits) {
			fruit.display(++number);

			total += fruit.getAmount();
		}

		System.out.println("Total: " + total + "\n");
	}

	private Fruit getFruit(int choice, int quantity) {
		Fruit fruit = listFruits.get(choice);
		fruit.setQuantity(quantity);
		fruit.setAmount(quantity * fruit.getPrice());
//		System.out.println("fruit = " + fruit);
		return fruit;
	}

	public List<Fruit> getListFruits() {
		return this.listFruits;
	}

	private Fruit inputFruitInShopping() {
		int choice, quantity;
		System.out.print("You choice: ");
		choice = checkLimitInput(1, listFruits.size());

		choice--;

		System.out.println("You selected: " + listFruits.get(choice).getName());

		System.out.print("Please input quantity: ");
		quantity = Integer.parseInt(scn.nextLine());

		Fruit fruit = getFruit(choice, quantity);

		return fruit;

	}

	private int menu() {
		System.out.println("1. Create Fruit");
		System.out.println("2. View Orders");
		System.out.println("3. Shopping");
		System.out.println("4. Exit");
		System.out.print("Your choice: ");
		int choice = checkLimitInput(1, 4);
		return choice;
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				addFruit();
				break;
			case 2:
				viewOrder();
				break;
			case 3:
				shopping();
				break;
			case 4:
				return;
			}
		}
	}

	private void shopping() {
		int number = 0;
		System.out.println("-------------------------");
		System.out.println("Item\tFruit Name\tOrigin\tPrice");

		for (Fruit fruit : listFruits) {
			fruit.displayShop(++number);
		}

		char x = 'n';

		List<Fruit> listShoppingTmp = new LinkedList<>();
		do {
			listShoppingTmp.add(inputFruitInShopping());
			System.out.print("Do you want to order now (Y/N): ");
			x = scn.nextLine().charAt(0);
		} while (checkChooseYN(x));

		addFruitIntoOrders(listShoppingTmp);
	}

	private void viewOrder() {
		if (orders.isEmpty()) {
			System.out.println("\nlist orders is empty\n");
		}
		orders.forEach((customer, fruits) -> {
			displayOrders(customer, fruits);
		});
	}

}
