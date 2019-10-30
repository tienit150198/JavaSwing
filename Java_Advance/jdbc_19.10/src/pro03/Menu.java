package pro03;

public class Menu {
	public static int menu() {
		System.out.println("1. Admin Roles");
		System.out.println("2. Customer Roles");
		System.out.println("3. Exits");
		System.out.print("Enter your choice: ");
		int choose = Validate.checkLimitInput(1, 3);

		return choose;
	}

	public static int menuAdmin() {
		System.out.println("1. Add Items");
		System.out.println("2. Update Items");
		System.out.println("3. View Items");
		System.out.println("4. Remove Items");
		System.out.println("5. Exit");
		System.out.print("Enter your choice: ");
		int choose = Validate.checkLimitInput(1, 5);
		return choose;
	}

	public static int menuCustomer() {
		System.out.println("1. Add to Shopping Basket");
		System.out.println("2. Display the Shopping Basket");
		System.out.println("3. Remove from Shopping Basket");
		System.out.println("4. Print Invoice(Orders)");
		System.out.println("5. Exit");
		System.out.print("Enter your choice: ");
		int choose = Validate.checkLimitInput(1, 5);
		return choose;
	}

}
