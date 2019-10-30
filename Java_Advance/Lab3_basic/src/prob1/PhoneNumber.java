package prob1;

public abstract class PhoneNumber {
	int area;
	String number;

	public PhoneNumber() {
		super();
	}

	public PhoneNumber(int area, String number) {
		super();
		this.area = area;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return this.area + " - " + this.number;
	}

	public abstract void display();
}
