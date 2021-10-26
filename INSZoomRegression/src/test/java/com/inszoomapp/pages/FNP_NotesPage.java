package com.inszoomapp.pages;

import java.util.List;

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

public class FNP_NotesPage extends LoadableComponent<FNP_NotesPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;
	public String parentWindow;
	
	
	//added by saurav on 16March 2020
	
	@FindBy(xpath = "//h2[contains(text(),'NOTES')]")
	WebElement header_Notes;
	
	@FindBy(xpath = "//ancestor::div[@class='modal fade in']//button")
	WebElement icon_close;
	
	
	public FNP_NotesPage(WebDriver driver) {

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
			Assert.fail("FNP notes page didnt loaded");
		}
	}
	
	public void verifyNotes(String notesDescription,boolean screenshot) throws Exception {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 11 Nov 2019
		  */
		
		Utils.verifyIfDataPresent(driver, "//div[@class='title'][contains(text(),'"+ notesDescription +"')]", "xpath", notesDescription, "notes page");
		Utils.clickDynamicElement(driver, "//div[@class='title'][contains(text(),'"+ notesDescription +"')]", "xpath", "Notes description link");
		Utils.verifyIfDataPresent(driver, "//div[@class='modal fade in']//div[contains(text(),'"+notesDescription+"')]", "xpath", notesDescription, "Notes description");
		Utils.clickElement(driver, icon_close, "close icon");
	}
	
	 public void verifyNotesPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */
		    try{
			    Utils.waitForAjax(driver);
		    	Utils.verifyIfStaticElementDisplayed(driver, header_Notes, "Notes Page", "on FNP page");
		    }catch(Exception e){
			 Log.failsoft("Verification of Notes Page failed", driver);
		    }

	}
	
}