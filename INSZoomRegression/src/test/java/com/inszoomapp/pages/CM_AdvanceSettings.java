package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class CM_AdvanceSettings extends LoadableComponent<CM_AdvanceSettings> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	
	//Web element
	
	@FindBy(id="btn_SaveFormAddendumsetting")
	WebElement btn_saveFormAddendumSettings;
	
	@FindBy(id="cboAdndmSign")
	WebElement dropdown_signatureInAddendum;
	
	@FindBy(id="btn_EditFormAddendumSettings")
	WebElement btn_editFormAddendumSettings;
	
	@FindBy(xpath = "//span[@title='Form Addendum Settings']")
	WebElement tab_formAddendumSettings;
	
	@FindBy(xpath = "//span[@title='Security Settings']/..//img[contains(@src, 'ftv2pnode')]")
	WebElement img_securitySettings;

	@FindBy(id="btn_SaveDefaultAccessPeriodforformsquestionnairesToDo")
	WebElement btn_saveDefaultAccessPeriod;
	
	@FindBy(id="btn_SaveSecuritySettings")
	WebElement btn_saveSecuritySettings;
	
	@FindBy(id="btn_EditSecuritySettings")
	WebElement btn_editSecuritySettings;
	
	@FindBy(id="btn_SaveFormFontSettings")
	WebElement btn_saveFormFontSettings;
	
	@FindBy(id="cboFontSize")
	WebElement dropdown_formFontSize;
	
	@FindBy(id="cboAccTimes")
	WebElement dropdown_defaultAccessPeriod;
		
	@FindBy(id="ddlUsrLocking")
	WebElement dropdown_invalidLoginAttempts;
	
	@FindBy(id="btn_EditFormFontSettings")
	WebElement btn_editFormFontSettings;
	
	@FindBy(id="btn_EditdefaultFormQuestionnairesToDoaccessperiod")
	WebElement btn_editDefaultAccessPeriod;
	
	@FindBy(css="span[title='Form Font Settings']")
	WebElement btn_formFontSettings;
	
	@FindBy(css="span[title='Default Access Period for Forms/Questionnaires/To Do']")
	WebElement btn_defaultAccessPeriod;
	
	@FindBy(css="span[title='Setup']")
	WebElement btn_setup;
	
	@FindBy(css="span[title='Locked Users']")
	WebElement btn_lockedUsers;
	
	@FindBy(id="btn_UnlockselectedUsers")
	WebElement btn_unlockSelectedUsers;
	
	@FindBy(id="btn_Savesettings")		//Added by Saksham Kapoor 30 Sep 2020
	WebElement btn_SaveEmailSettings;
	
	@FindBy(id="cboDefaultMail")		//Added by Saksham Kapoor 30 Sep 2020
	WebElement dropDown_defaultEmailForEmployee;

	@FindBy(id = "cboEmailRestrict")
	WebElement dropDown_restrictEmailInFNP;
	
	@FindBy(name = "cmboEmlFrmAcss")
	WebElement dropDown_userToChangeWhileEmail;
	
	@FindBy(id = "btn_Savesettings")
	WebElement btn_saveEmailSettings;
	
	@FindBy(name = "CboCCEmail")
	WebElement dropDown_CCtoSupervisor;
	
	@FindBy(id = "btn_EditMisc.Settings")
	WebElement btn_editEmailSettings;
	
	@FindBy(name = "cboEflngEn")
	WebElement dropDown_EfilingEnable;
	
	@FindBy(name = "cboTrckChng")
	WebElement dropDown_TrackChanges;
	
	@FindBy(id = "btn_SaveFormEmailsetting")
	WebElement btn_SaveFormEmailSetting;
	
	@FindBy(name = "cboFormAccType")
	WebElement dropDown_FormAccessType;
	
	@FindBy(id = "btn_EditFormEmailSettings")
	WebElement btn_EditFormEmailSettings;
	
	@FindBy(xpath = "//span[contains(text(),'Form Email Settings')]")
	WebElement tab_FormEmailSettings;
	
	@FindBy(id = "btn_SaveasDefaultEmailAccess")
	WebElement btn_SaveDefaultEmailAccess;
	
	@FindBy(name = "cboEmailAccess")
	WebElement dropDown_DefaultEmailAccess;
	
	@FindBy(id = "btn_EditdefaultEmailAccess")
	WebElement btn_editDefaultEmailAccess;
	
	@FindBy(xpath = "//span[contains(text(),'Default Email Access')]")
	WebElement tab_DefaultEmailAccess;
	
	@FindBy(xpath = "//span[@title='Email']")
	WebElement tab_Email;
	
	@FindBy(xpath = "//span[@title='Org Settings']/..//img[contains(@src, 'plastnode')]")
	WebElement img_expandOrgSettings;
	
	@FindBy(xpath = "//span[@title='Email']/..//img[contains(@src, 'ftv2pnode')]")
	WebElement img_expandEmail;
	
	@FindBy(xpath = "//span[@title='Form']/..//img[contains(@src, 'ftv2pnode')]")
	WebElement img_expandForm;

	public CM_AdvanceSettings(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
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
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}

	//Functions
	public void expandOrgSettings() throws InterruptedException
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */
		Thread.sleep(5000);
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, img_expandOrgSettings, "plus to expand org settings");
		driver.switchTo().defaultContent();
	}
	
	public void expandForms()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, img_expandForm, "plus to expand form");
		driver.switchTo().defaultContent();
	}
	
	public void expandEmail()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, img_expandEmail, "plus to expand email");
		driver.switchTo().defaultContent();
	}
	
	public void clickOnEmailTab()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, tab_Email, "email tab");
		driver.switchTo().defaultContent();
	}
	
	public void clickOnDefaultEmailAccessTab()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, tab_DefaultEmailAccess, "default email access tab");
		driver.switchTo().defaultContent();
	}
	
	
	public void clickFormEmailSettingsTab()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, tab_FormEmailSettings, "form email settings tab");
		driver.switchTo().defaultContent();
	}
	
	public void setDefaultEmailAccess(String dropDownValue) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_editDefaultEmailAccess, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Default Email Access", "title", "false");
		Utils.clickElement(driver, dropDown_DefaultEmailAccess, "dropdown");
		
		Utils.selectOptionFromDropDownByVal(driver, globalVariables.defaultEmailAccess.get(dropDownValue), dropDown_DefaultEmailAccess);
		Utils.clickElement(driver, btn_SaveDefaultEmailAccess, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		String value[] = dropDownValue.split(" ");
		String temp = "";
		for(int i=0; i<value.length; i++)
			temp = temp + value[i] + "\u00A0";
		driver.switchTo().frame("rtFrame");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+value[0]+"')]", "xpath", dropDownValue, "advance settings page");
		driver.switchTo().defaultContent();
	}
	 
	public void setFormAccessType(String accessType) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_EditFormEmailSettings, "edit form email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Form Email Settings", "title", "false");
		Utils.selectOptionFromDropDown(driver, accessType, dropDown_FormAccessType);
		Utils.clickElement(driver,btn_SaveFormEmailSetting, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+accessType+"')]", "xpath", accessType, "forms settings page");
		driver.switchTo().defaultContent();
	}
	
	public void setTrackChanges(String option) throws Exception
	{
		/*
		* Created By : M Likitha Krishna
		* Created On : 05 Oct 2020
		*/
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_EditFormEmailSettings, "edit form email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Form Email Settings", "title", "false");
		Utils.selectOptionFromDropDown(driver, option, dropDown_TrackChanges);
		Utils.selectOptionFromDropDown(driver, "Edit Form - PDF", dropDown_FormAccessType);
		Utils.clickElement(driver,btn_SaveFormEmailSetting, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		if(option.equals("No"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Track Changes')]//following-sibling::td[contains(text(),'No')]", "xpath", "No", "Track Changes");
		else
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Track Changes')]//following-sibling::td/img[@alt='Yes']", "xpath", "Yes", "Track Changes");
		driver.switchTo().defaultContent();
	}
	
	public void setEfilingEnabled(String option) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 05 Oct 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_EditFormEmailSettings, "edit form email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Form Email Settings", "title", "false");
		Utils.selectOptionFromDropDown(driver, option, dropDown_EfilingEnable);
		Utils.clickElement(driver,btn_SaveFormEmailSetting, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		if(option.equals("No"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Efiling Enabled')]//following-sibling::td[contains(text(),'No')]", "xpath", "No", "Efiling Enabled");
		else
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Efiling Enabled')]//following-sibling::td/img[@alt='Yes']", "xpath", "Yes", "Efiling Enabled");
		driver.switchTo().defaultContent();
	}
	
	public void setSendEmailCCtoSupervisor(String option) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 05 Oct 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_editEmailSettings, "edit email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Misc. Settings", "title", "false");
		Utils.selectOptionFromDropDown(driver, option, dropDown_CCtoSupervisor);
		Utils.clickElement(driver,btn_saveEmailSettings, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		if(option.equals("No"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'supervisor')]//following-sibling::td[2]/img[@alt='Yes']", "xpath", "No", "supervisor");
		else
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'supervisor')]//following-sibling::td[1]/img[@alt='Yes']", "xpath", "Yes", "supervisor");
		driver.switchTo().defaultContent();
	}
	
	public void setAllowUserToMakeChangesSettingWhileEmailing(String option) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 05 Oct 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_EditFormEmailSettings, "edit email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Form Email Settings", "title", "false");
		Utils.waitForElement(driver, dropDown_FormAccessType); //just to ensure page is loaded
		if(!(Utils.isElementPresent(driver, "cmboEmlFrmAcss", "name")))
		{
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
			return;
		}
		Utils.selectOptionFromDropDown(driver, option, dropDown_userToChangeWhileEmail);
		Utils.clickElement(driver,btn_SaveFormEmailSetting, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		if(option.equals("No"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Allow user to change')]//following-sibling::td[contains(text(),'No')]", "xpath", "No", "Allow user to change settings while emailing");
		else
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Allow user to change')]//following-sibling::td[1]/img[@alt='Yes']", "xpath", "Yes", "Allow user to change settings while emailing");
//		globalVariables.formAccessType = driver.findElement(By.xpath("//th[contains(text(),'Form Access Type')]/..//td")).getText().trim();
//		globalVariables.enableEfiling = driver.findElement(By.xpath("//th[contains(text(),'Efiling Enabled')]/..//td")).getText().trim();
//		globalVariables.trackChanges = driver.findElement(By.xpath("//th[contains(text(),'Track Changes')]/..//td")).getText();
//		if(!(globalVariables.enableEfiling.equals("No")))
//			globalVariables.enableEfiling = "Yes";
//		if(!(globalVariables.trackChanges.equals("No")))
//			globalVariables.trackChanges = "Yes";
		driver.switchTo().defaultContent();
	}
	
	
	public void setRestrictClientAbilityViewOrSelectEmailOf(String option) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 09 Oct 2020
		 */ 
		driver.switchTo().frame("rtFrame");
		Utils.clickElement(driver, btn_editEmailSettings, "edit email settings");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Misc. Settings", "title", "false");
		Utils.selectOptionFromDropDown(driver, option, dropDown_restrictEmailInFNP);
		Utils.clickElement(driver,btn_saveEmailSettings, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		driver.switchTo().frame("rtFrame");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Restrict Client ability to view or select email')]/..//td[contains(text(),'"+option+"')]", "xpath", option, "in Restrict Client ability to view or select email");
		driver.switchTo().defaultContent();
	}
	
	public void changeDefaultEmployeeForEmployee(String value) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Sep 2020
		 * 
		 */ 
		driver.switchTo().frame("rtFrame");
		
		Utils.clickElement(driver, btn_editEmailSettings, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Misc. Settings", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, value, dropDown_defaultEmailForEmployee);
		
		Utils.clickElement(driver, btn_SaveEmailSettings, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		
		driver.switchTo().frame("rtFrame");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Default Email for employee')]/following-sibling::td[contains(text(), '" + value + "')]", "xpath", value, "Default Email for Employee");
	}
	
	
	public void clickOnFormFontSettings()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 01 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "frmTree", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmTree");
		
		Utils.clickElement(driver, btn_formFontSettings, "Form Font Settings");
		driver.switchTo().defaultContent();
	}
	
	
	public void clickOnDefaultAccessPeriod()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 01 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "frmTree", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmTree");
		
		Utils.clickElement(driver, btn_defaultAccessPeriod, "Default Access Period");
		driver.switchTo().defaultContent();
	}
	
	
	public void changeFontSize(int fontSize) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 01 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.clickElement(driver, btn_editFormFontSettings, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Inszoom.com - Edit Form Font Settings", "title", "false");
		 
		String option = String.valueOf(fontSize);
		Utils.selectOptionFromDropDownByVal(driver, option, dropdown_formFontSize);
		
		Utils.clickElement(driver, btn_saveFormFontSettings, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Font Size')]/following-sibling::td[contains(text(), '" + option + "')]", "xpath", option, "Font Size");	
	}
	
	
	public void changeAccessPeriod(int accessPeriod) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.clickElement(driver, btn_editDefaultAccessPeriod, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Default Access Period for Forms/Questionnaires", "title", "false");
		 
		String option = String.valueOf(accessPeriod);
		Utils.selectOptionFromDropDownByVal(driver, option, dropdown_defaultAccessPeriod);
		
		Utils.clickElement(driver, btn_saveDefaultAccessPeriod, "Save");
		
		//For logging of current date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date currentDate = new Date();
		Log.message("The current date is " + formatter.format(currentDate) );
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Default Access Period')]/following-sibling::td[contains(text(), '" + option + " Month')]", "xpath", option + " Month", "Default Access Period");	
	}

	
	public void expandSecuritySettings()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 29 Sep 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, img_securitySettings, "plus to expand Security Settings");
		driver.switchTo().defaultContent();
	}
	
	
	public void clickOnSetup()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "frmTree", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmTree");
		
		Utils.clickElement(driver, btn_setup, "Setup");
		driver.switchTo().defaultContent();
	}
	
	
	public void clickOnLockedUsers()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "frmTree", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmTree");
		
		Utils.clickElement(driver, btn_lockedUsers, "Locked Users");
		driver.switchTo().defaultContent();
	}
	
	
	public void verifyLockedUser(String userName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + userName + "')]", "xpath", userName, "Locked Users");
		driver.switchTo().defaultContent();
	}
	
	
	public void unlockUser(String userName) throws InterruptedException
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + userName + "')]/preceding-sibling::td/input", "xpath", userName + " checkbox");
		Utils.clickElement(driver, btn_unlockSelectedUsers, "Unlock");
		Thread.sleep(3000);
		
		driver.switchTo().defaultContent();
	}
	
	
	public void changeInvalidLoginAttemptsAllowed(int randomInvalidAttempt) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Oct 2020
		 * 
		 */ 
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.clickElement(driver, btn_editSecuritySettings, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Edit Security Settings", "title", "false");
		 
		String option = String.valueOf(randomInvalidAttempt);
		Utils.selectOptionFromDropDownByVal(driver, option, dropdown_invalidLoginAttempts);
		
		Utils.clickElement(driver, btn_saveSecuritySettings, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Invalid Login Attempts Allowed')]/following-sibling::td[contains(text(), '" + option + "')]", "xpath", option, "Default Email for Employee");	
	}

	public void clickOnFormAddendumSettings()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Oct 2020
		 */ 
		driver.switchTo().frame("frmTree");
		Utils.clickElement(driver, tab_formAddendumSettings, "Form Addendum Settings");
		driver.switchTo().defaultContent();
	}
	
	
	public void changeSignatureInAddendum(String choice) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Oct 2020
		 * 
		 */ 
		
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		Utils.clickElement(driver, btn_editFormAddendumSettings, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Form Addendum Settings", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, choice, dropdown_signatureInAddendum);
		
		Utils.clickElement(driver, btn_saveFormAddendumSettings, "Edit");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Zoom Easy Customization", "title", "false");
		
		Utils.waitForElement(driver, "rtFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("rtFrame");
		
		if(choice.equals("Yes"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Signature in addendum')]/following-sibling::td/img[@alt='Yes']", "xpath", choice, "Signature in Addendum");
		if(choice.equals("No"))
			Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Signature in addendum')]/following-sibling::td[contains(text(), 'No')]", "xpath", choice, "Signature in Addendum");
	}
	
	

}
