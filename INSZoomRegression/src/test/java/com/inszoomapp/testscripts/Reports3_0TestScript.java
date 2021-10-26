package com.inszoomapp.testscripts;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_Reports_3_0_Page;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class Reports3_0TestScript  extends BaseTest
{
	String userName = "AABD467";
	String password = "Zoom@1234";
	String userName1 = "Aindem01";
	String password1 = "Ins@12345";
	
	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();
		
		globalVariables.browserUsedForExecution = browserName;
		globalVariables.environmentName = env;
		
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'All Open Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_1(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'All Open Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndclickAllOpenCases();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "All Open Cases", "Case ID");
			//reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "All Open Cases");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Initiation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_2(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Initiation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.countAndClickInitiation();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Initiation Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Data Collection'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_3(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Data Collection'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.clickDataCollection();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Data Collection ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Preparation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_4(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Preparation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.clickPreparation();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Preparation Phas", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Filing'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_5(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Filing'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.clickFiling();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Filing Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'RFE/Audit'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_6(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE/Audit'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.clickRFEAudit();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In RFE _ Audit", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Review'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_7(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Review'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
		
			int noOfRows = reports_3_0_page.clickReview();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Review Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'New Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_8(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'New Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickNewCases();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "New Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'RFE Received'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_9(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Received'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickRFEReceived();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Received Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'RFE Submitted'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_10(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Submitted'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickRFESubmitted();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Submitted Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Approved'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_11(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Approved'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickApproved();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Approved", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Denied'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_12(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Denied'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickDenied();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Denied", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Withdrawn'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_13(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Withdrawn'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickWithdrawn();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Withdrawn", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Case Step Reminders'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_14(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Case Step Reminders'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickCaseStepReminders();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Case Step Reminders", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Appointments'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_15(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Appointments'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickAppointment();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Appointments");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Court Dates'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_16(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Court Dates'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickCourtDates();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Court Dates", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Interviews'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_17(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Interviews'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickInterviews();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Interviews", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'RFE Due'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_18(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Due'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickRFEDue();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Due Case(s)", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Tasks/To-Do'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_19(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Tasks/To-Do'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			int noOfRows = reports_3_0_page.countAndClickTaskToDo();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Tasks _ To-Do Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Open case by type'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_20(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Open case by type'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.clickOpenCasesByType();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Open Cases By Case Type ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Perm pie chart'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_21(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Perm pie chart'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.clickPERMFiled();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "PERM", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Prospects'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_22(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Prospects'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickProspects();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Individual Prospects");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Case Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_23(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Case Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickCaseExpiration();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Client Doc Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_24(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Client Doc Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.clickI797();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Number of Records Verification from Excel for 'Elligibility as per Visa bulletin'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_25(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Elligibility as per Visa bulletin'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			int noOfRows = reports_3_0_page.countAndClickEligibleForFilingVisa();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Eligibility As Per Curren", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'All Open Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_26(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'All Open Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickAllOpenCasesPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "All Open Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Initiation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_27(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Initiation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickInitiationPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Initiation Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Data Collection'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_28(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Data Collection'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickDataCollectionPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Data Collection ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Preparation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_29(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Preparation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickPreparationPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Preparation Phas", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Review'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_30(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Review'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickReviewPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Review Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Filing'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_31(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Filing'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickFilingPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Filing Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'RFE/Audit'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_32(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE/Audit'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickRFEAuditPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In RFE _ Audit", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'New Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_33(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'New cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickNewCasesPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "New Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'RFE Received'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_34(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Received'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickRFEReceivedPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Received Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'RFE Submitted'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_35(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Submitted'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickRFESubmittedPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Submitted Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Approved'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_36(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Approved'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickApprovedPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Approved", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Denied'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_37(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Denied'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickDeniedPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Denied", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Withdrawn'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_38(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Withdrawn'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickWithdrawnPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Withdrawn", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Open Case By Type'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_39(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Open Case By Type'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickOpenCasesByTypePL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Open Cases By Case Type ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'PERM'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_40(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'PERM'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickPERMFiledPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "PERM", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Prospects'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_41(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Prospects'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickProspectsPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Individual Prospects");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Case Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_42(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Case Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.click21YearsAgeoutPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations", "Client ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Client Doc Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_43(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Client Doc Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickI797PL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations", "Client ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Elligibility as per Visa bulletin'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_44(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Elligibility as per Visa bulletin'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			int noOfRows = reports_3_0_page.clickEligibleForFilingVisaPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Eligibility As Per Curren", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'RFE Due'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_45(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE Due'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickRFEDuePL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "RFE Due Case(s)", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Case Step Reminders'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_46(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Case Step Reminders'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickCaseStepRemindersPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Case Step Reminders", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Appointments'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_47(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Appointments'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickAppointmentsPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Appointments");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Court Dates'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_48(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Court Dates'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickCourtDatesPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Court Dates", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Interview'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_49(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Interview'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickInterviewsPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Interviews", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Paralegal / Case Worker Reports"}, description = "Number of Records Verification from Excel for 'Tasks/To-Do'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_50(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Tasks/To-Do'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName1, password1, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPLReportsTab();
			
			reports_3_0_page.clickUpcomingCaseActivityPL();
			
			int noOfRows = reports_3_0_page.clickTaskToDoPL();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Tasks _ To-Do Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'All Open Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_51(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'All Open Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickAllOpenCasesHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "All Open Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Initiation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_52(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Initiation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickInitiationHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Initiation Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Waiting For Decision'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_53(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Waiting For Decision'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickWaitingForDecisionHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Waiting For Decision", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Data Collection'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_54(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Data Collection'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickDataCollectionHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Data Collection ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Preparation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_55(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Preparation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickPreparationHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Preparation Phas", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Review'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_56(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Review'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickReviewHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Review Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Filing'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_57(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Filing'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickFilingHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In Filing Phase", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'RFE / Audit'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_58(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'RFE / Audit'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickRFEAuditHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases In RFE _ Audit", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Cases Opened'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_59(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Cases Opened'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickCasesOpenedHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "New Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Yet to File'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_60(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Yet to File'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickYetToFileHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Yet To File", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Filed and Pending with Govt.'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_61(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Filed and Pending with Govt.'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickPendingWithGovtHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Filed", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'In RFE/Audit'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_62(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'In RFE/Audit'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickInRFEAuditHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "In RFE_Audit Cases", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Approved'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_63(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Approved'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickApprovedHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Approved", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Denied'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_64(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Denied'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickDeniedHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Denied", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Withdrawn'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_65(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Withdrawn'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickWithdrawnHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Cases Withdrawn", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Open Case by Type'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_66(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Open Case by Type'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickOpenCasesByTypeHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Open Cases By Case Type ", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'PERM'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_67(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'PERM'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickPERMFiledHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "PERM", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Clients By Current Immigration Status'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_68(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Clients By Current Immigration Status'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickClientByCurrentImmigrationStatusHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Client Details");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Case Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_69(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Case Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickCaseExpirationHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Client Doc Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_70(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Client Doc Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickClientExpirationHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Expirations");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Elligibility as per Visa bulletin'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_71(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Elligibility as per Visa bulletin'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickEligibleForFilingVisaHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Eligibility As Per Curren", "Case ID");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Number of Records Verification from Excel for 'Client by Citizenship'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void EV_TC_72(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Verification from Excel for 'Client by Citizenship'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			int noOfRows = reports_3_0_page.clickClientByCitizenshipHR();
			
			reports_3_0_page.verifyNumberOfRowsInExcel(noOfRows, "Citizenship Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for All Open Cases", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_AllOpenCases(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for All Open Cases";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

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
	
	
	@Test(description = "Reports Column Verification for Waiting For Decision", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_WaitingForDecision(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Waiting For Decision";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWaitingForDecision();
			
			reports_3_0_page.verifyColumns("Waiting For Decision");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for New Case(s)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_NewCases(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for New Case(s)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickNewCases();
			
			reports_3_0_page.verifyColumns("New cases");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Phases Report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_PhasesReport(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Phases Report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInitiation();
			
			reports_3_0_page.verifyColumns("Phases Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Cases Filed", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CasesFiled(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Cases Filed";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickFiled();
			
			reports_3_0_page.verifyColumns("Case Filed");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for RFE Received", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_RFEReceived(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for RFE Received";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickRFEReceived();
			
			reports_3_0_page.verifyColumns("RFE Received");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for RFE Submitted", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_RFESubmitted(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for RFE Submitted";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickRFESubmitted();
			
			reports_3_0_page.verifyColumns("RFE Submitted");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Approved", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Approved(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Approved";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickApproved();
			
			reports_3_0_page.verifyColumns("Approved");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Denied", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Denied(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Denied";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickDenied();
			
			reports_3_0_page.verifyColumns("Denied");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Withdrawn", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Withdrawn(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Withdrawn";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWithdrawn();
			
			reports_3_0_page.verifyColumns("Withdrawn");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Perm Pie Chart", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_PermPieChart(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Perm Pie Chart";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPERMFiled();
			
			reports_3_0_page.verifyColumns("Perm pie chart");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Prospects", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Prospects(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Prospects";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickProspects();
			
			reports_3_0_page.verifyColumns("Prospects");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Case Expiration", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CaseExpiration(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Case Expiration";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseExpiration();
			
			reports_3_0_page.verifyColumns("Case Expiration");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Elligibility as per Visa bulletin", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_ElligibilityAsPerVisaBulletin(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Elligibility as per Visa bulletin";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickEligibleForFilingVisa();
			
			reports_3_0_page.verifyColumns("Elligibility as per Visa bulletin");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Life Cycle", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_LifeCycle(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Life Cycle";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickLifeCycle();
			
			reports_3_0_page.verifyColumns("Life Cycle");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Case status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CaseStatus(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Case status";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseStatus();
			
			reports_3_0_page.verifyColumns("Case status");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Priority Dates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_PriorityDates(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Priority Dates";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPriorityDates();
			
			reports_3_0_page.verifyColumns("Priority dates");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for J-1", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_J1(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for J-1";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickJ1();
			
			reports_3_0_page.verifyColumns("J1");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Case LCA", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CaseLCA(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Case LCA";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseLCA();
			
			reports_3_0_page.verifyColumns("Case LCA");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Perm Report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_PermReport(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Perm Report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPERM();
			
			reports_3_0_page.verifyColumns("Perm report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for H1-B Report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_H1BReport(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for H1-B Report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickH1BRegistration();
			
			reports_3_0_page.verifyColumns("H1 B report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for NIV report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_NIVReport(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for NIV report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickNIV();
			
			reports_3_0_page.verifyColumns("NIV report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for IV Report", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_IVReport(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for IV Report";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickIV();
			
			reports_3_0_page.verifyColumns("IV Report");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Login statistics", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_LoginStatistics(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Login statistics";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickLoginStatistics();
			
			reports_3_0_page.verifyColumns("Login statistics");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Corp user", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CorpUser(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Corp user";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCorpUser();
			
			reports_3_0_page.verifyColumns("Corp user");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Citizenship", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Citizenship(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Citizenship";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCitizenship();
			
			reports_3_0_page.clickCountry();
			
			reports_3_0_page.verifyColumns("Citizenship");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Case Request", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CaseRequest(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Case Request";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseRequest();
			
			reports_3_0_page.verifyColumns("Case Request");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for e-Consent", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_EConsent(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for e-Consent";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickEConsent();
			
			reports_3_0_page.verifyColumns("e-Consent");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for RFE Due", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_RFEDue(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for RFE Due";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickRFEDue();
			
			reports_3_0_page.verifyColumns("RFE Due");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Steps and Reminders", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_StepsAndReminders(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Steps and Reminders";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickCaseStepReminders();
			
			reports_3_0_page.verifyColumns("Steps and Reminders");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Appointment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Appointment(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Appointment";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickAppointment();
			
			reports_3_0_page.verifyColumns("Appointment");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Client Doc Expiration", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_ClientDocExpiration(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Client Doc Expiration";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.click21YearsAgeout();
			
			reports_3_0_page.verifyColumns("Client Doc Expiration");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Open case by type", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_OpenCaseByType(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Open case by type";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickOpenCaseByTypeUndefined();
			
			reports_3_0_page.verifyColumns("Open case by type");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Greencard Process", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_GreencardProcess(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Greencard Process";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			reports_3_0_page.clickGreencardProcess();
			
			reports_3_0_page.verifyColumns("Greencard Process");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Interviews", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_Interviews(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Interviews";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickInterviews();
			
			reports_3_0_page.verifyColumns("Interviews");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Court dates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_CourtDates(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Court dates";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickCourtDates();
			
			reports_3_0_page.verifyColumns("Court dates");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Tasks/To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_TaskToDo(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Tasks/To-Do";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickTaskToDo();
			
			reports_3_0_page.verifyColumns("Tasks/To-Do");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Employee Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_EmployeeDetails(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Employee Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickClientDetails();
			
			reports_3_0_page.verifyColumns("Employee details");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Invoice Report-Corporation", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_InvoiceReportCorporation(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Invoice Report-Corporation";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInvoice();
			
			reports_3_0_page.verifyInvoiceColumns("Invoice report-Corporation");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Invoice Report-Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_InvoiceReportInvoice(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Invoice Report-Invoice";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInvoice();
			
			reports_3_0_page.clickViewByInvoiceTab();
			
			reports_3_0_page.verifyInvoiceColumns("Invoice report-Invoice");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Work Summary-By Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_WorkSummaryCase(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Work Summary-By Case";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();
			
			reports_3_0_page.clickViewByCaseTab();
			
			reports_3_0_page.verifyWorkSummaryColumns("Work Summary-By Case");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Work Summary-By Corporation", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_WorkSummaryCorporation(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Work Summary-By Corporation";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();
			
			reports_3_0_page.clickViewByCorporationTab();
			
			reports_3_0_page.verifyWorkSummaryColumns("Work Summary-By Corporation");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Reports Column Verification for Work Summary-By Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CV_WorkSummaryClient(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Reports Column Verification for Work Summary-By Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();

			reports_3_0_page.verifyWorkSummaryColumns("Work Summary-By Client");
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'All Open Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_1(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'All Open Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickAllOpenCases();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Phases Report'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_2(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Phases Report'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInitiation();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Waiting for Decision'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_3(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Waiting for Decision'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWaitingForDecision();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'New Cases'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_4(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'New Cases'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickNewCases();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Case Filed'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_5(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Case Filed'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickFiled();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'RFE Received'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_6(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'RFE Received'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickRFEReceived();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'RFE Submitted'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_7(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'RFE Submitted'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickRFESubmitted();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Approved'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_8(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Approved'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickApproved();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Denied'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_9(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Denied'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickDenied();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Withdrawn'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_10(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Withdrawn'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWithdrawn();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Open case by type'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_11(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Open case by type'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickOpenCaseByTypeUndefined();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Perm pie chart'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_12(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Perm pie chart'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPERMFiled();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Prospects'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_13(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Prospects'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickProspects();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Case Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_14(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Case Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseExpiration();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Client Doc Expiration'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_15(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Client Doc Expiration'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.click21YearsAgeout();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Elligibility as per Visa bulletin'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_16(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Elligibility as per Visa bulletin'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickEligibleForFilingVisa();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

	@Test(groups={"Partner"}, description = "Timeout check for 'Life Cycle'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_17(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Life Cycle'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickLifeCycle();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Employee details'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_18(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Employee details'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickClientDetails();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'NIV report'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_19(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'NIV report'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickNIV();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'IV Report'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_20(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'IV Report'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickIV();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Case status'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_21(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Case status'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseStatus();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Priority dates'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_22(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Priority dates'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPriorityDates();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'J-1'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_23(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'J-1'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickJ1();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Case LCA'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_24(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Case LCA'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseLCA();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'PERM'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_25(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'PERM'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickPERM();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'H1 B report'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_26(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'H1 B report'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickH1BRegistration();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Login statistics'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_27(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Login statistics'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickLoginStatistics();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Corp user'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_28(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Corp user'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCorpUser();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Invoice report-Corporation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_29(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Invoice report-Corporation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInvoice();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Invoice report-Invoice'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_30(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Invoice report-Corporation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickInvoice();
			
			reports_3_0_page.clickViewByInvoiceTab();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Citizenship'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_31(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Citizenship'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCitizenship();
			
			reports_3_0_page.clickCountry();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Case Request'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_32(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Case Request'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickCaseRequest();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'RFE Due'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_33(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'RFE Due'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickRFEDue();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Steps and Reminders'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_34(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Steps and Reminders'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickCaseStepReminders();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Appointment'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_35(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Appointment'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickAppointment();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Interviews'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_36(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Interviews'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickInterviews();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Court Dates'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_37(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Court Dates'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickCourtDates();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Work Summary-By Client'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_38(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Work Summary-By Client'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Work Summary-By Corporation'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_39(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Work Summary-By Corporation'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();
			
			reports_3_0_page.clickViewByCorporationTab();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Work Summary-By Case'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_40(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Work Summary-By Case'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickWorkSummary();
			
			reports_3_0_page.clickViewByCaseTab();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'e-consent'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_41(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'e-consent'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickEConsent();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"HR / Corporation Portal Reports"}, description = "Timeout check for 'Greencard Process'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_42(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Greencard Process'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickHRPortalReportsTab();
			
			reports_3_0_page.selectAllHeadquarters();
			
			reports_3_0_page.clickGreencardProcess();
			
			reports_3_0_page.checkTimeout();
				
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Partner"}, description = "Timeout check for 'Task/To-Do'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TO_43(String browser) throws Exception 
	{		
		globalVariables.testCaseDescription = "Timeout check for 'Task/To-Do'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);
			
			login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickReportsTab();
			
			CM_Reports_3_0_Page reports_3_0_page = new CM_Reports_3_0_Page(driver);
			
			reports_3_0_page.clickUpcomingCaseActivity();
			
			reports_3_0_page.clickTaskToDo();
			
			reports_3_0_page.checkTimeout();
			
			Log.testCaseResult();
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
