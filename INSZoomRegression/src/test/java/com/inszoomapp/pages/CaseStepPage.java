package com.inszoomapp.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class CaseStepPage extends LoadableComponent<CaseStepPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(id = "btn_ChangeCaseStatus")
    WebElement btn_ChangeCaseStatus;
	
	@FindBy(id = "txtCompletedDate")
    WebElement textBox_CompletedDate;
	
	@FindBy(id = "btn_SendCaseStatusEmail")
    WebElement btn_SendCaseStatusEmail;
	
	@FindBy(xpath = "//textarea[@id='txtSubject']")
    WebElement txt_subject;
		
	@FindBy(xpath = "//td[contains(text(),'Step Case Managers')]/../following-sibling::tr[1]/td//a[@title='Compose Email']")
    WebElement img_composeEmail;
	
	@FindBy(id = "btn_Saveothercasemanagers")
    WebElement btn_saveCaseManagerPage;
	
	@FindBy(xpath = "//tr[@class='TBLROW1'][1]/td[4]")
    WebElement txt_caseManagerType;
	
	@FindBy(xpath = "//tr[@class='TBLROW1'][1]/td[3]")
    WebElement txt_caseManagerName;
	
	@FindBy(xpath = "//tr[@class='TBLROW1'][1]/td[2]")
    WebElement txt_caseManagerId;
	
	@FindBy(xpath = "//a[contains(text(),'Other Case Managers')]")
    WebElement lnk_otherCaseManagers;
	
	@FindBy(id = "btn_Savedata")
    WebElement btn_SaveData;
	
	@FindBy(xpath = "//input[@id='btn_PreviousStep']/../following-sibling::td/input[@id='btn_NextStep']")
    WebElement btn_nextInAction;
	
	@FindBy(id = "btn_GoToStepsList")
    WebElement btn_GoToStepsList;
	
	@FindBy(id = "btn_Finish")
    WebElement btn_Finish;
	
	@FindBy(xpath = "//input[@id='chk_I140_Date']")
    WebElement checkBox_updateData;
	
	@FindBy(xpath = "//td[contains(text(),'Update Data')]/preceding-sibling::td[1]/input")
    WebElement checkBox_ActionName;

	@FindBy(id="btn_NextStep")
    WebElement btn_NextStep;
	
	@FindBy(xpath = "//input[@value='STP_STS_CHG_2_N']")
    WebElement checkBox_StepStatusNotStarted;
	
	@FindBy(id = "btn_AddNewEvent")
    WebElement btn_AddNewEvent;
	
	@FindBy(id = "btn_DeleteCasesteps")
    WebElement btn_DeleteCasesteps;
	
	@FindBy(id = "btn_SaveCasestatusdetails")
    WebElement btn_saveEdittedChanges;
	
	@FindBy(id = "cboStepStatus")
    WebElement dropDown_stepStatus;
	
	@FindBy(id = "divOverwrite")
    WebElement btn_YesRecalculate;
	
	@FindBy(id = "btn_SaveresequenceCasesteps")
    WebElement btn_SaveResequenceCasesteps;
	
	
	@FindAll(@FindBy(how = How.XPATH, using = "//tbody//tr[@class='TBLROW1' or @class='TBLROW2']"))
	static List<WebElement> allRowsList;
	
	private static final String NEW_ORDER_ROW_LOC = "//tr[%d]/td[2]//input[@name='txtStepNbr']";
	
	@FindBy(xpath = "//table[@class='StepsTable']//tr")
    WebElement tbl_CaseSteps;
	
	@FindBy(id = "btn_SaveCaseremindermainstepdetails")
    WebElement btn_SaveReminder;
	
	@FindBy(id = "txtEstDate")
    WebElement textBox_EstDate;
	
	@FindBy(id = "txtstepdesc")
    WebElement textBox_StepName;
	
	@FindBy(xpath = "//table[@class='StepsTable']//tr")
    WebElement tbl_StepsTable;
	
	@FindBy(id = "btn_SaveClientaccessforCasesteps")
    WebElement btn_Save;
	
	@FindBy(xpath = "//a[@title='Go']")
    WebElement icon_Go;
	
	@FindBy(id = "ComboBoxAction")
    WebElement dropDown_actionforStatusReminderSteps;
	
	public CaseStepPage(WebDriver driver, String url) {
		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		PageFactory.initElements(driver, this);
	}

	public CaseStepPage(WebDriver driver) {
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
			Log.fail("Blanket applicants Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
	public void giveCorpAccessToCaseStep(boolean screenshot) throws Exception 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 04 Nov 2019
		  */ 

		getStepsList();
		
		Utils.selectOptionFromDropDownByVal(driver, "ClntAccess", dropDown_actionforStatusReminderSteps);
		Utils.clickElement(driver, icon_Go, "Go icon");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Set Client Access For Case Steps", "title", "false");
	
		String chooseAccessTable;
		Utils.waitForElement(driver, "table[class='TableStyle']>tbody>tr", 90, "css");
		
		try{
			List<WebElement> buttonOptions = driver.findElements(By.cssSelector("table[class='TableStyle']>tbody>tr"));
			for (int i = 1; i < buttonOptions.size(); i++) {
				chooseAccessTable = buttonOptions.get(i).getText();
				
				Utils.waitForElement(driver, "table[class='TableStyle']>input+tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(6)>select", 90, "css");
				WebElement buttonOptionsFirst = driver.findElement(By.cssSelector("table[class='TableStyle']>input+tbody>tr:nth-child(" + (i + 1) + ")>td:nth-child(6)>select"));
				Utils.selectOptionFromDropDownByVal(driver, "Y", buttonOptionsFirst);
			}
		}
		catch(Exception e){
			Log.fail("Unable to give corp access to the case step");
		}
		
		Utils.clickElement(driver, btn_Save, "save button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");
	}
	
	
	
	
	public void verifyCorpAcessGiven() throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 05 Nov 2019
		*/ 
		
		Utils.verifyIfDataNotPresent(driver, "//table[@class='StepsTable']//td[contains(text(),'Corporation : No')]", tbl_StepsTable, "xpath", "NO", "Corp Access");
	}
	
	public int numberOfCaseSteps()
	{
		int size = 0;
		try {
			Utils.waitForElement(driver, "table[class='StepsTable']>tbody>tr", 90, "css");
			List<WebElement>caseSteps = driver.findElements(By.xpath("//td[@class='TDOCITEMHEADER']/a[contains(@href,'TransferStep')]"));
			size = caseSteps.size();
		} 
		catch(Exception e)
		{
			Log.fail("Unable to count number of case steps");
		}
		
		return size;
	}
	
	
	public void addReminderAndVerify(String stepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 05 Nov 2019
		*/ 
		try{
		int numberOfCaseStepsBeforeAdding = numberOfCaseSteps();
       
//		Utils.selectOptionFromDropDown(driver, "   a) Reminder ", dropDown_actionforStatusReminderSteps);
		Utils.selectOptionFromDropDownByIndex(driver, 2, dropDown_actionforStatusReminderSteps);
		                                       
		Utils.clickElement(driver, icon_Go, "Go icon");
		
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();

		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String dateOutput = sdf.format(c.getTime());

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Add Case Reminder/Main Step", "title", "false");

		Utils.enterText(driver, textBox_StepName, stepName, "Case Step Name");	
		Utils.enterText(driver, textBox_EstDate, dateOutput, "Estimate Date");
		
		Utils.clickElement(driver, btn_SaveReminder, "Save");

				
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");
		
		int numberOfCaseStepsAfterAdding = numberOfCaseSteps();
		
		if(numberOfCaseStepsAfterAdding == numberOfCaseStepsBeforeAdding+1)
		{
			Log.pass("Added reminder successfully");
		}
		else
		{
			Log.fail("Unable to add reminder successfully");
		}
		}catch(Exception e){
			Log.fail("Error while adding Reminder. ERROR :\n\n " + e.getMessage());
		}

	}
	
	
	public List<String> getStepsList() 
	{
	    List<String> stepsNameList = new ArrayList<String>();
		Utils.waitForElement(driver, tbl_CaseSteps);
		Utils.waitForElement(driver, "//td[@class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]", 90, "xpath");
		List<WebElement> allRowsList = driver.findElements(By.xpath("//td[@class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]"));
		int count = allRowsList.size();
		for (int i = 1; i <= count; i ++) 
		{
			Utils.waitForElement(driver, "//td[@id='"+i+"' and @class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]", 90, "xpath");
			WebElement eachRowStepName = driver.findElement(By.xpath("//td[@id='"+i+"' and @class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]"));
			stepsNameList.add(eachRowStepName.getText().trim().toString());
		}
		Log.message("Available Steps :" + stepsNameList.toString());
		globalVariables.QA_ClientAccessCaseSteps = stepsNameList;
		return stepsNameList;
	}
	
	
	public String[][] getStepsListInSequence() {
		
		Utils.waitForElement(driver, tbl_CaseSteps);
		List<WebElement> caseStepsList = driver.findElements(By.xpath("//td[@class='TDOCITEMHEADER'  and @style='padding-left:5px;']"));
		int valueRows = 0;
		String[][] caseListInSequence = new String[caseStepsList.size()][2];
		for (int i = 1; i <= caseStepsList.size(); i++) 
		{
			List<WebElement> caseStep = driver.findElements(By.xpath("//td[@class='TDOCITEMHEADER'  and @style='padding-left:5px;']"));
			String serialNo = caseStep.get(i-1).getText().trim().toString();
			caseListInSequence[valueRows][0] = serialNo;
			Utils.waitForElement(driver, "//td[@class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]", 90, "xpath");
			WebElement stepName = driver.findElement(By.xpath("//td[@id='"+i+"' and @class='TDOCITEMHEADER']//a[contains(@href, 'TransferStep')]"));
			String nameOfStep = stepName.getText().trim().toString();
			caseListInSequence[valueRows][1] = nameOfStep;
			valueRows++;
		}
		return caseListInSequence;
	}
	
	
	public void changeOrder(int constRowNumber) 
	{
		int startNumber = 1;
		for (int i = allRowsList.size() + constRowNumber; i > 1; i--) 
		{
			String newOrderSno = String.format(NEW_ORDER_ROW_LOC, i);
			Utils.waitForElement(driver, newOrderSno, 50, "xpath");
			driver.findElement(By.xpath(newOrderSno)).clear();
			driver.findElement(By.xpath(newOrderSno)).sendKeys(String.valueOf(startNumber));
			startNumber = startNumber + 1;
		}
	}
	
	public void clickSaveResequenceCasesteps()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 07 Nov 2019
		*/
		Utils.clickElement(driver, btn_SaveResequenceCasesteps, "save resequence case step button");
		Utils.waitForElement(driver, "PopUpFrame", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("PopUpFrame");
		Utils.clickElement(driver, btn_YesRecalculate, "recalculate button");
	}
	
	
	public void checkResequence() throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 07 Nov 2019
		*/ 
		
		String[][] beforeUIOrder = getStepsListInSequence();

//		Utils.selectOptionFromDropDown(driver, "Resequence Case Step ", dropDown_actionforStatusReminderSteps);
		
		Utils.selectOptionFromDropDownByIndex(driver, 5, dropDown_actionforStatusReminderSteps);
		Utils.clickElement(driver, icon_Go, "Go icon");

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Resequence Case Steps", "title", "false");
		
				
		changeOrder(1);		
				
		clickSaveResequenceCasesteps();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");

			
		String[][] afterUIOrder = getStepsListInSequence();
		

		int compareSameSteps = 0;
		int j = beforeUIOrder.length - 1;
		for (int i = 0; i < beforeUIOrder.length; i++) {
			if (beforeUIOrder[i][0].equals(afterUIOrder[i][0]) && beforeUIOrder[i][1].equals(afterUIOrder[j][1])) {
				compareSameSteps++;
				j--;
			} else {
				Log.message("Not resequenced: After vs before step number:" + afterUIOrder[i][0] + " ; "
						+ beforeUIOrder[i][0], driver, true);
				Log.message("Not resequenced: Step Nmae after vs before reorder:" + afterUIOrder[i][1] + " ; "
						+ beforeUIOrder[i][1], driver, true);
			}
		}
		Log.message("Matched CompareSameSteps count:" + compareSameSteps);
		if (compareSameSteps == beforeUIOrder.length) {
			Log.pass("Verified Resequence Case Step updated successfully");
		} else {
			Log.fail("Verified Resequence Case Step is not updated");
		}

	}
	
	
	public void editStepStatus(String addedStepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		*/ 
		List<String> stepsNameList = getStepsList();
		int i =0;
		for( i = 2 ; i <= stepsNameList.size()*2; i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(addedStepName))
			{
				int flag = (i/2)%2;
				if(flag == 1)
				   Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW1']//td/a/img[@title='Edit Case Step Details']", "xpath", "edit icon");
				else
					Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW2']//td/a/img[@title='Edit Case Step Details']", "xpath", "edit icon");
				break;
			}
		}
		

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Status/Reminders Details", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, "Work In Progress", dropDown_stepStatus);
		
		Utils.clickElement(driver, btn_saveEdittedChanges, "save button");
		
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");

		Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a/../following-sibling::td[1]/span", globalVariables.elementWaitTime, "xpath");
		String stepStatus = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a/../following-sibling::td[1]/span")).getText();
		stepStatus = stepStatus.trim();
		
		if(stepStatus.equals("In Progress"))
			Log.message("Successfully editted the case step", driver, true);
		else
			Log.message("Unable to edit the case step", driver, true);
		
	}
	
	public void clickdeleteCaseStepButton()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		*/
		Utils.clickElement(driver, btn_DeleteCasesteps, "delete case step button");
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
	}
	
	public void deleteCaseStepAndVerify(String caseStepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		 */ 
		List<String> stepsNameList = getStepsList();
		int i =0;
		for( i = 2 ; i <= stepsNameList.size()*2; i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(caseStepName))
			{
				int flag = (i/2)%2;
				if(flag == 1)
				   Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW1']//td/a/img[@title='Delete Case Step']", "xpath", "delete icon");
				else
					Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW2']//td/a/img[@title='Delete Case Step']", "xpath", "delete icon");
				break;
			}
		}
	
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Delete Case steps", "title", "false");
			
		clickdeleteCaseStepButton();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");
		
		int flag = 1;
		
		for( i = 2 ; i <= stepsNameList.size(); i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(caseStepName))
			{
				Log.fail("Unable to delete case step", driver, true);
				flag = 0;
				break;
			}
		}
		
		if(flag == 1)
			Log.pass("Successfully deleted the case step", driver, true);
	}
	
	public void clickAddNewEvent()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		 */
		Utils.clickElement(driver, btn_AddNewEvent, "add new event button");
	}
	
	
	
	public void addStepEvent(String caseStepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		 */

		Utils.selectOptionFromDropDownByVal(driver, "AddEvent", dropDown_actionforStatusReminderSteps);
		Utils.clickElement(driver, icon_Go, "Go icon");
		
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "aty_case_main_step_list_view.aspx", "url", "false");

		Utils.clickDynamicElement(driver, "//td[text()='"+caseStepName+"']/following-sibling::td//a", "xpath", "add event step icon");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com Events", "title", "false");
		
		clickAddNewEvent();

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Event Customization", "title", "false");
		
		Utils.clickElement(driver, checkBox_StepStatusNotStarted, "check box change step status to not started");

		Utils.clickElement(driver, btn_NextStep, "next step button");
		
		((JavascriptExecutor) driver).executeScript("scroll(0,300);");
		
		Utils.clickElement(driver, checkBox_ActionName, "check box update data");
		
		((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
		
		Utils.clickUsingAction(driver, btn_nextInAction);
		
		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Action:Update Case Data", "title", "false");
		
		Utils.clickElement(driver, checkBox_updateData, "checked I-140 Filed date");
		
		Utils.clickElement(driver, btn_SaveData, "save action button");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com :: Event Customization", "title", "false");
		
		Utils.clickElement(driver, btn_Finish, "finish button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com Events", "title", "false");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(),'On Step Status Change to Not Started')]", "xpath", "On Step Status Change to Not Started", "event name");

		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'Update Data')]", "xpath", "Update Data", "action name");
		
		Utils.clickElement(driver, btn_GoToStepsList, "go to list button");
		
	}


	public void verifyIfEventIconPresent(String stepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 16 Nov 2019
		*/ 
		List<String> stepsNameList = getStepsList();
		int i =0;
		for( i = 2 ; i <= stepsNameList.size()*2; i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(stepName))
			{
				int flag = (i/2)%2;
				if(flag == 1)
				   Utils.verifyIfDataPresent(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW1']//td/a/img[@title='Case Step Event Details']", "xpath", "events details icon", "case list page");
				else
					Utils.verifyIfDataPresent(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW2']//td/a/img[@title='Case Step Event Details']", "xpath", "events details icon", "case list page");
				break;
			}
		}
	}
	
	public void clickOnStepName(String stepName)throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		Utils.clickDynamicElement(driver, "//a[contains(text()[2], '"+ stepName +"')]", "xpath", "step link");
	}
	
	public void clickOnFirstCaseManagerOnList()
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		Utils.clickDynamicElement(driver, "//tr[@class='TBLROW1'][1]/td[1]/input[@type='checkbox']", "xpath", "checked first case manager checkbox from list");
	}
	
	
	public void linkAttorneyCaseManager(String stepName, String dataWrite, String sheetName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */
		String updatedMangerStatus = "false";
		List<String> stepsNameList = getStepsList();
		
		int i =0;
		for( i = 2 ; i <= stepsNameList.size()*2; i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(stepName))
			{
				int flag = (i/2)%2;
				if(flag == 1)
				   Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW1']//td/a/img[@title='Edit Case Step Details']", "xpath", "edit icon");
				else
					Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW2']//td/a/img[@title='Edit Case Step Details']", "xpath", "edit icon");
				break;
			}
		}
		

		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Status/Reminders Details", "title", "false");
		
		((JavascriptExecutor) driver).executeScript("scroll(0,1000);");
				
				
		Utils.clickElement(driver, lnk_otherCaseManagers, "other case managers link");		

		Utils.waitForAllWindowsToLoad(3, driver);
		Utils.switchWindows(driver, "INSZoom.com - Assign Other Case Managers", "title", "false");
		
		clickOnFirstCaseManagerOnList();
				
		Utils.waitForElement(driver, txt_caseManagerId);
		String caseManagerId = txt_caseManagerId.getText();
		
		Utils.waitForElement(driver, txt_caseManagerName);
		String caseManagerName = txt_caseManagerName.getText();
		
		Utils.waitForElement(driver, txt_caseManagerType);
		String caseMangerType = txt_caseManagerType.getText();
		
		Utils.clickElement(driver, btn_saveCaseManagerPage, "save button");
						
		Log.message("Manager Details :" + caseManagerId + " ; " + caseManagerName + " ; " + caseMangerType);
					
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Status/Reminders Details", "title", "false");

		Utils.clickElement(driver, btn_saveEdittedChanges, "save button");

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");
		driver.navigate().refresh();

		
		Utils.waitForElement(driver, "//td[contains(text(), 'Step Case Managers:')]", globalVariables.elementWaitTime, "xpath");
		String fetchedCaseManagerName = driver.findElement(By.xpath("//td[contains(text(), 'Step Case Managers:')]")).getText().trim().toString();
		
		Log.message("Updated manager details: " + fetchedCaseManagerName, driver, true);

		((JavascriptExecutor) driver).executeScript("scroll(0,500);");

		if (fetchedCaseManagerName.contains(caseManagerName)) {
			updatedMangerStatus = "true";
			Log.message("Updated Case Manager details:" + fetchedCaseManagerName, driver, true);
			
			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			writeExcel.setCellData("QA_A_updatedMangerStatus", sheetName, "Value", updatedMangerStatus);
			writeExcel.setCellData("QA_A_assignedCaseManagerID", sheetName, "Value", caseManagerId);

			writeExcel.setCellData("QA_A_assignedCaseManagerName", sheetName, "Value", caseManagerName);
			writeExcel.setCellData("QA_A_assignedCaseManagerType", sheetName, "Value", caseMangerType);
			
			Log.pass("Verified Linked Attorney/Case Manager added successfully");
		} 
		
		else 
		{
			Log.message("Not updated Case Manager details:", driver, true);
			updatedMangerStatus = "false";

			String directory = System.getProperty("user.dir");
			ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
			writeExcel.setCellData("QA_A_updatedMangerStatus", sheetName, "Value", updatedMangerStatus);
			Log.fail("Verified Attorney/Case Manager not linked");
		}
	}
	
	public String getBodyOfEmail() {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		// Switch into Iframe
		String composeWinHandleID = driver.getWindowHandle();
		Log.message("Iframe - Email Window Handler:" + composeWinHandleID);
		
		Utils.waitForElement(driver, "htmMsg__ctl0_Editor_contentIframe", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		
		Utils.waitForElement(driver, "//html//body", globalVariables.elementWaitTime, "xpath");
		String reterivedBoodyTemplage = driver.findElement(By.xpath("//html//body")).getText().trim().toString();
		Log.message("Email Boody Template--->" + reterivedBoodyTemplage);
		driver.switchTo().window(composeWinHandleID);
		return reterivedBoodyTemplage;
	}
	
	public String getSubjectName() {
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		String emailSubject = txt_subject.getAttribute("value").trim();
		Log.message("Email Subject is :" + emailSubject);
		return emailSubject;
	}
	
	public void clickComposeEmailIcon(String caseStepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		List<String> stepsNameList = getStepsList();
		int i =0;
		for( i = 2 ; i <= stepsNameList.size()*2; i += 2)
		{
			Utils.waitForElement(driver, "//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a", globalVariables.elementWaitTime, "xpath");
			String rowStepName = driver.findElement(By.xpath("//table[@class='StepsTable']//tr[" + i + "]/td[@class='TDOCITEMHEADER']/a")).getText();
			rowStepName = rowStepName.trim();
			if(rowStepName.equals(caseStepName))
			{
				int flag = (i/2)%2;
				if(flag == 1)
				   Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW1']//td/a/img[@title='Compose Email']", "xpath", "compose email icon");
				else
					Utils.clickDynamicElement(driver, "//table[@class='StepsTable']//tr["+(i+1)+"][@class='TBLROW2']//td/a/img[@title='Compose Email']", "xpath", "compose email icon");
				break;
			}
		}
	}
	
	public boolean SendMailToCaseManager(String caseManagerStatus, String stepName) throws Exception 
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 19 Nov 2019
		 */ 
		boolean sentEmailPageStatus = false;

		if(caseManagerStatus.equalsIgnoreCase("true"))
		Utils.clickElement(driver, img_composeEmail, "compose email image");
		else
			clickComposeEmailIcon(stepName);
		
		String caseManagerEmailBody = getBodyOfEmail();
		String caseManagerEmailSubject = getSubjectName();
		Log.message("reterived Subject Name:###############" + caseManagerEmailSubject);
		Log.message("reterived Email Body details:##############" + caseManagerEmailBody);
		Utils.clickElement(driver, btn_SendCaseStatusEmail, "send email button");
		if (caseManagerEmailBody.contains(stepName)) {
			sentEmailPageStatus = true;
			Log.message("Edited page detils are shown fine in Email page: Body of Email is:\n" + caseManagerEmailBody,
					driver, true);
			Log.pass("Verified Compose page shown with updated Step Status successfully", driver, true);
		} else {
			Log.fail("Verified Compose page not shown Step details", driver, true);
			sentEmailPageStatus = false;
		}
		return sentEmailPageStatus;
	}

	
	public void addStepStatus(String stepName,String status) throws Exception
	{
		/*
		 * Created By : M Likitha Krishna
		 * Created On : 08 dec 2020
		 */
		
		if(Utils.isAlertPresent(driver))
			driver.switchTo().alert().accept();
		
		Utils.waitForElement(driver, "//a[contains(text()[2],'"+stepName+"') or contains(text()[1],'"+stepName+"')]", globalVariables.elementWaitTime, "xpath");
		WebElement text_stepName = driver.findElement(By.xpath("//a[contains(text()[2],'"+stepName+"') or contains(text()[1],'"+stepName+"')]"));
		Utils.waitForElementClickable(driver, text_stepName);
		Utils.clickElement(driver, text_stepName, stepName);
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "INSZoom.com - Edit Case Status/Reminders Details", "title", "false");
		
		Utils.selectOptionFromDropDown(driver, status, dropDown_stepStatus);
		
		if(status.equals("Completed"))
		{
			Utils.enterText(driver, textBox_CompletedDate, globalVariables.genericEndDate, "Completed date");
			Utils.clickElement(driver, btn_saveEdittedChanges, "save button");
//			Utils.waitForAllWindowsToLoad(1, driver);
//			
//			if(driver.getWindowHandles().size() == 2)
//			{
//				Utils.waitForAllWindowsToLoad(2, driver);
//				Utils.switchWindows(driver, "INSZoom.com - Update  Case Status", "title", "false");
//				Utils.clickElement(driver, btn_ChangeCaseStatus, "save change case status");
//			}
		}
		
		else
		{
			Utils.clickElement(driver, btn_saveEdittedChanges, "save button");
		}

		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case Status & Reminders/Steps/Deadline/Court/Interview Dates", "title", "false");

	}
	
}