package com.support.files;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.support.files.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Hashtable;

public class ReadWriteExcel {
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public HSSFWorkbook workbook = null;
	public HSSFSheet sheet = null;
	public HSSFRow row = null;
	public HSSFRow rows = null;
	public HSSFCell cell = null;
	String xlFilePath;

	private String workBookName;
	private String workSheet;
	private String testCaseId;
	private boolean doFilePathMapping;
	private HashMap<String, String> data;

	private Hashtable<String, Integer> excelHeaders = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<String, Integer>();

	public ReadWriteExcel() {
	}

	public ReadWriteExcel(String xlWorkBook, String xlWorkSheet) {
		this.workBookName = xlWorkBook;
		this.workSheet = xlWorkSheet;
	}

	public ReadWriteExcel(String xlWorkBook, String xlWorkSheet, String tcID) {
		this.workBookName = xlWorkBook;
		this.workSheet = xlWorkSheet;
		this.testCaseId = tcID;
	}

	public String getWorkBookName() {
		return workBookName;
	}

	public void setWorkBookName(String workBookName) {
		this.workBookName = workBookName;
	}

	public void setFilePathMapping(boolean doFilePathMapping) {
		this.doFilePathMapping = doFilePathMapping;
	}

	public String getWorkSheet() {
		return workSheet;
	}

	public void setWorkSheet(String workSheet) {
		this.workSheet = workSheet;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public ReadWriteExcel(String xlFilePath) throws Exception {
		this.xlFilePath = xlFilePath;
		System.out.println(xlFilePath);
		fis = new FileInputStream(xlFilePath);
		workbook = new HSSFWorkbook(fis);
		fis.close();
	}

	public boolean setCellData(String rowName, String sheetName, String colName, String value) {
		try {
			int col_Num = -1;
			int rowNum = 0;
			sheet = workbook.getSheet(sheetName);
			rows = sheet.getRow(0);
			/*
			 * row = sheet.getRow(0); System.out.print(row); for (int i = 0; i <
			 * row.getLastCellNum(); i++) { if
			 * (row.getCell(i).getStringCellValue().trim().equals(colName)) { col_Num = i; }
			 * }
			 */

			for (int j = 0; j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row.getCell(0).getStringCellValue().trim().equals(rowName)) {
					rowNum = j;
					for (int i = 0; i <= rows.getLastCellNum(); i++) {
						if (rows.getCell(i).getStringCellValue().trim().equals(colName)) {
							col_Num = i;
							System.out.println("Row Number is " + (rowNum) + " and column number is " + (col_Num) + "");
							break;
						}
					}
					break;
				}

			}

			sheet.autoSizeColumn(col_Num);
			row = sheet.getRow(rowNum);
			System.out.print(row);
			if (row == null)
				row = sheet.createRow(rowNum);

			cell = row.getCell(col_Num);
			System.out.print(cell);
			if (cell == null)
				cell = row.createCell(col_Num);

			cell.setCellValue(value);

			fos = new FileOutputStream(xlFilePath);
			workbook.write(fos);
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public String initTest(String workbook, String sheetName, String testcaseID) {
		/** Loading the test data from excel using the test case id */
		ReadWriteExcel testData = new ReadWriteExcel();
		testData.setWorkBookName(workbook);
		testData.setWorkSheet(sheetName);
		testData.setFilePathMapping(true);
		testData.setTestCaseId(testcaseID);
		return testData.readDataCell();
	}

	// to read only particular cell

	public String readDataCell() {

		HSSFCell testData = null;
		String test = null;
		com.support.files.ReadFromExcel readTestData = new com.support.files.ReadFromExcel();
		boolean isDataFound = false;
		testCaseId = testCaseId != null ? testCaseId.trim() : "";
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		try {

			sheet = readTestData.initiateExcelConnection(workSheet, workBookName, doFilePathMapping); // to initiate a
																										// connection to
																										// an excel
																										// sheet
			excelrRowColumnCount = readTestData.findRowColumnCount(sheet, excelrRowColumnCount); // find number of rows
																									// and columns
			excelHeaders = readTestData.readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount); // to find excel
																										// header fields

			for (int r = 0; r < excelrRowColumnCount.get("RowCount"); r++) {

				row = sheet.getRow(r);
				if (row == null)
					continue;

				for (int c = 0; c < excelrRowColumnCount.get("ColumnCount"); c++) {

					if (row.getCell(excelHeaders.get("Rowname")) == null)
						break;

					cell = row.getCell(excelHeaders.get("Rowname"));

					if (!readTestData.convertHSSFCellToString(cell).toString().equalsIgnoreCase(testCaseId))
						continue;

					isDataFound = true;

					for (String key : excelHeaders.keySet()) {
						testData = row.getCell(excelHeaders.get(key));
						test = testData.toString();
						break;
					}

					break;

				}

				if (isDataFound)
					break;

			}

			if (!isDataFound)
				Assert.fail("\nTest Data not found in test data sheet for Test Case Id  : " + testCaseId);

		} catch (RuntimeException e) {
			Assert.fail("Error During Execution; Execution Failed More details " + e);
			e.printStackTrace();
		}

		return test;
	}

}