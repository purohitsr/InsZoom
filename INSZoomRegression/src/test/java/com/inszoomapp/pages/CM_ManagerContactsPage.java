package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ManagerContactsPage extends LoadableComponent<CM_ManagerContactsPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	public CM_ManagerContactsPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_ManagerContactsPage(WebDriver driver) {
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
			Log.fail("Manager contacts Page did not open up. Site might be down.", driver, true);
		}
	}
	
	public void caseManagerSignForms(String signPerson,boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  * 
		  */
		
		Utils.verifyIfDataPresent(driver, "//table[@summary='Case managers who sign forms for this Case']/tbody/tr/td[@class='TDOCLIST'][contains(text(),'"+ signPerson +"')]", "xpath", signPerson, "Case Managers Who Sign Forms For This Case");
	}
	
	
	public void firmStaffReceiveRemainders(String signPerson,boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 19 Oct 2019
		  * 
		  */ 
		
		Utils.verifyIfDataPresent(driver, "//table[@summary='Firm staff who will receive reminders']/tbody/tr/td[@class='TDOCLIST'][contains(text(),'"+ signPerson +"')]", "xpath", signPerson, "Firm Staff who will recieve reminders For This Case");
	}
	
	
	
}