package com.inszoomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_SettingsSecurityProfile  extends LoadableComponent<CM_SettingsPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//label[contains(text(),'Case Manager')]/ancestor::td/following-sibling::td//a[contains(text(),'Settings')]")
	WebElement lnk_profileSettings;
	
	@FindBy(id = "saveChangesforAccessRights")
	WebElement btn_saveAccessRights;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_SettingsSecurityProfile(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	
	public void toggleCheckBox(String key , boolean value)
    {
    	/*
	 		Created by Kuchinad Shashank
	 		Created on 10th June 2020
    	*/
    	
		Utils.waitForElement(driver, "//label[@for='" + key + "']", globalVariables.elementWaitTime, "xpath"); 
    	
    	String input = "//input[@id='" + key + "']" ;
		
		String label = "//label[@for='" + key + "']" ;
		
		WebElement checkbox_input = driver.findElement(By.xpath(input));
		
		//Utils.scrollToElement(driver, checkbox_input);
		
		if(value)
		{
			if (checkbox_input.isSelected()) 
			{
				Log.message("The checkbox for" + key +" is checked by default", driver, true);
			}
			
			else 
			{
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for" + key + " is checked", driver, true);
			}
		}
		
		else
		{
			if (checkbox_input.isSelected()) 
			{
				
				Utils.clickDynamicElement(driver, label, "xpath" , "CheckBox");
				Log.message("The checkbox for" + key + " is unchecked", driver, true);		
			}
			
			else 
			{
				Log.message("The checkbox for" + key +" is unchecked by default", driver, true);
				
			}
		}
		
		Utils.scrollIntoView(driver, btn_saveAccessRights);
		Utils.clickElement(driver, btn_saveAccessRights, "Save Changes Button");
		
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		
    }

	
	public void backToSettings() throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 11/06/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);

		Utils.switchWindows(driver, "INSZoom.com - View Details", "title", "false");
	}
	
	
	public void clickProfileSettingsLink()
	{
		/*
 		 *	Created by Kuchinad Shashank
 		 *	Created on 10th June 2020
		 */
		
		Utils.clickElement(driver, lnk_profileSettings, "Settings link");
		
		Utils.waitForElementPresent(driver, "//img[@class='zc-image']","xpath");
    	
		Utils.waitForElementNotVisible(driver, "//img[@class='zc-image']","xpath");
	}
	
	
	public void clickGenericRadioButton(String key)
	{
		/*
 		 *	Created by Kuchinad Shashank
 		 *	Created on 18th June 2020
		 */
		
		String label = "//label[@for='" + key + "']";
		
		Utils.clickDynamicElement(driver, label, "xpath" , "Radio Button");
		
		Utils.scrollIntoView(driver, btn_saveAccessRights);
		Utils.clickElement(driver, btn_saveAccessRights, "Save Changes Button");
		
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success success_alert_msg show']", "css");
		
	}
}
