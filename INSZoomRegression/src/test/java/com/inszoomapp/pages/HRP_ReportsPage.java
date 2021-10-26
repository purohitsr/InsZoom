package com.inszoomapp.pages;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.NetPermission;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class HRP_ReportsPage extends LoadableComponent<HRP_ReportsPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(css = "a[title='Click here to Download Excel File']")
	WebElement lnk_Excel;
	
	@FindBy(css = "a[title='Click here to Download Text File']")
	WebElement lnk_Text;
	
	@FindBy(id = "btn_Export[[Case]]StatusReport")
	WebElement btn_ExportStatusReport;
	
	@FindBy(id = "cboPetType")
	WebElement dropDown_Petition;
	
	@FindBy(id = "cboCountry")
	WebElement dropDown_Country;
	
	@FindBy(css = "a[title='Go to the next page']>span")
	WebElement nextPage;
	
	@FindBy(css = "div[class='k-pager-wrap k-grid-pager k-widget k-floatwrap']>ul")
	WebElement div_Pagination;	
	
	public HRP_ReportsPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public HRP_ReportsPage(WebDriver driver) {
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
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}
  

	
	
	

	public void selectReports(String HRP_ReportName, boolean screenshot) throws Exception {
		
		/*
		* Created By : M Likitha Krishna
		* Created On : 21 Oct 2019
		*/ 	
		
		boolean status = true;
		boolean value = true;
		String reportView;
		
	
		try {
			Utils.waitForAjax(driver);
			Utils.waitForElement(driver, div_Pagination);
			Utils.scrollIntoView(driver, div_Pagination);
			
			
			List<WebElement> numberList = driver.findElements(
					By.cssSelector("div[class=\"k-pager-wrap k-grid-pager k-widget k-floatwrap\"]>ul>li"));
	
	
			for (int j = 1; j < numberList.size(); j++) {
				Utils.waitForAjax(driver);
				Utils.waitForElement(driver, "table[role='grid']>tbody>tr", 90, "css");
				//SHOULD BE DYNAMIC
				List<WebElement> taskDetailsRow = driver.findElements(By.cssSelector("table[role='grid']>tbody>tr"));
				for (int i = 0; i < taskDetailsRow.size(); i++) {
					reportView = taskDetailsRow.get(i).getText();
					System.out.println(reportView);
	
					if (reportView.contains(HRP_ReportName)) {
						try {
							Log.message("clicking the report with name as "+ (HRP_ReportName) + "", driver, screenshot);
							Utils.waitForAjax(driver);
							Utils.waitForElement(driver, "table[role='grid']>tbody>tr:nth-child("+ (i + 1) + ")>td>div>a", globalVariables.elementWaitTime, "css");
							
							Utils.waitForElement(driver, "//a[contains(text(), '" + HRP_ReportName + "')]", globalVariables.elementWaitTime, "xpath");
							WebElement reportsViewBtn = driver.findElement(By.xpath("//a[contains(text(), '" + HRP_ReportName + "')]"));
							Utils.scrollIntoView(driver, reportsViewBtn);
							reportsViewBtn.click();
							Log.message("Clicked the report link with name as "
									+ (HRP_ReportName) + "");
	
						} catch (Exception e) {
							Log.fail(
									"Unable to click the report link. ERROR :\n\n " + e.getMessage(),
									driver, screenshot);
						}
						status = false;
						value = false;
						break;
					} else {
						value = true;
					}
	
				}
				if (value) {
					try {
						Utils.waitForElement(driver, nextPage);
						nextPage.click();
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,-1000)");
						Log.message("Clicked on next page");
	
					} catch (Exception e) {
						Log.fail("Unable to click on next page. ERROR :\n\n " + e.getMessage(),
								driver, screenshot);
					}
	
	
				} else {
					break;
				}
			}
			if (status) {
				Log.fail("Report is not present to choose the report " + (HRP_ReportName) + "", driver, screenshot);
			}
		}
		catch(Exception e)
		{
			Log.message("Unable to select report", driver, true);
		}
	}

	
	public void exportAndVerifyTextFile(String country, String fileNameStartsWith,String HRP_ReportName, String optionPass, String optionFail, boolean screenshot) throws Exception
	{
		/*
		* Created By : M Likitha Krishna
		* Created On : 08 Nov 2019
		*/ 	
		
		Utils.waitForAllWindowsToLoad(2, driver);

		Utils.switchWindows(driver, "p_rpt_clnt_status_pet.aspx", "url", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, country, dropDown_Country);
		
		Utils.selectOptionFromDropDownByIndex(driver, 1, dropDown_Petition);
		
		Utils.clickElement(driver, btn_ExportStatusReport, "Export status report");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "p_rpt_clnt_status_pet_note_txt.aspx", "url", "false");

		Utils.waitForElement(driver, lnk_Text);
		if (lnk_Text.isDisplayed()) {
			Log.pass(optionPass, driver, true);
			Utils.clickElement(driver, lnk_Text, "text link to download text format file");

			String systemUserName;

			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println("Address : " + (localhost.getHostAddress()).trim());

			boolean fileStatus = true;
			Log.message("Current Working directory: " + System.getProperty("user.dir"));
			String home = System.getProperty("user.home");

			if (localhost.getHostAddress().trim().equals("10.0.0.149")) {
				Log.message("Execution from Jenkins - to fetch the download folder path");
				home = "C:\\Users\\rajesh.kalaivanan\\";
			}

			File folder = new File(home + File.separatorChar + "Downloads" + File.separatorChar);
			Log.message("Downloading Directory :" + home + File.separatorChar + "Downloads" + File.separatorChar);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (listOfFiles[i].getName().startsWith(fileNameStartsWith)) {

						if (listOfFiles[i].getName().contains("_csv.txt")) {
							Log.pass("Verified the text file for the report " + (HRP_ReportName)
									+ " is downloaded as " + (listOfFiles[i].getName()) + "");
							fileStatus = false;
							break;
						}
					}
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("## Directory :" + listOfFiles[i].getName());
				}
			}
			if (fileStatus) {
				Log.fail("Text file not downloaded for the report " + (HRP_ReportName) + "", driver, true);
			}
		} else {
			Log.fail(optionFail, driver, true);
		}
		driver.close();
		Utils.switchWindows(driver, "p_rpt_clnt_status_pet.aspx", "url", "false");
		driver.close();
		Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
	}
	
	
	public void exportAndVerifyExcelFile(String country, String fileNameStartsWith,String HRP_ReportName, String optionPass, String optionFail, boolean screenshot) throws Exception
	{                                                                              
		/*
		* Created By : M Likitha Krishna
		* Created On : 08 Nov 2019
		*/ 	
		
		Utils.waitForAllWindowsToLoad(2, driver);

		Utils.switchWindows(driver, "p_rpt_clnt_status_pet.aspx", "url", "false");
		
		Utils.selectOptionFromDropDownByVal(driver, country, dropDown_Country);
		
		Utils.selectOptionFromDropDownByIndex(driver, 1, dropDown_Petition);
		
		Utils.clickElement(driver, btn_ExportStatusReport, "Export status report");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "p_rpt_clnt_status_pet_note_txt.aspx", "url", "false");

		Utils.waitForElement(driver, lnk_Excel);
		if (lnk_Excel.isDisplayed()) {
			Log.pass(optionPass, driver, screenshot);
			Utils.clickElement(driver, lnk_Excel, "Excel link");


			// checking whether the text file is downloaded
			String systemUserName;

			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println("Address : " + (localhost.getHostAddress()).trim());

			boolean fileStatus = true;
			Log.message("Current Working directory: " + System.getProperty("user.dir"));
			String home = System.getProperty("user.home");

			if (localhost.getHostAddress().trim().equals("10.0.0.149")) {
				Log.message("Execution from Jenkins - to fetch the download folder path");
				home = "C:\\Users\\rajesh.kalaivanan\\";
			}

			File folder = new File(home + File.separatorChar + "Downloads" + File.separatorChar);
			Log.message("Downloading Directory :" + home + File.separatorChar + "Downloads" + File.separatorChar);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (listOfFiles[i].getName().startsWith(fileNameStartsWith)) {
						if (listOfFiles[i].getName().contains("_csv.csv")) {
							Log.pass("Verified the excel file for the report " + (HRP_ReportName)
									+ " is downloaded as " + (listOfFiles[i].getName()) + "");
							fileStatus = false;
							break;
						}
					}
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("## Directory :" + listOfFiles[i].getName());
				}
			}
			if (fileStatus) {
				Log.fail("Excel file not downloaded for the report " + (HRP_ReportName) + "", driver, screenshot);
			}
		} else {
			Log.fail(optionFail);
		}

		driver.close();
		Utils.switchWindows(driver, "p_rpt_clnt_status_pet.aspx", "url", "false");
		driver.close();
		Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
	}
	
}	
	
	
