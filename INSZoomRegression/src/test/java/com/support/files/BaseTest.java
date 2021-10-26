package com.support.files;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class BaseTest {
	protected static ExtentReports extent;
	protected static String webSite;
	protected static String env;
	protected static String browserName;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext ctx) {

	}

	/*
	 * @BeforeTest public void init(ITestContext context) { webSite =
	 * (System.getProperty("webSite") != null ? System.getProperty("webSite") :
	 * context.getCurrentXmlTest().getParameter("webSite")).toLowerCase(); }
	 */

	/**
	 * Inits the test data.
	 *
	 * @param workbook
	 *            the workbook
	 * @param sheetName
	 *            the sheet name
	 */
	public HashMap<String, String> initTestData(String workbook, String sheetName, String testcaseID) {
		/** Loading the test data from excel using the test case id */
		TestDataExtractor testData = new TestDataExtractor();
		testData.setWorkBookName(workbook);
		testData.setWorkSheet(sheetName);
		testData.setFilePathMapping(true);
		testData.setTestCaseId(testcaseID);
		return testData.readData();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		// extent = new ExtentReports("./test-output/TestAutomationExtentReport.html",
		// true, DisplayOrder.NEWEST_FIRST, NetworkMode.ONLINE);
		/*
		 * extent = new ExtentReports("./target/surefire-reports/TestReport.html", true,
		 * DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
		 */
		extent = new ExtentReports("./target/surefire-reports/ExtentReport.html", true, DisplayOrder.OLDEST_FIRST,NetworkMode.ONLINE);
	}

	/*
	 * After suite will be responsible to close the report properly at the end You
	 * an have another afterSuite as well in the derived class and this one will be
	 * called in the end making it the last method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() {
		extent.flush();

	}

}
