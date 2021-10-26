package com.inszoomapp.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.WebElement;

import com.support.files.Log;
import com.support.files.Utils;

public class LoginPage extends LoadableComponent<LoginPage>
{

	WebDriver driver = null;
	
	@FindBy(css = "input[type='text']")
	WebElement txtbox_username;
	
	@FindBy(css = "input[type='password']")
	WebElement txtbox_password;
	
	@FindBy(id="login")
	WebElement btn_login;
	
	public LoginPage(WebDriver driver)
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
	
	
	public void login(String webSite, String userName, String password)
	{
		driver.get(webSite);
		
		try {
			Utils.waitForElement(driver, txtbox_username);
			txtbox_username.sendKeys(userName);
			Log.message("Entered username as " + userName);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to enter username. ERROR :\n\n " + e.getMessage());
		}


		try {
			Utils.waitForElement(driver, txtbox_password);
			txtbox_password.sendKeys(password);
			Log.message("Entered password as " + password);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to enter password. ERROR :\n\n " + e.getMessage());
		}


		try {
			Utils.waitForElement(driver, btn_login);
			btn_login.click();
			Log.message("Clicked on Login");

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to click on Login. ERROR :\n\n " + e.getMessage());
		}


	}

}
