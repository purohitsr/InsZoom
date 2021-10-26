package ContentAutomation;

import java.io.FileInputStream;
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
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.GetDataFromExcelAndVerfiy;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class Testscript extends BaseTest
{
	AppDataBase data ;
	
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

			switch (env.toUpperCase()) {
			case "QA": {
				data = new AppDataQA();
				globalVariables.environmentName = env;
				break;
			}
			case "STAGE": {
				data = new AppDataStage();
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
				globalVariables.environmentName = env;
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test(description = "AOS Adjustment of Status (Family Based) (I-485 ONLY)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void contentAutomaiton_1(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "AOS Adjustment of Status (Family Based) (I-485 ONLY)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		//String workbookName = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
		//String sheetName = "Sheet1";
		
		String workbookName = ".\\src\\main\\resources\\testdata\\data\\Document_Checklist_update_for_Templates.xls";
		String sheetName = "AOS - Family Based";
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin("AsauravProd", "Zoom@1234", true);
			
			login.clickAgreeButton(false);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KB_Petition petitionPage = new CM_KB_Petition(driver);
			
			petitionPage.clickPetitonName("AOS Adjustment of Status (Family Based) (I-485 ONLY)");
			
			GetDataFromExcelAndVerfiy obj = new GetDataFromExcelAndVerfiy(driver);
			
			obj.test1(workbookName, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "AOS Adjustment of Status (Employment Based) (I-140 & I-485)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void contentAutomaiton_2(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "AOS Adjustment of Status (Employment Based) (I-140 & I-485)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		//String workbookName = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
		//String sheetName = "Sheet1";
		
		String workbookName = ".\\src\\main\\resources\\testdata\\data\\Document_Checklist_update_for_Templates.xls";
		String sheetName = "AOS - Emp Based (I-140 & I-485)";
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin("AsauravProd", "Zoom@1234", true);
			
			login.clickAgreeButton(false);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KB_Petition petitionPage = new CM_KB_Petition(driver);
			
			petitionPage.clickPetitonName("AOS Adjustment of Status (Employment Based) (I-140 & I-485)");
			
			GetDataFromExcelAndVerfiy obj = new GetDataFromExcelAndVerfiy(driver);
			
			obj.test1(workbookName, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Immediate Relative -  Minor Child of USC (I-130 & I-485)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void contentAutomaiton_3(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "Immediate Relative -  Minor Child of USC (I-130 & I-485)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		//String workbookName = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
		//String sheetName = "Sheet1";
		
		String workbookName = ".\\src\\main\\resources\\testdata\\data\\Document_Checklist_update_for_Templates.xls";
		String sheetName = "Immediate Rel - MinorChild of U";
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin("AsauravProd", "Zoom@1234", true);
			
			login.clickAgreeButton(false);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KB_Petition petitionPage = new CM_KB_Petition(driver);
			
			petitionPage.clickPetitonName("Immediate Relative - Minor Child of USC (I-130 & I-485)");
			
			GetDataFromExcelAndVerfiy obj = new GetDataFromExcelAndVerfiy(driver);
			
			obj.test1(workbookName, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Immediate Relative - Parent of USC (I-130 & I-485)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void contentAutomaiton_4(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "Immediate Relative - Parent of USC (I-130 & I-485)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		//String workbookName = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
		//String sheetName = "Sheet1";
		
		String workbookName = ".\\src\\main\\resources\\testdata\\data\\Document_Checklist_update_for_Templates.xls";
		String sheetName = "Immediate Rel - Parent of USC";
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin("AsauravProd", "Zoom@1234", true);
			
			login.clickAgreeButton(false);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KB_Petition petitionPage = new CM_KB_Petition(driver);
			
			petitionPage.clickPetitonName("Immediate Relative - Parent of USC (I-130 & I-485)");
			
			GetDataFromExcelAndVerfiy obj = new GetDataFromExcelAndVerfiy(driver);
			
			obj.test1(workbookName, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
	
	@Test(description = "Immediate Relative - Spouse of USC (I-130 & I-485)", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void contentAutomaiton_5(String browser) throws Exception
	{
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		globalVariables.testCaseDescription = "Immediate Relative - Spouse of USC (I-130 & I-485)";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
		//String workbookName = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
		//String sheetName = "Sheet1";
		
		String workbookName = ".\\src\\main\\resources\\testdata\\data\\Document_Checklist_update_for_Templates.xls";
		String sheetName = "Immediate Rel - Spouse of USC";
		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerHomePage = login.caseManagerlogin("AsauravProd", "Zoom@1234", true);
			
			login.clickAgreeButton(false);

			caseManagerHomePage.clickKnowledgeBase(true);
			
			CM_KB_Petition petitionPage = new CM_KB_Petition(driver);
			
			petitionPage.clickPetitonName("Immediate Relative - Spouse of USC (I-130 & I-485)");
			
			GetDataFromExcelAndVerfiy obj = new GetDataFromExcelAndVerfiy(driver);
			
			obj.test1(workbookName, sheetName);
			
			caseManagerHomePage.clickLogout(true);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	

}
