package com.inszoomapp.pages;

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
import com.support.files.Utils;

public class CM_ClientLetterHTMLPage extends LoadableComponent<CM_ClientLetterHTMLPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	@FindBy(id = "btn_CopyCaseletterfromotherCases")
	WebElement btn_CopyCaseLetter;
	
	@FindBy(id = "btn_ComposeClientLetter")
	WebElement btn_ComposeClientLetter;
	
	@FindBy(id = "btn_ShowallLetters")
	WebElement btn_ShowAllHTML;
	
	@FindBy(id = "btn_SearchLetters")
	WebElement btn_FindLetterTemplateHTML;
	
	@FindBy(id = "txtLetterSearch")
	WebElement searchBox_LetterTemplateHTML;
	
	@FindBy(id = "btn_SaveCaseLetter(HTML)andCloseWindow")
	WebElement btn_SaveAndCloseClientEditLetterWindow;
	
	@FindBy(css = "html > body")
	WebElement textBox_LetterTemplateDescription;
	
	@FindBy(id = "txtLetterSub")
	WebElement textBox_LetterTemplateTitle;
	
	@FindBy(id = "txtLetterDate")
	WebElement textBox_accessDate;
	
	@FindBy(id = "btn_SaveClientLetterandCloseWindow")
	WebElement btn_SaveAndCloseClientComposeLetterWindow;
	
	@FindBy(id = "cboClntAcc")
	WebElement dropDown_clientAccess;
	
	@FindBy(id = "btn_SearchLettersTemplate")
	WebElement btn_FindLettersTemplate;
	
	@FindBy(id = "txtLetterSearch")
	WebElement searchBox_letterTemplate;
	
	@FindBy(xpath = "//a[contains(text(),'Choose Letter Template')]")
	WebElement link_chooseLetterTemplate;


	public CM_ClientLetterHTMLPage(WebDriver driver) {

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
		} else {
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}

	public void composeUsingLetterTemplateClient(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.clickElement(driver, link_chooseLetterTemplate, "choose letter template link");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose Letter Template", "title", "false");
		Utils.enterText(driver, searchBox_letterTemplate, templateName, "in search box");
		Utils.clickElement(driver, btn_FindLettersTemplate, "Find button");
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/following-sibling::td/a[contains(text(),'Copy')]", "xpath", "copy letter template link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Compose Client Letter", "title", "false");
		Utils.verifyIfDataPresent(driver, "//textarea[@id='txtLetterSub'][contains(text(),'"+templateName+"')]", "xpath", templateName, "in template name");
		
		Utils.waitForElement(driver, "htmLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmLetterDesc__ctl0_Editor_contentIframe");
		Utils.verifyIfDataPresent(driver, "//html/body[contains(text(),'"+templateDescription+"')]", "xpath", templateDescription, "description box");
		driver.switchTo().defaultContent();
		Utils.selectOptionFromDropDown(driver, "All", dropDown_clientAccess);
		Utils.enterText(driver, textBox_accessDate, globalVariables.accessDate, "access date");
		Utils.clickElement(driver, btn_SaveAndCloseClientComposeLetterWindow, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com :: View Client Letters", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+templateName+"')]/following-sibling::td[contains(text(),'All')]//following-sibling::td[contains(text(),'"+globalVariables.accessDateVerification+"')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	
	public void clickOnComposeClientLetterButton() throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 03 April 2020
		 */
		Utils.clickElement(driver, btn_ComposeClientLetter, "compose client letter button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Compose Client Letter", "title", "false");
	}
	
	
	public void composeLetterClient(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.enterText(driver, textBox_LetterTemplateTitle, templateName, "title");
		
		Utils.waitForElement(driver, "htmLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmLetterDesc__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_LetterTemplateDescription, templateDescription, "description");
		driver.switchTo().defaultContent();
		Utils.selectOptionFromDropDown(driver, "All", dropDown_clientAccess);
		Utils.enterText(driver, textBox_accessDate, globalVariables.accessDate, "access date");
		Utils.clickElement(driver, btn_SaveAndCloseClientComposeLetterWindow, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com :: View Client Letters", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+templateName+"')]/following-sibling::td[contains(text(),'All')]//following-sibling::td[contains(text(),'"+globalVariables.accessDateVerification+"')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	public void editClientLetter(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.enterText(driver, textBox_LetterTemplateTitle, templateName, "title");
		
		Utils.waitForElement(driver, "htmLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmLetterDesc__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_LetterTemplateDescription, templateDescription, "description");
		driver.switchTo().defaultContent();
		Utils.clickElement(driver, btn_SaveAndCloseClientEditLetterWindow, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com :: View Client Letters", "title", "false");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+templateName+"')]/following-sibling::td[contains(text(),'All')]//following-sibling::td[contains(text(),'"+globalVariables.accessDateVerification+"')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	public void clickClientEditLetter(String templateName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/preceding-sibling::td[2]/a", "xpath", "edit option");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Edit Client Letter", "title", "false");
	}
	
	public void clickClientPrintPreview(String templateName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/..//a[@title='Print preview Client letter']", "xpath", "print preview link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, ">INSZoom.com - Print Client Letters", "title", "false");
	}
	
	public void verifyClientPrintPreview(String templateDescription)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+templateDescription+"')]", "xpath", templateDescription, "description in print preview page");
		driver.close();
	}
	
	public void verifySearchBox(String template1, String template2)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.enterText(driver, searchBox_LetterTemplateHTML, template1, "in HTML search box");
		Utils.clickElement(driver, btn_FindLetterTemplateHTML, "find button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+template1+"')]", "xpath", template1, "as find result");
		Utils.enterText(driver, searchBox_LetterTemplateHTML, "JUNK", "in HTML search box");
		Utils.clickElement(driver, btn_FindLetterTemplateHTML, "find button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No records found.')]", "xpath", "No Records Found", "as find result");
		Utils.clickElement(driver, btn_ShowAllHTML, "show all button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+template1+"')]", "xpath", template1, "as find result");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+template2+"')]", "xpath", template2, "as find result");
	}
	
	
	public void deleteLetterTemplate(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :22 April 2020
		 */
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/..//img[@title='Delete Client Letter']", "xpath", "delete icon");
		 if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
		 } 
		 else {
				System.out.println("alert was not present");
		 }
		 driver.navigate(). refresh();
		 Utils.waitForElement(driver, "//table[@summary='Client Letters']//tr", globalVariables.elementWaitTime, "xpath");
		 WebElement waitElement = driver.findElement(By.xpath("//table[@summary='Client Letters']//tr"));
		 Utils.verifyIfDataNotPresent(driver, "//th[contains(text(),'"+templateName+"')]", waitElement, "xpath", templateName, "letter template page");
	}
	
}
