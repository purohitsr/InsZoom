package com.support.files;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;


/**
 * Util class consists wait for page load,page load with user defined max time and is used globally in all classes and methods
 * 
 */
public class Utils {
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
//	public static int maxElementWait = 25;

	/**
	 * waitForPageLoad waits for the page load with default page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public static void waitForPageLoad(final WebDriver driver) 
	/*{
		waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
	}*/

	
	{

        try   {             

              waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
              String brwsrName = globalVariables.browserUsedForExecution.split("_")[0].toLowerCase().trim();
              if(brwsrName.equalsIgnoreCase("Chrome")) {
//            Thread.sleep(5000);
            	  ((FluentWait<WebDriver>) driver)
                 .withTimeout(120, TimeUnit.SECONDS)
               .pollingEvery(5, TimeUnit.SECONDS)
               .ignoring(NoSuchElementException.class);
              }

              else if((brwsrName.equalsIgnoreCase("iexplorer")) || (brwsrName.equalsIgnoreCase("safari"))){
//                  Thread.sleep(15000);
                    ((FluentWait<WebDriver>) driver)
                       .withTimeout(180, TimeUnit.SECONDS)
                     .pollingEvery(5, TimeUnit.SECONDS)
                     .ignoring(NoSuchElementException.class);
                    }
              else {
                    ((FluentWait<WebDriver>) driver)
                       .withTimeout(100, TimeUnit.SECONDS)
                     .pollingEvery(5, TimeUnit.SECONDS)
                     .ignoring(NoSuchElementException.class);
              }
        }catch(Exception e) {
             

        }

  }	
	
	
	/**
	 * waitForPageLoad waits for the page load with default page load wait time based on the environment used for execution(IE/Chrome/Safari)
	 * 
	 * @param driver
	 *            : Webdriver
	 * @throws InterruptedException 
	 */
	public static void waitForPageLoad(final WebDriver driver, String brwsrName) throws InterruptedException 
	{
		try	{		
			
			waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
			
			brwsrName = globalVariables.browserUsedForExecution.split("_")[0].toLowerCase().trim();
			
			if(brwsrName.equalsIgnoreCase("Chrome")) {
			Thread.sleep(5000);
			}
			else if((brwsrName.equalsIgnoreCase("iexplorer")) || (brwsrName.equalsIgnoreCase("safari"))){
				Thread.sleep(15000);
			}
			else {
				Thread.sleep(5000);
			}	
		
		}catch(Exception e) {
	           Log.message("Page load / switching in to Windows / loading of elements needs attention.....");
		}
	}
	
	/**
	 * waitForPageLoad waits for the page load with custom page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param maxWait
	 *            : Max wait duration
	 */
	public static void waitForPageLoad(final WebDriver driver, int maxWait) {
		//long startTime = StopWatch.startTime();
		FluentWait <WebDriver> wait = new WebDriverWait(driver, maxWait).pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
		try {

			if (configProperty.getProperty("documentLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.documentLoad);

			if (configProperty.getProperty("imageLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.imagesLoad);

			if (configProperty.getProperty("framesLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.framesLoad);

			String title = driver.getTitle().toLowerCase();
			String url = driver.getCurrentUrl().toLowerCase();
			//Log.event("Page URL:: " + url);

			if ("the page cannot be found".equalsIgnoreCase(title) || title.contains("is not available") || url.contains("/error/") || url.toLowerCase().contains("/errorpage/")) {
				Assert.fail("Site is down. [Title: " + title + ", URL:" + url + "]");
			}
		}
		catch (TimeoutException e) {
			driver.navigate().refresh();
			wait.until(WebDriverFactory.documentLoad);
			wait.until(WebDriverFactory.imagesLoad);
			wait.until(WebDriverFactory.framesLoad);
		}
		//Log.event("Page Load Wait: (Sync)", StopWatch.elapsedTime(startTime));

	} // waitForPageLoad

	/**
	 * To get the test orientation
	 * 
	 * <p>
	 * if test run on sauce lab device return landscape or portrait or valid message, otherwise check local device execution and return landscape or portrait or valid message
	 * 
	 * @return dataToBeReturned - portrait or landscape or valid message
	 */
	public static String getTestOrientation() {
		String dataToBeReturned = null;
		boolean checkExecutionOnSauce = false;
		boolean checkDeviceExecution = false;
		checkExecutionOnSauce = (System.getProperty("SELENIUM_DRIVER") != null || System.getenv("SELENIUM_DRIVER") != null) ? true : false;

		if (checkExecutionOnSauce) {
			checkDeviceExecution = ((System.getProperty("runUserAgentDeviceTest") != null) && (System.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = (System.getProperty("deviceOrientation") != null) ? System.getProperty("deviceOrientation") : "no sauce run system variable: deviceOrientation ";
			}
			else {
				dataToBeReturned = "sauce browser test: no orientation";
			}
		}
		else {
			checkDeviceExecution = (configProperty.hasProperty("runUserAgentDeviceTest") && (configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = configProperty.hasProperty("deviceOrientation") ? configProperty.getProperty("deviceOrientation") : "no local run config variable: deviceOrientation ";
			}
			else {
				dataToBeReturned = "local browser test: no orientation";
			}
		}
		return dataToBeReturned;
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element) {
		return waitForElement(driver, element, globalVariables.elementWaitTime);
	}
	
	/**
	 * To select an submenu option on the left menu of corporation/client/case
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param option
	 *            : Left menu tab option to select
	 * Author : Sharon Mathew
	 * Date : 10/8/21
	 */
	public static void selectSubMenuOption(WebDriver driver, String option) {
		try
		{
		clickDynamicElement(driver,"//td[@class='LMIS']//span[contains(text(),'"+option+"')]","xpath",option+" Left Menu Tab");
		}
		catch(Exception e)
		{
			 Log.fail("Failed to click desired submenu option : "  + e.getMessage(), driver, true);
		}
	}

	/**
	 * To select an option on the left menu of corporation/client/case
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param option
	 *            : Checkbox option to select
	 * Author : Sharon Mathew
	 * Date : 10/8/21
	 */
	public static void verifyandSelectCheckBox(WebDriver driver, String option) {
		try
		{	
			waitForElementIfPresent(driver,"//td[contains(text(),'"+option+"')]//parent::tr//following::input[@type='checkbox']",30,"xpath","Checkbox Visible");
			WebElement element=driver.findElement(By.xpath("//td[contains(text(),'"+option+"')]//parent::tr//following::input[@type='checkbox']"));
		try
		{
			String value=element.getAttribute("checked");
			if(value.equals("true"))
			{
				Log.message(option+" is already enabled.");
			}
		}
		catch(Exception e)
		{
			Log.message(option+" is not enabled");
			Log.message(option+" is now being enabled");
			clickElement(driver,element,option+" Left Menu Tab");
			WebElement saveBtn=driver.findElement(By.id("btn_AddRemoveshortcuts"));
			clickElement(driver,saveBtn,option+"Save Button");
			Log.message(option+" is now enabled");
		}
		}
		catch(Exception e)
		{
			Log.fail("Failed to enable the checkbox"  + e.getMessage(), driver, true);
		}
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @param maxWait
	 *            : Max wait duration
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		WebElement waitElement=null;
		try {
			waitElement = wait.until(ExpectedConditions.visibilityOf(element));                   
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
				
			}
		}
		catch (Exception e) {
		       
			statusOfElementToBeReturned = false;
			Log.message(element + "Element Not found", driver, true);
			
		}
		return statusOfElementToBeReturned;
	}
	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @param maxWait
	 *            : Max wait duration
	 * @return boolean - return true if element is present else return false
	 */
    public static boolean waitForElement(WebDriver driver, String locator, int maxWait,String by) 
    {
          
          /*
          * Modified By: Saksham Kapoor
          * Modified On: 09/09/2019
          * Modifications : Added search by name and id clause
          */
          
          boolean statusOfElementToBeReturned = false;
          //long startTime = StopWatch.startTime();
          WebDriverWait wait = new WebDriverWait(driver, maxWait);
          try {
              if(by.equals("xpath")){
                WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));                   
                if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                      statusOfElementToBeReturned = true;
                      
                }
              }
              
              if(by.equals("name")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
              
              if(by.equals("css")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
                
              if(by.equals("id")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
          }
          catch (Exception e) {
                statusOfElementToBeReturned = false;
                Log.fail("Waited but element not found. ERROR - "  + e.getMessage(), driver, true);
                
          }
          return statusOfElementToBeReturned;
    }
    
    
    public static boolean waitForElementIfPresent(WebDriver driver, String locator, int maxWait,String by,String element) 
    {
          
         /*
          * Created By: M Likitha Krishna
          * Created On: 14 Nov 2019
          */
          
          boolean statusOfElementToBeReturned = false;
          //long startTime = StopWatch.startTime();
          WebDriverWait wait = new WebDriverWait(driver, maxWait);
          try {
              if(by.equals("xpath")){
                WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));                   
                if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                      statusOfElementToBeReturned = true;
                      
                }
              }
              
              if(by.equals("name")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
              
              if(by.equals("css")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
                
              if(by.equals("id")){
                      WebElement waitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));                   
                      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
                            statusOfElementToBeReturned = true;
                            
                      }
                    }
          }
          catch (Exception e) {
                statusOfElementToBeReturned = false;
               // Log.failsoft("Unable to find element "+element);
                
          }
          return statusOfElementToBeReturned;
    }
    
    
    public static void waitForElementPresent(WebDriver driver, String locator, String by) 
    {
          
          /*
          * Created By: Saksham Kapoor
          * Created On: 18/10/2019
          * Functionality : To wait for element to be present in the DOM
          */

          WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
          try 
          {
              if(by.equalsIgnoreCase("xpath"))
              {
            	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));    
              }
              
              if(by.equalsIgnoreCase("css"))
              {
            	  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));    
              }
              
              if(by.equalsIgnoreCase("name"))
              {
            	  wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));    
              }
              
              if(by.equalsIgnoreCase("id"))
              {
            	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));    
              }
          }
          catch (Exception e) {
                Log.message("Waited but element not found. ERROR - "  + e.getMessage(), driver, true);
                
          }
    }
    

	
	public static boolean waitForElementClickable(WebDriver driver, WebElement element)
	{
		
		/* Created By : Saksham Kapoor
		 * Created On : 09/05/2019
		 */
		
		boolean truth = false;
		WebDriverWait wait = new WebDriverWait(driver, globalVariables.pageLoadTime);
		try
		{
			WebElement waitElement = wait.until((ExpectedConditions.elementToBeClickable(element)));
			if(waitElement != null)
			{
				truth = true;
			}
		}catch(Exception e) {
		        e.printStackTrace();
			truth = false;
			Log.fail("Element not clickable", driver, true);
		}
		return truth;
	}
	
	public static void waitForElementTobeReDrawn(WebDriver driver, WebElement element)
	{
		
		/* Created By : Saurav Purohit
		 * Created On : 09/05/2019
		 */
		

		WebDriverWait wait = new WebDriverWait(driver, globalVariables.pageLoadTime);
		try
		{
			wait.until(ExpectedConditions.visibilityOf(element));
			
		}catch(StaleElementReferenceException e) {
		        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
		        e.printStackTrace();
		        Log.message("Element Redrawing failed "+e.getMessage());
			
		}
		
	}
	
	public static void clickUsingAction(WebDriver driver, WebElement element)
	{
		
		/* Created By : Saksham Kapoor
		 * Created On : 09/05/2019
		 */
		
		Utils.waitForElement(driver, element);
		Actions action = new Actions(driver);
		try
		{
			action.moveToElement(element).click().build().perform();
		}catch(Exception e) {
			Log.fail("Unable to click on the element " + e.getMessage(), driver, true);
		}
	}
	
	
	public static WebDriver switchWindows(WebDriver driver, String windowToSwitch, String opt, String closeCurrentDriver) throws Exception {

		/*	Modified By: Saksham Kapoor 
		 * 	Modified On: 12/07/2019
		 *  Modified On: 15/07/2019
		 */
		
		WebDriver currentWebDriver = driver;
		WebDriver assingedWebDriver = driver;
		boolean windowFound = false;
		
		ArrayList <String> multipleWindows = new ArrayList <String>(assingedWebDriver.getWindowHandles());

		for (int i = 0; i < multipleWindows.size(); i++) {

			assingedWebDriver.switchTo().window(multipleWindows.get(i));

			if (opt.equals("title")) {
			   while(assingedWebDriver.getTitle().equals("")){
			       WebDriverWait wait = new WebDriverWait(driver, globalVariables.pageLoadTime);
			       wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
			   }
			   
//				if (assingedWebDriver.getTitle().equals(windowToSwitch)) {
				    if (assingedWebDriver.getTitle().contains(windowToSwitch)) {
					windowFound = true;
					break;
				}
			}
			else if (opt.equals("url")) {
				if (assingedWebDriver.getCurrentUrl().contains(windowToSwitch)) {
					windowFound = true;
					break;
				}
			}// if

		}// for

		if (!windowFound)
		{
			throw new Exception("Window: " + windowToSwitch + ", not found!!");
		}
		else {
			if (closeCurrentDriver.equals("true"))
				currentWebDriver.close();
		}

		return assingedWebDriver;

	}// switchWindows
	
	/**
     * To get matching text element from List of web elements
     * 
     * @param elements - 
     * @param contenttext - text to match
     * @return elementToBeReturned as WebElement
     * @throws Exception -
     */
    public static WebElement getMachingTextElementFromList(List<WebElement> elements, String contenttext) throws Exception {
        WebElement elementToBeReturned = null;
        boolean found = false;

        if (elements.size() > 0) {
            for (WebElement element : elements) {
                if (element.getText().trim().replaceAll("\\s+", " ").equalsIgnoreCase(contenttext)) {
                    elementToBeReturned = element;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Didn't find the correct text(" + contenttext + ")..! on the page");
            }
        } else {
            throw new Exception("Unable to find list element...!");
        }
        return elementToBeReturned;
    }  
    

    /**
     * Switching between tabs or windows in a browser
     * 
     * @param driver -
     */
    public static void switchToNewWindow(WebDriver driver) {
        String winHandle = driver.getWindowHandle();        
        for (String index : driver.getWindowHandles()) {
            if (!index.equals(winHandle)) {
                driver.switchTo().window(index);
                break;
            }
        }
        if (!((RemoteWebDriver) driver).getCapabilities().getBrowserName().matches(".*safari.*")) {
            ((JavascriptExecutor) driver).executeScript("if(window.screen)"
                    + "{window.moveTo(0, 0);"
                    + " window.resizeTo(window.screen.availWidth, window.screen.availHeight);"
                    + "};");
        }
    }    

    
    /**
     * To close a newly opened window
     * @param driver -
     */
    public static void closeNewWindow(WebDriver driver) {
        if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().matches(".*Edge")) {
            ((JavascriptExecutor)driver).executeScript("window.top.close();");
        } else {
            driver.close();
        }
    }	
    
    /**
     * Closes given window & switches to the other window handle passed
     * @param driver
     * @param windowHandleToClose
     * @param windowHandleToSwitchTo
     */
    public static void closeWindowAndSwitchtoWindow(WebDriver driver,String windowHandleToClose, String windowHandleToSwitchTo) {
        // Log.event("Closing current window");
        driver.switchTo().window(windowHandleToClose);
        driver.close();
        driver.switchTo().window(windowHandleToSwitchTo);
    }
	
    /**
     * To scroll into particular element
     * 
     * @param driver -
     * @param element - the element to scroll to
     */
    public static void scrollIntoView(final WebDriver driver, WebElement element) {
        try {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
        } catch (Exception ex) {
            Log.event("Moved to element..");
        }
    }
    
    
    /*To Verify that a Particular Element is Present in the Page
     * @param - the element of which presence to be verified
     * @param - driver
     * Created By : @sauravp
     * 
     */
    
    public static boolean isElementPresent(WebDriver driver,String element,String by)
    {
      try
      {
          List<WebElement> elementList = new ArrayList<WebElement>();
          switch(by)
          {
          	case "xpath":
	            elementList = driver.findElements(By.xpath(element));
	            break;
          
          	case "id":
	            elementList = driver.findElements(By.id(element));
	            break;
          	case "css":
	            elementList = driver.findElements(By.cssSelector(element));
	            break;
          }
          if(elementList.size()>0)
          {
            return true;
          }
          else 
          {
            return false;
          }
      }catch(Exception e){
    	  return false;
      }
    }
    
    
   
    
    public static void scrollToElement(WebDriver driver, WebElement element)
	{
		
		/* Created By : Saksham Kapoor
		 * Created On : 16/06/2019
		 */

		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
		}catch(Exception e) {
			Log.fail("Failed to Scroll " + e.getMessage(), driver, true);
		}
	} 
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : int : expectedWindowSize ,WebDriver driver
     * This Method helps wait till the expected no of Windows pops up .
     * name as an argument .
     */
    
    
    
    public static void waitForAllWindowsToLoad(int expectedWindowSize,WebDriver driver){
		try {
			ExpectedCondition<Boolean> windowCondition = new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver arg0) 
				{
					return driver.getWindowHandles().size() == expectedWindowSize;
				}
			};
			WebDriverWait waitForWindow = new WebDriverWait(driver, globalVariables.elementWaitTime);
			waitForWindow.until(windowCondition);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Error while waiting for " + expectedWindowSize + " windows to load . ERROR - " + e.getMessage());
		}
    }
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * This Method takes a date in String format and returns the time difference between current data and the 
     * parameter .
     *      */
    public static long getTimeDifference(String date1) throws ParseException{

	DateFormat sdf  = new SimpleDateFormat("MMMM d yyyy hh:mm aa");
	Date date = sdf.parse(date1);
	System.out.println(date);
	
	System.out.println(new Date());
	
	long mills =new Date().getTime()-date.getTime();
	
	long difference = (mills / 1000)  / 60;
	return difference;
    }
    
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : driver: Webdriver,String option to select , WebElement dropdown
     * This Method selects a option from the dropdown .
     * name as an argument .
     */
    	
	public static void selectOptionFromDropDown(WebDriver driver,String option,WebElement dropList) throws Exception{
	try{

	    WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(dropList));
		}catch(Exception e){
			e.printStackTrace();
		}
	    Select selectObj = new Select(dropList);
	    waitForOptionsAvaiable(driver, selectObj);
        selectObj.selectByVisibleText(option);
	    Log.message("Selected option "+option+" from Dropdown By Visible text");
	}catch(Exception e){
	    e.printStackTrace();
	    throw new Exception("Failed to select " + option + " from Dropdown. ERROR - " + e.getMessage());
	}
	
    } 
	 

    
    
    public static void selectOptionByClick(WebDriver driver, String option, WebElement dropDown, WebElement searchBox, String result)
    {
    	
    	/*
    	 * Created By : Saksham Kapoor
    	 * Created On : 08/08/2019
    	 * 
    	 * This function is created to select an option from a dropdown. 
    	 * First we type the required option and then click on the appropriate option to select.
    	 * This function has been created as sometimes searching by visible text does not work. 
    	 * 
    	 */
    	
    	try {
    		Utils.waitForElement(driver, dropDown);
			dropDown.click();
			Log.message("Clicked on Drop Down");
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to click on dropdown . Error Message - " + e.getMessage());
		}
    	
		try {
			Utils.waitForElement(driver, searchBox);
			searchBox.click();
			searchBox.clear();
			Thread.sleep(1000);
			searchBox.sendKeys(option);
			Log.message("Typed " + option + " in the search box");
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to type " + option + " in the search Box. Error Message - " + e.getMessage(), driver, true);
		}
		
		
		try {
			Utils.waitForElement(driver, result, globalVariables.elementWaitTime, "xpath");
			driver.findElement(By.xpath(result)).click();
			Log.message("Chose the option as " + option);
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to choose option as " + option + ". Error Message - " + e.getMessage());
		}
    	
    }
    
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : driver: Webdriver,String option to select , WebElement dropdown
     * This Method selects a option from the dropdown By value .
     * name as an argument .
     */
    public static void selectOptionFromDropDownByVal(WebDriver driver,String option,WebElement dropList){
	try{
	    Utils.waitForElement(driver, dropList);
	    Select selectObj = new Select(dropList);
            selectObj.selectByValue(option);
	    Log.message("Selected option "+option+" from Dropdown By Value");
	}catch(Exception e){
	    e.printStackTrace();
	    Log.fail("Failed to select "+option+" from Dropdown. ERROR - " + e.getMessage(), driver, true);
	}
	
    }
    
    

    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : driver: Webdriver,String option to select , WebElement dropdown
     * This Method selects a option from the dropdown By value .
     * name as an argument .
     */
    public static void selectOptionFromDropDownByIndex(WebDriver driver,int option,WebElement dropList){
	try{
	    Utils.waitForElement(driver, dropList);
	    Select selectObj = new Select(dropList);
            selectObj.selectByIndex(option);
	    Log.message("Selected option "+option+" from Dropdown By Index");
	}catch(Exception e){
	    e.printStackTrace();
	    Log.fail("Failed to select "+option+" from Dropdown. ERROR - " + e.getMessage(), driver, true);
	}
	
    }
    
    
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : driver: Webdriver,String option to select , WebElement dropdown
     * This Method selects a option from the dropdown .
     * name as an argument .
     */
   /* public static void waitUntilSelectOptionsPopulated(Select select,WebDriver driver){
    	
    	
    	try{
    	 new FluentWait<WebDriver>(driver)
             .withTimeout(60, TimeUnit.SECONDS)
             .pollingEvery(10, TimeUnit.MILLISECONDS) 
             .ignoring(StaleElementReferenceException.class)
             .until(new Predicate<WebDriver>() {
                 public boolean apply(WebDriver d) {
                     return (select.getOptions().size() > 1);
                 }
               
             });
    	
    	}catch(StaleElementReferenceException e){
    	   e.printStackTrace();
    	}
    	 
        }*/
    
    
    
    
    /* Created By : Sauravp
     * Created On : 28/05/2019
     * @param     : driver: Webdriver,String option to select , WebElement dropdown
     * This Method selects a option from the dropdown .
     * name as an argument .
     */
    public static void waitUntilLoaderDisappear(WebDriver driver){
	
	
	try{
	    WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@class='MyLoadingImage' and @alt='Loading...']")));
	
	}catch(StaleElementReferenceException e){
	   e.printStackTrace();
	}
	 
    }
    
	
	 public static void waitUntillLoaderDisappearsInHRP(WebDriver driver)
    {
    	
    	/*
    	 * Created By: Saksham Kapoor
    	 * Created on: 31/07/2019
    	 * Functionality :  This function waits for the loaders in HRP and FNP to disappear
    	 * 
    	 */
	    try {
			WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading']")));
		} catch (Exception e) {
			Log.fail("Error while waiting for loader to disappear. Error - " + e.getMessage(), driver, true);
		}
    }
    
    
    
    /* Created By : Sauravp
     * Created On : 18/06/2019
     * @param     : driver: Webdriver,String Locator to waitFor, selectBy locator 
     * This Method Waits till an Element is Not Visible But Is Present In DOM.
     * name as an argument .
     */
    public static void waitForElementNotVisible(WebDriver driver,String sLocator,String by){
	
	WebDriverWait wait = new WebDriverWait(driver, globalVariables.pageLoadTime);
	try{
	    if(by.equals("id"))
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(sLocator)));
	    else if(by.equals("xpath"))
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(sLocator)));
	    else if(by.equals("css"))
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(sLocator)));
	    else if(by.equals("name"))
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(sLocator)));
	}catch(StaleElementReferenceException e){
	   Log.fail("Waited for Element toBe Not Present But Element is Still Present", driver, true);
	   e.printStackTrace();
	}
	 
    }
    
    
    /* Created By : Sauravp
     * Created On : 18/06/2019
     * @param     : driver: Webdriver,Select Select
     * This Method Waits till a Select DropDown populates all options .
     * name as an argument .
     */
    public static void waitForOptionsAvaiable(WebDriver driver,Select select){
	WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
	ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
	    @Override
	    public Boolean apply(WebDriver arg0) {
		return select.getOptions().size()>1;
	    }
	    };
	try{
	    wait.until(condition);
	}catch(StaleElementReferenceException e){
	   Log.fail("Waited for Options toBe Populated but Failed to Populate The options", driver, true);
	   e.printStackTrace();
	}
	 
    }
    
    
    
    /* Created By : Sauravp
     * Created On : 18/06/2019
     * @param     : driver: Webdriver,Select Select
     * This Method Waits till a Select DropDown populates all Group options .
     * name as an argument .
     */
    public static void waitForOptionGroupsAvaiable(WebDriver driver,WebElement element){
    	try{
			WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
			ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() 
			{
			    @Override
			    public Boolean apply(WebDriver arg0) 
			    {
			    	return element.findElements(By.tagName("optgroup")).size()>0;
			    }
	    };
	try{
	    wait.until(condition);
	}catch(StaleElementReferenceException e){
	   e.printStackTrace();
	   Log.message("Waited for Options group toBe Populated but group options populate failed ", driver, true);
	   
	}
    	}catch(Exception e){
    		Log.message("Waited for Options group toBe Populated but group options populate failed ", driver, true);
    	}
	 
    }
  
  /*
   * This method will waits for element and retrieve it based on locator passed as parameter
   * This does not use Page Factory approach to fetch element . only the locator is 
   * passed here . This is introduced to handle staleElemenetException which occours on
   * Pages using latest techstack i.e AJAX .
   * created by: sauravp
   * 
   */
  public static WebElement getElement(WebDriver driver, By locator){
      WebElement element = null;
      try{
		  waitForElementPresent(driver,locator);
		  element = driver.findElement(locator);
		  return element;
		  
      }catch(StaleElementReferenceException e){
    	  Log.message("Stale Element Exception Thrown . Hence rebuilding the element");
    	  element = driver.findElement(locator);
    	  return element;
	  
      }catch(Exception e){
    	  e.printStackTrace();
      }
      return element;
  }
  /*
   * This method Wait for an element based on locator String passed 
   * created By:sauravp
   */

   private static void waitForElementPresent(WebDriver driver ,By locator) {
      
       try{
	   
		   WebDriverWait wait = new WebDriverWait(driver, 60);
		   wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       }catch(Exception e){
	   
       }
    
}
   
   
   
   /*
    * 
    * created by: sauravp
    * This method is ceated to handle pageLoad of Complex Pages where AJAX calls are triiggerd but this seems to be Not working 
    * 
    */
   
   
   public static void waitForLoad(WebDriver driver) {
       new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
               ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
   }
   
   /*
    * This method will fetch PageLoadtimes . It uses java script to calculate pageloadtime 
    * created by: sauravp
    * 
    */
   
   public static long getPageLoadTime(WebDriver driver){
       
       long timeInSeconds=0;
       try{
	   
	   Long loadtime = (Long)((JavascriptExecutor)driver).
		   executeScript("return window.performance.timing.domComplete - performance.timing.responseStart;");
	   Long loadtime1 = (Long)((JavascriptExecutor)driver).
			   executeScript("return window.performance.timing.domComplete - performance.timing.navigationStart;");
           timeInSeconds = (loadtime + loadtime1)/1000;
           return timeInSeconds;
       }catch(Exception e){
	   
       }
       return timeInSeconds;
   }
   
   
   public static String getSelectedOptionFromDropDown(WebDriver driver,WebElement dropList){
    	try{
    	    Utils.waitForElement(driver, dropList);
    	    Select selectObj = new Select(dropList);
            String selectedOption = selectObj.getFirstSelectedOption().getText();
            Log.message("Fetched option "+selectedOption+" from Dropdown");
            return selectedOption ;    
    	}catch(Exception e){
    	    e.printStackTrace();
    	    Log.fail("Failed to fetch selected option from Dropdown", driver, true);
    	    return null;
    	}
    	
        }
    /*
     * Created by Sauravp
     * This Method loads till all ajax request are completed . This helps us deal with StaleElement Exception when the page
     * has Ajax call being executed . 
     * @Param : WebDriver driver
     */
    public static void waitForAjax(WebDriver driver) {
        ExpectedCondition<Boolean> windowCondition = new ExpectedCondition<Boolean>() {

 	    @Override
 	    public Boolean apply(WebDriver arg0) {
 		return (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");

 	    }
 	};
 	WebDriverWait waitForWindow = new WebDriverWait(driver,globalVariables.elementWaitTime);
 	waitForWindow.until(windowCondition);
    } 
    
     
	public static boolean isAlertPresent(WebDriver driver) 
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		} 
		catch (NoAlertPresentException e) 
		{
			return false;
		}
	}
  
	
	public static void clickElement(WebDriver driver, WebElement element, String message)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 10/10/2019
		 * Functionality : Click on any element.
		 * Parameters : element - element to be clicked, message - brief description about the element
		 */
		
		try {
			Log.message("Clicking on " + message);
			waitForElement(driver, element);
			waitForElementClickable(driver, element);
			//scrollToElement(driver, element);
//			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().x+")");
			element.click();
			
			Log.message("Clicked on " + message);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to click on " + message + ". ERROR :\n\n " + e.getMessage());
		}
	}
	
	
	public static String getText(WebDriver driver, WebElement element, String message)
	{
		String text=null;
		try {
			waitForElement(driver, element);
//			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
			text=element.getText();
			Log.message("Text Fetched from "+message);

		} catch (Exception e) {
			Log.fail("Unable to fetch text from "+message+". ERROR :\n\n " + e.getMessage(), driver, true);
		}
		return(text);
	}
	
	public static void enterText(WebDriver driver, WebElement element, String text, String message)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 10/10/2019
		 * Functionality : Type text in a text-box.
		 * Parameters : element - element to be clicked, text - the data to be typed, message - brief description about the element
		 */
		
		try {
			waitForElement(driver, element);
//			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
			Utils.waitForElementClickable(driver, element);
			element.click();
			Thread.sleep(1000);
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(text);
			Log.message("Typed " + text + " in " + message + " textbox.");

		} catch (Exception e) {
			Log.fail("Unable to type " + text + " in " + message + " textbox. ERROR :\n\n " + e.getMessage(), driver, true);
		}
	}
	
	
	public static void enterTextInDynamicElement(WebDriver driver, String element, String by, String text, String message)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 10/10/2019
		 * Functionality : Type text in a text-box.
		 * Parameters : element - element to be clicked, text - the data to be typed, message - brief description about the element
		 */
		
		try {
			waitForElement(driver, element, globalVariables.elementWaitTime, by);
			WebElement textbox = null;
			if(by.equalsIgnoreCase("id"))
				textbox = driver.findElement(By.id(element));
			
			if(by.equalsIgnoreCase("css"))
				textbox = driver.findElement(By.cssSelector(element));
			
			if(by.equalsIgnoreCase("xpath"))
				textbox = driver.findElement(By.xpath(element));
			
			if(by.equalsIgnoreCase("name"))
				textbox = driver.findElement(By.name(element));
	
			textbox.click();
			Thread.sleep(1000);
			textbox.clear();
			Thread.sleep(1000);
			textbox.sendKeys(text);
			//Log.message("Typed " + text + " in " + message + " textbox.");

		} catch (Exception e) {
			Log.fail("Unable to type " + text + " in " + message + " textbox. ERROR :\n\n " + e.getMessage(), driver, true);
		}
	}
	

	public static void clickDynamicElement(WebDriver driver, String element, String by, String message)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 10/10/2019
		 * Functionality : Click on any element.
		 * Parameters : element - element to be clicked, by - xpath/css/id etc. , message - brief description about the element
		 */
		
		try {
			Log.message("Clicking on " + message);
			
			waitForElement(driver, element, globalVariables.elementWaitTime, by);
			
			if(by.equalsIgnoreCase("id"))
				driver.findElement(By.id(element)).click();
			
			if(by.equalsIgnoreCase("css"))
				driver.findElement(By.cssSelector(element)).click();
			
			if(by.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(element)).click();
			
			if(by.equalsIgnoreCase("name"))
				driver.findElement(By.name(element)).click();
			
			Log.message("Clicked on " + message);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to click on " + message + ". ERROR :\n\n " + e.getMessage());
		}
	}
	
	
	public static void verifyIfDataPresent(WebDriver driver, String element, String by, String data, String message)
    {
          /*
          * Created By : M Likitha Krishna
          * Created On : 10 Oct 2019
          * Functionality : Verification on data 
           * Parameters : element - element to be verified, data - the data to be verified, message - brief description
          */
          
		try {
			waitForElement(driver, element, globalVariables.elementWaitTime, by);
            if(isElementPresent(driver, element, by))
            {
            	Log.message("", driver, true);
            	Log.pass("Verified " + data + " in " + message);
            }
            else
            {
            	Log.fail(data + " not present in " + message , driver, true);
            }
                     
		}catch (Exception e) 
		{
			Log.fail("Unable to verify " + data + " in " + message + ". ERROR :\n\n " + e.getMessage(), driver, true);
        }
    }
	
	
	public static void verifyIfDataNotPresent(WebDriver driver, String element, WebElement waitElement, String by, String data, String message)
    {
         /*
          * Created By : M Likitha Krishna
          * Created On : 11 Oct 2019
          * Functionality : Verification on data 
          * Parameters : element - element to be verified, data - the data to be verified, message - brief description
          */
          
		try {
			
			Utils.waitForElement(driver, waitElement, globalVariables.elementWaitTime);
            if(!isElementPresent(driver, element, by))
            {
            	Log.message("", driver, true);
            	Log.pass("Verified " + data + " not present in " + message);
            }
            else
            {
            	Log.fail(data + " present in " + message , driver, true);
            }
                     
		}catch (Exception e) 
		{
			Log.fail("Unable to verify " + data + " in " + message + ". ERROR :\n\n " + e.getMessage(), driver, true);
        }
    }
	
	
	public static void saveDataToExcel(String dataWrite, String sheetName, String[] dataToWrite, String[] columnInExcel) throws Exception
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 31 Oct 2019
         * Functionality : Writing data in Excel sheet
         * Parameters : dataWrite - file in which data has to be written, data - the data to be written, sheetName - sheet in excel where data has to be written, columnInExcel - the key in excel sheet w.r.t. which data has to be stored
         */
		
		String directory = System.getProperty("user.dir");
		ReadWriteExcel writeExcel = new ReadWriteExcel(directory + dataWrite);
		
		for(int i = 0; i < dataToWrite.length; i++)
			writeExcel.setCellData(columnInExcel[i], sheetName, "Value", dataToWrite[i]);
	}
	
	
	public static File[] getAllDownloadedFiles()
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 11/11/2019
         * 
         */
		
		String home = System.getProperty("user.home");

		File folder = new File(home + File.separatorChar + "Downloads" + File.separatorChar);
		Log.message("Downloading Directory :" + home + File.separatorChar + "Downloads" + File.separatorChar);
		File[] listOfFiles = folder.listFiles();
		
		return listOfFiles;

	}
	
	
	public static void verifyIfStaticElementDisplayed(WebDriver driver, WebElement element,String data, String locationOfWebElement)
    {
          /*
          * Created By : M Likitha Krishna
          * Created On : 11 Nov 2019
          * Functionality : Verification of element 
           * Parameters : element - element to be verified, data - the data to be verified, locationOfWebElement
          */
          
		try {
			if(waitForElement(driver, element))
			{
				Log.message("", driver, true);
            	Log.pass("Verified " + data + "displayed in " + locationOfWebElement);
            }
            else
            {
            	Log.fail(data + " not displayed in " + locationOfWebElement , driver, true);
            }
                     
		}catch (Exception e) 
		{
			Log.fail("Unable to verify " + data + "is displayed in " + locationOfWebElement + ". ERROR :\n\n " + e.getMessage(), driver, true);
        }
    }
	
	
	public static void softVerifyIfStaticElementDisplayed(WebDriver driver, WebElement element,String data, String locationOfWebElement)
    {
          /*
          * Created By : Saurav Purohit
          * Created On : 21 Jan 2020
          * Functionality : Soft Verification of element 
           * Parameters : element - element to be verified, data - the data to be verified, locationOfWebElement
          */
          
		try {
			if(waitForElement(driver, element))
			{
            	Log.pass("Verified " + data + "displayed in " + locationOfWebElement , driver, true);
            }
            else
            {
            	Log.failsoft(data + " not displayed in " + locationOfWebElement , driver);
            }
                     
		}catch (Exception e) 
		{
			Log.failsoft("Unable to verify " + data + "is displayed in " + locationOfWebElement + ". ERROR :\n\n " + e.getMessage(), driver);
        }
    }


	public static void softVerifyPageTitle(WebDriver driver,String expactedTitle)
	    {
	          /*
	          * Created By : Saurav Purohit
	          * Created On : 21 Jan 2020
	          * Functionality : Soft Verification of element 
	           * Parameters : element - element to be verified, data - the data to be verified, locationOfWebElement
	          */
	          
			try {
				
				WebDriverWait wait = new WebDriverWait(driver, globalVariables.pageLoadTime);
			       wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
				
				if(driver.getTitle().contains(expactedTitle))
				{
	            	Log.pass("Verified that Page Title is " + expactedTitle ,driver, true);
	            }
	            else
	            {
	            	Log.failsoft("Actual Page Title "+driver.getTitle()+"is is different than Expected Title "+ expactedTitle , driver);
	            }
	                     
			}catch (Exception e) 
			{
				Log.failsoft("Unable to verify Page Title " + ". ERROR :\n\n " + e.getMessage(), driver);
	        }
	    } 
		
	
	public static void waitForFrametoBeAvaialable(WebDriver driver,String frameName){
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		}catch(Exception e){
			Log.fail("Unable to find frame" + frameName + ". ERROR :\n\n " + e.getMessage(), driver, true);
		}
	}

	
	public static void verifyIfDownloaded(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 19 Aug 2020
         * 
         */
		
		Thread.sleep(5000);
		Utils.switchWindows(remoteDriver, "Downloads", "title", "false");
		ArrayList filesFound = (ArrayList) remoteDriver.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList').items.filter(e => e.state === 'COMPLETE').map(e => e.filePath || e.file_path || e.fileUrl || e.file_url);");
		
		Thread.sleep(10000);
		
		if(filesFound.size() == 1)
            Log.pass("Downloaded successfully ", remoteDriver, true);
        else
            Log.fail("Unable to Download", remoteDriver, true);
		
		remoteDriver.close();
		
	}
	
	
	public static void openDownloadsWindow(RemoteWebDriver remoteDriver) throws Exception
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 19 Aug 2020
         * 
         */
		
		((JavascriptExecutor)remoteDriver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(remoteDriver.getWindowHandles());
		remoteDriver.switchTo().window(tabs.get(tabs.size() - 1));
		remoteDriver.get("chrome://downloads/");
		Utils.switchWindows(remoteDriver, "Downloads", "title", "false");
		
		remoteDriver.switchTo().window(tabs.get(tabs.size() - 2));
	}
	
	
	public static void scrollToDynamicElement(WebDriver driver, String element, String by, String message)
    {
         /*
          * Created By : Nitisha Sinha
          * Created On : 5 June 2020
          * Functionality : Sroll to Dynamic Element
          * Parameters : Element to be scrolled to if present, Message - The element being scrolled to
          */
		try {
			waitForElement(driver, element, globalVariables.elementWaitTime, by);
			WebElement scrollElement = null;
			if(by.equalsIgnoreCase("id"))
				scrollElement = driver.findElement(By.id(element));
			
			if(by.equalsIgnoreCase("css"))
				scrollElement = driver.findElement(By.cssSelector(element));
			
			if(by.equalsIgnoreCase("xpath"))
				scrollElement = driver.findElement(By.xpath(element));
			
			if(by.equalsIgnoreCase("name"))
				scrollElement = driver.findElement(By.name(element));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", scrollElement);
	
			

		} catch (Exception e) {
			Log.fail("Unable to scroll to  " +  message + " ERROR :\n\n " + e.getMessage(), driver, true);
		}
		
    }
		
		public static void waitForLoadingCompleted(WebDriver driver)
	    {
	         /*
	          * Created By : Saurav Purohit
	          * Created On : 17th June 2021
	          * Functionality : wait for Pace activity to start and wait for it to complete
	          * Parameters : Element to be scrolled to if present, Message - The element being scrolled to
	          */
			try {
				WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class,'pace-running')]")));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[contains(@class,'pace-done')]")));
				
			} catch (Exception e) {
				Log.message("Unable to find any Pace loader  ERROR :\n\n " + e.getMessage(), driver, true);
			}
		
    }
		
		
		public static void waitForLoadingMaskToDisappearInQuestionnaireSearchWindow(WebDriver driver)
        {
             /*
              * Created By : Saurav Purohit
              * Created On : 3rd Aug 2021
              * Functionality : wait for Loading Mask to Disappear in Questionnaire Search window After add/Remove questionnaire
              * 
              */
            try {
                WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
               
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='k-loading-mask']")));
                
                wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='k-loading-mask']"))));

            } catch (Exception e) {
                Log.message("Issue Loading for Loading Mask in Questionnaire Search Window  ERROR :\n\n " + e.getMessage(), driver, true);
            }    
        
    }
		
		public static void switchToWindowById(WebDriver driver) 
		{
			try
			{
				  /*
		          * Created By : Souvik Ganguly
		          * Created On : 25 June 2021
		          * Functionality : Iterate through the next child window based on id and switch
		          * To be used when child window has the same title as that of parent window.
		          */
				String mwh=driver.getWindowHandle();
				Set<String> s=driver.getWindowHandles();
				Iterator<String> ite=s.iterator();
				while(ite.hasNext())
				{
				    String popupHandle=ite.next().toString();
				    if(!popupHandle.contains(mwh))
				    {
				        driver.switchTo().window(popupHandle);
				        
				    }
				}
				
			 
			}
			catch (Exception e)
			{
				
				Log.fail("Unable to switch to window  "  + " ERROR :\n\n " + e.getMessage(), driver, true);
				
			}
			   
			}
		
		
		public static void waitForKendoImageCompleted(WebDriver driver)
        {
             /*
              * Created By : Saurav Purohit
              * Created On : 17th June 2021
              * Functionality : This function waits for Kendo image loader to be avaiable and disappear
              */
            try {
                WebDriverWait wait = new WebDriverWait(driver, 180);
                
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='k-loading-image']")));           
                
                wait.until(ExpectedConditions.invisibilityOf(driver.findElement((By.xpath("//div[@class='k-loading-image']")))));
          

            } catch (Exception e) {
                Log.message("Problem Loading in Kendo image ERROR :\n\n " + e.getMessage(), driver, true);
            }
        
        
        
        
        
    }
		
		public static void waitForLoadingMaskToDisappearInPortalSetup(WebDriver driver)
        {
             /*
              * Created By : Souvik Ganguly
              * Created On : 6th Sept 2021
              * Functionality : wait for Loading Mask to Disappear in Portal Setup window After Add User
              * 
              */
            try {
                WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
               
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='row iconLoadingForModal maskOverlay']")));
                
                wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='row iconLoadingForModal maskOverlay']"))));

            } catch (Exception e) {
                Log.message("Issue Loading for Loading Mask in Portal Setup Window  ERROR :\n\n " + e.getMessage(), driver, true);
            }    
        
    }
		
		public static void enterTextInCombo(WebDriver driver, WebElement element, String text, String message)
		{
			/*
			 * Created By : Souvik Ganguly
              * Created On : 6th Sept 2021
              * Functionality : Entering text in combo box and pressing enter
			 */
			
			try {

				Utils.enterText(driver, element, text, message);
				Thread.sleep(1000);
				element.sendKeys(Keys.ENTER);

			} catch (Exception e) {
				Log.fail("Unable to type " + text + " in " + message + " textbox. ERROR :\n\n " + e.getMessage(), driver, true);
			}
		}
		
		
   
}
