package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	
	XSSFWorkbook wb;

	public ExcelDataProvider() {

		/*
		 * File src = new
		 * File(System.getProperty("user.dir")+"/ExcelData/myDataSheet.xlsx"); 
		 * try {
		 * FileInputStream fis = new FileInputStream(src); } 
		 * catch
		 * (FileNotFoundException e) { e.printStackTrace(); }
		 * 
		 * XSSFWorkbook wb = new XSSFWorkbook(); XSSFSheet sh=
		 * wb.getSheet("CreateUserData"); XSSFRow row = sh.getRow(0); XSSFCell data =
		 * row.getCell(0); data.getStringCellValue();
		 */

		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/CRMDataNew.xlsx")));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public int columnCount(String sheetName, int row) {
		return wb.getSheet(sheetName).getRow(row).getPhysicalNumberOfCells();

	}

	public int columnCount(int sheetIndex, int row) {
		return wb.getSheetAt(sheetIndex).getRow(row).getPhysicalNumberOfCells();

	}

	public int columnCount(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();

	}

	public int columnCount(int sheetIndex) {
		return wb.getSheetAt(sheetIndex).getRow(0).getPhysicalNumberOfCells();
	}

	public int rowCount(int sheetIndex) {
		return wb.getSheetAt(sheetIndex).getPhysicalNumberOfRows();
	}

	public int rowCount(String sheetName) {
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();

	}

	public String getCellData(String sheetName, int row, int col) {
		XSSFCell cell = wb.getSheet(sheetName).getRow(row).getCell(col);

		String data = " ";

		if (cell.getCellType() == CellType.STRING) {
			data = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			/*
			 * double doubleData = cell.getNumericCellValue(); 
			 * int a = (int)doubleData; 
			 * data = String.valueOf(a);
			 */
			data = String.valueOf((int) cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			data = " ";
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			data = String.valueOf((boolean) cell.getBooleanCellValue());
		}

		return data;
	}

	public String getCellData(int sheetIndex, int row, int col) {
		XSSFCell cell = wb.getSheetAt(sheetIndex).getRow(row).getCell(col);

		String data = " ";

		if (cell.getCellType() == CellType.STRING) {
			data = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			data = String.valueOf((int) cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			data = " ";
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			data = String.valueOf((boolean) cell.getBooleanCellValue());
		}

		return data;
	}

}
