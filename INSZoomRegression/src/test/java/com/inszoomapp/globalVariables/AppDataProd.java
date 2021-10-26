package com.inszoomapp.globalVariables;

public class AppDataProd extends AppDataBase
{
	//public String abc = "";

	public AppDataProd()
	{
		PetitionType_AddCase ="Advanced Permission to Enter as Nonimmigrant";
		AddCase_CountryName ="United States of America";
		CaseManagerToWork ="Saurav Purohit";
		//Using in P3 test (MU_TC_37) Service Center Info
		clientFirstName = "Manoj";
		fnpLoginForP3 = "";
		fnpPasswordForP3 = "";
		questionnaire = "Automation-I-941: Application for Entrepreneur Parole";
		secondQuestionnaire = "Aspire ETA-9035";
		eConsent = "Agreement Policy";
		
		//Smoke Test Data
		
		caseIdForSmokeTest = "KBHCZ00598-3" ;
		
		//Added for Email template CaseName . Not added this as the precondition data setup for Email template in Prod is 
		//yet to be done
		caseName1 = "KBHCZ02084-2";
		caseName2 = "KBHCZ02084-3";
		petitionTemplateName = "B-1 Business Visitor CP- Demo" ;
		petitionTemplateNamelinkedToEditTemplate= "H-1B Specialty Occupation Workers EOS (I-129)";
		petitionTemplateForCaseCreation = "H-1B Specialty Occupation (Generic)";
		corpNameforCaseCreation = "Corp1WithHQ";
		clientNameforCaseCreation = "Saurav Ranjan Purohit";
		
		
		CM_userName = "ASauravProd";
		CM_password = "Zoom@123";
		
//		CM_userName = "AABD467";
//		CM_password = "Zoom@123";
		
//		CM_userName = "A213";
//		CM_password = "Divij@123";

//		CM_userName = "ASakkuProd";
//		CM_password = "Zoom@123";
		
//		CM_userName = "AAutomationQA";
//		CM_password = "Zoom@123";
		
//		CM_userName = "ALikithaProd";
//		CM_password = "Zoom@123";
		
		B_userName = "BAutomationQA";
		B_password = "Zoom@1234";
		
		P_userName = "PAutomationQA";
		P_password = "Zoom@1234";
		
		
	//	*********** Portal Setup Credentials *******************
		CM_userNamePortal = "ASauravProd";
		CM_passwordPortal = "Zoom@123";
		
		B_userNamePortal = "BHCZ02280";
		B_passwordPortal = "Zoom@1234";
		
		P_userNamePortal = "PHCZ02279";
		P_passwordPortal = "Zoom@123";
		
		corporationName = "Portal Setup Corportion";
		
		
	//	********** Portal Setup 3.2 Credentials *************
		
		CM_userNamePortal3_2 = "AAutomationQA";
		CM_passwordPortal3_2 = "Zoom@123";
				
		CEO_userName = "CHCZ46702095";
		CEO_password = "Zoom@1234";
		
		Manager_userName = "CHCZ88902097";
		Manager_password = "Zoom@1234";
		
		Lead_userName = "CHCZ34602096";
		Lead_password = "Zoom@1234";
		
		Captain_userName = "CHCZ92302114";
		Captain_password = "Zoom@1234";
		
		Employee_userName = "CHCZ43202116";
		Employee_password = "Zoom@1234";
		
		Intern_userName = "CHCZ80902115";
		Intern_password = "Zoom@1234";	
		
		caseIdForSupervisorCheck = "KBHCZ02084-1" ; //Added By Saksham Kapoor on 22/08/19
		
		
		//********* For FNP Standalone testcases**************
		
		FNPLoginIdForStandalone = "BLoginForFNP";
		FNPPasswordForStandalone = "Zoom@123";
		
	}
}
