package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_BlanketApplicantsPage extends LoadableComponent<CM_BlanketApplicantsPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_DeleteblanketapplicantsfromthisCase")
	WebElement btn_DeleteBlanketApplicants;
	
	@FindBy(id = "btn_Addblanketapplicants")
	WebElement btn_SaveBlanketApplicants;
	
	
	@FindBy(id = "btn_AddblanketapplicantsforthisCase")
	WebElement btn_AddBlanketApplicants;
	
	public CM_BlanketApplicantsPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_BlanketApplicantsPage(WebDriver driver) {
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
			Log.fail("Blanket applicants Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void addNewBlanketApplicants(String clientName,boolean screenshot) throws Exception 
	{
		/*
	       * Created By : M Likitha Krishna
	       * Created On : 18 Oct 2019
	       */
		Utils.clickElement(driver, btn_AddBlanketApplicants, "Add Blanket Applicants");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Case Blanket Applicants", "title", "false");
		Utils.waitForElement(driver, "//td[contains(text(),'"+ clientName +"')]/../td/input[@type='checkbox']", globalVariables.elementWaitTime, "xpath");
		WebElement checkBox_ChooseClient = driver.findElement(By.xpath("//td[contains(text(),'"+ clientName +"')]/../td/input[@type='checkbox']"));
		Utils.clickElement(driver, checkBox_ChooseClient, "check box to choose client");
		Utils.clickElement(driver, btn_SaveBlanketApplicants, "Save Blanket Applicants");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Blanket Applicants List", "title", "false");	
	}
	
	
	public void verifyAddedBlanketApplicant(String clientName, Boolean screenShot){
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 18 Oct 2019
 		 */
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Name')]/../../tr[2]/td[2]/a[contains(text(),'"+ clientName +"')]", "xpath", clientName, "Blanket application added");
	}
	
	public void deleteBlanketApplicantAndVerify(String clientName) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 18 Oct 2019
 		 */

		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ clientName +"')]/../../td/input", "xpath", "Selected client to be deleted");
		Utils.clickElement(driver, btn_DeleteBlanketApplicants, "delete button");
		if (Utils.isAlertPresent(driver)) 
		{
			driver.switchTo().alert().accept();
		} 
		else 
		{
			System.out.println("alert was not present");
		}
		
		Utils.waitForElement(driver, "//th[contains(text(),'Name')]", globalVariables.elementWaitTime, "xpath");
		WebElement txt_Name = driver.findElement(By.xpath("//th[contains(text(),'Name')]"));
		Utils.verifyIfDataNotPresent(driver, "//th[contains(text(),'Name')]/../../tr[2]/td[2]/a[contains(text(),'"+ clientName +"')]", txt_Name, "xpath", clientName, "Client name");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}