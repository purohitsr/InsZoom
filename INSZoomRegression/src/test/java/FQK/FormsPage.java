package FQK;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.support.files.Log;

public class FormsPage extends LoadableComponent<FormsPage>
{
	private final WebDriver driver;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public FormsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyIfDataPopulated(String id, String data)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 30 Jan 2020
		 */
		
		String text = driver.findElement(By.id(id)).getAttribute("value");
		
		if(text.equals(data))
		{
			Log.pass("Verified " + data);
		}
		
		else
		{
			Log.failsoft("Not able to verify " + data);
		}
	}

}
