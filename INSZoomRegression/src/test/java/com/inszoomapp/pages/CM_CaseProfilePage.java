package com.inszoomapp.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CM_CaseProfilePage extends LoadableComponent<CM_CaseProfilePage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "btn_MarkCasefordeletion")
	WebElement btn_markForDeletion;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00__0")
	WebElement tbl_CaseList;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")			//Added by Kuchinad Shashank
    WebElement txt_ShowAllCase;	
	
	@FindBy(id = "CaseList")
	WebElement waitElement_caseListHeader;
	
	@FindBy(id = "LM101")
	WebElement tab_addCase;
	
	@FindBy(xpath = "//span[contains(text(),'Receipt Numbers')] ")
	WebElement tab_receiptNumber;										//Added by Yatharth on 12th Au,2020
	
	@FindBy(xpath = "//h1[contains(text(),'Case Receipt Numbers')]")
	WebElement txt_headerReceiptNumbers;								//Added by Yatharth on 12th Aug,2020
	
	@FindBy(id = "btn_PackageForFiling")	//Added by Kuchinad Shashank on 18th March
	WebElement btn_packageForFiling;
	
	@FindBy(xpath = "//span[contains(text(),'Forms')]")	//Added by Kuchinad Shashank on 18th March
	WebElement tab_forms;
	
	@FindBy(xpath = "//h1[contains(text(),'case forms')]")	//Added by Kuchinad Shashank on 18th March
	WebElement header_caseForms;
	
	@FindBy(id = "LMAnchor27")				//Added by Yatharth on 17th March 2020
	WebElement tab_Documents;
	
	@FindBy(id = "LMAnchor595")				//Added by Yatharth on 16th March 2020
	WebElement tab_lettersWord;
	
	@FindBy(xpath = "//h1[contains(text(),'Letters')]")		//Added by Yatharth on 17th March 2020
	WebElement txt_headerLetterPage;
	
	@FindBy(xpath = "//h1[contains(text(),'Documents')]")		//Added by Yatharth on 17th March 2020
	WebElement txt_headerDocumentsPage;

	public CM_CaseProfilePage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CaseProfilePage(WebDriver driver) {
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
			Log.fail("Case profile Page did not open up. Site might be down.", driver, true);
		}
	}

	
	public void verifyCaseprofileDetails(String edittedDate,String receiptNumber,boolean screenshot) throws Exception 
	{
		Utils.waitForElement(driver, "frmCaseDetails", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmCaseDetails");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Recently Added Receipt')]/../td[2][contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "Receipt number");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Recently Added  Receipt Date')]/../td[4][contains(text(),'"+edittedDate+"')]", "xpath", edittedDate, "edited Receipt Date details has been verified in the case profile");
		driver.switchTo().defaultContent();
	}


	public void clickPackageForFilingButton() throws Exception
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		Utils.clickElement(driver, btn_packageForFiling, "Package For Filing Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Assemble - INSZoom.com", "title", "false");
		
	}
	
	
	public void clickFormsTab() throws Exception
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 18/03/2020
		 * Modified: K Shashank on  7/17/2020
		 * Modified: Added Scroll to Element function
		 */
		
		Utils.scrollToElement(driver, tab_forms);
		
		Utils.clickElement(driver, tab_forms, "Forms Tab");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		
		Utils.switchWindows(driver, "Form - INSZoom.com", "title", "false");
		
		Utils.waitForElement(driver, header_caseForms);
	}
	
	
	public void clickDocuments()
	{
		//Created by Yatharth Pandya on 17th March 2020
		
		Utils.clickElement(driver, tab_Documents, "status docs");
		Utils.switchToNewWindow(driver);
		Utils.waitForElement(driver, txt_headerDocumentsPage);
	}

	
	public void clickLettersWord()
	{
		//Created by Yatharth Pandya on 17th March 2020
		
		Utils.clickElement(driver, tab_lettersWord, "Letters HTML");
		Utils.switchToNewWindow(driver);
		Utils.waitForElement(driver, txt_headerLetterPage);
	}
	
	public void clickReceiptNumber()
	{
		//Created by Yatharth Pandya on 12th Aug,2020
		
		Utils.clickElement(driver, tab_receiptNumber, "Receipt Number");
		Utils.switchToNewWindow(driver);
		Utils.waitForElement(driver, txt_headerReceiptNumbers);
		
	}
	
	public void verifyCaseStatus(String caseStatus)
	{
		//Created by : Yatharth Pandya
		//Created on : 18th August, 2020
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ caseStatus +"')]", "xpath", caseStatus, "Case status");
	}
	
	public void verifyCaseReceiptData(String date)
	
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 21/08/2020
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Receipt Type')]/following-sibling::td[contains(text(),'" + globalVariables.receiptType1 + "')]", "xpath", globalVariables.receiptType1, "Receipt type");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Receipt Number')]/following-sibling::td[1][contains(text(),'" + globalVariables.receiptNumberForDataValidation + "')]", "xpath", globalVariables.receiptNumberForDataValidation, "Receipt Number");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Receipt Status')]/following-sibling::td[1][contains(text(),'" + globalVariables.receiptStatus1 + "')]", "xpath", globalVariables.receiptStatus1, "Receipt Status");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Sent To Gov. Agency On / Filed Date')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Filed Date");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Receipt Notice Date')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Receipt Notice Date");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Receipt/Received Date')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Receipt/Received Date");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'RFE/Audit Received')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "RFE/Audit Received");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'RFE/Audit Response Due')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "RFE/Audit Response Due");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'RFE/Audit Response Submitted')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "RFE/Audit Response Submitted");
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Approved On')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Approved On");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Approval Received By Firm On')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Approval Received by Firm On");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Approval Valid From')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Approval Valid from");

		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Withdrawn Date')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Withdrawn Date");
		Utils.verifyIfDataPresent(driver, "//th[contains(text(),'Denial Date')]/following-sibling::td[1][contains(text(),'" + date + "')]", "xpath", date, "Denial Date");

	}
	
	
	public void verifyCaseCreationAccess(boolean value)
	 {
		 /*
		  * Created by Kuchinad Shashank
		  * Created on 06th August 2020
		  */
		 
		 if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "LM101", "id", "Add Case Tab", "Left sub Menu");
		 }
		 else
		 {
			 Utils.verifyIfDataNotPresent(driver, "LM101", tab_Documents, "id", "Add Case Tab", "Left sub Menu");
		 }
	 }
	 
	 
	 public void verifyDeleteButtonAccess(boolean value)
	 {
		 /*
		  * Created By : Kuchinad Shashank
		  * Created On : 2 March 2020
		  */ 
		   
		 Utils.clickElement(driver, btn_markForDeletion, "Mark for Deletion Button");
		 String msg = driver.switchTo().alert().getText();
		 driver.switchTo().alert().dismiss();
		 boolean result = msg.contains("Are you sure to mark this Case for deletion?");
		 if(value)
		 {
			if(result)
			{
				Log.pass("Verified. Delete Corporation Access is given.", driver, true);
			}
			else
			{
				Log.fail("Delete Corporation Access is not given.", driver, true);
			}
		 }
		 else
		 {
			if(!result)
			{
				Log.pass("Verified. Delete Corporation Access is not given.", driver, true);
			}
			else
			{
				Log.fail("Delete Corporation Access is given.", driver, true);
			}
		  }
	}
	
}