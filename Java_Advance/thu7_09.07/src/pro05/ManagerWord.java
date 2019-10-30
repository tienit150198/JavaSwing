package pro05;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManagerWord {
	static Scanner scn = new Scanner(System.in);
	Map<String, String> words;

	public ManagerWord() {
		words = new HashMap<>();
	}

	public Map<String, String> getWords() {
		return this.words;
	}

	private int checkLimitInput(int _min, int _max) {
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

	private int menu() {
		System.out.println("1. Add new word");
		System.out.println("2. Delete word");
		System.out.println("3. Search word");
		System.out.println("4. Display all word");
		System.out.println("5. Exit");
		System.out.print("Your choice: ");
		int choice = checkLimitInput(1, 5);
		return choice;
	}

	public void run() {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				addWord();
				break;
			case 2:
				deleteWord();
				break;
			case 3:
				searchWord();
				break;
			case 4:
				printAllWord();
			case 5:
				return;
			}
		}
	}

	private void printAllWord() {
		if(words.isEmpty()) {
			System.out.println("words list is empty");
			return;
		}
		words.forEach((key, value) -> System.out.println(key + "\t" + value));
	}

	private void addWord() {
		String englishWord, vietnamWord;
		System.out.print("Enter English: ");
		englishWord = scn.nextLine();
		System.out.print("Enter Vietnamese: ");
		vietnamWord = scn.nextLine();

		words.put(englishWord.trim(), vietnamWord.trim());
	}

	private void deleteWord() {
		System.out.print("Enter English: ");
		String englishWord = scn.nextLine();
		words.remove(englishWord);
		System.out.println("Delete successful");
	}

	private void searchWord() {
		System.out.print("Enter English: ");
		String englishWord = scn.nextLine();
		if (words.containsKey(englishWord)) {
			System.out.println("Vietnamese: " + words.get(englishWord));
			return;
		}
		System.out.println("Not found in data");
	}

}
