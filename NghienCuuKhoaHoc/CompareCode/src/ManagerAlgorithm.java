import java.util.ArrayList;
import java.util.List;

public class ManagerAlgorithm {
	public synchronized static int editDistDP(String str1, String str2, int m, int n) {
		// Create a table to store results of subproblems
		int dp[][] = new int[m + 1][n + 1];

		// Fill d[][] in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0)
					dp[i][j] = j;

				else if (j == 0)
					dp[i][j] = i;

				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];

				else
					dp[i][j] = 1 + Utility.min(dp[i][j - 1], // Insert
							dp[i - 1][j], // Remove
							dp[i - 1][j - 1]); // Replace
			}
		}

		return dp[m][n];
	}

	/******************** Z function ******************/
	public synchronized static int ZSearch(String text, String pattern) {

		// Create concatenated string "P$T"
		String concat = pattern + "$" + text;

		int l = concat.length();

		int Z[] = new int[l];

		getZarr(concat, Z);
		int countFound = 0;
		for (int i = 0; i < l; ++i) {

			if (Z[i] == pattern.length()) {
				// System.out.println("Pattern found at index " + (i - pattern.length() - 1));
				countFound++;
			}
		}
		return countFound;
	}

	private synchronized static void getZarr(String str, int[] Z) {

		int n = str.length();

		int L = 0, R = 0;

		for (int i = 1; i < n; ++i) {

			if (i > R) {

				L = R = i;

				while (R < n && str.charAt(R - L) == str.charAt(R))
					R++;

				Z[i] = R - L;
				R--;

			} else {

				int k = i - L;

				if (Z[k] < R - i + 1)
					Z[i] = Z[k];

				else {

					L = i;
					while (R < n && str.charAt(R - L) == str.charAt(R))
						R++;

					Z[i] = R - L;
					R--;
				}
			}
		}
	}

	/*************** end Z function *****************/

	/*************** KMP *****************/
	public synchronized static int KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		int lps[] = new int[M];
		int j = 0; // index for pat[]

		computeLPSArray(pat, M, lps);

		int i = 0; // index for txt[]
		int countFound = 0;
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				// System.out.println("Found pattern " + "at index " + (i - j));
				countFound++;
				j = lps[j - 1];
			}

			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
		return countFound;
	}

	private synchronized static void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				if (len != 0) {
					len = lps[len - 1];

				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}

	/*************** end KMP *****************/

	public synchronized static List<String> ngrams(int n, String str) {
		List<String> ngrams = new ArrayList<String>();
		for (int i = 0; i < str.length() - n + 1; i++) {
			ngrams.add(str.substring(i, i + n));
		}
		return ngrams;
	}
}
