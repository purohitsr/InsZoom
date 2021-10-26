package com.inszoomapp.pages;

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
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_CaseListPage extends LoadableComponent<CM_CaseListPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "txtDescription")
    WebElement textbox_CaseDescription;                //Added by Souvik  on 9th September
	
	@FindBy(xpath = "//label[text()='Crime Info']/../following-sibling::td/input")
    WebElement txtbox_CrimeInfo;           //Added by Saurav  on 4th Sep 2020
	
	@FindBy(id = "btn_UpdateTheInformation.")
    WebElement btn_Update;                //Added by Saurav  on 10th Aug 2020
	
	@FindBy(xpath = "//td[contains(text(),'Sponsor details have changed')]")
    WebElement lnk_SponserChange;        //Added by Saurav  on 10th Aug 2020
	
	@FindBy(id = "btn_SendEmail")
	WebElement btn_sendEmail;				//Added by Yatharth Pandya on 6th Oct,2020
	
	@FindBy(id = "txtSub")
	WebElement textbox_emailSubject;		//Added by Yatharth Pandya on 6th Oct,2020

	@FindBy(id = "btn_AddCaseManagers(signingtheforms)")
	WebElement btn_AddCMSave;		//Added by Yatharth Pandya on 6th Oct,2020
	
	@FindBy(css="label[for='rdoWageSource1']")
	WebElement checkbox_prevailingWageSource;
	
	@FindBy(id = "btn_Cancel")
	WebElement btn_closeCaseAcessRightsPopUp;                //Added by Nitisha Sinha on 16th sept 2020
	
	@FindBy(xpath = "//input[@data-vv-as='Approved On Date']")
	WebElement textBox_ApprovedDate;                //Added by Nitisha Sinha on 16th sept 2020
	
	@FindBy(id="inputCMSignPerson")
	WebElement dropdown_signingCM;
	
	@FindBy(id = "genericPetition")
	WebElement txtBox_petitionDescriptionForStandardEdition;
	
	@FindBy(xpath = "//td[@id='CaseList'][contains(text(),'Case List')]")
	WebElement header_caseList;
	
    @FindBy(xpath = "//table[@id='ctl00_MainContent_ctl00_RadGridList_ctl00']//tr")
    WebElement table_caseResult;
   
    @FindBy(id = "txtFileNumber")
    WebElement textBox_FileNumber;
	
	@FindBy(id = "btn_SaveNewShippingMailinginfo")
	WebElement btn_saveShippingDetails;
	
	@FindBy(id = "txtComment")
	WebElement textBox_shipmentComment;
	
	@FindBy(id = "txtShpmntCost")
	WebElement textBox_shipmentCost;
	
	@FindBy(id = "cboLogAccess")
	WebElement dropDown_ExtranetAccess;
	
	@FindBy(id = "txtTrackNum")
	WebElement textBox_trackingNumber;

	@FindBy(id = "cboShpmntCatg")
	WebElement dropDown_shipmentReason;
	
	@FindBy(id = "cboShpmntMethod")
	WebElement dropDown_shipmentMethod;
	
	@FindBy(id = "txtShpmntDate")
	WebElement textBox_shipmentDate;
	
	@FindBy(id = "btn_Addnewshippingmailinginformation")
	WebElement btn_addShippigMailingLog;
	
	@FindBy(id = "LM167")
	WebElement tab_ShippingMailingLog;
	
	@FindBy(xpath = "//*[@id='btn_SignForms']")
	WebElement link_SignFormsEdit;
	
	@FindBy(xpath = "//*[@id='btn_AttachRemoveCasemanagerwhosignformsforthisCase']")
	WebElement btn_AddCM;
	
	@FindBy(xpath = "//a[@title='Show All Case' and contains(@style,':bold')]")
	WebElement lnk_ShowAllBold;
	
	//added By saurav ofor Smoke tests on 26 feb
	
	@FindBy(css = "input[id='btn_EditProposedJobDetails']")
	WebElement btnProposedJob;
	
	@FindBy(xpath = "//span[contains(text(),'Blanket Applicants')]")
	WebElement tabBlanketApplicants;
	
	@FindBy(css = "input[id='btn_AddblanketapplicantsforthisCase']")
	WebElement btnAddNewBlanketApplicants;
	
	@FindBy(xpath = "//span[contains(text(),'SnapShot')]")
	WebElement tabSnapShots;
	
	
	@FindBy(css = "input[title='Edit priority date details/AOS date details']")
	WebElement btnVisaPriorityDateInfoEdit;
	
	@FindBy(xpath = "//span[contains(text(),'Managers/Contacts')]")
	WebElement tabManagerContacts;
	
	@FindBy(css = "input[id='btn_AttachRemoveCasemanagerwhosignformsforthisCase']")
	WebElement btnAddRemoveCaseManager;
	
	@FindBy(css = "input[id='btn_AttachRemoveStatusDocs']")
	WebElement btnAttachRemoveStatusDocs;
	
	@FindBy(css = "input[title='Edit Custom Field Values']")
	WebElement btnEditCustomFields;
	
	@FindBy(css = "input[id='btn_AttachRemoveCustomFields']")
	WebElement btnAttachRemoveCustomFields;
	
	@FindBy(css = "input[id='btn_CopyCustomFields']")
	WebElement btn_CopyCustomFields;

	@FindBy(xpath = "//span[contains(text(),'Contracts')]")
	WebElement tab_Contacts;
	
	@FindBy(xpath = "//span[contains(text(),'Letters (HTML)')]")
	WebElement tab_LetterHTML;
	
	@FindBy(xpath="//label[contains(text(),'Country')]/../following-sibling::td/select")
	WebElement dropDown_country;
	
	@FindBy(id="txtcity")
	WebElement textBox_proposedJobDetailsCity;
	
	@FindBy(id="btn_SaveProposedJobInfo")
	WebElement btn_SaveProposedJobInfo;
	
	@FindBy(id="txtRelOccup")
	WebElement textBox_relatedOccupation;
	
	@FindBy(id="txtExpRelMos")
	WebElement textBox_ExperienceInRelatedOccupationMonths;
	
	@FindBy(id="txtExpRelYrs")
	WebElement textBox_ExperienceInRelatedOccupationYears;
	
	@FindBy(id="txtExpReqMos")
	WebElement textBox_experienceInJobOfferedMonth;
	
	@FindBy(id="txtExpReq")
	WebElement textBox_experienceInJobOfferedYears;
	
	@FindBy(id="txtMajorStudy")
	WebElement textBox_majorFieldOfStudy;
	
	@FindBy(id="txtQualReq")
	WebElement textBox_qualification;
	
	@FindBy(id="txtNoHrsWeekBasic")
	WebElement textBox_noOfHoursPerWeek;
	
	@FindBy(id="txtOccup")
	WebElement textBox_SOCcode;
	
	@FindBy(id="cboPropJob")
	WebElement dropDown_jobType;
	
	@FindBy(id="txtPropjobDesc")
	WebElement textBox_jobDuties;
	
	@FindBy(id="txtPropJobTitle")
	WebElement textBox_proposedJobTitle;
	
	@FindBy(id="txtSubOrds")
	WebElement textBox_noOfSubOrdinates;
	
	@FindBy(id="txtSupTitle")
	WebElement textBox_supervisorTitle;
	
	@FindBy(id="cboCountryAddr")
	WebElement dropDown_proposedJobDetailsCountry;
	
	@FindBy(id="btn_EditProposedJobDetails")
	WebElement btn_EditProposedJobDetails;
	
	@FindBy(xpath = "//td[contains(text(),'Custom label info for Case')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_EditCustomFieldValues']")
	WebElement btn_EditCustomFieldInfoForCase;
	
	@FindBy(id="txtfromdate")
	WebElement textBox_ApprovalValidFrom;
	
	@FindBy(id="txttodate")
	WebElement textBox_ApprovalTill;
	
	@FindBy(id="txtApprvldate")
	WebElement textBox_ApprovedOn;
	
	@FindBy(id = "btn_SaveCasedetails")
	WebElement btn_SaveCasedetails;
	
	@FindBy(id="saveCaseEdit1")							//Added by Yatharth on 18th August, 2020 for ZoomCms case details page
	WebElement btn_SaveCasedetails1;
	
	@FindBy(id="caseStatus")							//Added by Yatharth on 18th August, 2020 for ZoomCms case details page	
	WebElement dropDown_caseStatus1;
	
	@FindBy(id = "cboodc")
	WebElement dropDown_caseStatus;
	
	@FindBy(id="btn_EditCasedetails")
	WebElement btn_EditCasedetails;
	
	@FindBy(id="btn_SaveeditedprioritydatedetailsAOSdatedetails")
	WebElement btn_saveVisaPriorityDetails;
	
	@FindBy(id="cboPriorityCategory")
	WebElement dropDown_visaCategory;
			
	@FindBy(id="cboPriorityNationality")
	WebElement dropDown_countryOfChargability;
			
	@FindBy(id="txtPriorityDate")
	WebElement textBox_PriorityDate;
	
	@FindBy(id="btn_EditprioritydatedetailsAOSdatedetails")
	WebElement btn_editVisaPriorityDate;
	
	@FindBy(id="idCustom1_okButton")
	WebElement btn_saveLCAGroup;
	
	@FindBy(id="drpLcaGrp")
	WebElement dropDown_LCAGroup;
	
	@FindBy(id="btn_SaveLCADetails")
	WebElement btn_SaveLCADetails;
	
	@FindBy(id="rdoWagePer3")
	WebElement checkBox_LCAWagePer;
	
	@FindBy(id="txtJobCounty")
	WebElement textBox_LCACountry;
	
	@FindBy(id="txtLCAEffDateTill")
	WebElement textBox_LCAEffectiveTill;
	
	@FindBy(id="txtLCAEffDate")
	WebElement textBox_LCAEffectiveOn;
	
	@FindBy(id="drpLCAExtntAccess")
	WebElement textBox_LCAExtntAccess;
	
	@FindBy(id="txtPubSource")
	WebElement textBox_LCAPublishedYear;
	
	@FindBy(xpath="//input[@name='rdoWageLevel' and @value='3']")
	WebElement checkBox_LCAWageLevel;
	
	@FindBy(id="txtTrackNumber")
	WebElement textBox_LCATrackNumber;
	
	@FindBy(id="txtAgencyName")
	WebElement textBox_LCAAgent;
	
	@FindBy(id="txtPreWage")
	WebElement textBox_LCAWages;
	
	@FindBy(id="txtJobPostal")
	WebElement textBox_LCAZipCode;
	
	@FindBy(id="txtJobState")
	WebElement textBox_LCAState;
	
	@FindBy(id="txtJobCity")
	WebElement textBox_LCACity;
	
	@FindBy(id="txtAddress1")
	WebElement textBox_LCAAddress;
	
	@FindBy(id="txtJobTitle")
	WebElement textBox_LCAJobTitle;
	
	@FindBy(id="txtCount")
	WebElement textBox_LCAtotalCount;
	
	@FindBy(id="txtValid")
	WebElement textBox_LCAValidThru;
	
	@FindBy(id="txtValFromDt")
	WebElement textBox_LCAValidFrom;
	
	@FindBy(id="txtETA")
	WebElement textBox_LCANumber;
	
	@FindBy(id="btn_AddCorp.newLCAinfo.")
	WebElement btn_addLCA;
	
	@FindBy(id="LM9312")
	WebElement tab_ToDo;
	
	@FindBy(id="LM94")
	WebElement tab_Questionnaires;
	
	@FindBy(id = "LM28")
    WebElement tab_Forms ;
	
	@FindBy(id = "LM286")
    WebElement tab_DistrictOfficeInfo ; 
	
	@FindBy(id = "LM595")
    WebElement tab_LetterMSWord; 
	
	@FindBy(xpath = "//span[contains(text(),'Case Status')]/../span[3]")
    WebElement txt_CaseStatus;
	
	@FindBy(id = "btn_ChangeCaseStatus")
    WebElement btn_saveCaseStatus; 

	@FindBy(id = "cboStatDesc")
    WebElement dropDown_CaseStatus;
	
	@FindBy(xpath = "//a[contains(text(),'Change Status')]")
    WebElement lnk_changeStatus;
	
	@FindBy(id = "LM25")
    WebElement tab_StatusRemindersSteps; 
	
	@FindBy(id="LM33")
	WebElement tab_Emails;
	
	@FindBy(id = "LM27")
    WebElement tab_DocsCheckListOrDocuments;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")
    WebElement txt_ShowAllCase;
	
	@FindBy(id = "LM317")
    WebElement tab_StatusDocs;
	
	@FindBy(id = "LM265")
    WebElement tab_ServiceCenterInfo;
	
	@FindBy(id = "LM26")
    WebElement tab_DetailsDates;
	
	@FindBy(id = "LM30")
    WebElement tab_ManagersContacts;
	
	@FindBy(id = "LM76")
    WebElement tab_SnapShot;
	
	@FindBy(id = "LM9261")
    WebElement tab_JobDetails; 
	
	@FindBy(id = "LM78")
    WebElement tab_BlanketApplicationTab;
	
	@FindBy(id = "LM164")
    WebElement tab_ProfileTab;
	
	@FindBy(id = "LeftMenuTab120")
    WebElement tab_ReceiptNumberTab;
	
	@FindBy(id = "LM73")
    WebElement tab_ClientProfileTab;
	
	@FindBy(xpath="//span[@aria-labelledby='select2-inputClnt-container']/span[@id='select2-inputClnt-container']/span[contains(text(), 'Choose the client for whom the petition requires to be processed ')]")
	WebElement dropdown_ClientSearch;
	
	@FindBy(id = "LM591")
    WebElement tab_CustomData;
	
	@FindBy(id = "btn_SaveCustomFieldValues")
    WebElement btn_SaveCustomFieldValues;
	
	@FindBy(id = "btn_EditCustomFieldValues")
    WebElement btn_EditCustomFieldValues; 
	
	@FindBy(id = "LM453")
    WebElement tab_Task; 
	
	@FindBy(id="LM61")
	WebElement tab_PhoneLog;
	
	@FindBy(id = "LM373")
    WebElement tab_CommunicationSummaryCase;
	
	@FindBy(id = "LM35")
	WebElement tab_AppointmentActivity;
	
	@FindBy(id = "LM31")
	WebElement tab_Notes;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00")
	WebElement tbl_CaseList;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    WebElement opt_EqualTo;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_case_id")
	WebElement icon_CaseIdFilter;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_case_id")
	WebElement searchBox_CaseId;
	
	@FindBy(css = "a[class='PgTtlCaseId']+font")
	WebElement txt_CaseId;

	@FindBy(css = "#epr-details-div > a.case-bnf-hdr")
	WebElement txt_CorpName;

	@FindBy(css = "#bnf-details-div > a")
	WebElement txt_ClientName;
	
	@FindBy(css = "#caseManagerReceivingRemainder > span > span.selection > span > ul")
	WebElement txt_ReceivingRemainder;

	@FindBy(css = "#caseManagerSigningPerson > span > span.selection > span > ul")
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
	
	@FindBy(xpath = "//span[contains(text(), 'Choose the type of petition that requires to be processed for the client')]")// Added By Saksham Kapoor on 08/08/2019
	WebElement dropdown_PetitionSearch;
	
	@FindBy(css = "input[value='Add']")
	WebElement btn_AddCase;

	@FindBy(css = "label[id='lbloptionsEmpBased']")
	WebElement chckbox_CorporateClient;

	@FindBy(css = "select[id='inputCountryList']")
	WebElement dropdown_Country;

	@FindBy(css = "select[id='inputSponsor']")
	WebElement dropdown_Sponsor;

	public CM_CaseListPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CaseListPage(WebDriver driver) {
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
			Log.fail("Case list Page did not open up. Site might be down.", driver, true);
		}
	}

	
	public void clickAndAddCase(String workbookNameWrite, String sheetName, String savedClientName, String corpName, String caseManeger, String country, String petition, boolean screenshot) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/10/2019
		 */
		
		Utils.clickElement(driver, btn_AddCase, "Add Case Button");
    
   		Utils.waitForAllWindowsToLoad(2, driver);
   		Utils.switchWindows(driver, "Case Creation", "title", "false");
   		
   		Utils.clickElement(driver, chckbox_CorporateClient, "'Corporate Client' radio button");
       
		Utils.selectOptionFromDropDown(driver, country, dropdown_Country);

		Utils.waitForElement(driver, dropdown_SelectPetitonType);
		Utils.waitForOptionGroupsAvaiable(driver, dropdown_SelectPetitonType);
			
		try {	
			Utils.selectOptionFromDropDown(driver, petition, dropdown_SelectPetitonType);
		} catch (Exception e) {
			Log.message("Option need to be selected by click", driver, screenshot);
			Utils.selectOptionByClick(driver, petition, dropdown_PetitionSearch, txtBox_SearchPetition, "//span[contains(text(),'" + petition + "')]");	
		}

	    Utils.selectOptionFromDropDown(driver, corpName, dropdown_Sponsor);
	   	
	    Utils.waitForElementPresent(driver, "select[id='inputClnt']", "css");
	    Utils.scrollIntoView(driver, dropdown_Client);
	   	Utils.selectOptionFromDropDown(driver, savedClientName, dropdown_Client);
	  
	    //Utils.waitForElementPresent(driver, "//option[contains(text(), '" + savedClientName + "')]", "xpath");
	   	//Utils.selectOptionByClick(driver, savedClientName, dropdown_ClientSearch, txtBox_SearchPetition, "//li[contains(text(),'" + savedClientName + "')]");
   		
   		try
   		{	
   			Utils.waitForElement(driver, txt_ReceivingRemainder);
	   		Utils.scrollIntoView(driver, txt_ReceivingRemainder);
	   		String ALoginCaseManagerReceivingRemainderTxt = txt_ReceivingRemainder.getAttribute("title");
	   		Utils.waitForElement(driver, txt_SigningPerson);
			String ALoginCaseManagerSigningPersonTxt = txt_SigningPerson.getAttribute("title");
			
			Log.message("Fetched Receiving Reminder as " + ALoginCaseManagerReceivingRemainderTxt);
			Log.message("Fetched Signing Person as " + ALoginCaseManagerSigningPersonTxt);
			
			// Write the data in excel
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("ALoginCaseManagerReceivingRemainderTxt", sheetName, "Value", ALoginCaseManagerReceivingRemainderTxt);
			writeExcel.setCellData("ALoginCaseManagerSigningPersonTxt", sheetName, "Value", ALoginCaseManagerSigningPersonTxt);
   			
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
	
	
	public void verifyIfCaseCreated(String attributeNameForCaseId, String country ,String savedClientName, String corpName, String dataWrite, String sheetName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/10/2019
		 */
		
		try {
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
				
				 writeExcel.setCellData(attributeNameForCaseId, sheetName, "Value", caseCreated);
				 writeExcel.setCellData(attributeNameForCaseId, sheetName, "Value", caseCreated);
		
				
				Log.message("Case id created " + caseCreated);

				try {
					Utils.waitForElement(driver, txt_CorpName);
					if (txt_CorpName.getText().contains(corpName)) {
						Log.message("", driver, screenshot);
						Log.pass("Successfully verified the corporation name after case creation " + corpName);

						String directory1 = System.getProperty("user.dir");
						ReadWriteExcel writeExcel1 = new ReadWriteExcel(directory1 + dataWrite);
						writeExcel1.setCellData(attributeNameForCaseId, sheetName, "Value", caseCreated);
					}

					else {
						Log.fail("Unable to verify the corporation name after case creation ", driver, screenshot);
					}

				} catch (Exception e) {
					Log.message("", driver, screenshot);
					Log.fail("Unable to fetch corporation name. Error Message - " + e.getMessage());
				}


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

		} catch (Exception e) {
		    Log.fail("Element case ID is not found. Error - " + e.getMessage(), driver, screenshot);
		}
	}
	
	public void assignCaseToUser(WebDriver driver, String username, boolean screenshot) 
	{
		try
		{	WebElement element1=driver.findElement(By.xpath("//*[@id='btn_AttachRemoveCasemanagerwhosignformsforthisCase']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element1);
			Utils.switchWindows(driver, "INSZoom.com - Add/Remove Case Manager(signing the forms)", "title", "false");
			Utils.waitForElementIfPresent(driver,"//td[contains(text(),'"+username+"')]//parent::tr//following::input[@type='CheckBox']",30,"xpath","Checkbox Visible");
			WebElement element=driver.findElement(By.xpath("//td[contains(text(),'"+username+"')]//parent::tr//following::input[@type='CheckBox']"));
		try
		{
			String value=element.getAttribute("checked");
			Log.message(username+" is already enabled.");
			Utils.clickElement(driver, btn_AddCMSave, "Save Button for Sign Forms");
			Utils.waitForElement(driver, btn_AddCM);
			Utils.waitForElementClickable(driver, btn_AddCM);
		}
		catch(Exception e)
		{
			try
			{
			Log.message(username+" is not enabled");
			Log.message(username+" is now being enabled");
			Utils.clickDynamicElement(driver,"//td[contains(text(),'"+username+"')]//parent::tr//following::input[@type='CheckBox']","xpath","Adding CM to the case");
			Utils.clickElement(driver, btn_AddCMSave, "Save Button for Sign Forms");
			Utils.waitForElement(driver, btn_AddCM);
			Utils.waitForElementClickable(driver, btn_AddCM);
			}
			catch(Exception e1)
			{
				Log.fail("Failed to enable the checkbox"  + e1.getMessage(), driver, true);
			}
		}
		}
		catch(Exception e)
		{
			Log.fail("Unable to add "+username+" to the case. ERROR :\n\n " + e.getMessage());
		}
		
	}
	
	 public void clickOnCaseId(String caseId, boolean screenshot) 
	 {           
	          /*
	          * Created By : M Likitha Krishna
	          * Created On : 14 Oct 2019
	          */ 
	     
	     try {
	    	 Utils.clickElement(driver, txt_ShowAllCase, "Show all cases" + Utils.getPageLoadTime(driver));
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CaseList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all case. ERROR - " + e.getMessage(), driver, screenshot);
		 }
		 
	     Utils.waitForElement(driver, lnk_ShowAllBold);
		 Utils.enterText(driver, searchBox_CaseId, caseId, "in case Id search box");
		 Utils.clickElement(driver, icon_CaseIdFilter, "Case ID");
	          
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CaseList);
			 Log.message("Clicked on EQUALTO filter to search client and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, screenshot);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	        
		 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
		 {
			 Log.fail("Unable to find desired case . Hence can not Proceed Further", driver, true);
		 }
	     
		 Utils.waitForElement(driver, "//a[contains(text(),'"+ caseId +"')]", globalVariables.elementWaitTime, "xpath");
		 WebElement lnk_CaseId = driver.findElement(By.xpath("//a[contains(text(),'"+ caseId +"')]"));
		 Utils.clickElement(driver, lnk_CaseId, caseId);
	    
	 }	
	 
	 public void clickNotesTabInCase(boolean sceenshot) 
	 {
			 /*
	         * Created By : M Likitha Krishna
	         * Created On : 14 Oct 2019
	         */
		 Utils.clickElement(driver, tab_Notes, "Notes tab");
		 
	 }
	 
	 public void clickAppointmentActivityTab(boolean sceenshot) 
	    {
			 /*
	         * Created By : M Likitha Krishna
	         * Created On : 15 Oct 2019
	         */
	          Utils.clickElement(driver, tab_AppointmentActivity, "Appointment Activity tab");
	          
	    }
	
	
	 public void clickCommunicationSummaryInCase(boolean screenshot)  
	 {
	        /*
	         * Created By : M Likitha Krishna
	         * Created On : 14 Oct 2019
	         */ 
		 Utils.clickElement(driver, tab_CommunicationSummaryCase, "Communication Summary in Case");
	 } 
	 
	 
	 public void clickPhoneLogTab()
	 {
		 /*
		  * Modified By: Saksham Kapoor
		  * Modified On: 16/10/2019
		  */
		 
		 Utils.clickElement(driver, tab_PhoneLog, "Phone Log Tab");
	 }
	 
	 
	 public void clickTaskTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 16 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_Task, "Task tab in Case");
	 } 
	 
	 
	 public void verifyCustomDataField(String salary, boolean screenshot) throws Exception 
		{
			/*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 17 Oct 2019
	 		 */
			Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Employee Expected Salary')]/../td[3][contains(text(),'"+ salary +"')]", "xpath", salary, "custom field salary");

		} 
	 

	 public void addCustomDataField(String salary, boolean screenshot) throws Exception 
     {
         /*
          * Modified By : Saurav Purohit
          * Modified On : 7 Sep 2021
          * Added a code to enter Crime info custom fields 
          */
         Utils.clickElement(driver, btn_EditCustomFieldValues, "edit custom field value button");
         Utils.waitForAllWindowsToLoad(2, driver);
         Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
         Utils.waitForElement(driver, "//th/label[contains(text(),'Employee Expected Salary')]/../../td/input", globalVariables.elementWaitTime, "xpath");
         WebElement textBox_Salary = driver.findElement(By.xpath("//th/label[contains(text(),'Employee Expected Salary')]/../../td/input"));
         Utils.enterText(driver, textBox_Salary, salary, "salary");
         Utils.enterText(driver, txtbox_CrimeInfo, "No Crime", "Crime Info mendatory field");
         Utils.selectOptionFromDropDown(driver, "India", dropDown_country);
         Utils.clickElement(driver, btn_SaveCustomFieldValues, "save button");
         Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
     } 
 
	 
	 public void clickCustomDataTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 17 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_CustomData, "Custom Data in Case");
	 }  
	 
	 
	 public void clickClientProfileTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 18 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_ClientProfileTab, "Client Profile tab in Case");
	 } 
	 
	 
	 public void clickReceiptNumberTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 18 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_ReceiptNumberTab, "Receipt Number Tab tab in Case");
	 } 
	 
	 public void clickProfileTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 18 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_ProfileTab, "Profile tab in Case");
	 } 

	 public void clickBlanketApplicationTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 18 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_BlanketApplicationTab, "BlanketApplication in Case");
	 } 

  
	 public void clickJobDetails(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 18 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_JobDetails, "Job Details in Case");
	 }  
 
	 public void clickSnapShotTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_SnapShot, "Snap Shot in Case");
	 }  
	 
	 public void verifySnapShotDetails(String clientName) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Case Information Report", "title", "false");
		 Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Client')]/../td[contains(text(),'"+ clientName +"')]", "xpath", clientName, "client name in snap shot");
		 Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
	 }

	 public void clickManagersContactsTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_ManagersContacts, "Managers/Contactst in Case");
	 }  

	 public void clickDetailsDatesTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_DetailsDates, "Details/Dates tab in Case");
		 Utils.waitForPageLoad(driver);
	 }  
	 
	 public void clickServiceCenterInfoTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_ServiceCenterInfo, "Service center info tab in Case");
	 }  
	 
	 public void clickStatusDocsTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 25 Oct 2019
		  */ 
		 Utils.clickElement(driver, tab_StatusDocs, "Status Docs tab in Case");
	 }  
	
	 
	 public void clickDocsCheckListOrDocumentsTab(boolean screenshot) throws Exception  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 04 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_DocsCheckListOrDocuments, "Docs Check list tab in Case");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Document - INSZoom.com", "title", "false");
	 }  
	 
	 
	 public void clickEmailsTab() throws Exception  
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 11 Nov 2019
		  */ 
		 
		 Utils.clickElement(driver, tab_Emails, "Emails tab in Case");

	 }  
	 
	 
	 public void clickStatusRemindersStepsTab(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 05 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_StatusRemindersSteps, "Status and reminders/steps tab in Case");
	 }  
	 
	 
	
	 public void clickChangeStatusLink(boolean screenshot)  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 07 Nov 2019
		  */ 
		 Utils.clickElement(driver, lnk_changeStatus, "change status link");
	 } 
	 
	 public void changeAndVerifyCaseStatus(String status) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 07 Nov 2019
		  */ 
		 clickChangeStatusLink(true);
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Update Case Status", "title", "false");
		 
		 Utils.selectOptionFromDropDown(driver, status, dropDown_CaseStatus);
		 
		 Utils.clickElement(driver, btn_saveCaseStatus, "Save Case Status Button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 
		 Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false"); 
		 
		 Utils.waitForElement(driver, txt_CaseStatus);
		 String caseStatus = txt_CaseStatus.getText().trim().toString();
		 
		 if(caseStatus.contains(status))
			 Log.pass("Change case status", driver, true);
		 else
			 Log.fail("Unable to change case status", driver, true);
		 
	 }
	 
	 public void clickLetterMSWordTab() throws Exception  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_LetterMSWord, "letter MS Word tab");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
	 } 
	 
	 public void clickDistrictOfficeInfoTab()  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_DistrictOfficeInfo, "district office info tab");
	 } 
	 
	 public void clickFormsTab() throws Exception  
	 {
		 /*
		  * Modified By : Saurav purohit
		  * Created On : 10 Aug 2021
		  * Added Logic to update Sponser Detail 
		  */ 
		 Utils.clickElement(driver, tab_Forms, "forms tab");
		 
		 if(driver.getWindowHandles().size() > 1)
		 {
			Utils.switchWindows(driver, "INSZoom.com::Data Difference Alert Message", "title", "false");
			
			Utils.waitForElement(driver, lnk_SponserChange);
			
			Utils.clickElement(driver, lnk_SponserChange, "Sponser DetailChange Link");
			
			Utils.waitForElement(driver, btn_Update);
			
			Utils.clickElement(driver, btn_Update, "Update Sponser Detail Button");
			
			Utils.waitForAllWindowsToLoad(1, driver);
			
			Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
	
		 }

		 
	 } 
	 
	 	 public void clickQuestionnairesTab()  
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 15 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_Questionnaires, "Questionnaires tab in Case");
	 }  
	 	 
	 public void clickToDoTab() throws Exception  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 30 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_ToDo, "To Do tab");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - To-Do", "title", "false");
	  } 	 
 

	 public void addLCADetails(String LCANumber, String validFrom, String validThru, String totalCount, String jobTitle, String address, String city, String country, String state, String zip, String wages , String agent,String trackNumber, String publishedYear, String effectiveOn, String effectiveTill) throws Exception
	 {
		Utils.waitForElementPresent(driver, "btn_AddCorp.newLCAinfo.", "id");
		Utils.scrollToElement(driver, btn_addLCA);
		 
		Utils.clickElement(driver, btn_addLCA, "add LCA button"); 
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add LCA Details", "title", "false");
		
		Utils.enterText(driver, textBox_LCANumber, LCANumber, "LCA number");
		
		Utils.enterText(driver, textBox_LCAValidFrom, validFrom, "valid from date");
		
		Utils.enterText(driver, textBox_LCAValidThru, validThru, "valid thru date");
		
		Utils.enterText(driver, textBox_LCAtotalCount, totalCount, "total count");
		
		Utils.enterText(driver, textBox_LCAJobTitle, jobTitle, "job title");
		
		Utils.enterText(driver, textBox_LCAAddress, address, "address");
		
		Utils.enterText(driver, textBox_LCACity, city, "city");
		
		Utils.enterText(driver, textBox_LCACountry, country, "country");
		
		Utils.enterText(driver, textBox_LCAState, state, "state");
		
		Utils.enterText(driver, textBox_LCAZipCode, zip, "zip");
		
		Utils.enterText(driver, textBox_LCAWages, wages, "wages");
		
		Utils.enterText(driver, textBox_LCAAgent, agent, "agent");
		
		Utils.enterText(driver, textBox_LCATrackNumber, trackNumber, "tracking number");
		
		Utils.clickElement(driver, checkBox_LCAWageLevel, "wage level");
			
		Utils.clickElement(driver, checkBox_LCAWagePer, "wage per");
		
		Utils.clickElement(driver, checkbox_prevailingWageSource, "OES in Prevailing Wage Source");
		
		Utils.enterText(driver, textBox_LCAPublishedYear, publishedYear, "published year");
		
		Utils.enterText(driver, textBox_LCAEffectiveOn, effectiveOn, "effective on");
		
		Utils.enterText(driver, textBox_LCAEffectiveTill, effectiveTill, "effective till");
		
		Utils.clickElement(driver, btn_SaveLCADetails, "save button");
		
		Utils.selectOptionFromDropDownByVal(driver, "1", dropDown_LCAGroup);
		
		Utils.clickElement(driver, btn_saveLCAGroup, "save LCA group");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'LCA Number')]/../td[contains(text(),'"+ LCANumber +"')]", "xpath", LCANumber, "LCA number");
		
	 }
	 
	 
	 
	 public void addVisaPriorityDateInfo(String priorityDate, String country, String visaCategory) throws Exception  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 17 Dec 2019
		  */ 
		 
		 Utils.waitForElementPresent(driver, "btn_EditprioritydatedetailsAOSdatedetails", "id");
		 Utils.scrollToElement(driver, btn_editVisaPriorityDate);
		 Utils.clickElement(driver, btn_editVisaPriorityDate, "edit visa priority date");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Priority Dates", "title", "false");
		 
		 Utils.enterText(driver, textBox_PriorityDate, priorityDate, "priority date");
		 
		 Utils.selectOptionFromDropDown(driver, country , dropDown_countryOfChargability);
		 
		 Utils.selectOptionFromDropDown(driver, visaCategory, dropDown_visaCategory);
		 
		 Utils.clickElement(driver, btn_saveVisaPriorityDetails, "save button");
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
		 
		 Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Visa Category')]/../td[contains(text(),'"+ visaCategory +"')]", "xpath", visaCategory, "visa category");
		
	  } 
	 
	 
	 public void changeCaseStatusToApproved(String caseId, String status, String approvedOn , String approvalTill) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 14 Jan 2019s
		  * Modification by : Yatharth Pandya
		  * Modification on : 10th Oct, 2020
		  * Modification : Handled one popup after changing case status to approved
		  */
		 
		 clickDetailsDatesTab(true);
		 Utils.clickElement(driver, btn_EditCasedetails, "edit");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
		 
		 Utils.selectOptionFromDropDown(driver, status, dropDown_caseStatus);
		 
		 if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
		 } 
		 else {
				System.out.println("alert was not present");
		 }
		 
		 Utils.enterText(driver, textBox_ApprovedOn, approvedOn, "approved on");
		 
		 Utils.enterText(driver, textBox_ApprovalValidFrom, approvedOn, "approval from");
		 
		 Utils.enterText(driver, textBox_ApprovalTill, approvalTill, "approval Till");
		 
		 Utils.clickElement(driver, btn_SaveCasedetails, "save");
		 
		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
		 
		 Utils.waitForPageLoad(driver, "Chrome");
		 
		 if(status == "Approved")
		 {
//			 Thread.sleep(10000);
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Case Access Rights & Reminders", "title", "false");
			 
			 Utils.clickElement(driver, btn_closeCaseAcessRightsPopUp, "Cancel Case acess rights pop up");

			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
			 
			 
		 }
		 
		 clickProfileTab(true);
		 
		 Utils.waitForPageLoad(driver, "Chrome");
		 		 
		 
	 }
	 
	 
//	 public void changeCaseStatusToApproved(String caseId, String status, String approvedOn , String approvalTill) throws Exception
//	 {
//		 /*
//		  * Created By : M Likitha Krishna
//		  * Created On : 14 Jan 2019s
//		  * Modification by : Yatharth Pandya
//		  * Modification on : 18th August, 2020
//		  * Modification : Page title change due to application change
//		  */
//		 
//		 clickDetailsDatesTab(true);
//		 Utils.clickElement(driver, btn_EditCasedetails, "edit");
//		 
//		 Utils.waitForAllWindowsToLoad(2, driver);
//		 Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
//		 
//		 Utils.selectOptionFromDropDown(driver, status, dropDown_caseStatus);
//		 
//		 if (Utils.isAlertPresent(driver)) {
//				driver.switchTo().alert().accept();
//		 } 
//		 else {
//				System.out.println("alert was not present");
//		 }
//		 
//		 Utils.enterText(driver, textBox_ApprovedOn, approvedOn, "approved on");
//		 
//		 Utils.enterText(driver, textBox_ApprovalValidFrom, approvedOn, "approval from");
//		 
//		 Utils.enterText(driver, textBox_ApprovalTill, approvalTill, "approval Till");
//		 
//		 Utils.clickElement(driver, btn_SaveCasedetails, "save");
//		 
//		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
//		 
//	 }
	 
	 
	 public void changeCaseStatusToClosed(String caseId, String status) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 14 Jan 2019
		  * Modification by : Yatharth Pandya
		  * Modification on : 18th August, 2020
		  * Modification : Page title change due to application change
		  */
		 
		 clickDetailsDatesTab(true);
		 Utils.clickElement(driver, btn_EditCasedetails, "edit");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
		 
		 Utils.selectOptionFromDropDown(driver, status, dropDown_caseStatus);
		 
		 Utils.clickElement(driver, btn_SaveCasedetails, "save");
		 
		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
		 
	 }
	 
	 
	 public void editCustomDataCase(String field, String data) throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 17/02/2020
		 */
		Utils.clickElement(driver, btn_EditCustomFieldInfoForCase, "edit custom field button in case");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
		//Utils.waitForElement(driver, btn_SaveCustomFieldValues);
		Utils.waitForElement(driver, "//th/label[contains(text(),'"+field+"')]/../following-sibling::td/input", globalVariables.elementWaitTime, "xpath");
		WebElement textBox_fieldData = driver.findElement(By.xpath("//th/label[contains(text(),'"+field+"')]/../following-sibling::td/input"));
		Utils.enterText(driver, textBox_fieldData, data, "custom field text box");
		Utils.clickElement(driver, btn_SaveCustomFieldValues, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
	}
	 
	 public void addProposedJobDetails(String city, String country, String supervisorTitle, String noOfSubOrdinates, String proposedJobTitle, String jobDuties, String jobType, String socCode, String noOfHoursPerWeek, String qualification, String majorFieldOfStudy, String experienceInJobOfferedYears, String experienceInJobOfferedMonths, String experienceInRelatedOccupationYears, String experienceInRelatedOccupationMonths, String relatedOccupation) throws Exception
	 {
		/*
		 * Created By: Likitha Krishna
		 * Created On: 17/02/2020
		 */
		 Utils.clickElement(driver, btn_EditProposedJobDetails, "edit proposed job details");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Edit Proposed Job Info", "title", "false");
		 Utils.enterText(driver, textBox_proposedJobDetailsCity, city, "city");
		 Utils.selectOptionFromDropDown(driver, country, dropDown_proposedJobDetailsCountry);
		 Utils.enterText(driver, textBox_supervisorTitle, supervisorTitle , "supervisor title");
		 Utils.enterText(driver, textBox_noOfSubOrdinates, noOfSubOrdinates, "no of subordinates");
		 Utils.enterText(driver, textBox_proposedJobTitle, proposedJobTitle, "proposed job title");
		 Utils.enterText(driver, textBox_jobDuties, jobDuties, "job duties");
		 Utils.selectOptionFromDropDown(driver, jobType, dropDown_jobType);
		 Utils.enterText(driver, textBox_SOCcode, socCode, "SOC Code");
		 Utils.enterText(driver, textBox_noOfHoursPerWeek, noOfHoursPerWeek, "no of hours per week");
		 Utils.enterText(driver, textBox_qualification, qualification, "qualification");
		 Utils.enterText(driver, textBox_majorFieldOfStudy, majorFieldOfStudy, "major field of study");
		 Utils.enterText(driver, textBox_experienceInJobOfferedYears, experienceInJobOfferedYears, "experience In Job Offered Years");
		 Utils.enterText(driver, textBox_experienceInJobOfferedMonth, experienceInJobOfferedMonths, "experience In Job Offered Months");
		 Utils.enterText(driver, textBox_ExperienceInRelatedOccupationYears, experienceInRelatedOccupationYears, "experience In Related Occupation Years");
		 Utils.enterText(driver, textBox_ExperienceInRelatedOccupationMonths, experienceInRelatedOccupationMonths, "experience In Related Occupation Months");
		 Utils.enterText(driver, textBox_relatedOccupation, relatedOccupation, "related occupation");
		 Utils.clickElement(driver, btn_SaveProposedJobInfo, "save button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Proposed Job Info", "title", "false");
	 }

	 
	  public void clickLetterHTMLTab()
	  {
		/*
		 * Created By: Likitha Krishna
		 * Created On: 13/04/2020
		 */
		  Utils.clickElement(driver, tab_LetterHTML, "letter HTML tab");
	  }
	
	  public void clickContractsTab() throws Exception
	  {
		/*
		 * Created By: Likitha Krishna
		 * Created On: 13/04/2020
		 */
		  Utils.clickElement(driver, tab_Contacts, "contracts tab");
		  
		  Utils.waitForAllWindowsToLoad(2, driver);
		  Utils.switchWindows(driver, "DocuSign - INSZoom.com", "title", "false");
	  }
	  
	  
	  public void clickAddCase()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2019
			  */ 
			 Utils.clickElement(driver, btn_AddCase, "Add Case Button");
		 }  
 
	  public void verifyCaseCreationPage() throws Exception 
		{
		
	        /*
	         * Created By :Sauravp
	         * Created On : 26 Feb 2020
	         */ 
			try{
			

			Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "Case Creation", "title", "false");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "List", "title", "false");
			
			Utils.waitUntilLoaderDisappear(driver);

		    }catch(Exception e){
			 Log.failsoft("Verification of Case Creation Page Failed ", driver);

			
		    }

		}
	  
	  
	  public void clickProposedJobEdit(boolean screenshot)  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver, btnProposedJob, "Proposed Job Edit button");
		 }  
	    
	    public void verifyEditProposedJobInfoPage() throws Exception 
		{
		
	        /*
	         * Created By :Sauravp
	         * Created On : 26 Feb 2020
	         */ 
			try{
			

			Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "INSZoom.com - Edit Proposed Job Info", "title", "false");
			
			Utils.softVerifyPageTitle(driver, "INSZoom.com - Edit Proposed Job Info");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "INSZoom.com - Proposed Job Info", "title", "false");

		    }catch(Exception e){
			 Log.failsoft("Verification of Edit Proposed Job Info Failed ", driver);

			
		    }

		}
	    
	    
	    public void clickBlanketApplicants()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver, tabBlanketApplicants, "Blanket applicants Tab");
		 }  
	    
	    
	    public void verifyCaseBlanketApplicantsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Blanket Applicants List");
		    }catch(Exception e){
			 Log.failsoft("Verification of Case Blanket Applicants List page failed", driver);
		    }

	    }
	    
	    public void clickAddNewBlanketApplicants()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver, btnAddNewBlanketApplicants, "Add New Button");
		 }  
	    
	    
	    public void verifyAddCaseBlanketApplicantsListPage() throws Exception 
	   	{
	   	
	           /*
	            * Created By :Sauravp
	            * Created On : 26 Feb 2020
	            */ 
	   		try{
	   		

	   		Utils.waitForAllWindowsToLoad(2, driver);

	   		Utils.switchWindows(driver, "INSZoom.com - Add Case Blanket Applicants", "title", "false");
	   		
	   		Utils.softVerifyPageTitle(driver, "INSZoom.com - Add Case Blanket Applicants");

	   		driver.close();

	   		Utils.waitForAllWindowsToLoad(1, driver);

	   		Utils.switchWindows(driver, "INSZoom.com - Case Blanket Applicants List", "title", "false");

	   	    }catch(Exception e){
	   		 Log.failsoft("Verification of Add Case Blanket Applicants Page Failed ", driver);

	   		
	   	    }

	   	}
	    
	    
	    
	    public void clickSnapShotTab()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver, tabSnapShots, "SnapShots tab");
		 }
	    
	    
	    public void verifyCaseInformationReportPage() throws Exception 
	   	{
	   	
	           /*
	            * Created By :Sauravp
	            * Created On : 26 Feb 2020
	            */ 
	   		try{
	   		

	   		Utils.waitForAllWindowsToLoad(2, driver);

	   		Utils.switchWindows(driver, "INSZoom.com - Case Information Report", "title", "false");
	   		
	   		Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Information Report");

	   		driver.close();

	   		Utils.waitForAllWindowsToLoad(1, driver);

	   		Utils.switchWindows(driver, "INSZoom.com - Case Blanket Applicants List", "title", "false");

	   	    }catch(Exception e){
	   		 Log.failsoft("Verification of Case Information Report Page Failed ", driver);

	   		
	   	    }

	   	}
	    
	     
	    
	    public void verifyDetailsAndDatesPage() 
	   	{
	   		 /*
	   		  * Created By : Saurav Purohit
	   		  * Created On : 26 Feb 2020
	   		  */
	   	    try{
	   		 
	   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - View Case Details");
	   	    }catch(Exception e){
	   		 Log.failsoft("Verification of INSZoom.com - View Case Details page failed", driver);
	   	    }

	     }
	    
	    
	    public void clickDetailsDatesEdit()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver, btn_EditCasedetails, "Edit Casedetails Button");
		 }
	    
	     
		 
		 
		 public void verifyEditCaseDetailsPage() throws Exception 
		   	{
		   	
		           /*
		            * Created By :Sauravp
		            * Created On : 26 Feb 2020
		            */ 
		   		try{
		   		

		   		 Utils.waitForAllWindowsToLoad(2, driver);
		   		 
		   		 Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
		   		
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com- Edit Case Details");

		   		 driver.close();

		   		 Utils.waitForAllWindowsToLoad(1, driver);

		   		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");

		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of Edit Case Details Page Failed ", driver);

		   		
		   	    }

		   	}
		 
		 
		 
		
		 public void clickVisaPriorityDatesEdit()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btnVisaPriorityDateInfoEdit, "Visa priority date Edit Button");
		 }
		 
		 
		 public void verifyEditPrioritydatesPage() throws Exception 
		   	{
		   	
		           /*
		            * Created By :Sauravp
		            * Created On : 26 Feb 2020
		            */ 
		   		try{
		   		

		   		 Utils.waitForAllWindowsToLoad(2, driver);
		   		 
		   		 Utils.switchWindows(driver, "INSZoom.com - Edit Priority Dates", "title", "false");
		   		
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Edit Priority Dates");

		   		 driver.close();

		   		 Utils.waitForAllWindowsToLoad(1, driver);

		   		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");

		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of Edit Priority dates Page Failed ", driver);

		   	    }

		   	}
		 
		
		 
		 public void verifyCaseManagerInfoPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case managers");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - Case managers page failed", driver);
		   	    }

		     }
		 
		 public void clickAttachRemoveSigningCaseManager()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btnAddRemoveCaseManager, "Add/Remove Case Manager");
		 }
		 
		 
		 
		 public void verifyAddRemoveCaseManagerPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   	    	 Utils.waitForAllWindowsToLoad(2, driver);
			   		 
			   		 Utils.switchWindows(driver, "INSZoom.com - Add/Remove Case Managers(signing the forms)", "title", "false");
			   		
			   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Add/Remove Case Managers(signing the forms)");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - Case managers", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of INSZoom.com - INSZoom.com - Case managers page failed", driver);
		   		 
		   	    }

		     }
		 
		 public void verifyStatusDocumentsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Status Documents");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - INSZoom.com - Status Documents page failed", driver);
		   	    }

		     }
		 
		 public void clickAttachRemoveStatusDocs()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btnAttachRemoveStatusDocs, "Attach/Remove Status Docs Button");
		 }
		 
		 
		 
		 
		 public void verifyAttachRemoveStatusDocsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   	    	 Utils.waitForAllWindowsToLoad(2, driver);
			   		 
			   		 Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
			   		
			   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Attach/Remove Status Documents");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - Status Documents", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of INSZoom.com - Attach/Remove Status Documents page failed", driver);
		   		 
		   	    }

		     }
		 
		 
		 public void verifyViewCustomFiledsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - View Custom Fields");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - View Custom Fields page failed", driver);
		   	    }

		     }
		 
		 
		 
		 
		 public void clickEditCustomfields()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btnEditCustomFields, "Edit custom fields Button");
		 }
		 
		 
		 
		 
		 public void verifyEditCustomFieldsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   	    	 Utils.waitForAllWindowsToLoad(2, driver);
			   		 
			   		 Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
			   		
			   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Edit Custom Field");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of INSZoom.com - Edit Custom Field page failed", driver);
		   		 
		   	    }

		     }
		 
		 public void clickAttachRemoveCustomfields()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btnAttachRemoveCustomFields, "Attach/Remove Button");
		 }
		 
		 
		 
		 
		 public void verifyAttachRemoveCustomfieldsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   	    	 Utils.waitForAllWindowsToLoad(2, driver);
			   		 
			   		 Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Custom Fields", "title", "false");
			   		
			   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Attach/Remove Custom Fields");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of INSZoom.com - Attach/Remove Custom Fields failed", driver);
		   		 
		   	    }
		   	}
		 
		 
		 public void clickCopyCustomfields()  
		 {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */ 
			 Utils.clickElement(driver,  btn_CopyCustomFields, "Copy Custom Fields");
		 }
		 
		 
		 
		 
		 public void verifyCopyCustomFieldsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		 
		   	    	 Utils.waitForAllWindowsToLoad(2, driver);
			   		 
			   		 Utils.switchWindows(driver, "INSZoom.com - Copy Custom Field", "title", "false");
			   		
			   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Copy Custom Field");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of INSZoom.com - Copy Custom Fields failed", driver);
		   		 
		   	    }
		   	}
		 
		 public void verifyDocumentsPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 26 Feb 2020
		   		  */
		   	    try{
		   		
			   		 Utils.softVerifyPageTitle(driver, "Document - INSZoom.com");

			   		 driver.close();

			   		 Utils.waitForAllWindowsToLoad(1, driver);

			   		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
		   	    }catch(Exception e){
		   	    	
		   		 Log.failsoft("Verification of Document - INSZoom.com", driver);
		   		 
		   	    }
		   	}
		 
		 
		 
		 
		 public void verifyFormsListPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 3rdMarch Feb 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - List Of Case Forms");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - List Of Case Forms page failed", driver);
		   	    }

		     }
		 
		 public void verifyStatusReminderPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 3rdMarch Feb 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates page failed", driver);
		   	    }

		     }
		 
		 
		 
		 public void verifyCommunicationSummaryPage() 
		   	{
		   		 /*
		   		  * Created By : Saurav Purohit
		   		  * Created On : 3rdMarch 2020
		   		  */
		   	    try{
		   		 
		   		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Client Case Communication Summary");
		   	    }catch(Exception e){
		   		 Log.failsoft("Verification of INSZoom.com - Client Case Communication Summary page failed", driver);
		   	    }

		     }
		 
		 public void clickShippingMailingLogTab()
		 {
			 /*
	   		  * Created By : Likitha Krishna
	   		  * Created On : 03 August 
	   		  */ 
			 Utils.clickElement(driver, tab_ShippingMailingLog, "Shipping/Mailing Log Tab");
		 }
	  
	  
		 public void addShippingDetails(String date, String shipmentMethod, String shipmentReason, String trackingNumber,String extranetAccess, String cost, String comment) throws Exception
		 {
			 /*
	   		  * Created By : Likitha Krishna
	   		  * Created On : 03 August 
	   		  */ 
			 Utils.clickElement(driver, btn_addShippigMailingLog, "Add new ");
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Add New shipping/mailing info", "title", "false");
			 Utils.enterText(driver, textBox_shipmentDate, date, "shippment date as "+date);
			 Utils.selectOptionFromDropDown(driver, shipmentMethod, dropDown_shipmentMethod);
			 Utils.selectOptionFromDropDown(driver, shipmentReason, dropDown_shipmentReason);
			 Utils.enterText(driver, textBox_trackingNumber, trackingNumber, "Tracking number");
			 Utils.selectOptionFromDropDown(driver, extranetAccess, dropDown_ExtranetAccess);
			 Utils.enterText(driver, textBox_shipmentCost, cost, "cost as "+cost);
			 Utils.enterText(driver, textBox_shipmentComment, comment, "comment as "+comment);
			 Utils.clickElement(driver, btn_saveShippingDetails, "save button");
			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Shipping/Mailing Log", "title", "false");
		 }
	  
		 
		 public void changeCaseStatus(String status) throws Exception
		 {
			 /*	Function  - Has to be used specifically for the orgs which are updated to new Tech Stack
			  * Created By : Yatharth Pandya
			  * Created On : 18th August, 2020
			  * Modified by Nitisha Sinha on 16th September 2020
			  * Modification done: changed function name and added change to approved logic
			  */
			 
			 clickDetailsDatesTab(true);
			 Utils.clickElement(driver, btn_EditCasedetails, "edit");
			 
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchToNewWindow(driver);
//			 Utils.switchWindows(driver, "Edit Case Details - INSZoom.com", "title", "false");
			 
			 Utils.selectOptionFromDropDown(driver, status, dropDown_caseStatus1);
			 
			 if(status == "Approved")
			 {
				 Thread.sleep(10000);
				 Utils.waitForElement(driver, textBox_ApprovedDate);
				 Utils.scrollToElement(driver, textBox_ApprovedDate);
				 Utils.waitForElementClickable(driver, textBox_ApprovedDate);
				 Utils.enterText(driver, textBox_ApprovedDate, "08/13/2020", "approved on"); 
				
			 }
			 
			 Utils.clickElement(driver, btn_SaveCasedetails1, "save");
			 
			 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
			 
			 Utils.waitForPageLoad(driver, "Chrome");
			 
			 if(status == "Approved")
			 {
				 Thread.sleep(10000);
				 Utils.waitForAllWindowsToLoad(2, driver);
				 Utils.switchWindows(driver, "INSZoom.com - Case Access Rights & Reminders", "title", "false");
				 
				 Utils.clickElement(driver, btn_closeCaseAcessRightsPopUp, "Cancel Case acess rights pop up");

				 Utils.waitForAllWindowsToLoad(1, driver);
				 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
				 
				 
			 }
			 
			 clickProfileTab(true);
			 
			 Utils.waitForPageLoad(driver, "Chrome");
			 
		 }
		 
		 public void verifyAppointmentPresent(String appointmentDescription)
		 {
			 //Created by Yatharth Pandya
			 //Created on 24th August 2020
			 
//			 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + "\u00A0" + appointmentDescription + "')]", "xpath", appointmentDescription, "Appointments");
			 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'${nbsp}"+appointmentDescription+"')]", "xpath", appointmentDescription, "Appointments");	
		 }
	  
		 public void editFileNumber(String fileNumber, String caseDescription) throws Exception         
	     {
	         /*
	             * Edited By : Souvik Ganguly
	             * Edited On : 7 September
	             * Added case description edit method
	             */
	         Utils.clickElement(driver, btn_EditCasedetails, "edit button");
	         Utils.waitForAllWindowsToLoad(2, driver);
	         Utils.switchWindows(driver, "INSZoom.com- Edit Case Details", "title", "false");
	         Utils.enterText(driver, textBox_FileNumber, fileNumber, "file number");
	         Utils.enterText(driver, textbox_CaseDescription, caseDescription, "case description");
	         Utils.clickElement(driver, btn_SaveCasedetails, "save");
	         Utils.waitForAllWindowsToLoad(1, driver);
	         Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
	         Utils.verifyIfDataPresent(driver, "//td[contains(text(),'File Number')]/..//td[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");   
	         Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Case Description')]/..//td[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "case Description");   
	     }
	     
	        
     public void clickOnFirstCaseOnList() throws Exception
     {
         /*
             * Created By : Likitha Krishna
             * Created On : 19 August
             */
         Utils.waitForElement(driver, table_caseResult);
         Utils.clickDynamicElement(driver, "//tr[@id='ctl00_MainContent_ctl00_RadGridList_ctl00__0']//a[contains(text(),'KB')]", "xpath", "first case");
     }

     
     public void clickAndverifyFormsListPage() 
	   	{
	   		 /*
	   		  * Created By : Saurav Purohit
	   		  * Created On : 17thAug 2020
	   		  */
	   	    try{
	   	    	
	   	     Utils.clickElement(driver, tab_Forms, "forms tab");	
	   	     Utils.waitForAllWindowsToLoad(2, driver);

	   		 Utils.switchWindows(driver, "Form - INSZoom.com", "title", "false");
	   		 Utils.softVerifyPageTitle(driver, "Form - INSZoom.com");
	   		 driver.close();
	   		 Utils.waitForAllWindowsToLoad(1, driver);

	   		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");
	   	    }catch(Exception e){
	   		 Log.failsoft("Verification of  INSZoom.com - View Custom Fields failed", driver);
	   	    }

	     }
     
     
     public void verifyAddCaseButtonPresent(boolean value)
	 {
		 /*
		  * Created by Kuchinad Shashank
		  * Created on 06th August 2020
		  */
		 
		 if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "input[value='Add']", "css", "Add Case Button", "Case List Page");
		 }
		 else
		 {
			 Utils.verifyIfDataNotPresent(driver, "input[value='Add']", header_caseList, "css", "Add Case Button", "Case List Page");
		 }
	 }
	 
	  
	 public void verifyDeleteIconAccess(boolean value, String caseId)
	 {
		 /*
		  * Created by Kuchinad Shashank
		  * Created on 06th August 2020
		  */
		 
		 try {
	    	 Utils.clickElement(driver, txt_ShowAllCase, "Show all cases" + Utils.getPageLoadTime(driver));
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CaseList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all case. ERROR - " + e.getMessage(), driver, true);
		 }
		 
	     Utils.waitForElement(driver, lnk_ShowAllBold);
		 Utils.enterText(driver, searchBox_CaseId, caseId, "in case Id search box");
		 Utils.clickElement(driver, icon_CaseIdFilter, "Case ID");
	          
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CaseList);
			 Log.message("Clicked on EQUALTO filter to search client and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	        
		 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
		 {
			 Log.fail("Unable to find desired case . Hence can not Proceed Further", driver, true);
		 }
		 
		 String ownCaseID = "//a[contains(text(),'" + caseId + "')]/../preceding-sibling::td//a[@title='Delete']";
		 
		 if(value)
		   {
			 	Utils.verifyIfDataPresent(driver, ownCaseID, "xpath", "Delete Icon", "Case List Page");
				
		   }
		   else
		   {
			   Utils.verifyIfDataNotPresent(driver, ownCaseID, header_caseList, "xpath", "Delete Icon", "Case List Page");
			   
		   }
	 }
	 
	 
	 public void clickAndAddCaseOnStandardEdition(String workbookNameWrite, String sheetName, String savedClientName, String corpName, String caseManeger, String country, String petition, boolean screenshot) throws Exception
		{
			/*
			 * Modified By: Kuchinad Shashank
			 * Modified On: 19/02/2020
			 * 
			 * NOTE: This is only used for adding case in Standard Edition .
			 */
			
			Utils.clickElement(driver, btn_AddCase, "Add Case Button");
	    
	   		Utils.waitForAllWindowsToLoad(2, driver);
	   		Utils.switchWindows(driver, "Case Creation", "title", "false");
	   		
	   		Utils.clickElement(driver, chckbox_CorporateClient, "'Corporate Client' radio button");

			Utils.enterText(driver, txtBox_petitionDescriptionForStandardEdition, petition, "Petition Description");

		    Utils.selectOptionFromDropDown(driver, corpName, dropdown_Sponsor);
		   	
		    Utils.waitForElementPresent(driver, "select[id='inputClnt']", "css");
		    Utils.scrollIntoView(driver, dropdown_Client);
		   	Utils.selectOptionFromDropDown(driver, savedClientName, dropdown_Client);
		  
		    //Utils.waitForElementPresent(driver, "//option[contains(text(), '" + savedClientName + "')]", "xpath");
		   	//Utils.selectOptionByClick(driver, savedClientName, dropdown_ClientSearch, txtBox_SearchPetition, "//li[contains(text(),'" + savedClientName + "')]");
	   		
	   		try
	   		{	
	   			Utils.waitForElement(driver, txt_ReceivingRemainder);
		   		Utils.scrollIntoView(driver, txt_ReceivingRemainder);
		   		String ALoginCaseManagerReceivingRemainderTxt = txt_ReceivingRemainder.getAttribute("title");
		   		Utils.waitForElement(driver, txt_SigningPerson);
				String ALoginCaseManagerSigningPersonTxt = txt_SigningPerson.getAttribute("title");
				
				Log.message("Fetched Receiving Reminder as " + ALoginCaseManagerReceivingRemainderTxt);
				Log.message("Fetched Signing Person as " + ALoginCaseManagerSigningPersonTxt);
				
				// Write the data in excel
				String directory = System.getProperty("user.dir");
				ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
				writeExcel.setCellData("ALoginCaseManagerReceivingRemainderTxt", sheetName, "Value", ALoginCaseManagerReceivingRemainderTxt);
				writeExcel.setCellData("ALoginCaseManagerSigningPersonTxt", sheetName, "Value", ALoginCaseManagerSigningPersonTxt);
	   			
	   		}catch(Exception e)
	   		{
	   			Log.message("",driver, screenshot);
				Log.fail("Unable to fetch the Case Manger Signing Person Name. Error - " + e.getLocalizedMessage());
	   		}
	   		
		   	Utils.selectOptionFromDropDown(driver, caseManeger, dropdown_ReceivingReminder);
		   	Utils.selectOptionFromDropDown(driver, caseManeger, dropdown_signingCM);
		   	
		    Utils.clickElement(driver, btn_SaveAddedCase, "Add Case");
		    
		    Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com", "title", "false");
		}
	 
	 
	 public void sendEventEmail(String emailSubject,String questionnaireAttached) throws Exception
	 {
		//Created by : Yatharth Pandya 
		//Created on : 6th Oct,2020
		 
		 Utils.waitForAllWindowsToLoad(3, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Email", "title", "false");
		 Utils.enterText(driver, textbox_emailSubject, emailSubject, "Subject textbox");
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + questionnaireAttached + "')]","xpath", questionnaireAttached, "Email Questionnaire list");
		 Utils.clickElement(driver, btn_sendEmail, "Send Email button");
//		 Utils.waitForAllWindowsToLoad(1, driver); 
		 Utils.switchWindows(driver, "INSZoom.com - View Case Details", "title", "false");
	 }


	 public void clickForms4_0Tab() throws Exception  
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Nov 2019
		  */ 
		 Utils.clickElement(driver, tab_Forms, "forms tab");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Form - INSZoom.com", "title", "false");
			
	 } 
	  
}
