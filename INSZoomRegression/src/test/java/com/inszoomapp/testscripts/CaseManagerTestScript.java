package com.inszoomapp.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.client.pages.CommunicationSummaryPage;
import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.AccessCodePage;
import com.inszoomapp.pages.CM_BlanketApplicantsPage;
import com.inszoomapp.pages.CM_CalendarPage;
import com.inszoomapp.pages.CM_CaseAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CasePhoneLogPage;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_CaseQuestionnairePage;
import com.inszoomapp.pages.CM_CaseTaskPage;
import com.inszoomapp.pages.CM_ChangeLoginIdClientPage;
import com.inszoomapp.pages.CM_ClientAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientFormsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientPhoneLogPage;
import com.inszoomapp.pages.CM_ClientProfilePage;
import com.inszoomapp.pages.CM_ClientQuestionnairePage;
import com.inszoomapp.pages.CM_ClientTaskPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_Client_Relative_VisaPage;
import com.inszoomapp.pages.CM_CorporationCustomDataPage;
import com.inszoomapp.pages.CM_CorporationEmailPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_CorporationQuestionnairePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_DetailsAndDatesPage;
import com.inszoomapp.pages.CM_DistrictOfficeInfoPage;
import com.inszoomapp.pages.CM_DocumentExpirationsPage;
import com.inszoomapp.pages.CM_EditClientRelativePage;
import com.inszoomapp.pages.CM_EmailsPage;
import com.inszoomapp.pages.CM_HistoryInfoPage;
import com.inszoomapp.pages.CM_InvoicePage;
import com.inszoomapp.pages.CM_JobDetailspage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_Letters_MSWordPage;
import com.inszoomapp.pages.CM_ManagerContactsPage;
import com.inszoomapp.pages.CM_MySettingsPage;
import com.inszoomapp.pages.CM_NotesPage;
import com.inszoomapp.pages.CM_PassportInfoPage;
import com.inszoomapp.pages.CM_PersonalInfoPage;
import com.inszoomapp.pages.CM_PetitionHistoryPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.CM_Reports_3_0_Page;
import com.inszoomapp.pages.CM_ServiceCentreInfoPage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.CM_TravelInfoPage;
import com.inszoomapp.pages.CM_UsImmigrationInfoPage;
import com.inszoomapp.pages.CaseStepPage;
import com.inszoomapp.pages.FNPNewsPage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FNP_DocumentsPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.inszoomapp.pages.ReportsPage;
import com.inszoomapp.pages.StatusDocumentsLinkPage;
import com.inszoomapp.reports.CustomizedReportsPage;
import com.inszoomapp.reports.FavoriteReportPage;
import com.inszoomapp.reports.ManagementReportsPage;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class CaseManagerTestScript extends BaseTest
{
	Properties prop = new Properties();
	InputStream input = null;
	String userName = null;
	String password = null;
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	String corporationName = "Automation";
	AppDataBase data ;
	
	
	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();
		
		sheetName = (System.getProperty("sheetName") != null ? System.getProperty("sheetName")
				: context.getCurrentXmlTest().getParameter("sheetName"));

		
		globalVariables.browserUsedForExecution = browserName;
		String os = System.getProperty("os.name");

		try {

			input = new FileInputStream("./src/main/resources/config.properties");
			prop.load(input);

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					
					
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Stg.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					}
					
					
					/*workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";*/
					//sheetName = "Inszoom";
					ReadWriteExcel readExcel = new ReadWriteExcel();
					userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					password = readExcel.initTest(workbookName, sheetName, "CM_password");
					break;
				}
				case "DEV": {
					data = new AppDataDev();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Dev.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Dev.xls";
					sheetName = "Inszoom";
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_QA.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_QA.xls";
					}else{
					workbookName = "testdata\\data\\Regression_QA.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_QA.xls";
					}
					
					/*workbookName = "testdata\\data\\Regression_QA.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_QA.xls";*/
					sheetName = "Inszoom";
					ReadWriteExcel readExcel = new ReadWriteExcel();
					userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					password = readExcel.initTest(workbookName, sheetName, "CM_password");
					

					break;
				}
				case "AST":
				{
					globalVariables.environmentName = env;
					break;
				}
				case "PROD": 
				{
					data = new AppDataProd();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Prod.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Prod.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Prod.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Prod.xls";
					}
					/*workbookName = "testdata\\data\\Regression_Prod.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Prod.xls";*/
					//sheetName = "Inszoom";
					ReadWriteExcel readExcel = new ReadWriteExcel();
					userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					password = readExcel.initTest(workbookName, sheetName, "CM_password");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void skipTestCase()
	{
			if(globalVariables.SKIP_REMAINING_TESTS)
			{
				throw new SkipException("Skipping the testCase");
			}
	}
	
	
	@Test(groups={"mandatory"}, description = "Creation of Corporation in CM Login", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_1(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Case Manager: Creation of Corporation in Case Manager";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(true);

			CM_CorporationPage corporationPage = caseManagerHomePage.clickCorporationTab(true);

			corporationPage.clickAddCorporationButton(true);
            
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);

			corporationPage.clickSaveCorporationButton(true);

			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"} , description = "Creation of Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		globalVariables.clientFirstName = globalVariables.clientFirstName + strDate;
		globalVariables.clientLastName = globalVariables.clientLastName + strDate;
		
		String[] name = {globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientFirstName + " " + globalVariables.clientLastName};
		String[] nameKeys = {"ALoginSavedFirstClientName", "ALoginSavedLastClientName", "ALoginSavedClientName"};;

		globalVariables.testCaseDescription = "Case Manager: Creation of client/FN in a corporation in Case Manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickAddClientButton(true);

			clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, corpName, globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientEmailID);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);

			clientListPage.clickSaveClientButton(true);

			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups={"mandatory"}, description = "Case Manager: Change Login Id and password for FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_30_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager: Change Login Id and Password for new Corp client and verify that login is successful with new credentials in FN Portal ";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ChangeLoginIdClientPage changeLoginIdPage = new CM_ChangeLoginIdClientPage(driver);
			
			clientListPage.clickChangeLoginIdClientTab(true);

			changeLoginIdPage.updateLoginId(workbookNameWrite, sheetName, true);

			clientListPage.clickChangeLoginIdClientTab(true);

			changeLoginIdPage.clickAndChangePassword(workbookNameWrite, sheetName, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"} , description = "FNP login Access: Fill the security question to change already reset password", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_30_1")
	public void CM_TC_67_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPasswordCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP login Access: Fill the security question to change already reset password";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpPassword, true);

			login.clickAgreeButton(false);

			login.changeFNPPassword(fnpNewPassword, fnpPassword, true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"} , description = "Change Login Id - in case manager for HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_30_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager : Change the login Id for HRP from corporation and reset the password for HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(true);

			caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(true);
			
			portalSetup.searchCorporationByName(corpName, true);
			
			portalSetup.clickCustomizeCorporationSettings(true);
			
			portalSetup.clickUserManagementTab(true);
			
			portalSetup.clickQuickActionsIconForUserManagement(true);
			
			portalSetup.clickInviteUserAgain(true);
			
			portalSetup.clickSaveAndInform(true);

			CM_CorporationPage corporationhomePage = caseManagerHomePage.clickCorporationTab(true);
			
			corporationhomePage.searchCorporationByName(corpName, true);

			corporationhomePage.clickChangeLoginIdTab(workbookNameWrite, sheetName, true);

			CM_EmailsPage emailsPage = corporationhomePage.clickEmailsTab(true);

			emailsPage.getURLToChangePassword(workbookNameWrite, sheetName, true);

			caseManagerHomePage.clickLogout(true);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("QA_HRPPassword", sheetName, "Value", "Hrp@1" + RandomStringUtils.randomAlphanumeric(5));

			String hrpPasswordURLLink = readExcel.initTest(workbookName, sheetName, "QA_ChangeHRPPasswordURLLink");
			String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
			String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");

			LoginPageTest changeHRPPasswordPage = new LoginPageTest(driver, hrpPasswordURLLink);

			changeHRPPasswordPage.resetHRPPassword(hrpLoginID, hrpPassword);

			login.clickAgreeButton(false);

			changeHRPPasswordPage.verifyHRPSecurityPage(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"} , description = "Creation of Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_60_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");

		globalVariables.testCaseDescription = "Case Manager: Creation of Case (USA) in newly created  corp client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);

            caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   

			caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForUSA", data.AddCase_CountryName,savedClientName, corpName, workbookNameWrite, sheetName, true);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"} , description = "Verify if login ID has changed for FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_30_1")
	public void CM_TC_30_1_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPasswordCreated");

		globalVariables.testCaseDescription = "Verify if login ID has changed for FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpPassword, true);

			login.clickAgreeButton(false);

			login.verifyFNPLoginAfterIDChange(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"}, description = "FNP: FNP login using modified password", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_2"})
	public void CM_TC_67_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		globalVariables.testCaseDescription = "FNP: FNP login using modified password";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);
	
			login.clickAcceptAndContinueBtn(true);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups={"mandatory"} , description = "FNP: Verify FNP home page after login", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_2"})
	public void CM_TC_67_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP: Verify FNP home page after login";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
					
			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);
	
			login.clickAcceptAndContinueBtn(true);
			
			fnpHomePagelogin.verifyFNPHomePage(clientName);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"mandatory"}, description = "Creation of relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		LocalDateTime localDate = LocalDateTime.now();
		String firstName = "Relative" + localDate;
		String lastName	= "LName" + localDate;
		String relativeName = firstName + " " + lastName;
		String gender = "F";
		String email = "first.relative@inszoom.com";
		
		String[] name = {firstName, lastName, relativeName};
		String[] nameKeys = {"QA_ALoginSavedRelativeFirstName", "QA_ALoginSavedRelativeLastName", "QA_ALoginSavedRelativeName"};

		globalVariables.testCaseDescription = "Case Manager : Add relative to the existing client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();

			editClientRelativePage.clickAddRelativeButton();

			editClientRelativePage.clickAddNewRelativeLink();

			editClientRelativePage.enterRelativesDataAndSave(firstName, lastName, relativeName, globalVariables.relation, gender, email);

			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);
		
			editClientRelativePage.verifyRelativeDetails(relativeName, globalVariables.relation);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(groups = {"FNP", "Priority2", "notes", "client", "HRP"}, description = "Case Manager - Client: Add notes", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_24_1(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Case Manager - Corp Client: Add notes";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashBoardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashBoardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
                
            clientListPage.clickNotesTabInClient(true);
                
            CM_NotesPage notesPage = new CM_NotesPage(driver);
                
            notesPage.addNotesInClient(globalVariables.firstNotesDetailsTextClient,true);

            notesPage.verifyNotesInClient(globalVariables.firstNotesDetailsTextClient, true);

            notesPage.addNotesInClient(globalVariables.secondNotesDetailsTextClient,true);

            notesPage.verifyNotesInClient(globalVariables.secondNotesDetailsTextClient, true);

            caseManagerDashBoardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
        	Log.exception(e, driver);
        }
        finally {
        	Log.endTestCase();
        	driver.quit();
        }
    }
	
	
    @Test(groups={"FNP", "Priority2", "client", "notes"}, description = "Notes edit", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_24_1")
    public void CM_TC_24_2(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

        globalVariables.testCaseDescription = "Case Manager-Client: Edit notes";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
       
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(true);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);

            clientListPage.clickNotesTabInClient(true);
                
            CM_NotesPage notesPage = new CM_NotesPage(driver);

            notesPage.clickEditAddInNotesClient(globalVariables.firstNotesDetailsTextClient, globalVariables.editFirstNotesDetailsTextClient, true);

            notesPage.verifyNotesInClient(globalVariables.editFirstNotesDetailsTextClient, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
        	Log.exception(e, driver);
        }
        finally {
        	Log.endTestCase();
        	driver.quit();
        }
    }
	
	
    @Test(groups={"notes", "client"}, description = "Notes preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_24_2")
    public void CM_TC_24_3(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Case Manager-Client: Preview notes";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS); 

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
                
            clientListPage.clickNotesTabInClient(true);

            CM_NotesPage notesPage = new CM_NotesPage(driver);

            notesPage.clickCheckBoxInNotesClient(globalVariables.editFirstNotesDetailsTextClient, true);

            notesPage.verifyPrintPreviewClient(globalVariables.editFirstNotesDetailsTextClient, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
        	driver.quit();
        } 
    }


    @Test(groups={"notes", "client"}, description = "Notes view", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_24_2")
    public void CM_TC_24_4(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Case Manager-Client: View the notes";
        
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(false);
			
			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickNotesTabInClient(true);
			
			CM_NotesPage notesPage = new CM_NotesPage(driver);
			
			notesPage.verifyNotesViewPage(globalVariables.editFirstNotesDetailsTextClient, true);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();

        }
        catch (Exception e) {
        	Log.exception(e, driver);
        }
        finally {
        	Log.endTestCase();
            driver.quit();
        }
    }


    @Test(groups={"notes", "client"}, description = "Notes delete", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_24_1")
    public void CM_TC_24_5(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

        globalVariables.testCaseDescription = "Case Manager-Client: Delete notes";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(false);
			
			CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickNotesTabInClient(true);
			
			CM_NotesPage notesPage = new CM_NotesPage(driver);
			
			notesPage.clickCheckBoxInNotesClient(globalVariables.secondNotesDetailsTextClient, true);
			
			notesPage.deleteNotesClient(globalVariables.secondNotesDetailsTextClient, true);
			
			notesPage.verifyNotesDeleted(globalVariables.secondNotesDetailsTextClient, true);
			
			caseManagerDashboardPage.clickLogout(true);
			
			Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
            driver.quit();
        } 
    }


    @Test(groups={"notes", "client"}, description = "Notes lock", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_24_2")
    public void CM_TC_24_6(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Case Manager-Client: Lock notes";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

        	CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
            
            clientListPage.clickNotesTabInClient(true);

            CM_NotesPage notesPage = new CM_NotesPage(driver);

            notesPage.clickLockIconClient(globalVariables.editFirstNotesDetailsTextClient, true);

            notesPage.clickCheckBoxInNotesClient(globalVariables.editFirstNotesDetailsTextClient, true);

            notesPage.verifyNotesLocked(globalVariables.editFirstNotesDetailsTextClient, true);

            caseManagerHomePage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        } 
    }

    
  	@Test(groups={"notes", "client"}, description = "Notes details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_24_2")
  	public void CM_TC_28_2(String browser) throws Exception 
  	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Case Manager - Client: Notes details present in communication summary";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
            LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
            
            caseManagerDashboardPage.clickCommunicationSummaryInClient(true);

            CommunicationSummaryPage communicationSummaryclient = new CommunicationSummaryPage(driver);
            
            communicationSummaryclient.verifyDataPresent(globalVariables.editFirstNotesDetailsTextClient, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }


    @Test(groups={"FNP", "Priority2", "notes", "case", "HRP"},description = "Case Manager - Case: Add New Notes to the case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_48_1(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	globalVariables.testCaseDescription = "Case Manager - Case: Add New Notes to the case";
    	final WebDriver driver = WebDriverFactory.get(browser);
    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);

            caseListPage.clickNotesTabInCase(true);
            
            CM_NotesPage notesPage = new CM_NotesPage(driver);

            notesPage.addNotesInCase(globalVariables.firstNotesDetailsTextCase, true);

            notesPage.verifyNotesInCase(globalVariables.firstNotesDetailsTextCase, true);

            caseManagerDashboardPage.clickLogout(true);
            
            Log.testCaseResult();

        }
        catch (Exception e) {
        	Log.exception(e, driver);
        }
        finally {
        	Log.endTestCase();
            driver.quit();
        }
    }

    
    @Test(groups={"FNP", "Priority2", "notes", "case"}, description = "Case Manager - Case: Edit added Notes to the case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_48_1")
    public void CM_TC_48_2(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
        globalVariables.testCaseDescription = "Case Manager - Case: Edit added Notes to the case";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);

            caseListPage.clickNotesTabInCase(true);
            
            CM_NotesPage notesPage = new CM_NotesPage(driver);

            notesPage.editAddedNotesInCase(globalVariables.firstNotesDetailsTextCase,globalVariables.editFirstNotesDetailsTextCase, false);

            notesPage.verifyNotesInCase(globalVariables.editFirstNotesDetailsTextCase, true);

            caseManagerDashboardPage.clickLogout(true);
            
            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
    

    @Test(groups={"notes", "case"}, description = "Case Manager: CASE: Notes - Print Preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_48_3(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
           
        globalVariables.testCaseDescription = "Case Manager - Case: Print Preview the Notes";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickCaseTab(true);

            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
            
            caseListPage.clickOnCaseId(caseCreated, true);
            
            caseListPage.clickNotesTabInCase(true);
            
            CM_NotesPage notesPage = new CM_NotesPage(driver);
            
            notesPage.addNotesInCase(globalVariables.printPreviewNotesDescriptionCase, true);
            
            notesPage.verifyNotesInCase(globalVariables.printPreviewNotesDescriptionCase, true);

            notesPage.clickCheckBoxInNotesCase(globalVariables.printPreviewNotesDescriptionCase, true);

            notesPage.verifyPrintPreviewCase(globalVariables.printPreviewNotesDescriptionCase, true);

            caseManagerDashboardPage.clickLogout(true);
            
            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        }
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
        
    
    @Test(groups={"notes", "case"}, description = "Case Manager: CASE: Notes - Newly add and Delete", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_48_4(String browser) throws Exception 
    {
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          globalVariables.testCaseDescription = "Case Manager - Case: Add and delete the notes";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          
          try 
          {
        	  LoginPageTest login = new LoginPageTest(driver, webSite);

              CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

              login.clickAgreeButton(false);

              caseManagerDashboardPage.clickCaseTab(true);

              CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
                
              caseListPage.clickOnCaseId(caseCreated,true);

              caseListPage.clickNotesTabInCase(true);
                
              CM_NotesPage notesPage = new CM_NotesPage(driver);

              notesPage.addNotesInCase(globalVariables.deleteNotesDescriptionCase, true);

              notesPage.verifyNotesInCase(globalVariables.deleteNotesDescriptionCase, true);

              notesPage.deleteNotesCase(globalVariables.deleteNotesDescriptionCase, true);;

              notesPage.verifyNotesDeletedCase(globalVariables.deleteNotesDescriptionCase,true);

              caseManagerDashboardPage.clickLogout(true);
                
              Log.testCaseResult();

          }
          catch (Exception e) {
        	  Log.exception(e, driver);
          }
          finally {
        	  Log.endTestCase();
        	  driver.quit();
          }
    }
        
    
    @Test(groups={"notes", "case"}, description = "Notes - View Notes", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_48_5(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
        globalVariables.testCaseDescription = "Case Manager - Case: View the added notes";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);

            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
            
            caseListPage.clickOnCaseId(caseCreated, true);
            
            caseListPage.clickNotesTabInCase(true);

            CM_NotesPage notesPage = new CM_NotesPage(driver);
            
            notesPage.addNotesInCase(globalVariables.viewNotesDescriptionCase, true);
            
            notesPage.verifyNotesInCase(globalVariables.viewNotesDescriptionCase, true);

            notesPage.verifyNotesViewPageInCase(globalVariables.viewNotesDescriptionCase, true);

            caseManagerDashboardPage.clickLogout(true);
            
            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        }
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }


    @Test(groups={"notes", "case"}, description = " Notes details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_48_2")
    public void CM_TC_53_2(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
        globalVariables.testCaseDescription = "Case Manager - Case: Notes details present in communication summary";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);

            CommunicationSummaryPage communicationSummaryCase = new CommunicationSummaryPage(driver);
            
            caseManagerDashboardPage.clickCommunicationSummaryInCase(true);

            communicationSummaryCase.verifyDataPresent(globalVariables.editFirstNotesDetailsTextCase, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        }
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }


	@Test(groups={"calendar", "client"}, description = "Add phone Log for Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_25_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Adding Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.addPhoneLogForClient(globalVariables.phoneLogAddMessage, true);
			
			clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.verifyPhoneLog(globalVariables.phoneLogAddMessage, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "client"}, description = "Edit phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_25_1")
	public void CM_TC_25_2(String browser) throws Exception
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Editing Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.editPhoneLog(globalVariables.phoneLogAddMessage, globalVariables.phoneLogEditMessage, true);
			
			clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.verifyPhoneLog(globalVariables.phoneLogEditMessage, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups={"calendar", "client"}, description = "Phone Log: Print Preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_25_2")
	public void CM_TC_25_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Print Preview the Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.selectPhoneLog(globalVariables.phoneLogEditMessage, true);

			clientPhoneLogPage.verifyPrintPreview(globalVariables.phoneLogEditMessage, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "client"}, description = "Phone Log: Verify Phone Log details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_25_2")
	public void CM_TC_25_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Verify Phone Log details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.selectPhoneLog(globalVariables.phoneLogEditMessage, true);

			clientPhoneLogPage.verifyPhoneLogDetails(globalVariables.phoneLogEditMessage, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "client"}, description = "Lock phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_25_2")
	public void CM_TC_25_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Lock Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.lockPhoneLog(globalVariables.phoneLogEditMessage, true);

			clientPhoneLogPage.selectPhoneLog(globalVariables.phoneLogEditMessage, true);

			clientPhoneLogPage.editLockedPhoneLog(true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "client"}, description = "Delete phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_25_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager - Client: Delete Phone Log";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_ClientPhoneLogPage clientPhoneLogPage = clientListPage.clickPhoneLogTab(true);

			clientPhoneLogPage.addPhoneLogForClient(globalVariables.phoneLogDeleteMessage, true);

			clientPhoneLogPage.selectPhoneLogCheckbox(globalVariables.phoneLogDeleteMessage, true);

			clientPhoneLogPage.clickDeleteIcon(true);

			clientPhoneLogPage.verifyDeletedPhoneLog(globalVariables.phoneLogDeleteMessage);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
  	@Test(groups={"calendar", "client"}, description = "Phone Log details in Communication Summary", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_25_2")
  	public void CM_TC_28_3(String browser) throws Exception 
  	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        
        globalVariables.testCaseDescription = "Case Manager - Client: Phone Log details present in communication summary";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
            LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
            
            caseManagerDashboardPage.clickCommunicationSummaryInClient(true);

            CommunicationSummaryPage communicationSummaryclient = new CommunicationSummaryPage(driver);
            
            communicationSummaryclient.verifyDataPresent(globalVariables.phoneLogEditMessage, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups={"calendar", "client"}, description = "Calendar - Phone log in client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_25_2")
	public void CM_TC_55_5(String browser) throws Exception 
	{
		String client_PhoneLogEditMessage = "Phone Log message after editing";

		globalVariables.testCaseDescription = "Case Manager - Calendar: Phone log for client is present in calendar message and calls";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickPhoneLogs();
			
			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.verifyPhoneLogPresent(client_PhoneLogEditMessage);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
  	
  	
	@Test(groups={"calendar", "case"}, description = "Add phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_49_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String case_PhoneLogMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogMessage");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Adding Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();

			casePhoneLogPage.addPhoneLogForCase(case_PhoneLogMessage, true);

			casePhoneLogPage.verifyPhoneLog(case_PhoneLogMessage, true);

			caseManagerHomePage.clickLogout(true);

			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "case"}, description = "Edit phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_1")
	public void CM_TC_49_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String case_PhoneLogMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogMessage");
		String case_PhoneLogEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");

		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Edit Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();
			
			casePhoneLogPage.editPhoneLog(case_PhoneLogMessage, case_PhoneLogEditMessage, true);

			casePhoneLogPage.verifyPhoneLog(case_PhoneLogEditMessage, true);

			caseManagerHomePage.clickLogout(true);

			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "case"}, description = "Print Preview Case Phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_2")
	public void CM_TC_49_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String case_PhoneLogEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");

		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Print Preview Case Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();
			
			casePhoneLogPage.selectPhoneLog(case_PhoneLogEditMessage, true);

			casePhoneLogPage.verifyPrintPreview(case_PhoneLogEditMessage, true);
			
			caseManagerHomePage.clickLogout(true);

			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "case"}, description = "Verify Case Phone Log Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_2")
	public void CM_TC_49_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String case_PhoneLogEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");

		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Verify Case Phone Log Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();
			
			casePhoneLogPage.selectPhoneLog(case_PhoneLogEditMessage, true);

			casePhoneLogPage.verifyPhoneLogDetails(case_PhoneLogEditMessage, true);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "case"}, description = "Lock Case Phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_2")
	public void CM_TC_49_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String case_PhoneLogEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");

		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Lock Case Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();
			
			casePhoneLogPage.lockPhoneLog(case_PhoneLogEditMessage, true);
			
			casePhoneLogPage.editLockedPhoneLog(case_PhoneLogEditMessage, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"calendar", "case"}, description = "Delete Case Phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_49_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel(); 

		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Case: Delete Case Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);

			CM_CasePhoneLogPage casePhoneLogPage = new CM_CasePhoneLogPage(driver);
			
			caseListPage.clickPhoneLogTab();
			
			casePhoneLogPage.addPhoneLogForCase(globalVariables.phoneLogDeleteMessage, true);
			
			casePhoneLogPage.deletePhoneLog(globalVariables.phoneLogDeleteMessage);
			
			casePhoneLogPage.verifyDeletedPhoneLog(globalVariables.phoneLogDeleteMessage);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
  	
  	
    @Test(groups={"calendar", "case"}, description = "Case Phone Log details in Communication Summary", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_2")
    public void CM_TC_53_3(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
        String edittedPhoneLogDescription = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");
        
        globalVariables.testCaseDescription = "Case Manager - Case: Check if Phone details present in communication summary";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseId, true);

            CommunicationSummaryPage communicationSummaryCase = new CommunicationSummaryPage(driver);
            
            caseManagerDashboardPage.clickCommunicationSummaryInCase(true);

            communicationSummaryCase.verifyDataPresent(edittedPhoneLogDescription, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
        }
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
  	
  	
	@Test(groups={"calendar", "case"}, description = "Calendar - Phone log in Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_49_2")
	public void CM_TC_55_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String client_PhoneLogEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Case_PhoneLogEditMessage");

		globalVariables.testCaseDescription = "Case Manager - Calendar: Phone log for case is present in calendar message and calls";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickPhoneLogs();
			
			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.verifyPhoneLogPresent(client_PhoneLogEditMessage);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
  	
  	
	@Test(groups = {"HRP", "client"}, description = "Edit client details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_7(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager: Edit client profile Case Manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.editClientDetails(workbookNameWrite, sheetName, globalVariables.employeeID, globalVariables.fileNumber, globalVariables.prospectiveEmployeeId);

			clientCreationPage.verifyClientDetails(globalVariables.employeeID);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"client"}, description = "Creation of Second and third client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_5_1_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		LocalDateTime localDate = LocalDateTime.now();
		String firstName2 = "ThirdClient" + localDate;
		String lastName2 = "Lname";
		String firstName1 = "SecondClient" + localDate;
		String lastName1 = "Lname";

		String clientName1 = firstName1 + " " + lastName1;
		String clientName2 = firstName2 + " " + lastName2;
		
		String[] name = {firstName1, lastName1, clientName1, firstName2, lastName2, clientName2};
		String[] nameKeys = {"QA_ALoginSavedFirstClientNameSecond", "QA_ALoginSavedLastClientNameSecond", "QA_ALoginSavedClientNameSecond", "QA_ALoginSavedFirstClientNameThird", "QA_ALoginSavedLastClientNameThird", "QA_ALoginSavedClientNameThird"};

		globalVariables.testCaseDescription = "Case Manager: Creation of second Client and third client in Case Manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickAddClientButton(true);
			
			clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, corpName, firstName1, lastName1, globalVariables.clientEmailID);

			clientListPage.clickSaveClientButton(true);

			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);
			
			clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickAddClientButton(true);

			clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, corpName, firstName2, lastName2, globalVariables.clientEmailID);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);

			clientListPage.clickSaveClientButton(true);

			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups = {"client"}, description = "Adding second Client as relative to the first client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_5_1(String browser) throws Exception 
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String clientNameLast = readExcel.initTest(workbookName, sheetName, "ALoginSavedLastClientName");
		String clientNameSecond = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedFirstClientNameSecond");
		String savedClientNameSecond = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameSecond");
		String fullClientName = clientName + " " + clientNameLast;	
		
		globalVariables.testCaseDescription = "Case Manager-Client: Adding second Client as relative to the first client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();

			editClientRelativePage.clickAddRelativeButton();

			editClientRelativePage.clickSearchExistingLink();

			editClientRelativePage.searchClient(clientNameSecond);

			editClientRelativePage.chooseRelation(clientNameSecond);

			editClientRelativePage.saveRelativeAndConfirm(fullClientName, savedClientNameSecond, globalVariables.relation);

			editClientRelativePage.verifySecondClientAsRelative(savedClientNameSecond, globalVariables.relation);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
		        e.printStackTrace();
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Creation of relative for third client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_5_2_0(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String QA_ALoginSavedFirstClientNameThird = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedFirstClientNameThird");

		LocalDateTime localDate = LocalDateTime.now();
		String firstName = "ThirdRelative" + localDate;
		String lastName	= "LName" ;
		String relativeName = firstName + " " + lastName;
		String relation = "Child";
		String gender = "F";
		String email = "third.relative@inszoom.com";
		
		String[] name = {firstName, lastName, relativeName};
		String[] nameKeys = {"QA_ALoginSavedRelativeFirstNameFirst", "QA_ALoginSavedRelativeLastNameFirst", "QA_ALoginSavedRelativeNameFirst"};

		
		globalVariables.testCaseDescription = "Case Manager-Client: Creation of new relative for the third client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(QA_ALoginSavedFirstClientNameThird, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();

			editClientRelativePage.clickAddRelativeButton();

			editClientRelativePage.clickAddNewRelativeLink();
			
			editClientRelativePage.enterRelativesDataAndSave(firstName, lastName, relativeName, relation, gender, email);

			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);
			
			editClientRelativePage.verifyRelativeDetails(relativeName, relation);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Adding third client and its relative to the first Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_5_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String QA_ALoginSavedFirstClientNameThird = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedFirstClientNameThird");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeNameFirst");
		String thirdClientName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameThird");

		globalVariables.testCaseDescription = "Case Manager: Adding relative ad third client and its relative to the first client";
		final WebDriver driver = WebDriverFactory.get(browser);

        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();

			editClientRelativePage.clickAddRelativeButton();

			editClientRelativePage.clickSearchExistingLink();

			editClientRelativePage.searchClient(QA_ALoginSavedFirstClientNameThird);
			
			editClientRelativePage.chooseRelation(QA_ALoginSavedFirstClientNameThird);
			
			editClientRelativePage.saveThirdRelativeAndConfirm(thirdClientName, relativeName, globalVariables.relation);

			editClientRelativePage.verifySecondClientAsRelative(thirdClientName, globalVariables.relation);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"client"}, description = "Edit the details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_6_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String clientLastName = readExcel.initTest(workbookName, sheetName, "ALoginSavedLastClientName");
		String thirdRelative = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeNameFirst");
		String relative = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeName");
		String secondClient = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameSecond");
		String thirdClient = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameThird");

		globalVariables.testCaseDescription = "Case Manager - Client: Edit the relations for all the relatives added to the first client";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(false);
			
			clientCreationPage.clickOnClientName(clientFirstName, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();
			
			editClientRelativePage.editRelations(globalVariables.relation, clientFirstName, clientLastName, thirdRelative, relative, secondClient, thirdClient);

			editClientRelativePage.verifyAfterEditingRelations(relative, secondClient, thirdClient, thirdRelative, globalVariables.relation);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Removing client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_6_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String thirdRelative = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeNameFirst");
		String relative = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeName");
		String secondClient = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameSecond");
		String thirdClient = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedClientNameThird");

		globalVariables.testCaseDescription = "Case Manager - Client: Removing the second and third client and its relatives from the first client";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(false);
			
			clientCreationPage.clickOnClientName(clientName, true);

			CM_EditClientRelativePage editClientRelativePage = clientCreationPage.clickRelativesTab();

			editClientRelativePage.removeRelation(thirdClient, thirdRelative, secondClient, relative);

			editClientRelativePage.verifyIfRelationRemoved();

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
  	
    @Test(groups={"FNP", "Priority2", "client", "calendar"}, description = "Add new Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_26_1(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
       
        globalVariables.testCaseDescription = "Case Manager - Client: Adding new Appointment/Activity";
        final WebDriver driver = WebDriverFactory.get(browser);           
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

            clientListPage.clickOnClientName(clientName, true);
            
            clientListPage.clickAppointmentsActivitiesTab(true);

            CM_ClientAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_ClientAppointmentsAndActivitiesPage(driver);

            appointmentsAndActivitiesPage.addNewAppointmentsActivities(globalVariables.appointmentActivityDescriptionClient, globalVariables.appointmentActivityLocationClient, globalVariables.appointmentActivitySubjectClient, globalVariables.appointmentActivityCategoryClient, caseManagerDashboardPage.fetchUserId(), globalVariables.appointmentActivityComments); 
            
            appointmentsAndActivitiesPage.verifyAppointmentActivity(globalVariables.appointmentActivityDescriptionClient, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        }
        catch (Exception e) {
            Log.exception(e, driver);
    	} 
        finally {
            Log.endTestCase();
            driver.quit();
        } 
    }
    
    
    @Test(groups = {"client", "calendar"}, description = "Edit Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_26_1")
    public void CM_TC_26_2(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
    	globalVariables.testCaseDescription = "Case Manager - Client: Editing a Appointment/Activity";
    	final WebDriver driver = WebDriverFactory.get(browser);
    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
    	
	      
    	try 
    	{
    		LoginPageTest login = new LoginPageTest(driver, webSite);
	
            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
	
            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickClientTab(true);

            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

            clientListPage.clickOnClientName(clientName, true);
            
            clientListPage.clickAppointmentsActivitiesTab(true);

            CM_ClientAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_ClientAppointmentsAndActivitiesPage(driver);
	            
            appointmentsAndActivitiesPage.selectAppointmentActivity(globalVariables.appointmentActivityDescriptionClient, true);

            appointmentsAndActivitiesPage.editAppointmentActivityLocation(globalVariables.editAppointmentActivityLocationClient, true);

            appointmentsAndActivitiesPage.verifyAppointmentActivityLocation(globalVariables.editAppointmentActivityLocationClient, true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

    	}
    	catch (Exception e) {
            Log.exception(e, driver);
    	}
    	finally {
            Log.endTestCase();
            driver.quit();
    	} 
    }
    
    
    @Test(groups = {"client", "calendar"}, description = "Add and Delete Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_26_3(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
    	
    	globalVariables.testCaseDescription = "Case Manager - Client: Deleting new Appointment/Activity";
        String appointmentActivityCategory = "Test Category for Regression";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

        	CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

        	login.clickAgreeButton(false);
                    
        	caseManagerDashboardPage.clickClientTab(true);

        	CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

            clientListPage.clickOnClientName(clientName, true);
            
            clientListPage.clickAppointmentsActivitiesTab(true);

            CM_ClientAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_ClientAppointmentsAndActivitiesPage(driver);

            appointmentsAndActivitiesPage.addNewAppointmentsActivities(globalVariables.deleteappointmentActivityDescriptionClient, globalVariables.appointmentActivityLocationClient, globalVariables.appointmentActivitySubjectClient, appointmentActivityCategory, caseManagerDashboardPage.fetchUserId(), globalVariables.appointmentActivityComments); 
            
            appointmentsAndActivitiesPage.verifyAppointmentActivity(globalVariables.deleteappointmentActivityDescriptionClient, true);
                    
            appointmentsAndActivitiesPage.selectAppointmentActivity(globalVariables.deleteappointmentActivityDescriptionClient, true);

            appointmentsAndActivitiesPage.clickDeleteAppointmentActivityButton(true);

            appointmentsAndActivitiesPage.verifyIfAppointmentActivityNotPresent(globalVariables.deleteappointmentActivityDescriptionClient,true);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
        	driver.quit();
        } 
    }
  	
  	
    @Test(groups = {"client", "calendar"}, description = "Appointments details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_26_1")
    public void CM_TC_28_1(String browser) throws Exception 
    {

    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
    	globalVariables.testCaseDescription = "Case Manager - Client: Appointment details present in communication summary";
    	final WebDriver driver = WebDriverFactory.get(browser);
    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

    	try 
    	{
    		LoginPageTest login = new LoginPageTest(driver, webSite);

    		CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

    		login.clickAgreeButton(false);
                    
    		caseManagerDashboardPage.clickClientTab(true);

    		CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

    		clientListPage.clickOnClientName(clientName, true);
                    
    		caseManagerDashboardPage.clickCommunicationSummaryInClient(true);

    		CommunicationSummaryPage communicationSummaryclient = new CommunicationSummaryPage(driver);

    		communicationSummaryclient.verifyDataPresent(globalVariables.appointmentActivitySubjectClient, true);

    		caseManagerDashboardPage.clickLogout(true);

    		Log.testCaseResult();

    	} 
    	catch (Exception e) {
    		Log.exception(e, driver);
    	}
    	finally {
    		Log.endTestCase();
    		driver.quit();
    	} 
    }
  	
  	
    @Test(groups = {"case", "calendar"}, description = "Add new Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_50_1(String browser) throws Exception 
    {

    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	globalVariables.testCaseDescription = "Case Manager - Case: Adding new Appointment/Activity";
    	String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	final WebDriver driver = WebDriverFactory.get(browser);
    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
              
    	try 
    	{
    		LoginPageTest login = new LoginPageTest(driver, webSite);
    		
    		CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
                    
    		String userId = caseManagerDashboardPage.fetchUserId();
    		
    		login.clickAgreeButton(false);

    		caseManagerDashboardPage.clickCaseTab(true);
                    
    		CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
                    
    		caseListPage.clickOnCaseId(caseId, true);
                    
    		caseListPage.clickAppointmentActivityTab(true);

    		CM_CaseAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_CaseAppointmentsAndActivitiesPage(driver);

    		appointmentsAndActivitiesPage.addNewAppointmentActivity(globalVariables.appointmentActivitySubjectCase, userId, globalVariables.appointmentActivityDescriptionCase, globalVariables.appointmentActivityLocationCase, globalVariables.appointmentActivityDurationCase, globalVariables.appointmentActivityCategoryCase, "p", true);

    		appointmentsAndActivitiesPage.verifyAppointmentActivity(globalVariables.appointmentActivityDescriptionCase, true);

    		caseManagerDashboardPage.clickLogout(true);

    		Log.testCaseResult();
    		
    	}
    	catch (Exception e) {
    		Log.exception(e, driver);
    	}
    	finally {
    		Log.endTestCase();
    		driver.quit();
    	} 
    }
    
    
    @Test(groups = {"case", "calendar"}, description = "Delete Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_50_2(String browser) throws Exception 
    {

    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	
    	globalVariables.testCaseDescription = "Case Manager - Case: Deleting new Appointment/Activity";
    	String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	final WebDriver driver = WebDriverFactory.get(browser);
    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
              
    	try 
    	{
    		LoginPageTest login = new LoginPageTest(driver, webSite);

    		CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

    		login.clickAgreeButton(false);
    		
    		caseManagerDashboardPage.clickCaseTab(true);
                    
    		String userId = caseManagerDashboardPage.fetchUserId();
    		
    		CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
                    
    		caseListPage.clickOnCaseId(caseId, true);
                    
    		caseListPage.clickAppointmentActivityTab(true);
                    
    		CM_CaseAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_CaseAppointmentsAndActivitiesPage(driver);
                    
    		appointmentsAndActivitiesPage.addNewAppointmentActivity(globalVariables.appointmentActivitySubjectCase, userId, globalVariables.deleteappointmentActivityDescriptionCase, globalVariables.appointmentActivityLocationCase, globalVariables.appointmentActivityDurationCase, globalVariables.appointmentActivityCategoryCase, "p", true);

    		appointmentsAndActivitiesPage.verifyAppointmentActivity(globalVariables.deleteappointmentActivityDescriptionCase, true);
                    
    		appointmentsAndActivitiesPage.selectAppointmentActivity(globalVariables.deleteappointmentActivityDescriptionCase, true);

    		appointmentsAndActivitiesPage.clickDeleteAppointmentActivityButton(true);

    		appointmentsAndActivitiesPage.verifyIfAppointmentActivityNotPresent(globalVariables.deleteappointmentActivityDescriptionCase,true);

    		caseManagerDashboardPage.clickLogout(true);

    		Log.testCaseResult();

    	} 
    	catch (Exception e) {
    		Log.exception(e, driver);
    	} 
    	finally {
    		Log.endTestCase();
    		driver.quit();
    	} 
    }

    
  	@Test(groups = {"case", "calendar"}, description = "Edit Appointments/Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_50_1")
	public void CM_TC_50_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "Case Manager - Case: Editing a Appointment/Activity";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickAppointmentActivityTab(true);

			CM_CaseAppointmentsAndActivitiesPage appointmentActivityPage = new CM_CaseAppointmentsAndActivitiesPage(driver);

			appointmentActivityPage.selectAppointmentActivity(globalVariables.appointmentActivityDescriptionCase, true);

			appointmentActivityPage.editAppointmentActivity(globalVariables.editAppointmentActivityLocationCase, globalVariables.editappointmentActivityDescriptionCase, true);

			appointmentActivityPage.verifyEdittedAppointmentActivity(globalVariables.editAppointmentActivityLocationCase, globalVariables.editappointmentActivityDescriptionCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Appointments details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_50_1")
	public void CM_TC_53_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Case Manager - Case: Appointment details present in communication summary";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickCommunicationSummaryInCase(true);
			
			CommunicationSummaryPage communicationSummaryPage = new CommunicationSummaryPage(driver);
			
			communicationSummaryPage.verifyDataPresent(globalVariables.appointmentActivitySubjectCase, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "calendar"}, description = "Calendar - Appointments in client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_26_1")
	public void CM_TC_55_1(String browser) throws Exception 
	{
		/*
		 * Make sure ALogin Time Zone should be Indian Time Zone
		 */
		
		globalVariables.testCaseDescription = "Case Manager - Calendar: Appointments in client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCalendarAppointmentActivityReminder();

			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.verifyClientAppointmentActivity(globalVariables.appointmentActivitySubjectClient, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Calendar - Appointments in case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_50_1")
	public void CM_TC_55_2(String browser) throws Exception 
	{
		
		globalVariables.testCaseDescription = "Case Manager - Calendar: Appointments in case";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCalendarAppointmentActivityReminder();;

			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.verifyCaseAppointmentPresent(globalVariables.appointmentActivitySubjectCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
  	
  	
	@Test(groups = {"case", "calendar"}, description = "Tasks Add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_51_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		globalVariables.testCaseDescription = "Add tasks under case in case manager";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);
			
			String userId = caseManagerDashboardPage.fetchUserId();

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);
			
			caseTaskPage.addNewTask(globalVariables.caseTaskTypeSelectCase, globalVariables.firstTaskDetailsCaseTextCase, userId, true);

			caseTaskPage.verifyTaskAdded(globalVariables.firstTaskDetailsCaseTextCase, true);
//
//			caseTaskPage.clickAddNewTaskBtn(caseTaskTypeSelect, secondTaskDetailsCaseText, caseCaseManagerOptionSelect,
//					true);
//			
//			caseTaskPage.verifyTaskAdded(secondTaskDetailsCaseText, true);
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Tasks Edit", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_51_1")
	public void CM_TC_51_4(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Edit tasks under case in case manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);
			
			caseTaskPage.selectTask(globalVariables.firstTaskDetailsCaseTextCase, true);

			caseTaskPage.editTaskDetails(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseTaskPage.verifyTaskDetails(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"case", "calendar"}, description = "Tasks Delete", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_51_2(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Delete tasks under client in case manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			String userId = caseManagerDashboardPage.fetchUserId();

			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);
			
			caseTaskPage.addNewTask(globalVariables.caseTaskTypeSelectCase, globalVariables.deleteTaskDetailsCaseTextCase, userId, true);

			caseTaskPage.verifyTaskAdded(globalVariables.deleteTaskDetailsCaseTextCase, true);
			
			caseTaskPage.selectTask(globalVariables.deleteTaskDetailsCaseTextCase, true);

			caseTaskPage.deleteTask(globalVariables.deleteTaskDetailsCaseTextCase, true);

			caseTaskPage.verifyTaskDeleted(globalVariables.deleteTaskDetailsCaseTextCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Task view under case in case manager", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_51_4")
	public void CM_TC_51_3(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Task view under case in case manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);

			caseTaskPage.selectTask(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseTaskPage.verifyTaskDetails(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Tasks Print Preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_51_4")
	public void CM_TC_51_5(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Print preview tasks under case in case manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);

			caseTaskPage.selectTask(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseTaskPage.verifyPrintPreviewDetails(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
/*	@Test(groups = {"case", "calendar"}, description = "Tasks lock", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_51_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Lock tasks under case in case manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDasboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDasboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickTaskTab(true);

			CM_CaseTaskPage caseTaskPage = new CM_CaseTaskPage(driver);
	
			//caseTaskPage.click_Lock(EditfirstTaskDetailsCaseText, true);

			caseManagerDasboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	
	
	@Test(groups = {"client", "calendar"}, description = "Calendar - Tasks in client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_27_4")
	public void CM_TC_55_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String client_TaskEditMessage = readExcel.initTest(workbookName, sheetName, "QA_A_Client_TaskEditMessage");
		
		globalVariables.testCaseDescription = "Case Manager - Calendar: Tasks in client present in calendar tasks";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCalendarAppointmentActivityReminder();

			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.clickTaskTab();

			calendarPage.verifyClientTasksPresent(client_TaskEditMessage, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"case", "calendar"}, description = "Calendar - Tasks in case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_51_4")
	public void CM_TC_55_4(String browser) throws Exception {
		
		globalVariables.testCaseDescription = "Case Manager - Calendar: Tasks in case present in calendar tasks";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCalendarAppointmentActivityReminder();

			CM_CalendarPage calendarPage = new CM_CalendarPage(driver);

			calendarPage.clickTaskTab();

			calendarPage.verifyCaseTasksPresent(globalVariables.editfirstTaskDetailsCaseTextCase, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"client", "calendar"}, description = "Tasks Add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_27_1(String browser) throws Exception
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: Add tasks";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			String userId = caseManagerDashboardPage.fetchUserId();
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickTaskTab(true);
			
			CM_ClientTaskPage clientTaskPage = new CM_ClientTaskPage(driver);

			clientTaskPage.addNewTask(globalVariables.taskTypeSelectClient, globalVariables.firstTaskDetailsTextClient, userId, true);

			clientTaskPage.verifyTask(globalVariables.firstTaskDetailsTextClient, true);

			//clientTaskPage.clickAddNewTaskBtn(taskTypeSelect, secondTaskDetailsText, caseManagerOptionSelect, true);

			//clientTaskPage.verifyTaskAdded(secondTaskDetailsText, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "calendar"}, description = "Tasks Delete", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_27_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: Task Delete";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			String userId = caseManagerDashboardPage.fetchUserId();
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickTaskTab(true);

			CM_ClientTaskPage clientTaskPage = new CM_ClientTaskPage(driver);
			
			clientTaskPage.addNewTask(globalVariables.taskTypeSelectClient, globalVariables.deleteTaskDetailsTextClient, userId, true);
			
			clientTaskPage.verifyTask(globalVariables.deleteTaskDetailsTextClient, true);

			clientTaskPage.selectTask(globalVariables.deleteTaskDetailsTextClient, true);

			clientTaskPage.deleteTask(globalVariables.deleteTaskDetailsTextClient, true);

			clientTaskPage.verifyTaskDeleted(globalVariables.deleteTaskDetailsTextClient, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"client", "calendar"}, description = "Tasks view", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_27_1")
	public void CM_TC_27_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: View the task";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickTaskTab(true);

			CM_ClientTaskPage clientTaskPage = new CM_ClientTaskPage(driver);

			clientTaskPage.selectTask(globalVariables.firstTaskDetailsTextClient, true);

			clientTaskPage.verifyTaskDetails(globalVariables.firstTaskDetailsTextClient, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "calendar"}, description = "Tasks Edit", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_27_1")
	public void CM_TC_27_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: Edit task";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickTaskTab(true);

			CM_ClientTaskPage clientTaskPage = new CM_ClientTaskPage(driver);

			clientTaskPage.selectTask(globalVariables.firstTaskDetailsTextClient, true);

			clientTaskPage.editTaskDetails(globalVariables.editfirstTaskDetailsTextClient, true);

			clientTaskPage.verifyTaskDetails(globalVariables.editfirstTaskDetailsTextClient, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"client", "calendar"}, description = "Tasks Print Preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_27_4")
	public void CM_TC_27_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: Print preview task";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickTaskTab(true);

			CM_ClientTaskPage clientTaskPage = new CM_ClientTaskPage(driver);

			clientTaskPage.selectTask(globalVariables.editfirstTaskDetailsTextClient, true);

			clientTaskPage.verifyPrintPreviewDetails(globalVariables.editfirstTaskDetailsTextClient, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "HRP"}, description = "Custom data Add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_15_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager - Client: Custom data Add";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickCustomDataTab();

			clientListPage.addCustomDataField(globalVariables.salary, true);

			clientListPage.verifyCustomDataField(globalVariables.salary, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"case"}, description = "Case:- Custom Data - Navigate into 'Custom Data', edit and save it", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_41_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "Case:- Custom Data - Navigate into 'Custom Data', edit and save it";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);

			caseListPage.clickCustomDataTab(true);
			
			caseListPage.addCustomDataField(globalVariables.salary, true);

			caseListPage.verifyCustomDataField(globalVariables.salary, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Job Details Current job info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_12_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: Edit Current job details";

		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_JobDetailspage jobDetailsPage = clientListPage.clickJobDetailsTab(true);

			jobDetailsPage.editJobDetails(globalVariables.jobTitleCurrent, globalVariables.jobCodeCurrent, true);

			jobDetailsPage.verifyJobDetails(globalVariables.jobTitleCurrent, globalVariables.jobCodeCurrent);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();	
		}
	}
	
	
	@Test(groups = {"client"}, description = "Proposed Job Details job info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_12_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Edit Proposed job details";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_JobDetailspage jobDetailsPage = clientListPage.clickJobDetailsTab(true);

			jobDetailsPage.editProposedJobDetails(globalVariables.jobTitleProposed, globalVariables.jobCityProposed, true);

			jobDetailsPage.verifyProposedJobDetails(globalVariables.jobCityProposed, globalVariables.jobTitleProposed);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();	
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "History Info-Add New Address History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Add New Address History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addAddressHistory(globalVariables.countryNew, globalVariables.cityNew, globalVariables.streetNumber, globalVariables.state, globalVariables.day, globalVariables.month, globalVariables.year);

			historyInfoPage.verifyAddressHistory(globalVariables.cityNew, globalVariables.countryNew, globalVariables.streetNumber, globalVariables.state);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client","HRP"}, description = "History Info-Edit Address History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_13_1")
	public void CM_TC_13_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Edit Address History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.editAddressHistory(globalVariables.cityEditted, globalVariables.cityNew);

			historyInfoPage.verifyAddressHistory(globalVariables.cityEditted, globalVariables.countryNew, globalVariables.streetNumber, globalVariables.state);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client","HRP"}, description = "History Info-Add New Employement History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Add New Employment History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addEmploymentHistory(globalVariables.employer, globalVariables.occupation, globalVariables.day, globalVariables.month, globalVariables.year, globalVariables.numberForPersonalInfo);

			historyInfoPage.verifyEmploymentHistory(globalVariables.occupation, globalVariables.employer);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "History Info-Edit Employement History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_13_5")
	public void CM_TC_13_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Edit Employement History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.editEmploymentHistory(globalVariables.occupation, globalVariables.jobNew);

			historyInfoPage.verifyEmploymentHistory(globalVariables.jobNew, globalVariables.employer);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "History Info-Add New Education History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Add New Education History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addEducationHistory(globalVariables.schoolType, globalVariables.schoolName, globalVariables.universityName, globalVariables.degree, globalVariables.fieldOfStudy, globalVariables.day, globalVariables.month, globalVariables.year, globalVariables.numberForPersonalInfo);

			historyInfoPage.verifyEducationHistory(globalVariables.schoolName, globalVariables.degree, globalVariables.fieldOfStudy);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "History Info-Edit Education History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_13_3")
	public void CM_TC_13_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Edit Education History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.editEducationHistory(globalVariables.schoolName, globalVariables.schoolNameNew);

			historyInfoPage.verifyEducationHistory(globalVariables.schoolNameNew, globalVariables.degree, globalVariables.fieldOfStudy);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups = {"client"}, description = "History Info-Add Marriage History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_7(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Add Marriage History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addMarriageHistory(globalVariables.wifeFirstName, globalVariables.wifeLastName, globalVariables.relationMarriage);

			historyInfoPage.verifyMarriageHistory(globalVariables.wifeFirstName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "History Info-Edit Marriage History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_13_7")
	public void CM_TC_13_8(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");


		globalVariables.testCaseDescription = "Case Manager - Client: History Info-Edit Marriage History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.editMarriageHistory(globalVariables.wifeFirstName, globalVariables.marriagePlace);

			historyInfoPage.verifyEdittedMarriageHistory(globalVariables.marriagePlace);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Edit client Personal Info details ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_8(String browser) throws Exception 
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Edit client Personal Info details";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(clientName, true);

			CM_PersonalInfoPage personalInfoPage = clientListPage.clickPersonalInfoTab(true);

			personalInfoPage.clickEditPersonalInfoButton();

			personalInfoPage.enterClientInfo(globalVariables.cityForPersonalInfo, globalVariables.numberForPersonalInfo, globalVariables.country);

			personalInfoPage.saveClientInfo();

			personalInfoPage.verifyEditedDetails(globalVariables.cityForPersonalInfo, globalVariables.numberForPersonalInfo, globalVariables.country);

			personalInfoPage.choosePersonalAddress("B");

			personalInfoPage.verifyProfileDetails(globalVariables.cityForPersonalInfo, globalVariables.numberForPersonalInfo);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client"}, description = "Edit passport details - Identification Mark", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_72_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager - Client: Editing passport details - Identification Mark";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_PassportInfoPage passportInfoPage = clientCreationPage.selectPassportInfo(true);

			passportInfoPage.editAdditionalPassportDetails(globalVariables.day, globalVariables.year, globalVariables.month, globalVariables.country, globalVariables.cityNew); 

			passportInfoPage.getClientDetailsAndUpdateExcel(workbookNameWrite, sheetName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "HRP"}, description = "Edit client residence details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_72_1_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager: Edit client residence details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickPersonalInfoTab(true);
			
			CM_PersonalInfoPage personalInfoPage = new CM_PersonalInfoPage(driver);

			personalInfoPage.editResidenceInfo(globalVariables.residenceCity, globalVariables.countryForPersonalInfo, globalVariables.apartment, globalVariables.street1, globalVariables.street2, globalVariables.pinCode, globalVariables.stateForPersonalInfo, globalVariables.mobile, globalVariables.telephone);
			
			personalInfoPage.verifyResidenceInfo(globalVariables.residenceCity, globalVariables.countryForPersonalInfo, globalVariables.apartment, globalVariables.street1, globalVariables.street2, globalVariables.pinCode, globalVariables.stateForPersonalInfo, globalVariables.mobile, globalVariables.telephone);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client"}, description = "Add Arrival/Departure info to the client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_14_1(String browser) throws Exception
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager - Client: Add Arrival/Departure info to the client";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_TravelInfoPage travelInfoPage = clientCreationPage.clickTravelInfoTab();

			travelInfoPage.addTravelInfo(globalVariables.arrivalAdmissionNumber);
			
			travelInfoPage.verifyTravelInfo(globalVariables.arrivalAdmissionNumber);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client"}, description = "Edit Arrival/Departure info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_14_1")
	public void CM_TC_14_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String client_DeparturePlaceName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DeparturePlaceName");
		
		globalVariables.testCaseDescription = "Case Manager - Client: Edit Arrival/Departure info details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_TravelInfoPage travelInfoPage = clientCreationPage.clickTravelInfoTab();

			travelInfoPage.editTravelInfo(globalVariables.arrivalAdmissionNumber, client_DeparturePlaceName);

			travelInfoPage.verifyTravelInfo(client_DeparturePlaceName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"client"}, description = "Click GetI_94", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_14_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager - Client: Get I-94";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_TravelInfoPage travelInfoPage = clientCreationPage.clickTravelInfoTab();

			travelInfoPage.clickGetI_94(globalVariables.firstNameForPassport, globalVariables.lastNameForPassport, globalVariables.birthDateForPassport, globalVariables.birthMonthForPassport, globalVariables.birthYearForPassport, globalVariables.passportNumberForPassport, globalVariables.countryForPassport);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"mySettings"}, description = "My settings - case manager details page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - My settings: case manager details page is loading fine";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickMySettings();

			CM_MySettingsPage mysettingsPage = new CM_MySettingsPage(driver);

			mysettingsPage.verfifyCaseManagerDetailsPage(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups = {"mySettings"}, description = "My settings - access rights page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - My settings: access rights page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickMySettings();

			CM_MySettingsPage mysettingsPage = new CM_MySettingsPage(driver);

			mysettingsPage.verifyCaseManagerAccessRights(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups = {"mySettings"}, description = "My settings - email alert preferences page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - My settings: email alert preferences page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickMySettings();

			CM_MySettingsPage mysettingsPage = new CM_MySettingsPage(driver);

			mysettingsPage.verifyCaseManagerEmailAlertPreferences(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"mySettings"}, description = "My settings - my Inszoom page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - My settings: my Inszoom page load";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickMySettings();

			CM_MySettingsPage mysettingsPage = new CM_MySettingsPage(driver);

			mysettingsPage.verifyCaseManagerINSZoom(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"settings"}, description = "Settings - case manager list page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_5(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Settings: case manager list page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);

			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);

			settingsPage.verifyCaseManagerINSZoom(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups = {"settings"}, description = "Settings - Advanced settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_6(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Settings:Advanced settings page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);

			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);

			settingsPage.verifyAdvancedSettings(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"settings"}, description = "Settings - Organization Tools", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_7(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Settings:Organization Tools page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickOrganizationToolsTab();

			settingsPage.verifyOrganizationToolsPage(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups={"KB"}, description = "Knowledge Base - Petition template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_8(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Knowledge Base:Petition template page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);

			knowledgeBasePage.verifyPetitionTemplate(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups={"KB"}, description = "Knowledge Base - Questionnaire list page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_9(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Knowledge Base:Questionnaire list page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);

			knowledgeBasePage.verifyQuestionnaireTemplate(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}

	
	@Test(groups={"KB"}, description = "Knowledge Base - Email template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_61_10(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case Manager - Knowledge Base:Email template page load";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);

			knowledgeBasePage.verifyEmailTemplatesPage(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups={"reports"}, description = "Reports :Add a report as Favorite report from management reports and verify from favorite report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_54_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String client_FavoriteReportName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_FavoriteReportName");

		globalVariables.testCaseDescription = "Reports :Add a report as Favorite report from management reports and verify from favorite report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickReportsTab(true);

			ReportsPage reportsPage = new ReportsPage(driver);
			
			reportsPage.clearOldfavoriteReports();
					
			reportsPage.clickManagementReportsTab();
			
			ManagementReportsPage managementReportsPage = new ManagementReportsPage(driver);
			
			managementReportsPage.addFavoriteReport(client_FavoriteReportName);
			
			FavoriteReportPage favoriteReportPage = new FavoriteReportPage(driver);
			
			reportsPage.clickFavoriteReportsTab();
			
			favoriteReportPage.verifyFavoriteReportPresent();

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		}

		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"reports"}, description = "Reports : Get Favourite Report and check Text/Excel download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_54_3(String browser) throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String report_Option_Corporation = readExcel.initTest(workbookName, sheetName, "QA_A_Report_Option_Corporation");
		String clientStatus = "Both";

		globalVariables.testCaseDescription = "Reports : Set Favorite report data and verify GET REPORT functionality and verify download links in EXPORT FILE";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickReportsTab(true);

			ReportsPage reportsPage = new ReportsPage(driver);
                
			reportsPage.clickFavoriteReportsTab();

			FavoriteReportPage favoriteReportPage = new FavoriteReportPage(driver);
                
			favoriteReportPage.clickFavoriteReportNameLink(globalVariables.favoriteReportName);
                
			ManagementReportsPage managementReportPage = new ManagementReportsPage(driver);
                
			managementReportPage.setDataToGetReport(report_Option_Corporation, clientStatus);
                
			managementReportPage.clickGetReportButton();
                
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.waitForAllWindowsToLoad(1, driver);
                
			managementReportPage.verifyReportTable(true);

			managementReportPage.clickExportFileButton();

			managementReportPage.verifyReportFileLinks();

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
    }
	
	
	@Test(groups = {"reports"}, description = "Reports : Search Customized Report and check Text/Excel download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_54_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String report_Option_Corporation = readExcel.initTest(workbookName, sheetName, "QA_A_Report_Option_Corporation");
		String clientStatus = "Both";

		globalVariables.testCaseDescription = "Reports : Search Customized Report and check Text/Excel download option";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickReportsTab(true);
			
			caseManagerDashboardPage.clickReportsTab(true);;

			ReportsPage reportsPage = new ReportsPage(driver);
			
			reportsPage.clickCustomizedReportsTab();
					
			CustomizedReportsPage customizedReportsPage = new CustomizedReportsPage(driver);
			
			customizedReportsPage.findAndSelectReport(globalVariables.favoriteReportName);

			ManagementReportsPage managementReportPage = new ManagementReportsPage(driver);
			
			managementReportPage.setDataToGetReport(report_Option_Corporation, clientStatus);
			
			managementReportPage.clickGetReportButton();
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.waitForAllWindowsToLoad(1, driver);
			
			managementReportPage.verifyReportTable(true);

			managementReportPage.clickExportFileButton();

			managementReportPage.verifyReportFileLinks();

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"reports"}, description = "Reports : Management Reports loadded and check Text/Excel download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_54_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String client_FavoriteReportName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_FavoriteReportName");
		String report_Option_Corporation = readExcel.initTest(workbookName, sheetName, "QA_A_Report_Option_Corporation");
		String clientStatus = "Both";

		globalVariables.testCaseDescription = "Reports : Management Reports loadded and check Text/Excel download option";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickReportsTab(true);;

			ReportsPage reportsPage = new ReportsPage(driver);
			
			reportsPage.clickManagementReportsTab();
			
			ManagementReportsPage managementReportPage = new ManagementReportsPage(driver);
			
			managementReportPage.clickReportName(client_FavoriteReportName);
			
            managementReportPage.setDataToGetReport(report_Option_Corporation, clientStatus);
			
			managementReportPage.clickGetReportButton();
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.waitForAllWindowsToLoad(1, driver);
			
			managementReportPage.verifyReportTable(true);

			managementReportPage.clickExportFileButton();

			managementReportPage.verifyReportFileLinks();

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"reports"}, description = "Reports : Management Reports loaded and check Text/Excel download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_54_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String client_AdhocReportName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_AdhocReportName");
		String report_Option_Corporation = readExcel.initTest(workbookName, sheetName, "QA_A_Report_Option_Corporation");
		String clientStatus = "Both";
		globalVariables.testCaseDescription = "Reports : Management Reports loaded and check Text/Excel download option";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickReportsTab(true);;

			ReportsPage reportsPage = new ReportsPage(driver);

			reportsPage.clickManagementReportsTab();
			
			ManagementReportsPage managementReportPage = new ManagementReportsPage(driver);
			
	        managementReportPage.clickReportName(client_AdhocReportName);
			
            managementReportPage.setDataToGetReport(report_Option_Corporation, clientStatus);
			
			managementReportPage.clickGetReportButton();
			
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.waitForAllWindowsToLoad(1, driver);
			
			managementReportPage.verifyReportTable(true);

			managementReportPage.clickExportFileButton();

			managementReportPage.verifyReportFileLinks();

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Add Docs Checklist and upload a document to the created docs checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_42_1(String browser) throws Exception 
	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		String[] docChecklistName = {globalVariables.docChecklistNameExtra};
		String[] docChecklistNameKey = {"QA_A_Case_addedBasicDesc"};

		globalVariables.testCaseDescription = "Case: Add Docs Checklist and upload a document to the created docs checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistNameExtra);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameExtra);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
			
			caseDocChecklistPage.uploadDocument(globalVariables.docChecklistNameExtra, globalVariables.filePath, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(globalVariables.docChecklistNameExtra, globalVariables.fileName);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Edit Docs Checklist Name and Add Descripton", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
	public void CM_TC_42_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		String[] docChecklistName = {globalVariables.docChecklistNameNew};
		String[] docChecklistNameKey = {"QA_A_Case_addedBasicDesc"};
		
		String nameOld = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case: Edit Docs Checklist Name and Add Descripton";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.editDocChecklistNameAndAddDescription(clientName, nameOld, globalVariables.docChecklistNameNew, globalVariables.docChecklistDescriptionNew);
			
			caseDocChecklistPage.verifyIfChecklistEditedAndDescriptionAdded(globalVariables.docChecklistNameNew, globalVariables.docChecklistDescriptionNew);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Edit Document Access", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_2")
	public void CM_TC_42_2_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case: Edit Document Access";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.changeDocumentAccessRights(docChecklistName, globalVariables.fileName, "Corporation");
			
			caseDocChecklistPage.changeDocumentAccessRights(docChecklistName, globalVariables.fileName, "Client");
			
			caseDocChecklistPage.changeDocumentAccessRights(docChecklistName, globalVariables.fileName, "Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(docChecklistName, globalVariables.fileName, "Corporation");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(docChecklistName, globalVariables.fileName, "Client");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(docChecklistName, globalVariables.fileName, "Vendor");
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Verify if the Document uploaded for Client is available in 'Previous Documents'and is available to be attached as Case Email Attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"CM_TC_21_1"})
	public void CM_TC_42_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case: Verify if the Document uploaded for Client is available in 'Previous Documents'and is available to be attached as Case Email Attachment";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.clickPreviousDocuments();
			
			caseDocChecklistPage.checkPreviousDocuments(clientName, globalVariables.fileNameWithoutExtension);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.attachDocument(globalVariables.fileNameWithoutExtension);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}

	
	@Test(groups = { "case"}, description = "Case: Select All and Download", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
	public void CM_TC_42_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Select All and Download";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
//			int numberOfFiles = Utils.getAllDownloadedFiles().length;
			
			caseDocChecklistPage.downloadAllDocuments(driver);
			
			caseDocChecklistPage.verifyIfDownloaded(driver);
		
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Download a Single Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_2")
	public void CM_TC_42_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case: Download a Single Document";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.downloadSingleDocument(docChecklistName, globalVariables.fileName, driver);
			
			caseDocChecklistPage.verifyIfDownloaded(driver);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Change uploaded document settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_42_2_1")
	public void CM_TC_42_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case: Change uploaded document settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(docChecklistName, globalVariables.fileName);
			
			caseDocChecklistPage.editDocumentDetails(docChecklistName, globalVariables.fileNameNew, globalVariables.fileDescription, globalVariables.folderName);
			
			caseDocChecklistPage.verifyIfDocumentEditted(docChecklistName, globalVariables.fileNameNew, globalVariables.fileDescription, globalVariables.folderName);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case: Docs Check List- Copy from template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_42_7(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Docs Check List- Copy from template";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			String checklistName = caseDocChecklistPage.copyFromTemplate();
			
			caseDocChecklistPage.verifyIfChecklistAdded(checklistName);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = { "case"}, description = "Case - Delete Document from doc checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_42_6")
	public void CM_TC_42_8(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case - Delete Document from doc checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.deleteUploadedDocument(docChecklistName, globalVariables.fileNameNew);
			
			caseDocChecklistPage.verifyIfDocumentDeleted(docChecklistName, globalVariables.fileNameNew);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case"}, description = "Case - Delete doc checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_42_6")
	public void CM_TC_42_9(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "QA_A_Case_addedBasicDesc");

		globalVariables.testCaseDescription = "Case - Delete doc checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.deleteDocChecklist(docChecklistName);
			
			caseDocChecklistPage.verifyIfChecklistDeleted(docChecklistName);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Letter(MS WORD)-Template from server(KB Templates) option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_22_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Template from server(KB Templates) option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.useServerTemplateAndVerify(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_1")
	public void CM_TC_22_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Verify download option";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.downloadAndVerify(globalVariables.letterMSWordTemplateName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_1")
	public void CM_TC_22_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Verify edit option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_3")
	public void CM_TC_22_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Verify delete option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Upload letter from desktop and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_22_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Upload letter from desktop and verify";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.uploadLetterFromDesktopAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.letterTemplateFilePath);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_5")
	public void CM_TC_22_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.downloadAndVerify(globalVariables.localLetterMSWordTemplateName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_5")
	public void CM_TC_22_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_22_7")
	public void CM_TC_22_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerifyLocalTemplate(globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Template from server(KB Templates) option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_90_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Template from server(KB Templates) option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.useServerTemplateAndVerify(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_1")
	public void CM_TC_90_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD)-Verify download option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.downloadAndVerify(globalVariables.letterMSWordTemplateName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_1")
	public void CM_TC_90_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD)-Verify edit option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_3")
	public void CM_TC_90_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD)-Verify delete option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Upload letter from desktop and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_90_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD)-Upload letter from desktop and verify";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.uploadLetterFromDesktopAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.letterTemplateFilePath);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_5")
	public void CM_TC_90_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.downloadAndVerify(globalVariables.localLetterMSWordTemplateName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_5")
	public void CM_TC_90_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_90_7")
	public void CM_TC_90_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerifyLocalTemplate(globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "case"}, description = "Form/s add form", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_43_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case Manager : Adding the form under case";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickFormsTab();

			CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);

			caseformPage.addFormAndVerify(globalVariables.formsFrom, globalVariables.formGroup, globalVariables.caseFormName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}


	@Test(groups = {"forms", "case"}, description = "Form/s edit form", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_43_1")
	public void CM_TC_43_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case Manager : Editing the form under case";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
		
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickFormsTab();

			CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);
			
			caseformPage.editFastFormAndVerify(globalVariables.caseFormName, globalVariables.middleName, globalVariables.countyOfCitizenship, globalVariables.streetName, globalVariables.cityName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "case"}, description = "Form/s delete", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_43_8")
	public void CM_TC_43_5(String browser) throws Exception {
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case Manager : Delete the form under case";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
		
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickFormsTab();

			CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);

			caseformPage.deleteFormAndVerfy(globalVariables.caseFormName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "case"}, description = "Form/s email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_43_1")
	public void CM_TC_43_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case Manager : Email the form under case";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
		
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickFormsTab();

			CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);
			
			caseformPage.sendEmailAndVerify(globalVariables.caseFormName, globalVariables.messageBodyForFormsEmail, globalVariables.subjectForEmailForm);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"forms", "client"}, description = "Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_19_1(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String caseID = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case Manager - Client: Forms";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickFormsTab();

			CM_ClientFormsPage formspage = new CM_ClientFormsPage(driver);

			formspage.clickEmailForms();
			
			formspage.verifyCaseId(caseID);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "client"}, description = "Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_19_2(String browser) throws Exception

	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Forms";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickFormsTab();

			CM_ClientFormsPage formspage = new CM_ClientFormsPage(driver);

			formspage.clickFormNameLink(caseCreated);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "client"}, description = "Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_19_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Forms";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickFormsTab();

			CM_ClientFormsPage formspage = new CM_ClientFormsPage(driver);

			formspage.clickAddFormsLink();

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"forms", "case"}, description = "Form/s Reload", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_43_2")
	public void CM_TC_43_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String lastClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedLastClientName");

		globalVariables.testCaseDescription = "Case Manager : Choosing reload value in dropdown for form under case";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickFormsTab();

			CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);
			
			caseformPage.reloadFormAndVerify(globalVariables.caseFormName, clientName, lastClientName);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
//	@Test(groups = {"ToDo", "client"}, description = "Client: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_98_1(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickClientTab(true);
//			
//			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//			
//			clientListPage.clickOnClientName(clientFirstName, true);
//			
//			clientListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuestionnaireTemplate("client", clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "client"}, description = "Client: To-Do Docs and Letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_21_1")
//	public void CM_TC_98_2(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoDocsAndLetterInstruction = "Instruction for To-Do Docs and Letter";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickClientTab(true);
//			
//			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//			
//			clientListPage.clickOnClientName(clientFirstName, true);
//			
//			clientListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingDocsAndLetter("client", clientName, toDoDocsAndLetterInstruction, econsent, globalVariables.fileNameWithoutExtension);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "client"}, description = "Client: To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_98_3(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoQuestionnaireInstruction = "Instruction for To-Do Invoice";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Client: To-Do Invoice";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickClientTab(true);
//			
//			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//			
//			clientListPage.clickOnClientName(clientFirstName, true);
//			
//			clientListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingInvoiceTemplate("client", clientName, toDoQuestionnaireInstruction, econsent);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	@Test(groups = {"ToDo", "client"}, description = "Client: To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_98_4(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
//		
//		String toDoName = "To Do Quick Task Test";
//		
//		globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickClientTab(true);
//			
//			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//			
//			clientListPage.clickOnClientName(clientFirstName, true);
//			
//			clientListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuickTask("client", clientName, toDoName, toDoQuickTaskInstruction);
//			
//			//clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_102_1(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickCorporationTab(true);
//			
//			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
//			
//			corpListPage.clickOnCorporationName(corpName);
//			
//			corpListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuestionnaireTemplate("corp",assignTo, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Docs and Letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_105")
//	public void CM_TC_102_2(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String toDoDocsAndLetterInstruction = "Instruction for To-Do Docs and Letter";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickCorporationTab(true);
//			
//			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
//			
//			corpListPage.clickOnCorporationName(corpName);
//			
//			corpListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingDocsAndLetter("corp", assignTo, toDoDocsAndLetterInstruction, econsent, globalVariables.fileNameWithoutExtension);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_101_1")
//	public void CM_TC_102_3(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		String toDoInvoiceInstruction = "Instruction for To-Do Invoice";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickCorporationTab(true);
//			
//			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
//			
//			corpListPage.clickOnCorporationName(corpName);
//			
//			corpListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingInvoiceTemplate("corp", assignTo, toDoInvoiceInstruction, econsent);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "corporation"}, description = "Corp : To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_102_4(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
//		
//		String toDoName = "To Do Quick Task Test";
//		
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//
//			login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//
//			caseManagerHomePage.clickCorporationTab(true);
//			
//			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
//			
//			corpListPage.clickOnCorporationName(corpName);
//			
//			corpListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuickTask("corp", assignTo, toDoName, toDoQuickTaskInstruction);
//			
//			//clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerHomePage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "case"}, description = "Case: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_112_1(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Case: To-Do Questionnaire";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//			
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuestionnaireTemplateAndDocsCheckList(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "case"}, description = "Case: To-Do Docs CheckLists", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
//	public void CM_TC_112_2(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoDocCheckListInstruction = "Instruction for To-Do Docs CheckLists";
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Case: To-Do Docs CheckLists";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//			
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingDocsCheckList(clientName, toDoDocCheckListInstruction, econsent, globalVariables.docChecklistName);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	
//	@Test(groups = {"ToDo", "case"}, description = "Case: To-Do Docs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
//	public void CM_TC_112_2_1(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoDocsInstruction = "Instruction for To-Do Docs";
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Case: To-Do Docs";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//			
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoDocs(clientName, toDoDocsInstruction, econsent, globalVariables.fileNameWithoutExtension);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	
//	@Test(groups = {"ToDo", "case"}, description = "Case: To-Do Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_38_0")
//	public void CM_TC_112_2_2(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//		
//		String toDoFormsInstruction = "Instruction for To-Do Forms";
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//
//		globalVariables.testCaseDescription = "Case: To-Do Forms";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//			
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoForms(clientName, toDoFormsInstruction, econsent, globalVariables.formNameForAddTestcase);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	
//	@Test(groups = {"ToDo", "case"}, description = "Case : To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_112_3(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String toDoInvoiceInstruction = "Case : To-Do Invoice";
//		
//		//String econsent = "e-consent for Automation";
//		
//		String econsent = "Agreement Policy";
//		
//		String toDoName = "To Do Invoice Test";
//		
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//            
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingInvoiceTemplate("case", assignTo, toDoInvoiceInstruction, econsent);
//			
//			//clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//	
//	@Test(groups = {"ToDo", "case"}, description = "Case : To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_112_4(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
//		
//		String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
//		
//		String toDoName = "To Do Quick Task Test";
//		
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickCaseTab(true);
//            
//            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//
//            caseListPage.clickOnCaseId(caseCreated, true);
//            
//            caseListPage.clickToDoTab();
//			
//			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
//			
//			clientToDoPage.addNewToDoUsingQuickTask("case", assignTo, toDoName, toDoQuickTaskInstruction);
//			
//			//clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}

	
	@Test(groups = {"questionnaire","client"}, description = "CLIENT : Remove all Questionnaires", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_0_2")
	public void CM_TC_20_0_1(String browser) throws Exception 
	
	/* Updated the script as the client questionnaire remove functionality has changed. As part of the change
	 * Added removeClientQuestionnaire and verifyAllClientQuestionnaireRemoved in Page level 
	 * Modified by : Saurav
	 * Modified Date : 14/06/2021
	*/
	
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Remove all Questionnaires";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickQuestionnairesTab();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
            clientQuestionnairePage.removeClientQuestionnaire();
			
			clientQuestionnairePage.verifyAllClientQuestionnaireRemoved();
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "Case : Remove all Questionnaires", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_20_0_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case : Remove all Questionnaires";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);

			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.removeAllQuestionnaires();
			
			caseQuestionnairePage.verifyIfQuestionnairesRemoved();
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire","client"}, description = "CLIENT : Add Questionnaires", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_0_1")
	public void CM_TC_20_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Add Questionnaires";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickQuestionnairesTab();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			clientQuestionnairePage.addQuestionnaire(data.questionnaire);
			
			clientQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, clientName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","client"}, description = "CLIENT : Check if added questionnaires are available in the compose email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_1")
	public void CM_TC_20_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Check if added questionnaires are available in the compose email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.clickIncludeQuestionnaires();
			
			clientEmailsPage.verifyIfQuestionnairesPresent(data.questionnaire);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "client"}, description = "CLIENT : Send an email after linking a new questionnaire and check if the new questionnaire appears as linked in the questionnaire page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_20_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Send an email after linking a new questionnaire and check if the new questionnaire appears as linked in the questionnaire page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickQuestionnairesTab();
			
			clientCreationPage.extractQuestionnaireCount();

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.clickIncludeQuestionnaires();
		
			clientEmailsPage.clickAddRemoveQuestionnaires();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
			clientQuestionnairePage.addQuestionnaire(data.secondQuestionnaire);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
			
			clientEmailsPage.verifyIfQuestionnairesPresent(data.secondQuestionnaire);
			
			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientCreationPage.clickQuestionnairesTab();
			
			clientCreationPage.verifyQuestionnaireAddedAfterEmail();
			
			/*clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			clientQuestionnairePage.verifyIfQuestionnairesAdded(data.secondQuestionnaire, clientName);*/
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","client"}, description = "CLIENT : Send an email after unlinking all questionnaires and check if the questionnaires appear as linked in the questionnaire page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_4")
	public void CM_TC_20_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Send an email after unlinking all questionnaires and check if the questionnaires appear as linked in the questionnaire page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.clickIncludeQuestionnaires();
		
			clientEmailsPage.clickAddRemoveQuestionnaires();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
			clientEmailsPage.removeSelectedQuestionnaire(data.secondQuestionnaire);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
			
			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientCreationPage.clickQuestionnairesTab();
			
			clientEmailsPage.verifyQuestionnairePresentInQuestionnaireListPage(data.secondQuestionnaire);
			
			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			clientQuestionnairePage.verifyQuestionnaireInSearchWindow(data.secondQuestionnaire, clientName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "CASE : Send Email to get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_45_1")
	public void CM_TC_59_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CASE : Send Email to get URL and Access Code";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistName);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistName);
			
			caseDocChecklistPage.uploadDocument(globalVariables.docChecklistName, globalVariables.filePath, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(globalVariables.docChecklistName, globalVariables.fileName);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");

			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			caseEmailsPage.includeDocChecklist(globalVariables.docChecklistName);
			
			caseEmailsPage.clickIncludeQuestionnaires();
			
			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "CASE : Login using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_59_1")
	public void CM_TC_59_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode");

		globalVariables.testCaseDescription = "CASE : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "CASE : Edit Questionnaire using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_59_2")
	public void CM_TC_59_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode");

		globalVariables.testCaseDescription = "CASE : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editQuestionnaire(data.questionnaire);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "CASE : Upload Document using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_59_2")
	public void CM_TC_59_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode");

		globalVariables.testCaseDescription = "CASE : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.uploadCaseDocument(globalVariables.filePath);
			
			accessCodePage.sendConfirmation();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","case"}, description = "CASE : Add Questionnaires", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_0_2")
	public void CM_TC_45_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE : Add Questionnaires";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);

			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.addQuestionnaire(data.questionnaire);
			
			caseQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, caseId);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "case", "email"}, description = "CASE : Send Email after attaching Questionnaire, uploading Document and including e-consent", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_45_1", "CM_TC_59_1"})
	public void CM_TC_52_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE : Send Email after attaching Questionnaire, uploading Document and including e-consent";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.caseEmailSubject);
			
			caseEmailsPage.includeDocChecklist(globalVariables.docChecklistName);
			
			caseEmailsPage.clickIncludeQuestionnaires();
			
			caseEmailsPage.attachDocument(globalVariables.fileNameWithoutExtension);
			
			caseEmailsPage.selectEConsent(data.eConsent);
			
			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.verifyIfEmailSent(globalVariables.caseEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "corporation"}, description = "Corporation: Questionairre add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC_30_2")
	public void CM_TC_65_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Corporation: Questionairre add";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(corpName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.addQuestionnaire(data.secondQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(data.secondQuestionnaire, corpName);
			
			corporationQuestionnairePage.grantAccess();

			/*LoginPageTest loginhrp = new LoginPageTest(driver, webSite);

			loginhrp.hrpLogin(hrpLoginID, hrpPassword, true);

			loginhrp.clickAgreeButton(false);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.checkIfQuestionnaireAdded(data.secondQuestionnaire);
*/
			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "email", "client"}, description = "Client : Send Email to get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_20_1")
	public void CM_TC_57_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Client : Send Email to get URL and Access Code";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(false);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, false);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			clientEmailsPage.clickIncludeQuestionnaires();
			
			clientEmailsPage.clickClientCanUploadDocumentsCheckbox();
			
			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","client"}, description = "Client : Login using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_57_1")
	public void CM_TC_57_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL_Client");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode_Client");

		globalVariables.testCaseDescription = "Client : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","client"}, description = "Client : Edit Questionnaire using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_57_2")
	public void CM_TC_57_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL_Client");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode_Client");

		globalVariables.testCaseDescription = "Client : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editQuestionnaire(data.questionnaire);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","client"}, description = "Client : Upload Document using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_57_3")
	public void CM_TC_57_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL_Client");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode_Client");

		globalVariables.testCaseDescription = "Client : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.uploadClientDocument(globalVariables.filePath);
			
			accessCodePage.sendConfirmation();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","corporation"}, description = "Corporation: Email questionnaires", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_65_1")
	public void CM_TC_58_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Corporation: Email questionnaires";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(corpName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			corporationEmailPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}


	@Test(groups = {"questionnaire","corporation","email"}, description = "Corporation: Get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_58_0")
	public void CM_TC_58_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Corporation: Get URL and Access Code";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(corpName);
			
			corporationListPage.clickEmailsTab();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire","corporation"}, description = "Corporation : Login using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_58_1")
	public void CM_TC_58_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");

		globalVariables.testCaseDescription = "Client : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","corporation"}, description = "Corporation : Edit Questionnaire using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_58_2")
	public void CM_TC_58_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Corporation : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editCorporationQuestionnaire(data.secondQuestionnaire,corpName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","corporation"}, description = "Corporation : Send Confirmation and Verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_58_3")
	public void CM_TC_58_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");

		globalVariables.testCaseDescription = "Corporation : Send Confirmation and Verify";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);
			
			accessCodePage.sendConfirmation();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","corporation"}, description = "Corporation: Email Questionnaire and check in List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_65_1")
	public void CM_TC_2_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Corporation: Email Questionnaire and check in List Page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(corpName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.addQuestionnaire(data.questionnaire);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Email Questionnaires", "title", "false");
			
			corporationEmailPage.verifyIfQuestionnairesPresent(data.questionnaire);
			
			corporationEmailPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
						
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, corpName);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire","corporation"}, description = "Corp : Send an email after unlinking all questionnaires and check if the questionnaires appear as linked in the questionnaire page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_2_4")
	public void CM_TC_2_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Corp : Send an email after unlinking all questionnaires and check if the questionnaires appear as linked in the questionnaire page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);

			corporationListPage.clickOnCorporationName(corpName);

			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.removeSelectedQuestionnaire(data.questionnaire);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Email Questionnaires", "title", "false");
			
			corporationEmailPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
						
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, corpName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
//	@Test(groups = {"invoice","corporation"}, description = "Invoices - Corp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_101_1(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		String paymentTerms = "Generic Terms";
//
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickInvoiceTab();
//            
//            CM_InvoicePage invoicePage = new CM_InvoicePage(driver);
//            
//            invoicePage.addNewInvoiceForCorp(corpName, paymentTerms);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//
//	
//	
//	
//	
//	@Test(groups = {"invoice","client","case"}, description = "Invoices - Client & Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void CM_TC_101_2(String browser) throws Exception 
//	{
//
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		
//		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		
//		String paymentTerms = "Generic Terms";
//
//		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		
//		globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//
//		try {
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//            login.clickAgreeButton(false);
//
//            caseManagerDashboardPage.clickInvoiceTab();
//            
//            CM_InvoicePage invoicePage = new CM_InvoicePage(driver);
//            
//            invoicePage.addNewInvoiceForClient(clientName, caseCreated, paymentTerms);
//			
//			caseManagerDashboardPage.clickLogout(true);
//
//			Log.testCaseResult();
//	
//		}
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
	
	@Test(groups={"FNP", "Priority2","client","HRP"}, description = "Client: Check Visas list page is loaded with details and ADD VISA", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_16_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Client: Check Visas list page is loaded with details";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickVisaTab();
			
			clientListPage.addVisaFields(globalVariables.countryName, globalVariables.visaNumber, globalVariables.visaType, globalVariables.genericStartDate, globalVariables.genericEndDate);
			
			//clientListPage.addVisaFields(globalVariables.countryName, globalVariables.visaNumber, true);
			
			//clientListPage.verifyVisaFields(globalVariables.verificationCountry, globalVariables.visaNumber, globalVariables.genericStartDate, globalVariables.genericEndDate, globalVariables.visaType); 

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Client: Edit VISA details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_16_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Client: Check Visas list page is loaded with details";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickVisaTab();
			
			clientListPage.editVisaNumber(globalVariables.visaNumber, globalVariables.edittedVisaNumber, true);
			
			//clientListPage.verifyVisaFields(globalVariables.verificationCountry, globalVariables.edittedVisaNumber, globalVariables.genericStartDate, globalVariables.genericEndDate, globalVariables.visaType); 
			 
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "HRP"}, description = "Client: Add Visas to the relatives", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "CM_TC_4")
	public void CM_TC_79_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String QA_ALoginSavedRelativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeName");
		
		globalVariables.testCaseDescription = "Client: Add Visas to the relatives";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickRelativesTab();

			CM_EditClientRelativePage relativePage = new CM_EditClientRelativePage(driver);
			
			relativePage.selectRelativeNameForVisa(QA_ALoginSavedRelativeName, true);
			
			clientListPage.clickVisasTab(true);

			CM_Client_Relative_VisaPage clientRelativeVisaPage = new CM_Client_Relative_VisaPage(driver);

			clientRelativeVisaPage.addRelativeVisaDetails(globalVariables.visaCountry, globalVariables.visaNumber, globalVariables.genericStartDate, globalVariables.genericEndDate); 

			//String LoginRelativeVisaNumber = readExcel.initTest(workbookName, sheetName, "QA_ALoginRelativeVisaNumber");

			clientRelativeVisaPage.verifyAddedVisa(globalVariables.visaNumber, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case"}, description = "Details/Dates - Edit Visa priority date info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_35_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Case Manager - Edit Visa priority date info details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickDetailsDatesTab(true);

			CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);
			 
			detailsAndDatesPage.editVisaPriorityDateInfo(globalVariables.visaCategory,globalVariables.consularProcessingCity, true); 
			 
			detailsAndDatesPage.verifyEditedVisaPriorityInfo(globalVariables.consularProcessingCity,true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "US Immigration Info Add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_11_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: US immigration info to add the visa";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clicktUSImmigrationTab();

			CM_UsImmigrationInfoPage usImmigrationInfoPage = new CM_UsImmigrationInfoPage(driver);

			usImmigrationInfoPage.addVisaInfo(workbookNameWrite, sheetName,globalVariables.visaNumber, true);

			//usImmigrationInfoPage.verifyVisaInfo(visaNumber,true);//optional
			
			String client_visaDate = readExcel.initTest(workbookName, sheetName, "QA_A_Client_visaDate");
			
			usImmigrationInfoPage.verifyVisaExpirationDate(client_visaDate, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		} 
	}
	
	
	@Test(groups = {"client"}, description = "US Immigration Info Edit", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_11_2(String browser) throws Exception {
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Case Manager-Client: US immigration info to edit the visa";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clicktUSImmigrationTab();

			CM_UsImmigrationInfoPage usImmigrationInfoPage = new CM_UsImmigrationInfoPage(driver);

			usImmigrationInfoPage.editVisaExpirationDate(workbookNameWrite, sheetName, true);

			String client_EditVisaDate = readExcel.initTest(workbookName, sheetName, "QA_A_Client_EditVisaDate");

			usImmigrationInfoPage.verifyVisaExpirationDate(client_EditVisaDate, true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	//for expired passport
	@Test(groups={"FNP", "Priority2","client"}, description = "Add another Passport Info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_10_2_1(String browser) throws Exception 
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Add another Passport Info details";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			CM_PassportInfoPage passportInfoPage = clientCreationPage.selectPassportInfo(true);

			passportInfoPage.addAdditionalPassportDetails(globalVariables.QA_A_SecondPassPortNumber, globalVariables.secondPassportFirstName, globalVariables.secondPassportLastName, globalVariables.nationality, globalVariables.expirationDate, globalVariables.issuingCountry, globalVariables.expirationIssueDate);

			passportInfoPage.verifyPassportDetailsAdded(globalVariables.QA_A_SecondPassPortNumber, globalVariables.secondPassportFirstName, globalVariables.secondPassportLastName, globalVariables.nationality, true);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.fail("Error - " + e.getMessage(), driver, true);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "Add New Passport Info Details to the client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_10_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Add New Passport Info Details to the client";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickPassportInfoTab();

			CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);

			passportInfoPage.addAdditionalPassportDetails(globalVariables.passportNumber, globalVariables.firstName, globalVariables.lastName, globalVariables.nationality, globalVariables.QA_A_PassportExpiresOnDateAddnew, globalVariables.issuingCountry, globalVariables.receiptNoticeDate);
			
			passportInfoPage.verifyPassportDetailsAdded(globalVariables.passportNumber, globalVariables.firstName, globalVariables.lastName, globalVariables.nationality, true);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}


	@Test(groups = {"client"}, description = "Edit Passport Info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_10_2(String browser) throws Exception 
	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager - Client: Edit Passport Info details";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickPassportInfoTab();

			CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);

			passportInfoPage.editAdditionalPassportDetails(globalVariables.day, globalVariables.year, globalVariables.month, globalVariables.country, globalVariables.cityNew); 
			 
		//	passportInfoPage.verifyEdittedAdditionalPassportDetails(globalVariables.edittedFirstName, globalVariables.issuePlace, globalVariables.issuingCountry);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.fail("ERROR - " + e.getMessage(), driver, true);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client"}, description = "Client: Status Docs:Status Doc page loaded or not", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_17_1(String browser) throws Exception {
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "Client: Status Docs:Status Doc page loaded or not";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickStatusDocsTab(true);
			
			clientListPage.verifiedDocumentExpirationTable(true);

			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

		
		@Test(groups = {"client"}, description = "Client: Status Docs:Add/Add all option to link the documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_17_2(String browser) throws Exception {
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			globalVariables.testCaseDescription = "Client: Status Docs:Add/Add all option to link the documents";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				
				clientListPage.clickOnClientName(clientName,true);
				
				clientListPage.clickStatusDocsTab(true);
				
				StatusDocumentsLinkPage statusDocsPage = new StatusDocumentsLinkPage(driver);
				
				statusDocsPage.removeAllStatusDocsPresent();
				
				statusDocsPage.verifyRemoveAllStatusDocs();
				
				statusDocsPage.addAllStatusDocumentsAndVerify();

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			} catch (Exception e) {
				Log.exception(e, driver);

			}

			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"client"}, description = "Client: Status Docs - Edit the details and save it", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_17_3(String browser) throws Exception {

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

			globalVariables.testCaseDescription = "Client: Status Docs - Edit the details and save it";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickStatusDocsTab(true);
				
				String[] documents = { "I-94", "Visa", "EAD", "Advance Parole Date" };
				
				StatusDocumentsLinkPage statusDocsPage = new StatusDocumentsLinkPage(driver);
				
				statusDocsPage.attachStatusDocsAndVerify(documents, true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			} catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Creation of Canada Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_40_0(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			//String corpName = "Automation18-10-2019 09:30:42";
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
			globalVariables.testCaseDescription = "Case Manager: Creation of Canada Case in Case Manager";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
				login.clickAgreeButton(false);

				CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);

	            caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCanadaCase_CountryName, data.petitionTypeForCanada, true);		   

				caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForCanada", data.AddCanadaCase_CountryName, savedClientName, corpName, workbookNameWrite, sheetName, true);

				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Case : Create Canada based Case then check Add Status Doc", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_40_0")
		public void CM_TC_40_1(String browser) throws Exception
		{
		
		     ReadWriteExcel readExcel = new ReadWriteExcel();
		     String caseCreated_Canada = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForCanada");
		
		     globalVariables.testCaseDescription = "Case : Create Canada based Case then check Add Status Doc";
		     final WebDriver driver = WebDriverFactory.get(browser);
		     driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		     try {
		           LoginPageTest login = new LoginPageTest(driver, webSite);
		
		           CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		           login.clickAgreeButton(false);
		           
		           caseManagerDashboardPage.clickCaseTab(true);
		
		           CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
		                              
		           caseListPage.clickOnCaseId(caseCreated_Canada, true);
		
		           caseListPage.clickStatusDocsTab(true);
		           
		           StatusDocumentsLinkPage statusDocsPage = new StatusDocumentsLinkPage(driver);
		           
		           statusDocsPage.addAllStatusDocumentsAndVerifyInCanadaCase();
		                                
		           caseManagerDashboardPage.clickLogout(true);
		
		                
		          Log.testCaseResult();
		               } 
		          catch (Exception e)
		     	  {
		              Log.exception(e, driver);
		           } 
		          finally 
		          {
		               Log.endTestCase();
		               driver.quit();
		           }
			}

		
		@Test(groups = {"case"}, description = "Case : Edit and Save Status Doc", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_40_1")
		public void CM_TC_40_2(String browser) throws Exception

		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated_Canada = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForCanada");

			globalVariables.testCaseDescription = "Case : Edit and Save Status Doc";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseCreated_Canada, true);
				
				caseListPage.clickStatusDocsTab(true);
				
				String[] statusDocs = { "Permanent Residence Card (PR)", "Temporary Resident Permit (TRP)",
						"Temporary Resident Visa (TRV)" };
				
				StatusDocumentsLinkPage statusDocsPage = new StatusDocumentsLinkPage(driver);
				
				statusDocsPage.attachStatusDocsAndVerifyInCase(statusDocs, true);
				
				caseManagerDashboardPage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case", "HRP"}, description = "Case -  Status & Reminders/Steps -select set access for corporation access", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_0(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager : Giving access to all the case steps under Status&Reminder/steps TAB case";

			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			//String action = "ClntAccess";

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

				caseListPage.clickOnCaseId(caseCreated, true);
				
				caseListPage.clickStatusRemindersStepsTab(true);

				CaseStepPage caseStepPage = new CaseStepPage(driver);
				
				caseStepPage.giveCorpAccessToCaseStep(true);
				
				caseStepPage.verifyCorpAcessGiven();
				
				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}

			catch (Exception e) {
				Log.exception(e, driver);
			}

			finally {
				Log.endTestCase();
				driver.quit();
			}
		}	
		
		
		@Test(groups = {"case"}, description = "Case: Status & Reminders/Steps select Add new reminder", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_1(String browser) throws Exception 
		{		
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case: Status & Reminders/Steps select Add new reminder";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickStatusRemindersStepsTab(true);

				CaseStepPage caseStepPage = new CaseStepPage(driver);
				
				caseStepPage.addReminderAndVerify(globalVariables.reminderCaseStepName); 
				
				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}

			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Case -  change status link the custom status of the case gets updated which would also gets reflected on details and dates page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_2(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			//String caseId = "KBHCZ02520-1";
			globalVariables.testCaseDescription = "Case -  change status link the custom status of the case gets updated which would also gets reflected on details and dates page";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			String changeStatus = "Waiting for Applicant info";
			
			try {

				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickStatusRemindersStepsTab(true);

				caseListPage.changeAndVerifyCaseStatus(changeStatus);
				
				caseListPage.clickDetailsDatesTab(true);
				
				CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);
				
				detailsAndDatesPage.verifyCurrentCaseStatus(changeStatus);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Case: Status & Reminders Resequence order", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_3(String browser) throws Exception
		{		
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case: Status & Reminders Resequence order";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try {

				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickStatusRemindersStepsTab(true);

				CaseStepPage caseStepPage = new CaseStepPage(driver);
				
				caseStepPage.checkResequence();

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}


		@Test(groups = {"case"}, description = "Case: Edit reminder", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_4(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();

			globalVariables.testCaseDescription = "Case: Edit reminder,Choose Case manager, Compose/Send email";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
//				editNewlyAddedStepStatus(addedStepName);
	//
//				Log.assertThat(editRemainderStepStatus, "Verified Completed status is listed successfully",
//						"Verified Completed status is not changed in listed table");

				caseManagerDashboardPage.clickLogout(true);

			    Log.testCaseResult();

			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		
		@Test(groups={"case", "FNP", "Priority2", "HRP"}, description = "Case - Case Steps (Add status for the existing case steps)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_47_7(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			//caseCreated = "KBHCZ02552-4"; 
			
			globalVariables.testCaseDescription = "Case Manager : Giving access to all the case steps under Status&Reminder/steps TAB case";
	
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
	
				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
	
				login.clickAgreeButton(false);
	
				caseManagerDashboardPage.clickCaseTab(true);
	
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	
				caseListPage.clickOnCaseId(caseCreated, true);
				
				caseListPage.clickStatusRemindersStepsTab(true);
	
				CaseStepPage caseStepPage = new CaseStepPage(driver);
				
				caseStepPage.addStepStatus(globalVariables.stepName_workInProgress, "Work In Progress");
				
				caseStepPage.addStepStatus(globalVariables.stepName_notApplicable, "Not Applicable");
				
				//caseStepPage.addStepStatus(globalVariables.stepName_completed, "Completed");
				
				caseManagerDashboardPage.clickLogout(true);
	
				Log.testCaseResult();
	
			}
	
			catch (Exception e) {
				Log.exception(e, driver);
			}
	
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"client"}, description = "Case History - Add Petition History to the client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_18_1(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			globalVariables.testCaseDescription = "Case Manager - Client: Case History - Add Petition History to the client";
			final WebDriver driver = WebDriverFactory.get(browser);
			
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickImmigrationStatusHistoryTab(true);
				
//				clientListPage.clickPetitionHistoryTab(true);

				CM_PetitionHistoryPage petitionHistoryHomePage = new CM_PetitionHistoryPage(driver);
				
				petitionHistoryHomePage.addImmigrationStatusHistory(globalVariables.petitionDocType, globalVariables.immigrationStatus, globalVariables.receiptDate, true);

//				petitionHistoryHomePage.addPetition(globalVariables.petitionDocType, globalVariables.immigrationStatus, globalVariables.receiptDate, true);
				
				petitionHistoryHomePage.verifyPetitionHistory(globalVariables.immigrationStatus, true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"client"}, description = "Case History - Edit Petition History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_18_2(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "Case Manager - Client: Case History - Edit Petition History";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickClientTab(true);

				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickImmigrationStatusHistoryTab(true);
				
//				clientListPage.clickPetitionHistoryTab(true);

				CM_PetitionHistoryPage petitionHistoryHomePage = new CM_PetitionHistoryPage(driver);

				petitionHistoryHomePage.editPetition(globalVariables.immigrationStatus,globalVariables.edittedImmigrationStatus, globalVariables.edittedPetitiomDocType, true);

				petitionHistoryHomePage.verifyPetitionHistory(globalVariables.edittedImmigrationStatus, true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Details/Dates - Edit Petition Related Info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_35_1(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String receiptValidFromDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptValidFromDate");
			String receiptValidTillDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptValidTillDate");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "Case Manager - Edit Petition Related Info details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickDetailsDatesTab(true);

				CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);

				detailsAndDatesPage.editCaseInfo(globalVariables.receiptType, globalVariables.editReceiptDate, globalVariables.receiptStatus, receiptValidFromDate, receiptValidTillDate, workbookNameWrite, caseId, true);

				detailsAndDatesPage.verifyEditedPetitionInfoDetails(receiptValidTillDate, true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
	
		
		@Test(groups = {"client"}, description = "Proposed Job Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_31_1(String browser) throws Exception {
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "Case Manager : Client job details proposed job details are pre-populated in the case level proposed job";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				
				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickJobDetailsTab(true);

				CM_JobDetailspage jobDetailsPage = new CM_JobDetailspage(driver);
				
				jobDetailsPage.editProposedJobDetailsCity(globalVariables.city, true);
				
				jobDetailsPage.verifyProposedJobDetailsCityInClient(globalVariables.city,true);

				clientListPage.clickCaseListTab(true);

				clientListPage.clickCaseId(caseId, true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickJobDetails(true);

				jobDetailsPage.verifyProposedJobDetailsCityInCase(globalVariables.city, true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Proposed Job Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_31_2(String browser) throws Exception {

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "Case Manager : Changes in the current case level proposed job details must not be reflected in client level proposed job details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				//caseManagerDashboardPage.clickClientTab(true);

				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

				//clientListPage.clickOnClientName(clientName, true);

				//clientListPage.clickCaseListTab(true);

				//clientListPage.clickCaseId(caseId, true);
				
				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickJobDetails(true);

				CM_JobDetailspage jobDetailsPage = new CM_JobDetailspage(driver);

				jobDetailsPage.editProposedJobDetailsInCase(globalVariables.edittedCityName,true);

				jobDetailsPage.verifyProposedJobDetailsCityInCase(globalVariables.edittedCityName, true);
				
				caseListPage.clickClientProfileTab(true);
				
				clientListPage.clickJobDetailsTab(true);

				jobDetailsPage.verifyIfNOTProposedJobDetailsCityInClient(globalVariables.edittedCityName, true);
				
				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();

			}
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			} 
		}
		
		
		@Test(groups = {"FNP", "Priority2", "case", "HRP"},description = "Receipt Numbers", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_36_1(String browser) throws Exception

		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String receiptSendDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptSendDate");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager: Adding new Receipt Number in Case Manager";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickReceiptNumberTab(true);

				CM_ReceiptNumbersPage receiptNumbersPage = new CM_ReceiptNumbersPage(driver);

				receiptNumbersPage.clickAddNewCaseReceiptNoBtn(globalVariables.receiptType, receiptSendDate, globalVariables.receiptDateTxt, globalVariables.receiptNumberTxt, "Pending", true);

				receiptNumbersPage.verifyReceiptNumber(globalVariables.receiptNumberTxt,true);

				caseManagerDashboardPage.clickLogout(true);
			
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		@Test(groups = {"FNP", "Priority2", "case", "HRP"},description = "Receipt Numbers", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_36_1")
		public void CM_TC_36_2(String browser) throws Exception
		{

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String validFromDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptValidFromDate");
			String validTillDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptValidTillDate");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager: Edit added Receipt Number in Case Manager";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickReceiptNumberTab(true);

				CM_ReceiptNumbersPage receiptNumbersPage = new CM_ReceiptNumbersPage(driver);

				receiptNumbersPage.editMainReceipt(globalVariables.dateSentToGovtAgent, globalVariables.receiptNumberTxt, globalVariables.edittedReceiptNumber, globalVariables.editReceiptDateTxt,globalVariables.receiptNoticeDate, validFromDate, validTillDate, caseId, globalVariables.genericStartDate);

				receiptNumbersPage.verifyReceiptNumber(globalVariables.edittedReceiptNumber,true);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case", "HRP"}, description = "Case - Profile", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_36_2")
		public void CM_TC_34(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "Case Manager - Case: Verify Case profile Details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickProfileTab(true);

				CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);

				caseProfilePage.verifyCaseprofileDetails(globalVariables.editReceiptDateInCaseProfile, globalVariables.edittedReceiptNumber, true);
				
				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Managers/Contacts", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_39(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String signPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager:  Managers/Contacts in Case Manager";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickManagersContactsTab(true);

				CM_ManagerContactsPage managerContactsPage = new CM_ManagerContactsPage(driver);
						
				managerContactsPage.caseManagerSignForms(signPerson, true);

				managerContactsPage.firmStaffReceiveRemainders(signPerson, true);
		
				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Blanket Applicants", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_32_1(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager - Case: Adding Blanket Applicants";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickBlanketApplicationTab(true);

				CM_BlanketApplicantsPage blanketApplicantsPage = new CM_BlanketApplicantsPage(driver);
						
				blanketApplicantsPage.addNewBlanketApplicants(clientName, true);

				blanketApplicantsPage.verifyAddedBlanketApplicant(clientName, true);

				caseManagerDashboardPage.clickLogout(true);
				
				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Blanket Applicants", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_32_1")
		public void CM_TC_32_2(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager - Case: Deleting Blanket Applicants";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickBlanketApplicationTab(true);

				CM_BlanketApplicantsPage blanketApplicantsPage = new CM_BlanketApplicantsPage(driver);

				blanketApplicantsPage.deleteBlanketApplicantAndVerify(clientName);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Snapshot", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_33(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			globalVariables.testCaseDescription = "Case Manager - Snapshot";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);

				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseId, true);
				
				caseListPage.clickSnapShotTab(true);

				caseListPage.verifySnapShotDetails(clientName);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"case"}, description = "Details/Dates - Edit AOS Date Info details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_35_4(String browser) throws Exception
		{

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "Case Manager - Edit AOS Date Info details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerDashboardPage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

				caseListPage.clickOnCaseId(caseCreated, true);
				
				caseListPage.clickDetailsDatesTab(true);

				CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);

				detailsAndDatesPage.editAOSDateDetailsInfo(globalVariables.cityInDatesDetails, true);

				detailsAndDatesPage.verifyAOSDateDetailsInfo(globalVariables.cityInDatesDetails);

				caseManagerDashboardPage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority1", "documents"}, description = "FNP: Check for the Documents uploaded in Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.DocumentManagementTestScript.Client_Doc_21"})
		public void CM_TC_84_2(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

			globalVariables.testCaseDescription = "FNP: Check for the Documents uploaded in client";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {

				LoginPageTest login = new LoginPageTest(driver, webSite);

				login.loginToFNP(fnpLogin, fnpNewPassword, true);

				login.clickAgreeButton(false);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);
				
				Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
				
				fnpHomePage.clickDocumentsTab();
				
				FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
				
				fnpDocumentsPage.verifyIfDocumentPresent(clientFile1NameWithoutExtension);
				
				Log.testCaseResult();

			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"email", "client"}, description = "Client : Send Email after attaching Questionnaire, uploading Document and including e-consent", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_20_1", "CM_TC_21_1"})
		public void CM_TC_23_4(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

			globalVariables.testCaseDescription = "Client : Send Email after attaching Questionnaire, uploading Document and including e-consent";

			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(false);

				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

				clientListPage.clickOnClientName(clientName, false);;
				
				clientListPage.clickEmailsTab();
				
				CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
				
				clientEmailsPage.clickComposeEmailButton();
				
				clientEmailsPage.changeEmailSubject(globalVariables.clientEmailSubject);
				
				clientEmailsPage.clickIncludeQuestionnaires();
				
				clientEmailsPage.attachDocument(globalVariables.fileNameWithoutExtension);
				
				clientEmailsPage.selectEConsent(data.eConsent);
				
				clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
				
				clientEmailsPage.verifyIfEmailSent(globalVariables.clientEmailSubject);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.exception(e, driver);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"email", "client"},description = "Check client email details in Communication Summary", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_23_4")
	  	public void CM_TC_28_4(String browser) throws Exception 
	  	{
	        ReadWriteExcel readExcel = new ReadWriteExcel();
	        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
	        
	        globalVariables.testCaseDescription = "Check client email details in Communication Summary";
	        final WebDriver driver = WebDriverFactory.get(browser);
	        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	        try 
	        {
	            LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

	            clientListPage.clickOnClientName(clientName, true);
	            
	            caseManagerDashboardPage.clickCommunicationSummaryInClient(true);

	            CommunicationSummaryPage communicationSummaryclient = new CommunicationSummaryPage(driver);
	            
	            communicationSummaryclient.verifyDataPresent(globalVariables.clientEmailSubject, true);

	            caseManagerDashboardPage.clickLogout(true);

	            Log.testCaseResult();

	        }
	        catch (Exception e) {
	            Log.exception(e, driver);
	        } 
	        finally {
	            Log.endTestCase();
	            driver.quit();
	        }
	    }
		
		

	 	@Test(groups = {"email", "case"}, description = "Check case email details in Communication Summary", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_52_4")
	  	public void CM_TC_53_4(String browser) throws Exception 
	  	{
	        ReadWriteExcel readExcel = new ReadWriteExcel();
	        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
	        
	        globalVariables.testCaseDescription = "Check case email details in Communication Summary";
	        final WebDriver driver = WebDriverFactory.get(browser);
	        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	        try 
	        {
	            LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

	            clientListPage.clickOnClientName(clientName, true);
	            
	            caseManagerDashboardPage.clickCommunicationSummaryInClient(true);

	            CommunicationSummaryPage communicationSummaryclient = new CommunicationSummaryPage(driver);
	            
	            communicationSummaryclient.verifyDataPresent(globalVariables.caseEmailSubject, true);

	            caseManagerDashboardPage.clickLogout(true);

	            Log.testCaseResult();

	        }
	        catch (Exception e) {
	            Log.exception(e, driver);
	        } 
	        finally {
	            Log.endTestCase();
	            driver.quit();
	        }
	    }
	 	
	 	
	 	@Test(groups = {"email", "client"}, description = "Client: Advanced Email- Check Select Template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_23_2(String browser) throws Exception 
		{

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String QA_A_Client_Email_Template_Title = "Change Request for Business Number";
			String QA_A_Client_changeRequestSubject = "Business Number";
			String QA_A_Client_changeRequestDescriptionDetails_1 = "Current Business Number";
			String QA_A_Client_changeRequestDescriptionDetails_2 = "Requested Business Number";
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

			globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				
				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickEmailsTab();
				
				CM_EmailsPage emailsPage = new CM_EmailsPage(driver);
				
				emailsPage.chooseMessageTemplateAndSetAdvanceOptions(workbookNameWrite, sheetName, QA_A_Client_changeRequestDescriptionDetails_2, QA_A_Client_changeRequestDescriptionDetails_1, QA_A_Client_changeRequestSubject, QA_A_Client_Email_Template_Title, globalVariables.questionnaireName, driver, true);
			
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
		
			}
			catch (Exception e) {
				Log.exception(e, driver);
			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}	
	 	
	 	
//*********************************************************************************************


		@Test(groups={"FNP", "Priority2", "case", "HRP"}, description="CaseManager-Case: Add LCA details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_115(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "CaseManager-Case: Add LCA details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        caseManagerDashboardPage.clickCaseTab(true);
		        
		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
		
		        caseListPage.clickOnCaseId(caseCreated, true);
		        
		        caseListPage.clickDetailsDatesTab(true);
		
		        caseListPage.addLCADetails(globalVariables.LCANumber, globalVariables.validFrom, globalVariables.validThru, globalVariables.totalCount, globalVariables.jobTitle, globalVariables.address, globalVariables.LCAcity, globalVariables.country, globalVariables.state, globalVariables.zip, globalVariables.wages, globalVariables.agent, globalVariables.trackNumber, globalVariables.publishedYear, globalVariables.LCAeffectiveOn, globalVariables.LCAeffectiveTill);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "case"}, description="CaseManager-Case: Add Visa Priority Date Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_116(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "CaseManager-Case: Add Visa Priority Date Info";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        caseManagerDashboardPage.clickCaseTab(true);
		        
		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
		
		        caseListPage.clickOnCaseId(caseCreated, true);
		        
		        caseListPage.clickDetailsDatesTab(true);
		
		        caseListPage.addVisaPriorityDateInfo(globalVariables.receiptNoticeDate, globalVariables.countryNew, globalVariables.visaPriority);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "case", "HRP"}, description="CaseManager-Case: Add SWA and DoL Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_117(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			globalVariables.testCaseDescription = "CaseManager-Case: Add Visa Priority Date Info";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        caseManagerDashboardPage.clickCaseTab(true);
		        
		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
		
		        caseListPage.clickOnCaseId(caseCreated, true);
		        
		        caseListPage.clickDetailsDatesTab(true);
		        
		        CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);
		
		        detailsAndDatesPage.addSwaAndDolInfo(globalVariables.swaCaseNumber, globalVariables.swaFiledDate, globalVariables.swaPriorityDate, globalVariables.dolRecievedDate, globalVariables.dolCaseNumber, globalVariables.noticeSentDate, globalVariables.noticeReceivedDate, globalVariables.backlogCaseNumber);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		
		@Test(groups={"FNP", "Priority2", "client"}, description="CaseManager-Client: Add Shipments and Mails Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_118(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "CaseManager-Client: Add Shipments and Mails Details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);
		
				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickShippingOrMailingLogTab(); 
				
				//Used these parameters to reuse global variables
				clientListPage.addShippingDetails(caseId, globalVariables.receiptNoticeDate, globalVariables.shippmentMethod, globalVariables.backlogCaseNumber);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "case", "HRP"}, description = "CaseManager-Add case to relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "CM_TC_4")
		public void CM_TC_119(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
			String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeName");
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			clientName = clientName + " (Other)";
			globalVariables.testCaseDescription = "CaseManager-Add case to relative";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				
				clientListPage.clickOnClientName(relativeFirstName, true);
				
				clientListPage.addCaseForRelative(workbookNameWrite, sheetName, relativeName, clientName, data.CaseManagerToWork, data.PetitionType_AddCase, true);
		
				clientListPage.verifyIfCaseCreated(data.AddCase_CountryName,relativeFirstName, clientName, workbookNameWrite, sheetName, true);
				
				clientListPage.editAccessRightForCase(corpName);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "case"}, description = "To get case details and update it on excel", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_119")
		public void CM_TC_119_0(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForRelative");
			globalVariables.testCaseDescription = "To get case details and update it on excel";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		    try 
		    {
		    	LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        caseManagerDashboardPage.clickCaseTab(true);
		        
		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
		
		        caseListPage.clickOnCaseId(caseCreated, true);
		
		        caseListPage.clickDetailsDatesTab(true);
		        
		        CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);
		        
		        detailsAndDatesPage.fetchAndUpdateCaseStartDateForRelative(workbookNameWrite, sheetName);
		        
		        detailsAndDatesPage.fetchAndUpdateCaseStatusForRelative(workbookNameWrite, sheetName);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
		
		    }
		    catch (Exception e) {
		    	Log.exception(e, driver);
		    }
		    finally {
		    	Log.endTestCase();
		        driver.quit();
		    }
		}
		
		
		@Test(groups={"FNP", "Priority2", "client", "case"}, description="CaseManager- Add shipments and mails in Shipments & Mails page for relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_119")
		public void CM_TC_120(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForRelative");
			String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		//	String relativeFirstName = "Testing";
		//	String relativeName = "Testing Relative";
			globalVariables.testCaseDescription = "CaseManager- Add shipments and mails in Shipments & Mails page for relative";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);
		
				clientListPage.clickOnClientName(relativeFirstName, true);
				
				clientListPage.clickShippingOrMailingLogTab(); 
				
				//Used these parameters to reuse global variables
				clientListPage.addShippingDetails(caseId, globalVariables.receiptNoticeDate, globalVariables.shippmentMethod, globalVariables.backlogCaseNumber);
		
		        caseManagerDashboardPage.clickLogout(true);
		        
		        Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "Add valid passport for relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//,dependsOnMethods = "CM_TC_4")
		public void CM_TC_121(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
			//String relativeFirstName = "Testing";
			//String relativeName = "Testing Relative";
		
			globalVariables.testCaseDescription = "Add valid passport for relative";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
		
				clientListPage.clickOnClientName(relativeFirstName, true);
				
				clientListPage.clickPassportInfoTab();
		
				CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);
		
				passportInfoPage.addAdditionalPassportDetails(globalVariables.passportNumber, globalVariables.firstName, globalVariables.lastName, globalVariables.nationality, globalVariables.QA_A_PassportExpiresOnDateAddnew, globalVariables.issuingCountry, globalVariables.receiptNoticeDate);
				
				passportInfoPage.verifyPassportDetailsAdded(globalVariables.passportNumber, globalVariables.firstName, globalVariables.lastName, globalVariables.nationality, true);
		
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "client"}, description = "Add notes in Notes Page for relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//,dependsOnMethods = "CM_TC_4")
		public void CM_TC_122(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
		    String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
			globalVariables.testCaseDescription = "Add notes in Notes Page for relative";
		    final WebDriver driver = WebDriverFactory.get(browser);
		    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		  
		    try 
		    {
		    	LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashBoardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        CM_ClientListPage clientListPage = caseManagerDashBoardPage.clickClientTab(true);
		
		        clientListPage.clickOnClientName(relativeName, true);
		            
		        clientListPage.clickNotesTabInClient(true);
		            
		        CM_NotesPage notesPage = new CM_NotesPage(driver);
		            
		        notesPage.addNotesInClient(globalVariables.notesDetailsTextRelative,true);
		
		        notesPage.verifyNotesInClient(globalVariables.notesDetailsTextRelative, true);
		
		        caseManagerDashBoardPage.clickLogout(true);
		
		        Log.testCaseResult();
		
		    }
		    catch (Exception e) {
		    	Log.exception(e, driver);
		    }
		    finally {
		    	Log.endTestCase();
		    	driver.quit();
		    }
		}
		
		
			//for expired passport
		@Test(groups={"FNP", "Priority2","client"}, description = "Add expired Passport Info details for relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "CM_TC_4")
		public void CM_TC_123(String browser) throws Exception {
		
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
			
		
			globalVariables.testCaseDescription = "Add expired Passport Info details for relative";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
		
				clientCreationPage.clickOnClientName(relativeName, true);
		
				CM_PassportInfoPage passportInfoPage = clientCreationPage.selectPassportInfo(true);
		
				passportInfoPage.addAdditionalPassportDetails(globalVariables.QA_A_SecondPassPortNumber, globalVariables.secondPassportFirstName, globalVariables.secondPassportLastName, globalVariables.nationality, globalVariables.QA_A_PassportExpiresOnDate, globalVariables.issuingCountry, globalVariables.QA_A_ExpiredPassportIssueDate);
		
				passportInfoPage.verifyPassportDetailsAdded(globalVariables.QA_A_SecondPassPortNumber, globalVariables.secondPassportFirstName, globalVariables.secondPassportLastName, globalVariables.nationality, true);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.fail("Error - " + e.getMessage(), driver, true);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "client"}, description = "CLIENT : Add contact details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_73_4_0(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "CLIENT : Add contact details";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
		
				clientCreationPage.clickOnClientName(clientName, true);
		
				clientCreationPage.clickProfileTab();
				
				CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
				
				clientProfilePage.editContactDetails(globalVariables.phoneNumber1, globalVariables.phoneNumber2, globalVariables.phoneNumber3, globalVariables.linkedIn, globalVariables.skype, globalVariables.facebook, globalVariables.twitter);
				
				clientProfilePage.verifyContactDetails(globalVariables.phoneNumber3, globalVariables.linkedIn, globalVariables.skype, globalVariables.facebook, globalVariables.twitter);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.fail("Error - " + e.getMessage(), driver, true);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2","client"}, description = "CLIENT : Add Personal Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_73_5_0(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "CLIENT : Add Personal Info";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
		
				clientCreationPage.clickOnClientName(clientName, true);
		
				clientCreationPage.clickProfileTab();
				
				CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
				
				clientProfilePage.editPersonalDetails(globalVariables.country, globalVariables.wifeFirstName, globalVariables.maritalStatus);
				
				clientProfilePage.verifyPersonalDetails(globalVariables.wifeFirstName, globalVariables.maritalStatus);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.fail("Error - " + e.getMessage(), driver, true);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2","client"}, description = "CLIENT : Add Residence Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_73_6_0(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "CLIENT : Add Residence Info";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
		
				clientCreationPage.clickOnClientName(clientName, true);
		
				clientCreationPage.clickProfileTab();
				
				CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
				
				clientCreationPage.clickProfileTab();
				
				clientProfilePage.editResidenceAddress(globalVariables.countryNew, globalVariables.cityNew, globalVariables.streetNumber, globalVariables.state, globalVariables.numberForPersonalInfo);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} 
			catch (Exception e) {
				Log.fail("Error - " + e.getMessage(), driver, true);
			} 
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2","client"}, description = "Add new Appointments/Activities for relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "CM_TC_4")
		public void CM_TC_124(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		   
		    globalVariables.testCaseDescription = "Case Manager : Adding new Appointment/Activity  for relative";
		    final WebDriver driver = WebDriverFactory.get(browser);           
		    driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		    
		    try 
		    {
		    	LoginPageTest login = new LoginPageTest(driver, webSite);
		
		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
		        login.clickAgreeButton(false);
		
		        caseManagerDashboardPage.clickClientTab(true);
		        
		        CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
		
		        clientListPage.clickOnClientName(relativeName, true);
		        
		        clientListPage.clickAppointmentsActivitiesTab(true);
		
		        CM_ClientAppointmentsAndActivitiesPage appointmentsAndActivitiesPage = new CM_ClientAppointmentsAndActivitiesPage(driver);
		
		        appointmentsAndActivitiesPage.addNewAppointmentsActivities(globalVariables.appointmentActivityDescriptionClient, globalVariables.appointmentActivityLocationClient, globalVariables.appointmentActivitySubjectClient, globalVariables.appointmentActivityCategoryClient, caseManagerDashboardPage.fetchUserId(), globalVariables.appointmentActivityComments);
		
		        appointmentsAndActivitiesPage.verifyAppointmentActivity(globalVariables.appointmentActivityDescriptionClient, true);
		
		        caseManagerDashboardPage.clickLogout(true);
		
		        Log.testCaseResult();
		
		    }
		    catch (Exception e) {
		        Log.exception(e, driver);
			} 
		    finally {
		        Log.endTestCase();
		        driver.quit();
		    } 
		}
		
		
		@Test(groups={"FNP", "Priority2", "case", "HRP"}, description = "Creation of Case (Open)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_60_4(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		
			globalVariables.testCaseDescription = "Case Manager: Creation of Case in Case Manager";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
				login.clickAgreeButton(false);
		
				CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
		
		        caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   
		
				caseListPage.verifyIfCaseCreated("QA_A_CaseOpen", data.AddCase_CountryName ,savedClientName, corpName, workbookNameWrite, sheetName, true);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "case", "HRP"}, description = "Creation of Case (Approved)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_60_5(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
			
			globalVariables.testCaseDescription = "Case Manager: Creation of Case (Approved)";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
				login.clickAgreeButton(false);
		
				CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
		
		        caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   
		
				caseListPage.verifyIfCaseCreated("QA_A_CaseApproved", data.AddCase_CountryName ,savedClientName, corpName, workbookNameWrite, sheetName, true);
				
				String caseId = readExcel.initTest(workbookName, sheetName, "QA_A_CaseApproved");
				
				caseListPage.changeCaseStatusToApproved(caseId, "Approved", globalVariables.genericStartDate, globalVariables.genericEndDate);
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		
		@Test(groups={"FNP", "Priority2", "case", "HRP"},description = "Creation of Case (Closed)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_60_6(String browser) throws Exception
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
			
			globalVariables.testCaseDescription = "Case Manager: Creation of Case (Closed)";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
				
				login.clickAgreeButton(false);
		
				CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
		
		        caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   
		
				caseListPage.verifyIfCaseCreated("QA_A_CaseClose", data.AddCase_CountryName ,savedClientName, corpName, workbookNameWrite, sheetName, true);
				
				String caseId = readExcel.initTest(workbookName, sheetName, "QA_A_CaseClose");
				
				caseListPage.changeCaseStatusToClosed(caseId, "Closed");
				
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
//		@Test(description="prerequisite test for Adding Firm Defined Document Expiration ",dataProviderClass=DataProviderUtils.class,dataProvider = "parallelTestDataProvider")
//	    public void CM_TC_9_0(String browser) throws Exception
//	    {
//	        globalVariables.testCaseDescription = "Case Manager-Client: Document Expirations tab to add firm document";
//	        final WebDriver driver = WebDriverFactory.get(browser);
//	         
//	        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//	          
//	        try
//	        {
//	        	LoginPageTest login = new LoginPageTest(driver, webSite);
//	              
//	            CM_DashboardPage caseManagerDashBoardPage = login.caseManagerlogin(userName, password, true);
//
//	            login.clickAgreeButton(false);
//	            
//	            caseManagerDashBoardPage.clickKnowledgeBase(true);
//	                
//	            CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
//	                
//	            knowledgeBasePage.clickExpirationDatesTemplateTab();
//	            
//	            knowledgeBasePage.addNewExpirationDateTemplate(driver, workbookNameWrite, sheetName); 
//	            
//	            caseManagerDashBoardPage.clickLogout(true);
//	               
//	        }catch(Exception e){
//	        	Log.exception(e, driver);
//	        }
//	        finally
//	        {
//	        	Log.endTestCase();
//	            driver.quit();
//	        }
//	    }
//
//		
//		@Test(description = "Document Expirations Add", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_9_0")
//	    public void CM_TC_9_1(String browser) throws Exception {
//
//	          ReadWriteExcel readExcel = new ReadWriteExcel();
//	          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//	          String validFromDate = readExcel.initTest(workbookName, sheetName, "QA_A_DateOfBirth");
//	          //String documentExpirationTemplateName = "ExpirationDateTemplate(No Tag in FNP)";
//	          String documentExpirationTemplateName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DocumentExpirationsName");
//	         
//	          globalVariables.testCaseDescription = "Case Manager-Client: Document Expirations tab to add firm document";
//	          final WebDriver driver = WebDriverFactory.get(browser);
//	          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//	          try {
//
//	                LoginPageTest login = new LoginPageTest(driver, webSite);
//
//	                CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//	                login.clickAgreeButton(false);
//	                
//	                caseManagerDashboardPage.clickClientTab(true);
//
//	                CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//
//	                clientListPage.clickOnClientName(clientName, true);
//	                
//	                clientListPage.clickDocumentExpirationTab(true);
//
//	                CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
//
//	                documentExpirationsPage.addDocument(documentExpirationTemplateName,validFromDate,globalVariables.documentDescription, workbookNameWrite, sheetName, true);
//
//	                documentExpirationsPage.verifyDocumentAdded(documentExpirationTemplateName, true);
//
//	                caseManagerDashboardPage.clickLogout(true);
//
//	                Log.testCaseResult();
//	          } 
//	          catch (Exception e) {
//	                Log.exception(e, driver);
//	          } 
//	          finally {
//	                Log.endTestCase();
//	                driver.quit();
//	          } 
//	    }
//		
//		
//		@Test(description = "Edit Document Expirations ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods="CM_TC_9_1")
//	    public void CM_TC_9_2(String browser) throws Exception {
//
//	          ReadWriteExcel readExcel = new ReadWriteExcel();
//	          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//	          String client_DocumentExpirationsDate = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DocumentExpirationsDate");
//	          String documentExpirationTemplateName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DocumentExpirationsName");
//	          //String documentExpirationTemplateName = "ExpirationDateTemplate(No Tag in FNP)";
//	          globalVariables.testCaseDescription = "Case Manager-Client: Document Expirations tab to edit firm document";
//	          final WebDriver driver = WebDriverFactory.get(browser);
//	          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//	          try {
//	                LoginPageTest login = new LoginPageTest(driver, webSite);
//
//	                CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//	                login.clickAgreeButton(false);
//	                
//	                caseManagerDashboardPage.clickClientTab(true);
//
//	                CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//
//	                clientListPage.clickOnClientName(clientName, true);
//	                
//	                clientListPage.clickDocumentExpirationTab(true);
//
//	                CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
//
//	                documentExpirationsPage.editDocumentExpirationTemplate(workbookNameWrite, sheetName, client_DocumentExpirationsDate,documentExpirationTemplateName, true);
//
//	                String client_EditDocumentExpirationsDate = readExcel.initTest(workbookName, sheetName,
//	                            "QA_A_Client_EditDocumentExpirationsDate");
//
//	                documentExpirationsPage.verifyEditDocument(client_EditDocumentExpirationsDate, true);
//
//	                caseManagerDashboardPage.clickLogout(true);
//
//	                Log.testCaseResult();
//
//	          }
//	          catch (Exception e) {
//	                Log.exception(e, driver);
//	          } 
//	          finally {
//	                Log.endTestCase();
//	                driver.quit();
//	          }
//	    }
//		
//		
//		@Test(description = "FNP - Verify if the document expiration date is more than 6 months there is no tag seen beside the document.", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"CM_TC_67_4", "CM_TC_30_1", "CM_TC_9_1"})
//		public void CM_TC_68_1(String browser) throws Exception {
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//			String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//			String documentExpirationTemplateName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DocumentExpirationsName");
//			//String documentExpirationTemplateName = "ExpirationDateTemplate(No Tag in FNP)";
//			globalVariables.testCaseDescription = "FNP - Verify if the document expiration date is more than 6 months there is no tag seen beside the document.";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//				
//				login.fnplogin(fnpLogin, fnpNewPassword, true);
//
//				FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
//
//				login.clickAgreeButton(false);
//
//				//login.clickAcceptAndContinueBtn(true);
//
//				fnpHomePagelogin.verifyExpirationAfter6Months(documentExpirationTemplateName,true);
//
//				fnpHomePagelogin.clickLogout(true);
//
//				Log.testCaseResult();
//			}
//
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			} finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}
//		
//		
//		@Test(description = "FNP - Verify the documents are getting expired less than 6 months an orange tag shows up along with the country flag", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"CM_TC_67_4", "CM_TC_30_1"})
//		public void CM_TC_68_2(String browser) throws Exception {
//
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//			String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//			String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//			String client_DocumentExpirationsDate = readExcel.initTest(workbookName, sheetName,
//					"QA_A_Client_DocumentExpirationsDate");
//			
//			  
//			globalVariables.testCaseDescription = "FNP - Verify the documents are getting expired less than 6 months an orange tag shows up along with the country flag";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//				
//				caseManagerDashboardPage.clickClientTab(true);
//
//				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//
//				clientListPage.clickOnClientName(clientName, true);
//				
//				clientListPage.clickDocumentExpirationTab(true);
//
//				CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
//
//				documentExpirationsPage.editDocumentExpirationLessThan6Months(globalVariables.documentExpirationTemplateLessThan6Months,workbookNameWrite, sheetName, client_DocumentExpirationsDate, true);
//				
//				caseManagerDashboardPage.clickLogout(true);
//
//				LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
//						
//				fnplogin.fnplogin(fnpLogin, fnpNewPassword, true);
//				
//				FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
//
//				fnplogin.clickAgreeButton(false);
//
//				//login.clickAcceptAndContinueBtn(true);
//
//				fnpHomePagelogin.verifyDocumentExpirationInOrLessThan6Months(globalVariables.documentExpirationTemplateLessThan6Months,true);
//
//				fnpHomePagelogin.clickLogout(true);
//
//				Log.testCaseResult();
//			}
//
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			} finally {
//				Log.endTestCase();
//				driver.quit();
//
//			}
//		}
//		
//		
//		@Test(description = "FNP - Verify the document expiration date is more than 6 months there is no tag seen beside the document.", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"CM_TC_67_4", "CM_TC_30_1"})
//		public void CM_TC_68_3(String browser) throws Exception {
//
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//			String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//			String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//			
//			String client_DocumentExpirationsDate = readExcel.initTest(workbookName, sheetName,
//					"QA_A_Client_DocumentExpirationsDate");
//
//			globalVariables.testCaseDescription = "FNP - Verify the document expiration date is more than 6 months there is no tag seen beside the document.";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//				
//				caseManagerDashboardPage.clickClientTab(true);
//
//				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//
//				clientListPage.clickOnClientName(clientName, true);
//				
//				clientListPage.clickDocumentExpirationTab(true);
//
//				CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
//
//				documentExpirationsPage.editDocumentExpirationMoreThan6Months(globalVariables.documentExpirationTemplateMoreThan6Months, workbookNameWrite, sheetName,
//						client_DocumentExpirationsDate, true);
//
//				caseManagerDashboardPage.clickLogout(true);
//
//				LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
//				
//				fnplogin.fnplogin(fnpLogin, fnpNewPassword, true);
//
//				FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
//
//				fnplogin.clickAgreeButton(false);
//
//				//login.clickAcceptAndContinueBtn(true);
//
//				fnpHomePagelogin.verifyExpirationAfter6Months(globalVariables.documentExpirationTemplateMoreThan6Months, true);
//
//				fnpHomePagelogin.clickLogout(true);
//
//				Log.testCaseResult();
//			}
//
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			} finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}
//		
//		
//		@Test(description = "FNP - Verify the document is Expiring in 6 months", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"CM_TC_67_4", "CM_TC_30_1"})
//		public void CM_TC_68_4(String browser) throws Exception {
//
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//			String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//			String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//			//String client_DocumentExpirationsDate = readExcel.initTest(workbookName, sheetName,"QA_A_Client_DocumentExpirationsDate");
//			
//			 
//			globalVariables.testCaseDescription = "FNP - Verify the document is Expiring in 6 months";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//				
//				caseManagerDashboardPage.clickClientTab(true);
//
//				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//
//				clientListPage.clickOnClientName(clientName, true);
//				
//				clientListPage.clickDocumentExpirationTab(true);
//
//				CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
//
//				documentExpirationsPage.editDocumentExpirationInThan6Months(globalVariables.documentExpirationTemplateIn6Months, workbookNameWrite ,sheetName, true);
//				
//				caseManagerDashboardPage.clickLogout(true);
//
//				LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
//				
//				fnplogin.fnplogin(fnpLogin, fnpNewPassword, true);
//
//				FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
//
//				fnplogin.clickAgreeButton(false);
//
//				//login.clickAcceptAndContinueBtn(true);
//
//				fnpHomePagelogin.verifyDocumentExpirationInOrLessThan6Months(globalVariables.documentExpirationTemplateIn6Months,true);
//
//				fnpHomePagelogin.clickLogout(true);
//
//				Log.testCaseResult();
//			}
//
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			} finally {
//				Log.endTestCase();
//				driver.quit();
//
//			}
//		}
//		
//		
		@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "Case Manager - Client : Set dates for document expiration templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"CM_TC_67_4", "CM_TC_30_1"})
		public void CM_TC_68_0(String browser) throws Exception {
		
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			
			globalVariables.testCaseDescription = "Case Manager - Client : Set dates for document expiration templates";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerDashboardPage.clickClientTab(true);
		
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
		
				clientListPage.clickOnClientName(clientName, true);
				
				SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 60); // Adding 60 days
				String expirationLess6Months = sdfSecond.format(calendar.getTime());
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, 200); // Adding 200 days
				String expirationInMore6Months = sdfSecond.format(calendar.getTime());
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -60); // Subtracting 60 days
				String expired = sdfSecond.format(calendar.getTime());
				
				clientListPage.clickDocumentExpirationTab(true);
		
				CM_DocumentExpirationsPage documentExpirationsPage = new CM_DocumentExpirationsPage(driver);
		
				documentExpirationsPage.editDocumentExpirationTemplate(globalVariables.documentExpirationTemplateExpired, expired);
			
				documentExpirationsPage.editDocumentExpirationTemplate(globalVariables.documentExpirationTemplateLessThan6Months, expirationLess6Months);
				
				documentExpirationsPage.editDocumentExpirationTemplate(globalVariables.documentExpirationTemplateMoreThan6Months, expirationInMore6Months);
				
				caseManagerDashboardPage.clickLogout(true);
		
				Log.testCaseResult();
			}
		
			catch (Exception e) {
				Log.exception(e, driver);
		
			} finally {
				Log.endTestCase();
				driver.quit();
		
			}
		}
//		
//		
//		@Test(description = "Service Centre Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//		public void CM_TC_37(String browser) throws Exception
//
//		{
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//			
//			globalVariables.testCaseDescription = "Case Manager: Service Centre Info - Edit list of Forms ";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//
//				caseManagerDashboardPage.clickCaseTab(true);
//				
//				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//				
//				caseListPage.clickOnCaseId(caseId, true);
//				
//				caseListPage.clickServiceCenterInfoTab(true);
//
//				CM_ServiceCentreInfoPage serviceCentreInfoPage = new CM_ServiceCentreInfoPage(driver);
//
//				serviceCentreInfoPage.editServiceCenterInfo(globalVariables.formName, globalVariables.serviceCenter, globalVariables.basisForBilling, true);
//
//				//serviceCentreInfoPage.verifyEditedFormDetails(true);
//
//				caseManagerDashboardPage.clickLogout(true);
//
//				Log.testCaseResult();
//			} 
//			catch (Exception e) {
//				Log.exception(e, driver);
//			} 
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}
//		
//		
//		@Test(description = "District Office Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//		public void CM_TC_38(String browser) throws Exception
//		{
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//
//			globalVariables.testCaseDescription = "Case Manager: District Office Info - Edit list of Forms ";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//
//				caseManagerDashboardPage.clickCaseTab(true);
//				
//				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//				
//				caseListPage.clickOnCaseId(caseId, true);
//				
//				caseListPage.clickDistrictOfficeInfoTab();
//
//				CM_DistrictOfficeInfoPage districtOfficeInfoPage = new CM_DistrictOfficeInfoPage(driver);
//
//				//districtOfficeInfoPage.editDistrictOfficeInfo(globalVariables.formName, globalVariables.districtOffice);
//
//				districtOfficeInfoPage.verifyEdittedDistrictOfficeInfo(globalVariables.formName, globalVariables.districtOffice);
//
//				caseManagerDashboardPage.clickLogout(true);
//
//				Log.testCaseResult();
//			} 
//			catch (Exception e) {
//				Log.exception(e, driver);
//			} 
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}
//		
//		
//		@Test(description = "Client: Advanced Email-  Check Recipient's Email details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//		public void tcMUTC23_1(String browser) throws Exception 
//		{
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//
//			globalVariables.testCaseDescription = "Client: Advanced Email-  Check Recipient's Email details";
//			final WebDriver driver = WebDriverFactory.get(browser);
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//			try {
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//				
//				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
//				
//				login.caseManagerlogin(userName, password, true);
//				
//				login.clickAgreeButton(false);
//
//				caseManagerHomePage.clickClientTab(true);
//				
//				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
//				
//				clientListPage.clickOnClientName(clientName, true);
//				
//				clientListPage.clickEmailsTab();
//				
//				CM_EmailsPage emailsPage = new CM_EmailsPage(driver);
//				
//				emailsPage.verifyRecipientEmailForClient();
//
//				caseManagerHomePage.clickLogout(true);
//
//				Log.testCaseResult();
//			}
//
//			catch (Exception e) {
//				Log.exception(e, driver);
//			}
//
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}
		
		
		
		@Test(groups = {"FNP", "Priority2", "client"}, description = "Add Main Passport Info Details to the client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_10_3(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
			globalVariables.testCaseDescription = "Case Manager - Client: Add New Passport Info Details to the client";
		
			final WebDriver driver = WebDriverFactory.get(browser);
			
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
		
				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
				login.clickAgreeButton(false);
				
				caseManagerHomePage.clickClientTab(true);
		
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
		
				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickPassportInfoTab();
		
				CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);
		
				passportInfoPage.editMainPassportDetails(globalVariables.passportNumber, globalVariables.firstName, globalVariables.lastName, globalVariables.nationality, globalVariables.genericEndDate, globalVariables.issuingCountry, globalVariables.genericStartDate);
		
				caseManagerHomePage.clickLogout(true);
		
				Log.testCaseResult();
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
	@Test(groups={"FNP", "Priority1", "case"}, description = "CASE: Add Docs Checklist, upload a document to the created docs checklist and give Access to all", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_75_0(String browser) throws Exception 
	{
		      
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "CASE: Add Docs Checklist, upload a document to the created docs checklist and give Access to all";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
		
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
		
			login.clickAgreeButton(false);
		            
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
		            
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
		            
			caseListPage.clickOnCaseId(caseCreated, true);
		            
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
		            
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		            
			caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistNameForFNP);
		            
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameForFNP);
		            
			caseDocChecklistPage.uploadDocument(globalVariables.docChecklistNameForFNP, globalVariables.filePath, clientName);
		            
			caseDocChecklistPage.verifyIfDocumentUploaded(globalVariables.docChecklistNameForFNP, globalVariables.fileName);
		            
			caseDocChecklistPage.changeDocumentAccessRights(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Corporation");
		    
			caseDocChecklistPage.changeDocumentAccessRights(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Client");
			
			caseDocChecklistPage.changeDocumentAccessRights(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Corporation");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Client");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(globalVariables.docChecklistNameForFNP, globalVariables.fileName, "Vendor");
		            
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		            
			caseManagerHomePage.clickLogout(true);
		
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
		
	
	@Test(groups={"FNP", "Priority1", "client", "email", "HRP"}, description = "CLIENT : Send an email without any attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_1_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Send an email without any attachment";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.clientContentEmailSubject);

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.clientContentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2","client","email"}, description = "RELATIVE : Send an email without any attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_4_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "RELATIVE : Send an email without any attachment";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(relativeName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.clientContentEmailSubject);

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.clientContentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1", "client", "email", "HRP"}, description = "CLIENT : Send an email with an attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_2_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Send an email with an attachment";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.clientAttachmentEmailSubject);
			
			clientEmailsPage.attachFile(globalVariables.filePath);

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.clientAttachmentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "email"}, description = "RELATIVE : Send an email with an attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_6_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "RELATIVE : Send an email with an attachment";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(relativeName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.clientAttachmentEmailSubject);
			
			clientEmailsPage.attachFile(globalVariables.filePath);

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.clientAttachmentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1", "email", "client", "HRP"}, description = "CLIENT : Send an email without access to FN", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_3_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CLIENT : Send an email without access to FN";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(clientName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.emailSubjectWithoutAccess);

			clientEmailsPage.changeExtranetAccess();

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.emailSubjectWithoutAccess);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "email"}, description = "RELATIVE : Send an email without access to FN", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_4_5_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "CLIENT : Send an email without access to FN";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(relativeName, true);

			clientCreationPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.emailSubjectWithoutAccess);

			clientEmailsPage.changeExtranetAccess();

			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.verifyIfEmailSent(globalVariables.emailSubjectWithoutAccess);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1", "case", "email", "HRP"}, description = "CASE : Send Email (only content)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_3_3_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE : Send Email (only content)";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.caseContentEmailSubject);

			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.verifyIfEmailSent(globalVariables.caseContentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1", "case", "email", "HRP"}, description = "CASE : Send Email (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_3_4_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE : Send Email (with attachment)";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.caseAttachmentEmailSubject);
			
			caseEmailsPage.attachFile(globalVariables.filePath);

			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.verifyIfEmailSent(globalVariables.caseAttachmentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1", "case", "email", "HRP"}, description = "CASE : Send an email without access to FN", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_3_5_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		System.out.println(webSite);
		System.out.println(globalVariables.environmentName);
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE : Send an email without access to FN";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.caseAttachmentEmailSubject);
			
			caseEmailsPage.changeExtranetAccess();

			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.verifyIfEmailSent(globalVariables.caseAttachmentEmailSubject);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
		
	
	@Test(groups={"FNP", "Priority2", "client"}, description = "Add Arrival/Departure info to the relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_14_4(String browser) throws Exception
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String client_DeparturePlaceName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DeparturePlaceName");
		
		globalVariables.testCaseDescription = "Case Manager - Client: Add Arrival/Departure info to the relative";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(relativeName, true);

			CM_TravelInfoPage travelInfoPage = clientCreationPage.clickTravelInfoTab();

			travelInfoPage.addTravelInfo(globalVariables.arrivalAdmissionNumber);
			
			travelInfoPage.editTravelInfo(globalVariables.arrivalAdmissionNumber, client_DeparturePlaceName);
			
			travelInfoPage.verifyTravelInfo(globalVariables.arrivalAdmissionNumber);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups = {"FNP"}, description = "FNP: Case - Check for the Added Doc Checklist and Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_75_0"})
	public void CM_TC_84_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "FNP: Case - Check for the Added Doc Checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifyIfDocChecklistAvailable(globalVariables.docChecklistNameForFNP);
			
			fnpCaseProfilePage.verifyIfDocumentsAvailable(globalVariables.fileNameWithoutExtension);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
      @Test(groups={"Priority1", "client"}, description = "Client: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
      public void CM_TC_21_1(String browser) throws Exception 
      {           
            
            ReadWriteExcel readExcel = new ReadWriteExcel();
            String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

            globalVariables.testCaseDescription = "Case: Add Docs Checklist and upload a document to the created docs checklist";
            final WebDriver driver = WebDriverFactory.get(browser);
            driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

            try {
                  LoginPageTest login = new LoginPageTest(driver, webSite);

                  CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

                  login.clickAgreeButton(false);
                
                  CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                  
                  clientListPage.clickOnClientName(clientName, true);
                  
                  clientListPage.clickDocumentsTab();
                  
                  CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                  
                  clientDocumentsPage.uploadDocument(globalVariables.filePath);
                  
                  clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileName);
                  
                  clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "client");
                  clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "corporation");
                  clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "vendor");
                  
                  driver.close();
                  Utils.waitForAllWindowsToLoad(1, driver);
                  Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
                  
                  caseManagerHomePage.clickLogout(true);

                  Log.testCaseResult();
            }
            catch (Exception e) {
                  Log.exception(e, driver);

            }
            finally {
                  Log.endTestCase();
                  driver.quit();

            }
      }

	
	
	@Test(groups={"FNP", "Priority1", "client"}, description = "RELATIVE: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_21_2(String browser) throws Exception 
	{		
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "RELATIVE: Upload a document and change the Access Rights";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
			
			clientListPage.clickOnClientName(relativeName, true);
			
			clientListPage.clickDocumentsTab();
			
			CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
			
			clientDocumentsPage.uploadDocument(globalVariables.filePath);
			
			clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileName);
			
			clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "client");
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();

		}
	}
	
	
	@Test(groups={"FNP", "Priority1"}, description = "FNP: Check for the Documents uploaded in Relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_21_2"})
	public void FNP_TC_84_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "FNP: Check for the Documents uploaded in Relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtension);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}	

		
		@Test(groups = {"FNP", "case"} ,description = "Prerequisite for CM_TC_81_1 to get case details and update it on excel", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	    public void CM_TC_81_0(String browser) throws Exception 
	    {
	    	ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
	    	globalVariables.testCaseDescription = "Prerequisite for CM_TC_81_1 to get case details and update it on excel";
	    	final WebDriver driver = WebDriverFactory.get(browser);
	    	driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	        try 
	        {
	        	LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerDashboardPage.clickCaseTab(true);
	            
	            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

	            caseListPage.clickOnCaseId(caseCreated, true);

	            caseListPage.clickDetailsDatesTab(true);
	            
	            CM_DetailsAndDatesPage detailsAndDatesPage = new CM_DetailsAndDatesPage(driver);
	            
	            detailsAndDatesPage.fetchAndUpdateCaseStartDate(workbookNameWrite, sheetName);
	            
	            detailsAndDatesPage.fetchAndUpdateCaseStatus(workbookNameWrite, sheetName);

	            caseManagerDashboardPage.clickLogout(true);
	            
	            Log.testCaseResult();

	        }
	        catch (Exception e) {
	        	Log.exception(e, driver);
	        }
	        finally {
	        	Log.endTestCase();
	            driver.quit();
	        }
	    }
		
	
//		@Test(groups = {"forms","case"}, description = "Add Form/s", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_56")
//		public void CM_TC_38_0(String browser) throws Exception {
//			
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreated");
//
//			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//
//			globalVariables.testCaseDescription = "Case Manager - Case: Add Form/s";
//
//			final WebDriver driver = WebDriverFactory.get(browser);
//			
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//			
//			String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//
//			try {
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//				CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
//
//				login.clickAgreeButton(false);
//
//				caseManagerHomePage.clickCaseTab(true);
//
//				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//				
//				caseListPage.clickOnCaseId(caseId, true);
//				
//				caseListPage.clickFormsTab();
//				
//				CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
//
//			//	caseFormPage.clickGoButton(true);
//
//				caseManagerHomePage.clickLogout(true);
//
//				Log.testCaseResult();
//
//			} 
//			catch (Exception e) {
//				Log.exception(e, driver);
//			} 
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			} 
//		}
		
		
		@Test(groups = {"portalSetup", "FNP"}, description="Portal Setup: Add Policy/Guidelines for Client and check in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC_67_4")
		public void CM_TC_114_2(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			
			globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Client and check in FNP";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerHomePage.clickPortalSetup(true);
				
				PortalSetup portalSetup = new PortalSetup(driver);
				
				portalSetup.clickDefaultPortalAccess(false);
				
				portalSetup.searchCorporationByName(corpName, false);
				
				portalSetup.clickCustomizeCorporationSettings(false);
				
				portalSetup.clickPortalContentTab();
				
				portalSetup.clickPolicyGuidelinesTab();
				
				portalSetup.clickAddPolicyAndEnterData(globalVariables.policyTitleClient, globalVariables.policyDescriptionClient);
				
				portalSetup.chooseAllClients();
				
				portalSetup.clickSaveAndPublishPolicy();
				
				portalSetup.verifyIfPolicyAdded(globalVariables.policyTitleClient, globalVariables.policyDescriptionClient);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "My Zoomboard", "title", "false");
					
				login = new LoginPageTest(driver, webSite);

				login.fnplogin(fnpLoginId,fnpPassword, true);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);

				login.clickAgreeButton(false);

				login.clickAcceptAndContinueBtn(true);
				
				fnpHomePage.clickNewsGuidelinesTab();
				
				FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
				
				fnpNewsPage.clickPolicyGuidelinesTab();
				
				fnpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleClient, globalVariables.policyDescriptionClient);

				Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"portalSetup", "FNP"}, description="Portal Setup: Add Policy/Guidelines for Corporation and Client and check in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC_67_4")
		public void CM_TC_114_1(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			
			globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Corporation and Client and check in FNP";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerHomePage.clickPortalSetup(true);
				
				PortalSetup portalSetup = new PortalSetup(driver);
				
				portalSetup.clickDefaultPortalAccess(false);
				
				portalSetup.searchCorporationByName(corpName, false);
				
				portalSetup.clickCustomizeCorporationSettings(false);
				
				portalSetup.clickPortalContentTab();
				
				portalSetup.clickPolicyGuidelinesTab();
				
				portalSetup.clickAddPolicyAndEnterData(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);
				
				portalSetup.chooseBothCheckbox();
				
				portalSetup.clickSaveAndPublishPolicy();
				
				portalSetup.verifyIfPolicyAdded(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "My Zoomboard", "title", "false");
					
				login = new LoginPageTest(driver, webSite);

				login.fnplogin(fnpLoginId,fnpPassword, true);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);

				login.clickAgreeButton(false);

				login.clickAcceptAndContinueBtn(true);
				
				fnpHomePage.clickNewsGuidelinesTab();
				
				FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
				
				fnpNewsPage.clickPolicyGuidelinesTab();
				
				fnpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);

				Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"portalSetup", "FNP"}, description="Delete Policy/Guidelines and check in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_114_2")
        public void CM_TC_114_3(String browser) throws Exception 
        {
              ReadWriteExcel readExcel = new ReadWriteExcel();
              String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
              String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
              String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
              
              globalVariables.testCaseDescription = "Delete Policy/Guidelines and check in FNP";
              final WebDriver driver = WebDriverFactory.get(browser);
              driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
              
              try 
              {
                    LoginPageTest login = new LoginPageTest(driver, webSite);

              CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

              login.clickAgreeButton(false);

              caseManagerHomePage.clickPortalSetup(true);
                    
	            PortalSetup portalSetup = new PortalSetup(driver);
	            
	            portalSetup.clickDefaultPortalAccess(false);
	            
	            portalSetup.searchCorporationByName(corpName, false);
	            
	            portalSetup.clickCustomizeCorporationSettings(false);
	            
	            portalSetup.clickPortalContentTab();
	            
	            portalSetup.clickPolicyGuidelinesTab();
	            
	            portalSetup.deleteNews(globalVariables.policyTitleClient);
	            
	            portalSetup.verifyIfPolicyDeleted(globalVariables.policyTitleClient);
	            
	            driver.close();
	            Utils.waitForAllWindowsToLoad(1, driver);
	            Utils.switchWindows(driver, "My Zoomboard", "title", "false");
	                  
	            login = new LoginPageTest(driver, webSite);
	
	            login.fnplogin(fnpLoginId,fnpPassword, true);
	
	            FnpHomePage fnpHomePage = new FnpHomePage(driver);
	
	            login.clickAgreeButton(false);
	
	            login.clickAcceptAndContinueBtn(true);
	            
	            fnpHomePage.clickNewsGuidelinesTab();
	            
	            FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
	            
	            fnpNewsPage.clickPolicyGuidelinesTab();
	            
	            fnpNewsPage.verifyIfNewsDeleted(globalVariables.policyTitleClient);
	
	            Log.testCaseResult();
	            
	      } catch (Exception e) {
	            Log.exception(e, driver);
	      } finally {
	            Log.endTestCase();
	            driver.quit();
	      }
        }

		
		
		@Test(groups={"FNP", "Priority2", "portalSetup"}, description="Portal Setup: Add News for Client and check in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC_67_4")
		public void CM_TC_113_2(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			
			globalVariables.testCaseDescription = "Portal Setup: Add News for Client and check in FNP";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerHomePage.clickPortalSetup(true);
				
				PortalSetup portalSetup = new PortalSetup(driver);
				
				portalSetup.clickDefaultPortalAccess(false);
				
				portalSetup.searchCorporationByName(corpName, false);
				
				portalSetup.clickCustomizeCorporationSettings(false);
				
				portalSetup.clickPortalContentTab();
				
				portalSetup.clickNewsTab();
				
				portalSetup.clickAddNewsAndEnterData(globalVariables.newsTitleClient, globalVariables.newsDescriptionClient);
				
				portalSetup.chooseAllClients();
				
				portalSetup.clickSaveAndPublish();
				
				portalSetup.verifyIfNewsAdded(globalVariables.newsTitleClient, globalVariables.newsDescriptionClient);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "My Zoomboard", "title", "false");
					
				login = new LoginPageTest(driver, webSite);

				login.fnplogin(fnpLoginId,fnpPassword, true);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);

				login.clickAgreeButton(false);

				login.clickAcceptAndContinueBtn(true);
				
				fnpHomePage.clickNewsGuidelinesTab();
				
				FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
				
				fnpNewsPage.verifyIfNewsPresent(globalVariables.newsTitleClient, globalVariables.newsDescriptionClient);

				Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "portalSetup"}, description="Portal Setup: Add News for Corporation and Client and check in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC_67_4")
		public void CM_TC_113_1(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			
			globalVariables.testCaseDescription = "Portal Setup: Add News for Corporation and Client and check in FNP";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerHomePage.clickPortalSetup(true);
				
				PortalSetup portalSetup = new PortalSetup(driver);
				
				portalSetup.clickDefaultPortalAccess(false);
				
				portalSetup.searchCorporationByName(corpName, false);
				
				portalSetup.clickCustomizeCorporationSettings(false);
				
				portalSetup.clickPortalContentTab();
				
				portalSetup.clickNewsTab();
				
				portalSetup.clickAddNewsAndEnterData(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);
				
				portalSetup.chooseBothCheckbox();
				
				portalSetup.clickSaveAndPublish();
				
				portalSetup.verifyIfNewsAdded(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "My Zoomboard", "title", "false");
					
				login = new LoginPageTest(driver, webSite);

				login.fnplogin(fnpLoginId,fnpPassword, true);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);

				login.clickAgreeButton(false);

				login.clickAcceptAndContinueBtn(true);
				
				fnpHomePage.clickNewsGuidelinesTab();
				
				FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
				
				fnpNewsPage.verifyIfNewsPresent(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);

				Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups={"FNP", "Priority2", "portalSetup"}, description="Portal Setup: Delete added News for FNP and Verify in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_113_2"})
		public void CM_TC_113_3(String browser) throws Exception 
		{
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
			String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
			
			globalVariables.testCaseDescription = "Portal Setup: Add News for Corporation and Client and check in FNP";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			
			try 
			{
				LoginPageTest login = new LoginPageTest(driver, webSite);

	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

	            login.clickAgreeButton(false);

	            caseManagerHomePage.clickPortalSetup(true);
				
				PortalSetup portalSetup = new PortalSetup(driver);
				
				portalSetup.clickDefaultPortalAccess(false);
				
				portalSetup.searchCorporationByName(corpName, false);
				
				portalSetup.clickCustomizeCorporationSettings(false);
				
				portalSetup.clickPortalContentTab();
				
				portalSetup.clickNewsTab();
				
				portalSetup.deleteNews(globalVariables.newsTitleClient);
				
				portalSetup.verifyIfNewsDeleted(globalVariables.newsTitleClient);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "My Zoomboard", "title", "false");
					
				login = new LoginPageTest(driver, webSite);

				login.fnplogin(fnpLoginId,fnpPassword, true);

				FnpHomePage fnpHomePage = new FnpHomePage(driver);

				login.clickAgreeButton(false);

				login.clickAcceptAndContinueBtn(true);
				
				fnpHomePage.clickNewsGuidelinesTab();
				
				FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
				
				fnpNewsPage.verifyIfNewsDeleted(globalVariables.newsTitleClient);

				Log.testCaseResult();
				
			} catch (Exception e) {
				Log.exception(e, driver);
			} finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation"}, description = "Corp: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_105(String browser) throws Exception 
		{		
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Case: Add Docs Checklist and upload a document to the created docs checklist";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.clickDocumentTab();
				
				CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
				
				clientDocumentsPage.uploadDocument(globalVariables.filePath);
				
				clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileName);
				
//				clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName);
//				
//				clientDocumentsPage.verifyIfDocumentAccessRightsChanged(globalVariables.fileName);
				
				driver.close();
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();

			}
		}
		
		
		 @Test(groups = {"HRP", "Priority2", "notes", "corporation"}, description = "Case Manager - CORP: Add notes", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		    public void CM_TC_125(String browser) throws Exception 
		    {
		    	ReadWriteExcel readExcel = new ReadWriteExcel();
		    	String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		        globalVariables.testCaseDescription = "Case Manager - CORP: Add notes";
		        final WebDriver driver = WebDriverFactory.get(browser);
		        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		      
		        try 
		        {
		        	LoginPageTest login = new LoginPageTest(driver, webSite);

		            CM_DashboardPage caseManagerDashBoardPage = login.caseManagerlogin(userName, password, true);

		            login.clickAgreeButton(false);
		            
		            caseManagerDashBoardPage.clickCorporationTab(true);

		            CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
		            
		            corpListPage.clickOnCorporationName(corpName);
		            
		            corpListPage.clickNotesTab();
		                
		            CM_NotesPage notesPage = new CM_NotesPage(driver);
		                
		            notesPage.addNotesInCorp(globalVariables.notesDetailsTextCorp, true);

		            notesPage.verifyNotesInCorp(globalVariables.notesDetailsTextCorp);

		            caseManagerDashBoardPage.clickLogout(true);

		            Log.testCaseResult();

		        }
		        catch (Exception e) {
		        	Log.exception(e, driver);
		        }
		        finally {
		        	Log.endTestCase();
		        	driver.quit();
		        }
		    }
		 
		 
		 
		@Test(groups = {"corporation", "HRP"}, description = "Corp: Edit custom labels", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_115_0(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp: Edit custom labels";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.clickCustomDataTab();
				
				CM_CorporationCustomDataPage customDataPage = new CM_CorporationCustomDataPage(driver);
				
				customDataPage.editDefaultCustomData(globalVariables.defaultCustomFieldName, globalVariables.defaultCustomData);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation", "HRP"}, description = "Corp: Attach custom label to corp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_115_1(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp: Attach custom label to corp";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.clickCustomDataTab();
				
				CM_CorporationCustomDataPage customDataPage = new CM_CorporationCustomDataPage(driver);
				
				customDataPage.attachCorpCustomLabel(globalVariables.corpCustomFieldName, globalVariables.corpCustomData);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation", "HRP"}, description = "Corp/Client: Attach custom label to client and add data in client level", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_115_2(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp/Client: Attach custom label to client and add data in client level";
			String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.clickCustomDataTab();
				
				CM_CorporationCustomDataPage customDataPage = new CM_CorporationCustomDataPage(driver);
				
				customDataPage.attachClientCustomLabel(globalVariables.clientCustomFieldName);
				
				caseManagerHomePage.clickClientTab(true);
				
				CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
				
				clientListPage.clickOnClientName(clientName, true);
				
				clientListPage.clickCustomDataTab();
				
				clientListPage.editCustomDataClient(globalVariables.clientCustomFieldName, globalVariables.clientCustomData);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation", "HRP"}, description = "Corp/Case: Attach custom label to case and add data in case level", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_115_3(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp/Case: Attach custom label to case and add data in case level";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.clickCustomDataTab();
				
				CM_CorporationCustomDataPage customDataPage = new CM_CorporationCustomDataPage(driver);
				
				customDataPage.attachCaseCustomLabel(globalVariables.caseCustomFieldName);
				
				caseManagerHomePage.clickCaseTab(true);
				
				CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
				
				caseListPage.clickOnCaseId(caseCreated, true);
				
				caseListPage.clickCustomDataTab(true);
				
				caseListPage.editCustomDataCase(globalVariables.caseCustomFieldName, globalVariables.caseCustomData);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation", "HRP"}, description = "Corp : Edit info of person signing forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_116_1(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp : Edit info of person signing forms";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.editSigningPersonInfo(globalVariables.signingPersonTitle , globalVariables.signingPersonContactNumber );
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
		@Test(groups = {"corporation", "HRP"}, description = "Corp : Edit Administrative contact details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
		public void CM_TC_116_2(String browser) throws Exception 
		{		
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			globalVariables.testCaseDescription = "Corp : Edit Administrative contact details";
			final WebDriver driver = WebDriverFactory.get(browser);
			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

			try {
				LoginPageTest login = new LoginPageTest(driver, webSite);
				
				CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

				login.caseManagerlogin(userName, password, true);

				login.clickAgreeButton(false);

				caseManagerHomePage.clickCorporationTab(true);
				
				CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
				corpListPage.clickOnCorporationName(corpName);
				
				corpListPage.editAdministrativeContactInfo(globalVariables.administrativePersonFirstName, globalVariables.administrativePersonLastName, globalVariables.administrativePersonTitle, globalVariables.administrativePersonContactNumber, globalVariables.administrativePersonEmailId);
				
				caseManagerHomePage.clickLogout(true);

				Log.testCaseResult();
			}
			catch (Exception e) {
				Log.exception(e, driver);

			}
			finally {
				Log.endTestCase();
				driver.quit();
			}
		}
		
		
			@Test(groups={"Priority2", "client", "HRP"}, description = "RELATIVE: Add New Address History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_9(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "Case Manager - RELATIVE: Add New Address History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(relativeFirstName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addAddressHistory(globalVariables.countryRelative, globalVariables.cityRelative, globalVariables.streetNumberRelative, globalVariables.stateRelative, globalVariables.dayRelative, globalVariables.monthRelative, globalVariables.yearRelative);

			historyInfoPage.verifyAddressHistory(globalVariables.cityRelative, globalVariables.countryRelative, globalVariables.streetNumberRelative, globalVariables.stateRelative);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "client", "HRP"}, description = "Relative: History Info-Add New Education History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_10(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "Case Manager-RELATIVE: History Info-Add New Education History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(relativeFirstName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addEducationHistory(globalVariables.schoolTypeRelative, globalVariables.schoolNameRelative, globalVariables.universityNameRelative, globalVariables.degreeRelative, globalVariables.fieldOfStudyRelative, globalVariables.dayRelative, globalVariables.monthRelative, globalVariables.yearRelative, globalVariables.numberForPersonalInfoRelative);

			historyInfoPage.verifyEducationHistory(globalVariables.schoolNameRelative, globalVariables.degreeRelative, globalVariables.fieldOfStudyRelative);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2", "client", "HRP"}, description = "History Info-Add New Employement History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_13_11(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "Case Manager-Client: History Info-Add New Employment History";
		final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

			clientListPage.clickOnClientName(relativeFirstName, true);

			CM_HistoryInfoPage historyInfoPage = clientListPage.clickHistoryInfo(true);

			historyInfoPage.addEmploymentHistory(globalVariables.employerRelative, globalVariables.occupationRelative, globalVariables.dayRelative, globalVariables.monthRelative, globalVariables.yearRelative, globalVariables.numberForPersonalInfoRelative);

			historyInfoPage.verifyEmploymentHistory(globalVariables.occupationRelative, globalVariables.employerRelative);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"corporation", "HRP"}, description = "Corp: Company Info for Filing Petitions (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_127(String browser) throws Exception 
	{		
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Corp: Company Info for Filing Petitions (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.addAddtionalCorpDeatils(globalVariables.businessDescription, globalVariables.businessType, globalVariables.yearEstablished, globalVariables.placeOfIncorporation, globalVariables.numberOfEmployees, globalVariables.doingBusinessAs, globalVariables.grossAnnualIncome, globalVariables.netAnnualIncome, globalVariables.taxID, globalVariables.stateTax, globalVariables.naicsCode);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"corporation", "HRP"}, description = "Corp: Primary Firm Contact", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_128(String browser) throws Exception 
	{		
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Corp: Primary Firm Contact";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickCaseManagerTab();
			
			corpListPage.editPrimaryFirmContact();
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"client", "HRP"}, description = "Client : Add Job details(Employment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_129(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Client : Add Job details(Employment)";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS); 

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
            
            CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
                
            clientProfilePage.editProfileDetails(globalVariables.employeeID, globalVariables.department);
            
            clientListPage.clickJobDetailsTab(true);
            
            clientListPage.editJobDetailsPage(globalVariables.Designation, globalVariables.annualSalary, globalVariables.currencyType, globalVariables.responsibilities, globalVariables.hireDate, globalVariables.rehireDate, globalVariables.terminationDate);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
        	driver.quit();
        } 
    }
	
	
	@Test(groups = {"HRP", "case"}, description = "Case: Add Proposed job details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_130(String browser) throws Exception 
	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Add Proposed job details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickJobDetails(true);
			
			caseListPage.addProposedJobDetails(globalVariables.cityName, globalVariables.countryName ,globalVariables.supervisorTitle, globalVariables.noOfSubOrdinates, globalVariables.proposedJobTitle, globalVariables.jobDuties, globalVariables.jobType, globalVariables.socCode, globalVariables.noOfHoursPerWeek, globalVariables.qualification, globalVariables.majorFieldOfStudy, globalVariables.experienceInJobOfferedYears, globalVariables.experienceInJobOfferedMonths, globalVariables.experienceInRelatedOccupationYears, globalVariables.experienceInRelatedOccupationMonths, globalVariables.relatedOccupation);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"client", "HRP"}, description = "Client : Add Personal Details (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_131(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
        globalVariables.testCaseDescription = "Client : Add Personal Details (Client)";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS); 

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);

            clientListPage.clickOnClientName(clientName, true);
            
            clientListPage.clickPassportInfoTab();
            
            CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);
            
            //country --> country of birth // citizenship --> nationality // countryName --> country of nationality
            passportInfoPage.addPersonalDetails(globalVariables.gender, globalVariables.day, globalVariables.month, globalVariables.year, globalVariables.placeOfBirth, globalVariables.country, globalVariables.citizenship, globalVariables.countryName);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
        	driver.quit();
        } 
    }
	
	
	@Test(groups={"client", "HRP"}, description = "Relative : Add Personal Details (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_132(String browser) throws Exception 
    {
    	ReadWriteExcel readExcel = new ReadWriteExcel();
    	String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
        globalVariables.testCaseDescription = "Relative : Add Personal Details (Client)";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS); 

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerDashboardPage.clickClientTab(true);
    		
			clientListPage.clickOnClientName(relativeFirstName, true);
            
            clientListPage.clickPassportInfoTab();
            
            CM_PassportInfoPage passportInfoPage = new CM_PassportInfoPage(driver);
            
            //country --> country of birth // citizenship --> nationality // countryName --> country of nationality
            passportInfoPage.addPersonalDetails(globalVariables.gender, globalVariables.day, globalVariables.month, globalVariables.year, globalVariables.placeOfBirth, globalVariables.country, globalVariables.citizenship, globalVariables.countryName);

            caseManagerDashboardPage.clickLogout(true);

            Log.testCaseResult();

        } 
        catch (Exception e) {
        	Log.exception(e, driver);
        } 
        finally {
        	Log.endTestCase();
        	driver.quit();
        } 
    }
    
    
    	@Test(groups = {"client", "HRP"}, description = "Edit client Work Address", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_72_1_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager: Edit client Work Address";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			CM_ClientListPage clientCreationPage = caseManagerHomePage.clickClientTab(true);

			clientCreationPage.clickOnClientName(clientName, true);
			
			clientCreationPage.clickPersonalInfoTab(true);
			
			CM_PersonalInfoPage personalInfoPage = new CM_PersonalInfoPage(driver);

			personalInfoPage.editWorkAddress(globalVariables.workCity, globalVariables.countryForWork, globalVariables.apartmentForWork, globalVariables.workAddressStreet1, globalVariables.workAddressStreet2, globalVariables.workAddressPinCode, globalVariables.workAddressState, globalVariables.workMobile, globalVariables.workTelephone);
			
			personalInfoPage.verifyResidenceInfo(globalVariables.workCity, globalVariables.countryForWork, globalVariables.apartmentForWork, globalVariables.workAddressStreet1, globalVariables.workAddressStreet2, globalVariables.workAddressPinCode, globalVariables.workAddressState, globalVariables.workMobile, globalVariables.workTelephone);

			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups = {"corporation", "HRP"}, description = "Corporation: Send (Corporation) Email (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_126_1(String browser) throws Exception 
	{			
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Corporation: Send (Corporation) Email (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
				
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
			corpListPage.clickOnCorporationName(corpName);
				
			corpListPage.clickEmailsTab();
				
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.clickComposeEmail();
			
			corporationEmailPage.changeEmailSubject(globalVariables.corpContentEmailSubject);
			
			corporationEmailPage.changeExtranetAccess("corporation");
			
			corporationEmailPage.enterMessageAndSendContentEmail(globalVariables.emailMessage);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"corporation", "HRP"}, description = "Corporation: Send (Corporation) Email (without extranet access)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_126_4(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Corporation: Send (Corporation) Email (without extranet access)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
				
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
			corpListPage.clickOnCorporationName(corpName);
				
			corpListPage.clickEmailsTab();
				
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.clickComposeEmail();
			
			corporationEmailPage.changeEmailSubject(globalVariables.emailSubjectWithoutAccess);
			
			corporationEmailPage.changeExtranetAccess("firm");
			
			corporationEmailPage.enterMessageAndSendContentEmail(globalVariables.emailMessage);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"corporation", "HRP"}, description = "Corporation: Send (Corporation) Email (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_126_2(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Corporation: Send (Corporation) Email (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
				
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
				
			corpListPage.clickOnCorporationName(corpName);
				
			corpListPage.clickEmailsTab();
				
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.clickComposeEmail();
			
			corporationEmailPage.changeEmailSubject(globalVariables.corpAttachmentEmailSubject);
			
			corporationEmailPage.changeExtranetAccess("corporation");
			
			corporationEmailPage.attachFile(globalVariables.filePath);
			
			corporationEmailPage.enterMessageAndSendContentEmail(globalVariables.emailMessage);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Excel Verification", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ReportsExcelVerification(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Excel Verification";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin("AABD467", "Zoom@123", true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.getTotalOpenCases();
			
			reports_3_0_page.clickAllOpenCases();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "All Open Cases");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ReportsColumnVerification(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin("AABD467", "Zoom@123", true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickAllOpenCases();
			
			reports_3_0_page.verifyColumns("All Open Cases Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
/*	 @Test(groups = {"reports", "HRP"}, description="Portal Setup : Get Report List", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC)
     public void CM_TC_133_1(String browser) throws Exception 
     {
       ReadWriteExcel readExcel = new ReadWriteExcel();
       String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
       String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
       
       globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Corporation and Client and check in FNP";
       final WebDriver driver = WebDriverFactory.get(browser);
       driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
       
       try 
       {
             LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);

             caseManagerHomePage.clickPortalSetup(true);
             
             PortalSetup portalSetup = new PortalSetup(driver);
             
             portalSetup.clickDefaultPortalAccess(false);
             
             portalSetup.searchCorporationByName(corpName, false);
             
             portalSetup.clickCustomizeCorporationSettings(false);
             
             portalSetup.clickCustomizeReportsAccessTab();

             List<List<String>> caseManagerReports = new ArrayList<List<String>>();
             
             caseManagerReports = portalSetup.getReportList();
             
             portalSetup.backToZoomboard();
             
             caseManagerHomePage.clickLogout(true);
             
             login.hrpLogin(hrpLoginID, hrpPassword, true);
 			
 			 HrpHomePage hrpHomePage = new HrpHomePage(driver);

 			//login.clickAgreeButton(false);

 			//login.clickAcceptAndContinueBtn(true);

 			 hrpHomePage.clickReportsTab();
 			 
 			List<List<String>> hrpReports = new ArrayList<List<String>>();
 			 
 			hrpReports = hrpHomePage.getReportList();
 			
 			hrpHomePage.verifyReports(caseManagerReports, hrpReports);
            
            Log.testCaseResult();
             
       } catch (Exception e) {
             Log.exception(e, driver);
       } finally {
             Log.endTestCase();
             driver.quit();
       }
     }
	 
	 
	 
	 @Test(groups = {"reports", "HRP"}, description="Portal Setup : Remove reports", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="CM_TC)
     public void CM_TC_133_2(String browser) throws Exception 
     {
       ReadWriteExcel readExcel = new ReadWriteExcel();
       String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
       String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
       
       globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Corporation and Client and check in FNP";
       final WebDriver driver = WebDriverFactory.get(browser);
       driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
       
       try 
       {
             LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);

             caseManagerHomePage.clickPortalSetup(true);
             
             PortalSetup portalSetup = new PortalSetup(driver);
             
             portalSetup.clickDefaultPortalAccess(false);
             
             portalSetup.searchCorporationByName(corpName, false);
             
             portalSetup.clickCustomizeCorporationSettings(false);
             
             portalSetup.clickCustomizeReportsAccessTab();

             portalSetup.removeReports();
             
             portalSetup.backToZoomboard();
             
             caseManagerHomePage.clickLogout(true);
             
             login.hrpLogin(hrpLoginID, hrpPassword, true);
 			
 			 HrpHomePage hrpHomePage = new HrpHomePage(driver);

 			//login.clickAgreeButton(false);

 			//login.clickAcceptAndContinueBtn(true);

 			 hrpHomePage.clickReportsTab();
 			 
 			 hrpHomePage.verifyNoReportsPresent();
            
            Log.testCaseResult();
             
       } catch (Exception e) {
             Log.exception(e, driver);
       } finally {
             Log.endTestCase();
             driver.quit();
       }
     }*/

	 
	
	 

		
			
}
