package pro04;

public class Main {
	public static void main(String[] args) {
		GenericsBoundTypeDemo<Double> instance = new GenericsBoundTypeDemo<Double>(3.0);
		System.out.println("Phan Thap Phan = " + instance.phanThapPhan());
	}
}
