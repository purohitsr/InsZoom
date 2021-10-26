package com.inszoomapp.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CM_CorporationContractPage extends LoadableComponent<CM_CorporationContractPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	@FindBy(id="chkAttach")
	WebElement checkbox_saveAttachments;
	
	@FindBy(id = "deleteLinkedTemplate")
	WebElement btn_delete;
	
	@FindBy(id = "btn_SendEmailForCorporation")
	WebElement btn_sendEmail;
	
	@FindBy(xpath = "//html/body")
	WebElement textBox_message;
	
	@FindBy(id = "htmMsg__ctl0_Editor_contentIframe")
	WebElement frame_emailMessage;
	
	@FindBy(xpath = "//textarea[@id='txtSubject']")
	WebElement textBox_subject;
	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement btn_continue;
	
	@FindBy(xpath = "//div[@class='modal md-effect-1 md-show']//button[contains(text(),'Save')]")
	WebElement btn_SaveAfterUpdatingTemplate;
	
	@FindBy(xpath = "//div[@class='toast-alert-message alert-success success_alert_msg show']")
	WebElement notification_updatedTemplateSuccessfully;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Save')]")
	WebElement btn_SaveAfterRename;
	
	@FindBy(xpath = "//label[contains(text(),'Name')]//following-sibling::form//input")
	WebElement textBox_Name;
	
	@FindBy(xpath = "//li//a[contains(text(),'Document Details')]")
	WebElement tab_DocumentDetails;
	
	@FindBy(xpath = "//a[@class='router-link-active']//span[contains(text(),'Corporation')]")
	WebElement link_Corp;
	
	@FindBy(id = "btn_Save")
	WebElement btn_Save;
	
	@FindBy(id = "txtDesciption")
	WebElement textBox_templateDescription;
	
	@FindBy(id = "txtName")
	WebElement textBox_templateName;
	
	@FindBy(id = "addTemplate")
	WebElement btn_addTemplate;
	
	@FindBy(xpath = "//div[@class='toast-alert-message alert-success success_alert_msg show']")
	WebElement alert_ContractsLinked;
	
	@FindBy(id = "txtSearch")
	WebElement searchBox_Contracts;

	public CM_CorporationContractPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
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
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}

	public void verifyKBTemplateOnContractsPage(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :29 April 2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", templateName, "case contracts page");
	}
	
	public void switchToCorpProfilePage() throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :29 April 2020
		 */
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
	}
	
	public void verifyTemplateDescription(String templateName, String documentName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 April 2020
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]/../i", "xpath", "down arrow to display details");
		Utils.waitForElement(driver, "//span[contains(text(),'"+templateName+"')]/../ancestor::div[1][@class='text-left template_name accord-panel-heading zc-toggl active']", globalVariables.elementWaitTime, "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='panel-collapse accord_panel_border collapse in']//a[contains(text(),'"+documentName+"')]", "xpath", documentName, "in template details");
	}
	
	public void useContractTemplate(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 April 2020
		 */
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]/../../following-sibling::div//button", "xpath", "use button");
		Utils.waitForElement(driver, alert_ContractsLinked);
		Utils.verifyIfDataPresent(driver, "//div[@class='template_card']//a[contains(text(),'"+templateName+"')]", "xpath", templateName, "used on contracts page");
	}
	
	public void verifyUsedText(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 April 2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1]/following-sibling::div/span[@id='UsedButton']", "xpath", templateName, "used on contracts page");
	}
	
	public void verifyDraftText(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 April 2020
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[2]//p[contains(text(),'Draft')]", "xpath", templateName, "used on contracts page");
	}
	
	public void verifySearchBox(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 April 2020
		 */
		Utils.enterText(driver, searchBox_Contracts, templateName, "template name");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", templateName, "as a result of search");
		Utils.enterText(driver, searchBox_Contracts, "JUNK TEXT", "searchbox");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'No records found')]", "xpath", "unavailable message", "search box result");
	}
	
	public void verifyAddTemplateFuctionality(String templateName, String templateDescription, String filePath) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 13 May 2020
		 */
		Utils.clickElement(driver, btn_addTemplate, "add template button");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Contract Template", "title", "false");
		Utils.enterText(driver, textBox_templateName, templateName, "template name");
		Utils.enterText(driver, textBox_templateDescription, templateDescription, "template description");
		
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
		
		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString()); 
			WebElement droparea = null;
		try {
			Utils.waitForElement(driver, "//div[@class='DropZone1']", globalVariables.elementWaitTime, "xpath");
			droparea = driver.findElement(By.xpath("//div[@class='DropZone1']"));
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
		
//		input.sendKeys(path.getAbsoluteFile().toString()); 
		
		Utils.waitForElement(driver, "//span[@class='ruUploadProgress ruUploadSuccess']", globalVariables.elementWaitTime, "xpath");
		
		Utils.clickElement(driver, btn_Save, "save button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
		Thread.sleep(3000);
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", templateName, "templates list");
	}
	
	public void verifyBreadcrumbs()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 13 May 2020
		 */
		Utils.clickElement(driver, link_Corp, "corp link to verify breadcrumbs");
	}
	
	
	public void verifyRename(String oldTemplateName, String newTemplateName) throws InterruptedException
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 June 2020
		 */
		Utils.clickDynamicElement(driver,"//a/span[contains(text(),'"+oldTemplateName+"')]" , "xpath", "clicked on template name");
		Utils.clickDynamicElement(driver, "(//a/span[contains(text(),'"+oldTemplateName+"')]/ancestor::li//a)[2]", "xpath", "Document name");
		Utils.clickElement(driver, tab_DocumentDetails, "document details tab");
		Utils.enterText(driver, textBox_Name, newTemplateName, newTemplateName);
		Log.message("Just to check u fail", driver, true);
		Thread.sleep(3000);
		Utils.clickElement(driver, btn_SaveAfterRename, "Save button");
		Utils.waitForElement(driver, notification_updatedTemplateSuccessfully);
		Utils.verifyIfDataPresent(driver, "//a/span[contains(text(),'"+newTemplateName+"')]", "xpath", newTemplateName, "updated template");
	}
	
	
	public void verifyRenameForUsedTemplate(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 June 2020
		 */
		driver.navigate().refresh();
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+templateName+"')]", "xpath", templateName, "rename on used template");
	}
	
	public void verifyDocumentInEmailPage(String templateName, String fileName) throws Exception
	{
		/* 
		 * Edited By : Souvik Ganguly
		 * Edited On : 18 August 2021
		 * Added sync
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[1]//button[contains(text(),'Email')]", "xpath", "Email option button");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Email", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+fileName+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+fileName+"')]", "xpath", fileName, "attached document");
	}
	
	public void verifyUploadedSignedCopy(String templateName,String filePath, String docName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 23 June 2020
		 */
		Utils.waitForElement(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[1]//div[@aria-label='Upload Signed Copy']", globalVariables.elementWaitTime, "xpath");
		WebElement btn_uploadSignedCopy = driver.findElement(By.xpath("//a[contains(text(),'"+templateName+"')]/ancestor::div[1]//div[@aria-label='Upload Signed Copy']/input"));
        
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
            //Utils.waitForElement(driver, btn_uploadSignedCopy);
		    ((RemoteWebElement) btn_uploadSignedCopy).setFileDetector(detector);
		    
			btn_uploadSignedCopy.sendKeys(file.getAbsolutePath());
        	
//            btn_uploadSignedCopy.sendKeys(path.getAbsoluteFile().toString());
            Log.message("Entered the absolute file path in the Upload Button");

        } catch (Exception e) {
            Log.message("", driver, true);
            Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
        }
        
        Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[1]//a[contains(text(),'"+docName+"')]", "xpath", docName, "uploaded as signed copy");
	}
	
	
	public void verifyEmailSignedCopy(String templateName, String fileName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 June 2020
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[1]//button[contains(text(),'Email Signed Copy')]", "xpath", "Email option button");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Email", "title", "false");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Attachments')]/../following-sibling::tr[1]//td[contains(text(),'"+fileName+"')]", "xpath", fileName, "under attachments in email page");
	}
	
	
	public void verifySignedOfflineTag(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 24 June 2020
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[1]/following-sibling::div/p[contains(text(),'Signed Offline')]", "xpath", "signed offline tag", templateName);
	}
	
	public void verifyChangeDocumentFunctionality(String templateName, String filePath)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 24 June 2020
		 */
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", templateName + "template");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1]/following-sibling::div[2]//div//span/a", "xpath", "current document");
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details Tab");
		
		
		Utils.waitForElement(driver, "//div[@class='tab-pane fade certificate_image_details active in']//div[@class='k-button k-upload-button']", globalVariables.elementWaitTime, "xpath");
		WebElement btn_browse = driver.findElement(By.xpath("//div[@class='tab-pane fade certificate_image_details active in']//div[@class='k-button k-upload-button']/input"));
        
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
            //Utils.waitForElement(driver, btn_uploadSignedCopy);
        	
		    ((RemoteWebElement) btn_browse).setFileDetector(detector);
		    
			btn_browse.sendKeys(file.getAbsolutePath());
        	
//        	btn_browse.sendKeys(path.getAbsoluteFile().toString());
            Log.message("Entered the absolute file path in the Upload Button");

        } catch (Exception e) {
            Log.message("", driver, true);
            Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
        }
		
		
		Utils.clickElement(driver, btn_SaveAfterUpdatingTemplate, "save button");
		Utils.clickElement(driver, btn_continue, "continue button");
	}
	
	public void verifyUpdatedDocument(String templateName, String docName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 24 June 2020
		 */
		Utils.waitForElement(driver, notification_updatedTemplateSuccessfully);
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", templateName + "template");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1]/following-sibling::div[2]//div//span/a[contains(text(),'"+docName+"')]", "xpath", docName, "updated document");
	}
	
	public void sendEmail(String templateName, String subject, String message) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 22 July 2020
		 */
		Utils.clickDynamicElement(driver, "//h4/a[contains(text(),'"+templateName+"')]/../following-sibling::div//button[contains(text(),'Email')]", "xpath", "Email button");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Email", "title", "false");
		Utils.enterText(driver, textBox_subject, subject, "subject");
		Utils.waitForElement(driver, frame_emailMessage);
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_message, message, "message");
		driver.switchTo().defaultContent();
		if(!checkbox_saveAttachments.isSelected())
			Utils.clickElement(driver, checkbox_saveAttachments, "Checkbox saying 'Check to save the attachments'");
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,-1000)");
		Utils.clickElement(driver, btn_sendEmail, "send email");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
		//Utils.verifyIfDataPresent(driver, "//h4/a[contains(text(),'"+templateName+"')]/../following-sibling::div//button[contains(text(),'Resend Email')]", "xpath", "Resent button", "contracts page");
	}
	
	public void sendEmailForSignedContract(String templateName, String subject, String message) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 22 July 2020
		 */
		Utils.clickDynamicElement(driver, "//h4/a[contains(text(),'"+templateName+"')]/../following-sibling::div//button[contains(text(),'Email Signed Copy')]", "xpath", "Email button");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send Email", "title", "false");
		Utils.enterText(driver, textBox_subject, subject, "subject");
		Utils.waitForElement(driver, frame_emailMessage);
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_message, message, "message");
		driver.switchTo().defaultContent();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,-1000)");
		Utils.clickElement(driver, btn_sendEmail, "send email");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
	}
	
	public void verifyDeleteFunctionality(String templateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 27 July 2020
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+templateName+"')]/ancestor::div[3]//i[@class='fa fa fa-trash-o close_card']", "xpath", "delete icon");
		Utils.clickElement(driver, btn_delete, "confirm button");
		Utils.clickElement(driver, notification_updatedTemplateSuccessfully, "deleted successfully message");
		Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+templateName+"')]", btn_addTemplate, "xpath", templateName, "contracts page");
	}
	
	public void verifyCorporationDetails(String corporationName, String signingPerson, String emailId) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 28 July 2020
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "in corp contacts page");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'"+signingPerson+"')]", "xpath", signingPerson, "signing person name");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'"+emailId+"')]", "xpath", emailId, "in corp conttracts page");
	}
	
}
