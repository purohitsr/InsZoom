package com.inszoomapp.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.AppDataUAT;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_KBQuestionnairePage extends LoadableComponent<CM_KBQuestionnairePage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//select[@name='cboCountryId']")
	WebElement dropdown_copyToCountry;                                //Added by Nitisha Sinha on 23rd Sept,2020
	
	@FindBy(id = "btn_Copyfromcountry")
	WebElement btn_copyFromCountry;                                //Added by Nitisha Sinha on 23rd Sept,2020
	
	@FindBy(xpath = "//td[contains(text(),'Edit Corporation Questionnaire Details')]")
	WebElement header_editCorporationQuestionnaireDetails;                                //Added by Nitisha Sinha on 21st Sept,2020
	
	@FindBy(id = "btn_SaveQuestionnaire")
	WebElement btn_saveEditQuestionnaireDetails;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(xpath = "//td[contains(text(),'Edit Client Questionnaire Details')]")
	WebElement header_editClientQuestionnaireDetails;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(id = "btn_CopythisQuestionnairetocreateanewone")
	WebElement btn_copyQuestionnaire;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(id = "txtQstName")
	WebElement textbox_copyQuestionnaireName;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(xpath = "//td[contains(text(),'Copy Questionnaire')]")
	WebElement header_copyQuestionnaire;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(xpath = "//a[@title='Go']")
	WebElement icon_showQuestionnaireList;                                //Added by Nitisha Sinha on 14th Sept,2020
	
	@FindBy(xpath = "//td[contains(text(),'Questionnaire Marked for Deletion')]")
	WebElement header_questionnaireMarkedForDeletion;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_Clickheretoviewallquestionnairemarkedfordeletion")
	WebElement btn_showQuestionnaireMarkedForDeletion;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_Delete")
	WebElement btn_deleteQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_AddnewCorporationquestionnaire")
	WebElement btn_addCorporationQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_AddnewClientquestionnaire")
	WebElement btn_addClientQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "cboOrgCountry")
	WebElement dropdown_country;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "txtar_quest_name")
	WebElement txtbox_questionnaireName;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(xpath = "//input[@value='Firm Custom Information']//ancestor::tr[contains(@class,'TBLROW')]//input[@type='checkbox']")
	WebElement checkBox_questionnaireSection;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_Savenewquestionnaire")
	WebElement btn_saveQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(xpath = "//td[@id='pageHDR'][contains(text(),'Questionnaire List')]")
	WebElement header_QuestionniareList;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_SearchQuestionnaire")
	WebElement btn_searchQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "cboSearchType")
	WebElement dropDown_findQuestionnaireWithName;             //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "txtSearchQuest")
	WebElement txtbox_searchQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_Find")
	WebElement btn_findQuestionnaire;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	@FindBy(id = "btn_GoToQuestionnaireList")
	WebElement btn_goToQuestionnaireList;                                //Added by Nitisha Sinha on 11th Sept,2020
	
	public CM_KBQuestionnairePage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}
	
	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Knowledge page didnt loaded");
		}

	}
	
	 public void addClientQuestionnaire(String questionnaireName) throws InterruptedException
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
	   Utils.clickElement(driver, btn_addClientQuestionnaire, "Add Client Questionnaire button");   
	   
	   Utils.waitForElementPresent(driver, "//td[contains(text(),'Add Client Questionnaire Details')]", "xpath");
	 
	   Utils.selectOptionFromDropDownByVal(driver, "USA", dropdown_country);
	  
	   txtbox_questionnaireName.clear();
		
	   Utils.enterText(driver, txtbox_questionnaireName, questionnaireName, "Questionnaire Name textbox");

	   JavascriptExecutor jse = (JavascriptExecutor)driver;
	   jse.executeScript("window.scrollBy(0,450)");
	   
	   Utils.clickElement(driver, checkBox_questionnaireSection, "Questionniare Section checkbox");
	   
	   jse.executeScript("window.scrollBy(0,-450)");

	   Utils.waitForElementClickable(driver, btn_saveQuestionnaire);
	   Utils.clickElement(driver, btn_saveQuestionnaire, "Save Questionnaire button");
	  
	 }
	 
	 
	 public void searchQuestionnaire(String questionnaireName, boolean value, String questionnaireType )
	 {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */

		 Utils.clickElement(driver, btn_searchQuestionnaire, "Search Questionnaire button");
		   
		 Utils.selectOptionFromDropDownByVal(driver, "E", dropDown_findQuestionnaireWithName);
		   
		 Utils.enterText(driver, txtbox_searchQuestionnaire, questionnaireName , "search questionnaire Text box");
		 
		 Utils.clickElement(driver, btn_findQuestionnaire, "Find Questionnaire button");
		   
		 Utils.waitForElementPresent(driver, "rptShowQstList_processing_bar", "id");
		 Utils.waitForElementNotVisible(driver, "rptShowQstList_processing_bar", "id");
		 if(questionnaireType == "zoom defined")
		 {
			 if(value == true)
				 Utils.verifyIfDataPresent(driver, "//tr[2]/td[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "Questionaire List");
			 else
				 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No Records found.')]", "xpath", "No records found", "Questionaire List");
		 
		 }
		 if(questionnaireType == "firm defined")
		 {
			 if(value == true)
				 Utils.verifyIfDataPresent(driver, "//tr[2]/td/a[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "Questionaire List");
			 else
				 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No Records found.')]", "xpath", "No records found", "Questionaire List");
		 
		 }
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(-1000,0)");
	 }
 
	 
	 public void verifyAddQuestionnaire(String questionnaireName)
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
		 
		Utils.clickElement(driver, btn_goToQuestionnaireList, "Go To Questionnaire List Button");
		
	   Utils.waitForElement(driver, header_QuestionniareList);
	   
	   Utils.clickElement(driver, btn_searchQuestionnaire, "Search Questionnaire button");
	   
	   searchQuestionnaire(questionnaireName, true, "firm defined");
	  }
	 
	 
	 public void addCorporationQuestionnaire(String questionnaireName, String country)
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
	   Utils.clickElement(driver, btn_addCorporationQuestionnaire, "Add Corporation Questionnaire button");   
	   
	   Utils.waitForElementPresent(driver, "//td[contains(text(),'Add Corporation Questionnaire Details')]", "xpath");
	 
	   Utils.selectOptionFromDropDownByVal(driver, country , dropdown_country);
	   
	   txtbox_questionnaireName.clear();
		
	   Utils.enterText(driver, txtbox_questionnaireName, questionnaireName, "Questionnaire Name textbox");
	   
//	   Utils.scrollIntoView(driver, btn_saveQuestionnaire);
//	   Utils.scrollToElement(driver, btn_saveQuestionnaire);
//	   
	   Utils.clickElement(driver, btn_saveQuestionnaire, "Save Questionnaire button");
	  
	 }

	 
	 public void deleteQuestionnaire(String questionnaireName) throws InterruptedException
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
		 
		 Utils.waitForElementPresent(driver, "//tr[2]/td/a[contains(text(),'" + questionnaireName + "')]//ancestor::tr[contains(@class,'TBLROW')]//input", "xpath");
		 Thread.sleep(5000);
		 Utils.clickDynamicElement(driver, "//tr[2]/td/a[contains(text(),'" + questionnaireName + "')]//ancestor::tr[contains(@class,'TBLROW')]//input", "xpath", "checkbox for searched questionnaire");
		
		 Utils.clickElement(driver, btn_deleteQuestionnaire, "Delete Questionnaire button");   
		   
		 while (isAlertPresent()) {
				driver.switchTo().alert().accept();
			}
		 
		 Utils.waitForElementNotVisible(driver, "Corporation Questionnaire25-09-2020 01:28:48", "xpath");
	 }
	 
	 
	 public void verifyDeleteQuestionnaire(String questionnaireName) throws InterruptedException
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
		 
		 searchQuestionnaire(questionnaireName, false, "firm defined");
		 
		 Utils.clickElement(driver, btn_showQuestionnaireMarkedForDeletion, "Show Questionnaire marked for deletion button");
		   
		 Utils.waitForElement(driver, header_questionnaireMarkedForDeletion);
		 
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "questionnaire marked for deletion list");
	 }
	 
	 
	 public void showQuestionnaireListForCountry(String country) throws InterruptedException
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 14th September 2020
		  */
		 
		 Utils.selectOptionFromDropDownByVal(driver, country, dropdown_country);
	
		 Utils.clickElement(driver, icon_showQuestionnaireList, "Show Questionnaire List");
		 
		 Utils.waitForPageLoad(driver);
	  }
	 
	 
	 public void copyQuestionnaireToCountry(String questionnaireName, String copyTocountry, String questionnaireType, String copiedQuestionaireName, String questionnaireCategory) throws Exception
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 14th September 2020
		  */
		 
		 searchQuestionnaire(questionnaireName, true, questionnaireType);
		 
		 if(questionnaireType.equals("zoom defined"))
			 Utils.clickDynamicElement(driver, "//td[contains(text(),'" + questionnaireName + "')]/following-sibling::td/a[contains(text(),'Copy To')]", "xpath", "Copy To link for "+questionnaireName);
		 
		 if(questionnaireType.equals("firm defined"))
			 Utils.clickDynamicElement(driver, "//a[contains(text(),'" + questionnaireName + "')]/parent::td/following-sibling::td/a[contains(text(),'Copy To')]", "xpath", "Copy To link for "+questionnaireName);
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Copy Questionnaire", "title", "false");
		 
		 Utils.waitForElement(driver, header_copyQuestionnaire);
		 
		 textbox_copyQuestionnaireName.clear();
		 Utils.enterText(driver, textbox_copyQuestionnaireName, copiedQuestionaireName, "Textbox for copy questionaire");
		 
		 Utils.clickElement(driver, btn_copyQuestionnaire, "Copy Questionnaire button");
		 
		 if(questionnaireCategory.equals("Client"))
		 {
			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Edit Client Questionnaire Details", "title", "false");
			 
			 Utils.waitForElement(driver, header_editClientQuestionnaireDetails);
		 }
		 
		 if(questionnaireCategory.equals("Corporation"))
		 {
			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Edit Corporation Questionnaire Details", "title", "false");
			 
			 Utils.waitForElement(driver, header_editCorporationQuestionnaireDetails);
		 }
		 Utils.selectOptionFromDropDownByVal(driver, copyTocountry, dropdown_country);
		 
		 Utils.clickElement(driver, btn_saveEditQuestionnaireDetails, "Save editted questionnaire Details button");
	  }
	 
	 
	 public void verifyCopyQuestionnaireToCountry(String questionnaireName, String copyTocountry) throws Exception
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 17th September 2020
		  */
		 
		 showQuestionnaireListForCountry(copyTocountry);
		 
		 searchQuestionnaire(questionnaireName, true, "firm defined");
	  }
	 
	 
	 public void copyQuestionnaireFromCountry(String copyFromcountry, String copyTocountry, String questionnaireName) throws Exception
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 23rd September 2020
		  */
		 
		 Utils.clickElement(driver, btn_copyFromCountry, "Copy from Country");
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Copy Questionnaires for different Country", "title", "false");
		 
		 showQuestionnaireListForCountry("USA");
		 
		 Utils.selectOptionFromDropDownByVal(driver, copyTocountry, dropdown_copyToCountry);
		 
		 Utils.clickDynamicElement(driver, "//td[contains(text(),'" + questionnaireName + "')]//preceding-sibling::td/a", "xpath", "copy to link for "+questionnaireName);
	  
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Questionnaire List", "title", "false");
	  }
	 
	 
	 public void recreateQuestionnaire(String questionnaireName, String questionnaireNameToCopy) throws Exception
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 24th September 2020
		  */
		 
		 Utils.clickDynamicElement(driver, "//a[contains(text(),'" + questionnaireName + "')]/parent::td/following-sibling::td/a[contains(text(),'Copy From')]", "xpath", "copy from link for " + questionnaireName);
		 
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Recreate Questionnaires", "title", "false");
		 
		 Utils.clickDynamicElement(driver, "//td[contains(text(),'" + questionnaireNameToCopy + "')]/parent::tr//a[contains(text(),'Copy')]", "xpath", "copy for " + questionnaireNameToCopy);
		 
		 while (isAlertPresent()) {
				driver.switchTo().alert().accept();
			}
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Questionnaire List", "title", "false");
	  }
	 
	 
	 public void verifyRecreateQuestionnaire(String questionnaireName, String subSection) throws Exception
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 24th September 2020
		  */
		 
		searchQuestionnaire(questionnaireName, true, "firm defined"); 
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName);
	 
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" + subSection + "')]", "xpath", subSection, questionnaireName);
	  }
	
	
}