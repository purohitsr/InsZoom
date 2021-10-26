package com.inszoomapp.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.AppDataUAT;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_KnowledgeBasePage extends LoadableComponent<CM_KnowledgeBasePage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	@FindBy(id = "btn_SaveCaseRequestTemplatedetails")
	WebElement btn_saveCaseRequestTemplatedetails; 				//Added by Yatharth Pandya on 26th Sept, 2020
	
	@FindBy(xpath = "//textarea[@name='txtTemplDesc']")
	WebElement txtbox_caseTemplateDescription;				//Added by Yatharth Pandya on 26th Sept, 2020
	
	@FindBy(xpath = "//input[@name='txtTemplName']")
	WebElement txtbox_caseTemplateName;						//Added by Yatharth Pandya on 26th Sept, 2020
	
	@FindBy(id = "btn_ChoosePetition")
	WebElement link_choosePetitionForCaseRequestTemplate;			//Added by Yatharth Pandya on 26th Sept, 2020
	
	@FindBy(id = "btn_AddCaseRequestTemplate")
	WebElement btn_addCaseRequestTemplate; 				//Added by Yatharth Pandya on 26th Sept, 2020
	
	@FindBy(id = "LMLeftTab59")
	WebElement tab_caseRequestTemplate;			//Added by Yatharth Pandya on 25th Sept, 2020
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_Filter_pet_name")
	WebElement dropdown_filterActiveFirmPetitionTemplate;							//Added by Yatharth on 15th Sept,2020
	
	@FindBy(id = "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl03_FilterTextBox_pet_name")
	WebElement txtbox_searchPetitionTemplate;														//Added by Yatharth on 15th Sept, 2020
	
	@FindBy(xpath = "//td[contains(text(),'Corporation Questionnaires')]")
	WebElement header_corporationQuestionaire;             //Added by Nitisha Sinha on 14th sept, 2020
	
	@FindBy(id = "LMARROWIMGFirmQst")
	WebElement option_QuestionnaireTemplate;             //Added by Nitisha Sinha on 11th sept, 2020
	
	@FindBy(xpath = "//div[@id='FirmQst']//td[contains(text(),'Corporation Related')]")
	WebElement tab_corporationQuestionnaireTemplate;        //Added by Nitisha Sinha on 11th sept, 2020
	
	@FindBy(id = "btn_Searchdocument")
	WebElement btn_findLetterTemplate;
	
	//added By Saurav on 4th March 2020
	
		@FindBy(xpath = "//span[contains(text(),'Copy/Merge Questionnaires')]")
		WebElement tab_CopyMergeQuestionnaire;
	      
	    
	    @FindBy(xpath = "//span[contains(text(),'Letter Templates (HTML)')]")
		WebElement tab_LetterTemplate;
	    
	    @FindBy(xpath = "//span[contains(text(),'Letter Templates(MSWord)')]")
		WebElement tab_LetterTemplateMsWord;
	    
	    
	    @FindBy(xpath = "//td[@title='Click here to view Firm Doc Checklist Items']")
		WebElement tab_DocCheckList;
		
		//
	
	@FindBy(id = "RadComboBox1_Arrow")
	WebElement arrow_templateFor;
	
	@FindBy(id = "btn_Save")
	WebElement btn_SaveContractTemplate;
	
	@FindBy(id = "RadComboBox1_Input")
	WebElement dropDown_templateFor;
	
	@FindBy(id = "txtDesciption")
	WebElement textBox_contractTemplateDescription;
	
	@FindBy(id = "txtName")
	WebElement textBox_contractTemplateName;
	
	@FindBy(id = "btn_Add")
	WebElement btn_addContractTemplate;
	
	@FindBy(xpath = "//td[@title='Click to view Contract Templates List']")
	WebElement tab_ContactTemplates;
	
	@FindBy(id = "btn_AddnewLetterTemplate(HTML)")
	WebElement btn_AddNewLetterTemplateHTML;
	
	@FindBy(id = "btn_SaveLetterTemplate(HTML)andCloseWindow")
	WebElement btn_SaveAndCloseLetterTemplateHTML;

	@FindBy(css = "html > body")
	WebElement textBox_HTMLLetterTemplatesDescription;

	@FindBy(id = "txtTmplName")
	WebElement textBox_HTMLLetterTemplatesName;
	
	@FindBy(id = "LMWordDocTmpl")
	WebElement tab_LetterTemplatesHTML;
	
	@FindBy(id="btn_Deleteselecteddocuments")
	WebElement btn_deleteDocuments;
	
	@FindBy(id="LMDocs")
	WebElement tab_docChecklistItems;
	
	@FindBy(id="btn_SearchDocChecklistItem")
	WebElement btn_findChecklistTemplate;
	
	@FindBy(css = "input[name='txtDocSearchText']")
	WebElement searchbox_checklistTemplate;
	
	@FindBy(xpath = "//input[@id='btn_Save']")
	WebElement btn_saveLetterTemplate;
	
	@FindBy(xpath = "//input[@value='Remove']")
	WebElement text_Remove;
	
	@FindBy(xpath = "//textarea[@id='txtDesciption']")
	WebElement textBox_LetterTemplateDescription;
	
	@FindBy(xpath = "//input[@id='txtName']")
	WebElement textBox_LetterTemplateName;
	
	@FindBy(xpath = "//input[@id='btn_Add']")
	WebElement btn_addLetterTemplate;
	
	@FindBy(xpath = "//span[contains(text(),'Letter Templates(MSWord')]")
	WebElement tab_LetterTemplatesMSWord;
	
	@FindBy(css = "td[title='Click here to view Document Tags/Folders'] > span")
	WebElement tab_documentTagsFolders;
	
	@FindBy(xpath = "//td[@title='Click here for Docs Check List']")
	WebElement tab_docsCheckList;
	
	@FindBy(xpath = "//a[@title='Zoom Petition Templates']")
	WebElement txt_ZoomPetitionTemplates;
	
	@FindBy(id = "LMEmailTmpl")
	WebElement tab_EmailTemplates;
	
	@FindBy(id = "LMFirmQst")
	WebElement tab_QuestionnaireTemplate;
	
	@FindBy(id = "btn_Savenewadditionalexpirationreminderdatetemplate")
	WebElement btn_SaveTemplate;
	
	@FindBy(id = "txtExprName")
	WebElement textBox_TemplateDescription;
	
	@FindBy(id = "btn_Addnewadditionalexpirationreminderdatetemplate")
	WebElement btn_AddExpirationDatesTemplate;
	
	@FindBy(xpath = "//td[@id='pageHDR'][contains(text(),'Firm Petition Templates')]")
	WebElement header_firmPetitionTemplates;
	
	@FindBy(id = "LMExpTmpl")
	WebElement tab_ExpirationDatesTemplates;
	
	public CM_KnowledgeBasePage(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Knowledge page didnt loaded");
		}

	}
	
	 public void clickExpirationDatesTemplateTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */
	   Utils.clickElement(driver, tab_ExpirationDatesTemplates, "Document expiration Dates templates tab");        
	 }
	 
	 public void addNewExpirationDateTemplate(WebDriver driver,String workbookNameWrite, String sheetName) throws Exception
     {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */
 		
	   Utils.clickElement(driver, btn_AddExpirationDatesTemplate, "add expiration dates templates");
	   Utils.waitForAllWindowsToLoad(2, driver);
       Utils.switchWindows(driver, "INSZoom.com - Add Expiration Date Template", "title", "false");
       Random random = new Random();
       String expirationTemplateName = "ExpirationDateTemplate_" + (random.nextInt(1000));
       Utils.enterText(driver, textBox_TemplateDescription, expirationTemplateName, "template description");
       String directory = System.getProperty("user.dir");
       ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
       writeExcel.setCellData("QA_A_Client_DocumentExpirationsName", sheetName, "Value", expirationTemplateName);
       Utils.clickElement(driver, btn_SaveTemplate, "save");      
       Utils.switchWindows(driver, "INSZoom.com - Expiration Dates Templates", "title", "false");
     }
	 
	 public void verifyPetitionTemplate(boolean screenshot) throws Exception 
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'Firm Petition Templates')]", "xpath", "Header - Firm Petition Templates", "Petitoin templates");
	}
	 
	 public void clickQuestionnaireTemplateTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */
	   Utils.clickElement(driver, tab_QuestionnaireTemplate, "Questionnaire Template tab");        
	 }
	 
	 
	 public void verifyQuestionnaireTemplate(boolean screenshot) throws Exception 
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickQuestionnaireTemplateTab();
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'Questionnaire List')]", "xpath", "Header - Questionnaire List", "Questionnaire List Page");
	}
	 
	 public void clickEmailTemplatesTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */
	   Utils.clickElement(driver, tab_EmailTemplates, "Email Templates tab");        
	 }
	 
	 public void verifyEmailTemplatesPage(boolean screenshot) throws Exception 
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		clickEmailTemplatesTab();
		Utils.verifyIfDataPresent(driver, "//td[@id='pageHDR'][contains(text(),'Firm Defined Email Templates')]", "xpath", "Header - Firm Defined Email Templates", "Email Templates Page");
	}
	 
	 public void clickZoomPetitionTemplates()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		 Utils.clickElement(driver, txt_ZoomPetitionTemplates, "Zoom petition templates link");
		 Utils.waitUntilLoaderDisappear(driver);
	 }
	 
	 public void clickOnZoomPetitionTemplateName(String templateName)
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.clickDynamicElement(driver, "//div[@id='ctl00_MainContent_ctl00_ctl00_MainContent_ctl00_RadGridListPanel']//a[contains(text(),'"+ templateName +"')]", "xpath", "Petition template name");
	 }
	 
	 
	 public void clickDocsCheckListTab()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Oct 2019
		  */
		Utils.clickElement(driver, tab_DocCheckList, "docs check list tab");
	 }
	
	 
	 public void clickDocumentTagsFolders()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 01 April 2020
		  * 
		  */
		 
		 Utils.clickElement(driver, tab_documentTagsFolders, "Document Tags/Folders");
	 }
	 
	 
	 public void clickLetterTemplateMSWordTab()
	  {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 17 March 2020
		  */
		Utils.clickElement(driver, tab_LetterTemplatesMSWord, "letter template (MS Word) tab");
	  }
	 
	 public void addMSLetterTemplate(String newTemplateName, String description, String filePath) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 17 March 2020
		  */
		 Utils.clickElement(driver, btn_addLetterTemplate, "add letter template button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Add Letter Template(MSWord)", "title", "false");
		 Utils.enterText(driver, textBox_LetterTemplateName, newTemplateName, "name");
		 Utils.enterText(driver, textBox_LetterTemplateDescription, description, "description");
		 
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
			 Utils.waitForElement(driver, "//input[@id='txtFile_BtnSubmit11_input']", globalVariables.elementWaitTime, "xpath");													
			 droparea = driver.findElement(By.xpath("//input[@id='txtFile_BtnSubmit11_input']"));
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
			
//		 input.sendKeys(path.getAbsoluteFile().toString()); 
		 
		 Utils.waitForElement(driver, text_Remove);
	        
		 Utils.clickElement(driver, btn_saveLetterTemplate, "save button");
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - MS Word Letter Template List", "title", "false");
	        
		 Utils.enterText(driver, searchbox_checklistTemplate, newTemplateName, "Petition Template searchbox");
			 
		 Utils.clickElement(driver, btn_findLetterTemplate, "Find");
	        
		 Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+newTemplateName+"')]", "xpath", newTemplateName, "on letter template page KB level");
	 }
	 
	
	 public void  deleteMSLetterTemplate(String newTemplateName)
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 17 March 2020
		  */
		Utils.clickDynamicElement(driver, "//td/a[contains(text(),'"+newTemplateName+"')]/../../td/a[contains(text(),'Delete')]", "xpath", "delete text");
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		Utils.verifyIfDataNotPresent(driver, "//td/a[contains(text(),'"+newTemplateName+"')]", btn_addLetterTemplate, "xpath", newTemplateName, "letter template page on KB");
	}
	 
	 
	 public void searchPetitionTemplate(String docChecklistName)
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 20 April 2020
		  * 
		  */
		 
		 Utils.enterText(driver, searchbox_checklistTemplate, docChecklistName, "Petition Template searchbox");
		 
		 Utils.clickElement(driver, btn_findChecklistTemplate, "Find");
		 
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + docChecklistName + "')]", "xpath", docChecklistName, "searched Checklist template");
	 }
	 
	 
	 public void deletePetitionTemplate(String docChecklistName) throws Exception
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 20 April 2020
		  * 
		  */
		 
		 Utils.clickDynamicElement(driver, "//a[contains(text(), 'Doc')]/../preceding-sibling::td//img[@alt='Delete Doc Checklist Items']", "xpath", "Delete icon");
	
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com :: List of Documents to Delete", "title", "false");
		
		 Utils.clickElement(driver, btn_deleteDocuments, "Delete Documents");
		 
		 driver.switchTo().alert().accept();
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - My Firm Doc Checklist Items", "title", "false");
	 }
	 
	 
	 public void clickDocChecklistItemsTab()
	 {
		 /*
		  * Created By : Saksham Kapoor
		  * Created On : 20 April 2020
		  * 
		  */
		 
		 Utils.clickElement(driver, tab_docChecklistItems, "Doc Checklist Items");
	 }
	 
	 
	 public void  clickLetterTemplateHTMLTab()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 02 April 2020
		  */
		 Utils.clickElement(driver, tab_LetterTemplatesHTML, "HTML Letter Template tab");
	 }
	 
	 
	 public void  addLetterTemplateHTML(String templateName, String templateDescription) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 02 April 2020
		  */
		 Utils.clickElement(driver, btn_AddNewLetterTemplateHTML, "add new button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Add new Letter Template (HTML)", "title", "false");
		 Utils.enterText(driver, textBox_HTMLLetterTemplatesName, templateName, "HTML template name");
		 
		 Utils.waitForElement(driver, "txtTmplDesc__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");

		 driver.switchTo().frame("txtTmplDesc__ctl0_Editor_contentIframe");
		 Utils.enterText(driver, textBox_HTMLLetterTemplatesDescription, templateDescription, "HTML template description");
		 driver.switchTo().defaultContent();
		 Utils.clickElement(driver, btn_SaveAndCloseLetterTemplateHTML, "save and close button");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Letter Templates (HTML) List", "title", "false");
		 Utils.enterText(driver, searchbox_checklistTemplate, templateName, "Petition Template searchbox");
		 Utils.clickElement(driver, btn_findLetterTemplate, "Find");
		 Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+templateName+"')]", "xpath", templateName, "in knowlegebase");
	 }
	 
	 public void  clickContractTemplatesTab()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 29 April 2020
		  */
		 Utils.clickElement(driver, tab_ContactTemplates, "Contract Template tab");
	 }
	 
	 public void  addContractTemplate(String templateName, String templateDescrition, String templateFor, String filePath) throws Exception
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 29 April 2020
		  */
		 Utils.clickElement(driver, btn_addContractTemplate, "add contract template");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "INSZoom.com - Add Contract Template", "title", "false");
		 Utils.enterText(driver, textBox_contractTemplateName, templateName, "template name");
		 Utils.enterText(driver, textBox_contractTemplateDescription, templateDescrition, "template description");
		 Utils.clickElement(driver, arrow_templateFor, "arrow to show list of template for");
		 Utils.clickDynamicElement(driver, "//label[contains(text(),'"+templateFor+"')]/input", "xpath", "template for");
		 Utils.clickElement(driver, arrow_templateFor, "arrow to close template for list");
		 
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
			
//			input.sendKeys(path.getAbsoluteFile().toString()); 
			
			Utils.waitForElement(driver, "//input[@value='Remove']", globalVariables.elementWaitTime, "xpath");
			
			Utils.clickElement(driver, btn_SaveContractTemplate, "save button");
			
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - List of Contract Templates", "title", "false");
			
			Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+templateName+"')]", "xpath", templateName, "template list");
	 }
	 
	 
	 
	 
	 public void VerifyFirmDefinedEmailTemplatePage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  */
			    try{
				 
				 Utils.softVerifyPageTitle(driver, "INSZoom.com - Firm Defined Email Templates");
				
			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Firm Defined Email Templates page failed", driver);
			    }

		}
		
		 public void VerifyMyFirmDocCheckListPage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  */
			    try{
				 
				 Utils.softVerifyPageTitle(driver, "INSZoom.com - My Firm Doc Checklist Items");
				                                  
				                                    
			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - My Firm  Doc Checklist Items page failed", driver);
			    }

		}
		
		
		public void VerifyQuestionnaireListPage() 
		{
				 /*
				  * Created By : Saurav Purohit
				  * Created On : 4th March 2020
				  */
			    try{
				 
				 Utils.softVerifyPageTitle(driver, "INSZoom.com - Questionnaire List");
				
			    }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - Questionnaire List page failed", driver);
			    }

		}
		
		
		 public void clickCopyMergeQuestionnaireTab()
		  {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th march 2020
			  */
			Utils.clickElement(driver, tab_CopyMergeQuestionnaire, "Copy merge questionnaire tab");
		  }
	
		 public void VerifyCopyMergeQuestionnairePage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
				    try{
					 
					 Utils.softVerifyPageTitle(driver, "INSZoom.com - Copy/Merge Questionnaires");
					
				    }catch(Exception e){
					 Log.failsoft("Verification of INSZoom.com - Copy/Merge Questionnaires page failed", driver);
				    }

			}
		 
		 
		 public void clickLetterTemplateTab()
		  {
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 4th march 2020
			  */
			Utils.clickElement(driver, tab_LetterTemplate, "Letter Template tab");
		  }
		 
		 public void VerifyLetterTemplatePage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
		    try{
		    	
//			     Utils.softVerifyPageTitle(driver, "INSZoom.com - MS Word Letter Template List");
		    	Utils.softVerifyPageTitle(driver, "INSZoom.com - Letter Templates (HTML) List");
			     
					
		       }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - MS Word Letter Template List page failed", driver);
			    }

			}
		 
		 
		 
		/* public void clickLetterTemplateMSWordTab()
		  {
			 
			  * Created By : Saurav Purohit
			  * Created On : 4th march 2020
			  
			Utils.clickElement(driver, tab_LetterTemplateMsWord, "Letter Template(MS Word) tab");
		  }*/
		 
		 public void VerifyLetterTemplateMSWordPage() 
			{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 4th March 2020
					  */
		    try{
					 
			     Utils.softVerifyPageTitle(driver, "INSZoom.com - MS Word Letter Template List");
					
		       }catch(Exception e){
				 Log.failsoft("Verification of INSZoom.com - MS Word Letter Template List page failed", driver);
			    }

			}
	 
	 
	 public void verifyKBEditAccess(boolean value)
	 {
		 /*
		  * Created By : Kuchinad Shashank
		  * Created On : 30th July 2020
		  */
		 
		 if(value)
		 {
			 Utils.verifyIfDataPresent(driver, "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_ctl00", "id", "Add/Edit Button", "Knowledge Base");
		 }
		 else
		 {
			 Utils.verifyIfDataNotPresent(driver, "ctl00_MainContent_ctl00_RadGridList_ctl00_ctl02_ctl00_ctl00", header_firmPetitionTemplates, "id", "Add/Edit Button", "Knowledge Base");
		 }
		 
	 }
	 
	 
	 public void clickCorporationQuestionnaireTemplateTab() throws InterruptedException
	  {
		 /*
		  * Created By : Nitisha Sinha
		  * Created On : 11th September 2020
		  */
		 Utils.waitForPageLoad(driver);
		 Actions action = new Actions(driver);
		 
		try{
		 action.moveToElement(option_QuestionnaireTemplate).build().perform();
		 action.moveToElement(tab_corporationQuestionnaireTemplate).build().perform();
		 
		 tab_corporationQuestionnaireTemplate.click();
		 
		 Utils.waitForElement(driver, header_corporationQuestionaire);
		 Log.message("Clicked on "+ tab_corporationQuestionnaireTemplate);
		 
		}catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to click on " + tab_corporationQuestionnaireTemplate + ". ERROR :\n\n " + e.getMessage());
		}
	 }
	 
	 
	 public void deleteAllMSLetterTemplate()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 28 September 2020
		  */
		 
		 while(Utils.isElementPresent(driver, "(//a[contains(text(),'Letter Template')]/../..//a[contains(text(),'Delete')])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//a[contains(text(),'Letter Template')]/../..//a[contains(text(),'Delete')])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
	 }
	 
	 public void deleteAllHTMLLetterTemplate()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 28 September 2020
		  */
		 while(Utils.isElementPresent(driver, "(//td[contains(text(),'HTML Letter Template')]/..//img[@alt='Delete Letter Template (HTML)'])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//td[contains(text(),'HTML Letter Template')]/..//img[@alt='Delete Letter Template (HTML)'])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
	 }
	 
	 public void deleteAllContractsTemplates()
	 {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 28 September 2020
		  */
		 while(Utils.isElementPresent(driver, "(//a[contains(text(),'Add')]/../..//img[@alt='Delete Attachment'])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//a[contains(text(),'Add')]/../..//img[@alt='Delete Attachment'])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
		 while(Utils.isElementPresent(driver, "(//a[contains(text(),'Delete Template')]/../..//img[@alt='Delete Attachment'])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//a[contains(text(),'Delete Template')]/../..//img[@alt='Delete Attachment'])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
		 while(Utils.isElementPresent(driver, "(//a[contains(text(),'KB Template')]/../..//img[@alt='Delete Attachment'])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//a[contains(text(),'KB Template')]/../..//img[@alt='Delete Attachment'])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
		 while(Utils.isElementPresent(driver, "(//a[contains(text(),'Rename')]/../..//img[@alt='Delete Attachment'])[1]", "xpath"))
		 {
			 Utils.clickDynamicElement(driver, "(//a[contains(text(),'Rename')]/../..//img[@alt='Delete Attachment'])[1]", "xpath", "Delete");
			 driver.switchTo().alert().accept();
		 }
	 }
	 
	 public void searchAndVerifyActiveFirmPetitionTemplate(String petitionName) throws Exception
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 15th Sept, 2020
		 

		 Utils.enterText(driver, txtbox_searchPetitionTemplate, petitionName, "Search Petition Template textbox");
		 Utils.clickElement(driver, dropdown_filterActiveFirmPetitionTemplate, "Filter");
		 Utils.clickDynamicElement(driver, "//span[contains(text(),'Contains')]", "xpath", "Contains filter");
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(),'" + petitionName + "')]", "xpath", petitionName, "Petition template list");
	 }
	 
	 
	 public void clickPetitionTemplate(String petitionName)
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 16th Sept, 2020
		 
		 Utils.clickDynamicElement(driver, "//a[contains(text(),'" + petitionName + "')]","xpath" , petitionName);
		 
	 }
	 
	 
	 public void clickCaseRequestTemplateTab()
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 25th Sept,2020
		 
		 Utils.clickElement(driver, tab_caseRequestTemplate, "Case Request Template");
	 }
	 
	 
	 public void addNewCaseTemplate(String petition, String templateName, String templateDescription) throws Exception
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 26th Sept, 2020
		 
		 Utils.clickElement(driver, btn_addCaseRequestTemplate, "Add case template button");
		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver, "kb_caserequest_template_add", "url", "false");
		 Utils.enterText(driver, txtbox_caseTemplateName, templateName, templateName);
		 Utils.enterText(driver, txtbox_caseTemplateDescription, templateDescription, templateDescription);
		 Utils.clickElement(driver, link_choosePetitionForCaseRequestTemplate, "Choose petition");
		 Utils.waitForAllWindowsToLoad(3, driver);
		 Utils.switchWindows(driver, "INSZoom.com :: Choose Petition", "title", "false");
		 WebElement checkbox_petition = driver.findElement(By.xpath("//td[contains(text(),'" + petition + "')]/preceding-sibling::td/input[@type='CheckBox']"));
		 
		 if(!checkbox_petition.isSelected())
			 Utils.clickElement(driver, checkbox_petition, "Checkbox checked");
		 
		 Utils.clickElement(driver, btn_SaveContractTemplate, "'Save' Petition template");
//		 Utils.waitForAllWindowsToLoad(2, driver);
		 Utils.switchWindows(driver,"kb_caserequest_template_add", "url", "false");
		 Utils.clickElement(driver, btn_saveCaseRequestTemplatedetails, "Save Case Template");
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.switchWindows(driver, "INSZoom.com :: Case Request Template", "title", "false");
		 Utils.waitForElement(driver, btn_addCaseRequestTemplate);
	 }
	 
	 
	 public void verifyCaseRequestTemplatePresent(String templateName)
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 26th Sept, 2020
		 
		 Utils.waitForElement(driver, btn_addCaseRequestTemplate);
		 Utils.verifyIfDataPresent(driver, "//a[contains(text(),'"+ templateName +"')]", "xpath", templateName, "Case template list");
	 }
	 
	 
	 public void deleteCaseRequestTemplate(String templateName)
	 {
		 //Created by : Yatharth Pandya
		 //Created on : 26th Sept, 2020
		 
		 Utils.waitForElement(driver, btn_addCaseRequestTemplate);
		 Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ templateName +"')]/../preceding-sibling::td/a/img[@title='Delete Case Request Template']", "xpath", "Teamplate Deleted");
		 if(Utils.isAlertPresent(driver))
			{
				driver.switchTo().alert().accept();
			}
		 
		 Utils.waitForAllWindowsToLoad(1, driver);
		 Utils.verifyIfDataNotPresent(driver, "//a[contains(text(),'"+ templateName +"')]", btn_addCaseRequestTemplate, "xpath", templateName, "Case Template list");	 
	 }
	 
	 
	 public void clickCaseRequestTemplate(String templateName)
	 {
	 //Created by : Yatharth Pandya
	 //Created on : 9th Oct,2020

	 Utils.clickDynamicElement(driver, "//a[contains(text(),'"+ templateName +"')]", "xpath", "Case Request Template List");

	 }


}