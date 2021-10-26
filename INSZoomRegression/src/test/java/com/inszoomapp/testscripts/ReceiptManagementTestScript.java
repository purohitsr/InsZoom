package com.inszoomapp.testscripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;


@Listeners(EmailReport.class)
public class ReceiptManagementTestScript {

	String webSite = null;
	String env = null;
	String browserName = null;
	String RMUserName = null;
    String RMPassword = null;
    
	public static List<String> corporationsUnderHQ = new ArrayList<String>();
	
	String test = "Intial";
	AppDataBase data ;
	
	@BeforeTest(alwaysRun=true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

		
		globalVariables.browserUsedForExecution = browserName;

		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataStage();
                    globalVariables.environmentName = env;
                    RMUserName = data.CM_userNameRM ;
                    RMPassword = data.CM_passwordRM ;
                    
                    break;
				}
				case "STAGE": {
					data = new AppDataQA();
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
	
	
	@BeforeTest(groups = {"receiptManagement"}, dependsOnMethods = {"init"})
	public void checkDataSetup() throws Exception
	{
		//Added by Nitisha Sinha on 12/08/2020
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.verifyAddedReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.verifyAddedReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.checkMainReceipt();
	        
	        cmReceiptPage.expandReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.verifyUploadedDocument(globalVariables.rmDocument, globalVariables.mainReceipt);
	        
	        cmReceiptPage.expandReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.verifyUploadedDocument(globalVariables.rmDocument, globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
	        Log.testCaseResult();
			

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify Case status as APPROVED when YES is selected when we select APPROVAL NOTICE in Main Receipt")
	public void RM_TC_33() throws Exception
	{
		//Created by Yatharth Pandya on 21st August,2020
		
		globalVariables.testCaseDescription = "Verify Case status as APPROVED when YES is selected when we select APPROVAL NOTICE in Main Receipt ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
						
			caseListPage.changeCaseStatus("Open");
			
			caseListPage.changeCaseStatus("Approved");
			
			caseListPage.changeCaseStatus("Open");
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.checkMainReceipt();
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.mainReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Approval Notice", 1);
			
			receiptNumberPage.verifyStatus("Approved");
			
			receiptNumberPage.fillDate("approvedOn_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.selectCaseStatusFromApprovalNotice("YES");
			
			receiptNumberPage.backToCaseProfilePage();
			
			caseListPage.clickProfileTab(true);
			
			caseProfilePage.verifyCaseStatus("Approved");
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
						
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify Case status as APPROVED when NO is selected when we select APPROVAL NOTICE in Main Receipt")
	public void RM_TC_34() throws Exception
	{
		//Created by Yatharth Pandya on 19th August,2020
		
		globalVariables.testCaseDescription = "Verify Case status as APPROVED when NO is selected when we select APPROVAL NOTICE in Main Receipt";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
						
			caseListPage.changeCaseStatus("Open");
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.checkMainReceipt();
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.mainReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Approval Notice", 1);
			
			receiptNumberPage.verifyStatus("Approved");
			
			receiptNumberPage.fillDate("approvedOn_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.selectCaseStatusFromApprovalNotice("NO");
			
			receiptNumberPage.backToCaseProfilePage();
			
			caseListPage.clickProfileTab(true);
			
			caseProfilePage.verifyCaseStatus("Open");
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	

	@Test(groups = {"receiptManagement"}, description = "Verify add Receipt Number")
	public void RM_TC_1() throws Exception
	{
		//Added by Nitisha Sinha on 12/08/2020
		
		globalVariables.testCaseDescription = "Verify add Receipt Number";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        Random rand = new Random(); 
	    	globalVariables.receiptNumber = Integer.toString(rand.nextInt(100000)); 
	    	
	    	cmReceiptPage.addReceiptDetails("O", globalVariables.receiptNumber);
	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.verifyAddedReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify that duplicate receipts cannot be added")
	public void RM_TC_29() throws Exception
	{
		//Added by Nitisha Sinha on 12/08/2020
		
		globalVariables.testCaseDescription = "Verify that duplicate receipts cannot be added";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.addReceiptDetails("O", globalVariables.mainReceipt);
	        
	        cmReceiptPage.verifyDuplicateReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Delete Utility for bulk receipts.")
	public void RM_TC_3() throws Exception
	{
		//Added by Nitisha Sinha on 13/08/2020
		
		globalVariables.testCaseDescription = "Verify Delete Utility for bulk receipts.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.addReceiptDetails("O", globalVariables.receiptNumber1);
	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.verifyAddedReceipt(globalVariables.receiptNumber1);
	        
	        cmReceiptPage.addReceiptDetails("O", globalVariables.receiptNumber2);
	        	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.selectReceipt(globalVariables.receiptNumber1);
	        
	        cmReceiptPage.selectReceipt(globalVariables.receiptNumber2);
	        
	        cmReceiptPage.clickGlobalActionMenu();
	        
	        cmReceiptPage.clickBulkDeleteReceipt();
	        
	        cmReceiptPage.verifyDeleteReceipt(globalVariables.receiptNumber1);
	        
	        cmReceiptPage.verifyDeleteReceipt(globalVariables.receiptNumber2);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Print Utility for bulk receipts.")
	public void RM_TC_2() throws Exception
	{
		//Added by Nitisha Sinha on 14/08/2020
		
		globalVariables.testCaseDescription = "Verify Print Utility for bulk receipts.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.selectReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.selectReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.clickGlobalActionMenu();
	        
	        cmReceiptPage.clickBulkPrintReceipt();
	        
	        cmReceiptPage.verifyPrintReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Print Utility for particular receipt.")
	public void RM_TC_6() throws Exception
	{
		//Added by Nitisha Sinha on 14/08/2020
		
		globalVariables.testCaseDescription = "Verify Print Utility for particular receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.mainReceipt);
	        
	        cmReceiptPage.printReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.verifyPrintReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Edit Receipt for a particular receipt.")
	public void RM_TC_9() throws Exception
	{
		//Added by Nitisha Sinha on 14/08/2020
		
		globalVariables.testCaseDescription = "Verify Edit Receipt for a particular receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.checkMainReceipt();
	        
	        cmReceiptPage.clickActionMenu(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.clickEditReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.editReceipt();
	        
	        cmReceiptPage.selectReceiptNoticeType("Other", 1);
	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.expandReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.verifyEditReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Email Utility for particular receipt.")
	public void RM_TC_8() throws Exception
	{
		//Added by Nitisha Sinha on 17/08/2020
		
		globalVariables.testCaseDescription = "Verify Email Utility for particular receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.mainReceipt);
	        
	        cmReceiptPage.clickEmailReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.emailReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	        
	        caseListPage.clickEmailsTab();
	        
	        CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
	        
	        caseEmailsPage.verifyIfEmailSent(globalVariables.rmEmailSubject);
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Email Utility for bulk receipt.")
	public void RM_TC_5() throws Exception
	{
		//Added by Nitisha Sinha on 17/08/2020
		
		globalVariables.testCaseDescription = "Verify Email Utility for bulk receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.selectReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.selectReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.clickGlobalActionMenu();
	        
	        cmReceiptPage.clickBulkEmailReceipt();
	        
	        cmReceiptPage.emailReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	        
	        caseListPage.clickEmailsTab();
	        
	        CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
	        
	        caseEmailsPage.verifyIfEmailSent(globalVariables.rmEmailSubject);
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify save and send email for a particular receipt")
	public void RM_TC_28() throws Exception
	{
		//Added by Nitisha Sinha on 17/08/2020
		
		globalVariables.testCaseDescription = "Verify save and send email for a particular receipt";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        Random rand = new Random(); 
	    	globalVariables.emailReceiptNumber = Integer.toString(rand.nextInt(100000)); 
	    	
	    	cmReceiptPage.addReceiptDetails("O", globalVariables.emailReceiptNumber);
	        
	        cmReceiptPage.clickSaveAndSendEmail();
	        
	        cmReceiptPage.emailReceipt();
	        
	        cmReceiptPage.verifyAddedReceipt(globalVariables.emailReceiptNumber);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.emailReceiptNumber);
	        
	        cmReceiptPage.deleteReceipt(globalVariables.emailReceiptNumber);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	        
	        caseListPage.clickEmailsTab();
	        
	        CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
	        
	        caseEmailsPage.verifyIfEmailSent(globalVariables.rmEmailSubject);
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify download Utility for bulk receipts.")
	public void RM_TC_4() throws Exception
	{
		//Added by Nitisha Sinha on 18/08/2020
		
		globalVariables.testCaseDescription = "Verify download Utility for bulk receipts.";
		
		final RemoteWebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.selectReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.selectReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.clickBulkDownload(driver);
	        
	        cmReceiptPage.verifyDownload(driver);
	        
	        Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "Receipt Details - INSZoom.com", "title", "false");
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}

	
	@Test(groups = {"receiptManagement"}, description = "Verify preview document uploaded for a receipt")
	public void RM_TC_27() throws Exception
	{
		//Added by Nitisha Sinha on 18/08/2020
		
		globalVariables.testCaseDescription = "Verify preview document uploaded for a receipt";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.expandReceipt(globalVariables.mainReceipt);
	        
	        cmReceiptPage.previewDocument(globalVariables.rmDocument, globalVariables.rmDocumentWithoutExtension, globalVariables.mainReceipt);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify the functionality of Make Main Receipt.")
	public void RM_TC_12() throws Exception
	{
		//Added by Nitisha Sinha on 18/08/2020
		
		globalVariables.testCaseDescription = "Verify the functionality of Make Main Receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.checkMainReceipt();
	        
	        cmReceiptPage.clickActionMenu(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.makeMainReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.verifyMakeMainReceipt();
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify Main receipt can be deleted.")
	public void RM_TC_13() throws Exception
	{
		//Added by Nitisha Sinha on 18/08/2020
		//Edited by Souvik Ganguly on 22/07/2021
		//Main receipt can now be deleted so test updated accordingly.
		
		globalVariables.testCaseDescription = "Verify Main receipt can be deleted.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

            LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
            cmReceiptPage.addNewReceiptDetails(globalVariables.receiptType2, globalVariables.receiptNumber3);
            
            cmReceiptPage.clickActionMenu(globalVariables.receiptNumber3);
	        
            cmReceiptPage.makeMainReceipt(globalVariables.receiptNumber3);
            
            cmReceiptPage.verifyMainReceiptCreated(globalVariables.receiptNumber3);
	        
            cmReceiptPage.verifyDeleteMainReceipt(globalVariables.receiptNumber3);
            
            cmReceiptPage.clickActionMenu(globalVariables.mainReceipt);
	        
            cmReceiptPage.makeMainReceipt(globalVariables.mainReceipt);
            
            cmReceiptPage.verifyMainReceiptCreated(globalVariables.mainReceipt);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify  upload document in edit receipt")
	public void RM_TC_24() throws Exception
	{
		//Added by Nitisha Sinha on 19/08/2020
		
		globalVariables.testCaseDescription = "Verify  upload document in edit receipt";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.receiptNumber);
	        
	        cmReceiptPage.clickEditReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.uploadDocument(globalVariables.rmFilePath);
	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.expandReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.verifyUploadedDocument(globalVariables.rmDocument, globalVariables.receiptNumber);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify  delete document in edit receipt")
	public void RM_TC_26() throws Exception
	{
		//Added by Nitisha Sinha on 19/08/2020
		
		globalVariables.testCaseDescription = "Verify  delete document in edit receipt";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.receiptNumber);
	        
	        cmReceiptPage.clickEditReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.deleteDocument();
	        
	        cmReceiptPage.saveReceipt();
	        
	        cmReceiptPage.expandReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.verifyDeletedDocument(globalVariables.rmDocument, globalVariables.receiptNumber);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify  download document in edit receipt")
	public void RM_TC_25() throws Exception
	{
		//Added by Nitisha Sinha on 21/08/2020
		
		globalVariables.testCaseDescription = "Verify  download document in edit receipt";
		
		final RemoteWebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.receiptNumber);
	        
	        cmReceiptPage.clickEditReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.clickDownload(driver);
	        
	        cmReceiptPage.verifyDownload(driver);
	        
	        Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "Receipt Details - INSZoom.com", "title", "false");
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"}, description = "Verify that the data is geting populated from receipt add/edit page to case detils/dates page correctly")
	public void RM_TC_30() throws Exception
	{
		//Added by Nitisha Sinha on 25/08/2020
		
		globalVariables.testCaseDescription = "Verify that the data is geting populated from receipt add/edit page to case detils/dates page correctly";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        Random rand = new Random(); 
	    	globalVariables.receiptNumberForDataValidation = Integer.toString(rand.nextInt(100000)); 
	    	
	    	cmReceiptPage.addReceiptDetails("O", globalVariables.receiptNumberForDataValidation);
	        
	    	cmReceiptPage.fillDate("receiptDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.fillDate("filedDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.selectReceiptNoticeType("Request For Evidence", 1);
			
	    	cmReceiptPage.fillDate("rfeReceivedDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.fillDate("rfeResponseDueDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.fillDate("rfeResponseSubmittedDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.clickAddReceiptNoticeDocuments();
	    	
	    	cmReceiptPage.selectReceiptNoticeType("Withdrawn Notice", 2);
	    	
	    	cmReceiptPage.fillDate("withdrawnDate", globalVariables.receiptDate1);
	    	
	    	cmReceiptPage.clickAddReceiptNoticeDocuments();
	    	
	    	cmReceiptPage.selectReceiptNoticeType("Denial Notice", 3);
	    	
			cmReceiptPage.fillDate("deniedDate", globalVariables.receiptDate1);
			
			cmReceiptPage.clickAddReceiptNoticeDocuments();
	    	
			cmReceiptPage.selectReceiptNoticeType("Approval Notice", 4);
			
			cmReceiptPage.fillDate("approvedOn_4", globalVariables.receiptDate1);
			
			cmReceiptPage.fillDate("validFromDate_4", globalVariables.receiptDate1);
			
			cmReceiptPage.fillDate("validTillDate_4", globalVariables.receiptDate1);
			
			cmReceiptPage.fillDate("noticeDate_4", globalVariables.receiptDate1);
			
			cmReceiptPage.fillDate("approvedReceivedOn_4", globalVariables.receiptDate1);
			
			cmReceiptPage.saveReceipt();
			
			cmReceiptPage.clickActionMenu(globalVariables.receiptNumberForDataValidation);
			
			cmReceiptPage.makeMainReceipt(globalVariables.receiptNumberForDataValidation);
			
	        cmReceiptPage.backToCaseProfilePage();
	        
	        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	        
	        caseListPage.clickDetailsDatesTab(true);
	        
	        caseProfilePage.verifyCaseReceiptData(globalVariables.receiptDate1Verification);
	        
	        caseListPage.clickProfileTab(true);
	        
	        caseProfilePage.clickReceiptNumber();
	        
	        cmReceiptPage.clickActionMenu(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.makeMainReceipt(globalVariables.secondaryReceipt);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.receiptNumberForDataValidation);
	        
	        cmReceiptPage.deleteReceipt(globalVariables.receiptNumberForDataValidation);
	        
	        cmReceiptPage.backToCaseProfilePage();

	        dashboard.clickLogout(true);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify Add Receipt Notice/Documents.")
	public void RM_TC_10() throws Exception
	{
		//Created by Yatharth Pandya on 13th August, 2020
		
		globalVariables.testCaseDescription = "Verify Add Receipt Notice/Documents";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.checkMainReceipt();
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Other", 1);
			
			receiptNumberPage.deleteAllReceiptType();
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.clickAddReceiptNoticeDocuments();
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify Delete Receipt Notice/Documents.")
	public void RM_TC_11() throws Exception
	{
		//Created by Yatharth Pandya on 13th August, 2020
		
		globalVariables.testCaseDescription = "Verify Delete Receipt Notice/Documents";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.checkMainReceipt();
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.deleteReceiptType("2");
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to PENDING when receipt notice type is selected as RECEIPT NOTICE")
	public void RM_TC_14() throws Exception
	{
		//Created by Yatharth Pandya on 14th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to PENDING when receipt notice type is selected as RECEIPT NOTICE";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Receipt Notice", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to PENDING when receipt notice type is selected as Premuim processing receipt notice")
	public void RM_TC_15() throws Exception
	{
		//Created by Yatharth Pandya on 14th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to PENDING when receipt notice type is selected as Premuim processing receipt notice";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Premium Processing Receipt Notice", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to Pending when receipt notice type is selected as Transfer Notice")
	public void RM_TC_19() throws Exception
	{
		//Created by Yatharth Pandya on 17th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to Pending when receipt notice type is selected as Transfer Notice";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Transfer Notice", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to Pending when receipt notice type is selected as ACS Appointment Notice")
	public void RM_TC_20() throws Exception
	{
		//Created by Yatharth Pandya on 17th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to Pending when receipt notice type is selected as ACS Appointment Notice";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("ASC Appointment Notice", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to Pending when receipt notice type is selected as Others")
	public void RM_TC_22() throws Exception
	{
		//Created by Yatharth Pandya on 18th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to Pending when receipt notice type is selected as Others";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Other", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify receipt # validation for receipt type as FOIA Receipt number ")
	public void RM_TC_31() throws Exception
	{
		//Created by Yatharth Pandya on 18th August,2020
		
		globalVariables.testCaseDescription = "Verify receipt # validation for receipt type as FOIA Receipt number ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptType("FOIA");
			
			receiptNumberPage.verifyReceiptNumberFormat(globalVariables.secondaryReceipt, "ABC1234567890", "DOL Case Number");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify receipt # validation for receipt type as USCIS Receipt number ")
	public void RM_TC_32() throws Exception
	{
		//Created by Yatharth Pandya on 19th August,2020
		
		globalVariables.testCaseDescription = "Verify receipt # validation for receipt type as USCIS Receipt number ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptType("USCIS Receipt Number");
			
			receiptNumberPage.verifyReceiptNumberFormat(globalVariables.secondaryReceipt, "ABC1234567890", "DOL Case Number");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to RFE when receipt notice type is selected as REQUEST FOR EVIDENCE")
	public void RM_TC_16() throws Exception
	{
		//Created by Yatharth Pandya on 19th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to RFE when receipt notice type is selected as REQUEST FOR EVIDENCE";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Request For Evidence", 1);
			
			receiptNumberPage.verifyStatus("RFE");
			
			receiptNumberPage.fillDate("rfeReceivedDate_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status and case status changes to Approved when receipt notice type is selected as Approval Notice ")
	public void RM_TC_17() throws Exception
	{
		//Created by Yatharth Pandya on 20th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status and case status changes to Approved when receipt notice type is selected as Approval Notice ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Approval Notice", 1);
			
			receiptNumberPage.verifyStatus("Approved");
			
			receiptNumberPage.fillDate("approvedOn_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status and case status changes to Approved when receipt notice type is selected as Approval Notice ")
	public void RM_TC_18() throws Exception
	{
		//Created by Yatharth Pandya on 20th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status and case status changes to Approved when receipt notice type is selected as Approval Notice ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Denial Notice", 1);
			
			receiptNumberPage.verifyStatus("Denied");
			
			receiptNumberPage.fillDate("deniedDate_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	@Test(groups = {"receiptManagement"},description = "Verify that the receipt status changes to Withdrawn when receipt notice type is selected as Withdrawn Notice")
	public void RM_TC_23() throws Exception
	{
		//Created by Yatharth Pandya on 19th August,2020
		
		globalVariables.testCaseDescription = "Verify that the receipt status changes to Withdrawn when receipt notice type is selected as Withdrawn Notice ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.clickOnReceiptNumber(globalVariables.secondaryReceipt);
			
			receiptNumberPage.selectReceiptNoticeType("Withdrawn Notice", 1);
			
			receiptNumberPage.verifyStatus("Withdrawn");
			
			receiptNumberPage.fillDate("withdrawnDate_1", "08/13/2020");
			
			receiptNumberPage.saveReceipt();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"receiptManagement"},description = "Verify add appointment when the receipt notice type is selected as ACS Appointment Notice")
	public void RM_TC_21() throws Exception
	{
		//Created by Yatharth Pandya on 21st August,2020
		
		globalVariables.testCaseDescription = "Verify add appointment when the receipt notice type is selected as ACS Appointment Notice";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
			
			dashboard.searchCase(true, data.RMCaseId);
						
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
			
			caseProfilePage.clickReceiptNumber();
			
			CM_ReceiptNumbersPage receiptNumberPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumberPage.addReceiptDetails("D", "54321");
			
			receiptNumberPage.selectReceiptNoticeType("ASC Appointment Notice", 1);
			
			receiptNumberPage.verifyStatus("Pending");
			
			receiptNumberPage.addAndVerifyAddAppointment("Breakfast");
			
			receiptNumberPage.clickCancel();
			
			receiptNumberPage.backToCaseProfilePage();
			
			dashboard.clickLogout(true);
			
			Log.testCaseResult();
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();	
			driver.quit();
		}
	}
	

	@Test(groups = {"receiptManagement"}, description = "Verify Delete Utility for particular receipt.")
	public void RM_TC_7() throws Exception
	{
		//Added by Nitisha Sinha on 13/08/2020
		
		globalVariables.testCaseDescription = "Verify Delete Utility for particular receipt.";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest loginPage = new LoginPageTest(driver,webSite);
			
			CM_DashboardPage dashboard = loginPage.caseManagerlogin(RMUserName, RMPassword, true);
						
			dashboard.searchCase(true, data.RMCaseId);
			 
			CM_CaseProfilePage caseProfilePage = new CM_CaseProfilePage(driver);
	            
	        caseProfilePage.clickReceiptNumber();
	        
	        CM_ReceiptNumbersPage cmReceiptPage = new CM_ReceiptNumbersPage(driver);
	        
	        cmReceiptPage.clickActionMenu(globalVariables.receiptNumber);
	        
	        cmReceiptPage.deleteReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.verifyDeleteReceipt(globalVariables.receiptNumber);
	        
	        cmReceiptPage.backToCaseProfilePage();
	        
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

