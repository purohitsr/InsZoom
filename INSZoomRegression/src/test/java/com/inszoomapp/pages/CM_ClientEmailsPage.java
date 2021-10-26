package com.inszoomapp.pages;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class CM_ClientEmailsPage extends LoadableComponent<CM_ClientEmailsPage> 
{
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	
	
	@FindBy(xpath="//a[contains(text(),'Advanced Options')]")
	WebElement lnk_AdvancedOptions; //Added by Saurav Purohit on 23th june, 2021
	
	@FindBy(id="txtAccDt")
	WebElement txtbox_accessPeriod;
	
	@FindBy(id="txtTo")
	WebElement txtbox_To;
	
	@FindBy(id = "txtCc")
	WebElement textBox_CC;
	
	@FindBy(css = "input[name ='chkDocs']")
	WebElement checkBox_EConsentName;
	
	@FindBy(xpath = "//a[contains(text(),'Select Client Letters (HTML)')]")
	WebElement link_HTMLLetterTemplateList;
	
	@FindBy(css="select[name='cboEmailAccess']")
	WebElement dropdown_ExtranetAccess;
	
	@FindBy(id="chkAttach")
	WebElement checkbox_saveAttachments;
	
	@FindBy(css="input[name='RowRemove']")
	WebElement btn_removeAttachment;
	
	@FindBy(id="objUploadDocs_BtnSubmit11_ClientState")
	WebElement btn_UploadFileInput;
	
	@FindBy(id="chkViewUpload")
	WebElement checkbox_IncludeDocuments;
	
	@FindBy(id="btn_AttachDocuments")
	WebElement lnk_AttachDocuments;
	
	@FindBy(id="btn_SelectDocs")
	WebElement btn_SaveDocuments;
	
	@FindBy(id="chkConsent")
	WebElement checkbox_SelectEConsent;
	
	@FindBy(id="btn_Choosee-Consents")
	WebElement lnk_ChooseEConsent;
	
	@FindBy(id="btn_Save")
	WebElement btn_Save;
	
	@FindBy(xpath="//td[contains(text(),'Agreement Policy')]/input")
	WebElement checkbox_EConsent;
	
	@FindBy(id="chkUpload")
	WebElement checkbox_ClientCanUploadDocuments;
	
	@FindBy(xpath="//font[contains(text(), 'access code')]/b")
	WebElement txt_AccessCode;
	
	@FindBy(xpath="//table/following-sibling::a")
	WebElement txt_URL;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="removebtn")
	WebElement btn_Remove;
	
	@FindBy(id="btn_SendEmailtoClient")
	WebElement btn_SendEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;
	
	@FindBy(id="btn_AddRemovequestionnaires")
	WebElement btn_AddRemoveQuestionnaires;
	
	@FindBy(id="ChkQuest")
	WebElement checkbox_IncludeQuestionnaires;
	
	@FindBy(css="i[class='fa fa-chevron-down']")
	WebElement icon_OpenAdvancedOptions;
	
	@FindBy(id="btn_ComposeEmailforClient")
	WebElement btn_ComposeEmail;

	public CM_ClientEmailsPage(WebDriver driver) {
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
		 * Modified On: 16/11/2019
		 */

		Utils.clickElement(driver, btn_ComposeEmail, "'Compose Email' Button");
	}
	
	
	public void clickIncludeQuestionnaires()
	{
		/* 
		 * Modified By: Saurav Purohit
		 * Modified On: 21/06/2021
		 * 
		 */
		Utils.waitForElement(driver, lnk_AdvancedOptions);
		if(!Utils.isElementPresent(driver, "i[class='fa fa-chevron-up']", "css"))
			
		{
			Utils.waitForElement(driver, icon_OpenAdvancedOptions);
			Utils.scrollToElement(driver, icon_OpenAdvancedOptions);
			Utils.clickElement(driver, icon_OpenAdvancedOptions, "Open Advanced Options");
		}
		Utils.waitForElement(driver, checkbox_IncludeQuestionnaires);
		Utils.scrollIntoView(driver, checkbox_IncludeQuestionnaires);
		Utils.clickElement(driver, checkbox_IncludeQuestionnaires, "Include Questionnaires Checkbox");
		
		Utils.waitForElement(driver, btn_AddRemoveQuestionnaires);
	}
	
	
	public void clickClientCanUploadDocumentsCheckbox()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 */
		
		if(!Utils.isElementPresent(driver, "i[class='fa fa-chevron-up']", "css"))
		{
			Utils.clickElement(driver, icon_OpenAdvancedOptions, "Open Advanced Options");
		}
		
		Utils.clickElement(driver, checkbox_ClientCanUploadDocuments, "Client can upload document Checkbox");
	}
	
	
	public void verifyIfQuestionnairesPresent(String questionnaire) throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 */

		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Included Questionnaire table");
	}
	
	
	public void enterMessageAndSendEmail(String emailMessage)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 * 
		 */
		
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		
		Utils.enterText(driver, txtbox_Message, emailMessage, "Message Box");
		
		driver.switchTo().defaultContent();
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email button");
	}
	
	
	public void clickAddRemoveQuestionnaires() throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 */

		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("javascript:window.scrollBy(0,2000)");
		
		Utils.clickElement(driver, btn_AddRemoveQuestionnaires, "'Add/Remove' Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
	}
	
	
	public void verifyIfQuestionnairesRemoved()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 */

		Utils.verifyIfDataPresent(driver, "td[class='NoRecord']", "xpath", "No records Found", "Linked questionnaires for email");
	}
	
	
	public void removeSelectedQuestionnaire(String secondQuestionnaire)
	{
		
		/* 
		 * Modified By: Saurav Purohit
		 * Modified On: 05/08/2021
		 * Added waitForLoadingMaskToDisappearInQuestionnaireSearchWindow funtion to wait for loading to complete after
		 * removing a form .
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + secondQuestionnaire + "')]/../preceding-sibling::td/input", "xpath", "Checkbox to select questionnaire");
		
		Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
		
		Utils.clickElement(driver, btn_Remove, "Remove");
		
		//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
		
		Utils.waitForLoadingMaskToDisappearInQuestionnaireSearchWindow(driver);
		
		Utils.waitForElementNotVisible(driver, "//a[contains(text(), '" + secondQuestionnaire + "')]", "xpath");
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
	
	
	public void getAccessCodeAndURL(String accessCodeEmailSubject, String dataWrite, String sheetName) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 * 
		 */
		
		String accessCode = "";
		String URL = "";
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + accessCodeEmailSubject + "')]", "xpath", "Email");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZOOM.com - View Message", "title", "false");
		
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
		writeExcel.setCellData("QA_ALoginAccessCodeURL_Client", sheetName, "Value", URL);
		writeExcel.setCellData("QA_ALoginAccessCode_Client", sheetName, "Value", accessCode);
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Client Emails", "title", "false");
	}
	
	
	public void selectEConsent(String econsent) throws Exception
	{
		/* 
		 * Modified By: Saurav Purohit
		 * Modified On: 16/06/2021
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_SelectEConsent, "Select e-consent checkbox");
		
		Utils.scrollIntoView(driver, lnk_ChooseEConsent);
		
		Utils.clickElement(driver, lnk_ChooseEConsent, "'Choose e-consent' link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose e-Consent Documents", "title", "false");
		
		Utils.clickElement(driver, checkBox_EConsentName, "First E-Consents Name");
		
		//Utils.clickDynamicElement(driver, "//td[contains(text(),'" + econsent + "')]/input", "xpath", econsent);
		
		//Utils.clickElement(driver, checkbox_EConsent, "'Agreement Policy' e-consent");
		
		Utils.clickElement(driver, btn_Save, "Save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
	}
	
	
	public void attachDocument(String document) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/11/2019
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_IncludeDocuments, "'Include Documents' checkbox");
		
		Utils.clickElement(driver, lnk_AttachDocuments, "'Attach Documents' link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Client Documents", "title", "false");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + document + "')]/../preceding-sibling::td/input", "xpath", "Document checkbox");
		
		Log.pass("", driver, true);
		Log.pass(document + " is available to be attached in Case Email");
		
		Utils.clickElement(driver, btn_SaveDocuments, "Save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
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
		 * Modified On: 20/12/2019
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_ExtranetAccess);
	}
	
	public void verifyEmailsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 10 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - List of Client Emails");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - List of Client Emails page failed", driver);
	    }

}
	
	
	public void verifyComposeEmailPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 10 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom - Advanced Email");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom - Advanced Email page failed", driver);
	    }
	}

	
	public void verifyHTMLLetterTemplateOnComposeEmailPage(String letterTemplateName) throws Exception 
	{
		 /*
		  * Created By : Souvik Ganguly
		  * Created On : 21 June 2021
		  */
		Utils.waitForElement(driver, link_HTMLLetterTemplateList);
		Utils.scrollToElement(driver, link_HTMLLetterTemplateList);
		Utils.clickElement(driver, link_HTMLLetterTemplateList, "HTML Letter temaplate list");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Client Letters (HTML)", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+letterTemplateName+"')]", "xpath", letterTemplateName, "letter template list");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
	}
	

	public void verifySentContractsEmail(String subject) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 22 July 2020
		  */
		Utils.verifyIfDataPresent(driver, "//font/following-sibling::a[contains(text(),'"+subject+"')]", "xpath", subject, "email list page");
	}
	
	public void downloadAndVerifyUploadedDocument(String subject, String docName, RemoteWebDriver driver) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 22 July 2020
		  */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+subject+"')]", "xpath", "on the email link "+subject);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZOOM.com - View Message", "title", "false");
		Utils.isElementPresent(driver, "//a[contains(text(),'"+docName+"')]", "xpath");

		Utils.openDownloadsWindow(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        Utils.clickDynamicElement(driver, "(/*//tr[@class='TBLROW2']//a[contains(text(), '')])[2]", "xpath", "doc name"+docName);
        Thread.sleep(10000);
        
        Utils.verifyIfDownloaded(driver);
        
	}
	
	public void verifyExtranetAccess(String dropDownValue) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 30 Sep 2020
		  */
		Utils.verifyIfDataPresent(driver, "//option[@value='"+globalVariables.defaultEmailAccess.get(dropDownValue)+"' and @selected]", "xpath", dropDownValue, "Case emails page");
	}
	
	
	public void verifySupervisorEmailInCC(String supervisorEmailId) throws Exception 
	{
		 /*
		  * Edited By : Souvik Ganguly
		  * Created On : 2 August 2021
		  * Added sync
		  */
		Utils.waitForElement(driver, textBox_CC);
		String actualEmailId = textBox_CC.getText();
		if(actualEmailId.equals(supervisorEmailId))
			Log.pass("Verified supervisor email ID as "+supervisorEmailId, driver, true);
		else
			Log.fail("Unable to verify supervisor ID", driver, true);
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
	
	
	public void verifyAccessDate(int accessPeriod) throws InterruptedException, ParseException
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 20 August 2021
		 * Added scroll function and logging messages
		 * Changed the logic to format the current time to PST
		 */
		//For logging of the access period and current date
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
	
	
	
	
	
	public void verifyQuestionnairePresentInQuestionnaireListPage(String questionnaire) throws Exception
    {
        
        /* 
         * Modified By: Saksham Kapoor
         * Modified On: 16/11/2019
         */

        Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Included Questionnaire table");
    }
}


