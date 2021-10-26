package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class CM_ServiceCentreInfoPage extends LoadableComponent<CM_ServiceCentreInfoPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_SaveServiceCenter")
	WebElement btn_SaveServiceCenter;
	
	@FindBy(id = "cboAlert")
	WebElement dropDown_ReminderAlertFlag;
	
	@FindBy(id = "txtFiledDt")
	WebElement textBox_ReceiptNoticeDate;
	
	@FindBy(css = "select[name='cboBasis']")
	WebElement dropDown_BasisForBilling;
	
	@FindBy(css = "select[name='cboSRCCNTR']")
	WebElement dropDown_ServiceCenter;
	
	public CM_ServiceCentreInfoPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_ServiceCentreInfoPage(WebDriver driver) {
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
			Log.fail("Service center info Page did not open up. Site might be down.", driver, true);
		}
	}
	
	public void clickEditLink(String formName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 23 Oct 2019
		 */ 
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+ formName +"')]/../td/a[contains(text(),'Edit')]", "xpath", "Edit link");
	}
	
	
	public void editServiceCenterInfo(String formName,String serviceCenter,String basisForBilling, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 23 Oct 2019
		 */ 
		
		clickEditLink(formName);
		//Utils.waitForAllWindowsToLoad(2, driver);
		System.out.print(driver.getTitle());
		Utils.switchWindows(driver, "aty_case_form_srvc_ctr_link.aspx", "url", "false");
		Utils.selectOptionFromDropDown(driver, serviceCenter, dropDown_ServiceCenter);
		Utils.selectOptionFromDropDownByVal(driver, basisForBilling, dropDown_BasisForBilling);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 1); // add 1 days
		String fromDate = sdf.format(c.getTime());

		Utils.enterText(driver, textBox_ReceiptNoticeDate, fromDate, "entered receipt notice date");
		//Utils.selectOptionFromDropDownByVal(driver, "Y", dropDown_ReminderAlertFlag);
		Utils.clickElement(driver, btn_SaveServiceCenter, "Save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com :: List of Forms with Service Center Dates", "title", "false");	
	}
	
	
	public void verifyEditedFormDetails(String formName, String serviceCenter)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ formName +"')]/following-sibling::td[1][contains(text(),'"+ serviceCenter +"')]", "xpath", serviceCenter, "In service center page");
		
	}
	
	
	
}