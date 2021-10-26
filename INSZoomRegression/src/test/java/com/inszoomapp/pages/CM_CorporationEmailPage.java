package com.inszoomapp.pages;

import java.io.File;

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

public class CM_CorporationEmailPage extends LoadableComponent<CM_CorporationEmailPage> 
{
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id="chkAttach")
	WebElement checkbox_saveAttachments;
	
	@FindBy(css="input[name='RowRemove']")
	WebElement btn_removeAttachment;
	
	@FindBy(name="cboEmailAccess")
	WebElement dropdown_extranetAccess;
	
	@FindBy(id="btn_ComposeemailforCorporation")
	WebElement btn_composeContentEmail;
	
	@FindBy(id="removebtn")
	WebElement btn_Remove;
	
	@FindBy(id="btn_AddRemovequestionnaires")
	WebElement btn_AddRemoveQuestionnaires;
	
	@FindBy(id="LMEprEmails")
	WebElement btn_ComposeEmail;
	
	@FindBy(xpath="//font[contains(text(), 'access code')]/b")
	WebElement txt_AccessCode;
	
	@FindBy(xpath="//table/following-sibling::a")
	WebElement txt_URL;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="btn_EmailCorp.Questionnaires")
	WebElement btn_SendEmail;
	
	@FindBy(id="btn_SendEmailForCorporation")
	WebElement btn_sendContentEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;

	public CM_CorporationEmailPage(WebDriver driver) {
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
	
	
	public void enterMessageAndSendEmail(String emailMessage)
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
	}
	
	
	public void enterMessageAndSendContentEmail(String emailMessage)
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
		
		Utils.waitForElement(driver, btn_sendContentEmail);
		Utils.scrollIntoView(driver, btn_sendContentEmail);
		Utils.clickElement(driver, btn_sendContentEmail, "Send Email button");
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
		Utils.switchWindows(driver, "INSZoom.com : View Email", "title", "false");
		
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
		writeExcel.setCellData("QA_ACorporation_Access_URL", sheetName, "Value", URL);
		writeExcel.setCellData("QA_ACorporation_Access_Code", sheetName, "Value", accessCode);
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Corporation Emails", "title", "false");
		
	}
	
	
	public void clickComposeEmailButton()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/11/2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_ComposeEmail, "Compose Email");
	}
	
	
	public void clickComposeEmail()
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/02/2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_composeContentEmail, "Compose Email");
	}
	
	
	public void addQuestionnaire(String questionnaire) throws Exception
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 27/11/2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_AddRemoveQuestionnaires, "'Add/Remove' Questionnaire");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
		
		corporationQuestionnairePage.addQuestionnaire(questionnaire);
	}
	
	
	public void verifyIfQuestionnairesPresent(String questionnaire) throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 27/11/2019
		 */

		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Included Questionnaire table");
	}
	
	
	public void removeSelectedQuestionnaire(String secondQuestionnaire) throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/11/2019
		 */
		
		Utils.clickElement(driver, btn_AddRemoveQuestionnaires, "'Add/Remove' Questionnaire");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + secondQuestionnaire + "')]/../preceding-sibling::td/input", "xpath", "Checkbox to select questionnaire");
		
		Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
		
		Utils.clickElement(driver, btn_Remove, "Remove");
		
		//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		
		Utils.waitForElementNotVisible(driver, "//a[contains(text(), '" + secondQuestionnaire + "')]", "xpath");
	}
	
	
	public void changeExtranetAccess(String choice)
	{
		/* 
		 * Created By: Saksham Kapoor
		 * Created On: 26/02/2020
		 */
		
		if(choice.equals("corporation"))
			Utils.selectOptionFromDropDownByVal(driver, "P", dropdown_extranetAccess);
		
		if(choice.equals("firm"))
			Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_extranetAccess);
	}
	
	
	public void attachFile(String filePath)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 26/02/2019
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
		Utils.clickDynamicElement(driver, "//font/following-sibling::a[contains(text(),'"+subject+"')]", "xpath", "on the email link "+subject);
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com : View Email", "title", "false");
		
		Utils.isElementPresent(driver, "//a[contains(text(),'"+docName+"')]", "xpath");
		
		Utils.openDownloadsWindow(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        
        Utils.clickDynamicElement(driver, "(/*//tr[@class='TBLROW2']//a[contains(text(), '')])[2]", "xpath", "doc name"+docName);
        
        Thread.sleep(8000);
        
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
	
	

}