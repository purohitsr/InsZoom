package securityXSS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class SecurityXSSTestscript extends BaseTest
{
	String userName = null;
	String password = null;
	private String workbookName = "";
	private String workbookNameXSS = "testdata\\data\\Input_XSS.xls";
	private String sheetName = "";
	AppDataBase data ;
	
	@BeforeTest(alwaysRun = true)
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
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Stg.xls";
					sheetName = "Inszoom";
					
					break;
				}
				case "DEV": {
					data = new AppDataDev();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_Dev.xls";
					sheetName = "Inszoom";
					
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					workbookName = "testdata\\data\\Regression_QA.xls";
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
					workbookName = "testdata\\data\\Regression_Prod.xls";
					sheetName = "Inszoom";

					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(description = "Check for textarea under Case Phone Log", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void XSS_TC_1(String browser) throws Exception
	{
		int failure = 0;
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");
		
		globalVariables.testCaseDescription = "Check for textarea under Case Phone Log";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try 
		{
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);

			caseManagerHomePage.clickCaseTab(true);
			
			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId(caseId, true);
			
			caseListPage.clickPhoneLogTab();
			
			CasePhoneLog casePhoneLog = new CasePhoneLog(driver);
			
			int i = 1;			
			do
			{
				String case_PhoneLogMessage = readExcel.initTest(workbookNameXSS, sheetName, "malicious_input_" + i);
				casePhoneLog.addPhoneLogForCase(case_PhoneLogMessage);
				
				if(!UtilsXSS.checkIfRedirectedToErrorPage(driver))
				{
					Log.message("", driver, true);
					Log.failsoft("Did not redirect to error page");
					failure += 1;
					
					List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					
					for(int j = tabs.size() - 1; j > 1;)
					{
						driver.switchTo().window(tabs.get(j));
						driver.close();
						j = driver.getWindowHandles().size() - 1;
					}
					
					driver.switchTo().window(tabs.get(0));
					driver.get(webSite);
					
					login.caseManagerlogin(userName, password, true);

					login.clickAgreeButton(false);

					caseManagerHomePage.clickCaseTab(true);
					
					caseListPage.clickOnCaseId(caseId, true);
					
					caseListPage.clickPhoneLogTab();
				}
				
				else
				{
					Log.pass("Redirected to error page.");
					Log.message("", driver, true);

					Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
					//casePhoneLog.dismissPhoneLog(case_PhoneLogMessage);
				}
				
				i += 1;
			}while(i < 361);
			
			
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.message("Total failed scenarios are: " + failure);
			Log.endTestCase();
			driver.quit();
		}
	}

	
	@Test(description = "Check for telerik under Case Email", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void XSS_TC_2(String browser) throws Exception 
	{
		int failure = 0;
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Check for telerik under Case Email";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickEmailsTab();
			
			CaseEmail caseEmailsPage = new CaseEmail(driver);
			
			caseEmailsPage.clickComposeEmailButton();
			
			int i = 1;
			
			do
			{
				String case_PhoneLogMessage = readExcel.initTest(workbookNameXSS, sheetName, "proper_input_" + i);
				
				caseEmailsPage.enterMessageAndSendEmail(case_PhoneLogMessage, "proper_input_" + i);
				
				if(!UtilsXSS.checkIfRedirectedToErrorPage(driver))
				{
					Log.pass("Not Redirected to error page.");
					Log.message("", driver, true);
					
					caseEmailsPage.clickComposeEmailButton();
				}
				
				else
				{
					Log.message("", driver, true);
					Log.failsoft("Redirected to error page");
					failure += 1;
					
					caseListPage.clickEmailsTab();
					
					caseEmailsPage.clickComposeEmailButton();
				}
				
				i += 1 ;
			}while(i < 3);
			
			i = 1;
			
			do
			{
				String case_PhoneLogMessage = readExcel.initTest(workbookNameXSS, sheetName, "malicious_input_" + i);
				
				caseEmailsPage.enterMessageAndSendEmail(case_PhoneLogMessage, "malicious_input_" + i);
				
				if(UtilsXSS.checkIfRedirectedToErrorPage(driver))
				{
					Log.pass("Redirected to error page.");
					
					Log.message("", driver, true);
				}
				
				else
				{
					Log.message("", driver, true);
					Log.failsoft("Did not redirect to error page");
					failure += 1;
					
					driver.get(webSite);
					
					login.caseManagerlogin(userName, password, true);

					login.clickAgreeButton(false);
					
					caseManagerHomePage.clickCaseTab(false);

					caseListPage.clickOnCaseId(caseId, false);
					
					caseListPage.clickEmailsTab();
					
					caseEmailsPage.clickComposeEmailButton();
				}
				
				i += 1 ;
				
			}while(i < 361);
			
		
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.message("Total failed scenarios are: " + failure);
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Check for textarea under Case Email Subject", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void XSS_TC_3(String browser) throws Exception 
	{
		int failure = 0;
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String caseId = readExcel.initTest(workbookName, sheetName, "QA_ALoginCaseCreatedForUSA");

		globalVariables.testCaseDescription = "Check for telerik under Case Email";

		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerHomePage.clickCaseTab(false);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);

			caseListPage.clickOnCaseId(caseId, false);
			
			caseListPage.clickCustomDataTab(false);
			
			CustomData customData = new CustomData(driver);
			
			customData.clickEditCustomData();
			
			int i = 1;
			
			do
			{
				String case_PhoneLogMessage = readExcel.initTest(workbookNameXSS, sheetName, "proper_input_" + i);
				
				customData.addCustomDataField(case_PhoneLogMessage, "proper_input_" + i);
				
				if(!UtilsXSS.checkIfRedirectedToErrorPage(driver))
				{
					Log.pass("Not Redirected to error page");
					Log.message("", driver, true);
					
					customData.clickEditCustomData();
				}
				
				else
				{
					Log.message("", driver, true);
					Log.failsoft("Redirected to error page for ' " + "proper_input_" + i + " '");
					failure += 1;
					
					List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					
					for(int j = tabs.size() - 1; j > 1;)
					{
						driver.switchTo().window(tabs.get(j));
						driver.close();
						j = driver.getWindowHandles().size() - 1;
					}
					
					driver.switchTo().window(tabs.get(0));
					driver.get(webSite);
					
					login.caseManagerlogin(userName, password, true);

					login.clickAgreeButton(false);

					caseManagerHomePage.clickCaseTab(true);
					
					caseListPage.clickOnCaseId(caseId, true);
					
					caseListPage.clickCustomDataTab(false);
					
					customData.clickEditCustomData();
				}
				
				i += 1 ;
			}while(i < 3);
			
			i = 1;
			
			do
			{
				String case_PhoneLogMessage = readExcel.initTest(workbookNameXSS, sheetName, "malicious_input_" + i);
				
				customData.addCustomDataField(case_PhoneLogMessage, "malicious_input_" + i);
				
				if(UtilsXSS.checkIfRedirectedToErrorPage(driver))
				{
					Log.pass("Redirected to error page.");
					
					Log.message("", driver, true);
				}
				
				else
				{
					Log.message("", driver, true);
					Log.failsoft("Did not redirect to error page for ' " + "malicious_input_" + i + " '");
					failure += 1;
					
					List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					
					for(int j = tabs.size() - 1; j > 1;)
					{
						driver.switchTo().window(tabs.get(j));
						driver.close();
						j = driver.getWindowHandles().size() - 1;
					}
					
					driver.switchTo().window(tabs.get(0));
					
					driver.get(webSite);
					
					login.caseManagerlogin(userName, password, true);

					login.clickAgreeButton(false);
					
					caseManagerHomePage.clickCaseTab(false);

					caseListPage.clickOnCaseId(caseId, false);
					
					caseListPage.clickCustomDataTab(false);
					
					customData.clickEditCustomData();
		
				}
				
				i += 1 ;
				
			}while(i < 361);
			
		
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} 
		catch (Exception e) {
			Log.exception(e, driver);
		} 
		finally {
			Log.message("Total failed scenarios are: " + failure);
			Log.endTestCase();
			driver.quit();
		}
	}
	
}
