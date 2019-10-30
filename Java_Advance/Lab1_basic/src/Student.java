public class Student extends Person {
	private int mark;
	private String grade;

	public Student() {
		super();
	}

	public Student(String id, String name, int age) {
		super(id, name, age);
	}

	public Student(String id, String name, int age, int mark, String grade) {
		super(id, name, age);
		this.mark = mark;
		this.grade = grade;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void calGrade() {
		if (mark >= 8.5)
			grade = "A";
		else if (mark >= 7.0)
			grade = "B";
		else if (mark >= 6.0)
			grade = "C";
		else if (mark >= 4.0)
			grade = "D";
		else
			grade = "Rớt";
	}

	@Override
	public void input() {
		System.out.println("Nhập ID sinh viên: ");
		id = scn.nextLine();
		System.out.println("Nhập tên sinh viên: ");
		name = scn.nextLine();
		System.out.println("Nhập tuổi sinh viên: ");
		age = Integer.parseInt(scn.nextLine());
	}

	@Override
	public void display() {
		System.out.println("Id sinh viên: " + id);
		System.out.println("Tên sinh viên: " + name);
		System.out.println("Tuổi sinh viên: " + age);
	}

	private int menu() {
		System.out.println("1. Nhập thông tin sinh viên");
		System.out.println("2. Hiển thị thông tin sinh viên");
		System.out.println("0. Thoát");
		System.out.print("Lựa chọn: ");
		int choice = checkLimitInput(0, 2);
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
				System.err.println("phạm vi trong khoảng " + _min + " đến " + _max);
				System.out.print("Nhập lại: ");
			}
		}
	}

	public static void main(String[] args) {
		Student student = new Student();
		while (true) {
			int choice = student.menu();

			switch (choice) {
			case 1:
				student.input();
				break;
			case 2:
				student.display();
				break;
			case 0:
				return;
			}
		}
	}
}
