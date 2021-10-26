package FQK;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.ReadWriteExcel;
import com.support.files.Utils;

public class QuestionnairePage extends LoadableComponent<QuestionnairePage> {
      private final WebDriver driver;
      private String appUrl;
      private boolean isPageLoaded;
      
      public QuestionnairePage(WebDriver driver, String url) {
            this.driver = driver;
            appUrl = url;
            driver.get(appUrl);
            PageFactory.initElements(driver, this);
      }

      public QuestionnairePage(WebDriver driver) {
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
      
      @FindBy(id = "txt46164")
      WebElement textBox_receiptNumber;
      
      @FindBy(id = "txt22689")
      WebElement textBox_CBP;
      
      @FindBy(id = "txt22688")
      WebElement textBox_ICE;
      
      @FindBy(id = "txt22687")
      WebElement textBox_USCIS;
      
      @FindBy(id = "cbo22618")
      WebElement dropdown_immigrationType;
      
      @FindBy(id = "_ctl0_txt6761")
      WebElement textBox_StreetNumber;
      
      @FindBy(xpath = "//a[contains(text(),'(From Employer/Petitioner)')]")
      WebElement lnk_quest2Name;
      
      @FindBy(id = "btn_Quit")
      WebElement btn_Quit;
      
      @FindBy(id = "btn_Save")
      WebElement btn_Save;
      
      @FindBy(id = "cbo46165")
      WebElement dropDown_I94;
      
      @FindBy(id = "cbo24715")
      WebElement dropDown_USCIS_PRC;
      
      @FindBy(id = "cbo24714")
      WebElement dropDown_USCIS;
      
      @FindBy(id = "_ctl0_txtCPhone")
      WebElement textBox_mobileNumber;
      
      @FindBy(id = "_ctl0_txtPhone1")
      WebElement textBox_dayTimeTelephoneNumber;
      
      @FindBy(id = "_ctl0_txtZip")
      WebElement textBox_zip;
      
      @FindBy(id = "_ctl0_txtState")
      WebElement textBox_state;
      
      @FindBy(id = "_ctl0_txtCity")
      WebElement textBox_city;
      
      @FindBy(id = "_ctl0_txtSuite")
      WebElement textBox_aptNumber;
      
      @FindBy(id = "_ctl0_txtAddr")
      WebElement textBox_streetName;
      
      @FindBy(id = "_ctl0_txt6757")
      WebElement textBox_streetNumber;
      
      @FindBy(id = "_ctl0_cboCountry")
      WebElement dropDown_country ;
      
      @FindBy(id = "_ctl0_txtAlienNbr")
      WebElement textBox_alienNumber ;
      
      @FindBy(xpath = "//a[contains(text(),'(From Employee/Beneficiary)')]")
      WebElement lnk_quest1Name;
      
      @FindBy(id = "_ctl0_txtELIS")
      WebElement USCIS_ELIS_AccountNumber ;
      
      @FindBy(id = "btn_Saveandcontinuetonextsection")
      WebElement btn_Saveandcontinuetonextsection ;
      
      public void fillQuest1() throws Exception
      {
            Utils.clickElement(driver, lnk_quest1Name, "first questionnaire name");
            
            Utils.waitForAllWindowsToLoad(2, driver);
            Utils.switchWindows(driver, "INSZoom.com - Client Questionnaire Update", "title", "false");
            
            Utils.waitForElement(driver, "sectionFrame", 40, "id");
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.enterText(driver, USCIS_ELIS_AccountNumber, FQK.globalVariables.elisAccountNumber, "USCIS ELIS Account Number");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "Save and continue button");
            
            /*//driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            //Utils.waitForElementNotVisible(driver, "sectionFrame", "id");
            //Utils.waitForElement(driver, "sectionFrame", globalVariables.elementWaitTime, "id");
            WebDriverWait wait = new WebDriverWait(driver, globalVariables.elementWaitTime);
          wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("sectionFrame"));
            //driver.switchTo().frame("sectionFrame"); 
*/          
            driver.switchTo().defaultContent();
            //Utils.waitForElement(driver, "//span[contains(text(), 'Personal Information')]/../..//img[contains(@src, 'success')]", globalVariables.elementWaitTime, "xpath");
            
            Utils.waitForPageLoad(driver);
            
            
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame");
            
            Utils.enterText(driver, textBox_alienNumber, FQK.globalVariables.alienNumber , "alien number");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "Save and continue button");
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            
            
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.selectOptionFromDropDown(driver, FQK.globalVariables.country , dropDown_country);
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            
            
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame");
            
            Utils.enterText(driver, textBox_streetNumber, FQK.globalVariables.EBstreetNumber, "street number");
            
            Utils.enterText(driver, textBox_streetName, FQK.globalVariables.EBstreetName, "street name");
            
            Utils.enterText(driver, textBox_aptNumber,FQK.globalVariables.EBaptNumber , "apt number");
            
            Utils.enterText(driver, textBox_city,FQK.globalVariables.EBcityName , "city name");
            
            //Utils.scrollIntoView(driver, textBox_state);
            
            Utils.enterText(driver, textBox_state,FQK.globalVariables.EBstate , "state");
            
            Utils.enterText(driver, textBox_zip,FQK.globalVariables.EBzip , "zip");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "save");
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.enterText(driver, textBox_dayTimeTelephoneNumber,FQK.globalVariables.EBdaytimeTelephoneNumber , "day time telephone number");
            
            Utils.enterText(driver, textBox_mobileNumber,FQK.globalVariables.EBmobileNumber , "mobile number");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "save");
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.selectOptionFromDropDown(driver, "Yes", dropDown_USCIS);
            
            Utils.selectOptionFromDropDown(driver, "Yes", dropDown_USCIS_PRC);
            
            Utils.selectOptionFromDropDown(driver, "Yes", dropDown_I94);
            
            Utils.clickElement(driver, btn_Save, "Final save button");
            
            driver.switchTo().defaultContent();
            
            Utils.clickElement(driver, btn_Quit, "quit button");
            
            Utils.waitForAllWindowsToLoad(1, driver);
            Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");
      }
      
      
      public void fillQuest2() throws Exception
      {
            Utils.clickElement(driver, lnk_quest2Name, "questionnaire 2");
            
            Utils.waitForAllWindowsToLoad(2, driver);
            Utils.switchWindows(driver, "INSZoom.com - Client Questionnaire Update", "title", "false");
            
            Utils.waitForPageLoad(driver);
            driver.switchTo().frame("sectionFrame");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "Save and continue button");
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.selectOptionFromDropDown(driver, FQK.globalVariables.country , dropDown_country);

            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame");
            
            Utils.enterText(driver, textBox_StreetNumber, FQK.globalVariables.EPstreetNumber, "street number");
            
            Utils.enterText(driver, textBox_streetName, FQK.globalVariables.EPstreetName, "street name");
            
            Utils.enterText(driver, textBox_aptNumber,FQK.globalVariables.EPaptNumber , "apt number");
            
            Utils.enterText(driver, textBox_city,FQK.globalVariables.EPcityName , "city name");
            
            //Utils.scrollIntoView(driver, textBox_state);
            
            Utils.enterText(driver, textBox_state,FQK.globalVariables.EPstate , "state");
            
            Utils.enterText(driver, textBox_zip,FQK.globalVariables.EPzip , "zip");
            
            Utils.enterText(driver, textBox_dayTimeTelephoneNumber,FQK.globalVariables.EPdaytimeTelephoneNumber , "day time telephone number");
            
            Utils.enterText(driver, textBox_mobileNumber,FQK.globalVariables.EPmobileNumber , "mobile number");
            
            Utils.clickElement(driver, btn_Saveandcontinuetonextsection, "Save and continue button");
            
            driver.switchTo().defaultContent();
            Utils.waitForPageLoad(driver);
            Thread.sleep(5000);
            driver.switchTo().frame("sectionFrame"); 
            
            Utils.selectOptionFromDropDownByVal(driver, "1", dropdown_immigrationType);
            
            Utils.enterText(driver, textBox_USCIS, FQK.globalVariables.EP_USCIS, "USCIS data");
            
            Utils.enterText(driver, textBox_ICE, FQK.globalVariables.EP_ICE, "ICE data");
            
            Utils.enterText(driver, textBox_CBP, FQK.globalVariables.EP_CBP, "CBP data");
            
            Utils.enterText(driver, textBox_receiptNumber, FQK.globalVariables.EP_receiptNumber, "receipt number");
            
            Utils.clickElement(driver, btn_Save, "Save");
            

            driver.switchTo().defaultContent();
            
            Utils.clickElement(driver, btn_Quit, "quit button");
            
            Utils.waitForAllWindowsToLoad(1, driver);
            Utils.switchWindows(driver, "INSZoom.com - Case Questionnaires List", "title", "false");
      }
      

      
}
