package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_CalendarPage extends LoadableComponent<CM_CalendarPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//span[contains(text(),'Messages and Calls')]")
	WebElement tab_MassagesAndCalls ;

	
	@FindBy(xpath = "//img[@alt='Group Appointment/Activity']")
	WebElement icon_AppointmentActivity ;
	
	@FindBy(id = "LM729")
	WebElement tab_Task; 

	public CM_CalendarPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CalendarPage(WebDriver driver) {
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
			Log.fail("Cakendar Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void verifyPhoneLogPresent(String message)
	{
		/*
         * Created By: Saksham Kapoor
         * Created On: 10/09/2019
         */
		
		Utils.waitForElement(driver, "IFrameList1", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("IFrameList1");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + message + "')]", "xpath", message, "Phone Logs in Calendar Page");
		
		driver.switchTo().defaultContent();
	}
	
	
	 public void verifyClientAppointmentActivity(String description, boolean sceenshot) throws Exception 
	 {
		 	/*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 16 Oct 2019
	 		 */
		 ((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		 boolean status = true;
		 Utils.waitForElement(driver, icon_AppointmentActivity);
		 List<WebElement> descriptions = driver.findElements(By.xpath("//img[@alt='Group Appointment/Activity']/.."));
		 for(int i = 0; i < descriptions.size(); i++)
		 {
				try {
					if (descriptions.get(i).getText()
							.contains(description)) 
					{
						Log.message("Verified appointment decription in calendar", driver, true);
						status = false;
						break;
					}
				} catch (Exception e) {
					Log.fail("Unable to fetch appointment decription in calendar" + e.getMessage(),driver,true);
				}
			}
			((JavascriptExecutor) driver).executeScript("scroll(0,-300);");
			if(status)
				Log.fail("Unable to find required appointment activity", driver, true);
	          
	    }
	 
	
	 public void verifyCaseAppointmentPresent(String description, boolean sceenshot) throws Exception 
	    {
		 	/*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 16 Oct 2019
	 		 */
		 ((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		 boolean status = true;
		 Utils.waitForElement(driver, icon_AppointmentActivity);
		 List<WebElement> descriptions = driver.findElements(By.xpath("//img[@alt='Group Appointment/Activity']/.."));
		 for(int i = 0; i < descriptions.size(); i++)
		 {
			 try {
				 if (descriptions.get(i).getText().contains(description)) 
				 {
					 Log.message("Verified appointment decription in calendar", driver, true);
					 status = false;
					 break;
				 }
			 } catch (Exception e) {
				 Log.fail("Unable to fetch appointment decription in calendar" + e.getMessage(),driver,true);
			 }
		 }
		 ((JavascriptExecutor) driver).executeScript("scroll(0,-300);");
		 if(status)
			 Log.fail("Unable to find required appointment activity", driver, true);
	          
	}
	 
	 
	 public void clickTaskTab() 
	 {
		 /*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 16 Oct 2019
	 		 */
		 Utils.clickElement(driver, tab_Task, "Task tab");
	}

	 public void verifyCaseTasksPresent(String taskMessage, boolean screenshot)throws Exception 
		{
			/*
			 * Created By : M Likitha Krishna
			 * Created On : 16 Oct 2019
			*/ 
			Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" +taskMessage+ "')]", "xpath", taskMessage, "present in calendar");
		}
	 
	 public void verifyClientTasksPresent(String taskMessage, boolean screenshot)throws Exception 
	 {
			/*
			 * Created By : M Likitha Krishna
			 * Created On : 16 Oct 2019
			*/ 
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" +taskMessage+ "')]", "xpath", taskMessage, "present in calendar");
	}

	 
	 public void verifyCalendarPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4thMarch 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Reminders");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Case Reminders page failed", driver);
		    }

	}
	 
	 
	 public void verifyCalendarTaskListPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4thMarch 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "List");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Calendar List page failed", driver);
		    }

	}

	 
	 public void clickMessagesAndCallsTab() 
	 {
		 /*
	 		 * Created By : Saurav Purohit
	 		 * Created On : 4thMarch 2020
	 		 */
		 Utils.clickElement(driver, tab_MassagesAndCalls, "Messages And Calls tab");
	}
	 
	 public void verifyMessagesAndCallsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4thMarch 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Messages and Calls");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Messages and Calls page failed", driver);
		    }

	}

}
