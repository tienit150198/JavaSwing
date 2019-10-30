package pro03;

public class ManagerShop {
	Shop shop;

	public ManagerShop() {
		shop = new Shop("Zeroes");
	}

	private void addShoppingBasket() {
		Basket basket = shop.getCart();
		Store store = shop.getSt();
		store.display();

		System.out.print("Enter ID your choice: ");
		int choice = Validate.checkInputInt();

		int found = Validate.findIdFromStore(store.getListItem(), choice);
		if (found != -1) {
			System.out.print("Enter quantity: ");
			int iqtt = Validate.checkInputInt();

			ShopItem itemAdd = store.getListItem().get(found);
			try {
				boolean check = basket.addItem(itemAdd, iqtt);

				if (check) {
					System.out.println("Add item succesfully!!!");
				} else
					throw new Exception();

			} catch (Exception e) {
				System.out.println("Add item failed ");
				e.printStackTrace();
			}

		} else {
			System.out.println("id not found");
		}

	}

	private void displayShoppingBasket() {
		shop.getCart().displayItemBasket();
	}

	private void printInvoice() {
		System.out.println("--------------------------------------");

		System.out.println("Customer name: " + shop.getCustomerName());

		Basket basket = shop.getCart();
		System.out.println(
				"Book total weight: " + basket.getTotalWeightBook() + "g, total price : " + basket.getTotalPriceOfBook());
		System.out.println("Number of Software: " + basket.getNumberSofware() + ", total price : "
				+ basket.getTotalPriceOfSoftware());
		System.out.println("Total basket cost: " + basket.getTotalPrice());

		System.out.println("--------------------------------------");
	}

	private void removeShoppingBasket() {
		Basket basket = shop.getCart();
		basket.displayItemBasket();

		System.out.print("Enter ID your choice: ");
		int choice = Validate.checkInputInt();
		
		int found = Validate.findIdFromStore(basket.getSelectedItems(), choice);
		if (found != -1) {
			System.out.print("Enter number remove: ");
			int number = Validate.checkInputInt();
			
			ShopItem itemRemove = basket.getSelectedItems().get(found);
			boolean check = basket.removeItem(itemRemove, number);

			if (check) {
				System.out.println("remove item succesfully!!!");
			} else {
				System.out.println("remove item failed!!!");
			}

		} else {
			System.out.println("id not found");
		}
	}

	public void run() {
		while (true) {
			int choice = Menu.menu();
			if (choice == 1) {
				int choiceAdmin = Menu.menuAdmin();

				switch (choiceAdmin) {
				case 1:
					shop.getSt().addItems();
					break;
				case 2:
					shop.getSt().updateItems();
					break;
				case 3:
					shop.getSt().display();
					break;
				case 4:
					shop.getSt().removeItems();
					break;
				default:
					break;
				}
			}

			else if (choice == 2) {
				int choiceCustomer = Menu.menuCustomer();

				switch (choiceCustomer) {
				case 1:
					addShoppingBasket();
					break;
				case 2:
					displayShoppingBasket();
					break;
				case 3:
					removeShoppingBasket();
					break;
				case 4:
					printInvoice();
					break;

				default:
					break;
				}
			} else
				break;
			System.out.println();
		}
	}
}
