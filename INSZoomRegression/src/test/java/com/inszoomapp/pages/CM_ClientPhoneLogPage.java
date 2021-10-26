package com.inszoomapp.pages;

import java.util.ArrayList;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_ClientPhoneLogPage extends LoadableComponent<CM_ClientPhoneLogPage>
{
	ReadWriteExcel readExcel = new ReadWriteExcel();

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(css = "table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr")
	WebElement tbl_PhoneLogs;
	
	@FindBy(css = "input[title='Delete']")
	WebElement icon_Delete;
	
	@FindBy(id="btn_EditPhoneLogDetails")
	WebElement btn_EditPhoneLog;
	
	@FindBy(css = "input[value='Print Preview']")
	WebElement btn_PrintPreview;
	
	@FindBy(css = "input[title='Dismiss Phone Message']")
	WebElement btn_DismissPhoneLogMessage;
	
	@FindBy(css = "input[id='btn_Savephonelogdetails']")
	WebElement btn_SavePhoneLog;
	
	@FindBy(id = "UserCntrl_UserCntrl_btnAddAll")
	WebElement btn_AddAll;
	
	@FindBy(id = "UserCntrl_UserCntrl_btnAdd")
	WebElement btn_Add;
	
	@FindBy(css = "input[value='Add']")
	WebElement btn_AddPhoneLog;

	@FindBy(css = "textarea[name='txtdesc']")
	WebElement txtbox_PhoneLogMessage;

	@FindBy(css = "input[name='chkPhoneAction1']")
	WebElement chckbox_PhoneAction1;

	@FindBy(css = "input[name='chkPhoneAction2']")
	WebElement chckbox_PhoneAction2;

	@FindBy(css = "input[name='chkPhoneAction3']")
	WebElement chckbox_PhoneAction3;

	@FindBy(css = "input[name='chkPhoneAction4']")
	WebElement chckbox_PhoneAction4;

	@FindBy(css = "input[name='chkPhoneAction5']")
	WebElement chckbox_PhoneAction5;

	@FindBy(css = "input[name='chkPhoneAction6']")
	WebElement chckbox_PhoneAction6;


	public CM_ClientPhoneLogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Client phone log page didnt loaded");
		}

	}

	
	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void addPhoneLogForClient(String phoneLogAddMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
		Utils.clickElement(driver, btn_AddPhoneLog, "Add Button");

		CM_DashboardPage caseManagerDashboardPage = new CM_DashboardPage(driver);
		String userId = caseManagerDashboardPage.fetchUserId();
		
		Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com :: Add New Phone Log Entry", "title", "false");

		Utils.enterText(driver, txtbox_PhoneLogMessage, phoneLogAddMessage, "Phone Log Message");
		
		Utils.clickElement(driver, chckbox_PhoneAction1, "Phone Action 1");
		Utils.clickElement(driver, chckbox_PhoneAction2, "Phone Action 2");
		Utils.clickElement(driver, chckbox_PhoneAction3, "Phone Action 3");
		Utils.clickElement(driver, chckbox_PhoneAction4, "Phone Action 4");
		Utils.clickElement(driver, chckbox_PhoneAction5, "Phone Action 5");
		Utils.clickElement(driver, chckbox_PhoneAction6, "Phone Action 6");
		
		Utils.clickDynamicElement(driver, "//option[contains(text(), '" + userId + "')]", "xpath", userId);
		
		Utils.clickElement(driver, btn_Add, "Add Button");

		Utils.clickElement(driver, btn_SavePhoneLog, "Save Button");

		/*
		 * An extra window with no title pops-up unexpectedly.
		 * So we are switching windows in the basic manner.
		 * 
		 */
//		Utils.waitForAllWindowsToLoad(3, driver);
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(2));
//
//		Utils.waitForElement(driver, "//td[contains(text(), '" + phoneLogAddMessage + "')]", globalVariables.elementWaitTime, "xpath");
//		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");
			
	}
	
	
	public void verifyPhoneLog(String phoneLogMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
//		Utils.waitForAllWindowsToLoad(2, driver);
//		Utils.switchWindows(driver, "INSZoom.com :: Notify New Phone Message", "title", "false");
//		
//		Utils.waitForElement(driver, "//td[contains(text(), '" + phoneLogMessage + "')]", globalVariables.elementWaitTime, "xpath");
//		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");
//		
//		Utils.waitForAllWindowsToLoad(1, driver);
//		Utils.switchWindows(driver, "List", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "table[class='rgMasterTable rgClipCells rgClipCells'][id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr", "css", phoneLogMessage, "Phone Logs");
	}
	
	
	public void editPhoneLog(String phoneLogMessage, String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */

		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + phoneLogMessage + "')]/../..//img[@title = 'Edit']", "xpath", "Edit Icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Phone Log", "title", "false");
		
		Utils.enterText(driver, txtbox_PhoneLogMessage, edittedMessage, "New Message");
		
		Utils.clickElement(driver, btn_SavePhoneLog, "Save Button");
		
		/*
		 * An extra window with no title pops-up unexpectedly.
		 * So we are switching windows in the basic manner.
		 * 
		 */
//		Utils.waitForAllWindowsToLoad(3, driver);
//		ArrayList <String> tabs = new ArrayList <String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(2));
//		
//		Utils.waitForElement(driver, "//td[contains(text(), '" + edittedMessage + "')]", globalVariables.elementWaitTime, "xpath");
//		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");
	}
	
	
	public void selectPhoneLog(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.clickDynamicElement(driver, "//table[@class='rgMasterTable rgClipCells rgClipCells'][@id='ctl00_MainContent_ctl00_RadGridList_ctl00']/tbody/tr/td/a[contains(text(), '" + edittedMessage + "')]", "xpath", "Editted Phone Log Message");
	}
	
	
	public void verifyPrintPreview(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.clickElement(driver, btn_PrintPreview, "Print Preview Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
        Utils.switchWindows(driver, "INSZoom.com - Print Preview of Phone Log", "title", "false");
    
        Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + edittedMessage + "')]", "xpath", edittedMessage, "Print Preview");
			
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Phone Log", "title", "false");
	}
	
	
	public void verifyPhoneLogDetails(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
        Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + edittedMessage + "')]", "xpath", edittedMessage, "Phone Log Description");
	}
	
	
	public void lockPhoneLog(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + edittedMessage + "')]/../..//img[@title = 'lock']", "xpath", "Edit Icon");

		if (Utils.isAlertPresent(driver)) {
			driver.switchTo().alert().accept();
		} 
		else 
		{
			System.out.println("alert was not present");
		}
	}
	
	
	public void editLockedPhoneLog(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */

		String alertMessage = "";
		
		Utils.clickElement(driver, btn_EditPhoneLog, "Edit Button");
		
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		wait.until(ExpectedConditions.alertIsPresent());

			if (Utils.isAlertPresent(driver)) {
				alertMessage = driver.switchTo().alert().getText();
				Log.message("Fetched message from alert as: " + alertMessage);
				if (alertMessage.contains("This phone message is locked")) 
				{
					Log.pass("Successfully verified the locked phone log message");
				} 
				
				else 
				{
					Log.fail("We are able to edit phone logs as alert is not present");
				}
				driver.switchTo().alert().accept();
			} 
			else 
			{
				Log.fail("Alert is not present");
			}
			
		driver.switchTo().defaultContent();
	}
	
	
	public void selectPhoneLogCheckbox(String message, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + message + "')]/../..//input[@class='checkbox']", "xpath", "Edit Icon");
	}

	
	public void clickDeleteIcon(boolean screenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
	    Utils.clickElement(driver, icon_Delete, "Delete Icon");
	
		if (Utils.isAlertPresent(driver)) 
		{
			driver.switchTo().alert().accept();
		}
	}
	
	
	public void verifyDeletedPhoneLog(String message)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//td[contains(text(), '" + message + "')]", tbl_PhoneLogs, "xpath", message, "Phone Log Description");
	}

	
	public void verifyPhoneLogPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 10 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "List");
	    }catch(Exception e){
		 Log.failsoft("Verification of Phone Logs List page failed", driver);
	    }

}
}
