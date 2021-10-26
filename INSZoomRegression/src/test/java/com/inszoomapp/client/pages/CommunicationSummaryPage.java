package com.inszoomapp.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CommunicationSummaryPage extends LoadableComponent<CommunicationSummaryPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(css = "#rcbStatusCombo_Input")
	static WebElement statusComboSelect;
	
	
	public CommunicationSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, statusComboSelect))) {
			Log.fail("Communication Summary Page did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void verifyDataPresent(String notesDetailsText, boolean screenshot)throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ notesDetailsText +"')]", "xpath", notesDetailsText, "communication Summary");
	}
	
	
}