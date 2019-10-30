package exportExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class exportData {
	static int count = 1;
	static Map<String, String> data = new TreeMap<String, String>();
	public static void getData(){
		for(int i = 0 ; i <= 9 ; i++) {
			for(char j = 'a' ; j <= 'z' ; j++) {
				for(char k = 'a' ; k <= 'z' ; k++) {
					String x = "";
					x = x.concat(String.valueOf(i));
					x = x.concat(String.valueOf(j));
					x = x.concat(String.valueOf(k));
					System.out.println(x);
					data.put(String.valueOf(count++), x);
				}
			}
		}
		data.put(String.valueOf(0), String.valueOf(count-1));
		
	}
	public static void main(String[] args) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		HSSFSheet sheet = hssfWorkbook.createSheet("Employee data");
		
		// put data into the map
		exportData.getData();
		
		Set<String> keySet = data.keySet();

		int rownum = 0;
		for (String key : keySet) {

			Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			String text = data.get(key);
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(text);
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("export.xls"));
			hssfWorkbook.write(out);
			out.close();
			hssfWorkbook.close();
			System.out.println("export.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
