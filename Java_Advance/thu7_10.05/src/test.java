import java.util.Stack;

public class test {
	static String reverseInParentheses(String inputString) {
		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < inputString.length(); i++) {
			// if charAt i == '(' ==> push to Stack
			if (inputString.charAt(i) == '(') {
				s.push(i);
			}
			// if charAt i == ')' && stack not empty ==> get index of open ( '(' ) and index
			// of close ( ')' ) ==> implement replace substring it
			if (inputString.charAt(i) == ')' && s.size() > 0) {
				int open = s.pop() + 1;
				int close = i;

				// convert String to StringBuilder
				StringBuilder tmp = new StringBuilder(inputString);
//	            System.out.println("tmp0 = " + tmp);

				// replace = delete + insert

				// get subString and reverse
				StringBuilder str = new StringBuilder();
				str.append(inputString.substring(open, close));
				str = str.reverse(); // https://www.geeksforgeeks.org/reverse-a-string-in-java/
//	            System.out.println("str = " + str);

				// implement delete substring open - close
				tmp.delete(open - 1, close + 1); // https://www.tutorialspoint.com/java/lang/stringbuilder_delete.htm
//	            System.out.println("tmp = " + tmp);
				// insert substring reversed
				tmp.insert(open - 1, str.toString()); // https://www.tutorialspoint.com/java/lang/stringbuilder_insert_string.htm
//	            System.out.println("tmp1 = " + tmp);

				// convert StringBuilder to String
				inputString = tmp.toString();

				// size -2 ( charAt open and close )
				i -= 2;
			}
		}

		return inputString;
	}

	static int maxSubmatrixSum(int[][] matrix, int n, int m) {
		int ans = 0;
		for (int i = 0; i < matrix.length - n + 1; i++) {
			for (int j = 0; j < matrix[i].length - m + 1; j++) {
				int val = 0;
				for (int k = i; k < i + n; k++) {
					for (int t = j; t < j + m; t++) {
						val += matrix[k][t];
					}
				}
				ans = Math.max(ans, val);
			}
		}
		return ans;
	}
	

	public static void main(String[] args) {
//		System.out.println("foo(bar(baz))blim");
//		System.out.println(reverseInParentheses("foo(bar(baz))blim"));

//		int[][] matrix = {{1, 12, 11, 10}, 
//		                  {4,  3,  2,  9}, 
//		                  {5,  6,  7,  8}};
//		int n = 2, m = 1;
		
//		System.out.println(maxSubmatrixSum(matrix,n,m));
		String ver1 = new String("1.0.5");
		System.out.println(ver1);
		String sp1[] = ver1.split("\\.");
		System.out.println(sp1[0]);
	}
}
