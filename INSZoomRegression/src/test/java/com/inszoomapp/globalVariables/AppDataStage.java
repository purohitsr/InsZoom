package com.inszoomapp.globalVariables;

public class AppDataStage extends AppDataBase{
	
	public AppDataStage ()
	{
		PetitionType_AddCase ="AOS Adjustment of Status (Family Based) (I-485 ONLY)";
		AddCase_CountryName ="United States of America";
		CaseManagerToWork ="Saurav Purohit";
		//Using in P3 test (MU_TC_37) Service Center Info
		clientFirstName = "";
		fnpLoginForP3 = "";
		fnpPasswordForP3 = "";
		questionnaire = "Automation-I-941: Application for Entrepreneur Parole";
		secondQuestionnaire = "Aspire ETA-9035";
		eConsent = "e-consent for Automation";
		
		//Smoke Test data
		clintFirstNameForSmokeTest="Smoke";
		caseIdForSmokeTest = "KBGRIT00168-2" ;
		
		
		//Added for Email template CaseName . Not added this as the precondition data setup for Email template in Stage is 
	       //yet to be done
		caseName1 = "KBGRIT00003-2";
		caseName2 = "KBGRIT00003-3";
		petitionTemplateName = "B-1 Business Visitor CP" ;
		petitionTemplateForCaseCreation="E-1 Treaty Traders CP";
		corpNameforCaseCreation="CorpEmailTemplate";
		clientNameforCaseCreation="client1first client1last";
		petitionTemplateNamelinkedToEditTemplate="H-1B Specialty Occupation Workers EOS (I-129)";
		
		
//		CM_userName = "ASakku";
//		CM_password = "Zoom@1234567";
		
		CM_userName = "aSauravstg";
		CM_password = "Zoom@123";
		
//		CM_userName = "Asvth";
//		CM_password = "Ins@1234";
		
		B_userName = "";
		B_password = "Zoom@1234";
		
		P_userName = "";
		P_password = "Zoom@1234";
		
		
	//	*********** Portal Setup Credentials *******************
		
		corporationName = "NSCorp";
		
		newCorporationName = "newCorp";
		
		caseIdPortal = "KBGRIT00528-2";
		
		hrpClientName = "Hermoine";
		
		roleName = "CEO"; 
		
		B_userNamePortal = "Hermoine"; // BGRHB03137
		B_passwordPortal = "Zoom@123"; //  Zoom@1234
		
		P_userNamePortal = "SharonRpp"; // PGRHB01563
		P_passwordPortal = "Zoom@123"; // Zoom@1234  
		
		CM_userNamePortal = "AAutomationQA";
		CM_passwordPortal = "Zoom@123"; 
		
		
	//	********** Portal Setup 3.2 Credentials *************
		
//		CM_userNamePortal3_2 = "ASakkuQA";
//		CM_passwordPortal3_2 = "Zoom@123";
		
		CM_userNamePortal3_2 = "APortal3.2QA";
		CM_passwordPortal3_2 = "Zoom@123";
		
		CEO_userName = "CGRIT61700706";
		CEO_password = "Zoom@123";
		
		Manager_userName = "CGRIT92700707";
		Manager_password = "Zoom@123";
		
		Lead_userName = "CGRIT61000708";
		Lead_password = "Zoom@123";
		
		Captain_userName = "CGRIT71900709";
		Captain_password = "Zoom@123";
		
		Employee_userName = "CGRIT75000716";
		Employee_password = "Zoom@123";
		
		Intern_userName = "CGRIT53800717";
		Intern_password = "Zoom@123";
	
		
		//*********** Receipt Management Testcases***********
		
		CM_userNameRM = "AReceiptQA";
	    CM_passwordRM = "Zoom@123";
	    		
	    RMCaseId = "KBGRMZ00048-2";
	    
	   
	    //********** Access Rights Credentials*************

	        Standard_Super_CM_userName = "AGROZ1";
	        Standard_Super_CM_password = "Zoom@1234";
	       
	        Standard_CM_userName = "AGROZ2";
	        Standard_CM_password = "Zoom@123";
	       
	        EnterpriseEnabled_Super_CM_userName = "AGROC1";
	        EnterpriseEnabled_Super_CM_password = "Zoom@123";
	       
	        EnterpriseEnabled_CM_userName = "AGROC2";
	        EnterpriseEnabled_CM_password = "Zoom@123";
	       
	        EnterpriseDisabled_Super_CM_userName = "AGROD1";
	        EnterpriseDisabled_Super_CM_password = "Zoom@1234";
	       
	        EnterpriseDisabled_CM_userName = "AGROD2";
	        EnterpriseDisabled_CM_password = "Zoom@123";
	       
	        ProfessionalEnabled_Super_CM_userName = "AGROY1";
	        ProfessionalEnabled_Super_CM_password = "Zoom@123";
	       
	        ProfessionalEnabled_CM_userName = "AGROY2";
	        ProfessionalEnabled_CM_password = "Zoom@123";
	       
	        ProfessionalDisabled_Super_CM_userName = "AGROX1";
	        ProfessionalDisabled_Super_CM_password = "Zoom@123";
	       
	        ProfessionalDisabled_CM_userName = "AGROX2";
	        ProfessionalDisabled_CM_password = "Zoom@123";

	        Standard_SuperUserCaseId = "KBGROZ00002-1";
	        EnterpriseEnabled_SuperUserCaseId = "KBGROC00139-1";
	        EnterpriseDisabled_SuperUserCaseId = "KBGROD00045-1";
	        ProfessionalDisabled_SuperUserCaseId = "KBGROX00002-1";
			
	        //*********** Questionnaire Testcases***********
	    	
	        CM_userNameQuest = "AGRIV14";//"ANitishaManual";
	        CM_passwordQuest = "Zoom@123";
	        
	        B_userNameQuest = "BGRIV00570";//"BGRMZ00002";
	        B_passwordQuest = "Zoom@123";
	    	
	 		P_userNameQuest = "PGRIV00569";//"PGRMZ00050";
	 		P_passwordQuest = "Zoom@123";
	 		
	 		//questClientAddCase = "Questionnaire Client 2";
	 	    
	 	    questCaseManagerToWork = "QFname lQLname";
	 	    questPetitionType_AddCase = "Questionnaire Template";
	 	    
	 	    questCaseId="KBGRIV00570-1";
	 	    questCaseForKBEvent="KBGRIT02480-2";
	}
	
}
