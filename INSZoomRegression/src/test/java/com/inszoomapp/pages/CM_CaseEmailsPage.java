package com.inszoomapp.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_CaseEmailsPage extends LoadableComponent<CM_CaseEmailsPage> 
{
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id="txtAccDt")
	WebElement txtbox_accessPeriod;
	
	@FindBy(id="txtTo")
	WebElement txtbox_To;
	
	@FindBy(xpath = "//label[contains(text(),'Include Forms')]/ancestor::td[1]//input")
	WebElement checkBox_IncludeForms;
	
	@FindBy(css = "input[name ='chkDocs']")
	WebElement checkBox_EConsentName;
	
	@FindBy(xpath = "//a[contains(text(),'Select Case Letters (HTML)')]")
	WebElement link_caseHTMLLettersList;
	
	@FindBy(xpath = "//a[contains(text(),'Select Case Letters (Ms Word)')]")
	WebElement link_caseMSLettersList;
	
	@FindBy(id="chkCaseLetters")
	WebElement checkbox_IncludeCaseLetters;
	
	@FindBy(css="select[name='cboEmailAccess']")
	WebElement dropdown_ExtranetAccess;
	
	@FindBy(id="chkAttach")
	WebElement checkbox_saveAttachments;
	
	@FindBy(css="input[name='RowRemove']")
	WebElement btn_removeAttachment;
	
	@FindBy(id="objUploadDocs_BtnSubmit11_ClientState")
	WebElement btn_UploadFileInput;
	
	@FindBy(id="btn_SelectDocs")
	WebElement btn_SaveDocuments;
	
	@FindBy(id="chkViewUpload")
	WebElement checkbox_IncludeDocuments;
	
	@FindBy(id="btn_Save")
	WebElement btn_Save;
	
	@FindBy(xpath="//td[contains(text(),'')]/input")
	WebElement checkbox_EConsent;
	
	@FindBy(id="btn_Choosee-Consents")
	WebElement lnk_ChooseEConsent;
	
	@FindBy(id="chkConsent")
	WebElement checkbox_SelectEConsent;
	
	@FindBy(xpath="//font[contains(text(), 'access code')]/b")
	WebElement txt_AccessCode;
	
	@FindBy(xpath="//table/following-sibling::a")
	WebElement txt_URL;
	
	@FindBy(id="btn_EmailCaseAdvancedKit")
	WebElement btn_SendEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;
	
	@FindBy(id="btn_SelectDocs")
	WebElement btn_Done;
	
	@FindBy(id="btn_SelectDocsChecklist")
	WebElement lnk_SelectDocChecklist;
	
	@FindBy(css="a[title='Add/Remove questionnaires']")
	WebElement btn_AddRemoveQuestionnaires;
	
	@FindBy(id="chkDocs")
	WebElement checkbox_IncludeDocChecklist;
	
	@FindBy(id="ChkQuest")
	WebElement checkbox_IncludeQuestionnaires;
	
	@FindBy(css="i[class='fa fa-chevron-down']")
	WebElement icon_OpenAdvancedOptions;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="btn_ComposeCaseemail")
	WebElement btn_ComposeEmail;
	
	@FindBy(id="btn_AttachDocuments")
	WebElement lnk_AttachDocuments;

	public CM_CaseEmailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Emails Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickComposeEmailButton() throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/11/2019
		 */

		Utils.clickElement(driver, btn_ComposeEmail, "'Compose Email' Button");
	}
	
	
	public void clickAttachDocumentsLink() throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/11/2019
		 */

		Utils.clickElement(driver, lnk_AttachDocuments, "'Attach Documents' Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Documents", "title", "false");
	}
	
	
	public void verifyIfAllDocumentsPresentToAttach(List<String> allDocuments)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/11/2019
		 */
		
		for(int i = 0; i< allDocuments.size(); i++)
		{
			Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + allDocuments.get(i) + "')]", "xpath", allDocuments.get(i), "Attach Documents List");
		}
	}
	
	
	public void changeEmailSubject(String accessCodeEmailSubject)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 * 
		 */
		
		Utils.enterText(driver, txtbox_Subject, accessCodeEmailSubject, "'Subject'");
	}
	
	
	public void clickIncludeQuestionnaires()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 */
		
		if(!Utils.isElementPresent(driver, "i[class='fa fa-chevron-up']", "css"))
		{
			Utils.clickElement(driver, icon_OpenAdvancedOptions, "Open Advanced Options");
		}
		
		Utils.clickElement(driver, checkbox_IncludeQuestionnaires, "Include Questionnaires Checkbox");
		
	}
	
	
	public void includeDocChecklist(String docChecklist) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 */
		
		if(!Utils.isElementPresent(driver, "i[class='fa fa-chevron-up']", "css"))
		{
			Utils.clickElement(driver, icon_OpenAdvancedOptions, "Open Advanced Options");
		}
		
		Utils.clickElement(driver, checkbox_IncludeDocChecklist, "Include Doc Checklist Checkbox");
		Utils.scrollIntoView(driver, lnk_SelectDocChecklist);
		Utils.clickElement(driver, lnk_SelectDocChecklist, "'Select Doc Checklist' link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Docs Checklist", "title", "false");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + docChecklist + "')]/../preceding-sibling::td//input", "xpath", "Checkbox to select doc checklist");
		
		Utils.clickElement(driver, btn_Done, "Done Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Email", "title", "false");
		
	}
	
	
	public void enterMessageAndSendEmail(String emailMessage) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 * 
		 */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,-1000)");
		
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		
		Utils.enterText(driver, txtbox_Message, emailMessage, "Message Box");
		
		driver.switchTo().defaultContent();
		
		Utils.waitForElement(driver, btn_SendEmail);
		Utils.scrollIntoView(driver, btn_SendEmail);
		Utils.clickElement(driver, btn_SendEmail, "Send Email button");

		Utils.switchWindows(driver, "INSZoom.com - Case Emails", "title", "false");
	}
			
	
	public void getAccessCodeAndURL(String accessCodeEmailSubject, String dataWrite, String sheetName) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 * 
		 */
		
		String accessCode = "";
		String URL = "";
		
		Utils.waitForElement(driver, "//a[contains(text(), '" + accessCodeEmailSubject + "')]", globalVariables.elementWaitTime, "xpath");
		WebElement email = driver.findElement(By.xpath("//a[contains(text(), '" + accessCodeEmailSubject + "')]"));
		
		Utils.scrollToElement(driver, email);
		
		Utils.clickElement(driver, email, "Received Email");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Email", "title", "false");
		
		try {
			Utils.waitForElement(driver, txt_URL);
			URL = txt_URL.getText();
			Log.message("Got the URL as: " + URL);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to get the URL from email. ERROR :\n\n " + e.getMessage());
		}


		try {
			Utils.waitForElement(driver, txt_AccessCode);
			accessCode = txt_AccessCode.getText();
			Log.message("Got the Access Code as: " + accessCode);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to get the Access Code. ERROR :\n\n " + e.getMessage());
		}

		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		writeExcel.setCellData("QA_ALoginAccessCodeURL", sheetName, "Value", URL);
		writeExcel.setCellData("QA_ALoginAccessCode", sheetName, "Value", accessCode);
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Emails", "title", "false");
	}
	
	
	public void selectEConsent(String eConsent) throws Exception
	{
		/* 
		 * Modified By: saurav purohit
		 * Modified On: 09/06/2021
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_SelectEConsent, "Select e-consent checkbox");
		
		Utils.scrollToElement(driver, lnk_ChooseEConsent);
		
		Utils.clickElement(driver, lnk_ChooseEConsent, "'Choose e-consent' link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose e-Consent Documents", "title", "false");
		
		Utils.clickElement(driver, checkBox_EConsentName, "First E-Consents Name");
		
		//Utils.clickDynamicElement(driver, "//td[contains(text(),'" + eConsent + "')]/input", "xpath", eConsent);
		
		//Utils.clickElement(driver, checkbox_EConsent, "'Agreement Policy' e-consent");
		
		Utils.clickElement(driver, btn_Save, "Save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Email", "title", "false");
	}
	
	
	public void attachDocument(String document) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/11/2019
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_IncludeDocuments, "'Include Documents' checkbox");
		
		clickAttachDocumentsLink();
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + document + "')]/../preceding-sibling::td/input", "xpath", "Document checkbox");
		
		Log.pass("", driver, true);
		Log.pass(document + " is available to be attached in Case Email");
		
		Utils.clickElement(driver, btn_SaveDocuments, "Save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Email", "title", "false");
		
	}
	
	
	public void verifyIfEmailSent(String subject)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/11/2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + subject + "')]", "xpath", subject, "Emails list");
	}
	
	
	public void attachFile(String filePath)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/12/2019
		 * 
		 */
		
		filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		File filepath = new File(filePath);
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);

		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString());

		WebElement droparea = null;
		try {

			Utils.waitForElement(driver, "span[id='objUploadDocs_BtnSubmit11']", globalVariables.elementWaitTime, "css");
			droparea = driver.findElement(By.cssSelector("span[id='objUploadDocs_BtnSubmit11']"));
		} catch (Exception e) {
			Log.fail("Not able to fetch the drop area for file");
		}
		
		WebDriver driver = ((RemoteWebElement) droparea).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;


		String JS_DROP_FILE = "var target = arguments[0]," + "    offsetX = arguments[1],"
				+ "    offsetY = arguments[2]," + "    document = target.ownerDocument || document,"
				+ "    window = document.defaultView || window;" + "" + "var input = document.createElement('INPUT');"
				+ "input.type = 'file';" + "input.style.display = 'none';" + "input.onchange = function () {"
				+ "  var rect = target.getBoundingClientRect(),"
				+ "      x = rect.left + (offsetX || (rect.width >> 1)),"
				+ "      y = rect.top + (offsetY || (rect.height >> 1)),"
				+ "      dataTransfer = { files: this.files };" + ""
				+ "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {"
				+ "    var evt = document.createEvent('MouseEvent');"
				+ "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);"
				+ "    evt.dataTransfer = dataTransfer;" + "    target.dispatchEvent(evt);" + "  });" + ""
				+ "  setTimeout(function () { document.body.removeChild(input); }, 25);" + "};"
				+ "document.body.appendChild(input);" + "return input;";

		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
	    ((RemoteWebElement) input).setFileDetector(detector);
	    
		input.sendKeys(file.getAbsolutePath());
		
//		input.sendKeys(filepath.getAbsoluteFile().toString());
		
		Utils.waitForElement(driver, btn_removeAttachment);
		
		Utils.clickElement(driver, checkbox_saveAttachments, "'Check to Save Attachments'");
	}
	
	
	public void changeExtranetAccess()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/12/2019
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_ExtranetAccess);
	}
	
	
	public void verifyMSLetterTemplateOnComposeEmailPage(String letterTemplateName) throws Exception{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 26 March 2020
		 */
		Utils.clickElement(driver, checkbox_IncludeCaseLetters, "check box include case letters");
		Utils.waitForElement(driver, link_caseMSLettersList);
		Utils.clickElement(driver, link_caseMSLettersList, "case letter list link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (Ms Word) List", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+letterTemplateName+"')]", "xpath", letterTemplateName, "in compose email");
		driver.close();
	} 
	
	public void verifyHTMLLetterTemplateOnComposeEmailPage(String letterTemplateName) throws Exception{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 26 March 2020
		 */
		Utils.clickElement(driver, checkbox_IncludeCaseLetters, "check box include case letters");
		Utils.waitForElement(driver, link_caseHTMLLettersList);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
		Utils.clickElement(driver, link_caseHTMLLettersList, "case letter list link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (HTML)", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+letterTemplateName+"')]", "xpath", letterTemplateName, "in compose email");
		driver.close();
	}
	
	
	public void verifyEmailsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Emails");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Case Emails page failed", driver);
	    }    
	}
	
	
	
	public void verifySentContractsEmail(String subject) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 22 July 2020
		  */
		Utils.verifyIfDataPresent(driver, "//font/following-sibling::a[contains(text(),'"+subject+"')]", "xpath", subject, "email list page");
	}
	
	
	public void downloadAndVerifyUploadedDocument(String subject, String docName, RemoteWebDriver remoteDriver) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 22 July 2020
		  */
		Utils.clickDynamicElement(driver, "//font/following-sibling::a[contains(text(),'"+subject+"')]", "xpath", "on the email link "+subject);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Email", "title", "false");
		Utils.isElementPresent(driver, "//a[contains(text(),'"+docName+"')]", "xpath");
		Utils.openDownloadsWindow(remoteDriver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        Utils.clickDynamicElement(driver, "(/*//tr[@class='TBLROW2']//a[contains(text(), '')])[2]", "xpath", "doc name"+docName);
        Thread.sleep(8000);
        Utils.verifyIfDownloaded(remoteDriver);
        
	}
	
	public void verifyExtranetAccess(String dropDownValue) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 30 Sep 2020
		  */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,600)");
		
		Utils.verifyIfDataPresent(driver, "//option[@value='"+globalVariables.defaultEmailAccess.get(dropDownValue)+"' and @selected]", "xpath", dropDownValue, "Case emails page");
	}
	
	
	public void verifyDefaultFormAccessType(String formName, String dropDownValue) throws Exception 
	{
		 /*
		  * Edited By : Souvik Ganguly
		  * Edited On : 10 Aug 2021
		  * Added sync
		  */
		Utils.waitForElement(driver, checkbox_saveAttachments);
		if(!checkbox_saveAttachments.isSelected())
			Utils.clickElement(driver, checkBox_IncludeForms, "Checked checkbox Include Forms");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,600)");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//select/option[contains(text(),'"+dropDownValue+"') and @selected]", "xpath", dropDownValue, "Form access type");
	}
	
	public void verifyDefaultTrackChanges(String formName, String option) throws Exception 
	{
		 /*
		  * Edited By : Souvik Ganguly
		  * Edited On : 05 Aug 2021
		  * Added sync
		  */
		Utils.waitForElement(driver, checkbox_saveAttachments);
		if(!checkbox_saveAttachments.isSelected())
			Utils.clickElement(driver, checkBox_IncludeForms, "Checked checkbox Include Forms");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,600)");
        
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//select[option[contains(text(),'"+option+"') and @selected]]", "xpath", option, "Track changes");
	}
	
	public void verifyDefaultEfilingEnable(String formName, String option) throws Exception 
	{
		 /*
		  * Created By : Souvik Ganguly
		  * Created On : 10 Aug 2021
		  * Added sync
		  */
		Utils.waitForElement(driver, checkbox_saveAttachments);
		if(!checkbox_saveAttachments.isSelected())
			Utils.clickElement(driver, checkBox_IncludeForms, "Checked checkbox Include Forms");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,600)");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//select/option[contains(text(),'"+option+"') and @selected]", "xpath", option, "Efiling Enable");
	}
	
	
	public void verifyEnableUserToChangeSettingsLocked(String option, String formName, String formNameForEfiling, String formAcessType, String trackChanges, String enableEfiling) throws Exception 
	{
		/*
		  * Created By : Souvik Ganguly
		  * Created On : 10 Aug 2021
		  * Added sync
		  */
		Utils.waitForElement(driver, checkbox_saveAttachments);
		if(!checkbox_saveAttachments.isSelected())
			Utils.clickElement(driver, checkBox_IncludeForms, "Checked checkbox Include Forms");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,600)");
		
		if(option.equals("No"))
		{
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//td[contains(text(),'')]", "xpath", "text", "and cannot be changed");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//td[contains(text(),'')]", "xpath", "text", "in track changes and cannot be changed");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formNameForEfiling+"')]/..//td[contains(text(), '')]", "xpath", "text", "in enable efiling and cannot be changed");
		}
		if(option.equals("Yes"))
		{
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//select/option[contains(text(),'') and @selected]", "xpath", formAcessType, "Form access type");
			if(Utils.isElementPresent(driver, "//td[contains(text(),'Track Changes')]", "xpath"))
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formName+"')]/..//select[option[contains(text(),'') and @selected]]", "xpath", trackChanges, "Track changes");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+formNameForEfiling+"')]/..//select/option[contains(text(),'') and @selected]", "xpath", enableEfiling, "Efiling Enable");
		}
	}
	
	
	public void verifyDefaultEmailForEmployee(String workbookName, String sheetName, String value)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Sep 2020
		 * 
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String mainEmail = readExcel.initTest(workbookName, sheetName, "Client_MainEmailID");
        String alternateEmail = readExcel.initTest(workbookName, sheetName, "Client_AltEmailID");
		
		Utils.waitForElement(driver, txtbox_To);
		String emailId = txtbox_To.getText();
		
		if(value.equals("None"))
		{
			if(emailId.equals(""))
			{
				Log.message("", driver, true);
				Log.pass("There is no Email ID present by default");
			}
			else
			{
				Log.message("", driver, true);
				Log.fail("There is some Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Main Email"))
		{
			if(emailId.equals(mainEmail))
			{
				Log.message("", driver, true);
				Log.pass("Default email ID verified");
			}
			else
			{
				Log.message("", driver, true);
				Log.fail("There is some other Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Alternate Email"))
		{
			if(emailId.equals(alternateEmail))
			{
				Log.message("", driver, true);
				Log.pass("Default Email ID present");
			}
			else
			{
				Log.message("", driver, true);
				Log.fail("There is some other Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Main and Alternate Email"))
		{
			if(emailId.equals(mainEmail + ";" + alternateEmail))
			{
				Log.message("", driver, true);
				Log.pass("Default Email ID present");
			}
			else
			{
				Log.message("", driver, true);
				Log.fail("There is some other Email ID present by default: " + emailId);
			}
		}
	}
	
	
	public void verifyAccessDate(int accessPeriod)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 20 August 2021
		 * Added scroll function and logging messages
		 * Changed the logic to format the current time to PST
		 */
		 
		Log.message("The access period is " + accessPeriod + " months");
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("PST"));
		Log.message("The current PST date is " + formatter.format(currentDate));
		Date newDate = DateUtils.addMonths(currentDate, accessPeriod);
		String strDate = formatter.format(newDate); 
	    
	    String temp[] = strDate.split("/");
	    if(temp[0].charAt(0) == '0')
	    	temp[0] = temp[0].replace("0", "");
	    if(temp[1].charAt(0) == '0')
	    	temp[1] = temp[1].replace("0", "");
	    
	    strDate = temp[0] + "/" + temp[1] + "/" + temp[2];	    
	    Utils.waitForElement(driver, txtbox_accessPeriod);
	    Utils.scrollIntoView(driver, txtbox_accessPeriod);
	    
	    if(txtbox_accessPeriod.getAttribute("value").equals(strDate))
	    {
	    	Log.message("", driver, true);
	    	Log.pass("Access Period is verified");
	    	Log.message("Access Period is verified as the date on UI is: " + txtbox_accessPeriod.getAttribute("value") + " which is same as : " + strDate);
	    }
	    
	    else
	    {
	    	
	    	Log.message("", driver, true);
	    	Log.fail("Access Period is NOT verified as the date on UI is: " + txtbox_accessPeriod.getAttribute("value") + " whereas it should be : " + strDate);
	    }
	}
}

