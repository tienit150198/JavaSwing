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

public class exportExcel {
	public static void main(String[] args) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		
		HSSFSheet sheet = hssfWorkbook.createSheet("Employee data");
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		
		data.put("1", new Object[] {"ID", "NAME"});
		data.put("2", new Object[] {1, "A"});
		data.put("3", new Object[] {2, "B"});
		data.put("4", new Object[] {3, "C"});
		
		Set<String> keySet = data.keySet();
		
		int rownum = 0;
		for (String key : keySet) {

			Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			Object [] objArr = data.get(key);
			for(Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
			}
		}
		try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xls"));
            hssfWorkbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
