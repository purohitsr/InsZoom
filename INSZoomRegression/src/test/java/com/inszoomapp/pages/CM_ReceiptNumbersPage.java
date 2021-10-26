package com.inszoomapp.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ReceiptNumbersPage extends LoadableComponent<CM_ReceiptNumbersPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//button[text()='Delete Receipt']")         //Added by Souvik Ganguly on 19th July, 2021
	WebElement btn_DeleteReceipt;
	
	@FindBy(id = "editAppointment_1")
	WebElement link_editAppointment;							//Added by Yatharth Pandya on 20th August, 2020
	
	@FindBy(id = "btn_SaveAppointmentActivityDetails")
	WebElement btn_saveAppointment;								//Added by Yatharth Pandya on 20th August, 2020
	
	@FindBy(id = "AGRMZ6")
	WebElement checkBox_CaseManager;							//Added by Yatharth Pandya on 20th August, 2020
	
	@FindBy(id = "appttype")
	WebElement dropdown_appointmentCategory;					//Added by Yatharth Pandya on 20th August, 2020
	
	@FindBy(id = "addAppointment_1")
	WebElement link_addAppointment;								//Added by Yatharth Pandya on 20th August, 2020
	
	@FindBy(id = "CancelApprovedButton")											//Added by Yatharth Pandya on 18th August,2020
	WebElement btn_cancelApprovedButton;
	
	@FindBy(id = "SaveApprovedButton")											//Added by Yatharth Pandya on 18th August,2020
	WebElement btn_saveApprovedButton;
	
	@FindBy(xpath = "//span[contains(text(),'Invalid Receipt Number format.')]")		//Added by Yatharth Pandya on 17th August, 2020
	WebElement txt_receiptNumberInvalid;
	
	@FindBy(xpath = "//h1[contains(text(),'Case Receipt Numbers')]")
	WebElement txt_caseReceiptNumbers;									//Added by Yatharth Pandya on 17th Aug, 2020
	
	@FindBy(id = "Cancel1")
	WebElement btn_cancel;								//Added by Yatharth Pandya on 14th Aug,2020
		
	@FindBy(xpath = "//div[@id='NoticeDocContainer_2']/parent::div//button[contains(@id,'SaveButton')]")
	WebElement btn_continue;									//Added by Yatharth on 13th Aug,2020
	
	@FindBy(xpath = "//span[contains(text(),'Add Receipt/Notice Documents')]")
	WebElement link_addReceiptNoticeDocuments;						//Added by Yatharth Pandya on 13th Aug,2020
	
	@FindBy(xpath = "//div[@id='caseApprovedConfirmPopup'][@class='modal md-effect-1 md-show']")
    WebElement popup_caseApproved;                            //Added by Nitisha Sinha on 25th Aug, 2020
	
	@FindBy(xpath = "//span[contains(text(),'Download Document')]")
    WebElement icon_downloadDocument;                            //Added by Nitisha Sinha on 21th Aug, 2020
	
	@FindBy(xpath = "//div[@id='removeConfirmPopUp']//button[contains(@id,'SaveButton')]")
    WebElement btn_confirmDocumentDelete;                            //Added by Nitisha Sinha on 19th Aug, 2020
	
	@FindBy(xpath = "//span[@aria-label='Close']")
    WebElement icon_deleteDocument;                            //Added by Nitisha Sinha on 19th Aug, 2020
	
	@FindBy(id = "uploadInputButton_1")
    WebElement btn_browseDocumentInput;                            //Added by Nitisha Sinha on 19th Aug, 2020
	
	@FindBy(id = "uploadDivElement_1")
    WebElement btn_browseDocument;                            //Added by Nitisha Sinha on 19th Aug, 2020

	@FindBy(xpath = "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//a[@data-toggle='dropdown']")
    WebElement dropdown_mainReceiptActionMenu;                                          //Added by Nitisha Sinha on 18th Aug, 2020

	@FindBy(id = "SaveMakeMainButton")
    WebElement btn_confirmMakeMainReceipt;                            //Added by Nitisha Sinha on 18th Aug, 2020

	@FindBy(xpath = "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//div[@class='form-check checkbox-nice checkbox-inline']/span")
    WebElement txt_mainReceipt;                                        //Added by Nitisha Sinha on 18th Aug, 2020
	
	@FindBy(xpath = "//div[@id='documentPreviewModalFooter']//button[contains(text(),'Close')]")
    WebElement btn_closeDocumentPreviewPopup;                            //Added by Nitisha Sinha on 18th Aug, 2020
	
	@FindBy(xpath = "//a[contains(@id,'Download_GlobalAction')]")
    WebElement tab_bulkDownload;                            //Added by Nitisha Sinha on 18th Aug, 2020
	
	@FindBy(id = "SaveAndSendEmailReceipt1")
    WebElement btn_saveAndSendEmail;                            //Added by Nitisha Sinha on 14th Aug, 2020
	
	@FindBy(xpath = "//a[contains(@id,'Email_GlobalAction')]")
    WebElement tab_bulkEmail;                            //Added by Nitisha Sinha on 14th Aug, 2020
	
	@FindBy(id="txtSub")                                 //Added by Nitisha Sinha on 14th August 2020
	WebElement txtbox_subjectForEmail;
	
	@FindBy(id="txtTo")                                 //Added by Nitisha Sinha on 14th August 2020
	WebElement txtbox_emailRecepient;
	
	@FindBy(id="btn_SendCaseEmail")                 //Added by Nitisha Sinha on 14th August 2020
	WebElement btn_sendEmail;
	
	@FindBy(id="details_1")                 //Added by Nitisha Sinha on 14th August 2020
	WebElement txtbox_receiptDetails;
	
	@FindBy(xpath = "//body/embed[@type='application/pdf']")                 //Added by Nitisha Sinha on 14th August 2020
	WebElement popup_print;
	
	@FindBy(xpath = "//a[contains(@id,'Print_GlobalAction')]")
    WebElement tab_bulkPrint;                            //Added by Nitisha Sinha on 14th Aug, 2020
	
	@FindBy(xpath = "//a[contains(@id,'Delete_GlobalAction')]")
    WebElement tab_bulkDelete;                            //Added by Nitisha Sinha on 13th Aug, 2020
	
	@FindBy(xpath = "//a[contains(@id,'actionMenu_GlobalAction')]//i")
    WebElement dropdown_globalActionMenu;                            //Added by Nitisha Sinha on 13th Aug, 2020
	
	@FindBy(id = "SaveButton")
    WebElement btn_confirmDelete;                            //Added by Nitisha Sinha on 13th Aug, 2020
	
	@FindBy(id = "receiptNumber")
    WebElement txtbox_receiptNumber;                            //Added by Nitisha Sinha on 13th Aug, 2020
	
	@FindBy(id = "receiptType")
    WebElement dropdown_receiptType;                            //Added by Nitisha Sinha on 12th Aug, 2020
	
	@FindBy(id = "receiptDataHeader")
    WebElement header_receiptData;                            //Added by Nitisha Sinha on 12th Aug, 2020
	
	@FindBy(xpath = "//a[contains(text(),'Add Receipt Number')]")
    WebElement btn_addReceiptNumber;                            //Added by Nitisha Sinha on 12th Aug, 2020                       
   
	@FindBy(id = "SaveChangesButton")
    WebElement btn_saveChanges;                            //Added by Nitisha Sinha on 25th Aug, 2020                       
   
	@FindBy(xpath = "//div[@id='changeToMMR'][@class='modal md-effect-1 md-show']")
    WebElement popup_acceptChanges;                            //Added by Nitisha Sinha on 25th Aug, 2020                       
   
    @FindBy(id = "SaveButton_OGxjpvNAGRMZ21597301006")
    WebElement btn_confirm;                                    //Added by Yatharth on 13th Aug,2020
	
    @FindBy(id = "SaveReceipt1")
    WebElement btn_saveReceipt;                                //Added by Yatharth Pandya on 13th Aug,2020
	
	@FindBy(xpath = "//h4[contains(text(),'Receipt Details')]")
	WebElement txt_receiptDetails;									//Added by Yatharth Pandya on 13th Aug,2020
	
	@FindBy(id = "txtWithdrawDate")
	WebElement textBox_withdrawnDate;
	
	@FindBy(id = "txtSentDate")
	WebElement textBox_dateSentToGovtAgent;
	
	@FindBy(id = "txtReceiptNoticeDate")
	WebElement textBox_noticeDate;
	
	@FindBy(css = "input[id='btn_SaveCaseReceiptnumberinfo'][value='Save']")
	WebElement btn_SaveEdittedReceipt;
	
	@FindBy(id = "txtValidTillDate")
	WebElement textBox_ValidTillDate;
	
	@FindBy(id = "txtValidFromDate")
	WebElement textBox_ValidFromDate;
	
	@FindBy(id = "txtApprDate")
	WebElement textBox_ApprovedOnDate;
	
	@FindBy(css = "input[id='btn_SaveCaseReceiptnumberinfo'][value='Save']")
	WebElement btn_SaveReceipt;
	
	@FindBy(id = "cboRecptStatus")
	WebElement dropDown_ReceiptStatus;
	
	@FindBy(id = "txtReceiptNum")
	WebElement textBox_ReceiptNumber;
	
	@FindBy(id = "txtSentDate")
	WebElement textBox_SendDate;
	
	@FindBy(id = "txtReceiptDate")
	WebElement textBox_ReceiptDate;
	
	
	@FindBy(id = "cboRcptType")
	WebElement dropDown_ReceiptType;
	
	@FindBy(id = "btn_AddNewCaseReceiptNumber")
	WebElement btn_AddNewCaseReceiptNumber;
	
	public CM_ReceiptNumbersPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_ReceiptNumbersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}
	
	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
	 public void clickAddNewCaseReceiptNoBtn(String receiptType, String receiptSendDate, String receiptDate, String receiptNumber, String receiptStatus, boolean screenshot) throws Exception {
	        /*
	        * Created By : M Likitha Krishna
	        * Created On : 17 Oct 2019
	        */
	        Utils.clickElement(driver, btn_AddNewCaseReceiptNumber, "add new receipt number button");
	        Utils.waitForAllWindowsToLoad(2, driver);
	        Utils.switchWindows(driver, "INSZoom.com - Add New Case Receipt Number", "title", "false");
	        Utils.selectOptionFromDropDownByVal(driver, receiptType, dropDown_ReceiptType);
	        Utils.enterText(driver, textBox_SendDate, receiptSendDate, "receipt send date");
	        Utils.enterText(driver, textBox_ReceiptDate, receiptDate, "receipt date text");
	        Utils.enterText(driver, textBox_ReceiptNumber, receiptNumber, "receipt number");
	        //Utils.selectOptionFromDropDownByVal(driver, receiptStatus, dropDown_ReceiptStatus);
	        Utils.selectOptionFromDropDown(driver, receiptStatus, dropDown_ReceiptStatus);
	        Utils.clickElement(driver, btn_SaveReceipt, "save button");
	        Utils.waitForAllWindowsToLoad(1, driver);
	        Utils.switchWindows(driver, "INSZOOM.com - Case Receipt List", "title", "false");
	  }



	
	
	
	public void verifyReceiptNumber(String receiptNumber, Boolean screenShot){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 18 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ receiptNumber +"')]", "xpath", receiptNumber, "receipt number");
	}
	

	
	public void editMainReceipt(String dateSentToGovtAgent, String receiptNumber, String edittedReceiptNumber,String editReceiptDateTxt, String receiptNoticeDate, String validFromDate,String validTillDate, String sheetName,String withdrawnDate) throws Exception {
		/*
 		 * Edited By : Saurav Purohit
 		 * Edited On : 17/06/21
 		 * Modified this method : Reason : If the Receipt is not main receipt , it will be changed to a main receeipt before Editing. This
 		 * is required if more than 1 receipt is present .
 		 */
		Utils.waitForElement(driver, "//a[contains(text(),'"+ receiptNumber +"')]/../following-sibling::td[9]", globalVariables.elementWaitTime, "xpath");
		
		if(driver.findElements(By.xpath("//a[contains(text(),'"+ receiptNumber +"')]/../following-sibling::td[9]/a")).size()>0){
			Log.message("This Receipt is not Main Receipt . Hence converting it to main Receipt");
			WebElement lnk_MakeMainReceipt = driver.findElement(By.xpath("//a[contains(text(),'"+ receiptNumber +"')]/../following-sibling::td[9]"));
			Utils.scrollToElement(driver, lnk_MakeMainReceipt);
			Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ receiptNumber +"')]/../following-sibling::td[9]", "xpath", "Make Main Receipt Link");
		}
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ receiptNumber +"')]/../../td/a/img[@title='Edit Case Main Receipt Info']", "xpath", "Edit icon");	
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Receipt Number Info", "title", "false");
		Utils.enterText(driver, textBox_dateSentToGovtAgent, dateSentToGovtAgent, "date sent to govt agent");
		Utils.enterText(driver, textBox_ReceiptNumber, edittedReceiptNumber, "edittedReceiptNumber");
		Utils.enterText(driver, textBox_ReceiptDate, editReceiptDateTxt, "receipt date");	
		Utils.enterText(driver, textBox_noticeDate, receiptNoticeDate, "receipt notice date");
		Utils.selectOptionFromDropDownByVal(driver, "1", dropDown_ReceiptStatus);
		while (isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		Utils.enterText(driver, textBox_ApprovedOnDate, validFromDate, "Approved On");
		Utils.enterText(driver, textBox_ValidFromDate, validFromDate, "valid from date");
		Utils.enterText(driver, textBox_ValidTillDate, validTillDate, "valid till date");
		//Utils.enterText(driver, textBox_withdrawnDate, withdrawnDate, "withdrawn date");
		Utils.clickElement(driver, btn_SaveEdittedReceipt, "Save Editted Receipt");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZOOM.com - Case Receipt List", "title", "false");			

	}
	
	
	public void selectReceiptNumber(String receiptNumber)
	{
		//Created by Yatharth on 13th August,2020
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]", "xpath", "Receipt Number");
		Utils.waitForElement(driver, txt_receiptDetails);	
	}
	
	
	public void verifyAddedReceipt(String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 12/08/2020
         */
		if(Utils.waitForElement(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]", globalVariables.elementWaitTime, "xpath"))
		{
			Log.pass("Receipt Number " + receiptNumber + " is present in the receipt list");
		}
		else
		{
			Log.fail("Receipt Number " + receiptNumber + " is not present in the receipt list");
		}
	}

	
	public void clickOnReceiptNumber(String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 12/08/2020
         */
		
		if(Utils.isElementPresent(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]", "xpath"))
		{
			Utils.clickDynamicElement(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]", "xpath", "Receipt Number " + receiptNumber);
		}
		else
		{
			Log.fail("Receipt Number " + receiptNumber + " is not present in the receipt list");
		}
	}
	
	
	public void verifyUploadedDocument(String docName, String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 12/08/2020
         */
		if(Utils.isElementPresent(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[contains(text(),'" + docName + "')]", "xpath"))
		{
			Log.pass("The document is uploaded");
		}
		else
		{
			Log.fail("The document is not uploaded");
		}
	}
	
	
	public void expandReceipt(String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 12/08/2020
         */
		
		Utils.scrollToDynamicElement(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]/ancestor::div[2]//i", "xpath", "expand " + receiptNumber);
		
		Utils.clickDynamicElement(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]/ancestor::div[2]//i", "xpath", "expand " + receiptNumber);
	}
	
	
	public void backToCaseProfilePage() throws Exception
    {
        /*
         * Created By : Yatharth Pandya on 8th june, 2020
         * Modified by : Nitisha Sinha on 10th June 2020
         */
    	driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
        Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
       
    }
	
	
	public void saveReceipt() throws InterruptedException
    {
        //Created by Yatharth Pandya on 13th August,2020
		//Modified by Nitisha Sinha on 19th August, 2020
		//Modification Done: Added logic to handle the loader
       
        Utils.clickElement(driver, btn_saveReceipt, "Save");
        
        Utils.waitForElementPresent(driver, "//div[@class='load-more-loader']", "xpath");
        Utils.waitForElementNotVisible(driver, "//div[@class='load-more-loader']", "xpath");
        
        Utils.waitForElement(driver, txt_caseReceiptNumbers);
    }
	
	
    public void addReceiptDetails(String receiptType, String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 12/08/2020
         */
    	
    	Utils.clickElement(driver, btn_addReceiptNumber, "Add Receipt Number button");
    	
    	Utils.waitForElement(driver, header_receiptData);
    	
    	Utils.clickElement(driver, dropdown_receiptType, "Receipt Type dropdown");
    	
    	Utils.selectOptionFromDropDownByVal(driver, receiptType, dropdown_receiptType);
    	
    	Utils.enterText(driver, txtbox_receiptNumber, receiptNumber, "Receipt Number Textbox");
    	
    }
    
    
    public void verifyDuplicateReceipt()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	if(Utils.isElementPresent(driver, "//span[contains(text(),'This receipt number already exists.')]", "xpath"))
    	{
    		Log.pass("Verified that the duplicate receipts cannot be added");
		}
		else
		{
			Log.fail("Failed to verify that the duplicate receipts cannot be added");
		}
    }
    
    
    public void deleteReceipt(String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	
    	Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Delete Receipt')]", "xpath", "Delete button for " + receiptNumber);
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Delete Receipt')]", "xpath", "Delete button for " + receiptNumber);

    	Utils.clickElement(driver, btn_confirmDelete, "Confirm delete button");
    	
    	Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success show']", "xpath");
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
    }
    
    
    public void verifyDeleteReceipt(String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	
    	Utils.verifyIfDataNotPresent(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]", btn_addReceiptNumber, "xpath", receiptNumber, "receipt details list");
    }
    
    
    public void clickActionMenu(String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */

    	Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[@data-toggle='dropdown']", "xpath", "Action menu for " + receiptNumber);

    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[@data-toggle='dropdown']", "xpath", "Action menu for " + receiptNumber);
    }
    
    
    public void clickGlobalActionMenu()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	
    	Utils.clickElement(driver, dropdown_globalActionMenu, "Global Action Menu dropdown");
    }
    
    
    public void selectReceipt(String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	
    	Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]/parent::div/label", "xpath", "checkbox for " + receiptNumber);
    	
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]/parent::div/label", "xpath", "checkbox for " + receiptNumber);
    }
    
    
    public void clickBulkDeleteReceipt()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 13/08/2020
         */
    	
    	Utils.clickElement(driver, tab_bulkDelete, "Receipt Bulk Delete tab");
    	
    	Utils.clickElement(driver, btn_confirmDelete, "Confirm delete button");
    	
    	Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success show']", "xpath");
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
    }
    
    
    public void clickBulkPrintReceipt()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Utils.clickElement(driver, tab_bulkPrint, "Receipt Bulk Print tab");
    }
    
    
    public void verifyPrintReceipt() throws Exception
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	   	
    	Utils.waitForAllWindowsToLoad(3, driver);
    	
    	String winHandleBefore = driver.getWindowHandle();

    	for(String winHandle : driver.getWindowHandles()){
    	    driver.switchTo().window(winHandle);
    	}
    	
    	if(Utils.waitForElement(driver, popup_print))
    	{
    		Log.pass("Successfully verified the appearance of print tab");
    	}else{
    		Log.fail("Verification of the appearance of print tab up failed");
    	}

    	driver.close();

    	driver.switchTo().window(winHandleBefore);
    }
    
    
    public void printReceipt(String receiptNumber)
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Print')]", "xpath", "Print button for " + receiptNumber);
    }
    
    
    public void editReceipt()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		
		globalVariables.receiptNoticeDetails = globalVariables.receiptNoticeDetails + strDate;
    	
		txtbox_receiptDetails.clear();
		
    	Utils.enterText(driver, txtbox_receiptDetails, globalVariables.receiptNoticeDetails, "Receipt notice details");
    }
    
    
    public void verifyEditReceipt()
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + globalVariables.receiptNoticeDetails + "')]", "xpath", "details edited", "Receipt Notice Details");
    }
    
    
    public void emailReceipt() throws Exception
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */

    	Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Email Case Receipt", "title", "false");
		
		Utils.enterText(driver, txtbox_emailRecepient, "demo@demo.k", "email Recepient textbox");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		
		globalVariables.rmEmailSubject = globalVariables.rmEmailSubject + strDate;
		
		txtbox_subjectForEmail.clear();
		Utils.enterText(driver, txtbox_subjectForEmail, globalVariables.rmEmailSubject, "Subject for email");
		
		Utils.clickElement(driver, btn_sendEmail, "Send Email button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Receipt Details - INSZoom.com", "title", "false");
		
    }
    
    
    public void clickEmailReceipt(String receiptNumber) throws Exception
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    
    	Utils.waitForElementPresent(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Email Receipt')]", "xpath");
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Email Receipt')]", "xpath", "Email Receipt button for " + receiptNumber);
    
    }
    
    
    public void clickBulkEmailReceipt() throws Exception
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Utils.clickElement(driver, tab_bulkEmail, "Bulk Email Receipt tab");
    	
    }
    
    
    public void clickSaveAndSendEmail() throws Exception
    {
    	/*
         * Created By: Nitisha Sinha
         * Created On: 14/08/2020
         */
    	
    	Utils.clickElement(driver, btn_saveAndSendEmail, "Save and Send Email button");
    	
    }
    
    
    public void verifyIfDownloaded(int oldNumberOfFiles)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		int updatedNumberOfFiles = Utils.getAllDownloadedFiles().length;
		
		if(updatedNumberOfFiles == oldNumberOfFiles + 1)
		{
			Log.pass("", driver, true);
			Log.pass("All file/s are downloaded");
		}
		
		else
		{
			Log.fail("Files are not downloaded as number of files after download are: " + updatedNumberOfFiles + " whereas before download the number of files were: " + oldNumberOfFiles, driver, true);
		}
	}
	
    
    public static File[] getAllDownloadedFiles()
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 11/11/2019
         * 
         */
		
		String home = System.getProperty("user.home");

		File folder = new File(home + File.separatorChar + "Downloads" + File.separatorChar);
		Log.message("Downloading Directory :" + home + File.separatorChar + "Downloads" + File.separatorChar);
		File[] listOfFiles = folder.listFiles();
		
		return listOfFiles;

	}
    
	
    public void clickBulkDownload(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 18th August 2020
		 * 
		 */
    	
    	Utils.openDownloadsWindow(remoteDriver);
		

    	Utils.clickElement(driver, dropdown_globalActionMenu, "Global Action Menu dropdown");
    
    	Utils.clickElement(driver, tab_bulkDownload, "Bulk Download button");
		
		Utils.waitForElement(driver, "//span[contains(text(), 'Preparing Download')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Preparing Download')]", "xpath");

		Thread.sleep(5000);
    	
		
	}
    
    
    public void previewDocument(String docName, String docNameWithoutExtension, String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 18/08/2020
         */
	
		Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[contains(text(),'" + docName + "')]", "xpath", docName + " in " + receiptNumber);
		
		Utils.waitForElementPresent(driver, "//div[@id='documentPreviewModalHeader']//h4[contains(text(),'" + docNameWithoutExtension + "')]", "xpath");
		
		Utils.clickElement(driver, btn_closeDocumentPreviewPopup, "Button for Close Preview popup for Document");
		
	}
    
    
    public void checkMainReceipt()
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 18/08/2020
         */
    	
    	String mainReceipt = txt_mainReceipt.getText();
    	
    	if(!mainReceipt.equals(globalVariables.mainReceipt))
    	{
    		globalVariables.secondaryReceipt = globalVariables.mainReceipt;
    		globalVariables.mainReceipt = mainReceipt;
    	}
	}
    
    
    public void makeMainReceipt(String receiptNumber)
	{
		/*
         * Edited By: Souvik Ganguly
         * Edited On: 19/07/2021
         */
	
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Make Main Receipt')]", "xpath", "make main receipt for " + receiptNumber);

    	Utils.clickElement(driver, btn_confirmMakeMainReceipt, "Confirm make main reciept button");
    	
    	if(Utils.waitForElement(driver, popup_acceptChanges))
    	{
    		Utils.clickElement(driver, btn_saveChanges, "YES");
    	}
    	
    	if(Utils.waitForElement(driver, popup_caseApproved))
    	{
    		Utils.clickElement(driver, btn_saveApprovedButton, "Yes");
    	}
    	Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg show", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "xpath");
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
    
	}
    
	
    public void verifyMakeMainReceipt()
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 18/08/2020
         */
    	
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//span[contains(text(),'" + globalVariables.secondaryReceipt + "')]", "xpath", globalVariables.secondaryReceipt, "main Receipt");
	}
    
    public void verifyMainReceiptCreated(String receiptNumber)
	{
		/*
         * Created By: Souvik Gaguly
         * Created On: 19/07/2021
         */
    	
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//span[contains(text(),'" + receiptNumber + "')]", "xpath", receiptNumber, "main Receipt");
	}
    
    
    
    public void verifyDeleteMainReceipt(String receiptNumber)
	{
		/*
         * Edited By: Souvik Ganguly
         * Edited On: 19/07/2021
         */
    	
    	Utils.clickElement(driver, dropdown_mainReceiptActionMenu, "Action menu for main receipt");
    	
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Delete Receipt')]", "xpath", "Delete Main Receipt");
    	
    	Utils.waitForElement(driver, btn_DeleteReceipt);
    	
    	Utils.clickElement(driver, btn_DeleteReceipt, "Delete receipt button");
    	
    	Utils.waitForElementNotVisible(driver,"//button[text()='Delete Receipt']", "xpath");    	
    	
    	Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//span[contains(text(),'" + receiptNumber + "')]",null ,"xpath", "Main receipt", "list");
    	
    	//Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Delete Receipt')]//ancestor::li[1][@class='zoom-action-dropdown-list forbidden']", "xpath", "Delete main receipt is diabled", globalVariables.secondaryReceipt);
    }
    
    
    
    
    
    
    public void uploadDocument(String filePath) throws InterruptedException
	{
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 19/08/20
		  * 
		  */

    	//Utils.clickElement(driver, btn_UploadFile, "Upload File");
    	filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
		}
		File path = new File(filePath);		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);
		
		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString());

		try {
			Utils.waitForElement(driver, btn_browseDocument);
//			btn_browseDocumentInput.sendKeys(path.getAbsoluteFile().toString());
			
			WebElement ele = driver.findElement(By.id("uploadInputButton_1"));
		    ((RemoteWebElement) ele).setFileDetector(detector);
		    
			ele.sendKeys(file.getAbsolutePath());
		
			Log.message("Entered the absolute file path in the Upload Button");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}

		
		Utils.waitForElementPresent(driver, "//span[@title='" + globalVariables.rmDocument + "']", "xpath");
		Utils.verifyIfDataPresent(driver, "//span[@title='" + globalVariables.rmDocument + "']", "xpath", "doc", "doc");
		//Thread.sleep(10000);
	}
    
    
    public void clickEditReceipt(String receiptNumber)
    {
    	/*
		  * Created By : Nitisha Sinha
		  * Created On : 19/08/20
		  * 
		  */
    	
    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Edit')]", "xpath", "Edit button for " + receiptNumber);
    	
    	Utils.waitForElement(driver, header_receiptData);
    	
    }
    
    
    public void deleteDocument()
    {
    	/*
		  * Created By : Nitisha Sinha
		  * Created On : 19/08/20
		  * 
		  */
    	
    	Utils.scrollToElement(driver, icon_deleteDocument);
    	
    	Utils.clickElement(driver, icon_deleteDocument, "delete Document icon");
    	
    	Utils.clickElement(driver, btn_confirmDocumentDelete, "Confirm Button to Delete the document");
    	
    	Utils.waitForElement(driver, btn_browseDocument);
    }
    
    
    public void verifyDeletedDocument(String docName, String receiptNumber)
	{
		/*
         * Created By: Nitisha Sinha
         * Created On: 19/08/2020
         */
		if(Utils.isElementPresent(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[contains(text(),'" + docName + "')]", "xpath"))
		{
			Log.fail("The document is not successfully deleted");
		}
		else
		{
			Log.pass("The document is successfully deleted");
		}
	}
    
    
    public void clickDownload(RemoteWebDriver remoteDriver) throws Exception 
	{
		
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 21/08/2020
		 */
    	
    	Utils.openDownloadsWindow(remoteDriver);
    	
    	Utils.waitForElement(remoteDriver, icon_downloadDocument);
    	
    	Utils.scrollToElement(remoteDriver, icon_downloadDocument);
		
		Utils.clickElement(driver, icon_downloadDocument, "Download Document icon");
		
		Utils.waitForElement(driver, "//span[contains(text(), 'Preparing Download')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Preparing Download')]", "xpath");

		Thread.sleep(5000);
		
	}
    
    
	public void verifyDownload(RemoteWebDriver remoteDriver) throws Exception 
	{
		
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 21/08/2020
		 */ 
				
		Utils.verifyIfDownloaded(remoteDriver);
		
	}
	
	
	public void clickAddReceiptNoticeDocuments() throws InterruptedException
	{
		//Created by Yatharth Pandya on 13th August,2020	
		Utils.scrollToElement(driver, link_addReceiptNoticeDocuments);
		Utils.clickElement(driver, link_addReceiptNoticeDocuments, "Link add Receipt");
	}
	
	
	public void deleteReceiptType(String number) throws InterruptedException
	{
		//Created by Yatharth Pandya on 13th August, 2020
		
		Utils.clickDynamicElement(driver, "//h4//i[@id='DeleteNotice_"+number+"']", "xpath", "Delete Icon");
		Utils.clickElement(driver, btn_continue, "Confirm delete");
		saveReceipt();
	}
	
	
	public void deleteAllReceiptType() throws InterruptedException
	{
		//Created by Yatharth Pandya on 13th August,2020
		
		while(Utils.isElementPresent(driver, "//h4//i[@id='DeleteNotice_2']", "xpath"))
		{
			deleteReceiptType("2");
			clickOnReceiptNumber("4321");
		}
		saveReceipt();
	}
	
	
	public void selectReceiptNoticeType(String noticeType, int number) throws Exception
	{
		//Created by Yatharth Pandya on 13th August,2020
		//Modified by Nitisha Sinha on 25th Aug,2020
		//Modification done: Made the function more generic
		
		WebElement dropdown_receiptType1 = driver.findElement(By.xpath("//select[@id='noticeDocType_" + number + "']"));
		
		Utils.selectOptionFromDropDown(driver, noticeType, dropdown_receiptType1);
	}
	
	
	public void verifyStatus(String statusValue)
	{
		//Created by Yatharth Pandya on 13th August, 2020
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+statusValue+"')]", "xpath", statusValue, "Receipt Details page");
	}
	
	
	public void clickCancel()
	{
		//Created by : Yatharth Pandya
		//Created on : 14th August, 2o2o
		
		Utils.clickElement(driver, btn_cancel, "Cancel");
		Utils.waitForElement(driver, txt_caseReceiptNumbers);
	}
	
	
	public void selectReceiptType(String receiptType) throws Exception
	{
		//Created by : Yatharth Pandya
		//Created on : 17th August, 2020
		
		Utils.selectOptionFromDropDown(driver, receiptType, dropdown_receiptType);
	}
	
	
	public void verifyReceiptNumberFormat(String wrongReceiptNumber, String rightReceiptNumber,String defaultReceiptType) throws Exception
	{
		//Created by : Yatharth Pandya
		//Created on : 17th Aug,2020

		Utils.clickElement(driver, txtbox_receiptNumber, "Receipt Number TextBox.");
		txtbox_receiptNumber.clear();
		txtbox_receiptNumber.sendKeys(wrongReceiptNumber);
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Invalid Receipt Number format.')]", "xpath", "INVALID DATA", "Receipt Number Field");
		Utils.clickElement(driver, txtbox_receiptNumber, "Receipt Number TextBox.");
		txtbox_receiptNumber.clear();
		txtbox_receiptNumber.sendKeys(rightReceiptNumber);
		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Invalid Receipt Number format.')]", txt_receiptDetails, "xpath", "INVALID DATA", "Receipt Number Field");
		Utils.selectOptionFromDropDown(driver, defaultReceiptType, dropdown_receiptType);
		Utils.clickElement(driver, txtbox_receiptNumber, "Receipt Number TextBox.");
		txtbox_receiptNumber.clear();
		txtbox_receiptNumber.sendKeys(wrongReceiptNumber);
		
	}
	
	
	public void fillDate(String textBoxId, String date)
	{
		//Created by : Yatharth Pandya 
		//Created on : 17th August, 2020
		
		WebElement textBox_date = driver.findElement(By.xpath("//input[contains(@id,'"+ textBoxId +"')]")); 
		Utils.clickElement(driver, textBox_date, "Date Input field");
		textBox_date.clear();
		textBox_date.sendKeys(date);	
	}
	
	
	public void selectCaseStatusFromApprovalNotice(String caseStatusApproved)
    {
        //Created by : Yatharth Pandya
        //Created on : 18th August, 2020
       
            if(caseStatusApproved == "NO")
            {
                Utils.clickElement(driver, btn_cancelApprovedButton, "No");
                Utils.waitForElementPresent(driver, "//div[@class='load-more-loader']", "xpath");
                Utils.waitForElementNotVisible(driver, "//div[@class='load-more-loader']", "xpath");
            }
            else if(caseStatusApproved == "YES")
            {
                Utils.clickElement(driver, btn_saveApprovedButton, "Yes");
                Utils.waitForElementPresent(driver, "//div[@class='load-more-loader']", "xpath");
                Utils.waitForElementNotVisible(driver, "//div[@class='load-more-loader']", "xpath");
            }
       
    }
	
	
	public void addAndVerifyAddAppointment(String appointmentCategory) throws Exception
	{
		//Created by : Yatharth Pandya
		//Created on : 20th August, 2020
		
		Utils.clickElement(driver, link_addAppointment, "Add Appointment");
    	String winHandleBefore = driver.getWindowHandle();

    	for(String winHandle : driver.getWindowHandles())
    	{
    	    driver.switchTo().window(winHandle);
    	}
		Utils.selectOptionFromDropDown(driver, appointmentCategory, dropdown_appointmentCategory);
		//Utils.clickElement(driver, checkBox_CaseManager, "Case manager for appointment");
		Utils.clickElement(driver, btn_saveAppointment, "Save Appointment");
		if(Utils.isAlertPresent(driver))
		{
			driver.switchTo().alert().accept();
			//Thread.sleep(10000);
			driver.switchTo().window(winHandleBefore);
		}
		
//		if(Utils.isAlertPresent(driver))
//		{
//			driver.switchTo().alert().accept();
//		}
//		
//		driver.switchTo().window(winHandleBefore);
//		
		Utils.waitForElement(driver, link_editAppointment);
		Utils.verifyIfDataPresent(driver, "editAppointment_1", "id", "Edit Appointment link", "Receipt details page");
		
	}
	
	
	public void editReceiptNumber(String receiptNumber, String edittedReceiptNumber) throws Exception{
        /*
          * Created By : M Likitha Krishna
          * Created On : 19 Aug 2020
          */
        Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ receiptNumber +"')]/../../td/a/img[@title='Edit Case Main Receipt Info']", "xpath", "Edit icon");
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - Edit Case Receipt Number Info", "title", "false");
        Utils.enterText(driver, textBox_ReceiptNumber, edittedReceiptNumber, "edittedReceiptNumber");
        Utils.clickElement(driver, btn_SaveEdittedReceipt, "Save Editted Receipt");
        Utils.waitForAllWindowsToLoad(1, driver);
        Utils.switchWindows(driver, "INSZOOM.com - Case Receipt List", "title", "false");       
    }
	
	public void addNewReceiptDetails(String receiptType, String receiptNumber)
    {
    	/*
         * Created By: Souvik Ganguly
         * Created On: 19/07/2021
         */
		
		try{
    	//Adding Receipt Details
    	Utils.clickElement(driver, btn_addReceiptNumber, "Add Receipt Number button");
    	
    	Utils.waitForElement(driver, header_receiptData);
    	
    	Utils.clickElement(driver, dropdown_receiptType, "Receipt Type dropdown");
    	
    	Utils.selectOptionFromDropDownByVal(driver, receiptType, dropdown_receiptType);
    	
    	Utils.enterText(driver, txtbox_receiptNumber, receiptNumber, "Receipt Number Textbox");
    	
    	//Saving Receipt Number
    	
        Utils.clickElement(driver, btn_saveReceipt, "Save");
        
        Utils.waitForElementPresent(driver, "//div[@class='load-more-loader']", "xpath");
        Utils.waitForElementNotVisible(driver, "//div[@class='load-more-loader']", "xpath");
        
        Utils.waitForElement(driver, txt_caseReceiptNumbers);
        
        //Verifying added receipt
        
        if(Utils.waitForElement(driver, "//div[@id='ReceiptData']//span[contains(text(),'" + receiptNumber + "')]", globalVariables.elementWaitTime, "xpath"))
		{
			Log.pass("Receipt Number " + receiptNumber + " is present in the receipt list");
		}
		else
		{
			Log.fail("Receipt Number " + receiptNumber + " is not present in the receipt list");
		}
		}
		
		catch (Exception e)
		{
			Log.fail("Unable to add new receipt. ERROR :\n\n " + e.getMessage(), driver, true);
		}
    	
    }
	
	 public void changeToMainReceipt(String receiptNumber)
	    {
	    	/*
	         * Created By: Souvik Ganguly
	         * Created On: 19/07/2021
	         */
           
		    try{
		    //Click on Action Button
	    	Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[@data-toggle='dropdown']", "xpath", "Action menu for " + receiptNumber);

	    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//a[@data-toggle='dropdown']", "xpath", "Action menu for " + receiptNumber);
	    	
	    	//Make main receipt
	    	Utils.clickDynamicElement(driver, "//span[contains(text(),'" + receiptNumber + "')]//ancestor::div[@class='row ReceiptDetails']//label[contains(text(),'Make Main Receipt')]", "xpath", "make main receipt for " + receiptNumber);

	    	Utils.clickElement(driver, btn_confirmMakeMainReceipt, "Confirm make main reciept button");
	    	
	    	//Verify main receipt created
	    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Main Receipt')]//ancestor::div[@class='row ReceiptDetails']//span[contains(text(),'" + receiptNumber + "')]", "xpath", receiptNumber, "main Receipt");
	    	
	    	
	    }
		    
		    catch (Exception e)
		    {
		    	Log.fail("Unable to change to main receipt. ERROR :\n\n " + e.getMessage(), driver, true);
		    }
	    }
	
}


