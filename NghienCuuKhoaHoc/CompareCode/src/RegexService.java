import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService {
	public static Collection<? extends String> getForSyntax(String text) {
		List<String> isList = new ArrayList<>();
		Pattern pattern = Pattern
				.compile("[;]?(for[\\s]?[(][\\s]?[\\w].*?[\\s]?[;].*[\\s]?[\\w].*?[\\s]?[;].*[\\s]?[)][\\s]?)[{|\\s]*");
		Matcher matcher = pattern.matcher(text);

		// kiểm tra ở text có for hay không. Nếu có thì lấy ra rồi xóa đoạn đó đi.
		while (matcher.find()) {
			isList.add(matcher.group(1));
		}

		if (!isList.isEmpty()) {
			for (String string : isList) {
				text = text.replaceAll(string, "");
			}
		}
		return isList;
	}
	
	public static String getFileName(String path) {
		String regex = "\\\\((\\w*)((\\.cpp)|(\\.c)|(\\.h)))$";
		String result = null;
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(path);
		
		while(matcher.find()) {
			result = matcher.group(1);
		}
		
		return result;
	}
	
	public static boolean isAcceptFile(String path) {
		return path.matches(".*((\\.cpp)|(\\.c)|(\\.h))$");
	}
	
	public static String changeCodeRegex(String code) {
		String result = code.replace("(", "\\(");
		result = result.replace(")", "\\)");
		result = result.replace("+", "\\+");
		result = result.replace("*", "\\*");
		result = result.replace("^", "\\^");
		result = result.replace("}", "\\}");
		result = result.replace("{", "\\{");
		return result;
	}
}
