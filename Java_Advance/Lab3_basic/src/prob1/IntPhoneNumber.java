package prob1;

public class IntPhoneNumber extends PhoneNumber {
	String countryCode;

	public IntPhoneNumber() {
		super();
	}

	public IntPhoneNumber(int area, String number) {
		super(area, number);
	}

	public IntPhoneNumber(String countryCode, int area, String number) {
		super(area, number);
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return this.countryCode + " - " + super.toString();
	}

	@Override
	public void display() {
		System.out.println(countryCode.equals("") == true ? super.toString() : toString());
	}

}
