package pro01;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private int id;
	private ArrayList<Product> listProduct;
	private String name, address;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int id, String name, String address, LocalDate date, ArrayList<Product> listProduct) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
		this.listProduct = listProduct;
	}

	String getAddress() {
		return address;
	}

	LocalDate getDate() {
		return date;
	}

	int getId() {
		return id;
	}

	ArrayList<Product> getListProduct() {
		return listProduct;
	}

	String getName() {
		return name;
	}

	void setAddress(String address) {
		this.address = address;
	}

	void setDate(LocalDate date) {
		this.date = date;
	}
	
	void setId(int id) {
		this.id = id;
	}

	void setListProduct(ArrayList<Product> listProduct) {
		this.listProduct = listProduct;
	}

	void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", address=" + address + ", date=" + date + ", listProduct="
				+ listProduct + "]";
	}

	public void display() {
		System.out.println(date.toString());
		System.out.println(name);
		System.out.println(id + " - " + address);

		System.out.println("List shopping: ");
		if (listProduct.isEmpty()) {
			System.out.println("0 product");
		}
		listProduct.forEach(x -> x.display());
		System.out.println("---");
	}
}
