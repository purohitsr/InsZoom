package com.inszoomapp.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_PetitionHistoryPage extends LoadableComponent<CM_PetitionHistoryPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Added By Saurav
	
	@FindBy(xpath = "(//table[@summary='Current Immigration Status Lookup']//a[contains(text(),'Copy')])[1]")
    WebElement lnk_copyFirstImmigrationStatus; //Added By Saurav on 3nd Sep
	
	@FindBy(xpath = "//a[contains(text(),'Immigration Status Lookup')]")
    WebElement lnk_immigrationStatusLookup; //Added By Saurav on 3nd Sep
	
	@FindBy(id = "btn_AddImmigrationStatusHistory")
	WebElement btn_AddImmigrationStatusHistory; //Added By Saurav on 2nd Sep
	
	
	@FindBy(id = "btn_Close")
	WebElement btn_Close;
	
	@FindBy(id = "btn_SaveImmigrationHistory")
	WebElement btn_SaveImmigrationHistory;
	
	@FindBy(id = "btn_EditImmigrationStatusHistoryDetails")
	WebElement btn_EditImmigrationStatusHistoryDetails;
	
	@FindBy(id = "btn_SaveImmigrationStatusHistoryDetails")
	WebElement btn_SaveImmigrationStatusHistoryDetails;
	
	@FindBy(id = "txtRecptDate")
	WebElement textBox_receiptDate;
	
	@FindBy(id = "txtNonimmigrant")
	WebElement textBox_ImmigrationStatus;
	
	@FindBy(id = "cboPet")
	WebElement dropDown_PetitionDocType;
	
	@FindBy(id = "btn_AddPetitionHistory")
	WebElement btn_AddPetitionHistory;
	
	public CM_PetitionHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Petition history Page did not open up. Site might be down.", driver, true);
		}
	}
	
	public void addImmigrationStatusHistory(String petitionDocType, String immigrationStatus,String receiptDate,boolean screenshot) throws Exception 
	{
		/*
         * Modified By : Sauravp
         * Modified On : 7th Sep 2021
         * changed the method name from addPetition to addImmigrationStatusHistory
         */ 
		
		Utils.clickElement(driver, btn_AddImmigrationStatusHistory, "add Immigration status history button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Immigration Status History", "title", "false");
		Utils.waitForElement(driver, lnk_immigrationStatusLookup);
		Utils.clickElement(driver, lnk_immigrationStatusLookup, "immigration status lookup");
		
        Utils.waitForAllWindowsToLoad(3, driver);
        Utils.switchWindows(driver, "INSZoom.com - Current Immigration Status Lookup", "title", "false");
        Utils.waitForElement(driver, lnk_copyFirstImmigrationStatus);
        Utils.clickElement(driver, lnk_copyFirstImmigrationStatus, "immigration status lookup");
        
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - Add Immigration Status History", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, petitionDocType , dropDown_PetitionDocType);
		Utils.enterText(driver, textBox_ImmigrationStatus, immigrationStatus, "Immigration Status");
		
		Utils.enterText(driver, textBox_receiptDate, receiptDate, "receipt date");

		Utils.clickElement(driver, btn_SaveImmigrationStatusHistoryDetails, "save");	
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : View Immigration Status History Details", "title", "false");

	}
	
	public void editPetition(String presentImmigratitionStatus, String edittedImmigrationStatus, String edittedPetitiomDocType, boolean screenshot) throws Exception 
	{
		/*
         * Created By : Saurav Purohit
         * Created On : 19 Oct 2019
         */ 
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ presentImmigratitionStatus +"')]", "xpath", "Petition History (Immigratition link)");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Immigration Status History", "title", "false");
		Utils.clickElement(driver, btn_EditImmigrationStatusHistoryDetails, "Edit ImmigrationStatusHistoryDetails button");
		Utils.selectOptionFromDropDownByVal(driver, edittedPetitiomDocType, dropDown_PetitionDocType);
		Utils.enterText(driver, textBox_ImmigrationStatus, edittedImmigrationStatus, "Editted Immigration status");
		Utils.clickElement(driver, btn_SaveImmigrationHistory, "save Immigration History button");
		Utils.clickElement(driver, btn_Close, "Close button");
		Utils.switchWindows(driver, "INSZoom.com : View Immigration Status History Details", "title", "false");		
	}
	
	public void verifyPetitionHistory(String immigrationStatus, Boolean screenShot){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 19 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ immigrationStatus +"')]", "xpath", immigrationStatus, "immigrationStatus");
	}
	

	public void verifyViewPetitionHistoryDetailsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 10 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com : View Petition History Details");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com : View Petition History Details Failed ", driver);
	    }
		
		
	}
	
	public void clickAndVerifyAddPetitionHistory() throws Exception 
	{
		/*
         * Created By :Sauravp
         * Created On : 10 Feb 2020
         */ 
		try{
		Utils.clickElement(driver, btn_AddPetitionHistory, "add petition history button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Petition History", "title", "false");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : View Petition History Details", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Add Petition History Page Failed ", driver);
		 driver.close();
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com : View Petition History Details", "title", "false");
	    }

	}
	
	
	public void verifyViewImmigrationStatusHistoryDetailsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 10 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com : View Immigration Status History Details");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com : View Immigration Status History Details", driver);
	    }
		
		
	}
	
	public void clickAndVerifyAddImmigrationStatusHistory() throws Exception 
	{
		/*
         * Created By :Sauravp
         * Created On : 01 May 2020
         */ 
		try{
		Utils.clickElement(driver, btn_AddImmigrationStatusHistory, "add Immigration Status history button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Immigration Status History", "title", "false");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : View Immigration Status History Details", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com : View Immigration Status History Details Failed ", driver);
		 driver.close();
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com : View Immigration Status History Details", "title", "false");
	    }

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}