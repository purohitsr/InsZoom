package com.inszoomapp.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CM_CasePackageForFiling extends LoadableComponent<CM_CasePackageForFiling>{

	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	WebElement btn_AddPage;
	
	@FindBy(id = "selectDropdown")
	WebElement dropdown_addLetterTemplate;
	
	@FindBy(id = "addLetterTemplate")
	WebElement radioBtn_addLetterTemplate;
	
	@FindBy(id = "addBlankDocName")
	WebElement txtBox_blankPageName;
	
	@FindBy(id = "addBlank")
	WebElement radioBtn_addBlankPage;
	
	@FindBy(xpath = "//div[contains(text(),'Case Documents')]")
	WebElement dropdown_caseDocuments;
	
	@FindBy(xpath = "//div[contains(text(),'Client Documents')]")
	WebElement dropdown_clientDocuments;
	
	@FindBy(xpath = "//div[contains(text(),'Corporation Documents')]")
	WebElement dropdown_corporationDocuments;
	
    @FindBy(xpath = "//h1[contains(text(),'Package for filing')]")			//Added by Yatharth on 17th March 2020
    WebElement txt_assembleHeader;

	@FindBy(xpath = "//label[contains(text(),'Delete Package')]")		//Created by Nitisha Sinha on 18/02/2020
	WebElement label_deletePackage;
	
	@FindBy(xpath = "//button[contains(text(),'Close')]")		//Created by Nitisha Sinha on 18/02/2020
	WebElement btn_closeViewDocumentList;
		
	@FindBy(id = "headerTxtSearch")
	WebElement txtbox_searchDocuments;            //Created by Nitisha Sinha on 16/02/2020
	
	@FindBy(id = "SaveButton")		                          //Created by Nitisha Sinha on 18/02/2020
	WebElement btn_confirmDeletePackage;

	@FindBy(xpath = "//div[contains(text(),'Forms')]")              //Created by Nitisha Sinha on 17/02/2020
	WebElement header_forms; 

	@FindBy(xpath = "//div[@id='recreatePackageConfirmPopUp']//input")	//Created by Shashank on 16/02/2020
	WebElement txtbox_recreatePackageName;
	
	@FindBy(xpath = "//div[@class='modal-dialog profile_certificate_modal']/div[@id='modalContent']//a[@id='modalCloseBtn']")	//Created by Shashank on 16/02/2020
	WebElement icon_closePreview;
	
	@FindBy(xpath = "//div[@id='PackageDocuments']/following-sibling::div//input")	//Created by Shashank on 16/02/2020
	WebElement textbox_packageName;
	
	@FindBy(xpath = "//div[@id='printLogDiv']")		//Created by Shashank on 16/02/2020
	WebElement dropdown_printLog;
	
	@FindBy(xpath = "//div[contains(text(),'Forms')]")		//Created by Shashank on 16/02/2020
	WebElement dropdown_forms;
	
	@FindBy(xpath = "//div[contains(text(),'Letters')]")	//Created by Shashank on 16/02/2020
	WebElement dropdown_letters;
	
	@FindBy(xpath = "//div[contains(text(),'Supporting Documents')]")	//Created by Shashank on 16/02/2020
	WebElement dropdown_documents;
	
	@FindBy(xpath = "//button[contains(text(),'Assemble')]/..")	//Created by Shashank on 16/02/2020
	WebElement btn_assemble;
	
	@FindBy(id = "SaveButtonNew")			//Created by Shashank on 16/02/2020
	WebElement btn_saveRecreatePackage;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_CasePackageForFiling(WebDriver driver)
	{
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}
	
	
	public void clickIconSelectForms()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		
		Utils.clickElement(driver, dropdown_forms, "Forms DropDown");
		
		Utils.scrollIntoView(driver, dropdown_letters);
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+ globalVariables.formNamePackageForFiling +"')]/../../following-sibling::td/a", "xpath", "add icon");
	}
	
	
	public void clickIconSelectCaseLetters()
	{
		/*
		 * Edited by Souvik Ganguly
		 * Edited on 20/08/2021
		 * Added Sync
		 */
		Utils.waitForElement(driver, dropdown_letters);
		Utils.scrollToElement(driver, dropdown_letters);
		Utils.clickElement(driver, dropdown_letters, "Letters DropDown");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+ globalVariables.letterMSWordTemplateName +"')]/../../following-sibling::td/a", "xpath", "add icon");
		
		Utils.clickElement(driver, dropdown_letters, "Letters DropDown");
	}
	
	
	public void clickIconSelectSupportingDocs()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		
		Utils.scrollIntoView(driver, dropdown_caseDocuments);
		Utils.clickElement(driver, dropdown_caseDocuments, "Case Documents");
		WebElement caseDocument = driver.findElement(By.xpath("//div[@id='searchGrid_Case_Documents']//td/a"));
		Utils.scrollToElement(driver, caseDocument);
		Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Case_Documents']//td/a", "xpath", "add icon");
		
		Utils.scrollToElement(driver, dropdown_clientDocuments);
		Utils.clickElement(driver, dropdown_clientDocuments, "Client Documents");
		WebElement clientDocument = driver.findElement(By.xpath("//div[@id='searchGrid_Client_Documents']//td/a"));
		Utils.scrollToElement(driver, clientDocument);
		Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Client_Documents']//td/a", "xpath", "add icon");
		
		Utils.scrollToElement(driver, dropdown_corporationDocuments);
		Utils.clickElement(driver, dropdown_corporationDocuments, "Corporation Documents");
		
		WebElement corpDocument = driver.findElement(By.xpath("//div[@id='searchGrid_Corporation_Documents']//td/a"));
		Utils.scrollToElement(driver, corpDocument);
		Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Corporation_Documents']//td/a", "xpath", "add icon");
		
	}
	
	
	public void verifySelectForms()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		
		//Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.formNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.formNamePackageForFiling + "')]", "xpath", "Form", "Document Selected List");
	}
	
	
	public void verifySelectDocuments()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-check has-tooltip']", "xpath", "green tick mark", "Select grid");

		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-check has-tooltip']", "xpath", "green tick mark", "Select grid");
	}
	
	
	public void verifySelectLetters()
	{
		/*
		 * Created by Kuchinad Shashank
		 * Created on 16/03/2020
		 */
		
		//Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]/../../following-sibling::td/i[@class='fa fa-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]", "xpath", "Form", "Document Selected List");
	}

	
	public void backToCaseProfilePage() throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		//driver.close();
		
		//Utils.waitForAllWindowsToLoad(1, driver);
	
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
	}
	
	
	public void verifyAllSelectArePresent()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		verifySelectForms();
		verifySelectDocuments();
		verifySelectDocuments();
	}
	
	
	public void clickAssembleButton()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		Utils.clickElement(driver, btn_assemble, "Assemble Button");
		
	}
	
	
	public void createPackage() throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		
		globalVariables.packageName = globalVariables.packageName.substring(0, globalVariables.packageName.indexOf(" "));
		
		LocalDateTime localDate = LocalDateTime.now(); 
		globalVariables.packageName = globalVariables.packageName + " " +localDate;
		
		Utils.enterText(driver, textbox_packageName, globalVariables.packageName, "Package Name");
		
		clickAssembleButton();
		
		Utils.waitForElement(driver, "//div[@class='modal-dialog profile_certificate_modal']/div[@id='modalContent']", globalVariables.elementWaitTime, "xpath");
		
		Utils.clickElement(driver, icon_closePreview, "close preview Button");
		
	}
	
	
	public void savePackageNameToExcel(String workbookNameWrite, String sheetName) throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + workbookNameWrite);
		
		writeExcel.setCellData("SavedPackageName", sheetName, "Value", globalVariables.packageName);
		
	}
	
	
	public void clickPrintLogDropDown()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		Utils.clickElement(driver, dropdown_printLog, "Print Log DrpDown");
		
	}
	
	
	public void verifyPackageCreated(String workBookName, String sheetName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 16/03/2020
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");
		
		Utils.verifyIfDataPresent(driver, " //p[contains(text(),'" + packageName + "')]", "xpath", "Package", "Print Log List");

	}
	
	
	public void clickDownloadPackageIcon(String workBookName, String sheetName, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");
		
		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickDynamicElement(driver, "//p[contains(text(),'" + packageName + "')]/../following-sibling::td//img[@alt='Download']", "xpath", "Download Icon");
		
		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Preparing Download')]", "xpath");
		
		Thread.sleep(3000);
		
		Utils.verifyIfDownloaded(remoteDriver);
	}
	
	
	public void clickMenuDropDownIcon(String workBookName, String sheetName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");
		
		Utils.clickDynamicElement(driver, "//p[contains(text(),'"+ packageName +"')]/../following-sibling::td//a[@class='dropdown-toggle p-0']", "xpath", "dropdown menu");
		
	}
	
	
	public void verifyDownloadPackage(String workBookName, String sheetName) throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		List<String> nameOfFiles = new ArrayList<>();
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");

		Utils.waitForElementPresent(driver, "//span[contains(text(),'Preparing Download')]", "xpath");
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ packageName +".pdf')]", "xpath", "Download ", "Package for filing");

		Utils.waitForElementNotVisible(driver, "//span[contains(text(),'Preparing Download')]", "xpath");
		
		Thread.sleep(3000);
		
		File folder = new File("C:\\Users\\intern06\\Downloads");
		File[] listOfFiles = folder.listFiles();
		
		packageName = packageName.replace(":","_");
		for (int i = 0; i < listOfFiles.length; i++) 
		{
			System.out.println("File " + listOfFiles[i].getName());
			nameOfFiles.add(listOfFiles[i].getName()); 
		}
		
		if(nameOfFiles.contains(""+packageName+".pdf"))
		{
			System.out.println("Package Name" +packageName);
			Log.pass("Verfed. The file is downloaded in the download directory", driver, true);
		}
		else
		{
			System.out.println("Package Name" +packageName);
			Log.fail("File is not downloaded in the download directory", driver, true);
		}
	}

	
	public void clickRecreatePackageLink(String workBookName, String sheetName)
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");

		Utils.clickDynamicElement(driver, "//p[contains(text(),'" + packageName + "')]/../following-sibling::td//label[contains(text(),'Recreate Package')]", "xpath", "Download Icon");
	}
	
	
	public void RecreatePackage(String packageName, String dataWrite, String sheetName) throws Exception
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		
		packageName = packageName + strDate;
		
		Utils.enterText(driver, txtbox_recreatePackageName, packageName, "Package Name");
		
		Utils.clickElement(driver, btn_saveRecreatePackage, "Continue Button");
		
		Utils.waitForElementPresent(driver, "div[class='toast-alert-message alert-success show']", "css");
		Utils.waitForElementNotVisible(driver, "div[class='toast-alert-message alert-success show']", "css");
		
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		writeExcel.setCellData("SavedPackageName", sheetName, "Value", packageName);
		
	}

	
	public void verifyAllFormsPresent()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
		
		Utils.clickElement(driver, dropdown_forms, "Forms DropDown");
		
		List<WebElement> forms = driver.findElements(By.xpath("//div[@id='searchGrid_Forms']//tbody//span[contains(@class,'pointer spacing')]"));
		List<String> formName = new ArrayList<>();
		
		for(int i=0; i<forms.size(); i++)
		{
			formName.add(forms.get(i).getText());
			System.out.println(forms.get(i).getText());
		}
		System.out.println(formName);
		System.out.println(globalVariables.formsUnderCase);
		if(globalVariables.formsUnderCase.containsAll(formName))
		{
			Log.pass("Verified. All the auto genereated forms are present", driver, true);
		}
		else
		{
			Log.fail("All the forms are not present in the forms Package List", driver, true);
		}
	}
	
	
	public void verifyDeletePackage()
	{
	
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 18/03/2020
		 */
		
		Utils.verifyIfDataNotPresent(driver, "//p[contains(text(),'" + globalVariables.packageName + "')]", header_forms, "xpath", globalVariables.packageName, "package list");
		
	}
	
	
	public void deletePackage()
	{
	
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 18/03/2020
		 */
		
		Utils.clickDynamicElement(driver, "//p[contains(text(),'"+ globalVariables.packageName +"')]/../following-sibling::td//a[@class='dropdown-toggle p-0']", "xpath", "dropdown menu");
		
		Utils.clickElement(driver, label_deletePackage, "Delete Package Label");
		
		Utils.clickElement(driver, btn_confirmDeletePackage, "Delete Package Confirm Button");
		
		Utils.waitForElementPresent(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
    	
    	Utils.waitForElementNotVisible(driver, "//div[@class='toast-alert-message alert-success success_alert_msg show']", "xpath");
		
	} 

	
	public void viewDocumentList(String workBookName, String sheetName)
	{
	
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 18/03/2020
		 */
		
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");
		
		Utils.clickDynamicElement(driver, "//p[contains(text(),'"+ packageName +"')]/../following-sibling::td//label[contains(text(),'View Documents List')]", "xpath", "Label View Document List");
		
		
	} 
	
	public void verifyViewDocumentList(String workBookName, String sheetName)
	{
	
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 18/03/2020
		 */
		
		ReadWriteExcel readExcel = new ReadWriteExcel();
		
		String packageName = readExcel.initTest(workBookName, sheetName, "SavedPackageName");
		
		Utils.verifyIfDataPresent(driver, "//strong[contains(text(),'" + packageName + "')]", "xpath", packageName, "package list");
		
		Utils.clickElement(driver, btn_closeViewDocumentList, "Close button for view Document List");
		
	} 

	public void verifySearchForms(String documentName, boolean value)
	{
		/*
		 * Created by Nitisha Sinha
		 * Created on 17/03/2020
		 */
		
		Utils.enterText(driver, txtbox_searchDocuments, documentName, "search documents textbox");
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Forms']//span[contains(text(),'" + documentName + "')]", "xpath", documentName, "Forms");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//div[@id='searchGrid_Forms']//span[contains(text(),'" + documentName + "')]", header_forms, "xpath", documentName, "Forms");
		}
			
	}
	
	public void verifySearchLetters(String documentName, boolean value)
	{
		/*
		 * Created by Nitisha Sinha
		 * Created on 17/03/2020
		 */
		
		Utils.enterText(driver, txtbox_searchDocuments, documentName, "search documents textbox");
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Letters']//span[contains(text(),'" + documentName + "')]", "xpath", documentName, "Letters");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//div[@id='searchGrid_Letters']//span[contains(text(),'" + documentName + "')]", header_forms, "xpath", documentName, "Letters");
		}
			
	}
	
	
	public void verifySearchSupportingDocuments(String documentName, boolean value)
	{
		/*
		 * Created by Nitisha Sinha
		 * Created on 17/03/2020
		 */
		
		Utils.enterText(driver, txtbox_searchDocuments, documentName, "search documents textbox");
		
		if(value)
		{
			Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Case_Documents']//span[contains(text(),'" + documentName + "')]", "xpath", documentName, "Case documents");
			Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Client_Documents']//span[contains(text(),'" + documentName + "')]", "xpath", documentName, "Clients documents");
			Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Corporation_Documents']//span[contains(text(),'" + documentName + "')]", "xpath", documentName, "Corporation documents");
		}
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//div[@id='searchGrid_Case_Documents']//span[contains(text(),'" + documentName + "')]", header_forms, "xpath", documentName, "Case Documents");
			Utils.verifyIfDataNotPresent(driver, "//div[@id='searchGrid_Client_Documents']//span[contains(text(),'" + documentName + "')]", header_forms, "xpath", documentName, "Clients Documents");
			Utils.verifyIfDataNotPresent(driver, "//div[@id='searchGrid_Corporation_Documents']//span[contains(text(),'" + documentName + "')]", header_forms, "xpath", documentName, "Corporation Documents");
		}
			
	}
	
	
    public void verifyRemoveSupportingDocs()
    {
  	  /*Created by Yatharth Pandya
  	   * Created on 16/03/2020
  	  */
  	  
  	  
        //Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-plus']", "xpath", "green tick mark", "Select grid");
        Utils.verifyIfDataNotPresent(driver, "//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]", txt_assembleHeader, "xpath", "Cross mark", "Removed Supporting Docs");
    }
    
    public void verifyRemoveLetters()
    {
  	  /*Created by Yatharth Pandya
  	   * Created on 17/03/2020
  	   */
  	  
  	  
        //Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]/../../following-sibling::td/i[@class='fa fa-plus']", "xpath", "green tick mark", "Select grid");
        Utils.verifyIfDataNotPresent(driver, "//p[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]", txt_assembleHeader, "xpath", "Cross mark", "Removed Letters");
    }
    
    public void verifyRemoveForms()
    {
  	  /*Created by Yatharth Pandya
  	   * Created on 17/03/2020
  	  */
  	  
  	  
        //Utils.verifyIfDataPresent(driver, "//span[contains(text(),'" + globalVariables.formNamePackageForFiling + "')]/../../following-sibling::td/i[@class='fa fa-plus']", "xpath", "green tick mark", "Select grid");
        Utils.verifyIfDataNotPresent(driver, "//p[contains(text(),'" + globalVariables.formNamePackageForFiling + "')]", txt_assembleHeader, "xpath", "Cross mark", "Removed Forms");
  	  
    }
    
    public void clickRemoveSupportingDocs()
    {
  	  /*
  	   * Created by Yatharth Pandya
  	   * Created on 16th March 2020
  	   */
  	  Utils.waitForElement(driver, txt_assembleHeader);
  	  Utils.clickDynamicElement(driver, "//td/p[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]/../following-sibling::td/i[@class='fa fa-times pointer has-tooltip']", "xpath", "Remove icon");  	  
  	  Utils.clickDynamicElement(driver, "//td/p[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]/../following-sibling::td/i[@class='fa fa-times pointer has-tooltip']", "xpath", "Remove icon");
  	  Utils.clickDynamicElement(driver, "//td/p[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]/../following-sibling::td/i[@class='fa fa-times pointer has-tooltip']", "xpath", "Remove icon");
    }
    
    public void clickRemoveLetters()
    {
  	  /*
  	   * Created by Yatharth Pandya on 17th March 2020
  	   */
  	  Utils.waitForElement(driver, txt_assembleHeader);
  	  Utils.clickDynamicElement(driver, "//td/p[contains(text(),'"+ globalVariables.letterMSWordTemplateName +"')]/../following-sibling::td/i[@class='fa fa-times pointer has-tooltip']", "xpath", "Remove icon");
    }
    
    public void clickRemoveForms()
    {
  	  /*
  	   * Created by Yatharth Pandya on 17th March 2020
  	   */
  	  Utils.waitForElement(driver, txt_assembleHeader);
  	  Utils.clickDynamicElement(driver, "//td/p[contains(text(),'"+ globalVariables.formNamePackageForFiling +"')]/../following-sibling::td/i[@class='fa fa-times pointer has-tooltip']", "xpath", "Remove icon");  	 
  	   
    }

    
    public void verifyCaseLettersPresent()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
    	
    	Utils.clickElement(driver, dropdown_letters, "Letters DropDown");
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ globalVariables.letterMSWordTemplateName +"')]", "xpath", "MS Word Letter", "Package For Filing");
    }
    
    
    public void verifyDocumentPresent()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 17/03/2020
		 */
    	
    	Utils.clickElement(driver, dropdown_caseDocuments, "Documents DropDown");
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]", "xpath", "Case Document", "Package For Filing");
    	
    	Utils.scrollToElement(driver, dropdown_clientDocuments);
    	Utils.clickElement(driver, dropdown_clientDocuments, "Documents Dropdown");
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]", "xpath", "Client Document", "Package For Filing");
    	
    	Utils.scrollToElement(driver, dropdown_corporationDocuments);
    	Utils.clickElement(driver, dropdown_corporationDocuments, "Documents Dropdown");
    	Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ globalVariables.documentNamePackageForFiling +"')]", "xpath", "Corporation Document", "Package For Filing");
    	
    }
    
    
    public void clickAddAllFormsLink()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Forms']//a[contains(text(),'Add All')]", "xpath", "Add all forms Icon");
    }
    
    
    public void clickAddAllLettersLink()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Letters']//a[contains(text(),'Add All')]", "xpath", "Add all forms Icon");
    }
    
    
    public void clickAddAllCaseDocumentsLink()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.clickDynamicElement(driver, "//div[@id='searchGrid_Case_Documents']//a[contains(text(),'Add All')]", "xpath", "Add all forms Icon");
    }
    
    
    public void verifyAddAllForms()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Forms']//i[@class='fa fa-check add-all-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.formNamePackageForFiling + "')]", "xpath", "Form", "Document Selected List");
    }
    
    
    public void verifyAddAllLetters()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Letters']//i[@class='fa fa-check add-all-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]", "xpath", "Form", "Document Selected List");
    }
    
    
    public void verifyAddAllCaseDocuments()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.verifyIfDataPresent(driver, "//div[@id='searchGrid_Case_Documents']//i[@class='fa fa-check add-all-check has-tooltip']", "xpath", "green tick mark", "Select grid");
		Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]", "xpath", "Form", "Document Selected List");
    }
    
    
    public void clickAddBlankPageButton(String pageName) throws Exception
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.waitForElement(driver, "//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]", globalVariables.elementWaitTime, "xpath");
    	WebElement docName = driver.findElement(By.xpath("//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]"));
    	
    	Actions actions = new Actions(driver);
    	actions.moveToElement(docName).perform();
    	
    	Utils.clickDynamicElement(driver, "//i[@class='fa fa-times pointer has-tooltip']/following-sibling::i", "xpath", "Plus Icon");
    	
    	Utils.clickElement(driver, radioBtn_addBlankPage, "Add blank Page Radio Button");
    	
    	Utils.enterText(driver, txtBox_blankPageName, pageName, " Blank Page Name");
    	
    	Utils.clickElement(driver, btn_AddPage, "Add Blank Page");
    	
    	Utils.waitForAllWindowsToLoad(3, driver);
    	
    	Utils.switchWindows(driver, "INSZoom-Update Letter", "title", "false");
    	
    	driver.close();
    	
    	Utils.waitForAllWindowsToLoad(2, driver);
    	
    	Utils.switchWindows(driver, "Assemble - INSZoom.com", "title", "false");
    	
    	
    }
    
    
    public void verifyAddBlackPageButton(String pageName)
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.blankPageName + "')]", "xpath", "Form", "Document Selected List");
    }
    
    
    public void clickAddLetterTemplate() throws Exception
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.waitForElement(driver, "//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]", globalVariables.elementWaitTime, "xpath");
    	WebElement docName = driver.findElement(By.xpath("//p[contains(text(),'" + globalVariables.documentNamePackageForFiling + "')]"));
    	
    	Actions actions = new Actions(driver);
    	actions.moveToElement(docName).perform();
    	
    	Utils.clickDynamicElement(driver, "//i[@class='fa fa-times pointer has-tooltip']/following-sibling::i", "xpath", "Plus Icon");
    	
    	Utils.scrollIntoView(driver, radioBtn_addLetterTemplate);
    	
    	Utils.clickElement(driver, radioBtn_addLetterTemplate, "Add blank Page Radio Button");
    	
    	Utils.scrollIntoView(driver, dropdown_addLetterTemplate);
    	
    	Utils.selectOptionFromDropDown(driver, globalVariables.letterMSWordTemplateName, dropdown_addLetterTemplate);
    	
    	Utils.scrollIntoView(driver, btn_AddPage);
    	
    	Utils.clickElement(driver, btn_AddPage, "Letter Template");
    	
    }
    
    
    public void verifyAddLetterTemplate()
    {
    	/*
		 * Added By: Kuchinad Shashank
		 * Added On: 24/08/2020
		 */
    	
    	Utils.verifyIfDataPresent(driver, "//p[contains(text(),'" + globalVariables.letterMSWordTemplateName + "')]", "xpath", "Form", "Document Selected List");
    }
}
