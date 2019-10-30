package pro03;

public class Fraction {
	int tu, mau;

	public Fraction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fraction(int tu, int mau) {
		super();
		this.tu = tu;
		this.mau = mau;
	}

	@Override
	public String toString() {
		return "Fraction [tu=" + tu + ", mau=" + mau + "]";
	}

	int getTu() {
		return tu;
	}

	void setTu(int tu) {
		this.tu = tu;
	}

	int getMau() {
		return mau;
	}

	void setMau(int mau) {
		this.mau = mau;
	}

	public void input() {
		System.out.print("Nhập tử số:");
		tu = Validate.checkInputInteger();
		System.out.print("Nhập mẫu số:");
		mau = Validate.checkMauSo();
	}

	public void display() {
		System.out.println(tu + "/" + mau);
	}

	public double chia() {
		return (tu / (1.0 * mau));
	}
}
