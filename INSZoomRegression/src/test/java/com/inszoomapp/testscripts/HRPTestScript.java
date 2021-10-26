package com.inszoomapp.testscripts;

import java.io.InputStream;
import java.io.OutputStream;
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
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_CorporationDocumentsPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.FNPProfilePage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRPNewsPage;
import com.inszoomapp.pages.HRP_CasePage;
import com.inszoomapp.pages.HRP_ClientPage;
import com.inszoomapp.pages.HRP_CorporationPage;
import com.inszoomapp.pages.HRP_ReportsPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class HRPTestScript extends BaseTest {
	public String[] documentName;
	Properties prop = new Properties();
	OutputStream output = null;
	InputStream input = null;
	
	private String workbookName = "";
	private String workbookNameWrite = null;
	private String sheetName = "";
	private String username = "";
	private String password = ""; 
	
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
				: context.getCurrentXmlTest().getParameter("sheetName"));
		
		globalVariables.browserUsedForExecution = browserName;
		
		String os = System.getProperty("os.name");
		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
					username = data.CM_userName;
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
					username = readExcel.initTest(workbookName, sheetName, "CM_userName");
					password = readExcel.initTest(workbookName, sheetName, "CM_password");
					break;
				}
				case "DEV": {
					data = new AppDataDev();
					username = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Dev.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Dev.xls";
					sheetName = "Inszoom";
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					username = data.CM_userName;
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
					username = data.CM_userName;
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
					username = readExcel.initTest(workbookName, sheetName, "CM_userName");
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
	
	
	@Test(description = "HRP Reports TXT file", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_66_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String HRP_ReportName = readExcel.initTest(workbookName, sheetName, "QA_HRP_ReportName");

		globalVariables.testCaseDescription = "HRP-Reports: Choose any reports and it contains download option for text file";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String textOptionPass = "Option to download report in TXT format is seen";
		String textOptionFail = "Option to download report in TXT format is not seen";
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String fileNameStartsWith = hrpLoginID.substring(0, 4);
		String reportCountry = "USA";
		

		
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickReportsTab();

			HRP_ReportsPage reportsPage = new HRP_ReportsPage(driver);
			
			reportsPage.selectReports(HRP_ReportName , true);

			reportsPage.exportAndVerifyTextFile(reportCountry, fileNameStartsWith,HRP_ReportName, textOptionPass, textOptionFail, true);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP Reports Excel file", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_66_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String HRP_ReportName = readExcel.initTest(workbookName, sheetName, "QA_HRP_ReportName");

		globalVariables.testCaseDescription = "HRP-Reports: Choose any reports and it contains download option for Excel file";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String excelOptionPass = "Option to download report in Excel format is seen";
		String excelOptionFail = "Option to download report in Excel format is not seen";
		String reportCountry = "USA";
		String fileNameStartsWith = hrpLoginID.substring(0, 4);
		

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickReportsTab();

			HRP_ReportsPage reportsPage = new HRP_ReportsPage(driver);
			
			reportsPage.selectReports(HRP_ReportName, true);
			
			reportsPage.exportAndVerifyExcelFile(reportCountry, fileNameStartsWith, HRP_ReportName, excelOptionPass, excelOptionFail, true);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP search client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_62_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "HRP-Client: Searching the client";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}

		finally {
			Log.endTestCase();
			driver.quit();

		}

	}
	
	
	@Test(description = "HRP select client name and verify client tab", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_62_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "HRP-Client: Select the client and verify the tabs after search";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.verifyTabsInClient();

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP case and verify tabs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_62_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "HRP-Case: Click on case description and Verify the tabs present";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCaseLink(caseCreated);

			hrpClientPage.verifyTabsInCaseDetails();

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP case steps", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_47_0"})
	public void CM_TC_62_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "HRP-Case: case steps tab contains all the case steps";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
			String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();

			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCaseLink(caseCreated);
			
			hrpClientPage.clickCaseStepsTab();

			hrpClientPage.caseStepsVerify(true);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}

		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP case details/dates tab", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2")
	public void CM_TC_62_5(String browser) throws Exception 
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		

		globalVariables.testCaseDescription = "HRP-Case: details/dates tab contains data";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();

			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCaseLink(caseCreated);
			
			hrpClientPage.clickDetailsDatesTab();

			hrpClientPage.verifyDetailsDates(true);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP - Messages: Verify email would be listed in the received email list", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_57_1"})
	public void CM_TC_23_3_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "HRP - Messages: Verify email would be listed in the received email list";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();

			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
			
			hrpClientPage.verifyIfEmailPresent(globalVariables.accessCodeEmailSubject);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP - CASE: Verify email would be listed in the received email list", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_59_1"})
	public void CM_TC_62_6(String browser) throws Exception 
	{

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "HRP - CASE: Verify email would be listed in the received email list";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();

			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCaseLink(caseCreated);
			
			hrpClientPage.clickEmailsTab();

			hrpClientPage.verifyIfEmailPresent(globalVariables.accessCodeEmailSubject);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "HRP : check the sent mail is available", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_2"})
	public void CM_TC_52_5_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		globalVariables.testCaseDescription = "HRP : check the sent mail is available";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickMyCorporationTab();

			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickEmailsTab();
			
			hrpCorporationPage.sendEmail(globalVariables.sendSubjectContent, globalVariables.sendMessage, globalVariables.sendEmailID);

			hrpCorporationPage.verifyIfEmailPresent(globalVariables.sendEmailID);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Left menu collapse and expand in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Left menu collapse and expand in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);

			fnpHomePage.verifyCollapseFunctionality();
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify logout in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify logout in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Signing person name on the navigation bar", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String signingPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		
		globalVariables.testCaseDescription = "Signing person name on the navigation bar";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.verifySigningPersonNameOnNavigationBar(signingPerson);

			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Notes (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_125")
	public void HRP_TC_10_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Notes (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickNotesTab();
			
			hrpCorpPage.verifyNotesPresent(globalVariables.notesDetailsTextCorp);

			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"Priority2", "HRP"}, description="Search Notes (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_125")
	public void HRP_TC_10_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Search Notes (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickNotesTab();
			
			hrpCorpPage.verifyNotesSearchBox(globalVariables.notesDetailsTextCorp);

			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Case Information on Client Page (Open)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_4"})
	public void HRP_TC_35_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseOpen");
		
		globalVariables.testCaseDescription = "Verify Case Information on Client Page (Open)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyCaseOpenLabel(caseIdCreated);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Case Information on Client Page (Approved)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_5"})
	public void HRP_TC_35_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseApproved");
		
		globalVariables.testCaseDescription = "Verify Case Information on Client Page (Approved)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyCaseApprovedLabel(caseIdCreated);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Case Information on Client Page (Closed)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_6"})
	public void HRP_TC_35_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseClose");
		
		globalVariables.testCaseDescription = "Verify Case Information on Client Page (Closed)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyCaseClosedLabel(caseIdCreated);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Passports (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_10_1"})
	public void HRP_TC_36(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Passports (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickPassportAndVisasTab();
			
			hrpClientPage.verifyPassportDetails(globalVariables.passportNumber, globalVariables.FNPnoticeDate, globalVariables.countryNew, globalVariables.validToFNP);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Visas (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_16_1"})
	public void HRP_TC_37(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Visas (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickPassportAndVisasTab();
			
			hrpClientPage.verifyVisaDetails(globalVariables.countryName, globalVariables.visaType, globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify Notes (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_24_1"})
	public void HRP_TC_42_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Notes (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickNotesTab();
			
			hrpClientPage.verifyNotes(globalVariables.firstNotesDetailsTextClient);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Notes (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_24_1"})
	public void HRP_TC_42_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Notes (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickNotesTab();
			
			hrpClientPage.verifyNotesSearch(globalVariables.firstNotesDetailsTextClient);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify Notes (Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_48_1"})
	public void HRP_TC_51_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify Notes (Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			hrpClientPage.clickCaseNotesTab();
			
			hrpClientPage.verifyCaseNotes(globalVariables.firstNotesDetailsTextCase);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Notes (Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_48_1"})
	public void HRP_TC_51_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Search Notes (Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			hrpClientPage.clickCaseNotesTab();
			
			hrpClientPage.verifyCaseNotesSearch(globalVariables.firstNotesDetailsTextCase);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify Case Steps ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_47_0","com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_47_7"})
	public void HRP_TC_43(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify Case Steps ";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);

			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_notStarted, "Not Started");

			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_workInProgress, "In Progress");
			
			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_notApplicable, "Not Applicable");
			
			//fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_completed, "Completed");
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "HRP"}, description="Add e-consent, decline it and then Accept it. Check if e-consent is logged in Activity", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_1_0(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Add e-consent, decline it and then Accept it. Check if e-consent is logged in Activity";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

            caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(false);
			
			portalSetup.searchCorporationByName(corpName, false);
			
			portalSetup.clickCustomizeCorporationSettings(false);
			
			portalSetup.clickPortalContentTab();
			
			portalSetup.addEConsent(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
			
			portalSetup.chooseEConsentforCorporation(globalVariables.eConsentTitle);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "My Zoomboard", "title", "false");
			
			login = new LoginPageTest(driver, webSite);

			login.hrpLoginWithoutAcceptingConsents(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			hrpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
			
			hrpHomePage.declineEConsentAndCheck();
			
			login = new LoginPageTest(driver, webSite);

			login.hrpLoginWithoutAcceptingConsents(hrpLoginID, hrpPassword, true);

			//login.clickAgreeButton(false);

			hrpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
			
			hrpHomePage.acceptEConsentAndCheck();
			
			hrpHomePage.clickProfile();
			
			hrpHomePage.clickActivityTab();
			
			hrpHomePage.verifyIfEConsentLogged(globalVariables.eConsentTitle);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
/*	@Test(groups={"Priority1", "HRP"}, description="Decline e-consent in HRPP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_1_0")
	public void HRP_TC_1_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Decline e-consent in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			hrpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
			
			hrpHomePage.declineEConsentAndCheck();
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	
	
/*	@Test(groups={"Priority1", "HRP"}, description="Accept e-consent in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_1_0")
	public void HRP_TC_1_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Accept e-consent in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			hrpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
			
			hrpHomePage.acceptEConsentAndCheck();
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	
	
	@Test(groups={"Priority2", "HRP"}, description="HRP: Check accepted e-consent in Activity tab", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_1_0")
	public void HRP_TC_1_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Accept e-consent in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);
			
			hrpHomePage.clickProfile();
			
			hrpHomePage.clickActivityTab();
			
			hrpHomePage.verifyIfEConsentLogged(globalVariables.eConsentTitle);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HRP", "Priority2", "portalSetup"}, description="Portal Setup: Add News for Corporation and check in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_18_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Portal Setup: Add News for Corporation and check in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

            caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(false);
			
			portalSetup.searchCorporationByName(corpName, false);
			
			portalSetup.clickCustomizeCorporationSettings(false);
			
			portalSetup.clickPortalContentTab();
			
			portalSetup.clickNewsTab();
			
			portalSetup.clickAddNewsAndEnterData(globalVariables.newsTitleCorp, globalVariables.newsDescriptionCorp);
			
			portalSetup.clickSaveAndPublish();
			
			portalSetup.verifyIfNewsAdded(globalVariables.newsTitleCorp, globalVariables.newsDescriptionCorp);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "My Zoomboard", "title", "false");
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewspage = new HRPNewsPage(driver);
			
			hrpNewspage.verifyIfNewsPresent(globalVariables.newsTitleCorp, globalVariables.newsDescriptionCorp);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HRP", "Priority2", "portalSetup"}, description="Portal Setup: Add News for Corporation and Client and check in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_18_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Portal Setup: Add News for Corporation and Client and check in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

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

			login.hrpLogin(hrpLoginID,hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.verifyIfNewsPresent(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HRP", "Priority2", "portalSetup"}, description="Portal Setup: Delete added News for HRP and Verify in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"HRP_TC_18_2"})
	public void HRP_TC_18_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Portal Setup: Delete added News for HRP and Verify in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

            caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(false);
			
			portalSetup.searchCorporationByName(corpName, false);
			
			portalSetup.clickCustomizeCorporationSettings(false);
			
			portalSetup.clickPortalContentTab();
			
			portalSetup.clickNewsTab();
			
			portalSetup.deleteNews(globalVariables.newsTitleCorp);
			
			portalSetup.verifyIfNewsDeleted(globalVariables.newsTitleCorp);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "My Zoomboard", "title", "false");
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.verifyIfNewsDeleted(globalVariables.newsTitleCorp);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search for Added News", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_18_1")
	public void HRP_TC_18_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
	
		globalVariables.testCaseDescription = "HRP: Search for Added News";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.searchNews(globalVariables.newsDescriptionBoth);
			
			hrpNewsPage.verifyIfNewsPresent(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup", "HRP"}, description="Portal Setup: Add Policy/Guidelines for Corporation and check in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_19_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Corporation and check in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

            caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(false);
			
			portalSetup.searchCorporationByName(corpName, false);
			
			portalSetup.clickCustomizeCorporationSettings(false);
			
			portalSetup.clickPortalContentTab();
			
			portalSetup.clickPolicyGuidelinesTab();
			
			portalSetup.clickAddPolicyAndEnterData(globalVariables.policyTitleCorp, globalVariables.policyDescriptionCorp);
			
			portalSetup.clickSaveAndPublishPolicy();
			
			portalSetup.verifyIfPolicyAdded(globalVariables.policyTitleCorp, globalVariables.policyDescriptionCorp);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "My Zoomboard", "title", "false");
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.clickPolicyGuidelinesTab();
			
			hrpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleCorp, globalVariables.policyDescriptionCorp);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup", "HRP"}, description="Portal Setup: Add Policy/Guidelines for Corporation and Client and check in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_19_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Portal Setup: Add Policy/Guidelines for Corporation and Client and check in HRP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

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

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.clickPolicyGuidelinesTab();
			
			hrpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup", "HRP"}, description="Delete Policy/Guidelines and check in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_19_2")
	public void HRP_TC_19_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Delete Policy/Guidelines and check in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

            //login.clickAgreeButton(false);

            caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetup = new PortalSetup(driver);
			
			portalSetup.clickDefaultPortalAccess(false);
			
			portalSetup.searchCorporationByName(corpName, false);
			
			portalSetup.clickCustomizeCorporationSettings(false);
			
			portalSetup.clickPortalContentTab();
			
			portalSetup.clickPolicyGuidelinesTab();
			
			portalSetup.deleteNews(globalVariables.policyTitleCorp);
			
			portalSetup.verifyIfPolicyDeleted(globalVariables.policyTitleCorp);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "My Zoomboard", "title", "false");
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.clickPolicyGuidelinesTab();
			
			hrpNewsPage.verifyIfNewsDeleted(globalVariables.policyTitleCorp);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search for Added Policy", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_19_1")
	public void HRP_TC_19_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "HRP: Search for Added Policy";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickNewsGuidelinesTab();
			
			HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
			
			hrpNewsPage.clickPolicyGuidelinesTab();
			
			hrpNewsPage.searchPolicy(globalVariables.policyTitleBoth);
			
			hrpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search for Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_20_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Search for Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search for a non-existent Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_20_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "HRP: Search for a non-existent Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(hrpLoginID, true);
			
			hrpClientPage.verifyIfClientNotPresent();

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Education History (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_4")
	public void HRP_TC_34_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Education History (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifySchoolHistory(globalVariables.schoolNameNew, globalVariables.historyDateFNP, globalVariables.universityName, globalVariables.degree, globalVariables.fieldOfStudy, globalVariables.numberForPersonalInfo);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Employment History (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_6")
	public void HRP_TC_34_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Employement History (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifyEmploymentHistory(globalVariables.jobNew, globalVariables.historyDateFNP, globalVariables.employer, globalVariables.numberForPersonalInfo);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Past Addresses (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_2")
	public void HRP_TC_34_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Verify Past Addresses (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifyAddressHistory(globalVariables.cityEditted, globalVariables.historyDateFNP, globalVariables.countryNew, globalVariables.streetNumber, globalVariables.state);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1", "documents"}, description="HRP: Upload Digital Docs (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_9_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "HRP: Upload Digital Docs (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickDigitalDocuments();
			
			hrpCorporationPage.uploadFile(globalVariables.filePathFNP);
			
			hrpCorporationPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtensionFNP);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: View Upload Digital Docs (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_9_1")
	public void HRP_TC_9_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "HRP: View Upload Digital Docs (Corporation)";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
			
			hrpCorporationPage.clickDigitalDocuments();
			
			hrpCorporationPage.viewDocument(globalVariables.fileNameWithoutExtensionFNP, driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2", "documents"}, description="HRP: Upload Digital Docs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_24_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Upload Digital Docs (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
			
			hrpClientPage.uploadDocument(globalVariables.filePathHRP);
			
			hrpClientPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtensionHRP);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: View Uploaded Digital Docs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_24_1")
	public void HRP_TC_24_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: View Uploaded Digital Docs (Client";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
				
			hrpClientPage.viewDocument(globalVariables.fileNameWithoutExtensionHRP, driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search Uploaded Digital Docs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_24_1")
	public void HRP_TC_24_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Search Uploaded Digital Docs (Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
			
			hrpClientPage.searchForDocument(globalVariables.fileNameWithoutExtensionHRP);
				
			hrpClientPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtensionHRP);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Search non-existent Digital Docs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_24_1")
	public void HRP_TC_24_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Search non-existent Digital Docs (Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
			
			hrpClientPage.searchForDocument(clientName);
			
			hrpClientPage.verifyIfDocumentNotPresent();
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Delete Uploaded Digital Docs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_24_1")
	public void HRP_TC_24_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Delete Uploaded Digital Docs (Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
				
			hrpClientPage.deleteDocument(globalVariables.fileNameWithoutExtensionHRP);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Default Custom Data (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_0")
	public void HRP_TC_13_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Default Custom Data (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickCustomDataTab();
			
			hrpCorpPage.verifyDefaultCustomData(globalVariables.defaultCustomFieldName, globalVariables.defaultCustomData);
			
			hrpCorpPage.verifyCrimeInfo(globalVariables.crimeInfoData);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Corporation Specific Custom Data (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_1")
	public void HRP_TC_13_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Corporation Specific Custom Data (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickCustomDataTab();
			
			hrpCorpPage.verifyCorpCustomData(globalVariables.corpCustomFieldName, globalVariables.corpCustomData);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Default Custom Data (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_15_1")
	public void HRP_TC_39_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Verify Default Custom Data (Client)";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCustomDataTab();
			
			hrpClientPage.verifyDefaultCustomData(globalVariables.clientDefaultCustomFieldName, globalVariables.salary);
			
			hrpClientPage.verifyCrimeInfo(globalVariables.crimeInfoData);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Specific Custom Data (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_2")
	public void HRP_TC_39_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Verify Specific Custom Data (Client)";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);

			hrpClientPage.clickCustomDataTab();
			
			hrpClientPage.verifySpecificCustomData(globalVariables.clientCustomFieldName, globalVariables.clientCustomData);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	

	@Test(groups={"Priority2", "HRP"}, description = "Verify Custom Details (Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_3"})
	public void HRP_TC_48(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify Custom Details (Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			HRP_CasePage casePage = new HRP_CasePage(driver);
			
			casePage.clickDetailsDatesTab();
			
			casePage.verifyCustomDetails(globalVariables.caseCustomFieldName, globalVariables.caseCustomData);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify Filing Details(Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_36_2"})
	public void HRP_TC_44(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify Filing Details(Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			HRP_CasePage casePage = new HRP_CasePage(driver);
			
			casePage.clickDetailsDatesTab();
			
			casePage.verifyFilingDetails(globalVariables.FNPfiledOnDate, globalVariables.edittedReceiptNumber, globalVariables.FNPnoticeDate);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify Receipt Details(Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_34"})
	public void HRP_TC_46(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify Receipt Details(Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			HRP_CasePage casePage = new HRP_CasePage(driver);
			
			casePage.clickDetailsDatesTab();
			
			casePage.verifyReceiptDetails(globalVariables.QA_ALoginReceiptSendDate_FNP, globalVariables.edittedReceiptNumber, globalVariables.editReceiptDateInCaseProfile,globalVariables.validFrom_FNP, globalVariables.validTo_FNP , "Approved");
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify LCA Details(Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115"})
	public void HRP_TC_49(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify LCA Details(Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			HRP_CasePage casePage = new HRP_CasePage(driver);
			
			casePage.clickDetailsDatesTab();
			
			casePage.verifyLCADetails(globalVariables.LCANumber, globalVariables.jobTitle, globalVariables.validThru,globalVariables.LCAeffectiveOn, globalVariables.LCAeffectiveTill);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify SWA and DoL Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_117"})
	public void HRP_TC_50(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify SWA and DoL Info";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseCreated);
			
			HRP_CasePage casePage = new HRP_CasePage(driver);
			
			casePage.clickDetailsDatesTab();
			
			casePage.verifySWAandDoLInfo(globalVariables.swaCaseNumber, globalVariables.swaFiledDateFNP, globalVariables.swaPriorityDateFNP, globalVariables.dolRecievedDateFNP, globalVariables.dolCaseNumber, globalVariables.noticeSentDateFNP, globalVariables.noticeReceivedDateFNP, globalVariables.backlogCaseNumber);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Verify LCA (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115"})
	public void HRP_TC_38(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Verify LCA (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickLCATab();
			
			hrpClientPage.verifyLCAdetails(globalVariables.LCANumber, globalVariables.jobTitle,globalVariables.LCAcity, data.PetitionType_AddCase , globalVariables.validFromFNP, globalVariables.validThruFNP,  globalVariables.LCAeffectiveOnFNP, globalVariables.LCAeffectiveTillFNP);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Signatory Information (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_0")
	public void HRP_TC_16_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String signingPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		
		globalVariables.testCaseDescription = "Verify Signatory Information (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.verifySignatoryInfo(signingPerson, globalVariables.signingPersonTitle, globalVariables.signingPersonContactNumber, "corp@inszoom.com");
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Administrative Contact (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115_0")
	public void HRP_TC_16_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String signingPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		
		globalVariables.testCaseDescription = "Verify Administrative Contact (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			String name = globalVariables.administrativePersonFirstName + " " + globalVariables.administrativePersonLastName ; 
			
			hrpCorpPage.verifyAdministrativeContactInfo(name, globalVariables.administrativePersonTitle, globalVariables.administrativePersonContactNumber, globalVariables.administrativePersonEmailId);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Visas for Relative (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_79_0")
	public void HRP_TC_31(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "Verify Visas for Relative (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.clickPassportAndVisasInRelative();
			
			hrpClientPage.verifyVisaInRelative(globalVariables.countryName, globalVariables.visaType, globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Passports for Relative (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_121")
	public void HRP_TC_30(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "Verify Passports for Relative (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.clickPassportAndVisasInRelative();
			
			hrpClientPage.verifyPassportInRelative(globalVariables.issuingCountry, globalVariables.passportNumber, globalVariables.genericStartDate_FNP, globalVariables.validToFNP);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Client) Emails (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_1_0")
	public void HRP_TC_41_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Client) Emails (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.verifyIfContentEmailPresent(globalVariables.emailMessage, globalVariables.clientContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Client) Emails (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_2_0")
	public void HRP_TC_41_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Client) Emails (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.verifyIfEmailPresentWithAttachment(globalVariables.emailMessage, globalVariables.clientAttachmentEmailSubject, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Client) Emails (without extarnet access)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_3_0")
	public void HRP_TC_41_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Client) Emails (without extarnet access)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Sent (Client) Emails (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.FNPTestScript.FNP_TC_74_2")
	public void HRP_TC_41_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Sent (Client) Emails (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.verifyIfSentEmailPresent(globalVariables.sendMessage, globalVariables.sendSubjectContent);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Sent (Client) Emails (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.FNPTestScript.FNP_TC_4_7")
	public void HRP_TC_41_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Sent (Client) Emails (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.verifyIfSentEmailPresentWithAttachment(globalVariables.sendMessage, globalVariables.sendSubjectAttachment, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2", "documents"}, description="HRP: Verify Uploaded Docs from A-Login (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.DocumentManagementTestScript.Client_Doc_21")
	public void HRP_TC_25(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");
		
		globalVariables.testCaseDescription = "HRP: Verify Uploaded Docs from A-Login  (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickDocuments();
			
			hrpClientPage.verifyIfDocumentPresent(clientFile1NameWithoutExtension);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify if clicking on Client Picture in (Case) is redirecting to Client ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_54(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify if clicking on Client Picture in (Case) is redirecting to Client ";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpClientPage.verifyTabsInClient();
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Sent (Case) Email (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.FNPTestScript.FNP_TC_3_1")
	public void HRP_TC_52_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Sent (Case) Email (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Sent (Case) Email (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.FNPTestScript.FNP_TC_3_2")
	public void HRP_TC_52_7(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Sent (Case) Email (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfEmailPresentWithAttachment(globalVariables.sendMessage, globalVariables.sendSubjectAttachmentCase, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Case) Emails (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_3_0")
	public void HRP_TC_52_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Case) Emails (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.emailMessage, globalVariables.caseContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Case) Emails (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_4_0")
	public void HRP_TC_52_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Case) Emails (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfEmailPresentWithAttachment(globalVariables.emailMessage, globalVariables.caseAttachmentEmailSubject, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Verify Received (Case) Emails (without extranet access)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_5_0")
	public void HRP_TC_52_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Received (Case) Emails (without extranet access)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Important Documents and Dates (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_16_1")
	public void HRP_TC_33(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Verify Important Documents and Dates (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);


		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			////login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyVisaDetailsInImportantDocumentsAndDate(globalVariables.countryName, globalVariables.visaType, globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups={"Priority2", "HRP"}, description="Verify Important Documents and Dates for Relative (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_79_0")
	public void HRP_TC_28(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "Verify Important Documents and Dates for Relative (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.verifyVisaDetailsInImportantDocumentsAndDate(globalVariables.countryName, globalVariables.visaType, globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Company Info for Filing Petitions (Corporation)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_127")
	public void HRP_TC_15(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Company Info for Filing Petitions (Corporation)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.verifyCompanyInfoForFilingPetitions(globalVariables.businessDescription, globalVariables.businessType, globalVariables.yearEstablished, globalVariables.placeOfIncorporation, globalVariables.numberOfEmployees, globalVariables.doingBusinessAs, globalVariables.grossAnnualIncome, globalVariables.netAnnualIncome, globalVariables.taxID, globalVariables.stateTax, globalVariables.naicsCode);

			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Primary Firm Contact (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_128")
	public void HRP_TC_22(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Verify Primary Firm Contact (Client)";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyPrimaryFirmContact(globalVariables.primaryFirmContact);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}

		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Primary Firm Contact (Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_128")
	public void HRP_TC_53(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Verify Primary Firm Contact (Case)";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.verifyPrimaryContactFirmContact(globalVariables.primaryFirmContact);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}

		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Check Following in Following tab in Profile", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Check Following in Following tab in Profile";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFollowButton();
			
			hrpHomePage.clickProfile();
			
			hrpHomePage.verifyFollowingSection(clientName);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}

		catch (Exception e) {
			Log.exception(e, driver);

		}

		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"},description = "Verify Employment (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_129")
	public void HRP_TC_40(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		globalVariables.testCaseDescription = "Verify Employment (Client)";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickJobTab();
			
			hrpClientPage.verifyEmployment(globalVariables.employeeID, globalVariables.department, globalVariables.Designation, globalVariables.annualSalary, globalVariables.currencyType, globalVariables.responsibilities, globalVariables.hireDateHRP, globalVariables.rehireDateHRP, globalVariables.terminationDateHRP);

			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Proposed Job Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_130")
	public void HRP_TC_47(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Proposed Job Info";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickDetailsDatesTab();
			
			hrpCasePage.verifyProposedJobDetails(globalVariables.cityName, globalVariables.countryName ,globalVariables.supervisorTitle, globalVariables.noOfSubOrdinates, globalVariables.proposedJobTitle, globalVariables.jobDuties, globalVariables.jobType, globalVariables.socCode, globalVariables.noOfHoursPerWeek, globalVariables.qualification, globalVariables.majorFieldOfStudy, globalVariables.experienceInJobOfferedYears, globalVariables.experienceInJobOfferedMonths, globalVariables.experienceInRelatedOccupationYears, globalVariables.experienceInRelatedOccupationMonths, globalVariables.relatedOccupation);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Expiring in 6 months (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_68_0")
	public void HRP_TC_23(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Verify Expiring in 6 months (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyExpiratingIn6Months(globalVariables.documentExpirationTemplateLessThan6Months);
						
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Personal Details (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_131")
	public void HRP_TC_32(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Verify Personal Details (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.verifyPersonalDetails(globalVariables.gender, globalVariables.placeOfBirth, globalVariables.country, globalVariables.citizenship, globalVariables.dob);
						
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Personal Details for Relative (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_132")
	public void HRP_TC_26_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "HRP: Verify Personal Details for Relative (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.verifyPersonalDetails(globalVariables.gender, globalVariables.placeOfBirth, globalVariables.country, globalVariables.citizenship, globalVariables.dob);
						
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Family Information (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_4")
	public void HRP_TC_26_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "Verify Family Information (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

			hrpClientPage.searchforClient(clientName, true);

			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.verifyRelative(relativeFirstName,globalVariables.relation);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"Priority2", "HRP"}, description="Verify Firm contacts on Home Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_128")
	public void HRP_TC_55(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "Verify Firm contacts on Home Page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.verifyFirmContacts(globalVariables.primaryFirmContact);
			
			hrpHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
		@Test(groups = {"HRP", "Priority2"}, description="HRP: RELATIVE - Verify Education History (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_10")
	public void HRP_TC_29_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		
		globalVariables.testCaseDescription = "HRP: RELATIVE - Verify Education History (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifySchoolHistory(globalVariables.schoolNameRelative, globalVariables.historyDateRelativeFNP, globalVariables.universityNameRelative, globalVariables.degreeRelative, globalVariables.fieldOfStudyRelative, globalVariables.numberForPersonalInfoRelative);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: RELATIVE - Verify Employment History (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_11")
	public void HRP_TC_29_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "HRP: RELATIVE - Verify Employement History (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifyEmploymentHistory(globalVariables.occupationRelative, globalVariables.historyDateRelativeFNP, globalVariables.employerRelative, globalVariables.numberForPersonalInfoRelative);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority2"}, description="HRP: Verify Past Addresses (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_9")
	public void HRP_TC_29_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: RELATIVE - Verify Past Addresses (Client)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickFamilyIcon();
			
			hrpClientPage.clickOnRelativeName(relativeFirstName);
			
			hrpClientPage.clickBackgroundTab();
			
			hrpClientPage.verifyAddressHistory(globalVariables.cityRelative, globalVariables.historyDateRelativeFNP, globalVariables.countryRelative, globalVariables.streetNumberRelative, globalVariables.stateRelative);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
    
    @Test(groups={"Priority2", "HRP"}, description="Verify Residence Address (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_72_1_0")
    public void HRP_TC_34_2(String browser) throws Exception
    {
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
          String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          
          globalVariables.testCaseDescription = "Verify Residence Address (Client)";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          try 
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                      
                login = new LoginPageTest(driver, webSite);

                login.hrpLogin(hrpLoginID, hrpPassword, true);
                
                //login.clickAgreeButton(false);
    
                //login.clickAcceptAndContinueBtn(true);
                
                HrpHomePage hrpHomePage = new HrpHomePage(driver);
                
                hrpHomePage.clickClientTab();
                
                HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

                hrpClientPage.searchforClient(clientName, true);

                hrpClientPage.clickOnClientName(clientName);
                
                hrpClientPage.verifyResidenceAddress(globalVariables.residenceCity, globalVariables.countryForPersonalInfo, globalVariables.apartment, globalVariables.street1, globalVariables.street2, globalVariables.pinCode, globalVariables.stateForPersonalInfo, globalVariables.mobile, globalVariables.telephone);
                              
                hrpHomePage.clickLogout(true);
                
                Log.testCaseResult();
                
          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"Priority2", "HRP"}, description="Verify Work Address (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_72_1_1")
    public void HRP_TC_34_1(String browser) throws Exception
    {
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
          String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          
          globalVariables.testCaseDescription = "Verify Work Address (Client)";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          try 
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                      
                login = new LoginPageTest(driver, webSite);

                login.hrpLogin(hrpLoginID, hrpPassword, true);
                
                //login.clickAgreeButton(false);
    
                //login.clickAcceptAndContinueBtn(true);
                
                HrpHomePage hrpHomePage = new HrpHomePage(driver);
                
                hrpHomePage.clickClientTab();
                
                HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);

                hrpClientPage.searchforClient(clientName, true);

                hrpClientPage.clickOnClientName(clientName);
                
                hrpClientPage.verifyResidenceAddress(globalVariables.workCity, globalVariables.countryForWork, globalVariables.apartmentForWork, globalVariables.workAddressStreet1, globalVariables.workAddressStreet2, globalVariables.workAddressPinCode, globalVariables.workAddressState, globalVariables.workMobile, globalVariables.workTelephone);
                              
                hrpHomePage.clickLogout(true);
                
                Log.testCaseResult();
                
          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
	@Test(groups={"Priority1", "HRP"}, description="Verify Received (Corporation) Emails (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_126_1")
	public void HRP_TC_11_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Received (Corporation) Emails (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickEmailsTab();
			
			hrpCorpPage.verifyIfContentEmailPresent(globalVariables.corpContentEmailSubject, globalVariables.emailMessage);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "HRP"}, description="Verify Received (Corporation) Emails (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_126_2")
	public void HRP_TC_11_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Received (Corporation) Emails (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickEmailsTab();
			
			hrpCorpPage.verifyIfEmailPresentWithAttachment(globalVariables.corpAttachmentEmailSubject, globalVariables.emailMessage, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "HRP"}, description="Verify Received (Corporation) Emails (without extranet access)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_126_4")
	public void HRP_TC_11_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		
		globalVariables.testCaseDescription = "Verify Received (Corporation) Emails (without extranet access)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickEmailsTab();
			
			hrpCorpPage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "HRP"}, description = "HRP - CORPORATION: Verify the sent emails", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_11_5(String browser) throws Exception
	{		
		globalVariables.testCaseDescription = "HRP - CORPORATION: Verify the sent emails";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			//login.clickAgreeButton(false);
	
			//login.clickAcceptAndContinueBtn(true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickEmailsTab();
			
			hrpCorpPage.composeEmail(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
						
			hrpCorpPage.verifyIfSentEmailPresent(globalVariables.sendMessage, globalVariables.sendSubjectContent);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "HRP"}, description = "HRP - CORPORATION: Verify the sent emails (with attachments)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_11_6(String browser) throws Exception
	{		
		globalVariables.testCaseDescription = "HRP - CORPORATION: Verify the sent emails (with attachments)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
						
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickMyCorporationTab();
			
			HRP_CorporationPage hrpCorpPage = new HRP_CorporationPage(driver);
			
			hrpCorpPage.clickEmailsTab();
			
			hrpCorpPage.composeEmailWithAttachment(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectAttachment, globalVariables.filePath, globalVariables.fileName);
						
			hrpCorpPage.verifyIfSentEmailPresentWithAttachment(globalVariables.sendSubjectAttachment, globalVariables.sendMessage, globalVariables.fileNameWithoutExtension);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Reply to an Email (Case) ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_3_0")
	public void HRP_TC_52_5(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "HRP: Reply to an Email (Case)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickCaseLink(caseId);
			
			HRP_CasePage hrpCasePage = new HRP_CasePage(driver);
			
			hrpCasePage.clickOnClientName(clientName);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.replyToEmail(globalVariables.replyMessage, globalVariables.caseContentEmailSubject);
			
			hrpCasePage.clickEmailsTab();
			
			hrpCasePage.verifyIfRepliedEmailPresent(globalVariables.replyMessage, "RE:" + globalVariables.caseContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	
	@Test(groups = {"HRP", "Priority1"}, description="HRP: Reply to an Email (Client) ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_1_0")
	public void HRP_TC_41_7(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "HRP: Reply to an Email (Client) ";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);

			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.clickClientTab();
			
			HRP_ClientPage hrpClientPage = new HRP_ClientPage(driver);
			
			hrpClientPage.searchforClient(clientName, true);
			
			hrpClientPage.clickOnClientName(clientName);
			
			hrpClientPage.clickEmailsTab();
				
			hrpClientPage.replyToEmail(globalVariables.replyMessage, globalVariables.clientContentEmailSubject);
			
			hrpClientPage.clickEmailsTab();
			
			hrpClientPage.verifyIfRepliedEmailPresent(globalVariables.replyMessage, "RE:" + globalVariables.clientContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with First Name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Client with First Name";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(clientFirstName,clientFirstName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with Last Name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientLastName = readExcel.initTest(workbookName, sheetName, "ALoginSavedLastClientName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Client with Last Name";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(clientLastName,clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with Full Name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		
		globalVariables.testCaseDescription = "Search Client with Full Name";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(clientName,clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with Employee ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_129")
	public void HRP_TC_56_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String empId = readExcel.initTest(workbookName, sheetName, "QA_ALoginEmpIdTxt");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Client with Employee ID";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(empId, clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with Email ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		
		globalVariables.testCaseDescription = "Search Client with Email ID";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(globalVariables.clientEmailID, clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with File Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_7(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Client with File Number";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(globalVariables.fileNumber, clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "HRP"}, description = "Search Client with Prospective ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_TC_56_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Search Client with Prospective ID";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);

			//login.clickAcceptAndContinueBtn(true);

			hrpHomePage.verifyDashboardSearchBox(globalVariables.prospectiveEmployeeId, clientName);
			
			hrpHomePage.clickLogout(true);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if Document uploaded in HRP is present in Corporation Document page and check Filter for 'Corporation' and 'Case Manager'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "HRP_TC_9_1")
    public void Corp_Doc_33(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Check if Document uploaded in HRP is present in Corporation Document page and check Filter for 'Corporation' and 'Case Manager'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(username, password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileNameFNP);
    			
    			corporationDocumentsPage.selectFilter("Corporation");
	
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(globalVariables.fileNameFNP, corpFile1Name);
    			
    			corporationDocumentsPage.selectFilter("Corporation");
    			
    			corporationDocumentsPage.selectFilter("Case Manager");
    			
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(corpFile1Name, globalVariables.fileNameHRP);
    			
    			corporationDocumentsPage.selectFilter("Case Manager");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if Document uploaded in HRP (Client) is present in client Document page and check Filter for 'Corporation' and 'Case Manager'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="HRP_TC_24_1")
    public void Client_Doc_34(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Check if Document uploaded in HRP (Client) is present in client Document page and check Filter for 'Corporation' and 'Case Manager'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(data.CM_userName, data.CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileNameHRP);
                
                clientDocumentsPage.selectFilter("Corporation");
              
                clientDocumentsPage.verifyIfFilteredDocumentPresent(globalVariables.fileNameHRP, clientFile1Name);
                
                clientDocumentsPage.selectFilter("Corporation");
    			
                clientDocumentsPage.selectFilter("Case Manager");
    			
                clientDocumentsPage.verifyIfFilteredDocumentPresent(clientFile1Name, globalVariables.fileNameHRP);
                
                clientDocumentsPage.selectFilter("Case Manager");
                
                Log.testCaseResult();
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