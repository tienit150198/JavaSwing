package pro01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ManagerStore {
	static Scanner scn = new Scanner(System.in);

	ArrayList<Order> listOrder;
	ArrayList<Product> listProduct;

	public ManagerStore() {
		listOrder = new ArrayList<>();
		listProduct = new ArrayList<>();

		listProduct.add(new Product(1, "A", 3));
		listProduct.add(new Product(2, "B", 7));
		listProduct.add(new Product(3, "C", 1));
		
		LoadOrderFromFile();
	}

	private void addProduct() {
		listProduct.add(newProduct(listProduct));
		System.out.println("Add product successfully!\n");
	}

	private void changePriceInID(int id, double price) {
		for (Product product : listProduct) {
			if (product.getId() == id) {
				product.setPrice(price);
				break;
			}
		}
	}

	private void createNewOrder() {
		listOrder.add(newOrder(listOrder));
		System.out.println("Add Order successfully!\n");
	}

	private void listAllProduct() {
		System.out.println("---------------------------");
		listProduct.forEach(x -> x.display());
		System.out.println("---------------------------");

	}

	private int menu() {
		System.out.println("1. Add a new product to the Store");
		System.out.println("2. Update price for a particular product");
		System.out.println("3. A list of all available products in the Store");
		System.out.println("4. Create a new Order");
		System.out.println("5. Print information of an Order by Order ID");
		System.out.println("6. Sort all products by product price as ascending");
		System.out.println("7. Print information of all Orders by a specific customer ID");
		System.out.println("8. Export information of a specific Order ID to text file");
		System.out.print("Your choice: ");
		int choice = Validate.checkLimitInput(1, 8);
		return choice;
	}

	private Order newOrder(ArrayList<Order> orders) {
		int id = Validate.autoIncreaseIDOrder(orders);
		LocalDate dateNow = LocalDate.now();

		String name, address;
		ArrayList<Product> productsOrder = new ArrayList<>();

		System.out.println("Enter name: ");
		name = Validate.checkInputString();
		System.out.println("Enter address: ");
		address = Validate.checkInputString();

		listAllProduct();
		char choiceYN = 'n';
		int choice;
		do {
			System.out.print("Enter product ID: ");
			choice = Validate.checkInputInt();

			productsOrder.add(listProduct.get(choice - 1));

			System.out.println("Do you want to continue shopping? (Y/N)");
			choiceYN = Validate.checkInputCharacter();

		} while (Validate.checkChooseYN(choiceYN));

		return new Order(id, name, address, dateNow, productsOrder);
	}

	private Product newProduct(ArrayList<Product> listProduct) {
		int id;
		double price;
		String name;

		id = Validate.autoIncreaseIDProduct(listProduct);
		System.out.println("Enter name: ");
		name = Validate.checkInputString();
		System.out.println("Enter price: ");
		price = Validate.checkInputDouble();

		return new Product(id, name, price);
	}

	private void printOrderByCustomerId() {
		System.out.println("---------------------------");
		listOrder.forEach(x -> x.display());
	}

	private void printOrderById() {
		System.out.println("---------------------------");
		int id;
		System.out.println("Enter id: ");
		id = Validate.checkInputInt();

		int found = Validate.findIdInOrder(listOrder, id);
		if (found == -1) {
			System.out.println("id not found");
			return;
		}

		listOrder.get(found).display();
		System.out.println("---------------------------");
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				addProduct();
				break;
			case 2:
				updatePrice();
				break;
			case 3:
				listAllProduct();
				break;
			case 4:
				createNewOrder();
				break;
			case 5:
				printOrderById();
				break;
			case 6:
				sortProductByPrice();
				break;
			case 7:
				printOrderByCustomerId();
				break;
			case 8:
				SaveToFile();
				break;

			}
		}
	}

	/*private void SaveToFile() {
		File file = new File("order.dat");

		if (file.exists()) {
			if (file.delete()) {
				System.out.println("File is exists, system deleted old file");
			}
		}

		try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))) {
			for (Order order : listOrder) {
				bfw.write("Date = " + order.getDate().toString());
				bfw.newLine();

				bfw.write("name = " + order.getName());
				bfw.newLine();

				bfw.write("id = " + order.getId());
				bfw.newLine();

				bfw.write("address = " + order.getAddress());
				bfw.newLine();

				bfw.write("list product shopping");
				bfw.newLine();
				for (Product product : listProduct) {
					bfw.write(product.getId() + " - " + product.getName() + " - " + product.getPrice());
					bfw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	private boolean writeObject(File file, Object o) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(o);
		}catch(IOException e) {
			return false;
		}
		return true;
	}
	
	public Object readObject(File file) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			return ois.readObject();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void SaveToFile() {
		File file = new File("order.dat");
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("File is exists, system delete old file");
			}
		}
		
		writeObject(file, listOrder);
		
	}
	
	@SuppressWarnings("unchecked")
	private void LoadOrderFromFile() {
		File file = new File("order.dat");
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("File doesn't exists, system create new file");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Object readObject = readObject(file);
		ArrayList<Order> orders = (ArrayList<Order>) readObject;
		
		if(orders != null)
			listOrder.addAll(orders);
	}

	private void sortProductByPrice() {
		Collections.sort(listProduct);
		System.out.println("Sort list Product succesfully!\n");
	}

	private void updatePrice() {
		int id;
		double price;
		System.out.println("Enter product ID: ");
		id = Validate.checkInputInt();
		System.out.println("Enter price: ");
		price = Validate.checkInputDouble();

		int foundID = Validate.findIdInProduct(listProduct, id);
		if (foundID == -1) {
			System.out.println("ID not found!");
			return;
		}

		changePriceInID(id, price);
		System.out.println("Update successfully!");

	}

}
