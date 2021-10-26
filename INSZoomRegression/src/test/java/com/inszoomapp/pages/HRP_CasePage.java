package com.inszoomapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;


public class HRP_CasePage extends LoadableComponent<HRP_CasePage> 
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(name="sendEmailBtn")
	WebElement btn_sendEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;
	
	@FindBy(id="ReplyButton")
	WebElement btn_reply;
	
	@FindBy(xpath="//div[@id='InboxMessages']//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;

	@FindBy(css = "a[href='#tab-emails']")
	WebElement tab_emails;
	
	
	
	@FindBy(xpath = "//a[contains(text(),'Details/Dates')]")
	WebElement tab_DetailsDatesTab;

	public HRP_CasePage(WebDriver driver) {
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
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickOnClientName(String clientName)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 16/08/2021
		 * Added sync
		 */
		
		Utils.clickDynamicElement(driver, "//div[contains(text(), '" + clientName +"')]", "xpath", "Client's Name");
		Utils.waitForAjax(driver);
	}
	
	public void clickDetailsDatesTab()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 Feb 2019
		 */ 
		Utils.clickElement(driver, tab_DetailsDatesTab, "details/dates tab");
	}
	
	public void verifyCustomDetails(String field, String data) {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 Feb 2019
		 */ 
		Utils.verifyIfDataPresent(driver, "//div[@id='CustomDetails']//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
	}
	
	public void verifyFilingDetails(String filedOnDate,String applicationOrReceiptNo, String applicationOrReceiptNoticeDate)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 20 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Filed On Date')]/following-sibling::td[contains(text(),'"+filedOnDate+"')]", "xpath", filedOnDate, "Filed On Date");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Application / Receipt #')]/following-sibling::td/a[contains(text(),'"+applicationOrReceiptNo+"')]", "xpath", applicationOrReceiptNo, "Application / Receipt #");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Application / Receipt Notice Date')]/following-sibling::td[contains(text(),'"+applicationOrReceiptNoticeDate+"')]", "xpath", applicationOrReceiptNoticeDate, "Application / Receipt Notice Date");
	}
	
	
	public void verifyReceiptDetails(String filedDate, String receiptNumber, String receiptDate, String validFrom, String validTo, String status)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		String validity = validFrom + " - " + validTo;  
		Utils.verifyIfDataPresent(driver, "//div[@id='AddtionalApplication/ReceiptDetails']//td[contains(text(),'"+filedDate+"')]/following-sibling::td/a[contains(text(),'"+receiptNumber+"')]/../following-sibling::td[contains(text(),'"+receiptDate+"')]/following-sibling::td[contains(text(),'"+validity+"')]/following-sibling::td[contains(text(),'"+status+"')]", "xpath", "receipt details", "additional receipt details");
	}
	
	public void verifyLCADetails(String lcaNumber, String jobTitle, String validThru,String LCAeffectiveOn, String LCAeffectiveTill)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Number')]/../td[2][contains(text(),'"+ lcaNumber +"')]", "xpath", lcaNumber, "lca number");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Position')]/../td[2][contains(text(),'')]", "xpath", jobTitle, "job title");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'LCA Valid To')]/../td[2][contains(text(),'')]", "xpath", validThru, "valid to");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Effective On')]/../td[2][contains(text(),'')]", "xpath", LCAeffectiveOn, "effective on");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Effective To')]/../td[2][contains(text(),'')]", "xpath", LCAeffectiveTill, "effective to");
	}
	
	
	public void verifySWAandDoLInfo(String swaCaseNumber, String swaFiledDate,String swaPriorityDate, String dolRecievedDate,String dolCaseNumber, String noticeSentDate,String noticeReceivedDate, String backlogCaseNumber ) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
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
	
	
	public void clickEmailsTab()
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 16/08/2021
		 * Added sync
		 */
		
		Utils.clickElement(driver, tab_emails, "Emails tab");
		Utils.waitForAjax(driver);
	}


	public void verifyIfEmailPresent(String sendEmailID, String sendMessage, String sendSubject)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 12/07/2021
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Email");
		
		Utils.verifyIfDataPresent(driver, "//div[@class='modal fade in' and contains(@style,'display: block;')]//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
	}


	public void verifyIfEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 12/07/2021
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
	}


	public void verifyIfEmailNotPresent(String emailSubjectWithoutAccess)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 17/02/2019
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(), '" + emailSubjectWithoutAccess + "')]", waitElement_Pagination, "xpath", emailSubjectWithoutAccess, "Received emails list");
	}
	
	
	public void verifyPrimaryContactFirmContact(String primaryFirmContact)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='primaryFirmContactModalPopup']/following-sibling::div//h2[contains(text(),'"+primaryFirmContact+"')]", "xpath", primaryFirmContact, "primary firm contact");
	}
	
	
	public void verifyProposedJobDetails(String city, String country, String supervisorTitle, String noOfSubOrdinates, String proposedJobTitle, String jobDuties, String jobType, String socCode, String noOfHoursPerWeek, String qualification, String majorFieldOfStudy, String experienceInJobOfferedYears, String experienceInJobOfferedMonths, String experienceInRelatedOccupationYears, String experienceInRelatedOccupationMonths, String relatedOccupation)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		String fullAddress = city + "   " + country ;
		String experienceInJobOffered = experienceInJobOfferedYears + " / " + experienceInJobOfferedMonths;
		String experienceInRelatedOccupation = experienceInRelatedOccupationYears + "/ " + experienceInRelatedOccupationYears;
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Country')]/following-sibling::td[contains(text(),'"+country+"')]", "xpath", country, "country");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Full Work Address')]/following-sibling::td[contains(text(),'"+fullAddress+"')]", "xpath", fullAddress, "full address");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Supervisor Title')]/following-sibling::td[contains(text(),'"+supervisorTitle+"')]", "xpath", supervisorTitle, "supervisor Title");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No of Subordinates')]/following-sibling::td[contains(text(),'"+noOfSubOrdinates+"')]", "xpath", noOfSubOrdinates, "no Of Sub Ordinates");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Job Title (Applicant)')]/following-sibling::td[contains(text(),'"+proposedJobTitle+"')]", "xpath", proposedJobTitle, "job title of applicant");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Job Duties')]/following-sibling::td[contains(text(),'"+jobDuties+"')]", "xpath", jobDuties, "job Duties");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Job Type')]/following-sibling::td[contains(text(),'"+jobType+"')]", "xpath", jobType, "job Type");
		//Utils.verifyIfDataPresent(driver, "//td[contains(text(),'SOC Code')]/following-sibling::td[contains(text(),'')]", "xpath", data, message);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No of Hours Per Week (Basic)')]/following-sibling::td[contains(text(),'"+noOfHoursPerWeek+"')]", "xpath", noOfHoursPerWeek, "no Of Hours  Per Week");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Qualification Degree')]/following-sibling::td[contains(text(),'"+qualification+"')]", "xpath", qualification, "qualification");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Major Field of Study')]/following-sibling::td[contains(text(),'"+majorFieldOfStudy+"')]", "xpath", majorFieldOfStudy, "major Field Of Study");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Experience in Job offered (Years/Months)')]/following-sibling::td[contains(text(),'"+experienceInJobOffered+"')]", "xpath", experienceInJobOffered, "experience In Job Offered");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Experience in Related Occupation (Years/Months)')]/following-sibling::td[contains(text(),'"+experienceInRelatedOccupation+"')]", "xpath", experienceInRelatedOccupation, "experience In Related Occupation");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Related Occupation')]/following-sibling::td[contains(text(),'"+relatedOccupation+"')]", "xpath", relatedOccupation, "related occupation");
	}
	
	
	public void replyToEmail(String emailMessage, String caseContentEmailSubject) throws InterruptedException
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 27/02/2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + caseContentEmailSubject + "')]", "xpath", "Received email");
		
		Utils.clickElement(driver, btn_reply, "Reply");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		
		Utils.enterText(driver, txtbox_Message, emailMessage, "Message Box");
		
		driver.switchTo().defaultContent();
		
		Utils.clickElement(driver, btn_sendEmail, "Send Email");
		
		driver.navigate().refresh();
		
	}
	
	
	public void verifyIfRepliedEmailPresent(String replyMessage, String caseContentEmailSubject)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 05/07/2021
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + caseContentEmailSubject + "')]", "xpath", caseContentEmailSubject, "Subject");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + caseContentEmailSubject + "')]", "xpath", "Received Email");
		
	//	Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + caseContentEmailSubject + "')]", "xpath", caseContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + replyMessage + "')]", "xpath", replyMessage, "Email popup");

		
	}
	
	
}