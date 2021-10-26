package com.inszoomapp.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
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

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

import java.text.*;

public class CM_DetailsAndDatesPage extends LoadableComponent<CM_DetailsAndDatesPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_SaveeditedDOLSWAInfo")
	WebElement btn_SaveEditedDOLSWAInfo;
	
	@FindBy(id = "txtBLogCaseNum")
	WebElement textBox_backlogCaseNumber;
	
	@FindBy(id = "txtBacklogRcptDt")
	WebElement textBox_CenterNoticeReceivedOn;
	
	@FindBy(id = "txtBLogDt")
	WebElement textBox_backlogNoticeSentDate;
	
	@FindBy(id = "txtDOLCaseNbr")
	WebElement textBox_DOLcaseNumber;
	
	@FindBy(id = "txtDOLRcvdDate")
	WebElement textBox_DOLreceivedDate;
	
	@FindBy(id = "txtSWAPrtyDate")
	WebElement textBox_swaPriorityDate;
	
	@FindBy(id = "txtSWAFiledDate")
	WebElement textBox_swaFiledDate;
	
	@FindBy(id = "txtSWACaseNbr")
	WebElement textBox_swaCaseNumber;
	
	@FindBy(id = "btn_EditCaserelatedSWAandDOLinfo")
	WebElement btn_editSWAandDOLinfo;
	
	@FindBy(xpath = "//td[contains(text(),'Case Opened Date')]/following-sibling::td")
	WebElement textBox_CaseStartDate;
	
	@FindBy(xpath = "//span[contains(text(),'Case Status')]/following-sibling::span")
	WebElement textBox_CaseStatus;
	
	@FindBy(id = "cboPriorityCategorycheckBox")
	WebElement checkBox_VisaCategoryNA;
	
	@FindBy(id = "btn_SaveeditedprioritydatedetailsAOSdatedetails")
	WebElement btn_SaveAOSDetails;
	
	@FindBy(css = "input[name='txtaosCity']")
	WebElement textBox_AOSCity;
	
	@FindBy(css = "input[id='pri_or_aos_1']")
	WebElement radioBtn_EditAOSDateInfoRadioBtn;
	
	@FindBy(id="btn_EditprioritydatedetailsAOSdatedetails")
	WebElement btn_EditPriorityDateDetailsAOSdateDetails;
	
	@FindBy(id="cboPriorityCategory")
	WebElement dropDown_VisaCategory;
	
	@FindBy(id="btn_SaveeditedprioritydatedetailsAOSdatedetails")
	WebElement btn_SaveEditedVisaPriorityDateDetails;
	
	@FindBy(id="txtconsularProcessgCity")
	WebElement textBox_ConsularProcessingCity;
	
	@FindBy(id="pri_or_aos_0")
	WebElement checkBox_EditVisaPriorityDate;
	
	@FindBy(id="btn_EditprioritydatedetailsAOSdatedetails")
	WebElement btn_EditVisaPriorityDateDetails;
	
	@FindBy(id="cboodc")
	WebElement dropDown_CaseStatus;
	
	@FindBy(id="btn_Save")
	WebElement btn_SaveReminders;
	
	@FindBy(id = "btn_SaveCasedetails")
	WebElement btn_SaveCasedetails;
	
	@FindBy(id = "txttodate")
	WebElement textBox_ValidTill;
	
	
	@FindBy(id = "txtfromdate")
	WebElement textBox_ValidFrom;
	
	@FindBy(id = "txtApprvldate")
	WebElement textBox_txtApprvldate;
	
	
	@FindBy(id = "cboRecptStatus")
	WebElement dropDown_ReceiptStatus;
	
	@FindBy(id = "txtreceiptdate")
	WebElement textBox_ReceiptDate;
	
	@FindBy(id = "txtreceiptnbr")
	WebElement textBox_ReceiptNumber;
	
	@FindBy(id = "cboRcptType")
	WebElement dropDown_ReceiptType;
	
	@FindBy(id = "btn_EditCasedetails")
	WebElement btn_EditCasedetails;
	
	public CM_DetailsAndDatesPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_DetailsAndDatesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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

	public boolean isAlertPresent() 
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	
	public void editCaseInfo(String receiptType,String editReceiptDate,String receiptStatus,String receiptValidFromDate, String receiptValidTillDate,String dataWrite, String sheetName,boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		
		String editedReceiptNumber = RandomStringUtils.randomNumeric(6).toString();
		
		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		writeExcel.setCellData("QA_ALoginEditedReceiptNumber", sheetName, "Value", editedReceiptNumber);
		
		Utils.clickElement(driver, btn_EditCasedetails, "Edit case details button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
		Utils.selectOptionFromDropDownByVal(driver, receiptType, dropDown_ReceiptType);
		Utils.enterText(driver, textBox_ReceiptNumber,editedReceiptNumber , "Editted receipt Number");
		Utils.enterText(driver, textBox_ReceiptDate, editReceiptDate, "receipt date");
		Utils.selectOptionFromDropDown(driver, receiptStatus, dropDown_ReceiptStatus);
		
		while (Utils.isAlertPresent(driver)) {
			driver.switchTo().alert().accept();
		}

		Utils.enterText(driver, textBox_txtApprvldate, receiptValidFromDate, "Approved on date");	
		Utils.enterText(driver, textBox_ValidFrom, receiptValidFromDate, "Valid from date");		
		Utils.enterText(driver, textBox_ValidTill, receiptValidTillDate, "Valid till date");
		Utils.selectOptionFromDropDownByVal(driver, "O", dropDown_CaseStatus);//Do not change
		Utils.clickElement(driver, btn_SaveCasedetails, "save case details button");
//		Utils.waitForAllWindowsToLoad(2, driver);
//		Utils.switchWindows(driver, "INSZoom.com - Case Access Rights & Reminders", "title", "false");
//		Utils.clickElement(driver, btn_SaveReminders, "Save reminders");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");	
	}

	
	public void verifyEditedPetitionInfoDetails(String receiptValidTillDate, boolean screenshot) throws Exception 
	{
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM dd yyyy");
			java.util.Date dDate = sdfmt1.parse(receiptValidTillDate);
			String strOutput = sdfmt2.format(dDate);

			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Approval Valid Till')]/following-sibling::td[contains(text(),'" +strOutput+ "')]", "xpath", receiptValidTillDate, "receipt Valid Till Date");
	}
	
	public void editVisaPriorityDateInfo(String visaCategory, String consularProcessingCity, boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		
		Utils.clickElement(driver, btn_EditVisaPriorityDateDetails, "Edit priority date details AOS date details button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Priority Dates", "title", "false");
		Utils.clickElement(driver, checkBox_EditVisaPriorityDate, "checked edit visa priority dates");	
		Utils.selectOptionFromDropDownByVal(driver, visaCategory, dropDown_VisaCategory);
		Utils.enterText(driver, textBox_ConsularProcessingCity, consularProcessingCity, "consular processing city");	
		Utils.clickElement(driver, btn_SaveEditedVisaPriorityDateDetails, "Save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
	}
	
	
	public void verifyEditedVisaPriorityInfo(String consularProcessingCity,boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Consular Processing City')]/../td[contains(text(),'"+ consularProcessingCity +"')]", "xpath", consularProcessingCity, "Edited Visa Priority Info Details");
	}
	
	public void clickAOSDateInforEditButton()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */ 
		Utils.clickElement(driver, btn_EditPriorityDateDetailsAOSdateDetails, "edit priority date details AOS date details button");
		
	}
	
	
	public void editAOSDateDetailsInfo(String city, boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */ 
		
		clickAOSDateInforEditButton();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Priority Dates", "title", "false");
		Utils.clickElement(driver, radioBtn_EditAOSDateInfoRadioBtn, "selected the edit AOS Date info radio button");
		Utils.clickElement(driver, checkBox_VisaCategoryNA, "Visa category as NA");
		Utils.enterText(driver, textBox_AOSCity, city, "Entered city name");
		Utils.clickElement(driver, btn_SaveAOSDetails, "save AOS details button");	
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
	}

	
	public void verifyAOSDateDetailsInfo(String city) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */ 
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'AOS Interview Location City,State')]/../td[contains(text(),'"+ city +"')]", "xpath", city, "AOS Date details section");
		
	}
	
	
	 public void verifyCurrentCaseStatus(String status) throws Exception
		{
			 /*
			  * Created By : M Likitha Krishna
			  * Created On : 07 Nov 2019
			  */ 
			Utils.verifyIfDataPresent(driver, "//table[@summary='Case Info ']//td[contains(text(),'Current Case Status')]/../td[2][contains(text(),'"+ status +"')]", "xpath", status , "Case Status on details and dates page");
		}
	 
	 public void fetchAndUpdateCaseStartDate(String workbookNameWrite, String sheetName) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */
		 
		Utils.waitForElement(driver, textBox_CaseStartDate);
		String caseStartDate = textBox_CaseStartDate.getText();
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_CaseStartDate", sheetName, "Value", caseStartDate);
	 }
	 
	 public void fetchAndUpdateCaseStatus(String workbookNameWrite, String sheetName) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */
		 
		Utils.waitForElement(driver, textBox_CaseStatus);
		String caseStatus = textBox_CaseStatus.getText();
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_CaseStatus", sheetName, "Value", caseStatus);
	 }
	 
	 public void fetchAndUpdateCaseStartDateForRelative(String workbookNameWrite, String sheetName) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */
		 
		Utils.waitForElement(driver, textBox_CaseStartDate);
		String caseStartDate = textBox_CaseStartDate.getText();
		Log.message("CASE START DATE : " + caseStartDate);
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_CaseStartDateForRelative", sheetName, "Value", caseStartDate);
	 }
	 
	 public void fetchAndUpdateCaseStatusForRelative(String workbookNameWrite, String sheetName) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */
		
		Utils.waitForElement(driver, textBox_CaseStatus);
		String caseStatus = textBox_CaseStatus.getText();
		Log.message("CASE STATUS : " + caseStatus);
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_CaseStatusForRelative", sheetName, "Value", caseStatus);
	 }
	 
	 
	 public void addSwaAndDolInfo(String swaCaseNumber, String swaFiledDate,String swaPriorityDate, String dolRecievedDate,String dolCaseNumber, String noticeSentDate,String noticeReceivedDate, String backlogCaseNumber ) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 16 Dec 2019
		  */ 
		 
		 Utils.waitForElement(driver, btn_editSWAandDOLinfo);
		 Utils.scrollToElement(driver, btn_editSWAandDOLinfo);
		 Utils.clickElement(driver, btn_editSWAandDOLinfo, "edit SWA and DOL info");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit DOL SWA Details", "title", "false");
		 
		 Utils.enterText(driver, textBox_swaCaseNumber, swaCaseNumber, "SWA case number");
		 
		 Utils.enterText(driver, textBox_swaFiledDate, swaFiledDate, "SWA filed date");
		 
		 Utils.enterText(driver, textBox_swaPriorityDate, swaPriorityDate, "SWA Priority date");
		 
		 Utils.enterText(driver, textBox_DOLreceivedDate, dolRecievedDate, "DOL Received date");
		 
		 Utils.enterText(driver, textBox_DOLcaseNumber, dolCaseNumber, "DOL Case number");
		 
		 Utils.enterText(driver, textBox_backlogNoticeSentDate, noticeSentDate, "SWA Notice Case Sent to Backlog Processing Center On");
		 
		 Utils.enterText(driver, textBox_CenterNoticeReceivedOn, noticeReceivedDate, "Backlog Processing Center Notice Received On");
		 
		 Utils.enterText(driver, textBox_backlogCaseNumber, backlogCaseNumber, "Backllog case number");
		 
		 Utils.clickElement(driver, btn_SaveEditedDOLSWAInfo, "save button");
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
		 
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'DOL Case Number')]/following-sibling::td[1][contains(text(),'"+dolCaseNumber+"')]", "xpath", dolCaseNumber, "DOL Case Number");
	 }
	
}