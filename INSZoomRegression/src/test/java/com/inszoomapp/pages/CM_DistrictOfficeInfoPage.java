package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_DistrictOfficeInfoPage extends LoadableComponent<CM_DistrictOfficeInfoPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	String filedDate = null ; 
	
	@FindBy(id = "btn_SaveDistrictOffice")
	WebElement btn_SaveDistrictOfficeInfo;
	
	@FindBy(id = "txtFiledDt")
	WebElement textBox_FiledDate;
	
	@FindBy(id = "cboDistOff")
	WebElement dropDown_DistrictOffice;

	public CM_DistrictOfficeInfoPage(WebDriver driver) {
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
			Log.fail("District office info Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
	
	public void editDistrictOfficeInfo(String formName, String districtOffice) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
	
		Utils.clickDynamicElement(driver, "//td[@class='TDOCLIST' and contains(text(),'"+ formName +"')]/following-sibling::td/a[@title='Click here to Edit District Office Info']", "xpath", "edit link");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Link/ Unlink District Office", "title", "false");						
					
		Utils.selectOptionFromDropDown(driver, districtOffice, dropDown_DistrictOffice);			
					
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 5); // add 5 days
		filedDate = sdf.format(c.getTime());
		
		Utils.enterText(driver, textBox_FiledDate, filedDate, "filed date");
					
		Utils.clickElement(driver, btn_SaveDistrictOfficeInfo, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com :: List of Forms with District Office Info", "title", "false");
	}
	
	
	
	
	public void verifyEdittedDistrictOfficeInfo(String formName, String districtOffice)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Nov 2019
		  */
		Utils.verifyIfDataPresent(driver, "//td[@class='TDOCLIST' and contains(text(),'"+ formName +"')]/following-sibling::td[contains(text(),'"+ districtOffice +"')]", "xpath", "editted data", "district office info page");
	}
	
	
}