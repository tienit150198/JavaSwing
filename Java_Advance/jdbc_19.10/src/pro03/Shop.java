package pro03;

public class Shop {
	Basket cart;
	String customerName;
	Store st;

	public Shop() {
		super();
		cart = new Basket();
		st = new Store();
	}
	
	public Shop(String customerName) {
		super();
		cart = new Basket();
		st = new Store();
		this.customerName = customerName;
	}

	public Shop(String customerName, Store st, Basket cart) {
		super();
		this.customerName = customerName;
		this.st = st;
		this.cart = cart;
	}

	Basket getCart() {
		return cart;
	}

	String getCustomerName() {
		return customerName;
	}

	Store getSt() {
		return st;
	}

	void setCart(Basket cart) {
		this.cart = cart;
	}

	void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	void setSt(Store st) {
		this.st = st;
	}

	@Override
	public String toString() {
		return "Shop [customerName=" + customerName + ", st=" + st + ", cart=" + cart + "]";
	}


}
