package pro04;

public class GenericsBoundTypeDemo<T extends Number> {
	private T number;	// số , chữ , true/false,...

	public GenericsBoundTypeDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericsBoundTypeDemo(T number) {
		super();
		this.number = number;
	}

	T getNumber() {
		return number;
	}

	void setNumber(T number) {
		this.number = number;
	}
	
	public double nghichDao() {
		return 1/number.doubleValue();
	}
	
	public double phanThapPhan() {
		return number.doubleValue() - number.intValue();
	}

}
