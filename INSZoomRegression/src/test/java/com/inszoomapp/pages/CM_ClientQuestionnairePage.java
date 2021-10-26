package com.inszoomapp.pages;

import java.util.List;

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

public class CM_ClientQuestionnairePage extends LoadableComponent<CM_ClientQuestionnairePage> {
	
	private final WebDriver driver;
	private boolean isPageLoaded;
	public int clientQuestionnaireCount;
	public int totalQuestionnaireCountBeforeDelte;
	public int questionnaireCountBefore;
	public int questionnaireCountAfterAddition;
	public boolean questCounter = true;
	
	@FindBy(xpath = "//div[@id='QuestionnaireSearchResults']//tbody[@role='rowgroup']") //Added by Souvik Ganguly dated 28/06/2021
	WebElement table_QuestionnaireSearchResults;
	
	@FindBy(xpath = "//button[@id='removebtn' and @disabled]")
	WebElement btn_RemoveDisabled;
	
	@FindBy(xpath = "//div[@class='pace-progress' and @data-progress-text='100%']")
	WebElement element_PaceDone;                                 //Added by Saurav Purohit on 15th june, 2021		
	
	@FindBy(xpath = "(//table[contains(@summary,'Questionnaire List For')])[1]")
	WebElement table_questionnaireList;							//Added by Saurav Purohit on 15th june, 2021		
	
	@FindBy(xpath = "//div[contains(text(),'Client Info')]")
	WebElement txt_headerClientProfile;							//Added by Yatharth Pandya on 23rd Sept, 2020
	
	@FindBy(xpath = "//h1[contains(text(),'Questionnaire')]")
	WebElement txt_headerQuestionnaireList; 						//Added by Yatharth on 22nd sept, 2020 
	
	@FindBy(css="input[placeholder='Enter Questionnaire Name']")
	WebElement txtbox_SearchQuestionnaire;
	
	@FindBy(id="removebtn")
	WebElement btn_Remove;
	
	@FindBy(id="selectall")
	WebElement checkbox_SelectAll;
	
	@FindBy(xpath="//button[contains(text(), 'Continue')]")
	WebElement btn_Continue;
	
	@FindBy(css="select[name='optQuestUtil']")
	WebElement dropdown_QuestionnaireActions;
	
	@FindBy(css="a[title='Go']")
	WebElement btn_Go;
	
	public CM_ClientQuestionnairePage(WebDriver driver)
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
		questionnaireCountBefore = checkAddedQuestionnaireCount();
		
		Utils.selectOptionFromDropDownByVal(driver, "AddOrRemoveQuest", dropdown_QuestionnaireActions);
		
		Utils.clickElement(driver, btn_Go, "Go Icon");
		
		Utils.clickElement(driver, btn_Continue, "Continue Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Search Questionnaire", "title", "false");
		

	}
	
	
	public void removeAllQuestionnaires()
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 15 Nov 2019
		 * 
		 */
		
		if(Utils.isElementPresent(driver, "div[class='k-grid-norecords']", "css"))
		{
			Log.pass("", driver, true);
			Log.pass("No Questionnaires Present");
		}
		
		else
		{
			Utils.clickElement(driver, checkbox_SelectAll, "Select All Checkbox");
			
			Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
			
			Utils.clickElement(driver, btn_Remove, "Remove");
			
			//Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
			Utils.waitUntillLoaderDisappearsInHRP(driver);
			
			Utils.waitForElement(driver, "div[class='k-grid-norecords']", globalVariables.elementWaitTime, "css");
		}
		
	}
	
	
	public void verifyIfQuestionnairesRemoved(String clientName) throws Exception
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 15 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "div[class='k-grid-norecords']", "css", "No records Found", "Questionnaire List");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Questionnaires for " + clientName, "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//table[contains(@summary,'Questionnaire List For " + clientName + "')]//td[@class='NoRecord']", "xpath", "No records Found", "Linked Questionnaire table");
	}
	
	
	public void addQuestionnaire(String questionnaire) throws InterruptedException
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Created On: 28 June 2021
		 * Modified By : Saurav
		 * Modified date : 3rd Aug 2021
		 * COmmented waitForLoadingCompleted method and added a new Method "waitForLoadingMaskToDisappearInQuestionnaireSearchWindow"
		 * to wait for Loading image to appear and disappear  
		 * 
		 */
	    Log.message("In Questionnaire Search window ");
		/*Utils.waitForElement(driver, table_QuestionnaireSearchResults);	
		Utils.waitForPageLoad(driver);*/
	    Utils.waitForLoadingMaskToDisappearInQuestionnaireSearchWindow(driver);
		if(Utils.isElementPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath"))
		{			
			questCounter = false ;
			Log.pass("", driver, true);
			Log.pass(questionnaire + " Questionnaire already Present");
		}
		
		else
		{
			
			Utils.waitForLoad(driver);
			
			List<WebElement> noOfQuestBefore = driver.findElements(By.xpath("//div[@id='QuestionnaireSearchResults']/table/tbody/tr"));
			
			Log.message("Total No Of Questionnaire before adding in Questionnaire Search window "+noOfQuestBefore.size());
			
			Utils.enterText(driver, txtbox_SearchQuestionnaire, questionnaire, "questionnaire search box");
			
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaire + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");
			
//			Utils.waitForLoadingCompleted(driver);
			Utils.waitForLoadingMaskToDisappearInQuestionnaireSearchWindow(driver);
			Utils.waitForElement(driver, table_QuestionnaireSearchResults);	
//			Utils.waitForPageLoad(driver);
						
			List<WebElement> noOfQuestAfterAdding = driver.findElements(By.xpath("//div[@id='QuestionnaireSearchResults']/table/tbody/tr"));
			
			Log.message("No Of Questionnaire After adding in Questionnaire Search window "+noOfQuestAfterAdding.size());
			
			if(noOfQuestAfterAdding.size()>noOfQuestBefore.size()){
				questCounter = true;
				Log.message("Questionnaire Added successfully in List Window ");
			}
			Log.message("Closing Questionnaire Serach window ");

			
		}
	}
	
	
	public void verifyIfQuestionnairesAdded(String questionnaire, String clientName) throws Exception
	{
		/*
         * Edited By: Souvik Ganguly
		 * Created On: 28 June 2021
		 * 
		 */
		try
		{
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Questionnaires for " + clientName, "title", "false");
		
		questionnaireCountAfterAddition = checkAddedQuestionnaireCount();
		
		if(questCounter == true)
		{
			if(questionnaireCountAfterAddition==questionnaireCountBefore+1){
				Log.pass("Questionnaire added Successfully on main Questionnaire window");
			}else{
				Log.fail("Issue with Questionnaire addition on main Questionnaire window ", driver, true);
			}
		}
		
		else if (questCounter == false)
		{
			Log.pass("No new Questionnaire was added as questionnaire already present");
		}
		
		else
		{
			Log.fail("Issue with Questionnaire addition ", driver, true);
		}
		}
		catch(Exception e)
		{
			Log.fail("Unable to verify questionnaires added . ERROR :\n\n " + e.getMessage(), driver, true);
		}
		

//		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Client Questionnaire Page");
	}
	
	
	public void verifyQuestionnairesPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 10 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Questionnaires for");
	    }catch(Exception e){
		 Log.failsoft("Verification of client Questionnaires page failed", driver);
	    }

}
	
	public void verifySearchQuestionnairePage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Feb 2020
		  */
	    try{
		 
	    	/*Utils.waitForAllWindowsToLoad(2, driver);
	    	Utils.switchWindows(driver, "Search Questionnaire", "title", "false");*/
	    	
	    	Utils.softVerifyPageTitle(driver, "Search Questionnaire");
	    	driver.close();
	    	Utils.waitForAllWindowsToLoad(1, driver);
	    	Utils.switchWindows(driver, "INSZoom.com - Questionnaires for", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Case Questionnaires List Page failed", driver);
	    }

	}
	
	 public void removeQuestionnaire(String questionnaire , String clientName) throws Exception
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
		 		Log.message(questionnaire + "is not prsent in client questionnaire list");
		 	
		    driver.close();
	        Utils.waitForAllWindowsToLoad(1, driver);
	        Utils.switchWindows(driver, "aty_bnf_quest_list.aspx", "url", "false");
	        CM_ClientListPage clientCreationPage = new CM_ClientListPage(driver);
	        clientCreationPage.clickQuestionnairesTab();
	        Utils.verifyIfDataNotPresent(driver, "//table[@summary='Questionnaire List For "+ clientName +"']//td/a[contains(text(),'"+ questionnaire +"')]", txt_headerClientProfile, "xpath", questionnaire, "Client questionnaire Page");
	        
		    }
	 
	 
	 public int checkClientQuestionnaireCount() throws Exception
	    {
	        // Created by : Saurav Purohit 
	        // Created on : 14/06/21
           //  Method details : this method will return the no of client questionnire attached

		
            try{
		 	
		 	List<WebElement> allrows  = driver.findElements(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr"));
		 	
		 	for(int i = 3; i<= allrows.size(); i=i+3){
		 		
		 	WebElement lastColumn = driver.findElement(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr["+i+"]/td[8]"));
		 	
		 	totalQuestionnaireCountBeforeDelte = totalQuestionnaireCountBeforeDelte+1;
		 		
		 	if(lastColumn.findElements(By.tagName("a")).size()==0){
		 		
		 		clientQuestionnaireCount=clientQuestionnaireCount+1;	
		 	}	
		 	
		 	}
		 	Log.message("No Of Total Questionnaire Before Delete are &&&&&&&&&&&&&&&&&"+totalQuestionnaireCountBeforeDelte);
            }catch(Exception e){
            	
            	Log.fail("Unable to verify no of client questionnaire attached . ERROR :\n\n " + e.getMessage(), driver, true);
            }
		 	return clientQuestionnaireCount;
	        
		    }
	 
	 
	 
	 public void removeClientQuestionnaire() throws Exception
	    {
	            /*Created by : Saurav Purohit 
	             * Created on : 14/06/21
	             * Method details : this method will return the no of client questionnaire attached */
		    
             try{
        	       clientQuestionnaireCount = checkClientQuestionnaireCount();
        	       
        	       Log.message("No of Client Questionnaire attached are &&&&&&&&&&&&&&&&&&"+clientQuestionnaireCount);
		 	
        	       if( clientQuestionnaireCount > 0){
        		 
        		   chooseAddRemoveQuestionnaires();
        		 
        		   clickRemoveButtoninQuestionnaireListWindow();
		 	
		 	    }
             }catch(Exception e){
         	
         	   Log.fail("Unable to remove questionnaire attached . ERROR :\n\n " + e.getMessage(), driver, true);
         	   
             }
	
		 }
	 
	 
	 
	 public void clickRemoveButtoninQuestionnaireListWindow() throws Exception
		{
			/*
			 * Created By: Saurav Purohit 
			 * Created On: 14/06/21
			 * Description : This method will check Select All check box and click on Remove switch to parent
			 * window from child window
			 * 
			 */
		        try{
//		        Utils.waitForElement(driver, element_PaceDone);
		 
				Utils.clickElement(driver, checkbox_SelectAll, "Select All Checkbox");
				
				Utils.waitForElementNotVisible(driver, "//button[@id='removebtn' and @disabled]", "xpath");
				
				Utils.clickElement(driver, btn_Remove, "Remove");
				
				Utils.waitUntillLoaderDisappearsInHRP(driver);
				
				Utils.waitForElement(driver, btn_RemoveDisabled);
				
				Utils.waitForElementPresent(driver, "//div[@class='pace-progress' and @data-progress-text='100%']", "xpath");
				
				driver.close();
				
				Utils.waitForAllWindowsToLoad(1, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Questionnaires for", "title", "false");
				
		        }catch(Exception e){
		        	e.printStackTrace();
		        }
					
		}
	 
	 
	 public void verifyAllClientQuestionnaireRemoved()
		{
			/*
			 * Created By: Saurav Purohit
			 * Created On: 14/06/21
			 * Description : This method will Verify client questionnaire is removed successfully .
			 */
		   int totalQuestionnaireAfterdelete=0;
		   
		   
		   try{
			 
		    Utils.waitForElement(driver, table_questionnaireList);
		    
		    List<WebElement> allrows  = driver.findElements(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr"));
		 	
		 	for(int i = 3; i<= allrows.size(); i=i+3){
		 		
		 	driver.findElement(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr["+i+"]/td[8]"));
		 	
		 	totalQuestionnaireAfterdelete = totalQuestionnaireAfterdelete+1;
		 	
		 	}
		 	System.out.println("&&&&&&&&&&&& Total Questionnaire After Delete are "+totalQuestionnaireAfterdelete);
		 	if(totalQuestionnaireCountBeforeDelte-totalQuestionnaireAfterdelete==clientQuestionnaireCount){
		 		Log.pass("Client Questionnaire is successfully Deleted ", driver);
		 	}else{
		 		Log.fail("Client Questionnaire Deletion is unsuccessful . ERROR :\n\n " , driver, true);
		 	}
		
		   }catch(Exception e){
			   Log.fail("Client Questionnaire Deletion is unsuccessful  textbox. ERROR :\n\n " + e.getMessage(), driver, true);
		   }
			
		}
	 
	 
	 
	 public int checkAddedQuestionnaireCount() throws Exception
	    {
	     // Created by : Saurav Purohit 
	     // Created on : 14/06/21
        //  Method details : this method will return the no of client questionnire attached
           
		 int noOfQuest = 0;
         try{
        	 
        	 Utils.waitForElement(driver, btn_Go);        	
		 	
		 	List<WebElement> allrows  = driver.findElements(By.xpath("(//table[contains(@summary,'Questionnaire List For')])[1]/tbody/tr"));
		 	
		 	if(allrows.size()==3)
		 	{
		 		Log.message("presently No Questionnaire are added ");
		 		return noOfQuest;
		 		}
		 		else
		 		{
		 		for(int i = 3; i<= allrows.size(); i=i+3){
		 		noOfQuest = noOfQuest+1;
		 		}
		 		}
		 	Log.message("presently total Questionnaires added are "+noOfQuest);
         }
         catch(Exception e){
         	
         	Log.fail("Unable to verify no of questionnaires attached . ERROR :\n\n " + e.getMessage(), driver, true);
         }
		 	return noOfQuest;
	        
		    }
	
	 public void verifyQuestionnaireInSearchWindow(String questionnaire, String clientName) throws Exception
	    {
	        /*
	         * Created By: Saurav Purohit
	         * Created On: 29 July 2021
	         * Method to verify a questionnaire in questionnaire search window
	         * 
	         */
	        try
	        {
	        Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "Questionnaire Search Page");
	        
	        driver.close();
	        
	        Utils.waitForAllWindowsToLoad(1, driver);
	        
	        Utils.switchWindows(driver, "INSZoom.com - Questionnaires for " + clientName, "title", "false");
	        }
	        catch(Exception e)
	        {
	            Log.fail("Unable to verify questionnaire in Questionnaire Serach Window . ERROR :\n\n " + e.getMessage(), driver, true);
	        }
	        

	    }

}
