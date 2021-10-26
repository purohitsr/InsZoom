package com.inszoomapp.pages.cases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class DocumentCheckListPage extends LoadableComponent<DocumentCheckListPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	String parentWindowId;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ComboBoxAction']")
	static WebElement caseOptionSelect;

	public DocumentCheckListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, caseOptionSelect))) {
			Log.fail("DocumentCheckListPage did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	


	
}
