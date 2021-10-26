package com.inszoomapp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
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

public class CM_CaseFormPage extends LoadableComponent<CM_CaseFormPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(xpath = "//td[contains(text(),'Sponsor details have changed')]")
    WebElement lnk_SponserChange; //Added by Saurav  on 25th Aug 2021

	@FindBy(id = "btn_UpdateTheInformation.")
    WebElement btn_Update;                //Added by Saurav  on 25th Aug 2021
	
	@FindBy(xpath="//img[@alt='delete']")
	WebElement icon_Delete; // Added by Souvik on 11th Aug 2021
	
	@FindBy(id="cboAddForm_1")
    WebElement dropdown_FormsOptions; // Added by Saurav on 5th Aug 2021
	
	@FindBy(xpath="(//table[contains(@summary,'Case Forms for Client')]/tbody/tr)[last()]/td")
	WebElement lastrow_caseFormsTable; // Added by Saurav on 5th Aug 2021

    @FindBy(id="form48_1")
    WebElement txtbox_PinCodeMailingAddress; // Added by Saurav on 5th Aug 2021
	
	@FindBy(id="form52_1")
    WebElement txtbox_PinCodePhysicalAddresss; // Added by Saurav on 5th Aug 2021
	
	@FindBy(xpath="//td[@class='PAGINGBARLBL']")
    WebElement section_Pagination; // Added by Saurav on 29 July 2021
	
	@FindBy(xpath="//div[@class='loader' and @style='display: none;']")
	WebElement loader;
	
	@FindBy(id="form55_1")
	WebElement txtbox_DOB;
	
	@FindBy(id="f222_1")
	WebElement dropdown_State;
	
	@FindBy(xpath="//footer[@class='modal-footer']//button[contains(text(), 'Delete')]")
	WebElement btn_deleteForm4_0;
	
	@FindBy(xpath = "//span[contains(text(), 'Delete')]")
	WebElement btn_deleteSelectedForms4_0;
	
	@FindBy(xpath = "//p//img[contains(@src, 'ggg==')]")
	WebElement btn_saveForm4_0;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement	btn_save4_0;
	
	@FindBy(css = "input[placeholder='Search']")
	WebElement searchbox_searchForm4_0;
	
	@FindBy(css="div[class='toast-alert-message alert-success success_alert_msg show']")
	WebElement txt_FileUploadMessageAppeared4_0;

	@FindBy(xpath = "//button[contains(text(), 'Add Form')]")
	WebElement btn_addForm4_0;
	
	@FindBy(id="addendum-signature")
	WebElement checkbox_includeSignature;
	
	@FindBy(css="div[class='text-center show-addendum-section'] > span")
	WebElement btn_addendum;
	
	@FindBy(xpath = "//td[contains(text(),'Case Details')]")
	WebElement header_caseDetails;
	
	@FindBy(xpath = "//td[@id='LM164']/span[contains(text(),'Profile')]")
	WebElement tab_CaseProfile;
	
	@FindBy(id = "btnSaveNextBtm")
	WebElement btn_saveAndNext;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_ddlAPP_POB_CNTRY")
	WebElement textBox_birthCountry;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxAPP_POB_ST_PROVINCE")
	WebElement textBox_birthState;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxAPP_POB_CITY")
	WebElement textBox_birthCity;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxDOBYear")
	WebElement textBox_birthYear;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_ddlDOBMonth")
	WebElement dropDown_birthMonth;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_ddlDOBDay")
	WebElement dropDown_birthDay;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_ddlAPP_MARITAL_STATUS")
	WebElement dropDown_Status;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_rblAPP_GENDER_1")
	WebElement radioButton_sex;
	
	@FindBy(xpath = "//input[@id='ctl06_ctl00_SiteContentPlaceHolder_FormView1_rblTelecodeQuestion_1']")
	WebElement radioButton_teleCode;
	
	@FindBy(xpath = "//input[@id='ctl06_ctl00_SiteContentPlaceHolder_FormView1_rblOtherNames_1']")
	WebElement radioButton_otherNames;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxAPP_FULL_NAME_NATIVE")
	WebElement textBox_fullName;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxAPP_GIVEN_NAME")
	WebElement textBox_givenName;
	
	@FindBy(id = "ctl06_ctl00_SiteContentPlaceHolder_FormView1_tbxAPP_SURNAME")
	WebElement textBox_surName;
	
	
	@FindBy(xpath = "//table[@summary='Search Results']")
	WebElement tbl_searchResults;
	
	@FindBy(id = "btn_SearchCaseForms")
	WebElement btn_SearchCaseForms;
	
	@FindBy(id = "txtSearchId")
	WebElement searchBox_formNumber;
	
	@FindBy(css = "input[name='F1']")
	WebElement textBox_lastName;

	@FindBy(css = "input[name='F2']")
	WebElement textBox_firstName;
	
	@FindBy(id = "txtSubject")
	WebElement textBox_Subject;
	
	@FindBy(id = "btn_EmailCaseAdvancedKit")
	WebElement btn_SendEmail;
	
	@FindBy(id = "btn_ComposeEmailAndAttachForm(s)_1")
	WebElement btn_EmailForms;
	
	@FindBy(id = "btn_DeleteAllTheSelectedForms")
	WebElement btn_Delete;
	
	@FindBy(xpath = "//input[@title='Print']")
	WebElement btn_Print;
	
	@FindBy(xpath = "//input[@value='Clear All']")
	WebElement btn_ClearAll;
	
	@FindBy(id = "btn_ClickhereforBulkPrint_1")
	WebElement btn_BulkPrint;
	
	@FindBy(css = "a[id='Close']")
	WebElement icon_Close;
	
	@FindBy(css = "a[id='Save']")
	WebElement icon_Save;
	
	@FindBy(css = "input[name='F12']")
	WebElement textBox_cityName;
	
	@FindBy(css = "input[name='F9']")
	WebElement textBox_streetName;
	
	@FindBy(css = "input[name='F6']")
	WebElement textBox_countryOfCitizenship;
	
	@FindBy(css = "input[name='F3']")
	WebElement textBox_middleName;
	
	@FindBy(id = "btn_SaveSelectedCaseFormsAndCloseTheWindow")
	WebElement btn_SaveAndClose;
	
	@FindBy(id = "cboFormGroup")
	WebElement dropDown_FormGroup;
	
	@FindBy(xpath = "//a[@class='IconFont' and @title='Add forms to the case_1']")
	WebElement icon_Go;
	
	@FindBy(id = "cboAddForm_1")
	WebElement dropDown_AddFromFrom;
	
	public CM_CaseFormPage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Case  page didnt loaded");
		}

	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	
	public void addFormAndVerify(String formsFrom, String formGroup, String formName) throws Exception
	{
		 /*
		  * Modified By : Saurav
		  * Modified On : 10th Aug 2021
		  * Added Java script click for Go button as Normal click does not work some time
		  */
	    Utils.waitForElementClickable(driver, dropDown_AddFromFrom);
	    
	    if(driver.getWindowHandles().size() > 1)
        {
           Utils.switchWindows(driver, "INSZoom.com::Data Difference Alert Message", "title", "false");
           
           Utils.waitForElement(driver, lnk_SponserChange);
           
           Utils.clickElement(driver, lnk_SponserChange, "Sponser DetailChange Link");
           
           Utils.waitForElement(driver, btn_Update);
           
           Utils.clickElement(driver, btn_Update, "Update Sponser Detail Button");
           
           Utils.waitForAllWindowsToLoad(1, driver);
           
           Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
   
        }

	    
		Utils.selectOptionFromDropDown(driver, formsFrom, dropDown_AddFromFrom);
	
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", icon_Go);
        Log.message("clicked on Go Button");
		
		if(Utils.isAlertPresent(driver)){
			driver.switchTo().alert().accept();
			Log.message("Alert is present . accepted the alert");
			
		}
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Link Immigration Forms", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, formGroup, dropDown_FormGroup);
		
		Utils.waitForElement(driver, section_Pagination);
		
		Utils.clickDynamicElement(driver, "//b[contains(text(),'"+ formName +"')]/../input[@class='CheckBox']", "xpath", "checkbox of the required form");
		
		Utils.clickElement(driver, btn_SaveAndClose, "save button");
		
		
//		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//B[contains(text(),'"+ formName +"')]", "xpath", formName, "forms page");
		
	}
	
	
	
	public void editFastFormAndVerify(String formsName, String middleName, String countyOfCitizenship, String streetName, String cityName) throws Exception
	{
		 /*
		  * Modified By : saurav purohit
		  * Created On : 05/08/2021
		  * adding Physical address pin code and Mailing address pincode which is mendatory to edit a form
		  */
		
		Utils.waitForElement(driver, "//b[contains(text(),'"+ formsName +"')]/ancestor::tr[2]/td//select[@name='optFrmUtil']", globalVariables.elementWaitTime, "xpath");
		WebElement dropDown_FormUtility = driver.findElement(By.xpath("//b[contains(text(),'"+ formsName +"')]/ancestor::tr[2]/td//select[@name='optFrmUtil']"));
		
		Utils.selectOptionFromDropDownByVal(driver, "Edit Fast Form", dropDown_FormUtility);
		
		Utils.clickDynamicElement(driver, "//b[contains(text(),'"+ formsName +"')]/ancestor::tr[2]/td//a[@id='Beneficiary_UtilityGo_1']/img", "xpath", "Go Icon");
		
		
		String winHandleBefore = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		
		String winhandlechild = driver.getWindowHandle();
		
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='loader' and @style='display: none;']")));
       
//		Utils.waitForElement(driver, loader);
		
		Utils.waitForElement(driver, "frmhtmlForms", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("frmhtmlForms");
		
		Utils.enterText(driver, textBox_middleName, middleName, "middle name");
		
		Utils.enterText(driver, textBox_countryOfCitizenship, countyOfCitizenship, "country of cititzenship");
		
		Utils.scrollToElement(driver, textBox_countryOfCitizenship);

		Utils.enterText(driver, textBox_streetName, streetName, "street name");
		
		Utils.enterText(driver, textBox_cityName, cityName, "city name");
		
		Utils.enterText(driver, txtbox_DOB, "01/23/1950", "Date of Birth");
		
		Utils.selectOptionFromDropDownByVal(driver, "AA", dropdown_State);
		
		Utils.enterText(driver, txtbox_PinCodePhysicalAddresss, "45367", "Physical address pincode");
		
		Utils.enterText(driver, txtbox_PinCodeMailingAddress, "45367", "Mailing address pincode");
		

		driver.switchTo().window(winhandlechild);
		
		Log.message("Filled the details in html form" + (middleName) + ", " + (countyOfCitizenship) + ", " + (streetName) + ", " + (cityName) + "", driver, true);
		
		Utils.waitForElement(driver, icon_Save);
		
		Utils.scrollToElement(driver, icon_Save);
		
		icon_Save.click();

		Utils.waitForElement(driver, "frmhtmlForms", globalVariables.elementWaitTime, "id");
		
		driver.switchTo().frame("frmhtmlForms");
		
		String text_countryAfterEdit = textBox_countryOfCitizenship.getAttribute("value");
		
		
		if (text_countryAfterEdit.equalsIgnoreCase(countyOfCitizenship)) {
			Log.pass("The details verified and the country is" + (text_countryAfterEdit) + "", driver, true);
		} else {
			Log.fail("The error has been occured while verifying the details", driver, true);
		}
		driver.switchTo().window(winhandlechild);
		
		Utils.waitForElementNotVisible(driver, "//p[contains(text(),'Form Data Update')]", "xpath");

		Utils.waitForElement(driver, icon_Save);
		
		Utils.scrollToElement(driver, icon_Close);
		
		Utils.clickElement(driver, icon_Close, "Close Icon");

		try{
		if (Utils.isAlertPresent(driver)) {
		    Log.message("Alert is present ");
			driver.switchTo().alert().accept();
		} else {
			System.out.println("alert was not present");
		}
		}
		catch(Exception e){
			
		}
		Log.message("Switching to main window");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
	}
	
	public void deleteFormAndVerfy(String formName) throws Exception {
		 /*
		  * Created By : Sauravp
		  * Created On : 05/08/2021
		  * Modified the logic of deleteForm
		  */
	    Utils.waitForElement(driver, lastrow_caseFormsTable);
	    
	    String sFormsCountBeforeDelete =lastrow_caseFormsTable.getText();
	    
	    int clientFormsCountBeforeDelete = Integer.parseInt(sFormsCountBeforeDelete);
	    
		Utils.clickDynamicElement(driver, "//b[contains(text(),'"+ formName +"')]//ancestor::tr[2]/td[4]//img[@title='Delete Forms']", "xpath", "delete icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Delete Forms", "title", "false");
		
		Utils.clickElement(driver, btn_Delete, "delete button");
		
		try {
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();
			} else {
				System.out.println("alert was not present");
			}
		
		} catch (NoAlertPresentException e) {
			System.out.println("Exception=====!");
			e.printStackTrace();
			System.out.println("End of exception======");
		}
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
		
		Select select = new Select(dropdown_FormsOptions);
		
		Utils.waitForOptionsAvaiable(driver, select);
		
		Utils.waitForElement(driver, "//table[@class='TableStyle']/tbody/tr", globalVariables.elementWaitTime, "xpath");
		
		 Utils.waitForElement(driver, lastrow_caseFormsTable);
	        
	     String sFormsCountAfterDelete =lastrow_caseFormsTable.getText();
	        
	     int clientFormsCountAfterDelete = Integer.parseInt(sFormsCountAfterDelete);
	     
	     if(clientFormsCountAfterDelete == clientFormsCountBeforeDelete-1){
	        
	         Log.pass("Forms Got Deleted Successfully ", driver, true);
	     }else{
	         Log.fail("Issue with Form Deletion ", driver, true);
	     }
		
		/*WebElement waitElement_table = driver.findElement(By.xpath("//table[@class='TableStyle']/tbody/tr"));
		
		Utils.verifyIfDataNotPresent(driver, "//b[contains(text(),'"+ formName +"')]", waitElement_table, "xpath", formName, "forms table");*/
		
	}

	public void sendEmailAndVerify(String caseFormName, String messageBodyForFormsEmail, String subjectForFormsEmail) {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, btn_EmailForms, "emial forms button");
		
		Utils.enterText(driver, textBox_Subject, subjectForFormsEmail, "subject");
		
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		Utils.waitForElement(driver, "//html//body", globalVariables.elementWaitTime, "xpath");
		driver.findElement(By.xpath("//html//body")).sendKeys(messageBodyForFormsEmail);
		
		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		
		Utils.clickDynamicElement(driver, "//td[@class='TDOCLIST'and contains(text(),'"+ caseFormName +"')]/preceding-sibling::td/input[3]", "xpath", "check box of the form");
	
		Utils.clickElement(driver, btn_SendEmail, "send email button");
		
		Utils.verifyIfDataPresent(driver, "//font[contains(text(),'Subject')]/../a[contains(text(),'"+ subjectForFormsEmail +"')]", "xpath", "sent email", "email page");
	}
	
	
	
	public void reloadFormAndVerify(String formName, String firstName, String lastName) throws Exception
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 26 Nov 2019
		  * Modified By : Saurav 
		  * Modified Date : 9th June 2021
		  * Added a Explicite wait for Loader in form reload window
		  * Modified By : Saurav 
		  * Modified Date : 1st July 2021
		  * Added a waitForFrametoBeAvaialable to wait till frame is available and to switch
		  * Modified Date : 1st July 2021
		  */
	    String winHandleBefore="";
		int windowSize = driver.getWindowHandles().size();
		try{
        if(windowSize == 2)
        {
            Utils.switchWindows(driver, "INSZoom.com::Data Difference Alert Message", "title", "false");
            driver.close();
            Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
        }
		
        Utils.waitForElement(driver, "//b[contains(text(),'"+ formName +"')]/ancestor::tr[2]/td//select[@name='optFrmUtil']", globalVariables.elementWaitTime, "xpath");
		WebElement dropDown_FormUtility = driver.findElement(By.xpath("//b[contains(text(),'"+ formName +"')]/ancestor::tr[2]/td//select[@name='optFrmUtil']"));
		
		Utils.selectOptionFromDropDownByVal(driver, "ReLoad", dropDown_FormUtility);
		
		Utils.clickDynamicElement(driver, "//b[contains(text(),'"+ formName +"')]/ancestor::tr[2]/td//a[@id='Beneficiary_UtilityGo_1']/img", "xpath", "Go Icon");
		
		try
		{
			if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			} 
			else 
			{
				System.out.println("alert was not present");
			}
		}
		catch (Exception e){}
		
		try{
		
		winHandleBefore = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		}catch(Exception e){
		    Log.fail("Failed to switch to Forms Details Window . Error " +e.getMessage(), driver, true);
		}
		
		String winhandlechild = driver.getWindowHandle();
		
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='loader' and contains(@style,'display: none;')]")));
		
		Utils.waitForFrametoBeAvaialable(driver, "frmhtmlForms");
        
		String middleName = textBox_middleName.getAttribute("value");
		
		String countyOfCitizenship = textBox_countryOfCitizenship.getAttribute("value");
		
		String streetName = textBox_streetName.getAttribute("value");
		
		String cityName = textBox_cityName.getAttribute("value");

		String lastNameRecreate = textBox_lastName.getAttribute("value");
		
		String firstNameRecreate = textBox_firstName.getAttribute("value");
		
		try {
			if (countyOfCitizenship.contains("") && middleName.contains("") && streetName.contains("")
					&& firstNameRecreate.contains(firstName)
					&& lastNameRecreate.contains(lastName)) {
				Log.pass("verified the edited fields are empty and master fields are shown " + (firstName) + ","
						+ (lastName) + " ", driver, true);
			}
		} catch (Exception e) {
			throw new Exception("Unable to see the empty fields" + e.getMessage());
		}
		driver.switchTo().window(winhandlechild);
		
		Utils.clickElement(driver, icon_Close, "close icon");
		
		driver.switchTo().window(winHandleBefore);
		
		}catch(Exception e){
		    Log.fail("Failed to Reload forms and verify . Error " +e.getMessage(), driver, true);
		}
		
	}
	
	public void addForm(String formName, String clientName) throws Exception
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 26 Nov 2019
		  */
		
		Utils.clickElement(driver, icon_Go, "go icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Link Immigration Forms", "title", "false");
		
		Utils.enterText(driver, searchBox_formNumber, formName, "form name in search box");
		
		Utils.clickElement(driver, btn_SearchCaseForms, "find button");
		
		Utils.waitForElement(driver, tbl_searchResults);
		
		Utils.clickDynamicElement(driver, "//input[@type='checkbox']", "xpath", "checkbox of the form");
		
		Utils.clickElement(driver, btn_SaveAndClose, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//td[@class='TDOCLIST FORMDISPID']/b[text()='"+ formName +"']", "xpath", formName, "forms page");
	}
	
	
	public void clickOnFillForm(String formName, String caseId) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Jan 2020
		 */
		
		Utils.clickDynamicElement(driver, "//b[contains(text(), '" + formName + "')]/ancestor::td[2]/following-sibling::td//a[@title='Fill Form']", "xpath", "Fill for " + formName);
		
		if(Utils.isElementPresent(driver, "fillModalOkButton", "id"))
			Utils.clickDynamicElement(driver, "fillModalOkButton", "id", "OK");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, caseId, "title", "false");
	}
	
	
	
/*	public void filldetails() throws Exception
	{	
		
//	Utils.clickDynamicElement(driver, "btnSaveBtm", "id", "save button");
//	Utils.clickDynamicElement(driver, "btnCloseBtm", "id", "close button");
//	Utils.waitForAllWindowsToLoad(1, driver);
//	Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
	Utils.clickDynamicElement(driver, "//b[contains(text(),'DS-160')]/ancestor::td[2]/following-sibling::td[2]//img[@title='Submit Form to U.S. State Dept.']", "xpath", "e-fill img");
	Utils.clickDynamicElement(driver, "(//div[@class='modal-footer']//button[contains(text(),'Agree')])[1]", "xpath", "agree button");
//	Utils.waitForAllWindowsToLoad(2, driver);
//	Utils.switchWindows(driver, "INSZoom Efile Progress Disclaimer", "title", "false");
	Utils.waitForAllWindowsToLoad(3, driver);
	Utils.switchWindows(driver, "Nonimmigrant Visa - Instructions Page", "title", "false");
//	driver.switchTo().alert().accept();
	
	WebElement dropDown_location = driver.findElement(By.id("ctl00_SiteContentPlaceHolder_ucLocation_ddlLocation"));
	Utils.selectOptionFromDropDownByVal(driver, "BGH", dropDown_location);
		
		
		
		Utils.clickDynamicElement(driver, "//b[contains(text(),'DS-160')]/ancestor::td[2]/following-sibling::td[2]//img[@title='Fill Form']", "xpath", "fill icon");
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - DS160 Form", "title", "false");
		Utils.waitForElement(driver, "//span[contains(text(),'Designate Location and Application Info')]", globalVariables.elementWaitTime, "xpath");
//		WebElement dropDown_location = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_ucLocation_ddlLocation"));
//		Utils.selectOptionFromDropDownByVal(driver, "MDR", dropDown_location);
		
//		WebElement dropDown_securityQuest = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_ddlQuestions"));
//		Utils.selectOptionFromDropDownByVal(driver, "17", dropDown_securityQuest);
//		WebElement answer = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_txtAnswer"));
//		Utils.enterText(driver, answer, "Japan", "security answer");

		Utils.clickElement(driver, btn_saveAndNext, "save and next");
		Utils.enterText(driver, textBox_surName, "Likitha", "surname");
		Utils.enterText(driver, textBox_givenName, "Krishna", "given name");
		Utils.enterText(driver, textBox_fullName, "Likitha Krishna", "full name");
		Utils.clickElement(driver, radioButton_otherNames,"no in other name radio button" );
		Utils.clickElement(driver, radioButton_teleCode,"no in telecode radio button" );
		Utils.clickElement(driver, radioButton_sex, "sex as female");
		Utils.selectOptionFromDropDownByVal(driver, "S", dropDown_Status);
		Utils.selectOptionFromDropDown(driver, "11", dropDown_birthDay);
		Utils.selectOptionFromDropDown(driver, "DEC", dropDown_birthMonth);
		Utils.enterText(driver, textBox_birthYear, "1997", "birth year");
		Utils.enterText(driver, textBox_birthCity, "Chennai", "birth city");
		Utils.enterText(driver, textBox_birthState, "Tamil Nadu", "birth state");
		Utils.enterText(driver, textBox_birthCountry, "India", "birth country");
		Utils.clickElement(driver, btn_saveAndNext, "save and next");
	}*/
	
	public void clickAddForms(boolean screenshot) throws Exception  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 26 Feb 2019
		  */ 
       Utils.clickElement(driver, icon_Go, "go icon");
		
	 }  
	
	
	public void VerifyLinkImiigrationPage() throws Exception 
	{
	
       /*
        * Created By :Sauravp
        * Created On : 3rdMarch 2020
        */ 
		try{
	  Utils.waitForAllWindowsToLoad(2, driver);
	  
	  Utils.switchWindows(driver, "INSZoom.com - Link Immigration Forms", "title", "false");

	  Utils.switchWindows(driver, "INSZoom.com - Link Immigration Forms", "title", "false");

	  driver.close();

	  Utils.waitForAllWindowsToLoad(1, driver);

     Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");

	    }catch(Exception e){
     Log.failsoft("Verification of INSZoom.com - List Of Case Forms Page Failed ", driver);

		 
	    }

	}
	
	
	
	public void clickBulkPrint(boolean screenshot) throws Exception  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 3rdMarch 2019
		  */ 
      Utils.clickElement(driver, btn_BulkPrint, "Bulk print");
		
	 }  
	
	
	
	
	public void VerifyBulkPrintPage() throws Exception 
	{
	
       /*
        * Created By :Sauravp
        * Created On : 3rdMarch 2020
        */ 
		try{
	  Utils.waitForAllWindowsToLoad(2, driver);
	  
	  Utils.switchWindows(driver, "INSZoom.Com : Case Forms Bulk Print", "title", "false");

	  Utils.switchWindows(driver, "INSZoom.Com : Case Forms Bulk Print", "title", "false");

	  driver.close();

	  Utils.waitForAllWindowsToLoad(1, driver);

     Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");

	    }catch(Exception e){
     Log.failsoft("Verification of INSZoom.com - List Of Case Forms Page Failed ", driver);

		 
	    }

	}
	
	
	public void getListOFForms()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 18/03/2020
		 */
		
		Utils.waitForElement(driver, "//h4[@class='title_head']//span[@class=' has-tooltip']", globalVariables.elementWaitTime, "xpath");
		List<WebElement> forms = driver.findElements(By.xpath("//h4[@class='title_head']//span[@class=' has-tooltip']"));
		
		for(int i = 0; i < forms.size(); i+=1)
		{
			globalVariables.formsUnderCase.add(forms.get(i).getText());		
		}
	}
	
	
	public void clickCaseProfileTab()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 18/03/2020
		 */
		
		Utils.clickElement(driver, tab_CaseProfile, "Case Profile Tab");
		
		Utils.waitForElement(driver, header_caseDetails);
	}
	
	
	public void backToCaseProfileTab() throws Exception
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 18/03/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		
	}
	
	
	public void clickFormOn4_0(String formName) throws Exception
	{
		/*
		 * Created by Saksham Kapoor
		 * Created on 07/10/2020
		 * 
		 */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		
		Utils.clickDynamicElement(driver, "//h4//span[contains(text(), '" + formName + "')]", "xpath", formName);
	}
	
	
	public void verifyFontSize(int fontSize)
	{
		/*
		 * Created by Saksham Kapoor
		 * Created on 07/10/2020
		 * 
		 */
		
		Utils.waitForElement(driver, "indexcontainer", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("indexcontainer");
		
		Utils.waitForElement(driver, "frmhtmlForms", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("frmhtmlForms");
		
		Utils.verifyIfDataPresent(driver, "//input[@name='F1' and @style='font-size: " + fontSize +"pt;']", "xpath", String.valueOf(fontSize), "Font Size");
	}
	
	
	public void verifySignatureOnAddendum(String choice)
	{
		/*
		 * Created by Saksham Kapoor
		 * Created on 14/10/2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_addendum, "Addendum");
		
		if(choice.equals("Yes"))
		{
			if(checkbox_includeSignature.isSelected())
			{
				Log.message("", driver, true);
				Log.pass("Include Signature is checked");
			}
			
			else
			{
				Log.message("", driver, true);
				Log.fail("Include Signature is NOT checked");
			}
		}
		
		if(choice.equals("No"))
		{
			if(!checkbox_includeSignature.isSelected())
			{
				Log.message("", driver, true);
				Log.pass("Include Signature is NOT checked");
			}
			
			else
			{
				Log.message("", driver, true);
				Log.fail("Include Signature is checked");
			}
		}
		
		driver.navigate().back();
	}
	
	
	public void addForm4_0(String formName)
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 14 Oct 2020
         */
		
		Utils.clickElement(driver, btn_addForm4_0, "Add Form");
		
		Utils.enterText(driver, searchbox_searchForm4_0, formName, "Form Searchbox");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + formName + "')]/following-sibling::button", "xpath", "Add");
		
		Utils.waitForElement(driver, "span[class='k-widget k-dropdown kendo-dropdownlist']", globalVariables.elementWaitTime, "css");
		
		Utils.clickElement(driver, btn_save4_0, "Save");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared4_0);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + formName + "')]", "xpath", formName, "Forms List");
	}
	
	
	public void deleteForm4_0(String formName)
	{
		/*
         * Edited By : Souvik Ganguly
         * Edited On : 03 August 2021
         * This method is created as a part of cleanup process so that the added G-28 form can be deleted post test completion so as to cause duplication issues in other tests
         */
		try
		{
		Utils.waitForElement(driver, btn_addForm4_0);
		
		if(Utils.isElementPresent(driver, "//label[contains(@for, 'check-all-for-')]", "xpath"))
		{
			Utils.clickDynamicElement(driver, "//span[contains(text(), 'G-28')]/ancestor::td/preceding-sibling::td//label", "xpath", "Checkbox for " + formName);
			Utils.clickElement(driver, btn_deleteSelectedForms4_0, "Delete");
			Utils.clickElement(driver, btn_deleteForm4_0, "Delete");
			Utils.waitForElementNotVisible(driver, "//footer[@class='modal-footer']//button[contains(text(), 'Delete')]", "xpath");
			Utils.waitForElement(driver, icon_Delete);
		    Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), 'G-28')]", btn_addForm4_0, "xpath", formName, "form list");
			Log.message("The added G-28 form is deleted as part of post execution cleanup", driver, true);			
			Utils.waitForElement(driver, txt_FileUploadMessageAppeared4_0);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css");
		}
		}
		catch (Exception e)
		{
			Log.message("Unable to delete G-28 form as part of post execution cleanup. ERROR :\n\n " + e.getMessage(), driver, true);
		}
			
	}
	
}