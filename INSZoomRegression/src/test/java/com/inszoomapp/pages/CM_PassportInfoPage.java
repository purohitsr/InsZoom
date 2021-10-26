package com.inszoomapp.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_PassportInfoPage extends LoadableComponent<CM_PassportInfoPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Added By Souvik on 8 June 2021 
	@FindBy(xpath="//th[contains(text(), 'Identification Marks')]/following-sibling::td[1]")
	WebElement txt_IdentificationMark;
	
	//Added By Saurav on 21 Jan 2021 
	@FindBy(id = "txtIdentitymarks")
	WebElement txtbox_IdentityMark;
	
	@FindBy(id = "btn_SaveClientsadditionalinfo")
	WebElement btn_SaveClientsAdditionalinfo;
	
	@FindBy(id = "cboBnfGender")
	WebElement dropDown_gender;
	
	@FindBy(id = "btn_EditClientsAdditionalinfo")
	WebElement btn_EditClientsAdditionalinfo;
	
	@FindBy(id = "txtcboCountry")
	WebElement textBox_countryOfBirth;
	
	@FindBy(id = "txtBirthPlace")
	WebElement textBox_placeOfBirth;
	
	@FindBy(id = "txtBirthdate")
	WebElement textBox_birthYear;
	
	@FindBy(id = "cboFDD")
	WebElement dropDown_birthDate;
	
	@FindBy(id = "cbofromMM")
	WebElement dropDown_birthMonth;
	
	@FindBy(id = "btn_SavePassportInfo")
	WebElement btn_SavePassportInfo;
	
	@FindBy(id = "txtNationality")
	WebElement textBox_Nationality;
	
	@FindBy(id = "txtPassportExpiryDt")
	WebElement textBox_ExpirationDate;
	
	@FindBy(id = "txtPassportIssueDt")
	WebElement textBox_IssuingDate;
	
	@FindBy(id = "txtPassportIssueCntry")
	WebElement textBox_IssuingCnty;
	
	@FindBy(id = "txtLastName")
	WebElement textBox_LastName;
	
	@FindBy(id = "txtFirstName")
	WebElement textBox_FirstName;
	
	@FindBy(id = "txtPassportNbr")
	WebElement textBox_passportNumber;
	
	@FindBy(id = "btn_EditPassportdetails")
	WebElement btn_EditPassportdetails; 
	
	@FindBy(xpath = "//input[@name='txtIssueDt']")
	WebElement textBox_issuingDate;
	
	@FindBy(id="txtBirthPlace")
	WebElement txtbox_cityOfBirth;
	
	@FindBy(id = "txtltlPassportIssuingCntry")
	WebElement textBox_issuingCountry;
	
	@FindBy(id = "btn_SaveAdditionalDetails")
	WebElement btn_SaveEdittedAdditionalDetails;
	
	@FindBy(id = "txtltlPassportIssuingCntry")
	WebElement textBox_IssuingCountry;
	
	@FindBy(id = "txtIssuePlace")
	WebElement textBox_IssuePlace;
	
	@FindBy(id = "EditAdditionalPassportDetails")
	WebElement btn_EditAdditionalPassportDetails;
	
	@FindBy(id = "btn_SaveAdditionalPassportDetails")
	WebElement btn_SaveAdditionalPassportDetails;
	
	@FindBy(id = "cboCountryNationality")
	WebElement dropDown_countryOfNationality;
	
	@FindBy(css = "input[name='txtExpDt']")
	WebElement textBox_expirationDate;
	
	@FindBy(id="txtlname")
	WebElement textBox_lastName;
	
	@FindBy(id="txtfname")
	WebElement textBox_firstName;
	
	@FindBy(id="txtPsptNo")
	WebElement textBox_additionalPassportNumber;
	
	@FindBy(id="btn_AddAdditionalPassportDetails")
	WebElement btn_AddAdditionalPassportDetails;
	
	@FindBy(xpath="//th[contains(text(), 'Country of Birth')]/following-sibling::td[1]")
	WebElement txt_Country;
	
	@FindBy(xpath="//th[contains(text(), 'Date of Birth')]/following-sibling::td[1]")
	WebElement txt_DoB;	
	
	@FindBy(id="btn_SavePassportInfo")
	WebElement btn_SavePassportDetails;
	
	@FindBy(css = "input[name='txtcboCountry']")
	WebElement txtbox_CountryOfBirth;
	
	@FindBy(css = "input[title='Edit Passport details']")
	WebElement btn_EditButton;
	
	@FindBy(css = "select[id='cbofromMM']")
	WebElement dropdown_DOBMM;

	@FindBy(css = "select[name='cboFDD']")
	WebElement dropdown_DOBDD;

	@FindBy(css = "input[name='txtBirthdate']")
	WebElement textbox_DOBYYYY;
	
	public CM_PassportInfoPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_PassportInfoPage(WebDriver driver) {
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


	public void editPassportDetails(String day, String year, String month, String country, String cityNew) throws Exception 
	{
		/*
		 * Modified By: Likitha Krishna
		 * Modified On: 18/10/2019
		 */
		
		Utils.clickElement(driver, btn_EditButton, "Edit Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Passport Info", "title", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, month, dropdown_DOBMM);
		
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_DOBDD);
		
		Utils.enterText(driver, textbox_DOBYYYY, year, "Year");		
			
		Utils.enterText(driver, txtbox_CountryOfBirth, country, "Country");
		
		Utils.enterText(driver, txtbox_cityOfBirth, cityNew, "City");

		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		
		Utils.clickElement(driver, btn_SavePassportDetails, "Save Button");
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");

	}
	
	
	public void getClientDetailsAndUpdateExcel(String workbookNameWrite, String sheetName) throws Exception {
		/*
		 * Modified By: Souvik Ganguly
		 * Modified On: 06/03/2021
		 */

		String identificationMark = "";
		
		
		try {
			Utils.waitForElement(driver, txt_IdentificationMark);
			identificationMark = txt_IdentificationMark.getText();
			Log.message("Fetched identification mark as: " + identificationMark);

		} catch (Exception e) {
			Log.fail("Unable to fetch identification mark. ERROR :\n\n " + e.getMessage(), driver,
					true);
		}
		try{
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_IdentificationMark", sheetName, "Value", identificationMark);
		}
		catch (Exception e){
		Log.fail("Unable to update excel with edited passport information. ERROR :\n\n " + e.getMessage(), driver,
					false);
		}
	}
	
	
	public void clickAddAdditionalPassportDetailsButton()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, btn_AddAdditionalPassportDetails, "add additional passport details button");
	}
	
	
	
	public void addAdditionalPassportDetails(String passportnumber,String firstName,String lastName,String nationality, String passportExpiresOnDate,String issuingCountry, String issuingDate) throws Exception {

		/*
	     * Created By : M Likitha Krishna
	     * Created On : 08 Nov 2019
		 */ 
			clickAddAdditionalPassportDetailsButton();

			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - Additional Details", "title", "false");
		
			Utils.enterText(driver, textBox_additionalPassportNumber, passportnumber, "passport textbox");
			
			Utils.enterText(driver, textBox_firstName, firstName, "first name testbox");

			Utils.enterText(driver, textBox_lastName, lastName, "last name testbox");
			
			Utils.enterText(driver, textBox_issuingCountry, issuingCountry, "issuing country");
			
			Utils.enterText(driver, textBox_issuingDate, issuingDate, "issuing date");
			
			Utils.enterText(driver, textBox_expirationDate, passportExpiresOnDate, "passport expires on date");
			
			Utils.selectOptionFromDropDown(driver, nationality, dropDown_countryOfNationality);
		
			Utils.clickElement(driver, btn_SaveAdditionalPassportDetails, "save button");
			
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
			
	}
	
	
	public void verifyPassportDetailsAdded(String passportnumber,String firstName,String lastName,String nationality, boolean screenshot) 
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 08 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Passport #')]/../td[contains(text(),'" +passportnumber+ "')]", "xpath", passportnumber, "in passport number");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Name')]/../td[contains(text(),'"+firstName+"  "+lastName+"')]", "xpath", firstName+lastName, "Name");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Country of Nationality')]/../td[contains(text(),'" +nationality+ "')]", "xpath", nationality, "country of nationality");
	}
	
	
	public void clickEditAdditionalPassportDetailsButton()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, btn_EditAdditionalPassportDetails, "edit additional passport details button");
	}
	
	
	public void editAdditionalPassportDetails(String day, String year, String month, String country, String cityNew) throws Exception 
	{
		/*
		 * Modified By: Likitha Krishna
		 * Modified On: 18/10/2019
		 */
			
		Utils.clickElement(driver, btn_EditButton, "Edit Button");
	
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Passport Info", "title", "false");
		
		/*Utils.selectOptionFromDropDownByVal(driver, month, dropdown_DOBMM);
		
		Utils.selectOptionFromDropDownByVal(driver, day, dropdown_DOBDD);
		
		Utils.enterText(driver, txtbox_cityOfBirth, cityNew, "City of Birth");
		
		Utils.enterText(driver, textbox_DOBYYYY, year, "Year");		
			
		Utils.enterText(driver, txtbox_CountryOfBirth, country, "Country");
		
		Utils.enterText(driver, txtbox_cityOfBirth, cityNew, "City of Birth");

*/		
		
		
		Utils.enterText(driver, txtbox_IdentityMark, "Black Mole", "Identification mark");
		
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		
		Utils.clickElement(driver, btn_SavePassportDetails, "Save Button");
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
	
	} 
		 

	
	public void verifyEdittedAdditionalPassportDetails(String edittedFirstName, String issuePlace,String issuingCountry)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Name')]/../td[1][contains(text(),'"+ edittedFirstName +"')]", "xpath", edittedFirstName, "Name");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Issuance Place')]/../td[1][contains(text(),'"+ issuePlace +"')]", "xpath", issuePlace, "Issuance Place");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Issuing Country')]/../td[2][contains(text(),'"+ issuingCountry +"')]", "xpath", issuingCountry, "Issuing Country");
	}
	
	
	public void editMainPassportDetails(String passportnumber,String firstName,String lastName,String nationality, String passportExpiresOnDate,String issuingCountry, String issuingDate) throws Exception {

		/*
	     * Created By : M Likitha Krishna
	     * Created On : 17 Jan 2020
		 */ 
			Utils.clickElement(driver, btn_EditPassportdetails, "edit button");

			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - Edit Passport Info", "title", "false");
		
			Utils.enterText(driver, textBox_passportNumber, passportnumber, "passport textbox");
			
			Utils.enterText(driver, textBox_FirstName, firstName, "first name testbox");

			Utils.enterText(driver, textBox_LastName, lastName, "last name testbox");
			
			Utils.enterText(driver, textBox_IssuingCnty, issuingCountry, "issuing country");
			
			Utils.enterText(driver, textBox_IssuingDate, issuingDate, "issuing date");
			
			Utils.enterText(driver, textBox_ExpirationDate, passportExpiresOnDate, "passport expires on date");
			
			Utils.enterText(driver, textBox_Nationality, nationality, "nationality");
		
			Utils.clickElement(driver, btn_SavePassportInfo, "save button");
			
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
	}	
	
	
		public void verifyPassportInfoPageTitle() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 30 Jan 2020
		  */
	    try{
	       Utils.softVerifyPageTitle(driver, "INSZoom.com - View Passport Info");
	    }catch(Exception e){
		 Log.failsoft("Verification of View Client's Profile Page  Failed", driver);
	    }
	    
	  
}
	
	 
	  
	  public void verifyPassportInfoAdditionalDetailsPage() throws Exception 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 30 Jan 2020
			  */
		    try{
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Additional Details", "title", "false");
			 Utils.softVerifyPageTitle(driver, "INSZoom.com - Additional Details");
			 driver.close();
			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
		    }catch(Exception e){
			 Log.failsoft("Verification of Additional Details Failed", driver);
			 driver.close();
			 Utils.waitForAllWindowsToLoad(1, driver);
			 Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
		    }
		    
		  
	}
	
	
	public void addPersonalDetails(String gender, String day, String month, String year, String placeOfBirth, String country, String citizenship, String countryName) throws Exception
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 27 Jan 2020
		 */ 
		Utils.clickElement(driver, btn_EditPassportdetails, "edit button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Passport Info", "title", "false");
		//Commented By Saurav as the DOB,Year,Country Of Birth is moved to  Client Profile Page .
		
/*		Utils.selectOptionFromDropDown(driver, month, dropDown_birthMonth);
		Utils.selectOptionFromDropDown(driver, day, dropDown_birthDate);
		Utils.enterText(driver, textBox_birthYear, year, "year");
		 //country --> country of birth // citizenship --> nationality // countryName --> country of nationality
		Utils.enterText(driver, textBox_placeOfBirth, placeOfBirth, "place of birth");
		Utils.enterText(driver, textBox_countryOfBirth, country, "country of birth");*/
	/*	Utils.enterText(driver, textBox_Nationality, citizenship, "citizenship");
		Utils.selectOptionFromDropDown(driver, countryName, dropDown_countryOfNationality);*/
		Utils.enterText(driver, txtbox_IdentityMark, "Black Mole", "Identification mark");
		Utils.clickElement(driver, btn_SavePassportInfo, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
		
		Utils.clickElement(driver, btn_EditClientsAdditionalinfo, "edit additional info button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Client Additional Info", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, gender, dropDown_gender);
		Utils.clickElement(driver, btn_SaveClientsAdditionalinfo, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Passport Info", "title", "false");
	}
	
}
