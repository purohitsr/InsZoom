package com.inszoomapp.pages;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_EditClientRelativePage extends LoadableComponent<CM_EditClientRelativePage> 
{

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Added By Saurav On 21 Jan 2020
	@FindBy(xpath = "//td[@id='PARAHDRWITHNOCAPS']//td[2][contains(text(),'Relative')]")
	WebElement txt_RelativesInfo;
		
	@FindBy(id="btn_CancelClientsRelativesRegistration")
	WebElement btn_Cancel;
	
	@FindBy(id="btn_RemoveRelative(s)")
	WebElement btn_RemoveRelation;
	
	@FindBy(id="btn_UnlinkRelative(s)")
	WebElement btn_RemoveRelative;
	
	@FindBy(css = "input[title='Edit Relation(s)']")
	WebElement btn_EditRelations;
	
	@FindBy(id="btn_SaveRelativesClient")
	WebElement btn_SaveExistingRelative;
	
	@FindBy(xpath = "//input[contains(@onclick, 'Javascript:if(validName());')]")
	WebElement btn_Go;
	
	@FindBy(css = "input[name='txtFname']")
	WebElement searchbox_FirstName;
	
	@FindBy(xpath = "//a[contains(text(), 'Search existing to Add as Relative')]")
	WebElement lnk_AddExistingRelative;
	
	@FindBy(css = "input[value='Save'][type='button']")
	WebElement btn_SaveRelative;

	@FindBy(id="txtEmail")
	WebElement txtbox_EmailID;
	
	@FindBy(id = "txtFname")
	WebElement txtbox_FirstName;

	@FindBy(id = "txtLname")
	WebElement txtbox_LastName;

    @FindBy(css = "select[id='cboRelation']")
	WebElement dropdown_Relation;

	@FindBy(css = "select[id='cboBnfGender_cmbGender']")
	WebElement dropdown_Gender;
	
	@FindBy(id="btn_SaveEditRelationship")
	WebElement btn_SaveAfterEditingRelationship;
	
	@FindBy(id="btn_Add")
	WebElement btn_AddRelative;
	
	@FindBy(xpath = "//a[contains(text(), 'Add new')]")
	WebElement lnk_AddNewRelative;


	public CM_EditClientRelativePage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);

	}

	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Edit client relative Page did not open up. Site might be down.", driver, true);
		}

	}

	
	public void clickAddRelativeButton()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.clickElement(driver, btn_AddRelative, "Add Relative button");
	}
	
	
	public void clickAddNewRelativeLink()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.clickElement(driver, lnk_AddNewRelative, "Add New Relative link");

	}
	
	
	public void enterRelativesDataAndSave(String firstName, String lastName, String relativeName, String relation, String gender, String email) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.enterText(driver, txtbox_FirstName, firstName, "First Name");
		
		Utils.enterText(driver, txtbox_LastName, lastName, "Last Name");

		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown_Relation);
				
		Utils.selectOptionFromDropDownByVal(driver, gender, dropdown_Gender);
		
		Utils.enterText(driver, txtbox_EmailID, email, "Email ID");
	
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
				
		Utils.clickElement(driver, btn_SaveRelative, "Save Button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com: Relative's Confirmation", "title", "false");
		
		Utils.clickElement(driver, btn_SaveAfterEditingRelationship, "Save Button in Relative Confirmation Window");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com: Client's Relative(s)", "title", "false");
				
	}
	
	
	public void verifyRelativeDetails(String relativeName, String relation)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 24/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + relativeName + "')]/../following-sibling::td/a[contains(text(), '" + relation + "')]", "xpath", relation, "Relation");
	}
	
	
	public void clickSearchExistingLink() throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */
		
		Utils.clickElement(driver, lnk_AddExistingRelative, "'Search existing to Add as Relative'");

	}


	public void searchClient(String clientName)  
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */
		
		Utils.enterText(driver, searchbox_FirstName, clientName, "Client Name");
		
		Utils.clickElement(driver, btn_Go, "GO Button");
			
	}
	
	
	public void chooseRelation(String clientName)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */

		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientName + "')]/../preceding-sibling::td/input", "xpath", "Relative Check box");
		Utils.waitForElement(driver, "//a[contains(text(), '" + clientName + "')]/../following-sibling::td/div/select", globalVariables.elementWaitTime, "xpath");
		WebElement dropList = driver.findElement(By.xpath("//a[contains(text(), '" + clientName + "')]/../following-sibling::td/div/select"));
		Utils.selectOptionFromDropDownByVal(driver, "Child", dropList);
	}

	
	public void saveRelativeAndConfirm(String firstClientName, String relativeName, String relation) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */
		
		Utils.clickElement(driver, btn_SaveExistingRelative, "Save Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com: Relative's Confirmation", "title", "false");
		Utils.waitForElement(driver, "//font[contains(text(), '" + relativeName + "')]/../following-sibling::td//select", globalVariables.elementWaitTime, "xpath");
		WebElement dropdown_relation = driver.findElement(By.xpath("//font[contains(text(), '" + relativeName + "')]/../following-sibling::td//select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown_relation);
		
		Utils.clickElement(driver, btn_SaveAfterEditingRelationship, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com: Client's Relative(s)", "title", "false");
		
	}
	
	
	public void verifySecondClientAsRelative(String secondClientName, String relation)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + secondClientName +"')]/../following-sibling::td//a[contains(text(), '" + relation +"')]", "xpath", "Child", "Relationship");
		
	}
	
	
	public void saveThirdRelativeAndConfirm(String thirdClientName, String relativeName, String relation) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 31/10/2019
		 */
		
		Utils.clickElement(driver, btn_SaveExistingRelative, "Save Button");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com: Relative's Confirmation", "title", "false");
		Utils.waitForElement(driver, "//font[contains(text(), '" + relativeName + "')]/../following-sibling::td//select", globalVariables.elementWaitTime, "xpath");
		WebElement dropdown_relation = driver.findElement(By.xpath("//font[contains(text(), '" + relativeName + "')]/../following-sibling::td//select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown_relation);
		Utils.waitForElement(driver, "//font[contains(text(), '" + thirdClientName + "')]/../following-sibling::td//select", globalVariables.elementWaitTime, "xpath");
		WebElement dropdown_relation1 = driver.findElement(By.xpath("//font[contains(text(), '" + thirdClientName + "')]/../following-sibling::td//select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown_relation1);
		
		Utils.clickElement(driver, btn_SaveAfterEditingRelationship, "Save Button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com: Client's Relative(s)", "title", "false");
		
	}
	
	
	public void editRelations(String relation, String clientFirstName, String clientLastName, String thirdRelative, String relative, String secondClient, String thirdClient) throws Exception 
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 04/11/2019
		 */
		
		Utils.clickElement(driver, btn_EditRelations, "Edit Relations");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Relationship", "title", "false");
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + clientFirstName + " " + clientLastName + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		WebElement dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + clientFirstName + " " + clientLastName + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + thirdClient + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + relative + "']//td[contains(text(), '" + thirdClient + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + thirdRelative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + thirdClient + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + secondClient + "']//td[contains(text(), '" + thirdClient + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + thirdClient + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + thirdClient + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + thirdClient + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + thirdClient + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + thirdRelative + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + thirdRelative + "']//td[contains(text(), '" + relative + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		Utils.waitForElement(driver, "//table[@summary='Relative(s) of " + thirdRelative + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select", globalVariables.elementWaitTime, "xpath");
		dropdown = driver.findElement(By.xpath("//table[@summary='Relative(s) of " + thirdRelative + "']//td[contains(text(), '" + secondClient + "')]/following-sibling::td/select"));
		Utils.selectOptionFromDropDownByVal(driver, relation, dropdown);
		
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
			
		Utils.clickElement(driver, btn_SaveAfterEditingRelationship, "Save Button");


		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com: Client's Relative(s)", "title", "false");
		
	}
	
	
	public void verifyAfterEditingRelations(String relative, String secondClient, String thirdClient, String thirdRelative, String relation)
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 04/11/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + secondClient +"')]/../following-sibling::td//a[contains(text(), '" + relation +"')]", "xpath", "Child", "Relationship for " + secondClient);
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + relative +"')]/../following-sibling::td//a[contains(text(), '" + relation +"')]", "xpath", "Child", "Relationship for " + relative);
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + thirdRelative +"')]/../following-sibling::td//a[contains(text(), '" + relation +"')]", "xpath", "Child", "Relationship for " + thirdRelative);
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + thirdClient +"')]/../following-sibling::td//a[contains(text(), '" + relation +"')]", "xpath", "Child", "Relationship for " + thirdClient);
	}
	
	
	public void removeRelation(String thirdClient, String thirdRelative, String secondClient, String relative) throws Exception
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 04/11/2019
		 */
		
		Utils.clickElement(driver, btn_RemoveRelative, "Remove Relation");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com: Unlink Relative(s)", "title", "false");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + relative + "')]/preceding-sibling::td/input", "xpath", "CheckBox for " + relative);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + thirdRelative + "')]/preceding-sibling::td/input", "xpath", "CheckBox for " + thirdRelative);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + thirdClient + "')]/preceding-sibling::td/input", "xpath", "CheckBox for " + thirdClient);
		Utils.clickDynamicElement(driver, "//td[contains(text(),'" + secondClient + "')]/preceding-sibling::td/input", "xpath", "CheckBox for " + secondClient);
		
		Utils.clickElement(driver, btn_RemoveRelation, "Remove Button");
		
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com: Client's Relative(s)", "title", "false");
	}
	
	
	public void verifyIfRelationRemoved()
	{
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 04/11/2019
		 */
		
		Utils.verifyIfDataPresent(driver, "td[class='noRecord']", "css", "No Record Found", "Relatives");
	}
	
	
	public void selectRelativeNameForVisa(String relativeName, boolean screenshot) throws Exception {
		/*
		 * Modified By: Likitha Krishna
		 * Modified On: 21/08/2019
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ relativeName +"')]", "xpath", "relativeName in relative page");
	} 
 
	public void verifyRelativesPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Jan 2020
		  */
	        
		Utils.softVerifyIfStaticElementDisplayed(driver, txt_RelativesInfo, "Relative Header", "Relative Info Page");
		
	}
	
	public void verifyRelativesRegistrationPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Jan 2020
		  */
	    try{
//	        String actualTitle = driver.getTitle();
		
	
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Client 's/Relative's Registration");
//		Log.message("clicking on Cancel button again ");
		Utils.clickElement(driver, btn_Cancel, "Cancel button ");
	    }catch(Exception e){
		 Log.failsoft("Verification of Reative Registation failed", driver);
	    }
	    
	   
	}
	
	
	 public void verifySerachForExistingClientPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 21 Jan 2020
			  */
		    try{
		       Utils.softVerifyPageTitle(driver, "INSZoom.com - Search for existing Client");
		    }catch(Exception e){
			 Log.failsoft("Verification of SerachForExistingClient For Relatives Page Failed", driver);
		    }
	}
	
	
	
	

}
