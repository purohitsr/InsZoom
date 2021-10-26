package com.inszoomapp.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.xmlbeans.impl.common.GlobalLock;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import popupCheck.CorpPage;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseContractPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseLetterHTMLPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_ClientContractPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientLetterHTMLPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_CorpLetterHTMLPage;
import com.inszoomapp.pages.CM_CorporationContractPage;
import com.inszoomapp.pages.CM_CorporationEmailPage;
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
public class ContractsTestScript extends BaseTest
{
	
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	private String userName = "";
	private String password = "";
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
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Dev.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Dev.xls";
					sheetName = "Inszoom";
					userName = data.CM_userName;
					password = data.CM_password;
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
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
					userName = data.CM_userName;
					password = data.CM_password;

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
	
	
	@Test(groups = {"contracts"}, description = "Add Contracts Template in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void addContractTemplateInKB(String browser) throws Exception {

		globalVariables.testCaseDescription = "Add Contracts Template in KB";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickKnowledgeBase(true);

			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickContractTemplatesTab();
			
			knowledgeBasePage.addContractTemplate(globalVariables.contractsTemplateNameAddedInKB, globalVariables.contractsTemplateDescriptionAddedInKB, "All", globalVariables.contractFilePath);
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Verify Breadcrumbs (Corp)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Verify Breadcrumbs (Corp)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyBreadcrumbs();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Verify Corporation Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPerson = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String emailId = globalVariables.corpEmailID;
		globalVariables.testCaseDescription = "Verify Corporation Details";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyCorporationDetails(corpName, signingPerson, emailId);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Verify Search box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Verify Search box";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifySearchBox(globalVariables.contractsKBTemplate);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Add template in KB and verify on contract page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addContractTemplateInKB")
	public void Corp_Contract_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Add template in KB and verify on contract page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyKBTemplateOnContractsPage(globalVariables.contractsTemplateNameAddedInKB);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Check Template description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Check Template description";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyTemplateDescription(globalVariables.contractsKBTemplate, globalVariables.docName);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "corp"}, description = "Use the template which is available ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Use the template which is available ";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.useContractTemplate(globalVariables.contractsKBTemplate);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Check if Used label is coming for the templates in use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_6")
	public void Corp_Contract_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Check if Used label is coming for the templates in use";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyUsedText(globalVariables.contractsKBTemplate);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Check if DRAFT label is coming up after using a template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_6")
	public void Corp_Contract_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Check if DRAFT label is coming up after using a template";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyDraftText(globalVariables.contractsKBTemplate);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Add template using ADD TEMPLATE button", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		globalVariables.testCaseDescription = "Add template using ADD TEMPLATE button";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyAddTemplateFuctionality(globalVariables.newCorpContractTemplateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Corp_ContractsTemplateName", sheetName, "Value", globalVariables.newCorpContractTemplateName);
			
			corpContractsPage.useContractTemplate(globalVariables.newCorpContractTemplateName);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify document uploaded in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_9")
	public void Corp_Contract_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");
		

		globalVariables.testCaseDescription = "Corp: Contract: Verify document uploaded in email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyDocumentInEmailPage(templateName, globalVariables.docName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Send email and verify in email list page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_6")
	public void Corp_Contract_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");
		

		globalVariables.testCaseDescription = "Corp: Contract: Send email and verify in email list page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.sendEmail(globalVariables.contractsKBTemplate, globalVariables.emailSubjectForCorpContracts, globalVariables.emailMessage);

			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpContractsPage.switchToCorpProfilePage();
			
			corpListPage.clickEmailsTab();
			
			corpEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForCorpContracts);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Download the Document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_11")
	public void Corp_Contract_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");
		

		globalVariables.testCaseDescription = "Corp: Contract: Download the Document from Email List Page";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpListPage.clickEmailsTab();
			
			corpEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForCorpContracts, globalVariables.docName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify Upload Signed copy functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_9")
	public void Corp_Contract_13_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Verify Upload Signed copy functionality";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyUploadedSignedCopy(templateName, globalVariables.contractFilePathForUploadSignedCopy, globalVariables.docName2);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify Email Signed Copy button", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_13_0")
	public void Corp_Contract_13_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Verify Email Signed Copy button";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyEmailSignedCopy(templateName, globalVariables.docName2);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify Signed offline tag ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_13_0")
	public void Corp_Contract_13_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Verify Signed offline tag";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifySignedOfflineTag(templateName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify uploaded signed copy document in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_13_0")
	public void Corp_Contract_13_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Verify uploaded signed copy document in email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.sendEmail(templateName, globalVariables.emailSubjectForSignedCorpContracts, globalVariables.emailMessage);

			corpContractsPage.switchToCorpProfilePage();
			
			corpListPage.clickEmailsTab();
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForSignedCorpContracts);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Download the signed copy document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_13_3")
	public void Corp_Contract_13_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");
		

		globalVariables.testCaseDescription = "Corp: Contract: Download the signed copy document from Email List Page";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpListPage.clickEmailsTab();
			
			corpEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForSignedCorpContracts, globalVariables.docName2, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Rename Template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_9")
	public void Corp_Contract_13_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Rename Template";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyRename(templateName, globalVariables.renameCorpContractTemplateName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Corp_ContractsTemplateName", sheetName, "Value", globalVariables.renameCorpContractTemplateName);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Check if Renamed template is available in the used contracts", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_9")
	public void Corp_Contract_14(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: Check if Renamed template is available in the used contracts";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyRename(templateName, globalVariables.renameCorpContractTemplateName);
			
			corpContractsPage.verifyRenameForUsedTemplate(globalVariables.renameCorpContractTemplateName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Corp_ContractsTemplateName", sheetName, "Value", globalVariables.renameCorpContractTemplateName);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: change the uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Contract_9")
	public void Corp_Contract_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Corp_ContractsTemplateName");

		globalVariables.testCaseDescription = "Corp: Contract: change the uploaded document";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyChangeDocumentFunctionality(templateName, globalVariables.contractFilePathForUploadSignedCopy);
			
			corpContractsPage.verifyUpdatedDocument(templateName, globalVariables.docName2);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "corp"}, description = "Corp: Contract: Verify Delete functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Contract_18(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String templateName = "Delete template";

		globalVariables.testCaseDescription = "Corp: Contract: Verify Delete functionality";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickContractsTab();
			
			CM_CorporationContractPage corpContractsPage = new CM_CorporationContractPage(driver);
			
			corpContractsPage.verifyAddTemplateFuctionality(templateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			corpContractsPage.useContractTemplate(templateName);
			
			corpContractsPage.verifyDeleteFunctionality(templateName);
			
			corpContractsPage.switchToCorpProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Verify Breadcrumbs (Client)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Verify Breadcrumbs (Client)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyBreadcrumbs();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Verify Corporation Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "Verify Corporation Details";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyClientAndSponserName(corpName, clientName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	@Test(groups = {"contracts", "client"}, description = "Verify Search Box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Verify Search Box";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifySearchBox(globalVariables.contractsKBTemplate);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Add template in KB and verify on contract page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addContractTemplateInKB")
	public void Client_Contract_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Add template in KB and verify on contract page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyKBTemplateOnContractsPage(globalVariables.contractsTemplateNameAddedInKB);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Check Template description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Check Template description";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyTemplateDescription(globalVariables.contractsKBTemplate, globalVariables.docName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Use the template which is available", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Use the template which is available";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.useContractTemplate(globalVariables.contractsKBTemplate);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	@Test(groups = {"contracts", "client"}, description = "Check if USED label is coming for the templates in use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",  dependsOnMethods = "Client_Contract_6")
	public void Client_Contract_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Check if USED label is coming for the templates in use";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyUsedText(globalVariables.contractsKBTemplate);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	@Test(groups = {"contracts", "client"}, description = "Check if DRAFT label is coming up after using a template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_6")
	public void Client_Contract_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Check if DRAFT label is coming up after using a template";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyDraftText(globalVariables.contractsKBTemplate);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	@Test(groups = {"contracts", "client"}, description = "Add template using ADD TEMPLATE button", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Add template using ADD TEMPLATE button";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyAddTemplateFuctionality(globalVariables.newClientContractTemplateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Client_ContractsTemplateName", sheetName, "Value", globalVariables.newClientContractTemplateName);
			
			clientContractPage.useContractTemplate(globalVariables.newClientContractTemplateName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify document uploaded in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",  dependsOnMethods = "Client_Contract_9")
	public void Client_Contract_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");
		
		globalVariables.testCaseDescription = "Client: Contracts: Verify document uploaded in email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyDocumentInEmailPage(templateName, globalVariables.docName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Send email and verify in email list page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",  dependsOnMethods = "Client_Contract_6")
	public void Client_Contract_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");
		
		globalVariables.testCaseDescription = "Client: Contracts: Send email and verify in email list page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.sendEmail(globalVariables.contractsKBTemplate, globalVariables.emailSubjectForClientContracts, globalVariables.emailMessage);

			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientContractPage.switchToClientProfilePage();
			
			clientListPage.clickEmailsTab();
			
			clientEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForClientContracts);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Download the Document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",  dependsOnMethods = "Client_Contract_11")
	public void Client_Contract_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");
		
		globalVariables.testCaseDescription = "Client: Contracts: Download the Document from Email List Page";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientListPage.clickEmailsTab();
			
			clientEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForClientContracts, globalVariables.docName, driver);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Upload Signed copy functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_9")
	public void Client_Contract_13_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Verify Upload Signed copy functionality";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyUploadedSignedCopy(templateName, globalVariables.contractFilePathForUploadSignedCopy, globalVariables.docName2);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Email Signed Copy button", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_13_0")
	public void Client_Contract_13_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Verify Email Signed Copy button";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifyEmailSignedCopy(templateName, globalVariables.docName2);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Signed offline tag ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_13_0")
	public void Client_Contract_13_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Verify Signed offline tag ";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.verifySignedOfflineTag(templateName);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify uploaded signed copy document in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_13_0")
	public void Client_Contract_13_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Verift uploaded signed copy document in email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
						
			clientContractPage.sendEmail(templateName, globalVariables.emailSubjectForSignedClientContracts, globalVariables.emailMessage);
			
			clientContractPage.switchToClientProfilePage();
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForSignedClientContracts);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Download the signed copy document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",  dependsOnMethods = "Client_Contract_13_3")
	public void Client_Contract_13_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");
		
		globalVariables.testCaseDescription = "Client: Contracts: Download the signed copy document from Email List Page";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientListPage.clickEmailsTab();
			
			clientEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForSignedClientContracts, globalVariables.docName2, driver);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Rename Template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_9")
	public void Client_Contract_13_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Rename Template";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyRename(templateName, globalVariables.renameCorpContractTemplateName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Client_ContractsTemplateName", sheetName, "Value", globalVariables.renameClientContractTemplateName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Check if Renamed template is available in the used contracts", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_9")
	public void Client_Contract_14(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Check if Renamed template is available in the used contracts";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyRename(templateName, globalVariables.renameClientContractTemplateName);
			
			clientContractPage.verifyRenameForUsedTemplate(globalVariables.renameClientContractTemplateName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Client_ContractsTemplateName", sheetName, "Value", globalVariables.renameClientContractTemplateName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Change the uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Contract_9")
	public void Client_Contract_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = readExcel.initTest(workbookName, sheetName, "Client_ContractsTemplateName");

		globalVariables.testCaseDescription = "Client: Contracts: Change the uploaded document";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyChangeDocumentFunctionality(templateName, globalVariables.contractFilePathForUploadSignedCopy);
			
			clientContractPage.verifyUpdatedDocument(templateName, globalVariables.docName2);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Delete functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_18(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		String templateName = "Delete Template";

		globalVariables.testCaseDescription = "Client: Contracts: Change the uploaded document";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyAddTemplateFuctionality(templateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			clientContractPage.useContractTemplate(templateName);
			
			clientContractPage.verifyDeleteFunctionality(templateName);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Employee ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_7")
	public void Client_Contract_19(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Client: Contracts: Verify Employee ID";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyEmployeeId(globalVariables.employeeID);
			
			clientContractPage.switchToClientProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "client"}, description = "Client: Contracts: Verify Employee Status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Contract_20(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Client: Contracts: Verify Employee Status";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);

			clientListPage.clickContractsTab();
			
			CM_ClientContractPage clientContractPage = new CM_ClientContractPage(driver);
			
			clientContractPage.verifyEmployeeDetails();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "case"}, description = "Verify Breadcrumbs (Case)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify Breadcrumbs (Case)";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyBreadcrumbs();

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
	
	@Test(groups = {"contracts", "case"}, description = "Verify Client and Case Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify Client and Case Details";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyClientCaseDetails(caseId, clientName, corpName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	@Test(groups = {"contracts", "case"}, description = "Verify Search Box", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify Search Box";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifySearchBox(globalVariables.contractsKBTemplate);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Add template in KB and verify on contract page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "addContractTemplateInKB")
	public void Case_Contract_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Add template in KB and verify on contract page";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyKBTemplateOnContractsPage(globalVariables.contractsTemplateNameAddedInKB);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	@Test(groups = {"contracts", "case"}, description = "Check Template description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Check Template description";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyTemplateDescription(globalVariables.contractsKBTemplate, globalVariables.docName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Use the template which is available ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Use the template which is available";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.useContractTemplate(globalVariables.contractsKBTemplate);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Check if USED label is coming for the templates in use", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_6")
	public void Case_Contract_7(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Check if USED label is coming for the templates in use";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyUsedText(globalVariables.contractsKBTemplate);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Check if DRAFT label is coming up after using a template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_6")
	public void Case_Contract_8(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Check if DRAFT label is coming up after using a template";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyDraftText(globalVariables.contractsKBTemplate);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Add template using ADD TEMPLATE button", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_9(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Add template using ADD TEMPLATE button";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyAddTemplateFuctionality(globalVariables.newCaseContractTemplateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Case_ContractsTemplateName", sheetName, "Value", globalVariables.newCaseContractTemplateName);
			
			caseContractsPage.useContractTemplate(globalVariables.newCaseContractTemplateName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Verify document uploaded in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_9")
	public void Case_Contract_10(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contracts: Verify document uploaded in email page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyDocumentInEmailPage(templateName, globalVariables.docName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "case"}, description = "Send email and verify in email list page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_6")
	public void Case_Contract_11(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contracts: Send email and verify in email list page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.sendEmail(globalVariables.contractsKBTemplate, globalVariables.emailSubjectForCaseContracts, globalVariables.emailMessage);

			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseContractsPage.switchToCaseProfilePage();
			
			caseListPage.clickEmailsTab();
			
			caseEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForCaseContracts);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "case"}, description = "Download the Document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_11")
	public void Case_Contract_12(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contracts: Download the Document from Email List Page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);

			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseListPage.clickEmailsTab();
			
			caseEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForCaseContracts, globalVariables.docName, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify Upload Signed copy functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_9")
	public void Case_Contract_13_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify Upload Signed copy functionality";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyUploadedSignedCopy(templateName, globalVariables.contractFilePathForUploadSignedCopy, globalVariables.docName2);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify Email Signed Copy buttony", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_13_0")
	public void Case_Contract_13_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify Email Signed Copy button";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyEmailSignedCopy(templateName, globalVariables.docName2);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify Signed offline tag ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_13_0")
	public void Case_Contract_13_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify Signed offline tag ";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifySignedOfflineTag(templateName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify uploaded signed copy document in email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_13_0")
	public void Case_Contract_13_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify uploaded signed copy document in email page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.sendEmail(templateName, globalVariables.emailSubjectForSignedCaseContracts, globalVariables.emailMessage);
			
			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseContractsPage.switchToCaseProfilePage();
			
			caseListPage.clickEmailsTab();
			
			caseEmailPage.verifySentContractsEmail(globalVariables.emailSubjectForCaseContracts);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "case"}, description = "Download the signed copy document from Email List Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_13_3")
	public void Case_Contract_13_5(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contracts: Download the signed copy document from Email List Page";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);

			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
			
			caseListPage.clickEmailsTab();
			
			caseEmailPage.downloadAndVerifyUploadedDocument(globalVariables.emailSubjectForSignedCaseContracts, globalVariables.docName2, driver);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Rename Template", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "Case_Contract_9")
	public void Case_Contract_13_6(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Rename Template";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyRename(templateName, globalVariables.renameCaseContractTemplateName);

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Case_ContractsTemplateName", sheetName, "Value", globalVariables.renameCaseContractTemplateName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Check if Renamed template is available in the used contracts", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_9")
	public void Case_Contract_14(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Check if Renamed template is available in the used contractse";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			//caseContractsPage.useContractTemplate(templateName);
			
			caseContractsPage.verifyRename(templateName, globalVariables.renameCaseContractTemplateName);
			
			caseContractsPage.verifyRenameForUsedTemplate(globalVariables.renameCaseContractTemplateName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("Case_ContractsTemplateName", sheetName, "Value", globalVariables.renameCaseContractTemplateName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Change the uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Contract_9")
	public void Case_Contract_16(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Change the uploaded document";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = readExcel.initTest(workbookName, sheetName, "Case_ContractsTemplateName");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyChangeDocumentFunctionality(templateName, globalVariables.contractFilePathForUploadSignedCopy);
			
			caseContractsPage.verifyUpdatedDocument(templateName, globalVariables.docName2);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify Delete functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_18(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify Delete functionality";
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String templateName = "Delete Template";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyAddTemplateFuctionality(templateName, globalVariables.newContractTemplateDescription, globalVariables.contractFilePath);
			
			caseContractsPage.useContractTemplate(templateName);
			
			caseContractsPage.verifyDeleteFunctionality(templateName);
			
			caseContractsPage.switchToCaseProfilePage();
			
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
	
	
	@Test(groups = {"contracts", "case"}, description = "Case: Contract: Verify Country", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Contract_19(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Case: Contract: Verify Country";
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
			
			caseListPage.clickContractsTab();
			
			CM_CaseContractPage caseContractsPage = new CM_CaseContractPage(driver);
			
			caseContractsPage.verifyCountry(globalVariables.countryName, globalVariables.countryflag);

			Log.testCaseResult();
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