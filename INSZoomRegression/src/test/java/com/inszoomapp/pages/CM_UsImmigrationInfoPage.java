package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.TabExpander;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_UsImmigrationInfoPage extends LoadableComponent<CM_UsImmigrationInfoPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "btn_AddCurrentArrivalInfodetails")
	WebElement btn_AddNew;
	
	
	@FindBy(id = "btn_EditVisadetails")
	WebElement btn_EditVisadetails;
	
	@FindBy(id = "btn_SaveVISAdetails")
	WebElement btn_SaveVisaDetails;
	
	@FindBy(id = "txtexpdate")
	WebElement textBox_ExpirationDate;
	
	@FindBy(id="txtVisaNbr")
	WebElement textBox_VisaNumber;
	
	@FindBy(id="rdoNonImmg")
	WebElement checkBox_NonImmigrant;
	
	@FindBy(id="btn_AddVisadetails")
	WebElement btn_AddVisadetails;
	
	public CM_UsImmigrationInfoPage(WebDriver driver) {

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
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}

	}
	
	public void addVisaInfo(String workbookNameWrite,String sheetName,String visaNumber,boolean screenshot) throws Exception 
	{
		/*
	       * Created By : M Likitha Krishna
	       * Created On : 18 Oct 2019
	       */
	    
		Utils.clickElement(driver, btn_AddVisadetails, "add visa details button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Add VISA Details", "title", "false");
	    Utils.clickElement(driver, checkBox_NonImmigrant, "checked non immigrant");
	    Utils.enterText(driver, textBox_VisaNumber, visaNumber, "Visa Number");
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 200); // adding 200 days
		String visaDate = sdf.format(c.getTime());
		Utils.enterText(driver, textBox_ExpirationDate, visaDate, "Expiration Date");	
		
		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_visaDate", sheetName, "Value", visaDate);

		Utils.clickElement(driver, btn_SaveVisaDetails, "Save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client US Immigration Info", "title", "false");	
	}
	
	
	public void verifyVisaInfo(String visaNumber, Boolean screenShot)
	{
		/*
       * Created By : M Likitha Krishna
       * Created On : 19 Oct 2019
       */

		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Visa #')]/../td[contains(text(),'"+ visaNumber +"')]", "xpath", visaNumber, "Visa Number");
	}
	
	
	public void verifyVisaExpirationDate(String client_visaDate,boolean screenshot) throws Exception 
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 19 Oct 2019
	     */
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM/dd/yy");
			SimpleDateFormat sdfmt2 = new SimpleDateFormat("MMM dd yyyy");
			java.util.Date dDate = sdfmt1.parse(client_visaDate);
			String strOutputExpireDate = sdfmt2.format(dDate);
	
			String datesplit[] = strOutputExpireDate.split(" ");
			String month = datesplit[0];
			String date = datesplit[1];
			String year = datesplit[2];
	
			int dateResult = Integer.parseInt(date);
	
			String newDateEditOutput = month + " " + dateResult + " " + year;
	
			Utils.verifyIfDataPresent(driver, "//div[@id='pnlVisaExp']/../../td/table/tbody/tr[5]/td[2][contains(text(),'"+ newDateEditOutput +"')]", "xpath", newDateEditOutput, "date of expiration");
		
	}
	
	
	public void editVisaExpirationDate(String workbookNameWrite,String sheetName,boolean screenshot) throws Exception 
	{
		/*
	     * Created By : M Likitha Krishna
	     * Created On : 19 Oct 2019
	     */
		
		Utils.clickElement(driver, btn_EditVisadetails, "edit button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit VISA Details", "title", "false");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 300); // adding 300 days
		String visaDate = sdf.format(c.getTime());
		Utils.enterText(driver, textBox_ExpirationDate, visaDate, "expiration date");
		
		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		writeExcel.setCellData("QA_A_Client_EditVisaDate", sheetName, "Value", visaDate);
		
		Utils.clickElement(driver, btn_SaveVisaDetails, "Save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client US Immigration Info", "title", "false");
	}

	public void clickAddNewButton() throws Exception 
	{
		/*
	     * Created By : SauravP
	     * Created On : 05 Jan 2020
	     */
		
		Utils.clickElement(driver, btn_AddNew, "edit button");
		
	}
	
	
	public void verifyUSImmigrationInfoPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 05 Feb 2020
		  */
	    try{
		 
		 Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client US Immigration Info");
	    }catch(Exception e){
		 Log.failsoft("Verification of View Client US Immigration Info", driver);
	    }
	    
	  
}

	public void verifyEditCurrentArrivalInfoDetailsPage() throws Exception
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 05 Feb 2020
		  */
	    try{
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Current Arrival Info Details", "title", "false");
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Edit Current Arrival Info Details");
		driver.close();
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client US Immigration Info", "title", "false");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of Edit Current Arrival Info Details Page Failed ", driver);
		 driver.close();
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Client US Immigration Info", "title", "false");
	    }
	    
	  
}
	
	
}