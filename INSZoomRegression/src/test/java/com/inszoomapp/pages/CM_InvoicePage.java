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

public class CM_InvoicePage extends LoadableComponent<CM_InvoicePage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//th[@id='PAGEHDR'][contains(text(),'List of Invoice')]")
	WebElement header_listOfInvoices;
	
	@FindBy(id = "comboBox_1")
	WebElement dropDown_FeeTemplate;
	
	@FindBy(id = "btn_AddCasetoInvoice")
	WebElement btn_AddCasetoInvoice;
	
	@FindBy(id = "btn_AddCaseForInvoice")
	WebElement btn_AddCaseForInvoice;
	
	@FindBy(id = "btn_GoForClientSearch")
	WebElement btn_GoForClientSearch;
	
	@FindBy(xpath = "//input[@name='txtFname']")
	WebElement searchBox_ClientName;
	
	@FindBy(id = "btn_GoForCorporationSearch")
	WebElement btn_GoForCorporationSearch;
	
	@FindBy(id = "txtName")
	WebElement searchBox_CorpName;
	
	@FindBy(id = "btn_SaveNewInvoice")
	WebElement btn_SavePaymentType;
	
	@FindBy(id = "cmbHdrTmpl")
	WebElement dropDown_PaymentTerms;
	
	@FindBy(id = "btn_AddNewInvoice")
	WebElement btn_AddNewInvoice;
	
	public CM_InvoicePage(WebDriver driver) {

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
			Log.fail("Job details Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickInvoiceTab()
    {
		/*
         * Created By : M Likitha Krishna
         * Created On : 02 Dec 2019
         */
   
        Utils.clickElement(driver, btn_AddNewInvoice, "add new invoice button");
    } 
	
	public void addNewInvoiceForCorp(String corpName, String paymentTerms) throws Exception
	{
		/*
         * Created By : M Likitha Krishna
         * Created On : 02 Dec 2019
         */
		clickInvoiceTab();
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Invoice", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, paymentTerms, dropDown_PaymentTerms);
		
		Utils.clickElement(driver, btn_SavePaymentType, "Save");
		
		Utils.enterText(driver, searchBox_CorpName, corpName, "corp name");
		
		Utils.clickElement(driver, btn_GoForCorporationSearch, "go button");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+corpName+"')]", "xpath", "corp name");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Invoice Details", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'To')]/../td[contains(text(),'"+corpName+"')]", "xpath", corpName, "Invoice page");
	}
	
	public void addNewInvoiceForClient(String clientName, String caseId, String paymentTerms) throws Exception
	{
		/*
         * Created By : M Likitha Krishna
         * Created On : 02 Dec 2019
         */
		
		clickInvoiceTab();
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Invoice", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, paymentTerms, dropDown_PaymentTerms);
		
		Utils.clickElement(driver, btn_SavePaymentType, "Save");
		
		Utils.enterText(driver, searchBox_ClientName, clientName, "client name");
		
		Utils.clickElement(driver, btn_GoForClientSearch, "go button");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+clientName+"')]", "xpath", "client name");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Invoice Details", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'To')]/../td[contains(text(),'"+clientName+"')]", "xpath", clientName, "Invoice page");
		
		Utils.clickElement(driver, btn_AddCaseForInvoice, "add case for invoice button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Search Result", "title", "false");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+ caseId +"')]/../td/input[@type='checkbox']", "xpath", "select case");
		
		Utils.selectOptionFromDropDown(driver, "Asylum", dropDown_FeeTemplate);
		
		Utils.clickElement(driver, btn_AddCasetoInvoice, "add case to invoice button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Invoice Details", "title", "false");
	}
	
	
	public void verifyEditAccess(boolean value)
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 8th August 2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "btn_AddNewInvoice", "id", "Add Invoice Button", "Invoice List Page");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "btn_AddNewInvoice", header_listOfInvoices, "id", "Add Invoice Button", "Invoice List Page");
		}
		
		
	}

}