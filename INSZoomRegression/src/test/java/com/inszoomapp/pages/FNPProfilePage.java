package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;


public class FNPProfilePage extends LoadableComponent<FNPProfilePage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	//added By Saurav on 12th March
	
	@FindBy(css="div[id='profile-education-background']>div>div>div>div>header>h2")
	WebElement tab_Education;
		
	@FindBy(xpath="//li[@class='active']/a[@data-toggle='tab' and text()='About']")
	WebElement activetab_About;
	
	
	@FindBy(xpath="//a[contains(text(), 'Background')]")
	WebElement tab_Background;
	
	@FindBy(css="a[href='#tab-activity']")
	WebElement tab_activity;	
	
	
	public FNPProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void verifyClientName(String clientName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Born as')]/following-sibling::td[contains(text(), '" + clientName + "')]", "xpath", clientName, "Client Profile");
	}
	
	
	public void verifyDoB(String clientDoB)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Born on')]/following-sibling::td[contains(text(), '" + clientDoB.trim() + "')]", "xpath", clientDoB, "Client Profile");
	}
	
	
	public void verifyBirthCountry(String clientBornCountry)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Country of Birth')]/following-sibling::td[contains(text(), '" + clientBornCountry + "')]", "xpath", clientBornCountry, "Client Profile");
	}
	
	
	public void verifyEmployeeID(String employeeId)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Employee Id')]/following-sibling::td[contains(text(), '" + employeeId + "')]", "xpath", employeeId, "Client Profile");
	}
	
	
	public void verifyResidenceCity(String city)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'City')]/following-sibling::td[contains(text(), '" + city + "')]", "xpath", city, "Client Profile");
	}
	
	
	public void clickBackgroundTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 19 Nov 2019
		 */
		
		Utils.clickElement(driver, tab_Background, "'Background' tab");
	}
	
	
	public void verifySchoolHistory(String schoolNameNew, String historyDateFNP, String universityName, String degree, String fieldOfStudy, String numberForPersonalInfo )
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.waitForElementPresent(driver, "//div[@id='profile-education-background']//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@id='profile-education-background']//span[contains(text(), 'Loading...')]", "xpath");
		
		Utils.waitForAjax(driver);
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='SchoolName']/../../following-sibling::tbody//td[contains(text(), '" + schoolNameNew + "')]", "xpath", schoolNameNew, "School Name");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='UniversityBoard']/../../following-sibling::tbody//td[contains(text(), '" + universityName + "')]", "xpath", universityName, "University Name");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Degree']/../../following-sibling::tbody//td[contains(text(), '" + degree + "')]", "xpath", degree, "Degree");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='FieldOfStudy']/../../following-sibling::tbody//td[contains(text(), '" + fieldOfStudy + "')]", "xpath", fieldOfStudy, "Field of Study");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='FromDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "From Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='ToDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "To Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Phone']/../../following-sibling::tbody//td[contains(text(), '" + numberForPersonalInfo + "')]", "xpath", numberForPersonalInfo, "Phone Number");
	}
	
	
	public void verifyEmploymentHistory(String jobNew, String historyDateFNP, String employer, String numberForPersonalInfo)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.waitForElementPresent(driver, "//div[@id='profile-employment-background']//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@id='profile-employment-background']//span[contains(text(), 'Loading...')]", "xpath");
		
		Utils.waitForAjax(driver);
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='FromDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "From Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='ToDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "To Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='OccupationTitle']/../../following-sibling::tbody//td[contains(text(), '" + jobNew + "')]", "xpath", jobNew, "Designation");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Phone']/../../following-sibling::tbody//td[contains(text(), '" + numberForPersonalInfo + "')]", "xpath", numberForPersonalInfo, "Phone Number");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='EmployerName']/../../following-sibling::tbody//td[contains(text(), '" + employer + "')]", "xpath", employer, "Company");
	}
	
	
	public void verifyAddressHistory(String cityEditted, String historyDateFNP, String countryNew, String streetNumber, String state)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Nov 2019
		 */ 
		
		Utils.waitForElementPresent(driver, "//div[@id='profile-address-background']//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//div[@id='profile-address-background']//span[contains(text(), 'Loading...')]", "xpath");
		
		Utils.waitForAjax(driver);
		
		String address = cityEditted + " " + state + " " + countryNew;
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + address + "')]/following-sibling::td[contains(text(),'" + historyDateFNP + "')]/following-sibling::td[contains(text(),'" + historyDateFNP + "')]", "xpath", "Past address", "Background details"); 
		
//		Utils.verifyIfDataPresent(driver, "//th[@data-field='SuiteApt']/../../following-sibling::tbody//td[contains(text(), '" + address + "')]", "xpath", address, "Street");
//	
//		Utils.verifyIfDataPresent(driver, "//th[@data-field='FromDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "From Date");
//		
//		Utils.verifyIfDataPresent(driver, "//th[@data-field='ToDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "To Date");
		
	}
	
	
	public void clickActivityTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Dec 2019
		 */ 
		
		Utils.clickElement(driver, tab_activity, "Activity tab");
		Utils.waitForElementPresent(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForAjax(driver);
		
	}
	
	
	public void verifyIfEConsentLogged(String eConsentTitle)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Dec 2019
		 */ 
		
		boolean truth = false;
		int i = 1;
		
		do
		{
			Utils.waitForElement(driver, "//span[contains(@class,'k-state-selected') and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");
			if(Utils.isElementPresent(driver, "//a[contains(text(), '" + eConsentTitle + "')]/../../following-sibling::td//i[@class='fa fa-thumbs-up fa-stack-1x fa-inverse']", "xpath"))
			{
				Log.message("", driver, true);
				Log.pass("e-consent is present in the activity log");
				truth = true;
				break;
			}
			
			else
			{
				Utils.clickDynamicElement(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-e']", "xpath", "Next page");
				i += 1;
			}
		}while(Utils.isElementPresent(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-e']", "xpath"));
	
		if(!truth)
		{
			Log.fail("e-Consent is not present in Activity log", driver, true);
		}
	}
	
	
	public void verifyContactDetails(String phoneNumber1, String phoneNumber2, String phoneNumber3, String linkedIn, String skype, String facebook, String twitter)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 Jan 2019
		 */ 
		
		//Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Home')]/following-sibling::td[contains(text(), '" + phoneNumber1 + "')]", "xpath", phoneNumber1, "Home Number");
		
		//Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Work')]/following-sibling::td[contains(text(), '" + phoneNumber1 + "')]", "xpath", phoneNumber1, "Work Number");
		
		//Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Mobile')]/following-sibling::td[contains(text(), '" + phoneNumber3 + "')]", "xpath", phoneNumber3, "Mobile Number");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Linkedin')]/following-sibling::td/a[contains(text(), '" + linkedIn + "')]", "xpath", linkedIn, "LinkedIn");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Facebook')]/following-sibling::td/a[contains(text(), '" + facebook + "')]", "xpath", facebook, "Facebook");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Twitter')]/following-sibling::td/a[contains(text(), '" + twitter + "')]", "xpath", twitter, "Twitter");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Skype')]/following-sibling::td[contains(text(), '" + skype + "')]", "xpath", skype, "Skype");
	}
	
	
	public void verifyPersonalInfo(String country, String wifeFirstName, String maritalStatus)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 Jan 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Other Name used')]/following-sibling::td[contains(text(), '" + wifeFirstName + "')]", "xpath", wifeFirstName, "Other name");
		
		//Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Citizen of')]/following-sibling::td[contains(text(), '" + country + "')]", "xpath", country, "Citizen Of");
	
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Marital status')]/following-sibling::td[contains(text(), '" + maritalStatus + "')]", "xpath", maritalStatus, "Marital Status");
		
	}
	
	
	public void verifyResidenceInfo(String countryNew, String cityNew, String streetNumber, String state, String numberForPersonalInfo)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Jan 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Suite/Apt#')]", "xpath", numberForPersonalInfo, "Suite/Apt#");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Address')]", "xpath", streetNumber, "Address");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'City')]", "xpath", cityNew, "City");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'State')]", "xpath", state, "State");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Country')]", "xpath", countryNew, "Country");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Postal Code')]", "xpath", numberForPersonalInfo, "Postal Code");
	}
	
	
	public void verifyBirthInfo(String clientDOB, String cityNew, String country)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Jan 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Born on')]/following-sibling::td[contains(text(), '" + clientDOB.trim() + "')]", "xpath", clientDOB, "Client Profile");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'Country of Birth')]/following-sibling::td[contains(text(), '" + country + "')]", "xpath", country, "Client Profile");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), 'City/Town/Village of Birth')]/following-sibling::td[contains(text(), '" + cityNew + "')]", "xpath", cityNew, "Client Profile");
		
		
		
	}

	
	public void verifyProfilePage() 
	{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 12th March 2020
			  */
    try{
			 
	     Utils.verifyIfStaticElementDisplayed(driver, activetab_About, "In header section", "In Profiles Page");
			
       }catch(Exception e){
		 Log.failsoft("Verification of Active Tab failed", driver);
	    }

	} 
	
	public void verifyBackgroundSection() 
	{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 12th March 2020
			  */
    try{
			 
	     Utils.verifyIfStaticElementDisplayed(driver, tab_Education, "In header section", "In Profiles Page");
			
       }catch(Exception e){
		 Log.failsoft("Verification of Education Tab failed", driver);
	    }

	} 
	
}
