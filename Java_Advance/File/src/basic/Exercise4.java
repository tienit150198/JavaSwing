package basic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Exercise4 {
	static Scanner scn = new Scanner(System.in);
	static String pathSrc, pathDest;
	
	public boolean copyFile(String pathSrc, String pathDest) {
		File fileSource = new File(pathSrc);
		File fileDest = new File(pathDest);

		if (!fileSource.exists()) {
			System.out.println("file src isn't exists");
			return false;
		}
		try {
			if (!fileDest.exists())
				fileDest.createNewFile();

			Files.copy(fileSource.toPath(), fileDest.toPath(),StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void inputPath() {
		System.out.println("Enter source file: ");
		pathSrc = scn.nextLine();
		System.out.println("Enter destination file: ");
		pathDest = scn.nextLine();

		if (copyFile(pathSrc, pathDest))
			System.out.println("copy file from source to destination successfully");
	}

	public static void main(String[] args) {
		Exercise4 exercise4 = new Exercise4();
		
		while(true) {
			exercise4.inputPath();
		}
	}
}
