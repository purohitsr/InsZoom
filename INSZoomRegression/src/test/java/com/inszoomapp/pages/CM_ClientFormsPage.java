package com.inszoomapp.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class CM_ClientFormsPage extends LoadableComponent<CM_ClientFormsPage> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//a[contains(text(),'Add Forms')]")
	WebElement lnk_AddForms;
	
	@FindBy(id = "btn_ComposeEmailAndAttachForm(s)")
	WebElement btn_EmailForms;
	

	public CM_ClientFormsPage(WebDriver driver) {
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
			Log.fail("Client forms Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public void clickEmailForms()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Nov 2019
		  */
		Utils.clickElement(driver, btn_EmailForms, "Email forms button");
	}
	
	
	public void verifyCaseId(String caseId)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Nov 2019
		  */
		Utils.verifyIfDataPresent(driver, "//font[contains(text(),'"+ caseId +"')]", "xpath", caseId, "forms emails page");
	}
	
	public void clickFormNameLink(String caseId)
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Nov 2019
		  */
		Utils.clickDynamicElement(driver, "//table[@summary='Forms List']//a[@href='aty_case_forms_view_frame.aspx?QCaseID="+ caseId +"']", "xpath", "Forms list link");
	}
	
	public void clickAddFormsLink()
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 23 Nov 2019
		  */
		Utils.clickElement(driver, lnk_AddForms, "add forms link");
	}

	public void verifyClientFormsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 18 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Client 's Forms");
		
	    }catch(Exception e){
		 Log.failsoft("Verification of Client's Forms Page Failed ", driver);
	    }
		
		
	}
	
	
	public void verifyComposeEmailsPage() throws Exception
	{
		/*
		 * Modified By: Saurav Purohit
		 * Modified On: 21 Feb 2020 
		 */
		try{
	       
		Utils.softVerifyPageTitle(driver, "INSZoom.com - Case Advanced Email");
		driver.navigate().back();
		
	    }catch(Exception e){
		 Log.failsoft("Verification of INSZoom.com - Case Advanced Email Page Failed ", driver);
	    }
		
		
	}
	
}