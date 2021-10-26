package com.inszoomapp.pages;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_Client_Relative_VisaPage extends LoadableComponent<CM_Client_Relative_VisaPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "txtissDate")
	WebElement textBox_dateOfIssue;
	
	@FindBy(id = "txtexpdate")
	WebElement textBox_dateOfExpiration;
	
	@FindBy(id = "Nonimmigrant")
	WebElement dropDown_visaType;
	
	@FindBy(id = "btn_SaveVISAdetails")
	WebElement btn_SaveVisaDetails;
	
	
	@FindBy(id = "txtVisaNbr")
	WebElement textBox_VisaNumber;
	
	@FindBy(id = "cboCountry")
	WebElement dropDown_Country;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_GridComposeEmailBtn")
	WebElement icon_AddVisaRelative;
	
	public CM_Client_Relative_VisaPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_Client_Relative_VisaPage(WebDriver driver) {
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
			Log.fail("Client relative visa Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	//public void addRelativeVisaDetails(String country,String visaNumber,String workbookNameWrite,String sheetName,boolean screenshot) throws Exception {
	public void addRelativeVisaDetails(String country,String visaNumber,String dateOfIssue, String dateOfExpiration) throws Exception {
	/*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */
		
		Utils.clickElement(driver, icon_AddVisaRelative, "Add visa for relative icon");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add VISA Details", "title", "false");
		Utils.selectOptionFromDropDownByVal(driver, country, dropDown_Country);
		Utils.enterText(driver, textBox_VisaNumber, visaNumber, "visa number");
		
		Utils.selectOptionFromDropDown(driver, "Automation Scripting", dropDown_visaType);
		Utils.enterText(driver, textBox_dateOfIssue, dateOfIssue, "date of issue for visa");
		Utils.enterText(driver, textBox_dateOfExpiration, dateOfExpiration, "date of expiration for visa");

		// Write the data in excel
//		String directory = System.getProperty("user.dir");
//		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
//		writeExcel.setCellData("QA_ALoginRelativeVisaNumber", sheetName, "Value", visaNumber);				
				
		Utils.clickElement(driver, btn_SaveVisaDetails, "Save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");		
		
	}
	
	
	public void verifyAddedVisa(String loginRelativeVisaNumber,boolean screenshot) throws Exception {
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		 */
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ loginRelativeVisaNumber +"')]", "xpath", loginRelativeVisaNumber, "added visa number for relative");
	}

	
	
}