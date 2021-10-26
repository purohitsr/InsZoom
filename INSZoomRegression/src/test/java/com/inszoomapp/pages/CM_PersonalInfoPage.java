package com.inszoomapp.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_PersonalInfoPage extends LoadableComponent<CM_PersonalInfoPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//a[@title='Go']") //Added by Souvik Ganguly on 4 August 2021
	WebElement button_Go;
	
	@FindBy(id="txtCityw")
	WebElement txtbox_workAddressCity;
	
	@FindBy(id="txtStreetNow")
	WebElement txtbox_workAddressStreet1;
	
	@FindBy(id="txtAdd1w")
	WebElement txtbox_workAddressStreet2;
	
	@FindBy(id="txtStatew")
	WebElement txtbox_workAddressState;
	
	@FindBy(id="txtZipw")
	WebElement txtbox_workPinCode;	
	
	@FindBy(id="txtPhone1w")
	WebElement txtbox_workAddressTelephone;
		
	@FindBy(id="txtCellw")
	WebElement txtbox_workAddressMobile;
	
	@FindBy(id="txtAdd2w")
	WebElement txtbox_workAddressApartment;
	
	@FindBy(id="cboCountryAddrs")
	WebElement dropdown_residenceAddressCountry;
	
	@FindBy(id="txtAdd2s")
	WebElement txtbox_apartment;
	
	@FindBy(id="txtStreetNos")
	WebElement txtbox_street1;
	
	@FindBy(id="txtAdd1s")
	WebElement txtbox_street2;
	
	@FindBy(id="txtStates")
	WebElement txtbox_state;
	
	@FindBy(id="txtZips")
	WebElement txtbox_pinCode;
	
	@FindBy(id="txtPhone1s")
	WebElement txtbox_telephone;
	
	@FindBy(id="txtCells")
	WebElement txtbox_mobile;
	
	@FindBy(css = "input[name='txtCitys']")
	WebElement txtbox_ResidenceCity;
	
	@FindBy(id="LM73")
	WebElement tab_Profile;
	
	@FindBy(css = "select[name='cboContactAddr']")
	WebElement dropdown_AddressType;

	@FindBy(css = "input[title='Save Contact Address']")
	WebElement btn_SavePersonalAddress;
	
	@FindBy(id="btn_ChooseClientsPersonalAddress")
	WebElement lnk_ChooseClientPersonalAddress;
	
	@FindBy(css = "input[id='btn_SaveClientsDetails']")
	WebElement btn_SavePersonalInfo;
	
	@FindBy(id="btn_EditClientsDetails")
	WebElement btn_EditPersonalInfo;
	
	@FindBy(css = "input[name='txtCityw']")
	WebElement txtbox_City;;

	@FindBy(css = "input[name='txtCellw']")
	WebElement txtbox_MobileNumber;

	@FindBy(css = "select[name='cboCountryAddrw']")
	WebElement dropdown_workAddressCountry;

	public CM_PersonalInfoPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_PersonalInfoPage(WebDriver driver) {
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
	
	
	public void clickEditPersonalInfoButton() throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		Utils.clickElement(driver, btn_EditPersonalInfo, "Edit Button");
	}
	
	
	public void enterClientInfo(String city, String number, String country) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		Utils.enterText(driver, txtbox_City, city, "City/Town");
		
		Utils.enterText(driver, txtbox_MobileNumber, number, "Mobile Number");
			
		Utils.selectOptionFromDropDownByVal(driver, country, dropdown_workAddressCountry);

	}
	
	
	public void saveClientInfo()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		Utils.clickElement(driver, btn_SavePersonalInfo, "Save Button");
	}
	
	
	public void verifyEditedDetails(String city, String number, String country) throws Exception 
	{
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Mobile Number')]/following-sibling::td[contains(text(),'" + number + "')]", "xpath", number, "Mobile Number");
	
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'City/Town')]/following-sibling::td[contains(text(),'" + city + "')]", "xpath", city, "City");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Country')]/following-sibling::td[contains(text(),'" + country + "')]", "xpath", country, "Country");

	}
	
	
	public void choosePersonalAddress(String type) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */

		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		Utils.clickElement(driver, lnk_ChooseClientPersonalAddress, "Choose Main Personal Address");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Choose Contact Address", "title", "false");

		Utils.selectOptionFromDropDownByVal(driver, type, dropdown_AddressType);
		
		Utils.clickElement(driver, btn_SavePersonalAddress, "Save Button");
	
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Contact Info", "title", "false");
	
	}
	
	
	public void verifyProfileDetails(String city, String number) throws Exception 
	{
		
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 18/10/2019
		 */

		Utils.clickElement(driver, tab_Profile, "Profile Tab");

		Utils.waitForElement(driver, "frmBnfContact", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmBnfContact");

		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Contact Phone')]/following-sibling::td[contains(text(),'" + number + "')]", "xpath", number, "Mobile Number");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Contact Address')]/following-sibling::td[contains(text(),'" + city + "')]", "xpath", city, "City");

		driver.switchTo().defaultContent();

	}
	
	
	public void editResidenceInfo(String residenceCity, String country, String apartment, String street1, String street2, String pinCode, String state, String mobile, String telephone) throws Exception 
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Modified On: 30/07/2021
		 * Added sync to wait for the GO button before updating the country
		 */
		
		Utils.clickElement(driver, btn_EditPersonalInfo, "Edit Button");
		
		Utils.waitForElement(driver, "//a[@title='Go']", globalVariables.elementWaitTime, "xpath");
		
		Utils.selectOptionFromDropDownByVal(driver, country, dropdown_residenceAddressCountry);

		Utils.enterText(driver, txtbox_ResidenceCity, residenceCity, "Residence City");
		
		Utils.enterText(driver, txtbox_apartment, apartment, "Apartment");
		
		Utils.enterText(driver, txtbox_mobile, mobile, "Mobile");
		
		Utils.enterText(driver, txtbox_pinCode, pinCode, "Postal Code");
		
		Utils.enterText(driver, txtbox_state, state, "State");
		
		Utils.enterText(driver, txtbox_telephone, telephone, "Telephone #1");
		
		Utils.enterText(driver, txtbox_street1, street1, "Street");
		
		Utils.enterText(driver, txtbox_street2, street2, "Street");
		
		Utils.waitForElement(driver, btn_SavePersonalInfo);
		Utils.scrollIntoView(driver, btn_SavePersonalInfo);
		
		Utils.clickElement(driver, btn_SavePersonalInfo, "Save Button");

	}	
	
	
	public void editWorkAddress(String residenceCity, String country, String apartment, String street1, String street2, String pinCode, String state, String mobile, String telephone) throws Exception 
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Modified On: 30/07/2021
		 * Added sync to wait for the GO button before updating the country
		 */
		
		Utils.clickElement(driver, btn_EditPersonalInfo, "Edit Button");
		
		Utils.waitForElement(driver, button_Go);
		
		Utils.selectOptionFromDropDownByVal(driver, country, dropdown_workAddressCountry);

		Utils.enterText(driver, txtbox_workAddressCity, residenceCity, "Residence City");
		
		Utils.enterText(driver, txtbox_workAddressApartment, apartment, "Apartment");
		
		Utils.enterText(driver, txtbox_workAddressMobile, mobile, "Mobile");
		
		Utils.enterText(driver, txtbox_workPinCode, pinCode, "Postal Code");
		
		Utils.enterText(driver, txtbox_workAddressState, state, "State");
		
		Utils.enterText(driver, txtbox_workAddressTelephone, telephone, "Telephone #1");
		
		Utils.enterText(driver, txtbox_workAddressStreet1, street1, "Street");
		
		Utils.enterText(driver, txtbox_workAddressStreet2, street2, "Street");
		
		Utils.waitForElement(driver, btn_SavePersonalInfo);
		Utils.scrollIntoView(driver, btn_SavePersonalInfo);
		
		Utils.clickElement(driver, btn_SavePersonalInfo, "Save Button");

	}	
	
	
	public void verifyResidenceInfo(String residenceCity, String country, String apartment, String street1, String street2, String pinCode, String state, String mobile, String telephone)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 22/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'City/Town')]/following-sibling::td[contains(text(), '" + residenceCity + "')]", "xpath", residenceCity, "Residence Address");
	
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'State/Province')]/following-sibling::td[contains(text(), '" + state + "')]", "xpath", state, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Country')]/following-sibling::td[contains(text(), '" + country + "')]", "xpath", country, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Apt./Ste./Flr./Door No.')]/..//td[contains(text(), '" + "\u00A0" + apartment + "')]", "xpath", apartment, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Street')]/following-sibling::td[contains(text(), '" + street1 + "\u00A0" + street2 + "')]", "xpath", street1 + " " + street2, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Telephone')]/following-sibling::td[contains(text(), '" + telephone + "')]", "xpath", telephone, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Zip Code/Postal Code')]/following-sibling::td[contains(text(), '" + pinCode + "')]", "xpath", pinCode, "Residence Address");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Mobile Number')]/following-sibling::td[contains(text(), '" + mobile + "')]", "xpath", mobile, "Residence Address");

	}
	
	 public void verifyClientContactInfoPage() 
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Jan 2020
		  */
	    try
	    {
	       Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client's Contact Info");
	    }catch(Exception e)
	    {
		 Log.failsoft("Verification of Client's Contact Info Page Failed", driver);
	    }
	 }
	
	
	 public void verifyEditClientContactInfoPage() 
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Jan 2020
		  */
	    try{
	       Utils.softVerifyPageTitle(driver, "INSZoom.com - Edit Client's Contact Info");
	    }catch(Exception e){
		 Log.failsoft("Verification of Edit Client's Contact Info Page Failed", driver);
	    }
	 }


}
