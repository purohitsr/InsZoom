package com.inszoomapp.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class FnpHomePage extends LoadableComponent<FnpHomePage> 
{
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//input[@id='rdoRegular']")
    WebElement lbl_preocessingType;//Added by Saurav Purohit on 16 Aug, 2021
    
	
	@FindBy(xpath = "(//b[contains(text(),'Name')]//ancestor::table[1]/tbody/tr)[last()]")
    WebElement lastRow_CRNameTable;                      //Added by Saurav Purohit on 16 Aug, 2021
	
	@FindBy(id = "cboCountry")
	WebElement dropdown_country;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "cboTempl")
	WebElement dropdown_crTemplate;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "radBnf")
	WebElement radioBtn_cRForClient;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "btn_Save")
	WebElement btn_saveCRClient;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "btn_ClickHeretoCreateNewCaseRequest")
	WebElement btn_createNewCaseRequest;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(xpath = "//div[@id='UploadedDocs']/table")
	WebElement waitElement_uploadedDocuments;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "search-pending-todos")
	WebElement searchbox_assignedForYou;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(xpath = "//i[@title='Close']")
	WebElement icon_closeToDo;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	//Added By Saurav on 16th March 2020
	
	
		@FindBy ( xpath = "//span[contains(text(),'Case Request')]")
		WebElement tab_CaseRequest;
		
		@FindBy ( xpath = "//h2[contains(text(),'TRAVEL')]")
		WebElement header_Travel;
		
		
		@FindBy ( xpath = "//h2[contains(text(),'Case Details')]")
		WebElement header_CaseDetails;
		
		@FindBy ( xpath = "//h2[contains(text(),'Documents')]")
		WebElement header_Documents;
		
		//
	
	@FindBy (xpath = "(//div[@id='resetuserModal' and @style='display: none;'])[1]")
    WebElement waitElement_userInstruction; 
	
	@FindBy(xpath="//span[contains(text(),'Calendar')]")
    WebElement tab_calendar; 

	@FindBy(xpath= "//h3[contains(text(),'Update Security')]/../preceding-sibling::div[1]/button[contains(text(),'Save')]")
    WebElement btn_saveLoginID;
	
	@FindBy(id = "txtloginId")
    WebElement textBox_loginID;
	
	@FindBy(xpath= "//div[@class='passwordRules']/..//button[@class='btn btn-info'][contains(text(),'Close')]")
    WebElement btn_close;
	
	@FindBy(xpath= "//div[@class='passwordRules']/..//button[@class='btn btn-info'][contains(text(),'Save')]")
    WebElement btn_savePassword;
	
	@FindBy(id = "ChangePassword_ConfirmPassword")
    WebElement textBox_confirmPassword;
	
	@FindBy(id = "txtNewPassword")
    WebElement textBox_newPassword;
	
	@FindBy(id = "txtOldPassword")
    WebElement textBox_oldPassword;
	
	@FindBy(xpath="//a[contains(text(),'Password and Security')]")
    WebElement icon_PasswordAndSecurity;
	
	@FindBy(xpath="//button[@type='submit']")
    WebElement btn_upload;
	
	@FindBy(id="imgUser")
    WebElement img_defaultProfileImage;
	
	@FindBy(xpath="//a[contains(text(),'Closed Cases')]")
    WebElement text_closedCases; 
	
	@FindBy(xpath="//button[@class='btn btn-primary'][contains(text(),'OK')]")
    WebElement btn_OK;
    
    @FindBy(xpath="//i[@class='fa fa-print']")
    WebElement icon_print;
    
    @FindBy(xpath="//a[contains(text(),'Check Status')]")
    WebElement lnk_checkStatus;
    
    @FindBy(xpath="//span[contains(text(),'Shipments & Mails')]")
    WebElement tab_ShipmentsAndMails;
    
    @FindBy(xpath="//h2[contains(text(),'DASHBOARD')]")
    WebElement header_dashboard;
    
    @FindBy(xpath="//h2[contains(text(),'My To-Do List')]")
    WebElement header_ToDo;
    
    @FindBy(xpath="//span[contains(text(),'Assigned For You')]")
    WebElement header_AssignedForYou;
    
    @FindBy(xpath="//h2[contains(text(),'Tasks to Complete')]")
    WebElement header_TasksToComplete;
    
    @FindBy(xpath="//h2[contains(text(),'Important Documents and Dates')]")
    WebElement header_ImportantDocumentsAndDates;
    
    @FindBy(xpath="//h2[contains(text(),'Case')]")
    WebElement header_Case;
    
    @FindBy(xpath="//h2[contains(text(),'Visas')]")
    WebElement header_Visas;
    
    @FindBy(xpath="//i[@class='fa fa-info-circle']")
    WebElement icon_info;
    
    @FindBy(xpath="//i[@class='fa fa-home']")
    WebElement icon_home;
    
    @FindBy(xpath="//button[contains(text(),'OK')]")
    WebElement btn_ok;
    
    @FindBy(xpath="//div[@id='page-wrapper' and @class='container nav-small']")
    WebElement div_collageLeftMenu;
    
    @FindBy(id="make-small-nav")
    WebElement icon_collapse;
    
    @FindBy(xpath="//div[@id='page-wrapper' and @class='container']")
    WebElement div_normalPage;
	
	@FindBy(xpath="//button[contains(text(), 'Accept and Continue')]")
	WebElement btn_acceptAndContinue;
	
	@FindBy(xpath="//button[contains(text(), 'Decline and Exit')]")
	WebElement btn_declineAndExit;
	
	@FindBy(css="a[data-original-title='View News and Guidelines']")
	WebElement tab_NewsGuidelines;
	
	@FindBy(css="a[data-original-title='View Travel Information']")
	WebElement tab_Travel;
	
	@FindBy(xpath="//a[contains(@href, 'Profile')]/i[@class='fa fa-user']")
	WebElement lnk_MyProfile;
	
	@FindBy(css="a[data-original-title='View Documents']")
	WebElement tab_Documents;
	
	@FindBy(css = "a[data-original-title='View Passport']")
	WebElement tab_passport;
	
	@FindBy(css = "a[data-original-title='View Notes']")
	WebElement tab_notes; 
	
	@FindBy(css = "a[data-original-title='View Passport'] span")
	WebElement lnk_Passport;
	
	@FindBy(css = "#header-nav > ul > li.dropdown.profile-dropdown > a")
	WebElement dropDown_Profile;
	
	@FindBy(xpath = "//a[contains(text(),'Signout')]")
	WebElement icon_SignOut;
	
	@FindBy(xpath="//span[contains(text(), 'Messages')]")
	WebElement tab_Messages;
	
	@FindBy ( xpath = "//label[@for='BNFMessages']")
	WebElement tab_messages;
	
	@FindBy ( xpath = "//a[@href='#tab-documents']")              //Added by Nitisha Sinha on 6/02/2020
	WebElement tab_caseDocumentation;
	
	@FindBy ( xpath = "//a[@href='#tab-details']")              //Added by Nitisha Sinha on 6/02/2020
	WebElement tab_caseDetailsOrDates;
	
	@FindBy ( xpath = "//h2[contains(text(),'se Details')]")              //Added by Nitisha Sinha on 10/02/2020
	WebElement waitElement_caseDetails;
	
	@FindBy(xpath = "//h2[contains(text(),'Case')]")	      //Created by Kuchinad Shashank on 10/02/2020
	WebElement waitElement_caseHeader; 
	
	
	public FnpHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, lnk_Passport))) {
			Log.fail("Home Page did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	public void clickLogout(boolean screenshot) 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */ 

		try {
			Log.message("Clicking on Logout Icon");
			Utils.waitForElement(driver, dropDown_Profile);
			dropDown_Profile.click();
			Log.message("Clicked on Client Dropdown");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.message("Unable to click on Client Dropdown. ERROR :\n\n " + e.getMessage());
		}
		
		
		try {
			Log.message("Clicking on Signout Icon");
			Utils.waitForElement(driver, icon_SignOut);
			icon_SignOut.click();
			Log.message("Clicked on Signout Icon");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.message("Unable to click on Signout Icon. ERROR :\n\n " + e.getMessage());
		}

	}
	
	
	public void verifyExpirationAfter6Months(String templateName, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */
		Utils.waitForElement(driver, "//div[contains(text(),'"+ templateName +"')]", globalVariables.elementWaitTime, "xpath");
		WebElement waitElement = driver.findElement(By.xpath("//div[contains(text(),'"+ templateName +"')]"));
		Utils.verifyIfDataNotPresent(driver, "//div[contains(text(),'"+ templateName +"')]/div/div[@class='label label-warning']",waitElement , "xpath", templateName, "expiration after 6 months (No tag present)");
	}
	
	
	
	public void verifyDocumentExpirationInOrLessThan6Months(String templateName,boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Oct 2019
		 */

		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'"+ templateName +"')]/../div/div/span[contains(text(),'Expired')]", "xpath", templateName, "expiration less than 6 months");
	}
	
	
	public void verifyVisaDetails(String visaCountryName, String issueDate, String expireDate, String visaType) throws Exception {
		
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 04 Nov 2019
		 */
		
		//Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+ visaCountryName +"')]", "xpath", visaCountryName, "Verified the Visa Country name");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+visaCountryName+"')]/following-sibling::td[contains(text(),'"+visaType+"')]/../td[contains(text(),'"+issueDate+"')]/../td[contains(text(),'"+expireDate+"')]/../td[contains(text(),'Valid Visa')]", "xpath", "valid", "visa details");
	} 
	
	
	
	
	public void verifyCaseLinkedAndReceiptNumber(String receiptNumber,String receiptSendDate, String receiptValidFromDate, boolean screenshot) throws Exception {
		
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 04 Nov 2019
		 */
		

			((JavascriptExecutor) driver).executeScript("scroll(0,250);");
			
			/* Verify Receipt Number */
			
			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Case')]/../..//a[contains(text(),'"+ receiptNumber +"')]", "xpath", receiptNumber, "receipt number");

			/* Verify Receipt Date */

			SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM dd yyyy");
			java.util.Date dDate = sdfmt1.parse(receiptSendDate);
			String strOutput = sdfmt2.format(dDate);
			
			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Case')]/../..//div[@class='col-xs-3']//div[contains(text(),'"+ strOutput +"')]", "xpath", strOutput, "receipt date");

			/* Verify Receipt Valid from Date */

			java.util.Date dDate1 = sdfmt1.parse(receiptValidFromDate);
			String strOutput1 = sdfmt2.format(dDate1);

			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Case')]/../..//div[@class='col-xs-4']//div[contains(text(),'"+ strOutput1 +"')]", "xpath", strOutput1, "Validity");
	}
	
	
	public void verifyFNPHomePage(String clientName) 
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Nov 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),  '" + clientName + "')]", "xpath", clientName, "FNP Dashboard/Home Page");

	}
	
	
	 public void clickCaseLink(String caseId) throws InterruptedException 
	 {
			/*
			 * Created By : Likitha Krishna M
			 * Created On : 08 Nov 2019
			 */
			Utils.waitForAjax(driver);
			
			Utils.waitForElement(driver, "//div[@id='CaseListDetails']//a[contains(text(),'("+ caseId +")')]", globalVariables.elementWaitTime, "xpath");
			WebElement caseLink = driver.findElement(By.xpath("//div[@id='CaseListDetails']//a[contains(text(),'("+ caseId +")')]"));
			Utils.scrollIntoView(driver, caseLink);
			Utils.clickElement(driver, caseLink, caseId);
			
			Utils.waitForAjax(driver);
			Thread.sleep(3000);
	 }
		
		
	 public void clickNotesTab() 
	 {
		/*
		 * Created By : Likitha Krishna M
		 * Created On : 12 Nov 2019
		 */
		 Utils.clickElement(driver, tab_notes, "notes tab");
	 }
	
	
	 public void clickPassportTab() 
	 {
		/*
		 * Created By : Likitha Krishna M
		 * Created On : 12 Nov 2019
		 */
		 Utils.clickElement(driver, tab_passport, "passport tab");
	 }

	
	 public void clickDocumentsTab() 
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Nov 2019
		 */
		 
		 Utils.clickElement(driver, tab_Documents, "Documents tab");
		 
		 Utils.waitUntillLoaderDisappearsInHRP(driver);
	 }
		
	 
	 public void clickMyProfile()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		 
		 Utils.clickElement(driver, dropDown_Profile, "Client options dropdown");
		 
		 Utils.clickElement(driver, lnk_MyProfile, "'My Profile' icon");
		 
	 }
	 
	 
	 public void clickTravelTab()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 19 Nov 2019
		 */ 
		 
		 Utils.clickElement(driver, tab_Travel, "'Travel' tab"); 
	 }
	 
	 
	 public void verifyTravelDetails(String client_DeparturePlaceName)
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 19 Nov 2019
		 */ 
		 
		 Utils.verifyIfDataPresent(driver, "//th[@data-field='DeparturePlace']/../../following-sibling::tbody//td[contains(text(), '" + client_DeparturePlaceName + "')]", "xpath", client_DeparturePlaceName, "Departure City");
	 }
	 
	 
	 public void clickMessagesTab()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 02 Dec 2019
		 */ 
		 
		 Utils.clickElement(driver, tab_Messages, "'Messages' tab"); 
	 }
	 
	 
	 public void clickNewsGuidelinesTab()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 05 Dec 2019
		 */ 
		 
		 Utils.clickElement(driver, tab_NewsGuidelines, "'News/Guidelines' tab"); 
	 }
	 
	 
	 public void verifyIfEConsentAppears(String eConsentTitle, String eConsentDescription)
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On :13 Dec 2019
		  */
		 
		 Utils.verifyIfDataPresent(driver, "//h1[contains(text(), '" + eConsentTitle + "')]", "xpath", eConsentTitle, "E-Consent title");
		 
		 Utils.verifyIfDataPresent(driver, "//div[contains(text()[2], '" + eConsentDescription + "')]", "xpath", eConsentDescription, "E-Consent Description");
	 }
	 
	 
	 public void declineEConsentAndCheck()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 13 Dec 2019
		  */
		 
		 Utils.clickElement(driver, btn_declineAndExit, "'Decline and Exit'");

		 Utils.verifyIfDataPresent(driver, "login", "id", "Login Button", "Login Page");
	 }
	
	
	 public void acceptEConsentAndCheck(String clientName)
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 14 Dec 2019
		  */
		 
		 Utils.clickElement(driver, btn_acceptAndContinue, "'Accept and Continue'");
		 
		 verifyFNPHomePage(clientName);
	 }
	 
	 
	 public void verifyCollapseFunctionality()
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfStaticElementDisplayed(driver, div_normalPage, "left menu is normal", "FNP page");
			Utils.clickElement(driver, icon_collapse, "collapse button");
			Utils.verifyIfStaticElementDisplayed(driver, div_collageLeftMenu, "left menu is collapsed", "FNP page");
		}

		
		
		
		public void verifyCorpNameHeader(String corpName)
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfDataPresent(driver, "//h5[contains(text(),'"+ corpName +"')]", "xpath", corpName, "header on FNP page");
		}
		
		
		public void verifyClientNameHeader(String clientName)
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfDataPresent(driver, "//span[@class='hidden-xs' and contains(text(),'"+ clientName +"')]", "xpath", clientName, "header on FNP page");
		}
		
		
		public void verifyClientNameInLeftMenu(String clientName)
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfDataPresent(driver, "//a[@class='dropdown-toggle' and contains(text(),'"+clientName+"')]", "xpath", clientName, "in left menu on FNP page");
		}
		
		
		public void verifyUserInstruction(String userInstruction)
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfDataPresent(driver, "//div[contains(text(),'"+userInstruction+"')]", "xpath", userInstruction, "on FNP page");
			Utils.clickElement(driver, btn_ok, "ok button");
		}
		
		public void acceptUserInstruction()
		{
			/*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			if(Utils.isElementPresent(driver, "//button[contains(text(),'OK')]", "xpath"))
				Utils.clickElement(driver, btn_ok, "ok button in user instructions");
			//Utils.waitForElement(driver, waitElement_userInstruction);
			Utils.waitForElementPresent(driver, "//div[@class='modal fade' and @id='resetuserModal']", "xpath");
			//Utils.waitForElementPresent(driver, "(//div[@id='resetuserModal' and @style='display: none;'])[1]", "xpath");
		}
		
		public void verifyHomeButton()
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */	
			//if(Utils.isElementPresent(driver, "//button[contains(text(),'OK')]", "xpath"))
			//Utils.clickElement(driver, btn_ok, "ok button");
			
			Utils.clickElement(driver, tab_notes, "notes tabs to switch from initial page");
			
			Utils.clickElement(driver, icon_home, "home icon");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_dashboard, "Dashboard header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_ToDo, "My To Do header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_AssignedForYou, "Assigned For You header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_TasksToComplete, "Tasks to Complete header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_ImportantDocumentsAndDates, "Important Documents and Dates header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_Case, "Case header", "on FNP page");
			
			Utils.verifyIfStaticElementDisplayed(driver, header_Visas, "Visas header", "on FNP page");
			
		}
		
		public void clickShipmentsAndMailsTab()
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			//Utils.waitForElement(driver, "//div[@id='ToDoListDetails']//tr", globalVariables.elementWaitTime, "xpath");
			Utils.clickElement(driver, tab_ShipmentsAndMails, "shipment and mails tab");
		}
		
		public void verifyShipmentsAndMails(String shipmentDate, String shipmentMethod, String trackingNumber, String caseId) throws Exception
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+shipmentDate+"')]", "xpath", shipmentDate, "shipment date");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+shipmentMethod+"')]", "xpath", shipmentMethod, "shipment method");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+trackingNumber+"')]", "xpath", trackingNumber, "tracking number");
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+caseId+"')]", "xpath", caseId, "case id");
//			Utils.clickElement(driver, lnk_checkStatus, "check status link");
//			
//			Utils.waitForAllWindowsToLoad(2, driver);
//			Utils.switchWindows(driver, "INSZoom.com : Check Shipment Status", "title", "false");
//			
//			driver.close();
		}
		
		
		public void verifyRelative(String relativeName)
		{
			 /*
			  * Created By : Likitha Krishna
			  * Created On :13 Dec 2019
			  */
			
			Utils.verifyIfDataPresent(driver, "//div[@id='myImgDiv']/../span[contains(text(),'"+relativeName+"')]", "xpath", relativeName, "relative name in the left menu");
		}
		
		public void clickOnRelative(String relativeName)
		{
			 /*
			  * Edited By : Souvik Ganguly
			  * Edited On :28 July 2021
			  * Added function to wait for the loader to disappear
			  */
			Utils.clickDynamicElement(driver, "//div[@id='myImgDiv']/../span[contains(text(),'" + relativeName + "')]", "xpath", "relative name");
		
			Utils.waitForElement(driver, "//a[contains(text(), '" + relativeName + "')]", globalVariables.elementWaitTime, "xpath");
			
			Utils.waitForAjax(driver);
			
			Utils.waitUntillLoaderDisappearsInHRP(driver);
		}
		
		
		
		public void verifyVisaDetailsForRelative(String countryName, String visaType, String validFrom, String validTo )
        {
             /*
              Edited By : Souvik Ganguly
			  * Edited On :28 July 2021
			  * Added function to scroll to the element
              */
			Utils.scrollToDynamicElement(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+countryName+"')]", "xpath", "country name in visa");
            Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+countryName+"')]", "xpath", countryName, "country name in visa");
            Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+visaType+"')]", "xpath", visaType, "visa type in visa");
            Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+validFrom+"')]", "xpath", validFrom, "valid from in visa");
            Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'"+validTo+"')]", "xpath", validTo, "valid to in visa");
            Utils.verifyIfDataPresent(driver, "//div[@class='k-widget k-grid k-grid-display-block k-reorderable']/table/tbody/tr/td[contains(text(),'Valid Visa')]", "xpath", "Valid Visa", "valid visa");
        }
		
		
		public void verifyPrintUserInstruction(String userInstruction) throws Exception
		{
			/*
			 * Created By : Likitha Krishna
			 * Created On :19 Dec 2019
			 */
			Utils.clickElement(driver, icon_info, "info icon");
			Utils.verifyIfDataPresent(driver, "//div[contains(text(),'"+userInstruction+"')]", "xpath", userInstruction, "user instruction");
			Utils.clickElement(driver, icon_print, "print icon");
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "Print Guidelines", "title", "false");
			
			Utils.verifyIfDataPresent(driver, "//div[contains(text(),'"+userInstruction+"')]", "xpath", userInstruction, "user instruction");
		
			driver.close();
			
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
			
			Utils.clickElement(driver, btn_OK, "OK button");
		}
		
		public void clickCalendarTab()
		 {
			/*
			 * Created By : Likitha Krishna
			 * Created On : 06 Jan 2019
			 */ 
			 
			 Utils.clickElement(driver, tab_calendar, "Calendar tab"); 
		 } 
		
	
	public void verifyVisaDetailsInImportantDocumentsAndDate(String visaType, String validFrom, String validTo)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Jan 2019
		 */
		String visaTitile = "Visa (" + visaType + ")" ;
		String validity1 = "From " + validFrom;
		String validity2 = "To " + validTo;
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + visaTitile + "')]/following-sibling::div[contains(text(),'" + validity1 + "')]", "xpath", "Visa details", "in Important Documents And Date");
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + visaTitile + "')]/following-sibling::div[contains(text(),'" + validity2 + "')]", "xpath", "Visa details", "in Important Documents And Date");
	}
	
	
	public void verifyCaseStatus(String caseId, String status)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Jan 2019
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + caseId + "')]/following-sibling::span[contains(text(),'" + status + "')]", "xpath", status, "case status");
	}
	
	
	public void verifyCaseStatusClosed(String caseId)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Jan 2019
		 */
		Utils.clickElement(driver, text_closedCases, "closed cases");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + caseId + "')]/following-sibling::span[contains(text(),'Closed')]", "xpath", caseId, "close tag");
	} 
 

	public void verifyUploadProfileImage(String filePath) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
		
		Utils.clickElement(driver, img_defaultProfileImage, "default profile image icon");
		
		List<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
		
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

		Utils.waitForElement(driver, "files", globalVariables.elementWaitTime, "id");
		WebElement input = driver.findElement(By.id("files"));
		
	    ((RemoteWebElement) input).setFileDetector(detector);
	    
		input.sendKeys(file.getAbsolutePath());
		
//		input.sendKeys(path.getAbsoluteFile().toString()); 
        
        Utils.clickElement(driver, btn_upload, "upload button");
        
        Utils.waitForAllWindowsToLoad(1, driver);
        Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
        
        Utils.verifyIfDataPresent(driver, "//a[@onclick='showUploadImage()']/img", "xpath", "image", "profile icon");
	
        driver.navigate().refresh();
	} 
 
		
	public void verifyExpirationDocuments_Expired(String templateName)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + templateName + "')]/div/span[@class='label label-danger'][contains(text(),'Expired')]", "xpath", templateName, "Expired");
	}
	
	public void verifyExpirationDocuments_ExpiringInLessThan6Months(String templateName)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'"+templateName+"')]/div/div[@class='label label-warning'][contains(text(),'Expiring in 6 months')]", "xpath", templateName, "Expiring in less than 6 months");
	}
		
	public void verifyExpirationDocuments_ExpiredInMoreThan6Months(String templateName)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
		Utils.waitForElement(driver, "//div[@id='ImmigrationListDetails']//div[contains(text(),'"+templateName+"')]", globalVariables.elementWaitTime, "xpath");
		WebElement waitElement = driver.findElement(By.xpath("//div[@id='ImmigrationListDetails']//div[contains(text(),'"+templateName+"')]"));
		Utils.verifyIfDataNotPresent(driver, "//div[contains(text(),'" + templateName + "')]/div/span[@class='label label-danger'][contains(text(),'Expired')]",waitElement , "xpath", templateName, "expired label");
		Utils.verifyIfDataNotPresent(driver, "//div[contains(text(),'" + templateName + "')]/div/div[@class='label label-warning'][contains(text(),'Expiring in 6 months')]",waitElement, "xpath", templateName, "expiring in 6 months label");
	} 
 
		
	public void changePassword(String oldPassword, String newPassword, String workbookName, String sheetName) throws Exception  

	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
		
	 			
	   Utils.clickElement(driver, dropDown_Profile, "profile dropdown");
	   Utils.clickElement(driver, icon_PasswordAndSecurity, "Password and Security");
	   
	   List<String> tab = new ArrayList<>(driver.getWindowHandles());
	   driver.switchTo().window(tab.get(1));
		
	   Utils.enterText(driver, textBox_oldPassword, oldPassword, "Old password");
	   Utils.enterText(driver, textBox_newPassword, newPassword, "New password");
	   Utils.enterText(driver, textBox_confirmPassword, newPassword, "Confirm password");
	   
	   Utils.clickElement(driver, btn_savePassword, "save button");
	   
	   String directory = System.getProperty("user.dir");
	   ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookName);
		
	   writeExcel.setCellData("FNPPassword", sheetName, "Value", newPassword);
	   
	   Utils.waitForElement(driver, "//font[contains(text(),'Password updated successfully.')]", globalVariables.elementWaitTime, "xpath");
	   Utils.clickElement(driver, btn_close, "close");
	   
	   Utils.waitForAllWindowsToLoad(1, driver);
	   Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
	} 


	public void changeLoginId(String newLoginID, String workbookName, String sheetName) throws Exception  
	
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 16 Jan 2019
		 */
			
	   Utils.clickElement(driver, dropDown_Profile, "profile dropdown");
	   Utils.clickElement(driver, icon_PasswordAndSecurity, "Password and Security");
	   
	   List<String> tab = new ArrayList<>(driver.getWindowHandles());
	   driver.switchTo().window(tab.get(1));
		
	   Utils.enterText(driver, textBox_loginID, newLoginID, "new login id");
	   
	   Utils.clickElement(driver, btn_saveLoginID, "save button");
	   
	   String directory = System.getProperty("user.dir");
	   ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookName);
		
	   writeExcel.setCellData("FNPLoginIDCreated", sheetName, "Value", newLoginID);
	   
	   Utils.waitForElement(driver, "//div[contains(text(),'Login id is updated successfully')]", globalVariables.elementWaitTime, "xpath");
	   Utils.clickElement(driver, btn_close, "close");
	   
	   Utils.waitForAllWindowsToLoad(1, driver);
	   Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
	} 


	public void verifyPassportOnDashboard(String validFrom, String validTo)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 20 Jan 2019
		 */
		String validity1 = "From " + validFrom;
		String validity2 = "To " + validTo;
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'Passport')]/following-sibling::div[contains(text(),'"+validity1+"')]", "xpath", "passport details", "in Dashboard page");
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'Passport')]/following-sibling::div[contains(text(),'"+validity2+"')]", "xpath", "passport details", "in Dashboard page");

	}
	
	
	public void verifyImportantDateAndDoc(boolean value)
    {
    	// Created by Kuchinad Shashank
       // Created on 03/02/20
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Important Documents and Dates')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Important Documents and Dates')]", "xpath", "Important Date and Docs", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Important Documents and Dates')]", waitElement_caseHeader, "xpath", "Important Date and Docs", "FNP Home Page");
    	}
    	
    	
    } 
	
	
	public void verifyVisas(boolean value)
    {
    	// Created by Kuchinad Shashank
        // Created on 03/02/20

    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Visas')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Visas')]", "xpath", "Visas", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Visas')]", waitElement_caseHeader,"xpath", "Visas", "FNP Home Page");
    	}
    	
    }
	
	
	public void verifyCaseRequest(boolean value)
    {
    	// Created by Kuchinad Shashank
       // Created on 03/02/20
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Case Request')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Case Request')]", "xpath", "Case request", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Case Request')]", waitElement_caseHeader,"xpath", "Case Request", "FNP Home Page");
    	}
    	
    }
	
	
	public void verifyTravel(boolean value)
    {
    	// Created by Kuchinad Shashank
       // Created on 03/02/20
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Travel')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Travel')]", "xpath", "Travel", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Travel')]", waitElement_caseHeader,"xpath", "Travel", "FNP Home Page");
    	}
    	
    }
	
	
	public void verifyPassport(boolean value)
    {
    	// Created by Nitisha Sinha
       // Created on 03/02/20
		
		/*
		 * Modified By : Kuchinad Shashank
		 * Modified On : 06 Feb 2020
		 * Modified : Utils funtion (verify if data present) is added instead of if-else
		 * 			xpath and WebELement added as per testcase
		 */
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Passport')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Passport')]", "xpath", "Passport", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Passport')]", waitElement_caseHeader,"xpath", "Passport", "FNP Home Page");
    	}
    	
    }
	
	
	public void verifyClientNotes(boolean value)
    {
    	// Created by Nitisha Sinha
       // Created on 03/02/20
		
		/*
		 * Modified By : Kuchinad Shashank
		 * Modified On : 06 Feb 2020
		 * Modified : Utils funtion (verify if data present) is added instead of if-else
		 * 			  xpath and WebElement added as per testcase
		 */
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Notes')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Notes')]", "xpath", "Notes", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Notes')]", waitElement_caseHeader,"xpath", "Notes", "FNP Home Page");
    	}
    	
    }
	
	
	public void verifyDocuments(boolean value)
    {
    	// Created by Nitisha Sinha
       // Created on 03/02/20
		
		/*
		 * Modified By : Kuchinad Shashank
		 * Modified On : 06 Feb 2020
		 * Modified : Utils funtion (verify if data present) is added instead of if-else
		 * 				xpath and WebElement added as per testcase
		 */
    	
    	if(value)
    	{
    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Documents')]", "xpath");
    		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Documents')]", "xpath", "Documents", "FNP Home Page");
    	}
    	
    	else
    	{
    		
    		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Documents')]", waitElement_caseHeader,"xpath", "Documents", "FNP Home Page");
    	}
    	
    }
	

	 public void verifyEventsAndMeetings(boolean value)
	    {
	    	// Created by Nitisha Sinha
	       // Created on 03/02/20
	    	
	    	if(value)
			{
	    		Utils.waitForElementPresent(driver, "//div[@id='AppointmentListDetails']//a/i[@class='fa fa-calendar']", "xpath");
				Utils.verifyIfDataPresent(driver, "//div[@id='AppointmentListDetails']//a/i[@class='fa fa-calendar']", "xpath", "Events And Meetings", "FNP Home Page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//div[@id='AppointmentListDetails']//a/i[@class='fa fa-calendar']", waitElement_caseHeader,"xpath", "Events And Meetings", "FNP Home Page");
			}
	    	
		}
		 
	 
	  public void verifyMessages(boolean value)
	    {
	    	// Created by Nitisha Sinha
	       // Created on 03/02/20
	    		
			if(value)
			{
	    		Utils.waitForElementPresent(driver, "//a[@href='/Portal/FNP/Message/Messages']", "xpath");
				Utils.verifyIfDataPresent(driver, "//a[@href='/Portal/FNP/Message/Messages']", "xpath", "Messages", "FNP Home Page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//a[@href='/Portal/FNP/Message/Messages']", waitElement_caseHeader, "xpath", "Messages", "FNP Home Page");
			}
	    	
	    }
	    
	    
	    public void verifyShipmentAndMail(boolean value)
	    {
	    	// Created by Nitisha Sinha
	       // Created on 05/02/20	
	    	if(value)
			{
	    		Utils.waitForElementPresent(driver, "//h2[contains(text(),'Shipments & Mails')]", "xpath");
				Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Shipments & Mails')]", "xpath", "Shipment and Mail", "FNP Home Page");
			}
			
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Shipments & Mails')]", waitElement_caseHeader, "xpath", "Shipment and Mail", "FNP Home Page");
			}
	   
	    }
	    
	    
	    public void verifyNewstab(boolean value)
	    {
	    	/*
	    	 * Created by Kuchinad Shashank
	    	 * Created on 11 feb 2020
	    	 */
	    	if(value)
	    	{
	    		Utils.waitForElementPresent(driver, "a[data-original-title='View News and Guidelines']", "xpath");
	    		Utils.verifyIfDataPresent(driver, "a[data-original-title='View News and Guidelines']", "xpath", "Visas", "FNP Home Page");
	    	}
	    	
	    	else
	    	{
	    		Utils.verifyIfDataNotPresent(driver, "a[data-original-title='View News and Guidelines']", waitElement_caseHeader, "xpath", "News And Guidelines", "FNP Home Page");
	    	}
	    }


    
//    public void verifyCaseSteps(boolean value)
//    {
//    	// Created by Nitisha Sinha
//       // Created on 05/02/20
//    	
//        if(value)
//        {
//    
//              if(!Utils.isElementPresent(driver, "//div[@id='tab-process']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Case Step tab is present under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Case Step tab is not present under case in FNP Login");
//              }
//        }
//        
//        else
//        {
//        
//              if(Utils.isElementPresent(driver, "//div[@id='tab-process']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Case Step tab is not present under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Case Step tab is present under case in FNP Login");
//              }
//              
//         }
//    }
//
//    
//    public void verifyDocumentation(boolean value)
//    {
//    	// Created by Nitisha Sinha
//        // Created on 06/02/20
//    	
//    	Utils.clickElement(driver, tab_caseDocumentation, "Documentation tab");
//    	
//    	if(value)
//        {
//    
//              if(!Utils.isElementPresent(driver, "//div[@id='tab-documents']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Documentation tab is present under under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Documentation tab is not present under case in FNP Login");
//              }
//        }
//        
//        else
//        {
//        
//              if(Utils.isElementPresent(driver, "//div[@id='tab-documents']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Documentation tab is not present under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Documentation tab is present under case in FNP Login");
//              }      
//       }
//   }
//    
//    
//    public void verifyDetailsOrDates(boolean value) throws InterruptedException
//    {
//    	// Created by Nitisha Sinha
//       // Created on 06/02/20
//    	
//    	Utils.clickElement(driver, tab_caseDetailsOrDates, "Case Details or dates tab");
//    	if(value)
//        {
//    
//              if(!Utils.isElementPresent(driver, "//div[@id='tab-details']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Details/Dates tab is present under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Details/Dates tab is not present under case in FNP Login");
//              }
//        }
//        
//        else
//        {
//        
//              if(Utils.isElementPresent(driver, "//div[@id='tab-documents']/div[@class='norecords-text']", "xpath"))
//              {
//                    Log.pass("Verified. The Details/Dates tab is not present under case in FNP Login");
//              }
//              
//              else
//              {
//                    Log.fail("Failed to Verify. The Details/Dates tab is present under case in FNP Login");
//              }      
//       }
//    }
//    
//    
//    public void verifyFilingDetails(boolean value) throws InterruptedException
//    {
//    	// Created by Nitisha Sinha
//       // Created on 06/02/20
//    	
//    	Utils.clickElement(driver, tab_caseDetailsOrDates, "Case Details or dates tab");
//    	
//    	if(value)
//		{
//			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Filing Details')]", "xpath", "Filing Details", "FNP Home Page");
//		}
//		
//		else
//		{
//			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Filing Details')]", waitElement_caseDetails,"xpath", "Filing Details", "FNP Home Page");
//		}
//
//    }
//    
    
//    public void verifyDecisionDetails(boolean value) throws InterruptedException
//    {
//    	// Created by Nitisha Sinha
//       // Created on 06/02/20
//    	
//    	Utils.clickElement(driver, tab_caseDetailsOrDates, "Case Details or dates tab");
//    	
//    	if(value)
//		{
//			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Decision Details')]", "xpath", "Decision Details", "FNP Home Page");
//		}
//		
//		else
//		{
//			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Decision Details')]", waitElement_caseDetails,"xpath", "Decision Details", "FNP Home Page");
//		}
//    }
//    
//    
//    public void verifyAdditionalapplictionOrReceiptDetails(boolean value) throws InterruptedException
//    {
//    	// Created by Nitisha Sinha
//       // Created on 06/02/20
//    	
//    	Utils.clickElement(driver, tab_caseDetailsOrDates, "Case Details or dates tab");
//    	
//    	if(value)
//		{
//			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", "xpath", "Additional Application/ Receipt Details", "FNP Home Page");
//		}
//		
//		else
//		{
//			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", waitElement_caseDetails,"xpath", "Additional Application/ Receipt Details", "FNP Home Page");
//		}
//
//    }
//    
    
//    public void verifyVisaPriorityDateInfo(boolean value) throws InterruptedException
//    {
//    	/* 
//    	    Created by Nitisha Sinha
//        	Created on 06/02/20
//        */
//    	
//    	Utils.clickElement(driver, tab_caseDetailsOrDates, "Case Details or dates tab");
//	
//		if(value)
//		{
//			if(Utils.waitForElement(driver, "//h2[contains(text(),'Visa Priority Date Info')]", globalVariables.elementWaitTime, "xpath"))
//    			Log.pass("Visa Priority Date Info present under Documnets in FNP home page");
//    		else
//    			Log.pass("Visa Priority Date Info not present in FNP home page");
//		}
//		
//		else
//		{
//			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Visa Priority Date Info')]", waitElement_caseDetails,"xpath", "Documents", "FNP Home Page");
//		}
//    } 

    
    public void clickHomeIcon() 
	 {
		/*
		 * Created By : Sauravp
		 * Created On : 16 Nov 2019
		 */
   	Utils.clickElement(driver, icon_home, "home icon");
	 }
   
   
   
   
   public void verifyHomePage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
	    	Utils.verifyIfStaticElementDisplayed(driver, header_dashboard, "Dashboard header", "on FNP page");
	    }catch(Exception e){
		 Log.failsoft("Verification of Home page failed", driver);
	    }

}
   
   public void verifyCaseDetailsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
	    	Utils.verifyIfStaticElementDisplayed(driver, header_CaseDetails, "Case details header", "on FNP page");
	    }catch(Exception e){
		 Log.failsoft("Verification of Case Details page failed", driver);
	    }

}
   
   public void verifydocumentsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
	    	Utils.verifyIfStaticElementDisplayed(driver, header_Documents, "Documents Page", "on FNP page");
	    }catch(Exception e){
		 Log.failsoft("Verification of Docuements Page failed", driver);
	    }

}
   
   
   public void verifyTravelPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
	    	Utils.verifyIfStaticElementDisplayed(driver, header_Travel, "Travel Page", "on FNP page");
	    }catch(Exception e){
		 Log.failsoft("Verification of Travel Page failed", driver);
	    }

}
   
   
   public void clickCaseRequestTab() 
	 {
		/*
		 * Created By : Sauravp
		 * Created On : 16 Nov 2019
		 */
   	Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab");
	 }
   
	public void verifyCaseRequestPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
	     Utils.waitForAllWindowsToLoad(2, driver);
	     
	     Utils.switchWindows(driver, "INSZoom.com - Case Request", "title", "false");
	     
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Request");
		 
		 driver.close();

		 Utils.waitForAllWindowsToLoad(1, driver);

		 Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
		 
	    }catch(Exception e){
		 Log.failsoft("Verification of Case Request page failed", driver);
	    }

	}
	
	
	public void searchToDoAssignedForYou(String toDoTitle) throws InterruptedException
	{
		/*
		  * Created By : Nitisha Sinha
		  * Created On : 6 Oct 2020
		  */
		
		Utils.enterText(driver, searchbox_assignedForYou, toDoTitle, "Assigned for you searchbox in My");
		
//		Utils.waitForElementPresent(driver, "//div[@id='ToDoList']//div[@class='k-loading-image']", "xpath");
//		Utils.waitForElementNotVisible(driver, "//div[@id='ToDoList']//div[@class='k-loading-image']", "xpath");
		Thread.sleep(10000);
		Utils.clickDynamicElement(driver, "//label[contains(text(),'" + toDoTitle + "')]", "xpath", toDoTitle);
		
		Utils.waitForElementPresent(driver, "//span[contains(text(),'" + toDoTitle + "')]", "xpath");
	}
	
	
	public void verifyAssignQuestionnaireToClient(String clientName, String questionnaireName) throws InterruptedException
	{
		/*
		  * Created By : Nitisha Sinha
		  * Created On : 6 Oct 2020
		  */
			
//		Utils.waitForElement(driver, "//div[@id='UploadedDocs']//div[@class='k-loading-mask']", globalVariables.elementWaitTime, "xpath");
//		Utils.waitForElementPresent(driver, "//div[@id='UploadedDocs']//div[@class='k-loading-mask']", "xpath");
//		Utils.waitForElementNotVisible(driver, "//div[@id='UploadedDocs']//div[@class='k-loading-mask']", "xpath");
//		
		Thread.sleep(10000);
		Utils.waitForElement(driver, waitElement_uploadedDocuments);
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'Questionnaires for " + clientName + "')]", "xpath", "expand questionnaire icon");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Questionnaires for " + clientName + "')]//ancestor::table//a[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "questionnaire for " + clientName);
		
		Utils.waitForElementClickable(driver, icon_closeToDo);
		Utils.clickUsingAction(driver, icon_closeToDo);
			
	}
	
	
	public void verifyAssignCaseQuestionnaire(String clientName, String questionnaireName) throws InterruptedException
	{
		/*
		  * Created By : Nitisha Sinha
		  * Created On : 6 Oct 2020
		  */
		
		Thread.sleep(1000);
		Utils.clickDynamicElement(driver, "//span[contains(text(),'Questionnaires for " + clientName + "')]", "xpath", "expand questionnaire icon");
		Thread.sleep(1000);
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Questionnaires for " + clientName + "')]//ancestor::table//a[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "questionnaire for " + clientName);
		
		Utils.waitForElementClickable(driver, icon_closeToDo);
		Utils.clickUsingAction(driver, icon_closeToDo);
			
	}
	
	
	public void verifyCRQuestionnaire(String caseRequestName, String clientFirstName) throws Exception
	 {
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 1st Oct 2020
		 * Modified By : Saurav
		 * Modified on : 23/08/2021
		 * dded a Java script click followed by a wait - reason Though USA is selected it was not populating CR list 
         * Hence Java script click will reload the list and select the right CR .
         * Added a wait condition for loader . Added a Thread.sleep bacause explicite wait also did not work . Will work on this later and find
         * a comprehansive solution
		 */ 
		 
		Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab from Left Menu"); 
		
		Utils.waitForAllWindowsToLoad(2, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Case Request", "title", "false");
	     
		Utils.clickElement(driver, btn_createNewCaseRequest, "Create New case Request Button");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Initiate A Case Request", "title", "false");
		
        Utils.waitForElement(driver, dropdown_country);
        
        Select select = new Select(dropdown_country);
        
        Utils.waitForOptionsAvaiable(driver, select);
		
		Utils.selectOptionFromDropDownByVal(driver, "USA", dropdown_country);
        
        Utils.waitForElement(driver, lbl_preocessingType);
        
        Utils.waitForElement(driver, radioBtn_cRForClient);
        
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", radioBtn_cRForClient);
        
        Thread.sleep(10000);
        
       /* WebDriverWait wait1 = new WebDriverWait(driver, 30);
        
        wait1.until(ExpectedConditions.invisibilityOf(dropdown_crTemplate));
        
        wait1.until(ExpectedConditions.visibilityOf(dropdown_crTemplate));*/
        
     /*   WebDriverWait wait2 = new WebDriverWait(driver, 30);
        
        wait2.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//img[contains(@src,'CorpImages/icons/Pross')]"))));*/
        
        Utils.waitForElement(driver, dropdown_crTemplate);
		
		Select dropdownCaseRequestTemplate = new Select(dropdown_crTemplate);
        
        Utils.waitForOptionsAvaiable(driver, dropdownCaseRequestTemplate);
		
		Utils.selectOptionFromDropDown(driver, caseRequestName, dropdown_crTemplate);
	
		Utils.clickElement(driver, btn_saveCRClient, "save button for initiating case request");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Case Request Questionnaire List", "title", "false");
		
		Utils.waitForElement(driver, lastRow_CRNameTable);
		
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//b[contains(text(),'Name')]//ancestor::table[1]//a"), 1));
		
		List<WebElement> temp = driver.findElements(By.xpath("//b[contains(text(),'Name')]//ancestor::table[1]//a"));
		 
		HashSet<String> questionnaire=new HashSet();
		
		for(int j = 0; j < temp.size(); j += 1)
	   {
			questionnaire.add(temp.get(j).getText());
	 	  
	   }
		System.out.println(questionnaire);
		
		Log.message("Questionnaire List Under CR Template are "+globalVariables.questionnaireUnderCRTemplate);
		
		Log.message("Questionnaire List In FN Portal "+questionnaire);
		
		if(globalVariables.questionnaireUnderCRTemplate.equals(questionnaire))
		    
			Log.pass("CR Questionnaire are successsfully populated in FNP level");
		else
		    
			Log.fail("CR Questionnaire did not get successsfully populated in FNP level");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");

		}
	

	public void verifyIfDocumentsAvailable(String docName) throws Exception
	 {
		boolean present=Utils.isElementPresent(driver, "//*[contains(text(),'"+docName+"')]", "xpath");
		if(present)
			Log.pass("Document has been renamed in FNP as "+docName);
		else
			Log.fail("Document has not been renamed in FNP "+docName);
	 }

}