package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
//import java.util.NoSuchElementException;

public class PortalSetup extends LoadableComponent<PortalSetup> 
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "firstName")						//Added by Souvik Ganguly on 26/8/2021
	WebElement txtbox_firstName;
	
	@FindBy(id = "lastName")						//Added by Souvik Ganguly on 26/8/2021
	WebElement txtbox_lastName;
	
	@FindBy(id = "email")						//Added by Souvik Ganguly on 26/8/2021
	WebElement txtbox_email;
	
	@FindBy(id = "editEmail")						//Added by Souvik Ganguly on 26/8/2021
	WebElement txtbox_editEmail;
	
	@FindBy(xpath = "//*[@id='corpUserRole']/div[2]/div/div/span[1]/span/input") //Added by Souvik Ganguly on 26/8/2021
	WebElement comboBox_corpUserRole;
	
	@FindBy(xpath = "//div[@id='newUserModal']//button[contains(text(),'Save')]") //Added by Souvik Ganguly on 26/8/2021
	WebElement btn_Save;
	
	@FindBy(xpath = "//div[@id='editUserModal']//button[contains(text(),'Save')]") //Added by Souvik Ganguly on 26/8/2021
	WebElement btn_editSave;	
	
	@FindBy(xpath = "(//div[@id='KendoActiveCorpUserGrid']//tbody/tr)[last()]")  //Added by Saurav  on 09/08/2020 
    WebElement userlist_UserManagementTab; 
	
	@FindBy(xpath = "//a[contains(text(),'Saved / Shared Reports')]")	//Added by Nitisha Sinha on 05/03/2020 
	WebElement tab_savedSharedReports; 
	
	@FindBy(xpath = "//form[@data-vv-scope='rolesMultiSelectScope']/div")	//Added by Nitisha Sinha on 4/03/2020 
	WebElement dropdown_reportAccess;
	
	@FindBy(xpath = "//div[@id='ReportSearch']/input")		  //Added by Nitisha Sinha on 4/03/2020          
	WebElement txtbox_searchReport;
	
	@FindBy(xpath = "//div[@id='RoleCombo']//div[@class='k-widget k-multiselect k-multiselect-clearable']//input")
	WebElement textbox_selectCorporation;
	
	@FindBy(xpath = "//input[@name='txtSearch']")			//Added by Kuchinad Shashank on 2/3/2020
	WebElement txtbox_headQuarterName;
	
	@FindBy(xpath = "//u[contains(text(),'Headquarter')]")			//Added by Kuchinad Shashank on 2/3/2020
	WebElement lnk_headQuarter;
	
	@FindBy(xpath = "//button[@id = 'saveButton'][contains(text(),' Save Changes')]")		                   
	WebElement btn_saveReportChanges;
	
	@FindBy(xpath = "//div[@type='KendoGridComponent']")		                   
	WebElement tbl_reports;
	
	@FindBy(xpath = "//h4[contains(text(), 'Customize Reports Access')]")		                   
	WebElement tab_customizeReportsAccess;
	
	@FindBy(xpath = "//a[@href='#/Setup']")		                    //Added by Yatharth Pandya on 24/2/2020
	WebElement lnk_portalSetup;
	
	@FindBy(xpath = "//button[@id = 'SaveChanges']")                  //Added by Kuchinad Shashank on 20/02/2020
	WebElement btn_saveCorporationGeneralSettings;                     
	
	@FindBy(xpath = "//button[contains(text(),'Confirm')]")		//Added by Kuchinad Shashank on 20/02/2020
	WebElement btn_ConfirmNoCorporationAccess;

	/*@FindBy(xpath = "//input[@type='file']")		//Added by Kuchinad Shashank on 18/02/2020
	WebElement icon_chooseFile;*/

	@FindBy(xpath = "//a[contains(text(),'Corporation Portal Settings')]") //  	//Added by Yatharth Pandya on 18/02/2020
	WebElement tab_corporationPortalSettings; 
	
	@FindBy(id = "AddUser")						//Added by Yatharth Pandya on 20/2/2020
	WebElement btn_addCorporationUser;
	
	@FindBy(xpath = "//div[@class='col-sm-12 col-md-12']//button[@class='btn btn-zc btn-cancel pull-right mar5']")	//Added by Yatharth Pandya on 20/2/2020
	WebElement btn_closeNewUser;
	
	@FindBy(xpath = "//span[contains(text(),'To make changes, you can request your organisatio')]")			//Added by Yatharth Pandya on 20/2/2020
	WebElement txt_noAccessAlert;
	
	@FindBy(id = "newCorpRoleCancelButton")					//Added by Yatharth Pandya on 20/2/2020
	WebElement btn_cancelNewCorporationRole;

	
	@FindBy( id = "CancelChanges")	//Added by Kuchinad Shashank on 18/02/2020
	WebElement btn_cancelChanges;
	
	@FindBy(xpath = "//button[contains(text(),'Restore Users')]") //Added by Kuchinad Shashank on 18/02/2020
	WebElement btn_restoreUser;
	
	@FindBy(xpath = "//div[@id='deletedUsers']//input[@placeholder='Search User/Role']")  //Added by Kuchinad Shashank on 18/02/2020
	WebElement txtbox_searchDeletedUser;
	
	@FindBy(xpath = "//div[@id='activeUsers']//button")  //Added by Kuchinad Shashank on 18/02/2020
	WebElement btn_menuToggle;
	
	@FindBy(xpath = "//span[contains(text(),'Restore deleted users')]")  //Added by Kuchinad Shashank on 18/02/2020
	WebElement btn_restoreDeletedUsers;
	
	@FindBy(xpath = "//button[contains(text(),'Delete')]")  //Added by Kuchinad Shashank on 18/02/2020
	WebElement btn_deleteUser;
	
	@FindBy(css = "input[placeholder='Select Role']")  //Added by Kuchinad Shashank on 18/02/2020
	WebElement textbox_searchUserByRole;
	
	@FindBy (id = "editLastName" )			//Added by Kuchinad Shashank on 18/02/2020
	WebElement txtbox_lastNameEditUser;
	
	@FindBy(id = "btn_Sendemail")                //Added by Nitisha Sinha on 18/2/2020
	WebElement btn_sendEmail;
	
	@FindBy(xpath = "//span[contains(text(),'Reset User')]")                //Edited by Saurav on 12/07/2021 lebel has changed to span
	WebElement txt_resetUser;
	
	@FindBy(id = "econsentLogDetailsCloseButton")                //Added by Nitisha Sinha on 18/2/2020
	WebElement btn_closeEconsentLog;
	
	@FindBy(xpath = "//span[contains(text(),'e-Consent History')]")                //Edited by Saurav  on 12/07/2021 . lebel has changed to span
	WebElement txt_eConsentHistory;
	
	@FindBy(xpath = "//button[contains(text(),'Reset User')]")                //Added by Nitisha Sinha on 18/2/2020
	WebElement btn_resetUser;
	
	@FindBy(xpath = "//form[@data-vv-scope='DuplicateRole']//input[@placeholder='Title']")     //Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_duplicateRoleTitle;
	
	@FindBy(id = "duplicateCorpRoleSaveButton")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_saveDupliicateRole;
	
	@FindBy(id = "editCorpRoleDuplicateButton")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_duplicateCorpRole; 
	
	@FindBy( xpath = "//div[@id='tabs-epr']//a[contains(text(),'LCA')]")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement tab_LCA;
	
	@FindBy(id = "AddUser")			//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_addUser;
	
	@FindBy (id = "firstName" )		//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_firstNameNewUser;
	
	@FindBy (id = "lastName" )			//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_lastNameNewUser;
	
	@FindBy (id = "email" )				//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_emailNewUser;
	
	@FindBy ( xpath = "//div[@id='corpUserRole']//input[@name='Roles_input']") //Added by Kuchinad Shashank on 28/01/2020
	WebElement txtbox_roleInputForUser;
	
	@FindBy ( xpath = "//div[@id='RoleCombo']//input[@name='Roles_input']")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_roleEditUser;
	
	@FindBy ( xpath = "//div[@id='corpUserRolePortalAccess']//input/..")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement checkbox_portalAccess;
	
	@FindBy ( xpath = "//div[@class='col-sm-12 col-md-12']//button[contains(text(),'Save')]")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_saveNewUser;
	
	@FindBy (xpath = "//div[@id='editUserModal']//button[contains(text(),'Save')]")			//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_saveEditUser;
	
	@FindBy (xpath = "//div[@id='cannotDeletePopUp']//button[@class='btn btn-default']")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_cancelDeletePopUp;
	
	@FindBy (xpath = "//div[@id='cannotDeletePopUp']//div[contains(text(),'System')]")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtheading_deletePopUp;
	
	@FindBy (id="editCorpRoleCancelButton")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_cancelEditCorpRole;
	
	@FindBy ( xpath = "//div[@class='col-lg-8 has-feedback has-search']//input[@id='txtSearch']")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement searchbox_User;
	
	@FindBy ( xpath = "//form[@data-vv-scope='editCorpUser']")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement loader_editUser;
	
	@FindBy (xpath = "//a[@class='custom-portal']")			//Added by Kuchinad Shashank on 14/02/2020
	WebElement tab_customPortalSetting;
	
	@FindBy( xpath = "//div[@class='k-loading-image']")		//Added by Kuchinad Shashank on 14/02/2020
	WebElement loader_tableRole;
	
	@FindBy (xpath = "//input[@placeholder='Search Role/s']" ) //Added by Kuchinad Shashank on 14/02/2020
	WebElement searchbox_RoleTitle;
	
	@FindBy ( xpath = "//input[@placeholder='Search User/Role']") //Added by Kuchinad Shashank on 14/02/2020
	WebElement searchbox_UserName;
	
	@FindBy(xpath = "//h4[contains(text(), 'Role Management')]") //Added by Kuchinad Shashank on 14/02/2020
	WebElement tab_roleManagement;
	
	@FindBy (css = "button[id='editCorpRoleDeleteButton']")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_deleteCorporationRole;
	
	@FindBy (css = "button[class='btn btn-solid-green']")    //Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_confirmDeleteCorporationRole;
	
	@FindBy( xpath = "//span[contains(text(),'No items to display')]")     //Added by Kuchinad Shashank on 14/02/2020
	WebElement waitelement_textHeader;
	
	@FindBy (id = "addRoleButton")				//Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_addNewCorpationRole;			
	
	@FindBy (css = "input[placeholder='Role Name']")	//Added by Kuchinad Shashank on 14/02/2020
	WebElement txtbox_roleTitle;
	
	@FindBy ( css = "button[id='newCorpRoleSaveButton']")  //Added by Kuchinad Shashank on 14/02/2020
	WebElement btn_saveNewCorpationRole;
	
	@FindBy(id = "addRoleButton")                //Added by Nitisha Sinha on 17/2/2020
	WebElement btn_addNewCorporationRole;
	
	@FindBy(id = "roleTitleId")                //Added by Nitisha Sinha on 17/2/2020
	WebElement textbox_roleName;
	
	@FindBy(css = "label[for='allFNAccessRoleSave']")                //Added by Nitisha Sinha on 17/2/2020
	WebElement radioBtn_allClientAccess;
	
	@FindBy(id = "newCorpRoleSaveButton")                //Added by Nitisha Sinha on 17/2/2020
	WebElement btn_saveNewCorporationRole;
	
	@FindBy(css = "input[name='MenuAccess.BNFProfileMenus.BNFCustomData.HasAccess']")                //Added by Nitisha Sinha on 14/2/2020
	WebElement checkbox_customDataClientProfile;
	
	@FindBy(css = "input[name='MenuAccess.BNFProfileMenus.BNFEmails.HasAccess']")                //Added by Nitisha Sinha on 14/2/2020
	WebElement checkbox_emailClientProfile;
	
	@FindBy(css = "input[name='MenuAccess.CaseProfileMenus.CaseEmails.HasAccess']")                //Added by Nitisha Sinha on 14/2/2020
	WebElement checkbox_emailCaseProfile;
	
	@FindBy(css = "input[name='MenuAccess.CaseProfileMenus.CaseReceiptDetails.HasAccess']")                //Added by Nitisha Sinha on 14/2/2020
	WebElement checkbox_receiptDetailsCaseProfile;
	
	@FindBy(xpath = "//strong[contains(text(),'Admin')]/../following-sibling::td[@class='k-command-cell']/a")                //Added by Nitisha Sinha on 14/2/2020
	WebElement icon_editCorporationRoleManagement;
	
	@FindBy(xpath = "//span[@class='k-icon k-i-check']")                //Added by Nitisha Sinha on 14/2/2020
	WebElement icon_saveCorporationRoleManagement;
	
	@FindBy(css = "span[class='k-icon k-clear-value k-i-close']")                //Added by Nitisha Sinha on 12/2/2020
	WebElement icon_clearEConsentForCorp;
	
	@FindBy(xpath = "//div[@id='terms-of-use-tab']/div[3]//button[@id='removeButton']")                //Added by Nitisha Sinha on 12/2/2020
	WebElement btn_removeEConsentCorporation;
	
	@FindBy(id = "SaveButtonForHRRemove")                                   //Added by Nitisha Sinha on 12/2/2020
	WebElement btn_saveRemoveEConsentCorporation;
	
	@FindBy(xpath = "//div[@id='terms-of-use-tab']/div[5]//button[@id='removeButton']")                //Added by Nitisha Sinha on 12/2/2020
	WebElement btn_removeEConsentClient;
	
	@FindBy(id = "SaveButtonForRemove")                                   //Added by Nitisha Sinha on 12/2/2020
	WebElement btn_saveRemoveEConsentClient;
	
	@FindBy(xpath = "//b[contains(text(),'Client')]/../..//div[@class='textBox form-group']")  //Added by Kuchinad Shashank on 12/02/2020
	WebElement txtbox_eConsentMsgFnp;
	
	@FindBy(xpath = "//b[contains(text(),'Corporation')]/../..//div[@class='textBox form-group']")	//Added by Kuchinad Shashank on 12/02/2020
	WebElement txtbox_eConsentMsgHrp;
		
	@FindBy(xpath = "//h4[contains(text(),'Client Portal Menu')]") //Added By Yatharth Pandya on 3/2/2020
	WebElement tab_clientPortalMenu;
	
	@FindBy(id = "CaseVisaPriorityDateInfo") 		//Added By Yatharth Pandya on 03/02/2020
	WebElement checkbox_caseVisaPriorityDateinfo;
	
	@FindBy(xpath = "//label[@for='CaseVisaPriorityDateInfo']") //Added by Yatharth Pandya on 3/2/2020
	WebElement checkbox_caseVisaPriorityDateInfo2;
	
	@FindBy(id = "CaseCustomDetails") 			//Added by Yatharth Pandya on 3/2/2020
	WebElement checkbox_caseCustomDetails;
	
	@FindBy(xpath = "//label[@for='CaseCustomDetails']") // Added by Yatharth Pandya on 3/2/2020
	WebElement checkbox_caseCustomDetails2;
	
	@FindBy(id = "CaseLCADetails")  //Added By Yatharth Pandya on 3/2/2020
	WebElement checkbox_LCADetails;
	
	@FindBy(xpath = "//label[@for='CaseLCADetails']") //Added By Yatharth Pandya
	WebElement checkbox_LCADetails2;
	
	@FindBy(xpath = "//span[contains(text(),'Visa Priority Date Info')]/ancestor::tr/td[@class='text-right']//button")  //Added by Yatharth Pandya on 3/2/2020
	WebElement btn_previewVisaPriorityDateInfo;
	
	@FindBy(xpath = "//span[contains(text(),'Custom Details')]/ancestor::tr/td[@class='text-right']//button") // Added by Yatharth Pandya on 3/2/2020
	WebElement btn_previewCaseCustomDetails;

	@FindBy(xpath = "//h4[contains(text(),'Preview')]/following-sibling::a")  //Added by Kuchinad Shashank
	WebElement icon_closePreview;
	
	@FindBy(id = "editCorpRoleCancelButton")		//Created by Yatharth Pandya on 3/2/2020
	WebElement btn_closePreview;
	
	@FindBy(xpath = "//span[contains(text(),'Visas')]/ancestor::tr/td[@class='text-right']//button") //Added by Kuchinad Shashank
	WebElement btn_previewVisas;																	  //on 05/02/2020
	
	@FindBy(xpath = "//span[contains(text(),'Important Docs / Dates')]/ancestor::tr/td[@class='text-right']//button")
	WebElement btn_previewImpDateAndDocs;
	
	@FindBy(xpath = "//p[contains(text(),'Customized Portal Access')]") // Added by Nitisha Sinha on 31/01/2020
	WebElement tab_customizedPortalAccess;
	
	@FindBy(css = "input[id=BNFImportantDocumentsAndDates]") 	//Added by Kuchinad Shashank 3/02/2020
	WebElement checkbox_importantDateAndDocs;
	
	@FindBy(xpath = "//h4[contains(text(),'General Settings')]")   //Added by Kuchinad Shashank 13/02/2020
	WebElement tab_generalSettings;	
	
	@FindBy(css= "input[id='BNFVisas']")
	WebElement checkbox_Visas;
	
	@FindBy (xpath = "//button[@id = 'saveButton']")          //Created by Nitisha Sinha on 10/02/2020
	WebElement btn_saveClientPortalSettings; 

	@FindBy (xpath = "//button[@id = 'SaveChanges']")          //Created by Kuchinad Shashank on 10/02/2020
	WebElement btn_saveGeneralSettingsClientPortal;
	
	@FindBy(xpath ="//a[contains(text(),'Client Portal Settings')]")	//Added by Kuchinad Shashank on 3/02/2020
	WebElement tab_clientPortalSettings;
	
	@FindBy(xpath = "//b[contains(text(),'Corporation User')]/../..//button[@id='previewButton']") //Added by Kuchinad Shashank on 12/02/2020
	WebElement btn_previewCorporationUserInstruction;
	
	@FindBy(xpath = "//b[contains(text(),'Client User')]/../..//button[@id='previewButton']") //Added by Kuchinad Shashank on 12/02/2020
	WebElement btn_previewClientUserInstruction;
	
	@FindBy(xpath = "//div[@id='previewCorpPopUpData']//a[@id='modalCloseBtn']")		//Added by Kuchinad Shashank on 12/02/2020
	WebElement icon_closeUserInstruction;
	
	@FindBy(id = "ClosePreviewPolicyGuideline")				//Added by Kuchinad Shashank on 12/02/2020
	WebElement btn_closePreviewPolicy;
	
	@FindBy(id = "Cancel")				//Added by Kuchinad Shashank on 12/02/2020
	WebElement btn_closePreviewNews;
	
	@FindBy(id = "SaveButtonForCheckBox")		//Added by Kuchinad Shashank on 12/02/2020
	WebElement btn_confirmCopyEConsent;
	
	@FindBy(xpath="//a[contains(text(), 'Policy')]")
	WebElement tbl_Policy; 
	
	@FindBy(css="div[class='toast-alert-message alert-success success_alert_msg show']")
	WebElement txt_FileUploadMessageAppeared;
	
	@FindBy(xpath="(//button[@id='saveButton'])[2]")
	WebElement btn_saveClientUserInstruction;
	
	@FindBy(xpath="html/body")
	WebElement textBox_clientUserInstructionDescription;
	
	@FindBy(css="input[id='FNTitle']")
	WebElement textBox_clientUserInstructionTitle;
			
	@FindBy(css="li[id='userInstructions']")
	WebElement tab_userInstruction;
	
//	@FindBy(xpath="//div[contains(@class, 'pace-inactive')]")
//	WebElement div_PageLoaded;
	
	@FindBy(css="input[name='CorpTermsOfUse_input']")
	WebElement searchbox_eConsentCorporation;
	
	@FindBy(xpath="//b[contains(text(), 'Corporation Portal')]/../..//button[@id='saveButton']")
	WebElement btn_applyCorporation;
	
	@FindBy(css="input[name='FNTermsOfUse_input']")
	WebElement searchbox_eConsentClient;
	
	@FindBy(xpath="//b[contains(text(), 'Client Portal')]/../..//button[@id='saveButton']")
	WebElement btn_applyClient;
	
	@FindBy(xpath="//button[contains(text(), 'Publish')]")
	WebElement btn_publishEConsent;
	
	@FindBy(id="eConsentTitle")
	WebElement textbox_eConsentTitle;
	
	@FindBy(css="button[name='submit']")
	WebElement btn_addEConsent;
	
	@FindBy(xpath="//b[contains(text(), 'Client Portal')]/../..//i[@class='fa fa-plus']")
	WebElement icon_plusForClientEConsent;
	
	@FindBy(id="SpecificPolicyAndGuidelines")
	WebElement tab_PolicyGuidelines;
	
	@FindBy(id="addPolicyGuidelinesButton")
	WebElement btn_AddNewPolicy;
	
	@FindBy(id="AddPolicyGuideline")
	WebElement btn_SaveAndPublishPolicy;
	
	@FindBy(xpath="//a[contains(text(), 'News')]")
	WebElement tbl_News;
	
	@FindBy(xpath="//a[contains(text(), 'Policy')]")
	WebElement tbl_policy;
	
	@FindBy(css="label[for='FNImmediateManagers']")
	WebElement checkbox_Both;
	
	@FindBy(css="div[class='toast-alert-message alert-success success_alert_msg show']")
	WebElement txt_successMessageAppeared;
	
	@FindBy(id="SaveandPublishNews")
	WebElement btn_SaveAndPublish;
	
	@FindBy(css = "label[for='FNSupervisors']")
	WebElement checkbox_clientAccess;
	
	@FindBy(css = "input[id='Title']")
	WebElement txtbox_newsTitle;

	@FindBy(css = "html>body")
	WebElement txtbox_newsDescription;
	
	@FindBy(css = "button[id='addNewsButton']")
	WebElement btn_addNews;
	
	@FindBy(css="a[id='SpecificNews']")
	WebElement tab_News;
	
	@FindBy(xpath = "//a[contains(text(), 'Portal Content')]")
	WebElement tab_PortalContent;

	@FindBy(xpath = "//h4[contains(text(), 'User Management')]") // Added by Saksham Kapoor on 26/04/19
	WebElement tab_UserManagement;
	
	@FindBy(css = "a[class = 'click-link-a vm ']")
	WebElement lnk_Corporation;
	
	@FindBy(xpath = "//button[contains(text(), 'Customize Corporation Settings')]") // Added by Saksham Kapoor on 22/07/19
	WebElement btn_CustomizeCorportaionAccess;
	
	@FindBy(xpath = "//p[contains(text(), 'Default Portal Access')]") // Added by Saksham Kapoor on 22/07/19
	WebElement tab_defaultPortalAccess;
	
	@FindBy(id = "btn_Saveandinform") // Added by Saksham Kapoor on 22/07/19
	WebElement btn_SaveAndInform;
	
	@FindBy(id = "txtSubj") // Added by Saksham Kapoor on 22/07/19
	WebElement txt_SubjectForInviteUser;
	
	@FindBy(xpath = "//span[contains(text(), 'Invite User Again')]") // edited by Saurav Purohit on 12/07/21 . label has changed to span
	WebElement txt_inviteUserAgain;	
	
	@FindBy(xpath = "//i[@class = 'fa fa-ellipsis-h fa-stack-1x']") // Added by Saksham Kapoor on 22/07/19
	WebElement icon_QuickActionsForUserManagement;
	
	@FindBy(id = "kendoGridSearchBox")
	WebElement searchbox_Corporation;
	
	
	@FindBy(xpath = "//form[@data-vv-scope='FNTermsOfUse']//span[@title='clear']")                //Added by Nitisha Sinha on 24/6/2020
	WebElement icon_clearEConsentForClient;

	public PortalSetup(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Login Page did not open up. Site might be down.", driver);
		}
	}

	
	public void clickQuickActionsIconForUserManagement(boolean screenshot)
	{
		// Created by Saksham Kapoor on 22/07/19
		
   	  	JavascriptExecutor js = (JavascriptExecutor) driver;
   	  	js.executeScript("window.scrollBy(0,1000)");
   	  	Utils.clickElement(driver, icon_QuickActionsForUserManagement, "Quick Actions icon in User Management Tab");
  	   	 
	}
	
	public void clickInviteUserAgain(boolean screenshot) throws Exception
	{
		// Created by Saksham Kapoor on 22/07/19
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 29/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.clickElement(driver, txt_inviteUserAgain, "Invite User Again");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Email Id/Password", "title", "false");
		
		String subject = "Invitation " + java.time.LocalDateTime.now().toString();
		globalVariables.subjectForInvitation = subject;
		
		Utils.scrollToElement(driver, txt_SubjectForInviteUser);
		Utils.enterText(driver, txt_SubjectForInviteUser, subject, "Subject");
			
	}
	
	
	public void clickSaveAndInform(boolean screenshot) throws Exception
	{
		// Created by Saksham Kapoor on 22/07/19
		
		Utils.scrollToElement(driver, btn_SaveAndInform);
		Utils.clickElement(driver, btn_SaveAndInform, "Save and Inform Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "My Zoomboard", "title", "false");
		
		
	}
	
	
	public void clickDefaultPortalAccess(boolean screenshot)
	{
		// Created by Saksham Kapoor on 22/07/19
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 29/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.clickElement(driver, tab_defaultPortalAccess, "Default Portal Access Tab");
	}
	
	
	public void searchCorporationByName(String corpName, boolean screenshot)
	{
		// Created by Saksham Kapoor on 22/07/19
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 29/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		/*
		 * Modified By: Nitisha Sinha
		 * Modified On: 23/06/2020
		 * Modifications: Added Logic to handle the loader
		 * 
		 */
		
		Utils.enterText(driver, searchbox_Corporation, corpName, "Corporation search box");
		Utils.waitForElementPresent(driver, "//div[@class='table-responsive']/center/div", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='table-responsive']/center/div", "xpath");
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + corpName + "')]", "xpath", "Searched Corporation");
		//Utils.clickElement(driver, lnk_Corporation, "Searched Corporation");
		
		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-running')]", "xpath");
		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-done')]", "xpath");
	}
	
	
	public void clickCustomizeCorporationSettings(boolean screenshot)
	{
		// Created by Saksham Kapoor on 22/07/19
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 29/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.clickElement(driver, btn_CustomizeCorportaionAccess, "Customize Corporation Access");
		
//		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-running')]", "xpath");
//		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-done')]", "xpath");
		
		Utils.waitForElementPresent(driver, "//img[@class='zc-image']","xpath");
		Utils.waitForElementNotVisible(driver, "//img[@class='zc-image']","xpath");
		
	}
	
	
	public void clickUserManagementTab(boolean screenshot) throws InterruptedException
	{
		// Created by Saksham Kapoor on 14/10/19
		
		Utils.clickElement(driver, tab_UserManagement, "User Managemnet Tab");
		
		Utils.waitForKendoImageCompleted(driver);
		
	}
	
	
	public void clickPortalContentTab()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, tab_PortalContent, "Portal Content tab");
		
		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-running')]", "xpath");
		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-done')]", "xpath");
	}
	
	
	public void clickNewsTab()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, tab_News, "News tab");
	}
	
	
	public void clickAddNewsAndEnterData(String newsTitleClient, String newsDescriptionClient) throws InterruptedException
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, btn_addNews, "Add News");
		
		Utils.enterText(driver, txtbox_newsTitle, newsTitleClient, "News Title");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		Utils.enterText(driver, txtbox_newsDescription, newsDescriptionClient, "News Description");
		driver.switchTo().defaultContent();
	}
	
	
	public void chooseAllClients()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, checkbox_clientAccess, "All Clients Checkbox");
	}
	
	
	public void clickSaveAndPublish()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, btn_SaveAndPublish, "Save and Publish");
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
	}
	
	
	public void verifyIfNewsAdded(String newsTitleClient, String newsDescriptionClient)
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.verifyIfDataPresent(driver, "//label[contains(text(), '" + newsTitleClient + "')]", "xpath", newsTitleClient, "News");
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), '" + newsDescriptionClient + "')]", "xpath", newsDescriptionClient, "News Description");
	}
	
	
	public void chooseBothCheckbox()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, checkbox_Both, "'Both' checkbox");
	}
	
	
	public void deleteNews(String newsTitleClient)
	{
		// Created by Saksham Kapoor on 06/12/19
		
		Utils.clickDynamicElement(driver, "//label[contains(text(), '" + newsTitleClient + "')]/ancestor::td/following-sibling::td//a[contains(text(), 'Delete')]", "xpath", "Delete");
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
	}
	
	
	public void verifyIfNewsDeleted(String newsTitleClient)
	{
		// Created by Saksham Kapoor on 06/12/19
		
		Utils.verifyIfDataNotPresent(driver, "//label[contains(text(), '" + newsTitleClient + "')]", tbl_News, "xpath", newsTitleClient, "News tab");
	}
	
	
	public void clickPolicyGuidelinesTab()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, tab_PolicyGuidelines, "Policy/Guidelines tab");
	}
	
	
	public void clickAddPolicyAndEnterData(String policyTitleClient, String policyDescriptionClient) throws InterruptedException
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, btn_AddNewPolicy, "Add New Policy");
		
		Utils.enterText(driver, txtbox_newsTitle, policyTitleClient, "Policy Title");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		Utils.enterText(driver, txtbox_newsDescription, policyDescriptionClient, "Policy Description");
		driver.switchTo().defaultContent();
	}
	
	
	public void clickSaveAndPublishPolicy()
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.clickElement(driver, btn_SaveAndPublishPolicy, "Save and Publish");
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
	}
	
	
	public void verifyIfPolicyAdded(String policyTitleClient, String policyDescriptionClient)
	{
		// Created by Saksham Kapoor on 05/12/19
		
		Utils.verifyIfDataPresent(driver, "//label[contains(text(), '" + policyTitleClient + "')]", "xpath", policyTitleClient, "Policy");
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), '" + policyDescriptionClient + "')]", "xpath", policyDescriptionClient, "Policy Description");
	}
	
	
	public void addEConsent(String eConsentTitle, String eConsentDescription) throws Exception
	{
		// Created by Saksham Kapoor on 13/12/19
		
		Utils.clickElement(driver, icon_plusForClientEConsent, "'Plus' icon to add e-consent");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "e-Consent", "title", "false");
		
		//Utils.clickElement(driver, btn_addEConsent, "'Add'");
		
		Utils.enterText(driver, textbox_eConsentTitle, eConsentTitle, "e-consent Title");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		Utils.enterText(driver, txtbox_newsDescription, eConsentDescription, "e-consent Description");
		driver.switchTo().defaultContent();
		
		Utils.clickElement(driver, btn_publishEConsent, "'Publish'");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");
	}
	
	
	public void chooseEConsentforClient(String eConsentTitle)
	{
		// Created by Saksham Kapoor on 13/12/19
		
		//Utils.enterText(driver, searchbox_eConsent, eConsentTitle, "e-consent search box");
		
		try {
			Thread.sleep(3000);
			searchbox_eConsentClient.click();
			Thread.sleep(3000);
			searchbox_eConsentClient.clear();
			Thread.sleep(3000);
			searchbox_eConsentClient.sendKeys(eConsentTitle);
			Thread.sleep(5000);
			searchbox_eConsentClient.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to select e-consent. ERROR :\n\n " + e.getMessage());
		}


		Utils.clickElement(driver, btn_applyClient, "'Apply' button");
		
		if(Utils.isElementPresent(driver, "//span[contains(text(), 'This field is required')]", "xpath"))
			Log.fail("e-consent not selected");
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
	}
	
	
	public void chooseEConsentforCorporation(String eConsentTitle)
	{
		// Created by Saksham Kapoor on 13/12/19
		
		try {
			Thread.sleep(3000);
			searchbox_eConsentCorporation.click();
			Thread.sleep(3000);
			searchbox_eConsentCorporation.clear();
			Thread.sleep(3000);
			searchbox_eConsentCorporation.sendKeys(eConsentTitle);
			Thread.sleep(5000);
			searchbox_eConsentCorporation.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to select e-consent. ERROR :\n\n " + e.getMessage());
		}

		Utils.clickElement(driver, btn_applyCorporation, "'Apply' button");
		
		if(Utils.isElementPresent(driver, "//span[contains(text(), 'This field is required')]", "xpath"))
			Log.fail("e-consent not selected");
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
		
	}
	
	
	public void clickUserInstructionTab()
    {
          // Created by Likitha Krishna
          //  Created on 14/12/19
          
          Utils.clickElement(driver, tab_userInstruction, "User Instruction tab");
    }
    

    public void setClientUserInstruction(String title, String userInstruction) throws InterruptedException
    {
          // Created by Likitha Krishna
          // Created on 14/12/19
          
          Utils.enterText(driver, textBox_clientUserInstructionTitle, title, "client user instruction title");
          
          Thread.sleep(3000);
          driver.switchTo().frame(1);   
          Utils.enterText(driver, textBox_clientUserInstructionDescription, userInstruction, "description");    
          driver.switchTo().defaultContent();
          
          Utils.clickElement(driver, btn_saveClientUserInstruction, "save button");
          
          Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
          Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
    }

    public void verifyIfPolicyDeleted(String newsTitleClient)
	{
		// Created by Saksham Kapoor on 06/12/19
		
		Utils.verifyIfDataNotPresent(driver, "//label[contains(text(), '" + newsTitleClient + "')]", tbl_policy, "xpath", newsTitleClient, "Policy tab");
	} 
    
    
    public void clickSaveClientPortalSettings()
    {
    	// Created by Nitisha Sinha
        // Created on 10/02/20
    	
    	Utils.clickElement(driver, btn_saveClientPortalSettings, "Save Client portal settings button");
    	
    	Utils.waitForElementPresent(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
    	
    	Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
    
    }
    
    public void clickSaveGeneralSettingsClientPortal()
    {
    	// Created by Kuchinad Shashank
        // Created on 13/02/20
    	
    	Utils.clickElement(driver, btn_saveGeneralSettingsClientPortal, "Save Client portal general settings button");
    	
    	Utils.waitForElementPresent(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
    	
    	Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
    
    }
    
    public void clickClientPortalMenu()
    {
    	/*
    	  Created by Yatharth Pandya
    	  Created on 3/2/2020
    	 */
    	
    	Utils.clickElement(driver, tab_clientPortalMenu, "Client Portal Menu");
    	
    }
    
    
    public void verifyPreviewCorporationUserInstruction()
    {
    	/*
    	 *		Created by Kuchinad Shashank
 		 * 		Created on 12th Feb 2020 
    	 */
    	
    	Utils.clickElement(driver, btn_previewCorporationUserInstruction, "Preview");
    	
    	Utils.verifyIfDataPresent(driver, "//div[@class='modal md-effect-1 md-show']/div", "xpath", "Preview popup", "In Corporation User instruction");
    
    	Utils.clickElement(driver, icon_closeUserInstruction, "Close Preview icon");
    
    }
    
    
    public void verifyPreviewClientUserInstruction()
    {
    	/*
		 * Created By : Kuchinad Shashank
		 * Created On : 12th Feb 2020
		 */
    	
    	Utils.clickElement(driver, btn_previewClientUserInstruction, "Preview");
    	
    	Utils.verifyIfDataPresent(driver, "//div[@class='modal md-effect-1 md-show']/div", "xpath", "Preview popup", "In Corporation User instruction");
    
    	Utils.clickElement(driver, icon_closeUserInstruction, "Close Preview icon");
    
    }
    
    
    public void verifyPreviewPolicyGuidlines(String policyTitle)
    {
    	/*
		 * Created By : Kuchinad Shashank
		 * Created On : 12th Feb 2020
		 */
    	
    	Utils.clickDynamicElement(driver, "//label[contains(text(),'" + policyTitle + "')]/ancestor::tr//a[contains(text(),'Preview')]", "xpath", "Preview Button");
    	
    	Utils.verifyIfDataPresent(driver, "//div[@class='modal md-effect-1 md-show']/div", "xpath", "Policy Pop", "Policy/Guidlines in Portal Content");
    	
    	Utils.clickElement(driver, btn_closePreviewPolicy, "Close Preview Button");
    }
    
    public void clickGeneralSettingsTab()
    {
    	/*
		 * Created By : Kuchinad Shashank
		 * Created On : 13th Feb 2020
		 */
    	Utils.clickElement(driver, tab_generalSettings, "General Settings tab");
    	
    	Utils.waitForElementPresent(driver, "//img[@class='zc-image']","xpath");
		Utils.waitForElementNotVisible(driver, "//img[@class='zc-image']","xpath");
    }
    
    
    public void verifyPreviewNews(String newsTitle)
    {
    	/*
		 * Created By : Kuchinad Shashank
		 * Created On : 12th Feb 2020
		 */
    	
    	Utils.clickDynamicElement(driver, "//label[contains(text(),'" + newsTitle + "')]/ancestor::tr//a[contains(text(),'Preview')]", "xpath", "Preview Button");
    	
    	Utils.verifyIfDataPresent(driver, "//div[@class='modal md-effect-1 md-show']", "xpath", "News Popup", "News in Portal Content");
    
    	Utils.clickElement(driver, btn_closePreviewNews, "Close Preview Button");
    }
    
    
    public void verifySameasCorporationEConsentCheckBox()
    {	
    	/*
		 * Created By : Kuchinad Shashank
		 * Created On : 12th Feb 2020
		 */
    	
		toggleCheckBox("Corporation Portal Terms of Use", true);
		
		Utils.clickElement(driver, btn_confirmCopyEConsent, "Continue Button");
		
		if(txtbox_eConsentMsgHrp.getText().equals(txtbox_eConsentMsgFnp.getText()))
		{
			Log.pass("Verfied: The E-Consent is copied from Corporation and is same as Corporation Terms of use");
		}
		else
		{
			Log.fail("The econsent is not copied and is different from Corporation terms of use");
		}
    }
    
    
    public void verifyClearEConsentButtonCorporation()
    {
    	/*
   	 		Created by Nitisha Sinha
   	 		Created on 12th Jan 2020
    	*/
    	
    	Utils.clickElement(driver, searchbox_eConsentCorporation, "eConsent Searchbox for corporation");
    	
    	Utils.clickElement(driver, icon_clearEConsentForCorp, "Clear button for eConsent under corporation");
    	
    	if(Utils.isElementPresent(driver, "//form[@data-vv-scope='corpTermsOfUse']//span[contains(text(),'This field is required')]", "xpath"))
    	{
    		Log.pass("Verified the clear button functionality for Choose Terms of Use dropdown in Corporation Portal Terms of Use Content under Terms of Use");
    	}
    	else
    	{
    		Log.fail("Could not verify the clear button functionality for Choose Terms of Use dropdown in Corporation Portal Terms of Use Content under Terms of Use");
    	}
    	
    }
    
    
    public void verifyRemoveEConsentButtonCorporation()
    {
    	/*
   	 		Created by Nitisha Sinha
   	 		Created on 12th Jan 2020
    	*/
    	
    	Utils.clickElement(driver, btn_removeEConsentCorporation, "Remove eConsent button for corporation");
    	
    	Utils.clickElement(driver, btn_saveRemoveEConsentCorporation, "Save Remove eConsent for Corporation ");
    	
		if(Utils.isElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css"))
		{
    		Log.pass("Verified the remove button functionality in Corporation Portal Terms of Use Content under Terms of Use");
    	}
    	else
    	{
    		Log.fail("Could not verify the remove button functionality in Corporation Portal Terms of Use Content under Terms of Use");
    	}
    }
    
    
    public void verifyClearEConsentButtonClient() throws InterruptedException
    {
    	/*
   	 		Created by Nitisha Sinha
   	 		Created on 12th Jan 2020
   	 		Modified by Nitisha Sinha on 24th June, 2020
   	 		Modification done: changed the loactor for icon_clearEConsentForClient
    	*/
    	
    	Utils.clickElement(driver, searchbox_eConsentClient, "eConsent Searchbox for client");
    	
    	Utils.clickElement(driver, icon_clearEConsentForClient, "Clear button for eConsent under client");
    	
    	if(Utils.isElementPresent(driver, "//form[@data-vv-scope='FNTermsOfUse']//span[contains(text(),'This field is required')]", "xpath"))
    	{
    		Log.pass("Verified the clear button functionality for Choose Terms of Use dropdown in Client Portal Terms of Use Content under Terms of Use");
    	}
    	else
    	{
    		Log.fail("Could not verify the clear button functionality for Choose Terms of Use dropdown in Client Portal Terms of Use Content under Terms of Use");
    	}
    	
    }
    
    
    public void verifyRemoveEConsentButtonClient()
    {
    	/*
   	 		Created by Nitisha Sinha
   	 		Created on 12th Jan 2020
    	*/
    	
    	Utils.clickElement(driver, btn_removeEConsentClient, "Remove eConsent button for client");
    	
    	Utils.clickElement(driver, btn_saveRemoveEConsentClient, "Save Remove eConsent for client ");
    	
		if(Utils.isElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css"))
		{
    		Log.pass("Verified the remove button functionality in Client Portal Terms of Use Content under Terms of Use");
    	}
    	else
    	{
    		Log.fail("Could not verify the remove button functionality in Client Portal Terms of Use Content under Terms of Use");
    	}
    }
    
    
    public void clickClientPortalSettingsTab()
	{
	    	/**Created By Kuchinad Shashank
	    	  *Created on 3/02/2020 
		      *Modified by Nitisha Sinha on 23rd June 2020
		      *Modification Done: Modified the wait Element
		    **/
		
		Utils.clickElement(driver, tab_clientPortalSettings, "Client Portal Content");
		
		Utils.waitForElementPresent(driver, "//div[contains(text(),'Client Portal Menu Access')]", "xpath");
	}
	
	public void clickCustomizedPortalAccess()
	{
    	/*
    	 Created by Yatharth Pandya
    	 Created on 31/01/2020
    	 Modified by : saurav 
    	 Modified date : 12/07/2021
    	 Added a sync to 
    	*/
		Utils.clickElement(driver, tab_customizedPortalAccess, "Customized Portal Access");
		
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='load-more-loader']")));
        
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement((By.xpath("//div[@class='load-more-loader']")))));
    
	}
	
 
    public void verifyPreviewButton(String text)
    {
    	/*
   	 		Created by Kuchinad Shashank
   	 		Created on 06th Feb 2020
    	*/
    	
    	WebElement btn_preview = driver.findElement(By.xpath("//span[contains(text(),'"+ text +"')]/ancestor::tr/td[@class='text-right']//button"));
    	
    	Utils.scrollToElement(driver, btn_preview);
    	
    	Utils.clickElement(driver, btn_preview, "Preview button");
    	
    	Utils.verifyIfDataPresent(driver, "//div[@id='modalContent']/div[@class='modal-body modal-max-height']//img", "xpath", "Preview Image", "Client Portal Settings");
    	
    	Utils.clickElement(driver, btn_closePreview, "Close Preview button");
    }
    

    public void toggleCheckBox(String key , boolean value)
    {
    	/*
	 		Created by Kuchinad Shashank
	 		Created on 05th Feb 2020
    	*/
    	
    	Utils.waitForElement(driver, "//span[contains(text(),'" + key + "')]/../../label", globalVariables.elementWaitTime, "xapth"); 
    	
    	String input = "//span[contains(text(),'" + key + "')]/../../input" ;
		
		String label = "//span[contains(text(),'" + key + "')]/../../label" ;
		
		WebElement checkbox_input = driver.findElement(By.xpath(input));
		
		Utils.scrollToElement(driver, checkbox_input);
		
		if(value)
		{
			if (checkbox_input.isSelected()) 
			{
				Log.message("The checkbox for" + key +" is checked by default", driver, true);
			}
			
			else 
			{
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for" + key + " is checked", driver, true);
			}
		}
		
		else
		{
			if (checkbox_input.isSelected()) 
			{
				
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for" + key + " is unchecked", driver, true);		
			}
			
			else 
			{
				Log.message("The checkbox for" + key +" is unchecked by default", driver, true);
				
			}
		}
    }
    
    
        public void editCorporationRoleManagement()
    {
    	/*
 		Created by Nitisha Sinha
 		Created on 14th Feb 2020
    	 */
    	
    	Utils.clickElement(driver, icon_editCorporationRoleManagement, "Edit Role Access Management icon");
    }
    
    
    public void saveCorporationRoleManagement()
    {
    	/*
 		Created by Nitisha Sinha
 		Created on 14th Feb 2020
    	 */
    	
    	Utils.clickElement(driver, icon_saveCorporationRoleManagement, "Save Role Access Management icon");
    }
    
    
    public void toggleCheckBoxClientProfileAccess(boolean value, String key)
    {
    	/*
 		Created by Nitisha Sinha
 		Created on 17th Feb 2020
    	 */
    	
    	String input = "//input[@name='MenuAccess.BNFProfileMenus."+key+".HasAccess']" ;
    	
		String label = "//label[contains(@for,'MenuAccess.BNFProfileMenus."+key+".HasAccess')]";
		
		WebElement checkbox_input = driver.findElement(By.xpath(input));
    	
    	Utils.scrollIntoView(driver, checkbox_input);
    	
		if(value)
		{
			if (checkbox_input.isSelected()) 
			{
				Log.message("The checkbox for " + key +" in Client Profile is checked by default", driver, true);
			}
			
			else 
			{
				Utils.clickDynamicElement(driver, label,"xpath", "checkbox for" + key);
				Log.message("Clicked on " + key +" checkbox in Client Profile access");

			}
		}
		
		else
		{
			if (checkbox_input.isSelected()) 
			{
				
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for " + key + " in Client Profile is unchecked", driver, true);		
			}
			
			else 
			{
				Log.message("The checkbox for " + key +" in Client Profile is unchecked by default", driver, true);
				
			}
		}
    }
    
    
    public void toggleCheckBoxCaseProfileAccess(boolean value, String key)
    {
    	/*
 		Created by Nitisha Sinha
 		Created on 17th Feb 2020
    	 */
    	
    	String input = "//input[@name='MenuAccess.CaseProfileMenus."+key+".HasAccess']" ;
    	
		String label = "//label[contains(@for,'MenuAccess.CaseProfileMenus."+key+".HasAccess')]";
		
		WebElement checkbox_input = driver.findElement(By.xpath(input));
    	
    	Utils.scrollIntoView(driver, checkbox_input);
    	
		if(value)
		{
			if (checkbox_input.isSelected()) 
			{
				Log.message("The checkbox for " + key +" in Case Profile is checked by default", driver, true);
			}
			
			else 
			{
				Utils.clickDynamicElement(driver, label,"xpath", "checkbox for" + key);
				Log.message("Clicked on " + key +" checkbox in Case Profile access");

			}
		}
		
		else
		{
			if (checkbox_input.isSelected()) 
			{
				
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for " + key + " in Case Profile is unchecked", driver, true);		
			}
			
			else 
			{
				Log.message("The checkbox for " + key +" in Client Profile is unchecked by default", driver, true);
				
			}
		}
    }
    
    
    public void addCorporationRole(String roleTitle,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.clickElement(driver, btn_addNewCorporationRole, "ADD new Corporaiton Role");
		Utils.enterText(driver, textbox_roleName, roleTitle, "Role Title");
		Utils.clickElement(driver, btn_saveNewCorporationRole, "Save Corporation Role");
		
		Utils.waitForElement(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", globalVariables.elementWaitTime, "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
	}
    
    public void clickRoleManagementTab(boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.clickElement(driver, tab_roleManagement, "Role Managment Tab");
		
	}
	
	
	public void searchRoleByName(String roleTitle,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.waitForElement(driver, searchbox_RoleTitle);
		Utils.waitForElementClickable(driver, searchbox_RoleTitle);
		Utils.enterText(driver, searchbox_RoleTitle, roleTitle, "Searched Corporation Role");
		
		/*Utils.waitForElementPresent(driver, "//div[@class='k-loading-image']", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-image']", "xapth");*/
		Utils.waitForKendoImageCompleted(driver);
		
	}
	
	public void searchUserByName(String userName,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 * Modified By : saurav
		 * Modified Date : 12/07/2021
		 * 
		 */
		
		Utils.waitForElement(driver, searchbox_UserName);
		
		Utils.waitForElementClickable(driver, searchbox_UserName);
		
		Utils.enterText(driver, searchbox_UserName, userName, "Searched Corporation Role");
		
		Utils.waitForKendoImageCompleted(driver);
		
		
		
		/*Utils.waitForElementPresent(driver, "//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']", "xpath");*/
		
	}
	
	public void deleteCorporationRole(String roleTitle,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		searchRoleByName(roleTitle, true);		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + roleTitle +"')]/following-sibling::td/a[contains(text(),'Edit Role')]","xpath","Edit Button on Role");
		
		Utils.clickElement(driver, btn_deleteCorporationRole, "Delete Corporation role");
		Utils.clickElement(driver, btn_confirmDeleteCorporationRole, "Confirm Delete Role Button");
		
	}
	
	
	public void verifyPopUpOnDeletion()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.waitForElementPresent(driver, "//div[@id='cannotDeletePopUp']//div[contains(text(),'System')]", "xpath");
		Utils.clickElement(driver, btn_cancelDeletePopUp, "Cancel PopUp");
		Log.message("Clicked on close delete role pop up");
		
		Utils.clickElement(driver, btn_cancelEditCorpRole, "Cancel EditCorp Role");
	}
	
	public void changeUserRoleToDefault(String roleTitle)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+ roleTitle +"')]/../../following-sibling::td//i[@class='fa fa-pencil-square-o attachment-icon edit-popover']", "xpath", "Edit User");	
		
		Utils.waitForElement(driver, "//div[@class='row iconLoadingForModal maskOverlay']/img", globalVariables.elementWaitTime, "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='row iconLoadingForModal maskOverlay']", "xpath");
		
		txtbox_roleEditUser.clear();
		Utils.enterText(driver, txtbox_roleEditUser, "Standard User", "Role Title");
		txtbox_roleEditUser.sendKeys(Keys.ENTER);
		
		Utils.clickElement(driver, btn_saveEditUser, "Save Edit User");
		Utils.waitForElement(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", globalVariables.elementWaitTime, "xpath");
        Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
		
	}

	
    public void verifyAddCorporationRole(String roleName, boolean screenshot)
    {
    	/*
 		Created by Nitisha Sinha
 		Created on 17th Feb 2020
    	 */
    	
    	searchRoleByName(roleName, true);
    	Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+roleName+"')]", "xpath", "role Name", "Corporation Role");
    
    }
    
    
    public void addNewUser(String roleTitle, String firstName, String lastName, String email, boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		Utils.clickElement(driver, btn_addUser, "Add New User");
	    WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime); 
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[@class='zc-image']"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//img[@class='zc-image']"))));
		Utils.waitForElementClickable(driver, txtbox_firstNameNewUser);
		Utils.enterText(driver, txtbox_firstNameNewUser, firstName, "First Name");
		Utils.enterText(driver, txtbox_lastNameNewUser, lastName, "Last Name");
		Utils.enterText(driver, txtbox_emailNewUser, email, "Email");

		Utils.clickElement(driver, txtbox_roleInputForUser, "role Name text box");
		Utils.enterText(driver, txtbox_roleInputForUser, roleTitle, "Role Title");
		Utils.clickElement(driver, txtbox_roleInputForUser, "role name");
		txtbox_roleInputForUser.sendKeys(Keys.ENTER);
		
		
		Utils.waitForElement(driver, checkbox_portalAccess);
		if (!checkbox_portalAccess.isSelected())
		{
			Utils.clickElement(driver, checkbox_portalAccess, "Check box for Portal Access");
		}
		
		Utils.clickElement(driver, btn_saveNewUser, "Save New User");
		
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
	 }
    
    
    public void createDuplicateCorporationRole(String roleTitle)
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 17 Feb 2020
		 */
		
		searchRoleByName(roleTitle, true);	
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + roleTitle +"')]/following-sibling::td/a[contains(text(),'Edit Role')]","xpath","Edit Button on Role");
		
		Utils.clickElement(driver, btn_duplicateCorpRole, "duplicate button");
	
		Utils.enterText(driver, txtbox_duplicateRoleTitle, "Duplicate"+roleTitle+"", "Enter Role ID");
		
		Utils.clickElement(driver, btn_saveDupliicateRole, "Save Duplicate Corporation Button");
	
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		
	}
	
	
	public void verifyEditRoleButton(String roleTitle)
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 17 Feb 2020
		 */
		
		searchRoleByName(roleTitle, true);
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + roleTitle +"')]/following-sibling::td/a[contains(text(),'Edit Role')]", "xpath", "Edit Role Button");
		
		if(Utils.isElementPresent(driver, "//h4[contains(text(),'Edit Role - " + roleTitle +"')]", "xpath"))
		{
			Log.pass("Verifed. Edit Role Popup Appeared", driver, true);
		}
		else
		{
			Log.fail("Failed. Edit Role Popup didnot appear", driver, true);
		}
	}
	
	
	public void verifySearchRoleFunctionality(String roleTitle)
	{
		/*
        	Created by Kuhcinad Shashank
        	Created on 17th Feb 2020
		 */
		
		searchRoleByName(roleTitle, true);
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + roleTitle + "')]","xpath" ,"Role","In Corporation Portal Access");
	}
	
	
	public void toggleCheckBoxCorporationProfileAccess(String key, boolean value)
    {
		/*
        Created by Kuhcinad Shashank
        Created on 17th Feb 2020
		 */
      
		String input = "//input[@name='MenuAccess.CorpProfileMenus." + key + ".HasAccess']" ;
  
        String label = "//label[contains(@for,'MenuAccess.CorpProfileMenus." + key + ".HasAccess')]";
        
        WebElement checkbox_input = driver.findElement(By.xpath(input));
  
        Utils.scrollIntoView(driver, checkbox_input);
  
        if(value)
        {
		      if (checkbox_input.isSelected()) 
		      {
		            Log.message("The checkbox for " + key +" in Corporation Profile is checked by default", driver, true);
		      }
		      
		      else 
		      {
		            Utils.clickDynamicElement(driver, label,"xpath", "checkbox for" + key);
		            Log.message("Clicked on " + key +" checkbox in Corporation Profile access",driver,true);
		
		      }
        }
        
        else
        {
	          if (checkbox_input.isSelected()) 
	          {
	                Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
	                Log.message("The checkbox for " + key + " in Corporation Profile is unchecked", driver, true);            
	          }
	          
	          else 
	          {
	        	  Log.message("The checkbox for " + key +" in Corporation Profile is unchecked by default", driver, true);         
	          }
        }
    }
	
	
	public void verifyIfRoleDeleted(String roleTitle,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		searchRoleByName(roleTitle, true);
		Utils.verifyIfDataNotPresent(driver, "//td[contains(text(),'" + roleTitle + "')]", waitelement_textHeader,"xpath", "Role", "Corporation Portal Access");
		
	}
	
	
	public void verifyIfRoleAdded(String roleTitle,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		searchRoleByName(roleTitle, true);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + roleTitle + "')]","xpath" ,"Role","In Corporation Portal Access");

	}
	
	
	public void clickSendEmail(boolean screenshot) throws Exception
	{
		// Created by Nitisha Sinha on 18/2/2020
	    //modified by saurav on 12/07/2021 . removed waitForAllWindowsToLoad and switchWindows method from script
	    //lavel and added them to this method .
		
		Utils.clickElement(driver, btn_sendEmail, "Send Email button");	
		
		Utils.waitForAllWindowsToLoad(2, driver);
        
        Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");
		
	}
	
	public void resetUser(boolean screenshot) throws Exception
	{
		// Created by Nitisha Sinha on 18/2/2020
		
		Utils.clickElement(driver, txt_resetUser, "Reset User option");	
		
		Utils.clickElement(driver, btn_resetUser, "Reset User button");	
		
		Utils.waitForElement(driver, txt_successMessageAppeared);
		
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
		
	}
	
	
	public void verifyEconsentHistory(boolean screenshot) throws Exception
	{
		// Created by Nitisha Sinha on 18/2/2020
		
		Utils.clickElement(driver, txt_eConsentHistory, "Econsent History option");	
		
		if(Utils.isElementPresent(driver, "//h4[contains(text(),'Econsent Log')]", "xpath"))
    	{
    		Log.pass("Verified the functionality of 'e-Consent History' for a User under 'More options' in 'Actions'.");
    	}
    	else
    	{
    		Log.fail("Could not Verify the functionality of 'e-Consent History' for a User under 'More options' in 'Actions'");
    	}
		
		Utils.clickElement(driver, btn_closeEconsentLog, "Close Econsent Log button");
  
	}
	 

	public void verifyIfUserAdded(String userName,boolean screenshot)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 18/02/2020
		 */
		
		searchUserByName(userName, true);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + userName + "')]","xpath" ,"User Added","In Corporation Portal Access");

	}
	
	
	public void verifyViewUserByRoleInDropdown(String userName, String roleName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 18/02/2020
		 */
		
		Utils.clickElement(driver, textbox_searchUserByRole, "");
		Utils.enterText(driver, textbox_searchUserByRole, roleName, "Role Title");
		Utils.clickElement(driver, textbox_searchUserByRole, "");
		textbox_searchUserByRole.sendKeys(Keys.ENTER);
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + userName + "')]","xpath" ,"User","In Corporation Portal Access");
	}
	
	
	public void verifySearchUserByRole(String userName, String roleName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 18/02/2020
		 */	
		
		searchUserByName(roleName, true);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + userName + "')]","xpath" ,"User","In Corporation Portal Access");
	
	}
	
	
	public void verifySearchUserByUserName(String userName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 18/02/2020
		 */
		
		searchUserByName(userName, true);
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + userName + "')]","xpath" ,"User","In Corporation Portal Access");
	}
	
	
	public void editUserDetails(String userName, String lastName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 18/02/2020
		 */
		
		searchUserByName(userName, true);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + userName + "')]/following-sibling::td//i[@class='fa fa-pencil-square-o attachment-icon edit-popover']", "xpath", "Edit Icon");
		
		Utils.waitForElement(driver, "//div[@class='row iconLoadingForModal maskOverlay']/img", globalVariables.elementWaitTime, "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='row iconLoadingForModal maskOverlay']", "xpath");
		
		txtbox_lastNameEditUser.click();
		Utils.enterText(driver, txtbox_lastNameEditUser, lastName, "Last Name");
		
		Utils.clickElement(driver, btn_saveEditUser, "Save Edit User");
		
		Utils.waitForElementNotVisible(driver, "//div[@id='editUserModalFooter']//button[contains(text(),'Save')]", "xpath");
	}
	
	
	public void deleteUser(String userName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 19/02/2020
		 */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + userName + "')]/following-sibling::td//i[@class='fa fa-pencil-square-o attachment-icon edit-popover']", "xpath", "Edit Icon");
		
		Utils.waitForElement(driver, "//div[@class='row iconLoadingForModal maskOverlay']/img", globalVariables.elementWaitTime, "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@class='row iconLoadingForModal maskOverlay']", "xpath");
		
		Utils.clickElement(driver, btn_deleteUser, "Delete user Button");
	}
	
	
	public void verifyUserDeleted(String userName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 19/02/2020
		 */
		
		searchUserByName(userName, true);
		
		if(Utils.waitForElement(driver, "//h1[contains(text(),'No records available')]", globalVariables.elementWaitTime, "xpath"))
			Log.pass(userName + " deleted successfully");
		else
			Log.fail(userName + " could not be deleted successfull");
	}
	
	
	public void restoreDeletedUser(String userName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 19/02/2020
		 */
		
		/*
		 * Modified By: Nitisha Sinha
		 * Modified On: 2/03/2020
		 * Modification: changed the xpath of checkbox for Deleted User
		 */
		
		Utils.clickElement(driver, btn_menuToggle, "");
		
		Utils.clickElement(driver, btn_restoreDeletedUsers, "Restore Deleted User");
		
		Utils.enterText(driver, txtbox_searchDeletedUser, userName, "UserName");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + userName + "')]/preceding-sibling::td[last()]/div/div/label", "xpath", "checkbox for Deleted User");
		
		Utils.clickElement(driver, btn_restoreUser, "Restore User");
		
		Utils.waitForElement(driver, "//h1[contains(text(),'No records available')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.clickElement(driver, btn_cancelChanges, "Cancel");
	}

	
	public void verifyPortalSetup()
	{

		/*
		 * Added By: Nitisha Sinha
		 * Added On: 20/02/2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//h1[contains(text(),'Portal Setup')]", "xpath", "Portal setup header", "Portal setup page");
	}
	
	
	public void verifyAddNewUser() throws Exception
	{
		//Created by Yatharth Pandya on 20/2/2020
		
		Utils.clickElement(driver, btn_addCorporationUser, "Add New User");
		
		Utils.clickElement(driver, btn_closeNewUser, "Cancel");
			
	}
	
	
	public void verifyViewOnlyAccess() throws Exception
	{
		//Created by Yatharth Pandya on 20/2/2020
		
		Utils.waitForElement(driver, txt_noAccessAlert);
		
		if(Utils.isElementPresent(driver, "//span[contains(text(),'To make changes, you can request your organisation')]", "xpath"))
		{
			Log.message("Access blocked to add new user");
		}
		else
		{
			Log.fail("Access alert text not found");
		}
		
	}
	
	
	public void clickRoleManagementTab()
	{
		//Created by Yatharth Pandya on 20/2/2020
		
		Utils.clickElement(driver, tab_roleManagement, "Role Management Tab");
	}
	
	
	public void verifyAddNewCorporationRole() throws Exception
	{
		//Created by Yatharth Pandya on 20/2/2020
		
		Utils.clickElement(driver, btn_addNewCorporationRole, "New Corporation Role");
		
		Utils.clickElement(driver, btn_cancelNewCorporationRole, "Cancel");
		
	}
	
	
    public void clickCorporationPortalSettingsTab()
    {
      	//Created By Yatharth Pandya
    	//Created on 18/02/2020 
    	
    	Utils.clickElement(driver, tab_corporationPortalSettings, "Corporation portal Settings");
    
    } 
    
    
    public void uploadCorporationLogo(String abpath) throws Exception
	{	     
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 19/02/2020
		 */
		
    	LocalFileDetector detector = new LocalFileDetector();
        File file = detector.getLocalFile(abpath);
        
        WebElement icon_chooseFile = null;
		try {
			Utils.waitForElement(driver, "//div[@class='upload-logo-img']", globalVariables.elementWaitTime, "xpath");
			icon_chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
		} catch (Exception e) {
			Log.fail("Not able to fetch the drop area for file");
		}
		
		WebDriver driver = ((RemoteWebElement) icon_chooseFile).getWrappedDriver();
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

		WebElement ele = (WebElement) jse.executeScript(JS_DROP_FILE, icon_chooseFile, 0, 0);
	    ((RemoteWebElement) ele).setFileDetector(detector);
	    
		ele.sendKeys(file.getAbsolutePath());
        
		//icon_chooseFile.sendKeys(abpath);

		Utils.clickElement(driver, btn_saveCorporationGeneralSettings, "Save Settings");        
	}
	
	
	public void verifyuploadCorporationLogo()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 19/02/2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[@id='uploadComponent']//img", "xpath", "Image logo", "General Settings");
	}
	
	public void clickConfirmNoCorporationPortalAccess()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 20/02/2020
		 */
		
		Utils.clickElement(driver, btn_ConfirmNoCorporationAccess, "Confirm");
	}
	
	
	public void verifyNoPortalAccess(String CorporationName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 20/02/2020
		 */
		
		Utils.enterText(driver, searchbox_Corporation, CorporationName, "Corporation Name");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + CorporationName + "')]", "xpath", "Corporation", "No Portal Access");

	}
	
	
	public void verifyEditCorporationAccess()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 20/02/2020
		 */
		
		Utils.waitForElement(driver, "//span[contains(text(),\"You have 'View' access only\")]", globalVariables.elementWaitTime, "xpath");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),\"You have 'View' access only\")]", "xpath", "Error Message", "Portal Setup User Managment under Corporation");
	}
	
	
	public void backToCorporationProfilePage() throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 24/02/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
	
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
	}

	
	public void backToZoomboard() throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 24/02/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);

		Utils.switchWindows(driver, "My Zoomboard", "title", "false");
	}
	
	
	public void backToViewAccessRights() throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 24/02/2020
		 */
	
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - View Access Rights", "title", "false");	
	}
	
	
	public void clickBackToPortalSetup()
	{
		/*
		 * Added By: Yatharth Pandya
		 * Added On: 24/02/2020
		 */
		
		Utils.clickElement(driver, lnk_portalSetup, "Back to portal setup link");
	}
	
	public void clickCustomizeReportsAccessTab()
	{
		/*
		 * Added By: Likitha Krishna 
		 * Added On: 03/03/2020
		 */
		
		Utils.clickElement(driver, tab_customizeReportsAccess, "customize report access tab");
	}
	
//	public List<List<String>> getReportList() throws InterruptedException
//	{
//		/*
//		 * Added By: Likitha Krishna 
//		 * Added On: 03/03/2020
//		 */
//
//		
//		List<List<String>> data = new ArrayList<List<String>>();
//		//List<String> temp = new ArrayList<String>();
//		Thread.sleep(5000);
//		for (int i = 1; i <= 25; i++) {
//			List<String> temp = new ArrayList<String>();
//			temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+i+"]//div/label")).getText());
//			temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+i+"]//div/label/following-sibling::div")).getText());
//			data.add(temp);
//			
//		}
//		
//		Utils.clickDynamicElement(driver, "//ul[@class='k-pager-numbers k-reset']//a[contains(text(),'2')]", "xpath", "clicked on second page link");
//		
//		for (int i = 1; i <= 10; i++) {
//			List<String> temp = new ArrayList<String>();
//			temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+i+"]//div/label")).getText());
//			temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+i+"]//div/label/following-sibling::div")).getText());
//			data.add(temp);	
//		}
//		
//		return data;
//	}
//	
	
	 public List<List<String>> getReportList()
	{
		/*
		 * Added By: Likitha Krishna 
		 * Added On: 03/03/2020
		 */
    	
          int i = 1;
          List<List<String>> data = new ArrayList<List<String>>();
          Utils.waitForElement(driver, "//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']", globalVariables.elementWaitTime, "xpath");
          int noOfPages = (driver.findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']/li"))).size();
          System.out.println(noOfPages);
          for(int n = 1; n < noOfPages ; n++)
          {
            Utils.waitForElement(driver, "//span[@class='k-state-selected' and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");

			List<WebElement> reportList = driver.findElements(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']//div/label"));
			
			for (int j = 1; j <= reportList.size(); j++) 
			{
				List<String> temp = new ArrayList<String>();
	            Utils.waitForElement(driver, "//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+j+"]//div/label", globalVariables.elementWaitTime, "xpath");
				temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+j+"]//div/label")).getText());
				
				Utils.waitForElement(driver, "//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+j+"]//div/label/following-sibling::div", globalVariables.elementWaitTime, "xpath");
				temp.add(driver.findElement(By.xpath("//div[@type='KendoGridComponent']//tr[@class='k-master-row']["+j+"]//div/label/following-sibling::div")).getText());
				data.add(temp);	
				//temp.clear();
			}
  
            if(n != noOfPages-1)
            	Utils.clickDynamicElement(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-60-right']", "xpath", "Next page");
            i=i+1;
          }
   
          return data;
    }
	
	 public void removeReports() throws InterruptedException
	 {
			/*
			 * Added By: Likitha Krishna 
			 * Added On: 03/03/2020
			 */
		
		Utils.waitForElement(driver, "//span[@class='k-pager-info k-label']", globalVariables.elementWaitTime, "xpath");
		String text = driver.findElement(By.xpath("//span[@class='k-pager-info k-label']")).getText();
		String[] wordsInText = text.split(" ");
		int noOfReports = Integer.parseInt(wordsInText[4]);
		 
		 for(int i = 0 ; i < noOfReports ; i++)
		 {
			 Utils.waitForElement(driver, "(//li/a[contains(text(),'Remove')])[1]", globalVariables.elementWaitTime, "xpath");
			 Utils.clickDynamicElement(driver, "(//li/a[contains(text(),'Remove')])[1]", "xpath", "removed");
			 System.out.print(i);
			 System.out.print("\n");
		 }
		 Utils.clickElement(driver, btn_saveReportChanges, "save button");
		 Utils.waitForElement(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", globalVariables.elementWaitTime, "xpath");
	}
	 
	 
	  public void clickLeadEditUser()
		{
			/*
			 * Added By: Kuchinad Shashank
			 * Added On: 4/3/2020
			 */
			Utils.clickDynamicElement(driver, "//td[contains(text(),'Lead')]/following-sibling::td//i[@class='fa fa-pencil-square-o attachment-icon edit-popover']", "xpath", "Edit Icon");
			
			Utils.waitForElement(driver, "//div[@class='row iconLoadingForModal maskOverlay']/img", globalVariables.elementWaitTime, "xpath");
			Utils.waitForElementNotVisible(driver, "//div[@class='row iconLoadingForModal maskOverlay']", "xpath");

		}
		
		
		public void clickCaptainEditUser()
		{
			/*
			 * Added By: Kuchinad Shashank
			 * Added On: 4/3/2020
			 */
			Utils.clickDynamicElement(driver, "//td[contains(text(),'Lead')]/following-sibling::td//i[@class='fa fa-pencil-square-o attachment-icon edit-popover']", "xpath", "Edit Icon");
			
			Utils.waitForElement(driver, "//div[@class='row iconLoadingForModal maskOverlay']/img", globalVariables.elementWaitTime, "xpath");
			Utils.waitForElementNotVisible(driver, "//div[@class='row iconLoadingForModal maskOverlay']", "xpath");

		}
		
		
		public void verifySelectCorporationUnderHQ()
		{
			/*
			 * Added By: Kuchinad Shashank
			 * Added On: 4/3/2020
			 */
			
			Utils.scrollToElement(driver, textbox_selectCorporation);
			Utils.clickElement(driver, textbox_selectCorporation, "Text Box");
			
			Utils.waitForElement(driver, "//div[@id='corp-list']/following-sibling::div//ul[@aria-hidden='false']", globalVariables.elementWaitTime, "xpath");
			
			List<WebElement> temp = driver.findElements(By.xpath("//div[@id='corp-list']/following-sibling::div//li"));
			List<String> corpName =  new ArrayList<>();
			
			corpName.add(globalVariables.corporationNameWithHQ);

			for(int i = 0; i < temp.size(); i++)
			{
				System.out.println(temp.get(i).getText());
				corpName.add(temp.get(i).getText());
			}
			
			System.out.println(corpName);
			Log.message("The corporation list is " + corpName);
			
			if(globalVariables.corporationsUnderHeadQuarter.containsAll(corpName))
			{
				Log.pass("Test case passed as we can link all corporations ", driver, true);
			}
			
			else
			{
				Log.fail("Not able to link all corporations as all corporations are not available", driver, true);
			}
		}
		
		
		public void giveReportAccess(String reportName, String corpUser, boolean value) throws Exception
	   {
		   /*
			 * Added By: Nitisha Sinha
			 * Added On: 04/03/2020
			 */
		   Utils.enterText(driver, txtbox_searchReport, reportName, "search report TextBox");
		   
		   Utils.waitForElementPresent(driver, "//div[@class='k-loading-image']", "xpath");
		   
		   Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-image']", "xapth");
		   
		   Utils.scrollToElement(driver, dropdown_reportAccess);
		   
		   Utils.clickElement(driver, dropdown_reportAccess, "Report Access dropdown");
		   
		   if(value)
		   {
			   if(Utils.isElementPresent(driver, "//span[contains(text(),'" + corpUser + "')]", "xpath"))
			   {
				   Log.message("Report access for "+reportName+" is given to "+corpUser+" by default.");
			   }
			   else
			   {
				   Utils.clickDynamicElement(driver, "//div[@class='k-animation-container']//li[contains(text(),'" + corpUser + "')]", "xpath", "");
				   Log.message("Report access for "+reportName+" is given to "+corpUser);
			   }
		   }
		   
		   else
		   {
			   if(Utils.isElementPresent(driver, "//div[@class='k-widget k-multiselect k-multiselect-clearable k-state-hover']//span[contains(text(),'" + corpUser + "')]", "xpath"))
			   {
				   Utils.clickDynamicElement(driver, "//div[@class='k-animation-container']//li[contains(text(),'" + corpUser + "')]", "xpath", "");
				   Log.message("Report access for "+reportName+" is Removed from "+corpUser);
			   }
			   else
			   {
				   Log.message("Report access for "+reportName+" not given to "+corpUser);
			   }
		   }
		  
	   }
	   
	   
	   public void saveCustomizeReportAccess()
	   {
		   /*
			 * Added By: Nitisha Sinha
			 * Added On: 04/03/2020
			 */
		   
		   Utils.clickElement(driver, btn_saveReportChanges, "save button");
		   
		   Utils.waitForElement(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", globalVariables.elementWaitTime, "xpath"); 
		   
	   }
	   
	   
	   public void backToClientListPage() throws Exception
		{
			/*
			 * Added By: Nitisha Sinha
			 * Added On: 05/03/2020
			 */
			
			driver.close();
			
			Utils.waitForAllWindowsToLoad(1, driver);
		
			Utils.switchWindows(driver, "List", "title", "false");
		}
	   	
	   
	   public void getListOfCorporationUsersUnderCorporation(String portalSetupCorporationName) 
	   {
	       //created by Nitisha Sinha on 2/03/20
	       try{
	           
	           Utils.waitForElement(driver, userlist_UserManagementTab);
	           Thread.sleep(10000);
	    	   List<WebElement> temp = driver.findElements(By.xpath("//div[@id='KendoActiveCorpUserGrid']//tbody/tr/td[2]"));
	             
	    	   for(int i = 1; i < temp.size(); i += 1)
		       {
	    		   globalVariables.corporationUsersUnderCorportion.add(temp.get(i).getText().trim());
	    		   
		       }
		       Log.message("Created a list of Corporation Users under " + portalSetupCorporationName + "Corporation");
	           System.out.println(globalVariables.corporationUsersUnderCorportion);
	       }catch(Exception e){
	           e.printStackTrace();
	           Log.message("",driver,true);
	           Log.fail("Unable to fetch corporation users");
	       }
	   }


	public void verifyEditPortalAccess(boolean value)
	{
	   /*
	    * Created by Kuchinad Shashank
	    * Created on 8/05/2020
	    */
	   
	   if(!value)
	   {
		   Utils.verifyIfDataPresent(driver, "button[id='addRoleButton'][disabled='disabled']", "css", "Disabled Save Changes Button", "General Settingd Tab");
		   
		   clickGeneralSettingsTab();
		   
		   Utils.verifyIfDataPresent(driver, "button[id='SaveChanges'][disabled='disabled']", "css", "Disabled Save Changes Button", "General Settings Tab");
		   
		   clickClientPortalSettingsTab();
		   
		   Utils.verifyIfDataPresent(driver, "button[id='saveButton'][disabled='disabled']", "css", "Disabled Save Changes Button", "General Settings Tab");
	   }
	   else
	   {
		   Utils.verifyIfDataNotPresent(driver, "button[id='addRoleButton'][disabled='disabled']", tab_roleManagement, "css", "Disabled Save Changes Button", "General Settingd Tab");
		   
		   clickGeneralSettingsTab();
		   
		   Utils.verifyIfDataNotPresent(driver, "button[id='SaveChanges'][disabled='disabled']", tab_clientPortalSettings, "css", "Disabled Save Changes Button", "General Settings Tab");
		   
		   clickClientPortalSettingsTab();
		   
		   Utils.verifyIfDataNotPresent(driver, "button[id='saveButton'][disabled='disabled']", tab_clientPortalSettings, "css", "Disabled Save Changes Button", "General Settings Tab");
	   }
	}
	
	
	public void searchUserByRole(String roleName,boolean screenshot)
    {
        /*
         * Added By: Saurav Purohit
         * Added On: 05/07/2021
         */
        
        Utils.waitForElement(driver, searchbox_UserName);
        Utils.waitForElementClickable(driver, searchbox_UserName);
        Utils.enterText(driver, searchbox_UserName, roleName, "Searched Corporation Role");
        Utils.waitForKendoImageCompleted(driver);
        
       /* Utils.waitForElementPresent(driver, "//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']", "xpath");
        Utils.waitForElementNotVisible(driver, "//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']", "xpath");*/
        
       /* WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='KendoActiveCorpUserGrid']//div[@class='k-loading-image']")));*/
        
    }
	
	public void addCorpUser(String firstName, String lastName, String email, String role ) throws Exception
    {
        /*
         * Added By: Souvik Ganguly
         * Added On: 30/08/2021
         * Description: Create a Corp User
         */
        
		Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");
		Utils.waitForElement(driver, btn_addCorporationUser);
		Utils.clickElement(driver, btn_addCorporationUser, "Add New User button");
		Utils.waitForLoadingMaskToDisappearInPortalSetup(driver);
		Utils.enterText(driver, txtbox_firstName, firstName, "First Name");
		Utils.enterText(driver, txtbox_lastName, lastName, "Last Name");
		Utils.enterText(driver, txtbox_email, email, "Email");
		Utils.enterTextInCombo(driver, comboBox_corpUserRole, role, "User Role");
		Utils.clickElement(driver, btn_Save, "Save");		
        Utils.waitForElementNotVisible(driver, "//div[@id='newUserModal']//button[contains(text(),'Save')]", "xpath");
   
 	   
    }
	
	public void verifyCorpUserAdded(String workbookNameWrite, String  sheetName, String firstName,String lastName, String email, String role ) throws Exception
    {
        /*
         * Added By: Souvik Ganguly
         * Added On: 30/08/2021
         * Description: Verify if the corp user is added with all info
         */
       
       Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + firstName +" "+ lastName + "')]", "xpath", firstName + " " + lastName, "Users");
       Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + email + "')]", "xpath", email, "Email");
       Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ firstName +" "+ lastName + "')]/ancestor::tr/td/a/span[contains(text(),'Admin')]", "xpath", role, "Role");
       String corpUserName = firstName + " " + lastName;
	   String directory = System.getProperty("user.dir");
	   ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite); 	
	   writeExcel.setCellData("CorpUserFirstName", sheetName, "Value", firstName);
	   writeExcel.setCellData("CorpUserLastName", sheetName, "Value", lastName);
	   writeExcel.setCellData("CorpUserName", sheetName, "Value", corpUserName);
	   writeExcel.setCellData("CorpUserEmailId", sheetName, "Value", email);
	   Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
    }
	
	public void editCorpUserEmail(String workbookNameWrite, String  sheetName, String corpUser, String editedEmail ) throws Exception
    {
        /*
         * Added By: Souvik Ganguly
         * Added On: 13/09/2021
         * Description: Edit Corp User Email
         */
        
		Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");
		Utils.waitForElement(driver, btn_addCorporationUser);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + corpUser + "')]/ancestor::tr/td/div/i", "xpath", "Edit");
		Utils.waitForLoadingMaskToDisappearInPortalSetup(driver);
		Utils.enterText(driver, txtbox_editEmail, editedEmail, "Edited Email");
		Utils.clickElement(driver, btn_editSave, "Save");		
        Utils.waitForElementNotVisible(driver, "//div[@id='editUserModal']//button[contains(text(),'Save')]", "xpath");
        Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + editedEmail + "')]", "xpath", editedEmail, "Email");
        String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("CorpUserEmailId", sheetName, "Value", editedEmail);
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
    }
	
	public void deleteCorpUser(String corpUserName) throws Exception
    {
        /*
         * Added By: Souvik Ganguly
         * Added On: 15/09/2021
         * Description: Delete an existing Corp user
         */
        
		Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");
		Utils.waitForElement(driver, btn_addCorporationUser);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + corpUserName + "')]/ancestor::tr/td/div/i", "xpath", "Edit");
		Utils.waitForLoadingMaskToDisappearInPortalSetup(driver);
		Utils.clickElement(driver, btn_deleteUser, "Delete User");		
        Utils.waitForElementNotVisible(driver, "//button[contains(text(),'Delete')]", "xpath");
        Utils.waitForElementNotVisible(driver, "//td[contains(text(),'" + corpUserName + "')]", "xpath");
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
    }
	
	
	
}
