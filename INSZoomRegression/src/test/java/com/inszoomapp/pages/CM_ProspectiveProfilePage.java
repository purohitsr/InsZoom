package com.inszoomapp.pages;

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


public class CM_ProspectiveProfilePage extends LoadableComponent<CM_ProspectiveProfilePage>
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Web elements
	@FindBy(xpath = "//a[@class='rcCalPopup']") //Added by Souvik Ganguly on 28/06/2021
	WebElement link_Calendar;
	
	@FindBy(xpath = "//div[@id='QuestionnaireSearchResults']//tbody[@role='rowgroup']") //Added by Souvik Ganguly on 21/06/2021
	WebElement table_QuestionnaireSearchResults;
	
	@FindBy(id = "btn_DeleteprospectiveCorporationdetails")
	WebElement btn_DeleteProspectiveCorporation;
	
	@FindBy(id = "LMDetails")
	WebElement tab_Details;
	
	@FindBy(id = "btn_Continue")
	WebElement btn_Continue;
	
	@FindBy(id = "btn_Save&SendemailtonewClient")
	WebElement btn_SaveAndSendEmail;
	
	@FindBy(id = "btn_AcceptprospectiveCorporationasCorporation")
	WebElement btn_AcceptAsCorporation;
	
	@FindBy(id = "btn_SaveprospectiveClientregistration")
	WebElement btn_SaveProspectiveClientRegistration;
	
	@FindBy(xpath = "//select[@name='cboBnfType']")
	WebElement dropDown_category;
	
	@FindBy(id = "btn_AcceptprospectiveClientasClient")
	WebElement btn_AcceptAsClient;
	
	@FindBy(id = "btn_DeleteprospectiveClient")
	WebElement btn_DeleteProspectiveClient;
	
	@FindBy(id = "LMI025")
	WebElement tab_Profile;
	
	@FindBy(id = "btn_SaveprospectiveClientpersonaldetails")
	WebElement btn_SaveProspectiveClientDetails;
	
	@FindBy(id = "txtConsDate")
	WebElement textBox_consultationDate;
	
	@FindBy(id = "txtFileNum")
	WebElement textBox_fileNumber;
	
	@FindBy(id = "btn_EditprospectiveClientpersonaldetails")
	WebElement btn_EditProspectiveClient;
	
	@FindBy(id = "LMI013")
	WebElement tab_clientQuestionnaire;
							
	@FindBy(xpath = "//img[contains(@src,'icons/zoom_go_arrow.gif')]")
	WebElement btn_Go;
	
	@FindBy(css="input[placeholder='Enter Questionnaire Name']")
	WebElement txtbox_SearchQuestionnaire;
	
	@FindBy(css="select[name='optQuestUtil']")
	WebElement dropdown_QuestionnaireActions;
	
	@FindBy(id = "LMQuest")
	WebElement tab_Questionnaire;
	
	
	public CM_ProspectiveProfilePage(WebDriver driver)
	{
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
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	//functions
	
	public void clickQuestionnaireTab()
	 {
		 /*
 		  * Created By : Likitha Krishna
 		  * Created On : 19 August 2020 
 		  */ 
		 Utils.clickElement(driver, tab_Questionnaire, "questionnaire Tab");
	 }
	
	public void clickClientQuestionnaireTab()
	 {
		 /*
 		  * Created By : Likitha Krishna
 		  * Created On : 19 August 2020 
 		  */ 
		 Utils.clickElement(driver, tab_clientQuestionnaire, "client questionnaire Tab");
	 }
	
	public void chooseAddRemoveQuestionnaires() throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 19 Aug 2020
		 * 
		 */
		
		Utils.selectOptionFromDropDownByVal(driver, "AddOrRemoveQuest", dropdown_QuestionnaireActions);
		
		Utils.clickElement(driver, btn_Go, "Go Icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		
		Utils.waitUntillLoaderDisappearsInHRP(driver);
	}
	
	public void addQuestionnaire(String questionnaire) throws Exception
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 22 June 2021
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
	
	public void verifyIfCorporationQuestionnairesAdded(String questionnaire) throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 19 Aug 2020
		 * 
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "new_epr_quest_list.aspx", "url", "false");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
	}
	
	public void verifyIfClientQuestionnairesAdded(String questionnaire) throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 19 Aug 2020
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Questionnaire List", "title", "false");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
	}
	
	public void clickEditProspectiveClientButton() throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 24 Aug 2020
		 */
		Utils.clickElement(driver, btn_EditProspectiveClient, "edit button");
	}

	
	public void addfileNumberAndConsultationDate(String fileNumber, String consultationDate) throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 24 Aug 2020
		 */
		clickEditProspectiveClientButton();
		Utils.enterText(driver, textBox_fileNumber, fileNumber, "file number");
		Utils.enterText(driver, textBox_consultationDate, consultationDate, "consultation date");
		Utils.clickElement(driver, btn_SaveProspectiveClientDetails, "save button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
	}
	
	public void clickProfileTab() throws Exception
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 24 Aug 2020
		 */
		Utils.clickElement(driver, tab_Profile, "profile tab");
	}
	
	public void deleteClient() throws Exception
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Created On: 28 June 2021
		 */
		Utils.waitForElement(driver, "frmDefault", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmDefault");
		Utils.clickElement(driver, btn_DeleteProspectiveClient, "delete button");
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Utils.waitForElement(driver, link_Calendar);
		Utils.waitForPageLoad(driver);
	}
	
	public void acceptProspectiveClientAsClient() throws Exception
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		Utils.clickElement(driver, btn_AcceptAsClient, "accept as client button");
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		Utils.selectOptionFromDropDown(driver, "Individual/Family Based", dropDown_category);
		Utils.clickElement(driver, btn_SaveProspectiveClientRegistration, "save button");
	}

	
	public void acceptProspectiveCorporationAsCorporation() throws Exception
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		Utils.clickElement(driver, btn_AcceptAsCorporation, "accept as corporation button");
		driver.switchTo().alert().accept();
		Utils.clickDynamicElement(driver, "//input[@name='chkLgl']", "xpath", "email id checkbox");
		Utils.clickElement(driver, btn_SaveAndSendEmail, "save and send email");
		Utils.clickElement(driver, btn_Continue, "continue");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
	}
	
	public void deleteCorporation() throws Exception
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		Utils.clickElement(driver, tab_Details, "Details");
		Utils.clickElement(driver, btn_DeleteProspectiveCorporation, "delete corporation button");
		driver.switchTo().alert().accept();
	}

}
