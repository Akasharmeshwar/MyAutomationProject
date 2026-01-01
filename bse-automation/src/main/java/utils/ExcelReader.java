package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static String getCellData(String filePath, String sheetName, int row, int col) {

		try (FileInputStream fis = new FileInputStream(filePath)) {

			Workbook wb = WorkbookFactory.create(fis);

			return wb.getSheet(sheetName).getRow(row).getCell(col).toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

}
