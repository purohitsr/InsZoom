package com.inszoomapp.testscripts;

import java.io.FileInputStream;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.inszoomapp.globalVariables.AppDataBase;
import com.inszoomapp.globalVariables.AppDataProd;
import com.inszoomapp.globalVariables.AppDataQA;
import com.inszoomapp.globalVariables.AppDataStage;
import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.BaseTest;
import com.support.files.EmailReport;

@Listeners(EmailReport.class)
public class FNPStandaloneScripts extends BaseTest
{
	AppDataBase data ;
	
	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

		
		globalVariables.browserUsedForExecution = browserName;
		
		try {

			switch (env.toUpperCase()) {
			case "QA": {
				data = new AppDataQA();
				globalVariables.environmentName = env;
				break;
			}
			case "STAGE": {
				data = new AppDataStage();
				globalVariables.environmentName = env;
				break;
			}
			case "AST":
			{
				globalVariables.environmentName = env;
				break;
			}
			case "PROD": 
			{
				data = new AppDataProd();
				globalVariables.environmentName = env;
				break;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
