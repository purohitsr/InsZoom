package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
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

public class CM_CaseTaskPage extends LoadableComponent<CM_CaseTaskPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_PrintPreview")
	WebElement btn_PrintPreview;
	
	@FindBy(id = "btn_DeleteTask")
	WebElement btn_DeleteTask;
	
	@FindBy(id = "btn_SaveTaskDetails")
	WebElement btn_SaveEdittedTask;
	
	@FindBy(id = "btn_EditTask")
	WebElement btn_EditTask;
	
	@FindBy(id = "btn_Savenewtask")
	WebElement btn_SaveTask;
	
	@FindBy(xpath = "//button[@id='UserCntrl_UserCntrl_btnAdd']")
	WebElement btn_AddMember;
	
	@FindBy(id = "txtTaskDate")
	WebElement textBox_TaskDate;
	
	@FindBy(id = "txtdescription")
	WebElement textBox_Description;
	
	@FindBy(id = "cboTaskType")
	WebElement dropDown_TaskType;
	
	
	@FindBy(id = "btn_AddNewTask")
	WebElement btn_AddTask ;
	
	public CM_CaseTaskPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CaseTaskPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
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

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Case Task Page did not open up. Site might be down.", driver, true);
		}
		
	}
	
	
	public void addNewTask(String taskTypeSelect, String taskDetailsText, String userId,boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		
		Utils.clickElement(driver, btn_AddTask, "add task button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Task To Do", "title", "false");
		Utils.selectOptionFromDropDownByVal(driver, taskTypeSelect, dropDown_TaskType);
		Utils.enterText(driver, textBox_Description, taskDetailsText, "description");
		SimpleDateFormat sdfSecond = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		calendar.add(Calendar.DATE, 365); // Adding 120 days
		String taskDateCurrent = sdfSecond.format(calendar.getTime());
		Utils.enterText(driver, textBox_TaskDate, taskDateCurrent, "task date");
		Utils.clickDynamicElement(driver, "//option[contains(text(),'"+ userId +"')]", "xpath", "member");
		Utils.clickElement(driver, btn_AddMember, "add member button");
		Utils.clickElement(driver, btn_SaveTask, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Tasks List", "title", "false");

	}
	
	
	public void verifyTaskAdded(String taskDetailsText, boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" +taskDetailsText+ "')]", "xpath", taskDetailsText, "task details");
	}

	
	public void selectTask(String taskDetailsText, boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		
		Utils.waitForElementPresent(driver, "btn_AddNewTask", "id");
		Utils.scrollToElement(driver, btn_AddTask);
		Utils.clickDynamicElement(driver, "//a[contains(text(),'" +taskDetailsText+ "')]", "xpath", "Task");
	}
	
	public void clickEditTask(){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		Utils.clickElement(driver, btn_EditTask, "Edit task button");
	}
	
	
	public void editTaskDetails(String editTextDetailsText, boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		
		clickEditTask();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Task To Do", "title", "false");
		Utils.enterText(driver, textBox_Description, editTextDetailsText, "editted description");
		Utils.clickElement(driver, btn_SaveEdittedTask, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -", "title", "false");
	}
	
	
	public void verifyTaskDetails(String taskDetails, Boolean screenShot)
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ taskDetails +"')]", "xpath", taskDetails, "task details");
	}
	
	public void deleteTask(String taskDetails, Boolean screenShot)
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		Utils.clickElement(driver, btn_DeleteTask, "Delete button");
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		else
			Log.message("No alert present");	
	}
	
	
	
	public void verifyTaskDeleted(String taskDetails, Boolean screenShot)
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'" +taskDetails+ "')]", btn_AddTask, "xpath", taskDetails, "task details");	
	}
	
	
	
	public void verifyPrintPreviewDetails(String taskDetailsText, boolean screenshot) throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 16 Oct 2019
 		 */
		
		
		Utils.clickElement(driver, btn_PrintPreview, "Print preview");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver,  "INSZoom.com - Print Preview Of Tasks", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'" +taskDetailsText+ "')]", "xpath", taskDetailsText, "print preview");
		
		Utils.switchWindows(driver, "aty_task_view.aspx", "url", "false");
	}
	
	
	public void verifyTasksListPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - Tasks List");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Tasks List page failed", driver);
	    }

}
	
	
}