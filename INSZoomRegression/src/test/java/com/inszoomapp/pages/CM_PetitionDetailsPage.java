package com.inszoomapp.pages;

import java.util.List;

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

public class CM_PetitionDetailsPage extends LoadableComponent<CM_PetitionDetailsPage> {
	
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "pageHDR")
	WebElement txt_headerPetionQuestionnaire;		//Added by Yatharth Pandya on 21st Sept, 2020 		
	
	@FindBy(xpath = "//h1[contains(text(),'Questionnaire List')]")
	WebElement txt_headerQuestionnaireList;			// Added by Yatharth Pandya on 21st Sept, 2020
	
	@FindBy(id = "removebtn")
	WebElement btn_removeQuestionnaire;			//Added by Yatharth Pandya on 21st sept, 2020
	
	@FindBy(id = "txtSearch")
	WebElement txtbox_searchQuestionnaire; 			// added by Yatharth Pandya on 17th sept, 2020
	
	@FindBy(xpath = "//table[@summary='Client Information']/../../preceding-sibling::tr[1]//input[@id='btn_AddRemoveQuestionnaires']")
	WebElement btn_addRemoveQuestionnaire;				//Added by Yatharth Pandya on 17th Sept, 2020

	@FindBy(id = "LMLeftTab6")
	WebElement tab_questionnaire;		//Added by Yatharth Pandya on 17th Sept, 2020
	

	
	
	public CM_PetitionDetailsPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Login Page did not open up. Site might be down.", driver);
		}
	}
	
	
	public void clickQuestionnnaireTab()
	{
		//Created by : Yatharth Pandya
		//Created on : 17th Sept, 2020
		
		Utils.clickElement(driver, tab_questionnaire, "Questionnaire Tab");
	}
	
	
	public void addAndVerifyQuestionnaire(String petitionAttachedQuestionnaire) throws Exception
	{
		//Created by : Yatharth Pandya
		//Created on : 17th Sept, 2020
		
		Utils.clickElement(driver, btn_addRemoveQuestionnaire, "Add Questionnaire");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
//		Utils.clickElement(driver, txtbox_searchQuestionnaire, "Questionnaire search box");
//		txtbox_searchQuestionnaire.clear();
		Utils.enterText(driver, txtbox_searchQuestionnaire, petitionAttachedQuestionnaire, "Questionnaire Search box");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+ petitionAttachedQuestionnaire +"')]/../following-sibling::span//i[@class='fa fa-plus']", "xpath", "Questionnaire added");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.waitForElement(driver, "//a[contains(text(), '" + petitionAttachedQuestionnaire + "')]", globalVariables.elementWaitTime, "xpath");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "aty_petn_quest_list.aspx", "url", "false");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ petitionAttachedQuestionnaire +"')]", "xpath", petitionAttachedQuestionnaire, "Petition details page");
		
	}
	
	public void removeAndVerifyQuestionnaire(String petitionAttachedQuestionnaire) throws Exception
	{
		//Created by : Yatharth Pandya
		//Created on : 21st Sept, 2020
		
		Utils.clickElement(driver, btn_addRemoveQuestionnaire, "Add Questionnaire");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ petitionAttachedQuestionnaire +"')]//ancestor::tr//td/input", "xpath", "Attached Questionnaire page checked");
		Utils.waitForElementClickable(driver, btn_removeQuestionnaire);
		Utils.clickElement(driver, btn_removeQuestionnaire, "remove questionnaire");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
//		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ petitionAttachedQuestionnaire +"')]", txt_headerQuestionnaireList, "xpath", petitionAttachedQuestionnaire, "Questionnaire list page");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "aty_petn_quest_list.aspx", "url", "false");
		clickQuestionnnaireTab();
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ petitionAttachedQuestionnaire +"')]", txt_headerPetionQuestionnaire, "xpath", petitionAttachedQuestionnaire, "Petition Details Page");
		
	}
	
	
	
	
}
