package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.inszoomapp.globalVariables.globalVariables;

public class CM_EmailsPage extends LoadableComponent<CM_EmailsPage> 
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "txtSearch")
	WebElement searchBox_Questionnaire;
	
	@FindBy(id = "btn_AddRemovequestionnaires")
	WebElement btn_AddRemoveQuestionnaires;
	
	@FindBy(id = "htmMsg__ctl0_Editor_contentIframe")
	WebElement frame_EmailBody;
	
	@FindBy(id = "btn_SendEmailtoClient")
	WebElement btn_SendEmailtoClient;
	
	@FindBy(xpath = "//input[@id='chkSendAsAttachYN']")
	WebElement checkBox_EmailAttachment;
	
	@FindBy(id = "ChkQuest")
	WebElement checkBox_IncludeQuestionnaires;
	
	@FindBy(id = "btn_Save")
	WebElement btn_SaveEConsent;
	
	@FindBy(css = "input[name ='chkDocs']")
	WebElement checkBox_EConsentName;
			
	@FindBy(css = "a[title='Choose e-Consents']")
	WebElement lnk_ChooseEConsents;
			
	@FindBy(id = "chkConsent")
	WebElement checkBox_EConsent;
	
	@FindBy(xpath = "//select[@name='cboVendorAcc']")
	WebElement dropDown_VendorAccess;
	
	@FindBy(xpath = "//a[contains(text(),'Advanced Options')]/i[@class = 'fa fa-chevron-up']")
	WebElement arrow_AdvancedOptions;
	
	@FindBy(xpath = "//a[contains(text(),'Advanced Options')]")
	WebElement lnk_AdvancedOptions;
	
	@FindBy(id = "txtSubject")
	WebElement textBox_Subject;
	
	@FindBy(xpath = "//html//body")
	WebElement textBox_EmailBody;
	
	@FindBy(xpath = "//a[@title='Select Email Message']")
	WebElement txt_SelectMessage;
	
	@FindBy(id = "txtBcc")
	WebElement textBox_Bcc;
	
	@FindBy(id = "txtTo")
	WebElement textBox_To;
	
	@FindBy(id = "tdfrom")
	WebElement textBox_From;
	
	@FindBy(id = "txtCc")
	WebElement textBox_Cc;
	
	@FindBy(id = "btn_Doneselectingemailrecipients")
	WebElement btn_DoneSelectingEmailRecipients;
	
	@FindBy(xpath = "(//td[1]/input[@name='chkToClntPara'])[1]/ancestor::tr[1]//td[@class='TDOCLIST'][2]")
	WebElement txt_emailId;
	
	@FindBy(xpath = "(//td[3]/input[@name='chkBccClntPara'])[1]")
	WebElement checkBox_Bcc;
	
	@FindBy(xpath = "(//td[2]/input[@name='chkCcClntPara'])[1]")
	WebElement checkBox_Cc;
	
	@FindBy(xpath = "(//td[1]/input[@name='chkToClntPara'])[1]")
	WebElement checkBox_To;
	
	@FindBy(xpath = "//a[@title='Choose Recipients of this Email']")
	WebElement lnk_ChooseRecipients;
	
	@FindBy(id = "btn_ComposeEmailforClient")
	WebElement btn_ComposeEmailforClient;
	
	@FindBy(xpath = "//b[contains(text(),'Please Note:')]/parent::font/font[3]/a")// Added By Saksham Kapoor on 22/07/2019
	WebElement txt_ChangeHRPPasswordURL;

	public CM_EmailsPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_EmailsPage(WebDriver driver) {
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
			Log.fail("Emails Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void getURLToChangePassword(String dataWrite, String sheetName, boolean screenshot) throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */

		String changeHRPPasswordURLTxt = "";

		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + globalVariables.subjectForInvitation + "') or contains(text(), 'Your invitation to INSZoom')]", "xpath", "Email containing link to change password");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com : View Email", "title", "false");

		try 
		{
			Utils.waitForElement(driver, txt_ChangeHRPPasswordURL);
			changeHRPPasswordURLTxt = txt_ChangeHRPPasswordURL.getText();
			Log.message("Fetched the URL to change password from the email.");
			
		} catch (Exception e) {
			Log.message("", driver, screenshot);
			Log.fail("Unable to fetch URL from email. Error Message - " + e.getMessage());
		}
		
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		writeExcel.setCellData("QA_ChangeHRPPasswordURLLink", sheetName, "Value", changeHRPPasswordURLTxt);

		driver.close();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Corporation Emails", "title", "false");
	}
	
	
	public void verifyRecipientEmailForClient() throws Exception
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 27 Nov 2019
		  */
		
		Utils.clickElement(driver, btn_ComposeEmailforClient, "compose email button");
		
		Utils.waitForElement(driver, "//th[contains(text(),'From')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.clickElement(driver, lnk_ChooseRecipients, "choose recipients link");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Select Email Recipients", "title", "false");
		
		/** click on checkbox(TO, CC, BCC) client Email ****/

		if (!(checkBox_To.isSelected())) {
			Utils.clickElement(driver, checkBox_To, "Checked Client To CheckBox");
		}
		
		Utils.clickElement(driver, checkBox_Cc, "Checked Client Cc CheckBox");
		
		Utils.clickElement(driver, checkBox_Bcc, "Checked Client Bcc CheckBox");
		
		Utils.waitForElement(driver, txt_emailId);
		String clientToCcBccEmail = txt_emailId.getText().trim().toString();
		
		if (clientToCcBccEmail.contains(" [Main Email Id]")) 
		{
			clientToCcBccEmail = clientToCcBccEmail.replace(" [Main Email Id]", "");
		}
		
		Utils.clickElement(driver, btn_DoneSelectingEmailRecipients, "done button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
		
		Utils.waitForElement(driver, textBox_To);
		String toEmailId = textBox_To.getText().trim().toString();
		
		Utils.waitForElement(driver, textBox_Cc);
		String ccEmailId = textBox_Cc.getText().trim().toString();
		
		Utils.waitForElement(driver, textBox_Bcc);
		String bccEmailId = textBox_Bcc.getText().trim().toString();
		
		if (clientToCcBccEmail.equals(toEmailId) && clientToCcBccEmail.equals(ccEmailId) && clientToCcBccEmail.equals(bccEmailId)) 
		{
			Log.pass("Retrieved Client Recipients Emails are loaded fine", driver, true);
		} 
		else 
		{
			Log.fail("Retrieved Client Recipients Emails are not loaded", driver, true);
		}
	}
	
	public void clickAdvancedOptionLink()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 28 Nov 2019
		  */
		
		 Utils.waitForElement(driver, lnk_AdvancedOptions);
		 try{
		 if(driver.findElements(By.xpath("//a[contains(text(),'Advanced Options')]/i[@class = 'fa fa-chevron-up']")).size() == 0)
		    Utils.clickElement(driver, lnk_AdvancedOptions, "advance option link");
		 }
		 catch(Exception e)
		 {
			 Log.message("Unable to click on advance option link");
		 }
	}
	
	public void setVendorAccess(String value)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 27 Nov 2019
		  */
		Utils.selectOptionFromDropDownByVal(driver, value, dropDown_VendorAccess);
	}

	public void enableAndChooseEConsent() throws Exception
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 27 Nov 2019
		  */
		Utils.waitForElement(driver, checkBox_EConsent);

    	if (!(checkBox_EConsent.isSelected())) 
    	{
    		Utils.clickElement(driver, checkBox_EConsent, "checked on e-consent check box");
    	}

		Utils.clickElement(driver, lnk_ChooseEConsents, "choose e-consents link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose e-Consent Documents", "title", "false");
		
		Utils.clickElement(driver, checkBox_EConsentName, "First E-Consents Name");
		
		Utils.clickElement(driver, btn_SaveEConsent, "save E-Consents button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
		
	}
	
		
	public void enableAndSelectQuestionnaires(String questionnaireName) throws Exception
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 27 Nov 2019
		  */

		 Utils.waitForElement(driver, checkBox_IncludeQuestionnaires);
		 if (!(checkBox_IncludeQuestionnaires.isSelected())) {
			Utils.clickElement(driver, checkBox_IncludeQuestionnaires, "checked Include questionnaire checkbox");
		}
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("javascript:window.scrollBy(0,2000)");
		 Utils.clickElement(driver, btn_AddRemoveQuestionnaires, "add remove questionnaire button");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		 
		 Utils.enterText(driver, searchBox_Questionnaire, questionnaireName, "questionnaire name");
		 
		 Utils.waitUntillLoaderDisappearsInHRP(driver);
		 
		 Utils.clickDynamicElement(driver, "//span[@class='content-headline' and contains(text(),'Automation-I-941: Application for Entrepreneur Parole.')]/ancestor::li/span[@class='pull-right']", "xpath", "add questionnaire");
		 
		 Utils.waitForElement(driver, "//a[contains(text(), '" + questionnaireName + "')]", globalVariables.elementWaitTime, "xpath");
		 
		 driver.close();
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
		 	
	}
	
	public void disableEmailAttachment() throws Exception
	{
		Utils.waitForElement(driver, checkBox_EmailAttachment);
		 if (checkBox_EmailAttachment.isSelected()) {
			Utils.clickElement(driver, checkBox_EmailAttachment, "checked email attachment checkbox");
		}
	}
	
	public void chooseMessageTemplateAndSetAdvanceOptions(String dataWrite, String sheetName, String client_changeRequestDescriptionDetails_1, String client_changeRequestDescriptionDetails_2, String client_changeRequestSubject, String client_Email_Template_Title, String questionnaire, WebDriver driver, boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		
	    try{
	    	Utils.clickElement(driver, btn_ComposeEmailforClient, "compose email button");
	    	
			Utils.clickElement(driver, txt_SelectMessage, "Select message link");
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.Com :: Choose Email Templates", "title", "false");
			
			Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ client_Email_Template_Title +"')]/../following-sibling::td/a[@id='btn_ChoosethisEmailTemplate']", "xpath", "choose link");

			

			String changeRequesiSubject = client_changeRequestSubject;
			String changeRequesiDescriptionDetails_1 = client_changeRequestDescriptionDetails_1;
			String changeRequesiDescriptionDetails_2 = client_changeRequestDescriptionDetails_2;

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
			
			Utils.waitForElement(driver, frame_EmailBody);
			Utils.scrollToElement(driver, frame_EmailBody);
			
			Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
			driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
			
			Utils.waitForElement(driver, textBox_EmailBody);
			String bodyOfEmailContent = textBox_EmailBody.getText().trim().toString();
			
			driver.switchTo().defaultContent();
			
			Log.message("Fetched Body of Email as " + bodyOfEmailContent);
			
			Utils.waitForElement(driver, textBox_Subject);
			String updatedSubject = textBox_Subject.getText().trim().toString();

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			writeExcel.setCellData("QA_ALoginComposeEmailSubjectFromClient", sheetName, "Value", updatedSubject);
			
			Boolean emailTemplateUpdatedStatus ;
			
			if (bodyOfEmailContent.contains(changeRequesiDescriptionDetails_1) || bodyOfEmailContent.contains(changeRequesiDescriptionDetails_2)) 
			{
				emailTemplateUpdatedStatus = true;
				Log.message("Email Template details are updated fine" + updatedSubject + " ; " + changeRequesiDescriptionDetails_1, driver, screenshot);
				Log.pass("Email Template details are updated fine");
			} 
			else 
			{
				Log.fail("Email Template details are not updated fine", driver, screenshot);
				emailTemplateUpdatedStatus = false;
			}

			if (emailTemplateUpdatedStatus) 
			{
				if (updatedSubject.contains(changeRequesiSubject)) {
					Log.pass("Email Body is updated fine", driver);
				} else {
					Log.fail("Email Body is not updated fine", driver);
				}
			} 
			else {
				Log.fail("Email Template details are not updated fine", driver, screenshot);
				emailTemplateUpdatedStatus = false;
			}
			
			Utils.waitForElement(driver, lnk_AdvancedOptions);
			Utils.scrollToElement(driver, lnk_AdvancedOptions);
			
			clickAdvancedOptionLink();

			Log.message("Advance Option selection", driver, true);
		
			setVendorAccess("Y");
			
			enableAndChooseEConsent();
		
			enableAndSelectQuestionnaires(questionnaire);
		
			disableEmailAttachment();
		
			Utils.clickElement(driver, btn_SendEmailtoClient, "send email button");

			
	    }catch(Exception e){
	    	Log.fail("Error while selecting template. ERROR - "+e.getMessage(), driver, screenshot);
	    }
	}
	
	public void sentEmail(String subject)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.verifyIfDataPresent(driver, "//font[contains(text(),'Subject')]/following-sibling::a[contains(text(),'"+ subject +"')]", "xpath", subject, "sent emails");
		
	}
}
