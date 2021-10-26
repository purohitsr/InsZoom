package com.inszoomapp.pages;



import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;


public class StatusDocumentsLinkPage extends LoadableComponent<StatusDocumentsLinkPage> {
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	String parentWindowId;
	
	
	@FindBy(id = "UserCntrl_btnAdd")
	WebElement btn_AddStatusDocs;
	
	
	@FindBy(xpath = "//table[@summary='Status Docs']/tbody/tr/td[4]")
	List<WebElement> row_AttachedStatusDocsNameInCase;
	
	@FindBy(xpath = "//a[contains(text(),'Status Docs')]/ancestor::table[1]/../../../tr/td[4]")
	List<WebElement> row_AttachedStatusDocsName;
	
	@FindBy(id = "UserCntrl_btnAddAll")
	WebElement btn_AddAllStatusDocs;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//select[@id='UserCntrl_list1']/option"))
	static List<WebElement> rows_RemovedStatusDocs;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//select[@id='UserCntrl_list2']/option"))
	static List<WebElement> rows_AttachedStatusDocs;
	
	@FindBy(id = "btn_SaveStatusDocumentsandclosethewindow")
	WebElement btn_SaveStatusDocuments;
	
	@FindBy(id = "UserCntrl_btnRemoveAll")
	WebElement btn_RemoveAllStatusDocs;
	
	@FindBy(id = "btn_AttachRemoveStatusDocs")
	WebElement btn_AttachRemoveStatusDocs;
	
	@FindBy(how = How.CSS, using = "select[id='UserCntrl_list1']")
	static WebElement list_RemovedStatusDocs;

	public StatusDocumentsLinkPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);
		if (isPageLoaded && !(Utils.waitForElement(driver, list_RemovedStatusDocs))) {
			Log.fail("StatusDocumentsLinkPage did not open up. Site might be down.", driver, true);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public void clickAttachRemoveStatusDocsButton() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		Utils.clickElement(driver, btn_AttachRemoveStatusDocs, "attach remove status docs");
	}
	
	
	public void clickRemoveAllStatusDocsButton() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		Utils.clickElement(driver, btn_RemoveAllStatusDocs, "remove all status docs");
	}
	
	public void clickAddAllStatusDocsButton() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		Utils.clickElement(driver, btn_AddAllStatusDocs, "added all status docs");
	}
	
	public void clickSaveStatusDocsButton() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		Utils.clickElement(driver, btn_SaveStatusDocuments, "save status docs button");
	}
	
	
	
	public void removeAllStatusDocsPresent() throws Exception
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		
		clickAttachRemoveStatusDocsButton();

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
				
		clickRemoveAllStatusDocsButton();
		clickSaveStatusDocsButton();
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");
	}
	
	public void verifyRemoveAllStatusDocs()
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		
		if(row_AttachedStatusDocsName.size() == 0)
		{
			Log.pass("Verified that REMOVE ALL functionality is working fine", driver);
		}
		else
		{
			Log.fail("Unable to Verify REMOVE ALL functionality", driver);
		}
	}
	
	public List<String> getRemovedStatusDocsList() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		List<String> removedStatusDoc = new ArrayList<String>();
		for (int i = 0; i < rows_RemovedStatusDocs.size(); i++) 
		{
			Utils.waitForElement(driver, rows_RemovedStatusDocs.get(i));
			removedStatusDoc.add(rows_RemovedStatusDocs.get(i).getText().trim().toString());
		}
		return removedStatusDoc;
	}
	
	
	public List<String> getAttachedStatusDocsList() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		List<String> attachedStatusDocsList = new ArrayList<String>();
		try {
			for (int i = 0; i < row_AttachedStatusDocsName.size(); i++) 
			{
				Utils.waitForElement(driver, row_AttachedStatusDocsName.get(i));
				attachedStatusDocsList.add(row_AttachedStatusDocsName.get(i).getText().trim().toString());
			}
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to fetch status docs list. Error Message - " + e.getMessage());
		}
		return attachedStatusDocsList;
	}

	
	public void addAllStatusDocumentsAndVerify() throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		
		List<String> attachedStatusDoc = new ArrayList<String>();
		List<String> removedStatusDoc = new ArrayList<String>();
		
		clickAttachRemoveStatusDocsButton();
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
			
		clickRemoveAllStatusDocsButton();
		removedStatusDoc = getRemovedStatusDocsList();
		clickAddAllStatusDocsButton();				
		
		clickSaveStatusDocsButton();
				
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");	
		
		attachedStatusDoc = getAttachedStatusDocsList();

		if (attachedStatusDoc.containsAll(removedStatusDoc)) {
			Log.pass("ADD ALL functionality in status docs is working fine :\n" + attachedStatusDoc, driver, true);

		} else {
			Log.fail("ADD ALL functionality in status docs is NOT working fine:\n" + attachedStatusDoc, driver, true);
		}
	}
	
	public void clickAddStatusDocsButton()
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 25 Oct 2019
 		 */
		
		Utils.clickElement(driver, btn_AddStatusDocs, "Add status docs button");
		
	}
	
	
	public void addStatusDocs(String[] documents ,boolean screenshot ) 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 25 Oct 2019
 		 */

		try {
			Utils.waitForElement(driver, list_RemovedStatusDocs);
			Select documentOption = new Select(list_RemovedStatusDocs);
			for (String eachDocument : documents) {
				Log.message("Document name:" + eachDocument);
				documentOption.selectByVisibleText(eachDocument);
				Log.message("Selected " + eachDocument);
			}

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to select options from drop down. Error Message - " + e.getMessage());
		}
		clickAddStatusDocsButton();
	}
	
	
	
	public void attachStatusDocsAndVerify(String[] documents,Boolean screenshot) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		
		clickAttachRemoveStatusDocsButton();

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
		
		clickRemoveAllStatusDocsButton();	
			
		addStatusDocs(documents,true);	
			
		clickSaveStatusDocsButton();
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client Status Document Dates", "title", "false");

		List<String> attachedStatusDocs = new ArrayList<String>();
		attachedStatusDocs = getAttachedStatusDocsList();

		Log.message(
				Arrays.asList(documents).size() + " status docs to be added are :" + Arrays.asList(documents).toString() + "\n");
		Log.message(attachedStatusDocs.size() + "  status docs added :" + attachedStatusDocs + "\n");

		if (attachedStatusDocs.containsAll(Arrays.asList(documents))) {
			Log.pass("Edited and Attached documents are showing fine :\n" + attachedStatusDocs, driver, true);

		} else {
			Log.fail("Edited and Attached documents are not listed/updated:\n" + attachedStatusDocs, driver, true);

		}
	}
	
	
	public List<String> getAttachedStatusDocsListInCaseLevel() 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 23 Oct 2019
 		 */
		List<String> attachedStatusDocsList = new ArrayList<String>();
		try {
			for (int i = 0; i < row_AttachedStatusDocsNameInCase.size(); i++) 
			{
				Utils.waitForElement(driver, row_AttachedStatusDocsNameInCase.get(i));
				attachedStatusDocsList.add(row_AttachedStatusDocsNameInCase.get(i).getText().trim().toString());
			}
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to fetch status docs list. Error Message - " + e.getMessage());
		}
		return attachedStatusDocsList;
	}
	
	
	
	public void addAllStatusDocumentsAndVerifyInCanadaCase() throws Exception {
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 25 Oct 2019
 		 */
		
		List<String> attachedStatusDoc = new ArrayList<String>();
		List<String> removedStatusDoc = new ArrayList<String>();
		
		clickAttachRemoveStatusDocsButton();
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
			
		clickRemoveAllStatusDocsButton();
		removedStatusDoc = getRemovedStatusDocsList();
		clickAddAllStatusDocsButton();				
		
		clickSaveStatusDocsButton();
				
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Status Documents", "title", "false");	
		
		attachedStatusDoc = getAttachedStatusDocsListInCaseLevel();

		if (attachedStatusDoc.containsAll(removedStatusDoc)) {
			Log.pass("ADD ALL functionality in status docs is working fine :\n" + attachedStatusDoc, driver, true);

		} else {
			Log.fail("ADD ALL functionality in status docs is NOT working fine:\n" + attachedStatusDoc, driver, true);
		}
	}
	
	
	
	public void attachStatusDocsAndVerifyInCase(String[] documents,Boolean screenshot) throws Exception 
	{
		/*
 		 * Created By : M Likitha Krishna
 		 * Created On : 25 Oct 2019
 		 */
		
		clickAttachRemoveStatusDocsButton();

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Attach/Remove Status Documents", "title", "false");
		
		clickRemoveAllStatusDocsButton();	
			
		addStatusDocs(documents,true);	
			
		clickSaveStatusDocsButton();
			
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Status Documents", "title", "false");	

		List<String> attachedStatusDocs = new ArrayList<String>();
		attachedStatusDocs = getAttachedStatusDocsListInCaseLevel();

		Log.message(
				Arrays.asList(documents).size() + " status docs to be added are :" + Arrays.asList(documents).toString() + "\n");
		Log.message(attachedStatusDocs.size() + "  status docs added :" + attachedStatusDocs + "\n");

		if (attachedStatusDocs.containsAll(Arrays.asList(documents))) {
			Log.pass("Edited and Attached documents are showing fine :\n" + attachedStatusDocs, driver, true);

		} else {
			Log.fail("Edited and Attached documents are not listed/updated:\n" + attachedStatusDocs, driver, true);

		}
	}

	
	public void verifyClientStatusDocsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 06 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com - View Client Status Document Dates");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of View Client Status Document Dates Page Failed ", driver);
	    }
		
		
	}
	
	
	
}