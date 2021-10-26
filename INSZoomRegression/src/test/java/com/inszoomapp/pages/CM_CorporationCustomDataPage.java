package com.inszoomapp.pages;

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

public class CM_CorporationCustomDataPage extends LoadableComponent<CM_CorporationEmailPage> 
{
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//td[contains(text(),'Crime Info')]/following-sibling::td/input")  //Created by Souvik Ganguly on 07/07/2021
	WebElement textbox_CrimeInfo;
	
	@FindBy(xpath = "//td[contains(text(),'Custom label info for Corporation')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_Editcustomlabelvalues']")
	WebElement btn_EditCustomLabelCorp;
	
	@FindBy(id = "btn_SaveandClose")
	WebElement btn_SaveandClose;
	
	@FindBy(xpath = "//td[contains(text(),'Custom label info for Case')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_AttachFields']")
	WebElement btn_AttachFieldsCase;
	
	@FindBy(xpath = "//td[contains(text(),'Custom label info for Client')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_AttachFields']")
	WebElement btn_AttachFieldsClient;
	
	@FindBy(xpath = "//td[contains(text(),'Custom label info for Corporation')]//ancestor::tr[3]/preceding-sibling::tr[1]//input[@id='btn_AttachFields']")
	WebElement btn_AttachFieldsCorp;
	
	@FindBy(xpath = "//td[contains(text(),'Country')]/following-sibling::td/select")
	WebElement dropDown_Country;
	
	@FindBy(id = "btn_SaveCustomFieldInfo")
	WebElement btn_SaveDefaultCustomFieldInfo;
	
	@FindBy(id = "btn_Editcustomlabelvalues")
	WebElement btn_editDefaultCustomData;


	public CM_CorporationCustomDataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

	}

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Emails Page did not open up. Site might be down.", driver, true);
		}
	}
	
	public void editDefaultCustomData(String fieldName, String data) throws Exception
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 07 July 2021
		 * Added the utility for entering Crime Info and verifying the same
		 */
		Utils.clickElement(driver, btn_editDefaultCustomData, "edit default custom data");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'" + fieldName + "')]/following-sibling::td/input", globalVariables.elementWaitTime, "xpath");
		WebElement field = driver.findElement(By.xpath("//td[contains(text(),'" + fieldName + "')]/following-sibling::td/input"));
		Utils.enterText(driver, field, data, "default custom field");
		//Utils.selectOptionFromDropDownByVal(driver, "India", dropDown_Country);
		Utils.enterText(driver, textbox_CrimeInfo, globalVariables.crimeInfoData, "default custom field");
		Utils.clickElement(driver, btn_SaveDefaultCustomFieldInfo, "save");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Custom Fields.", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+fieldName+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, "default custom field");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Crime Info')]/following-sibling::td[contains(text(),'"+globalVariables.crimeInfoData+"')]", "xpath", globalVariables.crimeInfoData, "crime info field");
	}
	
	
	public void attachCorpCustomLabel(String fieldName, String data) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Feb 2020
		 */
		Utils.clickElement(driver, btn_AttachFieldsCorp, "attach field button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Link Fields.", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]", globalVariables.elementWaitTime, "xpath");
		WebElement checkBox_enableField = driver.findElement(By.xpath("//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]"));
		Utils.clickElement(driver, checkBox_enableField, "enable corp custom field");
		Utils.clickElement(driver, btn_SaveandClose, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Custom Fields.", "title", "false");
		Utils.clickElement(driver, btn_EditCustomLabelCorp, "edit custom field");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Custom Field", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+fieldName+"')]/following-sibling::td/input", globalVariables.elementWaitTime, "xpath");
		WebElement textBox_customData = driver.findElement(By.xpath("//td[contains(text(),'"+fieldName+"')]/following-sibling::td/input"));
		Utils.enterText(driver, textBox_customData, data, "custom data");
		Utils.clickElement(driver, btn_SaveDefaultCustomFieldInfo, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Custom Fields.", "title", "false");
	}
	

	public void attachClientCustomLabel(String fieldName) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Feb 2020
		 */
		Utils.clickElement(driver, btn_AttachFieldsClient, "attach field button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Link Fields.", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]/input", globalVariables.elementWaitTime, "xpath");
		WebElement checkBox_enableField = driver.findElement(By.xpath("//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]/input"));
		Utils.clickElement(driver, checkBox_enableField, "enable corp custom field");
		Utils.clickElement(driver, btn_SaveandClose, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Custom Fields.", "title", "false");
	}
	
	public void attachCaseCustomLabel(String fieldName) throws Exception
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Feb 2020
		 */
		Utils.clickElement(driver, btn_AttachFieldsCase, "attach field button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Link Fields.", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]/input", globalVariables.elementWaitTime, "xpath");
		WebElement checkBox_enableField = driver.findElement(By.xpath("//td[contains(text(),'"+fieldName+"')]//preceding-sibling::td[1]/input"));
		Utils.clickElement(driver, checkBox_enableField, "enable corp custom field");
		Utils.clickElement(driver, btn_SaveandClose, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Custom Fields.", "title", "false");
	}

}