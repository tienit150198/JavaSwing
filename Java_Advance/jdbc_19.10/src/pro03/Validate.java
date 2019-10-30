package pro03;

import java.util.ArrayList;
import java.util.Scanner;

public class Validate {
	private final static Scanner scn = new Scanner(System.in);

	public static boolean checkChooseYN(char x) {
		return (x == 'y' || x == 'Y');
	}

	public static Character checkInputCharacter() {
		String res;
		while (true) {
			try {
				res = scn.nextLine().trim();

				if (res.isEmpty() || res.length() > 1) {
					throw new Exception();
				}

				return res.charAt(0);
			} catch (Exception e) {
				System.err.println("Please re-enter character");
			}
		}
	}

	public static Double checkInputDouble() {
		Double res;
		while (true) {
			try {
				res = Double.parseDouble(scn.nextLine().trim());
				return res;
			} catch (Exception e) {
				System.err.println("Please reinput double");
			}
		}
	}

	public static int checkInputInt() {
		int res;
		while (true) {
			try {
				res = Integer.parseInt(scn.nextLine().trim());
				return res;
			} catch (Exception e) {
				System.err.println("Please reinput integer");
			}
		}
	}

	public static String checkInputString() {
		String res;
		while (true) {
			res = scn.nextLine();
			if (res.trim().equals("")) {
				System.err.println("Please enter string not empty");
			} else
				break;
		}
		return res;
	}

	public static int checkLimitInput(int _min, int _max) {
		int choice;

		while (true) {
			try {
				choice = Integer.parseInt(scn.nextLine());
				if (choice < _min || choice > _max) {
					throw new NumberFormatException();
				}
				return choice;
			} catch (NumberFormatException e) {
				System.err.println("range received from " + _min + " to " + _max);
			}
		}
	}
	
	public static int findIdFromStore(ArrayList<ShopItem> shopItem, int id) {
		
		for(int i = 0 ; i < shopItem.size() ; i++) {
			if(shopItem.get(i).getId() == id) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int getIdMaxFromStore(ArrayList<ShopItem> shopItem) {
		int id = 0;
		for (int i = 0; i < shopItem.size(); i++) {
			id = Math.max(id, shopItem.get(i).getId());
		}
		return id + 1;
	}

}
