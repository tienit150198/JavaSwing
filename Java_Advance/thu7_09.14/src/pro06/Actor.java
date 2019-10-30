package pro06;

import java.util.ArrayList;
import java.util.Collections;

public class Actor implements Comparable<Actor> {
	String firstName, lastName;

	public Actor() {
		super();
	}

	public Actor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public int compareTo(Actor o) {
		return this.firstName.compareTo(o.firstName);
	}

	String getFirstName() {
		return firstName;
	}

	String getLastName() {
		return lastName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Actor [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public static void main(String[] args) {
		Actor actor4 = new Actor("F.D", "L.D");
		Actor actor3 = new Actor("F.C", "L.C");
		Actor actor1 = new Actor("F.A", "L.A");
		Actor actor2 = new Actor("F.B", "L.B");
		Actor actor5 = new Actor("F.E", "L.E");

		ArrayList<Actor> actors = new ArrayList<>();
		actors.add(actor1);
		actors.add(actor2);
		actors.add(actor3);
		actors.add(actor4);
		actors.add(actor5);

		Collections.sort(actors);

		actors.forEach(actor -> System.out.println(actor));
	}
}
