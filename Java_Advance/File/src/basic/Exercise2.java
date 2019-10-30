package basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise2 {
	static Scanner scn = new Scanner(System.in);

	public class Student {
		String name, studyClass;
		int age;

		public Student(String name, String studyClass, int age) {
			this.name = name;
			this.studyClass = studyClass;
			this.age = age;
		}

		public Student() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStudyClass() {
			return studyClass;
		}

		public void setStudyClass(String studyClass) {
			this.studyClass = studyClass;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String toString() {
			return name + "->" + studyClass + "->" + age;
		}

		public void input() {
			System.out.println("Enter name: ");
			name = scn.nextLine();
			System.out.println("Enter class: ");
			studyClass = scn.nextLine();
			System.out.println("Enter age: ");
			age = Integer.parseInt(scn.nextLine());
		}

		public void display() {
			System.out.println(name + " - " + studyClass + " - " + age);
		}

	}

	static String textReadFromFile;
	static String pathLoadFile, pathAddFile;
	List<Student> students;

	public Exercise2() {
		students = new ArrayList<>();
		pathLoadFile = "files/StudentLoad.txt";
		pathAddFile = "files/Student.txt";
	}

	public List<Student> readFile(String path) {
		File file = new File(path);
		List<Student> studentList = new ArrayList<>();
		if (!file.exists()) {
			System.err.println("File doesn't exists, please create file");
			return null;
		}
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String readLine;

			while ((readLine = bufferedReader.readLine()) != null) {
				if (readLine.startsWith("//"))
					continue;
				String split[] = readLine.split("->");

				studentList.add(new Student(split[0].trim(), split[1].trim(), Integer.parseInt(split[2].trim())));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return studentList;
	}

	public boolean addDataToFile(String path, String textWrite) {
		File file = new File(path);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
			if (!file.exists()) {
				System.err.println("File doesn't exists, we created new file");
				file.createNewFile();
			}
			bufferedWriter.newLine();
			bufferedWriter.write(textWrite);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public int menu() {
		System.out.println("1. Add a list of Students and save to File");
		System.out.println("2. Loading list of Students from a File");
		System.out.println("3. Exit");
		System.out.print("You choice: ");

		int choice = checkLimitInput(1, 3);
		return choice;
	}

	private int checkLimitInput(int _min, int _max) {
		int choice;

		while (true) {
			try {
				choice = Integer.parseInt(scn.nextLine());
				if (choice < _min || choice > _max) {
					throw new NumberFormatException();
				}
				return choice;
			} catch (NumberFormatException e) {
				System.err.println("range received from " + _min + " to " + _max);
			}
		}
	}

	public void addStudent() {
		Student student = new Student();
		student.input();

		addDataToFile(pathAddFile, student.toString());
	}

	public void loadStudents() {
		students = readFile(pathLoadFile);

		students.forEach(x -> x.display());
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				addStudent();
				break;
			case 2:
				loadStudents();
				break;
			default:
				return;
			}
		}
	}

	public static void main(String[] args) {
		Exercise2 exercise2 = new Exercise2();
		exercise2.run();
	}
}
