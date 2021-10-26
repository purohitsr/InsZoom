package com.inszoomapp.pages;

import org.openqa.selenium.By;
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

public class CM_CorporationQuestionnairePage extends LoadableComponent<CM_CorporationQuestionnairePage> {
	
	private final WebDriver driver;
	private boolean isPageLoaded;
	@FindBy(xpath = "//div[@id='QuestionnaireSearchResults']//tbody[@role='rowgroup']") //Added by Souvik Ganguly dated 21/06/2021
	WebElement table_QuestionnaireSearchResults;
	
	@FindBy(xpath = "//a[contains(text(),'Questionnaire Corporation')]")
    WebElement txt_headerQuestionnaireCorporation;                         // Added by Yatharth on 22nd Sept, 2020
   
    @FindBy(xpath = "//h1[contains(text(),'Questionnaire List')]")
    WebElement txt_headerQuestionnaireList;                         //Added by Yatharth on 22nd sept, 2020
   
    @FindBy(id = "removebtn")
    WebElement btn_removeQuestionnaire;            //Added by Yatharth on 22nd Sept, 2020
	
	@FindBy(id="btn_SaveGrantRevokeQuestionnairesAccess")
	WebElement btn_Save;
	
	@FindBy(css="input[name='chkQuestAccess']")
	WebElement checkbox_GrantAccess;
	
	@FindBy(css="input[placeholder='Enter Questionnaire Name']")
	WebElement txtbox_SearchQuestionnaire;
	
	@FindBy(css="select[name='optQuestUtil']")
	WebElement dropdown_QuestionnaireActions;
	
	@FindBy(css="a[title='Go']")
	WebElement btn_Go;
	
	public CM_CorporationQuestionnairePage(WebDriver driver)
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
			Log.fail("Corporation list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void chooseAddRemoveQuestionnaires() throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 22 Nov 2019
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "AddOrRemoveQuest", dropdown_QuestionnaireActions);
		
		Utils.clickElement(driver, btn_Go, "Go Icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		
	}
	
	
	public void chooseEmailQuestionnaire() throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 22 Nov 2019
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "Email Quest", dropdown_QuestionnaireActions);
		
		Utils.clickElement(driver, btn_Go, "Go Icon");
	}
	
	
	public void addQuestionnaire(String questionnaire)
	{
		/*
		 * Created By: Souvik Ganguly
		 * Created On: 22 June 2021
		 * 
		 */
		/*Utils.waitForElement(driver, table_QuestionnaireSearchResults);	
		Utils.waitForPageLoad(driver);*/
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
			
			Utils.waitUntillLoaderDisappearsInHRP(driver);
			
			Utils.waitForElement(driver, "//a[contains(text(), '" + questionnaire + "')]", globalVariables.elementWaitTime, "xpath");
			
		}
	}
	
	
	public void verifyIfQuestionnairesAdded(String questionnaire, String corpName) throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 22 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "aty_epr_quest_list.aspx", "url", "false");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
	}
	
	
	public void grantAccess() 
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 22 Nov 2019
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "GrantRevokeAccess", dropdown_QuestionnaireActions);
		
		Utils.clickElement(driver, btn_Go, "Go Icon");
		
		if(!checkbox_GrantAccess.isSelected())
			Utils.clickElement(driver, checkbox_GrantAccess, "Grant Access checkbox");
		
		Utils.clickElement(driver, btn_Save, "'Save' button");
		
	}
	
	  public void removeQuestionnaire(String questionnaire) throws Exception
	  {
	        //Created by : Yatharth Pandya 
	        //Created on : 22nd Sept, 2020
	        
		  if(Utils.isElementPresent(driver, "//a[contains(text(),'"+ questionnaire +"')]//ancestor::tr//td/input", "xpath"))
		  {
			  	Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ questionnaire +"')]//ancestor::tr//td/input", "xpath", "Attached Questionnaire page checked");
			  	Utils.waitForElementClickable(driver, btn_removeQuestionnaire);
			  	Utils.clickElement(driver, btn_removeQuestionnaire, "remove questionnaire");
		        Utils.waitUntillLoaderDisappearsInHRP(driver);  
		  }
		  else
		 		Log.message(questionnaire + "is not prsent in corporation questionnaire list");
		
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "aty_epr_quest_list.aspx", "url", "false");
			CM_CorporationListPage corporationListPage = new CM_CorporationListPage(driver);
			corporationListPage.clickQuestionnairesTab();        
			Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ questionnaire +"')]", txt_headerQuestionnaireCorporation, "xpath", questionnaire, "Corpoation questionnaire Page");
	  }
	  
	  
	    public void grantAccessForQuestionnaire(String questionnaire) 
		{
			/*
			 * Created By: Yatharth Pandya
			 * Created On: 25th Sept 2020
			 * 
			 */
			
			Utils.selectOptionFromDropDownByVal(driver, "GrantRevokeAccess", dropdown_QuestionnaireActions);
			
			Utils.clickElement(driver, btn_Go, "Go Icon");
			
			WebElement checkbox_GrantAccessQuestionnaire = driver.findElement(By.xpath("//td[contains(text(),'"+ questionnaire +"')]//following-sibling::td//input[@type='checkbox']"));
			
			if(!checkbox_GrantAccessQuestionnaire.isSelected())
				Utils.clickElement(driver, checkbox_GrantAccessQuestionnaire, "Grant Access checkbox");
			
			Utils.clickElement(driver, btn_Save, "'Save' button");
			
			Utils.waitForElement(driver, dropdown_QuestionnaireActions);
			
		}
		
	    public void revokeAccessForQuestionnaire(String questionnaire) 
		{
			/*
			 * Created By: Yatharth Pandya
			 * Created On: 25th Sept 2020
			 * 
			 */
			
			Utils.selectOptionFromDropDownByVal(driver, "GrantRevokeAccess", dropdown_QuestionnaireActions);
			
			Utils.clickElement(driver, btn_Go, "Go Icon");
			
			WebElement checkbox_GrantAccessQuestionnaire = driver.findElement(By.xpath("//td[contains(text(),'"+ questionnaire +"')]//following-sibling::td//input[@type='checkbox']"));
			
			if(checkbox_GrantAccessQuestionnaire.isSelected())
				Utils.clickElement(driver, checkbox_GrantAccessQuestionnaire, "Grant Access checkbox");
			
			Utils.clickElement(driver, btn_Save, "'Save' button");
			
			Utils.waitForElement(driver, dropdown_QuestionnaireActions);
		}
	    
		public void verifyIfQuestionnairePresent(String questionnaire, String corpName) throws Exception
		{
			/*
			 * Created By: Yatharth Pandya
			 * Created On: 20th Oct 2020
			 * 
			 */
						
			Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
		}
		
		
		public void removeSelectedQuestionnaire(String questionnaireName) throws Exception
		{
			
			/* 
			 * Modified By: Saurav Purohit
			 * Modified On: 24/06/2019
			 * This Method removes a questionnaire from Questionnaire list window and switch the control back to main window
			 */
			
			Utils.clickDynamicElement(driver, "//a[contains(text(), '" + questionnaireName + "')]/../preceding-sibling::td/input", "xpath", "Checkbox to select questionnaire");
			
			Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
			
			Utils.clickElement(driver, btn_removeQuestionnaire, "Remove Button");
			
			Utils.waitUntillLoaderDisappearsInHRP(driver);
			
			Utils.waitForElementNotVisible(driver, "//a[contains(text(), '" + questionnaireName + "')]", "xpath");
			
			driver.close();
			
			Utils.waitForAllWindowsToLoad(1, driver);
			
			Utils.switchWindows(driver, "INSZoom.com - Questionnaires for", "title", "false");
		}
	
}