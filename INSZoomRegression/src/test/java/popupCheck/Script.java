package popupCheck;

import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.globalVariables;
import com.inszoomapp.pages.LoginPageTest;
import com.support.files.DataProviderUtils;
import com.support.files.Log;
import com.support.files.WebDriverFactory;

public class Script 
{
	Properties prop = new Properties();
	OutputStream output = null;
	String webSite = null;
	String browserName = null;
	
	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

		globalVariables.browserUsedForExecution = browserName;
	}
	
	@Test(description = "Check PopUp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void loginTest(String browser) throws Exception 
	{
		final WebDriver driver = WebDriverFactory.get(browser);
		driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);

		try {
			
			//ElementProxy elementProxy = new ElementProxy(driver);
			LoginPageTest loginPage = new LoginPageTest(driver, webSite);
			Login login = new Login(driver);
			login.login("AHCZ16", "Zoom@123");
			CorpPage corp = new CorpPage(driver);
			corp.corpo();

		}catch(Exception e)
		{
			Log.fail(e.getMessage());
		}
		finally
		{
			//driver.quit();
		}
	}
}
