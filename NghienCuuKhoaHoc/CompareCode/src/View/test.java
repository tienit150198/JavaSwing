package View;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static List<String> ngrams(int n, String str) {
		List<String> ngrams = new ArrayList<String>();
		for (int i = 0; i < str.length() - n + 1; i++) {
			ngrams.add(str.substring(i, i + n));
		}
		return ngrams;
	}

	public static void main(String[] args) {
		String xx = "abcdef";
		ngrams(3, xx).forEach(x -> System.out.println(x));
	}
}
