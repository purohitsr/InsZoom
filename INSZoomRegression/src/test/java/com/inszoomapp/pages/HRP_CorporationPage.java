package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class HRP_CorporationPage extends LoadableComponent<HRP_CorporationPage>
{

	private final WebDriver driver;
	
	@FindBy(css = "#header-nav > ul > li.dropdown.profile-dropdown > a")   //Added by Sharon Mathew on 03/09/2020
	WebElement tab_Profile;
	
	@FindBy(xpath = "//a[contains(@onclick,'LoadDocuments()')]")	     //Added by Sharon Mathew on 03/09/2020
	WebElement btn_clientDocBtn; 
	
	@FindBy(xpath="//a[contains(@href,'BeneficiaryList')]")		//Added by Sharon Mathew on 03/09/2020
	WebElement tab_corpClients;
	
	@FindBy(xpath = "//a[contains(text(),'Corp Users')]")	      //Created by Nitisha Sinha on 3/03/2020
	WebElement tab_corpUsers;  
	
	@FindBy(xpath = "//h2[contains(text(),'Digital Documents')]")	      //Created by Nitisha Sinha on 18/02/2020
	WebElement waitElement_documentHeader; 
	
	@FindBy ( xpath = "//div[@id='tabs-epr']//a[contains(text(),'Details')]")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement waitele_HRPTabs;
	
	@FindBy ( xpath = "//div[@class='profile-img project-img-owner text-center']/a/img")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement waitele_image;
	
	@FindBy(id="chkAttach")
	WebElement checkbox_saveAttachment;
	
	@FindBy(name="RowRemove")
	WebElement btn_removeAttachment;
	
	@FindBy(id="btn_AttachFiletoEmail")
	WebElement btn_done;
	
	@FindBy(id="objUploadDocs_BtnSubmit11_input")
	WebElement btn_UploadFileInput;
	
	@FindBy(id="btn_AddDeleteAttachment")
	WebElement btn_addDeleteAttachment;
	
	@FindBy(id="btn_Sendemail")
	WebElement btn_sendCorpEmail;
	
	@FindBy(xpath = "//a[contains(text(), 'Compose Email')]")
	WebElement btn_composeEmail;
	
	@FindBy(xpath="//div[@id='InboxMessages']//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;	
	
	@FindBy(xpath = "//a[@href='#tab-lca']")
	WebElement tab_LCA;
	
	@FindBy(xpath = "//a[contains(text(),'Custom Data')]")
	WebElement tab_customData;
	
	@FindBy(id="btn_Uploaddocuments")  //Edited by Souvik Ganguly on 28/06/2021
	WebElement btn_UploadFile;
	
	@FindBy(xpath = "//button[contains(text(), 'Upload')]")
	WebElement btn_Upload;
	
	@FindBy(css = "img[src='../../Content/img/Document.png']")
	WebElement btn_digitalDocuments;
	
	@FindBy(id="NotesListSearch")
	WebElement searchBox_notes;
	
	@FindBy(xpath="//li/a[contains(text(),'Notes')]")
	WebElement tab_notes;
	
	@FindBy(xpath="//a[contains(text(), 'Compose Email')]")
	WebElement btn_ComposeEmail;
	
	@FindBy(xpath = "//a[@href='#tab-emails' and text()='Emails']")
	WebElement tab_Email;
	
	@FindBy(id="btn_SendEmailTo(s)")
	WebElement btn_SendEmail;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="txtMsg")
	WebElement txtbox_Message;
	
	@FindBy(css="textarea[name='txtTo']")
	WebElement txtbox_To;
	
	@FindBy(xpath = "//h2[contains(text(),'Corp Users')]")
	WebElement waitElement_corpUser;                                                // Added by Nitisha Sinha on 28th July, 2020

	public HRP_CorporationPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public void clickEmailsTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 03 Dec 2019
		  */ 
		
		Utils.clickElement(driver, tab_Email, "Emails tab");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
	}
	
	
	public void sendEmail(String sendSubject, String sendMessage, String sendEmailID) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 03 Dec 2019
		 */ 
		
		Utils.clickElement(driver, btn_ComposeEmail, "Compose Email");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Send E-mail", "title", "false");
		
		Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
		
		Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
		
		Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
	}
	
	
	public void verifyIfEmailPresent(String email)
	{
		/*
		 * Created By : Saksham Kapoor 
		 * Created On : 03 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + email + "')]", "xpath", email, "Sent Emails tab");
	}
	
	public void clickNotesTab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2019
		 */
		Utils.clickElement(driver, tab_notes, "notes tab");
	}
	
	public void verifyNotesPresent(String notesDetails) {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2019
		 */
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + notesDetails + "')]", "xpath", notesDetails, "notes details in corp");
	}

	
	public void verifyNotesSearchBox(String notesDetails) {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2019
		 */
		Utils.enterText(driver, searchBox_notes, "JUNK", "search box");
		//WebElement loader = driver.findElement(By.xpath("//div[@class='k-loading-mask']"));
		Utils.waitForElementPresent(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		//Utils.waitForElement(driver, "//div[@id='NotesGrid']//div[@class='k-grid-norecords'][contains(text(),'No Records Found')]", globalVariables.elementWaitTime, "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@id='NotesGrid']//div[@class='k-grid-norecords'][contains(text(),'No Records Found')]", "xpath", "No Records Found", "when junk data is added");
	
		Utils.enterText(driver, searchBox_notes, notesDetails, "search box");
		Utils.waitForElementPresent(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.waitForElement(driver, "//div[contains(text(),'" + notesDetails + "')]", globalVariables.elementWaitTime, "xpath");
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + notesDetails + "')]", "xpath", notesDetails, "search box");
	}
	
	public void clickDigitalDocuments()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */
		
		Utils.clickElement(driver, btn_digitalDocuments, "Digital Documents");
	}
	
	
	public void uploadFile(String filePath) throws Exception
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 29/06/2021
		 * 
		 */
		
		Utils.clickElement(driver, btn_Upload, "Upload");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom::Upload Documents", "title", "false");
		
		Utils.waitForPageLoad(driver);
		
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
			Utils.waitForElement(driver, "div[class='DropZone1']", globalVariables.elementWaitTime, "css");
			droparea = driver.findElement(By.cssSelector("div[class='DropZone1']"));
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
								
				Utils.waitForElement(driver, "span[class='ruUploadProgress ruUploadSuccess']", globalVariables.elementWaitTime, "css");

				Utils.waitForElement(driver, "input[name='RowRemove']", globalVariables.elementWaitTime, "css");
				
				//Thread.sleep(3000);
				
				Utils.clickElement(driver, btn_UploadFile, "Upload Button");
				
				Thread.sleep(5000);			
				
				//Utils.waitForAllWindowsToLoad(1, driver);
				
				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
			}
			
			
			public void verifyIfDocumentPresent(String fileName)
			{
				/*
				 * Created By : Saksham Kapoor
				 * Created On : 13 Nov 2019
				 * 
				 */
				WebElement fileText=driver.findElement(By.xpath("//a[contains(text(), '" + fileName + "')]"));
				Utils.waitForElement(driver, fileText);
				Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + fileName + "')]", "xpath", fileName, "Documents");
			}
			
			
			public void viewDocument(String fileNameWithoutExtensionFNP, RemoteWebDriver remoteDriver) throws Exception
			{
				/*
				 * Created By : Saksham Kapoor
				 * Created On : 11 Feb 2019
				 * 
				 */
				
				Utils.openDownloadsWindow(remoteDriver);
				
				Utils.clickDynamicElement(driver, "//a[contains(text(), '" + fileNameWithoutExtensionFNP + "')]", "xpath", "Uploaded File");
				
				Thread.sleep(10000);
				
				Utils.verifyIfDownloaded(remoteDriver);
				
			} 
		 
			public void clickCustomDataTab()
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 14 Feb 2019
				 */
				Utils.clickElement(driver, tab_customData, "custom data");
			}
			
			public void verifyDefaultCustomData(String field, String data)
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 14 Feb 2019
				 */
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
			}
			
			public void verifyCorpCustomData(String field, String data)
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 14 Feb 2019
				 */
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
			}
			
			public void verifySignatoryInfo(String name, String title, String mobileNumber, String email)
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 17 Feb 2019
				 */
				//Utils.verifyIfDataPresent(driver, "//h2[text()='Signatory Information']/../following-sibling::div//a[contains(text(),'"+name+"')]", "xpath", name, "name");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Signatory Information']/../following-sibling::div//td[contains(text(),'"+title+"')]", "xpath", title, "title");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Signatory Information']/../following-sibling::div//td[contains(text(),'"+email+"')]", "xpath", email, "email");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Signatory Information']/../following-sibling::div//td[contains(text(),'"+mobileNumber+"')]", "xpath", mobileNumber, "mobile");
				//Utils.verifyIfDataPresent(driver, "//h2[text()='Signatory Information']/../following-sibling::div[1]//a[contains(text(),'"+name+"')]/../following-sibling::table//tr/td[contains(text(),'"+title+"')]/../following-sibling::tr/td[contains(text(),'"+email+"')]/../following-sibling::tr/td[contains(text(),'"+mobileNumber+"')]", "xpath", "signatory info", "corp page");
			}
			
			
			public void verifyAdministrativeContactInfo(String name, String title, String mobileNumber, String email)
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 17 Feb 2019
				 */
				
				//Utils.verifyIfDataPresent(driver, "//h2[text()='Administrative Contact']/../following-sibling::div//a[contains(text(),'"+name+"')]", "xpath", name, "name");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Administrative Contact']/../following-sibling::div//td[contains(text(),'"+title+"')]", "xpath", title, "title");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Administrative Contact']/../following-sibling::div//td[contains(text(),'"+email+"')]", "xpath", email, "email");
				Utils.verifyIfDataPresent(driver, "//h2[text()='Administrative Contact']/../following-sibling::div//td[contains(text(),'"+mobileNumber+"')]", "xpath", mobileNumber, "mobile");
				//Utils.verifyIfDataPresent(driver, "//h2[text()='Administrative Contact']/../following-sibling::div[1]//a[contains(text(),'"+name+"')]/../following-sibling::table//tr/td[contains(text(),'"+title+"')]/../following-sibling::tr/td[contains(text(),'"+email+"')]/../following-sibling::tr/td[contains(text(),'"+mobileNumber+"')]", "xpath", "signatory info", "corp page");
			}
			
			
			public void clickLCATab()
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 24 Feb 2019
				 */
				Utils.clickElement(driver, tab_LCA, "LCA tab");
			}
		 

			public void verifyCompanyInfoForFilingPetitions(String businessDescription, String businessType, String yearEstablished, String placeOfIncorporation, String numberOfEmployees, String doingBusinessAs, String grossAnnualIncome, String netAnnualIncome, String taxID, String stateTax, String naicsCode)
			{
				/*
				 * Created By : Likitha Krishna
				 * Created On : 25 Feb 2019
				 */
				
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Business Type')]/following-sibling::td[1][contains(text(),'"+businessType+"')]", "xpath", businessDescription, "business description");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Gross Annual Income')]/following-sibling::td[1][contains(text(),'"+grossAnnualIncome+"')]", "xpath", grossAnnualIncome, "gross annual income");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Year Established')]/following-sibling::td[1][contains(text(),'"+yearEstablished+"')]", "xpath", yearEstablished, "year established");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Net Annual Income')]/following-sibling::td[1][contains(text(),'"+netAnnualIncome+"')]", "xpath", netAnnualIncome, "net annual income");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Place Of Incorporation')]/following-sibling::td[1][contains(text(),'"+placeOfIncorporation+"')]", "xpath", placeOfIncorporation, "place of incorporation");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Tax ID')]/following-sibling::td[1][contains(text(),'"+taxID+"')]", "xpath", taxID, "tax ID");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Number Of Employees')]/following-sibling::td[1][contains(text(),'"+numberOfEmployees+"')]", "xpath", numberOfEmployees, "number of employee");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'State Tax ID')]/following-sibling::td[1][contains(text(),'"+stateTax+"')]", "xpath", stateTax, "state tax");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Doing Business as (DBA)')]/following-sibling::td[1][contains(text(),'"+doingBusinessAs+"')]", "xpath", doingBusinessAs, "doing business as");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'NAICS Code')]/following-sibling::td[1][contains(text(),'"+naicsCode+"')]", "xpath", naicsCode, "NAICS code");
				Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Business Description')]/following-sibling::td[1][contains(text(),'"+businessDescription+"')]", "xpath", businessDescription, "business description");
			}
			
			
						public void verifyIfContentEmailPresent(String corpContentEmailSubject, String emailMessage)
			{
				/*
				 * Created By : Saksham Kapoor
				 * Created On : 16 Feb 2020
				 */
				
				Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + corpContentEmailSubject + "')]", "xpath", corpContentEmailSubject, "Emails tab");
				
				Utils.clickDynamicElement(driver, "//a[contains(text(), '" + corpContentEmailSubject + "')]", "xpath", "Received Email");
				
				Utils.verifyIfDataPresent(driver, "//div[@class='modal fade in']//h4[contains(text(), '" + corpContentEmailSubject + "')]", "xpath", corpContentEmailSubject, "Detailed Email popup");
			
				Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Detailed Email popup");
				
			}
			
			
			public void verifyIfEmailNotPresent(String emailSubjectWithoutAccess)
			{
				/*
				 * Created By: Saksham Kapoor
				 * Created On: 26/02/2020
				 * 
				 */
				
				Utils.verifyIfDataNotPresent(driver, "//a[contains(text(), '" + emailSubjectWithoutAccess + "')]", waitElement_Pagination, "xpath", emailSubjectWithoutAccess, "Received emails list");
			}
			
			
			public void composeEmail(String sendEmailID, String sendMessage, String sendSubject) throws Exception
			{
				/*
				 * Created By : Saksham Kapoor
				 * Created On : 16 Dec 2019
				 */
				
				Utils.clickElement(driver, btn_composeEmail, "'Compose Email'");
				
				Utils.waitForAllWindowsToLoad(2, driver);
				Utils.switchWindows(driver, "INSZoom.com - Send E-mail", "title", "false");
				
				Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
				
				Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
				
				Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
				
				Utils.clickElement(driver, btn_sendCorpEmail, "Send Email");
				
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
			}
			
			
		public void verifyIfSentEmailPresent(String sendMessage, String sendSubject)
		{
			/*
			 * Created By: Saksham Kapoor
			 * Created On: 26/02/2020
			 * 
			 */
			
			Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Sent Emails table");
			
			Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Sent email");
			
//			Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email popup");
			
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
		} 
		
		
		public void verifyIfEmailPresentWithAttachment(String clientContentEmailSubject, String emailMessage, String fileName)
		{
			/*
			 * Created By: Saksham Kapoor
			 * Created On: 26/02/2020
			 * 
			 */
			
			Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
			
			Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
			
//			Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
			
			Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
			
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
		}
			
			
		public void composeEmailWithAttachment(String sendEmailID, String sendMessage, String sendSubject, String filePath, String fileName) throws Exception
		{
			/*
			 * Edited By: Souvik Ganguly
			 * Edited On: 07/07/2021
			 * 
			 */
			
            Utils.clickElement(driver, btn_composeEmail, "'Compose Email'");
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - Send E-mail", "title", "false");
			
			Utils.clickElement(driver, btn_addDeleteAttachment, "'Add/Delete Attachments'");
			
			Utils.waitForAllWindowsToLoad(3, driver);
			Utils.switchWindows(driver, "INSZoom Email Attachment", "title", "false");
			
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
				Log.fail("Not able to fetch the drop area for file", driver, true);
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
//			input.sendKeys(filepath.getAbsoluteFile().toString());
			
			Utils.waitForElement(driver, btn_removeAttachment);
			
			Utils.clickElement(driver, btn_done, "Done");
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - Send E-mail", "title", "false");
			
			if(!checkbox_saveAttachment.isSelected())
				Utils.clickElement(driver, checkbox_saveAttachment, "Check to save the attachments");
			
			Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
			
			Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
			
			Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
			
			Utils.clickElement(driver, btn_sendCorpEmail, "Send Email");
			
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
		}
		
		

		public void verifyDigitalDocumentsTab(boolean value)
		{
			/*
			 * Added By: Kuchinad Shashank
			 * Added On: 14/02/2020
			 * Modified by NItisha Sinha due to UI change
			 */
			
			if(value)
			{
				Utils.waitForElement(driver, "//h2[contains(text(),'Digital Documents')]", globalVariables.elementWaitTime, "xpath");
				if(Utils.isElementPresent(driver, "//h2[contains(text(),'Digital Documents')]", "xpath"))
				{
					Log.pass("Verified. The Digital Documents access is provided", driver, true);
				}
				else
				{
					Log.fail("The Digital documents access is not provied", driver, true);
				}
			}
			else
			{
				
				Utils.waitForElement(driver, "//div[@id='DocumentListNoAccess'][contains(text(),'This feature is not active.')]", globalVariables.elementWaitTime,"xpath");
				if(Utils.isElementPresent(driver, "//div[@id='DocumentListNoAccess'][contains(text(),'This feature is not active.')]", "xpath"))
				{
					Log.pass("Verified. The Digital Documents access is not provided", driver, true);
				}
				else
				{
					Log.fail("The Digital documents access is provied", driver, true);
				}
			}
		}
	
	

	public void verifyDocumentUpload(boolean value)
    {
		// Created by Nitisha Sinha
        // Created on 18/02/20
    	
    	if(value)
		{
			Utils.verifyIfDataPresent(driver, "//button[contains(text(),'Upload')]", "xpath", "Document upload button", "Documents under FNP Home Page");
		}
		
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//button[contains(text(),'Upload')]", waitElement_documentHeader, "xpath", "Document upload button", "Documents under FNP Home Page");
		}
    } 
	
	
	public void verifyCustomDataTab(boolean value)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 14/02/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='tabs-epr']//a[contains(text(),'Custom Data')]", "xpath", "Custom Data Tab","Corporation Page in HRP Login");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//div[@id='tabs-epr']//a[contains(text(),'Custom Data')]", waitele_HRPTabs , "xpath","Custom Data Tab","Corporation Page in HRP Login");
		}
	}
	
	
	public void verifyEmailsAccess(boolean value)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 14/02/2020
		 */
		
		if(value)
		{
			if(Utils.isElementPresent(driver, "//a[contains(text(),'Compose Email')]", "xpath"))
			{
				Log.pass("Verified. The Emails access is provided");
			}
			else
			{
				Log.fail("The Emails access is not provied",driver,true);
			}
		}
		else
		{
			if(!Utils.isElementPresent(driver, "//a[contains(text(),'Compose Email')]", "xpath"))
			{
				Log.pass("Verified. The Emails access is not provided");
			}
			else
			{
				Log.fail("The Emails access is provied",driver,true);
			}
		}
	
	}
	
	
	public void verifyLCATab(boolean value)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 14/02/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='tabs-epr']//a[contains(text(),'LCA')]", "xpath", "LCA Tab","Corporation Page in HRP Login");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//div[@id='tabs-epr']//a[contains(text(),'LCA')]", waitele_HRPTabs , "xpath","LCA Tab","Corporation Page in HRP Login");
		}
	} 
	
	
	public void clickCorpUsersTab(boolean value)
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 3/03/2020
		 * Modified by Nitisha Sinha on 31st July, 2020
		 * Modification done: changed click function
		 */
		 
		Utils.clickUsingAction(driver, tab_corpUsers);
		 
	} 
	
	
	public void verifyAllCorpUsers()
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 3/03/2020
		 */
		
		try{
			
			Utils.waitForElementPresent(driver, "//div[@id='CorpAddlnContact']//tr/td[1]", "xpath");
			
            List<WebElement> temp = driver.findElements(By.xpath("//div[@id='CorpAddlnContact']//tr/td[1]"));
            
            List<String> corpUsers = new ArrayList<String>();
           
            System.out.println(temp.size());
            
            for(int i = 0; i < temp.size(); i += 1)
            {
                corpUsers.add(temp.get(i).getText().trim());
            }
           
            if(globalVariables.corporationUsersUnderCorportion.containsAll(corpUsers) && corpUsers.containsAll(globalVariables.corporationUsersUnderCorportion))
            {
            	Log.pass("Successfully verified the Functionality of All Corp Users");
            }
            else
            {
            	Log.fail("Failed to verify the Functionality of All Corp Users");
            }
      }catch(Exception e){
            Log.fail("Unable to fetch corporation users");
      }
		 
	}
	
	
	public void verifyIfSentEmailPresentWithAttachment(String sendSubjectAttachment, String sendMessage, String fileNameWithoutExtension)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 27/04/2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendSubjectAttachment + "')]", "xpath", sendSubjectAttachment, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubjectAttachment + "')]", "xpath", "Sent email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileNameWithoutExtension + "')]", "xpath", fileNameWithoutExtension, "Attachments");
	}
	
	public void verifyCrimeInfo(String data)
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On : 12 July 2021
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Crime Info')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, "crime info field");
	}
	
	public void clickOnClientTab()
	{
		/*
		 * Created By : Sharon Mathew
		 * Created On : 06 Sept 2021
		 */
		Utils.clickElement(driver, tab_corpClients, "Client Tab");
	}
	
	public void selectClientRecord(String clientFname)
	{
		/*
		 * Created By : Sharon Mathew
		 * Created On : 06 Sept 2021
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+clientFname+"')]", "xpath", "Client "+clientFname);
	}
	
	public void clickClientDocument()
	{
		Utils.clickElement(driver, btn_clientDocBtn, "Client Document Button");
	}
	
	
	public void switchToHRPHomepage()
	{
		try {
			Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
			Utils.waitForElement(driver, tab_Profile);
		} catch (Exception e) {
			Log.fail(" ERROR - "  + e.getMessage(), driver, true);
		}
	}
	
	
	
}
