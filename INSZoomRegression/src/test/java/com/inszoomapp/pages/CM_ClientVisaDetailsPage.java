package com.inszoomapp.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ClientVisaDetailsPage extends LoadableComponent<CM_ClientVisaDetailsPage> 
{

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(css = "select[name='cboIndClntAcc']")
	WebElement dropdown_ClientAccessForRelative;
	
	@FindBy(xpath = "//input[@title='Add New Visa']")
	WebElement icon_PlusAddNewVisa;
	
	
	
	public CM_ClientVisaDetailsPage(WebDriver driver) {

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
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, icon_PlusAddNewVisa))) {
			Log.fail("Home Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void verifyVisaDetailsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 06 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyIfStaticElementDisplayed(driver, icon_PlusAddNewVisa, "Add New Visa Icon", "xpath");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of Visa Page Failed ", driver);
	    }
		
		
	}
	
	
	
	
	
}