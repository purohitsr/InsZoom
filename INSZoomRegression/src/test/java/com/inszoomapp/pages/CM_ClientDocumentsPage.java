package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ClientDocumentsPage extends LoadableComponent<CM_ClientDocumentsPage> 
{
	
	private final WebDriver driver;
	
	@FindBy(xpath = "//li[contains(@id,'applicationAccess_multiEntitiesAccess')]") //Added by Sharon Mathew on 8-Sept-2021
	WebElement btn_ClientAccess;
	
	@FindBy(id = "SaveButtonNew") //Added by Sharon Mathew on 8-Sept-2021
	WebElement btn_confirmDel;
	
	@FindBy(xpath = "//*[@id='7_mainActionDropdownUlId']") //Added by Sharon Mathew on 8-Sept-2021
	WebElement btn_deleteOption;
	
	@FindBy(xpath = "//*[@id='actionMenu_mainActionDropdownUlId']") //Added by Sharon Mathew on 8-Sept-2021
	WebElement btn_drpdwn;
	
	@FindBy(xpath = "//div[@class='mainLoader']")
	WebElement loader_ExpeditingPageLoad;
	
	@FindBy(xpath="//div[@class='image-zoom-icons d_inline_block upload_grid_options fn_list']/ul/li/a/span/button")
	WebElement btn_docAccessRightsDrpdwn; // Added by Sharon Mathew on 31st Aug 2021
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Email')]")
	WebElement btn_email;
	
	@FindBy(xpath = "//b[contains(text(), 'Uploaded By')]")
	WebElement heading_filter;
	
	@FindBy(xpath = "//span[contains(text(), 'Select filter') or contains(text(), 'filter(s) Selected')]")
	WebElement btn_filters;
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Delete')]")
	WebElement btn_delete;
	
	@FindBy(css = "li[id='applicationAccess_multiEntitiesAccess'] > a > label")
	WebElement option_client;
	
	@FindBy(css = "li[id='corporationAccess_multiEntitiesAccess'] > a > label")
	WebElement option_corporation;
	
	@FindBy(css = "li[id='vendorAccess_multiEntitiesAccess'] > a > label")
	WebElement option_vendor;
	
	@FindBy(css = "i[class='fa fa-key fa-rotate-180']")
	WebElement btn_accessRights;
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Accept')]")
	WebElement btn_accept;
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Pending Review')]")
	WebElement btn_pendingForReview;
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Need Clarification')]")
	WebElement btn_needsClarification;
	
	@FindBy(xpath = "//label[contains(text(), 'Download')]")
	WebElement btn_download;
	
	@FindBy(xpath = "//a[contains(text(), 'Document')]//span[@class='k-icon k-i-sort-asc-sm']")
	WebElement icon_ascending;
	
	@FindBy(xpath = "//a[contains(text(), 'Document')]//span[@class='k-icon k-i-sort-desc-sm']")
	WebElement icon_descending;
	
	@FindBy(xpath = "//div[@id='BnfDocumentNativeGrid']//a[contains(text(), 'Document')]")
	WebElement header_document;
	
	@FindBy(xpath = "//label[contains(text(), 'Tags/Folders')]/..//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']")
	WebElement btn_addTagsFolders;
	
	@FindBy(css = "div[id='docNameFormGroup']>input")
	WebElement textbox_name;
	
	@FindBy(xpath = "//textarea[contains(@id,'documentDescription')]")
	WebElement textbox_description;
	
	@FindBy(xpath = "//label[contains(@for, 'vendor')]")
	WebElement checkbox_vendor;
	
	@FindBy(xpath = "//button[contains(text(), 'Save')]")
	WebElement btn_save;
	
	@FindBy(css = "div[class='dropdown open']")
	WebElement dropdown_status;
	
	@FindBy(css = "button[id='menu1']")
	WebElement btn_status;
	
	@FindBy(xpath = "//a[contains(text(), 'Document Details')]")
	WebElement tab_documentDetails;
	
	@FindBy(xpath = "//span[@class='dropdown-content text-left show']//input")
	WebElement searchbox_country;
	
	@FindBy(css = "div[class='dropdown-content show'] input[placeholder='Search..']")
	WebElement searchbox_tagFolder;
	
	@FindBy(css="label[for='selectAllDocumentsCheckBox']")
	WebElement checkbox_selectAll;
	
	@FindBy(id="mainActionDropdownUlId")
	WebElement btn_mainActions;
	
	@FindBy(xpath="//a[contains(text(),'Upload file')]")	
	WebElement btn_UploadFile;
	
	@FindBy(id="fileUploadInput")
	WebElement btn_FileUploadInput;
	
	@FindBy(xpath="//button[contains(text(), 'Continue')]")
	WebElement btn_continue;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_ClientDocumentsPage(WebDriver driver) 
	{
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	
	public void uploadDocument(String filePath)
	{
		/*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		//Utils.clickElement(driver, btn_UploadFile, "Upload File");
		
		filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		File path = new File(filePath);	
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);
		
		try {
			Utils.waitForElement(driver, btn_UploadFile);
//			Log.message("Upload File Path is "+ path.getAbsoluteFile().toString());
			
			WebElement ele = driver.findElement(By.id("fileUploadInput"));
		    ((RemoteWebElement) ele).setFileDetector(detector);
		    
			ele.sendKeys(file.getAbsolutePath());
			//btn_FileUploadInput.sendKeys(path.getAbsoluteFile().toString());
			Log.message("Entered the absolute file path in the Upload Button");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}


		
		
		//Utils.enterText(driver, btn_FileUploadInput, path.getAbsoluteFile().toString(), "File upload button");
//		try
//		{
//			File path = new File(filePath);
//			StringSelection ss = new StringSelection(path.getAbsoluteFile().toString());
//			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//	
//			Robot robot = new Robot();
//			robot.delay(4000);
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.delay(4000);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//			Log.message("Successfully Uploaded the Document");
//	
//		} catch (Exception e) {
//			Log.message("", driver, true);
//			Log.fail("Unable to upload the document. ERROR :\n\n " + e.getMessage());
//		}
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
	}
	
	
	public void uploadDocumentTest(String filePath)
	{
		/*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		//Utils.clickElement(driver, btn_UploadFile, "Upload File");
		filePath = System.getProperty("user.dir")+filePath;
		System.out.println("File Path is "+filePath);
		String os = System.getProperty("os.name");
		if(os.contains("Linux")){
			filePath = filePath.replace("\\", "/");
		Log.message("Final File Path "+filePath);
		
		}
		
		File path = new File(filePath);		
		
		try {
//			Utils.waitForLoaderToAppearAndDissapear(driver, loader_ExpeditingPageLoad);
			Thread.sleep(10000);
			Utils.waitForElement(driver, btn_UploadFile);
			Log.message("Upload File Path is "+path.getAbsoluteFile().toString());
			LocalFileDetector detector = new LocalFileDetector();
			File file = detector.getLocalFile(filePath);
			WebElement ele = driver.findElement(By.id("fileUploadInput"));
		    ((RemoteWebElement) ele).setFileDetector(detector);
			ele.sendKeys(file.getAbsolutePath());
//			btn_FileUploadInput.sendKeys(path.getAbsoluteFile().toString());
			Log.message("Entered the absolute file path in the Upload Button");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
	}
	
	
	
	
	
	
	public void verifyIfDocumentUploaded(String fileName)
	{
		/*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName, "Uploaded Documents");
	
	}
	
	
	public void changeDocumentAccessRights(String fileName, String choice)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 13 Nov 2019
		  * Modified On : 24 Mar 2020
		  * 
		  */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		
		Utils.waitForElement(driver, caseDocChecklistPage.dropdown_DocumentAccessRights);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		
        if(choice.equals("client"))
        {
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'applicationAccess')]//label[contains(text(), 'Client')]", "xpath", "Client");		
			Utils.waitForElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'applicationAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");
			checkForAccessRights(fileName, "client");
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");

        }
        
        if(choice.equals("corporation"))
        {
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'corporationAccess')]//label[contains(text(), 'Corporation')]", "xpath", "Corporation");		
			Utils.waitForElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'corporationAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");
			checkForAccessRights(fileName, "corporation");
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");

        }
        
        if(choice.equals("vendor"))
        {
			/*Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'vendorAccess')]//label[contains(text(), 'Vendor')]", "xpath", "Vendor");	
			Utils.waitForElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'vendorAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");*/
			checkForAccessRights(fileName, "vendor");
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");

        }
        
	}
	
	
	public void checkBreadcrumbs()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Client List')]/../following-sibling::li/span[contains(text(), 'Client Profile')]/../following-sibling::li/span[contains(text(), 'Documents')]", "xpath", "CLIENT LIST > CLIENT PROFILE > Documents", "breadcrumbs");
	}
	
	
	public void checkCorporationAndClientNameInHeader(String corpName, String clientName)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 15 Sept 2021
		 * Edit: Changed the xpath		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientName + "')]", "xpath", clientName, "header");
	
		Utils.verifyIfDataPresent(driver, "//div[contains(@class, 'profile-title-header')]//a[contains(text(), '" + corpName + "')]", "xpath", corpName, "header");
	}
	
	
	public void changeStatus(String fileName, String status)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Mar 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName +"')]/ancestor::td/following-sibling::td//a[@id='actionMenu_mainActionDropdownUlId']", "xpath", "Quick actions for the uploaded document");
		
		if(status.equals("Accept"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Accept')]", "xpath", "Accept");
			
		if(status.equals("Pending Review"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Pending Review')]", "xpath", "Accept");
		
		if(status.equals("Need Clarification"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Need Clarification')]", "xpath", "Accept");
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void checkIfStatusChanged(String fileName, String status)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Mar 2020
		 * 
		 */
		
		if(status.equals("Accept"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Accept Document']", "xpath", "Accept image", "Documents Page");
			
		if(status.equals("Pending Review"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Pending review']", "xpath", "Pending review image", "Documents Page");
			
		if(status.equals("Need Clarification"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Need Clarification']", "xpath", "Need Clarification image", "Documents Page");
		
	}
	
	
	public void verifyIfDownloaded(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDownloaded(remoteDriver);
	}
	
	
	public void downloadSingleDocument(String corpFile1Name, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + corpFile1Name + "')]/ancestor::td/following-sibling::td//img[@alt='Download']", "xpath", "Download icon");
	
		Utils.waitForElement(driver, "//span[contains(text(), 'Preparing Download')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Preparing Download')]", "xpath");

		Thread.sleep(5000);
	}
	
	
	public void deleteSingleDocument(String deleteFileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + deleteFileName +"')]/ancestor::td/following-sibling::td//a[@id='actionMenu_mainActionDropdownUlId']", "xpath", "Quick actions for the uploaded document");
		
		Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Delete')]", "xpath", "Delete");
		
		Utils.clickElement(driver, btn_continue, "Continue");
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void verifyIfDeleted(String deleteFileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), '" + deleteFileName +"')]", btn_UploadFile, "xpath", deleteFileName, "Documents");
	}
	
	
	public void verifyIfOptionsAvailableInQuickActions(String clientFile1Name)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), " + clientFile1Name +")]/ancestor::td/following-sibling::td//a[@id='actionMenu_mainActionDropdownUlId']", "xpath", "Quick actions for the uploaded document");

		Utils.waitForElement(driver, "//li[@class='dropdown options-icon-list open']//label", globalVariables.elementWaitTime, "xpath");
		List<WebElement> webElementsOfOptions = driver.findElements(By.xpath("//li[@class='dropdown options-icon-list open']//label"));
		
		List<String> listOfOptions = new ArrayList<>();
		
		for(int i = 0; i < webElementsOfOptions.size(); i++)
		{
			listOfOptions.add(webElementsOfOptions.get(i).getText());
		}
		
		if(listOfOptions.containsAll(globalVariables.listOfQuickActionsClient) && globalVariables.listOfQuickActionsClient.containsAll(listOfOptions))
		{
			Log.pass("", driver, true);
			Log.pass("All the required Actions/Options are present in the Document Quick Actions dropdown");
		}
		
		else
		{
			Log.fail("", driver, true);
			Log.fail("The expected options are: " + globalVariables.listOfQuickActions + " but the actual options are: " + listOfOptions);
		}
	}
	
	
	public void verifyIfOptionsDisbaled()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 17 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Accept')]", "xpath", "disbaled 'Accept'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Pending Review')]", "xpath", "disbaled 'Pending Review'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Need Clarification')]", "xpath", "disbaled 'Need Clarification'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Download')]", "xpath", "disbaled 'Download'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Print / All')]", "xpath", "disbaled 'Print / All'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Delete')]", "xpath", "disbaled 'Delete'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Activity')]", "xpath", "enabled 'Activity'", "Main Actions dropdown");

	}

	
	public void clickSelectAll()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, checkbox_selectAll, "'Select All' checkbox");
	}
	
	
	public void verifyIfOptionsEnabled()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Accept')]", "xpath", "enabled 'Accept'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Pending Review')]", "xpath", "enabled 'Pending Review'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Need Clarification')]", "xpath", "enabled 'Need Clarification'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Download')]", "xpath", "enabled 'Download'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Print / All')]", "xpath", "enabled 'Print / All'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Delete')]", "xpath", "enabled 'Delete'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Activity')]", "xpath", "enabled 'Activity'", "Main Actions dropdown");

	}
	
	
	public void addPassport(String clientFile1Name, String country)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 2 July 2021
		 * 
		 */
		
		Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + clientFile1Name + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'" + clientFile1Name + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
		
		Utils.enterText(driver, searchbox_tagFolder, "Passport", "Passport");
		
		Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = 'Passport']", "xpath", "Passport");
		
		Utils.enterText(driver, searchbox_country, country, country);
		
		Utils.clickDynamicElement(driver, "//span[@class='dropdown-content text-left show']//label[text()='" + country + "']", "xpath", country);
		
	}
	
	
	public void verifyIfCountryAdded(String clientFile1Name, String country)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + clientFile1Name + "')]/ancestor::td/following-sibling::td//img[contains(@src, '" + country + "') and @class='hasPopover selectedFlag']", "xpath", country + " flag", "Passport Tag/Folder");
	}
	
	
	public void checkIfHeadingsPresent()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 18 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[@id='BnfDocumentNativeGrid']//a[contains(text(), 'Document')]/../following-sibling::th[contains(text(), 'Tags/Folders')]/following-sibling::th/a[contains(text(), 'Uploaded')]/../following-sibling::th[contains(text(), 'Access')]", "xpath", "Table headings", "Documents Page");
	}
	
	
	public void removeTag(String document)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 Mar 2020
		 * 
		 */
		
		List<WebElement> removeTag = driver.findElements(By.xpath("//span[contains(text(),'" + document + "')]/ancestor::td/following-sibling::td//i[@class='fa fa-times pointer']"));
		
		for(int i = 0; i < removeTag.size(); i ++)
			Utils.clickElement(driver, removeTag.get(i), "'x' button");
	}
	
	
	public void verifyIfTagRemoved(String document)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + document + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder')]", "xpath", "Add Tag/Folder", "Documents Page");
	}
	
	
	public void previewDocument(String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 24 Mar 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName);
	}
	
	
	public void changeStatusFromPreview(String status)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 24 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details tab");
		
		Utils.clickElement(driver, btn_status, "Status");
		
		Utils.waitForElement(driver, dropdown_status);
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + status + "')]", "xpath", status);
	
		clickSaveButton();
	}
	
	public void verifyAccessBefore(String fileName)
	{
		/*
		 * Created By : Souvik Ganguly
		 * Created On : 28 June 2021
		 * This method is to check if the Vendor access is already present before removing the access
		 */
		
		Utils.scrollToDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName);
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'phnid4AAAAABJRU5ErkJggg==')]", "xpath", "Vendor image", "Access Rights");

	}
	
	
	public void changeAccessFromPreview(String fileName)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 28 June 2021
		 * 
		 */
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details tab");
		
		Utils.clickElement(driver, checkbox_vendor, "Vendor"); //Clicking on the checkbox will uncheck the Vendor Access
		
		clickSaveButton();
		
		Utils.waitForElementNotVisible(driver, "//button[contains(text(), 'Save')]", "xpath");
				
		//Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'phnid4AAAAABJRU5ErkJggg==')]", "xpath", "Vendor image", "Access Rights");		
		
		WebElement txt_Filename = driver.findElement(By.xpath("//span[contains(text(), '" + fileName + "')]"));
		
		Utils.scrollToDynamicElement(driver, "//span[contains(text(), '" + fileName + "')]", "xpath", fileName);
		
		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'phnid4AAAAABJRU5ErkJggg==')]", txt_Filename, "xpath", "Vendor image", "Access Rights");
		
		

	}
	
	
	public void addDescriptionFromPreview(String fileName, String description)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 24 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details tab");
		
		Utils.enterText(driver, textbox_description, description, "Description");
		
		clickSaveButton();
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/../following-sibling::p[contains(text(), '" + description + "')]", "xpath", description, "Document description");
	}


	public void renameDocument(String fileName, String fileNameWithoutExtension, String workbookNameWrite, String sheetName) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 26 Mar 2020
		 * 
		 */
		
		String newNameWithoutExtension = fileNameWithoutExtension + RandomStringUtils.randomAlphanumeric(3).toString();
		String[] temp = fileName.split("[.]");
		String newName = newNameWithoutExtension + "." + temp[1];
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details tab");
		
		Utils.enterText(driver, textbox_name, newNameWithoutExtension, "File Name");
		
		clickSaveButton();
		
		verifyIfDocumentUploaded(newName);
		
		String[] dataToWrite = {newName, newNameWithoutExtension};
        String[] columnInExcel = {"clientFile1Name", "clientFile1NameWithoutExtension"};
		
		Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel);
	}
	
	public void renameClientDocument(String fileName, String fileNameWithoutExtension, String workbookNameWrite, String sheetName) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 26 Mar 2020
		 * 
		 */
		
		String newNameWithoutExtension = fileNameWithoutExtension + RandomStringUtils.randomAlphanumeric(3).toString();
		String[] temp = fileName.split("[.]");
		String newName = newNameWithoutExtension + "." + temp[1];
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details tab");
		
		Utils.enterText(driver, textbox_name, newNameWithoutExtension, "File Name");
		
		clickSaveButton();
		
		verifyIfDocumentUploaded(newName);
		
		String[] dataToWrite = {newName, newNameWithoutExtension};
		String[] columnInExcel_renamed = {"clientFile1NameRenamed", "clientFile1NameRenamedWithoutExtension"};
		Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel_renamed);
	}
	
	
	public void addTag(String fileName, List<String> listOfTags)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 2 July 2021
		 * 
		 */
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + fileName + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.clickDynamicElement(driver, "//span[contains(text(),'" + fileName + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.enterText(driver, searchbox_tagFolder, listOfTags.get(i), "tag/folder searchbox");
			
			Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = '" + listOfTags.get(i) + "']", "xpath", listOfTags.get(i));
		}
	}
	
	
	public void verifyIfAlertForTagsPresent()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 26 Mar 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), 'Limit 6 tags per document reached.') and @class='toast-alert-message warning show']", "xpath", "Limit 6 tags per document reached.", "Documents Page");
	}
	
	
	public void addTagFromPreview(List<String> listOfTags)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details");
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.clickElement(driver, btn_addTagsFolders, "Add Tags/Folders");
			
			Utils.enterText(driver, searchbox_tagFolder, listOfTags.get(i), "Tag/Folder searchbox");
			
			Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = '" + listOfTags.get(i) + "']", "xpath", listOfTags.get(i));
		}

	}
	
	
	public void verifyIfTagsAdded(String fileName, List<String> listOfTags)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//span[contains(text(), '" + listOfTags.get(i) + "')]", "xpath", listOfTags.get(i), "Added Tags/Folders");
		}
	}
	
	
	public void removeTagFromPreview()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_documentDetails, "Document Details");
		
		List<WebElement> removeTag = driver.findElements(By.xpath("//label[contains(text(), 'Tags/Folders')]/..//i[@class='fa fa-times pointer']"));
		
		for(int i = 0; i < removeTag.size(); i ++)
			Utils.clickElement(driver, removeTag.get(i), "'x' button");
		
		clickSaveButton();
	}
	
	
	public void clickSaveButton()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_save, "Save");
	}
	
	
	public void verifyAscendingOrderOfDocuments()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		ArrayList<String> docsList = new ArrayList<String>();
		
        Utils.waitForElement(driver, "//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        List<WebElement> allDocsList = driver.findElements(By.xpath("//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
              WebElement eachDocName = allDocsList.get(i);
              docsList.add(eachDocName.getText().trim().toString());
        }
        Collections.sort(docsList, String.CASE_INSENSITIVE_ORDER);

        Utils.clickElement(driver, header_document, "Document heading");
        Utils.waitForElement(driver, icon_ascending);
        
        ArrayList<String> sortedDocsListFromUI = new ArrayList<String>();
        
        Utils.waitForElement(driver, "//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        allDocsList = driver.findElements(By.xpath("//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
            WebElement eachDocName = allDocsList.get(i);
            sortedDocsListFromUI.add(eachDocName.getText().trim().toString());
        }
        
        if (docsList.equals(sortedDocsListFromUI)) 
        {
        	Log.message("", driver, true);
        	Log.pass("Ascending order working fine"); 
        }
        
        else
        {
        	Log.message("", driver, true);
        	Log.pass("Ascending order not working fine"); 
        }
	}
	
	
	public void verifyDescendingOrderOfDocuments()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		ArrayList<String> docsList = new ArrayList<String>();
		
        Utils.waitForElement(driver, "//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        List<WebElement> allDocsList = driver.findElements(By.xpath("//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
              WebElement eachDocName = allDocsList.get(i);
              docsList.add(eachDocName.getText().trim().toString());
        }
        Collections.sort(docsList, String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(docsList);
        
        Utils.clickElement(driver, header_document, "Document heading");
        Utils.waitForElement(driver, icon_descending);
        
        ArrayList<String> sortedDocsListFromUI = new ArrayList<String>();
        
        Utils.waitForElement(driver, "//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        allDocsList = driver.findElements(By.xpath("//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
            WebElement eachDocName = allDocsList.get(i);
            sortedDocsListFromUI.add(eachDocName.getText().trim().toString());
        }
        
        if (docsList.equals(sortedDocsListFromUI)) 
        {
        	Log.message("", driver, true);
        	Log.pass("Ascending order working fine"); 
        }
        
        else
        {
        	Log.message("", driver, true);
        	Log.pass("Ascending order not working fine"); 
        }
	}
	
	
	public void downloadAllDocuments(RemoteWebDriver driver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		Utils.openDownloadsWindow(driver);
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions");
		
		Utils.clickElement(driver, btn_download, "Download");
		
		Utils.waitForElement(driver, "//span[contains(text(), 'Preparing Download')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Preparing Download')]", "xpath");

		Thread.sleep(5000);
	}
	
	
	public void changeStatusForMultiple(String status)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 27 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions");
		
		if(status.equals("Accept"))
			Utils.clickElement(driver, btn_accept, "Accept");
		
		if(status.equals("Pending for Review"))
			Utils.clickElement(driver, btn_pendingForReview, "'Pending for Review'");
		
		if(status.equals("Needs Clarification"))
			Utils.clickElement(driver, btn_needsClarification, "'Needs Clarification'");
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void checkForAccessRights(String fileName, String option)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 31 Mar 2020
		 * 
		 */
		
		if(option.equals("corporation"))
        {
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'YABaP4Jm7KfgCAAAAAElFTkSuQmCC')]", "xpath", "Corporation image", "Access Rights");
        }
        
		if(option.equals("client"))
        {
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'gAB3QBZsX09ujAAAAAElFTkSuQmCC')]", "xpath", "Client image", "Access Rights");
        }
		
        if(option.equals("vendor"))
        {
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'N0Puphnid4AAAAABJRU5ErkJggg==')]", "xpath", "Vendor Image", "Access Rights");
        }
	}
	
	
	public void changeAccessRightForMultiple(String option)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 31 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_accessRights, "Access Rights icon");
		
		if(option.equals("vendor"))
			Utils.clickElement(driver, option_vendor, "Vendor");
		
		if(option.equals("corporation"))
			Utils.clickElement(driver, option_corporation, "Corporation");
		
		if(option.equals("client"))
			Utils.clickElement(driver, option_client, "Client");	
	}
	
	
	public void deleteMultiple()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 31 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions");
		
		Utils.clickElement(driver, btn_delete, "Delete");
		
		Utils.clickElement(driver, btn_continue, "Continue");
		
		CM_CaseDocChecklistPage caseDocChecklistPage = new CM_CaseDocChecklistPage(driver);
		Utils.waitForElement(driver, caseDocChecklistPage.txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
		Utils.verifyIfDataPresent(driver, "//p[contains(text(), 'Drag and drop files to upload or browse')]", "xpath", "'Drag and drop files to upload or browse'", "Documents Page");
	}
	
	
	public void clickFiltersButtton()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 02 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_filters, "'Select filter'");
	}
	
	
	public void checkIfFiltersNotPresent(String heading, List<String> listOfFilters)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 02 Apr 2020
		 * 
		 */
		
		for(int i = 0; i < listOfFilters.size(); i++)
		{
			Utils.verifyIfDataNotPresent(driver, "//b[contains(text(), '" + heading + "')]/ancestor::li/following-sibling::li[1]//label[contains(text(), '" + listOfFilters.get(i) + "')]", heading_filter, "xpath", listOfFilters.get(i), "Filters Dropdown");
		}
	}
	
	
	public void checkIfFiltersPresent(String heading, List<String> listOfFilters)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 02 Apr 2020
		 * 
		 */
		
		for(int i = 0; i < listOfFilters.size(); i++)
		{
			Utils.verifyIfDataPresent(driver, "//b[contains(text(), '" + heading + "')]/ancestor::li/following-sibling::li//label[contains(text(), '" + listOfFilters.get(i) + "')]", "xpath", listOfFilters.get(i), "Filters Dropdown");
		}
	}
	
	
	public void verifyDocumentsPage() 
	{
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 21 Feb 2020
		  */
	    try{
		 
	    	Utils.waitForAllWindowsToLoad(2, driver);
	    	Utils.switchWindows(driver, "Document - INSZoom.com", "title", "false");
	    	driver.close();
	    	Utils.waitForAllWindowsToLoad(1, driver);
	    	Utils.switchWindows(driver, "INSZoom.com - Questionnaires", "title", "false");
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Documents Page failed", driver);
	    }
	}
	
	
	public void selectFilter(String filter)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 June 2020
		 * 
		 */
		
		clickFiltersButtton();
		
		if(filter.equals("Corporation"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Corporation')]", "xpath", "Corpoartion filter");
		
		if(filter.equals("Case Manager"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Case Manager')]", "xpath", "Case Manager filter");
		
		if(filter.equals("Client"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Client')]", "xpath", "Client filter");
		
		if(filter.equals("Pending Review"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Pending Review')]", "xpath", "Pending Review filter");
		
		if(filter.equals("Accept"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Accepted')]", "xpath", "Accepted filter");
		
		if(filter.equals("Needs Clarification"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Need Clarification')]", "xpath", "Need Clarification filter");
		
		if(filter.equals("Passport"))
			Utils.clickDynamicElement(driver, "//div[@class='btn-group open']//label[contains(text(), 'Passport')]", "xpath", "Passport filter");
		
		clickFiltersButtton();
	}
	
	
	public void verifyIfFilteredDocumentPresent(String file1, String file2)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 June 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + file1 + "')]", "xpath", file1, "Uploaded Documents");

		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), '" + file2 + "')]", btn_UploadFile, "xpath", file2, "Filtered Document");
	}
	
	
	public void emailMultipleDocuments() throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 June 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_mainActions, "Main Actions");
		
		Utils.clickElement(driver, btn_email, "Email");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom - Advanced Email", "title", "false");
	}
	
	public void enableAccessRight(String filename,String option)
	{
		WebElement fileChbx=driver.findElement(By.xpath("//span[contains(text(),'"+filename+"')]/ancestor::tr/td/div[contains(@class,'fn_checkbox')]"));
		Utils.waitForElement(driver, fileChbx);
		Utils.waitForElementClickable(driver, fileChbx);
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+filename+"')]/ancestor::tr/td/div[contains(@class,'fn_checkbox')]", "xpath",filename);
		Utils.clickElement(driver, btn_docAccessRightsDrpdwn, "Access Rights");
		Utils.waitForElement(driver, btn_ClientAccess);
		try
		{
			if(option.equalsIgnoreCase("Client"))
			{
				
			boolean present=Utils.isElementPresent(driver, "//li[contains(@id,'applicationAccess_multiEntitiesAccess')]/a/span/i[contains(@class,'hiddenClass')]", "xpath");
			if(present)
			{
				Log.message("Client Access not enabled. Enabling now.");
				Utils.clickElement(driver, driver.findElement(By.xpath("//li[contains(@id,'applicationAccess_multiEntitiesAccess')]/a")),"Client Access");
			}
			else
				Log.message("Client Access already enabled.");
			}
			
			if(option.equalsIgnoreCase("Vendor"))
			{
				boolean present=Utils.isElementPresent(driver, "//li[contains(@id,'vendorAccess_multiEntitiesAccess')]/a/span/i[contains(@class,'hiddenClass')]", "xpath");
				
			if(present)
			{
				Log.message("Vendor Access not enabled. Enabling now.");
				Utils.clickElement(driver, driver.findElement(By.xpath("//li[contains(@id,'vendorAccess_multiEntitiesAccess')]/a")),"Vendor Access");
			}
			else
				Log.message("Vendor Access already enabled.");
			}
			
			if(option.equalsIgnoreCase("Corporation"))
			{
				boolean present=Utils.isElementPresent(driver, "//li[contains(@id,'corporationAccess_multiEntitiesAccess')]/a/span/i[contains(@class,'hiddenClass')]", "xpath");
				
			if(present)
			{
				Log.message("Corporation Access not enabled. Enabling now.");
				Utils.clickElement(driver, driver.findElement(By.xpath("//li[contains(@id,'corporationAccess_multiEntitiesAccess')]/a")),"Corporation Access");
			}
			else
				Log.message(" Corporation Access already enabled.");
			}
			
			if(option.equalsIgnoreCase("Firm"))
			{

				boolean present=Utils.isElementPresent(driver, "//li[contains(@id,'lawFirmAccess_multiEntitiesAccess')]/a/span/i[contains(@class,'hiddenClass')]", "xpath");
				
			if(present)
			{
				Log.message("Firm Access not enabled. Enabling now.");
				Utils.clickElement(driver, driver.findElement(By.xpath("//li[contains(@id,'lawFirmAccess_multiEntitiesAccess')]/a")),"Firm Access");
			}
			else
				Log.message("Firm Access already enabled.");
			}
			
		}
		catch(Exception e)
		{
			Log.fail("Enabling "+option+" Access rights has failed.", driver, true);
		}
	}

	public void switchToClientProfileTab(WebDriver driver)
	{
		/*
		 * Created By : Sharon Mathew
		 * Created On : 31 August 2021
		 * 
		 */
		
		String currentHandle= driver.getWindowHandle();
		Set <String> handles=driver.getWindowHandles();
		for(String actual: handles) {
		if(!actual.equalsIgnoreCase(currentHandle)) {
		//Switch to the opened tab
		driver.switchTo().window(actual);
		}
		}
	}
	
	public void deleteAllDocuments()
	{
		try
		{
			Utils.waitForElement(driver, btn_drpdwn);
			if(!(Utils.isElementPresent(driver, "//div[@class='drag_drop_wrap undefined-check']", "xpath")))
			{
			clickSelectAll();
			Utils.clickElement(driver,btn_drpdwn,"Action Menu");
			Utils.clickElement(driver,btn_deleteOption,"Delete Option");
			boolean present=Utils.isElementPresent(driver, "SaveButtonNew", "id");
			if(present)
				Utils.clickElement(driver, btn_confirmDel, "Confirm Deletion");
			}
		}
		catch(Exception e)
		{
			Log.fail(" ERROR - "  + e.getMessage(), driver, true);
		}
	}
	
}


