package com.inszoomapp.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
import com.support.files.Utils;

public class CM_CasePhoneLogPage extends LoadableComponent<CM_CasePhoneLogPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//td[@id='CasePhoneLog']")
	WebElement section_PhoneLog;
	
	@FindBy(css="table[summary='Phone Messages List']")
	WebElement tbl_PhoneLogs;
	
	@FindBy(css="input[onclick='DeleteAllLogs()']")
	WebElement btn_DeleteFromAllCaseManagers;
	
	@FindBy(id="btn_Deleterecords")
	WebElement btn_Delete;
	
	@FindBy(id="btn_PreviewandPrintPhoneLogDetails")
	WebElement btn_PrintPreview;
	
	@FindBy(id="btn_SavePhoneLogDetails")
	WebElement btn_SaveEdittedPhoneLog;
	
	@FindBy(css = "input[id='btn_Savephonelogdetails']")
	WebElement btn_SavePhoneLog;
	
	@FindBy(id = "UserCntrl_UserCntrl_btnAddAll")
	WebElement btn_AddAll;
	
	@FindBy(id = "UserCntrl_UserCntrl_btnAdd")
	WebElement btn_Add;
	
	@FindBy(css = "input[title='Dismiss Phone Message']")
	WebElement btn_DismissPhoneLogMessage;
	
	@FindBy(id="btn_Addnewphonemessage")
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


	public CM_CasePhoneLogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Case phone log page didnt loaded");
		}

	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void addPhoneLogForCase(String case_PhoneLogMessage, boolean screenshot)throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */

		CM_DashboardPage caseManagerDashboardPage = new CM_DashboardPage(driver);
		String userId = caseManagerDashboardPage.fetchUserId();
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1);
		
		Utils.clickElement(driver, btn_AddPhoneLog, "Add Phone Logs");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Phone Log Entry", "title", "false");
		
		Utils.enterText(driver, txtbox_PhoneLogMessage, case_PhoneLogMessage, "Phone Log Description");

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
		Utils.waitForAllWindowsToLoad(3, driver);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));

		Utils.waitForElement(driver, "//td[contains(text(), '" + case_PhoneLogMessage + "')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		driver.switchTo().defaultContent();
	}
	
	
	public void verifyPhoneLog(String phoneLogMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1);
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + phoneLogMessage + "')]", "xpath", phoneLogMessage, "Phone Logs");
		
		driver.switchTo().defaultContent();
	}
	
	
	public void editPhoneLog(String phoneLogMessage, String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */

		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1);
		driver.findElements(By.xpath("")).size();
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + phoneLogMessage + "')]/../..//img[@title = 'Edit']", "xpath", "Edit Icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Phone Log", "title", "false");
		
		Utils.enterText(driver, txtbox_PhoneLogMessage, edittedMessage, "New Message");
		
		Utils.clickElement(driver, btn_SaveEdittedPhoneLog, "Save Button");
		
		/*
		 * An extra window with no title pops-up unexpectedly.
		 * So we are switching windows in the basic manner.
		 * 
		 */
		Utils.waitForAllWindowsToLoad(3, driver);
		ArrayList <String> tabs = new ArrayList <String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		
		Utils.waitForElement(driver, "//td[contains(text(), '" + edittedMessage + "')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickElement(driver, btn_DismissPhoneLogMessage, "Dismiss Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
	}
	
	
	public void selectPhoneLog(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1);
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + edittedMessage + "')]", "xpath", "Editted Phone Log Message");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Phone Caller Details", "title", "false");
	}
	
	
	public void verifyPrintPreview(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */
		
		Utils.clickElement(driver, btn_PrintPreview, "Print Preview Button");
		
		Utils.waitForAllWindowsToLoad(3, driver);
        Utils.switchWindows(driver, "INSZoom.com - Print Preview of Phone Caller Details", "title", "false");
    
        Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + edittedMessage + "')]", "xpath", edittedMessage, "Print Preview");
			
		driver.close();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Phone Caller Details", "title", "false");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
	}
	
	
	public void verifyPhoneLogDetails(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
        Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + edittedMessage + "')]", "xpath", edittedMessage, "Phone Log Description");
        
        driver.close();
        Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
	}

	
	public void lockPhoneLog(String edittedMessage, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 09/06/2021
		 * Added frame index as 1 as there are 2 frames with same index . Also added a ScrollToElement Method as the Lock Icon would 
		 * be hidden underneath chatbox . Also Commented Logout function as Not logging out would not much of an impact
		 * Reason : Since it was scrolled towards right to click the lock link .
		 */
		
//		Utils.waitForFrametoBeAvaialable(driver, "(//iframe[@id='__frm'])[2]");
		Utils.waitForElement(driver, section_PhoneLog);
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame(1);
		
		WebElement lnk_lock= driver.findElement(By.xpath("//a[contains(text(), '" + edittedMessage + "')]/../following-sibling::td/a[contains(text(), 'Lock')]"));
		
		Utils.scrollToElement(driver, lnk_lock);
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + edittedMessage + "')]/../following-sibling::td/a[contains(text(), 'Lock')]", "xpath", "Lock Icon");

		if (Utils.isAlertPresent(driver)) {
			driver.switchTo().alert().accept();
		} 
		else 
		{
			System.out.println("alert was not present");
		}
		driver.switchTo().defaultContent();
	}
	
	
	public void editLockedPhoneLog(String message, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: saurav purohit
		 * Modified On: 11/06/2021
		 */

		String alertMessage = "";
		

		Utils.waitForElement(driver, section_PhoneLog);
		driver.switchTo().frame(1);
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + message + "')]/../preceding-sibling::td/img[@alt='Firm']/../preceding-sibling::td//img[@title='Edit']", "xpath", "Edit Icon for locked Phone Log");

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
	
	
	public void deletePhoneLog(String edittedMessage) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 16/10/2019
		 */
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1);
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + edittedMessage + "')]/../preceding-sibling::td//img[@title='Delete']", "xpath", "Delete Icon");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Delete Case Phone Log", "title", "false");
		
		Utils.clickElement(driver, btn_Delete, "Delete Button");
		if (Utils.isAlertPresent(driver)) 
		{
			driver.switchTo().alert().accept();
		}
		
		//Utils.clickElement(driver, btn_DeleteFromAllCaseManagers, "Delete from all Case Managers");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		
		driver.navigate().refresh();
		
		driver.switchTo().defaultContent();
	}
	
	
	public void verifyDeletedPhoneLog(String message)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 15/10/2019
		 */
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(1) ;
		
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(), '" + message + "')]/../preceding-sibling::td//img[@title='Delete']", tbl_PhoneLogs, "xpath", message, "Phone Log Description");
		
		driver.switchTo().defaultContent();
	}

	
	public void verifyPhoneLogPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 3rd March 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com -- Case Notes List");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com -- Case Notes List page failed", driver);
	    }

}
	
	
}
