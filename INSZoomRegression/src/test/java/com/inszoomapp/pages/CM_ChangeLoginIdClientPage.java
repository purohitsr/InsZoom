package com.inszoomapp.pages;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_ChangeLoginIdClientPage extends LoadableComponent<CM_ChangeLoginIdClientPage> 
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;

	public String passwordInputTxt = "Fnp" + "@" + RandomStringUtils.randomNumeric(5).toString();
	
	@FindBy(css = "input[name='txtLogId']")
	WebElement txtbox_LoginId;
	
	@FindBy(css = "input[title='Save Client Login Id']")
	WebElement btn_SaveLoginId;
	
	@FindBy(css = "a[title='Please click here to Change Password']")
	WebElement lnk_ChangePassword;
	
	@FindBy(css = "input[id='objMyPswd_txtNewChPwd1']")
	WebElement txtbox_NewPassword;

	@FindBy(css = "input[id='objMyPswd_txtNewChPwd2']")
	WebElement txtbox_ConfirmNewPassword;

	@FindBy(css = "input[value='Change Password']")
	WebElement btn_ChangePassword;

	@FindBy(css = "input[value='Close']")
	WebElement btn_Close;

	public CM_ChangeLoginIdClientPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_ChangeLoginIdClientPage(WebDriver driver) {
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
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}

	
	public void updateLoginId(String workbookNameWrite, String sheetName, boolean screenshot) throws Exception
	{
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 10/10/2019
		 */
		
		String fnpLoginId = "";
		
		Utils.clickElement(driver, txtbox_LoginId, "Login Id textbox");
			
		String loginIdValue = txtbox_LoginId.getAttribute("value");
		
		Utils.enterText(driver, txtbox_LoginId, loginIdValue + "1", "Login ID");

		fnpLoginId = loginIdValue + "1";

		Utils.clickElement(driver, btn_SaveLoginId, "Save Button");
	
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("FNPLoginIDCreated", sheetName, "Value", fnpLoginId);
			
	}
	
	
	public void clickAndChangePassword(String workbookNameWrite, String sheetName, boolean screenshot)
			throws Exception 
	{
		Utils.clickElement(driver, lnk_ChangePassword, "change password link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Change Password", "title", "false");
			
		Utils.enterText(driver, txtbox_NewPassword, passwordInputTxt, "New Password");
		
		Utils.enterText(driver, txtbox_ConfirmNewPassword, passwordInputTxt, "Confirm New Password");

		Utils.clickElement(driver, btn_ChangePassword, "Change Password Button");
		
		Utils.clickElement(driver, btn_Close, "Close Button");
		

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Change Login Id", "title", "false");

		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("FNPPasswordCreated", sheetName, "Value", passwordInputTxt);

	}

}
