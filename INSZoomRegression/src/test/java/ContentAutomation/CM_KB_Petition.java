package ContentAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.support.files.Log;
import com.support.files.Utils;

public class CM_KB_Petition extends LoadableComponent<CM_KB_Petition>
{
	private final WebDriver driver;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_KB_Petition(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_pet_name")//Likitha
	WebElement icon_filter;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_pet_name")//Likitha
	WebElement searchBox_petition;
	
	@FindBy(xpath = "//tr[@id='ctl00_MainContent_ctl00_RadGridList_ctl00__0']")//Likitha
	WebElement tbl_petitionList;
	
	@FindBy(xpath = "//div[@id='ctl00_MainContent_ctl00_RadGridList_rfltMenu_detached']//span[text()='EqualTo']")//Likitha
	WebElement opt_EqualTo;
	
	@FindBy(xpath = "//td[@title='Click here for Docs Check List']")//Likitha
	WebElement tab_docsCheckList;
	
	@FindBy(xpath = "//u[contains(text(),'Zoom Petition Templates')]")//Likitha
	WebElement text_zoomPetitionTemplates;
	
	public void clickPetitonName(String petitionName)
	{
		Utils.clickElement(driver, text_zoomPetitionTemplates, "zoom petition templates");
		 Utils.waitUntilLoaderDisappear(driver);
		 Utils.waitForElement(driver, tbl_petitionList);
		
		 Utils.enterText(driver, searchBox_petition, petitionName, "in petition name search box");
		 Utils.clickElement(driver, icon_filter, "First name filter");
		
		try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_petitionList);
			 Log.message("Clicked on EQUALTO filter to search client and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	        
		 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
		 {
			 Log.fail("Unable to find desired case . Hence can not Proceed Further", driver, true);
		 }
	          
		// WebElement lnk_CaseId = driver.findElement(By.xpath("//a[contains(text(),'"+ petitionName +"')]"));
		 Utils.clickDynamicElement(driver, "//td/a[text()='"+petitionName+"']", "xpath", "petiton name");
		 //Utils.clickElement(driver, lnk_CaseId, "Click on client");
		
		
		//Utils.clickDynamicElement(driver, "//a[contains(text(),'Q-1 Cultural Exchange CP')]", "xpath", "petition name");
		
		Utils.clickElement(driver, tab_docsCheckList, "doc check list tab");
	
	}

}
