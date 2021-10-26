package com.inszoomapp.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class LoginPageTest extends LoadableComponent<LoginPageTest>
{
	private final WebDriver driver;
	private String appUrl;
	private boolean isPageLoaded;
	
	
	
	@FindBy(xpath="//body[contains(@class, 'pace-done')]")
    WebElement body_paceDone;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
    WebElement btn_ok;
	
	@FindBy(css = "span[id='post']>button[class='btn btn-primary pull-right']")
	WebElement acceptBtn;
	
	@FindBy(css = "input[value='Save'][class='zoomGridAddBtn']")
	WebElement btn_SaveFNPPassword;

	@FindBy(css = "div[id='pnlRedirect']>div>b+br+br+a")
	WebElement btn_ContinueFNP;
	
	@FindBy(css = "input[name='txtAnswer']")
	WebElement txtbox_FNPSecurityAnswer;

	@FindBy(css = "input[name='txtOldChPwd']")
	WebElement txtbox_OldFNPPassword;

	@FindBy(css = "input[name='txtNewChPwd1']")
	WebElement txtbox_NewFNPPassword;

	@FindBy(css = "input[name='txtNewChPwd2']")
	WebElement txtbox_FNPConfirmPassword;
	
	@FindBy(css = "select[name='ddlQuest']")
	WebElement dropdown_FNPSecurityQuestion;
	
	@FindBy(css = "table[summary='Change Password']")
	WebElement tbl_ChangePassword;
	
	@FindBy(xpath = "//font[contains(text(),'Please Enter Valid UserId & Password')]")
	WebElement txt_WrongCredentials;
	
	@FindBy(css = "select[name='ddlQuest']")
	WebElement dropdown_HRPQuestion;
	
	@FindBy(css = "a[class='btn']")
	WebElement btn_ContinueAfterHRPSecurityQuestion;
	
	@FindBy(css = "input[title='Save password']")
	WebElement btn_SaveHRPPassword;

	@FindBy(css = "input[id='txtAnswer']")
	WebElement txt_HRPSecurityAnswer;
	
	@FindBy(css = "label[id='ltlCaptcha']")
	WebElement txt_Captcha;

	@FindBy(css = "input[name='txtCaptchaAnswer']")
	WebElement txt_CaptchaAnswer;

	@FindBy(css = "img[id='Image1']")
	WebElement btn_OK;

	@FindBy(css = "a[class='btn']")
	WebElement btn_ContinueHRP;
	
	@FindBy(css = "input[name='txtPass']")
	WebElement txt_HRPPassword;

	@FindBy(css = "input[name='txtCPass']")
	WebElement txt_HRPConfirmPassword;
	
	@FindBy(css = "input[placeholder*='Login ID']") // Added By Saksham Kapoor on 08/10/2019
	WebElement txtbox_UserName;

	@FindBy(css = "input[placeholder='Password']") // Added By Saksham Kapoor on 08/10/2019
	WebElement txtbox_Password;

	@FindBy(id="login") // Added By Saksham Kapoor on 08/10/2019
	WebElement btn_LogIn;
	
	@FindBy(css = "button[id='btnagree']") // Added By Saksham Kapoor on 08/10/2019
	WebElement btn_Agree;

	@Override
	protected void load() 
	{
		isPageLoaded = true;
		driver.get(appUrl);
		Utils.waitForPageLoad(driver);
	}

	@Override
	protected void isLoaded() throws Error
	{
		if (!isPageLoaded) 
		{
			Assert.fail();
		} 
		else 
		{
			Log.fail("Login Page did not open up. Site might be down.", driver, true);
		}
	}
	
	
	public LoginPageTest(WebDriver driver, String url) {

		this.driver = driver;
		appUrl = url;
		driver.get(appUrl);
		Log.message("Successfully lauched the URL " + appUrl);
		PageFactory.initElements(driver, this);
	}
	
	
	public LoginPageTest(WebDriver driver) {

		this.driver = driver;
	}
	
	
	public void enterWrongCredentialsAndVerify(String CM_userNameAlt, int attempts)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 08/10/2020
		 * 
		 */
		
		for(int i = 0; i < attempts; i ++)
		{
			enterUserName(CM_userNameAlt);
			
			enterPassword(CM_userNameAlt);
			
			clickLoginButton();
		}
		
		Utils.verifyIfDataPresent(driver, "//span[contains(text(), 'For security purposes, you’ve been locked out of your account due to unsuccessful login attempts.')]", "xpath", "Locked User", "Login Page after unsuccessful " +attempts + " attempts");
	}
	
	
	public CM_DashboardPage caseManagerlogin(String username, String password, boolean screenShot) throws IllegalArgumentException, IllegalAccessException 
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 08/10/2019
		 * Functionality : Logging in to CM Login
		 */
		
		enterUserName(username);
		
		enterPassword(password);

		globalVariables.startTime = System.currentTimeMillis();
		
		clickLoginButton();
		
		if(driver.getTitle().equals("INSZoom::Login"))
		{
			globalVariables.SKIP_REMAINING_TESTS = true ;
			
			System.out.println("Value: "+globalVariables.SKIP_REMAINING_TESTS);
			Log.fail("Login failed ", driver, screenShot);
		}
		
		Log.message("Time Taken to Login TO application is " + Utils.getPageLoadTime(driver));
		return new CM_DashboardPage(driver);
	}
	
	
	public void loginToFNP(String username, String password, boolean screenShot) 
	{
		/*
		 * Created By: Saksham Kapoor
		 * Created On: 14/10/2019
		 * Edited by : Sauravp
		 * Edited Date: 12/07/2021 Added waitForLoadingCompleted and waitForAjax to ensure a wait till the 
		 * loader are successfully loaded .
		 */
		
		enterUserName(username);
		
		enterPassword(password);
		
		globalVariables.startTime = System.currentTimeMillis();
		
		clickLoginButton();
		
		Utils.waitForLoadingCompleted(driver);
		
		Utils.waitForAjax(driver);
		
	
		if(driver.getTitle().equals("INSZoom::Login") && txt_WrongCredentials.isDisplayed())
			Log.fail("Login Failed due to wrong credentials", driver, screenShot);
		
		else if(driver.getTitle().equals("INSZoom::Login"))
			Log.fail("Login Failed", driver, screenShot);
		
		Log.message("Time taken to Login to FN Portal is --> " + Utils.getPageLoadTime(driver)+ " Seconds ");   
	}
	
	
	public void verifyFNPLoginAfterIDChange(boolean sceenshot) {
		/*
		 * Modified By: Saksham Kapoor
		 * Modified On: 14/10/2019
		 */
		try {
			Utils.waitForElement(driver, tbl_ChangePassword);
			if (tbl_ChangePassword.isDisplayed()) {
				Log.pass("The Security question page is loaded for FNP user as expected.", driver, sceenshot);
			} else {
				Log.fail("The Security question page is not loaded for FNP user.", driver, sceenshot);
			}

		} catch (Exception e) {
			Log.fail("Error while interacting with security question. ERROR :\n\n " + e.getMessage());
		}
	}

	
	public void enterPassword(String password) 
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 08/10/2019
		 * Functionality : Enter Password
		 */
		
		Utils.enterText(driver, txtbox_Password, password, "Password");

	}

	   
	public void enterUserName(String username) 
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 08/10/2019
		 * Functionality : Enter Username
		 */
		
		Utils.enterText(driver, txtbox_UserName, username, "Username");
	
	}
	

	public void clickLoginButton() 
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 08/10/2019
		 * Functionality : Click Login Button
		 */
		
		Utils.clickElement(driver, btn_LogIn, "Login Button");
		
		try {
			if (Utils.isAlertPresent(driver)) 
			{
				driver.switchTo().alert().accept();
			} 
			
			else 
			{
				System.out.println("Alert was not present");
			}
		} catch (NoAlertPresentException e) {
			Log.fail("Error while interacting with alert. ERROR - " + e.getMessage());
		}
	}
	
	
	public void clickAgreeButton(boolean screenshot) {
		/*
		 * Created By: Saksham Kapoor
		 * Modified On: 08/10/2019
		 * Modifications: Click on Agree Button
		 * 
		 */
		try {
			//Utils.waitForElement(driver, "frmDisclaimer", globalVariables.elementWaitTime, "id");
			driver.switchTo().frame("frmDisclaimer");
			Utils.clickElement(driver, btn_Agree, "agree option in the disclaimer");
			
		} catch (Exception e) {
			Log.message("Agree option in the disclaimer is not present");
		}

		try {
			if (Utils.isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			} else {
				System.out.println("alert was not present");
			}
		} catch (NoAlertPresentException e) {
			System.out.println("Exception=====!");
			e.printStackTrace();
			System.out.println("End of exception======");
		}
	}
	
	
	public void resetHRPPassword(String hrpLoginID, String hrpPassword) throws Exception 
	{
		/* Created By : Saksham Kapoor
		 * Created On : 14/10/2019
		 */
		
		String captchaLabelTxthrp = "";
		int captchaValue = 0;
		
		Utils.enterText(driver, txt_HRPPassword, hrpPassword, "Password");
		
		Utils.enterText(driver, txt_HRPConfirmPassword, hrpPassword, "Confirm Password");

		try {
			
			Utils.waitForElement(driver, txt_Captcha);
			captchaLabelTxthrp = txt_Captcha.getText();
			Log.message("Fetched Captcha as " + captchaLabelTxthrp);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to fetch captcha. Error Message - " + e.getMessage());
		}


		String[] splitcaptchaLabelTxt = captchaLabelTxthrp.split(" ");
		System.out.println(splitcaptchaLabelTxt);

		int firstNumber = Integer.parseInt(splitcaptchaLabelTxt[0]);
		int secondNumber = Integer.parseInt(splitcaptchaLabelTxt[2]);

		if (splitcaptchaLabelTxt[1].equals("+")) {
			captchaValue = firstNumber + secondNumber;
		}
		if (splitcaptchaLabelTxt[1].equals("-")) {
			captchaValue = firstNumber - secondNumber;
		}
		if (splitcaptchaLabelTxt[1].equals("*")) {
			captchaValue = firstNumber * secondNumber;
		}
		if (splitcaptchaLabelTxt[1].equals("/")) {
			captchaValue = firstNumber / secondNumber;
		}

		Utils.enterText(driver, txt_CaptchaAnswer, String.valueOf(captchaValue), "Captcha Answer");

		Utils.clickElement(driver, btn_OK, "OK");

		Utils.clickElement(driver, btn_ContinueHRP, "Continue");

		Log.message("Successfully Reset the HRP password ");
		Log.message("Verifying the created HRP user credentials by logging in to HRP");
		
		Utils.enterText(driver, txtbox_UserName, hrpLoginID, "Username");
		
		Utils.enterText(driver, txtbox_Password, hrpPassword, "Password");
		
		Utils.clickElement(driver, btn_LogIn, "Login");

	}
	
	
	public void verifyHRPSecurityPage(boolean screenshot) throws Exception 
	{
		/* Created By : Saksham Kapoor
		 * Created On : 14/10/2019
		 */
		
		if(Utils.isElementPresent(driver, "select[name='ddlQuest']", "css"))
		{
			Utils.clickElement(driver, dropdown_HRPQuestion, "Security Question");
			Utils.selectOptionFromDropDownByVal(driver, "3", dropdown_HRPQuestion);
			
			Utils.enterText(driver, txt_HRPSecurityAnswer, "blue", "Security Question Answer");
			
			Utils.clickElement(driver, btn_SaveHRPPassword, "Save Button");
			
			Utils.clickElement(driver, btn_ContinueAfterHRPSecurityQuestion, "Continue");

			Log.pass("Successfully logged into the HRP page and submitted the values for security questions", driver, screenshot);
		}
		else
		{
			Log.message("Did not set the security question as has already been set.");
		}
	}
	
	
	public void changeFNPPassword(String fnpNewPassword, String fnpPassword, boolean screenshot) throws Exception 
	{
		/* Created By : Saksham Kapoor
		 * Created On : 14/10/2019
		 */
		
		Utils.clickElement(driver, dropdown_FNPSecurityQuestion, "Security Question");
		Utils.selectOptionFromDropDownByVal(driver, "3", dropdown_FNPSecurityQuestion);

		Utils.enterText(driver, txtbox_FNPSecurityAnswer, "blue", "Security Answer");

		Utils.enterText(driver, txtbox_OldFNPPassword, fnpPassword, "Old Password");
		
		Utils.enterText(driver, txtbox_NewFNPPassword, fnpNewPassword, "New Password");
		
		Utils.enterText(driver, txtbox_FNPConfirmPassword, fnpNewPassword, "Confirm Password");
		
		Utils.clickElement(driver, btn_SaveFNPPassword, "Save Button");

		Utils.clickElement(driver, btn_ContinueFNP, "Continue");

	}
	
	public void fnplogin(String username, String password, boolean screenShot) {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */ 
		enterUserName(username);
		enterPassword(password);
		clickLoginButton();
		
		if(driver.getTitle().equals("INSZoom::Login"))
		{
			Log.fail("Login failed ", driver, screenShot);
		}
		
		Log.message("Time Taken to Login TO application is " + Utils.getPageLoadTime(driver));
		clickAgreeButton(false);
//		clickAcceptAndContinueBtn(true);
		//FnpHomePage fnpHomePage = new FnpHomePage(driver);
		//fnpHomePage.acceptUserInstruction();
	}
	
	public void clickAcceptAndContinueBtn(boolean screenshot) 
	{
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 21 Oct 2019
		  */ 
		
		try {
			acceptBtn.click();
		} catch (Exception e) {
			Log.message("Accept and continue button not present", driver, screenshot);
		}
	}
	
	
	public HrpHomePage hrpLogin(String username, String password, boolean screenShot)
	{
		/*
		  * Created By : M Likitha Krishna
		  * Created On : 08 Nov 2019
		  */ 
		enterUserName(username);
		enterPassword(password);
		Log.message("Entered User Name & Password (" + username + "/" + password + ")", driver, screenShot);
		clickLoginButton();
		
		if(driver.getTitle().equals("INSZoom::Login"))
		{
			Log.fail("Login failed ", driver, screenShot);
		}
		
		clickAgreeButton(false);
//		clickAcceptAndContinueBtn(true);
		return new HrpHomePage(driver);
	} 
	
	
	public HrpHomePage hrpLoginWithoutAcceptingConsents(String username, String password, boolean screenShot)
	{
		/*
		  * Created By : Saksham Kapoor
		  * Created On : 03 Feb 2020
		  */ 
		enterUserName(username);
		enterPassword(password);
		Log.message("Entered User Name & Password (" + username + "/" + password + ")", driver, screenShot);
		clickLoginButton();
		
		if(driver.getTitle().equals("INSZoom::Login"))
		{
			Log.fail("Login failed ", driver, screenShot);
		}
		
		return new HrpHomePage(driver);
	} 
	
 
   public void handleUserInstructions()
   {
	     /*
		  * Created By : M Likitha Krishna
		  * Created On : 20 Jan 2020
		  */ 
	   if(Utils.isElementPresent(driver, "//button[contains(text(),'OK')]", "xpath"))
		{Utils.clickElement(driver, btn_ok, "ok button");}
   }
   
   
   public void fnpLoginForEconsent(String username, String password, boolean screenShot) {
		 /*
		  * Created By : M Likitha Krishna
		  * Created On : 22 Dec 2020
		  */ 
		enterUserName(username);
		enterPassword(password);
		clickLoginButton();
		
		if(driver.getTitle().equals("INSZoom::Login"))
		{
			Log.fail("Login failed ", driver, screenShot);
		}
		
		Log.message("Time Taken to Login TO application is " + Utils.getPageLoadTime(driver));
	}
	

}



	

	