package array_object;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerStudent {
	final static Scanner scn = new Scanner(System.in);
	
	public void displayAllStudent(ArrayList<Student> students) {
		students.forEach(student -> student.display());
	}
	// khi nào cần dùng dấu {}, khi nào không cần

	public void inputAllStudent(ArrayList<Student> students) {
		char x = 'n'; // nhập đến khi người ta bấm chữ n thì thôi

		do {
			Student student = new Student();
			student.input();

			students.add(student);

		} while (isCheckYN(x));
		// do..while khác while cái gì?
		// không cho phép sài: break, continue
		// các biến cục bộ không cho phép được sử dụng trong hàm ( ngoài lamda )
	}

	private static boolean isCheckYN(char x) {
		System.out.println("you want input student?(Y/N)");
		x = scn.nextLine().charAt(0);
		return (x == 'y' || x == 'Y');
	}
}
