
package array_object;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		ManagerStudent managerStudent = new ManagerStudent();

		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student(1,"a"));
		students.add(new Student(2,"d"));
		students.add(new Student(3,"b"));

		managerStudent.displayAllStudent(students);
		
		System.out.println("------------------------- sorted name -------------");
		Collections.sort(students);
		managerStudent.displayAllStudent(students);
	
	}
}
