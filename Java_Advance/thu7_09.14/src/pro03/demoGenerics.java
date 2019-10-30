package pro03;

import java.util.ArrayList;

public class demoGenerics {
	public static void main(String[] args) {
		ArrayList<Animal> animals = new ArrayList<>();
		
		animals.add(new Animal("dog", 4));
		animals.add(new Animal("cat", 4));
		animals.add(new Animal("Tiger", 4));
		animals.add(new Animal("Human", 2));
		
		System.out.println(animals);
		
		ArrayList<Double> doubles = new ArrayList<>();
		doubles.add(1.1);
		doubles.add(2.2);
		doubles.add(3.3);
		doubles.add(4.4);
	
		System.out.println(doubles);
	}
}
