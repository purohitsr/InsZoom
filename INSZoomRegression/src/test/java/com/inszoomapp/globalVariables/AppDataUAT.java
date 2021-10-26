package com.inszoomapp.globalVariables;

public class AppDataUAT extends AppDataBase
{
	public AppDataUAT()
	{
		PetitionType_AddCase ="AOS Adjustment of Status (Family Based) (I-485 ONLY)";
		AddCase_CountryName ="United States of America";
		CaseManagerToWork ="Saurav Purohit";
		//Using in P3 test (MU_TC_37) Service Center Info
		clientFirstName = "Saksham";
		fnpLoginForP3 = "";
		fnpPasswordForP3 = "";
		fileNameStartsWith = "PGRIV";
		
		//Smoke Test data
		
		caseIdForSmokeTest = "" ;
		
		//Email template Tests
		caseName1 = "KBGRIV00070-2";
		caseName2 = "KBGRIV00048-4";
		petitionTemplateName = "B-1 Business Visitor CP";
		petitionTemplateNamelinkedToEditTemplate = "B-2 Tourist Visitors COS (I-539)";
		petitionTemplateForCaseCreation = "H-1B Specialty Occupation (Generic)";
		corpNameforCaseCreation= "Corp1WithHQ";
		clientNameforCaseCreation= "Client1Corp1 Corp1";
		
		CM_userName = "ASakkuQA";
		CM_password = "Zoom@123";
		
		B_userName = "";
		B_password = "Zoom@1234";
		
		P_userName = "";
		P_password = "Zoom@1234";
		
		
	//	*********** Portal Setup Credentials *******************
		CM_userNamePortal = ""; // Aspire05
		CM_passwordPortal = "Zoom@123"; // Zoom@1234
		
		B_userNamePortal = ""; // BGRHB03137
		B_passwordPortal = "Zoom@1234"; //  Zoom@1234
		
		P_userNamePortal = ""; // PGRHB01563
		P_passwordPortal = "Zoom@123"; // Zoom@1234
		
		corporationName = "Portal Setup Corportion"; //Asiprejan22
		
		
	//	********** Portal Setup 3.2 Credentials *************
		
		CM_userNamePortal3_2 = "ASakkuQA";
		CM_passwordPortal3_2 = "Zoom@123";
				
		CEO_userName = "CGRIV67900055";
		CEO_password = "Zoom@1234";
		
		Manager_userName = "CGRIV48200057";
		Manager_password = "Zoom@1234";
		
		Lead_userName = "CGRIV86000056";
		Lead_password = "Zoom@1234";
		
		Captain_userName = "CGRIV37200058";
		Captain_password = "Zoom@1234";
		
		Employee_userName = "CGRIV40400059";
		Employee_password = "Zoom@1234";
		
		Intern_userName = "CGRIV73000060";
		Intern_password = "Zoom@1234";	
	}
	
}
