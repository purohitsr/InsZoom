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
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_Letters_MSWordPage extends LoadableComponent<CM_Letters_MSWordPage> {

	private String workbookName = "testdata\\data\\Regression_PoC.xls";
	private String workbookNameWrite = "\\src\\main\\resources\\testdata\\data\\Regression_PoC.xls";
	private String sheetName = "Inszoom";

	// ** Loading the test data from excel using the test case id */
	ReadWriteExcel readExcel = new ReadWriteExcel();
	

	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	
	
	
	@FindBy(xpath = "//img[@alt='Delete']")
	WebElement img_delete;
	
	@FindBy(xpath = "//button[@id='SaveButtonNew']")
	WebElement btn_continueForDeleteAll;
	
	@FindBy(xpath = "//label[@for='selectAllDocumentsCheckBox']")
	WebElement checkBox_deleteAll;
	
	@FindBy(xpath = "//img[@alt='Print']")
	WebElement img_Print;
	
	@FindBy(xpath = "//span[@class='k-icon k-i-sort-desc-sm']")
	WebElement icon_Descending;
	
	@FindBy(xpath = "//span[@class='k-icon k-i-sort-asc-sm']")
	WebElement icon_Ascending;
	
	@FindBy(xpath = "//div[@type='KendoNativeClientSideGrid']//a[@class='k-link' and contains(text(),'Letter')]")
	WebElement link_Letter;
	
	@FindBy(xpath = "//div[@type='KendoNativeClientSideGrid']//tr")
	WebElement rows_LetterTemplate;
	
	@FindBy(xpath = "//input[@id='txtSearch' and @placeholder='Search Letter']")
	WebElement searchBox_searchLetter;
	
	@FindBy(xpath = "//button[@id='useTemplate']")
	WebElement btn_useTemplate;
	
	@FindBy(xpath = "//h4[contains(text(),'Available Templates')]/..//input[@id='txtSearch']")
	WebElement searchBox_AvailableTemplates;
	
	@FindBy(xpath = "//input[@name='RowRemove']")
	WebElement text_Remove;
	
	@FindBy(xpath = "//input[@class='zoomGridAddBtn Local' and @value='Save']")
	WebElement btn_SaveUploadedTemplate; 
	
	@FindBy(id="fileUpld_BtnSubmit11_ClientState")
	WebElement btn_Browse;
	
	@FindBy(id = "txtTitle")
	WebElement textBox_TitleForLocalTemplate; 
	
	@FindBy(xpath = "//button[contains(text(),'Upload letter from desktop')]")
	WebElement btn_uploadLetterFromDesktop; 
	
	@FindBy(xpath = "//div[@class='row inline-btn-wrapper']//li")
	WebElement row_serverTemplateTable;
   
	@FindBy(xpath = "//div[@class='modal md-effect-1 md-show']//button[@id='SaveButtonNew']")
	WebElement btn_continue; 
	
	@FindBy(xpath = "//li[@class='dropdown options-icon-list open']//label[contains(text(),'Delete')]")
	WebElement option_delete; 
	
	@FindBy(id = "btn_SaveLetterDetails")
	WebElement btn_SaveLetterDetails; 
	
	@FindBy(id = "letterTitle")
	WebElement textBox_title; 
	
	@FindBy(xpath = "//div[@class='toast-alert-message alert-success success_alert_msg show' and contains(text(),' Your document will be downloaded shortly.')]")
	public static WebElement msg_DocumentWillBeDownloaded; 
	
	@FindBy(css="div[class='toast-alert-message alert-success success_alert_msg show']")
	public static WebElement txt_SuccessfullyAdded; 
	
	
	public CM_Letters_MSWordPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CM_Letters_MSWordPage(WebDriver driver) {
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
			Log.fail("Letter MS word Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
	public void useServerTemplateAndVerify(String templateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		 */ 
		
		Utils.clickDynamicElement(driver, "//a/span[contains(text(),'"+ templateName +"')]/../../following-sibling::div//button[@class='btn btn-sm btn-solid-green usebtn']", "xpath", "use template button");
		
		Utils.waitForElement(driver, txt_SuccessfullyAdded);
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css"); 
		
		Utils.verifyIfDataPresent(driver, "//a/span[contains(text(),'"+ templateName +"')]/../../following-sibling::div//span[contains(text(),'Used')]", "xpath", templateName, "Used button has successfully come up");
		
		Utils.verifyIfDataPresent(driver, "//div[@name='LettersNativeGrid']//tr/td/a[contains(text(),'"+ templateName +"')]", "xpath", templateName, "has successfully moved to use table");
		
		driver.close();
		
	}
	
	
	
	public void downloadAndVerify(String templateName, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 20 Nov 2019
		 */ 
		
		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickDynamicElement(driver, "//div[@name='LettersNativeGrid']//a[contains(text(),'"+ templateName +"')]/../..//img[@alt='Download']", "xpath", "download option");
		
		Utils.waitForElement(driver, msg_DocumentWillBeDownloaded);
		Utils.waitForElementPresent(driver, "//div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),' Your document will be downloaded shortly.')]", "xpath");
		
		Utils.verifyIfDownloaded(remoteDriver);
	}
	
	
	public void editAndVerify(String templateName, String editTemplateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 20 Nov 2019
		 */ 
		
		Utils.clickDynamicElement(driver, "//div[@name='LettersNativeGrid']//a[contains(text(),'"+ templateName +"')]/../..//a/img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAQAAACR313BAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAADdcAAA3XAUIom3gAAAAHdElNRQfjAg8INBTRRPaIAAAAv0lEQVQY033QsU7CABAG4C+lGgklcSMuDrJiWJ0JhvAMnWBx9TGIA6+AUQj4CIRAjIsund0cHF0IC4OTi7SVVG67+/LfJUdxXXuzdBGKxE7Bi9df7JpqKLsPTHxLJBKfKd6KPViLWO2t7ZqroG0t3ueOhSpKxm74yx0LUR7zXIAZn9k4R2imt8Mgl33yqGZsbpTd26Wnmu5s9FNpqYVp0zCwdOU9nbRtM74s+m3gYAVOHP1jdV+hoWfbAjy28vEDFXcsldzTO10AAAAldEVYdGRhdGU6Y3JlYXRlADIwMTktMDItMTVUMDc6NTI6MjArMDE6MDCvEReUAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDE5LTAyLTE1VDA3OjUyOjIwKzAxOjAw3kyvKAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAAASUVORK5CYII=']", "xpath", "edit option");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom-Edit Letter(MSWORD)", "title", "false");
		
		Utils.enterText(driver, textBox_title, editTemplateName, "editted template title");
		
		Utils.clickElement(driver, btn_SaveLetterDetails, "save button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//div[@name='LettersNativeGrid']//tr/td/a[contains(text(),'"+ editTemplateName +"')]", "xpath", editTemplateName, "editted template");
		
		driver.close();
		
	}
	
	public void deleteAndVerify(String templateName , String edittedtemplateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 20 Nov 2019
		 */ 
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+ edittedtemplateName +"')]/../..//img[@src='../Content/img/more-icon.png']", "xpath", "dots img to further click delete option for "+edittedtemplateName);
		
		Utils.clickElement(driver, option_delete, "delete option");
		
		Utils.clickElement(driver, btn_continue, "continue button");
		
		Utils.waitForElement(driver, row_serverTemplateTable);
		
	//	Utils.verifyIfDataPresent(driver, "//div[@class='row inline-btn-wrapper']//li//span[contains(text(),'"+ templateName +"')]", "xpath", templateName, "deleted successfully");
		
		Utils.waitForElement(driver, "//a/span[contains(text(),'"+templateName+"')]/../../following-sibling::div//button[@class='btn btn-sm btn-solid-green usebtn']", globalVariables.elementWaitTime, "xpath");
		WebElement waitElement_rowsOfTheTable = driver.findElement(By.xpath("//div[@id='LettersNativeGrid']//td"));
		
		Utils.verifyIfDataNotPresent(driver, "//div[@name='LettersNativeGrid']//tr/td/a[contains(text(),'"+ edittedtemplateName +"')]", waitElement_rowsOfTheTable, "xpath", edittedtemplateName, "user template table");
		
		driver.close();
		
	}
	
	
	public void uploadLetterFromDesktopAndVerify(String templateName, String filePath) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 20 Nov 2019
		 */ 
		
		
		Utils.clickElement(driver, btn_uploadLetterFromDesktop, "upload Letter From Desktop button");
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - MS-WORD Letter Generation", "title", "false");
		
		Utils.enterText(driver, textBox_TitleForLocalTemplate, templateName, "template title");
		
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
		
		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString()); 
			WebElement droparea = null;
		try {

			droparea = driver.findElement(By.xpath("//div[@class='DropZone1']"));
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
		
//		input.sendKeys(path.getAbsoluteFile().toString()); 
		
		Utils.waitForElement(driver, "//span[@class='ruUploadProgress ruUploadSuccess']", globalVariables.elementWaitTime, "xpath");
		Utils.waitForElement(driver, text_Remove);
        
        Utils.clickElement(driver, btn_SaveUploadedTemplate, "save button");
        
        if(driver.getWindowHandles().size() == 3)
        {
        Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom-Update Letter", "title", "false");
        }
		
		driver.close();
        
        Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
		
		driver.close();	
	}
	
	
	public void deleteAndVerifyLocalTemplate(String templateName) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 21 Nov 2019
		 */ 
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+ templateName +"')]/../..//img[@src='../Content/img/more-icon.png']", "xpath", "dots img to further click delete option");
		
		Utils.clickElement(driver, option_delete, "delete option");
		
		Utils.clickElement(driver, btn_continue, "continue button");
		
		Utils.waitForElement(driver, row_serverTemplateTable);
		
		WebElement waitElement_rowsOfTheTable = driver.findElement(By.xpath("//div[@id='LettersNativeGrid']//td"));
		
		driver.navigate().refresh();
		
		Utils.verifyIfDataNotPresent(driver, "//div[@name='LettersNativeGrid']//tr/td/a[contains(text(),'"+ templateName +"')]", waitElement_rowsOfTheTable, "xpath", templateName, "user template table");
		
		driver.close();
		
	}
	
	
	public void verifyClientAndCaseDetailsInCaseLevel(String clientName, String clientEmail, String caseId, String sponsor) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */ 
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both pad-spacing-none-left']//div/a[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both pad-spacing-none-left']//div/p[contains(text(),'"+clientEmail+"')]", "xpath", clientEmail, "client email");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both pad-spacing-none-left']//p[contains(text(),'Corporation:')]/a[contains(text(),'"+sponsor+"')]", "xpath", sponsor, "corporation name");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both pad-spacing-none-left']//span/a[contains(text(),'"+caseId+"')]", "xpath", caseId, "case id");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both pad-spacing-none-left']//p[contains(text(),'Sponsor')]/a[contains(text(),'"+sponsor+"')]", "xpath", sponsor, "sponsor name");
		driver.close();
	}
	
	
	public void verifyClientAndCaseDetailsInClientLevel(String clientName, String clientEmail, String sponsor) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */ 
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//div/a[contains(text(),'"+clientName+"')]", "xpath", clientName, "client name");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//div/p[contains(text(),'"+clientEmail+"')]", "xpath", clientEmail, "client email");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//p[contains(text(),'Employer and Sponsor: ')]/a[contains(text(),'"+sponsor+"')]", "xpath", sponsor, "Employer and Sponsor:");
		driver.close();
	}
	
	
	public void verifyCorpDetailsInCorpLevel(String corpName, String emailId, String signingPerson) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */ 
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//div/a[contains(text(),'"+corpName+"')]", "xpath", corpName, "corp name");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//div/p[contains(text(),'"+emailId+"')]", "xpath", emailId, "email id");
		Utils.verifyIfDataPresent(driver, "//div[@class='grid-view-wrap clear-both']//div/p[contains(text(),'"+signingPerson+"')]", "xpath", signingPerson, "Signing person");
		driver.close();
	}
	
	public void verifyBreadCrumbsInCorpLevel(){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */
	Utils.verifyIfDataPresent(driver, "//a[@class='router-link-active']/span[contains(text(),'Corporation')]", "xpath", "Corporation  redirect link", "letter ms word page");
	}
	
	
	public void verifyBreadCrumbsInClientLevel(){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */
	Utils.verifyIfDataPresent(driver, "//a[@class='router-link-active']/span[contains(text(),'Client')]", "xpath", "Client  redirect link", "letter ms word page");
	}
	
	
	public void verifyBreadCrumbsInCaseLevel(){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 March 2020
		 */
	Utils.verifyIfDataPresent(driver, "//a[@class='router-link-active']/span[contains(text(),'Case')]", "xpath", "Case  redirect link", "letter ms word page");
	}
	
	
	public void verifyAvailableTemplateSearchBox(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 March 2020
		 */
		Utils.enterText(driver, searchBox_AvailableTemplates, template, "template name");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+template+"')]", "xpath", template, "as a result of search");
		Utils.enterText(driver, searchBox_AvailableTemplates, "JUNK TEXT", "available template search box");
		String text = "Looks like you don't have any templates defined yet, let's add one.";
		Utils.verifyIfDataPresent(driver, "//div/p[contains(text(),\"" + text + "\")]", "xpath", "unavailable message", "search box result");
		driver.close();
	}
	
	
	public void verifyAvailableTemplateSearchBoxInClient(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 March 2020
		 */
		Utils.enterText(driver, searchBox_AvailableTemplates, template, "template name");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+template+"')]", "xpath", template, "as a result of search");
		Utils.enterText(driver, searchBox_AvailableTemplates, "JUNK TEXT", "available template searchbox");
		Utils.verifyIfDataPresent(driver, "//div/p[contains(text(),\"Looks like you don't have any templates defined yet, let's add one.\")]", "xpath", "unavailable message", "search box result");
		driver.close();
	}
	
	
	public void verifyTemplatedAddingInKB(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 March 2020
		 */
		Utils.verifyIfDataPresent(driver, "//a/span[contains(text(),'"+template+"')]", "xpath", template, "MS Word Letter page");
		driver.close();
	}
	

	public void verifyIfKBTemplateDeleted(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 17 March 2020
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//a/span[contains(text(),'"+template+"')]", btn_useTemplate, "xpath", template, "on letter template page");
		driver.close();
	}
	
	public void verifyUsedText(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+template+"')]/../../../div//span[contains(text(),'Used')]", "xpath", template, "in Letter page as Used");
		driver.close();
	}
	
	public void verifyquickActionDropDownOptions(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+template+"')]/../..//ul[@class='p-0 fn_icon_list']//a[@class='dropdown-toggle p-0']", "xpath", "quick action drop down");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+template+"')]/../..//ul[@class='p-0 fn_icon_list']//a[@class='dropdown-toggle p-0']/following-sibling::ul//li//a/label[contains(text(),'Fill Pending Keywords ')]", "xpath", "Fill Pending Keywords ", "in quick action dropdown");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+template+"')]/../..//ul[@class='p-0 fn_icon_list']//a[@class='dropdown-toggle p-0']/following-sibling::ul//li//a/label[contains(text(),'Refresh Letter')]", "xpath", "Refresh Letter", "in quick action dropdown");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+template+"')]/../..//ul[@class='p-0 fn_icon_list']//a[@class='dropdown-toggle p-0']/following-sibling::ul//li//a/label[contains(text(),'Activity Log')]", "xpath", "Activity Log", "in quick action dropdown");
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+template+"')]/../..//ul[@class='p-0 fn_icon_list']//a[@class='dropdown-toggle p-0']/following-sibling::ul//li//a/label[contains(text(),'Delete')]", "xpath", "Delete", "in quick action dropdown");
		driver.close();
	}
	
	public void verifyLetterSearchBox(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		Utils.enterText(driver, searchBox_searchLetter, template, "search letter search box");
		Utils.verifyIfDataPresent(driver, "//div[@class='col-md-12 col-lg-12']//a[contains(text(),'"+template+"')]", "xpath", template, "letter page after search");
		Utils.enterText(driver, searchBox_searchLetter, "JUNK", "search letter search box");
		Utils.verifyIfDataPresent(driver, "//div[@type='KendoNativeClientSideGrid']//p[contains(text(),'No records found.')]", "xpath", "No Records found", "letter page after search");
		driver.close();
	}
	
	
	
	public void editAndVerifyStatusInDraft(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/ancestor::div[1]/a", "xpath", "status dropdown");
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'In draft')]", "xpath", "in draft status");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr//a[@class='zoom-action-dropdown-anchor has-tooltip'][contains(text(),'In draft')]", "xpath", "In draft", "status for letter");
		driver.close();
	}
	
	public void editAndVerifyStatusSendForSignature(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/ancestor::div[1]/a", "xpath", "status dropdown");
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'Sent for signature')]", "xpath", "Sent for signature as status");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr//a[@class='zoom-action-dropdown-anchor has-tooltip'][contains(text(),'Sent for signature')]", "xpath", "Sent for signature", "status for letter");
		driver.close();
	}
	
	public void editAndVerifyStatusReadyForPrinting(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/ancestor::div[1]/a", "xpath", "status dropdown");											
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/div[@class='StatusOptions open']//label[contains(text(),'Ready for printing')]", "xpath", "Ready for printing as status");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr//a[@class='zoom-action-dropdown-anchor has-tooltip'][contains(text(),'Ready for printing')]", "xpath", "Ready for printing", "status for letter");
		driver.close();
	}
	
	public void editAndVerifyStatusNotStarted(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/ancestor::div[1]/a", "xpath", "status dropdown");
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'Not started')]", "xpath", "Not started as status");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr//a[@class='zoom-action-dropdown-anchor has-tooltip'][contains(text(),'Not started')]", "xpath", "Not started", "status for letter");
		driver.close();
	}
	
	public void verifyStatusOptions(String template){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']/ancestor::div[1]/a", "xpath", "status dropdown");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'Not started')]", "xpath", "Not started", "status dropdown");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'In draft')]", "xpath", "In draft", "status dropdown");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'Sent for signature')]", "xpath", "Sent for signature", "status dropdown");
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+template+"')]/ancestor::tr[1]//ul/li[@class='dropdown status-options-list']//label[contains(text(),'Ready for printing')]", "xpath", "Ready for printing", "status dropdown");
		driver.close();
	}
	
	
	public void verifyAscendingOrderOfLetterTemplates(){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		ArrayList<String> letterList = new ArrayList<String>();
		Utils.waitForElement(driver, rows_LetterTemplate);
		List<WebElement> allRowsList = driver.findElements(By.xpath("//div[@type='KendoNativeClientSideGrid']//tr/td/a"));
		int count = allRowsList.size();
		for (int i = 1; i <= count; i ++) {
			WebElement eachRowStepName = allRowsList.get(i-1);
			letterList.add(eachRowStepName.getText().trim().toString());
		}
		Collections.sort(letterList, String.CASE_INSENSITIVE_ORDER);
		
		Utils.clickElement(driver, link_Letter, "letter link to make it ascending");
		Utils.waitForElement(driver, icon_Ascending);
		
		ArrayList<String> sortedLetterListFromUI = new ArrayList<String>();
		Utils.waitForElement(driver, rows_LetterTemplate);
		allRowsList = driver.findElements(By.xpath("//div[@type='KendoNativeClientSideGrid']//tr/td/a"));
		count = allRowsList.size();
		for (int i = 1; i <= count; i ++) {
			WebElement eachRowStepName = allRowsList.get(i-1);
			sortedLetterListFromUI.add(eachRowStepName.getText().trim().toString());
		}
		if (letterList.equals(sortedLetterListFromUI)) 
            Log.message("Ascending order working fine"); 
        else
            Log.fail("Ascending order not working fine"); 
		driver.close();
	}
	
	public void verifyDescendingOrderOfLetterTemplates(){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 18 March 2020
		 */
		ArrayList<String> letterList = new ArrayList<String>();
		Utils.waitForElement(driver, rows_LetterTemplate);
		List<WebElement> allRowsList = driver.findElements(By.xpath("//div[@type='KendoNativeClientSideGrid']//tr/td/a"));
		int count = allRowsList.size();
		for (int i = 1; i <= count; i ++) {
			WebElement eachRowStepName = allRowsList.get(i-1);
			letterList.add(eachRowStepName.getText().trim().toString());
		}
		Collections.sort(letterList, String.CASE_INSENSITIVE_ORDER);
		Collections.reverse(letterList);
		
		Utils.clickElement(driver, link_Letter, "letter link to make it ascending");
		Utils.waitForElement(driver, icon_Ascending);
		Utils.clickElement(driver, link_Letter, "letter link to make it descending");
		Utils.waitForElement(driver, icon_Descending);
		
		ArrayList<String> sortedLetterListFromUI = new ArrayList<String>();
		Utils.waitForElement(driver, rows_LetterTemplate);
		allRowsList = driver.findElements(By.xpath("//div[@type='KendoNativeClientSideGrid']//tr/td/a"));
		count = allRowsList.size();
		for (int i = 1; i <= count; i ++) {
			WebElement eachRowStepName = allRowsList.get(i-1);
			sortedLetterListFromUI.add(eachRowStepName.getText().trim().toString());
		}
		if (letterList.equals(sortedLetterListFromUI)) 
            Log.message("Descending order working fine"); 
        else
            Log.fail("Descending order not working fine"); 
		driver.close();
	}
	
	
	public void verifyLetterTemplateDescription(String templateName, String docName){
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 26 March 2020
		 */
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+templateName+"')]", "xpath", "letter template");
		Utils.waitForElement(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1][@class='text-left template_name accord-panel-heading zc-toggl active']", globalVariables.elementWaitTime, "xpath");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1][@class='text-left template_name accord-panel-heading zc-toggl active']/following-sibling::div[2]//span[contains(text(),'Document')]", "xpath", "Document text", "in doc details");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+templateName+"')]/ancestor::div[1][@class='text-left template_name accord-panel-heading zc-toggl active']/following-sibling::div[2]//a[contains(text(),'"+docName+"')]", "xpath", docName, "doc name");
		driver.close();
	}
	
	
	public void verifyPrintFunctionality(String templateName) throws Exception{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 26 March 2020
		 */
		Utils.clickDynamicElement(driver, "//a[contains(text(),'"+templateName+"')]/../preceding-sibling::td//label[@for]", "xpath", "check box");
		Utils.clickElement(driver, img_Print, "print image");
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "PrintBnfDocuments", "url", "false");
		driver.close();
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Letters - INSZoom.com", "title", "false");
		driver.close(); 
	}
	
	public void useAllTemplates() throws InterruptedException
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 30 March 2020
		 */
		if(Utils.isElementPresent(driver, "(//button[@id='useTemplate'])[1]", "xpath"))
		{
		Utils.waitForElement(driver, "(//button[@id='useTemplate'])[1]", globalVariables.elementWaitTime, "xpath");
		List<WebElement> templateList = driver.findElements(By.id("useTemplate"));
		int noOfTemplates = templateList.size();
		System.out.print(noOfTemplates);
		for(int i = 1; i <= noOfTemplates; i++)
		{
			String message = "use button for template " + i ; 
			//Utils.waitForElement(driver, "(//button[@id='useTemplate'])["+i+"]", globalVariables.elementWaitTime, "xpath");
			Utils.clickDynamicElement(driver, "(//button[@id='useTemplate'])[1]", "xpath", message);
			Utils.waitForElement(driver, txt_SuccessfullyAdded);
			Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success success_alert_msg']", "css"); 
		}
		}
		else{
			Log.message("No template to add");
		}
		driver.close();
	}

	
	public void deleteAllLetters() 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 31 March 2020
		 */
		Utils.clickElement(driver, checkBox_deleteAll, "delete all checkbox");
		Utils.clickElement(driver, img_delete, "delete img");
		Utils.clickElement(driver, btn_continueForDeleteAll, "continue button");
		Utils.verifyIfDataPresent(driver, "//div[@class='drag_drop']/p[@class='my_fav_span'][contains(text(),'No records found.')]", "xpath", "No records founds", "letter template page");
		driver.close();
	}
	
	
	

	
}