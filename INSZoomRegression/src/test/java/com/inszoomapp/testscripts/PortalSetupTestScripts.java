package com.inszoomapp.testscripts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_AccessRightsPage;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_MySettingsPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.FNPNewsPage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FNP_DocumentsPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRP_ClientPage;
import com.inszoomapp.pages.HRP_CorporationPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

import org.testng.SkipException;

@Listeners(EmailReport.class)
public class PortalSetupTestScripts {
	
	String webSite = null;
	String env = null;
	String browserName = null;
	
	String userName = null;
	String password = null;
	String fnp_userName = null;
	String fnp_password = null;
	String hrp_userName = null;
	String hrp_password = null;
	String corporationName = null;
	String newCorporationName = null;
	String clientName = null;
	String hrpClientName = null;
	String caseId = null;
	String eConsentPortal = null;
	String roleName = null;
	String roleForUser ;
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

		sheetName = (System.getProperty("sheetName") != null ? System.getProperty("sheetName")
				: context.getCurrentXmlTest().getParameter("sheetName")).toLowerCase();
		
		String os = System.getProperty("os.name");

		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
					fnp_userName = data.B_userNamePortal;
					fnp_password = data.B_passwordPortal;
					hrp_userName = data.P_userNamePortal;
					hrp_password = data.P_passwordPortal;
					clientName = data.clientFirstName;
					hrpClientName = data.hrpClientName;
					corporationName = data.corporationName;
					newCorporationName = data.newCorporationName;
					roleName = data.roleName;
					caseId = data.caseIdPortal;
					eConsentPortal = data.eConsent;
					
					if(os.contains("Linux")){
                        workbookName = "testdata//data//Regression_Stg.xls";
                        workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
                    }else{
                    workbookName = "testdata\\data\\Regression_Stg.xls";
                    workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
                    }
//					workbookName = "testdata\\data\\Regression_Stg.xls";
//					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
//					//sheetName = "Inszoom";
					globalVariables.environmentName = env;
					
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
					fnp_userName = data.B_userNamePortal;
					fnp_password = data.B_passwordPortal;
					hrp_userName = data.P_userNamePortal;
					hrp_password = data.P_passwordPortal;
					clientName = data.clientFirstName;
					hrpClientName = data.hrpClientName;
					corporationName = data.corporationName;
					newCorporationName = data.newCorporationName;
					roleName = data.roleName;
					caseId = data.caseIdPortal;
					eConsentPortal = data.eConsent;
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					sheetName = "Inszoom";
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
					fnp_userName = data.B_userNamePortal;
					fnp_password = data.B_passwordPortal;
					globalVariables.environmentName = env;
			
					
					break;
				}
			}

		} catch (Exception e) {
			e.getMessage();
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
	
	
	@BeforeTest(groups = {"portalSetup"}, dependsOnMethods = {"init"})
	public void checkDataSetup() throws Exception
	{
		//Added by Nitisha Sinha on 29/09/2020

		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage corporationhomePage = dashboard.clickCorporationTab(true);
			
			corporationhomePage.clickOnCorporationName(corporationName);
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Important Date/Docs visible in FNP when Important Date/Docs is checked in Client Portal Settings")
	public void PS_TC_96() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Important Date/Docs visible in FNP when Important Date/Docs is checked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Important Docs / Dates", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyImportantDateAndDoc(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Important Date/Docs not visible in FNP when Important Date/Docs is unchecked in Client Portal Settings")
	public void PS_TC_97() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Important Date/Docs not visible in FNP when Important Date/Docs is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Important Docs / Dates", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyImportantDateAndDoc(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Important Date and Docs")
	public void PS_TC_98() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Important Date and Docs";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Important Docs / Dates");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Visas visible in FNP when Visa is checked in Client Portal Settings")
	public void PS_TC_99() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Visa visible in FNP when Visa is checked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Visas", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyVisas(true);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
		
		
	@Test(priority=1 ,groups = {"portalSetup"}, description = "Verify Visas not visible in FNP when Visa is unchecked in Client Portal Settings")
	public void PS_TC_100() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Visa not visible in FNP when Visa is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Visas", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyVisas(false);
			
			fnpHomePage.clickLogout(true);
	
			Log.testCaseResult();
	
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
		
		
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Visas")
	public void PS_TC_101() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Visas";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Visas");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Case Request visible in FNP when Case Request is checked in Client Portal Settings")
	public void PS_TC_102() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Case Request visible in FNP when Case Request is checked in Client Portal Settings";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Request", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyCaseRequest(true);
			
			fnpHomePage.clickLogout(true);
	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Case Request not visible in FNP when Case Request is unchecked in Client Portal Settings")
	public void PS_TC_103() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Case Request not visible in FNP when Case Request is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Request", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
		
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyCaseRequest(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Case Request")
	public void PS_TC_104() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Case Request";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Case Request");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Passport visible in FNP when Passport is checked in Client Portal Settings")
	public void PS_TC_105() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Passport visible in FNP when Passport is checked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Passport", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyPassport(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
				
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Passport not visible in FNP when Passport is unchecked in Client Portal Settings")
	public void PS_TC_106() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Passport not visible in FNP when Passport is unchecked in Client Portal Settings";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Passport", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyPassport(false);
			
			fnpHomePage.clickLogout(true);

			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Passport")
	public void PS_TC_107() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Passport";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Passport");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Client Notes visible in FNP when Client Notes is checked in Client Portal Settings")
	public void PS_TC_108() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Client Notes visible in FNP when Client Notes is checked in Client Portal Settings";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Client Notes", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyClientNotes(true);
			
			fnpHomePage.clickLogout(true);
	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Client Notes not visible in FNP when Client Notes is unchecked in Client Portal Settings")
	public void PS_TC_109() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Client Notes visible in FNP when Client Notes is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Notes", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyClientNotes(false);
			
			fnpHomePage.clickLogout(true);	
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Client Notes")
	public void PS_TC_110() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Client Notes";

		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Passport");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Documents visible in FNP when Documents is checked in Client Portal Settings")
	public void PS_TC_111() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Documents visible in FNP when Documents is checked in Client Portal Settings";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Documents", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyDocuments(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Documents not visible in FNP when Documents is unchecked in Client Portal Settings")
	public void PS_TC_112() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Documents visible in FNP when Documents is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Documents", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyDocuments(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Documents")
	public void PS_TC_113() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Documents";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Documents");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Travel visible in FNP when Travel is checked in Client Portal Settings")
	public void PS_TC_114() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Travel visible in FNP when Travel is checked in Client Portal Settings";

		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Travel", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyTravel(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Travel not visible in FNP when Travel is unchecked in Client Portal Settings")
	public void PS_TC_115() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Travel visible in FNP when Travel is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Travel", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyTravel(false);	
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of Travel")
	public void PS_TC_116() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of Travel";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Travel");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify News visible in FNP when News is checked in Client Portal Settings")
	public void PS_TC_117() throws Exception
	{
		globalVariables.testCaseDescription = "Verify News visible in FNP when News is checked in Client Portal Settings";

		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("News", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.verifyNews(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify News not visible in FNP when News is unchecked in Client Portal Settings")
	public void PS_TC_118() throws Exception
	{
		globalVariables.testCaseDescription = "Verify News not visible in FNP when News is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("News", false);
			
			portalSetupPage.toggleCheckBox("Policy / Guidelines", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.verifyNews(false);
			
			fnpHomePage.clickLogout(true);
				
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of News")
	public void PS_TC_119() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of News";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("News");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Policy / Guidelines visible in FNP when Policy / Guidelines is checked in Client Portal Settings")
	public void PS_TC_120() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Policy / Guidelines visible in FNP when Policy / Guidelines is checked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Policy / Guidelines", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.clickPolicyGuidelinesTab();
			
			fnpNewsPage.verifyPolicy(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Policy / Guidelines not visible in FNP when Policy / Guidelines is unchecked in Client Portal Settings")
	public void PS_TC_121() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Policy / Guidelines not visible in FNP when Policy / Guidelines is unchecked in Client Portal Settings";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Policy / Guidelines", false);
			
			portalSetupPage.toggleCheckBox("News", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.clickPolicyGuidelinesTab();
			
			fnpNewsPage.verifyPolicy(false);
			
			fnpHomePage.clickLogout(true);
						
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of News")
	public void PS_TC_122() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of News";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Policy / Guidelines");
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying News Tab not Present when news and policy both are unchecked")
	public void PS_TC_121_1() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying News Tab not Present when news and policy both are unchecked";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Policy / Guidelines", false);
			
			portalSetupPage.toggleCheckBox("News", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.verifyNewstab(false);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_123(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Events & Meetings", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyEventsAndMeetings(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_124(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Events & Meetings", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyEventsAndMeetings(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_125(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Events & Meetings under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Events & Meetings");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Messeges under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_126(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Messeges under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Messages", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyMessages(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Messeges under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_127(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Messeges under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Messages", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyMessages(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Messages under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_128(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Messages under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalsetup = new PortalSetup(driver);
			
			portalsetup.clickCustomizedPortalAccess();
			
			portalsetup.searchCorporationByName(corporationName, true);
			
			portalsetup.clickClientPortalSettingsTab();
			
			portalsetup.verifyPreviewButton("Messages");
			
			portalsetup.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the check functionality of Shipment And Mails under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_129(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the check functionality of Shipment And Mails under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Shipments & Mails", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyShipmentAndMail(true);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the uncheck functionality of Shipment And Mails under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_130(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the uncheck functionality of Shipment And Mails under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Shipments & Mails", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyShipmentAndMail(false);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Shipment And Mail under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_131(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Shipment And Mail under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Shipments & Mails");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the check functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_132(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the check functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.verifyCaseSteps(true);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the uncheck functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_133(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the uncheck functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.verifyCaseSteps(false);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_134(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Case Steps under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Case Steps");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_135(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Documentation", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDocumentationTab();
			
			caseProfile.verifyDocumentation(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_136(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Documentation", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.verifyFNPHomePage(clientName);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDocumentationTab();
			
			caseProfile.verifyDocumentation(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_137(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Documentation under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Documentation");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_138(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Details / Dates", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyDetailsOrDates(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_139(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Details / Dates", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyDetailsOrDates(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_140(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Details / Dates under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Details / Dates");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of filing details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_141(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of filing details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Filing Details", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyFilingDetails(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of filing details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_142(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of filing details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Filing Details", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyFilingDetails(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

		
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of filing details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_143(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of filing details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Filing Details");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_144(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Decision Details", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyDecisionDetails(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_145(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
		
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Decision Details", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyDecisionDetails(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_146(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Decision details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Decision Details");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Additional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_147(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Additional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Additional Application/Receipt Details", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyAdditionalapplictionOrReceiptDetails(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Additional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_148(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of DAdditional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Additional Application/Receipt Details", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyAdditionalapplictionOrReceiptDetails(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Additional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_149(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Additional Application/Receipt Details under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Additional Application/Receipt Details");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_150(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Visa Priority Date Info", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyVisaPriorityDateInfo(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_151(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Visa Priority Date Info", false);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(false);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.clickDetailsDatesTab();
			
			caseProfile.verifyVisaPriorityDateInfo(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_152(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Visa Priority Date Info under Show/Hide Client Profile Menus in Client portal menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Visa Priority Date Info");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Custom Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_153(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Custom Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPagetest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPagetest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Custom Details", true);
			
			portalSetupPage.clickSaveClientPortalSettings();			
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPagetest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyCustomDetails(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the Unchecked functionality of 'Custom Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_154(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the Unchecked functionality of 'Custom Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPagetest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPagetest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Custom Details",false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPagetest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyCustomDetails(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Custom Details' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_155(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Custom Details' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();

			portalSetupPage.verifyPreviewButton("Custom Details");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'LCA Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_156(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'LCA Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("LCA Details", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyLCADetails(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'LCA Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_157(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'LCA Details' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("LCA Details",false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyLCADetails(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'LCA Details' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_158(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'LCA' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.verifyPreviewButton("LCA Details");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'SWA and DOL Info' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_159(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'SWA and DOL Info' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("SWA and DOL Info", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifySWAandDOLInfo(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'SWA and DOL Info' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_160(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'SWA and DOL Info' in 'Details / Dates' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("SWA and DOL Info",false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifySWAandDOLInfo(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'SWA and DOL Info' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_161(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'SWA and DOL Info' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();

			portalSetupPage.verifyPreviewButton("SWA and DOL Info");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_162(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Supporting Documents", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifySupportingDocuments(true);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_163(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Supporting Documents",false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifySupportingDocuments(false);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus'in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_164(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Supporting Documents' under 'Show/Hide Case Profile Menus'in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();

			portalSetupPage.verifyPreviewButton("Supporting Documents");
						
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Case Docs CheckList' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_165(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Case Docs CheckList' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Docs CheckList", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifyCaseDocsCheckList(true);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Case Docs CheckList' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_166(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Case Docs CheckList' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Docs CheckList", false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifyCaseDocsCheckList(false);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Case Docs CheckList' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_167(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Case Docs CheckList' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();

			portalSetupPage.verifyPreviewButton("Case Docs CheckList");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Case Documents' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_168(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Case Documents' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Documents", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifyCaseDocuments(true);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Case Documents' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_169(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Case Documents' in 'Supporting Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Documents", false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
			
			fnpCaseProfilePage.verifyCaseDocuments(false);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Case Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_170(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Case Documents' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();

			portalSetupPage.verifyPreviewButton("Case Documents");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_171(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Communication", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyCommunication(true);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_172(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Communication", false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyCommunication(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Communication' under 'Show/Hide Case Profile Menus'in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_173(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Communication' under 'Show/Hide Case Profile Menus'in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
//			portalSetupPage.toggleCheckBox("Communication", true);
			
//			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.verifyPreviewButton("Communication");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
				
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Notes' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_174(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Notes' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Notes", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyCaseNotes(true);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Notes' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_175(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Notes' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Case Notes", false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyCaseNotes(false);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Notes' under 'Communication' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_176(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Notes' under 'Communication' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.verifyPreviewButton("Case Notes");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the checked functionality of 'Emails' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_177(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the checked functionality of 'Emails' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Emails", true);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyEmails(true);
			
			fnpHomePage.clickLogout(true);
				
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verfying the unchecked functionality of 'Emails' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_178(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verfying the unchecked functionality of 'Emails' in 'Communication' under 'Show/Hide Case Profile Menus' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.toggleCheckBox("Emails", false);
			
			portalSetupPage.clickSaveClientPortalSettings();	
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPageTest.loginToFNP(fnp_userName, fnp_password, true);
			
			fnpHomePage.clickCaseLink(caseId);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyEmails(false);
			
			fnpHomePage.clickLogout(false);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
		
		
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of 'Emails' under 'Communication' in 'Client portal menu'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_179(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of 'Emails' under 'Communication' in 'Client portal menu'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickClientPortalMenu();
			
			portalSetupPage.verifyPreviewButton("Emails");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of Corporation User Instructions Content under 'User Instructions' in Portal Content")
	public void PS_TC_205() throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of Corporation User Instructions Content under 'User Instructions' in Portal Content";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.clickUserInstructionTab();
			
			portalSetupPage.verifyPreviewCorporationUserInstruction();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality of Client User Instructions Content under 'User Instructions' in Portal Content")
	public void PS_TC_209() throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality of Client User Instructions Content under 'User Instructions'in Portal Content";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.clickUserInstructionTab();
			
			portalSetupPage.verifyPreviewClientUserInstruction();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality 'Actions' in 'Policy / Guidelines' for Portal Content")
	public void PS_TC_214() throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality 'Actions' in 'Policy / Guidelines' for Portal Content";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.clickPolicyGuidelinesTab();
			
			portalSetupPage.clickAddPolicyAndEnterData(corporationName, corporationName);
			
			portalSetupPage.clickSaveAndPublishPolicy();
			
			portalSetupPage.verifyPreviewPolicyGuidlines(corporationName);
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup : Verifying the Preview Functionality 'Actions' in 'News' for Portal Content")
	public void PS_TC_222() throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup : Verifying the Preview Functionality 'Actions' in 'News' for Portal Content";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.clickNewsTab();
			
			portalSetupPage.clickAddNewsAndEnterData(corporationName, corporationName);
			
			portalSetupPage.clickSaveAndPublish();
			
			portalSetupPage.verifyPreviewNews(corporationName);
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(priority=1, groups = {"portalSetup"}, description="Portal Setup:Verifying the checked Functionality of Same as Corporation Portal Terms of Use checkbox in Corporation Portal Terms of Use Content under Terms of Use")
	public void PS_TC_202() throws Exception
	{
		globalVariables.testCaseDescription = "Portal Setup:Verifying the checked Functionality of Same as Corporation Portal Terms of Use checkbox in Corporation Portal Terms of Use Content under Terms of Use";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.chooseEConsentforCorporation(eConsentPortal);
			
			portalSetupPage.verifySameasCorporationEConsentCheckBox();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the clear button functionality for Choose Terms of Use dropdown in Corporation Portal Terms of Use Content under Terms of Use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_191(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the clear button functionality for Choose Terms of Use dropdown in Corporation Portal Terms of Use Content under Terms of Use";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.chooseEConsentforCorporation(eConsentPortal);
			
			portalSetupPage.verifyClearEConsentButtonCorporation();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the remove button functionality in Corporation Portal Terms of Use Content under Terms of Use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_194(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the remove button functionality in Corporation Portal Terms of Use Content under Terms of Use";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.chooseEConsentforClient(eConsentPortal);
			
			portalSetupPage.verifyRemoveEConsentButtonCorporation();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the clear button functionality for Choose Terms of Use dropdown in Client Portal Terms of Use Content under Terms of Use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_198(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the clear button functionality for Choose Terms of Use dropdown in Client Portal Terms of Use Content under Terms of Use";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.chooseEConsentforClient(eConsentPortal);
			
			portalSetupPage.verifyClearEConsentButtonClient();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the remove button functionality in Client Portal Terms of Use Content under Terms of Use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_201(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the remove button functionality in Client Portal Terms of Use Content under Terms of Use";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickPortalContentTab();
			
			portalSetupPage.chooseEConsentforClient(eConsentPortal);
			
			portalSetupPage.verifyRemoveEConsentButtonClient();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Show step reminder date visible in FNP when Show step reminder date is checked in Client Portal Settings")
	public void PS_TC_180() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Show step reminder date visible in FNP when Show step reminder date is checked in Client Portal Settings";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("step reminder", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage fnpCaseProfile = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfile.verifyShowCaseStepReminder(true);
			
			fnpHomePage.clickLogout(true);
	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Show step reminder date not visible in FNP when Show step reminder date is unchecked in Client Portal Settings")
	public void PS_TC_181() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Show step reminder date not visible in FNP when Show step reminder date is unchecked in Client Portal Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("step reminder", false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage fnpCaseProfile = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfile.verifyShowCaseStepReminder(false);
			
			fnpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Client Portal Settings: Verifying preview button functionality of step reminder date")
	public void PS_TC_182() throws Exception
	{
		globalVariables.testCaseDescription = "Client Portal Settings: Verifying preview button functionality of step reminder date";
			
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("step reminder");
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Show completion date visible in FNP when Show completion date is checked in Client Portal Settings")
	public void PS_TC_183() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Show completion date visible in FNP when Show completion date is checked in Client Portal Settings";
	
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("completion date", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);

			loginPage.fnplogin(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver); 
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage fnpCaseProfile = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfile.verifyStepCompletionDate(true);
			
			fnpHomePage.clickLogout(true);
	
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Show step completion date to the Client in General Settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_184(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Show step completion date to the Client in General Settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Case Steps",true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Show step completion date to the Client", false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseId);
			
			FNP_CaseProfilePage caseProfile = new FNP_CaseProfilePage(driver);
			
			caseProfile.verifyStepCompletionDate(false);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Show step completion date to the Client in General Settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_185(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Show step completion date to the Client in General Settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Show step completion date to the Client");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Allow Client to upload document in the Client in General Settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_186(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Allow Client to upload document in the Client in General Settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox( "Documents", true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Allow Client to upload documents", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage documentsPage = new FNP_DocumentsPage(driver);
			
			documentsPage.verifyDocumentUpload(true);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Allow Client to upload document in the Client in General Settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_187(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Allow Client to upload document in the Client in General Settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.toggleCheckBox("Documents",true);
			
			portalSetupPage.clickSaveClientPortalSettings();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox( "Allow Client to upload documents",false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			login.loginToFNP(fnp_userName, fnp_password, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage documentsPage = new FNP_DocumentsPage(driver);
			
			documentsPage.verifyDocumentUpload(false);
			
			fnpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the preview functionality of Allow Client to upload document in the Client in General Settings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_188(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the preview functionality of Allow Client to upload document in the Client in General Settings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickClientPortalSettingsTab();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Allow Client to upload documents");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify The avaibility of portal Setup")
	public void PS_TC_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify The avaibility of portal Setup";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.verifyPortalSetup();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify the funtionality of Corporation(s) with Default Portal Access")
	public void PS_TC_2() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the funtionality of Corporation(s) with Default Portal Access";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage cm_corporation = new CM_CorporationPage(driver);
			
			cm_corporation.clickAddCorporationButton(true);
			
			cm_corporation.enterDataForCorporationCreation(workbookNameWrite, sheetName, newCorporationName, true);
			
			cm_corporation.clickSaveCorporationButton(true);
			
			cm_corporation.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickDefaultPortalAccess(true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
			portalSetupPage.searchCorporationByName(corpName, true);
			
			portalSetupPage.backToCorporationProfilePage();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify the funtionality of Corporation(s) with Customized Portal Access")
	public void PS_TC_3() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the funtionality of Corporation(s) with Customized Portal Access";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCorporationTab(true);
			
			CM_CorporationPage cm_corporation = new CM_CorporationPage(driver);
			
			cm_corporation.clickAddCorporationButton(true);
			
			cm_corporation.enterDataForCorporationCreation(workbookNameWrite, sheetName, newCorporationName, true);
			
			cm_corporation.clickSaveCorporationButton(true);
			
			cm_corporation.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickDefaultPortalAccess(true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
			portalSetupPage.searchCorporationByName(corpName, true);
			
			portalSetupPage.clickCustomizeCorporationSettings(true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpLCA", false);
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpLCA", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.clickBackToPortalSetup();
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corpName, true);
			
			portalSetupPage.backToCorporationProfilePage();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify the funtionality of Corporation(s) with NO Portal Access")
	public void PS_TC_4() throws Exception
	{
		globalVariables.testCaseDescription = "Verify the funtionality of Corporation(s) with NO Portal Access";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			CM_CorporationPage corporationPage = dashboard.clickCorporationTab(true);
			
			corporationPage.clickAddCorporationButton(true);
			
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);
			
			corporationPage.clickSaveCorporationButton(true);
			
			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickDefaultPortalAccess(true);
			
			portalSetupPage.searchCorporationByName(corpName, true);
			
			portalSetupPage.clickCustomizeCorporationSettings(true);
			
			portalSetupPage.toggleCheckBox("Enable Corporation Portal", false);
			
			portalSetupPage.clickConfirmNoCorporationPortalAccess();
			
			portalSetupPage.clickBackToPortalSetup();
					
			portalSetupPage.verifyNoPortalAccess(corpName);
			
			portalSetupPage.backToCorporationProfilePage();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify View Only funtionality of Corporation/Client/Case Access from Access Right in My Settings")
	public void PS_TC_5() throws Exception
	{
		globalVariables.testCaseDescription = "Verify View Only funtionality of Corporation/Client/Case Access from Access Right in My Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickMySettings();
			
			CM_MySettingsPage settingsPage = new CM_MySettingsPage(driver);
			
			settingsPage.clickAccessRightsTab();
			
			CM_AccessRightsPage accessRightsPage = new CM_AccessRightsPage(driver);
			
			accessRightsPage.clickEdit();
			
			accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("N");
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.verifyEditCorporationAccess();
			
			portalSetupPage.backToViewAccessRights();
			
			accessRightsPage.clickEdit();
			
			accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("Y");
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the 'View and Update'  Functionality of 'Corporation/Client/Case Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_6(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the 'View and Update'  Functionality of 'Corporation/Client/Case Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			CM_AccessRightsPage cm_accessRightsPage = new CM_AccessRightsPage(driver);
			
			CM_MySettingsPage cm_mySettingsPage = new CM_MySettingsPage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
		
			dashboard.clickMySettings();
			
			cm_mySettingsPage.clickAccessRightsTab();
			
			cm_accessRightsPage.clickEdit();
			
			cm_accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("Y");
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickCorporationPortalSettingsTab();
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.verifyAddNewUser();
			
			portalSetupPage.backToViewAccessRights();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		}catch (Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
		
	}	
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the 'ViewOnly'  Functionality of 'Portal Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_7(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the 'ViewOnly'  Functionality of 'Portal Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			CM_AccessRightsPage cm_accessRightsPage = new CM_AccessRightsPage(driver);
			
			CM_MySettingsPage cm_mySettingsPage = new CM_MySettingsPage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
		
			dashboard.clickMySettings();
			
			cm_mySettingsPage.clickAccessRightsTab();
			
			cm_accessRightsPage.clickEdit();
			
			cm_accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("N");
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickCorporationPortalSettingsTab();
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.verifyViewOnlyAccess();
			
			portalSetupPage.backToViewAccessRights();
			
			cm_accessRightsPage.clickEdit();
			
			cm_accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("Y");
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();	
			
		}catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}	
		
	}
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the 'View and Update'  Functionality of 'Portal Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_8(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the 'View and Update'  Functionality of 'Portal Access' from 'Access Rights' of case manager under 'Security and Tools' in 'My Settings'.";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			CM_AccessRightsPage cm_accessRightsPage = new CM_AccessRightsPage(driver);
			
			CM_MySettingsPage cm_mySettingsPage = new CM_MySettingsPage(driver);
			
			loginPageTest.caseManagerlogin(userName, password, true);
		
			dashboard.clickMySettings();
			
			cm_mySettingsPage.clickAccessRightsTab();
			
			cm_accessRightsPage.clickEdit();
			
			cm_accessRightsPage.giveAccessRightstoCorporationOrCaseOrClient("Y");
			
			dashboard.clickPortalSetup(true);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickCorporationPortalSettingsTab();
			
			portalSetupPage.clickRoleManagementTab();
			
			portalSetupPage.verifyAddNewCorporationRole();
			
			portalSetupPage.backToViewAccessRights();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();	
				
			
		}catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}

	}

	
	@Test(priority = 0, groups = {"portalSetup"}, description = "Verify Edit Role Button")
	public void PS_TC_9() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Edit Role Button";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickRoleManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			roleForUser = strDate+roleName;
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyEditRoleButton(strDate+roleName);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Duplicate Role Button in the Role Management tab")
	public void PS_TC_10() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Duplicate Role Button in the Role Management tab";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickRoleManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.createDuplicateCorporationRole(strDate+roleName);
			
			portalSetupPage.verifyIfRoleAdded("Duplicate"+strDate+roleName, true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Delete Role in Role Management under Corporation Portal Settings with no user created")
	public void PS_TC_11() throws Exception
	{
		globalVariables.testCaseDescription = "Delete Role in Role management under Corporation Portal Settings with no user created";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();

			portalSetupPage.searchCorporationByName(corporationName, true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyIfRoleAdded(strDate+roleName, true);
			
			portalSetupPage.deleteCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyIfRoleDeleted(strDate+roleName, true); 
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Delete Role in Role Management under User in Corporation Portal Settings,users are available under that role")
	public void PS_TC_12() throws Exception
	{
		globalVariables.testCaseDescription = "Delete Role in Role management under Corporation Portal Settings,users are available under that role";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
		
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyIfRoleAdded(strDate+roleName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.addNewUser(strDate+roleName, globalVariables.firstName, globalVariables.lastName, globalVariables.sendEmailID, true);
			
			portalSetupPage.clickRoleManagementTab(true);
			
			String userName = globalVariables.firstName + " " + globalVariables.lastName ;
			
			portalSetupPage.deleteCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyPopUpOnDeletion();
			
			portalSetupPage.clickUserManagementTab(true);
			
//			portalSetupPage.searchUserByName(userName, true);
			
			portalSetupPage.searchUserByRole(strDate+roleName, true);
			
			portalSetupPage.changeUserRoleToDefault(strDate+roleName);
			
			portalSetupPage.clickRoleManagementTab(true);
			
			portalSetupPage.deleteCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyIfRoleDeleted(strDate+roleName, true);
			
			portalSetupPage.backToZoomboard();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify LCA visible in HRP when corporation LCA is checked in Role Access management")
	public void PS_TC_13() throws Exception
	{
		globalVariables.testCaseDescription = "Verify LCA visible in HRP when corporation LCA is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpLCA", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);

			HrpHomePage hrpHomePage = loginPage.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.verifyLCATab(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify LCA is not visible in HRP when corporation LCA is unchecked in Role Access management")
	public void PS_TC_14() throws Exception
	{
		globalVariables.testCaseDescription = "Verify LCA is not visible in HRP when corporation LCA is unchecked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpLCA", false);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage = loginPage.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.verifyLCATab(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify CustomData visible in HRP when corporation CustomData is checked in Role Access management")
	public void PS_TC_15() throws Exception
	{
		globalVariables.testCaseDescription = "Verify CustomData visible in HRP when corporation CustomData is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpCorpCustomData", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.verifyCustomDataTab(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify CustomData not visible in HRP when corporation CustomData is unchecked in Role Access management")
	public void PS_TC_16() throws Exception
	{
		globalVariables.testCaseDescription = "Verify CustomData not visible in HRP when corporation CustomData is unchecked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpCorpCustomData", false);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.verifyCustomDataTab(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Digital Documents visible in HRP when corporation Digital Documents is checked in Role Access management")
	public void PS_TC_17() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Digital Documents visible in HRP when corporation Digital Documents is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpDigitalDocuments", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickDigitalDocuments();
			
			hrpCorporationPage.verifyDigitalDocumentsTab(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Digital Documents not visible in HRP when corporation Digital Documents is unchecked in Role Access management")
	public void PS_TC_18() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Digital Documents not visible in HRP when corporation Digital Documents is unchecked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpDigitalDocuments", false);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickDigitalDocuments();
			
			hrpCorporationPage.verifyDigitalDocumentsTab(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Emails visible in HRP when corporation Email is checked in Role Access management")
	public void PS_TC_18_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Emails visible in HRP when corporation Emails is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpEmails",true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickEmailsTab();
			
			hrpCorporationPage.verifyEmailsAccess(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Emails not visible in HRP when corporation Emails is unchecked in Role Access management")
	public void PS_TC_18_2() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Emails not visible in HRP when corporation Emails is unchecked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpEmails", false);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickEmailsTab();
			
			hrpCorporationPage.verifyEmailsAccess(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Case Requests visible in HRP when corporation Case Requset is checked in Role Access management")
	public void PS_TC_19() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Case Requset visible in HRP when corporation Case Requset is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpCaseRequest", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.verifyCaseRequestTab(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Case Requset not visible in HRP when corporation Case Requset is unchecked in Role Access management")
	public void PS_TC_19_1() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Case Requset not visible in HRP when corporation Case Requset is unchecked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpCaseRequest", false);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.verifyCaseRequestTab(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify LCA visible in HRP when corporation Client Profile LCA is checked in Role Access management")
	public void PS_TC_20() throws Exception
	{
		globalVariables.testCaseDescription = "Verify LCA visible in HRP when corporation Client Profile LCA is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(true, "BNFLCA");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.clickOnClientName(hrpClientName);
			
			hrpClientPage.verifyLCATab(true);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify LCA visible in HRP when corporation Client Profile LCA is checked in Role Access management")
	public void PS_TC_21() throws Exception
	{
		globalVariables.testCaseDescription = "Verify LCA visible in HRP when corporation Client Profile LCA is checked in Role Access management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(false, "BNFLCA");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			LoginPageTest loginHRP = new LoginPageTest(driver, webSite);

			HrpHomePage hrpHomePage = loginHRP.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.clickOnClientName(hrpClientName);
			
			hrpClientPage.verifyLCATab(false);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	

	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Custom Data�in Client Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_22(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Custom Data�in Client Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

		LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(true,"BNFCustomData");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.verifyCustomDataClientProfile(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Custom Data�in Client Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_23(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Custom Data�in Client Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(false,"BNFCustomData");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.verifyCustomDataClientProfile(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Emails�in Client Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_24(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Emails�in Client Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

		LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(true, "BNFEmails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickEmailsTab();
			
			hrpClient.verifyEmailClientProfile(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Emails�in Client Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_25(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Emails�in Client Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxClientProfileAccess(false, "BNFEmails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickEmailsTab();
			
			hrpClient.verifyEmailClientProfile(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Emails�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_26(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Emails�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

		LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(true, "CaseEmails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickEmailsTab();
			
			hrpClient.verifyEmailCaseProfile(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Emails�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_27(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Emails�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(false,"CaseEmails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickEmailsTab();
			
			hrpClient.verifyEmailCaseProfile(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Receipt details�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_28(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Receipt details�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

		LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(true, "CaseReceiptDetails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickDetailsDatesTab();
			
			hrpClient.verifyReceiptDetailsCaseProfile(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Receipt details�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_29(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Receipt details�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(false, "CaseReceiptDetails");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickDetailsDatesTab();
			
			hrpClient.verifyReceiptDetailsCaseProfile(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the checked functionality of Custom data�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_30(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the checked functionality of Custom data�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

		LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(true, "CaseCustomData");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickDetailsDatesTab();
			
			hrpClient.verifyCustomDataCaseProfile(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the unchecked functionality of Custom data�in Case Profile Access under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_31(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of Custom data�in Case Profile Access under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCaseProfileAccess(false, "CaseCustomData");
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
					
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
			
			hrpClient.clickOnClientName(hrpClientName);
			
			hrpClient.clickCaseLink(caseId);
			
			hrpClient.clickDetailsDatesTab();
			
			hrpClient.verifyCustomDataCaseProfile(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the functionality of Add new Corporation Role under Role  Management", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_32(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Verifying the functionality of Add new Corporation Role under Role  Management";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifyAddCorporationRole(strDate+roleName, true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verify Search Role functionality")
	public void PS_TC_34() throws Exception
	{
		globalVariables.testCaseDescription = "Verify Search Role functionality";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickRoleManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addCorporationRole(strDate+roleName, true);
			
			portalSetupPage.verifySearchRoleFunctionality(strDate+roleName);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the functionality of Add New User in User management")
	public void PS_TC_35() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of Add New User in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the functionality of View Users by Role Drop down in User management")
	public void PS_TC_36() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of View Users by Role Drop down in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.verifyViewUserByRoleInDropdown(userName,roleForUser);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the functionality of View Users by Role SearchBox in User management")
	public void PS_TC_37() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of View Users by Role SearchBox in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.verifySearchUserByUserName(userName);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the functionality of View Users by Role in SearchBox in User management")
	public void PS_TC_38() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of View Users by Role SearchBox in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.verifySearchUserByRole(userName, roleForUser);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the Edit functionality of Users in User management")
	public void PS_TC_39() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the Edit functionality of Users in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			String lastDate = formatter.format(date);
			
			portalSetupPage.editUserDetails(userName, lastDate);
			
			userName = globalVariables.firstName + " " + lastDate;
			
			portalSetupPage.verifyIfUserAdded(userName, true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the Delete functionality of Users in User management")
	public void PS_TC_40() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the Delete functionality of Users in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.deleteUser(userName);
			
			portalSetupPage.verifyUserDeleted(userName);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying the Restore Deleted users functionality of Users in User management")
	public void PS_TC_41() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the Restore Deleted Users functionality of Users in User management";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			portalSetupPage.addNewUser(roleForUser, globalVariables.firstName, strDate, globalVariables.sendEmailID, true);
			
			String userName = globalVariables.firstName + " " + strDate ;
			
			portalSetupPage.verifyIfUserAdded(userName,true);
			
			portalSetupPage.deleteUser(userName);
			
			portalSetupPage.verifyUserDeleted(userName);
			
			portalSetupPage.restoreDeletedUser(userName);
			
			portalSetupPage.verifyIfUserAdded(userName, true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the functionality of 'Invite User Again' for a User under 'More options' in 'Actions'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_42(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of 'Invite User Again' for a User under 'More options' in 'Actions'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.searchUserByName("New User", true);
			
			portalSetupPage.clickQuickActionsIconForUserManagement(true);
			
			portalSetupPage.clickInviteUserAgain(true);
			
			portalSetupPage.clickSendEmail(true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the functionality of 'Reset user' for a User under 'More options' in 'Actions'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_43(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of 'Reset user' for a User under 'More options' in 'Actions'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.searchUserByName("New User", true);
			
			portalSetupPage.clickQuickActionsIconForUserManagement(true);
			
			portalSetupPage.resetUser(true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the functionality of 'eConsent History' for a User under 'More options' in 'Actions'",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_44(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the functionality of 'eConsent History' for a User under 'More options' in 'Actions'";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.clickQuickActionsIconForUserManagement(true);
			
			portalSetupPage.verifyEconsentHistory(true);
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the checked functionality of 'Allow Corporation users to upload documents'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_45(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the checked functionality of 'Allow Corporation users to upload documents'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpDigitalDocuments", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Allow Corporation users to upload documents", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrp_corporation = new HRP_CorporationPage(driver);
			
			hrp_corporation.clickDigitalDocuments();
			
			hrp_corporation.verifyDocumentUpload(true);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the unchecked functionality of 'Allow Corporation users to upload documents'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_46(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of 'Allow Corporation users to upload documents'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.editCorporationRoleManagement();
			
			portalSetupPage.toggleCheckBoxCorporationProfileAccess("CorpDigitalDocuments", true);
			
			portalSetupPage.saveCorporationRoleManagement();
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Allow Corporation users to upload documents", false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrp_corporation = new HRP_CorporationPage(driver);
			
			hrp_corporation.clickDigitalDocuments();
			
			hrp_corporation.verifyDocumentUpload(false);
			
			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the preview functionality of 'Allow Corporation users to upload documents'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_47(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the preview functionality of 'Allow Corporation users to upload documents'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Allow Corporation users to upload documents");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the checked functionality of 'Show step reminder date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_48(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the checked functionality of 'Show step reminder date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Show step reminder date to Corporation", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();	
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrp_clientPage = new HRP_ClientPage(driver);
			
			hrp_clientPage.clickOnClientName(hrpClientName);
			
			hrp_clientPage.clickCaseLink(caseId);
			
			hrp_clientPage.clickCaseStepsTab();
			
			hrp_clientPage.verifyEstimatedDate(true);

			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the unchecked functionality of 'Show step reminder date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_49(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of 'Show step reminder date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Show step reminder date to Corporation", false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();	
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrp_clientPage = new HRP_ClientPage(driver);
			
			hrp_clientPage.clickOnClientName(hrpClientName);
			
			hrp_clientPage.clickCaseLink(caseId);
			
			hrp_clientPage.clickCaseStepsTab();
			
			hrp_clientPage.verifyEstimatedDate(false);

			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the preview functionality of 'Show step reminder date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_50(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the preview functionality of 'Show step reminder date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Show step reminder date to Corporation");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the checked functionality of 'Show step completion date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_51(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the checked functionality of 'Show step completion date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Show step completion date to Corporation", true);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();	
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrp_clientPage = new HRP_ClientPage(driver);
			
			hrp_clientPage.clickOnClientName(hrpClientName);
			
			hrp_clientPage.clickCaseLink(caseId);
			
			hrp_clientPage.clickCaseStepsTab();
			
			hrp_clientPage.verifyCompletedDate(true);

			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the unchecked functionality of 'Show step completion date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_52(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the unchecked functionality of 'Show step completion date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.toggleCheckBox("Show step completion date to Corporation", false);
			
			portalSetupPage.clickSaveGeneralSettingsClientPortal();	
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			HrpHomePage hrpHomePage =  new HrpHomePage(driver);
			
			login.hrpLogin(hrp_userName, hrp_password, true);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrp_clientPage = new HRP_ClientPage(driver);
			
			hrp_clientPage.clickOnClientName(hrpClientName);
			
			hrp_clientPage.clickCaseLink(caseId);
			
			hrp_clientPage.clickCaseStepsTab();
			
			hrp_clientPage.verifyCompletedDate(false);

			hrpHomePage.clickLogout(true);
		
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(priority=1, groups = {"portalSetup"}, description="Verifying the preview functionality of 'Show step completion date to Corporation'  under General Settings",dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void PS_TC_53(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verifying the preview functionality of 'Show step completion date to Corporation'  under General Settings";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(true);
			
			CM_DashboardPage dashboard = new CM_DashboardPage(driver);
			
			dashboard.clickPortalSetup(true);

			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			portalSetupPage.verifyPreviewButton("Show step completion date to Corporation");
			
			portalSetupPage.backToZoomboard();
		
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(priority=1, groups = {"portalSetup"}, description = "Verifying upload image in Corporation General Settings")
	public void PS_TC_54() throws Exception
	{
		globalVariables.testCaseDescription = "Verifying upload image in Corporation General Settings";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(corporationName, true);
			
			portalSetupPage.clickGeneralSettingsTab();
			
			String filePath = System.getProperty("user.dir")+"//src//main//resources//Sample.jpg";
	        String os = System.getProperty("os.name");
	        if(os.contains("Linux"))
	        {
	            filePath = filePath.replace("\\", "/");
	            Log.message("Final File Path "+filePath);
	        }
			
			File file = new File(filePath);
			String abpath = file.getAbsolutePath();
			
			portalSetupPage.uploadCorporationLogo(abpath);
			
			portalSetupPage.verifyuploadCorporationLogo();
			
			portalSetupPage.backToZoomboard();
		
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


