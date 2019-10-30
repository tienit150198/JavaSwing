package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Exercise3 {
	static Scanner scn = new Scanner(System.in);
	static String textReadFromFile;
	static String pathReadFile;

	public Exercise3() {
		textReadFromFile = new String();
	}

	public String readFile(String path) {
		File file = new File(path);

		if (!file.exists()) {
			System.err.println("File doesn't exists, please create file");
			return null;
		}

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String readLine;

			while ((readLine = bufferedReader.readLine()) != null) {
				textReadFromFile += readLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textReadFromFile;
	}

	public void display(String path) {
		System.out.println(readFile(path));
	}

	public String inputPath() {
		System.out.println("Enter path: ");
		String path = scn.nextLine();

		return path;
	}

	public static void main(String[] args) {
		Exercise3 exercise3 = new Exercise3();

		while (true) {
			pathReadFile = exercise3.inputPath();
			exercise3.display(pathReadFile);
		}
	}
}
