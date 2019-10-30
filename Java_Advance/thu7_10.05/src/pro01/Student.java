package pro01;

import java.util.Scanner;

import pro03.Validate;

public class Student {
	final Scanner scn = new Scanner(System.in);
	String maSv, hoten, xeploai;
	double diem;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String maSv, String hoten, String xeploai, double diem) {
		super();
		this.maSv = maSv;
		this.hoten = hoten;
		this.xeploai = xeploai;
		this.diem = diem;
	}

	String getMaSv() {
		return maSv;
	}

	void setMaSv(String maSv) {
		this.maSv = maSv;
	}

	String getHoten() {
		return hoten;
	}

	void setHoten(String hoten) {
		this.hoten = hoten;
	}

	String getXeploai() {
		return xeploai;
	}

	void setXeploai(String xeploai) {
		this.xeploai = xeploai;
	}

	double getDiem() {
		return diem;
	}

	void setDiem(double diem) {
		this.diem = diem;
	}

	@Override
	public String toString() {
		return "Student [maSv=" + maSv + ", hoten=" + hoten + ", xeploai=" + xeploai + ", diem=" + diem + "]";
	}

	public void input() {
		System.out.print("Nhập Mã sinh viên: ");
		maSv = Validate.checkInputString();
		System.out.print("Nhập Họ Tên: ");
		hoten = Validate.checkInputString();
		System.out.print("Nhập Điểm: ");
		diem = Validate.checkInputDouble();

		updateXeploai(diem);
	}

	public void display() {
		System.out.println(maSv + " - " + hoten + " - " + diem + " - " + xeploai);
	}

	private void updateXeploai(double diem2) {
		if (diem2 >= 8.5)
			xeploai = "Giỏi";
		else if (diem2 >= 7)
			xeploai = "Khá";
		else if (diem2 >= 5)
			xeploai = "Trung bình";
		else {
			xeploai = "Yếu";
		}
	}
}
