package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
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

public class CM_CaseAppointmentsAndActivitiesPage extends LoadableComponent<CM_CaseAppointmentsAndActivitiesPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_GotoAppointmentActivityList")
	WebElement btn_GoToList;	
	
	@FindBy(id = "btn_SaveAppointmentActivity")
	WebElement btn_SaveEdittedAppointmentActivity;	
	
	@FindBy(id = "btn_EditCaseAppointmentActivityInfo")
	WebElement btn_EditAppointmentActivity;
	
	@FindBy(id = "btn_DeleteAppointmentActivity")
	WebElement btn_DeleteAppointmentActivity;
	
	@FindBy(id = "btn_SaveAppointmentActivityDetails")
	WebElement btn_SaveAppointmentActivityDetails;
	
	@FindBy(id = "btn_ClearallGroupMembers")
	WebElement btn_ClearAll;
	
	@FindBy(id = "cboApptStatus")
	WebElement dropDown_Status;
	
	@FindBy(id = "btn_SavenewAppointmentActivitycategory")
	WebElement btn_SaveCategory;
	
	@FindBy(css = "input[name='txtApptDesc1']")
	WebElement textBox_AppointmentActivityCategory;
	
	@FindBy(id = "appttype")
	WebElement dropDown_Category;
	
	@FindBy(id = "btn_AddAppointmentActivityCategory")
	WebElement lnk_AddAppointmentActivityLink;
	
	@FindBy(id = "apptdrnHr")
	WebElement dropDown_Duration;
	
	@FindBy(id = "txtApptStartDate")
	WebElement textBox_StartDate;
	
	@FindBy(id = "txtSubject")
	WebElement textBox_Subject;
	
	@FindBy(id = "txtLocation")
	WebElement textBox_Location;
	
	@FindBy(id = "txtDesc")
	WebElement textBox_Description;
	
	@FindBy(id = "btn_AddNewCaseAppointmentActivity")
	WebElement btn_AddAppointmentActivity;
	
	public CM_CaseAppointmentsAndActivitiesPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CaseAppointmentsAndActivitiesPage(WebDriver driver) {
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
			Log.fail("Case appointments and activities page did not open up. Site might be down.", driver, true);
		}
	}
	
	 public void clickAddAppointmentActivityButton(boolean screenshot)
	 {
		 /*
	         * Created By : M Likitha Krishna
	         * Created On : 15 Oct 2019
	         */
		 	Utils.clickElement(driver, btn_AddAppointmentActivity, "add appointment/activity button");
	 }
	
	
	public void addNewAppointmentActivity(String case_SubjectAppointmentActivity,String userId, String description,String location,String duration,String category,String status, boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 15 Oct 2019
 		 */
		clickAddAppointmentActivityButton(true);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com -Add Appointment/Activity for Case", "title", "false");
		Utils.enterText(driver, textBox_Description, description, "description");
		Utils.enterText(driver, textBox_Location, location, "Location");
		Utils.enterText(driver, textBox_Subject, case_SubjectAppointmentActivity, "Subject");	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String dateOutput = sdf.format(c.getTime());	
		Utils.enterText(driver, textBox_StartDate, dateOutput, "StartDate");
		Utils.selectOptionFromDropDownByVal(driver, duration, dropDown_Duration);
		Utils.clickElement(driver, lnk_AddAppointmentActivityLink, "add appointment/activity category link");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Appointment/Activity Type", "title", "false");
		Utils.enterText(driver, textBox_AppointmentActivityCategory, category, "added new category");
		Utils.clickElement(driver, btn_SaveCategory, "Save category");
		Utils.switchWindows(driver, "INSZoom.com -Add Appointment/Activity for Case", "title", "false");
		Utils.selectOptionFromDropDown(driver, category, dropDown_Category);
		Utils.selectOptionFromDropDownByVal(driver, status, dropDown_Status);
		Utils.clickElement(driver, btn_ClearAll, "Clear All members");
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+ userId +"')]/../td/input[@type='checkbox']", "xpath", "Added member");
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		Utils.clickElement(driver, btn_SaveAppointmentActivityDetails, "save button");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        if(tab.size() > 1)
        {
              if(Utils.isAlertPresent(driver))
                    driver.switchTo().alert().accept();
              else
                    Log.message("Tab is still present", driver, screenshot);
        }
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Appointment/Activity List", "title", "false");

		}

	 public void verifyAppointmentActivity(String description, boolean sceenshot) throws Exception 
	    {
		 ((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		    boolean status = true;
			Utils.waitForElement(driver, "//font[contains(text(), 'Description/Subject')]", globalVariables.elementWaitTime, "xpath");
			List<WebElement> descriptions = driver.findElements(By.xpath("//font[contains(text(), 'Description/Subject')]/.."));
			for(int i = 0; i < descriptions.size(); i++)
			{
				try {
					if (descriptions.get(i).getText()
							.contains(description)) 
					{
						Log.message("Verified appointment decription", driver, true);
						status = false;
						break;
					}
				} catch (Exception e) {
					Log.fail("Unable to fetch appointment" + e.getMessage(),driver,true);
				}
			}
			((JavascriptExecutor) driver).executeScript("scroll(0,-300);");
			if(status)
				Log.fail("Unable to find required appointment activity", driver, true);
	          
	    }
	 
	
	 public void selectAppointmentActivity(String appointmentActivityDescription,boolean screenshot) throws Exception 
	 {
		 /*
	         * Created By : M Likitha Krishna
	         * Created On : 15 Oct 2019
	         */
		boolean status = true;
		Utils.waitForElement(driver, "//font[contains(text(), 'Description/Subject')]", globalVariables.elementWaitTime, "xpath");
		List<WebElement> descriptions = driver.findElements(By.xpath("//font[contains(text(), 'Description/Subject')]/.."));
		for(int i = 0; i < descriptions.size(); i++)
		{
			try {
				if (descriptions.get(i).getText()
						.contains(appointmentActivityDescription)) {
					Utils.clickDynamicElement(driver,
							"(//font[@class='FormPgFontColor'])[" + (i + 1)
									+ "]/../../preceding-sibling::tr[1]/td/a",
							"xpath", "Desired Appointment Activity");
					status = false;
					break;
				}
			} catch (Exception e) {
				throw new Exception("Unable to fetch appointment description" + e.getMessage());
			}
		}
		if(status)
			Log.fail("Unable to select required appointment activity", driver, true);
			
	 }
	
	 
	 
	 public void clickDeleteAppointmentActivityButton(boolean screenshot) {
		 	/*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 15 Oct 2019
	 		 */
		 Utils.clickElement(driver, btn_DeleteAppointmentActivity, "delete appointment/activity button");
		 if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
		} else {
				System.out.println("alert was not present");
		}
	}
	
	
	 public void verifyIfAppointmentActivityNotPresent(String description, boolean sceenshot) throws Exception 
	    {
		 	/*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 15 Oct 2019
	 		 */
		 ((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		    boolean status = false;
			Utils.waitForElement(driver, "//table[@summary='Case Appointments/Activities List']", globalVariables.elementWaitTime, "xpath");
			List<WebElement> descriptions = driver.findElements(By.xpath("//font[contains(text(), 'Description/Subject:')]/.."));
			for(int i = 0; i < descriptions.size(); i++)
			{
				try {
					if (descriptions.get(i).getText().contains(description)) {
						status = true;
						Log.fail("Appointment/activies present hence not deleted", driver, true);
						break;
					}
				} catch (Exception e) {
					throw new Exception("Unable to fetch appointment" + e.getMessage());
				}
			}
			if(!status)
				Log.pass("Appointment/activies deleted successfully", driver, true);
			((JavascriptExecutor) driver).executeScript("scroll(0,-300);");
	          
	    }
	 
	 
	 
		 public void editAppointmentActivity(String editLocation, String editAppointmentActivityDescriptionCase, boolean screenshot)
					throws Exception {
			 	/*
		 		 * Created By : M Likitha Krishna
		 		 * Created On : 16 Oct 2019
		 		 */
			
			 Utils.clickElement(driver, btn_EditAppointmentActivity, "Edit Appointment Activity");
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Calendar Edit", "title", "false");
			 Utils.enterText(driver, textBox_Location, editLocation, "Edited location");
			 Utils.enterText(driver, textBox_Description, editAppointmentActivityDescriptionCase, "Edited description");
			 Utils.clickElement(driver, btn_SaveEdittedAppointmentActivity, "saved editted details button");
			
			 if (driver.getWindowHandles().size() > 1) 
			 {
				try {
					while (Utils.isAlertPresent(driver)) {
						driver.switchTo().alert().accept();
					}
				} catch (NoAlertPresentException e) {
					Log.fail("Unable to handle alerts");
				}
			}
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case Appointments/Activities", "title", "false");
		 }
		 
		 
		 public void verifyEdittedAppointmentActivity(String editLocation, String editAppointmentActivityDescriptionCase,boolean screenshot) throws Exception {
			 	/*
		 		 * Created By : M Likitha Krishna
		 		 * Created On : 16 Oct 2019
		 		 */
			
			 Utils.waitForElement(driver, "//th[contains(text(),'Location')]", globalVariables.elementWaitTime, "xpath");
			 Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Location')]/../td[contains(text(),'"+ editLocation +"')]", "xpath", editLocation, "editted location");
			 Utils.clickElement(driver, btn_GoToList, "Go to List");
			 verifyAppointmentActivity(editAppointmentActivityDescriptionCase, true);
		}
	
		 
		 public void verifyAppointmentActivityPage() 
			{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 3rd March 2020
				  */
			    try{
				 
				 Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Appointment/Activity List");
			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Case Appointment/Activity List page failed", driver);
			    }

		}
	
	
}