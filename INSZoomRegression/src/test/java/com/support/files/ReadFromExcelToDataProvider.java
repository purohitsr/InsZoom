package com.support.files;

import org.testng.annotations.DataProvider;

public class ReadFromExcelToDataProvider {
	    
	    public String xlFilePath = ".\\src\\main\\resources\\testdata\\data\\DocTestData.xls";
	    public String sheetName = "Sheet1";
	    RowCountColumnCount eat = null;

	    @DataProvider
	    public Object[][] userFormData() throws Exception
	    {
	        Object[][] data = testData(xlFilePath, sheetName);
	        return data;
	    }
	    
	    public Object[][] testData(String xlFilePath, String sheetName) throws Exception
	    {
	        Object[][] excelData = null;
	        RowCountColumnCount eat = new RowCountColumnCount(xlFilePath);
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
