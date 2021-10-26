package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class CM_CaseDocChecklistPage extends LoadableComponent<CM_CaseDocChecklistPage> 
{
	private final WebDriver driver;
	
	@FindBy(xpath="//button[contains(text(), 'Continue') and @id='SaveButton']")
	WebElement btn_ContinueForDelete;
	
	@FindBy(xpath="//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Delete Checklist')]")
	WebElement dropDownOption_DeleteChecklist;
	
	@FindBy(xpath="//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Delete')]")
	WebElement dropDownOption_Delete;
	
	@FindBy(xpath="//img[contains(@src, '+A7WIAaMAAAAAElFTkSuQmCC')]")
	WebElement icon_previousDocuments;
	
	@FindBy(xpath="//h4[contains(text(),'Copy from Petition Template')]/../button")
	WebElement btn_closeButton;
	
	@FindBy(css = "i[class='fa fa-key fa-rotate-180']")
	WebElement dropdown_access;
	
	@FindBy(css = "div[class='dropdown open']")
	WebElement dropdown_status;
	
	@FindBy(css = "button[id='menu1']")
	WebElement btn_status;
	
	@FindBy(xpath = "//span[@class='dropdown-content text-left show']//input")
	WebElement searchbox_country;
	
	@FindBy(css = "div[class='dropdown-content show'] input[placeholder='Search..']")
	WebElement searchbox_tagFolder;
	
	@FindBy(xpath = "//input[@id='add_rqd_R']/following-sibling::label[contains(text(), 'Required')]")
	WebElement radioBtn_required;
	
	@FindBy(xpath= "//input[@id='add_rqd_O']/following-sibling::label[contains(text(), 'Optional')]")
	WebElement radioBtn_optional;
	
	@FindBy(xpath= "//input[@id='add_rqd_A']/following-sibling::label[contains(text(), 'If Applicable')]")
	WebElement radioBtn_ifApplicable;
	
	@FindBy(xpath = "//div[@id='addchecklist']//button[contains(text(), 'Save')]")
	WebElement btn_save;
	
	@FindBy(xpath = "//a[contains(text(), 'Document')]//span[@class='k-icon k-i-sort-asc-sm']")
	WebElement icon_ascending;
	
	@FindBy(xpath = "//a[contains(text(), 'Document')]//span[@class='k-icon k-i-sort-desc-sm']")
	WebElement icon_descending;
	
	@FindBy(xpath="//div[contains(@id,'chooseChecklistModalPopupB')]//a[@id='modalCloseBtn']")
	WebElement icon_ClosePopup;
	
	@FindBy(xpath="//input[contains(@id,'fileUploadInputB')]")
	WebElement btn_UploadFileInput;
	
	@FindBy(xpath="//button[contains(@id,'fileUploadButtonB')]")
	WebElement btn_UploadFile;
	
//	@FindBy(xpath="//button[contains(@id,'fileUploadButton')]")
//	WebElement btn_UploadFile;
	
	@FindBy(xpath="//button[contains(text(), 'Continue') and @id='SaveButton']")
	WebElement btn_ContinueDeleteDocChecklist;
	
	@FindBy(xpath="//button[contains(text(), 'Continue') and @id='SaveButtonNew']")
	WebElement btn_Continue;
	
	@FindBy(xpath="//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Delete Checklist Item')]")
	WebElement btn_DeleteChecklistItem;
	
	@FindBy(xpath="//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Delete')]")
	WebElement btn_DeleteDocument;
	
	@FindBy(xpath="//li[@class='dropdown options-icon-list open']")
	WebElement dropdown_DocumentActions;
	
	@FindBy(xpath="(//tbody[@id='petitionDocumentChecklists']//span)[1]")
	WebElement txt_DocChecklist;
	
	@FindBy(xpath="//tbody[@id='petitionDocumentChecklists']//span/../preceding-sibling::td[1]//label")
	WebElement checkbox_DocChecklist;
	
	@FindBy(xpath="//button[contains(text(),'Add to Checklist')]")
	WebElement btn_AddToChecklist;
	
	@FindBy(xpath="//a[contains(text(),'Copy from Template')]")
	WebElement btn_CopyFromTemplate;
	
	@FindBy(xpath="//button[contains(@id,'modalPopupSave')]")
	WebElement btn_SaveDocumentChanges;
	
	@FindBy(xpath="//label[contains(@for,'vendor')]")
	WebElement chckbox_Vendor;
	
	@FindBy(xpath="//div[@class='dropdown-content show']/input[@placeholder='Search..' and contains(@id, 'myInput') and @class='myInput']")
	WebElement searchbox_TagFolder;
	
	@FindBy(xpath="//div[@class='dropdown-content show' and @id='myDropdown2']")
	WebElement dropdown_AddTagFolder;
	
	@FindBy(xpath="//span[contains(text(), 'Add a tag/folder')]")
	WebElement btn_AddTagFolder;
	
	@FindBy(xpath="//a[contains(text(), 'Accepted')]")
	WebElement lnk_Accepted;
	
	@FindBy(id="menu1")
	WebElement dropdown_DocumentReview;
	
	@FindBy(css="div[class='dropdown open']")
	WebElement dropdown_DocumentReviewOpen;
	
	@FindBy(xpath="//textarea[contains(@id,'documentDescription')]")
	WebElement txtbox_FileDescription;
	
	@FindBy(xpath="//li[contains(@id,'docdetailstab')]")
	WebElement tab_DocumentDetails;
	
	@FindBy(xpath="//input[contains(@id,'fileName')]")
	WebElement txtbox_FileName;
	
	@FindBy(xpath="//div[@class='modal md-effect-1 md-show' and @id='previewModalPopUp']")
	WebElement popup_EditDocument;
	
	@FindBy(css="i[class='fa fa-caret-down']")
	WebElement icon_Actions;
	
	@FindBy(xpath="//label[@class='zoom-action-item-label micro pl13' and contains(text(), 'Download')]")
	WebElement btn_DownloadSelectedChecklists;
	
	@FindBy(css="label[for='selectAllDocumentChecklistsCheckBox']")
	WebElement chkbox_SelectAllChecklists;
	
	@FindBy(css="div[class='fn_tabs_wrap open']")
	WebElement dropdown_VerificationPreviousDocuments;
	
	@FindBy(css="a[id='opendoc']")
	WebElement icon_PreviousDocuments;
	
	@FindBy(css="div[class='fn_stages open']")
	public static WebElement dropdown_DocumentAccessRights;
	
	@FindBy(xpath = "//button[contains(text(), 'Save') and not (@id)]")
	WebElement btn_SaveEditedChecklist;
	
	@FindBy(css="div[class='toast-alert-message alert-success show']")
	public static WebElement txt_FileUploadMessageAppeared;
	
	@FindBy(css="div[class='toast-alert-message alert-success']")
	WebElement txt_FileUploadMessageDisappeared;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_CaseDocChecklistPage(WebDriver driver) 
	{
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	
	public void addDocChecklist(String clientName, String docChecklistName)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 08 Nov 2019
		  */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + clientName + "')]/../following-sibling::tr//span[contains(text(), 'Add Checklist Item')]", "xpath", "Add Checklist Item");
		
		Utils.enterTextInDynamicElement(driver, "//td[contains(text(), '" + clientName + "')]/../following-sibling::tr//input[@placeholder='Enter document description']", "xpath", docChecklistName, "Doc Checklist Title");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + clientName + "')]/../following-sibling::tr//button[contains(text(), 'Add')]", "xpath", "Add");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
	}
	
	
	public void uploadDocument(String docChecklistName, String filePath, String clientName) throws InterruptedException
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */

		filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + clientName + "')]/following-sibling::td/button[contains(@id, 'fileUploadButton')]", "xpath", "'Upload File' Button");
		
		Utils.clickDynamicElement(driver, "//label[(text() = '" + docChecklistName + "')]", "xpath", "Radio box for " + docChecklistName);
		
		Utils.waitForElementNotVisible(driver, "//div[contains(@id,'chooseChecklistModalPopupB')]//button[text()='Upload' and @disabled = 'disabled']", "xpath");
		
		Utils.clickElement(driver, icon_ClosePopup, "Close Icon");
		
		Utils.waitForElement(driver, btn_UploadFile);

		File path = new File(filePath);	
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);
		
		try {
			Utils.waitForElement(driver, btn_UploadFile);
			
			WebElement ele = driver.findElement(By.xpath("//input[contains(@id,'fileUploadInputB')]"));
		    ((RemoteWebElement) ele).setFileDetector(detector);
		    
			ele.sendKeys(file.getAbsolutePath());
			
			//btn_UploadFileInput.sendKeys(path.getAbsoluteFile().toString());
			Log.message("Entered the absolute file path in the Upload Button");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type the file path. ERROR :\n\n " + e.getMessage());
		}
		
		
		
		
		
		
//		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i", "xpath", "Arrow to expand the checklist");
//		
//		File filepath = new File(".\\src\\main\\resources\\TestFileForUpload.txt");
//
//		if (!filepath.exists())
//			Log.fail("File not found: " + filepath.toString());
//		
//		Utils.waitForElement(driver, "(//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//div[@class='drag_drop'])[1]", globalVariables.elementWaitTime, "xpath");
//		WebElement dropArea = driver.findElement(By.xpath("(//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//div[@class='drag_drop'])[1]"));
//		dropArea = driver.findElement(By.xpath("(//a[contains(text(), 'Browse')])[8]"));
//		dropArea.sendKeys(filepath.getAbsoluteFile().toString());
//		
//		Thread.sleep(4000);
		
//		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + clientName + "')]/following-sibling::td/button[contains(@id, 'fileUploadButton')]", "xpath", "'Upload File' Button");
//		
//		Utils.clickDynamicElement(driver, "//label[(text() = '" + docChecklistName + "')]", "xpath", "Radio box for " + docChecklistName);
//		
//		Utils.waitForElementNotVisible(driver, "//div[contains(@id,'chooseChecklistModalPopupB')]//button[text()='Upload' and @disabled = 'disabled']", "xpath");
//		
//		try {
//			Utils.clickDynamicElement(driver, "//div[contains(@id,'chooseChecklistModalPopupB')]//button[text()='Upload']", "xpath", "'Upload' Button");
//			
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
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");

	}
	
	
	public void verifyIfChecklistAdded(String docChecklistName)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]", "xpath", docChecklistName, "Document Checklist");
	}
	
	
	public void verifyIfDocumentUploaded(String docChecklistName, String fileName)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */

		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(),'" + fileName + "')]", "xpath", fileName, "Uploaded Document");
		
		
	}
	
	
	public void editDocChecklistNameAndAddDescription(String clientName, String nameOld, String docChecklistName, String docChecklistDescription)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + nameOld + "')]/ancestor::td/following-sibling::td//img[@src='../Content/img/more-icon.png']", "xpath", "Quick Actions Icon");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + nameOld + "')]/ancestor::td/following-sibling::td//label[contains(text(), 'Edit Checklist Item')]", "xpath", "'Edit Checklist Item' Link");
		
		Utils.enterTextInDynamicElement(driver, "//h4[contains(text(), '" + nameOld + "')]/ancestor::div[@class='modal-dialog add-checklist-modal']//input[@id='add_desc']", "xpath", docChecklistName, "Doc checklist name");
		
		Utils.enterTextInDynamicElement(driver, "//h4[contains(text(), '" + nameOld + "')]/ancestor::div[@class='modal-dialog add-checklist-modal']//div[@id='add_ComprehensiveDescription']", "xpath", docChecklistDescription, "Doc checklist description");
		
		Utils.clickElement(driver, btn_SaveEditedChecklist, "Save Button");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void verifyIfChecklistEditedAndDescriptionAdded(String docChecklistName, String docChecklistDescription)
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 09 Nov 2019
		  * 
		  */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]", "xpath", docChecklistName, "Document Checklist");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/following-sibling::span//i[@class='fa fa-info-circle']", "xpath", "Information Icon", "Doc checklist");
		
		Utils.waitForElementPresent(driver, "//p[contains(text(), '" + docChecklistDescription + "')]", "xpath");
		if(Utils.isElementPresent(driver, "//p[contains(text(), '" + docChecklistDescription + "')]", "xpath"))
		{
			Log.pass("Description is added", driver, true);
		}
		
		else
		{
			Log.fail("Description is not Added", driver, true);
		}
		
	}
	
	
	public void changeDocumentAccessRights(String docChecklistName, String fileName, String choice) throws InterruptedException
	{
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 11 Nov 2019
		  * 
		  */
				
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
		
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i", "xpath", "Arrow to expand the checklist");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/../../../../following-sibling::td//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");
		
		Utils.waitForElement(driver, dropdown_DocumentAccessRights);
		
        js.executeScript("javascript:window.scrollBy(0,250)");
		
        Thread.sleep(1000);
        
        if(choice.equals("Client"))
        {
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/../../../../following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'applicationAccess')]//label[contains(text(), 'Client')]", "xpath", "Client");		
			//Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/ancestor::td//div[@class='fn_stages open']//li[contains(@id, 'applicationAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");
        }
        
        if(choice.equals("Corporation"))
        {
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/../../../../following-sibling::td//div[@class='fn_stages open']//li[contains(@id, 'corporationAccess')]//label[contains(text(), 'Corporation')]", "xpath", "Corporation");		
			//Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/ancestor::td//div[@class='fn_stages open']//li[contains(@id, 'corporationAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");
        }
		
        if(choice.equals("Vendor"))
        {
//			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/ancestor::td//div[@class='fn_stages open']//li[contains(@id, 'vendorAccess')]//label[contains(text(), 'Vendor')]", "xpath", "Vendor");	
			//Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/ancestor::td//div[@class='fn_stages open']//li[contains(@id, 'vendorAccess')]//i[@class='fa fa-check span_blue']", globalVariables.elementWaitTime, "xpath");
        }
			
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileName + "')]/../../../../following-sibling::td//img[@class='access-icon has-tooltip']", "xpath", "Access Rights Icon");
	}
	
	
	public void verifyIfDocumentAccessRightsChanged(String docChecklistName, String fileName, String choice) throws InterruptedException
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Nov 2019
		 * 
		 */
				
		if(choice.equals("Client"))
		{
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'gAB3QBZsX09ujAAAAAElFTkSuQmCC')]", "xpath", "Client image", "Access Rights");
		}
		
		if(choice.equals("Corporation"))
        {
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'YABaP4Jm7KfgCAAAAAElFTkSuQmCC')]", "xpath", "Corporation image", "Access Rights");
        }
        
        if(choice.equals("Vendor"))
        {
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td/following-sibling::td//img[contains(@src, 'N0Puphnid4AAAAABJRU5ErkJggg==')]", "xpath", "Vendor Image", "Access Rights");
        }
	}
	
	
	public List<String> getAllDocumentsList(String clientName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Nov 2019
		 * 
		 */
		
		List<String> allDocuments = new ArrayList<>();
		
		Utils.clickElement(driver, icon_PreviousDocuments, "View Previous Documents");
		
		Utils.waitForElement(driver, dropdown_VerificationPreviousDocuments);
		
		Utils.waitForElement(driver, "//p[contains(text(), '" + clientName + "')]", globalVariables.elementWaitTime, "xpath");
		
		List<WebElement> documents = driver.findElements(By.xpath("//img[@class='dragndrop']/following-sibling::span"));
		
		for(int i = 2; i <= documents.size(); i += 2)
		{
			Utils.waitForElement(driver, "(//img[@class='dragndrop']/following-sibling::span)[" + i + "]", globalVariables.elementWaitTime, "xpath");
			String temp = driver.findElement(By.xpath("(//img[@class='dragndrop']/following-sibling::span)[" + i + "]")).getText();
			allDocuments.add(temp);
		}
		
		return allDocuments;
	}
	
	
	public void downloadAllDocuments(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Nov 2019
		 * 
		 */

		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickElement(driver, chkbox_SelectAllChecklists, "'Select All' CheckBox");
		
		Utils.clickElement(driver, icon_Actions, "'Actions' icon");
		
		Utils.clickElement(driver, btn_DownloadSelectedChecklists, "'Download' Button");
		
		Utils.waitForElementNotVisible(driver, "div[class='download_progress_modal']", "css");
		
		Thread.sleep(5000);
		
	}
	
	
	public void verifyIfDownloaded(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDownloaded(remoteDriver);
	}
	
	
	public void downloadSingleDocument(String docChecklistName, String fileName, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 Nov 2019
		 * 
		 */
		
		Utils.openDownloadsWindow(remoteDriver);
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		//Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i", "xpath", "Arrow to expand the checklist");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td//a[@class='zoom-action-dropdown-anchor has-tooltip']", "xpath", "Download");
		
		Utils.waitForElementNotVisible(driver, "div[class='download_progress_modal']", "css");
		
		Thread.sleep(5000);
		
	}
	
	
	public void selectDocumentToEdit(String docChecklistName, String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
				
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(),'" + fileName + "')]", "xpath", "Required Document Name i.e. " + fileName + " under " + docChecklistName);
		
		Utils.waitForElement(driver, popup_EditDocument);
		
	}
	
	
	public void editDocumentDetails(String docChecklistName, String fileNameNew, String fileDescription, String folderName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		Utils.enterText(driver, txtbox_FileName, fileNameNew, "New File Name");
		
		Utils.enterText(driver, txtbox_FileDescription, fileDescription, "Document Description");
		
		Utils.clickElement(driver, dropdown_DocumentReview, "Document Review");
		Utils.waitForElement(driver, dropdown_DocumentReviewOpen);
		
		Utils.clickElement(driver, lnk_Accepted, "Accepted");
		
		Utils.clickElement(driver, btn_AddTagFolder, "Add a Tag/Folder");
		Utils.waitForElement(driver, dropdown_AddTagFolder);
		
		Utils.enterText(driver, searchbox_TagFolder, folderName, "Tag/Folder search box");
		
		Utils.clickDynamicElement(driver, "//div[@id='myDropdown2']//label[contains(text(),'" + folderName + "')]", "xpath", "Folder");
		Utils.waitForElement(driver, "//span[@id='btn_13myDropdown2']/span[contains(text(),'" + folderName + "')]", globalVariables.elementWaitTime, "xpath");
		
		Utils.clickElement(driver, chckbox_Vendor, "Vendor");
		
		Utils.clickElement(driver, btn_SaveDocumentChanges, "Save Button");
	}
	
	
    public void verifyIfDocumentEditted(String docChecklistName, String fileNameNew, String fileDescription, String folderName) throws InterruptedException
    {
          /*
          * Created By : Saksham Kapoor
          * Created On : 12 Nov 2019
          * 
           */
          
          Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileNameNew + "')]", "xpath", fileNameNew, "New document name");
          
          Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(),'" + fileNameNew + "')]/following-sibling::span/img[@alt='Accept Document']", "xpath", "Approved Icon", "Document Review");
          
          Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + fileNameNew + "')]/../following-sibling::p[contains(text(), '" + fileDescription +"')]", "xpath", fileDescription, "Document Description");
          
          Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(), '" + fileNameNew + "')]/ancestor::td//span[contains(text(), '" + folderName + "')]", "xpath", folderName, "Tag/Folder");
          
          verifyIfDocumentAccessRightsChanged(docChecklistName, fileNameNew, "Corporation");
          
          verifyIfDocumentAccessRightsChanged(docChecklistName, fileNameNew, "Client");
    }

	
	
	public String copyFromTemplate()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_CopyFromTemplate, "Copy from template");
		
		Utils.clickElement(driver, checkbox_DocChecklist, "Doc Checklist Checkbox");
		
		String checklistName = "";
		try 
		{
			Utils.waitForElement(driver, txt_DocChecklist);
			checklistName = txt_DocChecklist.getText();
			Log.message("Fetched the name of checklist as : " + checklistName);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to fetch the name of doc checklist. ERROR :\n\n " + e.getMessage());
		}

		Utils.clickElement(driver, btn_AddToChecklist, "Add to Checklist Button");
		Utils.waitForElement(driver, "div[class='mainLoader']", globalVariables.elementWaitTime, "css");
		Utils.waitForElementNotVisible(driver, "div[class='mainLoader']", "css");
		
		return checklistName;
	}
	
	
	public void deleteUploadedDocument(String docChecklistName, String fileNameNew)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileNameNew + "')]/ancestor::td//li[@class='dropdown options-icon-list']/a", "xpath", "Actions icon for document");
		Utils.waitForElement(driver, dropdown_DocumentActions);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		
		Utils.clickElement(driver, btn_DeleteDocument, "Delete Button");
		
		Utils.clickElement(driver, btn_Continue, "Continue button");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
	}
	
	
	public void verifyIfDocumentDeleted(String docChecklistName, String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'" + docChecklistName + "')]/ancestor::tr/following-sibling::tr//span[contains(text(),'" + fileName + "')]", btn_UploadFile, "xpath", fileName, docChecklistName);
	}
	
	
	public void deleteDocChecklist(String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Nov 2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::td/following-sibling::td//img[@src='../Content/img/more-icon.png']", "xpath", "Quick Actions Icon");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		
		Utils.clickElement(driver, btn_DeleteChecklistItem, "Delete Button");
		
		Utils.clickElement(driver, btn_ContinueDeleteDocChecklist, "Continue button");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
	}
	
	
	public void verifyIfChecklistDeleted(String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]", btn_UploadFile, "xpath", docChecklistName, "Doc Checklist");
	}
	
	
	public void checkNamesOnHeader(String corpName, String clientName, String caseId)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 03 April 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + caseId + "')]", "xpath", caseId, "Header");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + clientName + "')]", "xpath", clientName, "Header");

		Utils.verifyIfDataPresent(driver, "//p[contains(text(), 'Corporation:')]/a[contains(text(), '" + corpName + "')]", "xpath", corpName, "Header");

		Utils.verifyIfDataPresent(driver, "//p[contains(text(), 'Sponsor:')]/a[contains(text(), '" + corpName + "')]", "xpath", corpName, "Header");

	}
	
	
	public void checkBreadcrumbs()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 03 April 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Case List')]/../following-sibling::li/span[contains(text(), 'Case Profile')]/../following-sibling::li/span[contains(text(), 'Documents')]", "xpath", "CASE LIST > CASE PROFILE > Documents", "breadcrumbs");
	}
	
	
	public void verifyReceived(String caseDocChecklistName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 April 2020
		 * 
		 */
		
		if(choice.equals("Received"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Received')]", "xpath", "Received", "Received Column");
		
		if(choice.equals("Not Received"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Not Received')]", "xpath", "Not Received", "Received Column");
		
		if(choice.equals("Partially Received"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Partially Received')]", "xpath", "Partially Received", "Received Column");
	}
	
	
	public void changeReceived(String caseDocChecklistName, String initialChoice, String finalChoice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 09 April 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//span[contains(text(), '" + initialChoice + "')]", "xpath", initialChoice);
		
		Utils.waitForElement(driver, "div[class='dropdown open']", globalVariables.elementWaitTime, "css");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//a[contains(text(), '" + finalChoice + "')]", "xpath", finalChoice);
	}
	
	
	public void verifyIfOptionsAvailableInQuickActions(String fileName, String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Apr 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td//li[@class='dropdown options-icon-list']/a", "xpath", "Actions icon for document");
		Utils.waitForElement(driver, dropdown_DocumentActions);
		
		Utils.waitForElement(driver, "//li[@class='dropdown options-icon-list open']//label", globalVariables.elementWaitTime, "xpath");
		List<WebElement> webElementsOfOptions = driver.findElements(By.xpath("//li[@class='dropdown options-icon-list open']//label"));
		
		List<String> listOfOptions = new ArrayList<>();
		
		for(int i = 0; i < webElementsOfOptions.size(); i++)
		{
			listOfOptions.add(webElementsOfOptions.get(i).getText());
		}
		
		if(listOfOptions.containsAll(globalVariables.listOfQuickActions) && globalVariables.listOfQuickActions.containsAll(listOfOptions))
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
		 * Created On : 13 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, icon_Actions, "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Download')]", "xpath", "disbaled 'Download'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Print Documents')]", "xpath", "disbaled 'Print Documents'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list forbidden']//label[contains(text(), 'Delete Checklist')]", "xpath", "disbaled 'Delete Checklist'", "Main Actions dropdown");

		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Print Checklist')]", "xpath", "enabled 'Print Checklist'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Email Checklist')]", "xpath", "enabled 'Email Checklist'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Activity')]", "xpath", "enabled 'Activity'", "Main Actions dropdown");

	}
	
	
	public void verifyIfOptionsEnabled()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, chkbox_SelectAllChecklists, "'Select All' CheckBox");
		
		Utils.clickElement(driver, icon_Actions, "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Download')]", "xpath", "enabled 'Download'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Print Documents')]", "xpath", "enabled 'Print Documents'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Delete Checklist')]", "xpath", "enabled 'Delete Checklist'", "Main Actions dropdown");

		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Print Checklist')]", "xpath", "enabled 'Print Checklist'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Email Checklist')]", "xpath", "enabled 'Email Checklist'", "Main Actions dropdown");
		
		Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Activity')]", "xpath", "enabled 'Activity'", "Main Actions dropdown");

	}
	
	
	public void checkIfHeadingsPresent()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Apr 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//th[contains(text(), 'Document Checklist')]/following-sibling::th[contains(text(), 'Received')]", "xpath", "Table headings", "Documents Page");
	}
	
	
	public void changeStatus(String docChecklistName, String fileName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Apr 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
   	  	js.executeScript("window.scrollBy(0,1000)");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/ancestor::td[1]/following-sibling::td//a[@id='actionMenu_mainActionDropdownUlId']", "xpath", "Actions icon for document");
		Utils.waitForElement(driver, dropdown_DocumentActions);
		
		if(choice.equals("Accepted"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Accept')]", "xpath", "Accept");
		
		if(choice.equals("Pending Review"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Pending Review')]", "xpath", "Pending Review");
		
		if(choice.equals("Need Clarification"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Need Clarification')]", "xpath", "Need Clarification");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void checkIfStatusChanged(String docChecklistName, String fileName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Apr 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		if(choice.equals("Accepted"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Accept Document']", "xpath", "Accept", "Document");
		
		if(choice.equals("Pending Review"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Pending review']", "xpath", "Pending Review", "Document");
		
		if(choice.equals("Need Clarification"))
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + fileName + "')]/following-sibling::span/img[@alt='Need Clarification']", "xpath", "Need Clarification", "Document");

	}
	
	
	public void selectDocChecklist(String caseDocChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 15 Apr 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/../../preceding-sibling::div//label", "xpath", caseDocChecklistName);
	}
	
	
	public void changeDocChecklistStatus(String caseDocChecklistName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 15 Apr 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//img[@src='../Content/img/more-icon.png']", "xpath", "Main Action Dropdown");
		Utils.waitForElement(driver, dropdown_DocumentActions);
		
		if(choice.equals("Accepted"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Accept')]", "xpath", "Accept");
		
		if(choice.equals("Pending Review"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Pending Review')]", "xpath", "Pending Review");
		
		if(choice.equals("Need Clarification"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Need Clarification')]", "xpath", "Need Clarification");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void verifyAscendingOrderOfDocuments(String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Apr 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		
		ArrayList<String> docsList = new ArrayList<String>();
		
        Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        List<WebElement> allDocsList = driver.findElements(By.xpath("//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
              WebElement eachDocName = allDocsList.get(i);
              docsList.add(eachDocName.getText().trim().toString());
        }
        
        Collections.sort(docsList, String.CASE_INSENSITIVE_ORDER);

        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//a[contains(text(), 'Document')]", "xpath", "Document heading");
        Utils.waitForElement(driver, icon_ascending);
        
        ArrayList<String> sortedDocsListFromUI = new ArrayList<String>();
        
        Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        allDocsList = driver.findElements(By.xpath("//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]"));
        
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
	
	
	public void verifyDescendingOrderOfDocuments(String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 16 Apr 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		ArrayList<String> docsList = new ArrayList<String>();
		
       Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        List<WebElement> allDocsList = driver.findElements(By.xpath("//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
              WebElement eachDocName = allDocsList.get(i);
              docsList.add(eachDocName.getText().trim().toString());
        }
        Collections.sort(docsList, String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(docsList);
        
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//a[contains(text(), 'Document')]", "xpath", "Document heading");
        Utils.waitForElement(driver, icon_descending);
        
        ArrayList<String> sortedDocsListFromUI = new ArrayList<String>();
        
        Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]", globalVariables.elementWaitTime, "xpath");
        allDocsList = driver.findElements(By.xpath("//span[contains(text(), '" + docChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(@class, 'trimTextDocName')]"));
        
        for (int i = 0; i < allDocsList.size(); i ++) 
        {
            WebElement eachDocName = allDocsList.get(i);
            sortedDocsListFromUI.add(eachDocName.getText().trim().toString());
        }
        
        if (docsList.equals(sortedDocsListFromUI)) 
        {
        	Log.message("", driver, true);
        	Log.pass("Descending order working fine"); 
        }
        
        else
        {
        	Log.message("", driver, true);
        	Log.pass("Descending order not working fine"); 
        }
	}
	
	
	public void addDocChecklistToPetition(String name, String docChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 20 Apr 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + name + "')]/../following-sibling::tr//span[contains(text(), 'Add Checklist Item')]", "xpath", "Add Checklist Item");
		
		Utils.enterTextInDynamicElement(driver, "//td[contains(text(), '" + name + "')]/../following-sibling::tr//input[@placeholder='Enter document description']", "xpath", docChecklistName, "Doc Checklist Title");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + name + "')]/../following-sibling::tr//button[contains(text(), 'Add')]/following-sibling::button/span[@class='caret']", "xpath", "dropdown to Add");
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + name + "')]/../following-sibling::tr//div[@class='btn-group open']//span[contains(text(), 'Add to Petition template')]", "xpath", "Add to Petition template");
		
		Utils.clickElement(driver, btn_save, "Save");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
	}
	
	
	public void changeRequiredToUpload(String caseDocChecklistName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 22 Apr 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//img[@src='../Content/img/more-icon.png']", "xpath", "Quick Actions Icon");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//label[contains(text(), 'Edit Checklist Item')]", "xpath", "'Edit Checklist Item' Link");
		
		if(choice.equals("Required"))
		{
			Utils.clickElement(driver, radioBtn_required, "Required");
			Utils.clickElement(driver, btn_SaveEditedChecklist, "Save Button");
			
			Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
			
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/following-sibling::span/span[contains(text(), 'Required')]", "xpath", "Required", "Doc Checklist");
		}
		
		if(choice.equals("Optional"))
		{
			Utils.clickElement(driver, radioBtn_optional, "Optional");
			Utils.clickElement(driver, btn_SaveEditedChecklist, "Save Button");
			
			Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
			
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/following-sibling::span/span[contains(text(), 'Optional')]", "xpath", "Optional", "Doc Checklist");
		}
		
		if(choice.equals("If Applicable"))
		{
			Utils.clickElement(driver, radioBtn_ifApplicable, "If Applicable");
			Utils.clickElement(driver, btn_SaveEditedChecklist, "Save Button");
			
			Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
			
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/following-sibling::span/span[contains(text(), 'If Applicable')]", "xpath", "If Applicable", "Doc Checklist");
		}
	}
	
	
	public void addPassportToChecklist(String caseDocChecklistName, String country)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 28 Apr 2020
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
		
		Utils.enterText(driver, searchbox_tagFolder, "Passport", "Passport");
		
		Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = 'Passport']", "xpath", "Passport");
		
		Utils.enterText(driver, searchbox_country, country, country);
		
		Utils.clickDynamicElement(driver, "//span[@class='dropdown-content text-left show']//label[text()='" + country + "']", "xpath", country);
	}
	
	
	public void verifyIfCountryAddedToChecklist(String caseDocChecklistName, String country)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 28 Apr 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//img[contains(@src, '" + country + "') and @class='hasPopover selectedFlag']", "xpath", country + " flag", "Passport Tag/Folder");
	}
	
	
	public void verifyIfTagAddedToDocument(String checklistName, String fileName, List<String> tags)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 28 Apr 2020
		 * 
		 */
		
		for(int i = 0; i < tags.size(); i++)
			Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + checklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(),'" + fileName + "')]/ancestor::td/following-sibling::td//span[contains(text(), '" + tags.get(i) + "')]", "xpath", tags.get(i), "Tags/Folders");
	}
	
	
	public void removeTag(String caseDocChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 28 Apr 2020
		 * 
		 */
		
		List<WebElement> removeTag = driver.findElements(By.xpath("//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//i[@class='fa fa-times pointer']"));
		
		for(int i = 0; i < removeTag.size(); i ++)
			Utils.clickElement(driver, removeTag.get(i), "'x' button");
	}
	
	
	public void removeTagFromDocument(String checklistName, String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 28 Apr 2020
		 * 
		 */
		
		List<WebElement> removeTag = driver.findElements(By.xpath("//span[contains(text(), '" + checklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(),'" + fileName + "')]/ancestor::td/following-sibling::td//i[@class='fa fa-times pointer']"));
		
		for(int i = 0; i < removeTag.size(); i ++)
			Utils.clickElement(driver, removeTag.get(i), "'x' button");
	}
	
	
	public void addTag(String caseDocChecklistName, List<String> listOfTags)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 10 Aug 2021
		 * Added scroll function
		 * 
		 */
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.scrollToDynamicElement(driver, "//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.clickDynamicElement(driver, "//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.enterText(driver, searchbox_tagFolder, listOfTags.get(i), "tag/folder searchbox");
			
			Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = '" + listOfTags.get(i) + "']", "xpath", listOfTags.get(i));
		}
	}
	
	
	public void verifyIfAlertForTagsPresent()
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 10 Aug 2021
		 * Added the function to wait for the alert to dissappear as it can act as an interceptioing element for the nect action
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), 'Limit 6 tags per document reached.') and @class='toast-alert-message warning show']", "xpath", "Limit 6 tags per document reached.", "Doc Checklist Page");
		Utils.waitForElementNotVisible(driver, "//div[contains(text(), 'Limit 6 tags per document reached.') and @class='toast-alert-message warning show']", "xpath");
	}
	
	
	public void verifyIfTagRemoved(String caseDocChecklistName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 29 Apr 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + caseDocChecklistName + "')]/ancestor::td//span[contains(text(), 'Add Tag/Folder')]", "xpath", "Add Tag/Folder", "Doc Checklist Page");
	}
	
	
	public void addTagToDocument(String caseDocChecklistName, String caseFile1Name, List<String> listOfTags)
	{
		/*
		 * Edited By : Souvik Ganguly
		 * Edited On : 10 Aug 2021
		 * Added scroll function
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.scrollToDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + caseFile1Name + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + caseFile1Name + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder");
			
			Utils.enterText(driver, searchbox_tagFolder, listOfTags.get(i), "tag/folder searchbox");
			
			Utils.clickDynamicElement(driver, "//div[@class='dropdown-content show']//label[text() = '" + listOfTags.get(i) + "']", "xpath", listOfTags.get(i));
		}
	}
	
	
	public void verifyIfTagRemovedFromDocument(String caseDocChecklistName, String caseFile1Name)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 29 Apr 2020
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::tr/following-sibling::tr[1]//span[contains(text(), '" + caseFile1Name + "')]/ancestor::td/following-sibling::td//span[contains(text(), 'Add Tag/Folder') or @class='badge badge-custom-default']", "xpath", "Add Tag/Folder", "Doc Checklist Page");
	}
	
	
	public void clickSaveButton()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_SaveDocumentChanges, "Save Button");
	}
	
	
	public void addTagFromPreview(List<String> listOfTags)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		for(int i = 0; i < listOfTags.size(); i++)
		{
			Utils.waitForElement(driver, "//div[@id='previewModalPopUp']//span[contains(text(), 'Add a tag/folder') or @class='badge badge-custom-default']", globalVariables.elementWaitTime, "xpath");
			WebElement addTag = driver.findElement(By.xpath("//div[@id='previewModalPopUp']//span[contains(text(), 'Add a tag/folder') or @class='badge badge-custom-default']"));
			
			Utils.scrollToElement(driver, addTag);
			Utils.clickDynamicElement(driver, "//div[@id='previewModalPopUp']//span[contains(text(), 'Add a tag/folder') or @class='badge badge-custom-default']", "xpath", "Add a Tag/Folder");
			Utils.waitForElement(driver, dropdown_AddTagFolder);
			
			Utils.enterText(driver, searchbox_TagFolder, listOfTags.get(i), "Tag/Folder search box");
			
			Utils.clickDynamicElement(driver, "//div[@id='myDropdown2']//label[contains(text(),'" + listOfTags.get(i) + "')]", "xpath", "Folder");
			Utils.waitForElement(driver, "//span[contains(@id, 'myDropdown2')]//span[contains(text(),'" + listOfTags.get(i) + "')]", globalVariables.elementWaitTime, "xpath");
		}
	}
	
	
	public void removeTagFromPreview()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Apr 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		List<WebElement> removeTag = driver.findElements(By.xpath("//span[contains(@id, 'myDropdown2')]//i[@class='fa fa-times pointer']"));
		
		for(int i = 0; i < removeTag.size(); i ++)
			Utils.clickElement(driver, removeTag.get(i), "'x' button");
	}
	
	
	public void renameDocument(String caseFile1Name, String caseFile1NameWithoutExtension, String caseRenamedFile, String workbookNameWrite, String sheetName) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 May 2020
		 * 
		 */
		
		String[] temp = caseFile1Name.split("[.]");
		String newName = caseRenamedFile + "." + temp[1];
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		Utils.enterText(driver, txtbox_FileName, caseRenamedFile, "New File Name");
		
		clickSaveButton();
		
        Utils.verifyIfDataPresent(driver, "//span[contains(text(), '" + newName + "')]", "xpath", newName, "New document name");

        String[] dataToWrite = {newName, caseRenamedFile};
        String[] columnInExcel = {"caseFile1Name", "caseFile1NameWithoutExtension"};
		
		Utils.saveDataToExcel(workbookNameWrite, sheetName, dataToWrite, columnInExcel);
		
	}
	
	
	public void addDescriptionToDocument(String description)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 11 May 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		Utils.enterText(driver, txtbox_FileDescription, description, "Document Description");
		
		clickSaveButton();
		
        Utils.verifyIfDataPresent(driver, "//p[contains(text(), '" + description + "')]", "xpath", description, "Document Description");
	}
	
	
	public void changeStatusFromPreview(String status)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 12 May 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		Utils.clickElement(driver, btn_status, "Status");
		
		Utils.waitForElement(driver, dropdown_status);
		
		Utils.clickDynamicElement(driver, "//a[contains(text(), '" + status + "')]", "xpath", status);
	
		clickSaveButton();
	}
	
	
	public void changeDocumentAccessRightsFormPreview(String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 19 June 2020
		 * 
		 */
		
		Utils.clickElement(driver, tab_DocumentDetails, "Document Details tab");
		
		if(choice.equals("Vendor"))
			Utils.clickDynamicElement(driver, "//label[contains(@for, 'vendor')]", "xpath", choice);
		
		if(choice.equals("Client"))
			Utils.clickDynamicElement(driver, "//label[contains(@for, 'application')]", "xpath", choice);
		
		if(choice.equals("Corporation"))
			Utils.clickDynamicElement(driver, "//label[contains(@for, 'corporation')]", "xpath", choice);
		
		clickSaveButton();
	}
	
	
	public void changeAccessForMultipleDocuments(String docChecklistName, String choice)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 19 June 2020
		 * 
		 */
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem']", "xpath", "Arrow to expand the checklist");
		
		Utils.clickElement(driver, dropdown_access, "Access Rights icon");
		
		if(choice.equals("Vendor"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Vendor')]", "xpath", choice);
	
		if(choice.equals("Client"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Client')]", "xpath", choice);
		
		if(choice.equals("Corporation"))
			Utils.clickDynamicElement(driver, "//li[@class='dropdown options-icon-list open']//label[contains(text(), 'Corporation')]", "xpath", choice);
	}

	
	 public void clickCopyFromTemplate() throws Exception  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 27 Feb 2020
		  */ 
		 
		 Utils.clickElement(driver, btn_CopyFromTemplate, "Copy From template");

	 }  

	 
	 public void verifyCopyFromPetitionPopup() throws Exception  
	 {
		 /*
		  * Created By : Saurav Purohit
		  * Created On : 27 Feb 2020
		  */ 
		 
		 Utils.verifyIfStaticElementDisplayed(driver, btn_AddToChecklist, "Add To checklist", "Copy from Petition template");
		 Utils.clickElement(driver, btn_closeButton, "close button");
		 driver.close();
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - View Custom Fields", "title", "false");

	 }  
	 
	 
	 public void clickPreviousDocuments()
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 June 2020
		 * 
		 */
		 
		 Utils.clickElement(driver, icon_previousDocuments, "Previous Documents");
	 }
	 
	 
	 public void checkPreviousDocuments(String clientName, String clientFile1Name)
	 {
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 23 June 2020
		 * 
		 */
		 
		 Utils.verifyIfDataPresent(driver, "//div[contains(@style,'display: block;') and @id='mySidenav']//p[contains(text(), '" + clientName + "')]/../ul//span[contains(text(), '" + clientFile1Name + "')]", "xpath", clientFile1Name, "Previous Documents");
	 }
		
	 
	 public void deleteAllDocumentsUnderDocCheckList(String caseDocChecklistName)
	{
	 	/*
		 * Created By : Likitha Krishna
		 * Created On : 17 Sep 2020
		 */
		
		Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/ancestor::td/following-sibling::td//img[@src='../Content/img/more-icon.png']", "xpath", "Main Action Dropdown");
		Utils.waitForElement(driver, dropdown_DocumentActions);
			
		Utils.clickElement(driver, dropDownOption_Delete, "Delete");
		
		Utils.clickElement(driver, btn_Continue, "Continue");
		
		Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/../../preceding-sibling::div/i[@class='fa fa-angle-right drop_down alignItem fa-angle-down']", "xpath"))
			
			Utils.clickDynamicElement(driver, "//span[contains(text(), '" + caseDocChecklistName + "')]/../../preceding-sibling::div/i", "xpath", "Arrow to expand the checklist");
		
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" +caseDocChecklistName+ "')]/ancestor::tr/following-sibling::tr//p[contains(text(),'Drag and drop files to upload or browse')]", "xpath", "Drag and drop text", caseDocChecklistName);
		
	}
	 
	 public void deleteAllChecklist()
		{
		 	/*
			 * Created By : Likitha Krishna
			 * Created On : 17 Sep 2020
			 */
			
			Utils.clickElement(driver, chkbox_SelectAllChecklists, "'Select All' CheckBox");
			
			Utils.clickElement(driver, icon_Actions, "Main Actions dropdown");
			
			Utils.verifyIfDataPresent(driver, "//li[@class='dropdown options-icon-list open']//li[@class='zoom-action-dropdown-list']//label[contains(text(), 'Delete Checklist')]", "xpath", "enabled 'Delete Checklist'", "Main Actions dropdown");

			Utils.clickElement(driver, dropDownOption_DeleteChecklist, "delete checklist");
			
			Utils.clickElement(driver, btn_ContinueForDelete, "Continue");
			
			Utils.waitForElement(driver, txt_FileUploadMessageAppeared);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success']", "css");
			
			Utils.verifyIfDataNotPresent(driver, "//span[contains(text(),'Document Checklist')]", chkbox_SelectAllChecklists, "xpath", "checklist", "document check list page");
			
		}
			
	
}
