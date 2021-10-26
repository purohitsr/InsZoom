package fqkDB;

import java.net.MalformedURLException;
import java.sql.*;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.support.files.BaseTest;
import com.support.files.DataProviderUtils;
import com.support.files.EmailReport;
import com.support.files.Log;
import com.support.files.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlatTablesSPExecution extends BaseTest
{
	
	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();

		env = (System.getProperty("env") != null ? System.getProperty("env")
				: context.getCurrentXmlTest().getParameter("env")).toLowerCase();

		browserName = (System.getProperty("browserName") != null ? System.getProperty("browserName")
				: context.getCurrentXmlTest().getParameter("browserName")).toLowerCase();

	}
	
	
	@Test(description = "Check DB connection", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
    public void connectDB(String browser) throws MalformedURLException
	{
//		final WebDriver driver = WebDriverFactory.get(browser);
        try{
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String connectionURL = "jdbc:sqlserver://172.18.1.11:1433;databasename=ENTDEV;user=appproductdev;password=appprod@321;";
	        Connection con=DriverManager.getConnection(connectionURL);
	        //CallableStatement cstmt = null;
	       
	        Statement stmt=con.createStatement();
	        ResultSet rs=stmt.executeQuery("select * from tbl_org");
	        while(rs.next())
	        	System.out.println(rs.getString(1));
	        con.close();  
        }catch(Exception e)
        {
        	Log.fail("ERROR: - " + e.getMessage());
        }
	}

}
