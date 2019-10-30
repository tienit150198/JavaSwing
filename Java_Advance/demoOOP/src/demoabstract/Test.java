package demoabstract;

public class Test {
	public static void main(String[] args) {
		Bicycle bicycle = new Bicycle();
		MotorBike motorBike = new MotorBike();
		
		bicycle.run();
		motorBike.run();
	}
}
