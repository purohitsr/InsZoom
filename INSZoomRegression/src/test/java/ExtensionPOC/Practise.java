package ExtensionPOC;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;

import com.inszoomapp.pages.CM_CaseFormPage;
import com.inszoomapp.pages.CM_CaseListPage;
import com.inszoomapp.pages.CM_DashboardPage;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.BaseTest;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.Utils;

@Listeners(EmailReport.class)
public class Practise extends BaseTest{
	
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/insind935/Desktop/grid/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addExtensions(new File("C:/Users/insind935/AppData/Local/Google/Chrome/User Data/Default/Extensions/bmdignebhhpjipncgjelngepnhlipaem/1.0.0.52_0.crx"));
//		options.addExtensions(new File("C:/Users/insind935/Downloads/INSZoom-E-File_v1.0.0.52.crx"));
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver = new ChromeDriver(dc);
		
		driver.get("https://global.inszoom.com/");
		Log.message("Launched Website");
		
		Utils.enterTextInDynamicElement(driver, "input[placeholder*='Login ID']", "css", "ASauravProd", "username");
		Utils.enterTextInDynamicElement(driver, "input[placeholder*='Password']", "css", "Zoom@1234", "password");
		Utils.clickDynamicElement(driver, "login", "id", "Clicked");
		
		LoginPageTest login = new LoginPageTest(driver, webSite);

        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin("Asauravprod", "Zoom@1234", true);

//        login.clickAgreeButton(false);
//
//        caseManagerDashboardPage.clickCaseTab(true);
//        
//        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//        
//        caseListPage.clickOnCaseId("KBHCZ02804-3", true); //Created for forms
//   
        //caseListPage.clickOnCaseId("KBHCZ02794-1", true);
        
//        caseListPage.clickFormsTab();

//        CM_CaseFormPage caseformPage = new CM_CaseFormPage(driver);
	}
	
}