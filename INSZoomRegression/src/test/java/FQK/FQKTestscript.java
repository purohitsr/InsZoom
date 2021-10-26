package FQK;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class FQKTestscript extends BaseTest
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
	
	
	@Test(description = "G-28 Form automation", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void FQK_TC_1(String browser) throws Exception
    {
          
          globalVariables.testCaseDescription = "G-28 Form automation";
          final WebDriver driver = WebDriverFactory.get(browser);
          driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

          try {
                LoginPageTest login = new LoginPageTest(driver, webSite);

                CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin("Asauravprod", "Zoom@1234", true);

                login.clickAgreeButton(false);

                caseManagerDashboardPage.clickCaseTab(true);
                
                CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
                
                caseListPage.clickOnCaseId("KBHCZ02804-3", true); //Created for forms
           
                //caseListPage.clickOnCaseId("KBHCZ02794-1", true);
                
                caseListPage.clickQuestionnairesTab();
                
                QuestionnairePage questionnairePage = new QuestionnairePage(driver);
                
                questionnairePage.fillQuest1();
                
                questionnairePage.fillQuest2();
                
                caseListPage.clickFormsTab();

                CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);

                caseformPage.addFormAndVerify(globalVariables.formsFrom, globalVariables.formGroup, "G-28");
                
               // caseListPage.clickFormsTab();
    			
    			CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
    			
    			caseFormPage.clickOnFillForm("G-28", "KBHCZ02804-3");
    			
    			driver.switchTo().frame("frmhtmlForms");
    			
    			FormsPage formspage = new FormsPage(driver);
    			
    			formspage.verifyIfDataPopulated("form58_1", FQK.globalVariables.attorneyELISAccountNumber);
    			formspage.verifyIfDataPopulated("form60_1", FQK.globalVariables.attorneylicensingAuthority);
    			formspage.verifyIfDataPopulated("form67_1", FQK.globalVariables.attorneylicenseNumber);
    			formspage.verifyIfDataPopulated("form64_1", FQK.globalVariables.attorneytelephone);
    			formspage.verifyIfDataPopulated("form69_1", FQK.globalVariables.attorneymobileNumber);
    			
    			WebElement applicant = driver.findElement(By.id("form5_2"));
    			Utils.scrollToElement(driver, applicant);
    			Utils.clickDynamicElement(driver, "form5_2", "id", "Applicant");
    			
    			formspage.verifyIfDataPopulated("form91_2", FQK.globalVariables.elisAccountNumber);
    			formspage.verifyIfDataPopulated("form84_2", FQK.globalVariables.alienNumber);
    			
    			formspage.verifyIfDataPopulated("form85_2", FQK.globalVariables.EPstreetNumber + " " + FQK.globalVariables.EPstreetName);
    			formspage.verifyIfDataPopulated("form83_2", FQK.globalVariables.EPaptNumber);
    			formspage.verifyIfDataPopulated("form86_2", FQK.globalVariables.EPcityName);
    			formspage.verifyIfDataPopulated("form82_2", FQK.globalVariables.EPzip);
    			formspage.verifyIfDataPopulated("form93_2", FQK.globalVariables.EP_receiptNumber);
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

	
	
	@Test(description = "Check if Data is populated from the questionnaire", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void formDataValidation(String browser) throws Exception
	{
		
		globalVariables.testCaseDescription = "Check if Data is populated from the questionnaire";
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin("ASauravProd", "Zoom@1234", true);

			login.clickAgreeButton(false);

			caseManagerDashboardPage.clickCaseTab(true);

			CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
			
			caseListPage.clickOnCaseId("KBHCZ02804-1", true);
			
			caseListPage.clickFormsTab();
			
			CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
			
			caseFormPage.clickOnFillForm("G-28", "KBHCZ02804-1");
			
			driver.switchTo().frame("frmhtmlForms");
			
			FormsPage formspage = new FormsPage(driver);
			
			formspage.verifyIfDataPopulated("form58_1", FQK.globalVariables.attorneyELISAccountNumber);
			formspage.verifyIfDataPopulated("form60_1", FQK.globalVariables.attorneylicensingAuthority);
			formspage.verifyIfDataPopulated("form67_1", FQK.globalVariables.attorneylicenseNumber);
			formspage.verifyIfDataPopulated("form64_1", FQK.globalVariables.attorneytelephone);
			formspage.verifyIfDataPopulated("form69_1", FQK.globalVariables.attorneymobileNumber);
			
			WebElement applicant = driver.findElement(By.id("form5_2"));
			Utils.scrollToElement(driver, applicant);
			Utils.clickDynamicElement(driver, "form5_2", "id", "Applicant");
			
			formspage.verifyIfDataPopulated("form91_2", FQK.globalVariables.elisAccountNumber);
			formspage.verifyIfDataPopulated("form84_2", FQK.globalVariables.alienNumber);
			
//			formspage.verifyIfDataPopulated("form52_1", data);
//			formspage.verifyIfDataPopulated("form50_1", data);
//			formspage.verifyIfDataPopulated("form49_1", data);
//			formspage.verifyIfDataPopulated("form61_1", data);
//			
//			formspage.verifyIfDataPopulated("form64_1", data);
//			formspage.verifyIfDataPopulated("form69_1", data);
//			formspage.verifyIfDataPopulated("form68_1", data);
//			formspage.verifyIfDataPopulated("form62_1", data);
//			
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

}
