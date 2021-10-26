package com.inszoomapp.pages;

import org.openqa.selenium.By;
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

public class CM_CaseLetterHTMLPage extends LoadableComponent<CM_CaseLetterHTMLPage> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	@FindBy(id = "btn_SaveCaseLetters")
	WebElement btn_SaveCaseLetters;
	
	@FindBy(id = "txtLetterSearch")
	WebElement searchBox_caseLetterTitle;
	
	@FindBy(id = "btn_CopyCaseletterfromotherCases")
	WebElement btn_copyCaseLetter;
	
	@FindBy(id = "btn_ShowallLetters")
	WebElement btn_ShowAll;
	
	@FindBy(id = "btn_SearchLetters")
	WebElement btn_Find;
	
	@FindBy(id = "txtLetterSearch")
	WebElement searchBox_LetterTemplateHTML;
				  
	@FindBy(id = "btn_SaveCaseLetter(HTML)andCloseWindow")
	WebElement btn_SaveAndCloseComposePage;
	
	@FindBy(css = "html > body")
	WebElement textBox_LetterTemplateDescription;
	
	@FindBy(id = "txtLetterSub")
	WebElement textBox_LetterTemplateTitle;
	
	@FindBy(id = "txtLetterDate")
	WebElement textBox_accessDate;
	
	@FindBy(xpath = "//select[@id='cboClntAcc']")
	WebElement dropDown_clientAccess;
	
	@FindBy(id = "btn_SearchLettersTemplate")
	WebElement btn_FindLettersTemplate;
	
	@FindBy(id = "txtLetterSearch")
	WebElement searchBox_letterTemplate;
	
	@FindBy(xpath = "//a[contains(text(),'Choose Letter Template')]")
	WebElement link_chooseLetterTemplate;
	
	@FindBy(id = "cboTmpl")
	WebElement dropDown_templateSource;
	
	@FindBy(id = "btn_ComposeCaseLetter(HTML)")
	WebElement btn_compose;


	public CM_CaseLetterHTMLPage(WebDriver driver) {

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
	
	public void clickOnComposeButton() throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 03 April 2020
		 */
		Utils.clickElement(driver, btn_compose, "compose case letter button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Compose Case Letter (HTML)", "title", "false");
	}
	

	public void composeUsingLetterTemplate(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.selectOptionFromDropDown(driver, "Choose from Letter Templates (HTML)", dropDown_templateSource);
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Choose Letter Template (HTML)", "title", "false");
		Utils.enterText(driver, searchBox_letterTemplate, templateName, "in search box");
		Utils.clickElement(driver, btn_FindLettersTemplate, "Find button");
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/following-sibling::td/a[contains(text(),'Copy')]", "xpath", "copy letter template link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Compose Case Letter (HTML)", "title", "false");
		Utils.verifyIfDataPresent(driver, "//textarea[@id='txtLetterSub'][contains(text(),'"+templateName+"')]", "xpath", templateName, "in template name");			 		
		
		Utils.waitForElement(driver, "txtLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame("txtLetterDesc__ctl0_Editor_contentIframe");
		Utils.verifyIfDataPresent(driver, "//html/body[contains(text(),'"+templateDescription+"')]", "xpath", templateDescription, "description box");
		driver.switchTo().defaultContent();
		//Utils.selectOptionFromDropDown(driver, "All", dropDown_clientAccess);
		Utils.selectOptionFromDropDownByVal(driver, "O", dropDown_clientAccess);
		Utils.enterText(driver, textBox_accessDate, globalVariables.accessDate, "access date");
		Utils.clickElement(driver, btn_SaveAndCloseComposePage, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (HTML) List", "title", "false");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th[contains(text(),'"+globalVariables.accessDateVerification+"')]//following-sibling::th[contains(text(),'All')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	public void composeHTMLLetter(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 02 July 2021
		 */
		Utils.enterText(driver, textBox_LetterTemplateTitle, templateName, "title");
		
		Utils.waitForElement(driver, "txtLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame("txtLetterDesc__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_LetterTemplateDescription, templateDescription, "description");
		driver.switchTo().defaultContent();
		Utils.selectOptionFromDropDown(driver, "All", dropDown_clientAccess);
		Utils.enterText(driver, textBox_accessDate, globalVariables.accessDate, "access date");
		Utils.clickElement(driver, btn_SaveAndCloseComposePage, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (HTML) List", "title", "false");
		//Commented by Souvik as verification will be done in the subsequent method
		//Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th[contains(text(),'"+globalVariables.accessDateVerification+"')]//following-sibling::th[contains(text(),'All')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	public void verifyIfHTMLLetterPresent(String templateName) throws InterruptedException
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On : 02 July 2021
		 */ 
		
		
		Utils.waitForElement(driver,"//img[@title='Edit Case Letter (HTML)'][1]" , globalVariables.elementWaitTime, "xpath");
		
		boolean truth = false;
		int i = 1;
		
		do
		{			
			Utils.waitForElement(driver, "//td[contains(text(),'Current Page') and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");			
			if(Utils.isElementPresent(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th[contains(text(),'"+globalVariables.accessDateVerification+"')]//following-sibling::th[contains(text(),'All')]", "xpath"))
			{
				Log.message("", driver, true);
				Log.pass("HTML Letter "+templateName+" is present");
				truth = true;
				break;
			}
			
			else
			{
				Utils.clickDynamicElement(driver, "//img[@title='Go To Next Page']", "xpath", "Next page");						
				i += 1;
			}
		}while(Utils.isElementPresent(driver, "//img[@title='Go To Next Page']", "xpath"));
	
		if(!truth)
		{
			Log.fail("HTML Letter "+templateName+" is present", driver, true);
		}
	}

	
	public void clickEditLetter(String templateName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :13 April 2020
		 */
		Utils.clickDynamicElement(driver, "//th[contains(text(),'"+templateName+"')]/preceding-sibling::th[2]//img", "xpath", "edit option");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Letter (HTML)", "title", "false");
	}
	
	public void editLetter(String templateName, String templateDescription) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :13 April 2020
		 */
		Utils.enterText(driver, textBox_LetterTemplateTitle, templateName, "title");
		
		Utils.waitForElement(driver, "txtLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame("txtLetterDesc__ctl0_Editor_contentIframe");
		Utils.enterText(driver, textBox_LetterTemplateDescription, templateDescription, "description");
		driver.switchTo().defaultContent();
		Utils.clickElement(driver, btn_SaveAndCloseComposePage, "save and close button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (HTML) List", "title", "false");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th[contains(text(),'"+globalVariables.accessDateVerification+"')]//following-sibling::th[contains(text(),'All')]", "xpath", globalVariables.accessDateVerification, "added letter template");
	}
	
	public void clickPrintPreview(String templateName) throws Exception 
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On :17 June 2021
		 */
		Utils.waitForElement(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th//a[contains(text(),'Preview with Logo')]", globalVariables.elementWaitTime, "xpath");
		WebElement lnk_Preview = driver.findElement(By.xpath("//th[contains(text(),'"+templateName+"')]/following-sibling::th//a[contains(text(),'Preview with Logo')]"));
		Utils.scrollIntoView(driver, lnk_Preview);
		Utils.clickDynamicElement(driver, "//th[contains(text(),'"+templateName+"')]/following-sibling::th//a[contains(text(),'Preview with Logo')]", "xpath", "print preview link");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letter (HTML)", "title", "false");
	}
	
	public void verifyPrintPreview(String templateDescription)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		
		Utils.waitForElement(driver, "txtLetterDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame("txtLetterDesc__ctl0_Editor_contentIframe");
		Utils.verifyIfDataPresent(driver, "//body[contains(text(),'"+templateDescription+"')]", "xpath", templateDescription, "description in print preview page");
		driver.switchTo().defaultContent();
		driver.close();
	}
	
	
	public void verifySearchBox(String template1, String template2)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :03 April 2020
		 */
		Utils.enterText(driver, searchBox_LetterTemplateHTML, template1, "in HTML search box");
		Utils.clickElement(driver, btn_Find, "find button");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+template1+"')]", "xpath", template1, "as find result");
		Utils.enterText(driver, searchBox_LetterTemplateHTML, "JUNK", "in HTML search box");
		Utils.clickElement(driver, btn_Find, "find button");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'No Records found.')]", "xpath", "No Records Found", "as find result");
		Utils.clickElement(driver, btn_ShowAll, "show all button");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+template1+"')]", "xpath", template1, "as find result");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+template2+"')]", "xpath", template2, "as find result");
	}
	
	
	public void deleteLetter(String templateName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :22 April 2020
		 */
		Utils.clickDynamicElement(driver, "//th[contains(text(),'"+templateName+"')]/..//img[@title='Delete Case Letter (HTML)']", "xpath", "delete icon");
		 if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
		 } 
		 else {
				System.out.println("alert was not present");
		 }
		 driver.navigate(). refresh();
		 Utils.waitForElement(driver, "//table[@summary='Case Letters (HTML)']//tr", globalVariables.elementWaitTime, "xpath");
		 WebElement waitElement = driver.findElement(By.xpath("//table[@summary='Case Letters (HTML)']//tr"));
		 Utils.verifyIfDataNotPresent(driver, "//th[contains(text(),'"+templateName+"')]", waitElement, "xpath", templateName, "letter template page");
	}
	
	
	public void copyCaseLetter(String templateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On :22 April 2020
		 */
		Utils.clickElement(driver, btn_copyCaseLetter, "copy case letter button");
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Copy Case Letters", "title", "false");
		Utils.enterText(driver, searchBox_caseLetterTitle, templateName, "in search box");
		Utils.clickElement(driver, btn_Find, "find button");
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+templateName+"')]/..//input", "xpath", "template check box");
		Utils.clickElement(driver, btn_SaveCaseLetters, "save button");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Letters (HTML) List", "title", "false");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'"+templateName+"')]", "xpath", templateName, "letter template page");
	}
	
}
