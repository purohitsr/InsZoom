package com.inszoomapp.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class HRP_HeadQuarterPage extends LoadableComponent<HRP_HeadQuarterPage>{
	
	private WebDriver driver;

	public HRP_HeadQuarterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	public void getHeadQuarterBranchesName()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 3/03/2020
		 */
		globalVariables.corporationBranchesOfHQ.clear();
		
		Utils.waitForElement(driver, "//div[@id='CorporationDetails']//td", globalVariables.elementWaitTime, "xpath");
		List<WebElement> branches = driver.findElements(By.xpath("//div[@id='CorporationDetails']//td"));
		for(int i=0 ; i<branches.size(); i+=3)
		{
			globalVariables.corporationBranchesOfHQ.add(branches.get(i).getText().trim());
		}
		System.out.println(globalVariables.corporationBranchesOfHQ);
		Log.message("", driver, true);
	}
	
	
	public void verifyViewAllBranches()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 2/03/2020
		 */
		
		List<WebElement> corpNameData = new ArrayList<>();
		
		Utils.waitForElement(driver, "//div[@id='CorporationDetails']//td", globalVariables.elementWaitTime, "xpath");
		corpNameData = driver.findElements(By.xpath("//div[@id='CorporationDetails']//td"));
		
		List<String> corpName = new ArrayList<>();
		
		for(int i=0; i < corpNameData.size(); i=i+3)
		{
			String name = corpNameData.get(i).getText();
			
			corpName.add(name);
		}
		
		System.out.println(globalVariables.corporationsUnderHeadQuarter);
		System.out.println(corpName);
		
		if(globalVariables.corporationsUnderHeadQuarter.containsAll(corpName))
		{
			Log.pass("Verfied. All Corporation are present under Branches", driver, true);
		}
		
		else
		{
			Log.fail("Not all Corporation Under HQ is Present in Branches",driver,true);
		}
	}
	
	
	public void verifyViewSpecificBranches()
	{
		/*
		 * Added By: Kuchinad Shashank
		 * Added On: 4/03/2020
		 * Modified by: Nitisha Sinha
		 * Modified on: 15th July,2020
		 * Modification done: changed xpath for noOfPages
		 */
		
		int i=0;
		
		List<WebElement> noOfPages = new ArrayList<WebElement>();
		
		//Utils.waitForElement(driver, "//ul[@class='k-pager-numbers k-reset']//li", globalVariables.elementWaitTime, "xpath");
		noOfPages = driver.findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']//li"));
		
		List<WebElement> corpName = new ArrayList<WebElement>();
		
		List<String> corporationName = new ArrayList<>();
		
		do
		{
			if(noOfPages.size() > 0 && i != 0)
			{
				noOfPages.get(i).click();
				i++;
			}
			Utils.waitForElement(driver, "//td/a", globalVariables.elementWaitTime, "xpath");
			corpName= driver.findElements(By.xpath("//td/a"));
			
			for(int j = 0; j < corpName.size(); j++ )
			{
				String text = corpName.get(j).getText();
				corporationName.add(text);
			}
			
			i++;
		}while(i < noOfPages.size()+1);
			
		Utils.waitForElement(driver, "//i[@class='fa fa-building']/following-sibling::span", globalVariables.elementWaitTime, "xpath");
		String text = driver.findElement(By.xpath("//i[@class='fa fa-building']/following-sibling::span")).getText();
		String[] name = text.split("\n");
		System.out.println(name[0]);
		if(corporationName.contains(name[0]))
		{
			Log.pass("Specific Corps are present in HeadQuarter Branches", driver, true);
		}
		else
		{
			Log.fail("Not all Specific Corps are present in the headQuarters branches Tab", driver, true);
		}
	}
}
