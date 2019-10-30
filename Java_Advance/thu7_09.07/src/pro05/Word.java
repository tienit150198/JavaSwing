package pro05;

import java.util.Scanner;

public class Word {
	static Scanner scn = new Scanner(System.in);
	String englishWord, vietnamWord;

	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Word(String englishWord, String vietnamWord) {
		super();
		this.englishWord = englishWord;
		this.vietnamWord = vietnamWord;
	}

	public String getEnglishWord() {
		return englishWord;
	}

	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}

	public String getVietnamWord() {
		return vietnamWord;
	}

	public void setVietnamWord(String vietnamWord) {
		this.vietnamWord = vietnamWord;
	}

	@Override
	public String toString() {
		return "Word [englishWord=" + englishWord + ", vietnamWord=" + vietnamWord + "]";
	}
	
	public void display(int stt) {
		System.out.println(stt + "\t" + englishWord + " = " + vietnamWord);
	}

	public void input() {
		System.out.println("Enter English: ");
		englishWord = scn.nextLine();
		System.out.println("Enter Vietnamese: ");
		vietnamWord = scn.nextLine();
	}

}
