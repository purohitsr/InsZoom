package securityXSS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.support.files.Log;
import com.support.files.Utils;

public class CaseEmail extends LoadableComponent<CaseEmail>
{
	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(id="btn_EmailCaseAdvancedKit")
	WebElement btn_SendEmail;
	
	@FindBy(css="head+body")
	WebElement txtbox_Message;
	
	@FindBy(id="btn_ComposeCaseemail")
	WebElement btn_ComposeEmail;
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail("Case phone log page didnt loaded");
		}

	}

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}
	
	
	public CaseEmail(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickComposeEmailButton() throws Exception
	{
		
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 11/11/2019
		 */

		Utils.clickElement(driver, btn_ComposeEmail, "'Compose Email' Button");
	}
	
	
	public void enterMessageAndSendEmail(String emailMessage, String count)
	{
		/* 
		 * Modified By: Saksham Kapoor
		 * Modified On: 20/11/2019
		 * 
		 */
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,-1000)");
		
		driver.switchTo().frame("htmMsg__ctl0_Editor_contentIframe");
		
		Utils.enterText(driver, txtbox_Message, emailMessage, "Message Box");
		
		Log.message(count);
		
		driver.switchTo().defaultContent();
		
		Utils.scrollIntoView(driver, btn_SendEmail);
		Utils.clickElement(driver, btn_SendEmail, "Send Email button");
	}
}
