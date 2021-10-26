package com.inszoomapp.testscripts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

public class ToDoTestScripts extends BaseTest
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

            input = new FileInputStream("./src/main/resources/config.properties");
            prop.load(input);

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
                    ReadWriteExcel readExcel = new ReadWriteExcel();
                    userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
                    password = readExcel.initTest(workbookName, sheetName, "CM_password");
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
    
    
    @BeforeMethod(alwaysRun = true)
    public void skipTestCase()
    {
            if(globalVariables.SKIP_REMAINING_TESTS)
            {
                throw new SkipException("Skipping the testCase");
            }
    }
    
    @Test(groups={"mandatory"}, description = "Creation of Corporation in CM Login", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_1(String browser) throws Exception 
    {
        globalVariables.testCaseDescription = "Case Manager: Creation of Corporation in Case Manager";
        
        final WebDriver driver = WebDriverFactory.get(browser);
        
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try {

            LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(true);

            CM_CorporationPage corporationPage = caseManagerHomePage.clickCorporationTab(true);

            corporationPage.clickAddCorporationButton(true);
            
            corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);

            corporationPage.clickSaveCorporationButton(true);

            corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);

            caseManagerHomePage.clickLogout(true);

            Log.testCaseResult();
            
        } catch (Exception e) {
            Log.exception(e, driver);
        } finally {
            Log.endTestCase();
            driver.quit();
        }
    }
    
    
    @Test(groups={"mandatory"} , description = "Creation of Client", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_3(String browser) throws Exception 
    {
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        globalVariables.clientFirstName = globalVariables.clientFirstName + strDate;
        globalVariables.clientLastName = globalVariables.clientLastName + strDate;
        
        String[] name = {globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientFirstName + " " + globalVariables.clientLastName};
        String[] nameKeys = {"ALoginSavedFirstClientName", "ALoginSavedLastClientName", "ALoginSavedClientName"};;

        globalVariables.testCaseDescription = "Case Manager: Creation of client/FN in a corporation in Case Manager";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try {
            LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            CM_ClientListPage clientListPage = caseManagerHomePage.clickClientTab(true);

            clientListPage.clickAddClientButton(true);

            clientListPage.enterDataToCreateClient(workbookNameWrite, sheetName, corpName, globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientEmailID);
            
            Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);

            clientListPage.clickSaveClientButton(true);

            clientListPage.verifyIfClientCreated(workbookNameWrite, sheetName, true);

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
    
    
    @Test(groups={"mandatory"} , description = "Creation of Case", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_60_3(String browser) throws Exception
    {
        ReadWriteExcel readExcel = new ReadWriteExcel();
        String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
        String savedClientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");

        globalVariables.testCaseDescription = "Case Manager: Creation of Case (USA) in newly created  corp client";
        final WebDriver driver = WebDriverFactory.get(browser);
        driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

        try {
            LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
            
            login.clickAgreeButton(false);

            CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);

            caseListPage.clickAndAddCase(workbookNameWrite, sheetName, savedClientName, corpName, data.CaseManagerToWork, data.AddCase_CountryName, data.PetitionType_AddCase, true);          

            caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForUSA", data.AddCase_CountryName,savedClientName, corpName, workbookNameWrite, sheetName, true);
            
            caseManagerHomePage.clickLogout(true);

            Log.testCaseResult();
        } catch (Exception e) {
            Log.exception(e, driver);
        } finally {
            Log.endTestCase();
            driver.quit();
        }
    }
    
    @Test(groups={"Priority1", "client"}, description = "Client: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void CM_TC_21_1(String browser) throws Exception 
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
                
                clientDocumentsPage.uploadDocument(globalVariables.filePath);
                
                clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileName);
                
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "client");
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "corporation");
                clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName, "vendor");
                
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
    
    

  @Test(groups = {"ToDo", "client"}, description = "Client: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_98_1(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

          login.caseManagerlogin(userName, password, true);

          login.clickAgreeButton(false);

          caseManagerHomePage.clickClientTab(true);
          
          CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
          
          clientListPage.clickOnClientName(clientFirstName, true);
          
          clientListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuestionnaireTemplate("client", clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  
  @Test(groups = {"ToDo", "client"}, description = "Client: To-Do Docs and Letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_98_2(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoDocsAndLetterInstruction = "Instruction for To-Do Docs and Letter";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

          login.caseManagerlogin(userName, password, true);

          login.clickAgreeButton(false);

          caseManagerHomePage.clickClientTab(true);
          
          CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
          
          clientListPage.clickOnClientName(clientFirstName, true);
          
          clientListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingDocsAndLetter("client", clientName, toDoDocsAndLetterInstruction, econsent, globalVariables.fileNameWithoutExtension);
          
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
  
  
  @Test(groups = {"ToDo", "client"}, description = "Client: To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_98_3(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoQuestionnaireInstruction = "Instruction for To-Do Invoice";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Client: To-Do Invoice";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

          login.caseManagerlogin(userName, password, true);

          login.clickAgreeButton(false);

          caseManagerHomePage.clickClientTab(true);
          
          CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
          
          clientListPage.clickOnClientName(clientFirstName, true);
          
          clientListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingInvoiceTemplate("client", clientName, toDoQuestionnaireInstruction, econsent);
          
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
  
  @Test(groups = {"ToDo", "client"}, description = "Client: To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_98_4(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
      
      String toDoName = "To Do Quick Task Test";
      
      globalVariables.testCaseDescription = "Client: Advanced Email- Check Select Template";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);
          
          CM_DashboardPage caseManagerHomePage = new CM_DashboardPage(driver);

          login.caseManagerlogin(userName, password, true);

          login.clickAgreeButton(false);

          caseManagerHomePage.clickClientTab(true);
          
          CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
          
          clientListPage.clickOnClientName(clientFirstName, true);
          
          clientListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuickTask("client", clientName, toDoName, toDoQuickTaskInstruction);
          
          //clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  
  @Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_102_1(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
      
      String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
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
          
          corpListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuestionnaireTemplate("corp",assignTo, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  @Test(groups = {"corporation"}, description = "Corp: Upload a document and change the Access Rights", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_105(String browser) throws Exception 
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
          
          clientDocumentsPage.uploadDocument(globalVariables.filePath);
          
          clientDocumentsPage.verifyIfDocumentUploaded(globalVariables.fileName);
          
//        clientDocumentsPage.changeDocumentAccessRights(globalVariables.fileName);
//        
//        clientDocumentsPage.verifyIfDocumentAccessRightsChanged(globalVariables.fileName);
          
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
  
  
  @Test(groups = { "case"}, description = "Case: Add Docs Checklist and upload a document to the created docs checklist", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_42_1(String browser) throws Exception 
  {
      
      ReadWriteExcel readExcel = new ReadWriteExcel();
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      String[] docChecklistName = {globalVariables.docChecklistNameExtra};
      String[] docChecklistNameKey = {"QA_A_Case_addedBasicDesc"};

      globalVariables.testCaseDescription = "Case: Add Docs Checklist and upload a document to the created docs checklist";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

          CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

          login.clickAgreeButton(false);
      
          CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);
          
          caseListPage.clickOnCaseId(caseCreated, true);
          
          caseListPage.clickDocsCheckListOrDocumentsTab(false);
          
          CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
          
          caseDocChecklistPage.addDocChecklist(clientName, globalVariables.docChecklistNameExtra);
          
          caseDocChecklistPage.verifyIfChecklistAdded(globalVariables.docChecklistNameExtra);
          
          Utils.saveDataToExcel(workbookNameWrite, sheetName, docChecklistName, docChecklistNameKey);
          
          caseDocChecklistPage.uploadDocument(globalVariables.docChecklistNameExtra, globalVariables.filePath, clientName);
          
          caseDocChecklistPage.verifyIfDocumentUploaded(globalVariables.docChecklistNameExtra, globalVariables.fileName);
          
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
  
  @Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Docs and Letter", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider",dependsOnMethods = "CM_TC_105")
  public void CM_TC_102_2(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String toDoDocsAndLetterInstruction = "Instruction for To-Do Docs and Letter";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
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
          
          corpListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingDocsAndLetter("corp", assignTo, toDoDocsAndLetterInstruction, econsent, globalVariables.fileNameWithoutExtension);
          
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
  
  
  @Test(groups = {"ToDo", "corporation"}, description = "Corp: To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_101_1")
  public void CM_TC_102_3(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
      
      String toDoInvoiceInstruction = "Instruction for To-Do Invoice";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
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
          
          corpListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingInvoiceTemplate("corp", assignTo, toDoInvoiceInstruction, econsent);
          
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
  
  
  @Test(groups = {"ToDo", "corporation"}, description = "Corp : To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_102_4(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
      
      String toDoName = "To Do Quick Task Test";
      
      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
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
          
          corpListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuickTask("corp", assignTo, toDoName, toDoQuickTaskInstruction);
          
          //clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  
  @Test(groups = {"ToDo", "case"}, description = "Case: To-Do Questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_112_1(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoQuestionnaireInstruction = "Instruction for To-Do Questionnaire";

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Case: To-Do Questionnaire";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
          
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuestionnaireTemplateAndDocsCheckList(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  
  @Test(groups = {"ToDo", "case"}, description = "Case: To-Do Docs CheckLists", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
  public void CM_TC_112_2(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoDocCheckListInstruction = "Instruction for To-Do Docs CheckLists";

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Case: To-Do Docs CheckLists";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
          
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingDocsCheckList(clientName, toDoDocCheckListInstruction, econsent, globalVariables.docChecklistName);
          
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
  
  
  
  @Test(groups = {"ToDo", "case"}, description = "Case: To-Do Docs", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_42_1")
  public void CM_TC_112_2_1(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoDocsInstruction = "Instruction for To-Do Docs";

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Case: To-Do Docs";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
          
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoDocs(clientName, toDoDocsInstruction, econsent, globalVariables.fileNameWithoutExtension);
          
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
    
    
  @Test(groups = {"ToDo", "case"}, description = "Case: To-Do Forms", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods = "CM_TC_38_0")
  public void CM_TC_112_2_2(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();
      
      String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
      
      String toDoFormsInstruction = "Instruction for To-Do Forms";

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";

      globalVariables.testCaseDescription = "Case: To-Do Forms";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
          
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoForms(clientName, toDoFormsInstruction, econsent, globalVariables.formNameForAddTestcase);
          
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
    
    
  @Test(groups = {"ToDo", "case"}, description = "Case : To-Do Invoice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_112_3(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String toDoInvoiceInstruction = "Case : To-Do Invoice";
      
      //String econsent = "e-consent for Automation";
      
      String econsent = "Agreement Policy";
      
      String toDoName = "To Do Invoice Test";
      
      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
            
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingInvoiceTemplate("case", assignTo, toDoInvoiceInstruction, econsent);
          
          //clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
  
  
  @Test(groups = {"ToDo", "case"}, description = "Case : To-Do Quick task", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void CM_TC_112_4(String browser) throws Exception 
  {

      ReadWriteExcel readExcel = new ReadWriteExcel();

      String caseCreated = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
      
      String assignTo = readExcel.initTest(workbookName, sheetName, "QA_A_assignedCaseManagerName");
      
      String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
      
      String toDoName = "To Do Quick Task Test";
      
      globalVariables.testCaseDescription = "Corp: Advanced Email- Check Select Template";
      final WebDriver driver = WebDriverFactory.get(browser);
      driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
      

      try {
          LoginPageTest login = new LoginPageTest(driver, webSite);

            CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

            login.clickAgreeButton(false);

            caseManagerDashboardPage.clickCaseTab(true);
            
            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(caseCreated, true);
            
            caseListPage.clickToDoTab();
          
          CM_ClientToDoPage clientToDoPage = new CM_ClientToDoPage(driver);
          
          clientToDoPage.addNewToDoUsingQuickTask("case", assignTo, toDoName, toDoQuickTaskInstruction);
          
          //clientToDoPage.addNewToDoUsingDocsAndLetters(clientName, toDoQuestionnaireInstruction, econsent, globalVariables.questionnaireName);
          
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
