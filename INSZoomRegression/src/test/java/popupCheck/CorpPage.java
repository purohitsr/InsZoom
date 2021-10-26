package popupCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.support.files.Log;

public class CorpPage 
{
	
	@FindBy(css="td[class='rgPagerCell NextPrevAndNumeric']>div:nth-child(5)")
	WebElement corp;
	
	private final WebDriver driver;
	 public CorpPage(WebDriver driver) throws IllegalArgumentException, IllegalAccessException
	 {
		 this.driver = driver;
		 MyPageFactory.initElements(driver, this);
	 }
	 
	 public void corpo() throws InterruptedException
	 {
		 corp.getText();
		 Log.message("Fetched Text: " + corp.getText());
	//	 Thread.sleep(10000);
		 corp.click();
		 Log.pass("Passed");
	 }
}
