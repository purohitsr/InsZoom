package com.inszoomapp.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class FNP_PassportPage extends LoadableComponent<FNP_PassportPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//h2[contains(text(),'PASSPORT')]")
	WebElement pageheader_Passport;
	
	@FindBy(css = "#PassportDetails > div > div > div.modal-footer > button")
	WebElement btn_closePassportDetails;
	
	
	public FNP_PassportPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}


	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("FNP passport page didnt loaded");
		}
	}
	
	public void verifyValidPassportDetails(String issuingCountry,String passportNumber, String validFrom, String validTo)
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 08 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Valid Passports')]/../..//a[contains(text(),'"+ passportNumber +"')]", "xpath", passportNumber, "passport page");
		Utils.clickDynamicElement(driver, "//h2[contains(text(),'Valid Passports')]/../..//a[contains(text(),'"+ passportNumber +"')]", "xpath", "passport number");
		Utils.verifyIfDataPresent(driver, "//span[@id='PassportNo'][contains(text(),'" +passportNumber+ "')]", "xpath", passportNumber, "passport window");
		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Valid Passports')]/../..//span[contains(text(),'"+issuingCountry+"')]/../following-sibling::td/a[contains(text(),'"+passportNumber+"')]/../following-sibling::td[contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]", "xpath", "passport details", "FNP login");
		Utils.clickElement(driver, btn_closePassportDetails, "on close button");
	}
	
	public void verifyExpiredPassportDetails(String issuingCountry,String passportNumber, String validFrom, String validTo)
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 08 Nov 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+issuingCountry+"')]/../following-sibling::td/a[contains(text(),'"+passportNumber+"')]/../following-sibling::td[contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]", "xpath", "passport details", "passport and visa tab");	
//		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Expired Passports')]/../..//a[contains(text(),'" +passportNumber+ "')]", "xpath", passportNumber, "expired passport section");
//		Utils.clickDynamicElement(driver, "//h2[contains(text(),'Expired Passports')]/../..//a[contains(text(),'"+ passportNumber +"')]", "xpath", "passport number");
//		Utils.verifyIfDataPresent(driver, "//span[@id='PassportNo'][contains(text(),'" +passportNumber+ "')]", "xpath", passportNumber, "passport window");
//		Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Expired Passports')]/../..//span[contains(text(),'"+issuingCountry+"')]/../following-sibling::td/a[contains(text(),'"+passportNumber+"')]/../following-sibling::td[contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]", "xpath", "passport details", "FNP login");
//		Utils.clickElement(driver, btn_closePassportDetails, "on close button");
	}
	
	 public void verifyPassportPage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  */
	    try{
			 Utils.waitForAjax(driver);	 
		     Utils.verifyIfStaticElementDisplayed(driver, pageheader_Passport, "In header section", "In Passport Page");
				
	       }catch(Exception e){
			 Log.failsoft("Verification of Passport Header failed", driver);
		    }

		} 
	
}