package com.inszoomapp.pages;


import org.openqa.selenium.By;
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

public class CM_CaseQuestionnairePage extends LoadableComponent<CM_CaseQuestionnairePage> {
	
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//div[contains(text(),'Case Info')]")
	WebElement txt_headerCaseProfile;							//Added by Yatharth Pandya on 23rd Sept, 2020
	
	@FindBy(xpath = "//h1[contains(text(),'Questionnaire')]")
	WebElement txt_headerQuestionnaireList; 						//Added by Yatharth Pandya on 22nd sept, 2020

	@FindBy(css="input[placeholder='Enter Questionnaire Name']")
	WebElement txtbox_SearchQuestionnaire;
	
	@FindBy(id="removebtn")
	WebElement btn_Remove;
	
	@FindBy(id="selectall")
	WebElement checkbox_SelectAll;
	
	@FindBy(id="btn_AddRemoveQuestionnaires_ForBeneficiary")
	WebElement btn_AddRemoveQuestionnaire;
	
	public CM_CaseQuestionnairePage(WebDriver driver)
	{
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
			Assert.fail();
		} else {
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void chooseAddRemoveQuestionnaires() throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 15 Nov 2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_AddRemoveQuestionnaire, "'Add/Remove' Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		//Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-running')]", "xpath");
		Utils.waitForElementPresent(driver, "//body[contains(@class, 'pace-done')]", "xpath");

	}
	
	
	public void removeAllQuestionnaires()
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 15 Nov 2019
		 * 
		 */
		
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		
		if(Utils.isElementPresent(driver, "div[class='k-grid-norecords']", "css"))
		{
			Log.pass("", driver, true);
			Log.pass("No Questionnaires Present");
		}
		
		else
		{			
			Utils.waitForElementClickable(driver, checkbox_SelectAll);
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[contains(@class,'pace-done')]")));
			
			Utils.clickElement(driver, checkbox_SelectAll, "Select All Checkbox");
			
			Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
			
			Utils.clickElement(driver, btn_Remove, "Remove");
			
			//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
			Utils.waitUntillLoaderDisappearsInHRP(driver);
		}
		
	}
	
	
	public void verifyIfQuestionnairesRemoved() throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 15 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "div[class='k-grid-norecords']", "css", "No records Found", "Questionnaire List");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");
	}
	
	
	public void addQuestionnaire(String questionnaire)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 20 Nov 2019
		 * 
		 */
		Utils.waitForLoadingMaskToDisappearInQuestionnaireSearchWindow(driver);
		if(Utils.isElementPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath"))
		{
			Log.pass("", driver, true);
			Log.pass(questionnaire + " Questionnaire already Present");
		}
		
		else
		{
			Utils.enterText(driver, txtbox_SearchQuestionnaire, questionnaire, "questionnaire search box");
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaire + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");
			
			//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
			Utils.waitUntillLoaderDisappearsInHRP(driver);
			
			Utils.waitForElement(driver, "//a[contains(text(), '" + questionnaire + "')]", globalVariables.elementWaitTime, "xpath");
			
		}
	}
	
	
	public void verifyIfQuestionnairesAdded(String questionnaire, String clientName) throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 20 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
	}
	
	
	
	public void verifyQuestionnairesListPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Questionnaires List");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Case Questionnaires List page failed", driver);
	    }    
	    
}
	
	public void clickAddRemoveQuestionnaire(boolean screenshot)  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 3rd March 2020
		  */ 
		Utils.clickElement(driver, btn_AddRemoveQuestionnaire, "'Add/Remove' Button");
	 }      
	
	
	
	public void VerifySearchQuestionnairePage() throws Exception 
	{
	
        /*
         * Created By :Sauravp
         * Created On : 3rdMarch 2020
         */ 
		try{
		

		Utils.waitForAllWindowsToLoad(2, driver);

		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		Utils.softVerifyPageTitle(driver, "Search Questionnaire");

		driver.close();

		Utils.waitForAllWindowsToLoad(1, driver);

		Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");

	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Case Questionnaires List Page Failed ", driver);

		
	    }

	}
	
	 public void removeQuestionnaire(String questionnaire) throws Exception
	    {
	        //Created by : Yatharth Pandya 
	        //Created on : 23nd Sept, 2020
	        
			if(Utils.isElementPresent(driver, "//a[contains(text(),'"+ questionnaire +"')]//ancestor::tr//td/input", "xpath"))
			{
			 	Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ questionnaire +"')]//ancestor::tr//td/input", "xpath", "Attached Questionnaire page checked");
		        Utils.waitForElementClickable(driver, btn_Remove);
		        Utils.clickElement(driver, btn_Remove, "remove questionnaire");
		        Utils.waitUntillLoaderDisappearsInHRP(driver);
//		        Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ questionnaire +"')]", txt_headerQuestionnaireList, "xpath", questionnaire, "Questionnaire list page");
	       
			}
			else
		 		Log.message(questionnaire + "is not prsent in case questionnaire list");
		
			driver.close();
	        Utils.waitForAllWindowsToLoad(1, driver);
	        Utils.switchWindows(driver, "aty_case_quest_list.aspx", "url", "false");
	        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
	        caseListPage.clickQuestionnairesTab();
	        Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ questionnaire +"')]", txt_headerCaseProfile, "xpath", questionnaire, "Client questionnaire Page");
	        
	    }

		

}

