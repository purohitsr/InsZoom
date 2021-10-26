package ContentAutomation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.ExcelToDataProvider;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.RowCountColumnCount;
import com.support.files.Utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.*;

public class GetDataFromExcelAndVerify extends LoadableComponent<GetDataFromExcelAndVerify> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
    public FileInputStream fis = null;

    public XSSFWorkbook workbook = null;

    public XSSFSheet sheet = null;

    public XSSFRow row = null;

    public XSSFCell cell = null;

    String xlFilePath;
	
	public GetDataFromExcelAndVerify(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public GetDataFromExcelAndVerify(WebDriver driver, String xlFilePath) throws Exception
    {

		this.driver = driver;
		PageFactory.initElements(driver, this);
        this.xlFilePath = xlFilePath;

        fis = new FileInputStream(xlFilePath);

        workbook = new XSSFWorkbook(fis);

      //  fis.close();	
     }


	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Details and dates Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
    public void test1(String workbookName, String sheetName) throws Exception
    {
		ExcelToDataProvider reader = new ExcelToDataProvider();
		Object[][] data = reader.userFormData(workbookName,sheetName);
		RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		for(int i = 0 ; i < noOfRows-1 ; i++)
		{
			if(data[i][0].equals("Beneficiary"))
			{
				try {
					Utils.waitForElementIfPresent(driver, "//tr/td[text()='From Sponsor']/../preceding-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[3][text()=\""+ data[i][5].toString() +"\"]", 20, "xpath",data[i][1].toString());	                     
					Log.pass("Verified "+data[i][1]);
				}catch (Exception e) 
				{
					Log.message("Unable to verify " + data[i][1] + " in " + "docs check list page" + ". ERROR :\n\n " + e.getMessage(), driver, true);
		        }
		    }
			else{
				try {
					Utils.waitForElementIfPresent(driver, "//tr/td[text()='From Sponsor']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[3][text()=\""+ data[i][5].toString() +"\"]", 20, "xpath",data[i][1].toString());	                     
					Log.pass("Verified "+data[i][1]);
				}catch (Exception e) 
				{
					Log.message("Unable to verify " + data + " in " + "docs check list page" + ". ERROR :\n\n " + e.getMessage(), driver, true);
		        }
		    }
		}
		System.out.println("Hello");
    }
      

    public void ExcelApiTest(String xlFilePath) throws Exception
    {

        this.xlFilePath = xlFilePath;

        fis = new FileInputStream(xlFilePath);

        workbook = new XSSFWorkbook(fis);

      //  fis.close();

    }
    
    
   public static int excelXSSF(String path, String sheetName) throws Exception
   {
       FileInputStream fis = new FileInputStream(path);
       XSSFWorkbook workbook = new XSSFWorkbook(fis);
       XSSFSheet sheet = workbook.getSheet(sheetName);
       XSSFRow row = sheet.getRow(0);
       int colNum = row.getLastCellNum();
       int rowNum = sheet.getLastRowNum()+1;

       return rowNum;
    }
   
   
   public static void getDataXSSF(String path, String sheetName) throws Exception
   {
       FileInputStream fis = new FileInputStream(path);
       XSSFWorkbook workbook = new XSSFWorkbook(fis);
       XSSFSheet sheet = workbook.getSheet(sheetName);
       XSSFRow row1 = sheet.getRow(0);
       System.out.println(row1.getCell(6).getStringCellValue());
       Iterator<Row> rowIterator = sheet.iterator(); // Traversing over each row of XLSX file while (rowIterator.hasNext()) { Row row = rowIterator.next(); // For each row, iterate through each columns Iterator<Cell> cellIterator = row.cellIterator(); while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch (cell.getCellType()) { case Cell.CELL_TYPE_STRING: System.out.print(cell.getStringCellValue() + "\t"); break; case Cell.CELL_TYPE_NUMERIC: System.out.print(cell.getNumericCellValue() + "\t"); break; case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break; default : } } System.out.println(""); }
       while (rowIterator.hasNext()) {
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
           while (cellIterator.hasNext()) {
        	   Cell cell = cellIterator.next();
        	   switch (cell.getCellType()) {
        	   	case Cell.CELL_TYPE_STRING:
                   System.out.print(cell.getStringCellValue() + "\t");
                   break;
        	   	case Cell.CELL_TYPE_NUMERIC:
                    System.out.print(cell.getNumericCellValue() + "\t");
                    break;
        	   	case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue() + "\t");
                    break;
        	   }
           }
           System.out.println("");
       }
    }

  

    public int getRowCount(String sheetName)

    {

        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum()+1;

        return rowCount;

    }

  

    public int getColumnCount(String workbookName, String sheetName) throws Exception

    {

    	RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		return 0;
    }
	
}