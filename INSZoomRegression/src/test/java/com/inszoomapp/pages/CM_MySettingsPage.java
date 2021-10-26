package com.inszoomapp.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 




import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_MySettingsPage extends LoadableComponent<CM_MySettingsPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//form[@id='frmAtySecurity']/table[1]/tbody[1]/tr[4]//input[@id='btn_EditCaseManagerSecurity']")
	WebElement btn_editAtyBilingAccessRights;
	
	@FindBy(id = "btn_EditCaseManagerSecurity")
	WebElement btn_editAtyAccessRights;
	
	@FindBy(xpath = "//td[@class='LMIS']/span[contains(text(),'Access Rights')]")
	WebElement tab_accessRightsNonSA;
	
	@FindBy(id = "LM4")
	WebElement tab_caseManagers;
	
	@FindBy(id = "lstAddressBookAcc")         // Added by Yatharth Pandya on 20/2/2020
	WebElement dropdown_corporationAccess;
	
	@FindBy(id = "btn_SavechangestomyAccessRights")  //Added by Yatharth Pandya on 20/2/2020
	WebElement btn_saveAccessRights;
	
	@FindBy(id = "pageHDR")							// Added by Yatharth Pandya on 20/2/2020
	WebElement txt_pageHeader;
	
	@FindBy(xpath = "//form[@id='frmAtySecurity']/table[1]/tbody[1]/tr[2]//input[@id='btn_EditmyAccessRights']") //Added by Yatharth Pandya on 20/2/2020
	WebElement btn_editAccessRights; 
	
	@FindBy(xpath = "//input[@id='btn_EditmyAccessRights']")
	WebElement btn_editAccess;
	
	@FindBy(xpath = "//td[@class='LMIS'][contains(text(),'Organization Tools')]")//Do not change
	WebElement tab_OrganizationTools;
	
	@FindBy(xpath = "//td[@title='Click to select Home Page']")//Do not change
	WebElement tab_MyINSZoom;
	
	@FindBy(xpath = "//td[@title='Click to view Preferences']")//Do not change
	WebElement tab_EmailAlertPreferences;
	
	@FindBy(id = "LMAtySecurity")
	WebElement tab_AccessRights;
	
	
	
	public CM_MySettingsPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Settings page didnt loaded");
		}
	}
	
	public void verfifyCaseManagerDetailsPage(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'View Case Manager Details')]", "xpath", "Header - View Case Manager Details", "Case manager details page");
	}
	
	public void clickAccessRightsTab()
	{
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		 */
		Utils.clickElement(driver, tab_AccessRights, "access rights tab");	
	}	
	
	public void verifyCaseManagerAccessRights(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickAccessRightsTab();
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'View Access Rights')]", "xpath", "header - View Access Rights", "in access rights page");
	}
	
	public void clickEmailAlertPreferencesTab()
	{
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		 */
		Utils.clickElement(driver, tab_EmailAlertPreferences, "Email alert tab");	
	}
	
	
	public void verifyCaseManagerEmailAlertPreferences(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickEmailAlertPreferencesTab();
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'Email Alert Preferences')]", "xpath", "header - Email Alert Preferences", "in Email Alert Preferences page");
	}
	
	public void clickMyINSZoomTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		 */
		Utils.clickElement(driver, tab_MyINSZoom, "My INSZoom tab");	
	}
	
	
	public void verifyCaseManagerINSZoom(boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickMyINSZoomTab();
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'My INSZoom')]", "xpath", "header - My INSZoom", "in My INSZoom page");
	}
	
	public void clickOrganizationToolsTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		 */
		Utils.clickElement(driver, tab_OrganizationTools, "Organization Tools tab");	
	}
	
	 public void clickEditAccessRightButton()
	{
		//Created by Yatharth Pandya
		//Created on 20/02/2020
			
		Utils.clickElement(driver, btn_editAccess, "Edit Access Rights");
				
	}

	 
	public void giveAccessRightstoCorporationOrCaseOrClient(WebDriver driver, String accessvalue )
	{
		
		//Utils.clickElement(driver, dropdown_corporationAccess, "Corporation access to databases dropdown");
		
		Utils.selectOptionFromDropDownByVal(driver, accessvalue, dropdown_corporationAccess);
		
		Utils.clickElement(driver, btn_saveAccessRights, "Save changes to My Access Rights");
		
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		
	} 
	
	
	public void verifyDisabledAccessRights()
	{
		 /*
		  * Created By : Kuchinad Shashank
		  * Created On : 9th June 2020
		  */
		
		Utils.verifyIfDataPresent(driver, "//select[@disabled='disabled']", "xpath", "Access Rights disabled", "Access Rights");
	}
	
	
	public void clickCaseManagersTab()
	{
		/*
		  * Created By : Kuchinad Shashank
		  * Created On : 22th July 2020
		  */
		
		Utils.clickElement(driver, tab_caseManagers, "Case Managers Tab");
		
		Utils.waitForElementPresent(driver, "//iframe[@id='IFrameListCM']", "xpath");
		
	}
	
	public void verifyEditAccessRightsButtonPresent(boolean value)
	{
		/*
		 * Created By : Kuchinad Shashank
		 * Created On : 22th July 2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//input[@id='btn_EditmyAccessRights']", "xpath", "Edit Button", "Access Rghts Page");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//input[@id='btn_EditmyAccessRights']", tab_AccessRights, "xpath", "Edit Button", "Access Rights Page");
		}
				
	}

	
	public void clickAccessRightsTabOfNonSuperAdmin()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 22 Oct 2019
		 */
		
		Utils.clickElement(driver, tab_accessRightsNonSA, "access rights tab");	
	}
	
	
	public void clickOnNonSuperAdminId(WebDriver driver, String Id) throws Exception
	{
		/*
		 * Created By : Kuchinad Shashank	
		 * Created On : 24/07/2020
		 */
		
		Utils.waitForFrametoBeAvaialable(driver, "IFrameListCM");
		
		Thread.sleep(15000);
		
		//driver.switchTo().frame("IFrameListCM");
		
		Utils.waitForElement(driver, "//a[contains(text(),'"+ Id + "')]", globalVariables.elementWaitTime, "xpath");

		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ Id + "')]", "xpath", "Case Manager Id");
		
		driver.switchTo().defaultContent();
	}
	
	
	public void clickEditAtyAccessRightButton()
	{
		//Created by Kuchinad Shashank
		//Created on 27/07/2020
			
		Utils.clickElement(driver, btn_editAtyAccessRights, "Edit Access Rights");
				
	}
	
	
	public void clickEditAtyBilingAccessRights()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 12/08/2020
		 */
		
		Utils.clickElement(driver, btn_editAtyBilingAccessRights, "Edit Accounting Database Button");
	}
	
}