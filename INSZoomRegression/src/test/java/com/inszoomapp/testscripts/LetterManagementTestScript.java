package com.inszoomapp.testscripts;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
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
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseLetterHTMLPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientLetterHTMLPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_CorpLetterHTMLPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_Letters_MSWordPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class LetterManagementTestScript extends BaseTest
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
	
	
	
	@Test(groups = {"letterTemplate"}, description = "Add Letter Template in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void addLetterTemplateInKB(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Add Letter Template in KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickLetterTemplateMSWordTab();
			
			knowledgeBasePage.addMSLetterTemplate(globalVariables.newLetterTemplateName, "Template Description", globalVariables.letterTemplateFilePath);
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {"letterTemplate"}, description = "Add HTML Letter Template in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void addHTMLLetterTemplateInKB(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		

		globalVariables.testCaseDescription = "Add HTML Letter Template in KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickLetterTemplateHTMLTab();
			
			knowledgeBasePage.addLetterTemplateHTML(globalVariables.letterHTMLTemplateName, globalVariables.letterHTMLTemplateDescription);
			
			
//			String directory = System.getProperty("user.dir");
//			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//			
//			writeExcel.setCellData("letterHTMLTemplateName", sheetName, "Value", globalVariables.letterHTMLTemplateName);
//			writeExcel.setCellData("letterHTMLTemplateDescription", sheetName, "Value", globalVariables.letterHTMLTemplateDescription);
			
			caseManagerDashboardPage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {"letterTemplate"}, description = "Delete Letter Template in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"addLetterTemplateInKB","Case_LetterMS_25","Client_LetterMS_25","Corp_LetterMS_25"})
	public void deleteLetterTemplateInKB(String browser) throws Exception {

		globalVariables.testCaseDescription = "Delete Letter Template in KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickLetterTemplateMSWordTab();
			
			knowledgeBasePage.deleteMSLetterTemplate(globalVariables.newLetterTemplateName);
			
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
	
	
	@Test(groups = {"letterTemplate"}, description = "Prerequisite For Copy Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void prerequisiteForCopyCase(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prerequisite For Copy Case";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_A_CaseOpen");
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.clickOnComposeButton();
			
			caseLetterHTMLPage.composeHTMLLetter(globalVariables.letterHTMLName, globalVariables.letterHTMLDescription);
			
			caseLetterHTMLPage.verifyIfHTMLLetterPresent(globalVariables.letterHTMLName);
			
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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Letter(MS WORD)-Template from server(KB Templates) option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_1(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_2(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_35")
	public void Client_LetterMS_3(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_3")
	public void Client_LetterMS_4(String browser) throws Exception {

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
	public void Client_LetterMS_5(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_5")
	public void Client_LetterMS_6(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_6")
	public void Client_LetterMS_7(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_7")
	public void Client_LetterMS_8(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify Client Details header", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify Client Details header";

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

			lettersMSWordPage.verifyClientAndCaseDetailsInClientLevel(clientName, globalVariables.clientEmailID, corpName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify Available Template search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify Available Template search box";

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

			lettersMSWordPage.verifyAvailableTemplateSearchBox(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify Used text comes up after using the template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify Used text comes up after using the template";

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

			lettersMSWordPage.verifyUsedText(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify quick action dropdown list", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify quick action dropdown list";

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

			lettersMSWordPage.verifyquickActionDropDownOptions(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify Search Letter search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//,dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify Search Letter search box";

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

			lettersMSWordPage.verifyLetterSearchBox(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify Delete all functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = {"useAllTemplatesClient","Client_LetterMS_4"})
	public void Client_LetterMS_17(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify Delete all functionality";

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

			lettersMSWordPage.deleteAllLetters();

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Click on Letter template and verify description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//,dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_22(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Click on Letter template and verify description";

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

			lettersMSWordPage.verifyLetterTemplateDescription(globalVariables.letterMSWordTemplateName, globalVariables.docName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify print functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_23(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify print functionality";

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

			lettersMSWordPage.verifyPrintFunctionality(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Add template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addLetterTemplateInKB")
	public void Client_LetterMS_25(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Add template in KB and verify on Letter template page";

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

			lettersMSWordPage.verifyTemplatedAddingInKB(globalVariables.newLetterTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Delete template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "deleteLetterTemplateInKB")
	public void Client_LetterMS_26(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Delete template in KB and verify on Letter template page";

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

			lettersMSWordPage.verifyIfKBTemplateDeleted(globalVariables.newLetterTemplateName);

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
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify status dropdown", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_27(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify status dropdown";

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

			lettersMSWordPage.verifyStatusOptions(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Edit status as In Draft ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_28(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Edit status as In Draft ";

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

			lettersMSWordPage.editAndVerifyStatusInDraft(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Edit status as Send for signature", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_29(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Edit status as Send for signature";

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

			lettersMSWordPage.editAndVerifyStatusSendForSignature(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Edit status as Ready for printing", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_30(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Edit status as Ready for printing";

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

			lettersMSWordPage.editAndVerifyStatusReadyForPrinting(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)Edit status as not started status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_31(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Edit status as not started status";

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

			lettersMSWordPage.editAndVerifyStatusNotStarted(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify ascending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_32(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify ascending order of letter templates";

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

			lettersMSWordPage.verifyAscendingOrderOfLetterTemplates();

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify descending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_33(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify descending order of letter templates";

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

			lettersMSWordPage.verifyDescendingOrderOfLetterTemplates();

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
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD) Verify breadcrumbs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterMS_34(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD) Verify breadcrumbs";

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

			lettersMSWordPage.verifyBreadCrumbsInClientLevel();

			Utils.waitForAllWindowsToLoad(2, driver);
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
	
	

	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(MS WORD)-Verify letter template in TO-Do Module", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterMS_1")
	public void Client_LetterMS_35(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(MS WORD)-Verify letter template in TO-Do Module";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
 			
             clientListPage.clickToDoTab();
 			
 			 CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
 			
 			 clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterMSWordTemplateName);
 			
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Compose letter template using template created in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addHTMLLetterTemplateInKB")
	public void Client_LetterHTML_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Compose letter template using template created in KB";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");
//		String letterHTMLTemplateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateDescription");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientLetterHTMLPage = new CM_ClientLetterHTMLPage(driver);
             
             clientLetterHTMLPage.clickOnComposeClientLetterButton();
             
             clientLetterHTMLPage.composeUsingLetterTemplateClient(globalVariables.letterHTMLTemplateName, globalVariables.letterHTMLTemplateDescription);
             
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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Verify Compose letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_LetterHTML_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Verify Compose letter";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientLetterHTMLPage = new CM_ClientLetterHTMLPage(driver);
             
             clientLetterHTMLPage.clickOnComposeClientLetterButton();
             
             clientLetterHTMLPage.composeLetterClient(globalVariables.letterHTMLName, globalVariables.letterHTMLDescription);
 			
//             String directory = System.getProperty("user.dir");
// 			 ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//             
// 			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.letterHTMLName);
// 			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.letterHTMLDescription);
             
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Verify edit letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterHTML_5")
	public void Client_LetterHTML_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Verify edit letter";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		//String letterTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
		

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientLetterHTMLPage = new CM_ClientLetterHTMLPage(driver);
             
             clientLetterHTMLPage.clickClientEditLetter(globalVariables.letterHTMLTemplateName);
             
             clientLetterHTMLPage.editClientLetter(globalVariables.editLetterHTMLName, globalVariables.editLetterHTMLDescription);
 			
//             String directory = System.getProperty("user.dir");
// 			 ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//             
// 			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.editLetterHTMLName);
// 			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.editLetterHTMLDescription);
             
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

	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Verify letter print preview", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterHTML_2")
	public void Client_LetterHTML_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Verify letter print preview";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String templateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLDescription");
		

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientLetterHTMLPage = new CM_ClientLetterHTMLPage(driver);
             
             clientLetterHTMLPage.clickClientPrintPreview(globalVariables.letterHTMLName);
             
             clientLetterHTMLPage.verifyClientPrintPreview(globalVariables.letterHTMLDescription);
             
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
	
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Verify search (Positive test , Negative test, Show All)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_LetterHTML_1", "Client_LetterHTML_2"})
	public void Client_LetterHTML_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Verify letter print preview";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientLetterHTMLPage = new CM_ClientLetterHTMLPage(driver);
             
             clientLetterHTMLPage.verifySearchBox(globalVariables.letterHTMLName, globalVariables.letterHTMLTemplateName);
             
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Check added Letter template in To-Do page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterHTML_2")
	public void Client_LetterHTML_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Check added Letter template in To-Do page";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		//String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickToDoTab();
  			
 			 CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
 			
 			 clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterHTMLName);
             
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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Delete Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterHTML_3")
	public void Client_LetterHTML_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Delete Letter HTML";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		//String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickLetterHTMLTab();
             
             CM_ClientLetterHTMLPage clientHTMLLetterPage = new CM_ClientLetterHTMLPage(driver);
             
             clientHTMLLetterPage.deleteLetterTemplate(globalVariables.editLetterHTMLName);
             
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Client - Letter(HTML)-Check added email in Email compose page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_LetterHTML_2")
	public void Client_LetterHTML_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client - Letter(HTML)-Check added email in Email compose page";
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		//String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			 LoginPageTest login = new LoginPageTest(driver, webSite);

             CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

             login.clickAgreeButton(false);
                             
             CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
             
             clientListPage.clickOnClientName(clientName, true);
             
             clientListPage.clickEmailsTab();
             
             CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
             
             clientEmailPage.clickComposeEmailButton();
             
             clientEmailPage.verifyHTMLLetterTemplateOnComposeEmailPage(globalVariables.letterHTMLName);
             
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
	public void Case_LetterMS_1(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_2(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_35")
	public void Case_LetterMS_3(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD)-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_3")
	public void Case_LetterMS_4(String browser) throws Exception {

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
	public void Case_LetterMS_5(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_5")
	public void Case_LetterMS_6(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_6")
	public void Case_LetterMS_7(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager-Case: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_7")
	public void Case_LetterMS_8(String browser) throws Exception {

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify Client Details Header", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify Client Details Header";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

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
			
			lettersMSWordPage.verifyClientAndCaseDetailsInCaseLevel(clientName, globalVariables.clientEmailID, caseId, corpName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify Available Template search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify Available Template search box";
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
			
			lettersMSWordPage.verifyAvailableTemplateSearchBox(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify Used text comes up after using the template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify Used text comes up after using the template";
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
			
			lettersMSWordPage.verifyUsedText(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify quick action dropdown list", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify quick action dropdown list";
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
			
			lettersMSWordPage.verifyquickActionDropDownOptions(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify Search Letter search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify Search Letter search box";
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
			
			lettersMSWordPage.verifyLetterSearchBox(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify Delete all functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "useAllTemplatesCase")
	public void Case_LetterMS_17(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify Delete all functionality";
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
			
			lettersMSWordPage.deleteAllLetters();

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Click on Letter template and verify description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_22(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Click on Letter template and verify description";
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
			
			lettersMSWordPage.verifyLetterTemplateDescription(globalVariables.letterMSWordTemplateName, globalVariables.docName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify print functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_23(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify print functionality";
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
			
			lettersMSWordPage.verifyPrintFunctionality(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Add Template and verify in compose email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_24(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Add Template and verify in compose email page";
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
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseEmailPage.clickComposeEmailButton(); 
			
			caseEmailPage.verifyMSLetterTemplateOnComposeEmailPage(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case Advanced Email", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Add template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addLetterTemplateInKB")
	public void Case_LetterMS_25(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Add template in KB and verify on Letter template page";
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
			
			lettersMSWordPage.verifyTemplatedAddingInKB(globalVariables.newLetterTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Delete template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "deleteLetterTemplateInKB")
	public void Case_LetterMS_26(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Delete template in KB and verify on Letter template page";
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
			
			lettersMSWordPage.verifyIfKBTemplateDeleted(globalVariables.newLetterTemplateName);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify status dropdown", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_27(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify status dropdown";
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
			
			lettersMSWordPage.verifyStatusOptions(globalVariables.letterMSWordTemplateName);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Edit status as In Draft ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_28(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Edit status as In Draft ";
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
			
			lettersMSWordPage.editAndVerifyStatusInDraft(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Edit status as Send for signature", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_29(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Edit status as Send for signature";
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
			
			lettersMSWordPage.editAndVerifyStatusSendForSignature(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Edit status as Ready for printing", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_30(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Edit status as Ready for printing";
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
			
			lettersMSWordPage.editAndVerifyStatusReadyForPrinting(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Edit status as not started status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_31(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Edit status as not started status";
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
			
			lettersMSWordPage.editAndVerifyStatusNotStarted(globalVariables.letterMSWordTemplateName);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify ascending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_32(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify ascending order of letter templates";
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
			
			lettersMSWordPage.verifyAscendingOrderOfLetterTemplates();

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify descending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_33(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify descending order of letter templates";
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
			
			lettersMSWordPage.verifyDescendingOrderOfLetterTemplates();

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
	
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify breadcrumbs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterMS_34(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify breadcrumbs";
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
			
			lettersMSWordPage.verifyBreadCrumbsInCaseLevel();

			Utils.waitForAllWindowsToLoad(2, driver);
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Verify letter template in TO-Do Module", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterMS_1")
	public void Case_LetterMS_35(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Verify letter template in TO-Do Module";
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
			
			caseListPage.clickToDoTab();
 			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Compose Letter HTML using letter template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addHTMLLetterTemplateInKB")
	public void Case_LetterHTML_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Compose Letter HTML";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");
//		String letterHTMLTemplateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateDescription");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.clickOnComposeButton();
			
			caseLetterHTMLPage.composeUsingLetterTemplate(globalVariables.letterHTMLTemplateName, globalVariables.letterHTMLTemplateDescription);
			
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Compose Letter without template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_LetterHTML_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Compose Letter without template";
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
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.clickOnComposeButton();
			
			caseLetterHTMLPage.composeHTMLLetter(globalVariables.letterHTMLName, globalVariables.letterHTMLDescription);
			
			caseLetterHTMLPage.verifyIfHTMLLetterPresent(globalVariables.letterHTMLName);
			
//            String directory = System.getProperty("user.dir");
//			 ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//            
//			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.letterHTMLName);
//			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.letterHTMLDescription);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Edit Letter template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterHTML_5")
	public void Case_LetterHTML_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Edit Letter template";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		//String letterTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.clickEditLetter(globalVariables.letterHTMLTemplateName);
            
			caseLetterHTMLPage.editLetter(globalVariables.editLetterHTMLName, globalVariables.editLetterHTMLDescription);
 			
            String directory = System.getProperty("user.dir");
 			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
             
 			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.editLetterHTMLName);
 			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.editLetterHTMLDescription);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Priview HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterHTML_2")
	public void Case_LetterHTML_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Priview HTML";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String templateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLDescription");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.clickPrintPreview(globalVariables.letterHTMLName);
		    
			caseLetterHTMLPage.verifyPrintPreview(globalVariables.letterHTMLDescription);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Search Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Case_LetterHTML_1", "Case_LetterHTML_2"})
	public void Case_LetterHTML_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Search Letter HTML";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLPage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLPage.verifySearchBox(globalVariables.letterHTMLName, globalVariables.letterHTMLTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Check added Letter template in To-Do page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterHTML_2")
	public void Case_LetterHTML_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Check added Letter template in To-Do page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickToDoTab();
 			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterHTMLName);

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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Delete Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterHTML_3")
	public void Case_LetterHTML_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Delete Letter HTML";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		//String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLpage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLpage.deleteLetter(globalVariables.editLetterHTMLName);

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
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(MS WORD)-Check added HTML template in Email compose page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_LetterHTML_3")
	public void Case_LetterHTML_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(MS WORD)-Check added HTML template in Email compose page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		//String letterHTMLName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseEmailPage.clickComposeEmailButton(); 
			
			caseEmailPage.verifyHTMLLetterTemplateOnComposeEmailPage(globalVariables.letterHTMLName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case Advanced Email", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case - Letter(HTML)-Copy case letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "prerequisiteForCopyCase")
	public void Case_LetterHTML_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case - Letter(HTML)-Copy case letter HTML";
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
			
			caseListPage.clickLetterHTMLTab();
			
			CM_CaseLetterHTMLPage caseLetterHTMLpage = new CM_CaseLetterHTMLPage(driver);
			
			caseLetterHTMLpage.copyCaseLetter(globalVariables.letterHTMLName);
			
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
	
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Letter(MS WORD)-Template from server(KB Templates) option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Template from server(KB Templates) option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.useServerTemplateAndVerify(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Verify download option";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD)-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_35")
	public void Corp_LetterMS_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Verify edit option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD)-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_3")
	public void Corp_LetterMS_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Verify delete option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerify(globalVariables.letterMSWordTemplateName, globalVariables.edittedletterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD)-Upload letter from desktop and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Upload letter from desktop and verify";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.uploadLetterFromDesktopAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.letterTemplateFilePath);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_5")
	public void Corp_LetterMS_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE- Verify download functionality";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_6")
	public void Corp_LetterMS_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE-Verify edit option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.editAndVerify(globalVariables.localLetterMSWordTemplateName, globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_7")
	public void Corp_LetterMS_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD) LOCAL TEMPLATE-Verify delete option";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.deleteAndVerifyLocalTemplate(globalVariables.edittedLocalLetterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify Corp Details header", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify Corp Details header";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyCorpDetailsInCorpLevel(corpName, globalVariables.corpEmailID, signingPerson);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify Available Template search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify Available Template search box";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyAvailableTemplateSearchBox(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify Used text comes up after using the template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify Used text comes up after using the template";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyUsedText(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify quick action dropdown list", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify quick action dropdown list";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyquickActionDropDownOptions(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify Search Letter search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify Search Letter search box";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyLetterSearchBox(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify Delete all functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "useAllTemplatesCorp")
	public void Corp_LetterMS_17(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify Delete all functionality";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.deleteAllLetters();

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Click on Letter template and verify description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_22(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Click on Letter template and verify description";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyLetterTemplateDescription(globalVariables.letterMSWordTemplateName, globalVariables.docName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify print functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_23(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify print functionality";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyPrintFunctionality(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Add template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addLetterTemplateInKB")
	public void Corp_LetterMS_25(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Add template in KB and verify on Letter template page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyTemplatedAddingInKB(globalVariables.newLetterTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Delete template in KB and verify on Letter template page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "deleteLetterTemplateInKB")
	public void Corp_LetterMS_26(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Delete template in KB and verify on Letter template page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyIfKBTemplateDeleted(globalVariables.newLetterTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify status dropdown", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_27(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify status dropdown";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyStatusOptions(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Edit status as In Draft ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_28(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Edit status as In Draft ";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.editAndVerifyStatusInDraft(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Edit status as Send for signature", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_29(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Edit status as Send for signature ";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.editAndVerifyStatusSendForSignature(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Edit status as Ready for printing", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_30(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Edit status as Ready for printing";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.editAndVerifyStatusReadyForPrinting(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Edit status as not started status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_31(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Edit status as not started status";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.editAndVerifyStatusNotStarted(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify ascending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_32(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify ascending order of letter templates";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyAscendingOrderOfLetterTemplates();

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Letter(MS WORD) Verify descending order of letter templates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_33(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp:  Letter(MS WORD) Verify descending order of letter templates";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyDescendingOrderOfLetterTemplates();

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	

	

	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Verify breadcrumbs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterMS_34(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Verify breadcrumbs";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			lettersMSWordPage.verifyBreadCrumbsInCorpLevel();

			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager-Corp: Verify letter template in TO-Do Module", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterMS_1")
	public void Corp_LetterMS_35(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Case Manager-Corp: Verify letter template in TO-Do Module";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickToDoTab();
 			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterMSWordTemplateName);

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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(MS WORD)-Compose letter template using template created in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addHTMLLetterTemplateInKB")
	public void Corp_LetterHTML_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Client - Letter(MS WORD)-Compose letter template using template created in KB";
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");
//		String letterHTMLTemplateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateDescription");
		

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)-Compose letter template using template created in KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.clickOnComposeCorporationLetterButton();
			
			corpLetterHTMLPage.composeUsingLetterTemplateCorporation(globalVariables.letterHTMLTemplateName, globalVariables.letterHTMLTemplateDescription);

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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Verify Compose letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_LetterHTML_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		
		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(HTML)-Verify Compose letter";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.clickOnComposeCorporationLetterButton();
            
			corpLetterHTMLPage.composeHTMLLetterCorp(globalVariables.letterHTMLName, globalVariables.letterHTMLDescription);
			
//            String directory = System.getProperty("user.dir");
//			 ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//            
//			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.letterHTMLName);
//			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.letterHTMLDescription);
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Edit Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterHTML_5")
	public void Corp_LetterHTML_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		
		globalVariables.testCaseDescription = "Case Manager-Corp: Edit Letter HTML";
		//String letterTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.clickCorpEditLetter(globalVariables.letterHTMLTemplateName);
             
			corpLetterHTMLPage.editCorpLetter(globalVariables.editLetterHTMLName, globalVariables.editLetterHTMLDescription);
 			
//            String directory = System.getProperty("user.dir");
// 			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//             
// 			writeExcel.setCellData("letterHTMLName", sheetName, "Value", globalVariables.editLetterHTMLName);
// 			writeExcel.setCellData("letterHTMLDescription", sheetName, "Value", globalVariables.editLetterHTMLDescription);
			
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
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Preview HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterHTML_2")
	public void Corp_LetterHTML_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String templateDescription = readExcel.initTest(workbookName, sheetName, "letterHTMLDescription");
		
		globalVariables.testCaseDescription = "Case Manager-Corp: Preview HTML";
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.clickCorpPrintPreview(globalVariables.letterHTMLName);
	             
			corpLetterHTMLPage.verifyCorpPrintPreview(globalVariables.letterHTMLDescription);
			
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
	
	
	
	
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Search Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_LetterHTML_1", "Corp_LetterHTML_2"})
	public void Corp_LetterHTML_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		
		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(HTML)-Search Letter HTML";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
//		String templateName = readExcel.initTest(workbookName, sheetName, "letterHTMLName");
//		String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.verifySearchBox(globalVariables.letterHTMLName, globalVariables.letterHTMLTemplateName);
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Check added Letter template in To-Do page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterHTML_2")
	public void Corp_LetterHTML_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Client - Letter(MS WORD)-Check added Letter template in To-Do page";
		
		//String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickToDoTab();
 			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfLetterTemplateAvailable(globalVariables.letterHTMLName);
			
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
	
	
	@Test(groups = {"letterTemplate", "corp"}, description = "Case Manager : Corp -Letter(HTML)-Delete Letter HTML", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_LetterHTML_3")
	public void Corp_LetterHTML_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Client - Letter(MS WORD)-Delete Letter HTML";
		
		//String letterHTMLTemplateName = readExcel.initTest(workbookName, sheetName, "letterHTMLTemplateName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickLetterHTMLTab();
			
			CM_CorpLetterHTMLPage corpLetterHTMLPage = new CM_CorpLetterHTMLPage(driver);
			
			corpLetterHTMLPage.deleteHTMLLetter(globalVariables.editLetterHTMLName);
			
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
	
	
	@Test(groups = {"letterTemplate", "case"}, description = "Case Manager - CASE : Use all templates in case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void useAllTemplatesCase(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager - CASE : Use all templates";
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
			
			lettersMSWordPage.useAllTemplates();
			
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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)  Use all templates in client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void useAllTemplatesClient(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)  Use all templates in client";

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

			lettersMSWordPage.useAllTemplates();

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
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Corp: Letter(MS WORD)  Use all templates in corp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void useAllTemplatesCorp(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case Manager-Corp: Letter(MS WORD)  Use all templates in corp";
		
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);

			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);

			corpListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.useAllTemplates();

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
			
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
	
	@Test(groups = {"letterTemplate"}, description = "Delete all Letter Templates from KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void deleteAllTemplatesForKB(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Delete all Letter Templates from KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickLetterTemplateMSWordTab();
			
			knowledgeBasePage.deleteAllMSLetterTemplate();
			
			knowledgeBasePage.clickLetterTemplateHTMLTab();
			
			knowledgeBasePage.deleteAllHTMLLetterTemplate();
			
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
	
	
}