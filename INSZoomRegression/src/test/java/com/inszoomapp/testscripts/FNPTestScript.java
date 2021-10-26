package com.inszoomapp.testscripts;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
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
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.FNPNewsPage;
import com.inszoomapp.pages.FNPProfilePage;
import com.inszoomapp.pages.FNP_CalendarPage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FNP_DocumentsPage;
import com.inszoomapp.pages.FNP_MessagesPage;
import com.inszoomapp.pages.FNP_NotesPage;
import com.inszoomapp.pages.FNP_PassportPage;
import com.inszoomapp.pages.FnpHomePage;
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
public class FNPTestScript extends BaseTest
{
	public String[] documentName;
	Properties prop = new Properties();
	OutputStream output = null;
	InputStream input = null;
	
	public String newPassword = "Fnp" + "@" + RandomStringUtils.randomNumeric(5).toString();
	public String newLoginID = "B" + RandomStringUtils.randomNumeric(5).toString(); 
	
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
	
	
	@Test(description = "FNP - Cases to be added to the client(BNF)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_36_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1"})
	public void CM_TC_69(String browser) throws Exception

	{

		globalVariables.testCaseDescription = "FNP : Verify the Case linked";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginIDCreated = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpLoginPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//		String receiptNumber = readExcel.initTest(workbookName, sheetName, "QA_ALoginEditedReceiptNumber");
		String receiptSendDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptSendDate");
		String receiptValidFromDate = readExcel.initTest(workbookName, sheetName, "QA_ALoginReceiptValidFromDate");
		

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginIDCreated,fnpLoginPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.verifyCaseLinkedAndReceiptNumber(globalVariables.edittedReceiptNumber,receiptSendDate,receiptValidFromDate,true);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Verify FNP visa details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_16_1")
	public void CM_TC_70(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify the Case linked";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyVisaDetails(globalVariables.verificationCountry, globalVariables.genericStartDate, globalVariables.genericEndDate, globalVariables.visaType);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Details/Dates", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1"})
	public void CM_TC_82_1(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String description = readExcel.initTest(workbookName, sheetName, "QA_ALoginUploadDocumentDescription");
		String description2 = readExcel.initTest(workbookName, sheetName, "QA_FNPUploadDocumentDescription");

		globalVariables.testCaseDescription = "FNP Case: Verify case details section under Details/Dates in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(data.PetitionType_AddCase);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);

			fnpCaseProfilePage.verifyDetailsDates(true);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	@Test(description = "FNP - verify details/Dates data's are present", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1","com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_36_2"})
	public void CM_TC_82_2(String browser) throws Exception

	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String description = readExcel.initTest(workbookName, sheetName, "QA_ALoginEditedReceiptNumber");

		globalVariables.testCaseDescription = "FNP Case: Verify case details are present under Details/Dates in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(data.PetitionType_AddCase);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
	
			fnpCaseProfilePage.verifyReceiptNumber(globalVariables.edittedReceiptNumber);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Communication", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1"})
	public void CM_TC_85_1(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String subject = readExcel.initTest(workbookName, sheetName, "QA_ALoginUpdatedSubjectCase");
		
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Communication: Verify Email and Notes section under communication";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
	//	String subject = readExcel.initTest(workbookName, sheetName, "QA_ALoginUpdatedSubjectCase"); 

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(data.PetitionType_AddCase);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
		
			fnpCaseProfilePage.clickCommunicationTab();

			fnpCaseProfilePage.verifyEmailHeader();
			
			fnpCaseProfilePage.verifyNotesHeader();

			//fnpCaseProfilePage.clickComposeEmailBtn(globalVariables.QA_FNPToEmail, true);

			//fnpCaseProfilePage.clickCommunicationBtn(true);

			//fnpCaseProfilePage.verifyReceivedEmail(subject, true);
			
			//fnpCaseProfilePage.verifySentEmailSubject(true);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"notes", "FNP"}, description = "FNP - Communication", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_48_2"})
	public void FNP_TC_85_2(String browser) throws Exception

	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Communication: Verify the notes added from Case Manager in FNP portal";
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
		
			fnpCaseProfilePage.clickCommunicationTab();

			fnpCaseProfilePage.verifyNotes(globalVariables.editFirstNotesDetailsTextCase);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"notes", "FNP"}, description = "Verify Notes in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_48_2"})
	public void FNP_TC_85_3(String browser) throws Exception {

		globalVariables.testCaseDescription = "Login to FNP and check the Notes is visible";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		

		try {
			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickNotesTab();
			
			FNP_NotesPage fnpNotesPage = new FNP_NotesPage(driver);

			fnpNotesPage.verifyNotes(globalVariables.editFirstNotesDetailsTextCase ,true);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Passport Details in FNP",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_10_1"})
    public void CM_TC_77_1(String browser) throws Exception {

          globalVariables.testCaseDescription = "FNP: Verify Passport Details in FNP";

          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          
          try {

                LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
                
                fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickPassportTab();
                
                FNP_PassportPage fnpPassPortPage = new FNP_PassportPage(driver);

                fnpPassPortPage.verifyValidPassportDetails(globalVariables.countryNew, globalVariables.passportNumber, globalVariables.FNPnoticeDate, globalVariables.validToFNP);

                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          }

          catch (Exception e) {
                Log.exception(e, driver);

          }

          finally {
                Log.endTestCase();
                driver.quit();

          }

    }

    @Test(description = "Verify Passport is expired in FNP", groups={"FNP", "Priority2"},dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_10_2_1"})
    public void CM_TC_77_2(String browser) throws Exception {

          globalVariables.testCaseDescription = "FNP: Verify Passport is expired";

          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

          try {
                
                LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
                
                fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickPassportTab();
                
                FNP_PassportPage fnpPassPortPage = new FNP_PassportPage(driver);
                
                fnpPassPortPage.verifyExpiredPassportDetails(globalVariables.countryNew,globalVariables.QA_A_SecondPassPortNumber, globalVariables.expirationIssueDate_FNP, globalVariables.expirationDate_FNP);

                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          }

          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }

	
	
	@Test(groups={"FNP", "Priority1", "documents"}, description = "FNP: Upload document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4"})
	public void CM_TC_75_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP: Uplod document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.uploadDocument(globalVariables.filePathFNP);
			
			fnpDocumentsPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtensionFNP);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1"}, description = "FNP: View Uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_75_2"})
	public void CM_TC_75_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP: View Uploaded document";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.viewDocument(globalVariables.fileNameWithoutExtensionFNP, driver);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1"}, description = "FNP: Delete Uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_75_2"})
	public void CM_TC_75_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP: Delete Uploaded document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.deleteDocument(globalVariables.fileNameWithoutExtensionFNP);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
/*	@Test(groups = {"client"}, description = "Verify the Client Name and DOB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_7", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_72_0", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_72_1_0"})
	public void FNP_TC_72_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		String clientDOB = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DateOfBirth");
		String clientBornCountry = readExcel.initTest(workbookName, sheetName, "QA_A_Client_Country");
		String clientEmpId = readExcel.initTest(workbookName, sheetName, "QA_ALoginEmpIdTxt");
		String clientResidenceCity = readExcel.initTest(workbookName, sheetName, "QA_ALoginResidenceCityTxt");

		globalVariables.testCaseDescription = "Verify the Client Name and DOB";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.verifyClientName(clientName);
			
			fnpProfilePage.verifyDoB(clientDOB);
			
			fnpProfilePage.verifyBirthCountry(clientBornCountry.trim());
			
			fnpProfilePage.verifyEmployeeID(clientEmpId.trim());
			
			fnpProfilePage.verifyResidenceCity(clientResidenceCity.trim());
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	
	
/*	@Test(groups={"FNP", "client"}, description = "FNP - My Profile: Verify the History Info Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_6", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_2"})
	public void FNP_TC_73(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the History Info Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.loginToFNP(fnpLogin, fnpNewPassword, true);

			login.clickAgreeButton(false);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.clickBackgroundTab();
			
			//fnpProfilePage.verifySchoolHistory(globalVariables.schoolNameNew);
			
			//fnpProfilePage.verifyEmploymentHistory(globalVariables.jobNew);
			
			//fnpProfilePage.verifyAddressHistory(globalVariables.cityEditted);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	

	@Test(groups={"FNP", "Priority2"}, description = "FNP: Verify Travel Details in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_14_1"})
	public void CM_TC_78(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String client_DeparturePlaceName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DeparturePlaceName");

		globalVariables.testCaseDescription = "FNP: Verify Travel Details in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickTravelTab();
			
			fnpHomePage.verifyTravelDetails(client_DeparturePlaceName);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP: Verify Travel Details in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_14_4"})
	public void CM_TC_78_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String client_DeparturePlaceName = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DeparturePlaceName");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		globalVariables.testCaseDescription = "FNP: Verify Travel Details in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			fnpHomePage.clickTravelTab();
			
			fnpHomePage.verifyTravelDetails(client_DeparturePlaceName);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Verify Case Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1"})
	public void CM_TC_81_1(String browser) throws Exception

	{
		globalVariables.testCaseDescription = "FNP - Case Details: Verify Case description, case ID ,Case start date and Case status";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseCreatedPetitionName = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreated");
		String caseStatus = readExcel.initTest(workbookName, sheetName, "QA_A_CaseStatus");
		String caseStartDate = readExcel.initTest(workbookName, sheetName, "QA_A_CaseStartDate");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);

			fnpCaseProfilePage.verifyCaseName(caseCreatedPetitionName ,caseIdCreated);

			fnpCaseProfilePage.verifyCaseStatus(caseStatus);

			fnpCaseProfilePage.verifyCaseStartDate(caseStartDate);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(description = "FNP - Documentation: Verify the Questionnaires and Letters in FNP portal", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_52_4"})
	public void CM_TC_83(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - Documentation: Verify the Questionnaires and Letters in FNP portal";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
		
			fnpCaseProfilePage.clickDocumentationTab();
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Messages: Verify the received emails", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_57_1"})
	public void CM_TC_74_1(String browser) throws Exception
	{
		
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP - Messages: Verify the received emails";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);

			fnpHomePagelogin.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
			
			fnpMessagePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.accessCodeEmailSubject);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP: CASE - Verify received email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_59_1"})
	public void CM_TC_52_5_2(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - Documentation: Verify the Questionnaires and Letters in FNP portal";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
		
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.verifyIfEmailPresent(globalVariables.accessCodeEmailSubject);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Verify the Case Steps tab consists of Step details such as Step name, Step status, Estimated Date, completed date", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_2", "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_30_1"})
	public void CM_TC_81_2(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Verify the Case Steps tab consists of Step details such as Step name, Step status, Estimated Date, completed date";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
		
			fnpCaseProfilePage.verifyCaseStepDetailsColumns();
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "FNP - Verify the Supporting docs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_59_1"})
	public void CM_TC_101(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Verify the Case Steps tab consists of Step details such as Step name, Step status, Estimated Date, completed date";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickSupportingDocsTab();
		
			fnpCaseProfilePage.verifySupportingDocs(globalVariables.docChecklistName);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups={"Priority1", "FNP", "HRP"}, description = "FNP - CASE: Verify the sent emails", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_3_1(String browser) throws Exception
	{
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP - CASE: Verify the sent emails";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.composeEmail(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
						
			fnpCaseProfilePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "FNP", "HRP"}, description = "FNP - CASE: Verify the emails sent with attachments", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_3_2(String browser) throws Exception
	{
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP - CASE: Verify the emails sent with attachments";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickCommunicationTab();
			
			fnpCaseProfilePage.composeEmailWithAttachment(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectAttachmentCase, globalVariables.filePath, globalVariables.fileName);
		
			fnpCaseProfilePage.verifyIfEmailPresentWithAttachment(globalVariables.sendMessage, globalVariables.sendSubjectAttachmentCase, globalVariables.fileName);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1"},  description = "FNP - CLIENT : Check received email (Message only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_1_0")
	public void FNP_TC_4_1(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - CLIENT : Check received email (Message only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.emailMessage, globalVariables.clientContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},  description = "FNP - Relative : Check received email (Message only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_4_0")
	public void FNP_TC_4_4(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - RELATIVE : Check received email (Message only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.emailMessage, globalVariables.clientContentEmailSubject);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},  description = "FNP - REALTIVE: Verify not received email (extranet access not given)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_5_0")
	public void FNP_TC_4_5(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - RELATIVE : Verify not received email (extranet access not given)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1"},  description = "FNP - CLIENT : Check received email (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_2_0")
	public void FNP_TC_4_2(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - CLIENT : Check received email (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailPresentWithAttachment(globalVariables.emailMessage, globalVariables.clientAttachmentEmailSubject, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},  description = "FNP - RELATIVE : Check received email (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_6_0")
	public void FNP_TC_4_6(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - RELATIVE : Check received email (with attachment)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailPresentWithAttachment(globalVariables.emailMessage, globalVariables.clientAttachmentEmailSubject, globalVariables.fileName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority1"},  description = "FNP - CLIENT : Check email not present in received emails as it was sent without extranet access", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_4_3_0")
	public void FNP_TC_4_3(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - CLIENT : Check email not present in received emails as it was sent without extranet access";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try
		{
			LoginPageTest loginPageTest = new LoginPageTest(driver, webSite);
			
			loginPageTest.fnplogin(fnpLoginId, fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickMessagesTab();
			
			FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
			
			fnpMessagesPage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "FNP"}, description = "FNP : Verify Received Case Messages (content only)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_3_0")
	public void FNP_TC_3_3(String browser) throws Exception
	{
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP : Verify Received Case Messages (content only)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickCommunicationTab();
					
			fnpCaseProfilePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.emailMessage, globalVariables.caseContentEmailSubject);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "FNP"}, description = "FNP - CASE: FNP : Verify Received Case Messages (with attachment)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_4_0")
	public void FNP_TC_3_4(String browser) throws Exception
	{
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP - CASE: FNP : Verify Received Case Messages (with attachment)ls";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickCommunicationTab();
					
			fnpCaseProfilePage.verifyIfEmailPresentWithAttachment(globalVariables.emailMessage, globalVariables.caseAttachmentEmailSubject, globalVariables.fileName);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority1", "FNP"}, description = "FNP - CASE: Verify not received Case email (extranet access not given)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.FNP_TC_3_5_0")
	public void FNP_TC_3_5(String browser) throws Exception
	{
		/*
		 * Check if extranet access is for All in Advanced Settings
		 * 
		 */
		
		globalVariables.testCaseDescription = "FNP - CASE: Verify not received Case email (extranet access not given)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickCommunicationTab();
					
			fnpCaseProfilePage.verifyIfEmailNotPresent(globalVariables.emailSubjectWithoutAccess);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"FNP", "Priority2"}, description="FNP: Search for Added News", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_113_1")
	public void CM_TC_113_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		globalVariables.testCaseDescription = "FNP: Search for Added News";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver);
				
			login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.searchNews(globalVariables.newsDescriptionBoth);
			
			fnpNewsPage.verifyIfNewsPresent(globalVariables.newsTitleBoth, globalVariables.newsDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"FNP", "Priority2"}, description="FNP: Search for Added Policy", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_114_1")
	public void CM_TC_114_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		globalVariables.testCaseDescription = "FNP: Search for Added Policy";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickNewsGuidelinesTab();
			
			FNPNewsPage fnpNewsPage = new FNPNewsPage(driver);
			
			fnpNewsPage.clickPolicyGuidelinesTab();
			
			fnpNewsPage.searchPolicy(globalVariables.policyTitleBoth);
			
			fnpNewsPage.verifyIfPolicyPresent(globalVariables.policyTitleBoth, globalVariables.policyDescriptionBoth);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "FNP"}, description="Left menu collapse and expand in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_45(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		globalVariables.testCaseDescription = "Left menu collapse and expand in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

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
	
	
	
	@Test(groups={"Priority2", "FNP"}, description="Verify Corporation Name on header", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_46(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		
		globalVariables.testCaseDescription = "Verify Corporation Name on header";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);

			fnpHomePage.verifyCorpNameHeader(corpName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"Priority2", "FNP"}, description="Verify Client Name on header", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_47(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		
		globalVariables.testCaseDescription = "Verify Client Name on header";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);

			fnpHomePage.verifyClientNameHeader(clientName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"Priority2", "FNP"}, description="Verify Client name in Left Menu", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_48(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		
		globalVariables.testCaseDescription = "Verify Client name in Left Menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
				
			login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);

			fnpHomePage.verifyClientNameInLeftMenu(clientName);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
//	@Test(groups={"Priority2", "FNP"}, description="Verify User Instructions", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods="FNP_TC_49_1")
//	public void FNP_TC_49_2(String browser) throws Exception
//	{
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		globalVariables.testCaseDescription = "Verify User Instructions";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//		try 
//		{
//		LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//		login = new LoginPageTest(driver, webSite);
//
//		login.fnplogin(fnpLoginId,fnpPassword, true);
//
//		FnpHomePage fnpHomePage = new FnpHomePage(driver);
//		
//		fnpHomePage.verifyPrintUserInstruction(globalVariables.clientUserInstruction);
//		//globalVariables.clientUserInstruction
//		Log.testCaseResult();
//		
//		} catch (Exception e) {
//			Log.exception(e, driver);
//		} finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
//	@Test(groups={"Priority2", "FNP"}, description="Verify User Instructions", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void FNP_TC_49_1(String browser) throws Exception
//	{
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//		
//		globalVariables.testCaseDescription = "Verify User Instructions";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//		try 
//		{
//		LoginPageTest login = new LoginPageTest(driver, webSite);
//
//        CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(data.CM_userName, data.CM_password, true);
//
//        caseManagerHomePage.clickPortalSetup(true);
//		
//		PortalSetup portalSetup = new PortalSetup(driver);
//		
//		portalSetup.clickDefaultPortalAccess(false);
//		
//		portalSetup.searchCorporationByName(corpName, false);
//		
//		portalSetup.clickCustomizeCorporationSettings(false);
//		
//		portalSetup.clickPortalContentTab();
//		
//		portalSetup.clickUserInstructionTab();
//		
//		portalSetup.setClientUserInstruction(globalVariables.clientUserInstructionTitle, globalVariables.clientUserInstruction);
//		
//		driver.close();
//		Utils.waitForAllWindowsToLoad(1, driver);
//		Utils.switchWindows(driver, "My Zoomboard", "title", "false");
//			
//		login = new LoginPageTest(driver, webSite);
//
//		login.fnplogin(fnpLoginId,fnpPassword, true);
//
//		FnpHomePage fnpHomePage = new FnpHomePage(driver);
//		
//		fnpHomePage.verifyUserInstruction(globalVariables.clientUserInstruction);
//
//		Log.testCaseResult();
//		
//			
//		} catch (Exception e) {
//			Log.exception(e, driver);
//		} finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	
	@Test(groups={"Priority2", "FNP"}, description="Verify Home Button Functionality", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_50(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		globalVariables.testCaseDescription = "Verify Home Button Functionality";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
		LoginPageTest login = new LoginPageTest(driver, webSite);
			
		login = new LoginPageTest(driver, webSite);

		login.fnplogin(fnpLoginId,fnpPassword, true);

		FnpHomePage fnpHomePage = new FnpHomePage(driver);
		
		fnpHomePage.verifyHomeButton();

		Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"FNP", "Priority2"},description = "Verify Client Notes in Notes Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_24_2"})
	public void FNP_TC_72(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify Client Notes in Notes Page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		

		try {
			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickNotesTab();
			
			FNP_NotesPage fnpNotesPage = new FNP_NotesPage(driver);

			fnpNotesPage.verifyNotes(globalVariables.editFirstNotesDetailsTextClient ,true);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(description = "Verify Signout", dataProviderClass = DataProviderUtils.class,groups={"FNP", "Priority2"}, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_64(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify Signout";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify LCA Details",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_115")
	public void FNP_TC_86(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify LCA Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyLCADetails(globalVariables.LCANumber, globalVariables.jobTitle, globalVariables.validThru,globalVariables.LCAeffectiveOn, globalVariables.LCAeffectiveTill);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify Visa Priority Date Info",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_116")
	public void FNP_TC_84(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify Visa Priority Date Info";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyVisaPriorityDate(globalVariables.FNPnoticeDate, globalVariables.visaPriority, globalVariables.countryNew);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify SWA and DoL Info",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_117")
	public void FNP_TC_88(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify SWA and DoL Info";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
			
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifySWAandDoLInfo(globalVariables.swaCaseNumber, globalVariables.swaFiledDateFNP, globalVariables.swaPriorityDateFNP, globalVariables.dolRecievedDateFNP, globalVariables.dolCaseNumber, globalVariables.noticeSentDateFNP, globalVariables.noticeReceivedDateFNP, globalVariables.backlogCaseNumber);
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify shipments and mails in Shipments & Mails page",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_118")
	public void FNP_TC_76(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify shipments and mails in Shipments & Mails page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickShipmentsAndMailsTab();
			
			fnpHomePagelogin.verifyShipmentsAndMails(globalVariables.FNPnoticeDate, globalVariables.shippmentMethod, globalVariables.backlogCaseNumber, caseIdCreated);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Relative name in Left Menu",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_4")
	public void FNP_TC_97(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify Relative name in Left Menu";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		//String relativeName = "Likitha  Krishna";
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.verifyRelative(relativeName);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(description = "FNP - Verify Visa for Relative",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_79_0"})
	public void FNP_TC_95(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Visa for Relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			fnpHomePagelogin.verifyVisaDetailsForRelative(globalVariables.countryName, globalVariables.visaType, globalVariables.FNPnoticeDate, globalVariables.noticeReceivedDateFNP);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Case Details for Relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_16_1"})
	public void FNP_TC_94(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - Verify Case Details for Relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;

		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			//fnpHomePagelogin.clickOnCaseInRelative();

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(description = "Verify Case Details for Relative",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_119_0"})
	public void FNP_TC_96(String browser) throws Exception

	{
		globalVariables.testCaseDescription = "Verify Case Details for Relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseCreatedPetitionName = data.PetitionType_AddCase;
		String caseStatus = readExcel.initTest(workbookName, sheetName, "QA_A_CaseStatusForRelative");
		String caseStartDate = readExcel.initTest(workbookName, sheetName, "QA_A_CaseStartDateForRelative");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForRelative");
		
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		
		
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);

			fnpCaseProfilePage.verifyCaseName(caseCreatedPetitionName ,caseIdCreated);

			fnpCaseProfilePage.verifyCaseStatus(caseStatus);

			fnpCaseProfilePage.verifyCaseStartDate(caseStartDate);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify shipments and mails in Shipments & Mails page for relative",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_120")
	public void FNP_TC_112(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "Verify shipments and mails in Shipments & Mails page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForRelative");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			Utils.waitForElement(driver, "//a[contains(text(),'"+relativeName+"')]", globalVariables.elementWaitTime, "xpath");
			
			fnpHomePagelogin.clickShipmentsAndMailsTab();
			
			fnpHomePagelogin.verifyShipmentsAndMails(globalVariables.FNPnoticeDate, globalVariables.shippmentMethod, globalVariables.backlogCaseNumber, caseId);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Passport Details in FNP",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_121"})
	public void FNP_TC_115(String browser) throws Exception {

		globalVariables.testCaseDescription = "FNP: Verify Passport Details in FNP";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
	
		
		try {

			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			Utils.waitForElement(driver, "//a[contains(text(),'"+relativeName+"')]", globalVariables.elementWaitTime, "xpath");
			
			fnpHomePagelogin.clickPassportTab();
			
			FNP_PassportPage fnpPassPortPage = new FNP_PassportPage(driver);

			fnpPassPortPage.verifyValidPassportDetails(globalVariables.countryNew, globalVariables.passportNumber, globalVariables.FNPnoticeDate, globalVariables.validToFNP);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "FNP - Verify Notes in Notes Page for relative", groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_122"})
	public void FNP_TC_110(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;

		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Verify Notes in Notes Page for relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			fnpHomePage.clickNotesTab();

			FNP_NotesPage fnpNotesPage = new FNP_NotesPage(driver);
			
			fnpNotesPage.verifyNotes(globalVariables.notesDetailsTextRelative, true);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify Expired Passport Details in FNP",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_123"})
	public void FNP_TC_116(String browser) throws Exception {

		globalVariables.testCaseDescription = "FNP: Verify Expired Passport Details in FNP";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		try {

			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			Utils.waitForElement(driver, "//a[contains(text(),'"+relativeName+"')]", globalVariables.elementWaitTime, "xpath");
			
			fnpHomePagelogin.clickPassportTab();
			
			FNP_PassportPage fnpPassPortPage = new FNP_PassportPage(driver);

			fnpPassPortPage.verifyValidPassportDetails(globalVariables.countryNew, globalVariables.passportNumber, globalVariables.FNPnoticeDate, globalVariables.validToFNP);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP: Relative - Upload document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4"})
	public void CM_TC_75_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "FNP: RELATIVE - Upload document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.uploadDocument(globalVariables.filePathFNP);
			
			fnpDocumentsPage.verifyIfDocumentPresent(globalVariables.fileNameWithoutExtensionFNP);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP: Relative - Delete Uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_75_5"})
	public void CM_TC_75_7(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "FNP: Delete Uploaded document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.deleteDocument(globalVariables.fileNameWithoutExtensionFNP);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP: Relative - View Uploaded document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"CM_TC_75_5"})
	public void CM_TC_75_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

		globalVariables.testCaseDescription = "FNP: Relative - View Uploaded document";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickOnRelative(relativeName);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickDocumentsTab();
			
			FNP_DocumentsPage fnpDocumentsPage = new FNP_DocumentsPage(driver);
			
			fnpDocumentsPage.viewDocument(globalVariables.fileNameWithoutExtensionFNP, driver);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Education History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_4"})
	public void CM_TC_73_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Education History";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.clickBackgroundTab();
			
			fnpProfilePage.verifySchoolHistory(globalVariables.schoolNameNew, globalVariables.historyDateFNP, globalVariables.universityName, globalVariables.degree, globalVariables.fieldOfStudy, globalVariables.numberForPersonalInfo);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Employment History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_6"})
	public void CM_TC_73_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Employment History";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.clickBackgroundTab();
			
			fnpProfilePage.verifyEmploymentHistory(globalVariables.jobNew, globalVariables.historyDateFNP, globalVariables.employer, globalVariables.numberForPersonalInfo);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Employment History", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_13_2"})
	public void CM_TC_73_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Employment History";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.clickBackgroundTab();
			
			fnpProfilePage.verifyAddressHistory(globalVariables.cityEditted, globalVariables.historyDateFNP, globalVariables.countryNew, globalVariables.streetNumber, globalVariables.state);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Contact Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_73_4_0"})
	public void CM_TC_73_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Contact Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
		
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.verifyContactDetails(globalVariables.phoneNumber1, globalVariables.phoneNumber2, globalVariables.phoneNumber3, globalVariables.linkedIn, globalVariables.skype, globalVariables.facebook, globalVariables.twitter);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Contact Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_73_5_0"})
	public void CM_TC_73_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Contact Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.verifyPersonalInfo(globalVariables.country, globalVariables.wifeFirstName, globalVariables.maritalStatus);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify the Residence Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_73_6_0"})
	public void CM_TC_73_6(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify the Residence Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.verifyResidenceInfo(globalVariables.countryNew, globalVariables.cityNew, globalVariables.streetNumber, globalVariables.state, globalVariables.numberForPersonalInfo);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - My Profile: Verify Birth Info", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_72_0"}, enabled=false)
	public void CM_TC_73_8(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String clientDOB = readExcel.initTest(workbookName, sheetName, "QA_A_Client_DateOfBirth");

		globalVariables.testCaseDescription = "FNP - My Profile: Verify Birth Info";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			Log.message("" + globalVariables.startTime + "  " + System.currentTimeMillis() + "  "  +(System.currentTimeMillis()-globalVariables.startTime)/1000);
			
			fnpHomePage.clickMyProfile();
			
			FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
			
			fnpProfilePage.verifyBirthInfo(clientDOB, globalVariables.cityNew, globalVariables.country);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Verify Appointments/Activity on Calendar Page",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_26_1"})
	public void FNP_TC_77(String browser) throws Exception {

		globalVariables.testCaseDescription = "FNP: Verify Appointments/Activity on Calendar Page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
		
			fnpHomePagelogin.clickCalendarTab();
			
			FNP_CalendarPage calendarPage = new FNP_CalendarPage(driver);
			
			//Hard coded the values as they are default values
			//calendarPage.verifyAppoimtmentActivity(globalVariables.appointmentActivitySubjectClient, calendarPage.retrieveDate(), globalVariables.appointmentActivityLocationClient, "08:00 AM", "60 min");
			calendarPage.verifyAppoimtmentActivity(globalVariables.appointmentActivitySubjectClient, calendarPage.formatDate(globalVariables.fromDate) , globalVariables.appointmentActivityLocationClient, "08:00 AM", "60 min");

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify Appointment Details",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_26_1"})
	public void FNP_TC_78(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify Appointment Details";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
		
			fnpHomePagelogin.clickCalendarTab();
			
			FNP_CalendarPage calendarPage = new FNP_CalendarPage(driver);
	          
			calendarPage.clickAppointmentActivity(globalVariables.appointmentActivitySubjectClient, calendarPage.formatDate(globalVariables.fromDate));
			
			//Hard coded the values as they are default values
			calendarPage.verifyAppoimtmentActivityDetails(globalVariables.appointmentActivitySubjectClient,globalVariables.appointmentActivityDescriptionClient, globalVariables.appointmentActivityLocationClient, "08:00 AM", "60 min",globalVariables.appointmentActivityComments);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(description = "Verify Appointments/Activities for Relative in Calendar",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_124"})
	public void FNP_TC_108(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Verify Appointments/Activities for Relative in Calendar";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			FNP_CalendarPage calendarPage = new FNP_CalendarPage(driver);			
	         
	        fnpHomePagelogin.clickCalendarTab();
			
			//Hard coded the values as they are default values
			calendarPage.verifyAppoimtmentActivity(globalVariables.appointmentActivitySubjectClient, calendarPage.formatDate(globalVariables.fromDate), globalVariables.appointmentActivityLocationClient, "08:00 AM", "60 min");

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	
	@Test(description = "Verify Appointment Details in relative",groups={"FNP", "Priority2"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_26_1"})
	public void FNP_TC_132(String browser) throws Exception {

		globalVariables.testCaseDescription = "Verify Appointment Details in relative";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;

		
		try {

			LoginPageTest fnplogin = new LoginPageTest(driver, webSite);
			
			fnplogin.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
			
			fnpHomePagelogin.clickOnRelative(relativeName);
		
			fnpHomePagelogin.clickCalendarTab();
			
			FNP_CalendarPage calendarPage = new FNP_CalendarPage(driver);
	          
			calendarPage.clickAppointmentActivity(globalVariables.appointmentActivitySubjectClient, calendarPage.formatDate(globalVariables.fromDate));
			
			//Hard coded the values as they are default values
			calendarPage.verifyAppoimtmentActivityDetails(globalVariables.appointmentActivitySubjectClient,globalVariables.appointmentActivityDescriptionClient, globalVariables.appointmentActivityLocationClient, "08:00 AM", "60 min",globalVariables.appointmentActivityComments);

			//fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		}

		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify case step status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_47_7"})
	public void FNP_TC_15(String browser) throws Exception

	{
		// ********************FNP Login********************

		globalVariables.testCaseDescription = "FNP - Verify case step status";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);

			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);		
			
			fnpHomePagelogin.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);

			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_notStarted, "Not Started");

			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_workInProgress, "In Progress");
			
			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_notApplicable, "Not Applicable");
			
//			fnpCaseProfilePage.verifyCaseStepsStatus(globalVariables.stepName_completed, "Completed");
			
			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Filing Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_36_2")
	public void FNP_TC_89(String browser) throws Exception

	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String description = readExcel.initTest(workbookName, sheetName, "QA_ALoginEditedReceiptNumber");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "FNP Case: Verify case details are present under Details/Dates in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
	
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyFillingDetails(globalVariables.FNPfiledOnDate, globalVariables.edittedReceiptNumber, globalVariables.FNPnoticeDate);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Receipt Details", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_36_2"})
	public void FNP_TC_90(String browser) throws Exception

	{
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLogin = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpNewPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String description = readExcel.initTest(workbookName, sheetName, "QA_ALoginEditedReceiptNumber");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "FNP - Verify Receipt Details";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLogin, fnpNewPassword, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
			fnpHomePage.clickCaseLink(caseIdCreated);
			
			FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
	
			fnpCaseProfilePage.clickDetailsDatesTab();
			
			fnpCaseProfilePage.verifyReceiptDetails(globalVariables.QA_ALoginReceiptSendDate_FNP, globalVariables.edittedReceiptNumber, globalVariables.editReceiptDateInCaseProfile,globalVariables.validFrom_FNP, globalVariables.validTo_FNP , "Approved");

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Important Documents and Dates on Dashboard Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_16_1"})
	public void FNP_TC_71(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Important Documents and Dates on Dashboard Page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyVisaDetailsInImportantDocumentsAndDate(globalVariables.visaType ,globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP );

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Case Information on Dashboard Page (Open)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_4"})
	public void FNP_TC_73_1(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Case Information on Dashboard Page (Open)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseOpen");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyCaseStatus(caseIdCreated, "Open");

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Case Information on Dashboard Page (Approved)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_5"})
	public void FNP_TC_73_2(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Case Information on Dashboard Page (Approved)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseApproved");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyCaseStatus(caseIdCreated, "Approved");

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Case Information on Dashboard Page (Closed)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_60_6"})
	public void FNP_TC_73_3(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Case Information on Dashboard Page (Closed)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_A_CaseClose");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyCaseStatusClosed(caseIdCreated);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Upload Image", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void FNP_TC_67(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Upload Image";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyUploadProfileImage(globalVariables.filePathImage);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Documents Expiring (Expired, Expired in less than 6 months, Expired in more than 6 months)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_68_0"})
	public void FNP_TC_152(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - Verify Documents Expiring (Expired, Expired in less than 6 months, Expired in more than 6 months)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePage = new FnpHomePage(driver);	
			
			fnpHomePage.verifyExpirationDocuments_Expired(globalVariables.documentExpirationTemplateExpired);
			
			fnpHomePage.verifyExpirationDocuments_ExpiringInLessThan6Months(globalVariables.documentExpirationTemplateLessThan6Months);
			
			fnpHomePage.verifyExpirationDocuments_ExpiredInMoreThan6Months(globalVariables.documentExpirationTemplateMoreThan6Months);

			fnpHomePage.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
//	@Test(groups={"FNP", "Priority2"},description = "FNP - Change Password and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void FNP_TC_68(String browser) throws Exception
//	{
//		globalVariables.testCaseDescription = "FNP - Change Password and verify";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//		String oldFNPPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//		
//		try {
//
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			login.fnplogin(fnpLoginId, oldFNPPassword, true);
//			
//			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
//			
//			fnpHomePagelogin.changePassword(oldFNPPassword, newPassword,workbookNameWrite, sheetName);
//
//			fnpHomePagelogin.clickLogout(true);
//			
//			login.fnplogin(fnpLoginId, newPassword, true);
//			
//			fnpHomePagelogin.clickLogout(true);
//
//			Log.testCaseResult();
//
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
//	
//	
//	@Test(groups={"FNP", "Priority2"},description = "FNP - Change Login ID and verify", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//	public void FNP_TC_69(String browser) throws Exception
//	{
//		globalVariables.testCaseDescription = "FNP - Change Login ID and verify";
//		final WebDriver driver = WebDriverFactory.get(browser);
//		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//		
//		ReadWriteExcel readExcel = new ReadWriteExcel();
//		String oldFnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//		String password = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//		
//		try {
//
//			LoginPageTest login = new LoginPageTest(driver, webSite);
//			
//			login.fnplogin(oldFnpLoginId, password, true);
//			
//			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
//			
//			fnpHomePagelogin.changeLoginId(newLoginID, workbookNameWrite, sheetName);
//
//			fnpHomePagelogin.clickLogout(true);
//			
//			login.fnplogin(newLoginID, password, true);
//			
//			fnpHomePagelogin.clickLogout(true);
//
//			Log.testCaseResult();
//
//		} 
//		catch (Exception e) {
//			Log.exception(e, driver);
//		}
//		finally {
//			Log.endTestCase();
//			driver.quit();
//		}
//	}
	
	
/*	@Test(groups={"FNP", "Priority2"},description = "FNP - Verify Important Documents and Dates for Relative", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_79_0"})
	public void FNP_TC_92(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP : Verify Important Documents and Dates for Relative";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
		String relativeLastName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeLastName");
		String relativeName = relativeFirstName + " " + relativeLastName;
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.clickOnRelative(relativeName);
			
			fnpHomePagelogin.verifyVisaDetailsInImportantDocumentsAndDate(globalVariables.visaType ,globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP );

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}*/
	
	
	@Test(groups={"FNP", "Priority2"}, description = "FNP - Verify Passport on Dasboard Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_10_3"})
	public void FNP_TC_75(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "FNP - Verify Passport on Dasboard Page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
		String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
		
		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			login.fnplogin(fnpLoginId,fnpPassword, true);
			
			FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);	
			
			fnpHomePagelogin.verifyPassportOnDashboard(globalVariables.genericStartDate_FNP, globalVariables.genericEndDate_FNP);

			fnpHomePagelogin.clickLogout(true);

			Log.testCaseResult();

		} 
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(groups={"FNP", "Priority1", "HRP"}, description = "FNP - Messages: Verify the sent emails", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4"})
    public void FNP_TC_74_2(String browser) throws Exception
    {
          
          /*
          * Check if extranet access is for All in Advanced Settings
          * 
           */
          globalVariables.testCaseDescription = "FNP - Messages: Verify the sent emails";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);

                fnpHomePagelogin.clickMessagesTab();
                
                FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
                
                fnpMessagePage.sendEmail(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
                
                fnpMessagePage.verifyIfSentEmailPresent(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
               
                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }


    @Test(description = "Verify Doc Checklist",groups={"FNP", "Priority1"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_75_0")
    public void CM_TC_75_8(String browser) throws Exception
    {
          globalVariables.testCaseDescription = "Verify Doc Checklist";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickCaseLink(caseIdCreated);
                
                FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
                
                fnpCaseProfilePage.clickSupportingDocsTab();
                
                fnpCaseProfilePage.verifyIfDocChecklistPresent(globalVariables.docChecklistNameForFNP);
                
                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(description = "Verify uploaded Document",groups={"FNP", "Priority1"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_75_0")
    public void CM_TC_75_9(String browser) throws Exception
    {
          globalVariables.testCaseDescription = "Verify upload Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickCaseLink(caseIdCreated);
                
                FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
                
                fnpCaseProfilePage.clickSupportingDocsTab();
                
          fnpCaseProfilePage.verifyIfDocumentsAvailable(globalVariables.fileNameWithoutExtension);
                
                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"Priority1", "FNP", "HRP"}, description = "FNP - Verify the sent emails with attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, invocationCount=10)
    public void FNP_TC_4_7(String browser) throws Exception
    {

          globalVariables.testCaseDescription = "FNP - Verify the sent emails with attachment";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

          try 
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePage = new FnpHomePage(driver);
                
                fnpHomePage.clickMessagesTab();
                
                FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
                
                fnpMessagesPage.composeEmailWithAttachment(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectAttachment, globalVariables.filePath, globalVariables.fileName);
                
                fnpMessagesPage.verifyIfSentEmailPresentWithAttachment(globalVariables.sendMessage, globalVariables.sendSubjectAttachment, globalVariables.fileName);
                
                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"Priority1"}, description = "FNP - RELATIVE: Verify the sent emails with attachment", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void FNP_TC_4_8(String browser) throws Exception
    {

          globalVariables.testCaseDescription = "FNP - RELATIVE: Verify the sent emails with attachment";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");

          try 
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePage = new FnpHomePage(driver);
                
                fnpHomePage.clickOnRelative(relativeFirstName);
                
                fnpHomePage.clickMessagesTab();
                
                FNP_MessagesPage fnpMessagesPage = new FNP_MessagesPage(driver);
                
                fnpMessagesPage.composeEmailWithAttachment(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.clientContentEmailSubject, globalVariables.filePathFNP, globalVariables.fileNameFNP);
                
                fnpMessagesPage.verifyIfSentEmailPresentWithAttachment(globalVariables.sendMessage, globalVariables.clientContentEmailSubject, globalVariables.fileNameFNP);
                
                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"Priority1"}, description = "FNP - RELATIVE: Verify the sent emails", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"com.inszoomapp.testscripts.CaseManagerTestScript.CM_TC_67_4"})
    public void FNP_TC_4_9(String browser) throws Exception
    {
          globalVariables.testCaseDescription = "FNP - RELATIVE: Verify the sent emails";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String relativeFirstName = readExcel.initTest(workbookName, sheetName, "QA_ALoginSavedRelativeFirstName");
          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickOnRelative(relativeFirstName);

                fnpHomePagelogin.clickMessagesTab();
                
                FNP_MessagesPage fnpMessagePage = new FNP_MessagesPage(driver);
                
                fnpMessagePage.sendEmail(globalVariables.sendEmailID, globalVariables.sendMessage, globalVariables.sendSubjectContent);
                
                fnpMessagePage.verifyIfEmailPresent(globalVariables.sendEmailID, globalVariables.sendSubjectContent, globalVariables.sendMessage);
                
                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"Priority1", "FNP"}, description="Add e-consent, decline it and then Accept it. Check if e-consent is logged in Activity", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void FNP_TC_1_0(String browser) throws Exception
    {
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
        String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
        String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");

        globalVariables.testCaseDescription = "Add e-consent, decline it and then Accept it. Check if e-consent is logged in Activity";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
        
        try 
        {
        	LoginPageTest login = new LoginPageTest(driver, webSite);

        	CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

        	login.clickAgreeButton(false);

        	caseManagerHomePage.clickPortalSetup(true);
              
        	PortalSetup portalSetup = new PortalSetup(driver);
		      
        	portalSetup.clickDefaultPortalAccess(false);
		      
        	portalSetup.searchCorporationByName(corpName, false);
		      
        	portalSetup.clickCustomizeCorporationSettings(false);
		      
        	portalSetup.clickPortalContentTab();
		      
        	portalSetup.addEConsent(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
		      
        	portalSetup.chooseEConsentforClient(globalVariables.eConsentTitle);
		      
        	driver.close();
        	Utils.waitForAllWindowsToLoad(1, driver);
        	Utils.switchWindows(driver, "My Zoomboard", "title", "false");
		      
        	login = new LoginPageTest(driver, webSite);
		      
        	login.fnpLoginForEconsent(fnpLoginId,fnpPassword, true);
		      
        	FnpHomePage fnpHomePage = new FnpHomePage(driver);
		      
        	fnpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
		      
        	fnpHomePage.declineEConsentAndCheck();
		      
        	login = new LoginPageTest(driver, webSite);
		      
        	login.fnpLoginForEconsent(fnpLoginId,fnpPassword, true);
		      
        	fnpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
		      
        	fnpHomePage.acceptEConsentAndCheck(clientName);
        	
        	fnpHomePage.clickMyProfile();
        	
        	FNPProfilePage fnpProfilePage = new FNPProfilePage(driver);
        	
        	fnpProfilePage.clickActivityTab();
        	
        	fnpProfilePage.verifyIfEConsentLogged(globalVariables.eConsentTitle);
		      
        	Log.testCaseResult();
              
        } catch (Exception e) {
        	Log.exception(e, driver);
        } finally {
        	Log.endTestCase();
        	driver.quit();
        }
  }

    
    
//  @Test(groups={"Priority1", "FNP"}, description="Decline e-consent in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="FNP_TC_1_0")
//  public void FNP_TC_1_1(String browser) throws Exception
//  {
//        ReadWriteExcel readExcel = new ReadWriteExcel();
//        String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//        String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//        
//        globalVariables.testCaseDescription = "Decline e-consent in FNP";
//        final WebDriver driver = WebDriverFactory.get(browser);
//        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//        
//        try 
//        {
//              LoginPageTest login = new LoginPageTest(driver, webSite);
//
//              login.fnplogin(fnpLoginId,fnpPassword, true);
//
//              FnpHomePage fnpHomePage = new FnpHomePage(driver);
//
//              login.clickAgreeButton(false);
//
//              fnpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
//              
//              fnpHomePage.declineEConsentAndCheck();
//              
//              Log.testCaseResult();
//              
//        } catch (Exception e) {
//              Log.exception(e, driver);
//        } finally {
//              Log.endTestCase();
//              driver.quit();
//        }
//  }
//  
//  
//  @Test(groups={"Priority1", "FNP"}, description="Accept e-consent in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="FNP_TC_1_0")
//  public void FNP_TC_1_2(String browser) throws Exception
//  {
//        ReadWriteExcel readExcel = new ReadWriteExcel();
//        String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
//        String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
//        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
//        
//        globalVariables.testCaseDescription = "Accept e-consent in FNP";
//        final WebDriver driver = WebDriverFactory.get(browser);
//        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//        
//        try 
//        {
//              LoginPageTest login = new LoginPageTest(driver, webSite);
//
//              login.fnplogin(fnpLoginId,fnpPassword, true);
//
//              FnpHomePage fnpHomePage = new FnpHomePage(driver);
//
//              login.clickAgreeButton(false);
//
//              fnpHomePage.verifyIfEConsentAppears(globalVariables.eConsentTitle, globalVariables.eConsentDescription);
//              
//              fnpHomePage.acceptEConsentAndCheck(clientName);
//              
//              Log.testCaseResult();
//              
//        } catch (Exception e) {
//              Log.exception(e, driver);
//        } finally {
//              Log.endTestCase();
//              driver.quit();
//        }
//  }
//  


    @Test(groups={"client", "documents"}, description = "CLIENT: Check if Document uploaded in FNP is present in client Document page and check Filter for 'Client'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="CM_TC_75_2")
    public void Client_Doc_33(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Check if Document uploaded in FNP is present in client Document page and check Filter for 'Client'";
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
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileNameFNP);
                
                clientDocumentsPage.selectFilter("Client");
              
                clientDocumentsPage.verifyIfFilteredDocumentPresent(globalVariables.fileNameFNP, clientFile1Name);
              
                clientDocumentsPage.selectFilter("Client");
                
                Log.testCaseResult();
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