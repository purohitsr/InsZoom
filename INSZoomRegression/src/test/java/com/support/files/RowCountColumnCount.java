package com.support.files;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RowCountColumnCount
{
   public FileInputStream fis = null;
   public HSSFWorkbook workbook = null;
   public HSSFSheet sheet = null;
   public HSSFRow row = null;
   public HSSFCell cell = null;
   String xlFilePath;
   
   
   
   public String getCellData(String sheetName,int colNum,int rowNum)
   {
       try
       {
           sheet = workbook.getSheet(sheetName);
           row = sheet.getRow(rowNum);
           cell = row.getCell(colNum);
           final int type = cell.getCellType();
          // System.out.print(type);
           
           return convertHSSFCellToString(cell);
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
       }
   }
   
   public String convertHSSFCellToString(HSSFCell cell) {

		String cellValue = "";

		if (cell != null)
			cellValue = cell.toString().trim();

		return cellValue;

	}
   
   
   public RowCountColumnCount(String xlFilePath) throws Exception
   {
       this.xlFilePath = xlFilePath;
       //fis = new FileInputStream(xlFilePath);
       
       String filePath = xlFilePath.replace("\\", File.separator);
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
       
       workbook = new HSSFWorkbook(fs);
      
   }
 
 
   public int getRowCount(String sheetName)
   {
       sheet = workbook.getSheet(sheetName);
       int rowCount = sheet.getLastRowNum()+1;
       return rowCount;
   }
 
   public int getColumnCount(String sheetName)
   {
       sheet = workbook.getSheet(sheetName);
       row = sheet.getRow(0);
       int colCount = row.getLastCellNum();
       return colCount;
   }
}