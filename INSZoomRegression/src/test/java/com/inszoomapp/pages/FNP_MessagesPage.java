package com.inszoomapp.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class FNP_MessagesPage extends LoadableComponent<FNP_MessagesPage> 
{
	private WebDriver driver;
	
	@FindBy(xpath = "//td[contains(text(),'Corporation')]")
	WebElement header_Corporation;
	
	@FindBy(xpath = "//td[contains(text(),'Vendor Case Managers')]")
	WebElement header_VendorCaseManger;
	
	@FindBy(xpath = "//td[contains(text(),'Case Managers')]")
	WebElement header_CaseManger;
	
	@FindBy(xpath = "//a[contains(text(),'Choose Recipients')]")
	WebElement lnk_ChooseRecipients;
	
	//Added By Saurav on 26th feb 2020
		
	@FindBy(xpath="//h2[contains(text(),'MESSAGES')]")
	WebElement pageheader_Messages;
	
	//
	
	@FindBy(id="chkAttach")
	WebElement checkbox_attachFile;
	
	@FindBy(id="btn_AttachtoEmail")
	WebElement btn_done;
	
	@FindBy(id="btn_AttachFileToEmail")
	WebElement btn_attach;
	
	@FindBy(id="txtFileUpload")
	WebElement btn_UploadFileInput;
	
	@FindBy(id="btn_AddDeleteAttachment")
	WebElement btn_AddDeleteAttachment;
	
	@FindBy(css="a[onclick='ComposeNewEMail();']")
	WebElement btn_composeEmail;
	
	@FindBy(xpath="//div[@id='InboxMessages']//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;
	
	@FindBy(id="btn_SendEmailTo(s)")
	WebElement btn_SendEmail;
	
	@FindBy(xpath="//a[contains(text()[2], 'Compose Email')]")
	WebElement btn_ComposeEmail;
	
	@FindBy(id="txtSubject")
	WebElement txtbox_Subject;
	
	@FindBy(id="txtMsg")
	WebElement txtbox_Message;
	
	@FindBy(css="textarea[name='txtTo']")
	WebElement txtbox_To;

	public FNP_MessagesPage(WebDriver driver) 
	{
		this.driver = driver;
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
	
	
	public void sendEmail(String sendEmailID, String sendMessage, String sendSubject) throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 02/12/2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_ComposeEmail, "'Compose Email' button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com-Send Email", "title", "false");
		
		Utils.enterText(driver, txtbox_To, sendEmailID, "'To' email address");
		
		Utils.enterText(driver, txtbox_Subject, sendSubject, "Subject");
		
		Utils.enterText(driver, txtbox_Message, sendMessage, "Message");
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
		
	}
	
	
	public void verifyIfEmailPresent(String sendEmailID, String sendMessage, String sendSubject)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 02/12/2019
		 * 
		 */
		
		driver.navigate().refresh();
		//Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendEmailID + "')]", "xpath", sendEmailID, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Sent email");
		
	//	Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
	}
	
	
	public void verifyIfSentEmailPresent(String sendEmailID, String sendMessage, String sendSubject)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 08/06/2021
		 */
		
		driver.navigate().refresh();
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendEmailID + "')]", "xpath", sendEmailID, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Sent email");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
	}
	
	
	public void verifyIfEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 18/12/2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
		
		Utils.verifyIfDataPresent(driver, "//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		
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
	
	
	public void composeEmailWithAttachment(String sendEmailID, String sendMessage, String sendSubject, String filePath, String fileName) throws Exception
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 08/06/2021
		 */
		
		Utils.clickElement(driver, btn_composeEmail, "'Compose Email'");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com-Send Email", "title", "false");
		
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
			Log.message("Entered the absolute file path in the Upload Button", driver, true);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}
		
		Thread.sleep(3000);
		
		Utils.clickElement(driver, btn_attach, "'Attach'");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + fileName + "')]", "xpath", fileName, "uploaded files");
		
		Thread.sleep(3000);
		
		Utils.clickElement(driver, btn_done, "Done");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com-Send Email", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + fileName + "')]", "xpath", fileName, "email message");
		Utils.waitForElement(driver, "img[alt='View Attachment']", globalVariables.elementWaitTime, "css");
		
		Thread.sleep(3000);
		
		if(!checkbox_attachFile.isSelected())
			Utils.clickElement(driver, checkbox_attachFile, "Checkbox saying 'Check to save the attachments'");
		
		Log.message("", driver, true);
		Thread.sleep(3000);
		
		Utils.clickElement(driver, btn_SendEmail, "Send Email");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
	}
	
	
	public void verifyIfSentEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 08/06/2021
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
	}
	
	
	 public void verifyMessagesPage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  */
	    try{
				 
		     Utils.verifyIfStaticElementDisplayed(driver, pageheader_Messages, "In header section", "In Messages Page");
				
	       }catch(Exception e){
			 Log.failsoft("Verification of Messages Header failed", driver);
		    }

		} 
	 
	 public void clickComposeEmail(boolean screenshot)  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 12 March 2019
		  */ 
		 Utils.clickElement(driver, btn_ComposeEmail, "Compose Email in Messages Page");
	 }  
	 
	 
	 public void verifySendEmailPage() 
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
	    	
	    	Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "INSZoom.com-Send Email", "title", "false");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
		 
		 
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com-Send Email page failed", driver);
	    }
	}
	 
	 public void verifyRestrictClientAbilityViewOrSelectEmailOf(String option, String caseManagerEmailID, String vendorCaseManagerEmailID) throws Exception
		{
			/*
			 * Created By: Likitha Krishna
			 * Created On: 09 Oct 2020
			 */
			
			Utils.clickElement(driver, btn_ComposeEmail, "'Compose Email' button");
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com-Send Email", "title", "false");
			
			Utils.clickElement(driver, lnk_ChooseRecipients, "choose recipients link");
			
			Utils.waitForAllWindowsToLoad(3, driver);
			Utils.switchWindows(driver, "INSZoom.com - Select Email Recipients", "title", "false");
			
			if(option.equals("Vendor Case Managers"))
			{	
				Utils.verifyIfDataNotPresent(driver, "//td[text()='Vendor Case Managers']", header_Corporation, "xpath", "Vendor Case Manager Tab", "Choose Recipient Page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Case Managers']", "xpath", "Case Manager Tab", "Choose recipients page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Case Managers']/ancestor::table[2]//td[contains(text(),'"+caseManagerEmailID+"')]", "xpath", caseManagerEmailID, "under case manager tab");
			}
			
			else if(option.equals("Case Managers"))
			{	
				Utils.verifyIfDataNotPresent(driver, "//td[text()='Case Managers']", header_Corporation, "xpath", "Case Manager Tab", "Choose Recipient Page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Vendor Case Managers']", "xpath", "Vendor Case Manager Tab", "Choose recipients page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Vendor Case Managers']/ancestor::table[2]//td[contains(text(),'"+vendorCaseManagerEmailID+"')]", "xpath", vendorCaseManagerEmailID, "under vendor case manager tab");
			}
			
			else if(option.equals("None"))
			{
				Utils.verifyIfDataPresent(driver, "//td[text()='Case Managers']", "xpath", "Case Manager Tab", "Choose recipients page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Case Managers']/ancestor::table[2]//td[contains(text(),'"+caseManagerEmailID+"')]", "xpath", caseManagerEmailID, "under case manager tab");
				Utils.verifyIfDataPresent(driver, "//td[text()='Vendor Case Managers']", "xpath", "Vendor Case Manager Tab", "Choose recipients page");
				Utils.verifyIfDataPresent(driver, "//td[text()='Vendor Case Managers']/ancestor::table[2]//td[contains(text(),'"+vendorCaseManagerEmailID+"')]", "xpath", vendorCaseManagerEmailID, "under vendor case manager tab");
			}
			
			else if(option.equals("Both"))
			{
				Utils.verifyIfDataNotPresent(driver, "//td[text()='Case Managers']", header_Corporation, "xpath", "Case Manager Tab", "Choose Recipient Page");
				Utils.verifyIfDataNotPresent(driver, "//td[text()='Vendor Case Managers']", header_Corporation, "xpath", "Vendor Case Manager Tab", "Choose Recipient Page");
			}
			
		}
	 
	 
}
