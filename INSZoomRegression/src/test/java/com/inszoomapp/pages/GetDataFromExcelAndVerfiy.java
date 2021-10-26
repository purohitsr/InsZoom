package com.inszoomapp.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.ExcelToDataProvider;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.RowCountColumnCount;
import com.support.files.Utils;

import java.text.*;

public class GetDataFromExcelAndVerfiy extends LoadableComponent<GetDataFromExcelAndVerfiy> {
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	public GetDataFromExcelAndVerfiy(WebDriver driver) {
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
			Log.fail("Details and dates Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	
    public void test1(String workbookName, String sheetName) throws Exception
    {
		ExcelToDataProvider reader = new ExcelToDataProvider();
		Object[][] data = reader.userFormData(workbookName, sheetName);
		RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		for(int i = 0 ; i < noOfRows-1 ; i++)
		{
			if(data[i][0].equals("Beneficiary"))
			{
				System.out.println("Beneficiary");
					if(Utils.waitForElementIfPresent(driver, "//tr/td[text()='From Beneficiary']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../following-sibling::td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString()))                     
					Log.pass("Verified "+data[i][1]);
					else{
						if(Utils.waitForElementIfPresent(driver, "//tr/td[text()='From Beneficiary']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]", 5, "xpath", data[i][1].toString()))
						{
							//Log.message(data[i][1] + "present");
							if(!(Utils.waitForElementIfPresent(driver, "//tr/td[text()='From Beneficiary']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../following-sibling::td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString())))
							{
								Log.failsoft("Incorrect Required field for " + data[i][1]);
							}
						}
						else 
						{
							Log.failsoft("Unable to verify " + data[i][1]);
						}
					}

		    }
			else if(data[i][0].equals("Dependent"))
			{
				System.out.println("Dependent");
				if(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../preceding-sibling::tr[preceding-sibling::tr/td[text()='From Dependent']]/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString()))                     
				Log.pass("Verified "+data[i][1]);
				else{
					if(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../preceding-sibling::tr[preceding-sibling::tr/td[text()='From Dependent']]/td/font[text()=\""+ data[i][1].toString() +"\"]", 5, "xpath", data[i][1].toString()))
					{
						//Log.message(data[i][1] + "present");
						if(!(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../preceding-sibling::tr[preceding-sibling::tr/td[text()='From Dependent']]/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString())))
						{
							Log.failsoft("Incorrect Required field for " + data[i][1]);
						}
					}
					else 
					{
						Log.failsoft("Unable to verify " + data[i][1]);
					}
				}

			}
			else{		
				System.out.println("Sponsor");
					if(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString()))
					Log.pass("Verified "+data[i][1]);
					else{
						if(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]", 5, "xpath", data[i][1].toString()))
						{
							//Log.message(data[i][1] + "present");
							if(!(Utils.waitForElementIfPresent(driver, "//td[text()='From Sponsor']/../following-sibling::tr/td/font[text()=\""+ data[i][1].toString() +"\"]/../../td[text()=\""+ data[i][3].toString() +"\"]", 5, "xpath", data[i][1].toString())))
							{
								Log.failsoft("Incorrect Required field for " + data[i][1]);
							}
						}
						else 
						{
							Log.failsoft("Unable to verify " + data[i][1]);
						}
					}

		    }
		}
		System.out.println("Hello");
    }
	
}