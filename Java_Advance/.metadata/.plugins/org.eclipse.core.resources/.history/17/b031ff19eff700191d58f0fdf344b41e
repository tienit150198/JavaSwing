package pro03;

public abstract class ShopItem implements Comparable<ShopItem> {
	int id;
	String iName;
	double iPrice;
	int iQttt;

	public ShopItem() {
		super();
	}

	public ShopItem(int id, String iName, double iPrice, int iQttt) {
		super();
		this.id = id;
		this.iName = iName;
		this.iPrice = iPrice;
		this.iQttt = iQttt;
	}

	@Override
	public int compareTo(ShopItem o) {
		return this.id - o.id;
	}

	int getId() {
		return id;
	}

	String getiName() {
		return iName;
	}

	double getiPrice() {
		return iPrice;
	}

	int getiQttt() {
		return iQttt;
	}

	void setId(int id) {
		this.id = id;
	}

	void setiName(String iName) {
		this.iName = iName;
	}

	void setiPrice(double iPrice) {
		this.iPrice = iPrice;
	}

	void setiQttt(int iQttt) {
		this.iQttt = iQttt;
	}

	@Override
	public String toString() {
		return "ShopItem [id=" + id + ", iName=" + iName + ", iPrice=" + iPrice + ", iQttt=" + iQttt + "]";
	}
	
	public abstract void display();
	public abstract double getPriceShip();
	protected abstract Object clone();
}
