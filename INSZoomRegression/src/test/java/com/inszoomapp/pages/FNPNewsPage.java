package com.inszoomapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class FNPNewsPage extends LoadableComponent<FNPNewsPage> 
{
	private WebDriver driver;
	
	@FindBy(css="button[onclick='searchGuidelines();']")
	WebElement btn_searchPolicy;
	
	@FindBy(id="searchguidelines")
	WebElement searchbox_policy;
	
	@FindBy(id="searchnews")
	WebElement searchbox_News;
	
	@FindBy(xpath="//a[contains(text(), 'Policy/Guidelines')]")
	WebElement tab_PolicyGuidelines;

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	public FNPNewsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyIfNewsPresent(String newsTitleClient, String newsDescriptionClient)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 05 Dec 2019
		 */ 
		
		Utils.waitForElementPresent(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		Utils.waitForElementNotVisible(driver, "//span[contains(text(), 'Loading...')]", "xpath");
		
		Utils.verifyIfDataPresent(driver, "//b[contains(text(), '" + newsTitleClient + "')]", "xpath", newsTitleClient, "News");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + newsDescriptionClient + "')]", "xpath", newsDescriptionClient, "News");
	}
	
	
	public void verifyIfNewsDeleted(String newsTitleClient)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 06 Dec 2019
		 */ 
		
		Utils.verifyIfDataNotPresent(driver, "//b[contains(text(), '" + newsTitleClient + "')]", searchbox_News, "xpath", newsTitleClient, "News");
	}
	
	
	public void clickPolicyGuidelinesTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 06 Dec 2019
		 */ 
		
		Utils.clickElement(driver, tab_PolicyGuidelines, "'Policy/Guidelines' tab");
	}
	
	
	public void verifyIfPolicyPresent(String policyTitleClient, String policyDescriptionClient)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 06 Dec 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//b[contains(text(), '" + policyTitleClient + "')]", "xpath", policyTitleClient, "Policy");
		
		Utils.verifyIfDataPresent(driver, "//p[contains(text(), '" + policyDescriptionClient + "')]", "xpath", policyDescriptionClient, "Policy");
	}
	
	
	public void searchNews(String newsDescription)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 21 Dec 2019
		 */ 
		
		Utils.enterText(driver, searchbox_News, newsDescription, "News Search box");
	}
	
	
	public void searchPolicy(String policyTitleBoth)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 21 Dec 2019
		 */ 
		
		Utils.enterText(driver, searchbox_policy, policyTitleBoth, "Search box");
		
		Utils.clickElement(driver, btn_searchPolicy, "Search");
	}
	
	
	public void verifyNews(boolean value)
    {
    	// Created by Kuchinad Shashank
        // Created on 03/02/20
		
		if(value)
		{
			if(!Utils.isElementPresent(driver, "//div[@id='tab-news']/div[@class='norecords-text']", "xpath"))
			{
				Log.pass("Verified. The News tab is present under the News from Left-Menu in FNP Login");
			}
			
			else
			{
				Log.fail("Failed to Verify. The News tab is not present under the News from Left-Menu in FNP Login");
			}
		}
    	
    	else
    	{   			
			if(Utils.isElementPresent(driver, "//div[@id='tab-news']/div[@class='norecords-text']", "xpath"))
    		{
    			Log.pass("Verified. The News tab is not present under the News from Left-Menu in FNP Login");
    		}
    		
    		else
    		{
    			Log.fail("failed to Verify. The News tab is present under the News from Left-Menu in FNP Login");
    		}

		}
    	
    }
	
	
	
	public void verifyPolicy(boolean value)
    {
    	// Created by Kuchinad Shashank
		//  Created on 3/02/2020
    	
    	if(value)
    	{
    		
    		if(!Utils.isElementPresent(driver, "//div[@id='tab-guidelines']/div[@class='norecords-text']", "xpath"))
    		{
    			Log.pass("Verified. The News tab is present under the News from Left-Menu in FNP Login");
    		}
    		
    		else
    		{
    			Log.fail("The News tab is not present under the News from Left-Menu in FNP Login");
    		}
    	}
    	else
    	{
    		
        	if(Utils.isElementPresent(driver, "//div[@id='tab-guidelines']/div[@class='norecords-text']", "xpath"))
    		{
    			Log.pass("Verified. The News tab is not present under the News from Left-Menu in FNP Login");
    		}
    		else
    		{
    			Log.fail("The News tab is present under the News from Left-Menu in FNP Login");
    		}

    	}
    	
    }

}
