package pro02;

import java.time.LocalDate;

public class Customer {
	LocalDate date;
	private String name, issue;
	boolean status;
	public Customer() {
		super();
	}
	public Customer(LocalDate date, String name, String issue, boolean status) {
		super();
		this.date = date;
		this.name = name;
		this.issue = issue;
		this.status = status;
	}
	LocalDate getDate() {
		return date;
	}
	String getIssue() {
		return issue;
	}
	String getName() {
		return name;
	}
	boolean isStatus() {
		return status;
	}
	void setDate(LocalDate date) {
		this.date = date;
	}
	void setIssue(String issue) {
		this.issue = issue;
	}
	void setName(String name) {
		this.name = name;
	}
	void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Customer [date=" + date + ", name=" + name + ", issue=" + issue + ", status=" + status + "]";
	}
	
	public void display() {
		System.out.println(date.toString() + " - " + name + " - " + issue + " - " + (status == true ? "" : "un") + "slove");
	}
}
