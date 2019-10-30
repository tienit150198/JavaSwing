package pro04;

import java.util.Scanner;

public class InputVariable {
	public static class Singleton {
		public static final InputVariable instance = new InputVariable();
	}

	public static InputVariable getInstance() {
		return Singleton.instance;
	}

	private static final Scanner scn = new Scanner(System.in);

	public static float inputFloat() {
		return Float.parseFloat(scn.nextLine());
	}

	public static double inputDouble() {
		return Double.parseDouble(scn.nextLine());
	}

	public static int inputInteger() {
		return Integer.parseInt(scn.nextLine());
	}

	public static long inputLong() {
		return Long.parseLong(scn.nextLine());
	}

	public static String inputString() {
		return scn.nextLine();
	}
	
	public static char inputCharacter() {
		return scn.nextLine().charAt(0);
	}
}
