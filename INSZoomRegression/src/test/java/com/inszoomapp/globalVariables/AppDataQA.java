package com.inszoomapp.globalVariables;


public class AppDataQA extends AppDataBase
{
	public AppDataQA()
	{
		PetitionType_AddCase ="AOS Adjustment of Status (Family Based) (I-485 ONLY)";
		AddCase_CountryName ="United States of America";
		CaseManagerToWork ="Saurav Purohit";
		//Using in P3 test (MU_TC_37) Service Center Info
		clientFirstName = "Saksham";
		fnpLoginForP3 = "";
		fnpPasswordForP3 = "";
		fileNameStartsWith = "PGRIV";
		
		
		//Smoke Test Data
		clintFirstNameForSmokeTest="Smoke";
		caseIdForSmokeTest = "KBGRIV00002-1" ;
		//Added for Email template CaseName
		
		caseName1 = "KBGRIV00070-2";
		caseName2 = "KBGRIV00048-4";
		petitionTemplateName = "B-1 Business Visitor CP";
		petitionTemplateForCaseCreation = "E-1 Treaty Traders CP";
		corpNameforCaseCreation = "Corp1WithHQ";
		clientNameforCaseCreation = "Client1Corp1 Corp1";
		petitionTemplateNamelinkedToEditTemplate= "B-2 Tourist Visitors COS (I-539)";
		
		caseIdForSupervisorCheck = "KBGRIV00048-2" ; //Added By Saksham Kapoor on 26/04/19
		
		CM_userName = "ALikistg";
		CM_password = "Zoom@123";
		
		B_userName = "BGRIV00004";
		B_password = "Zoom@123";
		
		P_userName = "PGRIV00003";
		P_password = "Zoom@123";
		
		
	//	*********** Portal Setup Credentials *******************
		CM_userNamePortal = ""; // Aspire05
		CM_passwordPortal = "Zoom@123"; // Zoom@1234
		
		B_userNamePortal = ""; // BGRHB03137
		B_passwordPortal = "Zoom@1234"; //  Zoom@1234
		
		P_userNamePortal = ""; // PGRHB01563
		P_passwordPortal = "Zoom@123"; // Zoom@1234
		
		corporationName = "Portal Setup Corportion"; //Asiprejan22
		
		
	//	********** Portal Setup 3.2 Credentials *************
		
		CM_userNamePortal3_2 = "ASysSanity";
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
		Employee_password = "Zoom@123";
		
		Intern_userName = "CGRIV73000060";
		Intern_password = "Zoom@1234";
		
		
		  //*********** Questionnaire Testcases***********
    	
        CM_userNameQuest = "AQuestionnaireQA";//"AGRIT21";
        CM_passwordQuest = "Zoom@123";
        
        B_userNameQuest = "BGRIT02480";//"BGRMZ00002";
        B_passwordQuest = "Zoom@1234";
    	
 		P_userNameQuest = "PGRIT02479";//"PGRMZ00050";
 		P_passwordQuest = "Zoom@1234";
 		
 		questCaseId = "KBGRIT02480-1";
 		
 		questCaseForKBEvent="KBGRIT02480-2";
 		
 		//questClientAddCase = "Questionnaire Client 2";
 	    
 	    questCaseManagerToWork = "Yatharth Pandya 2";
 	    questPetitionType_AddCase = "Questionnaire Template";
 	    clientQuestionnaireName = "Sample Contract Questionnaire (Employee)";
 	    
 	   caseRequestTemplateName="Automation Case Request Template";

	}
	
}
