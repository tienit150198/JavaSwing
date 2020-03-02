import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerAnalyzeText {

	public static class Singleton {
		public static final ManagerAnalyzeText instance = new ManagerAnalyzeText();
	}

	static List<String> listVariables;
	static Map<String, Set<String>> variables;

	public static ManagerAnalyzeText getInstance() {
		return Singleton.instance;
	}

	public static Map<String, Set<String>> getVariables() {
		return variables;
	}

	boolean checkCommentBlock;
	Map<String, String> mReplace;

	int numberInt, numberDouble, numberBool, numberChar, numberString;

	private ManagerAnalyzeText() {
		checkCommentBlock = false;
		numberInt = numberDouble = numberBool = numberChar = numberString = 0;

		listVariables = new LinkedList<>();
		variables = new HashMap<>();
		mReplace = ReadReplace.getReplace();
	}

	public synchronized List<String> changeVariable(Map<String, Set<String>> isMap) {
		List<String> listResult = new ArrayList<String>();

		isMap.forEach((dataType, setVariable) -> {
			List<String> listTmp = new LinkedList<>();
			int number = getNumber(dataType);

			for (String string : setVariable) {
				if (!isDigitSpecial(string)) {
					listTmp.add(String.valueOf(
							string + "=" + dataType.charAt(0) + dataType.charAt(dataType.length() - 1) + (number + 1)));
					number++;
				}
			}

			listResult.addAll(listTmp);
		});

		return listResult;
	}

	public int checkCommentBlock(char characIndex, char characIndexNext, int index, int sizeOfText,
			boolean flagComment) {
		// 1 to comment open ( /* ) - 2 to comment close ( */ )
		if (index < sizeOfText - 1 && characIndex == '/' && characIndexNext == '*')
			return 1;
		if (index < sizeOfText - 1 && characIndex == '*' && characIndexNext == '/' && flagComment)
			return 2;
		return 0;
	}

	private boolean checkDefine(String text) {
		return (text.startsWith("#define"));
	}

	public boolean checkNotRead(String text) {
		if (text.trim().startsWith("//") || text.trim().startsWith("#include") || text.trim().startsWith("using name")
				|| text.trim().startsWith("printf") || text.trim().startsWith("scanf") || text.trim().startsWith("cin")
				|| text.trim().startsWith("cout") || text.trim().startsWith("assert") || text.trim().equals(";"))
			return true;

		return false;
	}

	private StringBuilder clearStringBuilder(StringBuilder text) {
		return (text.delete(0, text.length()));
	}

	public synchronized List<String> formatAllCode(List<String> listCode) {
		List<String> isList = new ArrayList<>();

		for (String code : listCode) {
			isList.addAll(formatCode(code));
		}

		return isList;
	}

	public synchronized List<String> formatAllVariable(List<String> isList) {
		List<String> listResult = new ArrayList<>();

		for (String code : isList) {
			listResult.add(formatVariable(listVariables, code));
		}

		return listResult;
	}

	private Collection<? extends String> formatCode(String text) {
		List<String> isList = new ArrayList<>();

		if (checkDefine(text)) {
			isList.add(text);
			return isList;
		}

		/* */
		Pattern pattern = Pattern
				.compile("[;]?(for[\\s]?[(][\\s]?[\\w].*?[\\s]?[;].*[\\s]?[\\w].*?[\\s]?[;].*[\\s]?[)][\\s]?)[{|\\s]*");
		Matcher matcher = pattern.matcher(text);

		// kiểm tra ở text có for hay không. Nếu có thì lấy ra rồi xóa đoạn đó đi.
		while (matcher.find()) {
			isList.add(matcher.group(1));
		}

		if (!isList.isEmpty()) {
			for (String string : isList) {
				text = text.replace(string, "");
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {

			if (i < text.length() - 1 && checkCommentBlock(text.charAt(i), text.charAt(i + 1), i, text.length(),
					checkCommentBlock) == 1) {
				checkCommentBlock = true;
			}
			if (i < text.length() - 1 && checkCommentBlock(text.charAt(i), text.charAt(i + 1), i, text.length(),
					checkCommentBlock) == 2) {
				checkCommentBlock = false;
				i++;
				continue;
			}
			if (!checkCommentBlock) {
				if ((text.charAt(i) == ';' || text.charAt(i) == '{' || text.charAt(i) == '}')
						&& notEmptyStringBuilder(sb)) {
					if (sb.toString().contains("//")) {
						String tmp = sb.toString();
						sb = clearStringBuilder(sb);
						sb.append(tmp.substring(0, tmp.toString().indexOf("//")));
					}

					if (!sb.toString().isEmpty() && !checkNotRead(sb.toString().trim())) {
						isList.add(sb.toString().trim() + text.charAt(i));
					}
					sb = clearStringBuilder(sb);
					continue;
				}
				sb.append(text.charAt(i));
			}

		}
		if (!checkCommentBlock && notEmptyStringBuilder(sb) && !checkNotRead(sb.toString().trim())) {
			if (sb.toString().contains("//")) {
				String tmp = sb.toString();
				sb = clearStringBuilder(sb);
				sb.append(tmp.substring(0, tmp.toString().indexOf("//")));
			}

			if (!sb.toString().isEmpty() && !checkNotRead(sb.toString().trim())) {
				isList.add(sb.toString().trim());
			}
			sb = clearStringBuilder(sb);
		}

		return isList;
	}

	private synchronized String formatVariable(List<String> listVariables, String text) {
		String result = text;
		for (String variables : listVariables) {
			String split[] = variables.split("=");
			String oldChar = split[0].trim();
			String newChar = split[1].trim();

			int indexOld = 0;
			int index = result.indexOf(oldChar, indexOld);
			while (index != -1) {
				if (index == 0 && isDigitSpecial(result.charAt(index + oldChar.length()) + "")) {
					String tmpSt = result.substring(0, index);
					String tmpEn = result.substring(index + oldChar.length(), result.length());
					result = tmpSt + newChar + tmpEn;
				} else if (index > 0 && index < result.length() - 1 && isDigitSpecial(result.charAt(index - 1) + "")
						&& isDigitSpecial(result.charAt(index + oldChar.length()) + "")) {
					String tmpSt = result.substring(0, index);
					String tmpEn = result.substring(index + oldChar.length(), result.length());
					result = tmpSt + newChar + tmpEn;

				}
				index = result.indexOf(oldChar, indexOld);
				indexOld = index + 1;
			}

		}
		return result;
	}

	public synchronized Map<String, String> getAllDefine(List<String> listCode) {
		Map<String, String> mResult = new HashMap<>();

		listCode.forEach(code -> {
			mResult.putAll(getDefineSyntax(code));
		});
//		System.out.println(mResult);
		return mResult;
	}

	private Map<String, String> getDefineSyntax(String text) {
		Map<String, String> mResult = new HashMap<>();
		if (!text.startsWith("#define"))
			return mResult;

		String split[] = text.split(" ");
		if (split.length != 3)
			return mResult;

		mResult.put(split[1].trim(), split[2].trim());

		return mResult;
	}

	private int getNumber(String variable) {
		if (variable.equals("bool"))
			return numberBool;
		if (variable.equals("int"))
			return numberInt;
		if (variable.equals("double"))
			return numberDouble;
		if (variable.equals("char"))
			return numberChar;
		if (variable.equals("string"))
			return numberString;
		return -1;
	}

	public List<String> ignoreDigitSpecial(List<String> listCode) {
		List<String> isList = new ArrayList<>();
//		StringBuilder res = new StringBuilder();
		for (String code : listCode) {
			code = code.replaceAll("\\W", "");
			if (!code.equals("")) {
				isList.add(code);
//				res.append(code);
			}
		}
		return isList;
//		return res.toString();
	}

	public boolean isDigitSpecial(String text) {
		String regex = ("\\W");
		String newChar = "==>";
		text = text.replaceAll(regex, newChar);
		return (text.contains(newChar));
	}

	public boolean isPunctuation(char c) {
		return (c == '(' || c == ')' || c == '.' || c == ',' || c == ';' || c == '?' || c == '<' || c == '>' || c == '&'
				|| c == ' ');
	}

	public boolean notEmptyStringBuilder(StringBuilder text) {
		return (text.toString().trim().length() != 0);
	}

	public List<String> replaceAllCode(List<String> listCode) {
		List<String> isList = new ArrayList<>();
		for (String code : listCode) {
			isList.add(replaceText(code));
		}
		listVariables = changeVariable(variables);
		listVariables.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});

		return isList;
	}

	public synchronized List<String> replaceAllDefineToCode(Map<String, String> isMap, List<String> listCode) {
		List<String> listResult = new ArrayList<>();

		listCode.forEach(code -> {
			String text = replaceDefineToCode(isMap, code);
			if (text != null) {
				System.out.println(text);
				listResult.add(text);
			}
		});

		return listResult;
	}

	private String replaceDefineToCode(Map<String, String> isMap, String code) {
		if (code.startsWith("#define") || code.startsWith("get") || code.startsWith("put"))
			return null;
		String result = code;
		Set<String> keySet = isMap.keySet();

		for (String defineValue : keySet) {
			String oldChar = defineValue;
			System.out.println("OLDCHAR = " + oldChar);
			String newChar = isMap.get(defineValue);
			System.out.println("NEWCHAR = " + newChar);

			String regex = "([^a-zA-Z0-9]" + RegexService.changeCodeRegex(oldChar) + "\\W*)";
			System.out.println("REGEX = " + regex);
			if (code.contains(oldChar)) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(code);
				String textTail = "", textHead = "";
				while (matcher.find()) {
					String tmp = matcher.group(1).toString();
					textHead = String.valueOf(tmp.charAt(0) + "");
					textTail = String.valueOf(tmp.charAt(tmp.length() - 1) + "");

					if (isOpen(textHead)) {
						if (!isClose(textTail)) {
							textTail = "]" + textTail;
						}
					}

					System.out.println(tmp + " -> " + textTail + " -> " + textHead);
					result = result.replaceFirst(regex, textHead + newChar + textTail);
					System.out.println("RESULT = " + result);
				}

//				System.out.println("1 = " + textHead + newChar + textTail);

//				result = code.replaceAll(regex, newChar);
			}
			code = result;
		}

		return result;
	}

	private boolean isClose(String text) {
		return (text.equals(")") || text.equals("]") || text.equals("}"));
	}

	private boolean isOpen(String text) {
		return (text.equals("(") || text.equals("[") || text.equals("{"));
	}

	public List<String> replaceSpaceAfterLetterEqual(List<String> isList) {
		List<String> listResult = new ArrayList<>();

		String regex = "([\\s]*[\\W][\\s]*)";

		for (String code : isList) {
			if (isDigitSpecial(code)) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(code);

				while (matcher.find()) {
					String tmp1 = matcher.group(1);
					String tmp = tmp1.trim();
					if (!tmp.equals(" ") && !tmp.equals("") && !tmp1.equals(tmp)) {

						tmp1 = RegexService.changeCodeRegex(tmp1);
						tmp = RegexService.changeCodeRegex(tmp);
						code = code.replaceFirst(tmp1, tmp);
					}

				}
				listResult.add(code);

			} else
				listResult.add(code);
		}
		return listResult;
	}

	private synchronized String replaceText(String text) {
		String result = text;
		Set<String> ketSet = mReplace.keySet();
		Set<String> listVariable = new HashSet<String>();
		String keyTmp = "";
		for (String variable : ketSet) {
			Pattern pattern = Pattern.compile(mReplace.get(variable).trim());
			Matcher matcher = pattern.matcher(text.trim());

			while (matcher.find() && !text.trim().startsWith("print")) {
				keyTmp = variable;
				result = text.replace(matcher.group(1), variable);

				String split[] = matcher.group(3).split(",");
				if (split.length > 0) {
					for (String string : split) {
						String tmpSplit[] = string.trim().split("=");

						if (tmpSplit[0].trim().contains("[")) {
							String tmpSplit1[] = tmpSplit[0].trim().split("\\[");
							tmpSplit[0] = tmpSplit1[0].trim();
						}

						Pattern pattern1 = Pattern.compile("([a-zA-Z0-9]*)\\W[\\d]*\\W");
						Matcher matcher1 = pattern1.matcher(tmpSplit[0].trim());
						String tmp = tmpSplit[0].trim();
						while (matcher1.find()) {
							tmp = matcher1.group(1);
						}

						listVariable.add(tmp);
					}
				}
			}
		}
		final String tmp = keyTmp;
		variables.forEach((key, val) -> {
			if (key.equals(tmp)) {
				listVariable.addAll(val);
			}
		});
		variables.put(tmp, listVariable);

		return result;
	}

}
