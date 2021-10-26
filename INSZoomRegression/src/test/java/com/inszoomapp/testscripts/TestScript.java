package com.inszoomapp.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import popupCheck.CorpPage;
import ContentAutomation.GetDataFromExcelAndVerify;

import com.inszoomapp.client.pages.CommunicationSummaryPage;
import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataDev;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.AccessCodePage;
import com.inszoomapp.pages.CM_BlanketApplicantsPage;
import com.inszoomapp.pages.CM_CalendarPage;
import com.inszoomapp.pages.CM_CaseAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_CaseDocChecklistPage;
import com.inszoomapp.pages.CM_CaseEmailsPage;
import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_CasePhoneLogPage;
import com.inszoomapp.pages.CM_CaseProfilePage;
import com.inszoomapp.pages.CM_CaseQuestionnairePage;
import com.inszoomapp.pages.CM_CaseTaskPage;
import com.inszoomapp.pages.CM_ChangeLoginIdClientPage;
import com.inszoomapp.pages.CM_ClientAppointmentsAndActivitiesPage;
import com.inszoomapp.pages.CM_ClientDocumentsPage;
import com.inszoomapp.pages.CM_ClientEmailsPage;
import com.inszoomapp.pages.CM_ClientFormsPage;
import com.inszoomapp.pages.CM_ClientListPage;
import com.inszoomapp.pages.CM_ClientPhoneLogPage;
import com.inszoomapp.pages.CM_ClientProfilePage;
import com.inszoomapp.pages.CM_ClientQuestionnairePage;
import com.inszoomapp.pages.CM_ClientTaskPage;
import com.inszoomapp.pages.CM_ClientToDoPage;
import com.inszoomapp.pages.CM_Client_Relative_VisaPage;
import com.inszoomapp.pages.CM_CorporationCustomDataPage;
import com.inszoomapp.pages.CM_CorporationEmailPage;
import com.inszoomapp.pages.CM_CorporationListPage;
import com.inszoomapp.pages.CM_CorporationPage;
import com.inszoomapp.pages.CM_CorporationQuestionnairePage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.CM_DetailsAndDatesPage;
import com.inszoomapp.pages.CM_DistrictOfficeInfoPage;
import com.inszoomapp.pages.CM_DocumentExpirationsPage;
import com.inszoomapp.pages.CM_EditClientRelativePage;
import com.inszoomapp.pages.CM_EmailsPage;
import com.inszoomapp.pages.CM_HistoryInfoPage;
import com.inszoomapp.pages.CM_InvoicePage;
import com.inszoomapp.pages.CM_JobDetailspage;
import com.inszoomapp.pages.CM_KnowledgeBasePage;
import com.inszoomapp.pages.CM_Letters_MSWordPage;
import com.inszoomapp.pages.CM_ManagerContactsPage;
import com.inszoomapp.pages.CM_MySettingsPage;
import com.inszoomapp.pages.CM_NotesPage;
import com.inszoomapp.pages.CM_PassportInfoPage;
import com.inszoomapp.pages.CM_PersonalInfoPage;
import com.inszoomapp.pages.CM_PetitionHistoryPage;
import com.inszoomapp.pages.CM_ReceiptNumbersPage;
import com.inszoomapp.pages.CM_Reports_3_0_Page;
import com.inszoomapp.pages.CM_ServiceCentreInfoPage;
import com.inszoomapp.pages.CM_SettingsPage;
import com.inszoomapp.pages.CM_TravelInfoPage;
import com.inszoomapp.pages.CM_UsImmigrationInfoPage;
import com.inszoomapp.pages.CaseStepPage;
import com.inszoomapp.pages.FNPNewsPage;
import com.inszoomapp.pages.FNP_CaseProfilePage;
import com.inszoomapp.pages.FNP_DocumentsPage;
import com.inszoomapp.pages.FnpHomePage;
import com.inszoomapp.pages.HrpHomePage;
import com.inszoomapp.pages.LoginPageTest;
import com.inszoomapp.pages.PortalSetup;
import com.inszoomapp.pages.ReportsPage;
import com.inszoomapp.pages.StatusDocumentsLinkPage;
import com.inszoomapp.reports.CustomizedReportsPage;
import com.inszoomapp.reports.FavoriteReportPage;
import com.inszoomapp.reports.ManagementReportsPage;
import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.FileuploaddownloadUtil;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class TestScript extends BaseTest
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
				
					ReadWriteExcel readExcel = new ReadWriteExcel();
					userName = readExcel.initTest(workbookName, sheetName, "CM_userName");
					password = readExcel.initTest(workbookName, sheetName, "CM_password");
					break;
				}
			}
		} 
	}catch (Exception e) {
		e.printStackTrace();
	}

	}	
		
	
	
	
	
	
	
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Letter(MS WORD)-Template from server(KB Templates) option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_22_1(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Template from server(KB Templates) option";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);

			lettersMSWordPage.useServerTemplateAndVerify(globalVariables.letterMSWordTemplateName);

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
			
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
	
	
	
	@Test(groups = {"letterTemplate", "client"}, description = "Case Manager-Client: Letter(MS WORD)-Verify download option", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void CM_TC_22_2(String browser) throws Exception {

		ReadWriteExcel readExcel = new ReadWriteExcel();
		String clientName = readExcel.initTest(workbookName, sheetName, "ALoginSavedFirstClientName");

		globalVariables.testCaseDescription = "Case Manager-Client: Letter(MS WORD)-Verify download option";

		final RemoteWebDriver driver = WebDriverFactory.get(browser);
		
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {

			LoginPageTest login = new LoginPageTest(driver, webSite);

			CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin(userName, password, true);

			login.clickAgreeButton(false);
			
			caseManagerDashboardPage.clickClientTab(true);

			CM_ClientListPage clientListPage = new CM_ClientListPage(driver);

			clientListPage.clickOnClientName(clientName, true);
			
			clientListPage.clickLetterMSWordTab();

			CM_Letters_MSWordPage lettersMSWordPage = new CM_Letters_MSWordPage(driver);
			
			
			    FileuploaddownloadUtil.get_downloaded_files(driver);
//				System.out.println("total No of files are "+list.size());
				 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				 driver.switchTo().window(tabs.get(1));
				 Utils.clickDynamicElement(driver, "//div[@name='LettersNativeGrid']//a[contains(text(),'"+ globalVariables.letterMSWordTemplateName +"')]/../..//img[@alt='Download']", "xpath", "download option");
				
				
				 driver.switchTo().window(tabs.get(2));
				 System.out.println(driver.getTitle());
				 Thread.sleep(10000);
				 ArrayList filesFound = (ArrayList) driver.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList').items.filter(e => e.state === 'COMPLETE').map(e => e.filePath || e.file_path || e.fileUrl || e.file_url);");

				
//				Thread.sleep(10000);
				
				if(filesFound.size() == 1)
					Log.pass("Downloaded successfully ", driver, true);
				else
					Log.fail("Unable to Download", driver, true);
				
				driver.close();
			
			
			

			

			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");

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
