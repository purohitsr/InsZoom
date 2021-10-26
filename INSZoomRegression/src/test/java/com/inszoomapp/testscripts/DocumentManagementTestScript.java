package com.inszoomapp.testscripts;

import java.util.Arrays;
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
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_CorporationDocumentsPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRP_CorporationPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.KB_DocumentTagsFoldersPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class DocumentManagementTestScript extends BaseTest
{
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	private String CM_userName = "";
	private String CM_password = "";
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
					CM_userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					CM_password = readExcel.initTest(workbookName, sheetName, "CM_password");
					break;
				}
				case "DEV": {
					data = new AppDataDev();
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Dev.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Dev.xls";
					sheetName = "Inszoom";
					CM_userName = data.CM_userName;
					CM_password = data.CM_password;
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
					CM_userName = data.CM_userName;
					CM_password = data.CM_password;

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
					CM_userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					CM_password = readExcel.initTest(workbookName, sheetName, "CM_password");
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
	
	
	@Test(groups = {"corporation", "documents"}, description = "CORP: Check if Corporation name is present on Top", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Doc_1(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "CORP: Check if Corporation name is present on Top";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickDocumentTab();
			
			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
			
			corporationDocumentsPage.checkCorporationNameInHeader(corpName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"corporation", "documents"}, description = "CORP: Check if Breadcrumbs are CORPORATION LIST > CORPORATION PROFILE > Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Corp_Doc_2(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "CORP: Check if Breadcrumbs are CORPORATION LIST > CORPORATION PROFILE > Documents";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickDocumentTab();
			
			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
			
			corporationDocumentsPage.checkBreadcrumbs();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(groups={"corporation", "documents", "HRP"}, description = "CORPORATION: Upload a document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Corp_Doc_3_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String[] temp = globalVariables.corpFile1Initial.split("[.]");
          String[] dataToWrite = {globalVariables.corpFile1Initial, temp[0]};
          String[] columnInExcel = {"corpFile1Name", "corpFile1NameWithoutExtension"}; 

          globalVariables.testCaseDescription = "CORPORATION: Upload a document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.uploadDocument(globalVariables.corpFile1Path);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.corpFile1Initial);
    			
    			Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Upload multiple documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Corp_Doc_3_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Upload multiple document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.uploadDocument(globalVariables.corpFile2Path);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.corpFile2Name);
    			
    			corporationDocumentsPage.uploadDocument(globalVariables.corpFile3Path);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.corpFile3Name);
    			
    			corporationDocumentsPage.uploadDocument(globalVariables.corpFile4Path);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.corpFile4Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if all the options are available in Quick Actions for the Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_4(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
          
          globalVariables.testCaseDescription = "CORPORATION: Check if all the options are available in Quick Actions for the Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.verifyIfOptionsAvailableInQuickActions(corpFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if all the options for selected are disbaled if no Document is selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Corp_Doc_5_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Check if all the options for selected are disbaled if no Document is selected";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
               
    			corporationDocumentsPage.verifyIfOptionsDisbaled();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if all the options for selected are enbaled if Documents are selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_5_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Check if all the options for selected are enbaled if Documents are selected";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
               
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.verifyIfOptionsEnabled();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Download a File", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_6(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Download a File";
          final RemoteWebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.downloadSingleDocument(corpFile1Name, driver);
    			
    			corporationDocumentsPage.verifyIfDownloaded(driver);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Delete a File", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Corp_Doc_7(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Delete a File";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.uploadDocument(globalVariables.deleteFilePath);
                
    			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.deleteFileName);
                
    			corporationDocumentsPage.deleteSingleDocument(globalVariables.deleteFileName);
                
    			corporationDocumentsPage.verifyIfDeleted(globalVariables.deleteFileName);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
        
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if Uploaded Documents are available to be added in Corporation To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Corp_Doc_3_1")
    public void Corp_Doc_10_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "corpFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CORPORATION: Check if Uploaded Documents are available to be added in Corporation To-Do";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickToDoTab();
    			
    			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
    			
    			clientToDoPage.checkIfDocsAvailable(corpFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Preview a Document and tag 6 folders and check if limit has reached and check on the main page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_3_1"})
    public void Corp_Doc_11(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Preview a Document and tag 6 folders and check if limit has reached and check on the main page";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.addTagFromPreview(globalVariables.listOfTags);
    			
    			corporationDocumentsPage.verifyIfAlertForTagsPresent();
    			
    			corporationDocumentsPage.clickSaveButton();
    			
    			corporationDocumentsPage.verifyIfTagsAdded(corpFile1Name, globalVariables.listOfTags);	
    			
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.removeTagFromPreview();
    			
    			corporationDocumentsPage.verifyIfTagRemoved(corpFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Preview a Document and Rename it", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_22", "Corp_Doc_10_3"})
    public void Corp_Doc_12(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
          String corpFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "corpFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CORPORATION: Preview a Document and Rename it";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.renameDocument(corpFile1Name, corpFile1NameWithoutExtension, workbookNameWrite, sheetName);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if renamed Document is available in Todo page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Corp_Doc_12")
    public void Corp_Doc_13_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "corpFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CORPORATION: Check if renamed Document is available in Todo page";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickToDoTab();
    			
    			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
    			
    			clientToDoPage.checkIfDocsAvailable(corpFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
	@Test(groups = {"documents"}, description="HRP: Check if renamed Document is available in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_12")
	public void Corp_Doc_13_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String corpFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "corpFile1NameWithoutExtension");
		
		globalVariables.testCaseDescription = "HRP: Check if renamed Document is available in HRP";
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
			
			hrpCorporationPage.verifyIfDocumentPresent(corpFile1NameWithoutExtension);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Preview a Document and add Description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_14(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Preview a Document and add Description";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.addDescriptionFromPreview(corpFile1Name, globalVariables.documentDescription);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
       
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Preview a Document and change the access right", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_15(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
          
          globalVariables.testCaseDescription = "CORPORATION: Preview a Document and change the access right";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.verifyAccessBefore(corpFile1Name);
              
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.changeAccessFromPreview(corpFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Preview the Document and change the status of Document to 'Need Clarification'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_16(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Preview the Document and change the status of Document to 'Need Clarification'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.previewDocument(corpFile1Name);
    			
    			corporationDocumentsPage.changeStatusFromPreview("Need Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Need Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Accept a Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_17_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Accept a Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Accept");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Accept");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }

    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Change the status of Document to Pending Review", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_17_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Change the status of Document to Pending Review";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Pending Review");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Pending Review");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Change the status of Document to Need Clarification", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_17_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Change the status of Document to Need Clarification";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Need Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Need Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Tag 6 Folders for a Document and check if limit is reached", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_18(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Tag 6 Folders for a Document and check if limit is reached";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickFiltersButtton();
    			
    			corporationDocumentsPage.checkIfFiltersNotPresent("Tags", globalVariables.listOfTags);
              
    			corporationDocumentsPage.addTag(corpFile1Name, globalVariables.listOfTags);
                
    			corporationDocumentsPage.verifyIfAlertForTagsPresent();
    			
    			corporationDocumentsPage.verifyIfTagsAdded(corpFile1Name, globalVariables.listOfTags);
    			
    			corporationDocumentsPage.clickFiltersButtton();
    			
    			corporationDocumentsPage.checkIfFiltersPresent("Tags", globalVariables.listOfTags);
    		
    			corporationDocumentsPage.removeTag(corpFile1Name);
                
    			corporationDocumentsPage.verifyIfTagRemoved(corpFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }

    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Add Passport as a Tag and choose a Country then check 'Passport' filter then Remove the Tag", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_3_1", "Corp_Doc_3_2"})
    public void Corp_Doc_20(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Add Passport as a Tag and choose a Country then Remove the Tag";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.addPassport(corpFile1Name, globalVariables.country);
     				
    			corporationDocumentsPage.verifyIfCountryAdded(corpFile1Name, globalVariables.countryForPassport);
    			
    			corporationDocumentsPage.selectFilter("Passport");
    			
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(corpFile1Name, globalVariables.corpFile2Name);
    			   
    			corporationDocumentsPage.selectFilter("Passport");
    			
    			corporationDocumentsPage.removeTag(corpFile1Name);
 				
    			corporationDocumentsPage.verifyIfTagRemoved(corpFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents", "HRP"}, description = "CORPORATION: Give Corporation access to a Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
    public void Corp_Doc_21(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Give Corporation access to a Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.changeAccessRights(corpFile1Name, "corporation");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
	@Test(groups = {"HRP", "documents"}, description="HRP: Check the uploaded Document from A-Login in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_21")
	public void Corp_Doc_22(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();

		String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		String corpFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "corpFile1NameWithoutExtension");
		
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
			
			hrpCorporationPage.verifyIfDocumentPresent(corpFile1NameWithoutExtension);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "KB", "corporation"}, description="KB : Add a Tag in KB and check if we can use that tag in Documents page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_1")
	public void Corp_Doc_23_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
        String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
        
		globalVariables.testCaseDescription = "KB : Add a Tag in KB and check if we can use that tag in Documents page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

            login.clickAgreeButton(false);
			
            caseManagerHomePage.clickKnowledgeBase(false);
            
            CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
            
            knowledgeBasePage.clickDocumentTagsFolders();
            
            KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
            
            documentTagsFoldersPage.addTagFolder(globalVariables.tagFolderNameCorp);
            
            caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickDocumentTab();
			
			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
			
			corporationDocumentsPage.addTag(corpFile1Name, Arrays.asList(globalVariables.tagFolderNameCorp));
			
			corporationDocumentsPage.verifyIfTagsAdded(corpFile1Name, Arrays.asList(globalVariables.tagFolderNameCorp));
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "KB", "corporation"}, description="KB : Remove the added tag and check if we can use that tag in Documents page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_23_1")
	public void Corp_Doc_23_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
        String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
        
		globalVariables.testCaseDescription = "KB : Remove the added tag and check if we can use that tag in Documents page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

            login.clickAgreeButton(false);
			
            caseManagerHomePage.clickKnowledgeBase(false);
            
            CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
            
            knowledgeBasePage.clickDocumentTagsFolders();
            
            KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
            
            documentTagsFoldersPage.deleteTagFolder(globalVariables.tagFolderNameCorp);
            
            caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
			
			corpListPage.clickOnCorporationName(corpName);
			
			corpListPage.clickDocumentTab();
			
			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
			
			corporationDocumentsPage.verifyIfTagRemoved(corpFile1Name);
			            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
   
	
    @Test(groups={"corporation", "parked"}, description = "CORPORATION: Give Vendor access to multiple Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = "Corp_Doc_3_2")
    public void Corp_Doc_25_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Give Corporation access to multiple Documents";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.changeAccessRightForMultiple("vendor");
    			
    			corporationDocumentsPage.checkForAccessRights(globalVariables.corpFile2Name, "vendor");
    			
    			corporationDocumentsPage.checkForAccessRights(globalVariables.corpFile3Name, "vendor");
    			
    			corporationDocumentsPage.checkForAccessRights(globalVariables.corpFile4Name, "vendor");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
	
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Accept multiple Documents and check filter for 'Pending Review'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_3_2", "Corp_Doc_3_1"})
    public void Corp_Doc_25_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Accept multiple Documents and check filter for 'Pending Review'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.changeStatusForMultiple("Accept");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile2Name, "Accept");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile3Name, "Accept");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile4Name, "Accept");
    			
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Pending Review");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Pending Review");
    			
    			corporationDocumentsPage.selectFilter("Pending Review");
    			
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(corpFile1Name, globalVariables.corpFile2Name);
   
    			corporationDocumentsPage.selectFilter("Pending Review");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Make multiple Documents 'Pending for Review' and check filter for 'Needs Clarification'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_3_2", "Corp_Doc_3_1"})
    public void Corp_Doc_25_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Make multiple Documents 'Pending for Review' and check filter for 'Needs Clarification'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.changeStatusForMultiple("Pending for Review");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile2Name, "Pending for Review");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile3Name, "Pending for Review");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile4Name, "Pending for Review");
    			
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Need Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Need Clarification");
    			
    			corporationDocumentsPage.selectFilter("Needs Clarification");
    			
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(corpFile1Name, globalVariables.corpFile2Name);
   
    			corporationDocumentsPage.selectFilter("Needs Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Make multiple Documents 'Needs Clarification' and check filter for 'Accepted'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Corp_Doc_3_2", "Corp_Doc_3_1"})
    public void Corp_Doc_25_4(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
          String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");

          globalVariables.testCaseDescription = "CORPORATION: Make multiple Documents 'Needs Clarification' and check filter for 'Accepted'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    		
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.changeStatusForMultiple("Needs Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile2Name, "Needs Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile3Name, "Needs Clarification");
    			
    			corporationDocumentsPage.checkIfStatusChanged(globalVariables.corpFile4Name, "Needs Clarification");
    			
    			corporationDocumentsPage.changeStatus(corpFile1Name, "Accept");
    			
    			corporationDocumentsPage.checkIfStatusChanged(corpFile1Name, "Accept");
    			
    			corporationDocumentsPage.selectFilter("Accept");
    			
    			corporationDocumentsPage.verifyIfFilteredDocumentPresent(corpFile1Name, globalVariables.corpFile2Name);
   
    			corporationDocumentsPage.selectFilter("Accept");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
	
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Download multiple files", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_2")
    public void Corp_Doc_26(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Download multiple files";
          final RemoteWebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.downloadAllDocuments(driver);
    			
    			corporationDocumentsPage.verifyIfDownloaded(driver);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
	
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check if uploaded Documents are getting arranged in Ascending order and then check for Descending order", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Corp_Doc_3_2")
    public void Corp_Doc_30(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Check for all table headings";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.verifyAscendingOrderOfDocuments();
    			
    			corporationDocumentsPage.verifyDescendingOrderOfDocuments();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
    
    @Test(groups={"corporation", "documents"}, description = "CORPORATION: Check for all table headings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Corp_Doc_32(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Check for all table headings";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
              
    			corporationDocumentsPage.checkIfHeadingsPresent();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
	
	@Test(groups = {"client", "documents"}, description = "CLIENT: Check if Client name and Corporation name are present on Top", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Doc_1(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		globalVariables.testCaseDescription = "CLIENT: Check if Client name and Corporation name are present on Top";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickDocumentsTab();
			
			CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
			
			clientDocumentsPage.checkCorporationAndClientNameInHeader(corpName, clientName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"client", "documents"}, description = "CLIENT: Check if Breadcrumbs are CLIENT LIST > CLIENT PROFILE > Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Client_Doc_2(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
        String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "CLIENT: Check if Breadcrumbs are CLIENT LIST > CLIENT PROFILE > Documents";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

			login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickClientTab(true);
			
			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
			
			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickDocumentsTab();
			
			CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
			
			clientDocumentsPage.checkBreadcrumbs();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(groups={"client", "documents", "HRP", "FNP"}, description = "CLIENT: Upload a document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_3_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String[] temp = globalVariables.clientFile1Initial.split("[.]");
          String[] dataToWrite = {globalVariables.clientFile1Initial, temp[0]};
          String[] columnInExcel = {"clientFile1Name", "clientFile1NameWithoutExtension"}; 

          globalVariables.testCaseDescription = "CLIENT: Upload a document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.uploadDocument(globalVariables.clientFile1Path);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile1Initial);
                
                Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel);
              
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Upload multiple documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_3_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Upload multiple documents";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.uploadDocument(globalVariables.clientFile2Path);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile2Name);
                
                clientDocumentsPage.uploadDocument(globalVariables.clientFile3Path);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile3Name);
                
                clientDocumentsPage.uploadDocument(globalVariables.clientFile4Path);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile4Name);
              
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if all the options are available in Quick Actions for the Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_4(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Check if all the options are available in Quick Actions for the Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
               
                clientDocumentsPage.verifyIfOptionsAvailableInQuickActions(clientFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if all the options for selected are disbaled if no Document is selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_5_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Check if all the options for selected are disbaled if no Document is selected";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
               
                clientDocumentsPage.verifyIfOptionsDisbaled();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if all the options for selected are enbaled if Documents are selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_5_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Check if all the options for selected are enbaled if Documents are selected";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.clickSelectAll();
               
                clientDocumentsPage.verifyIfOptionsEnabled();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Download a File", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_6(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Download a File";
          final RemoteWebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    			
    			clientDocumentsPage.downloadSingleDocument(clientFile1Name, driver);
    			
    			clientDocumentsPage.verifyIfDownloaded(driver);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Delete a File", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_7(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Delete a File";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    			
                clientDocumentsPage.uploadDocument(globalVariables.deleteFilePath);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.deleteFileName);
                
                clientDocumentsPage.deleteSingleDocument(globalVariables.deleteFileName);
                
                clientDocumentsPage.verifyIfDeleted(globalVariables.deleteFileName);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"case", "documents"}, description = "CASE: Check if Documents uploaded for CLIENT are available to be included in Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_10_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");


          globalVariables.testCaseDescription = "CASE: Check if Documents uploaded for CLIENT are available to be included in Case Email";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCaseTab(false);
    			
    			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
    			
    			caseListPage.clickOnCaseId(caseCreated, false);
    			
    			caseListPage.clickEmailsTab();
    			
    			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
    			
    			caseEmailPage.clickComposeEmailButton();
    			
    			caseEmailPage.attachDocument(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CASE: Check if Documents uploaded for CLIENT are available to be included in Client Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_10_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CASE: Check if Documents uploaded for CLIENT are available to be included in Client Email";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
    			
                clientListPage.clickEmailsTab();
    			
    			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
    			
    			clientEmailPage.clickComposeEmailButton();
    			
    			clientEmailPage.attachDocument(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_10_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client TO-Do";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
    			
                clientListPage.clickToDoTab();
    			
    			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
    			
    			clientToDoPage.checkIfDocsAvailable(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Tag 6 Folders for a Document and check if limit is reached", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_11(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Tag 6 Folders for a Document and check if limit is reached";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.previewDocument(clientFile1Name);
    			
                clientDocumentsPage.addTagFromPreview(globalVariables.listOfTags);
    			
                clientDocumentsPage.verifyIfAlertForTagsPresent();
    			
                clientDocumentsPage.clickSaveButton();
    			
                clientDocumentsPage.verifyIfTagsAdded(clientFile1Name, globalVariables.listOfTags);	
    			
                clientDocumentsPage.previewDocument(clientFile1Name);
    			
                clientDocumentsPage.removeTagFromPreview();
    			
                clientDocumentsPage.verifyIfTagRemoved(clientFile1Name);
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
 
    @Test(groups={"client", "documents"}, description = "CLIENT: Preview a Document and Rename it", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_Doc_10_1"})
    public void Client_Doc_12(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

          
          globalVariables.testCaseDescription = "CLIENT: Preview a Document and Add Description";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.previewDocument(clientFile1Name);
                
                clientDocumentsPage.renameDocument(clientFile1Name, clientFile1NameWithoutExtension, workbookNameWrite, sheetName);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"case", "documents"}, description = "CASE: Check if Documents uploaded for CLIENT are available to be included in Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_12")
    public void Client_Doc_13_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");


          globalVariables.testCaseDescription = "CASE: Check if Documents uploaded for CLIENT are available to be included in Case Email";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCaseTab(false);
    			
    			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
    			
    			caseListPage.clickOnCaseId(caseCreated, false);
    			
    			caseListPage.clickEmailsTab();
    			
    			CM_CaseEmailsPage caseEmailPage = new CM_CaseEmailsPage(driver);
    			
    			caseEmailPage.clickComposeEmailButton();
    			
    			caseEmailPage.attachDocument(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_12")
    public void Client_Doc_13_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client Email";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
    			
                clientListPage.clickEmailsTab();
    			
    			CM_ClientEmailsPage clientEmailPage = new CM_ClientEmailsPage(driver);
    			
    			clientEmailPage.clickComposeEmailButton();
    			
    			clientEmailPage.attachDocument(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_12")
    public void Client_Doc_13_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameWithoutExtension");

          globalVariables.testCaseDescription = "CLIENT: Check if Documents uploaded for CLIENT are available to be included in Client TO-Do";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
    			
                clientListPage.clickToDoTab();
    			
    			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
    			
    			clientToDoPage.checkIfDocsAvailable(clientFile1NameWithoutExtension);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Preview a Document and Add Description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_14(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Preview a Document and Add Description";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.previewDocument(clientFile1Name);
                
                clientDocumentsPage.addDescriptionFromPreview(clientFile1Name, globalVariables.documentDescription);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Preview a Document and change the access right", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_15(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Preview a Document and change the access right";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.verifyAccessBefore(clientFile1Name);
              
                clientDocumentsPage.previewDocument(clientFile1Name);
                
                clientDocumentsPage.changeAccessFromPreview(clientFile1Name);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Change the status of Document to Need Clarification", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_16(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Change the status of Document to Need Clarification";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.previewDocument(clientFile1Name);
                
                clientDocumentsPage.changeStatusFromPreview("Need Clarification");
    			
                clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Need Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Accept a Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_17_1(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Accept a Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.changeStatus(clientFile1Name, "Accept");
    			
                clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Accept");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Change the status of Document to Pending Review", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_17_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Change the status of Document to Pending Review";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.changeStatus(clientFile1Name, "Pending Review");
    			
                clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Pending Review");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Change the status of Document to Need Clarification", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_17_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Change the status of Document to Need Clarification";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.changeStatus(clientFile1Name, "Need Clarification");
    			
                clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Need Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    

    @Test(groups={"client", "documents"}, description = "CLIENT: Preview a Document and tag 6 folders and check if limit has reached and check on the main page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_18(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Preview a Document and tag 6 folders and check if limit has reached and check on the main page";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.clickFiltersButtton();
    			
                //clientDocumentsPage.checkIfFiltersNotPresent("Tags", globalVariables.listOfTags);
              
                clientDocumentsPage.addTag(clientFile1Name, globalVariables.listOfTags);
                
                clientDocumentsPage.verifyIfAlertForTagsPresent();
                
                clientDocumentsPage.verifyIfTagsAdded(clientFile1Name, globalVariables.listOfTags);
                
                clientDocumentsPage.clickFiltersButtton();
    			
                clientDocumentsPage.checkIfFiltersPresent("Tags", globalVariables.listOfTags);
    		
                clientDocumentsPage.removeTag(clientFile1Name);
                
                clientDocumentsPage.verifyIfTagRemoved(clientFile1Name);
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Add Passport as a Tag and choose a Country then check 'Passport' filter then Remove the Tag", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_Doc_3_1", "Client_Doc_3_2"})
    public void Client_Doc_20(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Add Passport as a Tag and choose a Country then Remove the Tag";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.addPassport(clientFile1Name, globalVariables.country);
    			
                clientDocumentsPage.verifyIfCountryAdded(clientFile1Name, globalVariables.countryForPassport);
                
                clientDocumentsPage.selectFilter("Passport");
    			
                clientDocumentsPage.verifyIfFilteredDocumentPresent(clientFile1Name, globalVariables.clientFile2Name);
    			   
                clientDocumentsPage.selectFilter("Passport");
    			
                clientDocumentsPage.removeTag(clientFile1Name);
 				
                clientDocumentsPage.verifyIfTagRemoved(clientFile1Name);
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents", "FNP", "HRP"}, description = "CLIENT: Give Corporation and Client access to a Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_21(String browser) throws Exception 
    {           
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Give Corporation and Client access to a Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.changeDocumentAccessRights(clientFile1Name, "client");
                clientDocumentsPage.changeDocumentAccessRights(clientFile1Name, "corporation");

                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);

          }
          finally {
                Log.endTestCase();
                driver.quit();

          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Add a Tag in KB and check if we can use that tag in Documents page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_1")
    public void Client_Doc_23_1(String browser) throws Exception 
    {           
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Add a Tag in KB and check if we can use that tag in Documents page";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
        	  LoginPageTest login = new LoginPageTest(driver, webSite);

              CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

              login.clickAgreeButton(false);
  			
              caseManagerHomePage.clickKnowledgeBase(false);
              
              CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
              
              knowledgeBasePage.clickDocumentTagsFolders();
              
              KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
              
              documentTagsFoldersPage.addTagFolder(globalVariables.tagFolderNameClient);
                
              CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
            
              clientListPage.clickOnClientName(clientName, true);
            
              clientListPage.clickDocumentsTab();
            
              CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
            
              clientDocumentsPage.addTag(clientFile1Name, Arrays.asList(globalVariables.tagFolderNameClient));     
              
              clientDocumentsPage.verifyIfTagsAdded(clientFile1Name, Arrays.asList(globalVariables.tagFolderNameClient));

            Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);

          }
          finally {
                Log.endTestCase();
                driver.quit();

          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Remove the added tag and check if we can use that tag in Documents page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_23_1")
    public void Client_Doc_23_2(String browser) throws Exception 
    {           
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

          globalVariables.testCaseDescription = "CLIENT: Remove the added tag and check if we can use that tag in Documents page";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
        	  LoginPageTest login = new LoginPageTest(driver, webSite);

              CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

              login.clickAgreeButton(false);
  			
              caseManagerHomePage.clickKnowledgeBase(false);
              
              CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
              
              knowledgeBasePage.clickDocumentTagsFolders();
              
              KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
              
              documentTagsFoldersPage.deleteTagFolder(globalVariables.tagFolderNameClient);
                
              CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
            
              clientListPage.clickOnClientName(clientName, true);
            
              clientListPage.clickDocumentsTab();
            
              CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
            
              clientDocumentsPage.verifyIfTagRemoved(clientFile1Name);    

            Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);

          }
          finally {
                Log.endTestCase();
                driver.quit();

          }
    }
    
    
    @Test(groups={"client"}, description = "CLIENT: Give Vendor access to multiple Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods = {"Client_Doc_3_2", "Case_Doc_25_1"})
    public void Client_Doc_25_1(String browser) throws Exception 
    {           
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Give Vendor access to multiple Documents";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.clickSelectAll();
                
                clientDocumentsPage.changeAccessRightForMultiple("vendor");
                
                clientDocumentsPage.checkForAccessRights(globalVariables.clientFile2Name, "vendor");
                clientDocumentsPage.checkForAccessRights(globalVariables.clientFile3Name, "vendor");
                clientDocumentsPage.checkForAccessRights(globalVariables.clientFile4Name, "vendor");

                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();

          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Accept multiple files and check filter for 'Pending Review'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_Doc_3_2", "Client_Doc_3_1"})
    public void Client_Doc_25_2(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");
          
          globalVariables.testCaseDescription = "CLIENT: Accept multiple files and check filter for 'Pending Review'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    			
    			clientDocumentsPage.clickSelectAll();
    			
    			clientDocumentsPage.changeStatusForMultiple("Accept");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile2Name, "Accept");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile3Name, "Accept");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile4Name, "Accept");
    			
    			clientDocumentsPage.changeStatus(clientFile1Name, "Pending Review");
    			
    			clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Pending Review");
    			
    			clientDocumentsPage.selectFilter("Pending Review");
    			
    			clientDocumentsPage.verifyIfFilteredDocumentPresent(clientFile1Name, globalVariables.clientFile2Name);
   
    			clientDocumentsPage.selectFilter("Pending Review");
   
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }

    
    @Test(groups={"client", "documents"}, description = "CLIENT: Make multiple Documents 'Pending for Review' and check filter for 'Needs Clarification'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_Doc_3_2", "Client_Doc_3_1"})
    public void Client_Doc_25_3(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");
          
          globalVariables.testCaseDescription = "CLIENT: Make multiple Documents 'Pending for Review' and check filter for 'Needs Clarification'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    		
    			clientDocumentsPage.clickSelectAll();
    			
    			clientDocumentsPage.changeStatusForMultiple("Pending for Review");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile2Name, "Pending for Review");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile3Name, "Pending for Review");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile4Name, "Pending for Review");
    			
    			clientDocumentsPage.changeStatus(clientFile1Name, "Need Clarification");
    			
    			clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Need Clarification");
    			
    			clientDocumentsPage.selectFilter("Needs Clarification");
    			
    			clientDocumentsPage.verifyIfFilteredDocumentPresent(clientFile1Name, globalVariables.clientFile2Name);
   
    			clientDocumentsPage.selectFilter("Needs Clarification");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Make multiple Documents 'Needs Clarification' and check filter for 'Accepted'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = {"Client_Doc_3_2", "Client_Doc_3_1"})
    public void Client_Doc_25_4(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");
          
          globalVariables.testCaseDescription = "CLIENT: Make multiple Documents 'Needs Clarification' and check filter for 'Accepted'";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    			
    			clientDocumentsPage.clickSelectAll();
    			
    			clientDocumentsPage.changeStatusForMultiple("Needs Clarification");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile2Name, "Needs Clarification");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile3Name, "Needs Clarification");
    			
    			clientDocumentsPage.checkIfStatusChanged(globalVariables.clientFile4Name, "Needs Clarification");
    			
    			clientDocumentsPage.changeStatus(clientFile1Name, "Accept");
    			
    			clientDocumentsPage.checkIfStatusChanged(clientFile1Name, "Accept");
    			
    			clientDocumentsPage.selectFilter("Accept");
    			
    			clientDocumentsPage.verifyIfFilteredDocumentPresent(clientFile1Name, globalVariables.clientFile2Name);
   
    			clientDocumentsPage.selectFilter("Accept");
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Download multiple files", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_2")
    public void Client_Doc_26(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          
          globalVariables.testCaseDescription = "CLIENT: Download multiple files";
          final RemoteWebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    			
    			clientDocumentsPage.clickSelectAll();
    			
    			clientDocumentsPage.downloadAllDocuments(driver);
    			
    			clientDocumentsPage.verifyIfDownloaded(driver);
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Email multiple files", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_2")
    public void Client_Doc_29(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
          
          globalVariables.testCaseDescription = "CLIENT: Email multiple files";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
    				
    			clientDocumentsPage.clickSelectAll();
    			
    			clientDocumentsPage.emailMultipleDocuments();
    			
    			
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check if uploaded Documents are getting arranged in Ascending order and then check for Descending order", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Client_Doc_3_2")
    public void Client_Doc_30(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Check if uploaded Documents are getting arranged in Ascending order and then check for Descending order";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.verifyAscendingOrderOfDocuments();
                
                clientDocumentsPage.verifyDescendingOrderOfDocuments();
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client", "documents"}, description = "CLIENT: Check for all table headings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_32(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Check for all table headings";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
              
                clientDocumentsPage.checkIfHeadingsPresent();
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
	@Test(groups = {"case", "documents"}, description = "CASE: Check if Corporation name, Client name and Case name is present on Top", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_1(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		globalVariables.testCaseDescription = "CASE: Check if Corporation name, Client name and Case name is present on Top";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.checkNamesOnHeader(corpName, clientName, caseId);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "documents"}, description = "CASE: Check 'CASE LIST > CASE PROFILE > Documents' in Breadcrumbs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_2(String browser) throws Exception 
	{		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		globalVariables.testCaseDescription = "CASE: Check 'CASE LIST > CASE PROFILE > Documents' in Breadcrumbs";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.checkBreadcrumbs();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item directly to Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_3_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		String[] docChecklistName = {globalVariables.docChecklistName};
		String[] docChecklistNameKey = {"caseDocChecklistName"};

		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistName);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistName);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
		
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
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item directly to Corporation", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_3_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
	
		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Corporation";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(corpName, globalVariables.docChecklistNameCorp);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameCorp);
			
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
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item directly to Firm", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_3_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
	
		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Firm";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickSettings(false);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			String firmName = settingsPage.getFirmName();
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklist(firmName, globalVariables.docChecklistNameFirm);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameFirm);
			
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
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item to Petition Template for Client and check in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_4_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklistToPetition(clientName, globalVariables.docChecklistNameExtra);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameExtra);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickKnowledgeBase(false);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickDocChecklistItemsTab();
			
			knowledgeBasePage.searchPetitionTemplate(globalVariables.docChecklistNameExtra);
			
			knowledgeBasePage.deletePetitionTemplate(globalVariables.docChecklistNameExtra);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item to Petition Template for Corporation and check in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_4_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklistToPetition(corpName, globalVariables.docChecklistNameCorp);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameCorp);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickKnowledgeBase(false);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickDocChecklistItemsTab();
			
			knowledgeBasePage.searchPetitionTemplate(globalVariables.docChecklistNameCorp);
			
			knowledgeBasePage.deletePetitionTemplate(globalVariables.docChecklistNameCorp);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Add Checklist Item to Petition Template for Firm and check in KB", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_4_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Case: Add Checklist Item directly to Client";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickSettings(false);
			
			CM_SettingsPage settingsPage = new CM_SettingsPage(driver);
			
			String firmName = settingsPage.getFirmName();
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addDocChecklistToPetition(firmName, globalVariables.docChecklistNameFirm);
			
			caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameFirm);
			
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
			
			caseManagerHomePage.clickKnowledgeBase(false);
			
			CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			knowledgeBasePage.clickDocChecklistItemsTab();
			
			knowledgeBasePage.searchPetitionTemplate(globalVariables.docChecklistNameFirm);
			
			knowledgeBasePage.deletePetitionTemplate(globalVariables.docChecklistNameFirm);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Upload a document to the created docs checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Doc_3_1")
	public void Case_Doc_5_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		String[] temp = globalVariables.caseFile1Initial.split("[.]");
        String[] dataToWrite = {globalVariables.caseFile1Initial, temp[0]};
        String[] columnInExcel = {"caseFile1Name", "caseFile1NameWithoutExtension"};
		
		globalVariables.testCaseDescription = "Case: Upload a document to the created docs checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.caseFile1Path, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.caseFile1Initial);
			
			caseDocChecklistPage.verifyReceived(caseDocChecklistName, "Received");
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Upload multiple documents to the created docs checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Doc_3_1")
	public void Case_Doc_5_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		globalVariables.testCaseDescription = "Case: Upload multiple documents to the created docs checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.caseFile3Path, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.caseFile3Name);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.caseFile2Path, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.caseFile2Name);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.caseFile4Path, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.caseFile4Name);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Check if all the options are available in Quick Actions for the Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_6(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
	
		globalVariables.testCaseDescription = "CASE: Check if all the options are available in Quick Actions for the Document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
		
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.verifyIfOptionsAvailableInQuickActions(caseFile1Name, caseDocChecklistName);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Check if all the options for selected are disbaled if no Doc checklist is selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_7_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE: Check if all the options for selected are disbaled if no Doc checklist is selected";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.verifyIfOptionsDisbaled();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Check if all the options for selected are disbaled if no Doc checklist is selected", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Doc_5_1")
	public void Case_Doc_7_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE: Check if all the options for selected are disbaled if no Doc checklist is selected";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.verifyIfOptionsEnabled();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE: Download a Single Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Doc_5_1")
	public void Case_Doc_8(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");

		globalVariables.testCaseDescription = "CASE: Download a Single Document";
		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.downloadSingleDocument(caseDocChecklistName, caseFile1Name, driver);
			
			caseDocChecklistPage.verifyIfDownloaded(driver);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Delete Document from doc checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_9(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CASE - Delete Document from doc checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.deleteFilePath, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.deleteFileName);
			
			caseDocChecklistPage.deleteUploadedDocument(caseDocChecklistName, globalVariables.deleteFileName);
			
			caseDocChecklistPage.verifyIfDocumentDeleted(caseDocChecklistName, globalVariables.deleteFileName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "document"}, description = "CASE : Check if Documents uploaded in Doc Checklist are available to be added in Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Case_Doc_5_1"})
	public void Case_Doc_10_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");
		
		globalVariables.testCaseDescription = "CASE : Send Email after attaching Questionnaire, uploading Document and including e-consent";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.attachDocument(caseFile1Name);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "document"}, description = "CASE : Check if Doc Checklists are available to be added in Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Case_Doc_3_1"})
	public void Case_Doc_10_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE : Send Email after attaching Questionnaire, uploading Document and including e-consent";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.includeDocChecklist(caseDocChecklistName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check if Doc Checklists are available to be added in Client To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_10_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CASE - Check if Doc Checklists are available to be added in Client To-Do";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickToDoTab();
			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfDocChecklistAvailable(caseDocChecklistName, clientName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check if Document is available to be added in Case To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_10_4(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");

		globalVariables.testCaseDescription = "CASE - Check if Document is available to be added in Case To-Do";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickToDoTab();
			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfCaseDocumentAvailable(caseFile1NameWithoutExtension);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	/*@Test(groups = {"documents", "case"}, description = "CASE - Check if Document is available to be added in Case To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_29_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");

		globalVariables.testCaseDescription = "CASE - Check if Document is available to be added in Case To-Do";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickToDoTab();
			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfCaseDocumentAvailable(caseFile1NameWithoutExtension);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	*/
	@Test(groups = {"documents", "case"}, description = "Case: Preview a Document and tag 6 folders and check if limit has reached and check on the main page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_11(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");

		globalVariables.testCaseDescription = "Case: Preview a Document and tag 6 folders and check if limit has reached and check on the main page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
						
			caseDocChecklistPage.addTagFromPreview(globalVariables.listOfTags);
			
			caseDocChecklistPage.verifyIfAlertForTagsPresent();
			
			caseDocChecklistPage.clickSaveButton();
			
			caseDocChecklistPage.verifyIfTagAddedToDocument(caseDocChecklistName, caseFile1Name, globalVariables.listOfTags);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.removeTagFromPreview();
			
			caseDocChecklistPage.clickSaveButton();
			
			caseDocChecklistPage.verifyIfTagRemovedFromDocument(caseDocChecklistName, caseFile1Name);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Preview a Document and rename it", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Case_Doc_10_4", "Case_Doc_22"})
	public void Case_Doc_12(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
		
		globalVariables.testCaseDescription = "Case: Preview a Document and rename it";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.renameDocument(caseFile1Name, caseFile1NameWithoutExtension, globalVariables.caseRenamedFile, workbookNameWrite, sheetName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check if renamed Document is available in Todo page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_12")
	public void Case_Doc_13_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");

		globalVariables.testCaseDescription = "CASE - Check if renamed Document is available in Todo page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickToDoTab();
			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfCaseDocumentAvailable(caseFile1NameWithoutExtension);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(description = "Verify renamed Document in FNP",groups={"documents"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_12")
    public void Case_Doc_13_4(String browser) throws Exception
    {
          globalVariables.testCaseDescription = "Verify renamed Document in FNP";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");
          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickCaseLink(caseIdCreated);
                
                FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
                
                fnpCaseProfilePage.clickSupportingDocsTab();
                
                fnpCaseProfilePage.verifyIfDocumentsAvailable(caseFile1NameWithoutExtension);
                
                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
	
	@Test(groups = {"case", "document"}, description = "CASE : Check if renamed Document is available in Emails page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Case_Doc_12"})
	public void Case_Doc_13_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");
		
		globalVariables.testCaseDescription = "CASE : Check if renamed Document is available in Emails page";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.attachDocument(caseFile1Name);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Preview a Document and add Description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_14(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
		
		globalVariables.testCaseDescription = "Case: Preview a Document and add Description";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.addDescriptionToDocument(globalVariables.eConsentDescription);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Preview a Document and change Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_15(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
		
		globalVariables.testCaseDescription = "Case: Preview a Document and change Status";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.changeDocumentAccessRightsFormPreview("Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, caseFile1Name, "Vendor");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Preview a Document and change Status", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_16(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
		
		globalVariables.testCaseDescription = "Case: Preview a Document and change Status";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocumentToEdit(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.changeStatusFromPreview("Need Clarification");
			
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, caseFile1Name, "Need Clarification");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}	
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Mark a Document as 'Accepted', 'Pending Review', 'Needs Clarification'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_17(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");

		globalVariables.testCaseDescription = "CASE - Mark a Document as 'Accepted', 'Pending Review', 'Needs Clarification'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);

			caseDocChecklistPage.changeStatus(caseDocChecklistName, caseFile1Name, "Need Clarification");	
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, caseFile1Name, "Need Clarification");
			
			caseDocChecklistPage.changeStatus(caseDocChecklistName, caseFile1Name, "Accepted");	
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, caseFile1Name, "Accepted");
			
			caseDocChecklistPage.changeStatus(caseDocChecklistName, caseFile1Name, "Pending Review");	
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, caseFile1Name, "Pending Review");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Tag 6 Folders for a Doc checklist and Document then check if limit is reached", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_18(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");
		
		globalVariables.testCaseDescription = "CASE - Add Passport as a Tag and choose a Country then Add a Document and check if Passport tag is automatically added to it and Remove the tag";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addTag(caseDocChecklistName, globalVariables.listOfTags);
			
			caseDocChecklistPage.verifyIfAlertForTagsPresent();
						
			caseDocChecklistPage.removeTag(caseDocChecklistName);
			
			caseDocChecklistPage.verifyIfTagRemoved(caseDocChecklistName);
			
			caseDocChecklistPage.addTagToDocument(caseDocChecklistName, caseFile1Name, globalVariables.listOfTags);
			
			caseDocChecklistPage.verifyIfAlertForTagsPresent();
			
			caseDocChecklistPage.removeTagFromDocument(caseDocChecklistName, caseFile1Name);
			
			caseDocChecklistPage.verifyIfTagRemovedFromDocument(caseDocChecklistName, caseFile1Name);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Toggle between Received/Partially Received/Not Received", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_1")
	public void Case_Doc_19(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Toggle between Received/Partially Received/Not Received";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.changeReceived(caseDocChecklistName, "Received", "Not Received");
			caseDocChecklistPage.verifyReceived(caseDocChecklistName, "Not Received");
			
			caseDocChecklistPage.changeReceived(caseDocChecklistName, "Not Received", "Partially Received");
			caseDocChecklistPage.verifyReceived(caseDocChecklistName, "Partially Received");
			
			caseDocChecklistPage.changeReceived(caseDocChecklistName, "Partially Received", "Received");
			caseDocChecklistPage.verifyReceived(caseDocChecklistName, "Received");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Add Passport as a Tag and choose a Country then Add a Document and check if Passport tag is automatically added to it and Remove the tag", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_20(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CASE - Add Passport as a Tag and choose a Country then Add a Document and check if Passport tag is automatically added to it and Remove the tag";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addPassportToChecklist(caseDocChecklistName, globalVariables.country);
			
			caseDocChecklistPage.verifyIfCountryAddedToChecklist(caseDocChecklistName, globalVariables.countryForPassport);
			
			caseDocChecklistPage.uploadDocument(caseDocChecklistName, globalVariables.filePath, clientName);
			
			caseDocChecklistPage.verifyIfDocumentUploaded(caseDocChecklistName, globalVariables.fileName);
			
			caseDocChecklistPage.verifyIfTagAddedToDocument(caseDocChecklistName, globalVariables.fileName, Arrays.asList("Passport"));
			
			caseDocChecklistPage.removeTag(caseDocChecklistName);
			
			caseDocChecklistPage.removeTagFromDocument(caseDocChecklistName, globalVariables.fileName);
			
			caseDocChecklistPage.verifyIfTagRemovedFromDocument(caseDocChecklistName, globalVariables.fileName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "Case: Give Access to Client and Corporation for Document", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "Case_Doc_5_1")
	public void Case_Doc_21(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String caseFile1Name = readExcel.initTest(workbookName, sheetName, "caseFile1Name");

		globalVariables.testCaseDescription = "Case: Give Access to Client and Corporation for Document";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.changeDocumentAccessRights(caseDocChecklistName, caseFile1Name, "Corporation");
			
			caseDocChecklistPage.changeDocumentAccessRights(caseDocChecklistName, caseFile1Name, "Client");

			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, caseFile1Name, "Corporation");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, caseFile1Name, "Client");
		
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(description = "Verify uploaded Document in FNP", groups={"documents"}, dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_21")
    public void Case_Doc_22(String browser) throws Exception
    {
          globalVariables.testCaseDescription = "Verify upload Document";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
          String caseIdCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
          String caseFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "caseFile1NameWithoutExtension");
          
          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                login.fnplogin(fnpLoginId,fnpPassword, true);

                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
                
                fnpHomePagelogin.clickCaseLink(caseIdCreated);
                
                FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
                
                fnpCaseProfilePage.clickSupportingDocsTab();
                
                fnpCaseProfilePage.verifyIfDocumentsAvailable(caseFile1NameWithoutExtension);
                
                fnpHomePagelogin.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {
                Log.exception(e, driver);
          } finally {
                Log.endTestCase();
                driver.quit();
          }
    }
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Add a Tag in KB and check if we can use that tag in Checklist page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_23_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		
		globalVariables.testCaseDescription = "CASE - Add a Tag in KB and check if we can use that tag in Checklist page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickKnowledgeBase(false);
            
            CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
            
            knowledgeBasePage.clickDocumentTagsFolders();
            
            KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
            
            documentTagsFoldersPage.addTagFolder(globalVariables.tagFolderNameCase);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.addTag(caseDocChecklistName, Arrays.asList(globalVariables.tagFolderNameCase));
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Remove the added tag and check if we can use that tag in Checklist page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_23_1")
	public void Case_Doc_23_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		
		globalVariables.testCaseDescription = "CASE - Remove the added tag and check if we can use that tag in Checklist page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickKnowledgeBase(false);
		    
		    CM_KnowledgeBasePage knowledgeBasePage = new CM_KnowledgeBasePage(driver);
		    
		    knowledgeBasePage.clickDocumentTagsFolders();
		    
		    KB_DocumentTagsFoldersPage documentTagsFoldersPage = new KB_DocumentTagsFoldersPage(driver);
		    
		    documentTagsFoldersPage.deleteTagFolder(globalVariables.tagFolderNameCase);
		    
			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.verifyIfTagRemoved(caseDocChecklistName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case"}, description = "CASE - Change access for multiple Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")//, dependsOnMethods="Case_Doc_5_2")
	public void Case_Doc_25_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Mark a Document as Change access for multiple Documents";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocChecklist(caseDocChecklistName);
			
			caseDocChecklistPage.changeAccessForMultipleDocuments(caseDocChecklistName, "Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, globalVariables.caseFile2Name, "Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, globalVariables.caseFile3Name, "Vendor");
			
			caseDocChecklistPage.verifyIfDocumentAccessRightsChanged(caseDocChecklistName, globalVariables.caseFile4Name, "Vendor");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Mark multiple Documents as 'Accepted', 'Pending Review', 'Needs Clarification'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_2")
	public void Case_Doc_25_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Mark multiple Documents as 'Accepted', 'Pending Review', 'Needs Clarification'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocChecklist(caseDocChecklistName);
			
			caseDocChecklistPage.changeDocChecklistStatus(caseDocChecklistName, "Need Clarification");
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, globalVariables.caseFile2Name, "Need Clarification");
			
			caseDocChecklistPage.changeDocChecklistStatus(caseDocChecklistName, "Accepted");
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, globalVariables.caseFile3Name, "Accepted");
			
			caseDocChecklistPage.changeDocChecklistStatus(caseDocChecklistName, "Pending Review");
			caseDocChecklistPage.checkIfStatusChanged(caseDocChecklistName, globalVariables.caseFile4Name, "Pending Review");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check if uploaded Documents are getting arranged in Ascending order and then check for Descending order", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_5_2")
	public void Case_Doc_30(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Check if uploaded Documents are getting arranged in Ascending order and then check for Descending order";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.verifyAscendingOrderOfDocuments(caseDocChecklistName);
			
			caseDocChecklistPage.verifyDescendingOrderOfDocuments(caseDocChecklistName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check for all headings", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_32(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE - Check for all headings";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.checkIfHeadingsPresent();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Toggle 'Required to Upload' between 'Required', 'If Applicable' and 'Optional'", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_34(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Toggle 'Required to Upload' between 'Required', 'If Applicable' and 'Optional'";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.changeRequiredToUpload(caseDocChecklistName, "Required");
			
			caseDocChecklistPage.changeRequiredToUpload(caseDocChecklistName, "Optional");
			
			caseDocChecklistPage.changeRequiredToUpload(caseDocChecklistName, "If Applicable");
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check Doc checklist in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_35(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
	    String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "CASE - Check Doc checklist in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

            FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
            
            fnpHomePagelogin.clickCaseLink(caseCreated);
            
            FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
            
            fnpCaseProfilePage.clickSupportingDocsTab();
            
            fnpCaseProfilePage.verifyIfDocChecklistPresent(caseDocChecklistName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Edit Doc checklist name and add Description", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_3_1")
	public void Case_Doc_36(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		String[] docChecklistName = {globalVariables.docChecklistNameNew};
		String[] docChecklistNameKey = {"caseDocChecklistName"};
		
		globalVariables.testCaseDescription = "CASE - Edit Doc checklist name and add Description";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.editDocChecklistNameAndAddDescription(clientName, caseDocChecklistName, globalVariables.docChecklistNameNew, globalVariables.docChecklistDescriptionNew);
			
			caseDocChecklistPage.verifyIfChecklistEditedAndDescriptionAdded(globalVariables.docChecklistNameNew, globalVariables.docChecklistDescriptionNew);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case", "document"}, description = "CASE : Check if renamed Doc-checklists are available to be included in Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Case_Doc_36"})
	public void Case_Doc_37_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE : Check if renamed Doc-checklists are available to be included in Case Email";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.includeDocChecklist(caseDocChecklistName);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check if renamed Doc Checklists are available to be added in Client To-Do", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_36")
	public void Case_Doc_37_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "CASE - Check if renamed Doc Checklists are available to be added in Client To-Do";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickToDoTab();
			
			CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
			
			clientToDoPage.checkIfDocChecklistAvailable(caseDocChecklistName, clientName);
			
			clientToDoPage.checkIfLongDescriptionAvailableForDocChecklist(caseDocChecklistName, globalVariables.docChecklistDescriptionNew);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check renamed Doc checklist in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="Case_Doc_36")
	public void Case_Doc_37_3(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		
		String fnpLoginId = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
	    String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");

		globalVariables.testCaseDescription = "CASE - Check renamed Doc checklist in FNP";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			login.fnplogin(fnpLoginId,fnpPassword, true);

            FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
            
            fnpHomePagelogin.clickCaseLink(caseCreated);
            
            FNP_CaseProfilePage fnpCaseProfilePage = new FNP_CaseProfilePage(driver);
            
            fnpCaseProfilePage.clickSupportingDocsTab();
            
            fnpCaseProfilePage.verifyIfDocChecklistPresent(caseDocChecklistName);
            
            fnpCaseProfilePage.verifyIfChecklistDescriptionPresent(caseDocChecklistName, globalVariables.docChecklistDescriptionNew);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"documents", "case"}, description = "CASE - Check Previous Documents for Client and Corporation in Document Checklist Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods={"Client_Doc_3_1", "Corp_Doc_3_1"})
	public void Case_Doc_38(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

        String corpFile1Name = readExcel.initTest(workbookName, sheetName, "corpFile1Name");
        String clientFile1Name = readExcel.initTest(workbookName, sheetName, "clientFile1Name");

		globalVariables.testCaseDescription = "CASE - Check Previous Documents for Client and Corporation in Document Checklist Page";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.clickPreviousDocuments();
			
			caseDocChecklistPage.checkPreviousDocuments(corpName, corpFile1Name);
			
			caseDocChecklistPage.checkPreviousDocuments(clientName, clientFile1Name);

			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
    @Test(description="CORPORATION: Delete multiple Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", groups={"corp"})
    public void Corp_Doc_27(String browser) throws Exception 
    {           
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

          globalVariables.testCaseDescription = "CORPORATION: Delete multiple Documents";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                                
                caseManagerHomePage.clickCorporationTab(true);
    			
    			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
    			
    			corpListPage.clickOnCorporationName(corpName);
    			
    			corpListPage.clickDocumentTab();
    			
    			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
    			
    			corporationDocumentsPage.clickSelectAll();
    			
    			corporationDocumentsPage.deleteMultiple();
    			
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    
    @Test(groups={"client"}, description = "CLIENT: Delete All Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void Client_Doc_27(String browser) throws Exception 
    {           
          
          ReadWriteExcel readExcel = new ReadWriteExcel();
          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

          globalVariables.testCaseDescription = "CLIENT: Delete All Documents";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

                login.clickAgreeButton(false);
                
                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
                
                clientListPage.clickOnClientName(clientName, true);
                
                clientListPage.clickDocumentsTab();
                
                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
                
                clientDocumentsPage.clickSelectAll();
                
                clientDocumentsPage.deleteMultiple();
                
                Log.testCaseResult();
          }
          catch (Exception e) {
                Log.exception(e, driver);
          }
          finally {
                Log.endTestCase();
                driver.quit();
          }
    }
    
    @Test(groups = {"case"}, description = "CASE - Delete All Documents", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_27_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String caseDocChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");

		globalVariables.testCaseDescription = "CASE - Delete All Documents";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.selectDocChecklist(caseDocChecklistName);
			
			caseDocChecklistPage.deleteAllDocumentsUnderDocCheckList(caseDocChecklistName);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"case"}, description = "CASE: Delete all Checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_27_2(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "CASE: Delete all Checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.deleteAllChecklist();
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		}
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = { "case"}, description = "Case - Delete doc checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void Case_Doc_9_1(String browser) throws Exception 
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		String docChecklistName = readExcel.initTest(workbookName, sheetName, "caseDocChecklistName");
		globalVariables.testCaseDescription = "Case - Delete doc checklist";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
			
			caseListPage.clickOnCaseId(caseCreated, true);
			
			caseListPage.clickDocsCheckListOrDocumentsTab(false);
			
			CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
			
			caseDocChecklistPage.deleteDocChecklist(docChecklistName);
			
			caseDocChecklistPage.verifyIfChecklistDeleted(docChecklistName);
			
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
	
	
	 @Test(groups={"client", "documents"}, description = "CLIENT:Check if renamed Document uploaded by case manager is available in FNP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	    public void Client_Doc_13_4(String browser) throws Exception 
	    {           
	          ReadWriteExcel readExcel = new ReadWriteExcel();
	          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
	          String fnpUsername = readExcel.initTest(workbookName, sheetName, "FNPLoginIDCreated");
	          String fnpPassword = readExcel.initTest(workbookName, sheetName, "FNPPassword");
	          
	          globalVariables.testCaseDescription = "CLIENT: Check if renamed Document is available in FNP";
	          final WebDriver driver = WebDriverFactory.get(browser);
	          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	          try {
	                LoginPageTest login = new LoginPageTest(driver, webSite);

	                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

	                login.clickAgreeButton(false);
	                                
	                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
	                
	                clientListPage.clickOnClientName(clientName, true);
	                
	                clientListPage.clickDocumentsTab();
	                
	                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
	                
	                clientDocumentsPage.deleteAllDocuments();
	                
	                clientDocumentsPage.uploadDocument(globalVariables.clientFile5Path);
	                
	                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile5Initial);
	                
	                clientDocumentsPage.enableAccessRight(globalVariables.clientFile5Initial,"Client");
	                
	                clientDocumentsPage.previewDocument(globalVariables.clientFile5Initial);
	                
	                clientDocumentsPage.renameClientDocument(globalVariables.clientFile5Initial, globalVariables.clientFile5NameWithoutExtension, workbookNameWrite, sheetName);
	    			
	                clientDocumentsPage.switchToClientProfileTab(driver);
	    			
	    			caseManagerHomePage.clickLogout(true);
	    			
	                login.fnplogin(fnpUsername,fnpPassword, true);

	                FnpHomePage fnpHomePagelogin = new FnpHomePage(driver);
	                
	                fnpHomePagelogin.clickDocumentsTab();
	                
	                String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameRenamedWithoutExtension");
	    			
	                fnpHomePagelogin.verifyIfDocumentsAvailable(clientFile1NameWithoutExtension);
	                
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
	 
	 @Test(groups={"client", "documents"}, description = "CLIENT: Check if renamed Document uploaded by case manager is available in HRP", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	    public void Client_Doc_13_5(String browser) throws Exception 
	    {           
	          ReadWriteExcel readExcel = new ReadWriteExcel();
	          String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
	          String[] temp = globalVariables.clientFile1Initial.split("[.]");
	          String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
	  		  String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
	          
	          globalVariables.testCaseDescription = "CLIENT: Check if renamed Document is available in HRP";
	          final WebDriver driver = WebDriverFactory.get(browser);
	          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	          try {
	                LoginPageTest login = new LoginPageTest(driver, webSite);

	                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);

	                login.clickAgreeButton(false);
	                                
	                CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);
	                
	                clientListPage.clickOnClientName(clientName, true);
	                
	                clientListPage.clickDocumentsTab();
	                
	                CM_ClientDocumentsPage clientDocumentsPage = new CM_ClientDocumentsPage(driver);
	                
	                clientDocumentsPage.deleteAllDocuments();
	                
	                clientDocumentsPage.uploadDocument(globalVariables.clientFile6Path);
	                
	                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.clientFile6Initial);
	                
	                clientDocumentsPage.enableAccessRight(globalVariables.clientFile6Initial,"Corporation");
	                
	                clientDocumentsPage.previewDocument(globalVariables.clientFile6Initial);
	    			
	                clientDocumentsPage.renameClientDocument(globalVariables.clientFile6Initial, globalVariables.clientFile6NameWithoutExtension, workbookNameWrite, sheetName);
	    			
	                clientDocumentsPage.switchToClientProfileTab(driver);
	    			
	    			caseManagerHomePage.clickLogout(true);
	    			
	    			login.hrpLogin(hrpLoginID, hrpPassword, true);

	    			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
	    			
	    			hrpCorporationPage.clickOnClientTab();
	    			
	    			hrpCorporationPage.selectClientRecord(clientName);
	    			
	    			hrpCorporationPage.clickClientDocument();
	    			
	    			String clientFile1NameWithoutExtension = readExcel.initTest(workbookName, sheetName, "clientFile1NameRenamedWithoutExtension");
	    			
	    			hrpCorporationPage.verifyIfDocumentPresent(clientFile1NameWithoutExtension);
	    			
	                Log.testCaseResult();
	          }
	          catch (Exception e) {
	                Log.exception(e, driver);
	          }
	          finally {
	                Log.endTestCase();
	                driver.quit();
	          }
	    }
	 
	 @Test(groups={"corporation", "documents"}, description = "CORPORATION: Verify if the document uploaded by the case manager is getting filtered out when using filter Case Manager in the document page for a Corporation in Case Manager Portal", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	    public void Corp_Doc_33_1(String browser) throws Exception 
	    {           
		 ReadWriteExcel readExcel = new ReadWriteExcel();
         String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
         
         globalVariables.testCaseDescription = "CORPORATION: Check filter for uploaded by Case Manager.";
         final WebDriver driver = WebDriverFactory.get(browser);
         driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

         try 
         {
	            LoginPageTest login = new LoginPageTest(driver, webSite);
	
	            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);
	
	            login.clickAgreeButton(false);
	                               
	            caseManagerHomePage.clickCorporationTab(true);
	   			
	   			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
	   			
	   			corpListPage.clickOnCorporationName(corpName);
	   			
	   			corpListPage.clickDocumentTab();
	   			
	   			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
	             
	   			corporationDocumentsPage.deleteAllDocuments();
	   			
	   			corporationDocumentsPage.uploadDocument(globalVariables.corpFile5Path);
	               
	   			corporationDocumentsPage.verifyIfDocumentUploaded(globalVariables.corpFile5Initial);
	   			
	   			corporationDocumentsPage.selectFilter("Case Manager");
             
	   			corporationDocumentsPage.verifyIfFilteredDocument(globalVariables.corpFile5Initial,"Case Manager");
	           
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
	 
	 @Test(groups={"corporation", "documents"}, description = "CORPORATION: Verify if the document uploaded from the HRP portal of a corporation is getting filtered out when using filter Coporation in the document page for a Corporation in Case Manager Portal", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	    public void Corp_Doc_33_2(String browser) throws Exception 
	    {           
				 ReadWriteExcel readExcel = new ReadWriteExcel();
		         String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
		         String hrpLoginID = readExcel.initTest(workbookName, sheetName, "QA_HRPUserIdBySendEmail");
		 		 String hrpPassword = readExcel.initTest(workbookName, sheetName, "QA_HRPPassword");
		         
	          globalVariables.testCaseDescription = "CORPORATION: Check filter for uploaded by Corporation";
	          final WebDriver driver = WebDriverFactory.get(browser);
	          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

	          try {
	        	  	LoginPageTest login = new LoginPageTest(driver, webSite);
	        		
	        	  	login.hrpLogin(hrpLoginID, hrpPassword, true);

	  				HrpHomePage hrpHomePage = new HrpHomePage(driver);
	  			
	  				hrpHomePage.clickMyCorporationTab();
	  			
		  			HRP_CorporationPage hrpCorporationPage = new HRP_CorporationPage(driver);
		  			
		  			hrpCorporationPage.clickDigitalDocuments();
		  			
		  			hrpCorporationPage.uploadFile(globalVariables.corpFile5Path);
		  			
		  			hrpCorporationPage.verifyIfDocumentPresent(globalVariables.corpFile5NameWithoutExtension);
	    			
		  			hrpHomePage.clickLogout(true);
		  			
		            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(CM_userName, CM_password, true);
		
		            login.clickAgreeButton(false);
		                               
		            caseManagerHomePage.clickCorporationTab(true);
		   			
		   			CM_CorporationListPage corpListPage = new CM_CorporationListPage(driver);
		   			
		   			corpListPage.clickOnCorporationName(corpName);
		   			
		   			corpListPage.clickDocumentTab();
		   			
		   			CM_CorporationDocumentsPage corporationDocumentsPage = new CM_CorporationDocumentsPage(driver);
		   			
		   			corporationDocumentsPage.selectFilter("Corporation");
	             
		   			corporationDocumentsPage.verifyIfFilteredDocument(globalVariables.corpFile5NameWithoutExtension,"Corporation");
		           
		   			corporationDocumentsPage.selectFilter("Corporation");
		   			
		   			corporationDocumentsPage.deleteAllDocuments();
		   			
	                Log.testCaseResult();
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
