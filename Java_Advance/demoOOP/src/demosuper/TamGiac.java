package demosuper;

public class TamGiac extends DaGiac {
	double goc;

	public TamGiac() {
		super();
	}

	public TamGiac(int socanh) {
		super(socanh);
	}
	
	public TamGiac(double goc) {
		super();
		this.goc = goc;
	}
	
	public TamGiac(int socanh, double goc) {
		this(socanh);
		this.goc = goc;
	}

	@Override
	public void input() {
		super.input();
		System.out.print("Nhập số góc: ");
		goc = scn.nextInt();
	}

	@Override
	public void display() {
		super.display();
		System.out.println("Góc = " + goc);
	}

	public static void main(String[] args) {
		TamGiac tamGiac = new TamGiac();
		
		tamGiac.input();
		tamGiac.display();
		
	}
}
