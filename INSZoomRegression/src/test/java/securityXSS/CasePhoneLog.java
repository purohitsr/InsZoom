package securityXSS;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CasePhoneLog extends LoadableComponent<CasePhoneLog>
{
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id="btn_Addnewphonemessage")
	WebElement btn_AddPhoneLog;
	
	@FindBy(css = "input[title='Dismiss Phone Message']")
	WebElement btn_DismissPhoneLogMessage;
	
	@FindBy(css = "textarea[name='txtdesc']")
	WebElement txtbox_PhoneLogMessage;
	
	@FindBy(css = "input[id='btn_Savephonelogdetails']")
	WebElement btn_SavePhoneLog;
	
	@FindBy(id = "UserCntrl_UserCntrl_btnAddAll")
	WebElement btn_AddAll;
	
	public CasePhoneLog(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Case phone log page didnt loaded");
		}

	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void addPhoneLogForCase(String case_PhoneLogMessage)throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */

		driver.switchTo().frame(1);
		
		Utils.clickElement(driver, btn_AddPhoneLog, "Add Phone Logs");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Phone Log Entry", "title", "false");
		
		Utils.enterText(driver, txtbox_PhoneLogMessage, case_PhoneLogMessage, "Phone Log Description");
		Log.message("", driver, true);
		
		Utils.clickElement(driver, btn_AddAll, "Add All Button");

		Utils.clickElement(driver, btn_SavePhoneLog, "Save Button");
		
		if(driver.getWindowHandles().size() == 2)
		{
		
			if(Utils.isAlertPresent(driver))
			{
				Log.failsoft("Alert is present saying: " + driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
			}
		}
		
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(tabs.size() - 1));
	
	}
	
	public void dismissPhoneLog(String case_PhoneLogMessage) throws Exception
	{
		Utils.waitForAllWindowsToLoad(3, driver);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));

		Utils.waitForElement(driver, "//td[contains(text(), '" + case_PhoneLogMessage + "')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		driver.switchTo().defaultContent();
	}

}
