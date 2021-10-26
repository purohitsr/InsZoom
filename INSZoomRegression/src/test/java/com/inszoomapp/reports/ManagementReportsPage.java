package com.inszoomapp.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPath;

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

public class ManagementReportsPage extends LoadableComponent<ManagementReportsPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	String parentWindowId;
	
	
	@FindBy(css = "a[title='Click here to Download Excel File']>b>u")
	WebElement lnk_ExcelFileDownload;

	@FindBy(css = "a[title='Click here to Download Text File']>b>u")
	WebElement lnk_TextFileDownload;
	
	@FindBy(css = "input[value='Export File']")
	WebElement btn_ExportFile;
	
	@FindBy(xpath = "//table//tr")
	WebElement tbl_Report;
	
	@FindBy(id = "rptFrame")
	WebElement frame_Report;
	
	@FindBy(css = "input[value='Get Report']")
	WebElement btn_GetReport;
	
	@FindBy(id = "cboBnfStatus")
	WebElement dropDown_ClientStatus;
	
	@FindBy(id = "cboCorpCombo")
	WebElement dropDown_Corporation;
	
	@FindBy(id = "txtEndDate")
	WebElement textBox_ToDate;
	
	@FindBy(id = "txtStartDate")
	WebElement textBox_FromDate;
	
	@FindBy(id = "btn_AddtoFavoriteReports")
	WebElement btn_AddtoFavoriteReports;
	
	@FindBy(id = "btn_Close")
	WebElement btn_Close;
	
	@FindBy(css = "select[name='cboRpt']")
	WebElement exportReportDetails;
	
	
	public ManagementReportsPage(WebDriver driver) {
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

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, exportReportDetails))) {
			Log.fail("ManagementReportsPage did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	public void addFavoriteReport(String favoriteReportName) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
	  Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ globalVariables.favoriteReportName +"')]/../following-sibling::td[2]//td[2]//img", "xpath", "Clicked on add to fav icon");
	  Utils.waitForAllWindowsToLoad(2, driver);
	  Utils.switchWindows(driver, "INSZoom.com - Add Favorite Report", "title", "false");
	  Utils.clickElement(driver, btn_AddtoFavoriteReports, "add to favorite reports button");
	  Utils.clickElement(driver, btn_Close, "close button");
	  Utils.switchWindows(driver, "INSZoom.com - Reports", "title", "false");
	}
	
	public String getRequiredDate(int requriedDate) {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, requriedDate); // Adding or Subtracting given days
		return sdf.format(c.getTime());
	}

	public void setDataToGetReport(String corporation, String clientStatus) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		Utils.enterText(driver, textBox_FromDate, getRequiredDate(-18), "from date");
		Utils.enterText(driver, textBox_ToDate, getRequiredDate(1), "to date");
		Utils.selectOptionFromDropDown(driver, corporation, dropDown_Corporation);
		Utils.selectOptionFromDropDown(driver, clientStatus, dropDown_ClientStatus);
	}
	
	
	public void clickGetReportButton()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		Utils.clickElement(driver, btn_GetReport, "get report button");
	}
	
	
	
	public void verifyReportTable(Boolean screenshot) {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		
		Utils.waitForElement(driver, "frame_Report", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(frame_Report);
		//Utils.waitForElement(driver, "//table//tr", globalVariables.elementWaitTime, "xpath");
		List<WebElement> reportTableRows = driver.findElements(By.xpath("//table//tr"));
		
		Log.message("Report Loaded : " + reportTableRows.size(), driver, true);
		driver.switchTo().defaultContent();
		if(reportTableRows.size()>0)
			Log.pass("Favorite report loaded based on filters", driver, screenshot);
		else
			Log.fail("Favorite report is not loaded once applied filters", driver, screenshot);
	}
	
	
	public void clickExportFileButton()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		Utils.clickElement(driver, btn_ExportFile, "export file button");
	}
	
	public void verifyReportFileLinks() throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Oct 2019
		  */
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com::Case Activities Standard Report -- Grouped By Corporations", "title", "false"); 
		
		String textLink = lnk_TextFileDownload.getText().trim().toString();
		String exceLink = lnk_ExcelFileDownload.getText().trim().toString();
		Log.message("Download Text/Excel File links are shwoing fine\n" + textLink + " ; " + exceLink, driver, true);
		if (textLink.contains("Download Text File") && exceLink.contains("Download Excel File")) {
			Log.pass("Download Text/Excel File links are showing fine\n" + textLink + " ; " + exceLink, driver,
					true);
		} else {
			Log.fail("Download file links are not shwoing fine", driver, true);
		}
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "InsZoom.com -Case Activities Standard Report -- Grouped By Corporations", "title", "false");
	}
	
	public void clickReportName(String reportName)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ reportName +"')]", "xpath", "clicked on report link");
	}
	
	
	
}