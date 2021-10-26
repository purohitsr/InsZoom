package com.inszoomapp.pages;
import org.openqa.selenium.By;
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

public class CM_AccessRightsPage extends LoadableComponent<CM_AccessRightsPage> 
{

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//td[contains(text(),'Access Rights to Special Databases')]")
	WebElement text_AccessRightsSpecial;
	
	@FindBy(id = "cboDelClient")
	WebElement dropdown_deleteClientAccess;
	
	@FindBy(id = "cboAddClient")
	WebElement dropdown_addClientAccess;
	
	@FindBy(id = "cboAddCorp")
	WebElement dropdown_addCorporationAccess;
	
	@FindBy(id = "cboDelCorp")
	WebElement dropdown_deleteCorporationAccess;
	
	@FindBy(id = "btn_SavechangestoCaseManagerSecurity")
	WebElement btn_saveAtyAccessRights;
	
	@FindBy(xpath = "//form[@id='frmAtySecurity']/table[1]/tbody[1]/tr[2]//input[@id='btn_EditmyAccessRights']") //Added by Yatharth Pandya on 20/2/2020
	WebElement btn_edit;
	
	@FindBy(id = "lstAddressBookAcc")         // Added by Yatharth Pandya on 20/2/2020
	WebElement dropdown_corporationAccess;
	
	@FindBy(id = "btn_SavechangestomyAccessRights")  //Added by Yatharth Pandya on 20/2/2020
	WebElement btn_saveAccessRights;
	
	@FindBy(id = "pageHDR")							// Added by Yatharth Pandya on 20/2/2020
	WebElement txt_pageHeader;

	@FindBy(id = "btn_SaveChangesToLoginAccess")
	WebElement btn_saveChanges;
	
	@FindBy(xpath = "//input[@title='Add New Visa']")
	WebElement icon_PlusAddNewVisa;
	
	
	
	public CM_AccessRightsPage(WebDriver driver) {

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
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, btn_saveChanges))) {
			Log.fail("Home Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void verifyClientAccessRightsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 10 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Client Access Rights");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of Client Access Rights Page Failed ", driver);
	    }
		
		
	}
	
	
	public void clickEdit()
	{
		//Created by Yatharth Pandya
		//Created on 20/02/2020
		Utils.clickElement(driver, btn_edit, "Edit Access Rights");
		
	}

	
	public void giveAccessRightstoCorporationOrCaseOrClient(String accessvalue )
	{
		//Created by Kuchinad Shashank on 20/2/2020
		
		Utils.selectOptionFromDropDownByVal(driver, accessvalue, dropdown_corporationAccess);
		Utils.clickElement(driver, btn_saveAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}
	
	
	public void giveAccessRghtsToDeleteCorportaion(String accessValue)
	{
		/*
		 * Created By Kuchinad Shashank
		 * Created on 21th July 2020
		 */
		Utils.selectOptionFromDropDownByVal(driver, accessValue, dropdown_deleteCorporationAccess);
		Utils.clickElement(driver, btn_saveAtyAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}
	
	
	public void giveAccessRghtsToAddCorportaion(String accessValue)
	{
		/*
		 * Created By Kuchinad Shashank
		 * Created on 21th July 2020
		 */
		Utils.selectOptionFromDropDownByVal(driver, accessValue, dropdown_addCorporationAccess);
		Utils.clickElement(driver, btn_saveAtyAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}
	
	
	public void giveAccessRghtsToDeleteClient(String accessValue)
	{
		/*
		 * Created By Kuchinad Shashank
		 * Created on 21th July 2020
		 */
		Utils.selectOptionFromDropDownByVal(driver, accessValue, dropdown_deleteClientAccess);
		Utils.clickElement(driver, btn_saveAtyAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}
	
	
	public void giveAccessRghtsToAddClient(String accessValue)
	{
		/*
		 * Created By Kuchinad Shashank
		 * Created on 21th July 2020
		 */
		Utils.selectOptionFromDropDownByVal(driver, accessValue, dropdown_addClientAccess);
		Utils.clickElement(driver, btn_saveAtyAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}

	
	public void genericGiveAccessDropdown(String accessTypeId, String accessValue)
	{
		/*
		 * Created By Kuchinad Shashank
		 * Created on 10th August 2020
		 */
		
		Utils.waitForElement(driver, accessTypeId, globalVariables.elementWaitTime, "id");
		WebElement accessRight = driver.findElement(By.id(accessTypeId));
		Utils.selectOptionFromDropDownByVal(driver, accessValue, accessRight);
		Utils.clickElement(driver, btn_saveAtyAccessRights, "Save changes to My Access Rights");
		Utils.waitForElementNotVisible(driver, "input[id='btn_CancelchangestomyAccessRights']", "css");
		Utils.waitForElement(driver, text_AccessRightsSpecial);
	}
}