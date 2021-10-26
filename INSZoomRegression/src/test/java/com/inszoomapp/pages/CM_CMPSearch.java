package com.inszoomapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class CM_CMPSearch extends LoadableComponent<CM_CMPSearch> {

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	String clientFullName = "";
	
	@FindBy(xpath = "//span[contains(text(),'Searching...')]")
	WebElement loader_advanceSearch;
	
	@FindBy(id = "txtAdvSearch")
	WebElement searchBox_advance;
	
	@FindBy(id = "dropdownText")
	WebElement dropDown_search;

	public CM_CMPSearch(WebDriver driver) {

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
	
	public void verifySearchUsingCorpName(String dropDownValue, String key, String signingPerson, String corpID, String corpName, String email)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  10 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong//strong[text()='"+corpName+"']", "xpath", corpName, "Corporation Name as "+corpName);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong//strong[text()='"+corpName+"']/../..//small[contains(text(),'"+corpID+"')]", "xpath", corpID, "Corporation ID as "+corpID);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong//strong[text()='"+corpName+"']/../..//small[contains(text(),'"+signingPerson+"')]", "xpath", signingPerson, "Signing person as "+signingPerson);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong//strong[text()='"+corpName+"']/../..//small[contains(text(),'"+email+"')]", "xpath", email, "Email as "+email);
	}
	
	public void verifySearchUsingCorpID(String dropDownValue, String key, String signingPerson, String corpID, String corpName, String email)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  10 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span//strong[text()='"+corpName+"']", "xpath", corpName, "Corporation Name as "+corpName);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span//strong[text()='"+corpName+"']/../..//small//strong[contains(text(),'"+corpID+"')]", "xpath", corpID, "Corporation ID as "+corpID);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span//strong[text()='"+corpName+"']/../..//small[contains(text(),'"+signingPerson+"')]", "xpath", signingPerson, "Signing person as "+signingPerson);
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span//strong[text()='"+corpName+"']/../..//small[contains(text(),'"+email+"')]", "xpath", email, "Email as "+email);
	}
	
	public void verifySearchUsingInvalidData(String dropDownValue, String key)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  10 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//li[@id='noResultsFoundLI']//div[text()='No Records Found ']", "xpath", "No records found", "using "+key);
	}
	
	public void verifySearchUsingClientName(String corporationName, String dropDownValue, String key, String firstName, String lastName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  10 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]", "xpath", firstName + lastName, "Client Name");
		//Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	public void verifySearchUsingClientEmail(String corporationName, String dropDownValue, String key, String clientName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "Client Name");
		//Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small/strong[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	public void verifySearchUsingClientFileNumber(String corporationName, String dropDownValue, String key, String clientName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "Client Name");
		//Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small/strong[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	
	public void verifySearchUsingClientAlienNumber(String corporationName, String dropDownValue, String key, String clientName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "Client Name");
		//Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small/strong[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	
	public void verifySearchUsingClientDOB(String corporationName, String dropDownValue, String key, String clientName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "Client Name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	
	public void verifySearchUsingClientUsingCorporationName(String corporationName, String dropDownValue, String key, String clientName, String email, String fileNumber, String alienNumber, String dob, String applicantType, String clientType, String clientID)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "Client Name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+dob+"')]", "xpath", dob, "DOB");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientType+"')]", "xpath", clientType,"client type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+applicantType+"')]", "xpath", applicantType, "applicant type");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+alienNumber+"')]", "xpath", alienNumber, "alien number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+clientID+"')]", "xpath", clientID, "client ID");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+clientName+"')]/../..//small/strong[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	public void verifySearhClientName(String firstName, String lastName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  12 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		//Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		String clientName = firstName + " " + lastName ;
		Utils.enterText(driver, searchBox_advance, clientName, "search key as "+clientName);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]", "xpath", firstName + lastName, "Client Name");
	}
	
	public void verifySearchUsingCaseID(String dropDownValue, String key, String caseId, String caseDescription, String caseFileNumber, String clientName, String emailId, String receiptNumber)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  17 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]", "xpath", caseId, "Case ID" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "Case description (Petition Name)");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+caseFileNumber+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]/../..//small[contains(text()[2],'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "receipt Number");
	}
	
	
	public void verifySearchUsingCaseFileNumber(String dropDownValue, String key, String caseId, String caseDescription, String caseFileNumber, String clientName, String emailId, String receiptNumber)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  17 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]", "xpath", caseId, "Case ID" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "Case description (Petition Name)");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseFileNumber+"')]", "xpath", caseFileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "receipt Number");
	}
	
	public void verifySearchUsingCaseClientName(String dropDownValue, String key, String caseId, String caseDescription, String caseFileNumber, String firstName, String lastName, String emailId, String receiptNumber)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  18 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]", "xpath", caseId, "Case ID" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "Case description");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+firstName+"')]/..//strong[contains(text(),'"+lastName+"')]", "xpath", firstName + " " + lastName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+caseFileNumber+"')]", "xpath", caseFileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "receipt Number");
	}
	
	public void verifySearchUsingCaseDescription(String dropDownValue, String key, String caseId, String caseDescription, String caseFileNumber, String clientName, String emailId, String receiptNumber)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On:  8 September 2021
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]", "xpath", caseId, "Case ID" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "Case description (Petition Name)");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+caseFileNumber+"')]", "xpath", caseFileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "receipt Number");
	}
	
	public void verifySearchUsingCaseReceiptNumber(String dropDownValue, String key, String caseId, String caseDescription, String caseFileNumber, String clientName, String emailId, String receiptNumber)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  17 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]", "xpath", caseId, "Case ID" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "Case description (Petition Name)");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+caseFileNumber+"')]", "xpath", caseFileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//small[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+caseId+"')]/../..//strong[contains(text(),'"+receiptNumber+"')]", "xpath", receiptNumber, "receipt Number");
	}
	
	
	public void verifySearchUsingProspectiveClientName(String dropDownValue, String key, String firstName, String lastName, String fileNumber, String conversionRate)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+firstName+"')]", "xpath", firstName, "first name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+lastName+"')]", "xpath", lastName, "last name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number)");
		String conversion = "Conversion Possibility: " + conversionRate ; 
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+conversion+"')]", "xpath", conversionRate, "conversion rate");
	}
	
	
	public void verifySearchUsingProspectiveFileNumber(String dropDownValue, String key, String clientName, String fileNumber, String conversionRate)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+clientName+"')]/../..//strong[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number)");
		String conversion = "Conversion Possibility: " + conversionRate ; 
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//small[contains(text(),'"+conversion+"')]", "xpath", conversionRate, "conversion rate");
	}
	
	public void verifySearchUsingConvertedClientName(String dropDownValue, String key, String firstName, String lastName)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+firstName+"')]", "xpath", firstName, "first name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+lastName+"')]", "xpath", lastName, "last name" );
	}
	
	public void verifySearchUsingProspectivCorpName(String dropDownValue, String key, String corporationName, String contactPersonName, String fileNumber, String emailId)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../../small[contains(text(),'"+contactPersonName+"')]", "xpath", contactPersonName, "contact person name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../../small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../../small[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");	
	}
	
	public void verifySearchUsingProspectivCorpContactPerson(String dropDownValue, String key, String corporationName, String contactPersonFirstName, String contactPersonLastName, String fileNumber, String emailId)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//strong[contains(text(),'"+contactPersonFirstName+"')]", "xpath", contactPersonFirstName, "contact person first name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//strong[contains(text(),'"+contactPersonLastName+"')]", "xpath", contactPersonLastName, "contact person last name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text()[2],'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text()[2],'"+emailId+"')]", "xpath", emailId, "email id");	
	}
	
	public void verifySearchUsingProspectiveCorpFileNumber(String dropDownValue, String key, String corporationName, String contactPersonName, String fileNumber, String emailId)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text(),'"+contactPersonName+"')]", "xpath", contactPersonName, "contact person name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//strong[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text()[2],'"+emailId+"')]", "xpath", emailId, "email id");	
	}
	
	public void verifySearchUsingProspectiveCorpEmailId(String dropDownValue, String key, String corporationName, String contactPersonName, String fileNumber, String emailId)
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On:  24 August 2020
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name" );
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text(),'"+contactPersonName+"')]", "xpath", contactPersonName, "contact person name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//small[contains(text(),'"+fileNumber+"')]", "xpath", fileNumber, "file number");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong[contains(text(),'"+corporationName+"')]/../..//strong[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");	
	}
	
	public void verifySearchUsingCorpUserName(String corporationName, String dropDownValue, String key, String firstName, String lastName, String email)
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On:  30 September 2021
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]", "xpath", firstName + lastName, "Client Name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+email+"')]", "xpath", email, "email");
		
		if(!(corporationName.equals("")))
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//strong/strong[contains(text(),'"+firstName+"')]/following-sibling::strong[contains(text(),'"+lastName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
	public void verifySearchUsingCorpUserEmail(String corporationName, String dropDownValue, String key, String corpUserName, String email)
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On:  30 September 2021
		 */
		Utils.clickElement(driver, dropDown_search, "Search dropdown");
		Utils.clickDynamicElement(driver, "//div[@class='ESIdropdown-menu']//a[text()='"+dropDownValue+"']", "xpath", "drop down value = "+dropDownValue);
		Utils.enterText(driver, searchBox_advance, key, "search key as "+key);
		Utils.waitForElement(driver, loader_advanceSearch);
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Searching...')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+corpUserName+"')]", "xpath", corpUserName, "Client Name");
		Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+corpUserName+"')]/../..//small/strong[contains(text(),'"+email+"')]", "xpath", email, "email");
		if(!(corporationName.equals("")))
			Utils.verifyIfDataPresent(driver, "//div[@class='searchResultDropdown']//span/strong[contains(text(),'"+corpUserName+"')]/../..//small[contains(text(),'"+corporationName+"')]", "xpath", corporationName, "corporation name");
	}
	
}
