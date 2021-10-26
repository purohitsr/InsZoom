package com.inszoomapp.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class FNP_CaseProfilePage extends LoadableComponent<FNP_CaseProfilePage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(id="chkAttach")
	WebElement checkbox_attachFile;
	
	@FindBy ( xpath = "//a[@href='#tab-documents']")              //Added by Nitisha Sinha on 6/02/2020
	WebElement tab_caseDocumentation;
	
	@FindBy ( xpath = "//h2[contains(text(),'Case Details')]")              //Added by Nitisha Sinha on 10/02/2020
	WebElement waitElement_caseDetails;
	
	@FindBy ( xpath = "//a[@href='#tab-details']")              //Added by Nitisha Sinha on 6/02/2020
	WebElement tab_caseDetailsOrDates;
	 
	@FindBy ( xpath = "//a[contains(text(),'Case Steps')]")   //Added by Kuchinad Shashank on 13/02/2020
	WebElement tab_caseSteps;
	
	@FindBy(xpath="//div[@id='InboxMessages']//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;
	
	@FindBy(id="btn_AttachtoEmail")
	WebElement btn_done;
	
	@FindBy(id="btn_AttachFileToEmail")
	WebElement btn_attach;
	
	@FindBy(id="txtFileUpload")
	WebElement btn_UploadFileInput;
	
	@FindBy(id="btn_AddDeleteAttachment")
	WebElement btn_AddDeleteAttachment;
	
	@FindBy(id="composeNewEmailButton")
	WebElement btn_composeEmail;
	
	@FindBy(id="btn_SendCaseRelatedEmail")
	WebElement btn_SendEmail;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="txtMsg")
	WebElement txtbox_Message;
	
	@FindBy(css="textarea[name='txtTo']")
	WebElement txtbox_To;
	
	@FindBy(css="a[href='#tab-documents']")
	WebElement tab_Documentation;
	
	@FindBy(xpath = "//div[@id='ProcessingDetails']//tr[1]//a[contains(text(),'Completed Date')]")
	WebElement colName_CompletedDate;
	
	@FindBy(css="a[href='#tab-support']")
	WebElement tab_SupportingDocs;
	
	@FindBy(xpath = "//div[@id='ProcessingDetails']//tr[1]//a[contains(text(),'Estimated Date')]")
	WebElement colName_EstimatedDate;
	
	@FindBy(xpath = "//div[@id='ProcessingDetails']//tr[1]//a[contains(text(),'STEP STATUS')]")
	WebElement colName_StepStatus;
	
	@FindBy(xpath = "//div[@id='ProcessingDetails']//tr[1]//a[contains(text(),'STEP NAME')]")
	WebElement colName_StepName;	
	
	@FindBy(xpath = "//header[@class='main-box-header clearfix']/h2[contains(text(),'Notes')]")
	WebElement text_notes;	
	
	@FindBy(xpath = "//header[@class='main-box-header clearfix']/h2[contains(text(),'Emails')]")
	WebElement text_emails;	
	
	@FindBy(xpath = "//a[contains(text(),'Communication')]")
	WebElement tab_Communication;
	
	@FindBy(xpath = "//a[contains(text(),'Details/Dates')]")
	WebElement tab_DetailsDates;
	
	@FindBy(xpath = "//h2[contains(text(),'Case')]")	      //Created by Kuchinad Shashank on 10/02/2020
	WebElement waitElement_caseHeader; 
	
	@FindBy(xpath = "//h2[contains(text(),'DOCUMENTS')]")	      //Created by Nitisha Sinha on 13/02/2020
	WebElement waitElement_documentHeader; 
	
	public FNP_CaseProfilePage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("FNP case profile page didnt loaded");
		}
	}
	
	public void clickDetailsDatesTab() throws InterruptedException
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  * Modified By: Saurav
		  * Modified Date : 12/07/2021
		  * Added waitFOrLoading Completed and waitForAjax . Commented Thread.sleep .
		  */ 
//		Thread.sleep(10000);
	    Utils.waitForLoadingCompleted(driver);
	    
	    Utils.waitForAjax(driver);
	    
		Utils.clickElement(driver, tab_DetailsDates, "Details and Dates tab");
		
		Utils.waitForLoadingCompleted(driver);
		
		Utils.waitForElementPresent(driver, "//div[@id='tab-details']//div[@class='table-responsive']//img", "xpath");
		
    	Utils.waitForElementNotVisible(driver, "//div[@id='tab-details']//div[@class='table-responsive']//img", "xpath");
    	
	}
	
	public void clickCommunicationTab() throws InterruptedException
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */ 
		Utils.clickElement(driver, tab_Communication, "Communication tab");
		Thread.sleep(3000);
	}
	
	
	public void verifyDetailsDates(boolean screenshot) throws Exception {

		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */ 
		
		clickDetailsDatesTab();
		
		Utils.waitForAjax(driver);
		
		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Filing Details')]", "xpath", "Filing details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Decision Details')]", "xpath", "Decision Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Additional Application/ Receipt Details')]", "xpath", "Additional Application/ Receipt Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Visa Priority Date Info')]", "xpath", "Visa Priority Date Info header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Custom Details')]", "xpath", "Custom Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'LCA Details')]", "xpath", "LCA Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'SWA And DOL Info')]", "xpath", "SWA And DOL Info header", "under details and dates tab");

	}

	
	public void verifyReceiptNumber(String recieptNumber) throws InterruptedException
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */ 
		clickDetailsDatesTab();
		Utils.waitForAjax(driver);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Application / Receipt #')]/../td[2]/a[contains(text(),'"+ recieptNumber +"')]", "xpath", recieptNumber, "reciept Number");
	}
	
	
	public void verifyEmailHeader()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */ 
		Utils.verifyIfStaticElementDisplayed(driver, text_emails, "Emails header", "Communication Tab");
	}
	
	public void verifyNotesHeader()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */ 
		Utils.verifyIfStaticElementDisplayed(driver, text_notes, "Notes header", "Communication Tab");
	}
	
	
	public void verifyNotes(String notesDescription)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */ 
		Utils.verifyIfDataPresent(driver, "//div[@id='NotesComments']/div[contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "Under Notes");
	}
	
	public void verifyCaseName(String casePetitionName, String caseId)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */ 
		//Utils.verifyIfDataPresent(driver, "//td[text()='Case Name (Case ID)']/../td[2][contains(text(),'"+casePetitionName+"')]", "xpath", casePetitionName, "petiton name");
		Utils.verifyIfDataPresent(driver, "//td[text()='Case Name (Case ID)']/../td[2][contains(text(),'"+caseId+"')]", "xpath", caseId, "case Id");
	}
	
	
	public void verifyCaseStartDate(String caseStartDate)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */ 
		Utils.verifyIfDataPresent(driver, "//td[text()='Case Start Date']/../td[2][contains(text(),'"+ caseStartDate.trim() +"')]", "xpath", caseStartDate, "case start date");
	}
	
	public void verifyCaseStatus(String caseStatus)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */ 
		Utils.verifyIfDataPresent(driver, "//td[text()='Case Status']/../td[2][contains(text(),'"+ caseStatus +"')]", "xpath", caseStatus, "case status");
	}
	
	
	public void verifyCaseStepDetailsColumns()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 15 Nov 2019
		  */ 
		Utils.verifyIfStaticElementDisplayed(driver, colName_StepName, "Step Name ", "under Case details tab");
		Utils.verifyIfStaticElementDisplayed(driver, colName_StepStatus, "Step Status ", "under Case details tab");
		Utils.verifyIfStaticElementDisplayed(driver, colName_EstimatedDate, "Estimated Date ", "under Case details tab");
		Utils.verifyIfStaticElementDisplayed(driver, colName_CompletedDate, "Completed Date ", "under Case details tab");
	}
	
	
		public void clickSupportingDocsTab() throws InterruptedException
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 13 Nov 2019
		  */ 
		Utils.clickElement(driver, tab_SupportingDocs, "Supporting Docs tab");
		Utils.waitForElementPresent(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		
		Thread.sleep(3000);
		
		//Utils.waitUntillLoaderDisappearsInHRP(driver);
	}
	
	
	public void verifyIfDocChecklistAvailable(String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Nov 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//p[contains(text(), '" + docChecklistName + "')]", "xpath", docChecklistName, "Supporting Docs Tab");
	}
	
	
	public void verifyIfDocumentsAvailable(String fileNameNew)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Nov 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + fileNameNew + "')]", "xpath", fileNameNew, "Supporting Docs Tab");
	}
	
	
	public void clickDocumentationTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Nov 2019
		 */
		
		Utils.clickElement(driver, tab_Documentation, "'Documentation' tab");
	}
	
	
	public void verifyIfEmailPresent(String accessCodeEmailSubject)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 03 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + accessCodeEmailSubject + "')]", "xpath", accessCodeEmailSubject, "Received Email Subject");
	}
	
	
	public void verifySupportingDocs(String docName)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 05 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[@id='case-supportingdocs-casechecklist']//p/span[contains(text(),'"+ docName +"')]", "xpath", docName, "supporting docs under case in FNP");
	} 
 
	
	public void composeEmail(String sendEmailID, String sendMessage, String sendSubject) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Dec 2019
		 */
		
		Utils.clickElement(driver, btn_composeEmail, "'Compose Email'");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Case Related Email", "title", "false");
		
		Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
		
		Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
		
		Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
	}
	
	
	public void verifyIfEmailPresent(String sendEmailID, String sendMessage, String sendSubject) throws InterruptedException
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 08/06/2021
		 * 
		 */
		
		clickCommunicationTab();
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email ID");
		
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Email");
		
		
		Utils.verifyIfDataPresent(driver, "//div[@class='modal fade in' and contains(@style,'display: block;')]//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email popup");
				
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
		
	}
	
	
	public void composeEmailWithAttachment(String sendEmailID, String sendMessage, String sendSubject, String filePath, String fileName) throws Exception
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 08/06/2021
		 */
		
		Utils.clickElement(driver, btn_composeEmail, "'Compose Email'");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Case Related Email", "title", "false");
		
		Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
		
		Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
		
		Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
		
		Utils.clickElement(driver, btn_AddDeleteAttachment, "'Add/Delete Attachments'");
		
		Utils.waitForAllWindowsToLoad(3, driver);		
		
		Utils.switchWindows(driver, "INSZoom.com - Email Attachment", "title", "false");
		
		
		filePath = System.getProperty("user.dir")+filePath;
		
		
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		File path = new File(filePath);	
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);
		
		
		try {
			Utils.waitForElement(driver, btn_UploadFileInput);
			WebElement ele = driver.findElement(By.id("txtFileUpload"));
		    ((RemoteWebElement) ele).setFileDetector(detector);		    
			ele.sendKeys(file.getAbsolutePath());
			
//			btn_UploadFileInput.sendKeys(path.getAbsoluteFile().toString());
			Log.message("Entered the absolute file path in the Upload Button");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}
		
		Utils.clickElement(driver, btn_attach, "'Attach'");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + fileName + "')]", "xpath", fileName, "uploaded files");
		
		Utils.clickElement(driver, btn_done, "Done");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Case Related Email", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + fileName + "')]", "xpath", fileName, "email message");
		
		if(!checkbox_attachFile.isSelected())
			Utils.clickElement(driver, checkbox_attachFile, "Checkbox saying 'Check to save the attachments'");
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
		Thread.sleep(3000);
	}
	
	
	public void verifyIfEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName) throws InterruptedException
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 08/06/2021
		 * 
		 */
		
		clickCommunicationTab();
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
	}
	
	
	public void verifyIfEmailNotPresent(String emailSubjectWithoutAccess)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 20/12/2019
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(), '" + emailSubjectWithoutAccess + "')]", waitElement_Pagination, "xpath", emailSubjectWithoutAccess, "Received emails list");
	}
	

	public void verifyLCADetails(String lcaNumber, String jobTitle, String validThru,String LCAeffectiveOn, String LCAeffectiveTill)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Number')]/../td[2]/a[contains(text(),'"+ lcaNumber +"')]", "xpath", lcaNumber, "lca number");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Position')]/../td[2][contains(text(),'')]", "xpath", jobTitle, "job title");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Valid To')]/../td[2][contains(text(),'')]", "xpath", validThru, "valid to");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Effective On')]/../td[2][contains(text(),'')]", "xpath", LCAeffectiveOn, "effective on");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Effective To')]/../td[2][contains(text(),'')]", "xpath", LCAeffectiveTill, "effective to");
	}
	
	
	public void verifyVisaPriorityDate(String priorityDate, String visaCategory, String priorityCountry)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[text()='Priority Date']/../td[2][contains(text(),'"+priorityDate+"')]", "xpath", priorityDate, "priority date");
		Utils.verifyIfDataPresent(driver, "//td[text()='Visa Category']/../td[2][contains(text(),'"+visaCategory+"')]", "xpath", visaCategory, "visa category");
		Utils.verifyIfDataPresent(driver, "//td[text()='Priority Country']/../td[2][contains(text(),'"+priorityCountry+"')]", "xpath", priorityCountry, "priority country");
	}
	
	
	public void verifySWAandDoLInfo(String swaCaseNumber, String swaFiledDate,String swaPriorityDate, String dolRecievedDate,String dolCaseNumber, String noticeSentDate,String noticeReceivedDate, String backlogCaseNumber ) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[text()='SWA Case Number']/following-sibling::td[contains(text(),'"+swaCaseNumber+"')]", "xpath", swaCaseNumber, "SWA Case Number ");
		Utils.verifyIfDataPresent(driver, "//td[text()='SWA Filed Date']/following-sibling::td[contains(text(),'"+swaFiledDate+"')]", "xpath", swaFiledDate, "SWA Filed Date");
		Utils.verifyIfDataPresent(driver, "//td[text()='SWA Priority Date']/following-sibling::td[contains(text(),'"+swaPriorityDate+"')]", "xpath", swaPriorityDate, "SWA Priority Date");
		Utils.verifyIfDataPresent(driver, "//td[text()='DOL Received Date']/following-sibling::td[contains(text(),'"+dolRecievedDate+"')]", "xpath", dolRecievedDate, "DOL Recieved Date");
		Utils.verifyIfDataPresent(driver, "//td[text()='DOL Case Number']/following-sibling::td[contains(text(),'"+dolCaseNumber+"')]", "xpath", dolCaseNumber, "DOL Case Number");
		Utils.verifyIfDataPresent(driver, "//td[text()='Sent To Backlog Processing Center On']/following-sibling::td[contains(text(),'"+noticeSentDate+"')]", "xpath", noticeSentDate, "Sent To Backlog Processing Center On");
		Utils.verifyIfDataPresent(driver, "//td[text()='Backlog Processing Center Received On']/following-sibling::td[contains(text(),'"+noticeReceivedDate+"')]", "xpath", noticeReceivedDate, "Notice Received Date");
		Utils.verifyIfDataPresent(driver, "//td[text()='Backlog Case Number']/following-sibling::td[contains(text(),'"+backlogCaseNumber+"')]", "xpath", backlogCaseNumber, "backlog Case Number");
	}
	
	
	public void verifyCaseStepsStatus(String stepName, String status)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+stepName+"')]/following-sibling::td/span[contains(text(),'"+status+"')]", "xpath", status, "status for case step");
	}
	
	
	public void verifyFillingDetails(String filedOnDate,String applicationOrReceiptNo, String applicationOrReceiptNoticeDate)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Filed On Date')]/following-sibling::td[contains(text(),'"+filedOnDate+"')]", "xpath", filedOnDate, "Filed On Date");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Application / Receipt #')]/following-sibling::td/a[contains(text(),'"+applicationOrReceiptNo+"')]", "xpath", applicationOrReceiptNo, "Application / Receipt #");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Application / Receipt Notice Date')]/following-sibling::td[contains(text(),'"+applicationOrReceiptNoticeDate+"')]", "xpath", applicationOrReceiptNoticeDate, "Application / Receipt Notice Date");
	}

	public void verifyReceiptDetails(String filedDate, String receiptNumber, String receiptDate, String validFrom, String validTo, String status)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 09 Jan 2020
		 */
		String validity = validFrom + " - " + validTo;  
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+filedDate+"')]/following-sibling::td/a[contains(text(),'"+receiptNumber+"')]/../following-sibling::td[contains(text(),'"+receiptDate+"')]/following-sibling::td/span[contains(text(),'"+validity+"')]/../following-sibling::td[contains(text(),'"+status+"')]", "xpath", "receipt details", "additional receipt details");
	}
	
	
	public void verifyIfDocChecklistPresent(String docChecklistNameForFNP) throws InterruptedException
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 30 June 2021
		 */ 
		
		
		Utils.waitForElement(driver,"//a[contains(text(),'Supporting Docs') and @aria-expanded='true']" , globalVariables.elementWaitTime, "xpath");
		
		boolean truth = false;
		int i = 1;
		
		do
		{			
			Utils.waitForElement(driver, "//div[@id='case-supportingdocs-casechecklist']//span[contains(@class,'k-state-selected') and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");
			Utils.waitUntillLoaderDisappearsInHRP(driver);	
			if(Utils.isElementPresent(driver, "//p[contains(text(), '" + docChecklistNameForFNP + "')]", "xpath"))
			{
				Log.message("", driver, true);
				Log.pass("Doc Checklist is present in Supporting Docs tab");
				truth = true;
				break;
			}
			
			else
			{
				Utils.clickDynamicElement(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-60-right']", "xpath", "Next page");
				Utils.waitUntillLoaderDisappearsInHRP(driver);				
				i += 1;
			}
		}while(Utils.isElementPresent(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-60-right']", "xpath"));
	
		if(!truth)
		{
			Log.fail("Doc Checklist is not present in Supporting Docs tab", driver, true);
		}
	}
	
	
	public void verifyDocumentUpload(boolean value)
    {
		// Created by Nitisha Sinha
        // Created on 13/02/20
    	
    	if(value)
		{
    		if(Utils.waitForElement(driver, "//button[contains(text(),'Upload')]", globalVariables.elementWaitTime, "xpath"))
    			Log.pass("Upload button present under Documents in FNP home page");
    		else
    			Log.fail("Upload button is not present under Documents in FNP home page");	
    	}
		
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//button[contains(text(),'Upload')]", waitElement_documentHeader, "xpath", "Step Completion date", "Documents under FNP Home Page");
		}
   
    }
    
	
	 public void verifyCaseSteps(boolean value)
	    {
	    	// Created by Nitisha Sinha
	       // Created on 05/02/20
	    	
	        if(value)
	        {
	    
	              if(!Utils.isElementPresent(driver, "//div[@id='tab-process']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Case Step tab is present under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Case Step tab is not present under case in FNP Login");
	              }
	        }
	        
	        else
	        {
	        
	              if(Utils.isElementPresent(driver, "//div[@id='tab-process']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Case Step tab is not present under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Case Step tab is present under case in FNP Login");
	              }
	              
	         }
	    }

	    
	    public void verifyDocumentation(boolean value)
	    {
	    	// Created by Nitisha Sinha
	        // Created on 06/02/20
	    	
	    	if(value)
	        {
	    
	              if(!Utils.isElementPresent(driver, "//div[@id='tab-documents']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Documentation tab is present under under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Documentation tab is not present under case in FNP Login");
	              }
	        }
	        
	        else
	        {
	        
	              if(Utils.isElementPresent(driver, "//div[@id='tab-documents']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Documentation tab is not present under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Documentation tab is present under case in FNP Login");
	              }      
	       }
	   }
	    
	    
	    public void verifyDetailsOrDates(boolean value) throws InterruptedException
	    {
	    	// Created by Nitisha Sinha
	       // Created on 06/02/20
	    	
	    	if(value)
	        {
	    
	              if(!Utils.isElementPresent(driver, "//div[@id='tab-details']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Details/Dates tab is present under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Details/Dates tab is not present under case in FNP Login");
	              }
	        }
	        
	        else
	        {
	        
	              if(Utils.isElementPresent(driver, "//div[@id='tab-details']/div[@class='norecords-text']", "xpath"))
	              {
	                    Log.pass("Verified. The Details/Dates tab is not present under case in FNP Login");
	              }
	              
	              else
	              {
	                    Log.fail("Failed to Verify. The Details/Dates tab is present under case in FNP Login");
	              }      
	       }
	    }
	    
	    
	    public void verifyFilingDetails(boolean value) throws InterruptedException
	    {
	    	// Created by Nitisha Sinha
	       // Created on 06/02/20
	    
	    	if(value)
			{
	    		boolean staleElement = true; 
	    		while(staleElement){
	    		  try{
	    		     Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Filing Details')]","xpath", "Filing Details", "FNP Home Page");
	    		     staleElement = false;
	    		  } catch(StaleElementReferenceException e){
	    		    staleElement = true;
    		    }

    		} 
	    		
//	    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Filing Details')]", "xpath");
//	    		if(Utils.waitForElement(driver, "//h2[contains(text(),'Filing Details')]", globalVariables.elementWaitTime, "xpath"))
//	    			Log.pass("Filing Details present in FNP home page");
//	    		else
//	    			Log.fail("Filing Details not present in FNP home page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Filing Details')]", waitElement_caseDetails,"xpath", "Filing Details", "FNP Home Page");
			}

	    }
	    
	    
	    public void verifyDecisionDetails(boolean value) throws InterruptedException
	    {
	    	// Created by Nitisha Sinha
	       // Created on 06/02/20
	      //modified by Nitisha Sinha on 25th June, 2020
	      // Modification done: added logic to handle the loader
	    	
	    	if(value)
			{
	    		Utils.waitForElementIfPresent(driver, "//h2[contains(text(),'Decision Details')]", globalVariables.elementWaitTime, "xpath", "Decision Details");
	    		if(Utils.waitForElement(driver, "//h2[contains(text(),'Decision Details')]", globalVariables.elementWaitTime, "xpath"))
	    			Log.pass("Decision Details present in FNP home page");
	    		else
	    			Log.fail("Decision Details not present in FNP home page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Decision Details')]", waitElement_caseDetails,"xpath", "Decision Details", "FNP Home Page");
			}
	    }
	    
	    
	    public void verifyAdditionalapplictionOrReceiptDetails(boolean value) throws InterruptedException
	    {
	    	// Created by Nitisha Sinha
	       // Created on 06/02/20
	    	
	    	if(value)
			{
	    		if(Utils.waitForElement(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", globalVariables.elementWaitTime, "xpath"))
	    			Log.pass("Additional Application/ Receipt Details present in FNP home page");
	    		else
	    			Log.fail("Additional Application/ Receipt Details not present in FNP home page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", waitElement_caseDetails,"xpath", "Additional Application/ Receipt Details", "FNP Home Page");
			}

	    	
	    }
	    
	    
	    public void verifyVisaPriorityDateInfo(boolean value) throws InterruptedException
	    {
	    	// Created by Nitisha Sinha
	        // Created on 06/02/20
	    
			if(value)
			{
				if(Utils.waitForElement(driver, "//h2[contains(text(),'Visa Priority Date Info')]", globalVariables.elementWaitTime, "xpath"))
	    			Log.pass("Visa Priority Date Info present in FNP home page");
	    		else
	    			Log.fail("Visa Priority Date Info not present in FNP home page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Visa Priority Date Info')]", waitElement_caseDetails,"xpath", "Documents", "FNP Home Page");
			}
	    }
	    
	    

	    public void verifyCustomDetails(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.waitForElementPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Custom Details')]", "xpath");
	    		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Custom Details')]", "xpath", "Custom Details header", "under details and dates tab");
	    	}
	    	else 
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Custom Details')]",tab_DetailsDates, "xpath", "Custom Details header", "under details and dates tab");
	    		
	    	}
	    }
	    
	    
	    public void verifyLCADetails(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.waitForElementPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'LCA Details')]", "xpath");
	    		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'LCA Details')]", "xpath", "LCA Details header", "under details and dates tab");
	    	}
	    	else 
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'LCA Details')]",tab_DetailsDates, "xpath", "LCA Details header", "under details and dates tab");
	    		
	    	}
	    	
	    }
	    
	    
	    public void verifySWAandDOLInfo(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.waitForElementPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'SWA And DOL Info')]", "xpath");
	    		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'SWA And DOL Info')]", "xpath", "SWA and DOL Info header", "under details and dates tab");
	    	}
	    	else
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'SWA And DOL Info')]",tab_DetailsDates, "xpath", "SWA and DOL Info header", "under details and dates tab");
	    		
	    	}
	    	
	    }
	    
	    
	    public void verifySupportingDocuments(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		if(Utils.isElementPresent(driver, "//h2[contains(text(),'Check List')]", "xpath"))
	    		{
	    			Log.pass("Check list header present.");
	    		}
	    		else
	    		{
	    			Log.fail("Check List header not present");
	    		}
	    	}
	    	else
	    	{
	    		if(!Utils.isElementPresent(driver, "//h2[contains(text(),'Check List')]", "xpath"))
	    		{
	    			Log.pass("Check list header not present");
	    		}
	    		else
	    		{
	    			Log.fail("Check list header present");
	    		}

	    	}	
	    }
	    
	    
	    public void verifyCaseDocsCheckList(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Check List')]", "xpath", "Check list", "under Supporting Documents tab");
	    	}
	    	else
	    	{
	    		Utils.verifyIfDataPresent(driver, "tab-support", "id", "Check list not present", "under Supporting Documents tab");
	    	}
	    }
	    
	    
	    public void verifyCaseDocuments(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Documents')]", "xpath", "Documents", "under Supporting Documents tab");
	    	}
	    	else
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Documents')]",tab_SupportingDocs, "xpath", "Documents", "under Supporting Documents tab");
	    		
	    	}
	    	
	    }
	    
	    
	    public void verifyCommunication(boolean value)
        {
            /*
              Created by Yatharth pandya on 6/2/2020
              Modified by Nitisha Sinha on 1/6/2020
              Modification: change in logic because of UI change
             */
	    	
            if(!value)
            {
                if(driver.findElements(By.xpath("//div[@class='main-box-body clearfix']//div[contains(text(),'You don’t have access to this feature now.')]")).size()==2)
                {
                    Log.message("Communication Details not present");
                }
            }
            else
            {
                Log.message("Communication Details present");
            }
           
        }
	    
	    
        public void verifyCaseNotes(boolean value)
        {
            /*
              Created by Yatharth pandya on 6/2/2020
              Modified by Nitisha Sinha on 1/6/2020
              Modification: change in xapth
             */
            if(value)
            {
                Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Notes')]//ancestor::div[1]//div[@class='norecords-text']",waitElement_caseDetails, "xpath", "Notes", "In communication tab");
                Log.message("Notes present");
            }
            else
            {
                 Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Notes')]//ancestor::div[1]//div[@class='norecords-text']", "xpath", "Notes", "In communication tab");
                Log.message("Notes not present");
            }
           
        }
	    
	    
        public void verifyEmails(boolean value)
        {
            /*
              Created by Yatharth pandya on 6/2/2020
              Modified by Nitisha Sinha on 1/6/2020
              Modification: change in xapth
             */
            if(value)
            {
                Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Emails')]//ancestor::div[1]//div[@class='norecords-text']",waitElement_caseDetails, "xpath", "Communication header", "On case details page");
                Log.message("Emails present");
            }
            else
            {
                 Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Emails')]//ancestor::div[1]//div[@class='norecords-text']", "xpath", "Communication header", "On case details page");
                Log.message("Emails not present");
            }
           
        }
	    
	    
	    public void verifyShowCaseStepReminder(boolean value)
	    {
	    	/*
	    	  Created by Yatharth pandya on 6/2/2020
	    	 */
	    	if(value)
	    	{
	    		Utils.verifyIfDataPresent(driver, "//th[@data-field='EstReminderDate']/a", "xpath", "Estimated date", "under Case steps tab");
	    	}
	    	else
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "//th[@data-field='EstReminderDate']/a", tab_caseSteps, "xpath", "Estimated date", "under Case steps tab");
	    		
	    	}
	    	
	    }
	
	    
	    public void verifyStepCompletionDate(boolean value)
	    {
			// Created by Nitisha Sinha
	        // Created on 05/02/20
	    	
	    	if(value)
			{
				Utils.verifyIfDataPresent(driver, "//a[contains(text(),'Completed Date')]", "xpath", "Step Completion date", "Case under FNP Home Page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'Completed Date')]", tab_caseSteps, "xpath", "Step Completion date", "Case under FNP Home Page");
			}
	    }
	    
	    
	    public void verifyIfChecklistDescriptionPresent(String caseDocChecklistName, String docChecklistDescriptionNew)
	    {
	    	/*
			 * Created By : Saksham Kapoor
			 * Created On : 13 May 2020
			 */ 
			
			Utils.clickDynamicElement(driver, "//p[contains(text(), '" + caseDocChecklistName +"')]/following-sibling::div//div[2]", "xpath", "Arrow to Expand Checklist Description");
			Utils.verifyIfDataPresent(driver, "//p[contains(text(), '" + caseDocChecklistName + "')]/following-sibling::div//p[contains(text(), '" + docChecklistDescriptionNew + "')]", "xpath", docChecklistDescriptionNew, "Checklist Description");				
	    }
	
}