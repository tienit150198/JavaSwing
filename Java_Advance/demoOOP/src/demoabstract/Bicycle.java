package demoabstract;

public class Bicycle extends Bike {
	
	@Override
	public void run() {
		speed = 20;
		
		System.out.println("Bicycle running " + speed + " km/h");
	}
	
}
