package demoabstract;

public class MotorBike extends Bike {

	@Override
	public void run() {
		speed = 60;

		System.out.println("MotorBike running " + speed + " km/h");
	}

}
