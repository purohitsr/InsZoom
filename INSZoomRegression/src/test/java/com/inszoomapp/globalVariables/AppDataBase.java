package com.inszoomapp.globalVariables;

public class AppDataBase 
{	
	public String PetitionType_AddCase ="AOS Adjustment of Status (Family Based) (I-485 ONLY)";
	public String petitionTypeForCanada = "Alberta Immigrant Nominee Program - US Visa Holder category";
	public String AddCase_CountryName ="United States of America";
	public String AddCanadaCase_CountryName ="Canada";
	public String CaseManagerToWork ="Saurav Purohit";
	//Using in P3 test (MU_TC_37) Service Center Info
	public String clientFirstName = "Saksham";
	public String fnpLoginForP3 = "";
	public String fnpPasswordForP3 = "";
	public String fileNameStartsWith = "PGRIV";
	public String questionnaire = "Automation-I-941: Application for Entrepreneur Parole";
	public String secondQuestionnaire = "Aspire ETA-9035";
	public String eConsent = "e-consent for Automation";
	
	
	//Smoke Test Data
	public String clintFirstNameForSmokeTest = "";
	public String caseIdForSmokeTest = "KBGRIV00002-1";
	//Added for Email template CaseName
	
	public String caseName1 = "KBGRIV00070-2";
	public String caseName2 = "KBGRIV00048-4";
	public String petitionTemplateName = "B-1 Business Visitor CP";
	public String petitionTemplateForCaseCreation = "E-1 Treaty Traders CP";
	public String corpNameforCaseCreation = "Corp1WithHQ";
	public String clientNameforCaseCreation = "Client1Corp1 Corp1";
	public String petitionTemplateNamelinkedToEditTemplate= "B-2 Tourist Visitors COS (I-539)";
	
	public String caseIdForSupervisorCheck = "KBGRIV00048-2" ; //Added By Saksham Kapoor on 26/04/19
	
	public String CM_userName = "ASysSanity";
	public String CM_password = "Zoom@123";
	
	public String B_userName = "";
	public String B_password = "Zoom@1234";
	
	public String P_userName = "";
	public String P_password = "Zoom@1234";
	
	
//	*********** Portal Setup Credentials *******************
	public String CM_userNamePortal = ""; // Aspire05
	public String CM_passwordPortal = "Zoom@123"; // Zoom@1234
	
	public String B_userNamePortal = ""; // BGRHB03137
	public String B_passwordPortal = "Zoom@1234"; //  Zoom@1234
	
	public String P_userNamePortal = ""; // PGRHB01563
	public String P_passwordPortal = "Zoom@123"; // Zoom@1234
	
	public String corporationName = "Portal Setup Corportion"; //Asiprejan22
	
	public String newCorporationName = "newCorp";
	
	public String caseIdPortal = "KBGRIT00528-2";
	
	public String hrpClientName = "Hermoine";
	
	public String roleName = "CEO";
	
	
//	********** Portal Setup 3.2 Credentials *************
	
	public String CM_userNamePortal3_2 = "ASakkuQA";
	public String CM_passwordPortal3_2 = "Zoom@123";
			
	public String CEO_userName = "CGRIV67900055";
	public String CEO_password = "Zoom@1234";
	
	public String Manager_userName = "CGRIV48200057";
	public String Manager_password = "Zoom@1234";
	
	public String Lead_userName = "CGRIV86000056";
	public String Lead_password = "Zoom@1234";
	
	public String Captain_userName = "CGRIV37200058";
	public String Captain_password = "Zoom@1234";
	
	public String Employee_userName = "CGRIV40400059";
	public String Employee_password = "Zoom@1234";
	
	public String Intern_userName = "CGRIV73000060";
	public String Intern_password = "Zoom@1234";	
	
	
	
	//********* For FNP Standalone testcases**************
	
	public String FNPLoginIdForStandalone = "BLoginForFNP";
	public String FNPPasswordForStandalone = "Zoom@123";
	
	//*********** Receipt Management Testcases***********
	
	public String CM_userNameRM = "AReceiptQA";
    public String CM_passwordRM = "Zoom@123";
	
    public String RMCaseId = "KBGRMZ00048-1";
    
    //********* For Access Rights TestCases**************
    
    public String CaseManagerToWorkForAccessRights ="Kuchinad Shashank";
    public String CaseManagerToWorkForAccessRightsStd ="Yatharth Shashank";
   
    public String Standard_Super_CM_userName = "";
    public String Standard_Super_CM_password = "";
   
    public String Standard_CM_userName = "";
    public String Standard_CM_password = "";
    
    public String Standard_SuperUserCaseId = "";
   
    public String EnterpriseEnabled_Super_CM_userName = "";
    public String EnterpriseEnabled_Super_CM_password = "";
   
    public String EnterpriseEnabled_CM_userName = "";
    public String EnterpriseEnabled_CM_password = "";
   
    public String EnterpriseDisabled_Super_CM_userName = "";
    public String EnterpriseDisabled_Super_CM_password = "";
   
    public String EnterpriseDisabled_CM_userName = "";
    public String EnterpriseDisabled_CM_password = "";
    
    public String EnterpriseEnabled_SuperUserCaseId = "";
    public String EnterpriseDisabled_SuperUserCaseId = "";
   
    public String ProfessionalEnabled_Super_CM_userName = "";
    public String ProfessionalEnabled_Super_CM_password = "";
   
    public String ProfessionalEnabled_CM_userName = "";
    public String ProfessionalEnabled_CM_password = "";
   
    public String ProfessionalDisabled_Super_CM_userName = "";
    public String ProfessionalDisabled_Super_CM_password = "";
   
    public String ProfessionalDisabled_CM_userName = "";
    public String ProfessionalDisabled_CM_password = "";
    
    public String ProfessionalEnabled_SuperUserCaseId = "";
    public String ProfessionalDisabled_SuperUserCaseId = "";
    
    //*********** Questionnaire Testcases***********
	
  	public String CM_userNameQuest = "AGRMZ4";
    public String CM_passwordQuest = "Nitisha@123";
    
    public String B_userNameQuest = "";//"BGRMZ00002";
	public String B_passwordQuest = "";
	
	public String P_userNameQuest = "PGRIT02479";//"PGRMZ00050";
	public String P_passwordQuest = "Zoom@1234";
	
	public String questCaseManagerToWork = "";
    public String questPetitionType_AddCase = "";
    
    public String questCaseId = "";
    
    public static String questCaseForKBEvent="";
    public String clientQuestionnaireName = "";
    
    public static String caseRequestTemplateName="";
    

}
