package com.inszoomapp.pages;

import org.apache.commons.lang.RandomStringUtils;
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


public class CM_ProspectsListPage extends LoadableComponent<CM_ProspectsListPage>
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "btn_Savenewprospectivecorporationdetails")
	WebElement btn_SaveCorporatoinDetails;
	
	@FindBy(id = "txtPhone1")
	WebElement textBox_corpContactNumber;
	
	@FindBy(id = "txtEmail")
	WebElement textBox_corpEmail;
	
	@FindBy(id = "txtLname")
	WebElement textBox_contactPersonLastName;
	
	@FindBy(id = "txtFname")
	WebElement textBox_contactPersonFirstName;
	
	@FindBy(id = "txtOrg")
	WebElement textBox_corporationName;
	
	@FindBy(id = "txtFileNum")
	WebElement textBox_corporationFileNumber;
	
	@FindBy(id = "btn_SaveprospectiveClientregistration")
	WebElement btn_SaveProspectiveClientRegistration;
	
	@FindBy(xpath = "//select[@name='cboBnfType']")
	WebElement dropDown_category;
	
	@FindBy(id = "btn_AcceptprospectiveClientasClient")
	WebElement btn_AcceptAsClient;
	
	@FindBy(id = "btn_Save")
	WebElement btn_SaveConvesionPossiblity;
	
	@FindBy(xpath = "//input[@value='H']")
	WebElement radioButton_High;
	
	@FindBy(id = "btn_Close")
	WebElement btn_CloseIntakeSheet;
	
	@FindBy(xpath = "(//input[@id='btn_SaveNewProspectiveClientDetails'])[1]")
	WebElement btn_saveProspectiveClient;
	
	@FindBy(xpath = "(//input[@id='lname'])[1]")
	WebElement textBox_prospectiveClientLastName;
	
	@FindBy(xpath = "(//input[@id='fname'])[1]")
	WebElement textBox_prospectiveClientFirstName;
	
	@FindBy(xpath = "(//tr[@id='ctl00_MainContent_ctl00_RadGridList_ctl00__0']/td/a[contains(@href,'new_epr_clnt_view.aspx')])[1]")
	WebElement link_firstCorporationOnList; 
			
	@FindBy(xpath = "//div[@id='ctl00_MainContent_ctl00_RadGridList_GridData']//tr")
	WebElement table_Result;
					 
	@FindBy(xpath = "(//tr[@id='ctl00_MainContent_ctl00_RadGridList_ctl00__0']/td/a[contains(@href,'prosp_personal_view.aspx')])[1]")
	WebElement link_firstClientOnList; 
			
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_bnf_name")
	WebElement icon_ClientFilter; 
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_bnf_name")
	WebElement searchbox_clientName;
	
	@FindBy(id="btn_SearchClient")
	WebElement btn_find;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(2)>a[class='rmLink']")
    WebElement opt_Contains;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    WebElement opt_EqualTo;
	
	@FindBy(xpath = "//a[contains(@title,'Show All') and contains(@style,':bold')]")
	WebElement lnk_ShowAllCorporationBold;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00__0")
	WebElement tbl_ResultList;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_new_epr_petnr_nm")
	WebElement searchBox_CorporationName;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_new_epr_petnr_nm")
	WebElement icon_CorporationNameFilter; 
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")
    WebElement txt_ShowAll;

	@FindBy(id = "LMLeftTab3")
	WebElement tab_ProspectIndividual;
	
	@FindBy(id = "LMLeftTab2")
	WebElement tab_ProspectCorporation;
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_ctl00")
	WebElement btn_add;
	
	@FindBy(xpath = "//td[@id='pageHDR'][contains(text(),'Prospective Client')]")
	WebElement header_prospectiveClient;
	
	public CM_ProspectsListPage(WebDriver driver)
	{
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void verifyAddProspectsButtonPresent(boolean value)
	{
		/*
		 * Created by Kuchinad Shshasnk
		 * Created on 4/08/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_ctl00", "id", "Add Prospective Client Button", "Prospective Client List Page");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_ctl00", header_prospectiveClient, "id", "Add Peospective Client Button", "prospective Client List Page");
		}
	}
	
	public void clickProspectIndividual()
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		Utils.clickElement(driver, tab_ProspectIndividual, "prospect individual tab");
	}
	
	public void clickProspectCorporation()
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		Utils.clickElement(driver, tab_ProspectCorporation, "prospect corporation tab");
	}
	
	public void clickOnProspectiveCorporationName(String corporation)
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		   try {
		    	 Utils.clickElement(driver, txt_ShowAll, "Show all Corporation" + Utils.getPageLoadTime(driver));
				 Utils.waitUntilLoaderDisappear(driver);
				 Log.message("" + Utils.getPageLoadTime(driver));
				 Utils.waitForElement(driver, tbl_ResultList);
			 }
		     catch (Exception e) {
				 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
			 }
		     Utils.waitForElement(driver, lnk_ShowAllCorporationBold);
			 Utils.enterText(driver, searchBox_CorporationName, corporation, "in Corporation Name search box");
			 Utils.clickElement(driver, icon_CorporationNameFilter, "Corporation Name filter");
		          
			 try {
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
				 Utils.waitUntilLoaderDisappear(driver);
				 Utils.waitForElement(driver, tbl_ResultList);
				 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

			 } catch (Exception e) {
				 Log.message("", driver, true);
				 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
			 }
		        
			 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
			 {
				 Log.fail("Unable to find desired corporation . Hence can not Proceed Further", driver, true);
			 }
		          
			 Utils.waitForElement(driver, "//a[contains(text(),'" + corporation + "')]", globalVariables.elementWaitTime, "xpath");
			 WebElement lnk_Corporation= driver.findElement(By.xpath("//a[contains(text(),'" + corporation + "')]"));
			 Utils.clickElement(driver, lnk_Corporation, corporation);
	}
	
	
	public void clickOnProspectiveClientName(String clientName)
	{
		/*
		 * Created by Likitha Krishna
		 * Created on 19 Aug 2020
		 */
		try {
	    	 Utils.clickElement(driver, txt_ShowAll, "Show all client" + Utils.getPageLoadTime(driver));
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_ResultList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
	     Utils.waitForElement(driver, lnk_ShowAllCorporationBold);
		 Utils.enterText(driver, searchbox_clientName, clientName, "in Corporation Name search box");
		 Utils.clickElement(driver, icon_ClientFilter, "Corporation Name filter");
	          
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_ResultList);
			 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	        
		 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
		 {
			 Log.fail("Unable to find desired corporation . Hence can not Proceed Further", driver, true);
		 }
	          
		 Utils.waitForElement(driver, "//a[contains(text(),'" + clientName + "')]", globalVariables.elementWaitTime, "xpath");
		 WebElement lnk_Corporation= driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]"));
		 Utils.clickElement(driver, lnk_Corporation, clientName);
	}
	
	
		public void clickOnFirstProspectiveClientName()
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 19 Aug 2020
			 */	
			Utils.waitForElement(driver, table_Result);
			Utils.clickElement(driver, link_firstClientOnList, "first client prospect");
		}
		
		
		public void clickOnFirstProspectiveCorporation()
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 19 Aug 2020
			 */	
			Utils.waitForElement(driver, table_Result);
			Utils.clickElement(driver, link_firstCorporationOnList, "first corporation prospect");
		}
		
		
		public void clickAddProspectiveButton()
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 24 Aug 2020
			 */	
			Utils.clickElement(driver, btn_add, "add button");
		}
		
		
		public void addNewProspectiveClient(String firstName, String lastName) throws Exception
		{
			/*
			 * Edited by Souvik Ganguly
			 * Edited on 25 June 2021
			 */	
			clickAddProspectiveButton();
			Utils.enterText(driver, textBox_prospectiveClientFirstName, firstName, "first name");
			Utils.enterText(driver, textBox_prospectiveClientLastName, lastName, "last name");
			Utils.clickElement(driver, btn_saveProspectiveClient, "save");
			Utils.waitForElementNotVisible(driver, "//input[@id='btn_SaveNewProspectiveClientDetails'])[1]", "xpath");
			Utils.waitForAllWindowsToLoad(2, driver);
			Utils.switchToWindowById(driver);				
			//Utils.switchWindows(driver, "aty_prosp_bnf_qst_view.aspx", "url", "false");
			driver.close();
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Client Intake Sheet", "title", "false");
			String clientName = firstName + " " + lastName ;
			Utils.clickDynamicElement(driver, "//a//u[contains(text(),'"+clientName+"')]", "xpath", "client name "+clientName);
		}
		
		public void setClientConversionPossiblity(String clientName) throws Exception
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 19 Aug 2020
			 */
			try {
		    	 Utils.clickElement(driver, txt_ShowAll, "Show all Clients" + Utils.getPageLoadTime(driver));
				 Utils.waitUntilLoaderDisappear(driver);
				 Log.message("" + Utils.getPageLoadTime(driver));
				 Utils.waitForElement(driver, tbl_ResultList);
			 }
		     catch (Exception e) {
				 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
			 }
		     Utils.waitForElement(driver, lnk_ShowAllCorporationBold);
			 Utils.enterText(driver, searchbox_clientName, clientName, "in Corporation Name search box");
			 Utils.clickElement(driver, icon_ClientFilter, "Corporation Name filter");
		          
			 try {
				 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_Contains);
				 Utils.waitUntilLoaderDisappear(driver);
				 Utils.waitForElement(driver, tbl_ResultList);
				 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

			 } catch (Exception e) {
				 Log.message("", driver, true);
				 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
			 }
		        
			 if(Utils.isElementPresent(driver, "//div[contains(text(),'No records to display.')]", "xpath"))
			 {
				 Log.fail("Unable to find desired corporation . Hence can not Proceed Further", driver, true);
			 }
		          
			 Utils.waitForElement(driver, "//a[contains(text(),'"+clientName+"')]/../..//a[contains(text(),'Set')]", globalVariables.elementWaitTime, "xpath");
			 Utils.clickDynamicElement(driver, "//a[contains(text(),'"+clientName+"')]/../..//a[contains(text(),'Set')]", "xpath", "set button");
			 Utils.waitForAllWindowsToLoad(2, driver);
			 Utils.switchWindows(driver, "INSZoom.com - Prospective Client Conversion Possibility", "title", "false");
			 Utils.clickElement(driver, radioButton_High, "high");
			 Utils.clickElement(driver, btn_SaveConvesionPossiblity, "save");
		}
		
		
		public void acceptProspectiveClientAsClient() throws Exception
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 19 Aug 2020
			 */
			Utils.clickElement(driver, btn_AcceptAsClient, "accept as client button");
			driver.switchTo().alert().accept();
			Utils.selectOptionFromDropDown(driver, "Individual/Family Based", dropDown_category);
			Utils.clickElement(driver, btn_SaveProspectiveClientRegistration, "save button");
		}
		
		public void addProspectiveCorporation(String corporationName, String contactPersonFirstName, String contactPersonLastName, String fileNumber, String emailID) throws Exception
		{
			/*
			 * Created by Likitha Krishna
			 * Created on 25 Aug 2020
			 */
			clickAddProspectiveButton();
			Utils.enterText(driver, textBox_corporationFileNumber, fileNumber, "file number");
			Utils.enterText(driver, textBox_corporationName, corporationName, "corporation name");
			Utils.enterText(driver, textBox_contactPersonFirstName, contactPersonFirstName, "contact person first name");
			Utils.enterText(driver, textBox_contactPersonLastName, contactPersonLastName, "contact person last name");
			Utils.enterText(driver, textBox_corpEmail, emailID, "email ID");
			String phoneNumber = RandomStringUtils.randomNumeric(5);
			Utils.enterText(driver, textBox_corpContactNumber, phoneNumber, "phone number");
			Utils.clickElement(driver, btn_SaveCorporatoinDetails, "save");
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'File Number')]/../td[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Corporation Name')]/../td[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Telephone #1')]/../td[contains(text(),'"+phoneNumber+"')]", "xpath", phoneNumber, "phone number");
			Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Email')]/../td/a[contains(text(),'"+emailID+"')]", "xpath", emailID, "email Id");
		}
}
