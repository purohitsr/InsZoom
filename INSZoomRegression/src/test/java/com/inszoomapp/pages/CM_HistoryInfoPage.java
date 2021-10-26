package com.inszoomapp.pages;

import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_HistoryInfoPage extends LoadableComponent<CM_HistoryInfoPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id="cbofromMM")
	WebElement dropdown_fromMonth;
	
	@FindBy(id="cboFDD")
	WebElement dropdown_fromDate;
	
	@FindBy(id="txtfromdate")
	WebElement txtbox_fromYear;
	
	@FindBy(id="cboToMM")
	WebElement dropdown_toMonth;
	
	@FindBy(id="cboTDD")
	WebElement dropdown_toDate;
	
	@FindBy(id="txttodate")
	WebElement txtbox_toYear;
	
	@FindBy(id="txtSchlDegree")
	WebElement txtbox_degree;
	
	@FindBy(id="txtFldStdy")
	WebElement txtbox_fieldOfStudy;
	
	@FindBy(id="txtUniversity")
	WebElement txtbox_university;
	
	@FindBy(id="txtPhone")
	WebElement txtbox_phoneNumber;
	
	@FindBy(id="btn_EditCurrentMarriageInfo")
	WebElement btn_SaveMarriageHistoryEditted;
	
	@FindBy(id="txtMarriagePlace")
	WebElement txtbox_MarriagePlace;
	
	@FindBy(id="btn_SaveClientsRelativesDetails")
	WebElement btn_SaveRelative;
	
	@FindBy(id="cmbSpouseRelation_cmbSpouse")
	WebElement dropdown_Relation;
	
	@FindBy(id="txtFname")
	WebElement txtbox_RelativeFirstName;
	
	@FindBy(id="txtLname")
	WebElement txtbox_RelativeLastName;
	
	@FindBy(xpath = "//a[contains(text(), 'Add new')]")
	WebElement lnk_AddNewRelative;
	
	@FindBy(id="btn_AddNewMarriageHistory")
	WebElement btn_AddMarriageHistory;
	
	@FindBy(id="btn_EditEmploymentHistory")
	WebElement btn_SaveEmploymentHistoryEditted;
	
	@FindBy(css = "textarea[id='txtEmpName']")
	WebElement txtbox_Employer;

	@FindBy(css = "textarea[id='txtOccupn']")
	WebElement txtbox_Occupation;
	
	@FindBy(css = "input[id='btn_SaveEmploymentHistory']")
	WebElement btn_SaveEmploymentHistory;
	
	@FindBy(id="btn_AddNewEmploymentHistory")
	WebElement btn_AddEmploymentHistory;
	
	@FindBy(id="btn_SaveEducationHistory")
	WebElement btn_SaveEducationHistory;
	
	@FindBy(css = "textarea[id='txtSchlName']")
	WebElement txtbox_School;
	
	@FindBy(css = "select[id='cboSchlType']")
	WebElement dropdown_School;
	
	@FindBy(id="btn_AddNewEducationHistory")
	WebElement btn_AddEducationHistory;
	
	@FindBy(id="btn_AddNewAddressHistory")
	WebElement btn_AddAddressHistory;
	
	@FindBy(id="cboCountryAddr")
	WebElement dropdown_Country;
	
	@FindBy(id="txtStNo")
	WebElement txtbox_steetNumber;
	
	@FindBy(id="txtstate")
	WebElement txtbox_state;

	@FindBy(css = "input[id='txtcity']")
	WebElement txtbox_City;
	
	@FindBy(id="btn_SaveAddressHistory")
	WebElement btn_SaveAddressHistory;
	
	public CM_HistoryInfoPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);

	}

	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("History info Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void addAddressHistory(String country, String city, String streetNumber, String state, String day, String month, String year) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		Utils.clickElement(driver, btn_AddAddressHistory, "Add New Address History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Address History", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, country, dropdown_Country);
		
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_fromMonth);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_fromDate);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_toDate);
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_toMonth);
		Utils.enterText(driver, txtbox_fromYear, year, "From Year");
		Utils.enterText(driver, txtbox_toYear, year, "To year");
		
		Utils.enterText(driver, txtbox_City, city, "City in Address History");
		
		Utils.enterText(driver, txtbox_state, state, "State");
		
		Utils.enterText(driver, txtbox_steetNumber, streetNumber, "Street Number");
			
		Utils.clickElement(driver, btn_SaveAddressHistory, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	
	}
	
	
	public void editAddressHistory(String cityNew, String cityOld) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + cityOld + "')]/../preceding-sibling::td//img[@title='Edit Address History']", "xpath", "Edit Address History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Address History", "title", "false");
		
		Utils.enterText(driver, txtbox_City, cityNew, "City in Address History");
			
		Utils.clickElement(driver, btn_SaveAddressHistory, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	
	}
	
	
	public void verifyAddressHistory(String city, String countryNew, String streetNumber, String state)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		String address = streetNumber + " " + city + "  " + state + "  " + countryNew;
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + address + "')]", "xpath", address, "Address History");
	}
	
	
	public void addEducationHistory(String type, String name, String universityName, String degree, String fieldOfStudy, String day, String month, String year, String numberForPersonalInfo) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickElement(driver, btn_AddEducationHistory, "Add Education History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Education History", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, type, dropdown_School);
		
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_fromMonth);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_fromDate);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_toDate);
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_toMonth);
		Utils.enterText(driver, txtbox_fromYear, year, "From Year");
		Utils.enterText(driver, txtbox_toYear, year, "To year");
		
		Utils.enterText(driver, txtbox_degree, degree, "Degree");
		
		Utils.enterText(driver, txtbox_fieldOfStudy, fieldOfStudy, "Field of Study");
		
		Utils.enterText(driver, txtbox_university, universityName, "University");
		
		Utils.enterText(driver, txtbox_phoneNumber, numberForPersonalInfo, "Mobile Number");
		
		Utils.enterText(driver, txtbox_School, name, "School Name in Education History");
			
		Utils.clickElement(driver, btn_SaveEducationHistory, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
		
	}
	
	
	public void verifyEducationHistory(String school, String degree, String fieldOfStudy)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + school + "')]", "xpath", school, "Education History");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + degree + "')]", "xpath", degree, "Education History");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + fieldOfStudy + "')]", "xpath", fieldOfStudy, "Education History");
	}
	
	
	public void editEducationHistory(String oldSchool, String newSchool) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + oldSchool + "')]/../preceding-sibling::td//img[@title='Edit Education History']", "xpath", "Edit Education History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Education History", "title", "false");
		
		Utils.enterText(driver, txtbox_School, newSchool, "School Name in Education History");
			
		Utils.clickElement(driver, btn_SaveEducationHistory, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
		
	}
	
	
	public void addEmploymentHistory(String employer, String occupation, String day, String month, String year, String numberForPersonalInfo) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickElement(driver, btn_AddEmploymentHistory, "Add Education History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Employment History", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_fromMonth);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_fromDate);
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_toDate);
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_toMonth);
		Utils.enterText(driver, txtbox_fromYear, year, "From Year");
		Utils.enterText(driver, txtbox_toYear, year, "To year");
		
		Utils.enterText(driver, txtbox_Employer, employer, "Employer Name in Employment History");
		
		Utils.enterText(driver, txtbox_Occupation, occupation, "Occupation in Employment History");
		
		Utils.enterText(driver, txtbox_phoneNumber, numberForPersonalInfo, "Telephone Number");
			
		Utils.clickElement(driver, btn_SaveEmploymentHistory, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
		
	}
	
	
	public void verifyEmploymentHistory(String occupation, String employer)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + occupation + "')]", "xpath", occupation, "Employment History");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + employer + "')]", "xpath", employer, "Employment History");
	}

	
	public void editEmploymentHistory(String jobOld, String jobNew) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + jobOld + "')]/../preceding-sibling::td//img[@title='Edit Employment History']", "xpath", "Edit Education History Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Employment History", "title", "false");
		
		Utils.enterText(driver, txtbox_Occupation, jobNew, "Job in Employment History");
			
		Utils.clickElement(driver, btn_SaveEmploymentHistoryEditted, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
		
	}
	
	
	public void addMarriageHistory(String fName, String lName, String relation) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickElement(driver, btn_AddMarriageHistory, "Add Marriage History");
		
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		
		Utils.clickElement(driver, lnk_AddNewRelative, "Add New Relative link");
		
		Utils.enterText(driver, txtbox_RelativeFirstName, fName, "First Name");
		Utils.enterText(driver, txtbox_RelativeLastName, lName, "Last Name");
		
		Utils.selectOptionFromDropDown(driver, relation, dropdown_Relation);
		
		Utils.clickElement(driver, btn_SaveRelative, "Save Button");
	}
	
	
	public void verifyMarriageHistory(String fName)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + fName + "')]", "xpath", fName, "as Spouse in Marriage History");
	}
	
	
	public void verifyEdittedMarriageHistory(String place)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + place + "')]", "xpath", place, "as Marriage Place in Marriage History");
	}
	
	
	public void editMarriageHistory(String fName, String place) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 17/10/2019 
		 */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + fName + "')]/..//img[@title='Edit Record']", "xpath", "Add Marriage History");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Current Marriage Details", "title", "false");
	
		
		Utils.enterText(driver, txtbox_MarriagePlace, place, "Marriage Place");
		
		Utils.clickElement(driver, btn_SaveMarriageHistoryEditted, "Save Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");	
	}
	
	
	public void clickNewAddressHistory() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 05 Feb 2020 
		 */
		
		Utils.clickElement(driver, btn_AddAddressHistory, "Add Address History");
		
		
	}
	
	
	public void verifyAddHistoryPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 05 Feb 2020 
		 */
		try{
	        Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Address History", "title", "false");
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Add Address History");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }catch(Exception e){
		Log.failsoft("Verification of INSZoom.com - Add Address History Failed ", driver);
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }
		
		
	}
	
	
	public void clickNewEducationHistory() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 05 Feb 2020 
		 */
		
		Utils.clickElement(driver, btn_AddEducationHistory, "Add Address History");
		
		
	}
	
	
	public void verifyEducationHistoryPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 05 Feb 2020 
		 */
		try{
	        Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Education History", "title", "false");
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Add Education History");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Add Education History Failed ", driver);
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }
		
		
	}
	

	public void clickAddNewEmploymentHistory() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 06 Feb 2020 
		 */
		
		Utils.clickElement(driver, btn_AddEmploymentHistory, "Add Employment History");
		
		
	}
	
	
	public void verifyAddEmploymentHistoryPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 10 Feb 2020 
		 */
		try{
	        Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Employment History", "title", "false");
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Add Employment History");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Add Employment History Failed ", driver);
		 driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com : Client History Info", "title", "false");
	    }
		
		
	}
	

	
}
