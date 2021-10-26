package com.inszoomapp.pages;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;


public class CM_ClientAppointmentsAndActivitiesPage extends LoadableComponent<CM_ClientAppointmentsAndActivitiesPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "txtComments")
	WebElement textBox_Comments ;
	
	@FindBy(id = "btn_DeleteAppointmentActivity")
	WebElement btn_DeleteAppointmentActivity ;
	
	@FindBy(id = "btn_SaveAppointmentActivity")
	WebElement btn_SaveEdittedAppointmentActivity ;
	
	@FindBy(id = "btn_EditAppointmentActivity")
	WebElement btn_EditAppointmentActivity ;
	
	@FindBy(id = "btn_SaveAppointmentActivitydetails")
	WebElement btn_SaveAppointmentActivity ;
	
	@FindBy(id = "cboApptStatus")
	WebElement dropDown_Status;
	
	@FindBy(id = "appttype")
	WebElement dropDown_Category;
	
	@FindBy(id = "btn_SavenewAppointmentActivitycategory")
	WebElement btn_SaveNewAppointmentActivityCategory;
	
	@FindBy(css = "input[name='txtApptDesc1']")
	WebElement textBox_Category;
	
	@FindBy(id = "btn_AddAppointmentActivityCategory")
	WebElement lnk_AddCategory;
	
	@FindBy(id = "txtApptStartDate")
	WebElement textBox_StartDate;
	
	@FindBy(id = "txtSubject")
	WebElement textBox_Subject;
	
	@FindBy(id = "txtLocation")
	WebElement textBox_Location;
	
	
	@FindBy(id = "txtDesc")
	WebElement textBox_Description;

	@FindBy(id = "btn_AddNewAppointmentActivity")
	WebElement btn_AppointmentActivity;
	
	public CM_ClientAppointmentsAndActivitiesPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_ClientAppointmentsAndActivitiesPage(WebDriver driver) {
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
			Log.fail("Client appointment and activities Page did not open up. Site might be down.", driver, true);
		}
	}
	
	public void clickAddNewAppointmentActivityButton(boolean sceenshot) 
    {
		 /*
         * Created By : M Likitha Krishna
         * Created On : 15 Oct 2019
         * 
         */
		 
		Utils.clickElement(driver, btn_AppointmentActivity, "Appointment/Activity tab");
          
    }
	
	
	public void addNewAppointmentsActivities(String appointmentActivityDescription,String appointmentActivityLocation, String appointmentActivitySubject,String appointmentActivityCategory,String memberId, String comments) throws Exception 
    {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		 * Modified By : Saurav
		 * Modified Date : 23/06/21
		*/ 
          
          clickAddNewAppointmentActivityButton(true);
          Utils.waitForAllWindowsToLoad(2, driver);
          Utils.switchWindows(driver, "INSZoom.com - Add Appointment/Activity for Client", "title", "false");
          Utils.enterText(driver, textBox_Description, appointmentActivityDescription, "Description");     
          Utils.enterText(driver, textBox_Location, appointmentActivityLocation, "Location");     
          Utils.enterText(driver, textBox_Subject, appointmentActivitySubject, "Subject");
//          Date tomorrowDate = null;
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          Calendar c = Calendar.getInstance();
          c.add(Calendar.DATE, 1);
//          tomorrowDate = c.getTime();
//          c.setTime(new Date()); // Now use today date.

          //String fromDate = "";
          globalVariables.fromDate = sdf.format(c.getTime());
          Utils.enterText(driver, textBox_StartDate, globalVariables.fromDate, "Start Date");
           
          Utils.clickElement(driver, lnk_AddCategory, "Category");
          Utils.waitForAllWindowsToLoad(3, driver);
          Utils.switchWindows(driver, "INSZoom.com - Appointment/Activity Type", "title", "false");
          
          Utils.enterText(driver, textBox_Category, appointmentActivityCategory, "Add new category");
          Utils.clickElement(driver, btn_SaveNewAppointmentActivityCategory, "save category");    

          Utils.waitForAllWindowsToLoad(2, driver);
          Utils.switchWindows(driver, "INSZoom.com - Add Appointment/Activity for Client", "title", "false"); 

          Utils.selectOptionFromDropDown(driver, appointmentActivityCategory, dropDown_Category);
                
          Utils.selectOptionFromDropDownByVal(driver, "p", dropDown_Status);    

          Utils.waitForElement(driver, "//td[contains(text(),'"+ memberId +"')]/../td/input[@class='CheckBox']", globalVariables.elementWaitTime, "xpath");
          WebElement checkBox_MemberName = driver.findElement(By.xpath("//td[contains(text(),'"+ memberId +"')]/../td/input[@class='CheckBox']"));
          Utils.clickElement(driver, checkBox_MemberName, "checkbox to choose member name");  

          Utils.enterText(driver, textBox_Comments, comments, "comments box");
          
          Utils.clickElement(driver, btn_SaveAppointmentActivity, "saved appointment/activity"); 
          ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
          if(tab.size() > 1)
          {
                if(Utils.isAlertPresent(driver))
                      driver.switchTo().alert().accept();
                else
                      Log.message("Tab is still present", driver, true);
          }
                                      
                Utils.waitForAllWindowsToLoad(1, driver);
                Utils.switchWindows(driver, "bnf_appt_list.aspx", "url", "false");
     }									

	
	public void verifyAppointmentActivity(String description, boolean sceenshot) throws Exception 
    {
	    boolean status = true;
		Utils.waitForElement(driver, "//font[contains(text(), 'Description/Subject:')]", globalVariables.elementWaitTime, "xpath");
		List<WebElement> descriptions = driver.findElements(By.xpath("//font[contains(text(), 'Description/Subject:')]/.."));
		for(int i = 0; i < descriptions.size(); i++)
		{
			try {
				if (descriptions.get(i).getText().contains(description)) 
				{
					Log.pass("Appointment/Activity Verified", driver, true);
					status = false;
					break;
				}
			} catch (Exception e) {
				Log.fail("Unable to fetch appointment. ERROR - " + e.getMessage());
			}
		}
		if(status)
			Log.fail("Unable to find required appointment activity", driver, true);
          
    }
	 
	 
	 public void selectAppointmentActivity(String client_AppointmentActivityDescription,boolean screenshot) throws Exception 
	 {
		 /*
	         * Created By : M Likitha Krishna
	         * Created On : 15 Oct 2019
	         */
		boolean status = true;
		Utils.waitForElement(driver, "//font[contains(text(), 'Description/Subject:')]", globalVariables.elementWaitTime, "xpath");
		List<WebElement> descriptions = driver.findElements(By.xpath("//font[contains(text(), 'Description/Subject:')]/.."));
		for(int i = 0; i < descriptions.size(); i++)
		{
			try {
				if (descriptions.get(i).getText()
						.contains(client_AppointmentActivityDescription)) {
					Utils.clickDynamicElement(driver,
							"(//font[@class='FormPgFontColor'])[" + (i + 1)
									+ "]/../../preceding-sibling::tr[1]/td/a",
							"xpath", "Desired Appointment Activity");
					status = false;
					break;
				}
			} catch (Exception e) {
				throw new Exception("Unable to edit appointment to the client" + e.getMessage());
			}
		}
		if(status)
			Log.fail("Unable to select required appointment activity", driver, true);
			
	 }
	 
	 public void clickEditAppointmentActivityBtn(boolean screenshot)
	 {
		 /*
	         * Created By : M Likitha Krishna
	         * Created On : 15 Oct 2019
	         */
		 	Utils.clickElement(driver, btn_EditAppointmentActivity, "edit appointment/activity button");
	 }
	 
	 
	 public void editAppointmentActivityLocation(String edittedLocation, boolean screenshot) throws Exception {
		 		/*
		 		 * Created By : M Likitha Krishna
		 		 * Created On : 15 Oct 2019
		 		 */
		 		clickEditAppointmentActivityBtn(true);
				Utils.waitForAllWindowsToLoad(2, driver);
				Utils.switchWindows(driver, "INSZoom.com - Edit Appointment/Activity Details", "title", "false");
				Utils.enterText(driver, textBox_Location, edittedLocation, "Editted location");
				Utils.clickElement(driver, btn_SaveEdittedAppointmentActivity, "Save button after edit");
					if (Utils.isAlertPresent(driver)) {
						driver.switchTo().alert().accept();
					} else {
						System.out.println("alert was not present");
					}
				Utils.waitForAllWindowsToLoad(1, driver);
				Utils.switchWindows(driver, "INSZoom.com - View Appointment/Activity Details", "title", "false");
			}
		


	 public void verifyAppointmentActivityLocation(String location, boolean screenshot) {
		 /*
	 		 * Created By : M Likitha Krishna
	 		 * Created On : 15 Oct 2019
	 		 */
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ location +"')]", "xpath", location, "Location field");
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
		    boolean status = false;
			Utils.waitForElement(driver, "//table[@summary='Client Appointments/Activities List']", globalVariables.elementWaitTime, "xpath");
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
	          
	    }
	 
	 
	 
	 public void verifyPhoneLogPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 10 Feb 2020
			  */
		    try{
			 
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Appointments/Activities List For");
		    }catch(Exception e){
			 Log.failsoft("Verification of INSZoom.com - Appointments/Activities List page failed", driver);
		    }

	}
	 
	 
	 

}