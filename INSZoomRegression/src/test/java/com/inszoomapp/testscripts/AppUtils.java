package com.inszoomapp.testscripts;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import popupCheck.CorpPage;
import popupCheck.Login;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class AppUtils extends BaseTest {

	Properties prop = new Properties();
	OutputStream output = null;
	InputStream input = null;
	String userName = null;
	String password = null;
	AppDataBase data;

	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

		
		globalVariables.browserUsedForExecution = browserName;

		try {

			input = new FileInputStream("./src/main/resources/config.properties");
			prop.load(input);

			switch (env.toUpperCase()) 
			{
				case "QA": {
					data = new AppDataQA();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					break;
				}
				case "STAGE": {
					data = new AppDataStage();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					break;
				}
				case "AST": {
					globalVariables.environmentName = env;
					break;
				}
				case "PROD": {
					data = new AppDataProd();
					userName = data.CM_userName;
					password = data.CM_password;
					globalVariables.environmentName = env;
					break;
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Delete All Phone Logs if present", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void deletePhoneLogs(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Case Manager: Creation of Corporation in Case Manager";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			Login login1 = new Login(driver);
			
			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin(userName, password, true);
			
			caseManagerHomePage.deletePhoneLogs(true);
			
		}catch(Exception e){
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Check PopUp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void closePopUp(String browser) throws Exception 
	{
		globalVariables.testCaseDescription = "Case Manager: Closing of Popup which appears after every deployment";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			//ElementProxy elementProxy = new ElementProxy(driver);
			LoginPageTest loginPage = new LoginPageTest(driver, webSite);
			Login login = new Login(driver);
			login.login(userName, password);
			CorpPage corp = new CorpPage(driver);
			Thread.sleep(10000);
			corp.corpo();

		}catch(Exception e)
		{
			Log.fail(e.getMessage());
		}
		finally
		{
			driver.quit();
		}
	}
}