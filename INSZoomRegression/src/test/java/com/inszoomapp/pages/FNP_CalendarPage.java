package com.inszoomapp.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.support.files.Log;
import com.support.files.Utils;

public class FNP_CalendarPage extends LoadableComponent<FNP_CalendarPage> 
{

	@FindBy(xpath = "//div[contains(@style,'display: block;')]//button[contains(text(), 'Cancel')]")
	WebElement btn_Cancel;
	
	private WebDriver driver;
	public FNP_CalendarPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	public void verifyAppoimtmentActivity(String subject,String when,String where,String time,String duration)
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 06/01/2020
		 */
		
		Utils.verifyIfDataPresent(driver, "//td/a[contains(text(),'"+subject+"')]/../following-sibling::td[contains(text(),'"+when+"')]/../td[contains(text(),'"+where+"')]/../td[contains(text(),'"+time+"')]/../td/span[contains(text(),'"+duration+"')]", "xpath", "added appointments", "appointment page");
		Log.message("The date is " + when, driver, true);
	}
	
	public void clickAppointmentActivity(String subject, String when)
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 07/01/2020
		 */
		Utils.clickDynamicElement(driver, "//td[contains(text(),'"+when+"')]/preceding-sibling::td/a[contains(text(),'"+subject+"')]", "xpath", "appointment/activity");
	}
	
	public void verifyAppoimtmentActivityDetails(String subject,String description,String where,String time,String duration,String comments)
	{
		/*
		 * Created By: Likitha Krishna
		 * Created On: 07/01/2020
		 */
		Utils.verifyIfDataPresent(driver, "//span[@id='Subject'][contains(text(),'"+subject+"')]", "xpath", subject, "subject");
		Utils.verifyIfDataPresent(driver, "//span[@id='AppointmentDescription'][contains(text(),'"+description+"')]", "xpath", description, "description");
		Utils.verifyIfDataPresent(driver, "//span[@id='Location'][contains(text(),'"+where+"')]", "xpath", where, "where");
		Utils.verifyIfDataPresent(driver, "//span[@id='Time'][contains(text(),'"+time+"')]", "xpath", time, "time");
		Utils.verifyIfDataPresent(driver, "//span[@id='Duration'][contains(text(),'"+duration+"')]", "xpath", duration, "duration");
		Utils.verifyIfDataPresent(driver, "//span[@id='Comments'][contains(text(),'"+comments+"')]", "xpath", comments, "comments");
		Utils.clickElement(driver, btn_Cancel, "cancel button");
	}
	
	public String retrieveDate()
	{
		/*
		 * Created By: Souvik Ganguly
		 * Created On: 08/07/2021
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        //c.setTime(new Date());
        c.add(Calendar.DATE, 1); //Adding 1 day to the current date so that it can match the logic applied in Dependency tc:
          
        if(c.get(Calendar.DAY_OF_MONTH) > 9)
        	  sdf = new SimpleDateFormat("MMM dd yyyy");
        else
        	  sdf = new SimpleDateFormat("MMM d yyyy");
          
        String when = sdf.format(c.getTime());
        return when;
	}
	
	public String formatDate(String fromDate) throws ParseException
	{
		/*
		 * Created By: Souvik Ganguly
		 * Created On: 09/08/2021
		 * This will change the format of the date
		 */
		
		SimpleDateFormat input = new SimpleDateFormat("MM/dd/yyyy");
		Date dateValue = input.parse(fromDate);
       
          
        if(dateValue.getDate()>9)
        	  input = new SimpleDateFormat("MMM dd yyyy");
        else
        	  input = new SimpleDateFormat("MMM d yyyy");
          
        String when = input.format(dateValue.getTime());
        return when;
		
	
	}
	
}