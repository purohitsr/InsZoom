package com.inszoomapp.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_SettingsPage extends LoadableComponent<CM_SettingsPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	//Added By Sauravp on 4th march
	
	@FindBy(id = "productModalPopupContent")		                             //Added by Nitisha Sinha
	WebElement popup_storage;			   							            //on 05/11/2020				
	
	@FindBy(id = "productModalPopupCloseBtn")		                             //Added by Nitisha Sinha
	WebElement icon_closePopupStorage;			   							            //on 05/11/2020				
	
	@FindBy(xpath = "//th[contains(text(),'View Organization Information')]")		//Added by Kuchinad Shashank
	WebElement waitElement_header;			   							            //on 09/06/2020				
	
	@FindBy(xpath = "//td[contains(text(),'Security Profile')]")
	WebElement tab_securityProfile;
	
	
	@FindBy(xpath = "//td[@id='pageHDR'][contains(text(),'Tools Overview')]")
	WebElement waitElement_pageHeader;
	
	@FindBy(xpath = "//span[contains(text(),'Change My Password')]")
	WebElement tab_ChangeMyPassword;
	
	@FindBy(xpath = "//span[contains(text(),'Change Others Password')]")
	WebElement tab_ChangeOthersPassword;
	
    @FindBy(xpath = "//span[contains(text(),'Broadcast Messages')]")
	WebElement tab_BroadcastMessages;
    
    @FindBy(xpath = "//span[contains(text(),'Disk Space Usage Details')]")
   	WebElement tab_DiskSpaceUsageDetails;
    
    //
	
	
	@FindBy(xpath = "//tr[@title='Organization Tools']")
	WebElement tab_OrganizationTools;
	
	@FindBy(xpath = "//tr[@title='Advanced Settings']")
	WebElement tab_AdvancedSettings;
	
	@FindBy(id = "txtSearch")
	WebElement searchBox_AdvanceSettings;
	
	@FindBy(xpath = "//tr[@title='Case Managers']")
	WebElement tab_CaseManagers;
	
	
	public CM_SettingsPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Settings page didnt loaded");
		}
	}
	
	 public void clickCaseManagerTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	   Utils.clickElement(driver, tab_CaseManagers, "Case manager tab in settings page");        
	 }
	 
	
	 
	 public void verifyCaseManagerINSZoom(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickCaseManagerTab();
		Utils.verifyIfDataPresent(driver, "//td[@class='pageHDR'][contains(text(),'Case Manager List')]", "xpath", "header - Case Manager List", "in Case Manager List page");
	}
	 
	 public void clickAdvancedSettingsTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	   Utils.clickElement(driver, tab_AdvancedSettings, "Advanced Settings in settings page");        
	 }
	 
	 public void verifyAdvancedSettings(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickAdvancedSettingsTab();
		try {
			if (searchBox_AdvanceSettings.isDisplayed()) {
				Log.pass("Verified the visiblity of advanced settings page and the page is loading fine");
			} else {
				Log.fail("Unable to Verify the visiblity of advanced settings page", driver, screenshot);
			}
		} catch (Exception exception) {
			throw new Exception("Cannot locate the element" + exception.getMessage());
		}
	}
	 
	 
	 public void clickOrganizationToolsTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	   Utils.clickElement(driver, tab_OrganizationTools, "Organization Tools tab in settings page");        
	 }
	 
	 public void verifyOrganizationToolsPage(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'Tools Overview')]", "xpath", "Header - Tools Overview", "Organization Tools module in settings page");
	}
	 
	 
	 public String getFirmName()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 08 April 2020
		  */
		 
		 Utils.waitForElement(driver, "//th[text() = 'Name']/following-sibling::td[1]", globalVariables.elementWaitTime, "xpath");
		 
		 return driver.findElement(By.xpath("//th[text() = 'Name']/following-sibling::td[1]")).getText().trim();
	 }
	
	 
	 
	 
	 public void verifyAdvancedSettingsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - View Details");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - View Details page failed", driver);
		    }

	}
	 
	 
	 public void verifySearchSettingsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Zoom Easy Customization");
			 driver.navigate().back();
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Zoom Easy Customization page failed", driver);
		    }

	}
	
	 public void verifyToolsOverviewPage() 
	{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com -Tools overview");
			
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com -Tools overview page failed", driver);
		    }

	}
	 
	 public void clickChangeMyPasswordTab()
	  {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 4th March 2020
		  */
	   Utils.clickElement(driver, tab_ChangeMyPassword, "Change My Password Tab");        
	 }
	 
	 
	 public void VerifyChangeMyPasswordWindow() throws Exception 
		{
		
	        /*
	         * Created By :Sauravp
	         * Created On : 4th March 2020
	         */ 
			try{
			
			Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "INSZoom.com-Change My Password", "title", "false");
			
			Utils.softVerifyPageTitle(driver, "INSZoom.com-Change My Password");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "INSZoom.com -Tools overview", "title", "false");

		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com-Change My Password Page Failed ", driver);

		    }

		}
	 
	 
	
	 
	 
	 public void clickChangeOthersPasswordTab()
	  {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 4th March 2020
		  */
	   Utils.clickElement(driver, tab_ChangeOthersPassword, "Change My Password Tab");        
	 }
	 
	 
	 public void VerifyChangeOthersPasswordWindow() throws Exception 
		{
		
	        /*
	         * Created By :Sauravp
	         * Created On : 4th March 2020
	         */ 
			try{
			
			Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "INSZoom.com-Change Others Password", "title", "false");
			
			Utils.softVerifyPageTitle(driver, "INSZoom.com-Change Others Password");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "INSZoom.com -Tools overview", "title", "false");

		    }catch(Exception e){
			 Log.failsoft("Verification of  INSZoom.com-Change Others Password Page Failed ", driver);

		    }

		}
	 
	 
	 public void clickBroadcastMessagestab()
	  {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 4th March 2020
		  */
	   Utils.clickElement(driver, tab_BroadcastMessages, "Broadcast Messages Tab");        
	 }
	 
	 
	 public void VerifyBroadcastMessagesPage() throws Exception 
		{
		
	        /*
	         * Created By :Sauravp
	         * Created On : 4th March 2020
	         */ 
			try{
			
			Utils.waitForAllWindowsToLoad(2, driver);

			Utils.switchWindows(driver, "Broadcast Messages - INSZoom.com", "title", "false");
			
			Utils.softVerifyPageTitle(driver, "Broadcast Messages - INSZoom.com");

			driver.close();

			Utils.waitForAllWindowsToLoad(1, driver);

			Utils.switchWindows(driver, "INSZoom.com -Tools overview", "title", "false");

		    }catch(Exception e){
			 Log.failsoft("Verification of   Broadcast Messages - INSZoom.com Page Failed ", driver);

		    }

		}
	 
	 
	 
	 
	 
	 public void clickDiskSpaceUsageDetailstab()
	  {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 4th March 2020
		  */
	   Utils.clickElement(driver, tab_DiskSpaceUsageDetails, "Disk Space Usage Details Tab");        
	 }
	 
	 public void VerifyDiskSpaceUsageDetailsPage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  * Modified by Nitisha Sinha on 5th Nov, 2020
				  * Modification done: Written logc to handle added pop up window
				  */
			    try{
			    	Utils.waitForAllWindowsToLoad(2, driver);

					Utils.switchWindows(driver, "Subscription", "title", "false");
					
					if(Utils.waitForElement(driver, popup_storage))
					{	
						Utils.clickElement(driver, icon_closePopupStorage, "Storage popup close icon");
					}
				    Utils.softVerifyPageTitle(driver, "Subscription");
				    
				    driver.close();
				 
				    Utils.waitForAllWindowsToLoad(1, driver);
				 
				    Utils.switchWindows(driver, "INSZoom.com -Tools overview", "title", "false");
				
			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Disk Space Usage Details page failed", driver);
			    }

		}
	 
	 
	 public void clickSecurityProfileTab() throws Exception
	 {
		 /*
		  * Created By : Kuchinad Shashank
		  * Created On : 08 April 2020
		  */
		 
		 Utils.clickElement(driver, tab_securityProfile, "Security Profile Tab");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "Security Profiles - INSZoom.com", "title", "false");
		 
		 Utils.waitForElementPresent(driver, "//div[@class='k-grid-content k-auto-scrollable']", "xpath");
		 
	 }
	 
	 
	 public void verifySecurityProfileTabPresent(boolean value)
	 {
		 /*
		  * Created By : Kuchinad Shashank
		  * Created On : 08 April 2020
		  */
		 
		 if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Security Profile')]", "xpath", "Security Profile Tab", "Settings Page");
		 }
		 else
		 {
			Utils.verifyIfDataNotPresent(driver, "//td[contains(text(),'Security Profile')]", waitElement_header, "xpath", "Security Profile Tab", "Settings Page");
		 }
	 }
	 
	 
	 public void verifySetupToolsTabPresent(boolean value)
	 {
		 /*
		  * Created by Kuchinad Shashank
		  * Created on 14/08/2020
		  */
		 
		 if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Setup Tools')]", "xpath", "Setup Tools grp", "Organization Tools");
		 }
		 else
		 {
			 Utils.verifyIfDataNotPresent(driver, "//td[contains(text(),'Setup Tools')]", waitElement_pageHeader, "xpath", "Setup Tools grp", "Organization Tools");
		 }
	 }
}