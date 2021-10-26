package com.inszoomapp.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class PracticeTestScript extends BaseTest
{
	String userName = null;
	String password = null;
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
					break;
				}
				case "STAGE": {
					data = new AppDataQA();
					userName = data.CM_userName;
					password = data.CM_password;
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
	
	
	@Test(description = "Creation of Corporation in CM Login", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void sampleTest(String browser) throws Exception
	{
		globalVariables.testCaseDescription = "Case Manager: Creation of Corporation in Case Manager";
		
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		
		try
		{
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(webSite, userName, password);
			
			Log.testCaseResult();
		
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
}
