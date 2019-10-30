package generics;

import java.util.ArrayList;

public class demo {
	public static void main(String[] args) {
		ArrayList<Double> arrDb = new ArrayList<>();
		arrDb.add(1.1);
		arrDb.add(2.2);
		arrDb.add(3.3);
		
		ArrayList<String> arrSt = new ArrayList<>();
		arrSt.add("a");
		arrSt.add("b");
		arrSt.add("c");
		
		displayArray(arrDb);
		displayArray(arrSt);
	}
	
	public static void displayArray(ArrayList<?> arr) {	
		for (Object object : arr) {
			System.out.println(object);
		}
	}
}
