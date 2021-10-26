package com.inszoomapp.testscripts;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
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
import com.inszoomapp.pages.CM_AdvanceSettings;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_CorporationEmailPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationQuestionnairePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.FNP_MessagesPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class OrgSettingsTestScript extends BaseTest
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
	
	
//	@BeforeMethod(alwaysRun = true)
//	public void skipTestCase()
//	{
//			if(globalVariables.SKIP_REMAINING_TESTS)
//			{
//				throw new SkipException("Skipping the testCase");
//			}
//	}
	
	
	@Test(groups = {"settings"}, description = "Set Default System Email Acess  as Firm Only", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_1_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Default System Email Acess  as Firm Only";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandEmail();
			
			advanceSettings.clickOnDefaultEmailAccessTab();
			
			advanceSettings.setDefaultEmailAccess("Firm Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Firm Only on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_1_0")
	public void Settings_Email_1_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

		globalVariables.testCaseDescription = "Verify Default Email Access as Firm Only on aty_case_email.aspx (Case->Email)";

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
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.verifyExtranetAccess("Firm Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Firm Only on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_1_0")
	public void Settings_Email_1_1_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Firm Only on aty_client_email.aspx (Client->Email))";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifyExtranetAccess("Firm Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Firm Only on epr_client_email_add.aspx (Corp->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_1_0")
	public void Settings_Email_1_1_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Firm Only on epr_client_email_add.aspx (Corp->Email)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickEmailsTab();
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpEmailPage.clickComposeEmail();
			
			corpEmailPage.verifyExtranetAccess("Firm Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Firm Only on  aty_email_epr_quest.aspx(Corp->Questionnaire->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_1_0")
	public void Settings_Email_1_1_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Firm Only on  aty_email_epr_quest.aspx(Corp->Questionnaire->Email)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corpQuestionnaire = new CM_CorporationQuestionnairePage(driver);
			
			corpQuestionnaire.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpEmailPage.verifyExtranetAccess("Firm Only");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Default System Email Acess  as Corporation Only", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_1_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Default System Email Acess  as Corporation Only";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandEmail();
			
			advanceSettings.clickOnDefaultEmailAccessTab();
			
			advanceSettings.setDefaultEmailAccess("Corporation Only");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Corporation Only on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_2_0")
	public void Settings_Email_1_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

		globalVariables.testCaseDescription = "Verify Default Email Access as Corporation Only on aty_case_email.aspx (Case->Email)";

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
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.verifyExtranetAccess("Corporation Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Corporation Only on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_2_0")
	public void Settings_Email_1_2_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Corporation Only on aty_client_email.aspx (Client->Email))";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifyExtranetAccess("Corporation Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Corporation Only on epr_client_email_add.aspx (Corp->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_2_0")
	public void Settings_Email_1_2_3(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Corporation Only on epr_client_email_add.aspx (Corp->Email)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickEmailsTab();
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpEmailPage.clickComposeEmail();
			
			corpEmailPage.verifyExtranetAccess("Corporation Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Corporation Only on  aty_email_epr_quest.aspx(Corp->Questionnaire->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_2_0")
	public void Settings_Email_1_2_4(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Corporation Only on  aty_email_epr_quest.aspx(Corp->Questionnaire->Email)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corpQuestionnaire = new CM_CorporationQuestionnairePage(driver);
			
			corpQuestionnaire.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corpEmailPage = new CM_CorporationEmailPage(driver);
			
			corpEmailPage.verifyExtranetAccess("Corporation Only");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Default System Email Acess  as Client only", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_1_3_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Default System Email Acess  as Client only";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandEmail();
			
			advanceSettings.clickOnDefaultEmailAccessTab();
			
			advanceSettings.setDefaultEmailAccess("Client Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Client Only on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_3_0")
	public void Settings_Email_1_3_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

		globalVariables.testCaseDescription = "Verify Default Email Access as Client Only on aty_case_email.aspx (Case->Email)";

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
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.verifyExtranetAccess("Client Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as Client Only on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_3_0")
	public void Settings_Email_1_3_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

		globalVariables.testCaseDescription = "Verify Default Email Access as Client Only on aty_client_email.aspx (Client->Email))";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifyExtranetAccess("Client Only");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Default System Email Acess  as All", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_1_4_0(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Default System Email Acess  as All";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandEmail();
			
			advanceSettings.clickOnDefaultEmailAccessTab();
			
			advanceSettings.setDefaultEmailAccess("All");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as All on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_4_0")
	public void Settings_Email_1_4_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

		globalVariables.testCaseDescription = "Verify Default Email Access as All on aty_case_email.aspx (Case->Email)";

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
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.verifyExtranetAccess("All");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email Access as All on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_1_4_0")
	public void Settings_Email_1_4_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

		globalVariables.testCaseDescription = "Verify Default Email Access as All on aty_client_email.aspx (Client->Email))";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifyExtranetAccess("All");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Send Email CC for Client supervisor as Yes", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_3_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Send Email CC for Client supervisor as Yes";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setSendEmailCCtoSupervisor("Yes");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Supervisor email on Client Email page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_3_1_0")
	public void Settings_Email_3_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");
		String supervisorEmailID = readExcel.initTest(workbookName, sheetName, "supervisorEmailID");
				
		globalVariables.testCaseDescription = "Verify Supervisor email on Client Email page";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifySupervisorEmailInCC(supervisorEmailID);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"settings"}, description = "Set Send Email CC for Client supervisor as No", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_3_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Send Email CC for Client supervisor as No";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setSendEmailCCtoSupervisor("No");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify NO Supervisor email present on Client Email page in CC", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_3_2_0")
	public void Settings_Email_3_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");
		String supervisorEmailID = readExcel.initTest(workbookName, sheetName, "supervisorEmailID");
				
		globalVariables.testCaseDescription = "Verify NO Supervisor email present on Client Email page in CC";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientFirstName, true);
			
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
			
			clientEmailPage.clickComposeEmailButton();
			
			clientEmailPage.verifySupervisorEmailInCC("");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"settings"}, description = "Set 'Restrict Client ability to view or select email of ' as None", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set 'Restrict Client ability to view or select email of ' as None";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setRestrictClientAbilityViewOrSelectEmailOf("None");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify 'Restrict Client ability to view or select email of ' as None in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify 'Restrict Client ability to view or select email of ' as None in FNP";

		String fnp_userID = readExcel.initTest(workbookName, sheetName, "FNP_UserName");
		String fnp_password = readExcel.initTest(workbookName, sheetName, "FNP_Password");
		
		String caseManagerEmailID = readExcel.initTest(workbookName, sheetName, "CaseManagerEmailID");
		String vendorCaseManagerEmailID = readExcel.initTest(workbookName, sheetName, "VendorCaseManagerEmailID");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnp_userID, fnp_password, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
			
			fnpMessagePage.verifyRestrictClientAbilityViewOrSelectEmailOf("None", caseManagerEmailID, vendorCaseManagerEmailID);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set 'Restrict Client ability to view or select email of ' as Case Managers", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set 'Restrict Client ability to view or select email of ' as Case Managers";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setRestrictClientAbilityViewOrSelectEmailOf("Case Managers");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify 'Restrict Client ability to view or select email of ' as Case Managers in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify 'Restrict Client ability to view or select email of ' as Case Managers in FNP";

		String fnp_userID = readExcel.initTest(workbookName, sheetName, "FNP_UserName");
		String fnp_password = readExcel.initTest(workbookName, sheetName, "FNP_Password");
		
		String caseManagerEmailID = readExcel.initTest(workbookName, sheetName, "CaseManagerEmailID");
		String vendorCaseManagerEmailID = readExcel.initTest(workbookName, sheetName, "VendorCaseManagerEmailID");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnp_userID, fnp_password, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
			
			fnpMessagePage.verifyRestrictClientAbilityViewOrSelectEmailOf("Case Managers", caseManagerEmailID, vendorCaseManagerEmailID);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set 'Restrict Client ability to view or select email of ' as Vendor Case Managers", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_3_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set 'Restrict Client ability to view or select email of ' as Vendor Case Managers";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setRestrictClientAbilityViewOrSelectEmailOf("Vendor Case Managers");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify 'Restrict Client ability to view or select email of ' as Vendor Case Managers in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_3_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify 'Restrict Client ability to view or select email of ' as Vendor Case Managers in FNP";

		String fnp_userID = readExcel.initTest(workbookName, sheetName, "FNP_UserName");
		String fnp_password = readExcel.initTest(workbookName, sheetName, "FNP_Password");
		
		String caseManagerEmailID = readExcel.initTest(workbookName, sheetName, "CaseManagerEmailID");
		String vendorCaseManagerEmailID = readExcel.initTest(workbookName, sheetName, "VendorCaseManagerEmailID");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnp_userID, fnp_password, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
			
			fnpMessagePage.verifyRestrictClientAbilityViewOrSelectEmailOf("Vendor Case Managers", caseManagerEmailID, vendorCaseManagerEmailID);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set 'Restrict Client ability to view or select email of ' as Both", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_4_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set 'Restrict Client ability to view or select email of ' as Both";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.setRestrictClientAbilityViewOrSelectEmailOf("Both");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify 'Restrict Client ability to view or select email of ' as Both in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_4_4_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Verify 'Restrict Client ability to view or select email of ' as Both in FNP";

		String fnp_userID = readExcel.initTest(workbookName, sheetName, "FNP_UserName");
		String fnp_password = readExcel.initTest(workbookName, sheetName, "FNP_Password");
		
		String caseManagerEmailID = readExcel.initTest(workbookName, sheetName, "CaseManagerEmailID");
		String vendorCaseManagerEmailID = readExcel.initTest(workbookName, sheetName, "VendorCaseManagerEmailID");
		
		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnp_userID, fnp_password, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
			
			fnpMessagePage.verifyRestrictClientAbilityViewOrSelectEmailOf("Both", caseManagerEmailID, vendorCaseManagerEmailID);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"settings"}, description = "Set Form Access Type as Edit Form - PDF ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_4_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Form Access Type as Edit Form - PDF ";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setFormAccessType("Edit Form - PDF");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Form Access Type as Edit Form - PDF (on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_4_1_0")
	public void Settings_Forms_4_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Form Access Type as Edit Form - PDF (on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultFormAccessType(formName, "Edit Form");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Form Access Type as Email as Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_4_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Form Access Type as Email as Questionnaire";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setFormAccessType("Email as Questionnaire");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Form Access Type as Email as Questionnaire (on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_4_2_0")
	public void Settings_Forms_4_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Form Access Type as Email as Questionnaire (on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultFormAccessType(formName, "Email as Questionnaire");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Form Access Type as Print Form - PDF", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_4_3_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Form Access Type as Print Form - PDF";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setFormAccessType("Print Form - PDF");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Form Access Type as Print Form (on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_4_3_0")
	public void Settings_Forms_4_3_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Form Access Type as Print Form (on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultFormAccessType(formName, "Print Form");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Set Form Access Type as As Attachment (read only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_4_4_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Set Form Access Type as As Attachment (read only)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setFormAccessType("As Attachment (read only)");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Form Access Type as As Attachment (read only)(on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_4_4_0")
	public void Settings_Forms_4_4_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Form Access Type as As Attachment (read only)(on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultFormAccessType(formName, "As Attachment");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Set Track Changes as YES", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_6_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Set Track Changes as YES";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setTrackChanges("Yes");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Track Changes as YES(on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_6_1_0")
	public void Settings_Forms_6_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Track Changes as YES(on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultTrackChanges(formName, "Yes");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Set Track Changes as NO", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_6_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Set Track Changes as NO";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setTrackChanges("No");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Track Changes as NO(on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_6_2_0")
	public void Settings_Forms_6_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "AR-11";

		globalVariables.testCaseDescription = "Verify Track Changes as NO(on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultTrackChanges(formName, "No");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Set Efiling Enabled as YES", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_7_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Set Efiling Enabled as YES";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setEfilingEnabled("Yes");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Efiling Enabled as YES(on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_7_1_0")
	public void Settings_Forms_7_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "DS-160";

		globalVariables.testCaseDescription = "Verify Efiling Enabled as YES(on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultEfilingEnable(formName, "Yes");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Set Efiling Enabled as NO", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_7_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Set Efiling Enabled as NO";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
			
			advanceSettings.setEfilingEnabled("No");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Verify Efiling Enabled as NO(on Case -> Emails page)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_7_2_0")
	public void Settings_Forms_7_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = "DS-160";

		globalVariables.testCaseDescription = "Verify Efiling Enabled as NO(on Case -> Emails page)";

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
			
			caseEmailPage.verifyDefaultEfilingEnable(formName, "No");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Allow user to change settings while emailing as YES", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_8_1_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Allow user to change settings while emailing as YES";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("Yes");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Allow user to change settings while emailing as YES on Case Emails Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_8_1_0")
	public void Settings_Forms_8_1_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = readExcel.initTest(workbookName, sheetName, "FormName");
		String formNameForEfiling = readExcel.initTest(workbookName, sheetName, "FormNameForEfiling");

		globalVariables.testCaseDescription = "Allow user to change settings while emailing as YES on Case Emails Page";

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
			
			caseEmailPage.verifyEnableUserToChangeSettingsLocked("Yes", formName, formNameForEfiling, globalVariables.formAccessTypes.get(globalVariables.formAccessType), globalVariables.trackChanges, globalVariables.enableEfiling);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"settings"}, description = "Settings : Form Email Settings : Allow user to change settings while emailing as NO", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_8_2_0(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Settings : Form Email Settings : Allow user to change settings while emailing as NO";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickFormEmailSettingsTab();
			
			advanceSettings.setAllowUserToMakeChangesSettingWhileEmailing("No");
		
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"settings"}, description = "Allow user to change settings while emailing as NO on Case Emails Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_8_2_0")
	public void Settings_Forms_8_2_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
		String formName = readExcel.initTest(workbookName, sheetName, "FormName");
		String formNameForEfiling = readExcel.initTest(workbookName, sheetName, "FormNameForEfiling");

		globalVariables.testCaseDescription = "Allow user to change settings while emailing as NO on Case Emails Page";

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
			
			caseEmailPage.verifyEnableUserToChangeSettingsLocked("No", formName, formNameForEfiling, globalVariables.formAccessTypes.get(globalVariables.formAccessType), globalVariables.trackChanges, globalVariables.enableEfiling);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Default Email for employee as 'None'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_2_1_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Default System Email Acess  as 'None'";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.changeDefaultEmployeeForEmployee("None");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'None' on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_1_0")
    public void Settings_Email_2_1_1(String browser) throws Exception 
	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'None' on aty_case_email.aspx (Case->Email)";

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
            
            CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
            
            caseEmailsPage.clickComposeEmailButton();
            
            caseEmailsPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "None");
           
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as None on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_1_0")
    public void Settings_Email_2_1_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

        globalVariables.testCaseDescription = "Verify Default Email for employee as None on aty_client_email.aspx (Client->Email))";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
            
            clientListPage.clickOnClientName(clientFirstName, true);
            
            clientListPage.clickEmailsTab();
            
            CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
            
            clientEmailPage.clickComposeEmailButton();
            
            clientEmailPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "None");
    
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Set Default Email for employee as 'Main Email'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_2_2_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Default System Email Acess  as 'Main Email'";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.changeDefaultEmployeeForEmployee("Main Email");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Main Email' on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_2_0")
    public void Settings_Email_2_2_1(String browser) throws Exception 
	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Main Email' on aty_case_email.aspx (Case->Email)";

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
            
            CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
            
            caseEmailsPage.clickComposeEmailButton();
            
            caseEmailsPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Main Email");
            
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Main Email' on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_2_0")
    public void Settings_Email_2_2_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Main Email' on aty_client_email.aspx (Client->Email))";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
            
            clientListPage.clickOnClientName(clientFirstName, true);
            
            clientListPage.clickEmailsTab();
            
            CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
            
            clientEmailPage.clickComposeEmailButton();
            
            clientEmailPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Main Email");
    
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Set Default Email for employee as 'Alternate Email'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_2_3_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Default System Email Acess  as 'Alternate Email'";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.changeDefaultEmployeeForEmployee("Alternate Email");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Alternate Email' on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_3_0")
    public void Settings_Email_2_3_1(String browser) throws Exception 
	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Alternate Email' on aty_case_email.aspx (Case->Email)";

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
            
            CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
            
            caseEmailsPage.clickComposeEmailButton();
            
            caseEmailsPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Alternate Email");
            
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Alternate Email' on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_3_0")
    public void Settings_Email_2_3_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Alternate Email' on aty_client_email.aspx (Client->Email))";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
            
            clientListPage.clickOnClientName(clientFirstName, true);
            
            clientListPage.clickEmailsTab();
            
            CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
            
            clientEmailPage.clickComposeEmailButton();
            
            clientEmailPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Alternate Email");
    
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Set Default Email for employee as 'Main and Alternate Email'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Email_2_4_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Default System Email Acess  as 'Main and Alternate Email'";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.clickOnEmailTab();
			
			advanceSettings.changeDefaultEmployeeForEmployee("Main and Alternate Email");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Main and Alternate Email' on aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_4_0")
    public void Settings_Email_2_4_1(String browser) throws Exception 
	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Main and Alternate Email' on aty_case_email.aspx (Case->Email)";

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
            
            CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
            
            caseEmailsPage.clickComposeEmailButton();
            
            caseEmailsPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Main and Alternate Email");
            
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Verify Default Email for employee as 'Main and Alternate Email' on aty_client_email.aspx (Client->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Email_2_4_0")
    public void Settings_Email_2_4_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

        globalVariables.testCaseDescription = "Verify Default Email for employee as 'Main and Alternate Email' on aty_client_email.aspx (Client->Email))";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
            
            clientListPage.clickOnClientName(clientFirstName, true);
            
            clientListPage.clickEmailsTab();
            
            CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
            
            clientEmailPage.clickComposeEmailButton();
            
            clientEmailPage.verifyDefaultEmailForEmployee(workbookName, sheetName, "Main and Alternate Email");
    
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Change Default Access Period", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_1_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Change Default Access Period";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickOnDefaultAccessPeriod();
			
			advanceSettings.changeAccessPeriod(globalVariables.accessPeriod);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify 'Default Access Period' in aty_client_email.aspx (Client -> Emails)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_1_0")
    public void Settings_Forms_1_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstName");

        globalVariables.testCaseDescription = "Verify 'Default Access Period' in aty_client_email.aspx (Client -> Emails)";

        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);
            
            caseManagerDashboardPage.clickClientTab(true);
            
            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
            
            clientListPage.clickOnClientName(clientFirstName, true);
            
            clientListPage.clickEmailsTab();
            
            CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
            
            clientEmailPage.clickComposeEmailButton();
            
            clientEmailPage.verifyAccessDate(globalVariables.accessPeriod);
    
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Verify 'Default Access Period' in aty_case_email.aspx (Case->Email)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Settings_Forms_1_0")
    public void Settings_Forms_1_2(String browser) throws Exception 
	{
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");

        globalVariables.testCaseDescription = "Verify 'Default Access Period' in aty_case_email.aspx (Case->Email)";

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
            
            CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
            
            caseEmailsPage.clickComposeEmailButton();
            
            caseEmailsPage.verifyAccessDate(globalVariables.accessPeriod);
            
            Log.testCaseResult();
        } 
        catch (Exception e) {
            Log.exception(e, driver);
        } 
        finally {
            Log.endTestCase();
            driver.quit();
        }
    }
	
	
	@Test(groups = {"settings"}, description = "Set Font Size for Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_9_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Font Size for Forms";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickOnFormFontSettings();
			
			advanceSettings.changeFontSize(globalVariables.fontSize);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify if changed Font Size is reflected on Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Settings_Forms_9_0")
	public void Settings_Forms_9_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
        String formName = readExcel.initTest(workbookName, sheetName, "FormName");
        
		globalVariables.testCaseDescription = "Verify if changed Font Size is reflected on Forms";
		final WebDriver driver = WebDriverFactory.get(browser);	
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
            
            caseListPage.clickOnCaseId(caseId, true);
            
            caseListPage.clickForms4_0Tab();
            
            CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
            
            caseFormPage.clickFormOn4_0(formName);
            
            caseFormPage.verifyFontSize(globalVariables.fontSize);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set login attempts and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Security_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		globalVariables.testCaseDescription = "Set login attempts and verify";
		final WebDriver driver = WebDriverFactory.get(browser);	
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String CM_userNameAlt = readExcel.initTest(workbookName, sheetName, "CM_userNameAlt");

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandSecuritySettings();
			
			advanceSettings.clickOnSetup();
			
			advanceSettings.changeInvalidLoginAttemptsAllowed(globalVariables.randomInvalidAttempt);
			
			driver.get(webSite);
			
			login.enterWrongCredentialsAndVerify(CM_userNameAlt, globalVariables.randomInvalidAttempt);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Check if locked users are present in 'Locked Users' under 'Advance Settings', unlock and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Settings_Security_1")
	public void Settings_Security_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		globalVariables.testCaseDescription = "Check if locked users are present in 'Locked Users' under 'Advance Settings', unlock and verify";
		final WebDriver driver = WebDriverFactory.get(browser);	
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String CM_userNameAlt = readExcel.initTest(workbookName, sheetName, "CM_userNameAlt");
		String CM_passwordAlt = readExcel.initTest(workbookName, sheetName, "CM_passwordAlt");
		String userNameAlt = readExcel.initTest(workbookName, sheetName, "userNameAlt");

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandSecuritySettings();
			
			advanceSettings.clickOnLockedUsers();
			
			advanceSettings.verifyLockedUser(userNameAlt);
			
			advanceSettings.unlockUser(userNameAlt);
			
			driver.get(webSite);
			
			login.caseManagerlogin(CM_userNameAlt, CM_passwordAlt, true);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Signature in addendum to YES", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_2_1_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Signature in addendum to YES";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickOnFormAddendumSettings();
			
			advanceSettings.changeSignatureInAddendum("Yes");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify if Signature on Addendum is selected by default on Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Settings_Forms_2_1_0")
	public void Settings_Forms_2_1_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
        String formName = readExcel.initTest(workbookName, sheetName, "FormName");
        
		globalVariables.testCaseDescription = "Verify if Signature on Addendum is selected by default on Forms";
		final WebDriver driver = WebDriverFactory.get(browser);	
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
            
            caseListPage.clickOnCaseId(caseId, true);
            
            caseListPage.clickForms4_0Tab();
            
            CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
            
            caseFormPage.addForm4_0(globalVariables.formForSignature);
            
            caseFormPage.clickFormOn4_0(globalVariables.formForSignature);
            
            caseFormPage.verifySignatureOnAddendum("Yes");
            
            caseFormPage.deleteForm4_0(globalVariables.formForSignature);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Set Signature in addendum to NO", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Settings_Forms_2_2_0(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Set Signature in addendum to NO";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickSettings(true);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			settingsPage.clickAdvancedSettingsTab();
			
			CM_AdvanceSettings advanceSettings = new CM_AdvanceSettings(driver);
			
			advanceSettings.expandOrgSettings();
			
			advanceSettings.expandForms();
			
			advanceSettings.clickOnFormAddendumSettings();
			
			advanceSettings.changeSignatureInAddendum("No");
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"settings"}, description = "Verify if Signature on Addendum is NOT selected by default on Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Settings_Forms_2_2_0")
	public void Settings_Forms_2_2_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String caseId = readExcel.initTest(workbookName, sheetName, "CaseID");
        String formName = readExcel.initTest(workbookName, sheetName, "FormName");
        
		globalVariables.testCaseDescription = "Verify if Signature on Addendum is NOT selected by default on Forms";
		final WebDriver driver = WebDriverFactory.get(browser);	
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
            
            caseListPage.clickOnCaseId(caseId, true);
            
            caseListPage.clickForms4_0Tab();
            
            CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
            
            caseFormPage.addForm4_0(globalVariables.formForSignature);
            
            caseFormPage.clickFormOn4_0(globalVariables.formForSignature);
            
            caseFormPage.verifySignatureOnAddendum("No");
            
            caseFormPage.deleteForm4_0(globalVariables.formForSignature);
			
			Log.testCaseResult();
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