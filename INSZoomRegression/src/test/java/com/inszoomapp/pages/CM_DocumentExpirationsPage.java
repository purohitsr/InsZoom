package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_DocumentExpirationsPage extends LoadableComponent<CM_DocumentExpirationsPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_Save")
	WebElement btn_EdittedSaveDocument;
	
	@FindBy(id = "btn_Save")
	WebElement btn_SaveDocument;
	
	@FindBy(id = "txtDocNotes")
	WebElement textBox_Description;
	
	@FindBy(id = "txtToDt")
	WebElement textBox_ValidToDate;
	
	@FindBy(id = "txtFromDt")
	WebElement textBox_ValidFromDate;
	
	@FindBy(id = "cboDocType")
	WebElement dropDown_DocumentType;
	
	@FindBy(id="btn_Addnewfirmdefineddocumentexpirationdate")
	WebElement btn_AddNewDocument;
	
	public CM_DocumentExpirationsPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);

	}

	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Document expirations Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void addDocument(String documentName ,String validFromDate ,String documentDescription,String workbookNameWrite, String sheetName, boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */ 

		Utils.clickElement(driver, btn_AddNewDocument, "Add new document");
		Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - Add Firm Defined Document Expiration Date Details", "title", "false");
        Utils.selectOptionFromDropDown(driver, documentName, dropDown_DocumentType);
		Utils.enterText(driver, textBox_ValidFromDate, validFromDate, "Valid from date");
		SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		calendar.add(Calendar.DATE, 100); // Adding 100 days
		String toDate = sdfSecond.format(calendar.getTime());   
 
		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_DocumentExpirationsDate", sheetName, "Value", toDate);
         
		Utils.enterText(driver, textBox_ValidToDate, toDate, "Valid to date");
		Utils.enterText(driver, textBox_Description, documentDescription, "description");
		Utils.clickElement(driver, btn_SaveDocument, "Save");
		Utils.waitForAllWindowsToLoad(1, driver);
        Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");	

	}
	
	
	public void verifyDocumentAdded(String documentExpirationTemplateName, boolean screenshot) throws Exception {
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */ 

		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ documentExpirationTemplateName +"')]", "xpath", documentExpirationTemplateName, "document added");
	}
	
	public void clickEditDocumentIcon(String templateName)
	{
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ templateName +"')]/../../td/a/img[@title='Edit']", "xpath", "edit document expiration date template");
	}
	
	public void editDocument(String templateName,String editToDate, boolean screenshot) throws Exception 
    {
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */
		 clickEditDocumentIcon(templateName);
		 Utils.waitForAllWindowsToLoad(2, driver);
	     Utils.switchWindows(driver, "INSZoom.com - Edit Firm Defined Document Expiration Date Details", "title", "false");
	     Utils.enterText(driver, textBox_ValidToDate, editToDate, "edit to date");
	     Utils.clickElement(driver, btn_EdittedSaveDocument, "save button");
	     Utils.waitForAllWindowsToLoad(1, driver);
	     Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");

    }
	
	
	public void editDocumentExpirationTemplate(String workbookNameWrite, String sheetName, String client_DocumentExpirationsDate,String templateName,
            boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */
	      
	      SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(new Date()); // Now use today date.
	      calendar.add(Calendar.DATE, 365); // Adding 365 days
	      String editToDate = sdfSecond.format(calendar.getTime());
	
	      // Write the data in excel
	      String directory = System.getProperty("user.dir");
	      ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
	      writeExcel.setCellData("QA_A_Client_EditDocumentExpirationsDate", sheetName, "Value", editToDate);
	    
	      editDocument(templateName,editToDate,true);

		}

	
	public void verifyEditDocument(String client_EditDocumentExpirationsDate, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */
		
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM dd yyyy");
			java.util.Date dDate = sdfmt1.parse(client_EditDocumentExpirationsDate);
			String strOutputEdit = sdfmt2.format(dDate);
	
			String datesplit[] = strOutputEdit.split(" ");
			String month = datesplit[0];
			String date = datesplit[1];
			String year = datesplit[2];
	
			int dateResult = Integer.parseInt(date);
	
			String newDateEditOutput = month + " " + dateResult + " " + year;
	
			Utils.verifyIfDataPresent(driver, "//table[@summary='Firm Defined Document Expiration Dates']/tbody/tr/td[contains(text(),'"+ newDateEditOutput +"')]", "xpath", newDateEditOutput, "edit document expiration template");
	}
	
	
	public void editDocumentExpirationLessThan6Months(String templateName,String workbookNameWrite, String sheetName,
			String client_DocumentExpirationsDate, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */
		
		SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		calendar.add(Calendar.DATE, -150); // Adding 120 days
		String editToDate = sdfSecond.format(calendar.getTime());
		String dateFormat = editToDate;

		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_DocumentExpirationsDateBefore", sheetName, "Value", dateFormat);
		
		editDocument(templateName, editToDate, true);
	}
	
	public void editDocumentExpirationMoreThan6Months(String templateName, String workbookNameWrite, String sheetName,String client_DocumentExpirationsDate, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */
		

		SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		calendar.add(Calendar.DATE, 240); // Adding 240 days
		String editToDate = sdfSecond.format(calendar.getTime());
		String formatDate = editToDate;

		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_DocumentExpirationsDateAfter", sheetName, "Value", formatDate);
		
		editDocument(templateName, editToDate, true);

	}

	
	public void editDocumentExpirationInThan6Months(String templateName, String workbookNameWrite ,String sheetName, boolean screenshot)
			throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */

		SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		calendar.add(Calendar.DATE, -1);
		String editToDate = sdfSecond.format(calendar.getTime());
		String dateEdit = editToDate;

		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_DocumentExpirationsDateCurrent", sheetName, "Value", dateEdit);
		
		editDocument(templateName, editToDate, true);

	}

	public void editDocumentExpirationTemplate(String templateName, String validTo) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */
		
		clickEditDocumentIcon(templateName);
		 Utils.waitForAllWindowsToLoad(2, driver);
	     Utils.switchWindows(driver, "INSZoom.com - Edit Firm Defined Document Expiration Date Details", "title", "false");
	     Utils.enterText(driver, textBox_ValidToDate, validTo, "edit to date");
	     Utils.clickElement(driver, btn_EdittedSaveDocument, "save button");
	     Utils.waitForAllWindowsToLoad(1, driver);
	     Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");
	}


	public void verifyDocumentExpirationDatesPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 31 Jan 2020
		  */
	    try{
	       Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client Status Document Dates");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - View Client Status Document Dates Page Failed", driver);
	    }
}
 
 
 public void clickAddNewButton()
	{
        /*
	  * Created By : Saurav Purohit
	  * Created On : 31 Jan 2020
	  */
		Utils.clickElement(driver, btn_AddNewDocument, "Add New Button");
	}
 public void verifyAddFirmDefinedDocumentExpirationDatesPage() throws Exception 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 31 Jan 2020
		  */
	    try{
		 Utils.clickElement(driver, btn_AddNewDocument, "Add new document");
		 Utils.waitForAllWindowsToLoad(2, driver);
	         Utils.switchWindows(driver, "INSZoom.com - Add Firm Defined Document Expiration Date Details", "title", "false");
	         Utils.softVerifyPageTitle(driver, "INSZoom.com - Add Firm Defined Document Expiration Date Details");
	         driver.close();
	         Utils.waitForAllWindowsToLoad(1, driver);
	         Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");
	    }catch(Exception e){
		 driver.close();
		 Log.failsoft("Verification of Add Firm Defined DocumentExpiration DatesPage Failed", driver);
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");
	    }
}
	
}