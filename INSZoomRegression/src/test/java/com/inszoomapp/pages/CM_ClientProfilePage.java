package com.inszoomapp.pages;

import org.openqa.selenium.JavascriptExecutor;
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


public class CM_ClientProfilePage extends LoadableComponent<CM_ClientProfilePage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "txtBirthdate")
	WebElement textBox_year;
	
	@FindBy(id = "cbofromMM")
	WebElement dropDown_month;
	
	@FindBy(id = "cboFDD")
	WebElement dropDown_day;
	
	@FindBy(id = "txtGCAlienNbr")
	WebElement textBox_alienNumber;
	
	@FindBy(id = "ClientList")
	WebElement waitElement_clientListHeader;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_bnf_last_name")
	WebElement searchBox_clientLastName;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    WebElement opt_EqualTo;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_bnf_first_name")
	WebElement searchBox_clientName;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_bnf_first_name")
	WebElement icon_CorporationNameFilter; 
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00__0")
	WebElement tbl_ClientList;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")			//Added by Kuchinad Shashank
    WebElement txt_ShowAllClient;
	
	@FindBy(id = "txtFileNumber")
	WebElement textBox_fileNumber;
	
	@FindBy(id="btn_SaveClientprofiledetails")
	WebElement btn_SaveClientProfileDetails;
	
	@FindBy(id="txtDeptName")
	WebElement textBox_department;
	
	@FindBy(id="btn_DeptBusinessUnitLookup")
	WebElement lnk_DeptBusinessUnitLookup;
	
	@FindBy(id="txtEmpId")
	WebElement textBox_empId;
	
	@FindBy(id="btn_EditClientsprofiledetails")
	WebElement btn_EditClientProfileDetails;
	
	@FindBy(id="btn_EditAddressDetails")
	WebElement btn_editResidence;
	
	@FindBy(id="btn_SaveAddressDetails")
	WebElement btn_saveResidence;
	
	@FindBy(id="cboStreetNos")
	WebElement dropdown_residenceApartment;
	
	@FindBy(id="txtAdd2s")
	WebElement txtbox_residenceApartmentNumber;
	
	@FindBy(id="cboCountryAddrs")
	WebElement dropdown_residenceCountry;
	
	@FindBy(id="txtAdd1s")
	WebElement txtbox_residenceStreetName;
	
	@FindBy(id="txtCitys")
	WebElement txtbox_residenceCity;
	
	@FindBy(id="txtStates")
	WebElement txtbox_residenceState;
	
	@FindBy(id="txtZips")
	WebElement txtbox_residencePostalCode;
	
	@FindBy(id="btn_SaveClientsadditionalinfo")
	WebElement btn_saveAdditionalInfo;
	
	@FindBy(id="cboBnfMarStat")
	WebElement dropdown_maritalStatus;
	
	@FindBy(id="txtOname")
	WebElement txtbox_otherName;
	
	@FindBy(id="cboCntryOfRes")
	WebElement dropdown_country;
	           
	@FindBy(id="btn_EditClientsAdditionalinfo")
	WebElement btn_EditAdditionalInfo;
	
	@FindBy(id="btn_SaveClientprofiledetails")
	WebElement btn_save;
	
	@FindBy(id="cboDayTimePhoneKey")
	WebElement dropdown_phoneNumberType;
	
	@FindBy(id="txtPhone1s")
	WebElement txtbox_telephone1;
	
	@FindBy(id="txtPhone2s")
	WebElement txtbox_telephone2;
	
	@FindBy(id="cboDayTimePhoneValue")
	WebElement txtbox_telephone3;
	
	@FindBy(id="txtLinkedIn")
	WebElement txtbox_linkedIn;
	
	@FindBy(id="txtFacebook")
	WebElement txtbox_facebook;
	
	@FindBy(id="txtTwitter")
	WebElement txtbox_twitter;
	
	@FindBy(id="txtSkype")
	WebElement txtbox_skype;
	
	@FindBy(id="btn_EditClientsprofiledetails")
	WebElement btn_editProfile;

	public CM_ClientProfilePage(WebDriver driver) {

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

	
	public void editContactDetails(String phoneNumber1, String phoneNumber2, String phoneNumber3, String linkedIn, String skype, String facebook, String twitter) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/01/2020
		 */
		Utils.waitForElementPresent(driver, "//iframe[@id='frmBnfContact']", "xpath");
		
		driver.switchTo().frame("frmBnfContact");
		
		Utils.clickElement(driver, btn_editProfile, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, "WorkPhoneNo1", dropdown_phoneNumberType);
		
		Utils.enterText(driver, txtbox_linkedIn, linkedIn, "LinkedIn");
		
		Utils.enterText(driver, txtbox_facebook, facebook, "Facebook");
		
		Utils.enterText(driver, txtbox_skype, skype, "Skype");
		
		Utils.enterText(driver, txtbox_twitter, twitter, "Twitter");
		
	//	Utils.enterText(driver, txtbox_telephone1, phoneNumber1, "Telephone 1");
		
		//Utils.enterText(driver, txtbox_telephone2, phoneNumber2, "Telephone 2");
		
	//	Utils.enterText(driver, txtbox_telephone3, phoneNumber3, "Telephone 3");
		
		Utils.clickElement(driver, btn_save, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		
	}
	
	
	public void verifyContactDetails(String phoneNumber3, String linkedIn, String skype, String facebook, String twitter)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/01/2020
		 */
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("frmBnfContact");
		
	//	Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Primary Phone/Daytime Phone')]/following-sibling::td[contains(text(), '" + phoneNumber3 + "')]", "xpath", phoneNumber3, "Primary Phone Number");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'LinkedIn')]/following-sibling::td[contains(text(), '" + linkedIn + "')]", "xpath", linkedIn, "LinkedIn");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Twitter')]/following-sibling::td[contains(text(), '" + twitter + "')]", "xpath", twitter, "Twitter");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Skype')]/following-sibling::td[contains(text(), '" + skype + "')]", "xpath", skype, "Skype");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Facebook')]/following-sibling::td[contains(text(), '" + facebook + "')]", "xpath", facebook, "Facebook");
	
		driver.switchTo().defaultContent();
	}
	
	
	public void editPersonalDetails(String country, String wifeFirstName, String maritalStatus) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/01/2020
		 */
		
		Utils.waitForElement(driver, "frmAddnlPassport", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("frmAddnlPassport");
		
		Utils.clickElement(driver, btn_EditAdditionalInfo, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Additional Info", "title", "false");
		
		Utils.enterText(driver, txtbox_otherName, wifeFirstName, "Other Name");
		
		Utils.selectOptionFromDropDown(driver, country, dropdown_country);
		
		Utils.selectOptionFromDropDown(driver, maritalStatus, dropdown_maritalStatus);
		
		Utils.clickElement(driver, btn_saveAdditionalInfo, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
	}
	
	
	public void verifyPersonalDetails(String wifeFirstName, String maritalStatus)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 09/01/2020
		 */
		
		Utils.waitForElement(driver, "frmAddnlPassport", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("frmAddnlPassport");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Other Name')]/following-sibling::td[contains(text(), '" + wifeFirstName + "')]", "xpath", wifeFirstName, "Other Name");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Marital Status')]/following-sibling::td[contains(text(), '" + maritalStatus + "')]", "xpath", maritalStatus, "Marital Status");
		
		driver.switchTo().defaultContent();
	}
	
	
	public void editResidenceAddress(String countryNew, String cityNew, String streetNumber, String state, String numberForPersonalInfo) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/01/2020
		 */
		
		Utils.waitForElement(driver, "frmContactAdd", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmContactAdd");
		
		Utils.clickElement(driver, btn_editResidence, "Edit");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Address Details", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, countryNew, dropdown_residenceCountry);
		
		Utils.enterText(driver, txtbox_residenceCity, cityNew, "City");
		
		Utils.enterText(driver, txtbox_residencePostalCode, numberForPersonalInfo, "Postal Code");
		
		Utils.enterText(driver, txtbox_residenceState, state, "State");
		
		Utils.enterText(driver, txtbox_residenceStreetName, streetNumber, "Street Name");
		
		Utils.selectOptionFromDropDown(driver, "Door", dropdown_residenceApartment);
		
		Utils.enterText(driver, txtbox_residenceApartmentNumber, numberForPersonalInfo, "Apartment Number");
		
		Utils.clickElement(driver, btn_saveResidence, "Save");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
	}
	
	
	public void verifyClientProfilePage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 30 Jan 2020
		  */
	    try{
	       Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client's Profile");
	    }catch(Exception e){
		 Log.failsoft("Verification of View Client's Profile Page  Failed", driver);
	    }
	}
	
	
	public void editProfileDetails(String employeeId, String department) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 26 Feb 2020
		  */
		
		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmBnfContact");
		Utils.clickElement(driver, btn_EditClientProfileDetails, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
		Utils.enterText(driver, textBox_empId, employeeId, "Employee ID");
		Utils.clickElement(driver, lnk_DeptBusinessUnitLookup, "dept business unit lookup link");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose Dept/Business Unit", "title", "false");
		Utils.clickDynamicElement(driver, "//a[contains(text(),'Other')]", "xpath", "selected dept/business unit");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
		Utils.enterText(driver, textBox_department, department, "department");
		Utils.clickElement(driver, btn_SaveClientProfileDetails, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
	}
	
	public void updateFileNumber(String fileNumber) throws Exception 
	{
		 /*
		  * Created By : Likitha Krishna
		  * Created On : 19 Aug 2020
		  */
		Utils.clickDynamicElement(driver, "frmBnfContact", "id", "iframe");
		driver.switchTo().frame("frmBnfContact");
		Utils.clickElement(driver, btn_EditClientProfileDetails, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
		Utils.enterText(driver, textBox_fileNumber, fileNumber, "file number");
		Utils.clickElement(driver, btn_SaveClientProfileDetails, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		driver.switchTo().frame("frmBnfContact");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'File Number')]/../td[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		driver.switchTo().defaultContent();
	}
	
	
	 public void updateAlienNumberAndDOB(String alienNumber, String day, String month, String year) throws Exception
	 {
	        /*
	          * Created By : M Likitha Krishna
	          * Created On : 19 Jan 2021
	          */
		Utils.clickDynamicElement(driver, "frmBnfContact", "id", "iframe");
		driver.switchTo().frame("frmBnfContact");
		Utils.clickElement(driver, btn_EditClientProfileDetails, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Profile Details", "title", "false");
        Utils.enterText(driver, textBox_alienNumber, alienNumber, "alien number");
        Utils.selectOptionFromDropDownByVal(driver, day, dropDown_day);
        Utils.selectOptionFromDropDownByVal(driver, month, dropDown_month);
        Utils.enterText(driver, textBox_year, year, "year");
        Utils.clickElement(driver, btn_SaveClientProfileDetails, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
	 }
	
	
	public void verifySuperUserDeleteIconAccess(boolean value, String key, String ownClient)
	{
		/*
		 * Created By : Kuchinad Shashank
         * Created On : 18 June 2020
         */
		try {
	    	 Utils.clickElement(driver, txt_ShowAllClient, "All Client Link");
	    	 //txt_ShowAllCorporation.click();
	    	 
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_ClientList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
		
		Utils.enterText(driver, searchBox_clientName, globalVariables.superUserClientName, "Corporation Name");
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Filter Icon");
	   
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_ClientList);
			 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
		 
		 String otherCorpName = "//a[contains(text(),'" + globalVariables.superUserCorporationName + "')]/../preceding-sibling::td//a[@title='Delete']";
		 
		 if(value)
		   {
			   if(key.equalsIgnoreCase("ALL"))
			   {
				   Utils.verifyIfDataPresent(driver, otherCorpName, "xpath", "Delete Icon", "Client List Page");
			   }
			   else if(key.equalsIgnoreCase("Mine"))
			   {
				   Utils.verifyIfDataNotPresent(driver, otherCorpName, waitElement_clientListHeader, "xpath", "Delete Icon", "Client List Page");
				   
			   }
		   }
		   else
		   {
			  Utils.verifyIfDataNotPresent(driver, otherCorpName, waitElement_clientListHeader, "xpath", "Delete Icon", "Client List Page");
			   
		   }
	}
	
	
	public void verifyDeleteIconAccess(boolean value, String key, String ownClient)
   {
	   /*
        * Created By : Kuchinad Shashank
        * Created On : 18 June 2020
        */
	   try {
	    	 Utils.clickElement(driver, txt_ShowAllClient, "All Client Link");
	    	 //txt_ShowAllCorporation.click();
	    	 
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_ClientList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all client. ERROR - " + e.getMessage(), driver, true);
		 }
	   
	   //searchBox_CorporationName.sendKeys(corporation);
		 Utils.enterText(driver, searchBox_clientName, ownClient, "Corporation Name");
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Filter Icon");
	   
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_ClientList);
			 Log.message("Clicked on EQUALTO filter to search client and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	   String ownClientName = "//a[contains(text(),'" + ownClient + "')]/../preceding-sibling::td//a[@title='Delete']";
	   
	   if(value)
	   {
		   if(key.equalsIgnoreCase("ALL"))
		   {
			   Utils.verifyIfDataPresent(driver, ownClientName, "xpath", "Delete Icon", "Client List Page");
		   }
		   else if(key.equalsIgnoreCase("Mine"))
		   {
			   Utils.verifyIfDataPresent(driver, ownClientName, "xpath", "Delete Icon", "Client List Page");
			   
		   }
	   }
	   else
	   {
		   Utils.verifyIfDataNotPresent(driver, ownClientName, waitElement_clientListHeader, "xpath", "Delete Icon", "Client List Page");
		   
	   }
   }
}
