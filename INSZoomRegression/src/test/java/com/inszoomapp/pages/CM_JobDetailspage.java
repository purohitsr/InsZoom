package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_JobDetailspage extends LoadableComponent<CM_JobDetailspage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Added By saurav on 5th Feb 2020
	
	@FindBy(xpath="//td[contains(text(),'Current Job Info')]")
	WebElement header_CurrentJobInfo;
	
	
	@FindBy(id="btn_SaveClientsProposedJobInfo")//Likitha
	WebElement btn_SaveProposedJobDetails;
	
	@FindBy(id = "btn_SaveProposedJobInfo")
	WebElement btn_SaveProposedJobInfoInCase;
	
	@FindBy(id = "txtcity")
	WebElement textBox_ProposedJobDetailsCityInCase;
	
	@FindBy(id = "btn_EditProposedJobDetails")
	WebElement btn_EditProposedJobDetailsInCase;
		
	@FindBy(id = "txtcity")
	WebElement textBox_City; 
	
	@FindBy(id = "btn_EditProposedJobDetails") //Likitha
	WebElement btn_EditProposedJobDetails;
	
	@FindBy(css = "input[id=\"txtPropJobTitle\"]")
	WebElement txtbox_ProposedOccupation;
	
	@FindBy(css = "input[title='Edit Job Details']")
	WebElement btn_EditJobDetails;

	@FindBy(css = "input[name=\"txtcity\"]")
	WebElement txtbox_City;

	@FindBy(css = "input[id=\"txtOccupation\"]")
	WebElement txtbox_Occupation;

	@FindBy(css = "input[id=\"TxtJobCode\"]")
	WebElement txtbox_JobCode;
	
	@FindBy(css = "input[id='btn_SaveClientsCurrentJobDetails']")
	WebElement btn_SaveCurrentJobDetails;

	

	public CM_JobDetailspage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);

	}

	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Job details Page did not open up. Site might be down.", driver, true);
		}

	}
	
	
	public void editJobDetails(String jobTitle, String jobCode, boolean screenshot) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.clickElement(driver, btn_EditJobDetails, "Edit Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Current Job Details", "title", "false");
		
		Utils.enterText(driver, txtbox_Occupation, jobTitle, "Job Title");
		
		Utils.enterText(driver, txtbox_JobCode, jobCode, "Job Code");
			
		Utils.clickElement(driver, btn_SaveCurrentJobDetails, "Save Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Job Details", "title", "false");
		
	}
	
	
	public void editProposedJobDetails(String occupation, String city, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.clickElement(driver, btn_EditProposedJobDetails, "Edit Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Proposed Job Info", "title", "false");
		
		Utils.enterText(driver, txtbox_City, city, "City");

		Utils.enterText(driver, txtbox_ProposedOccupation, occupation, "Proposed Job");

		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");

		Utils.clickElement(driver, btn_SaveProposedJobDetails, "Save Button");
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Job Details", "title", "false");

	}
	
	
	
	public void verifyJobDetails(String occupation, String jobCode)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Occupation/Job Title')]/following-sibling::td[contains(text(), '" + occupation + "')]", "xpath", occupation, "Occupation in Job Details");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Job Code')]/following-sibling::td[contains(text(), '" + jobCode + "')]", "xpath", jobCode, "Job Code in Job Details");
	}
	
	
	public void verifyProposedJobDetails(String city, String jobTitle)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'City/Town')]/following-sibling::td[contains(text(), '" + city + "')]", "xpath", city, "City in Proposed Job Details");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Job Title')]/following-sibling::td[contains(text(), '" + jobTitle + "')]", "xpath", jobTitle, "Job Title in Proposed Job Details");
	}
	
	
	public void editProposedJobDetailsCity(String city, boolean screenshot) throws Exception 
	{
		/*
         * Created By : M Likitha Krishna
         * Created On : 18 Oct 2019
         */
		Utils.clickElement(driver, btn_EditProposedJobDetails, "Edit Proposed Job Details");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Proposed Job Info", "title", "false");
		Utils.enterText(driver, textBox_City, city, "city name");
		Utils.clickElement(driver, btn_SaveProposedJobDetails, "Save Proposed Job Details");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Job Details", "title", "false");
	}
	
	
	public void verifyProposedJobDetailsCityInCase(String city, Boolean screenShot)
	{
		/*
       * Created By : M Likitha Krishna
       * Created On : 18 Oct 2019
       */
	
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'City/Town')]/../td[contains(text(),'"+ city +"')]", "xpath", city, "city name");
	}
	
	public void verifyProposedJobDetailsCityInClient(String city, Boolean screenShot)
	{
		/*
       * Created By : M Likitha Krishna
       * Created On : 18 Oct 2019
       */
	
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'City/Town')]/../td[contains(text(),'"+ city +"')]", "xpath", city, "city name");
	}
	
	
	public void clickEditProposedJobDetailsButtonInCase(Boolean screenShot)
	{
		/*
       * Created By : M Likitha Krishna
       * Created On : 18 Oct 2019
       */
	
		Utils.clickElement(driver, btn_EditProposedJobDetailsInCase, "Edit proposed job details button");
	}
	
	
	public void editProposedJobDetailsInCase(String edittedCityName,boolean screenshot) throws Exception {
		  /*
	       * Created By : M Likitha Krishna
	       * Created On : 18 Oct 2019
	       */
		clickEditProposedJobDetailsButtonInCase(true);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Proposed Job Info", "title", "false");
		Utils.enterText(driver, textBox_ProposedJobDetailsCityInCase, edittedCityName, "Editted city name");
		Utils.clickElement(driver, btn_SaveProposedJobInfoInCase, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Proposed Job Info", "title", "false");
	}
	
	
	public void verifyIfNOTProposedJobDetailsCityInClient(String city, Boolean screenShot)
	{
		/*
       * Created By : M Likitha Krishna
       * Created On : 18 Oct 2019
       */
		Utils.waitForElement(driver, "//th[contains(text(),'City/Town')]", globalVariables.elementWaitTime, "xpath");
		WebElement text_City = driver.findElement(By.xpath("//th[contains(text(),'City/Town')]"));
		Utils.verifyIfDataNotPresent(driver, "//th[contains(text(),'City/Town')]/../td[contains(text(),'"+ city +"')]", text_City, "xpath", "city", "city");
	}
	
	
	public void verifyJobdetailsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 05 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client's Job Details");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - View Client's Job Details Page Failed", driver);
	    }

}
	
	public void verifyCurrentJobdInfo() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 05 Feb 2020
		  */
	    try{
		 
		 Utils.verifyIfStaticElementDisplayed(driver, header_CurrentJobInfo, "Current Job Header", "xpath");
	    }catch(Exception e){
		 Log.failsoft("Verification of Current Job Header has Failed", driver);
	    }

}
	
}