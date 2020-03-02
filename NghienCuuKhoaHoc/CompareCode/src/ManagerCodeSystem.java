import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;

public class ManagerCodeSystem implements Callable<CodeInformation> {
	private CodeInformation codeInfo;
	private ArrayList<String> codeStep;

	public ManagerCodeSystem() {
		codeStep = new ArrayList<>();
	}

	public ManagerCodeSystem(CodeInformation codeInfo) {
		this.codeInfo = codeInfo;
		codeStep = new ArrayList<>();
	}

	private synchronized void actionFormatCode() {
		codeInfo.setListCode(
				(ArrayList<String>) ManagerAnalyzeText.getInstance().formatAllCode(codeInfo.getListCode()));
//		codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
//		codeInfo.setStepCode(codeInfo.getStepCode());
	}

	private synchronized void actionFormatVariable() {
		codeInfo.setListCode(
				(ArrayList<String>) ManagerAnalyzeText.getInstance().replaceAllCode(codeInfo.getListCode()));

		codeInfo.setListCode(
				(ArrayList<String>) ManagerAnalyzeText.getInstance().formatAllVariable(codeInfo.getListCode()));
//		codeInfo.setStepCode(codeInfo.getListCode());
		codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
	}

	private synchronized void actionIgnoreDigitSpecial() {
		codeInfo.setListCode(
				(ArrayList<String>) ManagerAnalyzeText.getInstance().ignoreDigitSpecial(codeInfo.getListCode()));
//		codeInfo.setStepCode(codeInfo.getListCode());
		codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
	}

	private synchronized void actionReplaceDefineToCode() {
		Map<String, String> defineList = ManagerAnalyzeText.getInstance().getAllDefine(codeInfo.getListCode());
		codeInfo.setListCode((ArrayList<String>) ManagerAnalyzeText.getInstance().replaceAllDefineToCode(defineList,
				codeInfo.getListCode()));
//		codeInfo.setStepCode(codeInfo.getListCode());
		codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
	}

	private synchronized void actionReplaceSpaceAfterLetterEqual() {
		codeInfo.setListCode((ArrayList<String>) ManagerAnalyzeText.getInstance()
				.replaceSpaceAfterLetterEqual(codeInfo.getListCode()));
//		codeInfo.setStepCode(codeInfo.getListCode());
		codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
	}

	public CodeInformation getCodeInfo() {
		return codeInfo;
	}

	/*
	 * public synchronized void start(CodeInformation code) { synchronized (this) {
	 * if (code != null) { this.codeInfo = code; }
	 * codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
	 * actionFormatCode(); actionReplaceSpaceAfterLetterEqual();
	 * actionReplaceDefineToCode(); actionFormatVariable();
	 * actionIgnoreDigitSpecial(); codeInfo.setStepCode(codeStep); }
	 * 
	 * }
	 */

	protected void setCodeInfo(CodeInformation code) {
		this.codeInfo = code;
	}

	@Override
	public CodeInformation call() throws Exception {
		synchronized (this) {
			codeStep.add(Utility.convertListToString_default(codeInfo.getListCode()));
			actionFormatCode();
			actionReplaceSpaceAfterLetterEqual();
			actionReplaceDefineToCode();
			actionFormatVariable();
			actionIgnoreDigitSpecial();
			if(codeInfo.getStepCode() == null)
				codeInfo.setStepCode(codeStep);
		}
		return codeInfo;
	}

}
