package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CM_TravelInfoPage extends LoadableComponent<CM_TravelInfoPage> 
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(css = "input[id='btn_AccessthelatestI-94onCBPWebsite']")
	WebElement btn_Access_I_94;

	@FindBy(css = "input[name='passportNumber']")
	WebElement txtbox_PassportNumber;

	@FindBy(css = "select[name='passportCountry']")
	WebElement dropdown_PassportCountry;

	@FindBy(css = "button[id='consent']")
	WebElement btn_Continue;

	@FindBy(css = "input[id='firstName']")
	WebElement txtbox_FirstName;

	@FindBy(css = "input[id='lastName']")
	WebElement txtbox_LastName;

	@FindBy(css = "input[id='birthDay']")
	WebElement txtbox_BirthDate;

	@FindBy(css = "select[name='birthMonth']")
	WebElement dropdown_BirthMonth;

	@FindBy(css = "input[id='birthYear']")
	WebElement txtbox_BirthYear;

	@FindBy(css = "input[value='Next']")
	WebElement btn_Next;;
	
	@FindBy(id="txtDepPlace")
	WebElement txtbox_DeparturePlace;
	
	@FindBy(css = "input[alt='Add new Arrival/Departure info ']")
	WebElement btn_AddTravelInfo;
	
	@FindBy(css = "select[id='cboCase']")
	WebElement dropdown_LinkedCase;
	
	@FindBy(css = "input[name='txtArrAdmsnNum']")
	WebElement txtbox_ArrivalAdmissionNumber;

	@FindBy(css = "select[name='cboCountry']")
	WebElement dropdown_ArrivalCountry;

	@FindBy(css = "select[name='cboTypeOfTravl']")
	WebElement dropdown_TypeOfTravel;
	
	@FindBy(css = "input[id='btn_SaveArrivalDepartureInfo']")
	WebElement btn_SaveTravelInfo;
	

	public CM_TravelInfoPage(WebDriver driver) {
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
			Log.fail("Travel info Page did not open up. Site might be down.", driver, true);
		}
	}

	
	public void addTravelInfo(String arrivalAdmissionNumber) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 22/10/2019
		 */
		
		Utils.clickElement(driver, btn_AddTravelInfo, "Add Travel Info");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Arrival/Departure Info", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, "Not Applicable", dropdown_LinkedCase);
		
		Utils.enterText(driver, txtbox_ArrivalAdmissionNumber, arrivalAdmissionNumber, "Arrival Admission Number");

		Utils.selectOptionFromDropDownByVal(driver, "R", dropdown_TypeOfTravel);
		
		Utils.selectOptionFromDropDownByVal(driver, "DZA", dropdown_ArrivalCountry);

		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");

		Utils.clickElement(driver, btn_SaveTravelInfo, "Save Button");
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Arrival/Departure Info", "title", "false");

	}
	
	
	public void verifyTravelInfo(String arrivalAdmissionNumber)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 22/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + arrivalAdmissionNumber + "')]", "xpath", arrivalAdmissionNumber, "Arrival Admission Number");
	}
	
	
	public void editTravelInfo(String arrivalAdmissionNumber, String departurePlace) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + arrivalAdmissionNumber + "')]", "xpath", "Travel Info");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Arrival/Departure Info", "title", "false");
		
		Utils.enterText(driver, txtbox_DeparturePlace, departurePlace, "Departure Place");
		
		Utils.waitForElement(driver, btn_SaveTravelInfo);
		Utils.scrollToElement(driver, btn_SaveTravelInfo);
		Utils.clickElement(driver, btn_SaveTravelInfo, "Save Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Arrival/Departure Info", "title", "false");
	}
	
	
	public void clickGetI_94(String firstName, String lastName, String birthDate, String birthMonth, String birthYear, String passportNumber, String country) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
	    
		Utils.clickElement(driver, btn_Access_I_94, "Access I-94 (CBP)");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "I94 - Official Website", "title", "false");
		

	    ((JavascriptExecutor) driver).executeScript("scroll(0,250);");

	    Utils.clickElement(driver, btn_Continue, "Continue Button");

	    Utils.enterText(driver, txtbox_FirstName, firstName, "First Name");
	    Utils.enterText(driver, txtbox_LastName, lastName, "Last Name");
	    Utils.enterText(driver, txtbox_BirthDate, birthDate, "Birth Date");
		Utils.selectOptionFromDropDownByVal(driver, birthMonth, dropdown_BirthMonth);
		Utils.enterText(driver, txtbox_BirthYear, birthYear, "Birth Year");

		Utils.enterText(driver, txtbox_PassportNumber, passportNumber, "Passport Number");
		Utils.selectOptionFromDropDownByVal(driver, country, dropdown_PassportCountry);

		Utils.clickElement(driver, btn_Next, "Next Button");

		Utils.verifyIfDataPresent(driver, "//font[contains(text(), 'No record found for traveler')]", "xpath", "No Record found", "Traveller Information");
		
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Arrival/Departure Info", "title", "false");
		
	}
	
	public void verifyArrivalDeparturePage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 06 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com - List of Arrival/Departure Info");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - List of Arrival/Departure Info Page Failed ", driver);
	    }
		
		
	}
	

}
