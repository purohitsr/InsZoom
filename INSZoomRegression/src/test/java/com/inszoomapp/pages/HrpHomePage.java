package com.inszoomapp.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class HrpHomePage extends LoadableComponent<HrpHomePage> {
	
	
	@FindBy(xpath = "//input[@id='rdoRegular']")
	WebElement lbl_preocessingType;
	
	@FindBy(xpath = "//div[contains(text(),'No Records Found')]")
	WebElement waitElement_noRecordsFound;							//Added by Yatharth Pandya on 25th Sept, 2020
		
	@FindBy(xpath = "//b[contains(text(),'Name')]//ancestor::table[1]//a")
	WebElement waitElement_crQuestionnaireList;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(xpath = "//tr[@class='tblRow1']//input[@id='btn_ClickheretoCreateCaseRequest']")
	WebElement btn_createCaseRequest;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "btn_Save")
	WebElement btn_saveCRClient;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "btn_Find")
	WebElement btn_findCRClient;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "txtEmpFName")
	WebElement textbox_crClientFirstName;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(xpath = "//a[@title='Choose Client']")
	WebElement lnk_chooseCRClient;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "cboCountry")
	WebElement dropdown_country;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "cboTempl")
	WebElement dropdown_crTemplate;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(xpath = "//div[@id='UploadedDocs']/table")
	WebElement waitElement_uploadedDocuments;                      //Added by Nitisha Sinha on 6 Oct, 2020
	
	@FindBy(id = "pending")
	WebElement tab_assignedToMe;                      //Added by Nitisha Sinha on 22 June, 2020
	
	@FindBy(id = "Search_PENDING")
	WebElement searchbox_assignedToMe;                      //Added by Nitisha Sinha on 22 June, 2020
	
	@FindBy(xpath = "//i[@title='Close']")
	WebElement icon_closeToDo;                      //Added by Nitisha Sinha on 22 June, 2020

	//Added By Saurav on 4thMarch
	
		@FindBy(xpath="//h2[contains(text(),'CLIENT')]")
		WebElement header_Client;
		
		@FindBy(css = "#BeneficiaryDetails > div:nth-child(3) >table > tbody > tr:nth-child(1) > td:nth-child(2) > a")
		WebElement lnk_FirstClientName;
		
		@FindBy(css = "#tabs-bnf > ul > li:nth-child(1) > a")
		WebElement tab_ClientDetails;

		@FindBy(xpath = "//span[contains(text(),'Case Request')]")
		WebElement tab_CaseRequest;
		
		@FindBy(id = "btn_CaseRequestforExistingClient")
		WebElement btn_CaseRequestForExistingClient;
		
		@FindBy(id = "btn_CaseRequestforNewClient")
		WebElement btn_CaseRequestforNewClient;
		
		@FindBy(xpath = "//h2[contains(text(),'Reports')]")
		WebElement header_SharedReports;
		//
	
	
		
	@FindBy(css = "#header-nav > ul > li > a[data-original-title = 'Starndard Guideline']")			//Added by Yatharth Pandya on 3/3/2020
	WebElement icon_userInstruction;
	
	@FindBy(xpath="//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;
	
	@FindBy(id="//div[@id='CorporationDetails']")			//Added by Kuchinad Shashank on 2/3/2020
	WebElement tbl_corporationBranches;

	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id = "resetuserModal")// Added By Saksham Kapoor on 13/08/2019
	WebElement pop_UserInstructions;
	
	@FindBy(xpath = "//button[contains(text(), 'OK')]")// Added By Saksham Kapoor on 13/08/2019
	WebElement btn_OKForInstructions;
	
	@FindBy(xpath="//input[@id='txtSearch']")
	WebElement searchBox_onDashBoard;
	
	@FindBy(xpath="//a[contains(text(),'Firm Contacts')]")
	WebElement tab_firmContacts;
	
	@FindBy(xpath="//a[contains(text(),'Following')]")
	WebElement tab_following;

	@FindBy(css="a[data-original-title='View News and Guidelines']")
	WebElement tab_NewsGuidelines; 
	
	@FindBy(xpath="//a[contains(text(), 'Activity')]")
	WebElement tab_activity;
	
	@FindBy(xpath="//a[contains(text(), 'Profile')]")
	WebElement btn_Profile;
	
	@FindBy(xpath="//button[contains(text(), 'Accept and Continue')]")
	WebElement btn_acceptAndContinue;
	
	@FindBy(xpath="//button[contains(text(), 'Decline and Exit')]")
	WebElement btn_declineAndExit;
	
	@FindBy(id="Search_PENDING")
	WebElement searchbox_ToDo;
	
	@FindBy(css = "a[data-original-title='View Client']")
	WebElement tab_client;
	
	@FindBy(css="i[class='fa fa-building']")
	WebElement tab_myCorporation;
	
	@FindBy(css = "a[data-original-title='View Reports']") 
	WebElement tab_reports;
	
	@FindBy(css = "#header-nav > ul > li.dropdown.profile-dropdown > a")
	WebElement tab_Profile;
	
	@FindBy(xpath = "//a[contains(text(),'Signout')]")
	WebElement tab_SignOut;
	
	public HrpHomePage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail("HrP home page didnt loaded");
		}
	}
	
	public void clickReportsTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */
		Utils.clickElement(driver, tab_reports, "reports tab in HRP");
	}
	
	
	public void clickLogout(boolean screenshot) {
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */
		Utils.clickElement(driver, tab_Profile, "profile tab");
		Utils.clickElement(driver, tab_SignOut, "Sign out");

	}
	
	public void clickClientTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, tab_client, "client tab in HRP");
	}
	
	
	public void checkIfQuestionnaireAdded(String questionnaire)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Nov 2019
		  * 
		  */
		
		Utils.enterText(driver, searchbox_ToDo, questionnaire, "To do search box");
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), '" + questionnaire + "')]", "xpath", questionnaire, "HRP To Do list");
	}
	
	
	public void clickMyCorporationTab() throws InterruptedException
	{
		 /*
		  * Edited By : Souvik Ganguly
		  * Edited On : 17/9/2021
		  * Added try-catch
		  */
		Utils.clickElement(driver, tab_myCorporation, "My Corporation tab in HRP");
		
//		Thread.sleep(3000);
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'pace-active')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'pace-inactive')]")));
		}
		catch (Exception e) {
			Log.message("Unable to find any Pace loader  ERROR :\n\n " + e.getMessage(), driver, true);
		}
	}
	
	
	 public void verifyIfEConsentAppears(String eConsentTitle, String eConsentDescription)
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 14 Dec 2019
		  */
		 
		 Utils.verifyIfDataPresent(driver, "//h1[contains(text(), '" + eConsentTitle + "')]", "xpath", eConsentTitle, "E-Consent title");
		 
		 Utils.verifyIfDataPresent(driver, "//div[contains(text()[2], '" + eConsentDescription + "')]", "xpath", eConsentDescription, "E-Consent Description");
	 }
	 
	 
	 public void declineEConsentAndCheck()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 14 Dec 2019
		  */
		 
		 Utils.clickElement(driver, btn_declineAndExit, "'Decline and Exit'");

		 Utils.verifyIfDataPresent(driver, "login", "id", "Login Button", "Login Page");
	 }
	 
	 
	 public void acceptEConsentAndCheck()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 14 Dec 2019
		  */
		 
		 Utils.clickElement(driver, btn_acceptAndContinue, "'Accept and Continue'");
		 
		 Utils.verifyIfStaticElementDisplayed(driver, tab_client, "Client tab", "HRP Home Page");
	 }
	
	 
	 public void clickProfile()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 16 Dec 2019
		  */
		 
		 Utils.clickElement(driver, tab_Profile, "profile tab");
		 
		 Utils.clickElement(driver, btn_Profile, "'Profile'");
	 }
	 
	 
	 public void clickActivityTab()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 16 Dec 2019
		  */
		 
		 Utils.clickElement(driver, tab_activity, "'Activity' tab");
		 
		 
		 Utils.waitForElementPresent(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		 Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		 
		 Utils.waitForAjax(driver);
	 }
	
	 
	public void verifyIfEConsentLogged(String eConsentTitle)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Dec 2019
		 */ 
		
		boolean truth = false;
		int i = 1;
		
		do
		{
			Utils.waitForElement(driver, "//span[contains(@class,'k-state-selected') and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");
			if(Utils.isElementPresent(driver, "//a[contains(text(), '" + eConsentTitle + "')]/../../following-sibling::td//i[@class='fa fa-thumbs-up fa-stack-1x fa-inverse']", "xpath"))
			{
				Log.message("", driver, true);
				Log.pass("e-consent is present in the activity log");
				truth = true;
				break;
			}
			
			else
			{
				Utils.clickDynamicElement(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-e']", "xpath", "Next page");
				i += 1;
			}
		}while(Utils.isElementPresent(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-e']", "xpath"));
	
		if(!truth)
		{
			Log.fail("e-Consent is not present in Activity log", driver, true);
		}
	}
 
	 
	public void verifySigningPersonNameOnNavigationBar(String signingPerson)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2019
		 */ 
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown profile-dropdown']//span[contains(text(),'" + signingPerson + "')]", "xpath", signingPerson, "on navigation bar");
	}
	
	
	 public void clickNewsGuidelinesTab()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Feb 2019
		 */ 
		 
		 Utils.clickElement(driver, tab_NewsGuidelines, "'News/Guidelines' tab"); 
		 Utils.waitForAjax(driver);
	 } 
	 
	 
	 public void verifyFollowingSection(String clientName)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		 Utils.clickElement(driver, tab_following, "following tab");
		 Utils.verifyIfDataPresent(driver, "//div[@id='tab-friends']//a[contains(text(),'"+clientName+"')]", "xpath", clientName, "following person");
		 Utils.verifyIfDataPresent(driver, "//span[contains(text()[2],'Following')]", "xpath", "following tag", "following section");
	 }
   
	 
	 public void verifyFirmContacts(String firmContact)
	 {
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 11 Aug 2021
		 * Added sync
		 */
		Utils.waitForAjax(driver);
		Utils.clickElement(driver, tab_firmContacts, "firm contacts tab");
		Utils.waitForElement(driver, "//li[@class='dropdown hidden-xs col-lg-3 list-unstyled open']", 90, "xpath");
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown hidden-xs col-lg-3 list-unstyled open']//a/h2[contains(text(),'"+firmContact+"')]", "xpath", firmContact, "Firm Contacts tab");
	 }


	 public void verifyCaseRequestTab(boolean value)
	 {
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Case Request')]", "xpath", "LCA Tab","Corporation Page in HRP Login");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), 'Case Request')]", tab_myCorporation , "xpath","LCA Tab","Corporation Page in HRP Login");
		}
	} 
	
		
		
    public List<List<String>> getReportList()
	{
		/*
		 * Added By: Likitha Krishna 
		 * Added On: 03/03/2020
		 */
    	
          int i = 1;
          List<List<String>> data = new ArrayList<List<String>>();
          Utils.waitForElement(driver, "//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']", globalVariables.elementWaitTime, "xpath");
          int noOfPages = (driver.findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']/li"))).size();
          System.out.println(noOfPages);
          for(int n = 1; n < noOfPages ; n++)
          {
                Utils.waitForElement(driver, "//span[@class='k-state-selected' and contains(text(), '" + i + "')]", globalVariables.elementWaitTime, "xpath");

        		Utils.waitForElement(driver, "(//div[@id='Reports']//div[@style='margin-left:2%;']/a)", globalVariables.elementWaitTime, "xpath");
    			List<WebElement> reportList = driver.findElements(By.xpath("(//div[@id='Reports']//div[@style='margin-left:2%;']/a)"));
    			
    			for (int j = 1; j <= reportList.size(); j++) 
    			{
    				List<String> temp = new ArrayList<String>();
    				Utils.waitForElement(driver, "(//div[@id='Reports']//div[@style='margin-left:2%;']/a)["+j+"]", globalVariables.elementWaitTime, "xpath");
    				temp.add(driver.findElement(By.xpath("(//div[@id='Reports']//div[@style='margin-left:2%;']/a)["+j+"]")).getText());
            		
    				Utils.waitForElement(driver, "//div[@id='Reports']//div/parent::td[1]/following-sibling::td)["+j+"]", globalVariables.elementWaitTime, "xpath");
    				temp.add(driver.findElement(By.xpath("(//div[@id='Reports']//div/parent::td[1]/following-sibling::td)["+j+"]")).getText());
    				data.add(temp);
    				//temp.clear();
    			}
  
                if(n != noOfPages-1)
                Utils.clickDynamicElement(driver, "//a[@class='k-link k-pager-nav']/span[@class='k-icon k-i-arrow-e']", "xpath", "Next page");
                i=i+1;
          }
   
          return data;
    }


	   
	    public void verifyReports(List<List<String>> caseMangerReports, List<List<String>> hrpReports)
		{
			/*
			 * Added By: Likitha Krishna 
			 * Added On: 03/03/2020
			 */
	    	if (hrpReports.containsAll(caseMangerReports)) {
	    		Log.pass("All reports are visible on HRP");

			} else {
				Log.fail("All reports are not visible on HRP");
			}
		}
	    
	  
	    public void verifyNoReportsPresent()
		{
			/*
			 * Added By: Likitha Krishna 
			 * Added On: 04/03/2020
			 */
	    	Utils.verifyIfDataPresent(driver, "//div[contains(text(),'Report access not granted. For assistance, reach out to Firm Administrator.')]", "xpath", "NO RECORDS", "records page in HRP");
		} 
	    
	    
	    public void  verifyDashboardSearchBox(String key, String clientName)
		{
			/*
			 * Added By: Likitha Krishna 
			 * Added On: 04/03/2020
			 */
	    	Utils.enterText(driver, searchBox_onDashBoard, key, "search box");
	    	Utils.verifyIfDataPresent(driver, "//span[@class='content-headline'][contains(text(),'"+clientName+"')]", "xpath", key, "in the search result");
		}
	    
	    
	    public void clickHeadquarterTab(boolean screenshot)
		{
			/*
			 * Added By: Kuchinad Shashank
			 * Added On: 2/03/2020
			 */
			
			Utils.waitForElement(driver, "//span[contains(text(),'" + globalVariables.HeadQuarterName + "')]", globalVariables.elementWaitTime, "xpath");
			Utils.clickDynamicElement(driver, "//span[contains(text(),'" + globalVariables.HeadQuarterName + "')]", "xpath", "Headquater Tab");
			
			Utils.waitForElementPresent(driver, "//div[@class='k-loading-mask']", "xpath");
			Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
			
		}
		
		
		public void acceptUserInstructions(boolean screenshot)
	    {
	           /*
	            * Created By : Saksham Kapoor
	            * Created On : 13/08/2019
	            * This function checks if User Instructions Pop-Up is available and clicks on OK
	            * 
	            */
	    	try
	    	{
	    		Utils.waitForElement(driver, pop_UserInstructions);
	    		String style = pop_UserInstructions.getAttribute("style");

			    if(style.contains("display: block;"))
			    {
			    	try {
						Utils.waitForElement(driver, btn_OKForInstructions);
						btn_OKForInstructions.click();
						Log.message("Clicked on OK for User Instructions");
						Utils.waitForElementNotVisible(driver, "div[id='resetuserModal']>div>div>div>div>button", "css");

					} catch (Exception e) {
						Log.message("", driver, screenshot);
						Log.fail("Unable to click on OK for User Instructions. ERROR :\n\n " + e.getMessage());
					}


			    }
			    else
			    	Log.message("User Instruction pop did not populate");
		                  
	        }catch(Exception e){
	        	Log.message("",driver,screenshot);
	            Log.fail("Unable to Check if User Intructions Poped up. ERROR : \n\n " + e.getMessage());
	        }   
	    }
		
		
		public void clickUserInstructionIconAndAccept()
	    {
	    	/*
	    	 * Created by Yatharth Pandya on 3/3/2020
	    	 */
	    	Utils.clickElement(driver, icon_userInstruction, "User Instructions");
	    	Utils.waitForElement(driver, pop_UserInstructions);
	    	Utils.clickElement(driver, btn_OKForInstructions, "Ok for User Instructions");
	    }
		
		
		 public void VerifyClientPage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
		    try{
					 
			     Utils.verifyIfStaticElementDisplayed(driver, header_Client, "Client header", "In Page header section");
					
		       }catch(Exception e){
				 Log.failsoft("Verification of Client header In Page header section failed", driver);
			    }

			} 

		 
		 
		 public void clickFirstClientName()
		 {
			/*
			 * Created By : Saurav Purohit
			 * Created On : 4th march 2020
			 */ 
			 
			 Utils.clickElement(driver, lnk_FirstClientName, "first client name in List"); 
		 } 

		 
		 public void VerifyClientdetailsPage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
		    try{
					 
			     Utils.verifyIfStaticElementDisplayed(driver, tab_ClientDetails, "Client Details", "In Profile Page");
					
		       }catch(Exception e){
				 Log.failsoft("Verification of ClientDetails Tab In Client Detail section failed", driver);
			    }

			} 
		 
		 public void clickCaseRequestTab() throws Exception
		 {
			/*
			 * Created By : Saurav Purohit
			 * Modified On : 30 June 2021
			 */ 
			 
			 Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab from Left Menu"); 
			 
			 Utils.waitForAllWindowsToLoad(2, driver);
             
             Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
		 }  
		 
		 
		 public void VerifyCreateNewCaseRequestPage() throws Exception 
			{
			
		        /*
		         * Created By :Sauravp
		         * Created On : 5th March 2020
		         */ 
				try{
				

				Utils.waitForAllWindowsToLoad(2, driver);

				Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
				
				Utils.softVerifyPageTitle(driver, "INSZoom.com - Create New Case Request");

				driver.close();

				Utils.waitForAllWindowsToLoad(1, driver);

				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");

			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom - HR Portal Page Failed ", driver);
			    }

			}
		 
		 public void clickCaseRequestForExistingClient() throws Exception
		 {
			/*
			 * Created By : Saurav Purohit
			 * Created On : 4th march 2020
			 */ 
			 
			 Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab from Left Menu"); 
			 
			 Utils.waitForAllWindowsToLoad(2, driver);

			 Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
			 
			 Utils.clickElement(driver, btn_CaseRequestForExistingClient, "Case Request For Existing Client Button");
			 
			 
		 }  
		 
		 public void VerifyCaseRequestForExistingClientPage() throws Exception 
			{
			
		        /*
		         * Created By :Sauravp
		         * Created On : 5th March 2020
		         */ 
				try{
				

				Utils.waitForAllWindowsToLoad(3, driver);

				Utils.switchWindows(driver, "INSZoom.com - Case Request for Existing Client", "title", "false");
				
				Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Request for Existing Client");

				driver.close();

				Utils.waitForAllWindowsToLoad(2, driver);

				Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
				
				driver.close();
				
				Utils.waitForAllWindowsToLoad(1, driver);

				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");

			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Case Request for Existing Client Page Failed ", driver);
			    }

			}
		 
		 
		 public void clickCaseRequestForNewClient() throws Exception
		 {
			/*
			 * Created By : Saurav Purohit
			 * Created On : 4th march 2020
			 */ 
			 
			 Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab from Left Menu"); 
			 
			 Utils.waitForAllWindowsToLoad(2, driver);

			 Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
			 
			 Utils.clickElement(driver, btn_CaseRequestforNewClient, "Case Request For Existing Client Button");
			 
			 
		 }  
		 
		 public void VerifyCaseRequestForNewClientPage() throws Exception 
			{
			
		        /*
		         * Created By :Sauravp
		         * Created On : 5th March 2020
		         */ 
				try{
				

				Utils.waitForAllWindowsToLoad(3, driver);

				Utils.switchWindows(driver, "INSZoom.com - Case Request for New Client", "title", "false");
				
				Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Request for New Client");

				driver.close();

				Utils.waitForAllWindowsToLoad(2, driver);

				Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
				
				driver.close();
				
				Utils.waitForAllWindowsToLoad(1, driver);

				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");

			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Case Request for New Client Page Failed ", driver);
			    }

			}
		 
		 
		 public void verifySharedReportsPage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
		    try{
					 
			     Utils.verifyIfStaticElementDisplayed(driver, header_SharedReports, "In header section", "In Shared Reports Page");
					
		       }catch(Exception e){
				 Log.failsoft("Verification of Shared Reports Header failed", driver);
			    }

			}
		 
		 
		 public void  verifyDashboardSearchBoxForClientDetails(String key, String clientName, String employeeId, String corpName)
		{
			/*
			 * Added By: Likitha Krishna 
			 * Added On: 04/03/2020
			 */
	    	Utils.enterText(driver, searchBox_onDashBoard, key, "search box");
	    	Utils.verifyIfDataPresent(driver, "//span[@class='content-headline'][contains(text(),'"+clientName+"')]", "xpath", clientName, "as client name the search result");
	    	Utils.verifyIfDataPresent(driver, "//span[@class='content-text'][contains(text(),'Employee ID - "+employeeId+"')]", "xpath", key, "as employee ID in the search result");
	    	Utils.verifyIfDataPresent(driver, "//span[@class='content-headline'][contains(text(),'"+corpName+"')]", "xpath", key, "as corp name in the search result");
	    	
	    	
		}
		 
		 public void clickAssignedToMe()
			{
				/*
				  * Created By : Nitisha Sinha
				  * Created On : 22 June 2020
				  */
				
				Utils.clickElement(driver, tab_assignedToMe, "Assigned to Me tab in MyToDoList");
			}
			 
			 public void searchToDoAssignedToMe(String toDoTitle) throws InterruptedException
			{
				/*
				  * Created By : Nitisha Sinha
				  * Created On : 22 June 2020
				  * Modified By: Nitisha Sinha
				  * MOdified On: 20th July,2020
				  * Modification Done: Added logic for handling the loader
				  */
				
				Utils.enterText(driver, searchbox_assignedToMe, toDoTitle, "Assigned to me searchbox in My");
			
				Utils.waitForElementPresent(driver, "//div[@id='PENDING_ToDoList']//div[@class='k-loading-image']", "xpath");
				Utils.waitForElementNotVisible(driver, "//div[@id='PENDING_ToDoList']//div[@class='k-loading-image']", "xpath");
				Thread.sleep(10000);
				Utils.clickDynamicElement(driver, "//label[contains(text(),'" + toDoTitle + "')]", "xpath", toDoTitle);
				
				Utils.waitForElementPresent(driver, "//span[contains(text(),'" + toDoTitle + "')]", "xpath");

			}
			 
			 
			 public void verifyAssignQuestionnaiareToCorp(String questionnaireName, String corpName) throws InterruptedException
			{
				/*
				  * Created By : Nitisha Sinha
				  * Created On : 6 Oct 2020
				  */
						
				 	Utils.waitForElementPresent(driver, "//div[@id='UploadedDocs']//div[@class='k-loading-mask']", "xpath");
					Utils.waitForElementNotVisible(driver, "//div[@id='UploadedDocs']//div[@class='k-loading-mask']", "xpath");
					
					Utils.waitForElement(driver, waitElement_uploadedDocuments);
					
					Utils.clickDynamicElement(driver, "//span[contains(text(),'Questionnaires for " + corpName + "')]", "xpath", "expand questionnaire icon");
					
					Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Questionnaires for " + corpName + "')]//ancestor::table//a[contains(text(),'" + questionnaireName + "')]", "xpath", questionnaireName, "questionnaire for " + corpName);
					
					Utils.waitForElementClickable(driver, icon_closeToDo);
					Utils.clickUsingAction(driver, icon_closeToDo);
			}
			 
			 
			 public void verifyCRQuestionnaire(String clientFirstName) throws Exception
			 {
				/*
				 * Created By : Nitisha Sinha
				 * Created On : 1st Oct 2020
				 * Modified By: Saurav
				 * Modified Date : 29/06/2021
				 * Added a Java script click followed by a wait - reason Though USA is selected it was not populating CR list 
				 * Hence Java script click will reload the list and select the right CR .
				 */ 
				 try{
			/*	Utils.clickElement(driver, tab_CaseRequest, "Case Request Tab from Left Menu"); 
				
				Utils.waitForAllWindowsToLoad(2, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");*/
				
				Utils.clickElement(driver, btn_CaseRequestForExistingClient, "Case Request For Existing Client Button");
				
				Utils.waitForAllWindowsToLoad(3, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Case Request for Existing Client", "title", "false");
				
				Utils.selectOptionFromDropDown(driver, "United States of America", dropdown_country);
				
				Utils.waitForElement(driver, dropdown_country);
				
				Select select = new Select(dropdown_country);
				
				Utils.waitForOptionsAvaiable(driver, select);
				
				Utils.waitForElement(driver, lbl_preocessingType);
				
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", lbl_preocessingType);
				
				Select dropdownCaseRequestTemplate = new Select(dropdown_crTemplate);
				
				Utils.waitForOptionsAvaiable(driver, dropdownCaseRequestTemplate);
				
				Utils.selectOptionFromDropDown(driver, "Automation Case Request Template", dropdown_crTemplate);
				
				Utils.clickElement(driver, lnk_chooseCRClient, "link for choosing the client");
				
				Utils.waitForAllWindowsToLoad(4, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Choose Employee", "title", "false");
				
				Utils.enterText(driver, textbox_crClientFirstName, clientFirstName, "Client Name search box");
				
				Utils.clickElement(driver, btn_findCRClient, "Find Client button");
				
				Utils.clickDynamicElement(driver, "//td[contains(text(),'" + clientFirstName + "')]/parent::tr/td/input", "xpath", "checkbox for " + clientFirstName);
				
				Utils.clickElement(driver, btn_saveCRClient, "save client button");
				
				Utils.waitForAllWindowsToLoad(3, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Case Request for Existing Client", "title", "false");
				
				Utils.clickElement(driver, btn_saveCRClient, "save button for initiating case request");
				
				Utils.waitForAllWindowsToLoad(2, driver);
				
				Utils.switchWindows(driver, "INSZoom.com - Create New Case Request", "title", "false");
				
				Utils.clickElement(driver, btn_createCaseRequest, "Create btn for Case Request");
			
				Utils.waitForElementClickable(driver, waitElement_crQuestionnaireList);
				
				WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//b[contains(text(),'Name')]//ancestor::table[1]//a"), 1));
				
				List<WebElement> temp = driver.findElements(By.xpath("//b[contains(text(),'Name')]//ancestor::table[1]//a"));
				 
				HashSet<String> questionnaire=new HashSet();
				for(int j = 0; j < temp.size(); j += 1)
			   {
					questionnaire.add(temp.get(j).getText());
			 	  
			   }
				
				Log.message("Questionnaire List Under CR Template are "+globalVariables.questionnaireUnderCRTemplate);
		        
		        Log.message("Questionnaire List In FN Portal "+questionnaire);
				
				if(globalVariables.questionnaireUnderCRTemplate.equals(questionnaire))
					Log.pass("CR Questionnaire are successsfully populated in HRP level");
				else
					Log.fail("CR Questionnaire did not get successsfully populated in HRP level");
				
				driver.close();
				
				Utils.waitForAllWindowsToLoad(1, driver);
				
				Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
				 }catch (Exception e) {
						Log.message("", driver, true);
						Log.fail("Unable Verify Case Request Questionnaire "  + ". ERROR :\n\n " + e.getMessage());
					}
				}
			 
			 
			 public void checkIfQuestionnaireNotPresent(String questionnaire)
				{
					 /*
					  * Created By : Yatharth Pandya
					  * Created On : 25th Sept, 2020
					  * 
					  */
						
					Utils.enterText(driver, searchbox_ToDo, questionnaire, "To do search box");
									
					Utils.verifyIfDataNotPresent(driver, "//div[contains(text(), '" + questionnaire + "')]", waitElement_noRecordsFound, "xpath", questionnaire, "HRP To DO list");
				}
	    
}