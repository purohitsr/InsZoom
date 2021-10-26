package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_CorporationPage extends LoadableComponent<CM_CorporationPage>
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	public String corpName = "" ; // Gets updated in 'enterDataForCorporationCreation()'
	
	@FindBy(xpath = "//input[@type='submit' and @value='Add']")
    WebElement btn_Add; //Added by Saurav Purohit on 9th Aug 2021
	
	@FindBy(xpath = "//span[contains(text(),'Client List')]")
    WebElement tab_clientList; //Added by Saurav Purohit on 9th Aug 2021
	
	@FindBy(xpath = "(//table[@summary='Supervisor/Corp User']/tbody/tr)[last()]")
    WebElement lastRow_SupervisorTable; //Added by Saurav Purohit on 9th Aug 2021

	@FindBy(id = "CorporationList")
	WebElement waitElement_corpListHeader;
	
	@FindBy(xpath = "//th[contains(text(),'User Id')]/../td")
    WebElement textBox_userID;
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")	//Added by Yatharth Pandya on 2nd march 2020
	WebElement tab_showAll;
	
	@FindBy(css = "table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']")		//Added by Yatharth Pandya on 2/3/2020
	WebElement waitElement_clientList;
	
	@FindBy(css = "li[class='rmItem ']:nth-child(6)>a[class='rmLink']")
    WebElement opt_EqualTo;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_epr_petnr_nm")
	WebElement searchBox_CorporationName;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_epr_petnr_nm")
	WebElement icon_CorporationNameFilter; 
	
	@FindBy(xpath = "//u[contains(text(),'Show All')]")
    WebElement txt_ShowAllCorporation;
	
	@FindBy(id="ctl00_MainContent_ctl00_RadGridList_ctl00__0")
	WebElement tbl_CorporationList;
	
	@FindBy(xpath="//span[contains(text(),'Supervisor')]")
    WebElement tab_suprevisor;
	
	@FindBy(xpath="//span[contains(text(),'Client List')]")
    WebElement option_leftMenuClientList;
	
	@FindBy(xpath = "//input[@name='txtSearch']")
	WebElement txtbox_headQuarterName;
	
	@FindBy(xpath = "//u[contains(text(),'Headquarter')]")
	WebElement lnk_headQuarter;
	
	@FindBy(xpath = "//span[contains(text(),'Headquarter Branches')]")
	WebElement tab_HQBranches;
	
	@FindBy(css = "td[class='LMIS'][id='LMEprEmails']")
	WebElement tab_Emails;
	
	@FindBy(css = "table[summary='Login Info']>tbody>tr:nth-child(3)>td[class='TBLDATedit']")
	WebElement txt_UserId;

	@FindBy(css = "td[title='Click here to change Login Id']")
	WebElement tab_ChangeLoginId;
	
	@FindBy(css = "input[class='rgFilterBox'][name='ctl00$MainContent$ctl00$RadGridList$ctl00$ctl02$ctl03$FilterTextBox_epr_petnr_nm']")
	WebElement searchbox_CorporationName;
	
	@FindBy(css = "input[name='ctl00$MainContent$ctl00$RadGridList$ctl00$ctl02$ctl03$Filter_epr_petnr_nm'][type='submit']")
	WebElement btn_CorporationNameFilter;
	
	@FindBy(css = "input[title='Add Corporation']")
	WebElement btn_AddCorporation;
	
	@FindBy(id = "txtEprName")
	WebElement textBox_Name;

	@FindBy(id = "txtFname")
	WebElement textBox_FirstName;

	@FindBy(id = "txtLname")
	WebElement textBox_LastName;

	@FindBy(css = " input[id='txtEmail']")
	WebElement textBox_EmailId;
	
	@FindBy(css = "input[id='btn_SaveCorporationDetails']")
	WebElement btn_Save;
	
	@FindBy(className = "corp-bnf-header")
	WebElement text_CorporationHeading;
	
	
	public CM_CorporationPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_CorporationPage(WebDriver driver) {
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
			Log.fail("Corporation Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickAddCorporationButton(boolean screenshot) 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Click on 'ADD' button for corporation
		 * 
		 */
		Utils.clickElement(driver, btn_AddCorporation, "Add button in corporation list page");
		
	}
	
	
	public void enterDataForCorporationCreation(String workbookNameWrite, String sheetName, String corporationName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Enter the required data for corporation creation
		 * 
		 */
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);

		corpName = corporationName + strDate;
		
		Utils.enterText(driver, textBox_Name, corpName, "Corporation Name");			
		
		String fName = "Signing" + RandomStringUtils.randomAlphanumeric(2).toString();
		Utils.enterText(driver, textBox_FirstName, fName, "First Name");

		String lName = "Person" + RandomStringUtils.randomAlphanumeric(2).toString();
		Utils.enterText(driver, textBox_LastName, lName, "Last Name");
		
		String signingPerson = fName + " " + lName ;
		
		// Write the data in excel
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		
		writeExcel.setCellData("ALoginCaseManagerSigningPersonTxt", sheetName, "Value", signingPerson);
		
		Utils.enterText(driver, textBox_EmailId, "corp@inszoom.com", "Email");
		
	}
	
	
	public void clickSaveCorporationButton(boolean screenshot) throws InterruptedException 
	{
	     
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Click on Save button for corporation creation
		 * 
		 */
		

		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		Utils.clickElement(driver, btn_Save, "Save Button");

	}
	
	
	public void verifyIfCorporationCreated(String dataWrite, String sheetName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 25/07/2019
		 * Modifications: Verify if corporation got created or not
		 * 
		 */
		
		try
		{
		    Utils.waitForElement(driver, text_CorporationHeading);
			String savedCorporationName = text_CorporationHeading.getText();
			if (savedCorporationName.equalsIgnoreCase(corpName)) 
			{
				Log.pass("The corporation name has been verified successfully " + savedCorporationName, driver);
			} 
			else 
			{
				Log.message("", driver, screenshot);
				Log.fail("Verification failed. Fetched Corportaion Name: " + savedCorporationName + " Expected Corporation Name: " + corpName);
			}

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			writeExcel.setCellData("ALoginSavedCorporationName", sheetName, "Value", savedCorporationName);
		}catch(Exception e){
			Log.message("",driver, screenshot);
			Log.fail("Unable to fetch data for verification. Error - " + e.getLocalizedMessage());
		}
	}
	
	
	public void searchCorporationByName(String corpName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
		Utils.enterText(driver, searchbox_CorporationName, corpName, "Corporation Name Search Box");
		
		Utils.clickElement(driver, btn_CorporationNameFilter, "Filter to search");
		CM_ClientListPage clientListPage = new CM_ClientListPage(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", clientListPage.opt_EqualTo);
		Log.message("Clicked on 'Equal To' Filter option");
		Utils.waitUntilLoaderDisappear(driver);
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + corpName + "')]", "xpath", "Searched Corporation");
	}
	
	
	public void clickChangeLoginIdTab(String dataWrite, String sheetName, boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
		Utils.clickElement(driver, tab_ChangeLoginId, "Change Login ID Tab");
		
			String LoginIDHrp = "";
			try {
				Utils.waitForElement(driver, txt_UserId);
				LoginIDHrp = txt_UserId.getText();
				Log.message("Fetched Login ID as " + LoginIDHrp);

			} catch (Exception e) {
				Log.message("", driver, screenshot);
				Log.fail("Unable to fetch Login ID. Error Message - " + e.getMessage());
			}

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			writeExcel.setCellData("QA_HRPUserIdBySendEmail", sheetName, "Value", LoginIDHrp);
	}
	
	
	public CM_EmailsPage clickEmailsTab(boolean screenshot) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		
		Utils.clickElement(driver, tab_Emails, "Emails tab");
		return new CM_EmailsPage(driver);
	}
	
	
	public void clickHeadQuarterlink()
	{
		/*
         * Created By : Kuchinad Shashank
         * Created On : 2 March 2020
         */ 
		
		Utils.clickElement(driver, lnk_headQuarter, "HeadQuarter Link");
		
		Utils.waitForElementPresent(driver, "//img[@class='MyLoadingImage']", "xpath");
		Utils.waitForElementNotVisible(driver, "//img[@class='MyLoadingImage'", "xpath");
		
	}
	
	public void searchHeadQuarterByName()
	{
		/*
         * Created By : Kuchinad Shashank
         * Created On : 2 March 2020
         */ 
		Utils.enterText(driver, txtbox_headQuarterName, globalVariables.HeadQuarterName, "Headuater name");
		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + globalVariables.HeadQuarterName + "')]", "xpath", "headQuarter");
	}
	
	
	public void clickHeadQuarterBranches()
	{
		/*
	     * Created By : Kuchinad Shashank
	     * Created On : 2 March 2020
	     */ 
		Utils.clickElement(driver, tab_HQBranches, "Head Quarter Branches");
		Utils.waitForElement(driver, "//td[contains(text(),'View Headquarter Corporation')]", globalVariables.elementWaitTime, "xpath");

	}
	
	
	public void getListOfCorporationsUnderHeadquarter(boolean screenshot) 
    {
          //created by Saksham Kapoor on 24/04/19
          try
          {
        	  	Utils.waitForElement(driver, "table[summary = 'Corporations']>tbody>tr>td+td>a", globalVariables.elementWaitTime, "css");
                List<WebElement> temp = driver.findElements(By.cssSelector("table[summary = 'Corporations']>tbody>tr>td+td>a"));
                for(int i = 0; i < temp.size(); i += 3)
                {
                      globalVariables.corporationsUnderHeadQuarter.add(temp.get(i).getText().trim());
//                    System.out.println(temp.get(i).getText());
                }
                Log.message("Created a list of corporations under " + globalVariables.HeadQuarterName + "headquarter" + globalVariables.corporationsUnderHeadQuarter); // Modified By Saksham Kapoor on 29/04/19
                
          }catch(Exception e){
                Log.message("",driver,screenshot);
                Log.fail("Unable to fetch corporation names");
          }
    }
	
	
	public void getClientListForEachCorporation(boolean screenshot) {
        //created by Saksham Kapoor on 24/04/19
        try{
              
	            for(int i = 0; i < globalVariables.corporationsUnderHeadQuarter.size(); i += 1)
	            {
                String corpName = globalVariables.corporationsUnderHeadQuarter.get(i).trim();
//              String xpathExp = "//a[contains(text(),'"+corpName+"')]";
                String xpathExp = "//a[contains(text(),'" + corpName+ "')]";
                Utils.waitForElement(driver, xpathExp, globalVariables.elementWaitTime, "xpath");
	              driver.findElement(By.xpath(xpathExp)).click();         
	              Utils.waitForPageLoad(driver);
	              Thread.sleep(1000);
	              Utils.waitForElement(driver, option_leftMenuClientList);
	              option_leftMenuClientList.click();
	              
	              Utils.clickElement(driver, tab_showAll, "client name");
	              
	              Utils.waitUntilLoaderDisappear(driver);
	 			  Log.message("" + Utils.getPageLoadTime(driver));
	 			  Utils.waitForElement(driver, waitElement_clientList);
		 			 
	 			  Utils.waitForElement(driver, "table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr>td+td>a", globalVariables.elementWaitTime, "css");
	              List<WebElement> temp = driver.findElements(By.cssSelector("table[id='ctl00_MainContent_ctl00_RadGridList_ctl00']>tbody>tr>td+td>a"));
	              List<String> clientTemp = new ArrayList<String>(); 
	              for(int j = 0; j < temp.size(); j += 3)
	              {
	            	  clientTemp.add(temp.get(j).getText());
	            	  //System.out.println(temp.get(j).getText()); // Modified By Saksham Kapoor on 25/04/19
	              }
	              globalVariables.corpToClientMap.put(corpName, clientTemp);
	              
	              driver.navigate().back();
	              driver.navigate().back();
	              Utils.waitForPageLoad(driver);
	              driver.navigate().back();                
	              Utils.waitForPageLoad(driver);
              }
              Log.message("Created a Map of corporations vs Clients of " + globalVariables.HeadQuarterName + " headquarter as " + globalVariables.corpToClientMap); // Modified By Saksham Kapoor on 25/04/19
              
        }catch(Exception e){
              Log.message("",driver,screenshot);
              Log.fail("Unable to fetch client names");
        }
  }
	
	
	public void clickOnCorporationName(String corporation) 
	 {           
        /*
         * Created By : Saksham Kapoor
         * Created On : 22 Nov 2019
         * 
		 * Modified by Kuchinad Shashank
		 * Modified on 2nd March 2020
		 */
	     try {
	    	 Utils.clickElement(driver, txt_ShowAllCorporation, "All Corporation Link");
	    	 //txt_ShowAllCorporation.click();
	    	 
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CorporationList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
		 
		 
		 //searchBox_CorporationName.sendKeys(corporation);
		 Utils.enterText(driver, searchBox_CorporationName, corporation, "Corporation Name");
		 
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Filter Icon");
		 //icon_CorporationNameFilter.click();
	          
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CorporationList);
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

		 lnk_Corporation.click();
	    
	 }
   
	
   public void getClientListForEachCorpUsers(String corpName)
   {
	   /*
        * Modified  By : Saurav 
        * Modified  On : 9th Aug 2021
        * Added wait for tab_clientList and lastRow_SupervisorTable
        */ 
	   
	   	List<String> clientList = new ArrayList<>();
	   	
	   	clientList = globalVariables.corpToClientMap.get(corpName);
	   	
	   	Utils.waitForElement(driver, tab_clientList);
	   	
	   	Utils.clickElement(driver, tab_clientList, "Client list Tab");
	   	
	   	for(int i=0 ; i < clientList.size() ; i++)
	   	{
		   	Utils.clickElement(driver, tab_showAll, "Show All Clients");
		   	
		   	Utils.waitUntilLoaderDisappear(driver);
		
			Utils.waitForElement(driver, waitElement_clientList);
	   	
	   		String clientName = clientList.get(i);
	   		
	   		Utils.waitForElement(driver, waitElement_clientList);
	   		
	   		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + clientName + "')]", "xpath", "Client Name");
	   		
	   		Utils.waitForElement(driver, tab_suprevisor, globalVariables.elementWaitTime);
	   		
	   		Utils.clickElement(driver, tab_suprevisor, "Suprevisor");
	   		
	   		Utils.waitForElement(driver, lastRow_SupervisorTable);
	   		
	   		List<WebElement> suprevisor = driver.findElements(By.xpath("//table[@class='TableStyle']//td[@class='TBLDATA']/a"));
	   		
	   		for(int j=0; j < suprevisor.size(); j=j+3)
	   		{
	   			String corpUserName = "";
	   			corpUserName = suprevisor.get(j).getText();
	   			
	   			globalVariables.corpUserToClientMap.put(corpUserName, clientName);
	   		}
	   		
	   		driver.navigate().back();
	   		
	   		driver.findElement(By.xpath("//u[contains(text(),'Client List')]")).click();
	   		
	   		Utils.waitForElement(driver, btn_Add);
	   		
//	   		Utils.waitForPageLoad(driver);
	   	
	   	}
	   	
   	
   }
   
   
   
   public void createNewCorp(String workbookNameWrite, String  sheetName, String  corporationName,String firstName, String LastName, String email) throws Exception
   {
	   /*
        * Created By : Likitha Krishna
        * Created On : 19 August 2020
        */ 
	   Utils.clickElement(driver, btn_AddCorporation, "add button");
	   Utils.enterText(driver, textBox_Name, corporationName, "Corporation Name");
	   Utils.enterText(driver, textBox_FirstName, firstName, "First Name");
	   Utils.enterText(driver, textBox_LastName, LastName, "Last Name");
	   Utils.enterText(driver, textBox_EmailId, email, "Email");
	   Utils.clickElement(driver, btn_Save, "Save Button");
	   String signingPersonName = firstName + " " + LastName;
	   String directory = System.getProperty("user.dir");
	   ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
	   writeExcel.setCellData("ALoginSavedCorporationName", sheetName, "Value", corporationName);
	   writeExcel.setCellData("ALoginCaseManagerSigningPersonTxt", sheetName, "Value", signingPersonName);
	   writeExcel.setCellData("ALoginCaseManagerSigningPersonEmailID", sheetName, "Value", email);
	   writeExcel.setCellData("CorporationID", sheetName, "Value", getCorporationID());
   }
   
   public String getCorporationID() throws Exception
   {
	   /*
        * Edited By : Souvik Ganguly
        * Edited On : 25 June 2021
        */ 
	   Utils.clickElement(driver, tab_ChangeLoginId, "change login ID");
	   Utils.waitForElement(driver, textBox_userID);
	   String corpID = textBox_userID.getText();
	   return corpID ;
   }

   
   public void verifyAddCorporationButtonPresent(boolean value)
   {
	   /*
        * Created By : Kuchinad Shashank
        * Created On : 16 June 2020
        */ 
	   
	   if(value)
	   {
		   Utils.verifyIfDataPresent(driver, "input[title='Add Corporation']", "css", "Add Corporation Button", "Corporation Page");
	   }
	   else
	   {
		   Utils.verifyIfDataNotPresent(driver, "input[title='Add Corporation']", waitElement_corpListHeader, "css", "Add Corporation Button", "Corporation Page");
	   }
	   
   }
   
   
   public void verifySuperUserDeleteIconAcesss(boolean value, String key, String ownCorp)
   {
	   /*
        * Created By : Kuchinad Shashank
        * Created On : 18 June 2020
        */
	   
	   try {
	    	 Utils.clickElement(driver, txt_ShowAllCorporation, "All Corporation Link");
	    	 //txt_ShowAllCorporation.click();
	    	 
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CorporationList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
	   
	   //searchBox_CorporationName.sendKeys(corporation);
		 Utils.enterText(driver, searchBox_CorporationName, globalVariables.superUserCorporationName, "Corporation Name");
		 
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Filter Icon");
	   
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CorporationList);
			 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
	   
		 String otherCorpName = "//a[contains(text(),'" + globalVariables.superUserCorporationName + "')]/../preceding-sibling::td//a[@title='Delete']";
		 
		 if(value)
		   {
			   if(key.equalsIgnoreCase("ALL"))
			   {
				   Utils.verifyIfDataPresent(driver, otherCorpName, "xpath", "Delete Icon for other corp", "Corporation List Page");
			   }
			   else if(key.equalsIgnoreCase("Mine"))
			   {
				   Utils.verifyIfDataNotPresent(driver, otherCorpName, waitElement_corpListHeader, "xpath", "Delete Icon for other", "Corporation List Page");
				   
			   }
		   }
		   else
		   {
			   Utils.verifyIfDataNotPresent(driver, otherCorpName, waitElement_corpListHeader, "xpath", "Delete Icon for own corp", "Corporation List Page");
			   
		   }
   }
   
   
   public void verifyDeleteIconAccess(boolean value, String key, String ownCorp)
   {
	   /*
        * Created By : Kuchinad Shashank
        * Created On : 18 June 2020
        */
	   try {
	    	 Utils.clickElement(driver, txt_ShowAllCorporation, "All Corporation Link");
	    	 //txt_ShowAllCorporation.click();
	    	 
			 Utils.waitUntilLoaderDisappear(driver);
			 Log.message("" + Utils.getPageLoadTime(driver));
			 Utils.waitForElement(driver, tbl_CorporationList);
		 }
	     catch (Exception e) {
			 Log.fail("Issue while clicking on show all corporation. ERROR - " + e.getMessage(), driver, true);
		 }
	   
	   //searchBox_CorporationName.sendKeys(corporation);
		 Utils.enterText(driver, searchBox_CorporationName, ownCorp, "Corporation Name");
		 
		 Utils.clickElement(driver, icon_CorporationNameFilter, "Filter Icon");
	   
		 try {
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt_EqualTo);
			 Utils.waitUntilLoaderDisappear(driver);
			 Utils.waitForElement(driver, tbl_CorporationList);
			 Log.message("Clicked on EQUALTO filter to search corporation and waited for the loader to become invisible");

		 } catch (Exception e) {
			 Log.message("", driver, true);
			 Log.fail("Unable to click on EQUALTO filter or Error while waiting for loader to disappear or Unable to search for clients table. Error Message - " + e.getMessage());
		 }
		 
	   String ownCorpName = "//a[contains(text(),'" + ownCorp + "')]/../preceding-sibling::td//a[@title='Delete']";
	   
	   if(value)
	   {
		   if(key.equalsIgnoreCase("ALL"))
		   {
			   Utils.verifyIfDataPresent(driver, ownCorpName, "xpath", "Delete Icon for Own Corp", "Corporation List Page");
		   }
		   else if(key.equalsIgnoreCase("Mine"))
		   {
			   Utils.verifyIfDataPresent(driver, ownCorpName, "xpath", "Delete Icon for own corp", "Corporation List Page");
			   
		   }
	   }
	   else
	   {
		   Utils.verifyIfDataNotPresent(driver, ownCorpName, waitElement_corpListHeader, "xpath", "Delete Icon for own corp", "Corporation List Page");
		   
	   }
	    
	   
   }
}
