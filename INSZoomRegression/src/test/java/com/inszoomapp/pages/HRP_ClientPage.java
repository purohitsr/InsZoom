package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class HRP_ClientPage extends LoadableComponent<HRP_ClientPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//a[contains(text(),'My Client')]")		//Added by Kuchinad Shashank on 3/02/2020
	WebElement tab_Myclient;

	@FindBy(xpath = "//a[contains(text(), 'Details')]")
	WebElement waitElement_HRPTabs;	
	
	@FindBy(xpath = "//h2[contains(text(),'PROFILE')]")      //Added by Nitisha Sinha on 14/2/2020
	WebElement waitElement_profile;	
	
	@FindBy(id="ReplyButton")
	WebElement btn_reply;
	
	@FindBy(name="sendEmailBtn")
	WebElement btn_sendEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;
	
	@FindBy(xpath="//div[@id='InboxMessages']//span[@class='k-state-selected' and contains(text(), '1')]")
	WebElement waitElement_Pagination;	
	
	@FindBy(xpath = "//a[contains(text(),'Passport And Visas')]")
	WebElement tab_PassportAndVisasInRelative;
	
	@FindBy(xpath = "//a[@onclick='LoadRelatives();']")
	WebElement img_family;
	  
	@FindBy(id="txtSearchForDocument")
	WebElement searchbox_document;
	
	@FindBy(css="button[onclick='UploadDoc();']")
	WebElement btn_upload;

	@FindBy(id="uploadFile")
	WebElement btn_uploadFile;
	
	@FindBy(css = "img[src='../../Content/img/Document.png']")
	WebElement btn_documents; 
	
	@FindBy(xpath = "//div[@class='k-loading-mask']")
	WebElement loader_hrp;
	
	@FindBy(id = "NotesListSearch")
	WebElement searchBox_notes;
	
	@FindBy(id = "closed_case_accordian_anchor")
	WebElement link_closedCases;
	
	@FindBy(xpath = "//li/a[contains(text(),'Emails')]")
	WebElement tab_emails;
	
	@FindBy(xpath = "//a[@href='#tab-Notes' and text()='Notes']")
	WebElement tab_Notes;
	
	@FindBy(xpath = "//a[@href='#tab-emails' and text()='Emails']")
	WebElement tab_Email;
	
	@FindBy(xpath = "//a[@href='#tab-job' and text()='Job']")
	WebElement tab_Job;
	
	@FindBy(xpath = "//a[@href='#tab-customdata' and text()='Custom Data']")
	WebElement tab_CustomData;
	
	@FindBy(xpath = "//a[@href='#tab-LCA' and text()='LCA']")
	WebElement tab_LCA;
	
	@FindBy(xpath = "//a[@href='#tab-passportvisas' and text()='Passport And Visas']")
	WebElement tab_PassportAndVisas;
	
	@FindBy(xpath = "//a[@href='#tab-background' and text()='Background']")
	WebElement tab_Background;
	
	@FindBy(xpath = "//li/a[contains(text(),'Notes')]")
	WebElement tab_caseNotes;
	
	@FindBy(xpath = "//a[@href='#tab-process' and text()='Details']")
	WebElement tab_Details;
	
	@FindBy(xpath = "//li/a[contains(text(),'Details/Dates')]")
	WebElement tab_detailsDates;
	
	@FindBy(xpath = "//li/a[contains(text(),'Case Steps')]")
	WebElement tab_caseSteps;
	
	@FindBy(id = "txtSearchForName")
	WebElement searchBox_clientName;
	

	
	public HRP_ClientPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public HRP_ClientPage(WebDriver driver) {
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
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void searchforClient(String clientName, boolean screenshot) 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		
		Utils.enterText(driver, searchBox_clientName, clientName + Keys.ENTER, "in search box");
		
	}
	
	
	public void clickOnClientName(String clientName) throws InterruptedException
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickDynamicElement(driver, "//table[@role='grid']//a[contains(text(),'"+ clientName +"')]", "xpath", "Client Name");
		Thread.sleep(5000);
	}
	
	public void clickCaseLink(String caseId) throws InterruptedException 
	{
		/*
		 * Created By : Likitha Krishna M
		 * Created On : 08 Nov 2019
		 */
		Utils.waitForAjax(driver);
		
		Utils.waitForElement(driver, "//div[@id='CaseListDetails']//a[contains(text(),'"+ caseId +"')]", globalVariables.elementWaitTime, "xpath");
		WebElement caseLink = driver.findElement(By.xpath("//div[@id='CaseListDetails']//a[contains(text(),'"+ caseId +"')]"));
		Utils.scrollIntoView(driver, caseLink);
		Utils.clickElement(driver, caseLink, "Clicked on " + caseId);
		Thread.sleep(3000);
	}
	
	public void verifyTabsInCaseDetails()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.waitForAjax(driver);
		Utils.verifyIfStaticElementDisplayed(driver, tab_caseSteps, "case steps tab", "case page");
		Utils.verifyIfStaticElementDisplayed(driver, tab_detailsDates, "details/dates tab", "case page");
		Utils.verifyIfStaticElementDisplayed(driver, tab_emails, "emails tab", "case page");
		Utils.verifyIfStaticElementDisplayed(driver, tab_caseNotes, "notes tab", "case page");
	}
	
	
	public void caseStepsVerify(boolean sceenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		
		for(int i = 0 ; i < globalVariables.QA_ClientAccessCaseSteps.size(); i++)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='case-processingdetails']//div[@class='col-lg-3 CaseProcessingDetails'][contains(text(),'"+ globalVariables.QA_ClientAccessCaseSteps.get(i) +"')]", "xpath", globalVariables.QA_ClientAccessCaseSteps.get(i), "Case step page");
		}
	}
	
	
	public void clickCaseStepsTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, tab_caseSteps, "Case steps tab");
	}
	
	
	public void clickDetailsDatesTab() throws InterruptedException
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		Utils.clickElement(driver, tab_detailsDates, "Details/Dates tab");
		Thread.sleep(10000);
	}
	
	public void verifyDetailsDates(boolean screenshot) throws Exception {

		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */ 
		
		Utils.waitForAjax(driver);
		
		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Filing Details')]", "xpath", "Filing details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Decision Details')]", "xpath", "Decision Details header", "under details and dates tab");
		
		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Proposed Job Info')]", "xpath", "Proposed Job Info header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Additional Application/ Receipt Details')]", "xpath", "Additional Application/ Receipt Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Custom Data')]", "xpath", "Custom Data header", "under details and dates tab");
		
		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'Visa Priority Date Info')]", "xpath", "Visa Priority Date Info header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'LCA Details')]", "xpath", "LCA Details header", "under details and dates tab");

		Utils.verifyIfDataPresent(driver, "//header[@class='main-box-header clearfix']/h2[contains(text(),'SWA and DOL info')]", "xpath", "SWA And DOL Info header", "under details and dates tab");

	}
	
	public void verifyTabsInClient() throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Nov 2019
		  */ 
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_Details, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_Background, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_PassportAndVisas, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_LCA, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_CustomData, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_Job, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_Email, "Details tab", "client page");
		
		Utils.verifyIfStaticElementDisplayed(driver, tab_Notes, "Details tab", "client page");
	}
	
	
	public void clickEmailsTab()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 03 Dec 2019
		  */ 
		
		Utils.clickElement(driver, tab_Email, "Emails tab");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
	}
	
	
	public void verifyIfEmailPresent(String accessCodeEmailSubject)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 03 Dec 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + accessCodeEmailSubject + "')]", "xpath", accessCodeEmailSubject, "Received Emails tab");
	}
	
	
	public void verifyCaseOpenLabel(String caseId)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ caseId +"')]/..//span[@class='label label-info'][contains(text(),'Open')]", "xpath", caseId, "case open");
	}
	
	
	public void verifyCaseApprovedLabel(String caseId)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ caseId +"')]/..//span[@class='label label-success'][contains(text(),'Approved')]", "xpath", caseId, "case open");
	}
	
	
	public void verifyCaseClosedLabel(String caseId)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 07 Feb 2020
		 */
		
		Utils.clickElement(driver, link_closedCases, "show closed cases link");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + caseId + "')]/..//span[@class='label label-danger'][contains(text(),'Closed')]", "xpath", caseId, "case closed label");
	}
	
	public void clickPassportAndVisasTab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 10 Feb 2020
		 */
		
		Utils.clickElement(driver, tab_PassportAndVisas, "passport and visas tab");
	}
	
	public void verifyPassportDetails(String passportNumber, String dateOfIssuance, String country, String validTo)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 10 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+country+"')]/../../following-sibling::td/a[contains(text(),'"+passportNumber+"')]/../following-sibling::td[contains(text(),'"+dateOfIssuance+"')]/following-sibling::td[contains(text(),'"+validTo+"')]/following-sibling::td[contains(text(),'Valid')]", "xpath", "passport details", "passport and visa tab");
//		Utils.clickElement(driver, tab_PassportAndVisas, "passport and visas tab");
//		Utils.clickDynamicElement(driver, "//a[contains(text(),'" + passportName + "')]", "xpath", "passport number link");
//		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Date of Issuance')]/../td/span[contains(text(),'"+dateOfIssuance+"')]", "xpath", dateOfIssuance, "date of issuance");
//		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Issuing Country')]/../td/span[contains(text(),'"+country+"')]", "xpath", country, "country");
//		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Valid To')]/../td/span[contains(text(),'"+validTo+"')]", "xpath", validTo, "valid to");
	}
	
	
	public void verifyVisaDetails(String country, String visaType, String validFrom, String validTo)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 10 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+country+"')]/../../following-sibling::td[contains(text(),'"+visaType+"')]/following-sibling::td[1][contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]/following-sibling::td[contains(text(),'Valid Visa')]", "xpath", "visa details", "passport and visa page");
	}
	
	public void clickNotesTab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		
		Utils.clickElement(driver, tab_Notes, "notes tab");
	}
	
	
	public void verifyNotes(String notesDescription)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='Time']/following-sibling::div/div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "notes page");
		Utils.clickDynamicElement(driver, "//div[contains(text(),'"+notesDescription+"')]/../preceding-sibling::div/a[contains(text(),'note')]", "xpath", "notes link");
		Utils.verifyIfDataPresent(driver, "//header[@class='story-header']/following-sibling::div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "popup");
	}
	
	public void verifyNotesSearch(String notesDescription)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		Utils.enterText(driver, searchBox_notes, notesDescription, "notes search box");
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@id='Time']/following-sibling::div/div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "notes page for positive test case for notes");
		Utils.enterText(driver, searchBox_notes, "JUNK", "notes search box");
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@class='k-grid-norecords'][contains(text(),'No Records Found')]", "xpath", "No Records found", "negative test case for search box");	
	}
	
	
	public void clickCaseNotesTab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		
		Utils.clickElement(driver, tab_caseNotes, "notes tab in case");
	}
	
	public void verifyCaseNotes(String notesDescription)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@id='Time']/following-sibling::div/div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "notes page");
		Utils.clickDynamicElement(driver, "//div[contains(text(),'"+notesDescription+"')]/../preceding-sibling::div/a[contains(text(),'note')]", "xpath", "notes link");
		Utils.verifyIfDataPresent(driver, "//header[@class='story-header']/following-sibling::div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "popup");
		
	}
	
	public void verifyCaseNotesSearch(String notesDescription)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 11 Feb 2020
		 */
		Utils.enterText(driver, searchBox_notes, notesDescription, "notes search box");
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@id='Time']/following-sibling::div/div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "notes page for positive test case for notes");
		Utils.enterText(driver, searchBox_notes, "JUNK", "notes search box");
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath");
		Utils.verifyIfDataPresent(driver, "//div[@id='tab-notes']//div[@class='k-grid-norecords'][contains(text(),'No Records Found')]", "xpath", "No Records found", "negative test case for search box");	
	}
	
	
	public void verifyIfClientNotPresent()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), 'No Client Found')]", "xpath", "No Records Found", "Client List Page");
	}
	
	
	public void clickBackgroundTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */
		
		Utils.clickElement(driver, tab_Background, "Background");
	}
	
	
	public void verifySchoolHistory(String schoolNameNew, String historyDateFNP, String universityName, String degree, String fieldOfStudy, String numberForPersonalInfo )
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */ 

		Utils.verifyIfDataPresent(driver, "//th[@data-field='SchoolName']/../../following-sibling::tbody//td[contains(text(), '" + schoolNameNew + "')]", "xpath", schoolNameNew, "School Name");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='UniversityBoard']/../../following-sibling::tbody//td[contains(text(), '" + universityName + "')]", "xpath", universityName, "University Name");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Degree']/../../following-sibling::tbody//td[contains(text(), '" + degree + "')]", "xpath", degree, "Degree");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='FieldOfStudy']/../../following-sibling::tbody//td[contains(text(), '" + fieldOfStudy + "')]", "xpath", fieldOfStudy, "Field of Study");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='FromDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "From Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='ToDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "To Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Phone']/../../following-sibling::tbody//td[contains(text(), '" + numberForPersonalInfo + "')]", "xpath", numberForPersonalInfo, "Phone Number");
	}
	
	
	public void verifyEmploymentHistory(String jobNew, String historyDateFNP, String employer, String numberForPersonalInfo)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */ 
		

		Utils.verifyIfDataPresent(driver, "//th[@data-field='FromDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "From Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='ToDate']/../../following-sibling::tbody//td[contains(text(), '" + historyDateFNP + "')]", "xpath", historyDateFNP, "To Date");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='OccupationTitle']/../../following-sibling::tbody//td[contains(text(), '" + jobNew + "')]", "xpath", jobNew, "Designation");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='Phone']/../../following-sibling::tbody//td[contains(text(), '" + numberForPersonalInfo + "')]", "xpath", numberForPersonalInfo, "Phone Number");
		
		Utils.verifyIfDataPresent(driver, "//th[@data-field='EmployerName']/../../following-sibling::tbody//td[contains(text(), '" + employer + "')]", "xpath", employer, "Company");
	}
	
	
	public void verifyAddressHistory(String cityEditted, String historyDateFNP, String countryNew, String streetNumber, String state)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */ 
		
		String address = cityEditted + " " + state + " " + countryNew;
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + address + "')]/following-sibling::td[contains(text(),'" + historyDateFNP + "')]/following-sibling::td[contains(text(),'" + historyDateFNP + "')]", "xpath", "Past address", "Background details"); 
		
	}
	
	
	public void clickDocuments()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Feb 2019
		 */ 
		
		Utils.clickElement(driver, btn_documents, "Documents");
	}
	
	
	public void uploadDocument(String filePath)throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Feb 2019
		 */ 
		
		Utils.clickElement(driver, btn_upload, "Upload");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Upload file", "title", "false");
		
		filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		File filepath = new File(filePath);
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);

		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString());

		WebElement droparea = null;
		try {

			droparea = driver.findElement(By.cssSelector("div[class='dropzone dz-clickable']"));
		} catch (Exception e) {
			Log.fail("Not able to fetch the drop area for file");
		}
		
		WebDriver driver = ((RemoteWebElement) droparea).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;


		String JS_DROP_FILE = "var target = arguments[0]," + "    offsetX = arguments[1],"
				+ "    offsetY = arguments[2]," + "    document = target.ownerDocument || document,"
				+ "    window = document.defaultView || window;" + "" + "var input = document.createElement('INPUT');"
				+ "input.type = 'file';" + "input.style.display = 'none';" + "input.onchange = function () {"
				+ "  var rect = target.getBoundingClientRect(),"
				+ "      x = rect.left + (offsetX || (rect.width >> 1)),"
				+ "      y = rect.top + (offsetY || (rect.height >> 1)),"
				+ "      dataTransfer = { files: this.files };" + ""
				+ "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {"
				+ "    var evt = document.createEvent('MouseEvent');"
				+ "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);"
				+ "    evt.dataTransfer = dataTransfer;" + "    target.dispatchEvent(evt);" + "  });" + ""
				+ "  setTimeout(function () { document.body.removeChild(input); }, 25);" + "};"
				+ "document.body.appendChild(input);" + "return input;";

		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);

	    ((RemoteWebElement) input).setFileDetector(detector);
	    
		input.sendKeys(file.getAbsolutePath());
		
//		input.sendKeys(filepath.getAbsoluteFile().toString());
		
		Utils.clickElement(driver, btn_uploadFile, "Upload Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - HR Portal", "title", "false");
	}
	
	
	public void verifyIfDocumentPresent(String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Feb 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + fileName + "')]", "xpath", fileName, "Documents");
	}
	
	
	public void verifyIfDocumentNotPresent()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Feb 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), 'No Documents Found')]", "xpath", "No Documents Found", "Documents");
	}
	
	
	public void viewDocument(String fileNameWithoutExtensionFNP, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Nov 2019
		 * 
		 */
		
		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + fileNameWithoutExtensionFNP + "')]", "xpath", "View Icon");
		
		Thread.sleep(10000);
		
		Utils.verifyIfDownloaded(remoteDriver);
		
	}
	
	
	public void deleteDocument(String fileNameWithoutExtensionFNP)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Feb 2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + fileNameWithoutExtensionFNP + "')]/../following-sibling::td//i[@class='fa fa-trash-o']", "xpath", "Delete Icon");
		
		if(Utils.isAlertPresent(driver))
		{
			if(driver.switchTo().alert().getText().equals("Are you sure you want to delete this document?"))
			{
				driver.switchTo().alert().accept();
				Log.message("Accepted the alert to Delete the document");
			}
			
			else
				Log.message("Alert to confirm deletion is not present");
		}
		
		else
			Log.message("Alert to confirm deletion is not present");
		
		if(Utils.isAlertPresent(driver))
		{
			if(driver.switchTo().alert().getText().equals("Document Deleted Successfully"))
			{
				driver.switchTo().alert().accept();
				Log.message("Accepted the alert which verifies document deletion");
			}
			
			else
				Log.message("Alert to verify deletion is not present");
		}
		
		else
			Log.message("Alert to verify deletion is not present");
	}
	
	
	public void searchForDocument(String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Feb 2019
		 * 
		 */
		
		Utils.enterText(driver, searchbox_document, fileName, "Document searchbox");
		
		Utils.waitForElement(driver, loader_hrp);
		Utils.waitForElementNotVisible(driver, "//div[@class='k-loading-mask']", "xpath"); 
	}
	
	
	public void clickCustomDataTab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		
		Utils.clickElement(driver, tab_CustomData, "custom data tab");
	}
	
	public void verifySpecificCustomData(String field, String data)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='SpecificCustData']//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
	}
	
	
	public void clickLCATab()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		
		Utils.clickElement(driver, tab_LCA, "LCA tab");
	}
	
	
	public void verifyLCAdetails(String LCANumber, String jobTitle, String LCAcity, String caseDescription,  String validFrom, String validThru,  String LCAeffectiveOn, String LCAeffectiveTill)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		String validity = validFrom+ " - " + validThru ; 
		String effective = LCAeffectiveOn + " - " + LCAeffectiveTill ;
		//Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+LCANumber+"')]", "xpath", LCANumber, "LCA Numberin client page");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+jobTitle+"')]", "xpath", jobTitle, "job Title in client page");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+LCAcity+"')]", "xpath", LCAcity, "LCA city in client page");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+caseDescription+"')]", "xpath", caseDescription, "case description in client page");
		Utils.verifyIfDataPresent(driver, "//td/span[contains(text(),'"+validity+"')]", "xpath", validity, "validity of LCA in client page");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+effective+"')]", "xpath", effective, "in LCA on client page");
		
		//Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'99')]/../following-sibling::td[contains(text(),'"+jobTitle+"')]/following-sibling::td[contains(text(),'"+LCAcity+"')]/following-sibling::td[contains(text(),'"+caseDescription+"')]/following-sibling::td/span[contains(text(),'"+validity+"')]/../following-sibling::td", "xpath", "LCA details", "in client");
	}
	
	
	public void verifyDefaultCustomData(String field, String data)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='DefaultCustData']//td[contains(text(),'"+field+"')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, field);
	}
	
	public void clickFamilyIcon()
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 21 Feb 2020
		 */
		Utils.clickElement(driver, img_family, "family image");
	}
	
	public void clickOnRelativeName(String relativeName)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 21 Feb 2020
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+relativeName+"')]", "xpath", "relative name");
	}
	
	public void clickPassportAndVisasInRelative()
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 29 July 2021
		 * Added sync for the Tabs to load completely
		 */
		Utils.waitForAjax(driver);
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.clickElement(driver, tab_PassportAndVisasInRelative, "passport and visa tab in relative");
	}
	
	public void verifyVisaInRelative(String country, String visaType, String validFrom, String validTo)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 29 July 2021
		 * Added sync for the Tabs to load completely
		 */
		Utils.waitForAjax(driver);
		Utils.verifyIfDataPresent(driver, "//div[@id='VisaList']//span[contains(text(),'"+country+"')]", "xpath", country, "country");
		Utils.verifyIfDataPresent(driver, "//div[@id='VisaList']//td[contains(text(),'"+visaType+"')]", "xpath", visaType, "visa type");
		Utils.verifyIfDataPresent(driver, "//div[@id='VisaList']//td[contains(text(),'"+validFrom+"')]", "xpath", validFrom, "valid from");
		Utils.verifyIfDataPresent(driver, "//div[@id='VisaList']//td[contains(text(),'"+validTo+"')]", "xpath", validTo, "valid to");
		Utils.verifyIfDataPresent(driver, "//div[@id='VisaList']//span[contains(text(),'"+country+"')]/../../following-sibling::td[contains(text(),'"+visaType+"')]/following-sibling::td[contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]/following-sibling::td[contains(text(),'Valid Visa')]", "xpath", "visa details", "in relative page");
	}
	
	
	public void verifyPassportInRelative(String country, String passportNumber, String validFrom, String validTo)
	{
		/*
		 * Created By : Likitha Krishna
		 * Created On : 24 Feb 2020
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//span[contains(text(),'"+country+"')]", "xpath", country, "country");
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//a[contains(text(),'"+passportNumber+"')]", "xpath", passportNumber, "passport Number");
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//td[contains(text(),'"+validFrom+"')]", "xpath", validFrom, "valid from");
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//td[contains(text(),'"+validTo+"')]", "xpath", validTo, "valid to");
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//td[contains(text(),'"+validTo+"')]/../td[contains(text(),'Valid')]", "xpath", "Valid", "passport status");
		Utils.verifyIfDataPresent(driver, "//div[@id='PassportList']//span[contains(text(),'"+country+"')]/../../following-sibling::td/a[contains(text(),'"+passportNumber+"')]/../following-sibling::td[contains(text(),'"+validFrom+"')]/following-sibling::td[contains(text(),'"+validTo+"')]/following-sibling::td[contains(text(),'Valid')]", "xpath", "passport details", "in relative page");
	}
	
	
	public void verifyIfContentEmailPresent(String sendMessage, String sendSubject)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Feb 2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Received Emails tab");

		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Received Email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email Header");
		
		Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Emails");
	}


	public void verifyIfEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 14/02/2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//font[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
	}


	public void verifyIfEmailNotPresent(String emailSubjectWithoutAccess)
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 14/02/2020
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//a[contains(text(), '" + emailSubjectWithoutAccess + "')]", waitElement_Pagination, "xpath", emailSubjectWithoutAccess, "Received emails list");
	}


	public void verifyIfSentEmailPresentWithAttachment(String emailMessage, String clientContentEmailSubject, String fileName)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 06/07/2021
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email ID");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Sent email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + emailMessage + "')]", "xpath", emailMessage, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Attachments");
	}
	
	
	public void verifyIfSentEmailPresent(String sendMessage, String sendSubject)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 06/07/2021
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + sendSubject + "')]", "xpath", "Sent email");
		
//		Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + sendSubject + "')]", "xpath", sendSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + sendMessage + "')]", "xpath", sendMessage, "Email popup");
	} 
	
	
	 public void verifyVisaDetailsInImportantDocumentsAndDate(String country, String visaType, String validFrom, String validTo)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 14 Jan 2019
		 */
		Utils.verifyIfDataPresent(driver, "//div[@id='ImmigrationListDetails']//div[contains(text(),'"+country+"')]", "xpath", country, "country");
		String visaTitile = "Visa (" + visaType + ")" ;
		String validity1 = "From " + validFrom;
		String validity2 = "To " + validTo;
		Utils.verifyIfDataPresent(driver, "//div[@id='ImmigrationListDetails']//div[contains(text(),'" + visaTitile + "')]/following-sibling::div[contains(text(),'" + validity1 + "')]", "xpath", "Visa details", "in Important Documents And Date");
		Utils.verifyIfDataPresent(driver, "//div[@id='ImmigrationListDetails']//div[contains(text(),'" + visaTitile + "')]/following-sibling::div[contains(text(),'" + validity2 + "')]", "xpath", "Visa details", "in Important Documents And Date");

	 }
	 
	 public void verifyPrimaryFirmContact(String primaryContact)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Jan 2020
		 */
		 Utils.verifyIfDataPresent(driver, "//div[@id='FirmContactsList']//h2[contains(text(),'"+primaryContact+"')]", "xpath", primaryContact, "primary contact");
	 }
	 
	 public void clickFollowButton() throws InterruptedException
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		// WebElement btn_follow = driver.findElement(By.xpath("//a[@id='follow']"));
		 Thread.sleep(3000);
		 if(driver.findElement(By.xpath("//a[@id='follow']")).isDisplayed())
			 Utils.clickDynamicElement(driver, "//a[@id='follow']", "xpath", "follow button");
		 else
			 Log.message("Already following");
	 }
	 
	 
	 public void clickJobTab()
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		Utils.clickElement(driver, tab_Job, "job tabs");
	 }
	 
	 public void verifyEmployment(String employeeId, String department, String Designation, String annualSalary, String currencyType, String responsibilities, String hireDate, String rehireDate, String terminationDate)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		annualSalary = annualSalary + " (" + currencyType + ")" ; 
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Employee Id')]/following-sibling::td[contains(text(),'"+employeeId+"')]", "xpath", employeeId, "employee id");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Hire Date')]/following-sibling::td[contains(text(),'"+hireDate+"')]", "xpath", hireDate, "hire date");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Designation')]/following-sibling::td[contains(text(),'"+Designation+"')]", "xpath", Designation, "designation");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Annual Salary')]/following-sibling::td[contains(text(),'"+annualSalary+"')]", "xpath", annualSalary, "annual salary");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Department')]/following-sibling::td[contains(text(),'"+department+"')]", "xpath", department, "department");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Responsibilities')]/following-sibling::td[contains(text(),'"+responsibilities+"')]", "xpath", responsibilities, "responsiblities");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Rehire Date')]/following-sibling::td[contains(text(),'"+rehireDate+"')]", "xpath", rehireDate, "rehire date");
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Termination Date')]/following-sibling::td[contains(text(),'"+terminationDate+"')]", "xpath", terminationDate, "termination date");
	 }
	 
	 public void verifyExpiratingIn6Months(String templateName)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 26 Feb 2020
		 */
		 Utils.verifyIfDataPresent(driver, "//div[@id='ImmigrationRightMenuListDetails']//div[contains(text(),'"+templateName+"')]", "xpath", templateName, "expiration template");
	 }
	

	 public void  verifyPersonalDetails(String gender, String placeOfBirth, String country, String citizenship, String dob)
	 {
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 8 July 2021
		 * Removed methods to verify //country --> country of birth // citizenship --> nationality // countryName --> country of nationality
		 */
		 
		 Utils.verifyIfDataPresent(driver, "//div[contains(text(),'Gender')]/following-sibling::div[contains(text(),'"+gender+"')]", "xpath", gender, "gender");
		
	 }
	 
	 
	 public void  verifyRelative(String relativeName, String relationship)
	 {
		/*
		 * Created By : Likitha Krishna
		 * Created On : 28 Feb 2020
		 */
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+relativeName+"')]", "xpath", relativeName, "relative name");
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(),'Re')]/../following-sibling::span[contains(text(),'"+relationship+"')]", "xpath", relationship, "relationship");
	 }
	 
	 
 	public void verifyResidenceAddress(String residenceCity, String countryForPersonalInfo, String apartment, String street1, String street2, String pinCode, String stateForPersonalInfo, String mobile, String telephone)
	{
		 /*
         * Created By : Saksham Kapoor
         * Created On : 15 Feb 2020
         */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(),'" + apartment + "\u00A0" + street1 + " " + street2 + "\u00A0" + residenceCity + "\u00A0" + stateForPersonalInfo + "\u00A0" + pinCode + "\u00A0" + countryForPersonalInfo + "')]", "xpath", "Residence/Work Address", "Residence/Work Address");
	
		Utils.verifyIfDataPresent(driver, "//i[@class='fa fa-phone']/parent::div[contains(text()[2], '" + telephone + "')]", "xpath", telephone, "Telephone");
		
		Utils.verifyIfDataPresent(driver, "//i[@class='fa fa-mobile']/parent::div[contains(text()[4], '" + mobile + "')]", "xpath", mobile, "Mobile");
	}
 	
	
	public void replyToEmail(String emailMessage, String clientContentEmailSubject) throws InterruptedException
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 27/02/2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Received email");
		
		Utils.clickElement(driver, btn_reply, "Reply");
		
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		
		Utils.enterText(driver, txtbox_Message, emailMessage, "Message Box");
		
		driver.switchTo().defaultContent();
		
		Utils.clickElement(driver, btn_sendEmail, "Send Email");
		
		driver.navigate().refresh();
		
		Thread.sleep(3000);
		
	}
	
	
	public void verifyIfRepliedEmailPresent(String replyMessage, String clientContentEmailSubject)
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 06/07/2021
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", clientContentEmailSubject, "Subject");
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + clientContentEmailSubject + "')]", "xpath", "Received Email");
		
	//	Utils.verifyIfDataPresent(driver, "//div[@style='display: block;']//h4[contains(text(), '" + caseContentEmailSubject + "')]", "xpath", caseContentEmailSubject, "Email popup");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + replyMessage + "')]", "xpath", replyMessage, "Email popup");

		
	}
	
	
		public void verifyCustomDataClientProfile(boolean value)
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 14 Feb 2019
		 * 
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "a[href='#tab-customdata']", "css", "Custom Data", "client under HRP Home Page");
		}
		
		else
		{
			Utils.verifyIfDataNotPresent(driver, "a[href='#tab-customdata']", waitElement_profile,"css", "Custom Data", "client under HRP Home Page");
		}
	}
	
	
	public void verifyEmailClientProfile(boolean value)
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 14 Feb 2019
		 * 
		 */
		if(value)
        {
              if(!Utils.isElementPresent(driver, "//div[@id='tab-emails']//div[@class='norecords-text']", "xpath"))
              {
                    Log.pass("Verified. The Email functionality is enabled  under client in HRP Login");
              }
              
              else
              {
                    Log.fail("Failed to Verify. The Email functionality is not enabled  under client in HRP Login");
              }
        }
        
        else
        {
              if(Utils.isElementPresent(driver, "//div[@id='tab-emails']//div[@class='norecords-text']", "xpath"))
              {
                    Log.pass("Verified. The Email functionality is not enabled  under client in HRP Login");
              }
              
              else
              {
                    Log.fail("Failed to Verify. The Email functionality is enabled  under client in HRP Login");
              }
              
         }
    }
	
	
	public void verifyEmailCaseProfile(boolean value)
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 14 Feb 2019
		 * 
		 */
		if(value)
        {
              if(!Utils.isElementPresent(driver, "//div[@id='tab-emails']//div[@class='norecords-text']", "xpath"))
              {
                    Log.pass("Verified. The Email functionality is enabled  under case in HRP Login");
              }
              
              else
              {
                    Log.fail("Failed to Verify. The Email functionality is not enabled  under case in HRP Login");
              }
        }
        
        else
        {
              if(Utils.isElementPresent(driver, "//div[@id='tab-emails']//div[@class='norecords-text']", "xpath"))
              {
                    Log.pass("Verified. The Email functionality is not enabled  under case in HRP Login");
              }
              
              else
              {
                    Log.fail("Failed to Verify. The Email functionality is enabled  under case in HRP Login");
              }
              
         }
    }
	
	
	public void verifyReceiptDetailsCaseProfile(boolean value)
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 14 Feb 2019
		 * 
		 */
		
		Utils.waitForAjax(driver);
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", "xpath", "Receipt Details", "case under HRP Home Page");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Additional Application/ Receipt Details')]", waitElement_profile, "xpath", "Receipt Details", "case under HRP Home Page");
		}
	}
	
	
	public void verifyCustomDataCaseProfile(boolean value)
	{
		/*
		 * Created By : Nitisha Sinha
		 * Created On : 14 Feb 2019
		 * 
		 */
		if(value)
		{
			Utils.waitForElementPresent(driver, "//h2[contains(text(),'Custom Data')]", "xpath");
			Utils.verifyIfDataPresent(driver, "//h2[contains(text(),'Custom Data')]", "xpath", "Custom Data", "case under HRP Home Page");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//h2[contains(text(),'Custom Data')]", waitElement_profile, "xpath", "Custom Data", "case under HRP Home Page");
		}
	}
	
	
	public void verifyLCATab(boolean value)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 28/01/2020
		 */
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//li/a[contains(text(),'LCA')]", "xpath", "LCA Tab","Corporation Page in HRP Login");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//li/a[contains(text(),'LCA')]", waitElement_HRPTabs , "xpath","LCA Tab","Corporation Page in HRP Login");
		}
	}
	
	
    public void verifyEstimatedDate(boolean value)
    {
    	/*
    	  Created by Yatharth pandya on 18/2/2020
    	 */
    	
    	if(value)
    	{
    		Utils.verifyIfDataPresent(driver, "//th[@data-field='EstReminderDate']/a", "xpath", "Estimated date", "under Case Steps tab in HRP Portal");
    	}
    	else
    	{
    		Utils.verifyIfDataNotPresent(driver, "//th[@data-field='EstReminderDate']/a", tab_caseSteps, "xpath", "Estimated date", "under Case Steps tab in HRP Portal");	
    	}
    	
    }
    
    
    public void verifyCompletedDate(boolean value)
    {
    	/*
    	  Created by Yatharth pandya on 18/2/2020
    	 */
    	
    	if(value)
    	{
    		Utils.verifyIfDataPresent(driver, "//th[@data-field='CompletedDate']/a", "xpath", "Completed date", "under Case Steps tab in HRP Portal");
    	}
    	else
    	{
    		Utils.verifyIfDataNotPresent(driver, "//th[@data-field='CompletedDate']/a", tab_caseSteps, "xpath", "Completed date", "under Case Steps tab in HRP Portal");	
    	}
    	
    }
    
    
	public void clickMyClientTab()
	{
		/*
		 *  Created By: Kuchinad Shashank
		 *  Created On: 02/03/2020
		 */
		
		Utils.clickElement(driver, tab_Myclient, "MY CLient Tab");
	
	}
	
	
	public void verifyAllClientWithHQAccess()
	{
		/*
		 *  Created By: Kuchinad Shashank
		 *  Created On: 02/03/2020
		 */
		
		List <String> corpUsers = globalVariables.corporationBranchesOfHQ;
		List<String> clientList = new ArrayList<>();
		
		System.out.println(corpUsers.size());
		
		for(int k = 0 ; k < corpUsers.size(); k++)
		{
			System.out.println(globalVariables.corpToClientMap.get(corpUsers.get(k)));
			clientList.addAll(globalVariables.corpToClientMap.get(corpUsers.get(k)));
		}
		
		int i=0;
		
		List<WebElement> noOfPages = new ArrayList<WebElement>();
		noOfPages = driver.findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']//a[@class='k-link']"));
		
		List<WebElement> clientName = new ArrayList<WebElement>();
		
		List<String> clientFirstName = new ArrayList<>();
		
		do
		{
			if(noOfPages.size() > 0 && i != 0)
			{
				noOfPages.get(i-1).click();
				i++;
			}
			
			Utils.waitForElementPresent(driver, "//td/a", "xpath");
			clientName= driver.findElements(By.xpath("//td/a"));
			
			for(int j = 0; j < clientName.size(); j=j+2 )
			{
				String text = clientName.get(j).getText();
				clientFirstName.add(text);
			}
			
			i++;
		}while(i < noOfPages.size()+1);
		
		
		System.out.println(clientList);
		
		
		if(clientFirstName.containsAll(clientList))
		{
			Log.pass("All CLients are present in Clients Tab", driver, true);
		}
		else
		{
			Log.fail("Not all Clients are present in the CLient Tab", driver, true);
		}
	}
	
	
	public void verifyAllClientWithNoHQAccess()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 3/03/2020
		 */
		
		int i=0;
		
		List<WebElement> noOfPages = new ArrayList<WebElement>();
		
		noOfPages = driver.findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']//a[@class='k-link']"));
		
		List<WebElement> clientName = new ArrayList<WebElement>();
		
		List<String> clientFirstName = new ArrayList<>();
		
		do
		{
			if(noOfPages.size() > 0 && i != 0)
			{
				noOfPages.get(i-1).click();
				i++;
			}
			clientName= driver.findElements(By.xpath("//td/a"));
			
			for(int j = 0; j < clientName.size(); j=j+2 )
			{
				String text = clientName.get(j).getText();
				System.out.println(text);
				clientFirstName.add(text);
			}
			
			i++;
		}while(i < noOfPages.size()+1);
		
		
			//String corpName = driver.findElement(By.xpath("//i[@class='fa fa-building']/following-sibling::span/text()")).getText();
			String text = driver.findElement(By.xpath("//i[@class='fa fa-building']/following-sibling::span")).getText();
			String[] name = text.split("\n");
			List<String> allClients = new ArrayList<>();

			
			allClients = globalVariables.corpToClientMap.get(name[0]);
			System.out.println(allClients);
			if(allClients.containsAll(clientFirstName))
			{
				Log.pass("All CLients are present in All Clients Tab", driver, true);
			}
			else
			{
				Log.fail("Not all Clients are present in the ALl Client Tab", driver, true);
			}
	}
	
	
	
	
	public void verifyMyClient()
	{
		/*
		 *  Created By: Kuchinad Shashank
		 *  Created On: 02/03/2020
		 */
		
		String corpUserName = driver.findElement(By.xpath("//li[@class='dropdown profile-dropdown']//span")).getText();
		
		Collection<String> value = globalVariables.corpUserToClientMap.get(corpUserName);
		
		List<WebElement> clientName = new ArrayList<WebElement>();
		clientName= driver.findElements(By.xpath("//td/a"));
		
		List<String> clientFirstName = new ArrayList<>();	
		
		for(int j = 0; j < clientName.size(); j=j+2 )
		{
			clientFirstName.add(clientName.get(j).getText());
		}
		
		System.out.println(value);
		System.out.println(clientFirstName);
		
		if(value.containsAll(clientFirstName))
		{
			Log.pass("My CLients are present in MyClients Tab", driver, true);
		}
		else
		{
			Log.fail("Not all My Clients are present in the My Client Tab", driver, true);
		}	
	}

    
    public void verifyNegativeClientSearch()
    {
    	/*
  	  	Created by Nitisha Sinha on 04/03/2020
    	 */
    	if(Utils.isElementPresent(driver, "//div[@id='BeneficiaryDetails']//div[contains(text(),'No Client Found')]", "xpath"))
    	{
    		Log.pass("Successfully verified the negative Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access");
    	}
    	else
    	{
    		Log.fail("Failed to verify the negative Functionality of Client Search under Corp User Login with All Corps and Specific Clients Access");
    	}
    }
    
    public void verifyCrimeInfo(String data)
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On : 12 July 2021
		 */
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Crime Info')]/following-sibling::td[contains(text(),'"+data+"')]", "xpath", data, "crime info field");
	}

}