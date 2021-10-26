package com.inszoomapp.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class FavoriteReportPage extends LoadableComponent<FavoriteReportPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	String parentWindowId;
	

	
	
	@FindBy(xpath = "(//input[@title='Add to Favorite Reports'])[1]")
	static WebElement addToFavoriteReportBtn;
	
	@FindBy(css = "#PAGEHDR")
	static WebElement nameOfFavorite;
	
	public FavoriteReportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void threadSleepTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getReportName() {
		return nameOfFavorite.getText().trim().toString();
	}
	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, addToFavoriteReportBtn))) {
			Log.fail("AddFavoriteReportPage did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void verifyFavoriteReportPresent()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.verifyIfDataPresent(driver,"//a[contains(text(),'"+ globalVariables.favoriteReportName +"')]" , "xpath", globalVariables.favoriteReportName, "favorite reports page");
	}
	
	public void clickFavoriteReportNameLink(String reportName)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		 Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ reportName +"')]", "xpath", reportName);
	}
	


	
	
	
	
	
	
	
	
	
	
	
}