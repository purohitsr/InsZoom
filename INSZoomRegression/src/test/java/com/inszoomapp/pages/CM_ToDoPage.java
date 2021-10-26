package com.inszoomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;


public class CM_ToDoPage extends LoadableComponent<CM_ToDoPage>
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	//Web elements
	
	
	
	@FindBy(xpath = "//b[text()='Corporation Name']/ancestor::div[@class='tab-pane fade active in']")           //Added by Saurav Purohit on 14th April 2021
	WebElement tab_CorporationNameExpanded;
	
	@FindBy(xpath = "//h5[contains(text(),'Regarding Client')]/parent::a[@aria-expanded='true']")           //Added by Saurav Purohit on 14th April 2021
	WebElement tab_expandedRegardingclient;
	
	@FindBy(xpath = "//input[@id='todo-name']")           //Added by Nitisha Sinha on 15th June 2020
	WebElement textbox_ToDoTitle;
	
	@FindBy(xpath = "//i[@title='Edit']")           //Added by Nitisha Sinha on 15th June 2020
	WebElement icon_editToDoTitle;
	
	@FindBy(xpath = "//i[@title='Save changes']")           //Added by Nitisha Sinha on 15th June 2020
	WebElement icon_saveToDoTitle;
	
	@FindBy(xpath = "//span[@class='select2-selection__arrow']")           //Added by Nitisha Sinha on 16th June 2020
	WebElement dropdown_assignTo;
	
	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/input")           //Added by Nitisha Sinha on 16th June 2020
	WebElement searchbox_assignTo;
	
	@FindBy(id = "assigntodobutton")                              //Added by Nitisha Sinha on 18th June 2020
	WebElement btn_assignToDo;
	
	@FindBy(xpath = "(//div[contains(@onclick, 'CheckAccess')]//img[contains(@src, 'questionnairesanddocs.png')])[3]")
	WebElement img_CaseQuestionnaire;
			
	@FindBy(xpath = "//span[contains(text(),'Choose case')]")
	WebElement searchBox_Case;
	
	@FindBy(id = "applicantSearch")
	WebElement searchBox_ClientInCaseToDo;
	
	@FindBy(xpath = "//a/h5[contains(text(),'Regarding Case')]")
	WebElement tab_RegardingCase;
	
	@FindBy(xpath = "//div[contains(@onclick, 'CheckAccessInClientToDo')]//img[contains(@src, 'questionnairesanddocs.png')]")
	WebElement img_ClientQuestionnaire;
	
	@FindBy(id = "txtSearch")
	WebElement searchBox_Client;
	
	@FindBy(xpath = "//a/h5[contains(text(),'Regarding Client')]")
	WebElement tab_RegardingClient;
	
	@FindBy(xpath = "//input[@placeholder='Enter Questionnaire Name']")
	WebElement textBox_questionnaireBox;
	
	@FindBy(xpath = "//input[@placeholder='Enter Questionnaire Name']")
	WebElement searchBox_questionnaireName;
	
	@FindBy(xpath = "//span[text()='Add questionnaires']")
	WebElement link_AddQuestionnaire;
	
	@FindBy(xpath = "//th[@class='template-table-heading_for_quest cursor-pointer template-sub-header-indent']")
	WebElement waitElement_QuestionnaireExpand;
	
	@FindBy(xpath = "//span[text()='Questionnaires']/../..//i[@class='fa fa-angle-right']")
	WebElement arrow_QuestionnaireExpand;
	
	@FindBy(id = "corporationDropdown")
	WebElement textBox_corporationName;
	
	@FindBy(xpath = "//div[contains(@onclick, 'CheckAccessInCorporationToDo')]//img[contains(@src, 'questionnairesanddocs.png')]")
	WebElement img_CorporationQuestionnaire;
	
	@FindBy(xpath = "//a/h5[contains(text(),'Regarding Corporation')]")
	WebElement tab_RegardingCorpotation;
	
	@FindBy(xpath = "//div[@class='md-content modal-content']")
	WebElement window_ToDo;
	
	@FindBy(id = "AddToDo")
	WebElement btn_NewToDoButton;
	
	public CM_ToDoPage(WebDriver driver)
	{
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail();
		} else {
			Log.fail("Client list Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	//functions
	
	public void clickNewToDoButton()
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 21 Aug 2020
		 */
		Utils.clickElement(driver, btn_NewToDoButton, "new todo");
	}
	
	public void verifyCorpToDo(String corporationName, String questionnaireName) throws InterruptedException
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 28 July 2021
		 * Added an if condition because of a situation where the the text box is not available even after the tab is clicked. This issue cannot be reproduced manually so workaround is applied.
		 */
		clickNewToDoButton();
		Utils.waitForElement(driver, window_ToDo);
		Utils.clickElement(driver, tab_RegardingCorpotation, "Regarding Corporation");
		Thread.sleep(3000);	 //This is added as an exception as no sync element is available
		if(!driver.findElement(By.id("corporationDropdown")).isDisplayed())  //This condition is added in case the textbox is not displayed even after the tab is clicked.
		{
			Utils.clickElement(driver, tab_RegardingCase, "Regarding Case");
			Utils.clickElement(driver, tab_RegardingCorpotation, "Regarding Client");
		}
		Utils.enterText(driver, textBox_corporationName, corporationName, "corporation");
		Utils.waitForElement(driver, "//div[@class='dropdown-content']//div/span[contains(text(), '" + corporationName + "')]", globalVariables.elementWaitTime, "xpath");
		textBox_corporationName.sendKeys(Keys.DOWN, Keys.RETURN);
		Utils.waitForElement(driver, "//span[contains(text(),'" + corporationName + "') and @id='corporationName']", globalVariables.elementWaitTime, "xpath");
		Utils.clickElement(driver, img_CorporationQuestionnaire, "questionnaire icon");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
		Utils.scrollIntoView(driver, plus);
		Utils.scrollToElement(driver, plus);
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");	
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.verifyIfDataPresent(driver, "//td[@class='questionnaire-template-td template-accordion-item-indent']//span[contains(text(),'"+questionnaireName+"')]", "xpath", questionnaireName, "on todo page");
		driver.close();
	}
	
	public void verifyClientToDo(String clientFirstName, String questionnaireName) throws InterruptedException
	{
		/*
	     * Edited By: Souvik Ganguly
		 * Edited On: 28 July 2021
		 * Added an if condition because of a situation where the the text box is not available even after the tab is clicked. This issue cannot be reproduced manually so workaround is applied.
		 */
		clickNewToDoButton();
		Utils.waitForElement(driver, window_ToDo);
		Utils.clickElement(driver, tab_RegardingClient, "Regarding Client");
		Thread.sleep(3000);			//This is added as an exception as no sync element is available	
		if(!driver.findElement(By.id("txtSearch")).isDisplayed())  //This condition is added in case the textbox is not displayed even after the tab is clicked.
		{
			Utils.clickElement(driver, tab_RegardingCase, "Regarding Case");
			Utils.clickElement(driver, tab_RegardingClient, "Regarding Client");
		}
		Utils.enterText(driver, searchBox_Client, clientFirstName, "client first name");		
		Utils.waitForElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", "xpath", "client name");
		
		Utils.clickElement(driver, img_ClientQuestionnaire, "questionnaire icon");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
		Utils.scrollIntoView(driver, plus);
		Utils.scrollToElement(driver, plus);
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");	
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.verifyIfDataPresent(driver, "//td[@class='questionnaire-template-td template-accordion-item-indent']//span[contains(text(),'"+questionnaireName+"')]", "xpath", questionnaireName, "on todo page");
		driver.close();
	}
	
	
	public void verifyCaseToDo(String clientFirstName, String caseID, String questionnaireName) throws InterruptedException
	{
		/*
		 * Edited By: Souvik Ganguly
		 * Edited On: 28 July 2021
		 * Added an if condition because of a situation where the the text box is not available even after the tab is clicked. This issue cannot be reproduced manually so workaround is applied.
		 */
		clickNewToDoButton();
		Utils.waitForElement(driver, window_ToDo);
		Utils.clickElement(driver, tab_RegardingCase, "Regarding Case");
		Thread.sleep(3000);	//This is added as an exception as no sync element is available
		if(!driver.findElement(By.id("applicantSearch")).isDisplayed()) //This condition is added in case the textbox is not displayed even after the tab is clicked.
		{
			Utils.clickElement(driver, tab_RegardingCorpotation, "Regarding Case");
			Utils.clickElement(driver, tab_RegardingCase, "Regarding Client");
		}
		Utils.enterText(driver, searchBox_ClientInCaseToDo, clientFirstName, "client first name");
		
		Utils.waitForElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", "xpath", "client name");
		
		Utils.clickElement(driver, searchBox_Case, "case search box");
		Utils.waitForElement(driver, "//li[contains(text(),'"+caseID+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+caseID+"')]", "xpath", "case");
		
		Utils.clickElement(driver, img_CaseQuestionnaire, "questionnaire icon");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
		Utils.scrollIntoView(driver, plus);
		Utils.scrollToElement(driver, plus);
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");	
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.verifyIfDataPresent(driver, "//td[@class='questionnaire-template-td template-accordion-item-indent']//span[contains(text(),'"+questionnaireName+"')]", "xpath", questionnaireName, "on todo page");
		driver.close();
	}

	
	public void assignQuestionnaireToClient(String clientFirstName, String questionnaireName, String toDoTitle, String clientFullName) throws InterruptedException
	{
		/*
		 * Created By: Nitisha Sinha 
		 * Created On: 29 Sept 2020
		 */
		Utils.waitForElement(driver, window_ToDo);
		Utils.clickElement(driver, tab_RegardingClient, "Regarding Client");
		Utils.waitForElement(driver, tab_expandedRegardingclient);
		Utils.enterText(driver, searchBox_Client, clientFirstName, "client name searchbox");
		
		Utils.waitForElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", "xpath", "client name");
		
		Utils.clickElement(driver, img_ClientQuestionnaire, "questionnaire icon");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		
		Utils.scrollIntoView(driver, link_AddQuestionnaire);
		Utils.scrollToElement(driver, link_AddQuestionnaire);
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
		Utils.scrollIntoView(driver, plus);
		Utils.scrollToElement(driver, plus);
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");	
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.verifyIfDataPresent(driver, "//td[@class='questionnaire-template-td template-accordion-item-indent']//span[contains(text(),'"+questionnaireName+"')]", "xpath", questionnaireName, "on todo page");

		Utils.clickElement(driver, icon_editToDoTitle, "Edit To do Title"); 
    	
    	textbox_ToDoTitle.clear();
    	
    	Utils.enterText(driver, textbox_ToDoTitle, toDoTitle, "To Do title textbox");
    	
    	Utils.clickElement(driver, icon_saveToDoTitle, "save Todo Title");
    	
    	Utils.clickElement(driver, dropdown_assignTo, "Assign To dropdown");
    	
    	Utils.enterText(driver, searchbox_assignTo, clientFullName, "Assign to searchbox");
    	
    	Utils.clickDynamicElement(driver, "//ul[@class='select2-results__options']//span[contains(text(),'" + clientFullName + " (Client)')]", "xpath", "Client to which form should be assigned");
    	
    	Utils.clickElement(driver, btn_assignToDo, "Assign ToDo Button");
    	
    	Utils.waitForElement(driver, btn_NewToDoButton);
    	
	}
	
	
	public void backToClientQuestionnairePage(String clientName) throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 1/10/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Questionnaires for " + clientName, "title", "false");
	}
	
	
	public void backToCorporationQuestionnairePage(String clientName) throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 1/10/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Questionnaires for Questionnaire Corporation", "title", "false");
	}
	
	
	public void assignQuestionnaireToCorporation(String corporationName, String questionnaireName, String toDoTitle, String corporationSigningPersonName) throws InterruptedException
	{
		/*
		 * Created By: Nitisha Sinha 
		 * Created On: 6th Oct 2020
		 * Modified by : Saurav
		 * Modified Date : 30th June 2021
		 */
		
		Utils.waitForElement(driver, window_ToDo);
		
		Utils.clickElement(driver, tab_RegardingCorpotation, "Regarding Corporation");
		
		Utils.waitForElement(driver, tab_CorporationNameExpanded);
		
		Utils.enterText(driver, textBox_corporationName, corporationName, "corporation");

		WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'result is available')]"))); 
		
		textBox_corporationName.sendKeys(Keys.DOWN, Keys.RETURN);

		Utils.clickElement(driver, img_CorporationQuestionnaire, "questionnaire icon");
		
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		
		Utils.scrollIntoView(driver, link_AddQuestionnaire);
		
		Utils.scrollToElement(driver, link_AddQuestionnaire);
		
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
        js.executeScript("javascript:window.scrollBy(0,1000)");
        
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
        
		Utils.scrollIntoView(driver, plus);
		
		Utils.scrollToElement(driver, plus);
		
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");
        
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		
		Utils.clickElement(driver, icon_editToDoTitle, "Edit To do Title"); 
    	
    	textbox_ToDoTitle.clear();
    	
    	Utils.enterText(driver, textbox_ToDoTitle, toDoTitle, "To Do title textbox");
    	
    	Utils.clickElement(driver, icon_saveToDoTitle, "save Todo Title");
    	
    	Utils.clickElement(driver, dropdown_assignTo, "Assign To dropdown");
    	
    	Utils.enterText(driver, searchbox_assignTo, corporationSigningPersonName, "Assign to searchbox");
    	
    	Utils.clickDynamicElement(driver, "//ul[@class='select2-results__options']//span[contains(text(),'" + corporationSigningPersonName + " (Corporation Signing Person)')]", "xpath", "Corporation Signing Person to which form should be assigned");
    	
    	Utils.clickElement(driver, btn_assignToDo, "Assign ToDo Button");
    	
    	Utils.waitForElement(driver, btn_NewToDoButton);
	}
	
	
	public void assignQuestionnaireToCase(String clientFirstName, String questionnaireName, String toDoTitle, String caseID, String clientFullName) throws InterruptedException
	{
		/*
		 * Created By: Nitisha Sinha 
		 * Created On: 6 Oct 2020
		 */
		Utils.waitForElement(driver, window_ToDo);
		Utils.clickElement(driver, tab_RegardingCase, "Regarding Case");
		Utils.enterText(driver, searchBox_ClientInCaseToDo, clientFirstName, "client first name");
		
		Utils.waitForElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//span[contains(text(),'"+clientFirstName+"')]", "xpath", "client name");
		
		Utils.clickElement(driver, searchBox_Case, "case search box");
		Utils.waitForElement(driver, "//li[contains(text(),'"+caseID+"')]", globalVariables.elementWaitTime, "xpath");
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+caseID+"')]", "xpath", "case");
		
		Utils.clickElement(driver, img_CaseQuestionnaire, "questionnaire icon");
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.scrollToElement(driver, arrow_QuestionnaireExpand);
		Utils.clickElement(driver, arrow_QuestionnaireExpand, "questionnaire expand arrow");

		Utils.waitForElement(driver, waitElement_QuestionnaireExpand);
		Utils.clickElement(driver, link_AddQuestionnaire, "add questionnaire link");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
		Utils.enterText(driver, textBox_questionnaireBox, questionnaireName, "questionnaire name");
		Utils.waitForElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", globalVariables.elementWaitTime, "xpath");

        js.executeScript("javascript:window.scrollBy(0,1000)");
        WebElement plus = driver.findElement(By.xpath("//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']"));
		Utils.scrollIntoView(driver, plus);
		Utils.scrollToElement(driver, plus);
        Utils.clickDynamicElement(driver, "//span[contains(text(), '" + questionnaireName + "')]/../following-sibling::span//a/i[@class='fa fa-plus']", "xpath", "Plus icon to add questionnaire");	
		Utils.waitUntillLoaderDisappearsInHRP(driver);
		Utils.verifyIfDataPresent(driver, "//td[@class='questionnaire-template-td template-accordion-item-indent']//span[contains(text(),'"+questionnaireName+"')]", "xpath", questionnaireName, "on todo page");
		
		Utils.clickElement(driver, icon_editToDoTitle, "Edit To do Title"); 
    	
    	textbox_ToDoTitle.clear();
    	
    	Utils.enterText(driver, textbox_ToDoTitle, toDoTitle, "To Do title textbox");
    	
    	Utils.clickElement(driver, icon_saveToDoTitle, "save Todo Title");
    	
    	Utils.clickElement(driver, dropdown_assignTo, "Assign To dropdown");
    	
    	Utils.enterText(driver, searchbox_assignTo, clientFullName, "Assign to searchbox");
    	
    	Utils.clickDynamicElement(driver, "//ul[@class='select2-results__options']//span[contains(text(),'" + clientFullName + " (Client)')]", "xpath", "Client to which form should be assigned");
    	
    	Utils.clickElement(driver, btn_assignToDo, "Assign ToDo Button");
    	
    	Utils.waitForElement(driver, btn_NewToDoButton);
	}
	
	
	public void backToCaseQuestionnairePage(String clientName) throws Exception
	{
		/*
		 * Added By: Nitisha Sinha
		 * Added On: 1/10/2020
		 */
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		
		Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");
	}
}
