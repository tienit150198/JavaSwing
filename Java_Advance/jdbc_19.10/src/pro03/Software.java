package pro03;

public class Software extends ShopItem {
	int noCD;

	public Software() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Software(int noCD) {
		super();
		this.noCD = noCD;
	}

	public Software(int noCD, int id, String iName, double iPrice, int iQttt) {
		super(id, iName, iPrice, iQttt);
		this.noCD = noCD;
	}

	public Software(int id, String iName, double iPrice, int iQttt) {
		super(id, iName, iPrice, iQttt);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object clone() {
		return new Software(this.noCD, this.id, this.iName, this.iPrice, this.iQttt);
	}

	@Override
	public void display() {
		System.out.println("ID: " + id + " - " + "Name: " + iName + " - " + "Price: " + iPrice + " - " + "No CD: "
				+ noCD + " - " + "Number: " + iQttt);
	}

	int getNoCD() {
		return noCD;
	}

	@Override
	public double getPriceShip() {
		double total = 0;
		
		int numOfNoCD = this.noCD;
		int _min = Math.min(this.noCD, 3);
		numOfNoCD -= Math.max(numOfNoCD - _min, 0);
		
		total += _min*(3.25);
		total += (numOfNoCD*1.5);
		
		return total;
	}

	void setNoCD(int noCD) {
		this.noCD = noCD;
	}
	
	@Override
	public String toString() {
		return "Sofware [noCD=" + noCD + "]";
	}
}
