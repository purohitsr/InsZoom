package com.inszoomapp.reports;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class CustomizedReportsPage extends LoadableComponent<CustomizedReportsPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;

	
	@FindBy(id = "btn_SearchReport")
	WebElement btn_Find;
	
	@FindBy(id = "txtSearch")
	WebElement searchBox_Report;
	
	@FindBy(id = "btn_FindReport")
	WebElement btn_FindReport;
	
	@FindBy(how = How.CSS, using = "input[title='Find Report']")
	static WebElement findReportBtn;
	
	public CustomizedReportsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, findReportBtn))) {
			Log.fail("CustomizedReportsPage did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	public void findAndSelectReport(String nameOfReport) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 08 Nov 2019
		 */
		
		Utils.clickElement(driver, btn_FindReport, "find report button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Search Report", "title", "false");
 
		Utils.enterText(driver, searchBox_Report, nameOfReport, "entered report name in searchbox");
		Utils.clickElement(driver, btn_Find, "Find button");
		
		WebElement lnk_ReportName = driver.findElement(By.xpath("//a[contains(text(),'"+ nameOfReport +"')]"));
		
		Utils.clickElement(driver, lnk_ReportName, "report link");
		
		Utils.switchWindows(driver, "rpt_case_activities_log.aspx", "url", "false");

	}
	
	
	
	

}
