package demosuper;

import java.util.Scanner;

public class DaGiac {
	static protected Scanner scn = new Scanner(System.in);
	protected int socanh;

	public DaGiac() {
		super();	// gọi tới constructor của lớp cha
	}

	public DaGiac(int socanh) {
		super();
		this.socanh = socanh;
	}

	public int getSocanh() {
		return socanh;
	}

	public void setSocanh(int socanh) {
		this.socanh = socanh;
	}

	public void input() {
		System.out.print("Nhập số cạnh: ");
		socanh = scn.nextInt();
	}

	public void display() {
		System.out.println("Số cạnh = " + socanh);
	}

}
