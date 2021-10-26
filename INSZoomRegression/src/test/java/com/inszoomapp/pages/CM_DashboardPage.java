package com.inszoomapp.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_DashboardPage extends LoadableComponent<CM_DashboardPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id = "MnProspects")			//Added by Kuchinad Shashank
	WebElement tab_ProspectiveClients;
	
	@FindBy(id = "txtAdvSearch")
	WebElement searchBox_advSearch;
	
	@FindBy(id = "MnTo-Do")
    WebElement tab_ToDo;
   
    @FindBy(xpath = "//span[contains(text(),'Prospects')]")
    WebElement tab_Prospects;
	
	//added By Saurav on 6thJune 2020
	
	@FindBy(xpath = "//span[contains(text(),'Reports 1.0')]")
	WebElement tab_Reports1; 
	
	@FindBy(xpath = "//span[contains(text(),'Billing')]")
	WebElement tab_Billing; 
	
	@FindBy(id = "MnReports")
	WebElement tab_Reports;
	
	@FindBy(xpath = "//a[contains(text(),'Current Month')]")
	WebElement tab_CurrentMonth;
	
	@FindBy(xpath = "//a[contains(text(),'Priority Dates')]")
	WebElement lnk_PriorityDates;	
	
	@FindBy(id = "MnSetupSettings")
	WebElement tab_Settings;
	
	@FindBy(css = "a[title='My Settings']")
	WebElement tab_MySettings;
	
	@FindBy(id = "MnSetupKB")
	WebElement tab_KnowledgeBase;
	
	@FindBy(xpath = "//span[contains(text(),'Appointments/Activities/Reminders')]")
	WebElement tab_AppointmentActivityReminder;
	
	@FindBy(id = "LM373")
    WebElement tab_CommunicationSummaryCase;
	
	@FindBy(id = "LM109")
    WebElement tab_CommunicationSummaryClient;
	
	@FindBy(css = "a[id='MnCases']")
	WebElement tab_Case;
	
	@FindBy(id = "MnClient")
	WebElement tab_Client;
	
	@FindBy(css = "a[title='Setup']")
	WebElement tab_Setup;
	
	@FindBy(css = "a[title='Portal Setup']")
	WebElement tab_PortalSetup;
	
	@FindBy(xpath = "//td[contains(text(), 'Phone Log List')]/ancestor::table/following-sibling::form//input[@onclick='javascript:CheckAll(this);']") // Added By Saksham Kapoor on 10/09/19
	WebElement chkbox_SelectAllPhoneLogs;
	
	@FindBy(xpath = "//td[contains(text(), 'Phone Log List')]/ancestor::table/following-sibling::form//input[@title='Delete']") // Added By Saksham Kapoor on 10/09/19
	WebElement icon_DeletePhoneLog;
	
	@FindBy(id="MnCalendar") // Added By Saksham Kapoor on 10/09/19
	WebElement icon_Calendar;
	
	@FindBy(xpath = "//span[contains(text(), 'Phone Log')]") // Added By Saksham Kapoor on 10/09/19
	WebElement tab_PhoneLog;
	
	@FindBy(id = "MnCorporation") // Added By Saksham Kapoor on 09/10/2019
	WebElement tab_Corporation;
	
	@FindBy(id = "FM_PROFILE")
	WebElement icon_Profile;

	@FindBy(linkText = "Logout")
	WebElement lnk_Logout;
	
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	
	public CM_DashboardPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	
    public void deletePhoneLogs(boolean screenshot) 
    {
         /*
          * Created By: Saksham Kapoor
          * Created On: 10/09/2019
          * Functionality : This function is a pre-requisite which deletes all the phone logs so that there are no notifications
          */
          
          clickPhoneLogs();
          
          Utils.waitForElement(driver, "IFrameList1", globalVariables.elementWaitTime, "id");
          driver.switchTo().frame("IFrameList1");
          
          if(!verifyIfPhoneLogsDeleted())
          {     
                Log.message("Phone Logs are present and need to be deleted.");
                
                clickSelectAllPhoneLogs();
                
                clickDeletePhoneLogsIcon();
                
                driver.navigate().refresh();
                
                if(verifyIfPhoneLogsDeleted())
                {
                      Log.pass("Phone logs deleted", driver, true);
                }
                
                else
                {
                      Log.fail("Not able to delete Phone logs", driver, true);
                }
          }
          
          else
          {
                Log.pass("Phone logs not present", driver, screenshot);
          }
          
          driver.switchTo().defaultContent();
    }
    
    
    public void clickPhoneLogs()
    {
         /*
          * Created By: Saksham Kapoor
          * Created On: 11/09/2019
          * Functionality : Function to click on Phone Logs from Left Menu
          * 
          */
   
        Utils.clickElement(driver, icon_Calendar, "Calender tab from left menu");
 
        Utils.clickElement(driver, tab_PhoneLog, "Phone Log option from left menu");
    }
    
    
    public void clickSelectAllPhoneLogs()
    {
         /*
          * Created By: Saksham Kapoor
          * Created On: 11/09/2019
          * Functionality : Function to click on the check-box to select all phone logs
          * 
          */
          
    	Utils.clickElement(driver, chkbox_SelectAllPhoneLogs, "the check-box to select all phone logs");
    
    }
    
    
    public void clickDeletePhoneLogsIcon()
    {
         /*
          * Created By: Saksham Kapoor
          * Created On: 11/09/2019
          * Functionality : Function to click Delete icon and accept if any alert pops-up
          * 
          */
          
	    	Utils.clickElement(driver, icon_DeletePhoneLog, "Delete Phone Log icon");
	      
	    	try {
	        driver.switchTo().alert().accept();
	        Log.message("Accepted the alert");
	
	    	} catch (Exception e) {
	    		Log.message("Alert not present");
	    	}
    }
    
    
    public boolean verifyIfPhoneLogsDeleted()
    {
         /*
          * Created By: Saksham Kapoor
          * Created On: 11/09/2019
          * Functionality : This function verifies if phone logs are present or not
          * 
          */
          
          if(Utils.isElementPresent(driver, "//td[contains(text(), 'Phone Log List')]/ancestor::table/following-sibling::form//div[contains(text(), 'No records to display')]", "xpath"))
          {
                return true;
          }
          
          else
          {
                return false;
          }
    }
    
    
	public CM_CorporationPage clickCorporationTab(boolean screenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/10/2019
		 */
		
		Utils.clickElement(driver, tab_Corporation, "Corporation option from the left panel");
		return new CM_CorporationPage(driver);
	}
	
	
	public void clickLogout(boolean screenshot) 
	{
		/*
		 * Modified By: Likitha Krishna
		 * Modified On: 19/08/2019
		 */

		try {
			Log.message("Clicking on Profile Icon");
			Utils.waitForElement(driver, icon_Profile);
			Utils.scrollIntoView(driver, icon_Profile);
			icon_Profile.click();
			Log.message("Clicked on Profile Icon");

		} catch (Exception e) {
			Log.message("", driver, screenshot);
			Log.message("Unable to click on Profile Icon. ERROR :\n\n " + e.getMessage());
		}


		try {
			Log.message("Clicking on Logout");
			Utils.waitForElement(driver, lnk_Logout);
			lnk_Logout.click();
			Log.message("Clicked on Logout");

		} catch (Exception e) {
			Log.message("", driver, screenshot);
			Log.message("Unable to click on Logout. ERROR :\n\n " + e.getMessage());
		}

	}
	
	
	public CM_ClientListPage clickClientTab(boolean screenshot) throws InterruptedException 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 10/10/2019
		 */
		
		Utils.clickElement(driver, tab_Client, "Client Button from the LEFT Navigation pane");
	   
		return new CM_ClientListPage(driver);
	}
	
	
	public CM_CaseListPage clickCaseTab(boolean screenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Added Log messages for better readability in reports.
		 * 
		 */
		
		Utils.clickElement(driver, tab_Case, "Case Tab from left menu");
	  
	    return new CM_CaseListPage(driver);
	}
	
	
	public void clickPortalSetup(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 9th Aug 2021
		 * Modifications: Added Action class for Hover and click on Portal Setup tab
		 * 
		 */
		
//		Utils.clickElement(driver, tab_Setup, "Setup Tab from left menu");
		
		Utils.waitForElement(driver, tab_Setup);
		
		Utils.waitForElementClickable(driver, tab_Setup);
		
		Actions action = new Actions(driver);
		
		action.moveToElement(tab_Setup).build().perform();
		
		Utils.clickElement(driver, tab_PortalSetup, "Portal Setup from left menu");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		
		Utils.switchWindows(driver, "Portal Setup - INSZoom.com", "title", "false");

	}
	
	
	public void clickCommunicationSummaryInClient(boolean screenshot)  
    {
          /*
          * Created By : M Likitha Krishna
          * Created On : 11 Oct 2019
          */ 
		Utils.clickElement(driver, tab_CommunicationSummaryClient, "Communication Summary in Client");
    }
      
	public void clickCommunicationSummaryInCase(boolean screenshot)  
    {
        /*
         * Created By : M Likitha Krishna
         * Created On : 14 Oct 2019
         */ 
		Utils.clickElement(driver, tab_CommunicationSummaryCase, "Communication Summary in Case");
    }
      
	public String fetchUserId()
    {
         /*
         * Created By : M Likitha Krishna
         * Created On : 15 Oct 2019
         */
		
//		Utils.waitForElement(driver, "//div[contains(text(),'Current Session')]/font[contains(text(),'(')]", globalVariables.elementWaitTime, "xapth");
		Utils.waitForElement(driver, "//div[contains(text(),'Current Session')]/font[contains(text(),'(')]", globalVariables.elementWaitTime, "xpath");
		String temp = driver.findElement(By.xpath("//div[contains(text(),'Current Session')]/font[contains(text(),'(')]")).getText();
		String[] tempArr = temp.split("[(]");
		String id = tempArr[1].substring(0, tempArr[1].length()-1).trim();
		return id;
    }


	public void clickCalendarAppointmentActivityReminder()
    {
		 /*
         * Created By : M Likitha Krishna
         * Created On : 16 Oct 2019
         */
   
        Utils.clickElement(driver, icon_Calendar, "Calender tab from left menu");
        Utils.clickElement(driver, tab_AppointmentActivityReminder, "Appointment/Activity/Reminder option from left menu");
    } 
	
	public void clickKnowledgeBase(boolean screenshot) throws Exception 
	{
		 /*
         * Created By : M Likitha Krishna
         * Created On : 21 Oct 2019
         */
		
		try 
		{
		    Utils.waitForElement(driver, tab_Setup);
			Utils.clickUsingAction(driver, tab_Setup);

			Utils.waitForElement(driver, tab_KnowledgeBase);
			tab_KnowledgeBase.click();
			
			Log.message("Clicked Knowledge base button in dashboard page");
		} catch (Exception e) {
			Log.fail("unable to click Knowledge base button in dashboard page. ERROR - " + e.getMessage(), driver, screenshot);
		}
	}
	
	
	public void clickMySettings()
    {
		 /*
         * Created By : M Likitha Krishna
         * Created On : 22 Oct 2019
         */
   
        Utils.clickElement(driver, icon_Profile, "Porfile icon");
        Utils.clickElement(driver, tab_MySettings, "My settings");
    } 

	
	public void clickSettings(boolean screenshot) throws Exception {
		 /*
         * Created By : M Likitha Krishna
         * Created On : 22 Oct 2019
         */
		try {
			Utils.waitForElement(driver, tab_Setup);
			Utils.clickUsingAction(driver, tab_Setup);

			Utils.waitForElement(driver, tab_Settings);
			tab_Settings.click();
			
			Log.message("Clicked settings button in dashboard page");
		} 
		catch (Exception e) {
			Log.fail("Unable to click settings button in dashboard page" + e.getMessage(), driver, screenshot);
		}
	}
	
	public void clickPriorityDates(boolean screenshot) throws Exception {
		/*
         * Created By : M Likitha Krishna
         * Created On : 22 Oct 2019
         */
   
        Utils.clickElement(driver, lnk_PriorityDates, "Priority Dates Link");
	}
	
	public void clickReportsTab(boolean screenshot) throws Exception {
		/*
         * Created By : M Likitha Krishna
         * Created On : 22 Oct 2019
         */
   
        Utils.clickElement(driver, tab_Reports, "Reports Tab");
	}

	
	public void clickInvoiceTab()
    {
		 /*
         * Created By : M Likitha Krishna
         * Created On : 02 Dec 2019
         */
   
        Utils.clickElement(driver, tab_Billing, "Billing tab");
    }  
	
	
	public void clickReportsTab() throws Exception
    {
         /*
         * Created By : Saksham Kapoor
         * Created On : 04 May 2020
         */
  
        Utils.clickElement(driver, tab_Reports, "reports tab");
        //Utils.clickElement(driver, tab_Reports3_0, "reports 3.0 tab");
       
        Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "Reports - INSZoom.com", "title", "false");
    }
	
	
	public void clickReports1(){
		 /*
         * Created By : Sauravp
         * Created On : 6th June 2020
         */
		try{
			Actions action = new Actions(driver);
			action.moveToElement(tab_Reports).build().perform();
		    Utils.clickElement(driver, tab_Reports1, "Reports 1.0");
		
		}catch(Exception e){
			Log.fail("Unable to click on " + "Reports 1.0" + ". ERROR :\n\n " + e.getMessage());
		}
	}
	
	
	  public void clickProspectTab()
	    {
	         /*
	         * Created By : M Likitha Krishna
	         * Created On : 19 Aug 2020
	         */
	  
	        Utils.clickElement(driver, tab_Prospects, "Prospects tab");
	    }
	   
	    public void clickToDo() throws Exception
	    {
	         /*
	         * Created By : M Likitha Krishna
	         * Created On : 21 Aug 2020
	         */
	        Utils.clickElement(driver, tab_ToDo, "ToDo tab");
	        Utils.waitForAllWindowsToLoad(2, driver);
	        Utils.switchWindows(driver, "INSZoom.com - To-Do", "title", "false");
	    }
	    
	    
	    public void searchCase(boolean screenshot, String caseId)
	    {
	        /*
	         * Created By: Nitisha Sinha
	         * Created On: 24/07/2020
	         */
	       
	        Utils.enterText(driver, searchBox_advSearch, caseId, caseId);
	        
	        if(Utils.isElementPresent(driver, "//div[@id='recentRecordsSearchType'][contains(text(),'No Records Found ')]", "xpath"))
	        
		     {
		         Log.fail("Unable to find desired case . Hence can not Proceed Further", driver, true);
		     }
	     
	        Utils.clickDynamicElement(driver, "//div[@id='results']//strong[contains(text(),'" + caseId + "')]", "xpath", caseId);
	    }
	        
	        
	    public void verifyProspectsTabPresent(boolean value)
		{
			/*
			 * Created by Kuchinad Shashank
			 * Created on 5/08/2020
			 */
			
			if(value)
			{
				Utils.verifyIfDataPresent(driver, "MnProspects", "id", "Prospective Client", "Left Main Menu");
			}
			else
			{
				Utils.verifyIfDataNotPresent(driver, "MnProspects", tab_Corporation, "id", "Prospective Client Tab", "Left Main Menu");
			}
		}
		
		
		public void clickProspectiveClientTab()
		{
			/*
			 * Created by Kuchinad Shashank
			 * Created on 5/08/2020
			 */
			
			Utils.clickElement(driver, tab_ProspectiveClients, "Prospective CLients tab");
		}
		
		
		public void verifyInvoiceTabPresent(boolean value)
		{
			/*
			 * Created by Kuchinad Shashank
			 * Created on 5/08/2020
			 */
			
			if(value)
			{
				Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Billing')]", "xpath", "Invoice Tab", "Left Main Menu");
			}
			else
			{
				Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Billing')]", tab_Corporation, "xpath", "Invoice Tab", "Left Main Menu");
			}
		}
		
		public void hideFirmBanner()
		{
			//Created by : Yatharth Pandya
			//Created on : 23rd Oct,2020
			
			Utils.waitForElement(driver, icon_Profile);
			Utils.scrollIntoView(driver, icon_Profile);
			Utils.clickElement(driver, icon_Profile, "Profile icon");
			if(Utils.isElementPresent(driver, "//a[contains(text(),'Hide Firm Banner')]", "xpath"))
				Utils.clickDynamicElement(driver, "//a[contains(text(),'Hide Firm Banner')]", "xpath", "Hide firm banner");
				Utils.waitForAllWindowsToLoad(1, driver);
			
		}
		
}
