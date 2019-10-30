package pro03;

public class Book extends ShopItem {
	double weight;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(double weight) {
		super();
		this.weight = weight;
	}

	public Book(double weight, int id, String iName, double iPrice, int iQttt) {
		super(id, iName, iPrice, iQttt);
		this.weight = weight;
	}

	public Book(int id, String iName, double iPrice, int iQttt) {
		super(id, iName, iPrice, iQttt);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object clone() {
		return new Book(this.weight, this.id, this.iName, this.iPrice, this.iQttt);
	}

	@Override
	public void display() {
		System.out.println("ID: " + id + " - " + "Name: " + iName + " - " + "Price: " + iPrice + " - " + "Weight: "
				+ weight + " - " + "Number: " + iQttt);
	}

	@Override
	public double getPriceShip() {
		// TODO Auto-generated method stub
		return 0;
	}

	double getWeight() {
		return weight;
	}
	
	

}
