package com.inszoomapp.testscripts;

import java.util.ArrayList;
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
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HRPNewsPage;
import com.inszoomapp.pages.HRP_ClientPage;
import com.inszoomapp.pages.HRP_CorporationPage;
import com.inszoomapp.pages.HRP_HeadQuarterPage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.WebDriverFactory;


@Listeners(EmailReport.class)
public class PortalSetupTestScript3_2 {

	String webSite = null;
	String env = null;
	String browserName = null;
	
	String userName = null;
	String password = null;
	
	String CEOUsername = null;
	String CEOPassword = null;
	String managerUsername = null;
	String managerPassword = null;
	String leadUsername = null;
	String leadPassword = null;
	String captainUsername = null;
	String captainPassword = null;
	String employeeUsername = null;
	String employeePassword = null;
	String internUsername = null;
	String internPassword = null;
	
	private String workbookName = "";
	private String workbookNameWrite = "";
	private String sheetName = "";
	
	public static List<String> corporationsUnderHQ = new ArrayList<String>();
	
	String hqName = "SakshamHQ";
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
					userName = data.CM_userNamePortal3_2;
					password = data.CM_passwordPortal3_2;
					CEOUsername = data.CEO_userName;
					CEOPassword = data.CEO_password;
					managerUsername = data.Manager_userName;
					managerPassword = data.Manager_password;
					leadUsername = data.Lead_userName;
					leadPassword = data.Lead_password;
					captainUsername = data.Captain_userName;
					captainPassword = data.Captain_password;
					employeeUsername = data.Employee_userName;
					employeePassword = data.Employee_password;
					internUsername = data.Intern_userName;
					internPassword = data.Intern_password;
					workbookName = "testdata\\data\\Regression_Stg.xls";
					workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_Stg.xls";
					sheetName = "Inszoom";
					globalVariables.environmentName = env;
					
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userNamePortal;
					password = data.CM_passwordPortal;
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
	
	
	@Test(groups = {"portalSetup"}, description = "Prerequisite test for data extraction of portal setup 3.2")
	public void PreRequisite() throws Exception 
	{
		globalVariables.testCaseDescription = "Prerequisite test for data extraction of portal setup 3.2 ";
		
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			CM_CorporationPage corporationPage = caseManagerHomePage.clickCorporationTab(true);
			
			corporationPage.clickHeadQuarterlink();
			
			corporationPage.searchHeadQuarterByName();
			
			corporationPage.clickHeadQuarterBranches();
						
			corporationPage.getListOfCorporationsUnderHeadquarter(true);
			
			corporationPage.getClientListForEachCorporation(true);
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Prerequisite test for data extraction of portal setup 3.2")
    public void PreRequisiteData() throws Exception 
    {
          
      globalVariables.testCaseDescription = "Prerequisite test for data extraction of portal setup 3.2 ";

          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

          try {

                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

                CM_CorporationPage corporationPage = caseManagerHomePage.clickCorporationTab(true);
                
                corporationPage.clickOnCorporationName(globalVariables.corporationNameWithHQ);

                corporationPage.getClientListForEachCorpUsers(globalVariables.corporationNameWithHQ);                        

                caseManagerHomePage.clickPortalSetup(true);
                
                PortalSetup portalSetup = new PortalSetup(driver);
                
                portalSetup.clickCustomizedPortalAccess();
                
                portalSetup.searchCorporationByName(globalVariables.corporationNameWithHQ, true);
                
                portalSetup.clickUserManagementTab(true);
                
                portalSetup.getListOfCorporationUsersUnderCorporation(globalVariables.corporationNameWithHQ);
                
                portalSetup.backToClientListPage();
                
                caseManagerHomePage.clickLogout(true);

                Log.testCaseResult();

          } catch (Exception e) {

                Log.exception(e, driver);

          } finally 
          {
                Log.endTestCase();
                driver.quit();
          }

    }

	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(All Corp-All CLient) logged in, he/she will be able to view all Corporation of HeadQuater")
	public void PS3_2_TC_1() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp user logged in, he/she will be able to view all Corporation of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(CEOUsername, CEOPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickHeadquarterTab(true);
			
			HRP_HeadQuarterPage hqPage = new HRP_HeadQuarterPage(driver);
			
			hqPage.verifyViewAllBranches();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user logged in, he/she will be able to view all Corporation of HeadQuater")
	public void PS3_2_TC_10() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp user(All Corp-Specific CLient) logged in, he/she will be able to view all Corporation of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(managerUsername, managerPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickHeadquarterTab(true);
			
			HRP_HeadQuarterPage hqPage = new HRP_HeadQuarterPage(driver);
			
			hqPage.verifyViewAllBranches();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(All Corp-All CLient) logged in, he/she will be able to view all Clients of HeadQuater")
	public void PS3_2_TC_2() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(All Corp-All CLient) user logged in, he/she will be able to view all Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(CEOUsername, CEOPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickHeadquarterTab(false);
			
			HRP_HeadQuarterPage headQuarterPage = new HRP_HeadQuarterPage(driver);
			
			headQuarterPage.getHeadQuarterBranchesName();
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.verifyAllClientWithHQAccess();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(All Corp-All CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_3() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(All Corp-All CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(CEOUsername, CEOPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickClientTab();
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			clientpage.clickMyClientTab();
			
			clientpage.verifyMyClient();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(Spec Corp-All CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_19() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(Spec Corp-All CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(leadUsername, leadPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickHeadquarterTab(false);
			
			HRP_HeadQuarterPage headQuarterPage = new HRP_HeadQuarterPage(driver);
			
			headQuarterPage.getHeadQuarterBranchesName();
			
			hrphomepage.clickClientTab();
			
			clientpage.verifyAllClientWithHQAccess();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(spec Corp-All CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_20() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(spec Corp-Spec CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(leadUsername, leadPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.clickMyClientTab();
			
			clientpage.verifyMyClient();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(spec Corp-Spec CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_28() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(spec Corp-Spec CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(captainUsername, captainPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.verifyMyClient();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(All Corp-Spec CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_37() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(All Corp-Spec CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(employeeUsername, employeePassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.verifyAllClientWithNoHQAccess();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(No HQ + All CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_38() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(All Corp-Spec CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(employeeUsername, employeePassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.clickMyClientTab();
			
			clientpage.verifyMyClient();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user(No HQ + Spec CLient) logged in, he/she will be able to view My Clients of HeadQuater")
	public void PS3_2_TC_45() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp(All Corp-Spec CLient) user logged in, he/she will be able to view My Clients of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(internUsername, internPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			HRP_ClientPage clientpage = new HRP_ClientPage(driver);
			
			hrphomepage.clickClientTab();
			
			clientpage.verifyMyClient();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user logged in, he/she will be able to view all Corporation of HeadQuater")
	public void PS3_2_TC_18() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp user(All Corp-Specific CLient) logged in, he/she will be able to view all Corporation of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(leadUsername, leadPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickHeadquarterTab(true);
			
			HRP_HeadQuarterPage hqPage = new HRP_HeadQuarterPage(driver);

			hqPage.verifyViewSpecificBranches();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify that with corp user logged in, he/she will be able to view all Corporation of HeadQuater")
	public void PS3_2_TC_27() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify that with corp user(All Corp-Specific CLient) logged in, he/she will be able to view all Corporation of HeadQuater";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			HrpHomePage hrphomepage = login.hrpLogin(captainUsername, captainPassword, true);
			
//			hrphomepage.acceptUserInstructions(true);
			
			hrphomepage.clickHeadquarterTab(true);
			
			HRP_HeadQuarterPage hqPage = new HRP_HeadQuarterPage(driver);
			
			hqPage.verifyViewSpecificBranches();
			
			hrphomepage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify Functionality of Select Coporations under Portal Setup can be linked with corp User (Spec Corp+ ALL Client)")
	public void PS3_2_TC_35() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify Functionality of Select Coporations under Portal Setup can be linked with corp User (Spec Corp+ ALL Client)";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(globalVariables.corporationNameWithHQ, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.clickLeadEditUser();
			
			portalSetupPage.verifySelectCorporationUnderHQ();
			
			portalSetupPage.backToZoomboard();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Verify Functionality of Select Coporations under Portal Setup can be linked with corp User (Spec Corp+ spec Client)")
	public void PS3_2_TC_35_1() throws Exception 
	{
		globalVariables.testCaseDescription = "Verify Functionality of Select Coporations under Portal Setup can be linked with corp User (Spec Corp+ spec Client)";
		final WebDriver driver = WebDriverFactory.get(browserName);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		
		try {
			
			LoginPageTest login = new LoginPageTest(driver, webSite);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			caseManagerHomePage.clickPortalSetup(true);
			
			PortalSetup portalSetupPage = new PortalSetup(driver);
			
			portalSetupPage.clickCustomizedPortalAccess();
			
			portalSetupPage.searchCorporationByName(globalVariables.corporationNameWithHQ, true);
			
			portalSetupPage.clickUserManagementTab(true);
			
			portalSetupPage.clickCaptainEditUser();
			
			portalSetupPage.verifySelectCorporationUnderHQ();
			
			portalSetupPage.backToZoomboard();
			
			caseManagerHomePage.clickLogout(true);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with All Corps and All Clients Access")
  	public void PS3_2_TC_4() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with All Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(CEOUsername, CEOPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of Client Search under Corp User Login with All Corps and All Clients Access")
  	public void PS3_2_TC_8() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with All Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(CEOUsername, CEOPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0), true);
           
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0));
            
            driver.navigate().back();
            
            hrpClient.clickMyClientTab();
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with All Corps and Specific Clients Access")
  	public void PS3_2_TC_12() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with Specific Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access")
  	public void PS3_2_TC_16() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Manager Corp1"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Manager Corp1"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check the negative Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access")
  	public void PS3_2_TC_16_1() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check negative Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0), true);
           
            hrpClient.verifyNegativeClientSearch();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
     
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with Specific Corps and All Clients Access")
  	public void PS3_2_TC_21() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with Specific Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of Client Search under Corp User Login with Specific Corps and All Clients Access")
  	public void PS3_2_TC_25() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with Specific Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Lead Corp1"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Lead Corp1"), 0));
            
            driver.navigate().back();
            
            hrpClient.clickMyClientTab();
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Lead Corp1"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Lead Corp1"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check the negative Functionality of Client Search under Corp User Login with Specific Corps and All Clients Access")
  	public void PS3_2_TC_25_1() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check negative Functionality of Client Search under Corp User Login with Specific Corps and All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpToClientMap.get("Corp3WithHQ"), 0), true);
            
            hrpClient.verifyNegativeClientSearch();
          
            hrpClient.clickMyClientTab();
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Captain Corp1"), 0), true);
            
            hrpClient.verifyNegativeClientSearch();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
      
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with Specific Corps and Specific Clients Access")
  	public void PS3_2_TC_29() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with Specific Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of Client Search under Corp User Login with Specific Corps and Specific Clients Access")
  	public void PS3_2_TC_33() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with Specific Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Captain Corp1"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Captain Corp1"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check the negative Functionality of Client Search under Corp User Login with Specific Corps and Specific Clients Access")
  	public void PS3_2_TC_33_1() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check negative Functionality of Client Search under Corp User Login with Specific Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("CEO Corp1"), 0), true);
           
            hrpClient.verifyNegativeClientSearch();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with All Clients Access")
  	public void PS3_2_TC_39() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(employeeUsername, employeePassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of Client Search under Corp User Login with All Clients Access")
  	public void PS3_2_TC_43() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(employeeUsername, employeePassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
           
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Senior Employee"), 0), true);
           
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Senior Employee"), 0));
            
            driver.navigate().back();
            
            hrpClient.clickMyClientTab();
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Senior Employee"), 0), true);
            
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Senior Employee"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check the negative Functionality of Client Search under Corp User Login with All Clients Access")
  	public void PS3_2_TC_43_1() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check negative Functionality of Client Search under Corp User Login with All Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(employeeUsername, employeePassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);

        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpToClientMap.get("Corp3WithHQ"), 0), true);
            
            hrpClient.verifyNegativeClientSearch();
          
            hrpClient.clickMyClientTab();
            
            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Manager Corp1"), 0), true);
            
            hrpClient.verifyNegativeClientSearch();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Corp Users under Corp User Login with Specific Clients Access")
  	public void PS3_2_TC_46() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of All Corp Users under Corp User Login with Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(internUsername, internPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickMyCorporationTab();
            
            HRP_CorporationPage hrpCorporation = new HRP_CorporationPage(driver);
            
            hrpCorporation.clickCorpUsersTab(true);
            
            hrpCorporation.verifyAllCorpUsers();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    @Test(groups = {"portalSetup"}, description = "Check the Functionality of Client Search under Corp User Login with Specific Clients Access")
    public void PS3_2_TC_50() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check Functionality of Client Search under Corp User Login with and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(internUsername, internPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);

            hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Intern Gareeb"), 0), true);
           
            hrpClient.clickOnClientName((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Intern Gareeb"), 0));
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check the negative Functionality of Client Search under Corp User Login with Specific Clients Access")
  	public void PS3_2_TC_50_1() throws Exception
  	{
  		globalVariables.testCaseDescription = "Check negative Functionality of Client Search under Corp User Login with Specific Corps and Specific Clients Access";
  		
  		final WebDriver driver = WebDriverFactory.get(browserName);
  		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
  		
  		try {

  			LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(internUsername, internPassword, true);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickClientTab();
            
            HRP_ClientPage hrpClient = new HRP_ClientPage(driver);
            
        	hrpClient.searchforClient((String) CollectionUtils.get(globalVariables.corpUserToClientMap.get("Lead Corp1"), 0), true);
            
            hrpClient.verifyNegativeClientSearch();
            
            hrpHomePage.clickLogout(true);
            
            Log.testCaseResult();
  	
  		} catch (Exception e) {
  			Log.exception(e, driver);
  		} finally {
  			Log.endTestCase();	
  			driver.quit();
  		}
  	}
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All User Instructions under Corp User Login with All Corps and All Clients Access")
    public void PS3_2_TC_7() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All User Instructions under Corp User Login with All Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
              HrpHomePage hrpHomePage = login.hrpLogin(CEOUsername, CEOPassword, true);
                
//              hrpHomePage.acceptUserInstructions(true);
                
              hrpHomePage.clickUserInstructionIconAndAccept();
              
              hrpHomePage.clickLogout(true);
                
              Log.testCaseResult();     
          }catch(Exception e)
          {
                Log.exception(e, driver);
                
          }finally
          {
                Log.endTestCase();
              driver.quit();
          }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All User Instructions under Corp User Login with All Corps and Specific Clients Access")
    public void PS3_2_TC_15() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All User Instructions under Corp User Login with All Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
              HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
                
//              hrpHomePage.acceptUserInstructions(true);
                
              hrpHomePage.clickUserInstructionIconAndAccept();
              
              hrpHomePage.clickLogout(true);
                
              Log.testCaseResult(); 
              
          }catch(Exception e)
          {
                Log.exception(e, driver);
                
          }finally
          {
                Log.endTestCase();
              driver.quit();
          }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All User Instructions under Corp User Login with Specific Corps and All Clients Access")
    public void PS3_2_TC_24() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All User Instructions under Corp User Login with Specific Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
              HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
                
//              hrpHomePage.acceptUserInstructions(true);
                
              hrpHomePage.clickUserInstructionIconAndAccept();
              
              hrpHomePage.clickLogout(true);
                
              Log.testCaseResult(); 
              
          }catch(Exception e)
          {
                Log.exception(e, driver);
                
          }finally
          {
                Log.endTestCase();
              driver.quit();
          }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All User Instructions under Corp User Login with Specific Corps and Specific Clients Access")
    public void PS3_2_TC_32() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All User Instructions under Corp User Login with Specific Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try
          {
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
              HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
                
//              hrpHomePage.acceptUserInstructions(true);
                
              hrpHomePage.clickUserInstructionIconAndAccept();
              
              hrpHomePage.clickLogout(true);
                
              Log.testCaseResult();
              
          }catch(Exception e)
          {
                Log.exception(e, driver);
                
          }finally
          {
                Log.endTestCase();
              driver.quit();
          }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with All Corps and All Clients Access")
    public void PS3_2_TC_5() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with All Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(CEOUsername, CEOPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.verifyAllNewsAndGuidelines();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with All Corps and All Clients Access")
    public void PS3_2_TC_6() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with All Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(CEOUsername, CEOPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.clickPolicyGuidelinesTab();
                
                hrpNewsPage.verifyAllPolicy();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with All Corps and Specific Clients Access")
    public void PS3_2_TC_13() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with All Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.verifyAllNewsAndGuidelines();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with All Corps and Specific Clients Access")
    public void PS3_2_TC_14() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with All Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
         
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(managerUsername, managerPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.clickPolicyGuidelinesTab();
                
                hrpNewsPage.verifyAllPolicy();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }           
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with Specific Corps and All Clients Access")
    public void PS3_2_TC_22() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with Specific Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                  LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.verifySpecificNewsAndGuidelines();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with Specific Corps and All Clients Access")
    public void PS3_2_TC_23() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with Specific Corps and All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
         
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(leadUsername, leadPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.clickPolicyGuidelinesTab();
                
                hrpNewsPage.verifySpecficPolicy();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with Specific Corps and Specific Clients Access")
    public void PS3_2_TC_30() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with Specific Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                  LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.verifySpecificNewsAndGuidelines();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with Specific Corps and Specific Clients Access")
    public void PS3_2_TC_31() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with Specific Corps and Specific Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(captainUsername, captainPassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.clickPolicyGuidelinesTab();
                
                hrpNewsPage.verifySpecficPolicy();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with All Clients Access")
    public void PS3_2_TC_40() throws Exception 
    {
          globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with All Clients Access";
          final WebDriver driver = WebDriverFactory.get(browserName);
          driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
          
          try {
                
                  LoginPageTest login = new LoginPageTest(driver, webSite);
                
                HrpHomePage hrpHomePage = login.hrpLogin(employeeUsername, employeePassword, true);
                
                HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
                
//                hrpHomePage.acceptUserInstructions(true);
                
                hrpHomePage.clickNewsGuidelinesTab();
                
                hrpNewsPage.verifySpecificNewsAndGuidelines();
                
                hrpHomePage.clickLogout(true);
                
                }catch(Exception e)
                {
                      Log.exception(e, driver);
                      
                }finally
                {
                      Log.endTestCase();
                    driver.quit();
                }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with All Clients Access")
    public void PS3_2_TC_41() throws Exception 
    {
    	
	      globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with All Clients Access";
	      final WebDriver driver = WebDriverFactory.get(browserName);
	      driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	      
	      try {
	            
	            LoginPageTest login = new LoginPageTest(driver, webSite);
	            
	            HrpHomePage hrpHomePage = login.hrpLogin(employeeUsername, employeePassword, true);
	            
	            HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
	            
//	            hrpHomePage.acceptUserInstructions(true);
	            
	            hrpHomePage.clickNewsGuidelinesTab();
	            
	            hrpNewsPage.clickPolicyGuidelinesTab();
	            
	            hrpNewsPage.verifySpecficPolicy();
	            
	            hrpHomePage.clickLogout(true);
	            
	            }catch(Exception e)
	            {
	                  Log.exception(e, driver);
	                  
	            }finally
	            {
	                  Log.endTestCase();
	                driver.quit();
	            }
	}
	
	
	@Test(groups = {"portalSetup"}, description = "Check Functionality of All News under Corp User Login with Specfic Clients Access")
	public void PS3_2_TC_47() throws Exception 
	{
	  globalVariables.testCaseDescription = "Check Functionality of All News under Corp User Login with Specific Clients Access";
	  final WebDriver driver = WebDriverFactory.get(browserName);
	  driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	  
	  try {
	        
	         LoginPageTest login = new LoginPageTest(driver, webSite);
	        
	        HrpHomePage hrpHomePage = login.hrpLogin(internUsername, internPassword, true);
	        
	        HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
	        
//	        hrpHomePage.acceptUserInstructions(true);
	        
	        hrpHomePage.clickNewsGuidelinesTab();
	        
	        hrpNewsPage.verifySpecificNewsAndGuidelines();
	        
	        hrpHomePage.clickLogout(true);
	        
	        }catch(Exception e)
	        {
	              Log.exception(e, driver);
	              
	        }finally
	        {
                  Log.endTestCase();
                driver.quit();
            }
    }
    
    
    @Test(groups = {"portalSetup"}, description = "Check Functionality of All Policy under Corp User Login with Specific Clients Access")
    public void PS3_2_TC_48() throws Exception 
    {
	  globalVariables.testCaseDescription = "Check Functionality of All Policy under Corp User Login with Specific All Clients Access";
	  final WebDriver driver = WebDriverFactory.get(browserName);
	  driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
      
      try {
            
            LoginPageTest login = new LoginPageTest(driver, webSite);
            
            HrpHomePage hrpHomePage = login.hrpLogin(internUsername, internPassword, true);
            
            HRPNewsPage hrpNewsPage = new HRPNewsPage(driver);
            
//            hrpHomePage.acceptUserInstructions(true);
            
            hrpHomePage.clickNewsGuidelinesTab();
            
            hrpNewsPage.clickPolicyGuidelinesTab();
            
            hrpNewsPage.verifySpecficPolicy();
            
            hrpHomePage.clickLogout(true);
            
            }catch(Exception e)
            {
                  Log.exception(e, driver);
                  
            }finally
            {
                  Log.endTestCase();
                driver.quit();
            }

    }

}

