package com.inszoomapp.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class HRPNewsPage extends LoadableComponent<HRPNewsPage> 
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
	
	public HRPNewsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyIfNewsPresent(String newsTitleClient, String newsDescriptionClient)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 05 Dec 2019
		 */ 
		
		Utils.verifyIfDataPresent(driver, "//b[contains(text(), '" + newsTitleClient + "')]", "xpath", newsTitleClient, "News");
		
		Utils.verifyIfDataPresent(driver, "//a[contains(text(), '" + newsDescriptionClient + "')]", "xpath", newsDescriptionClient, "News");
	}
	
	
	public void verifyIfNewsDeleted(String newsTitleClient)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Feb 2019
		 */ 
		
		Utils.verifyIfDataNotPresent(driver, "//b[contains(text(), '" + newsTitleClient + "')]", searchbox_News, "xpath", newsTitleClient, "News");
	}
	
	
	public void clickPolicyGuidelinesTab()
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 07 Feb 2019
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
		
		Utils.clickDynamicElement(driver, "//b[contains(text(), '" + policyTitleClient + "')]/../../following-sibling::div//a[contains(text(), 'Read more')]", "xpath", "Read More");
		
		Utils.verifyIfDataPresent(driver, "//p[contains(text(), '" + policyDescriptionClient + "')]", "xpath", policyDescriptionClient, "Policy");
	}
	
	
	public void searchNews(String newsDescription) throws InterruptedException
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 21 Dec 2019
		 */ 
		
		Utils.enterText(driver, searchbox_News, newsDescription, "News Search box");
		
//		Utils.waitForElement(driver, "div[class='k-loading-mask']", globalVariables.elementWaitTime, "css");
//		Utils.waitForElementNotVisible(driver, "div[class='k-loading-mask']", "css");
//		
//		Utils.waitForAjax(driver);
		
		Thread.sleep(3000);
	}
	
	
	public void searchPolicy(String policyTitleBoth) throws InterruptedException
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 21 Dec 2019
		 */ 
		
		Utils.enterText(driver, searchbox_policy, policyTitleBoth, "Search box");
		
//		Utils.waitForElement(driver, "div[class='k-loading-mask']", globalVariables.elementWaitTime, "css");
//		Utils.waitForElementNotVisible(driver, "div[class='k-loading-mask']", "css");
//		
//		Utils.waitForAjax(driver);
		
		Thread.sleep(3000);
	}
	
	
	public void verifyAllNewsAndGuidelines()
    {
    	/*
    	 * Created by Yatharth Pandya on 4th March 2020
    	 */
    	
	    try{
			Utils.waitForElement(driver, "//div[@id='News']//div/p", globalVariables.elementWaitTime, "xpath");
	    	List<WebElement> temp = driver.findElements(By.xpath("//div[@id='News']//div/p"));
	    	
	    	Set<String> corpNews = new HashSet<>();
	    	
//	    	Set<String> corpNewsName = new HashSet<>();
	    	
//	    	System.out.println(temp.size());
	    	
//	    	List<String> list=Arrays.asList("Corp1WithHQ", "Corp3WithHQ", "Corp4WithHQ");
//	    	List<String> list1 = globalVariables.corporationsUnderHeadQuarter;
	    	for(int i = 0; i < temp.size(); i += 2)
	    	{
	    		System.out.println(temp.get(i).getText());
				String corpName = temp.get(i).getText().trim();
				corpNews.add(temp.get(i).getText().trim());
					
	    		System.out.println(temp.get(i+1).getText());
	            String newsName = temp.get(i+1).getText().trim();
	            
	            if(newsName.contains(corpName))
	            {
	            	if(newsName.equals(corpName + " News"))
	            	{
	            		Log.pass("Verfied. " + newsName + " matched with correct heading under " + corpName + " corporation");
	            	}
	            	else
	            	{
	            		Log.fail("News head did not match.");
	            	}
	            }
	            else
	            {
	            	Log.fail("Not all News is matching with Corporation name");
	            }	
	    	}
	    	
	    	if(globalVariables.corporationsUnderHeadQuarter.containsAll(corpNews))
	    	{
	    		Log.pass("All Corporation news present. ");
	    	}
	    	else
	    	{
	    		Log.fail("All Corportion news not present. ");
	    	}
	    	
	    }catch(Exception e)
	    {
	    	Log.fail("Unable to fetch News");
	    }
    		
    }
	
	
	public void verifyAllPolicy()
    {
    	/*
    	 * Created by Yatharth Pandya 
    	 * Created on 3/3/2020
    	 */
    	
    	try
    	{
    		Utils.waitForElement(driver, "//div[@id='Guidelines']",globalVariables.elementWaitTime, "xpath");
	    	List<WebElement> temp = driver.findElements(By.xpath("//div[@id='Guidelines']//div/p"));
	    	
	    	Set<String> corpPolicy = new HashSet<>();
	    	
    	
    		for(int i = 0; i < temp.size(); i += 3)
	        {
    			System.out.println(temp.get(i).getText());
    			String corpName = temp.get(i).getText().trim();
    			
    			corpPolicy.add(temp.get(i).getText().trim());
	    		System.out.println(temp.get(i+1).getText());
	            String policyName = temp.get(i+1).getText().trim();
       
	            if(policyName.contains(corpName))
	            {
	            	
	            	if(policyName.equals(corpName +" Policy"))
	            	{
	            		Log.pass("Verfied. " + policyName + " matched with correct heading under " + corpName + " corporation");
	            	}
	            	else
	            	{
	            		Log.fail("Policy Heading did not match");
	            	}
	            }
	            else
	            {
	            	Log.fail("Not All Policy matching with corporation Name");
	            }
	            
	        }
    		
    		if(globalVariables.corporationsUnderHeadQuarter.containsAll(corpPolicy))
    		{
    			Log.pass("All corportaion policies present.");
    		}
    		else
    		{
    			Log.fail("All coporation policy not present");
    		}
    		

    		
    	}catch(Exception e)
    	{
          Log.fail("Unable to fetch Policies. ");
    	}
    }

	
	public void verifySpecificNewsAndGuidelines()
    {
    	/*
    	 * Created by Yatharth Pandya on 5th March 2020
    	 */
    	
	    try{
			Utils.waitForElement(driver, "//div[@id='News']//div/p", globalVariables.elementWaitTime, "xpath");
	    	List<WebElement> temp = driver.findElements(By.xpath("//div[@id='News']//div/p"));
	    	
	    	Set<String> corpNews = new HashSet<>();
	    	
			Utils.waitForElement(driver, "//i[@class='fa fa-building']/following-sibling::span", globalVariables.elementWaitTime, "xpath");
	    	String text = driver.findElement(By.xpath("//i[@class='fa fa-building']/following-sibling::span")).getText();
			String[] name = text.split("\n");
			System.out.println(name[0]);
	    	
	    	
	    	for(int i = 0; i < temp.size(); i += 2)
	    	{
	    		System.out.println(temp.get(i).getText());
				String corpName = temp.get(i).getText().trim();
				corpNews.add(temp.get(i).getText().trim());
					
	    		System.out.println(temp.get(i+1).getText());
	            String newsName = temp.get(i+1).getText().trim();
	            
	            if(name[0].equals(corpName))
	            {
	            	if(newsName.equals(corpName + " News"))
	            	{
	            		Log.pass("Verfied. " + newsName + " matched with correct heading under " + corpName + " corporation");
	            	}
	            	else
	            	{
	            		Log.fail("News head did not match.");
	            	}
	            }
	            else
	            {
	            	Log.fail("Not all News is matching with Corporation name");
	            }	
	    	}
	    	
	    }catch(Exception e)
	    {
	    	Log.fail("Unable to fetch News");
	    }
    		
    }
	
	
	public void verifySpecficPolicy()
    {
    	/*
    	 * Created by Yatharth Pandya 
    	 * Created on 5/3/2020
    	 */
    	
    	try
    	{
    		Utils.waitForElement(driver, "//div[@id='Guidelines']",globalVariables.elementWaitTime, "xpath");
	    	List<WebElement> temp = driver.findElements(By.xpath("//div[@id='Guidelines']//div/p"));
	    	
	    	Set<String> corpPolicy = new HashSet<>();
	    	
			Utils.waitForElement(driver, "//i[@class='fa fa-building']/following-sibling::span", globalVariables.elementWaitTime, "xpath");
	    	String text = driver.findElement(By.xpath("//i[@class='fa fa-building']/following-sibling::span")).getText();
			String[] name = text.split("\n");
			System.out.println(name[0]);
    	
    		for(int i = 0; i < temp.size(); i += 3)
	        {
    			System.out.println(temp.get(i).getText());
    			String corpName = temp.get(i).getText().trim();
    			
    			corpPolicy.add(temp.get(i+1).getText().trim());
	    		System.out.println(temp.get(i+1).getText());
	            String policyName = temp.get(i+1).getText().trim();
       
	            if(name[0].equals(corpName))
	            {
	            	
	            	if(policyName.equals(corpName +" Policy"))
	            	{
	            		Log.pass("Verfied. " + policyName + " matched with correct heading under " + corpName + " corporation");
	            	}
	            	else
	            	{
	            		Log.fail("Policy Heading did not match");
	            	}
	            }
	            else
	            {
	            	Log.fail("Not All Policy matching with corporation Name");
	            }
	            
	        }
    		
    	}catch(Exception e)
    	{
          Log.fail("Unable to fetch Policies. ");
    	}
    }

}
