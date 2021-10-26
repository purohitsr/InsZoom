package com.inszoomapp.testscripts;

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
import com.inszoomapp.pages.CM_CMPSearch;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CaseQuestionnairePage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientProfilePage;
import com.inszoomapp.pages.CM_ClientQuestionnairePage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_CorporationQuestionnairePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_ProspectiveProfilePage;
import com.inszoomapp.pages.CM_ProspectsListPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.CM_ToDoPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class CMPSearchTestScript extends BaseTest
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Create a corporation with Corporation Name and Corporation ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CorpPrerequisite(String browser) throws Exception {

		globalVariables.testCaseDescription = "Create a corporation with Corporation Name and Corporation ID";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationPage corpListPage = new CM_CorporationPage(driver);
			
			corpListPage.createNewCorp(workbookNameWrite, sheetName, globalVariables.cmpSearchCorporationName, globalVariables.cmpSearchsigningPersonFirstName, globalVariables.cmpSearchsigningPersonLastName, globalVariables.cmpSearchsigningPersonEmailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporation name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpPrerequisite")
	public void Corp_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporation name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPersonName = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String  corporationID = readExcel.initTest(workbookName, sheetName, "CorporationID");
		String emailID = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonEmailID");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpName("Corporation", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpName("All", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporation id", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpPrerequisite")
	public void Corp_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporation id";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPersonName = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String  corporationID = readExcel.initTest(workbookName, sheetName, "CorporationID");
		String emailID = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonEmailID");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpID("Corporation", corporationID, signingPersonName, corporationID, corporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpID("All", corporationID, signingPersonName, corporationID, corporationName, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Edit corporation name and verify search box using new corp name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpPrerequisite")
	public void Corp_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Edit corporation name and verify search box using new corp name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPersonName = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String  corporationID = readExcel.initTest(workbookName, sheetName, "CorporationID");
		String emailID = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonEmailID");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.editCorporationName(globalVariables.cmpSearchEdittedCorporationName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("ALoginSavedCorporationName", sheetName, "Value", globalVariables.cmpSearchEdittedCorporationName);
			
			corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpName("Corporation", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpName("All", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Inactivate the corporation and search using the corp name (result should not come up)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpPrerequisite")
	public void Corp_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Inactivate the corporation and search using the corp name (result should not come up)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPersonName = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String  corporationID = readExcel.initTest(workbookName, sheetName, "CorporationID");
		String emailID = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonEmailID");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.inactivateCorporation();
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingInvalidData("Corporation", corporationName);
			
			cmpSearchPage.verifySearchUsingInvalidData("All", corporationName);
			
			corpListPage.activateCorporation();
			
			cmpSearchPage.verifySearchUsingCorpName("Corporation", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpName("All", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Delete the corporation and search using the corp name (result should not come up)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpPrerequisite")
	public void Corp_CMP_Search_5(String browser) throws Exception {

		globalVariables.testCaseDescription = "Delete the corporation and search using the corp name (result should not come up)";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String signingPersonName = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonTxt");
		String  corporationID = readExcel.initTest(workbookName, sheetName, "CorporationID");
		String emailID = readExcel.initTest(workbookName, sheetName, "ALoginCaseManagerSigningPersonEmailID");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpName("Corporation", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpName("All", corporationName, signingPersonName, corporationID, corporationName, emailID);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.deleteCorporation();
			
			cmpSearchPage.verifySearchUsingInvalidData("Corporation", corporationName);
			
			cmpSearchPage.verifySearchUsingInvalidData("All", corporationName);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Create Individual Client with Name, Email, File Number, Alien Number, Date of Birth", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ClientPrerequisite_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Create Individual Client with Name, Email, File Number, Alien Number, Date of Birth";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickAddClientButton(true);
			
			clientListPage.addIndividualClient(workbookNameWrite, sheetName, globalVariables.cmpSearchIndividualClientFirstName, globalVariables.cmpSearchIndividualClientLastName, globalVariables.cmpSearchClientEmail);
			
			clientListPage.clickProfileTab();
			
			CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
			
			clientProfilePage.updateFileNumber(globalVariables.cmpSearchClientFileNumber);
			
			clientProfilePage.updateAlienNumberAndDOB(globalVariables.cmpSearchIndividualClientAlienNumber, globalVariables.day, globalVariables.month, globalVariables.year);
			
			clientListPage.getLoginID();
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("IndividualClientFileNumber", sheetName, "Value", globalVariables.cmpSearchClientFileNumber);
			writeExcel.setCellData("IndividualClientAlienNumber", sheetName, "Value", globalVariables.cmpSearchIndividualClientAlienNumber);
			writeExcel.setCellData("ClientLoginID", sheetName, "Value", globalVariables.clientLoginID);
			writeExcel.setCellData("ClientDOB", sheetName, "Value", globalVariables.historyDateFNP);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using individual client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_1")
	public void Client_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using individual client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "IndividualClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientAlienNumber");
		String applicantType = "Main Contact";
		String clientType = "Individual/Family Based";
		String dob = readExcel.initTest(workbookName, sheetName, "ClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "ClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientName("","Client", clientName, firstName, lastName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientName("","All", clientName, firstName, lastName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using individual client email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_1")
	public void Client_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using individual client email";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "IndividualClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientAlienNumber");
		String applicantType = "Main Contact";
		String clientType = "Individual/Family Based";
		String dob = readExcel.initTest(workbookName, sheetName, "ClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "ClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientEmail("","Client", email, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientEmail("","All", email, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using individual client File Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_1")
	public void Client_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using individual client File Number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "IndividualClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientAlienNumber");
		String applicantType = "Main Contact";
		String clientType = "Individual/Family Based";
		String dob = readExcel.initTest(workbookName, sheetName, "ClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "ClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientFileNumber("", "Client", fileNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientFileNumber("", "All", fileNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using individual client Alien Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_1")
	public void Client_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using individual client Alien Number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "IndividualClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientAlienNumber");
		String applicantType = "Main Contact";
		String clientType = "Individual/Family Based";
		String dob = readExcel.initTest(workbookName, sheetName, "ClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "ClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientAlienNumber("", "Client", alienNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientAlienNumber("", "All", alienNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
//               PARKED
// Search does not give any results when searched with DOB	
//	@Test(groups = {"cmpsearch"}, description = "Verify search using individual client DOB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_1")
//	public void Client_CMP_Search_5(String browser) throws Exception {
//
//		globalVariables.testCaseDescription = "Verify search using individual client DOB";
//
//		final WebDriver driver = WebDriverFactory.get(browser);
//		
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
//		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
//		String email = readExcel.initTest(workbookName, sheetName, "IndividualClientEmailID");
//		String fileNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientFileNumber");
//		String alienNumber = readExcel.initTest(workbookName, sheetName, "IndividualClientAlienNumber");
//		String applicantType = "Main Contact";
//		String clientType = "Individual/Family Based";
//		String dob = readExcel.initTest(workbookName, sheetName, "ClientDOB");
//		String clientID = readExcel.initTest(workbookName, sheetName, "ClientLoginID");
//		String clientName = firstName + " " + lastName ; 
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//		try {
//
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//			
//			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
//			
//			cmpSearchPage.verifySearchUsingClientDOB("", "Client", dob, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			cmpSearchPage.verifySearchUsingClientDOB("", "All", dob, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			caseManagerDashboardPage.clickLogout(true);
//			
//			Log.testCaseResult();
//		} 
//		catch (Exception e) {
//			Log.exception(e, driver);
//		} 
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	
	
	
	@Test(groups = {"cmpsearch"}, description = "Create Corporate Client with Name, Email, File Number, Corporation, Alien Number, Date of Birth", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ClientPrerequisite_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Create Corporate Client with Name, Email, File Number, Corporation, Alien Number, Date of Birth";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickAddClientButton(true);
			
			clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, globalVariables.cmpSearchCorporationNameForCorpClient, globalVariables.cmpSearchCorporationClientFirstName, globalVariables.cmpSearchCorporationClientLastName, globalVariables.cmpSearchClientEmailID);
			
			clientListPage.clickSaveClientButton(true);
			
			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);
			
			clientListPage.clickProfileTab();
			
			CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
			
			clientProfilePage.updateFileNumber(globalVariables.cmpSearchClientFileNumber);
			
			clientProfilePage.updateAlienNumberAndDOB(globalVariables.cmpSearchCorporateClientAlienNumber, globalVariables.day, globalVariables.month, globalVariables.year);
			
			clientListPage.getLoginID();
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("CorporateClientFirstName", sheetName, "Value", globalVariables.cmpSearchCorporationClientFirstName);
			writeExcel.setCellData("CorporateClientLastName", sheetName, "Value", globalVariables.cmpSearchCorporationClientLastName);
			writeExcel.setCellData("CorporationNameForCorporateClient", sheetName, "Value", globalVariables.cmpSearchCorporationNameForCorpClient);
			writeExcel.setCellData("CorporateClientEmailID", sheetName, "Value", globalVariables.cmpSearchClientEmailID);
			writeExcel.setCellData("CorporateClientFileNumber", sheetName, "Value", globalVariables.cmpSearchClientFileNumber);
			writeExcel.setCellData("CorporateClientAlienNumber", sheetName, "Value", globalVariables.cmpSearchCorporateClientAlienNumber);
			writeExcel.setCellData("CorporateClientLoginID", sheetName, "Value", globalVariables.clientLoginID);
			writeExcel.setCellData("CorporateClientDOB", sheetName, "Value", globalVariables.historyDateFNP);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporate client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_6(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporate client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientName(corporationName,"Client", clientName, firstName, lastName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientName(corporationName,"All", clientName, firstName, lastName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporate client email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_7(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporate client email";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientEmail(corporationName,"Client", email, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientEmail(corporationName,"All", email, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporate client File Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_8(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporate client File Number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientFileNumber(corporationName, "Client", fileNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientFileNumber(corporationName, "All", fileNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using corporate client Alien Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_9(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using corporate client Alien Number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientAlienNumber(corporationName, "Client", alienNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientAlienNumber(corporationName, "All", alienNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
//  PARKED
// Search does not give any results when searched with DOB	
//	@Test(groups = {"cmpsearch"}, description = "Verify search using corporate client DOB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
//	public void Client_CMP_Search_10(String browser) throws Exception {
//
//		globalVariables.testCaseDescription = "Verify search using corporate client DOB";
//
//		final WebDriver driver = WebDriverFactory.get(browser);
//		
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
//		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
//		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
//		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
//		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
//		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
//		String applicantType = "Relative";
//		String clientType = "Employee";
//		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
//		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
//		String clientName = firstName + " " + lastName ; 
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//		try {
//
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//			
//			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
//			
//			cmpSearchPage.verifySearchUsingClientDOB(corporationName, "Client", dob, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			cmpSearchPage.verifySearchUsingClientDOB(corporationName, "All", dob, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			caseManagerDashboardPage.clickLogout(true);
//			
//			Log.testCaseResult();
//		} 
//		catch (Exception e) {
//			Log.exception(e, driver);
//		} 
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
//	
//  PARKED
//	@Test(groups = {"cmpsearch"}, description = "Verify search (All) box using Corporation", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
//	public void Client_CMP_Search_11(String browser) throws Exception {
//
//		globalVariables.testCaseDescription = "Verify search (All) box using Corporation";
//
//		final WebDriver driver = WebDriverFactory.get(browser);
//		
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
//		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
//		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
//		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
//		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
//		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
//		String applicantType = "Relative";
//		String clientType = "Employee";
//		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
//		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
//		String clientName = firstName + " " + lastName ; 
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//		try {
//
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//
//			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);
//
//			login.clickAgreeButton(false);
//			
//			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
//			
//			cmpSearchPage.verifySearchUsingClientUsingCorporationName(corporationName, "Client", corporationName, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			cmpSearchPage.verifySearchUsingClientUsingCorporationName(corporationName, "All", corporationName, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
//			
//			caseManagerDashboardPage.clickLogout(true);
//			
//			Log.testCaseResult();
//		} 
//		catch (Exception e) {
//			Log.exception(e, driver);
//		} 
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	@Test(groups = {"cmpsearch"}, description = "Edit file number and search using updated file number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_12(String browser) throws Exception {

		globalVariables.testCaseDescription = "Edit file number and search using updated file number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(firstName, true);
			
			clientListPage.clickProfileTab();
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			CM_ClientProfilePage clientProfilePage = new CM_ClientProfilePage(driver);
			
			clientProfilePage.updateFileNumber(globalVariables.cmpSearchEdittedClientFileNumber);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("CorporateClientFileNumber", sheetName, "Value", globalVariables.cmpSearchEdittedClientFileNumber);
			
			cmpSearchPage.verifySearchUsingClientFileNumber(corporationName, "Client", globalVariables.cmpSearchEdittedClientFileNumber, clientName, email, globalVariables.cmpSearchEdittedClientFileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			cmpSearchPage.verifySearchUsingClientFileNumber(corporationName, "All", globalVariables.cmpSearchEdittedClientFileNumber, clientName, email, globalVariables.cmpSearchEdittedClientFileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Delete Alien Number and search using alien number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_13(String browser) throws Exception {

		globalVariables.testCaseDescription = "Delete Alien Number and search using alien number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorporateClientEmailID");
		String fileNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientFileNumber");
		String alienNumber = readExcel.initTest(workbookName, sheetName, "CorporateClientAlienNumber");
		String applicantType = "Relative";
		String clientType = "Employee";
		String dob = readExcel.initTest(workbookName, sheetName, "CorporateClientDOB");
		String clientID = readExcel.initTest(workbookName, sheetName, "CorporateClientLoginID");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(firstName, true);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingClientFileNumber(corporationName, "Client", fileNumber, clientName, email, fileNumber, alienNumber, dob, applicantType, clientType, clientID);
			
			clientListPage.clickProfileTab();
			
			clientListPage.removeAlienNumber();
			
			cmpSearchPage.verifySearchUsingInvalidData("All", alienNumber);
			
			cmpSearchPage.verifySearchUsingInvalidData("Client", alienNumber);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Delete Client and search using client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ClientPrerequisite_2")
	public void Client_CMP_Search_14(String browser) throws Exception {

		globalVariables.testCaseDescription = "Delete Client and search using client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "CorporateClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorporateClientLastName");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(firstName, true);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearhClientName(firstName, lastName);
			
			clientListPage.deleteClient();
			
			cmpSearchPage.verifySearchUsingInvalidData("All", clientName);
			
			cmpSearchPage.verifySearchUsingInvalidData("Client", clientName);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Create a case with ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CasePrerequisite(String browser) throws Exception {

		globalVariables.testCaseDescription = "Create a case with ";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String petitionType = readExcel.initTest(workbookName, sheetName, "PetitionType");
		final WebDriver driver = WebDriverFactory.get(browser);
		String receiptSendDate = "7/24/2020";
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickAddClientButton(true);
			
			clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, globalVariables.cmpSearchCorporationNameForCorpClient, globalVariables.cmpSearchClientFirstNameForCase, globalVariables.cmpSearchClientLastNameForCase,globalVariables.cmpSearchClientEmailID);
			
			clientListPage.clickSaveClientButton(true);
			
			clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);
			
			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			String clientName = globalVariables.cmpSearchClientFirstNameForCase + " " + globalVariables.cmpSearchClientLastNameForCase;
			
			caseListPage.clickAndAddCase(workbookNameWrite, sheetName, clientName, corporationName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);
				
			caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForUSA", data.AddCase_CountryName,clientName, corporationName, workbookNameWrite, sheetName, true);
			
			caseListPage.clickDetailsDatesTab(true);
			
			caseListPage.editFileNumber(globalVariables.caseFileNumber, globalVariables.caseDescription);			
			
			caseListPage.clickReceiptNumberTab(true);

			CM_ReceiptNumbersPage receiptNumbersPage = new CM_ReceiptNumbersPage(driver);

			receiptNumbersPage.clickAddNewCaseReceiptNoBtn(globalVariables.receiptType, receiptSendDate, globalVariables.receiptDateTxt, globalVariables.receiptNumberTxt, "Pending", true);

			receiptNumbersPage.verifyReceiptNumber(globalVariables.receiptNumberTxt,true);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("ClientFirstNameForCase", sheetName, "Value", globalVariables.cmpSearchClientFirstNameForCase);
			writeExcel.setCellData("ClientLastNameForCase", sheetName, "Value", globalVariables.cmpSearchClientLastNameForCase);
			writeExcel.setCellData("ClientEmailForCase", sheetName, "Value", globalVariables.cmpSearchClientEmailID);
			writeExcel.setCellData("FileNumberForCase", sheetName, "Value", globalVariables.caseFileNumber);
			writeExcel.setCellData("CaseDescription", sheetName, "Value", globalVariables.caseDescription);			
			writeExcel.setCellData("ReceiptNumber", sheetName, "Value", globalVariables.receiptNumberTxt);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using case ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using case ID";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDescription = readExcel.initTest(workbookName, sheetName, "CaseDescription");
		String caseFileNumber = readExcel.initTest(workbookName, sheetName, "FileNumberForCase");
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String receiptNumber = readExcel.initTest(workbookName, sheetName, "ReceiptNumber");
		String clientName = firstName + " " + lastName ;
		String emailId = readExcel.initTest(workbookName, sheetName, "ClientEmailForCase");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCaseID("All", caseId, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
			cmpSearchPage.verifySearchUsingCaseID("Case", caseId, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using case file number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using case file number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDescription = readExcel.initTest(workbookName, sheetName, "CaseDescription");
		String caseFileNumber = readExcel.initTest(workbookName, sheetName, "FileNumberForCase");
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String receiptNumber = readExcel.initTest(workbookName, sheetName, "ReceiptNumber");
		String clientName = firstName + " " + lastName ;
		String emailId = readExcel.initTest(workbookName, sheetName, "ClientEmailForCase");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCaseFileNumber("All", caseFileNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
			cmpSearchPage.verifySearchUsingCaseFileNumber("Case", caseFileNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
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
	
 	
	@Test(groups = {"cmpsearch"}, description = "Verify search using case client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using case client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDescription = readExcel.initTest(workbookName, sheetName, "CaseDescription");
		String caseFileNumber = readExcel.initTest(workbookName, sheetName, "FileNumberForCase");
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String receiptNumber = readExcel.initTest(workbookName, sheetName, "ReceiptNumber");
		String clientName = firstName + " " + lastName ;
		String emailId = readExcel.initTest(workbookName, sheetName, "ClientEmailForCase");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCaseClientName("All", clientName, caseId, caseDescription, caseFileNumber, firstName, lastName, emailId, receiptNumber);
			
			cmpSearchPage.verifySearchUsingCaseClientName("Case", clientName, caseId, caseDescription, caseFileNumber, firstName, lastName, emailId, receiptNumber);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using case reciept number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using case reciept number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDescription = readExcel.initTest(workbookName, sheetName, "CaseDescription");
		String caseFileNumber = readExcel.initTest(workbookName, sheetName, "FileNumberForCase");
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String receiptNumber = readExcel.initTest(workbookName, sheetName, "ReceiptNumber");
		String clientName = firstName + " " + lastName ;
		String emailId = readExcel.initTest(workbookName, sheetName, "ClientEmailForCase");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCaseReceiptNumber("All", receiptNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
			cmpSearchPage.verifySearchUsingCaseReceiptNumber("Case", receiptNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, receiptNumber);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Edit receipt number and search using updated receipt number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_5(String browser) throws Exception {

		globalVariables.testCaseDescription = "Edit receipt number and search using updated receipt number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDescription = readExcel.initTest(workbookName, sheetName, "CaseDescription");
		String caseFileNumber = readExcel.initTest(workbookName, sheetName, "FileNumberForCase");
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String receiptNumber = readExcel.initTest(workbookName, sheetName, "ReceiptNumber");
		String clientName = firstName + " " + lastName ;
		String emailId = readExcel.initTest(workbookName, sheetName, "ClientEmailForCase");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickReceiptNumberTab(true);
			
			CM_ReceiptNumbersPage receiptNumbersPage = new CM_ReceiptNumbersPage(driver);
			
			receiptNumbersPage.editReceiptNumber(receiptNumber, globalVariables.edittedReceiptNumber);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCaseReceiptNumber("All", globalVariables.edittedReceiptNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, globalVariables.edittedReceiptNumber);
			
			cmpSearchPage.verifySearchUsingCaseReceiptNumber("Case", globalVariables.edittedReceiptNumber, caseId, caseDescription, caseFileNumber, clientName, emailId, globalVariables.edittedReceiptNumber);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Case : Delete Client and search using client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CasePrerequisite")
	public void Case_CMP_Search_6(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case : Delete Client and search using client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String firstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForCase");
		String lastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForCase");
		String clientName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(firstName, true);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearhClientName(firstName, lastName);
			
			clientListPage.deleteClient();
			
			cmpSearchPage.verifySearchUsingInvalidData("All", clientName);
			
			cmpSearchPage.verifySearchUsingInvalidData("Case", clientName);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Corporation : Search questionnaire verify results and attach the same", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Questionnaire_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Corporation : Search questionnaire verify results and attach the same";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.addQuestionnaire(data.secondQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(data.secondQuestionnaire, corporationName);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Client : Search questionnaire verify results and attach the same", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Questionnaire_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Client : Search questionnaire verify results and attach the same";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String firstName = readExcel.initTest(workbookName, sheetName, "IndividualClientFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "IndividualClientLastName");
		String clientName = firstName + " " + lastName ;

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

			clientCreationPage.clickOnClientName(firstName, true);

			clientCreationPage.clickQuestionnairesTab();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			clientQuestionnairePage.addQuestionnaire(data.questionnaire);
			
			clientQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, clientName);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Case : Search questionnaire verify results and attach the same", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Questionnaire_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Case : Search questionnaire verify results and attach the same";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCaseTab(false);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnFirstCaseOnList();

			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.addQuestionnaire(data.questionnaire);
			
			//String caseId = readExcel.initTest(workbookName, sheetName, "CaseForQuestionnaire");
			
			caseQuestionnairePage.verifyIfQuestionnairesAdded(data.questionnaire, "");
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Corporation : Search questionnaire verify results and attach the same", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Questionnaire_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Corporation : Search questionnaire verify results and attach the same";

		final WebDriver driver = WebDriverFactory.get(browser);
		String prospectiveCorporationName = "Sakku Prospective";
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectListPage = new CM_ProspectsListPage(driver);
			
			prospectListPage.clickProspectCorporation();
			
			prospectListPage.clickOnProspectiveCorporationName(prospectiveCorporationName);
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver);  
			
			prospectiveProfilePage.clickQuestionnaireTab();
			
			prospectiveProfilePage.chooseAddRemoveQuestionnaires();
			
			prospectiveProfilePage.addQuestionnaire(data.secondQuestionnaire);
			
			prospectiveProfilePage.verifyIfCorporationQuestionnairesAdded(data.secondQuestionnaire);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : Search questionnaire verify results and attach the same", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Questionnaire_CMP_Search_5(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : Search questionnaire verify results and attach the same";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectListPage = new CM_ProspectsListPage(driver);
			
			prospectListPage.clickProspectIndividual();
			
			prospectListPage.clickOnFirstProspectiveClientName();;
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver); 
			
			prospectiveProfilePage.clickClientQuestionnaireTab();
			
			prospectiveProfilePage.chooseAddRemoveQuestionnaires();
			
			prospectiveProfilePage.addQuestionnaire(data.secondQuestionnaire);
			
			prospectiveProfilePage.verifyIfClientQuestionnairesAdded(data.secondQuestionnaire);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "ToDo : Search Corportion Name and Questionnaire on assign ToDo window", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ToDo_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "ToDo : Search Corportion Name and Questionnaire on assign ToDo window";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String corporationName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.verifyCorpToDo("Corp_for_CM", globalVariables.cmpToDoCorpQuestionnaire);
			
			//caseManagerDashboardPage.clickLogout(true);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"cmpsearch"}, description = "ToDo : Search Client Name and Questionnaire on assign ToDo window", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ToDo_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "ToDo : Search Client Name and Questionnaire on assign ToDo window";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String caseForToDo = readExcel.initTest(workbookName, sheetName, "CaseForToDo");
		String clientFirstNameForToDo = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForToDo");
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.verifyClientToDo(clientFirstNameForToDo, data.questionnaire);
			
			//caseManagerDashboardPage.clickLogout(true);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"cmpsearch"}, description = "ToDo : Search Client Name, Case Name and Questionnaire on assign ToDo window", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ToDo_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "ToDo : Search Client Name, Case Name  and Questionnaire on assign ToDo window";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String caseForToDo = readExcel.initTest(workbookName, sheetName, "CaseForToDo");
		String clientFirstNameForToDo = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForToDo");
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.verifyCaseToDo(clientFirstNameForToDo, caseForToDo, data.questionnaire);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"cmpsearch"}, description = "HRP : Search for a client and verify if client details appear", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "HRP : Search for a client and verify if client details appear";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "HRP_UserName");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "HRP_Password");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForToDo");
		String clientLastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForToDo");
		String employeeId = readExcel.initTest(workbookName, sheetName, "HRP_EmployeeID");
		String corpName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String clientName = clientFirstName + " " + clientLastName;
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);
			
			hrpHomePage.verifyDashboardSearchBoxForClientDetails(clientName, clientName, employeeId, corpName);
			
			Log.testCaseResult();
			
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"cmpsearch"}, description = "HRP : Search for a employee ID and verify if client details appear", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void HRP_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "HRP : Search for a employee ID and verify if client details appear";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "HRP_UserName");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "HRP_Password");
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ClientFirstNameForToDo");
		String clientLastName = readExcel.initTest(workbookName, sheetName, "ClientLastNameForToDo");
		String employeeId = readExcel.initTest(workbookName, sheetName, "HRP_EmployeeID");
		String corpName = readExcel.initTest(workbookName, sheetName, "CorporationNameForCorporateClient");
		String clientName = clientFirstName + " " + clientLastName;
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.hrpLogin(hrpLoginID, hrpPassword, true);
			
			HrpHomePage	hrpHomePage = new HrpHomePage(driver);

			//login.clickAgreeButton(false);
			
			hrpHomePage.verifyDashboardSearchBoxForClientDetails(employeeId, clientName, employeeId, corpName);
			
			Log.testCaseResult();
			
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : Create a prospective client with Name,Consultation Date,Conversion Possibility,File Number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ProspectiveClientPrerequisite(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : Create a prospective client with Name,Consultation Date,Conversion Possibility,File Number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectsListPage = new CM_ProspectsListPage(driver);
			
			prospectsListPage.clickProspectIndividual();
			
			prospectsListPage.addNewProspectiveClient(globalVariables.cmpProspectiveClientFirstName, globalVariables.cmpProspectiveClientLastName);
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver);
			
			prospectiveProfilePage.addfileNumberAndConsultationDate(globalVariables.cmpProspectiveClientFileNumber, globalVariables.cmpProspectiveClientConsultationDate);
			
			caseManagerDashboardPage.clickProspectTab();
			
			prospectsListPage.setClientConversionPossiblity(globalVariables.cmpProspectiveClientFirstName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("ProspectiveClientFirstName", sheetName, "Value", globalVariables.cmpProspectiveClientFirstName);
			writeExcel.setCellData("ProspectiveClientLastName", sheetName, "Value", globalVariables.cmpProspectiveClientLastName);
			writeExcel.setCellData("ProspectiveClientFileNumber", sheetName, "Value", globalVariables.cmpProspectiveClientFileNumber);
			writeExcel.setCellData("ProspectiveClientConsultatationDate", sheetName, "Value", globalVariables.cmpProspectiveClientConsultationDate);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : verify search box using client name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveClientPrerequisite")
	public void PClient_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : verify search box using new client name";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveClientFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFirstName");
		String prospectiveClientLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientLastName");
		String  fileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFileNumber");
		//String  consultationDate = readExcel.initTest(workbookName, sheetName, "ProspectiveClientConsultatationDate");
		String clientName = prospectiveClientFirstName + " " + prospectiveClientLastName;
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectiveClientName("All", clientName, prospectiveClientFirstName, prospectiveClientLastName, fileNumber, "High");
			
			cmpSearchPage.verifySearchUsingProspectiveClientName("Prospective Client", clientName, prospectiveClientFirstName, prospectiveClientLastName, fileNumber, "High");
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : verify search box using file number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveClientPrerequisite")
	public void PClient_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : verify search box using new file number";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveClientFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFirstName");
		String prospectiveClientLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientLastName");
		String  fileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFileNumber");
		//String  consultationDate = readExcel.initTest(workbookName, sheetName, "ProspectiveClientConsultatationDate");
		String clientName = prospectiveClientFirstName + " " + prospectiveClientLastName;
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectiveFileNumber("All", fileNumber, clientName, fileNumber, "High");
			
			cmpSearchPage.verifySearchUsingProspectiveFileNumber("Prospective Client", fileNumber, clientName, fileNumber, "High");
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : Convert the propective client to client and verify if the profile appears on client search", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveClientPrerequisite")
	public void PClient_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : Convert the propective client to client and verify if the profile appears on client search";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveClientFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFirstName");
		String prospectiveClientLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveClientLastName");
		String  fileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveClientFileNumber");
		//String  consultationDate = readExcel.initTest(workbookName, sheetName, "ProspectiveClientConsultatationDate");
		String clientName = prospectiveClientFirstName + " " + prospectiveClientLastName;
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectListPage = new CM_ProspectsListPage(driver);
			
			prospectListPage.clickOnProspectiveClientName(clientName);
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver);
			
			prospectiveProfilePage.acceptProspectiveClientAsClient();
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingConvertedClientName("All",clientName, prospectiveClientFirstName, prospectiveClientLastName);
			
			cmpSearchPage.verifySearchUsingConvertedClientName("Client",clientName, prospectiveClientFirstName, prospectiveClientLastName);
			
			cmpSearchPage.verifySearchUsingInvalidData("Prospective Client", clientName);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Client : Delete a prospective client and verify search using the delete prospective client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveClientPrerequisite")
	public void PClient_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Client : Delete a prospective client and verify search using the delete prospective client";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectsListPage = new CM_ProspectsListPage(driver);
			
			prospectsListPage.clickProspectIndividual();
			
			prospectsListPage.addNewProspectiveClient(globalVariables.cmpProspectiveClientFirstNameForDelete, globalVariables.cmpProspectiveClientLastNameForDelete);
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver);
			
			prospectiveProfilePage.clickProfileTab();
			
			prospectiveProfilePage.deleteClient();
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingInvalidData("Prospective Client", globalVariables.cmpProspectiveClientFirstNameForDelete + " " + globalVariables.cmpProspectiveClientLastNameForDelete);
			
			cmpSearchPage.verifySearchUsingInvalidData("All", globalVariables.cmpProspectiveClientFirstNameForDelete + " " + globalVariables.cmpProspectiveClientLastNameForDelete);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Corporation : Create a prospective corporation with Corporation Name,File Number,Email,Contact Person name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void ProspectiveCorporationPrerequisite(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Corporation : Create a prospective corporation with Corporation Name,File Number,Email,Contact Person name";

		final WebDriver driver = WebDriverFactory.get(browser);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectsListPage = new CM_ProspectsListPage(driver);
			
			prospectsListPage.clickProspectCorporation();
			
			prospectsListPage.addProspectiveCorporation(globalVariables.cmpProspectiveCorpName, globalVariables.cmpProspectiveCorpContactPersonFirstName, globalVariables.cmpProspectiveCorpContactPersonLastName, globalVariables.cmpProspectiveCorpFileNumber, globalVariables.cmpProspectiveCorpEmailID);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("ProspectiveCorpName", sheetName, "Value", globalVariables.cmpProspectiveCorpName);
			writeExcel.setCellData("ProspectiveCorpContactFirstName", sheetName, "Value", globalVariables.cmpProspectiveCorpContactPersonFirstName);
			writeExcel.setCellData("ProspectiveCorpContactLastName", sheetName, "Value", globalVariables.cmpProspectiveCorpContactPersonLastName);
			writeExcel.setCellData("ProspectiveCorpFileNumber", sheetName, "Value", globalVariables.cmpProspectiveCorpFileNumber);
			writeExcel.setCellData("ProspectiveCorpEmail", sheetName, "Value", globalVariables.cmpProspectiveCorpEmailID);
			
			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Prospective corporation name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Prospective corporation name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveCorporationName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpName");
		String contactPersonFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactFirstName");
		String contactPersonLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactLastName");
		String contactPersonName = contactPersonFirstName + " " + contactPersonLastName;
		String corpFileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpFileNumber");
		String emailID = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpEmail");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectivCorpName("All", prospectiveCorporationName, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
			cmpSearchPage.verifySearchUsingProspectivCorpName("Prospective Corporation", prospectiveCorporationName, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Prospective corporation contact person name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Prospective corporation name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveCorporationName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpName");
		String contactPersonFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactFirstName");
		String contactPersonLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactLastName");
		String contactPersonName = contactPersonFirstName + " " + contactPersonLastName;
		String corpFileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpFileNumber");
		String emailID = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpEmail");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectivCorpContactPerson("All", contactPersonName, prospectiveCorporationName, contactPersonFirstName, contactPersonLastName, corpFileNumber, emailID);
			
			cmpSearchPage.verifySearchUsingProspectivCorpContactPerson("Prospective Corporation", contactPersonName, prospectiveCorporationName, contactPersonFirstName, contactPersonLastName, corpFileNumber, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Prospective corporation file number", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Prospective file number";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveCorporationName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpName");
		String contactPersonFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactFirstName");
		String contactPersonLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactLastName");
		String contactPersonName = contactPersonFirstName + " " + contactPersonLastName;
		String corpFileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpFileNumber");
		String emailID = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpEmail");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectiveCorpFileNumber("All", corpFileNumber, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
			cmpSearchPage.verifySearchUsingProspectiveCorpFileNumber("Prospective Corporation", corpFileNumber, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Prospective corporation email id", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_4(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Prospective corporation email id";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveCorporationName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpName");
		String contactPersonFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactFirstName");
		String contactPersonLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactLastName");
		String contactPersonName = contactPersonFirstName + " " + contactPersonLastName;
		String corpFileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpFileNumber");
		String emailID = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpEmail");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingProspectiveCorpEmailId("All", emailID, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
			cmpSearchPage.verifySearchUsingProspectiveCorpEmailId("Prospective Corporation", emailID, prospectiveCorporationName, contactPersonName, corpFileNumber, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Corp : Convert the prospective corporation to corporation and verify search", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_5(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Corp : Convert the prospective corporation to corporation and verify search";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String prospectiveCorporationName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpName");
		String contactPersonFirstName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactFirstName");
		String contactPersonLastName = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpContactLastName");
		String contactPersonName = contactPersonFirstName + " " + contactPersonLastName;
		String corpFileNumber = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpFileNumber");
		String emailID = readExcel.initTest(workbookName, sheetName, "ProspectiveCorpEmail");
		String signingPerson = contactPersonFirstName + " " + contactPersonLastName ;
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectiveListPage = new CM_ProspectsListPage(driver);
			
			prospectiveListPage.clickProspectCorporation();
			
			prospectiveListPage.clickOnProspectiveCorporationName(prospectiveCorporationName);
			
			CM_ProspectiveProfilePage prospectiveProfilePage = new CM_ProspectiveProfilePage(driver);
			
			prospectiveProfilePage.acceptProspectiveCorporationAsCorporation();
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpName("All", prospectiveCorporationName, signingPerson, "", prospectiveCorporationName, emailID);
			
			cmpSearchPage.verifySearchUsingCorpName("Corporation", prospectiveCorporationName, signingPerson, "", prospectiveCorporationName, emailID);
			
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
	
	
	@Test(groups = {"cmpsearch"}, description = "Prospective Corp : Delete prospective corporation and verify search", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "ProspectiveCorporationPrerequisite")
	public void PSCorp_CMP_Search_6(String browser) throws Exception {

		globalVariables.testCaseDescription = "Prospective Corp : Delete prospective corporation and verify search";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickProspectTab();
			
			CM_ProspectsListPage prospectiveListPage = new CM_ProspectsListPage(driver);
			
			prospectiveListPage.clickProspectCorporation();
			
			CM_ProspectiveProfilePage corporationProfilePage = new CM_ProspectiveProfilePage(driver);
			
			prospectiveListPage.addProspectiveCorporation(globalVariables.cmpProspectiveCorpNameForDelete, globalVariables.cmpProspectiveCorpContactPersonFirstName, globalVariables.cmpProspectiveCorpContactPersonLastName, globalVariables.cmpProspectiveCorpFileNumber, globalVariables.cmpProspectiveCorpEmailID);
			
			corporationProfilePage.deleteCorporation();
			
			CM_CMPSearch cmpsearch = new CM_CMPSearch(driver);
			
			cmpsearch.verifySearchUsingInvalidData("All", globalVariables.cmpProspectiveCorpNameForDelete);
			
			cmpsearch.verifySearchUsingInvalidData("Prospective Corporation", globalVariables.cmpProspectiveCorpNameForDelete);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Create a Corp User with email ID", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CorpUserPrerequisite(String browser) throws Exception {

		globalVariables.testCaseDescription = "Create a Corp User with email ID";

		final WebDriver driver = WebDriverFactory.get(browser);
		

		String corporationName = globalVariables.cmpSearchCorporationNameForCorpClient;
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickCorpUsersTab();
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.addCorpUser(globalVariables.cmpSearchCorpUserFirstName, globalVariables.cmpSearchCorpUserLastName, globalVariables.cmpSearchCorpUserEmail, globalVariables.cmpSearchCorpUserRole);
			
			portalSetupPage.verifyCorpUserAdded(workbookNameWrite, sheetName, globalVariables.cmpSearchCorpUserFirstName, globalVariables.cmpSearchCorpUserLastName, globalVariables.cmpSearchCorpUserEmail, globalVariables.cmpSearchCorpUserRole);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Corp User name", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpUserPrerequisite")
	public void CorpUser_CMP_Search_1(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Corp User name";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = globalVariables.cmpSearchCorporationNameForCorpClient;
		String firstName = readExcel.initTest(workbookName, sheetName, "CorpUserFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorpUserLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorpUserEmailId");
		String corpUserName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpUserName(corporationName, "Corp User", corpUserName, firstName, lastName, email);
			
			cmpSearchPage.verifySearchUsingCorpUserName(corporationName, "All", corpUserName, firstName, lastName, email);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Verify search using Corp User email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpUserPrerequisite")
	public void CorpUser_CMP_Search_2(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify search using Corp User email";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = globalVariables.cmpSearchCorporationNameForCorpClient;
		String firstName = readExcel.initTest(workbookName, sheetName, "CorpUserFirstName");
		String lastName = readExcel.initTest(workbookName, sheetName, "CorpUserLastName");
		String email = readExcel.initTest(workbookName, sheetName, "CorpUserEmailId");
		String corpUserName = firstName + " " + lastName ; 
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
			cmpSearchPage.verifySearchUsingCorpUserEmail(corporationName,"Corp User", email, corpUserName, email);
			
			cmpSearchPage.verifySearchUsingCorpUserEmail(corporationName,"All", email, corpUserName, email);
			
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
	
	@Test(groups = {"cmpsearch"}, description = "Edit email and verify search box using new new email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CorpUserPrerequisite")
	public void CorpUser_CMP_Search_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Edit email and verify search box using new new email";

		final WebDriver driver = WebDriverFactory.get(browser);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corporationName = globalVariables.cmpSearchCorporationNameForCorpClient;
		String corpUserName = readExcel.initTest(workbookName, sheetName, "CorpUserName");
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corporationName);
			
			corpListPage.clickCorpUsersTab();
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.editCorpUserEmail(workbookNameWrite, sheetName, corpUserName, globalVariables.cmpSearchCorpUserEditedEmail);
						
			CM_CMPSearch cmpSearchPage = new CM_CMPSearch(driver);
			
            cmpSearchPage.verifySearchUsingCorpUserEmail(corporationName,"Corp User", globalVariables.cmpSearchCorpUserEditedEmail, corpUserName, globalVariables.cmpSearchCorpUserEditedEmail);
			
			cmpSearchPage.verifySearchUsingCorpUserEmail(corporationName,"All", globalVariables.cmpSearchCorpUserEditedEmail, corpUserName, globalVariables.cmpSearchCorpUserEditedEmail);
						
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