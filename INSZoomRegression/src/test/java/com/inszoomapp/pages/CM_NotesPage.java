package com.inszoomapp.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_NotesPage extends LoadableComponent<CM_NotesPage> 
{

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	@FindBy(id = "btn_SaveCorporationnotes")
	WebElement btn_saveCorpNotes;
	
	@FindBy(id = "cboExtranetAccess1")
	WebElement dropdown_extraNetAccessInCorp;
	
	@FindBy(xpath = "//input[@title='Add Note']")
	WebElement btn_addCorpNotes ;

	@FindBy(css = "select[name='cboIndClntAcc']")
	WebElement dropdown_ClientAccessForRelative;
	
	@FindBy(id = "btn_Deleterecords")
	WebElement btn_DeleteNotesInCase;
	
	@FindBy(id = "btn_PrintPreviewofCaseNotes")
	WebElement btn_PrintPreviewNotesInCase;
	
	@FindBy(id = "btn_SavechangestoCasenotes")
	WebElement btn_SaveEdittedNotesCase;
	
	@FindBy(id = "btn_AddnotetotheCase")
	WebElement btn_AddNotesInCase;
	
	@FindBy(id = "btn_AddCaseNote")
	WebElement btn_SaveNotesInCase;
	
	@FindBy(css = "select[name='cboClntAcc']")
	WebElement dropdown_ClientAccessInCase;
	
	@FindBy(xpath = "/html/body")
	WebElement textBox_NotesDescriptionInCase;
	
	@FindBy(id = "btn_AddnotetotheCase")
	WebElement btn_AddNotesInClient;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_GridDelBtn")
	WebElement icon_deleteNotesInClient;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_GridPrtpreBtn")
	WebElement icon_PrintPreviewNotesInClient;
	
	@FindBy(id = "btn_SaveClientNotesComments")
	WebElement btn_SaveEdittedNotes;
	
	@FindBy(id = "btn_EditCasenotesdetails")
	WebElement btn_EditNotes;
	
	@FindBy(css = "input[id='btn_AddCaseNote']")
	WebElement notesSaveButton;
	
	@FindBy(css = "input[title='Add Note']")
	WebElement btn_AddNotes;
	
	@FindBy(css = "select[name='cboClntAcc']")
	WebElement dropdown_ClientAccess;
	
	@FindBy(xpath = "/html/body")
	WebElement textBox_NotesDescriptionInClient;
	
	@FindBy(id = "btn_AddClientNotesComments")
	WebElement btn_SaveNotes;

	@FindBy(css = "table[class=\"rgMasterTable rgClipCells rgClipCells\"][id=\"ctl00_MainContent_ctl00_RadGridList_ctl00\"]>tbody>tr")
	WebElement rows_NotesTable;
	
	public CM_NotesPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
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
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, notesSaveButton))) {
			Log.fail("Home Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickAddNotesButton(boolean sceenshot) 
	{
		Utils.clickElement(driver, btn_AddNotes, "Add Client Button");
	}
	
	
	public void clickSaveNotesButton(boolean sceenshot) 
	{
		Utils.clickElement(driver, btn_SaveNotes, "Save Client Button");
	}
	
	public void addNotesInClient(String NotesDetailsText, boolean screenshot) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 10 Oct 2019
		*/ 
		
		clickAddNotesButton(true);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Client Notes/Comments", "title", "false");
		Utils.waitForElement(driver, dropdown_ClientAccess);
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", 50, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_NotesDescriptionInClient, NotesDetailsText, "Notes decription");
		driver.switchTo().defaultContent();
		if(Utils.isElementPresent(driver, "//select[@name='cboClntAcc']/option[@value='O']", "xpath"))
			Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_ClientAccess);
		else
			Utils.selectOptionFromDropDownByVal(driver, "B", dropdown_ClientAccessForRelative);
		clickSaveNotesButton(true);
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");
	}
	
	public void verifyNotesInClient(String notesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 10 Oct 2019
		*/ 
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ notesDetailsText +"')]", "xpath" , notesDetailsText, "Notes Table");
	}
	
	
	public void clickEditAddInNotesClient(String firstNotesDetailsText, String editFirstNotesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 10 Oct 2019
		*/ 
		
			Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ firstNotesDetailsText +"')]/../../td[2]/a/img[@title='Edit']", "xpath", "On Notes link");
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchWindows(driver, "INSZoom.com - Edit Client Notes/Comments", "title", "false");
			Utils.waitForElement(driver, btn_SaveEdittedNotes);
			Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", 50, "id");
			driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
			Utils.enterText(driver, textBox_NotesDescriptionInClient, editFirstNotesDetailsText, "Edited text in notes description");
			driver.switchTo().defaultContent();
			Utils.clickElement(driver, btn_SaveEdittedNotes, "Clicked on save edited notes button");
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "List", "title", "false");
	}
	
	
	
	public void clickCheckBoxInNotesClient(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDescription +"')]/../../td/input[@type='checkbox']", "xpath", "Notes CheckBox");
	}
	
	
	public void clickPrintPreviewInClient(boolean screenshot) 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.clickElement(driver, icon_PrintPreviewNotesInClient, "Print Preview in client level");
	}
	
	
	public void verifyPrintPreviewClient(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		clickPrintPreviewInClient(true);
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com -Notes of ", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "on the print preview page");
		Utils.switchWindows(driver, "List", "title", "false");
	}
	
	
	public void verifyNotesViewPage(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDescription +"')]", "xpath", "Notes which was created");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client Notes/Comments", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "Notes view page");
		Utils.switchWindows(driver, "List", "title", "false");
	}
	

	public void clickDeleteIconClient(String notesDetailsText, boolean screenshot)  
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.clickElement(driver, icon_deleteNotesInClient, "Delete icon in Client Notes Page");
	}
	
	
	public void deleteNotesClient(String secondNotesDetailsText, boolean screenshot) throws InterruptedException 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
	    Utils.clickElement(driver, icon_deleteNotesInClient, "Delete icon in Client Notes Page");
		try {
			while (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			} 
		} catch (NoAlertPresentException e) {
			System.out.println("Exception=====!");
			e.printStackTrace();
			System.out.println("End of exception======");
		}
		Log.message("Successfully deleted" + (secondNotesDetailsText) + "");
	}
	
	
	public void verifyNotesDeleted(String notesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		
		Utils.waitForElement(driver, "ctl00_MainContent_ctl00_RadGridList_ctl00", globalVariables.elementWaitTime, "id");
		WebElement rowElement = driver.findElement(By.id("ctl00_MainContent_ctl00_RadGridList_ctl00"));
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ notesDetailsText +"')]",rowElement, "xpath" , notesDetailsText, "Notes Table");
		Log.pass("Verified deletion of notes");
	}
	
	
	
	public void clickLockIconClient(String notesDetailsText, boolean screenshot)  
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDetailsText +"')]/../../td[2]/a[@title='Lock']", "xpath", "Lock Notes Icon");
	}
	
	
	public void verifyNotesLocked(String notesDetailsText, boolean screenshot)  
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 11 Oct 2019
		*/ 
		clickDeleteIconClient(notesDetailsText, screenshot);

		try {
			if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
				Log.pass("Notes " + (notesDetailsText) + " locked", driver, screenshot);
			} else {
				Log.fail("Notes not Locked");
				
			}
		} catch (NoAlertPresentException e) {
			Log.fail("Notes not Locked", driver, true);
		}
	}
	
	
	public void addNotesInCase(String notesDescription,boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		 * 
		 */
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.clickElement(driver, btn_AddNotesInCase, "add notes button in case");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Case Notes/Comments", "title", "false");
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", 50, "id");
		
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_NotesDescriptionInCase, notesDescription, "notes description in case level");
		driver.switchTo().defaultContent();
		Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_ClientAccessInCase);
		Utils.clickElement(driver, btn_SaveNotesInCase, "save notes button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");

	}
	
	public void verifyNotesInCase(String notesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ notesDetailsText +"')]", "xpath" , notesDetailsText, "Notes Table");
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0,-350);");
	}
	
	
	public void editAddedNotesInCase(String firstNotesDetailsText, String editFirstNotesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ firstNotesDetailsText +"')]/../../td[2]/a/img[@title='Edit']", "xpath", "On edit notes link");	
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Notes/Comments", "title", "false");
		Utils.waitForElement(driver, btn_SaveEdittedNotesCase);
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_NotesDescriptionInCase, editFirstNotesDetailsText, "Edited text in notes description");
		driver.switchTo().defaultContent();
		Utils.clickElement(driver, btn_SaveEdittedNotesCase, "Clicked on save edited notes button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		((JavascriptExecutor) driver).executeScript("scroll(0,-350);");
	}
	
	
	public void clickCheckBoxInNotesCase(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDescription +"')]/../../td[4]/input[@type='checkbox']", "xpath", "Notes CheckBox");
		
	}

	
	public void clickPrintPreviewInCase(boolean screenshot) 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		Utils.clickElement(driver, btn_PrintPreviewNotesInCase, "Print Preview in case level");
	}
	
	public void verifyPrintPreviewCase(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		clickPrintPreviewInCase(true);
		driver.switchTo().defaultContent();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Notes", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "on the print preview page");
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		((JavascriptExecutor) driver).executeScript("scroll(0,-350);");
	}
	
	public void clickDeleteIconCase(String notesDetailsText, boolean screenshot)  
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDetailsText +"')]/../../td[3]" , "xpath", "Delete icon in Case Notes Page");
	}
	
	public void deleteNotesCase(String notesDetailsText, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		clickDeleteIconCase(notesDetailsText,true);
	    //Utils.clickElement(driver, btn_DeleteNotesInCase, "delete notes button in case");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Delete Case Notes", "title", "false");
		Utils.clickElement(driver, btn_DeleteNotesInCase, "delete notes button");
		if (Utils.isAlertPresent(driver)) {
			driver.switchTo().alert().accept();
		} else {
			System.out.println("alert was not present");
		}
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0,-350);");
	}
	
	public void verifyNotesDeletedCase(String notesDetailsText, boolean screenshot) throws Exception 
	{
		driver.navigate().refresh();
		
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.waitForElement(driver, "//td[contains(text(),'Notes List')]", globalVariables.elementWaitTime, "xpath");
		WebElement txtNotesList = driver.findElement(By.xpath("//td[contains(text(),'Notes List')]"));
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ notesDetailsText +"')]", txtNotesList, "xpath", notesDetailsText, "Notes Table");
		driver.switchTo().defaultContent();
	}
	
	public void verifyNotesViewPageInCase(String notesDescription, boolean screenshot) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ notesDescription +"')]", "xpath", "Notes which was created");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Case Notes/Comments", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "Notes view page");
		Utils.switchWindows(driver, "INSZoom.com -- Case Notes List", "title", "false");
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0,-350);");
	}
	
	public void clickLockIcon(String case_EditNotesTxt, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 14 Oct 2019
		*/ 
		
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		Utils.waitForElement(driver, "__frm", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
	}
	
	
	public void addNotesInCorp(String notesDetails, boolean screenshot) throws Exception {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 07 Feb 2019
		*/ 
		
		//driver.switchTo().frame(0);
		Utils.clickElement(driver, btn_addCorpNotes, "add corp notes button in corp");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add New Corporation Note", "title", "false");
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", 50, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_NotesDescriptionInCase, notesDetails, "notes description in corp level");
		driver.switchTo().defaultContent();
		Utils.selectOptionFromDropDownByVal(driver, "O", dropdown_extraNetAccessInCorp);
		Utils.clickElement(driver, btn_saveCorpNotes, "save notes button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "List", "title", "false");	
	}
	
	
	public void verifyNotesInCorp(String notesDetails)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 07 Feb 2019
		*/ 
	    Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + notesDetails + "')]", "xpath", notesDetails, "notes details in corp");
	}
	
	
	public void verifyNotesListPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 10 Feb 2020
		  */
	    try{
		 Utils.softVerifyPageTitle(driver, "List");
	    }catch(Exception e){
		 Log.failsoft("Verification of Notes List page failed", driver);
	    }

} 
	
}