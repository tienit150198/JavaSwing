package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exercise1 {
	static String textReadFromFile;
	static String pathReadFile, pathWriteFile;

	public Exercise1() {
		textReadFromFile = new String();
		pathReadFile = "files/Encrypt.txt";
		pathWriteFile = "files/outEncrypt.txt";
	}

	public boolean readFile(String path) {
		File file = new File(path);

		if (!file.exists()) {
			System.err.println("File doesn't exists, please create file");
			return false;
		}

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String readLine;

			while ((readLine = bufferedReader.readLine()) != null) {
				textReadFromFile += readLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean createFile(String path, String textWrite) {
		File fileCreate = new File(path);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileCreate))) {
			fileCreate.createNewFile();
			bufferedWriter.write(textWrite);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public String change(String textChange) {
		String result = "";
		for (int i = 0; i < textChange.length(); i++) {
			result += (char) (textChange.charAt(i) + 3);
		}
		return result;
	}

	public static void main(String[] args) {
		Exercise1 exercise1 = new Exercise1();
		if (exercise1.readFile(pathReadFile)) {
			System.out.println("read file successfully");
		}

		if (exercise1.createFile(pathWriteFile, exercise1.change(textReadFromFile))) {
			System.out.println("write file successfully");
		}

	}
}
