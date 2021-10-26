package com.inszoomapp.testscripts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.AccessCodePage;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CaseQuestionnairePage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientQuestionnairePage;
import com.inszoomapp.pages.CM_CorporationAdvancedCaseRequestTemplatePage;
import com.inszoomapp.pages.CM_CorporationEmailPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_CorporationQuestionnairePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_KBCaseRequestTemplatePage;
import com.inszoomapp.pages.CM_KBQuestionnairePage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_PetitionDetailsPage;
import com.inszoomapp.pages.CM_ToDoPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRPNewsPage;
import com.inszoomapp.pages.HRP_ClientPage;
import com.inszoomapp.pages.HRP_CorporationPage;
import com.inszoomapp.pages.HRP_HeadQuarterPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;


@Listeners(EmailReport.class)
public class QuestionnaireTestScript {

	String webSite = null;
	String env = null;
	String browserName = null;
	
	String sheetName = "";
	String workbookName = "";
	String workbookNameWrite = "";
	
	String userName = null;
	String password = null;
	
	String test = "Intial";
	AppDataBase data ;
	String corporationName = "QuestionnaireTestCorp";
	String newlyCreatedcorpName= "";
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

		String os = System.getProperty("os.name");
		
		globalVariables.browserUsedForExecution = browserName;

		try {

			switch (env.toUpperCase())
			{
				case "QA": {
					data = new AppDataQA();
					userName = data.CM_userNameQuest;
					password = data.CM_passwordQuest;
					globalVariables.environmentName = env;
					
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Stg.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					}
					break;
				}
				case "STAGE": {
					data = new AppDataStage();
					userName = data.CM_userNameQuest;
					password = data.CM_passwordQuest;
					globalVariables.environmentName = env;
					if(os.contains("Linux")){
						workbookName = "testdata//data//Regression_Stg.xls";
						workbookNameWrite = "//src//main//resources//testdata//data//Regression_Stg.xls";
					}else{
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					}
					
					
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
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
					globalVariables.environmentName = env;
					break;
				}
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Create Client Questionnaire")
	public void TC_1() throws Exception 
	{
		//Created by Nitisha Sinha on 9th September,2020
		
		globalVariables.testCaseDescription = "KB: Create Client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBClientQuestionnaireName = globalVariables.KBClientQuestionnaireName + strDate;
				
			caseManagerQuestionnairePage.addClientQuestionnaire(globalVariables.KBClientQuestionnaireName);
			
			caseManagerQuestionnairePage.verifyAddQuestionnaire(globalVariables.KBClientQuestionnaireName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("KBClientQuestionnaireName", sheetName, "Value", globalVariables.KBClientQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Create Corporation Questionnaire")
	public void TC_7() throws Exception 
	{
		//Created by Nitisha Sinha on 10th September,2020
		
		globalVariables.testCaseDescription = "KB: Create Corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBCorporationQuestionnaireName = globalVariables.KBCorporationQuestionnaireName + strDate;
						
			caseManagerQuestionnairePage.addCorporationQuestionnaire(globalVariables.KBCorporationQuestionnaireName, "USA");
			
			caseManagerQuestionnairePage.verifyAddQuestionnaire(globalVariables.KBCorporationQuestionnaireName);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
			writeExcel.setCellData("KBCorporationQuestionnaireName", sheetName, "Value", globalVariables.KBCorporationQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire"}, description = "KB: Delete Corporation Questionnaire", dependsOnMethods = {"TC_7"})
	public void TC_11() throws Exception 
	{
		//Created by Nitisha Sinha on 11th September,2020
		
		globalVariables.testCaseDescription = "KB: Delete Corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(corporationQuestionnaire, true, "firm defined");

			caseManagerQuestionnairePage.deleteQuestionnaire(corporationQuestionnaire);
			
			caseManagerQuestionnairePage.verifyDeleteQuestionnaire(corporationQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Delete Client Questionnaire", dependsOnMethods = {"TC_1"})
	public void TC_5() throws Exception 
	{
		//Created by Nitisha Sinha on 11th September,2020
		
		globalVariables.testCaseDescription = "KB: Delete Client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(clientQuestionnaire, true,  "firm defined");

			caseManagerQuestionnairePage.deleteQuestionnaire(clientQuestionnaire);
			
			caseManagerQuestionnairePage.verifyDeleteQuestionnaire(clientQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: 'Copy to' functionality for zoom defined client Questionnaire")
	public void TC_2() throws Exception 
	{
		//Created by Nitisha Sinha on 14th September,2020
		
		globalVariables.testCaseDescription = "KB: 'Copy to' functionality for zoom defined client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();

			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBClientQuestionnaireName = globalVariables.KBClientQuestionnaireName + strDate;
			
			caseManagerQuestionnairePage.copyQuestionnaireToCountry(globalVariables.zoomDefinedClientQuestionnaireName, "TGO", "zoom defined", globalVariables.KBClientQuestionnaireName, "Client");
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(globalVariables.KBClientQuestionnaireName, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(globalVariables.KBClientQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: 'Copy to' functionality for firm defined client Questionnaire")
	public void TC_3() throws Exception 
	{
		//Created by Nitisha Sinha on 14th September,2020
		
		globalVariables.testCaseDescription = "KB: 'Copy to' functionality for firm defined client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBClientQuestionnaireName = globalVariables.KBClientQuestionnaireName + strDate;
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseManagerQuestionnairePage.copyQuestionnaireToCountry(clientQuestionnaire, "TGO",  "firm defined", globalVariables.KBClientQuestionnaireName, "Client");
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(globalVariables.KBClientQuestionnaireName, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(globalVariables.KBClientQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Search Client Questionnaire")
	public void TC_53() throws Exception 
	{
		//Created by Nitisha Sinha on 17th September,2020
		
		globalVariables.testCaseDescription = "KB: Search Client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(clientQuestionnaire, true,  "firm defined");
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Search Corporation Questionnaire")
	public void TC_54() throws Exception 
	{
		//Created by Nitisha Sinha on 17th September,2020
		
		globalVariables.testCaseDescription = "KB: Search Corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(corporationQuestionnaire, true, "firm defined");
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: 'Copy to' functionality for zoom defined corporation Questionnaire")
	public void TC_8() throws Exception 
	{
		//Created by Nitisha Sinha on 21st September,2020
		
		globalVariables.testCaseDescription = "KB: 'Copy to' functionality for zoom defined corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();

			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBCorporationQuestionnaireName = globalVariables.KBCorporationQuestionnaireName + strDate;
			
			caseManagerQuestionnairePage.copyQuestionnaireToCountry(globalVariables.zoomDefinedCorporationQuestionnaireName, "TGO", "zoom defined", globalVariables.KBCorporationQuestionnaireName, "Corporation");
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(globalVariables.KBCorporationQuestionnaireName, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(globalVariables.KBCorporationQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: 'Copy to' functionality for firm defined corporation Questionnaire")
	public void TC_9() throws Exception 
	{
		//Created by Nitisha Sinha on 21st September,2020
		
		globalVariables.testCaseDescription = "KB: 'Copy to' functionality for firm defined corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();

			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.KBCorporationQuestionnaireName = globalVariables.KBCorporationQuestionnaireName + strDate;
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			caseManagerQuestionnairePage.copyQuestionnaireToCountry(corporationQuestionnaire, "TGO",  "firm defined", globalVariables.KBCorporationQuestionnaireName, "Corporation");
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(globalVariables.KBCorporationQuestionnaireName, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(globalVariables.KBCorporationQuestionnaireName);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Verify copy from country functionality for client Questionnaires")
	public void TC_55() throws Exception 
	{
		//Created by Nitisha Sinha on 23rd September,2020
		
		globalVariables.testCaseDescription = "KB: Verify copy from country functionality for client Questionnaires";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseManagerQuestionnairePage.copyQuestionnaireFromCountry("USA", "TGO", clientQuestionnaire);
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(clientQuestionnaire, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(clientQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Verify copy from country functionality for zoom defined corporation Questionnaires")
	public void TC_56() throws Exception 
	{
		//Created by Nitisha Sinha on 23rd September,2020
		
		globalVariables.testCaseDescription = "KB: Verify copy from country functionality for zoom defined corporation Questionnaires";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();
			
			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			caseManagerQuestionnairePage.copyQuestionnaireFromCountry("USA", "TGO", corporationQuestionnaire);
			
			caseManagerQuestionnairePage.verifyCopyQuestionnaireToCountry(corporationQuestionnaire, "TGO");
			
			caseManagerQuestionnairePage.deleteQuestionnaire(corporationQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire"}, description = "KB: Verify copy from  functionality for a firm defined client Questionnaire")
	public void TC_57() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "KB: Verify copy from  functionality for a firm defined client Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();

			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(clientQuestionnaire, true,  "firm defined");
			
			caseManagerQuestionnairePage.recreateQuestionnaire(clientQuestionnaire, "Client Intake Sheet Family");
			                                                                        
			caseManagerQuestionnairePage.verifyRecreateQuestionnaire(clientQuestionnaire, "Firm Custom Information");

			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Verify copy from  functionality for a firm defined corporation Questionnaire")
	public void TC_58() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "KB: Verify copy from  functionality for a firm defined corporation Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickQuestionnaireTemplateTab();

			caseManagerKnowledgeBasePage.clickCorporationQuestionnaireTemplateTab();
			
			CM_KBQuestionnairePage caseManagerQuestionnairePage = new CM_KBQuestionnairePage(driver);

			caseManagerQuestionnairePage.showQuestionnaireListForCountry("USA");
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			caseManagerQuestionnairePage.searchQuestionnaire(corporationQuestionnaire, true, "firm defined");
			
			caseManagerQuestionnairePage.recreateQuestionnaire(corporationQuestionnaire, "xyz");
			
			caseManagerQuestionnairePage.verifyRecreateQuestionnaire(corporationQuestionnaire, "copy this questionnaire section");

			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire", "to do"}, description = "CM_ToDo: Assign Questionnaire to client")
	public void TC_39() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "CM_ToDo: Attach Questionnaire to client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickClientTab(true);

            CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

            clientListPage.clickOnClientName(globalVariables.questionnaireClientFirstName, true);

            clientListPage.clickQuestionnairesTab();
            
            CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
            
            clientQuestionnairePage.chooseAddRemoveQuestionnaires();
            
            ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
            clientQuestionnairePage.removeQuestionnaire(clientQuestionnaire, globalVariables.questionnaireClientFullName);
			
			caseManagerHomePage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.clickNewToDoButton();
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.toDoTitleClient = globalVariables.toDoTitleClient + strDate;
				
			toDoPage.assignQuestionnaireToClient(globalVariables.questionnaireClientFirstName, clientQuestionnaire, globalVariables.toDoTitleClient, globalVariables.questionnaireClientFullName);
			
			toDoPage.backToClientQuestionnairePage(globalVariables.questionnaireClientFullName);
			
			caseManagerHomePage.clickLogout(true);
			
			login.fnplogin(data.B_userNameQuest,data.B_passwordQuest, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
            
            fnpHomePage.searchToDoAssignedForYou(globalVariables.toDoTitleClient);
           
            fnpHomePage.verifyAssignQuestionnaireToClient(globalVariables.questionnaireClientFullName, clientQuestionnaire);
			
            fnpHomePage.clickLogout(true);
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "to do"}, description = "CM_ToDo: Assign Questionnaire to corporation")
	public void TC_40() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "CM_ToDo: Attach Questionnaire to corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickCorporationTab(true);
			
         	CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
            
            corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
            
            corporationListPage.clickQuestionnairesTab();
            
            CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
            
            corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
            
            ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
            corporationQuestionnairePage.removeQuestionnaire(corporationQuestionnaire);
            
			caseManagerHomePage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.clickNewToDoButton();
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.toDoTitleCorp = globalVariables.toDoTitleCorp + strDate;
				
			toDoPage.assignQuestionnaireToCorporation(globalVariables.questionnaireCorporationName, corporationQuestionnaire, globalVariables.toDoTitleCorp, globalVariables.questionnaireCorporationSigningPersonName);
			
			toDoPage.backToCorporationQuestionnairePage(globalVariables.questionnaireClientFullName);
			
			caseManagerHomePage.clickLogout(true);
			
			HrpHomePage hrpHomePage = login.hrpLogin(data.P_userNameQuest, data.P_passwordQuest, true);
            
            hrpHomePage.clickAssignedToMe();
            
            hrpHomePage.searchToDoAssignedToMe(globalVariables.toDoTitleCorp);
           
            hrpHomePage.verifyAssignQuestionnaiareToCorp(corporationQuestionnaire, globalVariables.questionnaireCorporationName);
            
            hrpHomePage.clickLogout(true);
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire", "to do"}, description = "CM_ToDo: Assign Questionnaire to case")
	public void TC_41() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "CM_ToDo: Attach Questionnaire to case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickCaseTab(false);

            CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

            caseListPage.clickOnCaseId(data.questCaseId, false);

            caseListPage.clickQuestionnairesTab();
            
            CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
            
            caseQuestionnairePage.chooseAddRemoveQuestionnaires();
            
            ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
            caseQuestionnairePage.removeQuestionnaire(clientQuestionnaire);
            
			caseManagerHomePage.clickToDo();
			
			CM_ToDoPage toDoPage = new CM_ToDoPage(driver);
			
			toDoPage.clickNewToDoButton();
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			globalVariables.toDoTitleCase = globalVariables.toDoTitleCase + strDate;
				
			toDoPage.assignQuestionnaireToCase(globalVariables.questionnaireClientFirstName, clientQuestionnaire, globalVariables.toDoTitleCase, globalVariables.questCaseId, globalVariables.questionnaireClientFullName);
			
			toDoPage.backToCaseQuestionnairePage(globalVariables.questionnaireClientFullName);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.removeQuestionnaire(clientQuestionnaire);
            
			caseManagerHomePage.clickLogout(true);
			
			login.fnplogin(data.B_userNameQuest,data.B_passwordQuest, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
            
            fnpHomePage.searchToDoAssignedForYou(globalVariables.toDoTitleCase);
           
            fnpHomePage.verifyAssignCaseQuestionnaire(globalVariables.questionnaireClientFullName, clientQuestionnaire);
			
            fnpHomePage.clickLogout(true);
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Case Request: Verify whether the questionnaire is populated under case request in HRP")
	public void TC_47() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "Case Request: Verify whether the questionnaire is populated under case request in HRP";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickCorporationTab(true);

            CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
            
            corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
            
            corporationListPage.clickAdvancedCaseRequestTab();
            
            corporationListPage.getQuestionnaireForInitiorAndCreatorAttachedToCRTemplate(globalVariables.CRTemplate);
            
            caseManagerHomePage.clickLogout(true);    
            
            HrpHomePage hrpHomePage = login.hrpLogin(data.P_userNameQuest, data.P_passwordQuest, true);
            
            hrpHomePage.clickCaseRequestTab();
            
            hrpHomePage.verifyCRQuestionnaire("Questionnaire");
            
            hrpHomePage.clickLogout(true);
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Case Request: Verify whether the questionnaire is populated under case request in FNP")
	public void TC_48() throws Exception 
	{
		//Created by Nitisha Sinha on 24th September,2020
		
		globalVariables.testCaseDescription = "Case Request: Verify whether the questionnaire is populated under case request in FNP";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickCorporationTab(true);

            CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
            
            corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
            
            corporationListPage.clickAdvancedCaseRequestTab();
            
            corporationListPage.getQuestionnaireForInitiorAndCreatorAttachedToCRTemplate(globalVariables.CRTemplate);
            
            caseManagerHomePage.clickLogout(true);    
            
            login.fnplogin(data.B_userNameQuest,data.B_passwordQuest, true);

			FnpHomePage fnpHomePage = new FnpHomePage(driver);
			
            fnpHomePage.verifyCRQuestionnaire(data.caseRequestTemplateName,"Questionnaire");
            
            fnpHomePage.clickLogout(true);
            
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB: Attach the added client Questionnaire in TC_1 to the petition")
	public void TC_6() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB: Attach the added client Questionnaire in TC_1 to the petition";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.verifyPetitionTemplate(true);
			
			caseManagerKnowledgeBasePage.searchAndVerifyActiveFirmPetitionTemplate(globalVariables.questPetitionTemplate);
			
			caseManagerKnowledgeBasePage.clickPetitionTemplate(globalVariables.questPetitionTemplate);
			
			CM_PetitionDetailsPage petitionDetailsPage = new CM_PetitionDetailsPage(driver);
			
			petitionDetailsPage.clickQuestionnnaireTab();
			
			petitionDetailsPage.addAndVerifyQuestionnaire(globalVariables.questAttachQuestionnaireToPetition);
			
			petitionDetailsPage.removeAndVerifyQuestionnaire(globalVariables.questAttachQuestionnaireToPetition);
			
			caseManagerHomePage.clickLogout(true);	
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}	
	}
	
	@Test(groups = {"questionnaire"}, description = "Add Questionnaire to Corporation")
	public void TC_29() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Add Questionnaire to Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);
			
			CM_CorporationPage corporationPage = caseManagerHomePage.clickCorporationTab(true);

			corporationPage.clickAddCorporationButton(true);
            
			corporationPage.enterDataForCorporationCreation(workbookNameWrite, sheetName, corporationName, true);

			corporationPage.clickSaveCorporationButton(true);
			
			corporationPage.verifyIfCorporationCreated(workbookNameWrite, sheetName, true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
//			corporationPage.clickAddCorporationButton(true);
			
			newlyCreatedcorpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
			
//			corporationListPage.clickOnCorporationName(newlyCreatedcorpName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			/*corporationQuestionnairePage.removeQuestionnaire(corporationQuestionnaire);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			*/
			corporationQuestionnairePage.addQuestionnaire(corporationQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(corporationQuestionnaire, globalVariables.questionnaireCorporationName);
			
			/*corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.removeQuestionnaire(corporationQuestionnaire);*/
			
			caseManagerHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}	
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Remove Questionnaire from Corporation",dependsOnMethods="TC_29")
	public void TC_34() throws Exception
	{
		//Modified By Saurav Purohit
		//Modified Date : 24/06/2021
		
		globalVariables.testCaseDescription = "Remove Questionnaire from Corporation";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String corporationQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBCorporationQuestionnaireName");
			
			corporationListPage.clickOnCorporationName(newlyCreatedcorpName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.removeSelectedQuestionnaire(corporationQuestionnaire);
			
			
			
			/*corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.addQuestionnaire(corporationQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(corporationQuestionnaire, globalVariables.questionnaireCorporationName);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			corporationQuestionnairePage.removeQuestionnaire(corporationQuestionnaire);*/
			
			caseManagerHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}	
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Add Questionnaire to Client",dependsOnMethods="TC_29")
	public void TC_19() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		//Modified By Saurav  on 25/06/2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");

		Date date = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		String strDate = formatter.format(date);
		
		globalVariables.clientFirstName = globalVariables.clientFirstName + strDate;
		
		globalVariables.clientLastName = globalVariables.clientLastName + strDate;
		
		String[] name = {globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientFirstName + " " + globalVariables.clientLastName};
		
		String[] nameKeys = {"ALoginSavedFirstClientName", "ALoginSavedLastClientName", "ALoginSavedClientName"};;
		
		globalVariables.testCaseDescription = "Add Questionnaire to Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
			
			clientCreationPage.clickAddClientButton(true);

			clientCreationPage.enterDataToCreateClient(workbookNameWrite, sheetName, corpName, globalVariables.clientFirstName, globalVariables.clientLastName, globalVariables.clientEmailID);
			
			Utils.saveDataToExcel(workbookNameWrite, sheetName, name, nameKeys);

			clientCreationPage.clickSaveClientButton(true);


//			clientCreationPage.clickOnClientName(globalVariables.questionnaireClientFirstName, true);
			
//			clientCreationPage.clickOnClientName("Edit Questionnaire 1", true);
			
			clientCreationPage.clickQuestionnairesTab();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
//			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
//			clientQuestionnairePage.removeQuestionnaire("Sample Contract Questionnaire (Employee)", "Edit Questionnaire 1 2");
			
//			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			clientQuestionnairePage.addQuestionnaire(clientQuestionnaire);
			
//			clientQuestionnairePage.verifyIfQuestionnairesAdded("UK Buissness Visa Questionnaire", globalVariables.questionnaireClientFirstName);
			
			clientQuestionnairePage.verifyIfQuestionnairesAdded(clientQuestionnaire, globalVariables.clientFirstName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "Remove Questionnaire from Client",dependsOnMethods="TC_19")
	public void TC_24() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		//Modified By Sauravp on 28 June 2021
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String clientFirstName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");
		
		String clientFullName = readExcel.initTest(workbookName, sheetName, "ALoginSavedClientName");
		
		globalVariables.testCaseDescription = "Remove Questionnaire from Client";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickClientTab(true);

			CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);

//			clientCreationPage.clickOnClientName(globalVariables.questionnaireClientFirstName, true);
			
//			clientCreationPage.clickOnClientName("Edit Questionnaire 1", true);
			
			String clientQuestionnaireName = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			clientCreationPage.clickOnClientName(clientFirstName, true);

			clientCreationPage.clickQuestionnairesTab();
			
			CM_ClientQuestionnairePage clientQuestionnairePage = new CM_ClientQuestionnairePage(driver);
			
			
//			String questionnaireName = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
//			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
//			clientQuestionnairePage.addQuestionnaire(clientFirstName);
			
//			clientQuestionnairePage.verifyIfQuestionnairesAdded("UK Buissness Visa Questionnaire", globalVariables.questionnaireClientFirstName);
			
//			clientQuestionnairePage.verifyIfQuestionnairesAdded(data.clientQuestionnaireName, "Edit Questionnaire 1");
			
			clientQuestionnairePage.chooseAddRemoveQuestionnaires();
			
//			clientQuestionnairePage.removeQuestionnaire("Sample Contract Questionnaire (Employee)", "Edit Questionnaire 1 2");
			
			clientQuestionnairePage.removeQuestionnaire(clientQuestionnaireName, clientFullName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(groups = {"questionnaire"}, description = "Add Questionnaire to Case")
	public void TC_13() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Add Questionnaire to Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(globalVariables.questCaseId, false);

			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.removeQuestionnaire(clientQuestionnaire);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.addQuestionnaire(clientQuestionnaire);
			
			caseQuestionnairePage.verifyIfQuestionnairesAdded(clientQuestionnaire, globalVariables.questionnaireClientFirstName);			
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Remove Questionnaire from Case")
	public void TC_18() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Remove Questionnaire from Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(data.questCaseId, false);

			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			ReadWriteExcel readExcel = new ReadWriteExcel();
			String clientQuestionnaire = readExcel.initTest(workbookName, sheetName, "KBClientQuestionnaireName");
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.removeQuestionnaire(clientQuestionnaire);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.addQuestionnaire(clientQuestionnaire);
			
			caseQuestionnairePage.verifyIfQuestionnairesAdded(clientQuestionnaire, globalVariables.questionnaireClientFirstName);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.removeQuestionnaire(clientQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Grant Access for the Questionnaire")
	public void TC_35() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation : Grant Access for the Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
//			corporationQuestionnairePage.addQuestionnaire(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(globalVariables.questGrantAccessCorporationQuestionnaire, globalVariables.questionnaireCorporationName);
			
			corporationQuestionnairePage.grantAccessForQuestionnaire(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			login.hrpLogin(data.P_userNameQuest, data.P_passwordQuest, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.acceptUserInstructions(true);
			
			hrpHomePage.checkIfQuestionnaireAdded(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			hrpHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Revoke Access for the Questionnaire")
	public void TC_38() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation : Revoke Access for the Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.chooseAddRemoveQuestionnaires();
			
//			corporationQuestionnairePage.addQuestionnaire(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			corporationQuestionnairePage.verifyIfQuestionnairesAdded(globalVariables.questGrantAccessCorporationQuestionnaire, globalVariables.questionnaireCorporationName);
			
			corporationQuestionnairePage.revokeAccessForQuestionnaire(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			caseManagerHomePage.clickLogout(true);
			
			login.hrpLogin(data.P_userNameQuest, data.P_passwordQuest, true);
			
			HrpHomePage hrpHomePage = new HrpHomePage(driver);
			
			hrpHomePage.acceptUserInstructions(true);
			
			hrpHomePage.checkIfQuestionnaireNotPresent(globalVariables.questGrantAccessCorporationQuestionnaire);
			
			hrpHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "KB-Case request template : Attach Case Request Questionnaire")
	public void TC_42() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Add Questionnaire to Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplateTab();
			
			caseManagerKnowledgeBasePage.addNewCaseTemplate(globalVariables.questCaseRequestTemplatePetition, globalVariables.questCaseRequestTemplateName, globalVariables.questCaseRequestTemplateDescription);
			
			caseManagerKnowledgeBasePage.verifyCaseRequestTemplatePresent(globalVariables.questCaseRequestTemplateName);
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "KB-Case request template : Remove Case Request Questionnaire",dependsOnMethods = "TC_42")
	public void TC_43() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB-Case request template : Remove Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplateTab();
			
			caseManagerKnowledgeBasePage.verifyCaseRequestTemplatePresent(globalVariables.questCaseRequestTemplateName);
			
			caseManagerKnowledgeBasePage.deleteCaseRequestTemplate(globalVariables.questCaseRequestTemplateName);
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "Corporation-Case Request Template : Attach Case Request Questionnaire")
	public void TC_45() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation-Case Request Template : Attach Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickAdvancedCaseRequestTab();
			
			corporationListPage.addAdvancedCaseRequestTemplate(globalVariables.questCorporationCaseRequestTemplate);
			
			corporationListPage.verifyCaseRequestTemplatePresent(globalVariables.questCorporationCaseRequestTemplate);
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation-Case Request Template : Remove Case Request Questionnaire",dependsOnMethods = "TC_45")
	public void TC_46() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation-Case Request Template : Remove Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickAdvancedCaseRequestTab();
			
			corporationListPage.removeAdvancedCaseRequestTemplate(globalVariables.questCorporationCaseRequestTemplate);
			
			corporationListPage.verifyCaseReuestTemplateNotPresent(globalVariables.questCorporationCaseRequestTemplate);
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB - Petitions Events : Events on Case Status changes to Open")
	public void TC_49() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB - Petitions Events : Events on Case Status changes to Open";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(false);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(globalVariables.questCaseForKBEvent, false);
			
			caseListPage.changeCaseStatusToApproved(globalVariables.questCaseForKBEvent, "Approved", "10/6/2020", "10/6/2028");
			
			caseListPage.changeCaseStatusToClosed(globalVariables.questCaseForKBEvent, "Open");
			
            Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			String emailSubject = globalVariables.eventEmailSubject + strDate;
			
			caseListPage.sendEventEmail(emailSubject, globalVariables.questKBEventQuestionnaire);
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseListPage.clickEmailsTab();
			
			caseEmailsPage.verifyIfEmailSent(emailSubject);
			
			caseManagerHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Case  : Events on Case")
	public void TC_51() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Case  : Events on Case";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(false);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(globalVariables.questCaseId, false);
			
			caseListPage.changeCaseStatusToApproved(globalVariables.questCaseId, "Approved", "10/6/2020", "10/6/2028");
			
			caseListPage.changeCaseStatusToClosed(globalVariables.questCaseId, "Open");
			
            Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
			
			String emailSubject = globalVariables.eventEmailSubject + strDate;
			
			caseListPage.sendEventEmail(emailSubject, globalVariables.questCaseEventsQuestionnaire);
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseListPage.clickEmailsTab();
			
			caseEmailsPage.verifyIfEmailSent(emailSubject);
			
			caseManagerHomePage.clickLogout(true);
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Case : Create a case with firm defined petiton that has a client questionaire attached to it.(Attached in TC_6)")
	public void TC_12() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB - Petitions Events : Events on Case Status changes to Open";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
			login.clickAgreeButton(false);

			CM_CaseListPage caseListPage = caseManagerHomePage.clickCaseTab(true);

            caseListPage.clickAndAddCase(workbookNameWrite, sheetName, globalVariables.questionnaireClientFullName, globalVariables.questionnaireCorporationName, data.questCaseManagerToWork, data.AddCase_CountryName, data.questPetitionType_AddCase, true);		   

			caseListPage.verifyIfCaseCreated("QA_ALoginCaseCreatedForUSA", data.AddCase_CountryName, globalVariables.questionnaireClientFullName, globalVariables.questionnaireCorporationName, workbookNameWrite, sheetName, true);
			
			caseListPage.clickQuestionnairesTab();
			
			CM_CaseQuestionnairePage caseQuestionnairePage = new CM_CaseQuestionnairePage(driver);
			
			caseQuestionnairePage.chooseAddRemoveQuestionnaires();
			
			caseQuestionnairePage.verifyIfQuestionnairesAdded(globalVariables.questPetitionAttachedQuestionnaire, globalVariables.questionnaireClientFirstName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
			
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "KB-Case request template : Attach Case Request Questionnaire")
	public void TC_59() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB-Case request template : Attach Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplateTab();
			
			caseManagerKnowledgeBasePage.verifyCaseRequestTemplatePresent("Questionnaire CR template");
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplate("Questionnaire CR template");
			
			CM_KBCaseRequestTemplatePage cmKBCaseRequestTemplatePage = new CM_KBCaseRequestTemplatePage(driver);
			
			cmKBCaseRequestTemplatePage.clickQuestionnnaireTab();
			
			cmKBCaseRequestTemplatePage.addAndVerifyQuestionnaire("Adjustment of Status Checklist");
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "KB-Case request template : Remove Case Request Questionnaire",dependsOnMethods = "TC_59")
	public void TC_60() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "KB-Case request template : Remove Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KnowledgeBasePage caseManagerKnowledgeBasePage = new CM_KnowledgeBasePage(driver);
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplateTab();
			
			caseManagerKnowledgeBasePage.verifyCaseRequestTemplatePresent("Questionnaire CR template");
			
			caseManagerKnowledgeBasePage.clickCaseRequestTemplate("Questionnaire CR template");
			
			CM_KBCaseRequestTemplatePage cmKBCaseRequestTemplatePage = new CM_KBCaseRequestTemplatePage(driver);
			
			cmKBCaseRequestTemplatePage.clickQuestionnnaireTab();
			
			cmKBCaseRequestTemplatePage.removeAndVerifyQuestionnaire("Adjustment of Status Checklist");
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation-Case Request Template : Attach Case Request Questionnaire")
	public void TC_61() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation-Case Request Template : Attach Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickAdvancedCaseRequestTab();
			
			corporationListPage.verifyCaseRequestTemplatePresent("India Template");
			
			corporationListPage.clickAdvancedCaseRequestTemplate("India Template");
			
			CM_CorporationAdvancedCaseRequestTemplatePage advancedCRTemplatePage = new CM_CorporationAdvancedCaseRequestTemplatePage(driver);
			
			advancedCRTemplatePage.clickQuestionnnaireTab();
			
			advancedCRTemplatePage.addAndVerifyQuestionnaire("Adjustment of Status Checklist");
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation-Case Request Template : Remove Case Request Questionnaire",dependsOnMethods = "TC_61")
	public void TC_62() throws Exception
	{
		//Created by Yatharth Pandya in Sept,2020
		
		globalVariables.testCaseDescription = "Corporation-Case Request Template : Remove Case Request Questionnaire";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName(globalVariables.questionnaireCorporationName);
			
			corporationListPage.clickAdvancedCaseRequestTab();
			
			corporationListPage.verifyCaseRequestTemplatePresent("India Template");
			
			corporationListPage.clickAdvancedCaseRequestTemplate("India Template");
			
			CM_CorporationAdvancedCaseRequestTemplatePage advancedCRTemplatePage = new CM_CorporationAdvancedCaseRequestTemplatePage(driver);
			
			advancedCRTemplatePage.clickQuestionnnaireTab();
			
			advancedCRTemplatePage.removeAndVerifyQuestionnaire("Adjustment of Status Checklist");
			
			caseManagerHomePage.clickLogout(true);	
	
		}
		catch(Exception e)
		{
			Log.exception(e, driver);
		}
		finally
		{
			Log.endTestCase();
			driver.quit();
		}
	}
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Send Email to get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_70_0(String browser) throws Exception
	{
		//Created by Yatharth Pandya
		//Created on 15th oct,2020
		
		globalVariables.testCaseDescription = "Corporation : Send Email to get URL and Access Code";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.hideFirmBanner();

			caseManagerHomePage.clickCorporationTab(true);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName("Edit Quest Corporation");
			
			corporationListPage.clickQuestionnairesTab();
			
			CM_CorporationQuestionnairePage corporationQuestionnairePage = new CM_CorporationQuestionnairePage(driver);
			
			corporationQuestionnairePage.verifyIfQuestionnairePresent(data.questionnaire, "Edit Quest Corporation");
			
			corporationQuestionnairePage.chooseEmailQuestionnaire();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);			
			
			corporationEmailPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			corporationEmailPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			Log.testCaseResult();	
			
		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}	
		
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation: Get URL and Access Code",dependsOnMethods="TC_70_0", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_70_1(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Corporation: Get URL and Access Code";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCorporationTab(false);

			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			
			corporationListPage.clickOnCorporationName("Edit Quest Corporation");
			
			corporationListPage.clickEmailsTab();
			
			CM_CorporationEmailPage corporationEmailPage = new CM_CorporationEmailPage(driver);
			
			corporationEmailPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
			Log.testCaseResult();

		}
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Login using Access Code",dependsOnMethods="TC_70_1", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_70_2(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");

		globalVariables.testCaseDescription = "Client : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Edit Questionnaire using Access Code",dependsOnMethods="TC_70_2", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_70_3(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");

		globalVariables.testCaseDescription = "Corporation : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editCorporationQuestionnaire(data.questionnaire,"Edit Quest Corporation");

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Corporation : Send Confirmation and Verify",dependsOnMethods="TC_70_3", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_70_4(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_URL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ACorporation_Access_Code");

		globalVariables.testCaseDescription = "Corporation : Send Confirmation and Verify";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);
			
			accessCodePage.sendConfirmation();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Client : Send Email to get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider") //, dependsOnMethods="CM_TC_20_1")
	public void TC_71_1(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "Client : Send Email to get URL and Access Code";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.hideFirmBanner();
			
			caseManagerHomePage.clickClientTab(false);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName("Edit Questionnaire 1", false);
				
			clientListPage.clickEmailsTab();
			
			CM_ClientEmailsPage clientEmailsPage = new CM_ClientEmailsPage(driver);
			
			clientEmailsPage.clickComposeEmailButton();
			
			clientEmailsPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			clientEmailsPage.clickIncludeQuestionnaires();
			
			clientEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			clientEmailsPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
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
	
	
	@Test(groups = {"questionnaire"}, description = "Client : Login using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="TC_71_1")
	public void TC_71_2(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL_Client");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode_Client");

		globalVariables.testCaseDescription = "Client : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "Client : Edit Questionnaire using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="TC_71_2")
	public void TC_71_3(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL_Client");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode_Client");

		globalVariables.testCaseDescription = "Client : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editQuestionnaire(data.questionnaire);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "CASE : Send Email to get URL and Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider") //, dependsOnMethods="CM_TC_45_1")
	public void TC_72_1(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();

		globalVariables.testCaseDescription = "CASE : Send Email to get URL and Access Code";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.hideFirmBanner();
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId("KBGRIT03385-1", false);

			caseListPage.clickEmailsTab();
			
			CM_CaseEmailsPage caseEmailsPage = new CM_CaseEmailsPage(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			caseEmailsPage.changeEmailSubject(globalVariables.accessCodeEmailSubject);
			
			caseEmailsPage.clickIncludeQuestionnaires();
			
			caseEmailsPage.enterMessageAndSendEmail(globalVariables.emailMessage);
			
			caseEmailsPage.getAccessCodeAndURL(globalVariables.accessCodeEmailSubject, workbookNameWrite, sheetName);
			
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
	
	
	@Test(groups = {"questionnaire"}, description = "CASE : Login using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="TC_72_1")
	public void TC_72_2(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode");

		globalVariables.testCaseDescription = "CASE : Login using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.verifyAccessCodePage();

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"questionnaire"}, description = "CASE : Edit Questionnaire using Access Code", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider", dependsOnMethods="TC_72_2")
	public void TC_72_3(String browser) throws Exception 
	{
		//Created by Yatharth Pandya on 17th Oct,2020
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String accessCodeURL = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCodeURL");
		String accessCode = readExcel.initTest(workbookName, sheetName, "QA_ALoginAccessCode");

		globalVariables.testCaseDescription = "CASE : Edit Questionnaire using Access Code";
		WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			AccessCodePage accessCodePage = new AccessCodePage(driver, accessCodeURL);

			accessCodePage.enterAccessCodeAndSubmit(accessCode);

			login.clickAgreeButton(false);

			accessCodePage.editQuestionnaire(data.questionnaire);

			Log.testCaseResult();
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


