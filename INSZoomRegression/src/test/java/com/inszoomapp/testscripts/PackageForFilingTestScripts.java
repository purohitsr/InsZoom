package com.inszoomapp.testscripts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CasePackageForFiling;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_Letters_MSWordPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

import org.testng.SkipException;

@Listeners(EmailReport.class)
public class PackageForFilingTestScripts {

	String webSite = null;
	String env = null;
	String browserName = null;
	
	String userName = null;
	String password = null;
	
	RemoteWebDriver driver;
	
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	
	public static List<String> corporationsUnderHQ = new ArrayList<String>();
	
	AppDataBase data ;
		
	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

		sheetName = (System.getProperty("sheetName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("sheetName")).toLowerCase();
		
		globalVariables.browserUsedForExecution = browserName;
		String os = System.getProperty("os.name");
		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
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
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_QA.xls";
					}
					
					/*workbookName = "testdata\\data\\Regression_QA.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_QA.xls";*/
					sheetName = "Inszoom";
					

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
					sheetName = "Inszoom";

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
	
	@Test(description = "PreRequisite pdf document to the Case for Package for filing")
	public void CM_PFF_TC_PRE() throws Exception 
	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		String[] docChecklistName = {globalVariables.docChecklistName};
		String[] docChecklistNameKey = {"QA_A_Case_addedBasicDesc"};

		globalVariables.testCaseDescription = "Case: Add Docs Checklist and upload a document to the created docs checklist";
		final WebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistName);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistName);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
			
			caseDocChecklistPage.uploadDocument(globalVariables.docChecklistName, globalVariables.filePathForPFF, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(globalVariables.docChecklistName, globalVariables.fileNameForPFF);
			
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
	
	@Test(description = "Verify Select functionality for forms In Package for Filing")
	public void CM_PFF_TC_4() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Select functionality for forms In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectForms();
			
			packageForFilingPage.verifySelectForms();
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Select functionality for Case Letter In Package for Filing")
	public void CM_PFF_TC_5() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Select functionality for Case Letters In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectCaseLetters();
			
			packageForFilingPage.verifySelectLetters();
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Select functionality for documents In Package for Filing")
	public void CM_PFF_TC_6() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Select functionality for documents In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectSupportingDocs();
			
			packageForFilingPage.verifySelectDocuments();
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Select functionality for forms,letters,documents In Package for Filing")
	public void CM_PFF_TC_7() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Select functionality for forms,letters,documents in Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectSupportingDocs();
			packageForFilingPage.clickIconSelectForms();
			packageForFilingPage.clickIconSelectCaseLetters();
			
			packageForFilingPage.verifyAllSelectArePresent();
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify Assemble functionality for forms In Package for Filing")
	public void CM_PFF_TC_14() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Assemble functionality for forms In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectForms();
			
			packageForFilingPage.verifySelectForms();
			
			packageForFilingPage.createPackage();
			
			packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Assemble functionality for Case letters In Package for Filing")
	public void CM_PFF_TC_15() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Assemble functionality for Case Letters In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectCaseLetters();
			
			packageForFilingPage.verifySelectLetters();
			
			packageForFilingPage.createPackage();
			
			packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Assemble functionality for documents In Package for Filing")
	public void CM_PFF_TC_16() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Assemble functionality for documents In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectSupportingDocs();
			
			packageForFilingPage.verifySelectDocuments();
			
			packageForFilingPage.createPackage();
			
			packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Assemble functionality for forms,letters,documents In Package for Filing")
	public void CM_PFF_TC_17() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Assemble functionality for forms,letters,documents In Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectSupportingDocs();
			packageForFilingPage.clickIconSelectCaseLetters();
			packageForFilingPage.clickIconSelectForms();
			
			packageForFilingPage.verifyAllSelectArePresent();
			
			packageForFilingPage.createPackage();
			
			packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify download functionality of the package inside package for filing")
	public void CM_PFF_TC_18() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify download functionality for package in Package for Filing";
		
		final RemoteWebDriver driver = (RemoteWebDriver) WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.clickDownloadPackageIcon(workbookName, sheetName, driver);
			
			//packageForFilingPage.verifyDownloadPackage(workbookName, sheetName);
				
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Recreate functionality of the package inside package for filing")
	public void CM_PFF_TC_19() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Recreate functionality for package in Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.clickMenuDropDownIcon(workbookName, sheetName);
			
			packageForFilingPage.clickRecreatePackageLink(workbookName, sheetName);
			
			packageForFilingPage.RecreatePackage("Automation ", workbookNameWrite, sheetName);
			
			packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Recreate Download functionality of the package inside package for filing")
	public void CM_PFF_TC_1() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Recreate Download functionality for package in Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
			
			caseProfilePage.clickFormsTab();
			
			caseFormPage.getListOFForms();
			
			caseFormPage.backToCaseProfileTab();
			
			caseFormPage.clickCaseProfileTab();
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.verifyAllFormsPresent();
			
			packageForFilingPage.backToCaseProfilePage();
	
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Delete functionality of the package inside package for filing")
	public void CM_PFF_TC_20() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify Delete functionality for package in Package for Filing";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
			
			packageForFilingPage.clickIconSelectSupportingDocs();
			
			packageForFilingPage.verifySelectDocuments();
			
			packageForFilingPage.createPackage();
			
			packageForFilingPage.clickPrintLogDropDown();
			
			packageForFilingPage.deletePackage();
			
			packageForFilingPage.verifyDeletePackage();
			
			packageForFilingPage.backToCaseProfilePage();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the functionality of search forms in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_11() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the functionality of search forms in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchForms(globalVariables.formNamePackageForFiling, true);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the negative functionality of search forms in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_11_1() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the negative functionality of search forms in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchForms("Form" + RandomStringUtils.randomAlphanumeric(5).toString(), false);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the functionality of search letters in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_12() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the functionality of search letters in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchLetters(globalVariables.letterMSWordTemplateName, true);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the negative functionality of search letters in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_12_1() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the negative functionality of search letters in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchLetters("Letter" + RandomStringUtils.randomAlphanumeric(5).toString(), false);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the functionality of search supporting documents in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_13() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the functionality of search supporting documents in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchSupportingDocuments(globalVariables.documentNamePackageForFiling, true);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	

	@Test(description = "Verify the negative functionality of search supporting documents in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_13_1() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the negative functionality of search supporting documents in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.verifySearchSupportingDocuments("Supporting Document" + RandomStringUtils.randomAlphanumeric(5).toString(), false);
	
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify the functionality of view Document list in Package for Filing for CM Case profile Page")
	public void CM_PFF_TC_21() throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
		globalVariables.testCaseDescription = "Verify the functionality of delete package in Package for Filing for CM Case profile Page";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(userName, password, true);
			
			dashboard.clickCaseTab(true);
			
			CM_CaseListPage caseList = new CM_CaseListPage(driver);
			
			caseList.clickOnCaseId(caseCreated, true);
			
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickPackageForFilingButton();
			
			CM_CasePackageForFiling packageFiling = new CM_CasePackageForFiling(driver);
			
			packageFiling.clickIconSelectForms();
			
			packageFiling.createPackage();
			
			packageFiling.clickPrintLogDropDown();
			
			packageFiling.clickMenuDropDownIcon(workbookName, sheetName);
			
			packageFiling.viewDocumentList(workbookName, sheetName);
			
			packageFiling.verifyViewDocumentList(workbookName, sheetName);
			
			packageFiling.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify remove icon functionality for Supporting Documents")
    public void CM_PFF_TC_10() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
    	
	      globalVariables.testCaseDescription = "Verify remove icon functionality for Supporting Documents";
	      
	      final WebDriver driver = WebDriverFactory.get(browserName);
	      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	
		
	      try
	      {
	          LoginPageTest login = new LoginPageTest(driver, webSite);
	          
	          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
	
	          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
	          
	          caseListPage.clickOnCaseId(caseCreated, true);
	          
	          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	          
	          caseProfilePage.clickPackageForFilingButton();
	          
	          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
	          
	          packageForFilingPage.clickIconSelectSupportingDocs();
	          
	          packageForFilingPage.verifySelectDocuments();
	          
	          packageForFilingPage.clickRemoveSupportingDocs();
	          
	          packageForFilingPage.verifyRemoveSupportingDocs();
	          
	          packageForFilingPage.backToCaseProfilePage();
	          
	          caseManagerHomePage.clickLogout(true);
	          
	          Log.testCaseResult();     	         
	    	  
	      }catch(Exception e)
	      {
	    	  Log.exception(e, driver);
	      }finally
	      {
	    	 Log.endTestCase();
	    	 driver.quit(); 
	      }	
	}		
	
	
	@Test(description = "Verify remove icon functionality for letters")
	public void CM_PFF_TC_9() throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
	    globalVariables.testCaseDescription = "Verify remove icon functionality for letters";
	      
	    final WebDriver driver = WebDriverFactory.get(browserName);
	    driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	    
	    try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickIconSelectCaseLetters();
          
          packageForFilingPage.verifySelectLetters();
          
          packageForFilingPage.clickRemoveLetters();
          
          packageForFilingPage.verifyRemoveLetters();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
	}	
	
	
	@Test(description = "Verify remove icon functionality for forms")
    public void CM_PFF_TC_8() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
	
      globalVariables.testCaseDescription = "Verify remove icon functionality for forms";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickIconSelectForms();
          
          packageForFilingPage.verifySelectForms();
          
          packageForFilingPage.clickRemoveForms();
          
          packageForFilingPage.verifyRemoveForms();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }	
		
	
	@Test(description = "Verify Added case letters are present inside letters")
    public void CM_PFF_TC_2() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
        globalVariables.testCaseDescription = "Verify Added case letters are present inside letters";
          
        final WebDriver driver = WebDriverFactory.get(browserName);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.verifyCaseLettersPresent();
          
          packageForFilingPage.backToCaseProfilePage();

          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
    }
	
	
	@Test(description = "Verify Added documents letters are present inside supporting Documents")
    public void CM_PFF_TC_3() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Added case letters are present inside supporting docs";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.verifyDocumentPresent();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
    }
	
	
	@Test(groups = {"corporation"}, description = "Corp: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_PFF_TC_CorpDocumentUpload(String browser) throws Exception 
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
			
			clientDocumentsPage.uploadDocument(globalVariables.filePathForPFF);
			
			clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileNameForPFF);
			
//			clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName);
//			
//			clientDocumentsPage.verifyIfDocumentAccessRightsChanged(globalVariables.fileName);
			
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

	
	@Test(groups={"Priority1", "client"}, description = "Client: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_PFF_TC_ClientDocUpload(String browser) throws Exception 
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
                
                clientDocumentsPage.uploadDocument(globalVariables.filePathForPFF);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileNameForPFF);
                
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileNameForPFF, "client");
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileNameForPFF, "corporation");
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileNameForPFF, "vendor");
                
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
	
	
	@Test(description = "Verify Add All Forms Icon Functionality")
    public void CM_PFF_TC_22() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Forms Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.verifyAddAllForms();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
     	 
    	  
      }	
    }
	
	
	@Test(description = "Verify Add All Letters Icon Functionality")
    public void CM_PFF_TC_23() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Letters Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllLettersLink();
          
          packageForFilingPage.verifyAddAllLetters();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
    }
	
	
	@Test(description = "Verify Add All Case Documents Icon Functionality")
    public void CM_PFF_TC_24() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Case Documents Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.verifyAddAllCaseDocuments();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
	
	
	@Test(description = "Verify Add All Forms Icon Functionality")
    public void CM_PFF_TC_25() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Forms Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.verifyAddAllForms();
          
          packageForFilingPage.createPackage();
          
          packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
		  packageForFilingPage.clickPrintLogDropDown();
			
		  packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
    }
	
	
	@Test(description = "Verify Add All Letters Icon Functionality")
    public void CM_PFF_TC_26() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Letters Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllLettersLink();
          
          packageForFilingPage.verifyAddAllLetters();
          
          packageForFilingPage.createPackage();
          
          packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
		  packageForFilingPage.clickPrintLogDropDown();
			
		  packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {

     	 Log.endTestCase();
     	 driver.quit(); 
    	  
      }	
    }
	
	
	@Test(description = "Verify Add All Case Documents Icon Functionality")
    public void CM_PFF_TC_27() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add All Case Documents Icon Functionality";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.verifyAddAllCaseDocuments();
          
          packageForFilingPage.createPackage();
          
          packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
		  packageForFilingPage.clickPrintLogDropDown();
			
		  packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
	
	
	@Test(description = "Verify Add Blank Page to the document List")
    public void CM_PFF_TC_28() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add Blank Page to the document List";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.clickAddBlankPageButton(globalVariables.blankPageName);
          
          packageForFilingPage.verifyAddBlackPageButton(globalVariables.blankPageName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
	
	
	@Test(description = "Verify Add Letter Template from KB to the document List")
    public void CM_PFF_TC_29() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add Letter Template from KB to the document List";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.clickAddLetterTemplate();
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
	
	
	@Test(description = "Verify Add Blank Page to the document List")
    public void CM_PFF_TC_30() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add Blank Page to the document List";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.clickAddBlankPageButton(globalVariables.blankPageName);
          
          packageForFilingPage.verifyAddBlackPageButton(globalVariables.blankPageName);
          
          packageForFilingPage.createPackage();
          
          packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
		  packageForFilingPage.clickPrintLogDropDown();
			
		  packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
	
	
	@Test(description = "Verify Add Letter Template from KB to the document List")
    public void CM_PFF_TC_31() throws Exception 
    {
		ReadWriteExcel readExcel = new ReadWriteExcel();
    	String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
    	
      globalVariables.testCaseDescription = "Verify Add Letter Template from KB to the document List";
      
      final WebDriver driver = WebDriverFactory.get(browserName);
      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	
      try
      {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
          
          caseProfilePage.clickPackageForFilingButton();
          
          CM_CasePackageForFiling packageForFilingPage = new CM_CasePackageForFiling(driver);
          
          packageForFilingPage.clickAddAllCaseDocumentsLink();
          
          packageForFilingPage.clickAddAllFormsLink();
          
          packageForFilingPage.clickAddLetterTemplate();
          
          packageForFilingPage.createPackage();
          
          packageForFilingPage.savePackageNameToExcel(workbookNameWrite, sheetName);
			
		  packageForFilingPage.clickPrintLogDropDown();
			
		  packageForFilingPage.verifyPackageCreated(workbookName, sheetName);
          
          packageForFilingPage.backToCaseProfilePage();
          
          caseManagerHomePage.clickLogout(true);
          
          Log.testCaseResult();     	         
    	  
      }catch(Exception e)
      {
    	  Log.exception(e, driver);
    	  
      }finally
      {
     	 Log.endTestCase();
     	 driver.quit();   
      }	
    }
}
