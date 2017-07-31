package com.smartfactory.excelreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Reader.java contains method to read data from excel file
 */
public class Reader {
	public static String filename = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\testdata.xls";
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheet = null;
	private HSSFRow row = null;
	private HSSFCell cell = null;
	private String sheetName = null;

	public Reader(int i) {
		this.path = filename;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(i);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Purpose : This method returns the row count in a sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	/**
	 * Purpose : returns the data from a cell
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return
	 */
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	/**
	 * Purpose : returns the data from a cell
	 * 
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return
	 */
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	/**
	 * Purpose : find whether sheets exists
	 * 
	 * @param sheetName
	 * @return
	 */
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	/**
	 * Purpose : returns number of columns in a sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getColumnCount(String sheetName) {
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;
		return row.getLastCellNum();
	}

	/**
	 * Purpose : returns cell number
	 * 
	 * @param sheetName
	 * @param colName
	 * @param cellValue
	 * @return
	 */
	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;
	}

	public String[] getTestDataRow(String testName, Reader xls) {
		String getTestDataRowresult[] = new String[3];
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetName = workbook.getSheetName(i);
			int testStartRowNum = 0;
			for (int rNum = 1; rNum <= xls.getRowCount(sheetName); rNum++) {
				if (xls.getCellData(sheetName, 0, rNum).equals(testName)) {
					testStartRowNum = rNum;
					getTestDataRowresult[0] = sheetName;
					getTestDataRowresult[1] = testName;
					getTestDataRowresult[2] = String.valueOf(testStartRowNum);
					break;
				}
			}
		}
		return (getTestDataRowresult);
	}

	@SuppressWarnings("resource")
	public static void write(String catName, int intSheet, int intRow, int intCell)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		XSSFWorkbook workbook1 = null;
		FileInputStream fis = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;

		fis = new FileInputStream(Reader.filename);
		fis = new FileInputStream(Reader.filename);
		workbook1 = new XSSFWorkbook(fis);
		sheet = workbook1.getSheetAt(intSheet);
		row = sheet.getRow(intRow);
		cell = row.getCell(intCell);
		if (cell == null)
			cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(catName);

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(Reader.filename);
		workbook1.write(fileOut);
		fileOut.close();
	}

	/**
	 * 
	 * @param testName
	 * @param xls
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 */
	public Object[][] readData(String testName, Reader xls) {
		int testStartRowNum = 0;
		String sheetName = null;
		String temp[];
		temp = xls.getTestDataRow(testName, xls);
		sheetName = temp[0];
		testName = temp[1];
		testStartRowNum = Integer.parseInt(temp[2]);

		for (int rNum = 1; rNum <= xls.getRowCount(sheetName); rNum++) {
			if (xls.getCellData(sheetName, 0, rNum).equals(testName)) {
				testStartRowNum = rNum;
				break;
			}
		}

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;
		while (!xls.getCellData(sheetName, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}

		int dataStartRowNum = testStartRowNum + 2;
		int totalRows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}

		Object[][] data = new Object[totalRows][1];
		Hashtable<String, String> table = null;
		int index = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				table.put(xls.getCellData(sheetName, cNum, colStartRowNum), xls.getCellData(sheetName, cNum, rNum));
			}
			data[index][0] = table;
			index++;
		}
		return data;
	}

	public Hashtable<String, String> readData1(String testName, Reader xls) {
		Hashtable<String, String> table = null;
		int testStartRowNum = 0;
		String sheetName = null;
		String temp[];
		temp = xls.getTestDataRow(testName, xls);
		sheetName = temp[0];
		testName = temp[1];
		testStartRowNum = Integer.parseInt(temp[2]);

		for (int rNum = 1; rNum <= xls.getRowCount(sheetName); rNum++) {
			if (xls.getCellData(sheetName, 0, rNum).equals(testName)) {
				testStartRowNum = rNum;
				break;
			}
		}

		int colStartRowNum = testStartRowNum + 1;
		int totalCols = 0;
		while (!xls.getCellData(sheetName, totalCols, colStartRowNum).equals("")) {
			totalCols++;
		}

		int dataStartRowNum = testStartRowNum + 2;
		int totalRows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}

		Object[][] data = new Object[totalRows][1];

		int index = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				table.put(xls.getCellData(sheetName, cNum, colStartRowNum), xls.getCellData(sheetName, cNum, rNum));
			}
			data[index][0] = table;
			index++;
		}
		return table;
	}
	
	public static void main(String[] args) {
		Reader read=new Reader(0);
		System.out.println(read.getCellData("Sheet1",2,2));
		
		
	}
}