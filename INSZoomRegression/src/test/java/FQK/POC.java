





//@Test(groups = {"corporation", "HRP"}, description = "Corp: Attach custom label to corp", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//		public void POC(String browser) throws Exception 
//		{		
//			
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//			globalVariables.testCaseDescription = "Corp: Attach custom label to corp";
//			System.setProperty("webdriver.chrome.driver", "C:/Users/insind935/Desktop/grid/chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			
//			options.addExtensions(new File("C:/Users/insind935/AppData/Local/Google/Chrome/User Data/Default/Extensions/bmdignebhhpjipncgjelngepnhlipaem/1.0.0.52_0.crx"));
////			options.addExtensions(new File("C:/Users/insind935/Downloads/INSZoom-E-File_v1.0.0.52.crx"));
//			
//			DesiredCapabilities dc = DesiredCapabilities.chrome();
//			dc.setCapability(ChromeOptions.CAPABILITY, options);
//			
//			final WebDriver driver = new ChromeDriver(dc);
//			
//			//driver.get(browser);
//			
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//
//			try {
//				
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin("Asauravprod", "Zoom@1234", true);
//
//		        login.clickAgreeButton(false);
//
//		        caseManagerDashboardPage.clickCaseTab(true);
//		        
//		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//		   
//		        caseListPage.clickOnCaseId("KBHCZ02864-1", true);
//		        
//		        caseListPage.clickFormsTab();
//
//		        CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
//		        
//		        caseFormPage.filldetails();
//			}
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			}
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}


//public void filldetails() throws Exception
//	{	
//		Utils.clickDynamicElement(driver, "//b[contains(text(),'DS-160')]/ancestor::td[2]/following-sibling::td[2]//img[@title='Fill Form']", "xpath", "fill icon");
//		Utils.waitForAllWindowsToLoad(1, driver);
//		Utils.switchWindows(driver, "INSZoom.com - DS160 Form", "title", "false");
//		Utils.waitForElement(driver, "//span[contains(text(),'Designate Location and Application Info')]", globalVariables.elementWaitTime, "xpath");
//		WebElement dropDown_location = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_ucLocation_ddlLocation"));
//		Utils.selectOptionFromDropDownByVal(driver, "MDR", dropDown_location);
//		WebElement dropDown_securityQuest = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_ddlQuestions"));
//		Utils.selectOptionFromDropDownByVal(driver, "17", dropDown_securityQuest);
//		WebElement answer = driver.findElement(By.id("ctl06_ctl00_SiteContentPlaceHolder_txtAnswer"));
//		Utils.enterText(driver, answer, "Japan", "security answer");
////		Utils.clickDynamicElement(driver, "btnSaveBtm", "id", "save button");
////		Utils.clickDynamicElement(driver, "btnCloseBtm", "id", "close button");
////		Utils.waitForAllWindowsToLoad(1, driver);
////		Utils.switchWindows(driver, "INSZoom.com - List Of Case Forms", "title", "false");
////		Utils.clickDynamicElement(driver, "//b[contains(text(),'DS-160')]/ancestor::td[2]/following-sibling::td[2]//img[@title='Submit Form to U.S. State Dept.']", "xpath", "e-fill img");
////		Utils.clickDynamicElement(driver, "(//div[@class='modal-footer']//button[contains(text(),'Agree')])[1]", "xpath", "agree button");
//////		Utils.waitForAllWindowsToLoad(2, driver);
//////		Utils.switchWindows(driver, "INSZoom Efile Progress Disclaimer", "title", "false");
////		Utils.waitForAllWindowsToLoad(3, driver);
////		Utils.switchWindows(driver, "Nonimmigrant Visa - Instructions Page", "title", "false");
////		driver.switchTo().alert().accept();
//		Utils.clickElement(driver, btn_saveAndNext, "save and next");
//		Utils.enterText(driver, textBox_surName, "Likitha", "surname");
//		Utils.enterText(driver, textBox_givenName, "Krishna", "given name");
//		Utils.enterText(driver, textBox_fullName, "Likitha Krishna", "full name");
//		Utils.clickElement(driver, radioButton_otherNames,"no in other name radio button" );
//		Utils.clickElement(driver, radioButton_teleCode,"no in telecode radio button" );
//		Utils.clickElement(driver, radioButton_sex, "sex as female");
//		Utils.selectOptionFromDropDownByVal(driver, "S", dropDown_Status);
//		Utils.selectOptionFromDropDown(driver, "11", dropDown_birthDay);
//		Utils.selectOptionFromDropDown(driver, "DEC", dropDown_birthMonth);
//		Utils.enterText(driver, textBox_birthYear, "1997", "birth year");
//		Utils.enterText(driver, textBox_birthCity, "Chennai", "birth city");
//		Utils.enterText(driver, textBox_birthState, "Tamil Nadu", "birth state");
//		Utils.enterText(driver, textBox_birthCountry, "India", "birth country");
//		Utils.clickElement(driver, btn_saveAndNext, "save and next");
//	}




//@Test(description = "Fill forms and verify in e-file", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
//		public void POC(String browser) throws Exception 
//		{		
//			
//			ReadWriteExcel readExcel = new ReadWriteExcel();
//			String corpName = readExcel.initTest(workbookName, sheetName, "ALoginSavedCorporationName");
//			globalVariables.testCaseDescription = "Corp: Attach custom label to corp";
//			System.setProperty("webdriver.chrome.driver", "C:/Users/insind935/Desktop/grid/chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			
//			options.addExtensions(new File("C:/Users/insind935/AppData/Local/Google/Chrome/User Data/Default/Extensions/mjpjogohacpmkdhlnolomondagacmdoi/1.0.0.52_0.crx"));
////			options.addExtensions(new File("C:/Users/insind935/Downloads/INSZoom-E-File_v1.0.0.52.crx"));
//			
//			DesiredCapabilities dc = DesiredCapabilities.chrome();
//			dc.setCapability(ChromeOptions.CAPABILITY, options);
//			
//			final WebDriver driver = new ChromeDriver(dc);
//			
//			//driver.get(browser);
//			
//			driver.manage().timeouts().pageLoadTimeout(globalVariables.pageLoadTime, TimeUnit.SECONDS);
//			
//			
//
//			try {
//							
////				driver.get("https://global.inszoom.com/");
////				Log.message("Launched Website");
////				
////				Utils.enterTextInDynamicElement(driver, "input[placeholder*='Login ID']", "css", "ASauravProd", "username");
////				Utils.enterTextInDynamicElement(driver, "input[placeholder*='Password']", "css", "Zoom@1234", "password");
////				Utils.clickDynamicElement(driver, "login", "id", "Clicked");
//				
//				LoginPageTest login = new LoginPageTest(driver, webSite);
//
//		        CM_DashboardPage caseManagerDashboardPage = login.caseManagerlogin("Asauravprod", "Zoom@1234", true);
//
//		        login.clickAgreeButton(false);
//
//		        caseManagerDashboardPage.clickCaseTab(true);
//		        
//		        CM_CaseListPage caseListPage = new CM_CaseListPage(driver);
//		        
//		        //caseListPage.clickOnCaseId("KBHCZ02804-3", true); //Created for forms
//		   
//		        caseListPage.clickOnCaseId("KBHCZ02864-1", true);
//		        
//		        caseListPage.clickFormsTab();
//
//		        CM_CaseFormPage caseFormPage = new CM_CaseFormPage(driver);
//		        
//		        caseFormPage.filldetails();
//			}
//			catch (Exception e) {
//				Log.exception(e, driver);
//
//			}
//			finally {
//				Log.endTestCase();
//				driver.quit();
//			}
//		}