package com.inszoomapp.testscripts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientProfilePage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_InvoicePage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_MySettingsPage;
import com.inszoomapp.pages.CM_ProspectsListPage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.CM_SettingsSecurityProfile;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class SecurityProfileEnabledTestScript {

	String webSite = null;
	String env = null;
	String browserName = null;
	String editionType = null;
	String corporationName = "Automation";
	String superUserCaseId;
	
	String superAdminUserName = null;
	String superAdminPassword = null;
	
	String userName = null;
	String password = null;
	
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	AppDataBase data ;
	
	
	@BeforeTest(alwaysRun=true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();
		
		editionType = (System.getProperty("edition") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("edition")).toLowerCase();

		String os = System.getProperty("os.name");
		globalVariables.browserUsedForExecution = browserName;

		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
					if(editionType.equals("enterprise"))
					{
						superAdminUserName = data.EnterpriseEnabled_Super_CM_userName;
						superAdminPassword = data.EnterpriseEnabled_Super_CM_password;
						userName = data.EnterpriseEnabled_CM_userName;
						password = data.EnterpriseEnabled_CM_password;
						superUserCaseId = data.EnterpriseEnabled_SuperUserCaseId;
					}
					else if(editionType.equals("professional"))
					{
						superAdminUserName = data.ProfessionalEnabled_Super_CM_userName;
						superAdminPassword = data.ProfessionalEnabled_Super_CM_password;
						userName = data.ProfessionalEnabled_CM_userName;
						password = data.ProfessionalEnabled_CM_password;
					}
					
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Stg.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					}
					sheetName = "Security Profile";
					globalVariables.environmentName = env;
					
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
					globalVariables.environmentName = env;
				
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
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
					globalVariables.environmentName = env;
				
					break;
				}
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Security Profile is disabled for non Super-User")
	public void AR_SE_TC_1_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Security Profile is disabled for non Super-User";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.verifySecurityProfileTabPresent(false);
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify access rights in My Settings Access Rights is disabled")
	public void AR_SE_TC_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify access rights in My Settings Access Rights is disabled";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickMySettings();
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAccessRightButton();
			
			mySettingsPage.verifyDisabledAccessRights();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check Fucntionality of Add Corporation")
	public void AR_SE_TC_2() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Add Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCorporation", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = new CM_CorporationPage(driver);
			
			corporationPage.verifyAddCorporationButtonPresent(true);
			
			corporationPage.clickAddCorporationButton(true);
			
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);
			
			corporationPage.clickSaveCorporationButton(true);

			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
			CM_CorporationListPage corporationProfilePage = new CM_CorporationListPage(driver);
			
			corporationProfilePage.verifyAddCorporationAccess(true);
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck Fucntionality of Add Corporation")
	public void AR_SE_TC_3() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Add Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCorporation", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = new CM_CorporationPage(driver);
			
			corporationPage.verifyAddCorporationButtonPresent(false);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
	    	corporationPage.clickOnCorporationName(corporationCreated);
	    	
			CM_CorporationListPage corporationProfilePage = new CM_CorporationListPage(driver);
			
			corporationProfilePage.verifyAddCorporationAccess(false);
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check Fucntionality of Add Client")
	public void AR_SE_TC_25() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Add Client";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
        
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		globalVariables.clientFirstName = globalVariables.clientFirstName + strDate;
		globalVariables.clientLastName = globalVariables.clientLastName + strDate;
		

		String[] name = {globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientFirstName + " " + globalVariables.clientLastName};
		String[] nameKeys = {"ALoginSavedFirstClientName", "ALoginSavedLastClientName", "ALoginSavedClientName"};;

		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddClientDetails", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.verifyAddClientButtonPresent(true);
			
			clientListPage.clickAddClientButton(true);
			
			clientListPage.enterDataToCreateClient(workbookNameWrite , sheetName, corporationCreated, globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientEmailID);

			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);

			clientListPage.clickSaveClientButton(true);

			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);
			
			clientListPage.verifyAddClientAccess(true);

			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck Fucntionality of Add Client")
	public void AR_SE_TC_26() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Add Client";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddClientDetails", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.verifyAddClientButtonPresent(false);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.verifyAddClientAccess(false);

			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete All Corporation")
	public void AR_SE_TC_7() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete All Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCorporation", true);
			securityProfilePage.toggleCheckBox("DeleteCorporation", true);
			securityProfilePage.clickGenericRadioButton("allCorporation");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = new CM_CorporationPage(driver);
			CM_CorporationListPage corporationProfilePage = new CM_CorporationListPage(driver);
			
			corporationPage.verifyAddCorporationButtonPresent(true);
			
			corporationPage.clickAddCorporationButton(true);
			
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);
			
			corporationPage.clickSaveCorporationButton(true);

			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
	    	corporationProfilePage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifyDeleteIconAccess(true, "ALL", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifySuperUserDeleteIconAcesss(true, "ALL", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.clickOnCorporationName(globalVariables.superUserCorporationName);
	    	
	    	corporationProfilePage.verifyDeleteButtonAccess(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete Mine Corporation")
	public void AR_SE_TC_8() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete Mine Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCorporation", true);
			securityProfilePage.toggleCheckBox("DeleteCorporation", true);
			securityProfilePage.clickGenericRadioButton("ownCorporation");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = new CM_CorporationPage(driver);
			CM_CorporationListPage corporationProfilePage = new CM_CorporationListPage(driver);
			
			corporationPage.verifyAddCorporationButtonPresent(true);
			
			corporationPage.clickAddCorporationButton(true);
			
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);
			
			corporationPage.clickSaveCorporationButton(true);

			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
	    	corporationProfilePage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifyDeleteIconAccess(true, "Mine", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifySuperUserDeleteIconAcesss(true, "Mine", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.clickOnCorporationName(globalVariables.superUserCorporationName);
	    	
	    	corporationProfilePage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Delete Corporation")
	public void AR_SE_TC_9() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Delete Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCorporation", true);
			securityProfilePage.toggleCheckBox("DeleteCorporation", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = new CM_CorporationPage(driver);
			CM_CorporationListPage corporationProfilePage = new CM_CorporationListPage(driver);
			
			corporationPage.verifyAddCorporationButtonPresent(true);
			
			corporationPage.clickAddCorporationButton(true);
			
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);
			
			corporationPage.clickSaveCorporationButton(true);

			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
	    	String corporationCreated = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
	    	corporationProfilePage.verifyDeleteButtonAccess(false);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifyDeleteIconAccess(false, "", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifySuperUserDeleteIconAcesss(false, "", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.clickOnCorporationName(globalVariables.superUserCorporationName);
	    	
	    	corporationProfilePage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete All Client")
	public void AR_SE_TC_29() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete All Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			securityProfilePage.toggleCheckBox("DeleteClient", true);
			securityProfilePage.clickGenericRadioButton("allClient");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientProfilePage clientPage = new CM_ClientProfilePage(driver);
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientPage.verifyDeleteIconAccess(true, "ALL", clientName);
			
			dashboard.clickClientTab(true);
			
			clientPage.verifySuperUserDeleteIconAccess(true, "All", clientName);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientName, true);
			
	    	clientListPage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickClientTab(true);
	    	
	    	clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
	    	
	    	clientListPage.verifyDeleteButtonAccess(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete Mine Client")
	public void AR_SE_TC_30() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete Mine Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("DeleteClient", true);
			securityProfilePage.clickGenericRadioButton("ownClient");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientProfilePage clientPage = new CM_ClientProfilePage(driver);
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientPage.verifyDeleteIconAccess(true, "Mine", clientName);
			
			dashboard.clickClientTab(true);
			
			clientPage.verifySuperUserDeleteIconAccess(true, "Mine", clientName);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientName, true);
			
	    	clientListPage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickClientTab(true);
	    	
	    	clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
	    	
	    	clientListPage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Delete Client")
	public void AR_SE_TC_31() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Delete CLient";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("DeleteClient", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientProfilePage clientPage = new CM_ClientProfilePage(driver);
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientPage.verifyDeleteIconAccess(false, "Mine", clientName);
			
			dashboard.clickClientTab(true);
			
			clientPage.verifySuperUserDeleteIconAccess(false, "All", clientName);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientName, true);
			
	    	clientListPage.verifyDeleteButtonAccess(false);
	    	
	    	dashboard.clickClientTab(true);
	    	
	    	clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
	    	
	    	clientListPage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of View and Edit Prospective Client")
	public void AR_SE_TC_68() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check functionality of View and Edit Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("EditProspect", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickProspectiveClientTab();
			
			CM_ProspectsListPage prospectsListPage = new CM_ProspectsListPage(driver);
			
			prospectsListPage.verifyAddProspectsButtonPresent(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify uncheck functionality of View and Edit Prospective Client")
	public void AR_SE_TC_69() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of View and Edit Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
     
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewProspect", true);
			securityProfilePage.toggleCheckBox("EditProspect", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickProspectiveClientTab();
			
			CM_ProspectsListPage prospectsListPage = new CM_ProspectsListPage(driver);
			
			prospectsListPage.verifyAddProspectsButtonPresent(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify check functionality of View and Edit Prospective Client")
	public void AR_SE_TC_66() throws Exception
	{
		globalVariables.testCaseDescription = "Verify check functionality of View and Edit Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		   
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewProspect", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.verifyProspectsTabPresent(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify uncheck functionality of View and Edit Prospective Client")
	public void AR_SE_TC_67() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of View and Edit Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewProspect", false);
			securityProfilePage.toggleCheckBox("EditProspect", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.verifyProspectsTabPresent(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify check functionality of View and Edit Knowledge Base")
	public void AR_SE_TC_72() throws Exception
	{
		globalVariables.testCaseDescription = "Verify check functionality of View and Edit Knowledge BAse";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		   
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewAndEditKB", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.verifyKBEditAccess(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of View and Edit Knowledge Base")
	public void AR_SE_TC_73() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of View and Edit Knowledge Base";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewAndEditKB", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.verifyKBEditAccess(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Edit Portal Setup")
	public void AR_SE_TC_74() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check functionality of Edit Portal setup";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("viewAndEditPS", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickDefaultPortalAccess(true);
			
			portalSetupPage.searchCorporationByName(globalVariables.superUserCorporationName, true);
			
			portalSetupPage.clickCustomizeCorporationSettings(true);
			
			portalSetupPage.verifyEditPortalAccess(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Edit Portal Setup")
	public void AR_SE_TC_75() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of Edit Portal Setup ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("viewAndEditPS", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickDefaultPortalAccess(true);
			
			portalSetupPage.searchCorporationByName(globalVariables.superUserCorporationName, true);
			
			portalSetupPage.clickCustomizeCorporationSettings(true);
			
			portalSetupPage.verifyEditPortalAccess(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of View Billing")
	public void AR_SE_TC_83() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check functionality of View Billing ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewInvoices", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.verifyInvoiceTabPresent(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of View Biling")
	public void AR_SE_TC_88() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck functionality of View Biling";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewInvoices", false);
			securityProfilePage.toggleCheckBox("EditInvoices", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.verifyInvoiceTabPresent(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of View and Edit Biling")
	public void AR_SE_TC_84() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check functionality of View and Edit Biling";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("EditInvoices", true);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickInvoiceTab();
			
			CM_InvoicePage invoicePage = new CM_InvoicePage(driver);
			
			invoicePage.verifyEditAccess(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of View and Edit Biling")
	public void AR_SE_TC_85() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck functionality of View and Edit Biling";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("ViewInvoices", true);
			securityProfilePage.toggleCheckBox("EditInvoices", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickInvoiceTab();
			
			CM_InvoicePage invoicePage = new CM_InvoicePage(driver);
			
			invoicePage.verifyEditAccess(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check Fucntionality of Add All Case")
	public void AR_SE_TC_45() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Add All Case";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCase", true);
			securityProfilePage.clickGenericRadioButton("addallCase");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyAddCaseButtonPresent(true);
			
			caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWorkForAccessRights, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   

			caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForUSA", data.AddCase_CountryName,savedClientName, corpName, workbookNameWrite, sheetName, true);
			
			String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseProfilePage.verifyCaseCreationAccess(true);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseProfilePage.verifyCaseCreationAccess(true);
			
			CM_ClientListPage clientListPage = dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.verifyAddCaseAccess(true, true);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
			
			clientListPage.verifyAddCaseAccess(true, true);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check Functionality of Add Case - Mine Radio Button")
	public void AR_SE_TC_46() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Functionality of Add Case - Mine Radio Button";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCase", true);
			securityProfilePage.clickGenericRadioButton("addownCase");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyAddCaseButtonPresent(false);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseProfilePage.verifyCaseCreationAccess(true);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseProfilePage.verifyCaseCreationAccess(false);
			
			CM_ClientListPage clientListPage = dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.verifyAddCaseAccess(true, true);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
			
			clientListPage.verifyAddCaseAccess(false, false);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Add Case")
	public void AR_SE_TC_47() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck functionality of Add Case";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("AddCase", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyAddCaseButtonPresent(false);
			
			caseListPage.clickOnCaseId(caseCreated, false);
			
			caseProfilePage.verifyCaseCreationAccess(false);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseProfilePage.verifyCaseCreationAccess(false);
			
			CM_ClientListPage clientListPage = dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.verifyAddCaseAccess(false, false);
			
			dashboard.clickClientTab(true);
			
			clientListPage.clickOnClientName(globalVariables.superUserClientName, true);
			
			clientListPage.verifyAddCaseAccess(false, false);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete All Case")
	public void AR_SE_TC_50() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete All Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			securityProfilePage.toggleCheckBox("deleteCase", true);
			securityProfilePage.clickGenericRadioButton("allCase");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyDeleteIconAccess(true, caseCreated);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.verifyDeleteIconAccess(true, superUserCaseId);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickCaseTab(true);
	    	
	    	caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete Mine Client")
	public void AR_SE_TC_51() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Delete Mine Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
        ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			securityProfilePage.toggleCheckBox("deleteCase", true);
			securityProfilePage.clickGenericRadioButton("ownCase");
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyDeleteIconAccess(true, caseCreated);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.verifyDeleteIconAccess(false, superUserCaseId);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(true);
	    	
	    	dashboard.clickCaseTab(true);
	    	
	    	caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Delete Client")
	public void AR_SE_TC_52() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Delete CLient";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
			
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickSecurityProfileTab();
			
			CM_SettingsSecurityProfile securityProfilePage = new CM_SettingsSecurityProfile(driver);
			
			securityProfilePage.clickProfileSettingsLink();
			
			securityProfilePage.toggleCheckBox("deleteCase", false);
			
			securityProfilePage.backToSettings();
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyDeleteIconAccess(false, caseCreated);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.verifyDeleteIconAccess(false, superUserCaseId);
			
			dashboard.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(false);
	    	
	    	dashboard.clickCaseTab(true);
	    	
	    	caseListPage.clickOnCaseId(superUserCaseId, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseProfilePage.verifyDeleteButtonAccess(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
}
