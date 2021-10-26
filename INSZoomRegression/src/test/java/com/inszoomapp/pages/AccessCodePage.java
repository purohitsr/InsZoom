package com.inszoomapp.pages;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class AccessCodePage extends LoadableComponent<AccessCodePage>
{
	private final WebDriver driver;
	private String appUrl;
	
	
	

	@FindBy(css="table .DropZone1")
	WebElement section_uploadZone;
	
	@FindBy(id="btn_Uploaddocuments")
	WebElement btn_UploadDoccuments_Save;
	
	@FindBy(id="objUploadDocs_BtnSubmit11_input")//Added by Saurav on 9th June,2021
	WebElement btn_Browse;

	@FindBy(css="table .DropZone1")//Added by Saurav on 9th June,20201
	WebElement section_dropZone;
	
	@FindBy(id="lblChkQuestSummary")
	WebElement checkbox_completedQuestionnaire;
	
	@FindBy(id="btn_SaveLinkeddigitaldocs.")
	WebElement btn_SaveUploadedDocument;
	
	@FindBy(css="input[value='Remove']")
	WebElement btn_Remove;
	
	@FindBy(id="btn_Saveuploadeddocuments")
	WebElement btn_UploadFile;
	
	@FindBy(id="btn_UploadNewDocument")
	WebElement btn_UploadNewDoc;
	
	@FindBy(xpath="//button[contains(text(), 'Upload')]")
	WebElement btn_UploadDocument;
	
	@FindBy(css="i[class='fa fa-upload fa-stack-1x fa-inverse ActionClass']")
	WebElement icon_UploadDocument;
	
	@FindBy(id="btn_Quit")
	WebElement btn_Quit;
	
	@FindBy(id="btn_Save")
	WebElement btn_Save;
	
	@FindBy(id="_ctl0_txtLname")
	WebElement txtbox_LastName;
	
	@FindBy(xpath="//button[contains(text(), 'Send Confirmation')]")
	WebElement btn_SendConfirmation;
	
	@FindBy(css = "input[name='txtAccessId']")
	WebElement txtbox_AccessCode;
	
	@FindBy(css = "input[title='Submit']")
	WebElement btn_Submit;
	
	public AccessCodePage(WebDriver driver, String url) {

		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public void enterAccessCodeAndSubmit(String accessCode) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/11/2019
		 * 
		 */
		
		Utils.enterText(driver, txtbox_AccessCode, accessCode, "Access Code textbox");
		
		Utils.clickElement(driver, btn_Submit, "Submit button");
	}
	
	
	public void verifyAccessCodePage() 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/11/2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//button[contains(text(), 'Send Confirmation')]", "xpath", "Send Confirmation Button", "Access Code Page");
		
	}
	
	
	public void editQuestionnaire(String questionnaire) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/11/2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", "Questionnaire to Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Client Questionnaire Update", "title", "false");
		
		Utils.waitForElement(driver, "sectionFrame", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("sectionFrame");
		Utils.enterText(driver, txtbox_LastName, "Last Name", "'Last Name' text box");
		
		Utils.clickElement(driver, btn_Save, "Save Button");
		
		driver.switchTo().defaultContent();
		
		
		Utils.waitForElement(driver, "sectionFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("sectionFrame");
		
		Utils.verifyIfDataPresent(driver, "//input[@value='Last Name']", "xpath", "Last Name", "'Last Name'");
		
		driver.switchTo().defaultContent();
		
		driver.close();		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Document List", "title", "false");
	}
	
	
	public void editCorporationQuestionnaire(String questionnaire, String corpName) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/11/2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", "Questionnaire to Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Corporation Questionnaire Update", "title", "false");
		
		Utils.waitForElement(driver, "sectionFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("sectionFrame");
		Utils.enterText(driver, txtbox_LastName, "Last Name", "'Last Name' text box");
		
		Utils.clickElement(driver, btn_Save, "Save Button");
		
		driver.switchTo().defaultContent();
		Utils.waitForElement(driver, "sectionFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("sectionFrame");
		
		Utils.verifyIfDataPresent(driver, "//input[@value='Last Name']", "xpath", "Last Name", "'Last Name'");
		
		driver.switchTo().defaultContent();
		
		driver.close();		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Questionnaire for " + corpName, "title", "false");
	}
	
	
	public void uploadClientDocument(String path) throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 22/06/2021
		 * Added a Java script click for Save button
		 */
		
		Utils.clickElement(driver, btn_UploadDocument, "Upload Document");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Upload Documents", "title", "false");
		
		path = System.getProperty("user.dir")+path;
		String os = System.getProperty("os.name");
		if(os.contains("Linux")){
			path = path.replace("\\", "/");
		Log.message("Final File Path "+path);
		
		}
		
		//File filepath = new File(path);
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(path);

		if (!file.exists())
			throw new WebDriverException("File not found: " + file.toString());
        
		WebElement droparea;
		try {
            Utils.waitForElement(driver, section_uploadZone);
			droparea = driver.findElement(By.cssSelector("table .DropZone1"));
		} catch (Exception e) {
			droparea = driver.findElement(By.cssSelector("#upload-dropzone > div.dropzone > div > div > div"));
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

//		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
//		input.sendKeys(filepath.getAbsoluteFile().toString());
		
		
		WebElement ele = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
	    ((RemoteWebElement) ele).setFileDetector(detector);
	    
		ele.sendKeys(file.getAbsolutePath());
		
		Utils.waitForElement(driver, btn_Remove);
		
		/*Utils.clickElement(driver, btn_UploadFile, "'Upload' button");
		Utils.clickUsingAction(driver, btn_UploadFile);*/
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btn_UploadDoccuments_Save);
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Document List", "title", "false");
	}
	
	
		public void uploadCaseDocument(String path) throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 09/06/2021
		 * added Utils.waitForElement(driver, section_dropZone);
		 *       Utils.waitForElement(driver, btn_Browse);
		 */
		
		Utils.clickElement(driver, icon_UploadDocument, "Upload Document");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Digital Documents List", "title", "false");
		
		Utils.clickElement(driver, btn_UploadNewDoc, "Upload New Doc.");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Upload Documents", "title", "false");
		
		path = System.getProperty("user.dir")+path;
		String os = System.getProperty("os.name");
		if(os.contains("Linux")){
			path = path.replace("\\", "/");
		Log.message("Final File Path "+path);
		
		}
		
		File filepath = new File(path);
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(path);

		if (!file.exists())
			throw new WebDriverException("File not found: " + file.toString());

//		if (!filepath.exists())
//			throw new WebDriverException("File not found: " + filepath.toString());
        
		
		WebElement droparea;
		try {

			Utils.waitForElement(driver, section_dropZone);
			Utils.waitForElement(driver, btn_Browse);
			droparea = driver.findElement(By.cssSelector("table .DropZone1"));
		} catch (Exception e) {
			droparea = driver.findElement(By.cssSelector("#upload-dropzone > div.dropzone > div > div > div"));
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

//		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
//		input.sendKeys(filepath.getAbsoluteFile().toString());
		
		WebElement ele = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
	    ((RemoteWebElement) ele).setFileDetector(detector);
	    
		ele.sendKeys(file.getAbsolutePath());
		
		Utils.waitForElement(driver, btn_Remove);
		
		Utils.clickElement(driver, btn_UploadDoccuments_Save, "'Upload' button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Digital Documents List", "title", "false");	
		
		Utils.clickElement(driver, btn_SaveUploadedDocument, "'Save'");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Advanced Document List", "title", "false");
	}
	
	
	public void sendConfirmation()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 21/11/2019
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_completedQuestionnaire, "Check box for completion");
		
		Utils.clickElement(driver, btn_SendConfirmation, "Send Confirmation");
		
		Utils.verifyIfDataPresent(driver, "i[class='fa fa-check-circle fa-fw fa-lg']", "css", "Conformation Message", "Access Code Page");
	}

}
