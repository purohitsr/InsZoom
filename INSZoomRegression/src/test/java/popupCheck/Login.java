package popupCheck;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.support.files.Log;
import com.support.files.Utils;

public class Login 
{
	 private static WebDriver driver;
	 
	 @FindBy(css = "input[type='text']")
	 WebElement txt_username;
	 
	 @FindBy(css = "input[type='password']")
	 WebElement txt_password;
	 
	 @FindBy(id="login")
	 WebElement btn_login;
	 
//	 @FindBy(css="div[class='intercom-chat-snippet-card']")
//	 static WebElement popup;
	 
	 @FindBy(css="iframe[title='Intercom live chat message']")
	 static WebElement chatFrame;
	 
	 
	 public Login(WebDriver driver) throws IllegalArgumentException, IllegalAccessException
	 {
		 this.driver = driver;
		 MyPageFactory.initElements(driver, this);
	 }

	 
	 public void login(String userName, String password)
	 {
		 try {
			Utils.waitForElement(driver, txt_username);
			txt_username.sendKeys(userName);
			Log.message("Entered Username as " + userName);
			
			txt_password.sendKeys(password);
			Log.message("Entered Password as " + password);
			
			btn_login.click();
			Log.message("Clicked on Log In Button");
			
			try {
				driver.switchTo().alert().accept();
				Log.message("Accepted Alert");
				
			} catch (NoAlertPresentException e) {
				Log.message("No alert present");
			}
			
			Utils.waitForElement(driver, "a[id='MnCorporation']", 90, "css");
			driver.findElement(By.id("MnCorporation")).click();
			Log.message("Clicked on Corporation Tab");
			
			//Login loginPage = new Login(driver);
		


		} catch (Exception e) {
			e.printStackTrace();
			Log.fail("Unable to. ERROR :" + e.getMessage());
		}
	 }
	 
	 public static void checkForPopupAndKill() throws InterruptedException 
	    {   
		 Log.message("Inside Pop Up Function");
	        if (driver.findElements(By.cssSelector("iframe[title='Intercom live chat message']")).size()>0) 
	        {
	            System.out.println("You damn popup, you appearded again!!?? I am gonna kill you now!!");
	            List<WebElement> frames = driver.findElements(By.cssSelector("iframe[title='Intercom live chat message']"));
	            driver.switchTo().frame(frames.get(0));
	            Actions action = new Actions(driver);
	            
	            List<WebElement> popup = driver.findElements(By.xpath("//div[@class='intercom-chat-snippet-card' or @class='intercom-snippet-body']"));
	            action.moveToElement(popup.get(0)).build().perform();
	            Thread.sleep(10000);
	            //Utils.waitForElement(driver, "span[class='intercom-1acjxur e1hvvs533']", 90, "css");
	            List<WebElement> close = driver.findElements(By.xpath("//button[contains(text(), 'Clear')]"));
	            if(close.size()>0)
	            	action.moveToElement(close.get(0)).click().build().perform();
	           
	            driver.switchTo().defaultContent();
	            //Thread.sleep(10000);
	        }
	        else
	        {
	        	Log.message("Pop up didn't appear.");
	        	return;
	        }
	    }

}