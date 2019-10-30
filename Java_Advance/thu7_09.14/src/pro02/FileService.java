package pro02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileService {
	public List<Fruit> readFruit(String path) {
		File file = new File(path);
		List<Fruit> fruits = new LinkedList<>();
		if (!file.exists()) {
			System.out.println("File doesn't exists, please create file");
			return null;
		}

		try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
			String readLine;
			
			while((readLine = bfr.readLine()) != null) {
				String split[] = readLine.split("->");
				
				Fruit fruit = new Fruit(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]), split[3]);
				fruits.add(fruit);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fruits;
	}
}
