package com.support.files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelToDataProvider {
    
//    String xlFilePath = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
//    String sheetName = "Sheet1";
 //   String xlFilePath = ".\\src\\main\\resources\\testdata\\data\\NewestDocTestData.xls";
//	String sheetName = "AOS - Family Based";
	
//	String xlFilePath = ".\\src\\main\\resources\\testdata\\data\\NewestDocTestData.xls";
//	String sheetName = "Immediate Rel - Parent of USC";
	
    RowCountColumnCount eat = null;
    
//    @Test(dataProvider = "userData")
//    public void fillUserForm(String userName, String passWord, String dateCreated, String noOfAttempts, String result)
//    {
//       System.out.println("UserName: "+ userName);
//       System.out.println("PassWord: "+ passWord);
//       System.out.println("DateCreated: "+ dateCreated);
//       System.out.println("NoOfAttempts: "+ noOfAttempts);
//       System.out.println("Result: "+ result);
//       System.out.println("*********************");
//    }
    
    @Test(dataProvider = "userData")
    public void docTest(String From, String DocumentCheckList, String Description, String COS, String EOS, String CP)
    {
       System.out.println("DocumentCheckList: "+ DocumentCheckList);
       System.out.println("Description: "+ Description);
       System.out.println("COS: "+ COS);
       System.out.println("EOS: "+ EOS);
       System.out.println("CP: "+ CP);
       System.out.println("*********************");
    }
    
    
   // @DataProvider(name="userData")
    public Object[][] userFormData(String xlFilePath,String sheetName) throws Exception
    {
        Object[][] data = testData(xlFilePath, sheetName);
        return data;
    }
    
    public Object[][] testData(String xlFilePath, String sheetName) throws Exception
    {
        Object[][] excelData = null;
        eat = new RowCountColumnCount(xlFilePath);
        int rows = eat.getRowCount(sheetName);
        int columns = eat.getColumnCount(sheetName);
                
        excelData = new Object[rows-1][columns];
        
        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                excelData[i-1][j] = eat.getCellData(sheetName, j, i);
            }
            
        }
        return excelData;
    }
}