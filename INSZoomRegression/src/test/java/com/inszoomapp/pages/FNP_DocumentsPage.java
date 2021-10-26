package com.inszoomapp.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.Log;
import com.support.files.Utils;

public class FNP_DocumentsPage extends LoadableComponent<FNP_DocumentsPage> {

	private final WebDriver driver;
	
	@FindBy(xpath = "//h2[contains(text(),'DOCUMENTS')]")	      //Created by Nitisha Sinha on 13/02/2020
	WebElement waitElement_documentHeader; 
	
	@FindBy(css="button[onclick='UploadDoc();']")
	WebElement btn_Upload;
	
	@FindBy(id="uploadFile")
	WebElement btn_UploadFile;

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	public FNP_DocumentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyIfDocumentPresent(String fileName)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 13 Nov 2019
		 * 
		 */
		
		Utils.verifyIfDataPresent(driver, "//td[contains(text(), '" + fileName + "')]", "xpath", fileName, "Documents");
	}
	
	
	public void uploadDocument(String filePath) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Nov 2019
		 * 
		 */
		
		Utils.clickElement(driver, btn_Upload, "Upload");
		
		Utils.waitForAllWindowsToLoad(2, driver);
		Utils.switchWindows(driver, "Upload file", "title", "false");
		
		filePath = System.getProperty("user.dir")+filePath;
		String os = System.getProperty("os.name");
		if(os.contains("Linux"))
		{
			filePath = filePath.replace("\\", "/");
			Log.message("Final File Path "+filePath);
		}
		
		File filepath = new File(filePath);
		
		LocalFileDetector detector = new LocalFileDetector();
		File file = detector.getLocalFile(filePath);

		if (!file.exists())
			Log.fail("File not found in the path specified: " + file.toString());

		WebElement droparea = null;
		try {

			droparea = driver.findElement(By.cssSelector("div[class='dropzone dz-clickable']"));
		} catch (Exception e) {
			Log.fail("Not able to fetch the drop area for file");
		}
		
		WebDriver driver = ((RemoteWebElement) droparea).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;


		String JS_DROP_FILE = "var target = arguments[0]," + "    offsetX = arguments[1],"
				+ "    offsetY = arguments[2]," + "    document = target.ownerDocument || document,"
				+ "    window = document.defaultView || window;" + "" + "var input = document.createElement('INPUT');"
				+ "input.type = 'file';" + "input.style.display = 'none';" + "input.onchange = function () {"
				+ "  var rect = target.getBoundingClientRect(),"
				+ "      x = rect.left + (offsetX || (rect.width >> 1)),"
				+ "      y = rect.top + (offsetY || (rect.height >> 1)),"
				+ "      dataTransfer = { files: this.files };" + ""
				+ "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {"
				+ "    var evt = document.createEvent('MouseEvent');"
				+ "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);"
				+ "    evt.dataTransfer = dataTransfer;" + "    target.dispatchEvent(evt);" + "  });" + ""
				+ "  setTimeout(function () { document.body.removeChild(input); }, 25);" + "};"
				+ "document.body.appendChild(input);" + "return input;";

		WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, droparea, 0, 0);
		
		((RemoteWebElement) input).setFileDetector(detector);
	    
		input.sendKeys(file.getAbsolutePath());
//		input.sendKeys(filepath.getAbsoluteFile().toString());
		
		Utils.clickElement(driver, btn_UploadFile, "Upload Button");
		
		Utils.waitForAllWindowsToLoad(1, driver);
		Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
		
//		Utils.waitForElement(driver, "//img[@alt='Loading']", globalVariables.elementWaitTime, "xpath");
//		Utils.waitUntillLoaderDisappearsInHRP(driver);
	}
	
	
	public void viewDocument(String fileNameWithoutExtensionFNP, RemoteWebDriver remoteDriver) throws Exception
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Nov 2019
		 * 
		 */
		
		Utils.openDownloadsWindow(remoteDriver);
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + fileNameWithoutExtensionFNP + "')]/following-sibling::td//i[@class='fa fa-eye']", "xpath", "View Icon");
		
		Thread.sleep(10000);
		
		Utils.verifyIfDownloaded(remoteDriver);
		
	}
	
	
	public void deleteDocument(String fileNameWithoutExtensionFNP)
	{
		/*
		 * Created By : Saksham Kapoor
		 * Created On : 14 Nov 2019
		 * 
		 */
		
		Utils.clickDynamicElement(driver, "//td[contains(text(), '" + fileNameWithoutExtensionFNP + "')]/following-sibling::td//i[@class='fa fa-trash-o']", "xpath", "Delete Icon");
		
		if(Utils.isAlertPresent(driver))
		{
			if(driver.switchTo().alert().getText().equals("Are you sure you want to delete this document?"))
			{
				driver.switchTo().alert().accept();
				Log.message("Accepted the alert to Delete the document");
			}
			
			else
				Log.message("Alert to confirm deletion is not present");
		}
		
		else
			Log.message("Alert to confirm deletion is not present");
		
		if(Utils.isAlertPresent(driver))
		{
			if(driver.switchTo().alert().getText().equals("Document Deleted Successfully"))
			{
				driver.switchTo().alert().accept();
				Log.message("Accepted the alert which verifies document deletion");
			}
			
			else
				Log.message("Alert to verify deletion is not present");
		}
		
		else
			Log.message("Alert to verify deletion is not present");
	}
	
 
	public void verifyDocumentUpload(boolean value)
    {
		// Created by Nitisha Sinha
        // Created on 13/02/20
    	
    	if(value)
		{
			Utils.verifyIfDataPresent(driver, "//button[contains(text(),'Upload')]", "xpath", "Document upload button", "Documents under FNP Home Page");
		}
		
		else
		{
			Utils.verifyIfDataNotPresent(driver, "//button[contains(text(),'Upload')]", waitElement_documentHeader, "xpath", "Document upload button", "Documents under FNP Home Page");
		}
    } 
 
	 public void verifydocumentsPage() 
		{
			 /*
			  * Created By : Saurav Purohit
			  * Created On : 26 Feb 2020
			  */
		    try{
			    Utils.waitForAjax(driver);
		    	Utils.verifyIfStaticElementDisplayed(driver, waitElement_documentHeader, "Documents Page", "on FNP page");
		    }catch(Exception e){
			 Log.failsoft("Verification of Docuements Page failed", driver);
		    }

	}
		 
		 
		 
		
				
				
				
				
				public void clickUpload(boolean screenshot)  
				 {
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 26 Feb 2019
					  */ 
					Utils.clickElement(driver, btn_Upload, "Upload");
				 }  
				
				
				public void verifyUploadFilePage() 
				{
					 /*
					  * Created By : Saurav Purohit
					  * Created On : 26 Feb 2020
					  */
				    try{
				     Utils.waitForAllWindowsToLoad(2, driver);
				     
				     Utils.switchWindows(driver, "Upload file", "title", "false");
				     
					 Utils.softVerifyPageTitle(driver, "Upload file");
					 
					 driver.close();

					 Utils.waitForAllWindowsToLoad(1, driver);

					Utils.switchWindows(driver, "INSZoom - Foreign National Portal", "title", "false");
				    }catch(Exception e){
					 Log.failsoft("Verification of Uplaod File page failed", driver);
				    }

			}

}
