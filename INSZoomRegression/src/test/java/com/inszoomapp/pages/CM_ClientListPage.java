package com.inszoomapp.pages;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_ClientListPage extends LoadableComponent<CM_ClientListPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	int clientQuestionnaireCountBeforeEmail;
	int emailedQuestionnaireCountBeforeEmail;
	int clientQuestionnaireCountAfterEmail;
	int emailedQuestionnaireCountAfterEmail;
	
	
	@FindBy(xpath = "//td[contains(text(),'Basic Corporation Information')]") //Added by Sharon Mathew on 10th August, 2021	
	WebElement basicCorporationInfoText;	
	
	@FindBy(xpath = "//td[contains(text(),'Crime Info')]/following-sibling::td/input")  //Created by Souvik Ganguly on 07/07/2021
	WebElement textbox_CrimeInfo;
	
	@FindBy(id="ComboBoxAction") //Added by Saurav Purohit on 24/06/2021
    WebElement select_Actions;
	
	@FindBy(id="btn_SaveClientprofiledetails") //Added by Souvik Ganguly on 24/06/2021
	WebElement btn_SaveClientProfileDetails;
	
	@FindBy(id="btn_EditClientsprofiledetails") //Added by Souvik Ganguly on 24/06/2021
	WebElement btn_EditClientProfileDetails;
	
	@FindBy(xpath = "(//table[contains(@summary,'Questionnaire List For')])[1]")
	WebElement table_questionnaireList;							//Added by Saurav Purohit on 15th june, 2021	
	
		@FindBy(id="txtAccDt")
	WebElement txtbox_accessPeriod;
	
	@FindBy(id="txtTo")
	WebElement txtbox_To;
	
	@FindBy(id = "btn_MarkClientForDeletion")					//Added by K Shashank
	WebElement btn_markForDeletion;
	
	@FindBy(id = "ClientList")					//Added By K Shashank
	WebElement waitElement_clientListHeader;	
	
	@FindBy(xpath = "//span[contains(text(),'Add New Client')]") // Modified by Sharon Mathew on 16-08-21
	WebElement lnk_addNewClient;

	@FindBy(id = "LM11")
	WebElement tab_addCaseList;
	
	@FindBy(id = "LM102")
	WebElement tab_addCaseForms;
	
	@FindBy(id = "btn_Closethiswindow")
	WebElement btn_closeConfirmationDeleteWindow;
	
	//Added By Saurav on 9th June 2020
	
	@FindBy(id = "btn_Deleterecords")
    WebElement btn_confirmDelete;
   
    @FindBy(id = "btn_MarkClientForDeletion")
    WebElement btn_deleteClient;
   
    @FindBy(xpath = "//th[text()='Alien Number']/following-sibling::td")
    WebElement textBox_AlienNumber;
   
    @FindBy(xpath = "//th[contains(text(),'User Id')]/following-sibling::td")
    WebElement textBox_userId;
   
    @FindBy(id = "txtBirthdate")
    WebElement textBox_year;
   
    @FindBy(id = "cboFDD")
    WebElement dropDown_day;
   
    @FindBy(id = "cbofromMM")
    WebElement dropDown_month;
   
    @FindBy(id = "btn_SavePassportInfo")
    WebElement btn_SavePassportInfo;
   
    @FindBy(id = "txtGCAlienNbr")
    WebElement textBox_alienNumber;
   
    @FindBy(id = "btn_EditPassportdetails")
    WebElement btn_EditPassportdetails;
   
    //Added By Saurav on 9th June 2020
	
	@FindBy(id="cboSponType")
	WebElement dropdown_category;
	
	@FindBy(id = "LM282")
	WebElement tab_immigrationStatusHistory;
	
	@FindBy(xpath = "//span[contains(text(),'Contracts')]")
	WebElement tab_Contracts;
	
	@FindBy(xpath = "//td[@title='Click here to view  Letters']")
	WebElement tab_LetterHTML;
	
	@FindBy(id="txtPreEmpId")
	WebElement textBox_prospectEmpId;
	
	@FindBy(id="btn_SaveFileNumbersSelected")
	WebElement btn_saveFileNumberUpdated; 
	
	@FindBy(id="txtFileNumber")
	WebElement textBox_fileNumber; 
	
	@FindBy(css = "input[id='btn_EditClientsprofiledetails']")
	WebElement btn_EditClientInfoPage; 

	@FindBy(css = "table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr:nth-child(1)>td:nth-child(2)>a")
	WebElement lnk_FirstClient; 
		 
	@FindBy(xpath="//td[@title='Click to view Case Docs Checklist/Documents']")
	WebElement tab_DocCheckList;
	
	@FindBy(id="LMARROWIMG77")
	WebElement tab_ArrivalDepartureInfo; 
	
	@FindBy(id="btn_SaveClientsCurrentJobDetails")
	WebElement btn_SaveJobDetails;
	
	@FindBy(id="txtOccupation")
	WebElement textBox_designation;
	
	@FindBy(id="txtTerminationDate")
	WebElement textBox_terminationDate;
	
	@FindBy(id="txtRehireDate")
	WebElement textBox_rehireDate;
	
	@FindBy(id="txtHireDate")
	WebElement textBox_hireDate;
	
	@FindBy(id="txtJobDesc")
	WebElement textBox_responsiblity;
	
	@FindBy(id="TxtCurrency")
	WebElement textBox_currency;

	@FindBy(id="txtSalary")
	WebElement textBox_annualSalary;
	
	@FindBy(id="txtOccupation")
	WebElement textBox_occupation;
	
	@FindBy(id="btn_EditJobDetails")
	WebElement btn_EditJobDetails;
	
	@FindBy(id="btn_SaveeditedCaseformaccessrights")
	WebElement btn_SaveEditedCaseAccessRights;
	
	@FindBy(id="cboCaseAccess")
	WebElement dropDown_caseAccess;
	
	@FindBy(xpath = "//span[contains(text(),'Case Access Rights')]")
	WebElement tab_caseAccessRights;
	
	@FindBy(xpath = "//td[contains(text(),'Country')]/following-sibling::td/select")
	WebElement dropDown_CountryInCustomData;
	
	@FindBy(xpath="//td[contains(text(),'Custom label info for Client')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_EditCustomFieldValues']")
	WebElement btn_EditCustomFieldInfoForClient;
	
	@FindBy(id="txtexpdate")
	WebElement textBox_visaExpirationDate;
	
	@FindBy(id="txtissDate")
	WebElement textBox_visaIssueDate;
	
	@FindBy(id="Nonimmigrant")
	WebElement dropDown_visaType;
	
	@FindBy(id="LM73")
	WebElement tab_profile;
	
	@FindBy(css = "#bnf-details-div > a")
	WebElement txt_ClientName;
	
	@FindBy(css = "#epr-details-div > a.case-bnf-hdr")
	WebElement txt_CorpName;
	
	@FindBy(css = "a[class='PgTtlCaseId']+font")
	WebElement txt_CaseId;
	
	@FindBy(css = "select[id='inputSponsor']")
	WebElement dropdown_Sponsor;
	
	@FindBy(xpath="//span[@aria-labelledby='select2-inputClnt-container']/span[@id='select2-inputClnt-container']/span[contains(text(), 'Choose the client for whom the petition requires to be processed ')]")
	WebElement dropdown_ClientSearch;
	
	@FindBy(xpath = "//span[contains(text(), 'Choose the type of petition that requires to be processed for the client')]")// Added By Saksham Kapoor on 08/08/2019
	WebElement dropdown_PetitionSearch;
	
	@FindBy(css = "#caseManagerReceivingRemainder > span > span.selection > span > ul >li")
	WebElement txt_ReceivingRemainder;
	 
	@FindBy(css = "#caseManagerSigningPerson > span > span.selection > span > ul > li")
	WebElement txt_SigningPerson;
	
	@FindBy(css = "select[id='inputClnt']")
	WebElement dropdown_Client;
	
	@FindBy(css = "#AddCaseButton")
	WebElement btn_SaveAddedCase;
	
	@FindBy(xpath = "//*[@id='inputCMRecvReminder']")
	WebElement dropdown_ReceivingReminder;
	
	@FindBy(css = "select[id='inputPetition']")
	WebElement dropdown_SelectPetitonType;
	
	@FindBy(css = "span[class = 'select2-search select2-search--dropdown']>[class = 'select2-search__field']")// Added By Saksham Kapoor on 08/08/2019
	WebElement txtBox_SearchPetition;
	
	
	@FindBy(xpath="//span[contains(text(),'Add Case/Forms to Client')]")
	WebElement tab_addCaseOrFormToClient;
	
	@FindBy(id="btn_SaveNewShippingMailinginfo")
	WebElement btn_SaveNewShippingMailingInfo;
	
	@FindBy(id="txtComment")
	WebElement textBox_Comments;
	
	@FindBy(id="txtShpmntCost")
	WebElement textBox_ShippmentCost;
	
	@FindBy(id="cboLogAccess")
	WebElement dropDown_extranetAccess;
	
	@FindBy(id="txtTrackNum")
	WebElement textBox_TrackingNumber;
	
	@FindBy(id="cboShpmntCatg")
	WebElement dropDown_ShippmentReason;
	
	@FindBy(id="cboShpmntMethod")
	WebElement dropDown_ShippmentMethod;
	
	@FindBy(id="txtShpmntDate")
	WebElement textBox_ShippmentDate;
	
	@FindBy(id="cboCaseId")
	WebElement dropDown_caseId;
	
	@FindBy(id="btn_Addnewshippingmailinginformation")
	WebElement btn_AddNewShippingDetails;
	
	@FindBy(xpath="//span[contains(text(),'Shipping/Mailing Log')]")
	WebElement tab_ShippingMailingLog;
	
	@FindBy(css="input[title='Search Client']")
	WebElement lnk_searchClient;
	
	@FindBy(css="input[id='txtCtrl1']")
	WebElement searchbox_firstName;
	
	@FindBy(id="btn_SearchClient")
	WebElement btn_find;
	
	@FindBy(css="td[title='Click here to view To-Do items']")
	WebElement tab_ToDo;
	
	@FindBy(id="LM12")
	WebElement tab_Emails;
	
	@FindBy(id="LM19")
	WebElement tab_Questionnaires;	
	
	@FindBy(id="LMAnchor15")
	WebElement tab_Documents;
	
	@FindBy(xpath = "//tr[@title='Click to View/Email  Forms']")
	WebElement tab_forms; 
	
	@FindBy(xpath = "//td[@title='Click here to view  Letter(MSWord)']")
	WebElement tab_letterMSWord; 
	
	@FindBy(xpath = "//tr[@title='Click to view Passport Info']")
	WebElement tab_passportInfo; 
	
	@FindBy(css = "input[name='txtEmpId']")
	WebElement txtbox_EmployeeID;
	
	@FindBy(id="btn_SaveClientprofiledetails")
	WebElement btn_SaveEditedClientDetails;
	
	@FindBy(id="btn_EditClientsprofiledetails")
	WebElement btn_Edit;
	
	@FindBy(css = "td[title='Click to view  Relatives']")
	WebElement tab_Relatives;
	
	@FindBy(css = "td[id='LM77']")
	WebElement tab_TravelInfo; 
	
	@FindBy(id = "LM17")
	WebElement tab_AccessRights;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")
	WebElement txt_ShowAllClient;
	
	@FindBy(xpath = "//table[@summary='Document Expiration Dates']//tr")
	List<WebElement> documentExpirationDatesRows;
	
	@FindBy(id = "LM317")
	WebElement tab_StatusDocs;
	
	@FindBy(id = "LM183")
	WebElement tab_Visas;
	
	@FindBy(id = "LM108")
	WebElement tab_DocumentExpiration;
	
	@FindBy(id = "LM282")
	WebElement tab_PetitionHistory;
	
	@FindBy(id = "LM11")
	WebElement tab_caseList;
	
	@FindBy(css = "td[class='LMIS'][id='LM9'][title='Click to view Passport Info']")
	WebElement btn_PassportInfo;
	
	@FindBy(id="LM8")
	WebElement tab_PersonalInfo;
	
	@FindBy(id = "LM190")
	WebElement tab_USImmigration;
	
	@FindBy(id = "btn_SaveVISAdetails")
	WebElement btn_SaveVisa;
	
	@FindBy(id = "txtVisaNbr")
	WebElement textBox_visaNumber;
	
	@FindBy(xpath = "//th[contains(text(),'Visa Application Type')]")
	WebElement txt_VisaApplicationType;
	
	@FindBy(id = "cboCountry")
	WebElement dropDown_VisaCountry;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_GridComposeEmailBtn")
	WebElement icon_AddVisa;
	
	@FindBy(id = "LM183")
	WebElement tab_Visa;
	
	@FindBy(id = "btn_SaveCustomFieldValues")
	WebElement btn_SaveCustomFieldValues;
	
	@FindBy(id = "btn_EditCustomFieldValues")
	WebElement btn_EditCustomFieldValues;
	
	@FindBy(id = "LM20")
	WebElement tab_CustomData;
	
	@FindBy(id = "LM453")
    WebElement tab_TaskTab;
	
	@FindBy(id="LM13")
	WebElement tab_HistoryInfo;
	
	@FindBy(css = "td[title='Click to view JOB Details']")
	WebElement tab_JobDetails;
	
	@FindBy(id = "LM109")
    WebElement tab_CommunicationSummaryClient;
	
	@FindBy(id = "LM18")
    WebElement tab_AppointmentsActivities;
	
	@FindBy(css = "td[title='Click to view  Phone Log']")
	WebElement tab_PhoneLog;
	
	@FindBy(xpath = "//div[@class='rgDataDiv']/table")
    WebElement tbl_ClientList;
    
    @FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    public static WebElement opt_EqualTo;
    
    @FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_bnf_first_name")
    WebElement icon_FirstNameFilter;
    
    @FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_bnf_first_name")
    WebElement searchBox_ClientFirstName;
    
    @FindBy(id = "LM16")
    WebElement tab_notes;

	@FindBy(css = "td[id='LM81']")
	WebElement tab_ChangeLoginId;
	
	@FindBy(css = "input[id='btn_SaveClientDetails']")
	WebElement btn_SaveClient;
	
	@FindBy(css = "input[id='txtEmail']")
	WebElement txtbox_Email;
	
	@FindBy(css = "select[id='cboBnfMainApplYN']")
	WebElement dropdown_MainContact;
	
	@FindBy(css = "input[title='Add New Client']")
	WebElement btn_AddClient;
	
	@FindBy(css = "input[id='txtEprList']+a")
	WebElement lnk_ChooseCorporation;
	
	@FindBy(xpath = "//b[contains(text(),'Select Headquarter')]/ancestor::table")
	WebElement tbl_Corporations;
	
	@FindBy(css = "select[id='cboBnfSponsor']")
	WebElement dropdown_CorporationIs;
	
	@FindBy(id = "txtFname")
	WebElement txtbox_FirstName;

	@FindBy(id = "txtLname")
	WebElement txtBox_LastName;
	
	@FindBy(className = "case-hdr")
	WebElement txt_ClientHeading;


	public CM_ClientListPage(WebDriver driver) {

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

	
	public void clickAddClientButton(boolean sceenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 */
		Utils.clickElement(driver, btn_AddClient, "Add Client Button");
	}
	
	
	public void enterDataToCreateClient(String dataWrite, String sheetName, String corpName, String firstName1, String lastName1, String emailID) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "CorpEmp", dropdown_category);

		Utils.clickElement(driver, lnk_ChooseCorporation, "CHOOSE CORPORATION");
		
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - List of Recently Visited Corporation", "title", "false");
        
        Utils.waitForElement(driver, tbl_Corporations);
        Utils.clickDynamicElement(driver, "//td[contains(text(), '" + corpName + "')]/..//a", "xpath", "Corporation as " + corpName);

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -Client Registration", "title", "false");
		
		Utils.clickElement(driver, dropdown_CorporationIs, "'Corporation Is' dropdown");
	
		Utils.selectOptionFromDropDownByVal(driver, "E", dropdown_CorporationIs);
			
		Utils.enterText(driver, txtbox_FirstName, firstName1, "First Name");
		
		Utils.enterText(driver, txtBox_LastName, lastName1, "Last Name");
		
		clientFullName = firstName1 + " " + lastName1;
		
		Utils.clickElement(driver, dropdown_MainContact, "'Main Contact' dropdown");
		Utils.selectOptionFromDropDownByVal(driver, "N", dropdown_MainContact);

		Utils.enterText(driver, txtbox_Email, emailID, "Email");


	}
	
	
	public void clickSaveClientButton(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 10/10/2019
		 */
		
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		Utils.clickElement(driver, btn_SaveClient, "Save Button");
			
	}
	
	
	public void verifyIfClientCreated(String dataWrite, String sheetName, boolean screenshot)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 10/10/2019
		 */
		
		try
		{
			Utils.waitForElement(driver, txt_ClientHeading);
			String savedFirstClientName = txt_ClientHeading.getText();
	
			if (savedFirstClientName.equalsIgnoreCase(clientFullName)) 
			{
				Log.pass("The client name has been verified successfully " + savedFirstClientName, driver, screenshot);
			} 
			else 
			{
				Log.message("", driver, screenshot);
				Log.fail("Verification of details was not successful as the name fetched is " + savedFirstClientName + " instead of " + clientFullName);
			}

			
		}catch(Exception e){
			Log.message("",driver, screenshot);
			Log.fail("Error while fetching saved client data. Error - " + e.getLocalizedMessage());
		}
	}
	
	
	public void clickChangeLoginIdClientTab(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.clickElement(driver, tab_ChangeLoginId, "Change Login ID Tab");
	}
	
	public void clickNotesTabInClient(boolean sceenshot) 
    {
          Utils.clickElement(driver, tab_notes, "Notes tab");
          
    }
    
    
    public void clickOnClientName(String firstName, boolean screenshot) 
    {           
          /*
          * Created By : M Likitha Krishna
          * Created On : 10 Oct 2019
          */ 
    	
    	Utils.clickElement(driver, lnk_searchClient, "'Search Client'");
    	
    	Utils.waitUntilLoaderDisappear(driver);
    	
    	Utils.enterText(driver, searchbox_firstName, firstName, "first name search box");
    	
    	Utils.clickElement(driver, btn_find, "'Find'");
    	
    	Utils.clickDynamicElement(driver, "//a[contains(text(), '" + firstName + "')]", "xpath", "searched client");
    
    }

	
	public CM_ClientPhoneLogPage clickPhoneLogTab(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
		Utils.clickElement(driver, tab_PhoneLog, "Phone Log Tab");
		return new CM_ClientPhoneLogPage(driver);

	}
	
	
	public void clickAppointmentsActivitiesTab(boolean sceenshot) 
    {
		/*
         * Created By : M Likitha Krishna
         * Created On : 15 Oct 2019
         */
         Utils.clickElement(driver, tab_AppointmentsActivities, "Appointments Activities tab");
          
    }

 
	public void clickCommunicationSummaryInClient(boolean screenshot)  
    {
          /*
          * Created By : M Likitha Krishna
          * Created On : 11 Oct 2019
          */ 
		Utils.clickElement(driver, tab_CommunicationSummaryClient, "Communication Summary in Client");
    } 
	
	
	public CM_JobDetailspage clickJobDetailsTab(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.clickElement(driver, tab_JobDetails, "Job Details Tab");
		return new CM_JobDetailspage(driver);
	}
	
	
	public CM_HistoryInfoPage clickHistoryInfo(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.clickElement(driver, tab_HistoryInfo, "History Info Tab");
	
		return new CM_HistoryInfoPage(driver);
	}

	
	public void editVisaNumber(String presnetVisaNumber,String edittedVisaNumber, boolean screenshot) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ presnetVisaNumber +"')]/../../td/a[@title='Edit']", "xpath", "edit icon");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit VISA Details", "title", "false");
		Utils.enterText(driver, textBox_visaNumber, edittedVisaNumber, "editted visa number");
		Utils.clickElement(driver, btn_SaveVisa, "save editted visa details button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");
	}
	
	public CM_UsImmigrationInfoPage clicktUSImmigrationTab()
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		
		Utils.clickElement(driver, tab_USImmigration, "US Immigration Tab in client");
		return new CM_UsImmigrationInfoPage(driver);
	} 
	
	
	public void addVisaFields(String country, String visaNumber, String visaType, String issueDate, String expirationDate) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		clickAddVisaIcon();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add VISA Details", "title", "false");
		Utils.selectOptionFromDropDown(driver, country, dropDown_VisaCountry);
		Utils.waitForElement(driver, txt_VisaApplicationType);
		Utils.enterText(driver, textBox_visaNumber, visaNumber, "visa number");
		Utils.selectOptionFromDropDown(driver, visaType, dropDown_visaType);
		Utils.enterText(driver, textBox_visaIssueDate, issueDate, "visa issue date");
		Utils.enterText(driver, textBox_visaExpirationDate, expirationDate, "visa expiration date");
		Utils.clickElement(driver, btn_SaveVisa, "Save Visa");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");
	}
	
	
	public void verifyVisaFields(String country, String visaNumber, String issueDate, String expireDate, String visaType) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ country +"')]", "xpath", country, "country");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ visaNumber +"')]", "xpath", visaNumber, "visa number");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ issueDate +"')]", "xpath", visaNumber, "visa number");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ expireDate +"')]", "xpath", visaNumber, "visa number");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ visaType +"')]", "xpath", visaNumber, "visa number");
	}  
	
	
	public void verifyCustomDataField(String salary, boolean screenshot) throws Exception 
	{
		/*
 		 * Edited By : Souvik Ganguly
		 * Edited On : 13 July 2021
 		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Employee Expected Salary')]/../td[3][contains(text(),'"+ salary +"')]", "xpath", salary, "custom field salary");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Crime Info')]/following-sibling::td[contains(text(),'"+globalVariables.crimeInfoData+"')]", "xpath", globalVariables.crimeInfoData, "crime info field");
	}
	
	public void clickVisaTab(){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickElement(driver, tab_Visa, "Visa tab in client");
	}

	
	public void clickAddVisaIcon(){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickElement(driver, icon_AddVisa, "add visa button in client");
	}
	
	
	public void clickTaskTab(boolean screenshot)  
    {
          /*
          * Created By : M Likitha Krishna
          * Created On : 17 Oct 2019
          */ 
		Utils.clickElement(driver, tab_TaskTab, "Task Tab in Client");
    }
	
	public void clickCustomDataTab(){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickElement(driver, tab_CustomData, "Custom data tab");
	}
	
	
	
	public void addCustomDataField(String salary, boolean screenshot) throws Exception 
	{
		/*
 		 * Edited By : Souvik Ganguly
		 * Edited On : 07 July 2021
 		 */
		Utils.clickElement(driver, btn_EditCustomFieldValues, "edit custom field value button");
		Utils.selectOptionFromDropDownByVal(driver, "India", dropDown_CountryInCustomData);
		Utils.waitForElement(driver, "//td[contains(text(),'Employee Expected Salary')]/../td[2]/input", globalVariables.elementWaitTime, "xpath");
		WebElement textBox_Salary = driver.findElement(By.xpath("//td[contains(text(),'Employee Expected Salary')]/../td[2]/input"));
	    Utils.enterText(driver, textBox_Salary, salary, "salary");
	    Utils.enterText(driver, textbox_CrimeInfo, globalVariables.crimeInfoData, "default custom field");
	    Utils.clickElement(driver, btn_SaveCustomFieldValues, "save button");

	} 
	
	
	public CM_PersonalInfoPage clickPersonalInfoTab(boolean screenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		Utils.clickElement(driver, tab_PersonalInfo, "Personal Info Tab");
		return new CM_PersonalInfoPage(driver);
	}
	
	
	public CM_PassportInfoPage selectPassportInfo(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		Utils.clickElement(driver, btn_PassportInfo, "Passport Info Tab");
		
		return new CM_PassportInfoPage(driver);

	}
	
	
	public void clickCaseListTab(Boolean screenShot){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickElement(driver, tab_caseList, "Case List tab");
	}
	
	
	public void clickCaseId(String caseId, Boolean screenShot) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ caseId +"')]", "xpath", "CaseId");
	} 
	 
 
	public void clickPetitionHistoryTab(Boolean screenShot) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 19 Oct 2019
 		 */
		Utils.clickElement(driver, tab_PetitionHistory, "petition history button");
	} 
	
	
	
	public void clickDocumentExpirationTab(Boolean screenShot) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 19 Oct 2019
 		 */
		Utils.clickElement(driver, tab_DocumentExpiration, "Document Expiration button");
	} 

	
	public void clickVisasTab(Boolean screenShot) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 21 Oct 2019
 		 */
		Utils.clickElement(driver, tab_Visas, "Visas tab button");
	} 
	
	public void clickStatusDocsTab(Boolean screenShot) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 21 Oct 2019
 		 */
		Utils.clickElement(driver, tab_StatusDocs, "Status docs tab button");
	} 
	
	public void verifiedDocumentExpirationTable(Boolean screenshot) {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 21 Oct 2019
 		 */
		
		if (documentExpirationDatesRows.size() > 0) {
			Log.pass("Verified Document Expiration Table loading fine", driver, screenshot);
		}
		else
		{
			Log.fail("Unable to verified Document Expiration Table loading fine", driver, screenshot);
		}
	}
	
	public void clickAccessRightsTab(Boolean screenShot) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 04 Nov 2019
 		 */
		Utils.clickElement(driver, tab_AccessRights, "Access rights tab in client");
	} 
	
	
	
	public CM_TravelInfoPage clickTravelInfoTab() throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 22/10/2019
		 */
		
		Utils.clickElement(driver, tab_TravelInfo, "Travel Info Tab");
		
		return new CM_TravelInfoPage(driver);
	}


	public CM_EditClientRelativePage clickRelativesTab() throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.clickElement(driver, tab_Relatives, "Relatives Tab");
		
		return new CM_EditClientRelativePage(driver);
	}


	public void editClientDetails(String dataWrite, String sheetName, String employeeID, String fileNumber, String prospectiveEmployeeId) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame("frmBnfContact");

		Utils.clickElement(driver, btn_Edit, "Edit Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");

		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		writeExcel.setCellData("QA_ALoginEmpIdTxt", sheetName, "Value", employeeID);
		
		Utils.enterText(driver, txtbox_EmployeeID, employeeID, "Employee ID");
		
		Utils.enterText(driver, textBox_fileNumber, fileNumber, "file number");
		
		Utils.enterText(driver, textBox_prospectEmpId, prospectiveEmployeeId, "prospect emp id");
		
		Utils.clickElement(driver, btn_SaveEditedClientDetails, "Save Button");
		
		Thread.sleep(10000);
		if(driver.getWindowHandles().size() > 1)
		{
			Utils.switchWindows(driver, "INSZoom.com - Update File Number for Relative And Cases", "title", "false");
			
			Utils.clickElement(driver, btn_saveFileNumberUpdated, "save");
		}
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");

	}


	public void verifyClientDetails(String employeeID)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmBnfContact");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Employee Id')]/following-sibling::td[contains(text(), '" + employeeID + "')]", "xpath", employeeID, "Employee ID");
		
		driver.switchTo().defaultContent();
	}
	
	
	public void clickPassportInfoTab() {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, tab_passportInfo, "passport info tab");
	}
	
	public void clickLetterMSWordTab() throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 20 Nov 2019
		  */
		Utils.clickElement(driver, tab_letterMSWord, "letter MS Word tab");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
	}
	
	public void clickFormsTab() {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Nov 2019
		  */
		Utils.clickElement(driver, tab_forms, "forms tab");
	}
	
	
	public void clickQuestionnairesTab() throws Exception 
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 15 Nov 2019
		  */
		
		Utils.clickElement(driver, tab_Questionnaires, "Questionnaires tab");
	}
	
	
	public void clickEmailsTab() throws Exception 
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 16 Nov 2019
		  */
		
		Utils.clickElement(driver, tab_Emails, "Emails tab");
	}
	
	
		public void clickDocumentsTab() throws Exception 
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 13 Nov 2019
		  */
		
		Utils.clickElement(driver, tab_Documents, "Documents tab");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Document - INSZoom.com", "title", "false");
	}
		
		
	public void clickToDoTab() throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 29 Nov 2019
		  */
		
		Utils.clickElement(driver, tab_ToDo, "To Do tab");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - To-Do", "title", "false");
	} 
	
	public void clickShippingOrMailingLogTab() 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 29 Nov 2019
		  */
		Utils.clickElement(driver, tab_ShippingMailingLog, "shipping/Mailing Log tab");
	}
 
	public void addShippingDetails(String caseId, String shippmentDate, String shippmentMethod, String trackingNumber) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 29 Nov 2019
		  */
		Utils.clickElement(driver, btn_AddNewShippingDetails, "add new shipping deatils button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New shipping/mailing info", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, caseId, dropDown_caseId);
		
		Utils.enterText(driver, textBox_ShippmentDate, shippmentDate, "shippment date");
		
		Utils.selectOptionFromDropDown(driver, shippmentMethod, dropDown_ShippmentMethod);
		
		Utils.selectOptionFromDropDownByIndex(driver, 1, dropDown_ShippmentReason);
		
		//Utils.selectOptionFromDropDown(driver, "1. Other", dropDown_ShippmentReason);
		
		Utils.enterText(driver, textBox_TrackingNumber, trackingNumber, "tracking number");
		
		Utils.selectOptionFromDropDownByVal(driver, "B", dropDown_extranetAccess);
		
		Utils.enterText(driver, textBox_ShippmentCost, "20000", "shippment cost");
		
		Utils.enterText(driver, textBox_Comments, "My Test", "comments");
		
		Utils.clickElement(driver, btn_SaveNewShippingMailingInfo, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Shipping/Mailing Log", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+shippmentMethod+"')]", "xpath", shippmentMethod, "shippment method");
	}
	
	
	public void addCaseForRelative(String workbookNameWrite, String sheetName, String savedClientName, String corpName, String caseManeger, String petition, boolean screenshot) throws Exception
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 20 Dec 2019
		  */
		
		Utils.clickElement(driver, tab_addCaseOrFormToClient, "Add Case/Form to client");
	    
   		Utils.waitForAllWindowsToLoad(2, driver);
   		Utils.switchWindows(driver, "Case Creation", "title", "false");

		Utils.waitForElement(driver, dropdown_SelectPetitonType);
		Utils.waitForOptionGroupsAvaiable(driver, dropdown_SelectPetitonType);
			
		try {	
			Utils.selectOptionFromDropDown(driver, petition, dropdown_SelectPetitonType);
		} catch (Exception e) {
			Log.message("Option need to be selected by click", driver, screenshot);
			Utils.selectOptionByClick(driver, petition, dropdown_PetitionSearch, txtBox_SearchPetition, "//span[contains(text(),'" + petition + "')]");	
		}

		Utils.waitForElementPresent(driver, "select[id='inputClnt']", "css");
		Utils.scrollIntoView(driver, dropdown_Client);
		
		Utils.selectOptionFromDropDown(driver, savedClientName, dropdown_Client);
		
	    Utils.selectOptionFromDropDown(driver, corpName, dropdown_Sponsor);
	   	
	  
	  //  Utils.waitForElementPresent(driver, "//option[contains(text(), '" + savedClientName + "')]", "xpath");
	  //  Utils.selectOptionByClick(driver, savedClientName, dropdown_ClientSearch, txtBox_SearchPetition, "//li[contains(text(),'" + savedClientName + "')]");
   		
   		try
   		{	
   			Utils.waitForElement(driver, txt_ReceivingRemainder);
	   		Utils.scrollIntoView(driver, txt_ReceivingRemainder);
	   		String ALoginCaseManagerReceivingRemainderTxtForRelative = txt_ReceivingRemainder.getAttribute("title");
	   		Utils.waitForElement(driver, txt_SigningPerson);
			String ALoginCaseManagerSigningPersonTxtForRelative = txt_SigningPerson.getAttribute("title");
			
			Log.message("Fetched Receiving Reminder as " + ALoginCaseManagerReceivingRemainderTxtForRelative);
			Log.message("Fetched Signing Person as " + ALoginCaseManagerSigningPersonTxtForRelative);
			
			// Write the data in excel
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("QA_ALoginCaseManagerReceivingRemainderTxtForRelative", sheetName, "Value", ALoginCaseManagerReceivingRemainderTxtForRelative);
			writeExcel.setCellData("QA_ALoginCaseManagerSigningPersonTxForRelative", sheetName, "Value", ALoginCaseManagerSigningPersonTxtForRelative);
   			
   		}catch(Exception e)
   		{
   			Log.message("",driver, screenshot);
			Log.fail("Unable to fetch the Case Manger Signing Person Name. Error - " + e.getLocalizedMessage());
   		}
   		
	   	Utils.selectOptionFromDropDown(driver, caseManeger, dropdown_ReceivingReminder);

	    Utils.clickElement(driver, btn_SaveAddedCase, "Add Case");
	    
	    Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com", "title", "false");
		
	}
	
	
	
	public void verifyIfCaseCreated(String country ,String savedClientName, String corpName, String dataWrite, String sheetName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Likitha Krishna
		 * Modified On: 20/12/2019
		 */
		
		Utils.waitForElement(driver, txt_CaseId);
		if (txt_CaseId.isDisplayed()) 
		{
			String verifyCaseIdTxt = txt_CaseId.getText();
			Log.message("Case id created " + verifyCaseIdTxt);

			String tempCaseIDTrim = verifyCaseIdTxt.replace("(", "");
			String caseCreated = tempCaseIDTrim.replace(")", "");
			caseCreated = caseCreated.replace("[", "");
			caseCreated = caseCreated.replace("]", "");

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			
			  writeExcel.setCellData("QA_ALoginCaseCreatedForRelative", sheetName, "Value", caseCreated);
			
			
			Log.message("Case id created " + caseCreated);


			try {
				Utils.waitForElement(driver, txt_ClientName);
				if (txt_ClientName.getText().contains(savedClientName)) {
					Log.message("", driver, screenshot);
					Log.pass("Successfully verified the client name after case creation " + savedClientName);
				} else {
					Log.fail("Unable to verify the client name after case creation " + savedClientName, driver, screenshot);
				}

			} catch (Exception e) {
				Log.message("", driver, screenshot);
				Log.fail("Unable to fetch client full name . Error Message - " + e.getMessage());
			}
		}
	}	
	
	
	public void clickProfileTab()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/01/2020
		 */
		
		Utils.clickElement(driver, tab_profile, "Profile tab");
	}
	
	
	public void editCustomDataClient(String field, String data)
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 14/01/2020
		 */
		Utils.clickElement(driver, btn_EditCustomFieldInfoForClient, "edit custom field button in client");
		Utils.waitForElement(driver, btn_SaveCustomFieldValues);
		WebElement textBox_fieldData = driver.findElement(By.xpath("//td[contains(text(),'"+field+"')]/following-sibling::td/input"));
		Utils.enterText(driver, textBox_fieldData, data, "custom field text box");
		Utils.clickElement(driver, btn_SaveCustomFieldValues, "save button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
	}
	
	
	public void editAccessRightForCase(String corpName) throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 21/02/2020
		 */
		Utils.clickElement(driver, tab_caseAccessRights, "case access tab");
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+corpName+"')]/../..//td/input[@id='btn_EditCaseFormAccessRights']", "xpath", "edit access button");
		Utils.selectOptionFromDropDown(driver, "Granted", dropDown_caseAccess);
		Utils.clickElement(driver, btn_SaveEditedCaseAccessRights, "save");
	}
	
	public void editJobDetailsPage(String designation, String annualSalary, String currencyType, String responsibilities, String hireDate, String rehireDate, String terminationDate) throws Exception
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 26 Feb 2020
		  */
		Utils.clickElement(driver, btn_EditJobDetails, "edit job details button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Current Job Details", "title", "false");
		Utils.enterText(driver, textBox_designation, designation, "department");
		Utils.enterText(driver, textBox_annualSalary, annualSalary, "annual salary");
		Utils.enterText(driver, textBox_currency, currencyType, "currency type");
		Utils.enterText(driver, textBox_responsiblity, responsibilities, "responsiblity");
		Utils.enterText(driver, textBox_hireDate, hireDate, "hire date");
		Utils.enterText(driver, textBox_rehireDate, rehireDate, "rehire date");
		Utils.enterText(driver, textBox_terminationDate, terminationDate, "termination date");
		Utils.clickElement(driver, btn_SaveJobDetails, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Job Details", "title", "false");
	}
	
	
		public CM_TravelInfoPage clickArrivalDepartureInfoTab()
	{
		/*
		 * Modified By: Saurav purohit
		 * Modified On: 23/02/2020
		 */
		
		Utils.clickElement(driver, tab_ArrivalDepartureInfo, "Arrival DepartureInfo Tab");
		return new CM_TravelInfoPage(driver);
	}

	
	public void clickOnDocumentsTab()
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 21/02/2020
		 */
		
		Utils.clickElement(driver, tab_Documents, "Documents Tab");
		
	} 
 
	
	public void verifySaveButtonInAddNewClientPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 17 Jan 2020
		  */
		Utils.verifyIfStaticElementDisplayed(driver, btn_SaveClient, "Save Button", "Add New Client Page");
	} 
		
		
	public void clickFirstClientOnClientListPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 17 Jan 2020
		  */
	    Utils.clickElement(driver, lnk_FirstClient, "Fisrt Link from Client List");
	} 
	
	
	public void verifyEditButtonInClientListPage() throws InterruptedException 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 17 Jan 2020
		  */
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");
        driver.switchTo().frame("frmBnfContact");
		Utils.verifyIfStaticElementDisplayed(driver, btn_EditClientInfoPage, "Edit Button", "Client info Page");
		driver.switchTo().defaultContent();
	} 
	
	
	public void clickLetterHTMLTab() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 03 April 2020
		  */
		Utils.clickElement(driver, tab_LetterHTML, "letter HTML tab");
	}
	
	public void clickContractsTab() throws Exception 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 03 April 2020
		  */
		Utils.clickElement(driver, tab_Contracts, "contracts tab");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
	}
	
	
	public void clickImmigrationStatusHistoryTab(Boolean screenShot)
    {
        /*
          * Created By : M Likitha Krishna
          * Created On : 19 Oct 2019
          */
        Utils.clickElement(driver, tab_immigrationStatusHistory, "Immigration status history");
    }
   
    public void addIndividualClient(String workBook, String sheetName, String firstName, String lastName, String email) throws Exception
    {
        /*
          * Created By : M Likitha Krishna
          * Created On : 11 Aug 2020
          */
        Utils.selectOptionFromDropDownByVal(driver, "Ind", dropdown_category);
        Utils.enterText(driver, txtbox_FirstName, firstName, "first name");
        Utils.enterText(driver, txtBox_LastName, lastName, "last name");
        Utils.enterText(driver, txtbox_Email, email, "email");
        Utils.clickElement(driver, btn_SaveClient, "Save button");
        String fullName = firstName + " " + lastName ;
        Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+fullName+"')]", "xpath", fullName, "verified client created");
        // Write the data in excel
        String directory = System.getProperty("user.dir");
        ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workBook);
        writeExcel.setCellData("IndividualClientFirstName", sheetName, "Value", firstName);
        writeExcel.setCellData("IndividualClientLastName", sheetName, "Value", lastName);
        writeExcel.setCellData("IndividualClientEmailID", sheetName, "Value", email);
    }
   
   
    public void updateAlienNumberAndDOB(String alienNumber, String day, String month, String year) throws Exception
    {
        /*
          * Created By : M Likitha Krishna
          * Created On : 11 Aug 2020
          */
        Utils.clickElement(driver, btn_EditPassportdetails, "edit");
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - Edit Passport Info", "title", "false");
        Utils.enterText(driver, textBox_alienNumber, alienNumber, "alien number");
        Utils.selectOptionFromDropDownByVal(driver, day, dropDown_day);
        Utils.selectOptionFromDropDownByVal(driver, month, dropDown_month);
        Utils.enterText(driver, textBox_year, year, "year");
        Utils.clickElement(driver, btn_SavePassportInfo, "save");
        Utils.waitForAllWindowsToLoad(1, driver);
        Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
        Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Alien Number')]/following-sibling::td[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
    }
   
    public void getLoginID() throws Exception
    {
        /*
          * Created By : M Likitha Krishna
          * Created On : 12 Aug 2020
          */
        clickChangeLoginIdClientTab(true);
        globalVariables.clientLoginID = textBox_userId.getText();
    }

   
    public void deleteClient() throws Exception
    {
        /*
          * Created By : M Likitha Krishna
          * Created On : 14 Aug 2020
          */
        driver.switchTo().frame("frmBnfContact");
        Utils.clickElement(driver, btn_deleteClient, "delete button");
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com :: Confirm Deletion", "title", "false");
        Utils.clickElement(driver, btn_confirmDelete, "confirm delete");
        Thread.sleep(5000);
        Utils.waitForAllWindowsToLoad(1, driver);
        Utils.switchWindows(driver, "INSZoom.com- Corporation Client", "title", "false");
    }
	
    
    public void verifyAddClientButtonPresent(boolean value)
	{
		   /*
	        * Created By : Kuchinad Shashank
	        * Created On : 12 June 2020
	        */ 
		   
		   if(value)
		   {
			   Utils.verifyIfDataPresent(driver, "input[title='Add New Client']", "css", "Add Client Button", "Corporation Page");
		   }
		   else
		   {
			   Utils.verifyIfDataNotPresent(driver, "input[title='Add New Client']", waitElement_clientListHeader, "css", "Add Client Button", "Corporation Page");
		   }
		   
	}
	
	
	public void verifyAddClientAccess(boolean value)
	{
		/*
		 * Modified By : Sharon Mathew
		 * Modified On : 11/8/2021
		 * Method to verify if the Add client Tab is enable din the Left Sub Menu.
		 */
		try
		{
		if(value)
		{
			Boolean present=Utils.waitForElementIfPresent(driver, "//span[contains(text(),'Add New Client')]",20, "xpath", "Add New Client Tab");
			
			if(present)
			{
				Utils.clickElement(driver, lnk_addNewClient, "Add New Client link");
			}
			else
			{
				Utils.selectSubMenuOption(driver,"Shortcuts");
				Utils.switchWindows(driver, "INSZoom.com - Customize Tabs", "title", "false");
				Utils.verifyandSelectCheckBox(driver,"Add New Client");
				//Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
				Utils.waitForElement(driver,basicCorporationInfoText,60);
				Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Add New Client')]", "xpath", "Add New Client Tab", "Client Profile Page");
				present=true;
			
			}
			if(!Utils.isAlertPresent(driver)&&present==true)
			 {
				 Log.pass("Verified. The Add Client Access is given.", driver, true);
				 
			 }
			 else
			 {
				 Log.fail("Add Client Access is not given.", driver, true);
			 }
		}

	}
	catch(Exception e)
	{
		Log.fail("Add New Client verification failed. "  + e.getMessage(), driver, true);
	}
	}
	
	public void verifyDeleteButtonAccess(boolean value) throws Exception
   {
	   /*
        * Edited By : Souvik Ganguly
        * Edited On : 21 Juy 2021
        * Sync added
        */ 
	   
		driver.switchTo().frame("frmBnfContact");
		   
		Utils.clickElement(driver, btn_markForDeletion, "Mark for Deletion Button");
		   
	   if(value)
	   {
		   
		   Utils.switchWindows(driver, "INSZoom.com :: Confirm Deletion", "title", "false");
		   
		   Utils.waitForElement(driver, "//td[@class='TDOCLIST']", globalVariables.elementWaitTime, "xpath");
		   
		   String msg = driver.findElement(By.xpath("//td[@class='TDOCLIST']")).getText();
		   
		   Utils.clickElement(driver, btn_closeConfirmationDeleteWindow, "Close Window Button");
		   
		   Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		   
		   
		   
		   boolean result = msg.contains("Are you sure to mark this Client for Deletion?");
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
	   else
	   {
		   if(Utils.isAlertPresent(driver))
		   {
			   driver.switchTo().alert().dismiss();
			   Log.pass("Verified. The Delete Client Acess is not given.", driver, true);
			 
		   }
		   else
		   {
			 Log.fail("Delete Client Access is given.", driver, true);
		   }
	   }
	   
	   
	   driver.switchTo().defaultContent();
   }
	
	
	public void verifyAddCaseAccess(boolean value, boolean ownClient)
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 6th August 2020
		 */
		
		if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "LM102", "id", "Add Case Tab", "Left sub Menu");
			 Utils.clickElement(driver, tab_caseList, "Case List Left sub Menu Tab");
			 if(ownClient)
			 {
				 Utils.verifyIfDataPresent(driver, "btn_AddNewCaseForms", "id", "Add Case Button", "Clients Case List Page");
			 }
			 else
			 {
				 Utils.verifyIfDataNotPresent(driver, "btn_AddNewCaseForms", tab_Documents, "id", "Add Case Button", "Clients Case List Page");
			 }
		 }
		 else
		 {
			 Utils.verifyIfDataNotPresent(driver, "LM102", tab_Documents, "id", "Add Case Tab", "Left sub Menu");
			 Utils.clickElement(driver, tab_caseList, "Case List Left sub Menu Tab");
			 Utils.verifyIfDataNotPresent(driver, "btn_AddNewCaseForms", tab_Documents, "id", "Add Case Button", "Clients Case List Page");
			 
		 }
		
	}
	
	
	public void verifyEmployeeTransferTabPresent(boolean value)
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 14/08/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "LM262", "id", "Employee Transfer Tab", "Client Profile Menu Tab");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "LM262", tab_Questionnaires, "id", "Employee Transfer Tab", "Client Profile Menu Tab");
		}
	}
	
	public void verifyDefaultEmployeeForEmployee(String workbookName, String sheetName, String value)
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
				Log.pass("There is some Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Main Email"))
		{
			if(emailId.equals(mainEmail))
			{
				Log.message("", driver, true);
				Log.pass("There is no Email ID present by default");
			}
			else
			{
				Log.message("", driver, true);
				Log.pass("There is some Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Alternate Email"))
		{
			if(emailId.equals(alternateEmail))
			{
				Log.message("", driver, true);
				Log.pass("There is no Email ID present by default");
			}
			else
			{
				Log.message("", driver, true);
				Log.pass("There is some Email ID present by default: " + emailId);
			}
		}
		
		if(value.equals("Main and Alternate Email"))
		{
			if(emailId.equals(mainEmail + ";" + alternateEmail))
			{
				Log.message("", driver, true);
				Log.pass("There is no Email ID present by default");
			}
			else
			{
				Log.message("", driver, true);
				Log.pass("There is some Email ID present by default: " + emailId);
			}
		}
	}
	
	
	public void verifyAccessDate(int accessPeriod)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 Oct 2020
		 * 
		 */
		
		Date newDate = DateUtils.addMonths(new Date(), accessPeriod);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate = formatter.format(newDate); 
	    
	    String temp[] = strDate.split("/");
	    if(temp[0].charAt(0) == '0')
	    	temp[0] = temp[0].replace("0", "");
	    if(temp[1].charAt(0) == '0')
	    	temp[1] = temp[1].replace("0", "");
	    
	    strDate = temp[0] + "/" + temp[1] + "/" + temp[2];
	    
	    Utils.waitForElement(driver, txtbox_accessPeriod);
	    
	    if(txtbox_accessPeriod.getAttribute("value").equals(strDate))
	    {
	    	Log.message("", driver, true);
	    	Log.pass("Access Period is verified");
	    }
	    
	    else
	    {
	    	Log.message("", driver, true);
	    	Log.fail("Access Period is NOT verified");
	    }
	}
	
	public int checkClientQuestionnaireCount() throws Exception 
    {
         /*
          * Created By : Saurav
          * Created On : 22 June 2021
          * Modified By :Saurav
          * Modified On : 03/08/2021 . Added logic to check check last column of questionnaire List and pick the one which
          * is not associated with case . 
          */ 
           int clientQuestionnaireCount=0;
         try{
                Utils.waitForElement(driver, select_Actions);
                
                Select dropdown_Actions = new Select(select_Actions);
                
                Utils.waitForOptionsAvaiable(driver, dropdown_Actions);
                
                Utils.waitForElement(driver, table_questionnaireList);
                
                List<WebElement> allrows  = driver.findElements(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr"));
                
                for(int i = 3; i<= allrows.size(); i=i+3){
                    
                   WebElement lastColumn = driver.findElement(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr["+i+"]/td[8]"));
                   
                   if(lastColumn.findElements(By.tagName("a")).size()==0){
                       
                       clientQuestionnaireCount=clientQuestionnaireCount+1;    
                   }   
                
                }
             }catch(Exception e){
                   Log.fail("Issue In Fetching Questionaiire Count from Client questionnaire Section.. ERROR :\n\n " + e.getMessage(), driver, true);
               }
         
         return clientQuestionnaireCount;
    }
	
	
	public int checkEmailedQuestionnaireCount() throws Exception 
    {
         /*
          * Created By : Saurav
          * Created On : 22 June 2021
          * Modified by : saurav
          * Modified on 03/08/2021
          * Edited the message and variable ame .
          */   
         int emailedQuestionnaireCount=0;
         try{
             
                Utils.waitForElement(driver, table_questionnaireList);
                
                List<WebElement> allrows  = driver.findElements(By.xpath("//table[contains(@summary,'Currently Emailed ')]/tbody/tr"));
                
                for(int i = 3; i<= allrows.size(); i=i+2){
                    
                   emailedQuestionnaireCount = emailedQuestionnaireCount+1;
                
                }
             }catch(Exception e){
                   Log.fail("Issue In Fetching the questionnaire count from Emailed list of questionnaire Section. ERROR :\n\n " + e.getMessage(), driver, true);
               }
         return emailedQuestionnaireCount;
    }
	
	
	
	public void extractQuestionnaireCount() throws Exception 
	{
		 /*
		  * Created By : Saurav
		  * Created On : 22 June 2021
		  */   
		
		 try{
			 clientQuestionnaireCountBeforeEmail =checkClientQuestionnaireCount();
			 
			 emailedQuestionnaireCountBeforeEmail = checkEmailedQuestionnaireCount();
			 
			 Log.message("Total No of client questionnaire Before Sending Email are &&&&&&&&"+clientQuestionnaireCountBeforeEmail);
			 
			 Log.message("Total No of Emailed  questionnaire Before Sending Email are &&&&&&&"+emailedQuestionnaireCountBeforeEmail);
			    
	         }catch(Exception e){
				   Log.fail("Issue In Fetching the questionnaire count before Email in Email List and Client Questionnaire section. ERROR :\n\n " + e.getMessage(), driver, true);
			   }
	}
	
	public void extractQuestionnaireCountAfterEmail() throws Exception 
	{
		 /*
		  * Created By : Saurav
		  * Created On : 22 June 2021
		  */   
		
		 try{
			 clientQuestionnaireCountAfterEmail = checkClientQuestionnaireCount();
			 
			 emailedQuestionnaireCountAfterEmail = checkEmailedQuestionnaireCount();
			 
             Log.message("Total No of client questionnaire After Sending Email are "+clientQuestionnaireCountAfterEmail);
			 
			 Log.message("Total No of Emailed  questionnaire After Sending Email are "+emailedQuestionnaireCountAfterEmail);
			    
	         }catch(Exception e){
				   Log.fail("Issue In Fetching the questionnaire count After Email in Email list and client section. ERROR :\n\n " + e.getMessage(), driver, true);
			   }
	 }
	
	
	public void verifyQuestionnaireAddedAfterEmail() throws Exception 
	{
		 /*
		  * Created By : Saurav
		  * Created On : 22 June 2021
		  */   
		
		 try{
			 
			 extractQuestionnaireCountAfterEmail();
			 
			 if(clientQuestionnaireCountAfterEmail > clientQuestionnaireCountBeforeEmail){
				 Log.pass("Questionnaire added Successfully in Client Questionnaire Count After Email");
			 }else{
				 Log.fail("Questionnaire not added successfully in client section. ERROR :\n\n " , driver, true);
			 }
			 
			 if(emailedQuestionnaireCountAfterEmail > emailedQuestionnaireCountBeforeEmail){
				 Log.pass("Questionnaire added Successfully in Emailed List of Questionnaire ");
			 }else{
				 Log.fail("Questionnaire could not be added  in Emailed List of Questionnaire. ERROR :\n\n " , driver, true);
			 }
			    
	         }catch(Exception e){
				   Log.fail("Issue In Fetching the questionnaire count before Email in Email list and client section. ERROR :\n\n " , driver, true);
			   }
	 }
	
	public void removeAlienNumber() throws Exception
    {
        /*
          * Created By : Souvik Ganguly
          * Created On : 24 June 2021
          * The alien number info is moved to profile info from passport info
          */
	    
    	
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");   
		driver.switchTo().frame("frmBnfContact");
		Utils.clickElement(driver, btn_EditClientProfileDetails, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
        Utils.enterText(driver, textBox_alienNumber, "", "alien number");
        Utils.clickElement(driver, btn_SaveClientProfileDetails, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");	
		driver.switchTo().frame("frmBnfContact");
        Utils.waitForElement(driver, textBox_AlienNumber);
        if((textBox_AlienNumber.getText()).equals(" "))
            Log.message("Alien Number deleted successfully");
        else
            Log.fail("Alien Number not deleted");
        driver.switchTo().defaultContent();
    	
       
    }
	
}
