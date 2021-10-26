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
import com.inszoomapp.pages.CM_AccessRightsPage;
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
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class AccessRightsTestScript {

	String webSite = null;
	String env = null;
	String browserName = null;
	String editionType = null;
	String corporationName = "Automation";
	
	String superAdminUserName = null;
	String superAdminPassword = null;
	
	String userName = null;
	String password = null;
	String superUserCaseId;
	
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
						superAdminUserName = data.EnterpriseDisabled_Super_CM_userName;
						superAdminPassword = data.EnterpriseDisabled_Super_CM_password;
						userName = data.EnterpriseDisabled_CM_userName;
						password = data.EnterpriseDisabled_CM_password;
						superUserCaseId = data.EnterpriseDisabled_SuperUserCaseId;
					}
					else if(editionType.equals("professional"))
					{
						superAdminUserName = data.ProfessionalDisabled_Super_CM_userName;
						superAdminPassword = data.ProfessionalDisabled_Super_CM_password;
						userName = data.ProfessionalDisabled_CM_userName;
						password = data.ProfessionalDisabled_CM_password;
						superUserCaseId = data.ProfessionalDisabled_SuperUserCaseId;
					}else if(editionType.equals("standard"))
					{
						superAdminUserName = data.Standard_Super_CM_userName;
						superAdminPassword = data.Standard_Super_CM_password;
						userName = data.Standard_CM_userName;
						password = data.Standard_CM_password;
						superUserCaseId = data.Standard_SuperUserCaseId;
					}
					
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Stg.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					}
					
					if(editionType.equalsIgnoreCase("professional"))
						sheetName = "Access Rights-Professional";
					else if(editionType.equalsIgnoreCase("enterprise"))
						sheetName = "Access Rights-Enterprise";
					else if(editionType.equalsIgnoreCase("standard"))
						sheetName="Access Rights-Standard";
					
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
	
	
	@Test(groups = {"pp"}, description = "Verify if the Edit button is disabled in the Access Rights Page for a non-super user.")
	public void AR_TC_01() throws Exception
	{
		globalVariables.testCaseDescription = "Verify if the Edit button is disabled in the Access Rights Page for a non-super user.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickMySettings();
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			
			mySettingsPage.clickAccessRightsTabOfNonSuperAdmin();
			
			mySettingsPage.verifyEditAccessRightsButtonPresent(false);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify that the delete buttons are enabled for all corporations for the non - super user once the  Super user sets Access Rights to ALL for Delete/Restore Corporation")
	public void AR_TC_45() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the delete buttons are enabled for all corporations for the non - super user once the  Super user sets Access Rights to ALL for Delete/Restore Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddCorportaion("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToDeleteCorportaion("A");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the delete buttons are enabled for the corporations that are created by the non - super user once the  Super user sets Access Rights to MY for Delete/Restore Corporation")
	public void AR_TC_46() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the delete buttons are enabled for the corporations that are created by the non - super user once the  Super user sets Access Rights to MY for Delete/Restore Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddCorportaion("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToDeleteCorportaion("M");
			
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
	    	
	    	corporationPage.verifyDeleteIconAccess(true, "MINE", corporationCreated);
	    	
	    	dashboard.clickCorporationTab(true);
	    	
	    	corporationPage.verifySuperUserDeleteIconAcesss(true, "MINE", corporationCreated);
	    	
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the delete buttons are disabled for the non - super user once the  Super user sets Access Rights to NONE for Delete/Restore Corporation")
	public void AR_TC_47() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the delete buttons are disabled for the non - super user once the  Super user sets Access Rights to NONE for Delete/Restore Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddCorportaion("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToDeleteCorportaion("N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify Check Fucntionality of Add Corporation")
	public void AR_TC_43() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check Fucntionality of Add Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddCorportaion("Y");
			
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
	public void AR_TC_44() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Add Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddCorportaion("N");
			
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
	public void AR_TC_51() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddClient("Y");
			
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
	public void AR_TC_52() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddClient("N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete All Client")
	public void AR_TC_53() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddClient("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToDeleteClient("A");
			
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
	    	
	    	dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Delete Mine Client")
	public void AR_TC_54() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddClient("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
				
			accessRightsPage.giveAccessRghtsToDeleteClient("M");
			
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
	    	
	    	dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify Uncheck functionality of Delete Client")
	public void AR_TC_55() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToAddClient("Y");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.giveAccessRghtsToDeleteClient("N");
			
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
	    	
	    	dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify View and Edit functionality of Dropdown Knowledge Base")
	public void AR_TC_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify check functionality of View and Edit Knowledge BAse";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		   
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("lstKowledgeAcc", "Y");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify View Only functionality of Dropdown Knowledge Base")
	public void AR_TC_2() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of View and Edit Knowledge Base";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("lstKowledgeAcc", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify View Only Access functionality of Dropdown Prospective Client")
	public void AR_TC_3() throws Exception
	{
		globalVariables.testCaseDescription = "Verify View Only Access functionality of Dropdown Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		   
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("lstClntDatabaseAcc", "V");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify View and Edit Access functionality of dropdown Prospective Client")
	public void AR_TC_4() throws Exception
	{
		globalVariables.testCaseDescription = "Verify View and Edit Access functionality of dropdown Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		   
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("lstClntDatabaseAcc", "E");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify No Access functionality of Dropdown Prospective Client")
	public void AR_TC_5() throws Exception
	{
		globalVariables.testCaseDescription = "Verify No Access functionality of Dropdown Prospective Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("lstClntDatabaseAcc", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify Check functionality of Edit Portal Setup")
	public void AR_TC_20() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Check functionality of Edit Portal setup";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddCustomizeClntsForPortal", "Y");
			
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
	public void AR_TC_21() throws Exception
	{
		globalVariables.testCaseDescription = "Verify uncheck functionality of Edit Portal Setup ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddCustomizeClntsForPortal", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify the dropdown functionality of No Access of Billing ")
	public void AR_TC_90() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the dropdown functionality of No Access of Billing ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyBilingAccessRights();
			
			accessRightsPage.genericGiveAccessDropdown("lstAccountDatabaseAcc", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify the dropdown functionality of View Only Access of Billing ")
	public void AR_TC_91() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the dropdown functionality of View Only Access of Billing ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyBilingAccessRights();
			
			accessRightsPage.genericGiveAccessDropdown("lstAccountDatabaseAcc", "V");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify the dropdown functionality of View and Edit Access of Billing ")
	public void AR_TC_92() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the dropdown functionality of View and Edit Access of Billing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyBilingAccessRights();
			
			accessRightsPage.genericGiveAccessDropdown("lstAccountDatabaseAcc", "E");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the add case buttons are enabled for all clients created by the non-super user once the  Super user sets Access Rights to ALL for Add Case")
	public void AR_TC_59() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the add case buttons are enabled for all clients created by the non-super user once the  Super user sets Access Rights to ALL for Add Case";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName =readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		 
		String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboAddCase", "A");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the add case buttons are enabled for the clients created by or assigned to the non-super user once the  Super user sets Access Rights to MY for Add Case")
	public void AR_TC_60() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the add case buttons are enabled for the clients created by or assigned to the non-super user once the  Super user sets Access Rights to MY for Add Case";
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseCreated=readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");;
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboAddCase", "M");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the add case buttons are disabled for the non-super user once the  Super user sets Access Rights to NONE for Add Case")
	public void AR_TC_61() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the add case buttons are disabled for the non-super user once the  Super user sets Access Rights to NONE for Add Case";
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated=readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboAddCase", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify that the delete buttons are enabed for the non - super user once the  Super user sets Access Rights to ALL for Delete/Restore Case")
	public void AR_TC_62() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the delete buttons are enabed for the non - super user once the  Super user sets Access Rights to ALL for Delete/Restore Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated=readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
	        
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboDelCase", "A");
			
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
	public void AR_TC_63() throws Exception
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
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboDelCase", "M");
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			/*if(editionType.equalsIgnoreCase("standard"))
			{
				dashboard.clickCaseTab(true);
				caseListPage.clickOnCaseId(superUserCaseId, true);
				caseListPage.assignCaseToUser(driver,userName, true);
			}*/
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
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
	public void AR_TC_64() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Uncheck Fucntionality of Delete CLient";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated=readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboDelCase", "N");
			
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
	
	
	@Test(groups = {"pp"}, description = "Verify the Yes functionality of dropdown Employee Transfer")
	public void AR_TC_76() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the Yes functionality of dropdown Employee Transfer";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboToolsAccessRight", "E");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddEmployeeTransfer", "Y");
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientListPage clientlistPage = new CM_ClientListPage(driver);
			
			clientlistPage.clickOnClientName(clientFirstName, true);
			
			clientlistPage.verifyEmployeeTransferTabPresent(true);
			
			dashboard.clickClientTab(true);
			
			clientlistPage.clickOnClientName(clientFirstName, true);
			
			clientlistPage.verifyEmployeeTransferTabPresent(true);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify the No functionality of dropdown Employee Transfer")
	public void AR_TC_77() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the No functionality of dropdown Employee Transfer";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboToolsAccessRight", "E");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddEmployeeTransfer", "N");
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickClientTab(true);
			
			CM_ClientListPage clientlistPage = new CM_ClientListPage(driver);
			
			clientlistPage.clickOnClientName(clientFirstName, true);
			
			clientlistPage.verifyEmployeeTransferTabPresent(false);
			
			dashboard.clickClientTab(true);
			
			clientlistPage.clickOnClientName(clientFirstName, true);
			
			clientlistPage.verifyEmployeeTransferTabPresent(false);
			
			dashboard.clickLogout(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify the Select Yes functionality of dropdown Setup Tools")
	public void AR_TC_78() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the Select Yes functionality of dropdown Setup Tools";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboToolsAccessRight", "E");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddSetupTools", "Y");
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickOrganizationToolsTab();
			
			settingsPage.verifySetupToolsTabPresent(true);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify the Select No functionality of dropdown Setup Tools")
	public void AR_TC_79() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the Select No functionality of dropdown Setup Tools";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			 
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboToolsAccessRight", "E");
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("ddSetupTools", "N");
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickOrganizationToolsTab();
			
			settingsPage.verifySetupToolsTabPresent(false);
	    	
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"pp"}, description = "Verify that the add case buttons are enabled for all clients created by the non-super user once the  Super user sets Access Rights to ALL for Add Case")
	public void AR_TC_59_Standard() throws Exception
	{
		globalVariables.testCaseDescription = "Verify that the add case buttons are enabled for all clients created by the non-super user once the  Super user sets Access Rights to ALL for Add Case";
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName =readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");;
		String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(superAdminUserName, superAdminPassword, true);
			
			dashboard.clickSettings(true);
			
			CM_MySettingsPage mySettingsPage = new CM_MySettingsPage(driver);
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			mySettingsPage.clickCaseManagersTab();
			
			mySettingsPage.clickOnNonSuperAdminId(driver, userName);
			
			mySettingsPage.clickAccessRightsTab();
			
			mySettingsPage.clickEditAtyAccessRightButton();
			
			accessRightsPage.genericGiveAccessDropdown("cboAddCase", "A");
			
			dashboard.clickLogout(true);
			
			loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseListPage.verifyAddCaseButtonPresent(true);
			
			caseListPage.clickAndAddCaseOnStandardEdition(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWorkForAccessRightsStd, data.AddCase_CountryName, data.PetitionType_AddCase, true);		   

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
}
