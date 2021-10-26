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

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_CorporationListPage extends LoadableComponent<CM_CorporationListPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id="LM45")        //Added by Souvik Ganguly on 30th August 2021
	WebElement tab_CorpUsers;
	        
	@FindBy(xpath = "(//table[@summary='Questionnaire For Initiator']//tbody/tr)[last()]") //Added by Saurav Purohit on 16th Aug,2021
	WebElement lastRow_QuestionnaireForInitiatorTable;   
	
	@FindBy(id = "btn_ChoosecolumnsforCorporationList") //Added by Souvik Ganguly on 25th June,2021
	WebElement btn_chooseColumns;	
	
	@FindBy(id = "UserCntrl_btnRemove")
	WebElement btn_removeCaseRequestTemplate;		//Added by Yatharth Pandya on 3rd Oct,2020
	
	@FindBy(id = "UserCntrl_list2")
	WebElement list2_caseRequest;			//Added by Yatharth on 3st Oct,2020
	
	@FindBy(id = "btn_Savetemplatesandclosethewindow")
	WebElement btn_Savetemplates;						//Added by Yatharth Pandya on 3rd Oct,2020
	
	@FindBy(id = "UserCntrl_btnAdd")
	WebElement btn_addCaseRequestTemplate;				//Added by Yatharth Pandya on 3rd Oct,2020
	
	@FindBy(id = "UserCntrl_list1")
	WebElement list1_caseRequest;			//Added by Yatharth on 1st Oct,2020
	
	@FindBy(id = "cboOrgCountry")
	WebElement dropdown_chooseCountry;					//Added by Yatharth on 1st Oct,2020
	
	@FindBy(id = "btn_AttachRemoveTemplates")
	WebElement btn_AttachRemoveCaseRequestTemplates; 	//Added by Yatharth on 1st Oct,2020
	
	@FindBy(xpath = "//td[@id='lftmnuStart']//span[contains(text(),'Questionnaire')]")
	WebElement tab_questionnaireUnderCaseRequest;					//Added by Nitisha on 1st Oct,2020

	@FindBy(id = "LM310")
	WebElement tab_advancedCaseRequest;					//Added by Yatharth on 1st Oct,2020
	
	@FindBy(xpath = "//span[contains(text(),'Create New Corporation')]")		//Created by Kuchinad Shashank
	WebElement tab_createNewCorporation;
	
	@FindBy(css = "input[title='Mark Corporation for deletion']")					//Added by K Shashank
	WebElement btn_markForDeletion;
	
	@FindBy(id = "CheckBox")
	WebElement checkBox_active;
	
	@FindBy(id = "txtPetnrName")
	WebElement textBox_corporation;
	
	@FindBy(id = "btn_EditCorporationdetails")
	WebElement btn_EditCorporationDetails;
	
	@FindBy(id = "btn_MarkCorporationfordeletion")
	WebElement btn_MarkCorporationfordeletion;
	
	@FindBy(xpath = "//a[contains(@title,'Show All') and contains(@style,':bold')]")
	WebElement lnk_ShowAllCorporationBold;
	
	@FindBy(xpath = "//td[@title='Click here to view Contracts']")
	WebElement tab_Contracts;
	
	@FindBy(id = "LMCorpLtr")
	WebElement tab_LetterHTML;
	
	@FindBy(xpath = "//span[contains(text(),'Letters(MSWord)')]")
	WebElement tab_letterMSWord;
	
	@FindBy(xpath = "(//table[@summary='Case managers']//a)[2]")
	WebElement txt_caseManager;			
	
	@FindBy(id = "btn_Editcasemanagers")
	WebElement btn_SavePrimaryContactType;
	
	@FindBy(xpath = "//table[@summary='Attached Case Managers']//select")
	WebElement dropDown_primaryContactType;
	
	@FindBy(id = "btn_Editcasemanagerscontactresponsibility")
	WebElement btn_EditPrimaryFirmContact;
	
	@FindBy(id = "LMCorpCaseMgr")
	WebElement tab_CaseManagerTab;
	
	@FindBy(id = "btn_SaveCorporationdetails")
	WebElement btn_SaveAdditionalCorporationDetails;
	
	@FindBy(id = "txtTradeDet")
	WebElement textBox_DoingBusinessAs;
	
	@FindBy(id = "_ctl0_txtNaics")
	WebElement textBox_naicsCode;
	
	@FindBy(id = "txtStateTaxId")
	WebElement textBox_StateTaxId;
	
	@FindBy(id = "txtSsnNum")
	WebElement textBox_TaxId;
	
	@FindBy(id = "txtNetIncome")
	WebElement textBox_NetAnnualIncome;
	
	@FindBy(id = "txtGrossIncome")
	WebElement textBox_GrossAnnualIncome;
	
	@FindBy(id = "txtNbrOfEmp")
	WebElement textBox_NoOfEmployees;
	
	@FindBy(id = "txtEstPlace")
	WebElement textBox_PlaceOfEstablishment;
	
	@FindBy(id = "txtYearOfEst")
	WebElement textBox_YearOfEstablishment;
	
	@FindBy(id = "txtCategory")
	WebElement textBox_Category;
	
	@FindBy(id = "txtLicenceNo")
	WebElement textBox_LicenseNumber;
	
	@FindBy(id = "txtBusinessDesc")
	WebElement textBox_BusinessDescription;
	
	@FindBy(id = "txtBusinessType")
	WebElement textBox_BusinessType;
	
	@FindBy(xpath = "//table[@summary='Additional Details']/../../preceding-sibling::tr//input[@id='btn_EditCorporationMoreDetails']")
	WebElement btn_EditAdditionalCorpDetails;
	
	@FindBy(id = "txtCell2")
	WebElement textBox_AdministrativeMobileNumber;

	@FindBy(id = "txtadmnemail")
	WebElement textBox_AdministrativeEmailId;
	
	@FindBy(id = "txtadmntitle")
	WebElement textBox_AdministrativeTitle;
	
	@FindBy(id = "txtadmnlname")
	WebElement textBox_AdministrativeLastName;
	
	@FindBy(id = "txtadmnfname")
	WebElement textBox_AdministrativeFirstName;
	
	@FindBy(xpath = "//div[@id='pnlAdminInfo']/../../preceding-sibling::tr//input[@id='btn_EditCorporationMoreDetails']")
    WebElement btn_editAdministrativeContactInfo;
	
	@FindBy(id = "btn_SaveCorporationdetails")
	WebElement btn_SaveCorporationDetails;
	
	@FindBy(id = "txtCell")
	WebElement textBox_signingPersonMobileNumber;
	
	@FindBy(xpath = "//th[contains(text(),'Title')]//following-sibling::td/input")
    WebElement textBox_signingPersonTitle;
	
	@FindBy(xpath = "//div[@id='pnlSigningPerInfo']/../../preceding-sibling::tr//input[@id='btn_EditCorporationMoreDetails']")
    WebElement btn_editSigningPersonInfo;
	
	//Added By Saurav On 3rd Feb 2020
	@FindBy(css = "table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr:nth-child(1)>td:nth-child(2)>a")
	WebElement lnk_FirstCorpName;
	
	@FindBy(id="LMEprCustmLbls")
	WebElement tab_CustomData;
	
	@FindBy(id="LMEprNotes")
	WebElement tab_Notes;
	
	@FindBy(id="LMAnchor43")
	WebElement tab_Document;
	
	@FindBy(css = "td[title='Click here to view To-Do items']")
	WebElement tab_ToDo; 
	
	@FindBy(id="LMEprEmails")
	WebElement tab_Emails;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    WebElement opt_EqualTo;
	
	@FindBy(id="LeftMenuTab47")
	WebElement tab_Questionnaires;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")
    WebElement txt_ShowAllCorporation;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00__0")
	WebElement tbl_CorporationList;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_epr_petnr_nm")
	WebElement searchBox_CorporationName;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_epr_petnr_nm")
	WebElement icon_CorporationNameFilter; 
	

	public CM_CorporationListPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() 
	{
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Case list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	 public void clickOnCorporationName(String corporation) 
	 {           
         /*
          * Created By : Saksham Kapoor
          * Created On : 22 Nov 2019
          */ 
     
	     try {
	    	 Utils.clickElement(driver, txt_ShowAllCorporation, "Show all Corporation" + Utils.getPageLoadTime(driver));
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CorporationList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
	     Utils.waitForElement(driver, lnk_ShowAllCorporationBold);
		 Utils.enterText(driver, searchBox_CorporationName, corporation, "in Corporation Name search box");
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Corporation Name filter");
	          
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CorporationList);
			 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	        
		 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
		 {
			 Log.fail("Unable to find desired corporation . Hence can not Proceed Further", driver, true);
		 }
	          
		 Utils.waitForElement(driver, "//a[contains(text(),'" + corporation + "')]", globalVariables.elementWaitTime, "xpath");
		 WebElement lnk_Corporation= driver.findElement(By.xpath("//a[contains(text(),'" + corporation + "')]"));
		 Utils.clickElement(driver, lnk_Corporation, corporation);
	    
	 }	
	 
	 
	 public void clickQuestionnairesTab()  
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 22 Nov 2019
		  * 
		  */ 
		 
		 Utils.clickElement(driver, tab_Questionnaires, "Questionnaires tab in Corporation");
	 }  
	 
	 
	 public void clickEmailsTab()  
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 22 Nov 2019
		  * 
		  */ 
		 
		 Utils.clickElement(driver, tab_Emails, "Emails tab in Corporation");
	 }  

	 public void clickToDoTab() throws Exception  
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 30 Nov 2019
		  * 
		  */ 
		 
		 Utils.clickElement(driver, tab_ToDo, "To Do tab in Corporation");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - To-Do", "title", "false");
	 }  
	 
	 public void clickDocumentTab() throws Exception  
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 30 Nov 2019
		  */
		 
		 Utils.clickElement(driver, tab_Document, "Documents tab in Corporation");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Document - INSZoom.com", "title", "false");
		 
	 }   
	 
	 
	 public void clickNotesTab() throws Exception  
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 07 Feb 2019
		  */
		 
		 Utils.clickElement(driver, tab_Notes, "Notes tab in Corporation");
	 }  
	 
	
	 public void  clickCustomDataTab()
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 07 Feb 2019
		  */
		 
		 Utils.clickElement(driver, tab_CustomData, "Custom tab in Corporation");
	 }  
	 
	 
	 public void clickFirstCorpNameFromList(boolean screenshot) throws Exception 
	{
		/*
		 * Created By: Saurav Purohit
		 * Modified On: 16/01/2019
		 */
		
		Utils.clickElement(driver, lnk_FirstCorpName, "first Corporation Name from Corporation List");
	} 
	 
	 
	 public void  editSigningPersonInfo(String title, String contactNumber) throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 07 Feb 2019
		  */
		 
		 Utils.waitForElement(driver, "frmepredit", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmepredit");
		 
		 Utils.waitForElement(driver, btn_editSigningPersonInfo);
		 Utils.scrollIntoView(driver, btn_editSigningPersonInfo);
		 Utils.clickElement(driver, btn_editSigningPersonInfo, "edit signing person button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.enterText(driver, textBox_signingPersonTitle, title, "title");
		 Utils.enterText(driver, textBox_signingPersonMobileNumber, contactNumber, "contact number");
		 Utils.clickElement(driver, btn_SaveCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		 Utils.waitForElement(driver, "frmepredit", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmepredit");
		 Utils.verifyIfDataPresent(driver, "//table[@summary='Info of person signing forms']//th[contains(text(),'Title')]/../td[contains(text(),'"+title+"')]", "xpath", title, "title");
		 Utils.verifyIfDataPresent(driver, "//table[@summary='Info of person signing forms']//th[contains(text(),'Mobile Number')]/../td[contains(text(),'"+contactNumber+"')]", "xpath", contactNumber, "contact number of signing person");
		 driver.switchTo().defaultContent();
	 }  
	 
	 
	 public void editAdministrativeContactInfo(String firstName, String lastName, String title, String contactNumber, String email) throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 07 Feb 2019
		  */
		 
		 Utils.waitForElement(driver, "frmadmininfo", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmadmininfo");
		 Utils.clickElement(driver, btn_editAdministrativeContactInfo, "edit administrative contact button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.enterText(driver, textBox_AdministrativeFirstName, firstName, "adminstrative first name");
		 Utils.enterText(driver, textBox_AdministrativeLastName, lastName, "adminstrative last name");
		 Utils.enterText(driver, textBox_AdministrativeTitle, title, "title");
		 Utils.enterText(driver, textBox_AdministrativeMobileNumber, contactNumber, "mobile number");
		 Utils.enterText(driver, textBox_AdministrativeEmailId, email, "email id");
		 Utils.clickElement(driver, btn_SaveCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		 
		 Utils.waitForElement(driver, "frmadmininfo", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmadmininfo");
		 Utils.verifyIfDataPresent(driver, "//table[@summary='Info of Administrative contact person']//th[contains(text(),'Title')]/../td[contains(text(),'"+title+"')]", "xpath", title, "administrative title");
		 Utils.verifyIfDataPresent(driver, "//table[@summary='Info of Administrative contact person']//th[contains(text(),'Mobile Number')]/../td[contains(text(),'"+contactNumber+"')]", "xpath", contactNumber, "administrative contact");
		 Utils.verifyIfDataPresent(driver, "//table[@summary='Info of Administrative contact person']//th[contains(text(),'E-mail ID')]/../td[contains(text(),'"+email+"')][1]", "xpath", email, "email id");
		 driver.switchTo().defaultContent();
	 }
	 
	 
	 public void addAddtionalCorpDeatils(String businessDescription, String businessType, String yearEstablished, String placeOfIncorporation, String numberOfEmployees, String doingBusinessAs, String grossAnnualIncome, String netAnnualIncome, String taxID, String stateTax, String naicsCode) throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 25 Feb 2020
		  */
		 
		 Utils.waitForElement(driver, "frmepraddnl", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmepraddnl");
		 Utils.clickElement(driver, btn_EditAdditionalCorpDetails, "edit addtional corp details button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.enterText(driver, textBox_BusinessType, businessType, "business type");
		 Utils.enterText(driver, textBox_BusinessDescription, businessDescription, "business description");
		 Utils.enterText(driver, textBox_PlaceOfEstablishment, placeOfIncorporation, "place of establishment"); 
		 Utils.enterText(driver, textBox_YearOfEstablishment, yearEstablished, "year established");
		 Utils.enterText(driver, textBox_NoOfEmployees, numberOfEmployees, "number of employees");
		 Utils.enterText(driver, textBox_GrossAnnualIncome, grossAnnualIncome, "gross annual income");
		 Utils.enterText(driver, textBox_NetAnnualIncome, netAnnualIncome, "net annual income");
		 Utils.enterText(driver, textBox_TaxId, taxID, "tax id");
		 Utils.enterText(driver, textBox_StateTaxId, stateTax, "state tax id");
		 Utils.enterText(driver, textBox_naicsCode, naicsCode, "NAICS code");
		 Utils.enterText(driver, textBox_DoingBusinessAs, doingBusinessAs, "doing business as");
		 Utils.clickElement(driver, btn_SaveAdditionalCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
	 } 

	 public void clickCaseManagerTab()
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 26 Feb 2020
		  */
		 Utils.clickElement(driver, tab_CaseManagerTab, "case manager tab");
	 }
	 	 
	 public void editPrimaryFirmContact() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 26 Feb 2020
		  */
		 Utils.clickElement(driver, btn_EditPrimaryFirmContact, "edit primary firm contact");
		 Utils.waitForElement(driver, txt_caseManager);
		 globalVariables.primaryFirmContact = txt_caseManager.getText();
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com : Edit Corporation Case Manager", "title", "false");
		 
		 Utils.selectOptionFromDropDown(driver, "Yes", dropDown_primaryContactType);
		 Utils.clickElement(driver, btn_SavePrimaryContactType, "save");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com ::Corporation Case manager list", "title", "false");
	 }
	 
	 
	 public void clickLetterMSWordTab() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 14 March 2020
		  */
		 Utils.clickElement(driver, tab_letterMSWord, "letter MS Word tab");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
	 }
	 
	 
	 public void clickLetterHTMLTab() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 09 April 2020
		  */
		 Utils.clickElement(driver, tab_LetterHTML, "letter HTML tab");
	 }
	 
	 public void clickContractsTab() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 09 April 2020
		  */
		 Utils.clickElement(driver, tab_Contracts, "contracts tab");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
	 }
	 
	 public void deleteCorporation() throws Exception
	 {
		 /*
		  * Edited By : Souvik Ganguly
		  * Created On : 25 June 2021
		  */
		 Utils.waitForElement(driver, "frmeprView", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmeprView");
		 Utils.clickElement(driver, btn_MarkCorporationfordeletion, "delete");
		 driver.switchTo().alert().accept();
		 driver.switchTo().defaultContent();
		 Utils.waitForAllWindowsToLoad(1, driver);
		 /*The below line is added to wait for a button so that the loading of the entire page 
		  is completed before going to next operation*/
		 Utils.waitForElement(driver, btn_chooseColumns);
		 
	 }
	 
	
	 public void editCorporationName(String edittedCorporationName) throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 19 Aug 2020
		  */
		
		 Utils.waitForElement(driver, "frmeprView", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmeprView");
		 Utils.clickElement(driver, btn_EditCorporationDetails, "edit");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.enterText(driver, textBox_corporation, edittedCorporationName, "new corp name");
		 Utils.clickElement(driver, btn_SaveCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		 Utils.waitForElement(driver, "frmeprView", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmeprView");
		 Utils.verifyIfDataPresent(driver, "//th[text()='Name']/../td[contains(text(),'"+edittedCorporationName+"')]", "xpath", edittedCorporationName, "corp name");
		 driver.switchTo().defaultContent();
	 }
	 
	 public void inactivateCorporation() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 19 Aug 2020
		  */
		 Utils.waitForElement(driver, "frmeprView", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmeprView");
		 Utils.clickElement(driver, btn_EditCorporationDetails, "edit");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.clickElement(driver, checkBox_active, "active/inactive checkbox");
		 driver.switchTo().alert().accept();
		 Utils.clickElement(driver, btn_SaveCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		 driver.switchTo().frame("frmeprView");
		 Utils.verifyIfDataPresent(driver, "//b[contains(text(),'InActive')]", "xpath", "In active", "corp page");
		 driver.switchTo().defaultContent();
	 }
	 
	 
	 public void activateCorporation() throws Exception
	 {
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 19 Aug 2020
		  */
		 Utils.waitForElement(driver, "frmeprView", globalVariables.elementWaitTime, "id");
		 driver.switchTo().frame("frmeprView");
		 Utils.clickElement(driver, btn_EditCorporationDetails, "edit");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Details", "title", "false");
		 Utils.clickElement(driver, checkBox_active, "active/inactive checkbox");
		 Utils.clickElement(driver, btn_SaveCorporationDetails, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		 driver.switchTo().frame("frmeprView");
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Active')]", "xpath", "active", "corp page");
		 driver.switchTo().defaultContent();
	 }
	 
	 
	 public void verifyAddCorporationAccess(boolean value)
	 {
		 /*
		 * Modified By : Sharon Mathew
		 * Modified On : 11/8/2021
		 * Method to verify if the Create New Corporation is enabled in the Left Sub Menu.
		 */
		 try{
		// Utils.clickElement(driver, tab_createNewCorporation, "Create New Corporation Link");
		 Boolean present=Utils.waitForElementIfPresent(driver, "//span[contains(text(),'Create New Corporation')]",20, "xpath", "Create New Corporation Tab");
			
			if(present)
			{
				Utils.clickElement(driver, tab_createNewCorporation, "Create New Corporation link");
			}
			else
			{
				Utils.selectSubMenuOption(driver,"Shortcuts");
				Utils.switchWindows(driver, "INSZoom.com - Customize Tabs", "title", "false");
				Utils.verifyandSelectCheckBox(driver,"Create New Corporation");
				//Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
				Utils.waitForElement(driver,tab_createNewCorporation,60);
				Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Create New Corporation')]", "xpath", "Create New Corporation Tab", "Client Profile Page");
				present=true;
			
			}
		 if(value)
		 {
			 if(!Utils.isAlertPresent(driver)&&present==true)
			 {
				 Log.pass("Verified. The Add Corporation Acess is given", driver, true);
				 
			 }
			 else
			 {
				 Log.fail("ADD Corporation Access is not given", driver, true);
			 }
		 }
		 else
		 {
			 if(Utils.isAlertPresent(driver)&& present==true)
			 {
				 driver.switchTo().alert().accept();
				 Log.pass("Verified. The Add Corporation Acess is not given", driver, true);
				 
			 }
			 else
			 {
				 Log.fail("ADD Corporation Access is given", driver, true);
			 }
		 }
		 }
		 catch(Exception e)
		 {
			 Log.fail("Create New Corporation verification failed. "  + e.getMessage(), driver, true);
		 }
	 }
	 
	 
   public void verifyDeleteButtonAccess(boolean value)
   {
	   /*
        * Edited By : Souvik Ganguly
        * Edited On : 21 July 2021 
        * Updated the error message
        */ 
	   
	   driver.switchTo().frame("frmeprView");
	   Utils.clickElement(driver, btn_markForDeletion, "Mark for Deletion Button");
	   String msg = driver.switchTo().alert().getText();
	   driver.switchTo().alert().dismiss();
	   driver.switchTo().defaultContent();
	   boolean result = msg.contains("Are you sure to mark this Corporation for deletion?");
	   if(value)
		{
			if(result)
			{
				Log.pass("Verified. Delete Corporation Access is given.", driver, true);
			}
			else
			{
				Log.fail("Delete Corporation Access is not given.", driver, true);
			}
		}
		else
		{
			if(!result)
			{
				Log.pass("Verified. Delete Corporation Access is not given.", driver, true);
			}
			else
			{
				Log.fail("Delete Corporation Access is given.", driver, true);
			}
		}
   }
   
   public void clickAdvancedCaseRequestTab()
   {
	
	   //Created by : Yatharth Pandya
	   //Created on : 1st Oct,2020
	   
	   Utils.clickElement(driver, tab_advancedCaseRequest, "Advanced Case Request");
	   
   }

   
   public void  getQuestionnaireForInitiorAndCreatorAttachedToCRTemplate(String crTemplate)
   {
	
	   //Modified by : Saurav Purohit
	   //Created on : 16th Aug,2021
       //Added a wait for last row of Questionnaire For Initiator Table.
	   
	   Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ crTemplate +"')]", "xpath", crTemplate);
	   
	   Utils.clickElement(driver, tab_questionnaireUnderCaseRequest, "Questionnaire tab under Case Request");
	   
	   Utils.waitForElement(driver, lastRow_QuestionnaireForInitiatorTable);
	   
	   List<WebElement> temp = driver.findElements(By.xpath("//table[@summary='Questionnaire For Initiator']//td//a"));
	 
       for(int j = 0; j < temp.size(); j += 2)
       {
    	   globalVariables.questionnaireUnderCRTemplate.add(temp.get(j).getText());
     	  
       }
       
       temp = driver.findElements(By.xpath("//table[@summary='Questionnaire For Creator']//td//a"));
	   
       for(int j = 0; j < temp.size(); j += 2)
       {
    	   globalVariables.questionnaireUnderCRTemplate.add(temp.get(j).getText());
     	  
       }
      
   }
   
   
   public void addAdvancedCaseRequestTemplate(String templateName) throws Exception
   {
	   //Created by : Yatharth Pandya
	   //Created on : 1st Oct,2020
	   
	   Utils.clickElement(driver, btn_AttachRemoveCaseRequestTemplates, "Btn Attach/Remove");
	   Utils.waitForAllWindowsToLoad(2, driver);
	   Utils.switchWindows(driver, "INSZoom.com :: Attach/Remove Templates", "title", "false");
	   Utils.selectOptionFromDropDown(driver, "United States of America", dropdown_chooseCountry);
	   Utils.verifyIfDataPresent(driver, "//select[@id='UserCntrl_list1']/option[contains(text(),'" + templateName + "')]", "xpath", templateName, "Removed list");
	   Utils.selectOptionFromDropDown(driver, "Questionnaire CR template", list1_caseRequest);
	   Utils.clickElement(driver, btn_addCaseRequestTemplate, "Add btn");
	   Utils.verifyIfDataPresent(driver, "//select[@id='UserCntrl_list2']/option[contains(text(),'" + templateName + "')]", "xpath", templateName, "Added list");
	   Utils.clickElement(driver, btn_Savetemplates, "Save Templates");
	   Utils.waitForAllWindowsToLoad(1, driver);
	   Utils.switchWindows(driver, "INSZoom :: Advanced Case Request", "title", "false");
	   Utils.waitForElement(driver, btn_AttachRemoveCaseRequestTemplates);
   }
   
   
   public void removeAdvancedCaseRequestTemplate(String templateName) throws Exception
   {
	   //Created by : Yatharth Pandya
	   //Created on : 3rd Oct,2020
	   
	   Utils.clickElement(driver, btn_AttachRemoveCaseRequestTemplates, "Btn Attach/Remove");
	   Utils.waitForAllWindowsToLoad(2, driver);
	   Utils.switchWindows(driver, "INSZoom.com :: Attach/Remove Templates", "title", "false");
	   Utils.selectOptionFromDropDown(driver, "United States of America", dropdown_chooseCountry);
	   Utils.verifyIfDataPresent(driver, "//select[@id='UserCntrl_list2']/option[contains(text(),'" + templateName + "')]", "xpath", templateName, "Added list");
//	   Utils.selectOptionFromDropDown(driver, "Questionnaire CR template", list2_caseRequest);
	   Utils.clickDynamicElement(driver, "//select[@id='UserCntrl_list2']/option[contains(text(),'" + templateName + "')]", "xpath", templateName);
	   Utils.clickElement(driver, btn_removeCaseRequestTemplate, "Remove btn");
	   Utils.verifyIfDataPresent(driver, "//select[@id='UserCntrl_list1']/option[contains(text(),'" + templateName + "')]", "xpath", templateName, "Removed list");
	   Utils.clickElement(driver, btn_Savetemplates, "Save Templates");
	   Utils.waitForAllWindowsToLoad(1, driver);
	   Utils.switchWindows(driver, "INSZoom :: Advanced Case Request", "title", "false");
	   Utils.waitForElement(driver, btn_AttachRemoveCaseRequestTemplates);      
   }
   
   
   public void verifyCaseRequestTemplatePresent(String templateName)
   {
	   //Created by : Yatharth Pandya
	   //Created on : 3rd Oct,2020
	   
	   Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ templateName +"')]", "xpath", templateName, "Case request template list");
   }
   
   
   public void verifyCaseReuestTemplateNotPresent(String templateName)
   {
	   //Create by : Yatharth Pandya
	   //Created on : 3rd Oct,2020
	   
	   Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ templateName +"')]", btn_AttachRemoveCaseRequestTemplates, "xpath", templateName, "Case request template list");
   }

   
   public void clickAdvancedCaseRequestTemplate(String templateName)
   {
   //Created by : Yatharth Pandya
   //Created on : 9th Oct,2020

   Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ templateName +"')]", "xpath", "Case request template list"); 
   }
   
	 public void clickCorpUsersTab()  
	 {
		 /*
		  * Created By : Souvik Ganguly
		  * Created On : 2 Sep 2021
		  * 
		  */ 
		 
		 Utils.clickElement(driver, tab_CorpUsers, "CorpUsers tab in Corporation");
		 Utils.waitForAllWindowsToLoad(2, driver);
	 } 

		
}
