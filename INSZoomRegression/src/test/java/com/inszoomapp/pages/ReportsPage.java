package com.inszoomapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class ReportsPage extends LoadableComponent<ReportsPage> 
{

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//span[contains(text(),'Visa and Priority Dates Reports')]")
	WebElement tab_VisaPriorityDatesReports;
	
	@FindBy(xpath = "//td[contains(text(),'Visa and Priority Dates Reports')]")
	WebElement header_VisaPriorityDates;
	
	@FindBy(xpath = "//span[contains(text(),'Customized Reports')]")
	WebElement tab_CustomizedReports;
	
	@FindBy(xpath = "//tr[@title=\"Click here to view Favorite Reports\"]")
	WebElement tab_FavoriteReports;
	
	@FindBy(id = "LMmgmt")
	WebElement tab_ManagementReports;
	
	@FindBy(id = "btn_RemovefromtheFavoriteReportsList")
	WebElement btn_RemoveFavoriteReports;
	
	@FindBy(id = "btn_CheckAll")
	WebElement btn_CheckAllFavoriteReports;

	public ReportsPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail("HrP home page didnt loaded");
		}
	}
	
	public void clearOldfavoriteReports() 
	{
	   /*
	    * Created By : M Likitha Krishna
	    * Created On : 22 Oct 2019
	    */
		clickFavoriteReportsTab();
		try{
			if(Utils.isElementPresent(driver, "btn_CheckAll", "id"))
			{
			Utils.clickElement(driver, btn_CheckAllFavoriteReports, "Check all button");
			Utils.clickElement(driver, btn_RemoveFavoriteReports, "Remove button");
			}
		}
		catch(Exception e)
		{
			Log.message("No Previous Favorite Records to be removed");
		}
		if (Utils.isAlertPresent(driver)) {
			driver.switchTo().alert().accept();
		} else {
			System.out.println("alert was not present");
		}
	}
	
	 public void clickManagementReportsTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	   Utils.clickElement(driver, tab_ManagementReports, "Management Reports tab");        
	 }
	 
	 public void clickFavoriteReportsTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	   Utils.clickElement(driver, tab_FavoriteReports, "Favorite Reports tab");        
	 }
	 
	 public void clickCustomizedReportsTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */
	   Utils.clickElement(driver, tab_CustomizedReports, "Customized Reports tab");        
	 }
	
	 public void verifyFavoriteReportPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Favorite reports list");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Favorite reports list failed", driver);
		    }

	}
	 
	 public void verifyCustomizeReportsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Customized Report List");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Customized Report List page failed", driver);
		    }

	}
	 
	 public void verifyManagementReportsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Reports");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Management Reports page failed", driver);
		    }

	}
	 
	 
	 public void clickVisaPriorityDatesReportsTab()
	  {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 4th March 2020
		  */
	   Utils.clickElement(driver, tab_VisaPriorityDatesReports, "Visa and Priority dates Reports Tab");        
	 }
	
	 public void verifyVisaPriorityDatesReportsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th March 2020
			  */
		    try{
			 
			 Utils.verifyIfStaticElementDisplayed(driver, header_VisaPriorityDates, "Visa and Priority text", "Page header");
		    }catch(Exception e){
			 Log.failsoft("Verification of Visa and Priority dates page failed", driver);
		    }

	}
	
}