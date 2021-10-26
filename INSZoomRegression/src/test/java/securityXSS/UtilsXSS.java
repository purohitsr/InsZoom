package securityXSS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UtilsXSS 
{
	public static boolean checkIfRedirectedToErrorPage(WebDriver driver)
	{
		if(driver.getTitle().equals("Invalid Character Error - INSZoom.com"))
		{
			driver.findElement(By.xpath("//a[contains(text(), 'go back')]")).click();
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
