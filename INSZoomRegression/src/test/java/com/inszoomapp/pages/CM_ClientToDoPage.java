package com.inszoomapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ClientToDoPage extends LoadableComponent<CM_ClientToDoPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	
	
	@FindBy(xpath = "//i[@title='Save changes']")
    WebElement icon_SaveChanges;
	
	@FindBy(xpath = "//th[@class='template-table-heading_for_quest cursor-pointer template-sub-header-indent']")
    WebElement icon_ExpandQuestionnaires;
	
	@FindBy(xpath = "//i[@title='Edit']")
    WebElement icon_EditTodoName;
	
	@FindBy(id = "AddToDo")
    WebElement btn_NewToDoButton;
	
	@FindBy(css = "img[src='../../Content/img/questionnairesanddocs.png']")
	WebElement img_questionnaireAndDocChecklist;
	
	@FindBy(css = "img[src='../../Content/img/docsandletters.png']")
	WebElement img_DocsFormsLetters;
	
	@FindBy(css = "img[src='../../Content/img/invoice.png']")
	WebElement img_Invoice;
	
	@FindBy(xpath = "//div[@id='pendingkendocontainer']//tr")
	WebElement table_ToDo;
	
	@FindBy(id = "todo-description")
	WebElement textBox_ToDoInstruction;
	
	@FindBy(id = "todo-name")
	WebElement textBox_ToDoName;
	
	@FindBy(css = "img[src='../../Content/img/simpletask.png']")
	WebElement img_QuickTask;
	
	@FindBy(css = "img[src='../../Content/img/docsandletters.png']")
	WebElement img_DocsAndLetters;
	 
	@FindBy(xpath = "//div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]")
	WebElement alert_CreatedAndSentOut;
	
	@FindBy(xpath = "//span[contains(text(),'Add questionnaires')]")
	WebElement lnk_AddQuestionnaire;
	
	@FindBy(id = "assigntodobutton")
	WebElement btn_AssignToDo;
	
	@FindBy(xpath = "//div[@id='search-form']/input")
	WebElement searchBox_Questionnaire;
	
	@FindBy(css = "input[class='select2-search__field']")
	WebElement textBox_EConsent;
	
	@FindBy(xpath = "//title[text()='Kendo UI Editor content']/..//../body")
	WebElement textBox_instruction;
	
	/*@FindBy(id = "todo-description")
    WebElement textBox_instruction;*/
	
	@FindBy(css = "iframe[title='Editable area. Press F10 for toolbar.']")
	WebElement iframe_instruction;
	
	@FindBy(xpath = "//select[@id='corpusers']")
	WebElement dropDown_AssignTo;
	
	@FindBy(css = "img[src='../../Content/img/questionnairesanddocs.png']")
	WebElement img_Questionnaire;
	
	@FindBy(xpath = "//a/span[contains(text(),'New To-Do')]")
	WebElement btn_NewToDo;
	
	public CM_ClientToDoPage(WebDriver driver) {

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
			Assert.fail("HrP home page didnt loaded");
		}
	}
	
	public void addNewToDoUsingQuestionnaireTemplate(String forCorpOrClient, String clientName, String instruction, String econsent, String questionnaire) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On : 29 Nov 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_Questionnaire, "questionnaire icon");
		
		Utils.selectOptionFromDropDown(driver, clientName, dropDown_AssignTo);
		
//		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");
		driver.switchTo().frame(0);
		
		
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		/*Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");*/
		
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(),'"+ questionnaire +"')]", "xpath"))
		
		{
			Utils.waitForElement(driver, icon_ExpandQuestionnaires);
			Utils.scrollToElement(driver, icon_ExpandQuestionnaires);
			Utils.clickElement(driver, icon_ExpandQuestionnaires, "expand Questionnaire");
			/*Utils.waitForElement(driver, lnk_AddQuestionnaire);
			Utils.scrollToElement(driver, lnk_AddQuestionnaire);*/
			
			Utils.clickElement(driver, lnk_AddQuestionnaire, "add questionnaire link");
			
			Utils.waitForElement(driver, searchBox_Questionnaire);
			Utils.scrollToElement(driver, searchBox_Questionnaire);
			
			Utils.enterText(driver, searchBox_Questionnaire, questionnaire, "questionnaire name");
			
			Utils.clickDynamicElement(driver, "//span[@class='content-headline' and contains(text(),'"+ questionnaire +"')]/../following-sibling::span", "xpath", "add questionnaire icon");
		}
		
		try{
		if(Utils.isAlertPresent(driver)){
			driver.switchTo().alert().accept();
			Log.message("Questionnaire already present");
		}
		else
			Log.message("Questionnaire was not present earlier");
		}
		catch(Exception e)
		{
		}
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ questionnaire +"')]", "xpath", questionnaire, "add to-do page");
		
		/*Utils.waitForElement(driver, "//div[@class='onoffswitch-inner' and @linked-count]", globalVariables.elementWaitTime, "xpath");
		WebElement flag = driver.findElement(By.xpath("//div[@class='onoffswitch-inner' and @linked-count]"));
		String count = flag.getAttribute("linked-count");
		
		int i = Integer.parseInt(count);
		
		if(i == 0 )
			Log.fail("Questionnaires not added", driver, true);*/
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, btn_NewToDoButton);
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
//		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
//		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Fill out Questionnaires and Provide Documents')]", "xpath", "Fill out Questionnaires and Provide Documents to do", "to do page");
		
		driver.close();
		
		if(forCorpOrClient.equals("client"))
		{
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		}
		
		else
		{
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		}
	}
	
	public void addNewToDoUsingQuickTask(String forCorpOrClientOrCase, String clientName, String toDoName, String instruction) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On : 29 Nov 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_QuickTask, "quick task icon");
		
		Utils.selectOptionFromDropDown(driver, clientName, dropDown_AssignTo);
		
		Utils.clickElement(driver, icon_EditTodoName, " Edit toDos name Icon");
		
		Utils.enterText(driver, textBox_ToDoName, toDoName, "name textbox");
		
		Utils.clickElement(driver, icon_SaveChanges, "Save Changes Icon");
		
		Utils.enterText(driver, textBox_ToDoInstruction, instruction, "instructions");
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
//		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ toDoName +"')]", "xpath", toDoName, "to do page");
		
		driver.close();
		
		if(forCorpOrClientOrCase.equals("client"))
		{
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		}
		
		else if(forCorpOrClientOrCase.equals("corp"))
		{
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		}
		
		else
		{
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		}
	
	}
	
	
	public void addNewToDoUsingDocsAndLetter(String forCorpOrClient, String clientName, String instruction, String econsent, String docName) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On : 29 Nov 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_DocsAndLetters, "Docs and letter icon");
		
		Utils.selectOptionFromDropDown(driver, clientName, dropDown_AssignTo);
		
//		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(0);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		/*Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(),'"+ docName +"')]", "xpath", docName, "add to do page");
		
		Utils.waitForElement(driver, "docs-onoffswitch-inner", globalVariables.elementWaitTime, "id");
		String count = driver.findElement(By.id("docs-onoffswitch-inner")).getAttribute("linked-count");
		
		int i = Integer.parseInt(count);
		
		if(i == 0 )
			Log.fail("Documents not added", driver, true);*/
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Review Docs')]", "xpath", "Review Docs to do", "to do page");
	
		driver.close();
		
		if(forCorpOrClient.equals("client"))
		{
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		}
		
		else
		{
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		}
	}
	
	
	
	public void addNewToDoUsingQuestionnaireTemplateAndDocsCheckList(String clientName, String instruction, String econsent, String questionnaire) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On : 02 Dec 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_Questionnaire, "questionnaire icon");
		
		Utils.selectOptionFromDropDown(driver, clientName, dropDown_AssignTo);
		
//		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(0);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		/*Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");*/
		
	/*	
		for (int i = 1; i <= 4; i ++) 
		{
			Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]", globalVariables.elementWaitTime, "xpath");
			WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]"));
			String counts = onOffSwitch.getAttribute("linked-count");
			
			int linkedCount = Integer.parseInt(counts);
			
			Utils.waitForElement(driver, onOffSwitch);
			Utils.scrollToElement(driver, onOffSwitch);
			
			if(linkedCount > 0 )
				Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]/../div[2]", "xpath", "unlink all");
		}
		Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[1]", globalVariables.elementWaitTime, "xpath");
		WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])[1]"));
		String counts = onOffSwitch.getAttribute("linked-count");
		
		int linkedCount = Integer.parseInt(counts);*/
		
		
		if(!Utils.isElementPresent(driver, "//span[contains(text(),'"+ questionnaire +"')]", "xpath"))
		
		{
			Utils.waitForElement(driver, icon_ExpandQuestionnaires);
			Utils.scrollToElement(driver, icon_ExpandQuestionnaires);
			Utils.clickElement(driver, icon_ExpandQuestionnaires, "expand Questionnaire");
			Utils.waitForElement(driver, lnk_AddQuestionnaire);
			Utils.scrollToElement(driver, lnk_AddQuestionnaire);
			
			Utils.clickElement(driver, lnk_AddQuestionnaire, "add questionnaire link");
			
			Utils.waitForElement(driver, searchBox_Questionnaire);
			Utils.scrollToElement(driver, searchBox_Questionnaire);
			
			Utils.enterText(driver, searchBox_Questionnaire, questionnaire, "entered questionnaire name");
			
			Utils.clickDynamicElement(driver, "//span[@class='content-headline' and contains(text(),'"+ questionnaire +"')]/../following-sibling::span", "xpath", "add questionnaire icon");
		}
		
		if(Utils.isElementPresent(driver, "//span[contains(text(),'"+ questionnaire +"')]", "xpath"))
		{
			Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[1]/../div[2]", "xpath", "unlink all");
		}
		
		try{
		if(Utils.isAlertPresent(driver)){
			driver.switchTo().alert().accept();
			Log.message("Questionnaire already present");
		}
		else
			Log.message("Questionnaire was not present earlier");
		}
		catch(Exception e)
		{
		}
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'"+ questionnaire +"')]", "xpath", questionnaire, "add to-do page");
		Utils.waitForElement(driver, "//div[@class='onoffswitch-inner' and @linked-count]", globalVariables.elementWaitTime, "xpath");
		WebElement flag = driver.findElement(By.xpath("//div[@class='onoffswitch-inner' and @linked-count]"));
		String count = flag.getAttribute("linked-count");
		
		int i = Integer.parseInt(count);
		
		if(i == 0 )
			Log.fail("Questionnaires not added", driver, true);
		
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Fill out Questionnaires and Provide Documents')]", "xpath", "Fill out Questionnaires and Provide Documents to do", "to do page");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		
	}
	
	
	

	public void addNewToDoUsingDocsCheckList(String clientName, String instruction, String econsent, String docName) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On : 02 Dec 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_Questionnaire, "questionnaire icon");
		
		Utils.selectOptionFromDropDown(driver, clientName, dropDown_AssignTo);
		
		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(iframe_instruction);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");
		
		
		for (int i = 1; i <= 4; i ++) 
		{
			Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]", globalVariables.elementWaitTime, "xpath");	
			WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]"));
			String counts = onOffSwitch.getAttribute("linked-count");
			
			int linkedCount = Integer.parseInt(counts);
			
			Utils.waitForElement(driver, onOffSwitch);
			Utils.scrollToElement(driver, onOffSwitch);
			
			if(linkedCount > 0 )
				Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]/../div[2]", "xpath", "unlink all");
		}
		Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[2]", globalVariables.elementWaitTime, "xpath");
		WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])[2]"));
		String counts = onOffSwitch.getAttribute("linked-count");
		
		int linkedCount = Integer.parseInt(counts);
		
		if(linkedCount == 0 && Utils.isElementPresent(driver, "//span[contains(text(),'"+ docName +"')]", "xpath"))
			Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[2]/../div[2]", "xpath", "add all");
		
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Fill out Questionnaires and Provide Documents')]", "xpath", "Fill out Questionnaires and Provide Documents to do", "to do page");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		
	}


	public void addNewToDoUsingInvoiceTemplate(String forCorpOrClientOrCase, String assignTo, String instruction, String econsent) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On :  02 Dec 2019
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_Invoice, "invoice icon");
		
		Utils.selectOptionFromDropDown(driver, assignTo, dropDown_AssignTo);
		
		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(iframe_instruction);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Review Invoices')]", "xpath", "Fill out Questionnaires and Provide Documents to do", "to do page");
		
		driver.close();
		
		if(forCorpOrClientOrCase.equals("client"))
		{
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - View Client's Profile", "title", "false");
		}
		
		else if(forCorpOrClientOrCase.equals("corp"))
		{
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - View Corporation's Profile", "title", "false");
		}
		
		else
		{
			Utils.waitForAllWindowsToLoad(1, driver);
			Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		}
	}
	
	
	public void addNewToDoDocs(String assignTo, String instruction, String econsent, String fileName) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On :  04 Dec 2019
		 */
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_DocsFormsLetters, "DocsFormsLetter icon");
		
		Utils.selectOptionFromDropDown(driver, assignTo, dropDown_AssignTo);
		
		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(iframe_instruction);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");
		
		/****index here starts from 2******/
		
		for (int i = 2; i <= 5; i ++) 
		{
			Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]", globalVariables.elementWaitTime, "xpath");	
			WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]"));
			String counts = onOffSwitch.getAttribute("linked-count");
			
			int linkedCount = Integer.parseInt(counts);
			
			Utils.waitForElement(driver, onOffSwitch);
			Utils.scrollToElement(driver, onOffSwitch);
			
			if(linkedCount > 0 )
				Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]/../div[2]", "xpath", "unlink all");
		}
		
		Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[2]/../div[2]", "xpath", "link docs ");
		
		Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[2]", globalVariables.elementWaitTime, "xpath");
		WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])[2]"));
		String counts = onOffSwitch.getAttribute("linked-count");
		
		Utils.verifyIfDataPresent(driver, "//td[@class='document-template-td' and contains(text(),'"+ fileName +"')]", "xpath", fileName, "file present under docs");
		
		int linkedCount = Integer.parseInt(counts);
		
		if(linkedCount > 0)
			Log.message("Docs added", driver, true);
		
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Review Docs')]", "xpath", "Review Docs to do", "to do page");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		
	}
	
	
	
	public void addNewToDoForms(String assignTo, String instruction, String econsent, String formName) throws Exception
	{
		/* 
		 * Created By : M Likitha Krishna
		 * Created On :  04 Dec 2019
		 */
		Utils.clickElement(driver, btn_NewToDo, "New To Do button");
		
		Utils.clickElement(driver, img_DocsFormsLetters, "DocsFormsLetter icon");
		
		Utils.selectOptionFromDropDown(driver, assignTo, dropDown_AssignTo);
		
		Utils.waitForElement(driver, "iframe_instruction", globalVariables.elementWaitTime, "id");

		driver.switchTo().frame(iframe_instruction);
		
		Utils.enterText(driver, textBox_instruction, instruction, "instruction");
		
		driver.switchTo().defaultContent();
		
		Utils.enterText(driver, textBox_EConsent, econsent, "econsent");
		
		Utils.clickDynamicElement(driver, "//li[contains(text(),'"+ econsent +"')]", "xpath", "selected e-consent");
		
		/****index here starts from 2******/
		
		for (int i = 2; i <= 5; i ++) 
		{
			Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]", globalVariables.elementWaitTime, "xpath");
			WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]"));
			String counts = onOffSwitch.getAttribute("linked-count");
			
			int linkedCount = Integer.parseInt(counts);
			
			Utils.waitForElement(driver, onOffSwitch);
			Utils.scrollToElement(driver, onOffSwitch);
			
			if(linkedCount > 0 )
				Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])["+i+"]/../div[2]", "xpath", "unlink all");
		}
		
		Utils.clickDynamicElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[3]/../div[2]", "xpath", "link docs ");
		Utils.waitForElement(driver, "(//div[@class='onoffswitch-inner' and @linked-count])[2]", globalVariables.elementWaitTime, "xpath");
		WebElement onOffSwitch = driver.findElement(By.xpath("(//div[@class='onoffswitch-inner' and @linked-count])[2]"));
		String counts = onOffSwitch.getAttribute("linked-count");
		
		Utils.verifyIfDataPresent(driver, "//td[@class='forms-template-td' and contains(text(),'"+ formName +"')]", "xpath", formName, "under forms");
		
		int linkedCount = Integer.parseInt(counts);
		
		if(linkedCount > 0)
			Log.message("Docs added", driver, true);
		
		
		Utils.clickElement(driver, btn_AssignToDo, "assign to do button");
		
		Utils.waitForElement(driver, alert_CreatedAndSentOut);
		
		Utils.waitForElementNotVisible(driver,"div[@class='toast-alert-message alert-success success_alert_msg' and contains(text(),'Created and sent out')]" , "xpath");
		
		Utils.waitForElement(driver, table_ToDo);
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(),'Review Docs')]", "xpath", "Review Docs to do", "to do page");
		
		driver.close();
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom.com - Case profile", "title", "false");
		
	}
	
	
	public void checkIfDocsAvailable(String fileNameWithoutExtension)
	{
		/* 
		 * Created By : Saksham Kapoor
		 * Created On : 23 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To-Do");
		
		Utils.clickElement(driver, img_DocsAndLetters, "Docs and Letters");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Docs to review')]/ancestor::tbody/following-sibling::tbody//td[contains(text(), '" + fileNameWithoutExtension + "')]", "xpath", fileNameWithoutExtension, "Docs to Review");
	}
	
	
	public void checkIfLetterTemplateAvailable(String letterTemplateName)
	{
		/* 
		 * Edited By : Souvik Ganguly
		 * Created On : 14/06/2021
		 * 
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To-Do");
		
		Utils.clickElement(driver, img_DocsAndLetters, "Docs and Letters");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Letters')]/ancestor::tbody/following-sibling::tbody//a[contains(text(), '" + letterTemplateName + "')]", "xpath", letterTemplateName, "Letters section");
	} 
	
	
	public void checkIfDocChecklistAvailable(String docChecklistName, String clientName)
	{
		/* 
		 * Created By : Saksham Kapoor
		 * Created On : 23 Mar 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To-Do");
		
		Utils.clickElement(driver, img_questionnaireAndDocChecklist, "Questionnaires + Docs Checklist");

		Utils.waitForElement(driver, "//span[contains(text(), 'Docs Check List for " + clientName + "')]", globalVariables.elementWaitTime, "xpath");
		WebElement heading_checklist = driver.findElement(By.xpath("//span[contains(text(), 'Docs Check List for " + clientName + "')]"));
		Utils.scrollIntoView(driver, heading_checklist);
		Utils.scrollToElement(driver, heading_checklist);
        
		Utils.clickElement(driver, heading_checklist, "Checklist heading");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Docs Check List')]/..//span[contains(text(), '" + docChecklistName + "')]", "xpath", docChecklistName, "Docs Check List");
	}
	
	
	public void checkIfCaseDocumentAvailable(String fileNameWithoutExtension)
	{
		/* 
		 * Created By : Saksham Kapoor
		 * Created On : 11 May 2020
		 * 
		 */
		
		Utils.clickElement(driver, btn_NewToDo, "New To-Do");
		
		Utils.clickElement(driver, img_DocsFormsLetters, "Docs, forms and letters");
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'Docs to review')]/ancestor::tbody/following-sibling::tbody//td[contains(text(), '" + fileNameWithoutExtension + "')]", "xpath", fileNameWithoutExtension, "Docs to Review");

	}
	
	
	public void checkIfLongDescriptionAvailableForDocChecklist(String docChecklistName, String docChecklistDescriptionNew)
	{
		/* 
		 * Created By : Saksham Kapoor
		 * Created On : 18 Jun 2020
		 * 
		 */
		
		Utils.waitForElement(driver, "//span[contains(text(), '" + docChecklistName + "')]/../following-sibling::div//span", globalVariables.elementWaitTime, "xpath");
		WebElement heading_checklist = driver.findElement(By.xpath("//span[contains(text(), '" + docChecklistName + "')]/../following-sibling::div//span"));
		Utils.scrollIntoView(driver, heading_checklist);
		Utils.scrollToElement(driver, heading_checklist);
		
		Utils.clickElement(driver, heading_checklist, "checklist heading");
		
		Utils.verifyIfDataPresent(driver, "//div[contains(text(), '" + docChecklistDescriptionNew + "')]", "xpath", docChecklistDescriptionNew, "Checklist Description");
	}
}
