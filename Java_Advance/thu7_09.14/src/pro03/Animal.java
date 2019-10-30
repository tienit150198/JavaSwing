package pro03;

public class Animal {
	int leg;
	String name;
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Animal(String name, int leg) {
		super();
		this.name = name;
		this.leg = leg;
	}
	int getLeg() {
		return leg;
	}
	String getName() {
		return name;
	}
	void setLeg(int leg) {
		this.leg = leg;
	}
	void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Animal [name=" + name + ", leg=" + leg + "]";
	}
	
}
