package pro03;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {
	static Scanner scn = new Scanner(System.in);
	ArrayList<ShopItem> ls;

	public Store() {
		ls = new ArrayList<>();
		ls.add(new Book(1, 1, "Conan", 10.3, 10));
		ls.add(new Book(2, 2, "Doreamon", 1.2, 10));
		ls.add(new Software(7, 3, "My Tam", 7.8, 10));
		ls.add(new Book(3, 4, "Tom", 5.5, 10));
		ls.add(new Software(5, 5, "Snow", 6, 10));
	};

	public void addItems() {
		System.out.println("Enter type ( 1: sofware, 0: book):");
		int type = Validate.checkLimitInput(0, 1);

		ls.add(makeShopItem(type));
		System.out.println("add item successfully!!!");
	}

	public void display() {
		ls.forEach(x -> x.display());
	}

	ArrayList<ShopItem> getListItem() {
		return ls;
	}

	private ShopItem makeShopItem(int type) {
		ShopItem shopItem;

		int id = Validate.getIdMaxFromStore(ls);
		System.out.print("Enter Name: ");
		String iName = Validate.checkInputString();
		System.out.print("Enter price: ");
		double iPrice = Validate.checkInputDouble();
		System.out.print("Enter number: ");
		int iQttt = Validate.checkInputInt();

		if (type == 1) {
			System.out.print("Enter noCD: ");
			int noCD = Validate.checkInputInt();

			shopItem = new Software(noCD, id, iName, iPrice, iQttt);
		} else {
			System.out.print("Enter weight: ");
			int weight = Validate.checkInputInt();

			shopItem = new Book(weight, id, iName, iPrice, iQttt);
		}

		return shopItem;
	}

	public void removeItems() {
		display();

		System.out.println("Enter id your choice : ");
		int id = Validate.checkInputInt();

		int found = Validate.findIdFromStore(ls, id);
		if (found != -1) {
			ls.remove(found);
			System.out.println("remove item successfully!!!");
		} else {
			System.out.println("ID not found");
		}

	}

	public void updateItems() {
		display();

		System.out.println("Enter id your choice : ");
		int id = Validate.checkInputInt();

		int found = Validate.findIdFromStore(ls, id);
		if (found != -1) {
			ShopItem itemUpdate = ls.get(found);

			if (itemUpdate instanceof Book) {
				itemUpdate = makeShopItem(0);
			} else {
				itemUpdate = makeShopItem(1);
			}

			ls.set(found, itemUpdate);
			System.out.println("update item successfully!!!");
		} else {

			System.out.println("ID not found");
		}

	}

}
