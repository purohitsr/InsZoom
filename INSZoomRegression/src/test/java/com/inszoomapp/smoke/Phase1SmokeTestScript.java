package com.inszoomapp.smoke;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import popupCheck.CorpPage;

import com.inszoomapp.pages.CM_AccessRightsPage;
import com.inszoomapp.pages.CM_CalendarPage;
import com.inszoomapp.pages.CM_CaseAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CasePhoneLogPage;
import com.inszoomapp.pages.CM_CaseQuestionnairePage;
import com.inszoomapp.pages.CM_CaseTaskPage;
import com.inszoomapp.pages.CM_ClientAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientFormsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientPhoneLogPage;
import com.inszoomapp.pages.CM_ClientProfilePage;
import com.inszoomapp.pages.CM_ClientQuestionnairePage;
import com.inszoomapp.pages.CM_ClientTaskPage;
import com.inszoomapp.pages.CM_ClientVisaDetailsPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_CustomDataPage;
import com.inszoomapp.pages.CM_DashboardPage;



import com.inszoomapp.pages.CM_DocumentExpirationsPage;
import com.inszoomapp.pages.CM_EditClientRelativePage;
import com.inszoomapp.pages.CM_HistoryInfoPage;
import com.inszoomapp.pages.CM_JobDetailspage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_NotesPage;
import com.inszoomapp.pages.CM_PassportInfoPage;
import com.inszoomapp.pages.CM_PersonalInfoPage;
import com.inszoomapp.pages.CM_PetitionHistoryPage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.CM_TravelInfoPage;
import com.inszoomapp.pages.CM_UsImmigrationInfoPage;
import com.inszoomapp.pages.FNPProfilePage;
import com.inszoomapp.pages.FNP_DocumentsPage;
import com.inszoomapp.pages.FNP_MessagesPage;
import com.inszoomapp.pages.FNP_NotesPage;
import com.inszoomapp.pages.FNP_PassportPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRP_CasePage;
import com.inszoomapp.pages.HRP_ClientPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.ReportsPage;
import com.inszoomapp.pages.StatusDocumentsLinkPage;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;
import com.inszoomapp.globalVariables.*;

@Listeners(EmailReport.class)
public class Phase1SmokeTestScript extends BaseTest {
	public String[] documentName;
	Properties prop = new Properties();
	OutputStream output = null;
	InputStream input = null;

	// case Manager Cred
	String userName = null;
	String password = null;

	//Hrp Cred
	String hrpuserName = null;
	String hrppassword = null;

	//Fnp Cred
	String fnpuserName = null;
	String fnppassword = null;


	String corporationName = null;
	String verifyCountryNameProposedJob = null;
	String editedValueInClient = null;
	String editedValueInCase = null;

	String firstCaseName = "";
	String addedBasicDesc = "";
	String editedBasicDesc = "";
	String addedComprehensiveDesc = "";
	String editedComprehensiveDescription = "";
	String appendedBeneficiaryDocName = "";

	String caseNameForStatus = "";

	// Adding New from Dec 12
	public String addedStepName = "";
	public int editIconLocation = 0;
	public String caseManagerName = "";
	public String caseManagerId = "";
	public String caseMangerType = "";

	boolean addedRemainderStepStatus = false;
	AppDataBase data;

	String nameOfPetition = "Advanced Permission to Return to Unrelinquished Domicile (I-191)";
	String nameOfPetitionTwo = "Affidavit of Support Enforcement/Custody of Amerasians (I-363)";

	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();



		globalVariables.browserUsedForExecution = browserName;

		try {

			input = new FileInputStream("./src/main/resources/config.properties");
		
			prop.load(input);


			switch (env.toUpperCase()) {
			case "QA": {
				userName = prop.getProperty("QA_userName").toString();
				password = prop.getProperty("QA_password").toString();
				corporationName = prop.getProperty("corporationName").toString();
				globalVariables.environmentName = env;
				data = new AppDataQA();

				hrpuserName = prop.getProperty("QA_hrpUserName").toString();
				hrppassword = prop.getProperty("QA_hrpPassword").toString();
				fnpuserName = prop.getProperty("QA_fnpUserName").toString();
				fnppassword = prop.getProperty("QA_fnpPassword").toString();

				break;
			}
			case "STAGE": {
				userName = prop.getProperty("STAGE_userName").toString();
				password = prop.getProperty("STAGE_password").toString();
				corporationName = prop.getProperty("corporationName").toString();
				globalVariables.environmentName = env;
				data = new AppDataStage();
				hrpuserName = prop.getProperty("STAGE_hrpUserName").toString();
				hrppassword = prop.getProperty("STAGE_hrpPassword").toString();
				fnpuserName = prop.getProperty("STAGE_fnpUserName").toString();
				fnppassword = prop.getProperty("STAGE_fnpPassword").toString();
				break;
			}
			case "UAT": {
				userName = prop.getProperty("UAT_userName").toString();
				password = prop.getProperty("UAT_password").toString();
				corporationName = prop.getProperty("corporationName").toString();
				globalVariables.environmentName = env;
				data = new AppDataUAT();
				hrpuserName = prop.getProperty("UAT_hrpUserName").toString();
				hrppassword = prop.getProperty("UAT_hrpPassword").toString();
				fnpuserName = prop.getProperty("UAT_fnpUserName").toString();
				fnppassword = prop.getProperty("UAT_fnpPassword").toString();
				break;
			}
			case "PROD": {
				userName = prop.getProperty("PROD_userName").toString();
				password = prop.getProperty("PROD_password").toString();
				corporationName = prop.getProperty("corporationName").toString();
				globalVariables.environmentName = env;
				data = new AppDataProd();
				hrpuserName = prop.getProperty("PROD_hrpUserName").toString();
				hrppassword = prop.getProperty("PROD_hrpPassword").toString();
				fnpuserName = prop.getProperty("PROD_fnpUserName").toString();
				fnppassword = prop.getProperty("PROD_fnpPassword").toString();
				break;
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	// Test to Run Corporation level Smoke
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void testCaseManagerCorporationPage(String browser) throws Exception {
		
	// Refactored By : Sauravp On Feb 2020
		
    globalVariables.testCaseDescription = "Case Manager: Page load for all the cases";
    
    final WebDriver driver = WebDriverFactory.get(browser);
    
    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
            login.clickAgreeButton(true);
			
			/* Corporation Pages Validation*/
                        
			// click corporation 
			caseManagerHomePage.clickCorporationTab(true);
			
			//click on add button
			     
			CM_CorporationPage corpPage = new CM_CorporationPage(driver);
			     
			corpPage.clickAddCorporationButton(true);
			
			caseManagerHomePage.clickCorporationTab(true);
			     
			// click any corporation  name 
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickFirstCorpNameFromList(true);
	
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	   //  Test to Run Smoke Tests on Client level 
	
		@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void testCaseManagerClientPages(String browser) throws Exception {
			
			// Refactored By : Sauravp On Feb 2020
			
		    globalVariables.testCaseDescription = "Case Manager: Page load for all the Client Pages";
	        final WebDriver driver = WebDriverFactory.get(browser);
	        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	        
	        try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
            login.clickAgreeButton(true);
				
			// click corporation 
			caseManagerHomePage.clickClientTab(true);
				
			//click on add button
				     
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				     
			clientListPage.clickAddClientButton(true);
			
			clientListPage.verifySaveButtonInAddNewClientPage();
				     
			caseManagerHomePage.clickClientTab(true);
			
			clientListPage.clickOnClientName(data.clintFirstNameForSmokeTest, true);
			
			/*clientListPage.clickFirstClientOnClientListPage();*/
			
			clientListPage.verifyEditButtonInClientListPage();
			
			
			// click relatives tab
			clientListPage.clickRelativesTab();
			
			

			CM_EditClientRelativePage relativePage = new CM_EditClientRelativePage(driver);
			// click add button
			relativePage.clickAddRelativeButton();
			
			relativePage.verifyRelativesPage();

		       // click add new button link
			relativePage.clickAddNewRelativeLink();
			
			relativePage.verifyRelativesRegistrationPage();
			
			relativePage.clickAddRelativeButton();

			// click on search link
			relativePage.clickSearchExistingLink();

		       // click on profile tab
			relativePage.verifySerachForExistingClientPage();

		      // click on edit profile tab
			clientListPage.clickProfileTab();
			
		      // Verify User Lands in Profile Page
			CM_ClientProfilePage clientProfile = new CM_ClientProfilePage(driver);
			
			clientProfile.verifyClientProfilePage();
			

		      // click on personal info tab
			clientListPage.clickPersonalInfoTab(true);
			
			CM_PersonalInfoPage personalInfo = new CM_PersonalInfoPage(driver);
			
			personalInfo.verifyClientContactInfoPage();

		     // click on edit personal info
			personalInfo.clickEditPersonalInfoButton();
			
			personalInfo.verifyEditClientContactInfoPage();

		    // click on document expirations tab
		       clientListPage.clickDocumentExpirationTab(true);
		       
		       CM_DocumentExpirationsPage docExpPage = new CM_DocumentExpirationsPage(driver);
		       
		       docExpPage.verifyDocumentExpirationDatesPage();

		    // click on document expirations Add button
		       docExpPage.clickAddNewButton();
		       
		    // Verify Page Is loaded
		       docExpPage.verifyAddFirmDefinedDocumentExpirationDatesPage();

		   // click on passport info tab
		       clientListPage.clickPassportInfoTab();
		       
		       CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);
		       
		       passportInfoPage.verifyPassportInfoPageTitle();

		   // click on passport info add button
		       passportInfoPage.clickAddAdditionalPassportDetailsButton();
		       
		  // Verify Additional Details Page Title
		       passportInfoPage.verifyPassportInfoAdditionalDetailsPage();
		       

		  // click on us immigration info tab
		       CM_UsImmigrationInfoPage usImmigration =clientListPage.clicktUSImmigrationTab();
		       
		       usImmigration.verifyUSImmigrationInfoPage();
		       
		    // click on Add New button
		       usImmigration.clickAddNewButton();
		       
		       usImmigration.verifyEditCurrentArrivalInfoDetailsPage();

		  // click on job details tab
		       CM_JobDetailspage jobdatils = clientListPage.clickJobDetailsTab(true);
		       
		  // Verify Job Details Page
		      jobdatils.verifyJobdetailsPage();
		     
		   // Verify Current Job Details Header
		      jobdatils.verifyCurrentJobdInfo();
		    
		      CM_HistoryInfoPage historyInfoPage =clientListPage.clickHistoryInfo(true);

		    // click on Add New History Button
		      historyInfoPage.clickNewAddressHistory();
		      
		   // Verify Add History Page
		      historyInfoPage.verifyAddHistoryPage();
		     
                   // click on add education history
		      historyInfoPage.clickNewEducationHistory();
		    
		   // Verify Education History Page
		      historyInfoPage.verifyEducationHistoryPage();

		   // click on add employment history
		      historyInfoPage.clickAddNewEmploymentHistory();
		      
		   // Verify Education History Page
		      historyInfoPage.verifyAddEmploymentHistoryPage();

		  // click on arrival info tab
		      CM_TravelInfoPage travelInfoPage =clientListPage.clickArrivalDepartureInfoTab();
		      
		   // Verify Arrival Departure Info Page
		      travelInfoPage.verifyArrivalDeparturePage();

       		  // click on custom data tab
		     clientListPage.clickCustomDataTab();
		     
		     CM_CustomDataPage custom = new CM_CustomDataPage(driver);
		     
		  // Verify Custom Data Page 

		     custom.verifyCustomDataPage();		     
		  // click on visa tab
		     clientListPage.clickVisasTab(true);
		     
		     CM_ClientVisaDetailsPage visaDetails = new CM_ClientVisaDetailsPage(driver);
		     // Verify VisaDetails Page 
		     visaDetails.verifyVisaDetailsPage();

		    // click status docs tab
		     clientListPage.clickStatusDocsTab(true);
		     
		     StatusDocumentsLinkPage statusDoc = new StatusDocumentsLinkPage(driver);
		     
		     // click attach status doc
		     statusDoc.verifyClientStatusDocsPage();

		     // click petition history tab
		     clientListPage.clickImmigrationStatusHistoryTab(true);
		     
		     //verify View Petition History Details page
		     
		     CM_PetitionHistoryPage page = new CM_PetitionHistoryPage(driver);
		     page.verifyViewImmigrationStatusHistoryDetailsPage();

		     // click petition history add
		     page.clickAndVerifyAddImmigrationStatusHistory();

		     // click forms tab
		     clientListPage.clickFormsTab();
		     
		     //Verify Forms Page
		     
		     CM_ClientFormsPage formPage = new CM_ClientFormsPage(driver);
		     formPage.verifyClientFormsPage();

		    // click Email forms button
		     formPage.clickEmailForms();
		     
		    //Verify ComposeEmail Page
		      formPage.verifyComposeEmailsPage();

		    // click forms Tab again
		    clientListPage.clickFormsTab();

       		// click form add
//		    formPage.clickAddFroms();
		    
		 // Verify Link Immigration Page
//		    formPage.verifyLinkImmigrationFormsPage();

		  // click questionnaire
		     clientListPage.clickQuestionnairesTab();
		     
		     CM_ClientQuestionnairePage questionnaire = new CM_ClientQuestionnairePage(driver);
		     questionnaire.verifyQuestionnairesPage();

		   // click questionnaire add or remove
		      questionnaire.chooseAddRemoveQuestionnaires();
		     
		  // verify search questionnaire Page
		     questionnaire.verifySearchQuestionnairePage();

		  // click on DocCheckListTab
         	 clientListPage.clickOnDocumentsTab();

		 // Verify DocumentPage
         	 CM_ClientDocumentsPage documentPage = new CM_ClientDocumentsPage(driver);
         	 documentPage.verifyDocumentsPage();

						// click on digital docs access rights
//						clickDigitalDocsAccessRights(true);

						// click on letter ms word
//						clickLetterMsWord(true);

						// click on letter from server option
//						clickLetterMsWordServer(true);

						// click on letter from local option
//						clickLetterMsWordLocal(true);

		     // click on email tab
		     clientListPage.clickEmailsTab();
		     
		     CM_ClientEmailsPage email = new CM_ClientEmailsPage(driver);
		     
		  // Verify Emails Page
		     email.verifyEmailsPage();
		     
		  // click compose Email button
		     email.clickComposeEmailButton();
		   
		  // Verify compose Email Page
		     email.verifyComposeEmailPage();
		     
	   

	          // click on notes tab
		     clientListPage.clickNotesTabInClient(true);
		     
		  // Verify notes List Page
		     CM_NotesPage notes = new CM_NotesPage(driver);
		     notes.verifyNotesListPage();

	          // click on phone log tab
		     CM_ClientPhoneLogPage phoneLog =clientListPage.clickPhoneLogTab(true);
		  // Verify PhoneLogs  List Page
		     phoneLog.verifyPhoneLogPage();

	          // click on appointment tab
		     clientListPage.clickAppointmentsActivitiesTab(true);
		     
		  //Verify Appointment/Activities page
		     CM_ClientAppointmentsAndActivitiesPage apponitment = new CM_ClientAppointmentsAndActivitiesPage(driver);
		     apponitment.verifyPhoneLogPage();

	     // click on tasks tab
		clientListPage.clickTaskTab(true);

		CM_ClientTaskPage taskPage = new CM_ClientTaskPage(driver);
             // verify task list page
		taskPage.verifyTaskListPage();

	    // click on communication summary
		clientListPage.clickCommunicationSummaryInClient(true);

	    // click on access rights
		clientListPage.clickAccessRightsTab(true);
		
		CM_AccessRightsPage accessRights = new CM_AccessRightsPage(driver);
		
		 // verify client Access Rights page
		accessRights.verifyClientAccessRightsPage();
		
		// click on change login id
		clientListPage.clickChangeLoginIdClientTab(true);
		
			Log.testCaseResult();
			
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
	
		// Test to Run Case level Smoke
		
		@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	     public void testCaseManagerCasePages(String browser) throws Exception {
		
		// Refactored By : Sauravp On Feb 2020
		
		globalVariables.testCaseDescription = "Case Manager: Verify Page Loads for all case related Page";
	    
	    final WebDriver driver = WebDriverFactory.get(browser);
	    
	    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
	            login.clickAgreeButton(true);
	            
	            CM_CaseListPage cm_CaseListPage =caseManagerHomePage.clickCaseTab(true);
	            
	            cm_CaseListPage.clickAddCase();
	            
	            cm_CaseListPage.verifyCaseCreationPage();
	            
	            cm_CaseListPage.clickOnCaseId(data.caseIdForSmokeTest, true);
	            
	            cm_CaseListPage.clickJobDetails(true);
	            
	            cm_CaseListPage.clickProposedJobEdit(true);
	            
	            cm_CaseListPage.verifyEditProposedJobInfoPage();
	            
	            cm_CaseListPage.clickBlanketApplicants();
	            
	            cm_CaseListPage.verifyCaseBlanketApplicantsPage();
	            
	            cm_CaseListPage.clickAddNewBlanketApplicants();
	            
	            cm_CaseListPage.verifyAddCaseBlanketApplicantsListPage();
	            
	            cm_CaseListPage.clickSnapShotTab();
	            
	            cm_CaseListPage.verifyCaseInformationReportPage();
	            
	            cm_CaseListPage.clickDetailsDatesTab(true);
	            
	            cm_CaseListPage.verifyDetailsAndDatesPage();
	            
	            cm_CaseListPage.clickDetailsDatesEdit();
	            
	            cm_CaseListPage.verifyEditCaseDetailsPage();
	            
                // click edit button in Visa Priority dates Info sec
	            cm_CaseListPage.clickVisaPriorityDatesEdit();
	            
	         // Verify Edit Priority Dates Page	            
	            cm_CaseListPage.verifyEditPrioritydatesPage();
	            
	            
	         // click Receipt number Tab ***Left this as this has changed recently
	            
	            cm_CaseListPage.clickManagersContactsTab(true);
	            
	            cm_CaseListPage.verifyCaseManagerInfoPage();
	            
                cm_CaseListPage.clickAttachRemoveSigningCaseManager();
	            
	            cm_CaseListPage.verifyAddRemoveCaseManagerPage();
	            
                cm_CaseListPage.clickStatusDocsTab(true);
	            
	            cm_CaseListPage.verifyStatusDocumentsPage();
	            
	            cm_CaseListPage.clickAttachRemoveStatusDocs();
	            
	            cm_CaseListPage.verifyAttachRemoveStatusDocsPage();
	            
	            cm_CaseListPage.clickCustomDataTab(true);
	            
	            cm_CaseListPage.verifyViewCustomFiledsPage();
	            
	            cm_CaseListPage.clickEditCustomfields();
	            
	            cm_CaseListPage.verifyEditCustomFieldsPage();
	            
                cm_CaseListPage.clickAttachRemoveCustomfields();
	            
	            cm_CaseListPage.verifyAttachRemoveCustomfieldsPage();
	            
                cm_CaseListPage.clickCopyCustomfields();
	            
	            cm_CaseListPage.verifyCopyCustomFieldsPage();
	            
	            cm_CaseListPage.clickDocsCheckListOrDocumentsTab(true);
	            
	            cm_CaseListPage.verifyDocumentsPage();
	            
	            cm_CaseListPage.clickDocsCheckListOrDocumentsTab(true);
	            
	            CM_CaseDocChecklistPage docListPage = new CM_CaseDocChecklistPage(driver);
	            
	            docListPage.clickCopyFromTemplate();
	            
	            docListPage.verifyCopyFromPetitionPopup();
	            
//	            cm_CaseListPage.clickFormsTab();
	            
	            cm_CaseListPage.clickAndverifyFormsListPage();
	            
	           /* CM_CaseFormPage formsPage = new CM_CaseFormPage(driver);
	            
	            formsPage.clickAddForms(true);
	            
	            formsPage.VerifyLinkImiigrationPage();
	            
	            formsPage.clickBulkPrint(true);
	            
	            formsPage.VerifyBulkPrintPage();*/
	            
	            cm_CaseListPage.clickQuestionnairesTab();
	            
	            CM_CaseQuestionnairePage questionnairePage = new CM_CaseQuestionnairePage(driver);
	            
	            questionnairePage.verifyQuestionnairesListPage();
	            
	            questionnairePage.clickAddRemoveQuestionnaire(true);
	            
	            questionnairePage.VerifySearchQuestionnairePage();
	            
	            cm_CaseListPage.clickStatusRemindersStepsTab(true);
	            
	            cm_CaseListPage.verifyStatusReminderPage();
	            
	            cm_CaseListPage.clickPhoneLogTab();
	            
	            CM_CasePhoneLogPage phoneLogPage = new CM_CasePhoneLogPage(driver);
	            
	            phoneLogPage.verifyPhoneLogPage();
	            
	            cm_CaseListPage.clickAppointmentActivityTab(true);
	            
	            CM_CaseAppointmentsAndActivitiesPage appointmentPage = new CM_CaseAppointmentsAndActivitiesPage(driver);
	            
	            appointmentPage.verifyAppointmentActivityPage();
	            
	            cm_CaseListPage.clickTaskTab(true);
	            
	            CM_CaseTaskPage taskPage = new CM_CaseTaskPage(driver);
	            
	            taskPage.verifyTasksListPage();
	            
	            cm_CaseListPage.clickEmailsTab();
	            
	            CM_CaseEmailsPage emailPage = new CM_CaseEmailsPage(driver);
	            
	            emailPage.verifyEmailsPage();
	            
	            cm_CaseListPage.clickCommunicationSummaryInCase(true);
	            
	            cm_CaseListPage.verifyCommunicationSummaryPage();
	            
	            
	                        
	            
	Log.testCaseResult();
	} catch (Exception e) {
		Log.exception(e, driver);
	} finally {
		Log.endTestCase();
		driver.quit();
	}		
}
	
		// Test to Run Reports Smoke
		
		@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
        public void testCaseManagerCaseReportsPages(String browser) throws Exception {
			
		// Refactored By : Sauravp On Feb 2020
		
		globalVariables.testCaseDescription = "Case Manager: Verify Page Loads for all Reports Page related to case";
	    
	    final WebDriver driver = WebDriverFactory.get(browser);
	    
	    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	    

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
	            login.clickAgreeButton(true);
	            
	          /*  CM_CaseListPage cm_CaseListPage =caseManagerHomePage.clickCaseTab(true);
	            
	            cm_CaseListPage.clickOnCaseId(data.caseIdForSmokeTest, true);*/
	            
	            CM_DashboardPage dashBoardPage = new CM_DashboardPage(driver);
	            
	            dashBoardPage.clickReports1();
	            
	            ReportsPage reportPage = new ReportsPage(driver);
	            
	            reportPage.clickFavoriteReportsTab();
	            
	            reportPage.verifyFavoriteReportPage();
	            
	            reportPage.clickCustomizedReportsTab();
	            
	            reportPage.verifyCustomizeReportsPage();
	            
	            reportPage.clickManagementReportsTab();
	            
	            reportPage.verifyManagementReportsPage();
	            
	            reportPage.clickVisaPriorityDatesReportsTab();
	            
	            reportPage.verifyVisaPriorityDatesReportsPage();
	
	            Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}		
		}
	
		// Test to Run Calendar level Smoke
		@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
        public void testCaseManagerCalendarPages(String browser) throws Exception {
			
			// Refactored By : Sauravp On Feb 2020
    		
    		globalVariables.testCaseDescription = "Case Manager: Verify Page Loads for all Calendar Page";
    	    
    	    final WebDriver driver = WebDriverFactory.get(browser);
    	    
    	    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
    	    
    	   
    			try {
    				LoginPageTest login = new LoginPageTest(driver, webSite);
    				
    				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
    				
    	            login.clickAgreeButton(true);
    	            
    	            CM_CaseListPage cm_CaseListPage =caseManagerHomePage.clickCaseTab(true);
    	            
    	            cm_CaseListPage.clickOnCaseId(data.caseIdForSmokeTest, true);
    	            
    	            CM_DashboardPage dashBoardPage = new CM_DashboardPage(driver);
    	            
    	            dashBoardPage.clickCalendarAppointmentActivityReminder();
    	            
    	            CM_CalendarPage calendarPage = new CM_CalendarPage(driver);
    	            
    	            calendarPage.verifyCalendarPage();
    	            
    	            calendarPage.clickTaskTab();
    	            
    	            calendarPage.verifyCalendarTaskListPage();
    	            
    	            calendarPage.clickMessagesAndCallsTab();
    	            
    	            calendarPage.verifyMessagesAndCallsPage();      
    	
    	            Log.testCaseResult();
    			} catch (Exception e) {
    				Log.exception(e, driver);
    			} finally {
    				Log.endTestCase();
    				driver.quit();
    			}		
    		}
	
		// Test to Run Settings level Smoke
		
		    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
            public void testCaseManagerSettingsPages(String browser) throws Exception {
		    	
		    // Refactored By : Sauravp On Feb 2020
    		
    		globalVariables.testCaseDescription = "Case Manager: Verify Page Loads for Settings Pages";
    	    
    	    final WebDriver driver = WebDriverFactory.get(browser);
    	    
    	    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
    	    
    			try {
    				LoginPageTest login = new LoginPageTest(driver, webSite);
    				
    				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
    				
    	            login.clickAgreeButton(true);
    	            
    	            CM_CaseListPage cm_CaseListPage =caseManagerHomePage.clickCaseTab(true);
    	            
    	            cm_CaseListPage.clickOnCaseId(data.caseIdForSmokeTest, true);
    	            
    	            CM_DashboardPage dashBoardPage = new CM_DashboardPage(driver);
    	            
    	            dashBoardPage.clickSettings(true);
    	            
    	            CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
    	            
    	            settingsPage.verifyAdvancedSettingsPage();
    	            
    	            settingsPage.clickAdvancedSettingsTab();
    	            
    	            settingsPage.verifySearchSettingsPage();
    	            
    	            settingsPage.clickOrganizationToolsTab();
    	            
    	            settingsPage.verifyToolsOverviewPage();
    	            
    	            settingsPage.clickChangeMyPasswordTab();
    	            
    	            settingsPage.VerifyChangeMyPasswordWindow();
    	            
                    settingsPage.clickChangeOthersPasswordTab();
    	            
    	            settingsPage.VerifyChangeOthersPasswordWindow();
    	            
    	            settingsPage.clickBroadcastMessagestab();
    	            
    	            settingsPage.VerifyBroadcastMessagesPage();
    	            
                    settingsPage.clickDiskSpaceUsageDetailstab();
    	            
    	            settingsPage.VerifyDiskSpaceUsageDetailsPage();
    	            Log.testCaseResult();
    			} catch (Exception e) {
    				Log.exception(e, driver);
    			} finally {
    				Log.endTestCase();
    				driver.quit();
    			}		
    		}
        
		 // Test to Run KnowledgeBase level Smoke
		    
		    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
            public void testKnowledgeBasePages(String browser) throws Exception {
		    	
		    	// Refactored By : Sauravp On Feb 2020
        		
        		globalVariables.testCaseDescription = "Case Manager: Verify Important Page Loads for KnowledgeBase";
        	    
        	    final WebDriver driver = WebDriverFactory.get(browser);
        	    
        	    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        	    
        	   
        			try {
        				LoginPageTest login = new LoginPageTest(driver, webSite);
        				
        				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
        				
        	            login.clickAgreeButton(true);
        	            
        	            CM_CaseListPage cm_CaseListPage =caseManagerHomePage.clickCaseTab(true);
        	            
        	            cm_CaseListPage.clickOnCaseId(data.caseIdForSmokeTest, true);
        	            
        	            CM_DashboardPage dashBoardPage = new CM_DashboardPage(driver);
        	            
        	            dashBoardPage.clickKnowledgeBase(true);
        	            
        	            CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
        	            
        	            knowledgeBasePage.clickEmailTemplatesTab();
        	            
        	            knowledgeBasePage.VerifyFirmDefinedEmailTemplatePage();
        	            
        	            knowledgeBasePage.clickDocsCheckListTab();
        	            
        	            knowledgeBasePage.VerifyMyFirmDocCheckListPage();
        	            
        	            knowledgeBasePage.clickQuestionnaireTemplateTab();
        	            
        	            knowledgeBasePage.VerifyQuestionnaireListPage();
        	            
        	            knowledgeBasePage.clickCopyMergeQuestionnaireTab();
        	            
        	            knowledgeBasePage.VerifyCopyMergeQuestionnairePage();
        	            
        	            knowledgeBasePage.clickLetterTemplateTab();
        	            
        	            knowledgeBasePage.VerifyLetterTemplatePage();
        	            
        	            knowledgeBasePage.clickLetterTemplateMSWordTab();
        	            
        	            knowledgeBasePage.VerifyLetterTemplateMSWordPage();
        	            
        	            Log.testCaseResult();
        			} catch (Exception e) {
        				Log.exception(e, driver);
        			} finally {
        				Log.endTestCase();
        				driver.quit();
        			}		
        		}
            
		 // Test to HRP  level Smoke
		    
            @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
        	public void testHRPortalSmoke(String browser) throws Exception {
        		
            	// Refactored By : Sauravp On Feb 2020
            	
    	        globalVariables.testCaseDescription = "HRPortal: Verify Page load for all important pages";
        	        
        		final WebDriver driver = WebDriverFactory.get(browser);
        		
                driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
                        
        		try {	
        			LoginPageTest login = new LoginPageTest(driver, webSite);
        			
        			HrpHomePage caseManagerHomePage = login.hrpLogin(hrpuserName, hrppassword, true);	
        			
        			HrpHomePage homePage = new HrpHomePage(driver);
        			
        			homePage.clickClientTab();
        			
        			homePage.VerifyClientPage();
        			
        			// Click On first Client Link
        			homePage.clickFirstClientName();
        			
        			//Verify Client Page Opens 
        			homePage.VerifyClientdetailsPage();
        			
        			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
        			
        			//Click Case Link
        			clientpage.clickCaseLink(data.caseIdForSmokeTest);
        			
        			HRP_CasePage casePage = new HRP_CasePage(driver);
        			
        			// click Case Request Tab
        			homePage.clickCaseRequestTab();
        			
        			// Verify Case Request Tab
        			homePage.VerifyCreateNewCaseRequestPage();
        			
        			// click Case Request for Existing Client Button
        			homePage.clickCaseRequestForExistingClient();
        			
        			// Verify Case Request For Existing Client Page
        			homePage.VerifyCaseRequestForExistingClientPage();
        			
        			// click Case Request for New Client Button
        			homePage.clickCaseRequestForNewClient();
        			
        			// Verify Case Request For New Client Page
        			homePage.VerifyCaseRequestForNewClientPage();
        			
        			// click reports Tab 
        			homePage.clickReportsTab();
        			
        			// Verify Shared Reports Page
        			homePage.verifySharedReportsPage();
        			
        			Log.testCaseResult();
        		} catch (Exception e) {
        			Log.exception(e, driver);
        		} finally {
        			Log.endTestCase();
        			driver.quit();
        		}
        	}
            
            
         // Test to FNP  level Smoke
            @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
        	public void testFNPortalSmoke(String browser) throws Exception {
        		
            	// Refactored By : Sauravp On Feb 2020
            	
    	        globalVariables.testCaseDescription = "FNPortal: Verify Page load for all important pages";
        	        
        		final WebDriver driver = WebDriverFactory.get(browser);
        		
                driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
                        
        		try {	
        			LoginPageTest login = new LoginPageTest(driver, webSite);
        			
        		    login.hrpLogin(fnpuserName, fnppassword, true);	
        			
        			FnpHomePage homePage = new FnpHomePage(driver);
        			
        			//click Passport Tab
        			homePage.clickPassportTab();
        			
        			//verify Passport Page
        			FNP_PassportPage passportPage = new FNP_PassportPage(driver);
        			passportPage.verifyPassportPage();
        			
        			//click Messages Tab
        			homePage.clickMessagesTab();
        			
        			//Verify Messages Page
        			FNP_MessagesPage fnpMessages = new FNP_MessagesPage(driver);
        			fnpMessages.verifyMessagesPage();
        			
        			//click Compose Email Button
        			fnpMessages.clickComposeEmail(true);
        			
        			fnpMessages.verifySendEmailPage();
        			
        			//click on My Profile
        			homePage.clickMyProfile();
        			
        			//verify My Profile Page
        			FNPProfilePage profilePage = new FNPProfilePage(driver);
        			profilePage.verifyProfilePage();
        			
        			//Verify Background section
        			profilePage.clickBackgroundTab();
        			
        			profilePage.verifyBackgroundSection();
        			
        			//click Home Pgge Icon
        			homePage.clickHomeIcon();
        			
        			//verify Home Page
        			
        			homePage.verifyHomePage();
        			
        			//click Case Link
        			
        			homePage.clickCaseLink(data.caseIdForSmokeTest);
        			
        			//verify Case Details Page 
        			
        			homePage.verifyCaseDetailsPage();
        			
        			// document page
        			homePage.clickDocumentsTab();
        			//verify Documents Page
        			
        			FNP_DocumentsPage documentsPage = new FNP_DocumentsPage(driver);
        			documentsPage.verifydocumentsPage();
        			
        			// document upload
        			documentsPage.clickUpload(true);
        			
        			//verify Upload Page 
        			documentsPage.verifyUploadFilePage();
        			
        			// fnp notes page
        			homePage.clickNotesTab();
        			
        			FNP_NotesPage notesPage = new FNP_NotesPage(driver);
        			
        			// fnp Notes page
        			notesPage.verifyNotesPage();
        			
        			// fnp travel page
        			homePage.clickTravelTab();
        			
        			//verify Travel Page
        			homePage.verifyTravelPage();
        			
        			// fnp case request page
        			homePage.clickCaseRequestTab();
        			
        			//verify Case Request Page
        		    homePage.verifyCaseRequestPage();
        			
        			Log.testCaseResult();
        		} catch (Exception e) {
        			Log.exception(e, driver);
        		} finally {
        			Log.endTestCase();
        			driver.quit();
        		}
        	}
        
	}