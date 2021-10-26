package securityXSS;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class CustomData extends LoadableComponent<CustomData>
{
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id = "btn_SaveCustomFieldValues")
    WebElement btn_SaveCustomFieldValues;
	
	@FindBy(id = "btn_EditCustomFieldValues")
    WebElement btn_EditCustomFieldValues;
	
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
	
	
	public CustomData(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickEditCustomData() throws Exception
	{
		Utils.clickElement(driver, btn_EditCustomFieldValues, "edit custom field value button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
	}
	
	 public void addCustomDataField(String salary, String message) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 17 Oct 2019
 		 */
		
		WebElement textBox_Salary = driver.findElement(By.xpath("//th/label[contains(text(),'Employee Expected Salary')]/../../td/input"));
	    Utils.enterText(driver, textBox_Salary, salary, "salary");
	    Log.message(message, driver, true);
	    Utils.clickElement(driver, btn_SaveCustomFieldValues, "save button");
	    if(driver.getWindowHandles().size() == 2)
	    {
		    if(Utils.isAlertPresent(driver))
		    {
		    	Log.failsoft("Alert is present: " + driver.switchTo().alert().getText() + " FOR " + message);
		    }
	    }	
	    
	    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(tabs.size() - 1));
	} 
}
