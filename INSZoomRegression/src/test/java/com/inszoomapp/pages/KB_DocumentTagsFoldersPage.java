package com.inszoomapp.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.util.FindBugsSuppressWarnings;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.AppDataUAT;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class KB_DocumentTagsFoldersPage extends LoadableComponent<KB_DocumentTagsFoldersPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//td[contains(text(), 'Visa')]")
	WebElement text_visa;
	
	@FindBy(id="btn_Save")
	WebElement btn_save;
	
	@FindBy(id="btn_AddNewDocumentTagFolder")
	WebElement btn_addNew;
	
	@FindBy(id="txtFolderName")
	WebElement textbox_name;
	
	
	public KB_DocumentTagsFoldersPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Knowledge page didnt loaded");
		}

	}
	
	
	public void addTagFolder(String name) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 01 April 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_addNew, "'Add New'");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add a new Document Tag/Folder to Organization", "title", "false");
	
		Utils.enterText(driver, textbox_name, name, "Name");
		
		Utils.clickElement(driver, btn_save, "'Save'");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List of Document Tags/Folders", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + name + "')]", "xpath", name, "Tags/Folders");
	}
	
	
	public void deleteTagFolder(String name)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 01 April 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + name + "')]/preceding-sibling::td//img[@title='Delete Tag/Folder ']", "xpath", "Delete");
		
		if(Utils.isAlertPresent(driver))
		{
			driver.switchTo().alert().accept();
			Log.message("Accepted the Alert");
		}
		
		Utils.verifyIfDataNotPresent(driver, "//td[contains(text(), '" + name + "')]", text_visa, "xpath", name, "Tags/Folders");
	}

}