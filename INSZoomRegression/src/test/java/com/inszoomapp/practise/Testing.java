package com.inszoomapp.practise;

import java.util.Hashtable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.Test;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.ReadFromExcel;

public class Testing {

		@Test()
		public void readExcel(){
			System.out.println("In readExcel test");
			
		}
		
		
		
        /*public Hashtable<String,String> getdata(){
        	Hashtable<String, Integer> excelHeaders= new Hashtable<String, Integer>();
        	
        	
			ReadFromExcel excel = new ReadFromExcel();
			
			HSSFSheet sheet =excel.initiateExcelConnection("Name", "Regression_QA.xlsx", true);
			
			Hashtable<String, Integer> rowColumnCount = excel.findRowColumnCount(sheet, rowColumnCount);
					
			excel.readExcelHeaders(sheet, excelHeaders, rowColumnCount);
		}	*/
		

    }


