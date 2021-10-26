package com.inszoomapp.globalVariables;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class globalVariables 
{
	public static String fromDate = "";  //Added by Souvik Ganguly on 10/08/2021
	public static String testCaseDescription = null; //Added by Saksham Kapoor on 08/10/2019 
	public static String environmentName = null;  //Added by Saksham Kapoor on 08/10/2019
	public static String browserUsedForExecution = null;  //Added by Saksham Kapoor on 08/10/2019
	public static int pageLoadTime = 150;  //Added by Saksham Kapoor on 08/10/2019
	public static int elementWaitTime = 120;  //Added by Saksham Kapoor on 08/10/2019
	
	public static String subjectForInvitation =  null;
	
	public static boolean SKIP_REMAINING_TESTS ;
	//Regression 
	
	//DO NOT CHANGE THE VALUE : THIS REPORT IS AVAILABLE IN ALL LOGINS : IN HRP THIS REPORT IS AVAILABLE AS Case Activity report
	public static String favoriteReportName = "Case Activities Standard Report -- Grouped By Corporations"; //Used in MU_TC_54_1
	//Used in MU_TC_24_1, MU_TC_24_2
	public static String firstNotesDetailsTextClient = "First test data for note details client";
	//Used in MU_TC_24_1, MU_TC_24_5
	public static String secondNotesDetailsTextClient = "Second test data for note details client";
	//Used in MU_TC_24_2, MU_TC_24_3, MU_TC_24_4, MU_TC_24_6, MU_TC_28_2
	public static String editFirstNotesDetailsTextClient = "Edit the added notes to the client";
	//Used in MU_TC_48_1, MU_TC_48_2
	public static String firstNotesDetailsTextCase = "First test data for note details case";
	//Used in MU_TC_48_2
	public static String editFirstNotesDetailsTextCase = "Edit the added notes to the case";
	//Used in MU_TC_48_3
	public static String printPreviewNotesDescriptionCase = "Test data for print preview note details case";
	//Used in MU_TC_48_4
	public static String deleteNotesDescriptionCase = "Notes to be deleted in case";
	////Used in MU_TC_48_5
	public static String viewNotesDescriptionCase = "Test data for View note details case";
	//Used in MU_TC_26_1
	public static String appointmentActivityDescriptionClient = "Appointment/Activity Description client";
	//Used in MU_TC_26_1, MU_TC_26_3, MU_TC_28_1, MU_TC_55_1
	public static String appointmentActivitySubjectClient = "Regarding Appointment/Activity Details client";
	//Used in MU_TC_26_1,MU_TC_26_3
	public static String appointmentActivityLocationClient = "Chennai";
	//Used in MU_TC_26_1
	public static String appointmentActivityCategoryClient = "Test Category for Regression";
	//Used in MU_TC_26_2
	public static String editAppointmentActivityLocationClient = "Madurai";
	//Used in MU_TC_26_3
	public static String deleteappointmentActivityDescriptionClient = "Appointment/Activity for Delete testcase";
	//Used in MU_TC_50_1 ,MU_TC_50_2
	public static String appointmentActivityDescriptionCase = "Appointment/Activity Description case";
	//Used in MU_TC_50_1 ,MU_TC_50_2, MU_TC_53_1, MU_TC_55_2
	public static String appointmentActivitySubjectCase = "Regarding Appointment/Activity Details case";
	//Used in MU_TC_50_1,MU_TC_50_2
	public static String appointmentActivityLocationCase = "Chennai";
	//Used in MU_TC_50_1,MU_TC_50_2
	public static String appointmentActivityDurationCase = "3";
	//Used in MU_TC_50_1,MU_TC_50_2
	public static String appointmentActivityCategoryCase = "Test Appointment/Activity Category";
	//Used in MU_TC_50_2
	public static String deleteappointmentActivityDescriptionCase = "Delete Test Appointment/Activity Category in case";
	//Used in MU_TC_50_3
	public static String editappointmentActivityDescriptionCase = "Edit Test Appointment/Activity Category in case";
	//Used in MU_TC_50_3
	public static String editAppointmentActivityLocationCase = "Paris";
	//Used in MU_TC_51_1, MU_TC_51_4, MU_TC_51_3
	public static String firstTaskDetailsCaseTextCase = "First test data for task details in case";
	//Used in MU_TC_51_1, MU_TC_51_2
	public static String caseTaskTypeSelectCase = "T";
	//Used in MU_TC_51_4
	public static String editfirstTaskDetailsCaseTextCase = "Edit First test data for task details case";
	//Used in MU_TC_51_2, MU_TC_51_5
	public static String deleteTaskDetailsCaseTextCase = "Delete test data for task details in case";
	//Used in MU_TC_27_1, MU_TC_27_3,  MU_TC_27_4
	public static String firstTaskDetailsTextClient = "First test data for task details in client";
	//Used in MU_TC_27_1
	public static String taskTypeSelectClient = "T";
	//Used in MU_TC_27_2
	public static String deleteTaskDetailsTextClient = "Delete test data for task details in client";
	//Used in MU_TC_27_4
	public static String editfirstTaskDetailsTextClient = "Edit First test data for task details client";
	//Used in MU_TC_15_1, 41_1
	public static String salary = "20000";
	//Used in tcMUTC16_1, tcMUTC16_2
	public static String countryName = "United States of America";
	public static String countryflag = "/ZoomCMS/Content/img/flags/USA_flag.png";
	public static String verificationCountry = "United States Of America";
	//Used in tcMUTC16_1,tcMUTC16_2, MU_TC_11_1, MU_TC_35_3
	public static String visaNumber = "02988078";
	//Used in tcMUTC16_2
	public static String edittedVisaNumber = "28029134";
	//Used in MU_TC_79_0
	public static String visaCountry = "USA";
	//Used in MU_TC_35_3
	public static String consularProcessingCity = "Chennai";
	//Used in MU_TC_35_3
	public static String visaCategory = "E1";
	//Used in MU_TC_68_2
	public static String documentExpirationTemplateLessThan6Months = "ExpirationDateTemplate(LessThan6Months)";
	//Used in MU_TC_68_3
	public static String documentExpirationTemplateMoreThan6Months = "ExpirationDateTemplate(MoreThan6Months)";
	//Used in MU_TC_68_4
	public static String documentExpirationTemplateIn6Months = "ExpirationDateTemplate(In6Months)";
	//Used in MU_TC_9_1
	public static String documentDescription = "Document description";
	//Used in MU_TC_18_1
	public static String petitionDocType = "V";
	//Used in MU_TC_18_1
	public static String immigrationStatus = "B-1";
	//Used in MU_TC_18_1
	public static String receiptDate = "11/06/2018";
	//Used in MU_TC_18_2
	public static String edittedImmigrationStatus = "Visa";
	//Used in MU_TC_18_2
	public static String edittedPetitiomDocType = "G";
	//Used in MU_TC_35_1
	public static String receiptType = "P";
	//Used in MU_TC_35_1
	public static String editReceiptDate = "12/25/2018";
	//Used in MU_TC_35_1
	public static String receiptStatus = "Approved";
	//Used in MU_TC_31_1
	public static String city = "Chennai";
	//Used in MU_TC_31_2
	public static String edittedCityName = "Shimla";
	//Used in MU_TC_36_1
	public static String receiptDateTxt = "12/26/2018";
	//Used in MU_TC_36_1, MU_TC_36_2
	public static String receiptNumberTxt = RandomStringUtils.randomNumeric(13).toString();
	//Used in MU_TC_36_1
	public static String receiptStatusInReceiptNumber = "Pending";
	//Used in MU_TC_36_1
	public static String receiptTypeInReceiptNumber = "D";
	//Used in MU_TC_36_2
	public static String edittedReceiptNumber = RandomStringUtils.randomNumeric(13).toString();
	//Used in MU_TC_36_2
	public static String editReceiptDateTxt = "12/31/2018";
	//Used in MU_TC_34
	public static String editReceiptDateInCaseProfile = "Dec 31 2018";
	//Used in MU_TC_35_4
	public static String cityInDatesDetails = "Hydrabad";
	//Used in MU_TC_3
	public static String clientFirstName = "Client";
	public static String clientLastName = "Lname";
	//Used in MU_TC_25_1, MU_TC_25_2
	public static String phoneLogAddMessage = "Add Test Phone Log Message";
	//Used in MU_TC_25_2, MU_TC_25_3, MU_TC_25_4, MU_TC_25_5, MU_TC_28_3
	public static String phoneLogEditMessage = "Phone Log message after editing";
	//Used in MU_TC_25_6, MU_TC_49_6
	public static String phoneLogDeleteMessage = "Phone Log for Deletion";
	//Used in MU_TC_12_1
	public static String jobTitleCurrent = "Cricketer" +  RandomStringUtils.randomAlphanumeric(6).toString() ;
	public static String jobCodeCurrent = "27" +  RandomStringUtils.randomNumeric(2).toString();
	//Used in MU_TC_12_2
	public static String jobTitleProposed = "God of Cricket" ;
	public static String jobCityProposed = "Lords Cricket Ground" ;
	//Used in MU_TC_13_1
	public static String countryNew = "India";
	public static String countryRelative = "Pakistan";
	//Used in MU_TC_13_1, MU_TC_13_2
	public static String cityNew = "City" +  RandomStringUtils.randomAlphanumeric(6).toString();
	public static String cityRelative = "City" +  RandomStringUtils.randomAlphanumeric(6).toString();
	public static String streetNumber = "2611";
	public static String streetNumberRelative = "911";
	public static String cityEditted = "City" +  RandomStringUtils.randomAlphanumeric(6).toString();
	//Used in MU_TC_13_5
	public static String employer = "BCCI" +  RandomStringUtils.randomAlphanumeric(6).toString();
	public static String employerRelative = "ICC" +  RandomStringUtils.randomAlphanumeric(6).toString();
	//Used in MU_TC_13_5, MU_TC_13_6
	public static String occupation = "Coach" +  RandomStringUtils.randomAlphanumeric(6).toString();
	public static String occupationRelative = "Chief" +  RandomStringUtils.randomAlphanumeric(6).toString();
	public static String jobNew = "President" +  RandomStringUtils.randomAlphanumeric(6).toString();
	//Used in MU_TC_13_3, MU_TC_13_4
	public static String schoolName = "Old" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String schoolNameRelative = "School" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String schoolNameNew = "New" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String universityName = "Uni" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String universityNameRelative = "University" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String fieldOfStudy = "Field" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String fieldOfStudyRelative = "Books" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String degree = "Degree" + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String degreeRelative = "Third Degree" + RandomStringUtils.randomAlphanumeric(6).toString();

	//Used in MU_TC_13_3
	public static String schoolType = "Others";
	public static String schoolTypeRelative = "Undergraduate";
	//Used in MU_TC_13_7,  MU_TC_13_8
	static LocalDateTime localDate = LocalDateTime.now();
	public static String wifeFirstName = "Katrina" + RandomStringUtils.randomAlphanumeric(6).toString();;
	//Used in MU_TC_13_8
	public static String marriagePlace = "Nalasopara";
	//Used in MU_TC_13_7
	public static String wifeLastName = "Kaif";
	public static String relationMarriage = "Wife";
	//Used in MU_TC_8
	public static String cityForPersonalInfo = "City of Stars";
	public static String country  = "India";
	public static String numberForPersonalInfo = "100";
	public static String numberForPersonalInfoRelative = "555";
	//Used in MU_TC_6_1
	public static String relation = "Child";
	//Used in MU_TC_72_0
	public static String day = "8";
	public static String month = "Feb";
	public static String year = "1997";
	public static String dayRelative = "11";
	public static String monthRelative = "Sep";
	public static String yearRelative = "1995";
	public static String historyDateFNP = "Feb 8 1997";
	public static String historyDateRelativeFNP = "Sep 11 1995";
	//Used in MU_TC_37
	public static String formName = "I-485";
	public static String serviceCenter = "Vermont";
	public static String basisForBilling = "VT025";
	//Used in MU_TC_14_1, MU_TC_14_2
	public static String arrivalAdmissionNumber = "566356" + RandomStringUtils.randomNumeric(6).toString();
	//Used in MU_TC_14_3
	public static String firstNameForPassport = "James";
	public static String lastNameForPassport = "Anderson";
	public static String birthDateForPassport = "08";
	public static String birthMonthForPassport = "02";
	public static String birthYearForPassport = "1997";
	public static String passportNumberForPassport = "9867543210";
	public static String countryForPassport = "IND";
	
	public static String docChecklistName = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameExtra = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameExtra3 = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameExtra4 = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameFirm = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameCorp = "Document Checklist " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameForFNP = "Document Checklist " + RandomStringUtils.randomNumeric(6).toString();
	public static String docChecklistDescription = "Document Checklist Description " + RandomStringUtils.randomAlphanumeric(6).toString();
	public static String docChecklistNameNew = "Edited Document Checklist " + localDate;
	public static String docChecklistDescriptionNew = "EditedDocumentChecklistDescription" + RandomStringUtils.randomAlphanumeric(100).toString();
	
	public static String filePath = "\\src\\main\\resources\\TestFileForUpload.txt";
	public static String filePathFNP = "\\src\\main\\resources\\TestFileForFNPUpload.txt";
	public static String fileName = "TestFileForUpload.txt";
	public static String fileNameWithoutExtensionFNP = "TestFileForFNPUpload";
	public static String fileNameFNP = "TestFileForFNPUpload.txt";
	public static String fileNameWithoutExtension = "TestFileForUpload";
	public static String fileNameNew = "TestFileForUpload" + localDate;
	public static String fileDescription = "Document Description " + localDate;
	public static String folderName = "Approvals";
	public static String filePathHRP = "\\src\\main\\resources\\TestFileForHRPUpload.txt";
	public static String fileNameWithoutExtensionHRP = "TestFileForHRPUpload";
	public static String fileNameHRP = "TestFileForHRPUpload.txt";

	//Used in 47_1
	public static String reminderCaseStepName = "Case Step Name Reminder Testing";
	//Used in 47_0
	public static List<String> QA_ClientAccessCaseSteps = null;
	//MU_TC_10_1
	public static String nationality = "India";
	//MU_TC_10_1
	public static String firstName = "Likitha";
	//MU_TC_10_1
	public static String lastName = "Krishna";
	//MU_TC_10_1
	public static String passportNumber = "1234";
	//MU_TC_10_1
	public static String QA_A_PassportExpiresOnDateAddnew = "1/25/2023";
	//MU_TC_10_2
	public static String edittedFirstName = "Lalitha";
	//MU_TC_10_2
	public static String issuePlace = "Namma Chennai";
	//MU_TC_10_2
	public static String issuingCountry = "India";
	//MU_TC_10_2_1
	public static String QA_A_SecondPassPortNumber = "5678";
	//MU_TC_10_2_1
	public static String secondPassportFirstName = "Kesaria";
	//MU_TC_10_2_1
	public static String secondPassportLastName = "Jaswant";

	//MU_TC_35_3
	public static String chargableCountry = "India";
	
	public static long startTime=0;
	public static long endTime=0;
	
	public static String emailMessage = "Email Message";
	public static String replyMessage = "Reply Message";
	
	public static String accessCodeEmailSubject = "Access Code Email " + localDate;
	
	public static String caseEmailSubject = "Case Email Subject " + localDate;
	public static String clientEmailSubject = "Client Email Subject " + localDate;
	public static String clientContentEmailSubject = "Client Content Email Subject " + localDate;
	public static String corpContentEmailSubject = "Corporation Content Email Subject " + localDate;
	public static String caseContentEmailSubject = "Case Content Email Subject " + localDate;
	public static String clientAttachmentEmailSubject = "Client Attachment Email Subject " + localDate;
	public static String corpAttachmentEmailSubject = "Corp Attachment Email Subject " + localDate;
	public static String caseAttachmentEmailSubject = "Case Attachment Email Subject " + localDate;
	public static String emailSubjectWithoutAccess = "Email Subject without access" + localDate;
	
	//MU_TC_22_1
    public static String letterMSWordTemplateName = "Demo Letter for Automation";
    //MU_TC_22_3, MU_TC_22_4
    public static String edittedletterMSWordTemplateName = "Editted demo Letter for Automation";
    //MU_TC_22_5
    public static String letterTemplateFilePath = "\\src\\main\\resources\\Demo for Aspire.docx";
    //MU_TC_22_5
    public static String localLetterMSWordTemplateName = "Local demo Letter for Automation";
    //MU_TC_22_7
    public static String edittedLocalLetterMSWordTemplateName = "Editted Local demo Letter for Automation";
    //MU_TC_38
    public static String districtOffice = "Casper, WY";
    //MU_TC_43_1
    public static String formsFrom = "All forms list";
    //MU_TC_43_1
    public static String formGroup = "Asylum";
    //MU_TC_43_1
    public static String caseFormName = "AR-11";
    //MU_TC_43_2
    public static String middleName = "Employee";
    //MU_TC_43_2
    public static String countyOfCitizenship = "United States";
    //MU_TC_43_2
    public static String streetName = "Houston Street";
    //MU_TC_43_2
    public static String cityName = "New York";
    //MU_TC_43_6
    public static String messageBodyForFormsEmail = "Sending mail to the client";
    //MU_TC_43_6
    public static String subjectForEmailForm = "Subject for the email";
    
    public static String sendEmailID = "sendEmail" + RandomStringUtils.randomAlphanumeric(6).toString() + "@check.com" ;
    public static String sendSubjectContent = "send email " + RandomStringUtils.randomAlphanumeric(6).toString();
    public static String sendSubjectAttachment = "attachment email " + RandomStringUtils.randomAlphanumeric(6).toString();
    public static String sendMessage = "Compose Email" ;
    public static String sendSubjectAttachmentCase = "Case attachment " + RandomStringUtils.randomAlphanumeric(6).toString();
    
    public static String newsTitleClient = "Client News " + localDate;
    public static String newsDescriptionClient = "Client News Description " + localDate;
    public static String policyTitleClient = "Client Policy " + localDate;
    public static String policyDescriptionClient = "Client Policy Description " + localDate;
    public static String policyTitleBoth = "Both Policy " + localDate;
    public static String policyDescriptionBoth = "Both Policy Description " + localDate;
    public static String eConsentTitle = "e-Consent Titile " + localDate;
    public static String eConsentDescription = "e-Consent Description " + localDate;
    public static String policyTitleCorp = "Corp Policy " + localDate;
    public static String policyDescriptionCorp = "Corp Policy Description " + localDate; 
    public static String newsTitleCorp = "Corp News " + localDate;
    public static String newsDescriptionCorp = "Corp News Description " + localDate;
    public static String newsTitleBoth = "Both News " + localDate;
    public static String newsDescriptionBoth = "Both News Description " + localDate; 
    	 

    
    //MU_TC_38_0
    public static String formNameForAddTestcase = "N-600";
    //MU_TC_23_2
    public static String  questionnaireName = "Automation-I-941: Application for Entrepreneur Parole"; 
    
    //MU_TC_43_6
    public static String LCANumber = RandomStringUtils.randomNumeric(6).toString();
    public static String validFrom = "12/01/2019";
    public static String validFromFNP = "Dec 1 2019";
    public static String validThru = "12/31/2020";
    public static String validThruFNP = "Dec 31 2020";
    public static String totalCount = "5";
    public static String jobTitle = "Accountant";
    public static String address = "469, Pantheon Road, Egmore";
    public static String LCAcity = "Chennai";
    public static String LCAcountry = "India";
    public static String state = "Tamil Nadu";
    public static String stateRelative = "Karachi";
    public static String zip = "600008";
    public static String wages = "20000";
    public static String agent = "INSZoom";
    public static String trackNumber = "98765";
    public static String publishedYear = "2019";
    public static String LCAeffectiveOn = "01/01/2019";
    public static String LCAeffectiveOnFNP = "Jan 1 2019";
    public static String LCAeffectiveTill = "01/31/2019";
    public static String LCAeffectiveTillFNP = "Jan 31 2019";


    //MU_TC_117
    public static String swaCaseNumber = "1234";
    public static String swaFiledDate = "12/11/2023";
    public static String swaPriorityDate = "12/25/2023";
    public static String dolRecievedDate = "11/15/2023";
    public static String swaFiledDateFNP = "Dec 11 2023";
    public static String swaPriorityDateFNP = "Dec 25 2023";
    public static String dolRecievedDateFNP = "Nov 15 2023";
    public static String dolCaseNumber = "9876";
    public static String noticeSentDate = "1/1/2024";
    public static String noticeReceivedDate = "1/16/2024";
    public static String noticeSentDateFNP = "Jan 1 2024";
    public static String noticeReceivedDateFNP = "Jan 16 2024";
    public static String backlogCaseNumber= "007";
    
    public static String shippmentMethod = "Blue Dart";
    public static String shippmentReason = "1. Other";
    public static String trackingNumber = "7823478392";
    public static String visaType = "Automation Scripting";
    
    public static String validToFNP = "Jan 25 2023";
    
    public static String notesDetailsTextRelative = "Test data for note details relative";

    public static String visaPriority = "Employment-1st";

    //FNP_TC_49
    public static String clientUserInstruction = "Client User Instruction description" + localDate;
    //FNP_TC_49
    public static String clientUserInstructionTitle = "Client User Instruction title " + localDate;
    
    //MU_TC_82_2
  	public static String FNPfiledOnDate = "Jul 24 2020";
  	//MU_TC_82_2
  	public static String FNPnoticeDate = "Dec 11 2019"; 
  	
  	//MU_TC_10_2_1
  	public static String QA_A_PassportExpiresOnDate = "7/24/1997"; 
  	public static String QA_A_PassportExpiresOnDateFNP = "Jul 24 1997";
  	public static String QA_A_ExpiredPassportIssueDateFNP = "Jun 12 1996";
  	public static String QA_A_ExpiredPassportIssueDate = "6/12/1996";
  	
  	public static String phoneNumber1 = RandomStringUtils.randomNumeric(6).toString();
  	public static String phoneNumber2 = RandomStringUtils.randomNumeric(6).toString();
  	public static String phoneNumber3 = RandomStringUtils.randomNumeric(6).toString();
  	public static String twitter = RandomStringUtils.randomNumeric(6).toString() + ".com";
  	public static String skype = RandomStringUtils.randomNumeric(6).toString() + ".com";
  	public static String facebook = RandomStringUtils.randomNumeric(6).toString() + ".com";
  	public static String linkedIn = RandomStringUtils.randomNumeric(6).toString() + ".com";
  	public static String maritalStatus = "Married";
  	
  //Used in MU_TC_35_1
  	public static String receiptNoticeDate = "12/11/2019"; 
  	
  	public static String genericStartDate = "12/11/2019";
  	public static String genericEndDate = "1/16/2024";
  	public static String genericStartDate_FNP = "Dec 11 2019";
  	public static String genericEndDate_FNP = "Jan 16 2024";
  	
  	public static String appointmentActivitySubjectRelative = "Regarding Appointment/Activity Details for Relative";
  	
  	public static String stepName_workInProgress = "Input receipts into INSZoom database and notify client";
  	public static String stepName_notApplicable = "Finalize and file package to USCIS";
  	public static String stepName_completed = "Prepare and send out forms and docs for signature";
  	public static String stepName_notStarted =  "Send questionnaires and document checklist to clients";
  	
  	public static String dateSentToGovtAgent = "7/24/2020";
  	
  	public static String QA_ALoginReceiptSendDate_FNP = "Jul 24 2020";
  	public static String validFrom_FNP = "Dec 25 2018";  
  	public static String validTo_FNP = "Dec 24 2022"; 
  	
  	public static String appointmentActivityComments = "Automation test Appointment/Activity"; 
	public static String documentExpirationTemplateExpired = "ExpirationDateTemplate(Expired)"; 
	
	  
	public static String filePathImage = "\\src\\main\\resources\\Sample.jpg"; 
	
	public static String expirationIssueDate = "8/26/2019";
	public static String expirationDate = "10/01/2019";
	public static String expirationIssueDate_FNP = "Aug 26 2019";
	public static String expirationDate_FNP = "Oct 1 2019";
	
	
	public static String notesDetailsTextCorp = "Test data for note details corp" ; 
	
	public static String defaultCustomFieldName = "Custom field for default" ; 
	public static String defaultCustomData = "Default custom data" ;
	public static String crimeInfoData = "Crime Info Data" ;
	
	public static String corpCustomFieldName = "Custom field for corp" ; 
	public static String corpCustomData = "Corp custom data" ; 
	
	public static String caseCustomFieldName = "Custom field for case" ; 
	public static String caseCustomData = "Case custom data" ; 
	
	public static String clientCustomFieldName = "Custom field for client" ; 
	public static String clientCustomData = "Client custom data" ; 
	
	public static String clientDefaultCustomFieldName = "Employee Expected Salary" ; 
	
	public static String signingPersonContactNumber = "99405987337" ;
	public static String signingPersonTitle = "Developer" ;
	public static String administrativePersonContactNumber = "64782378243" ;
	public static String administrativePersonTitle = "Regional Manager" ;
	public static String administrativePersonEmailId = "admin@inszoom.com" ;
	public static String administrativePersonFirstName = "Sharmila" ;
	public static String administrativePersonLastName = "Singh" ;
	 
		public static String residenceCity = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String countryForPersonalInfo = "Kazakhstan";
	public static String apartment = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String street1 = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String street2 = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String pinCode = RandomStringUtils.randomNumeric(6).toString();
	public static String stateForPersonalInfo = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String mobile = RandomStringUtils.randomNumeric(6).toString();
	public static String telephone  = RandomStringUtils.randomNumeric(6).toString();
	
	public static String workCity = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String countryForWork = "Kazakhstan";
	public static String apartmentForWork = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String workAddressStreet1 = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String workAddressStreet2 = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String workAddressPinCode = RandomStringUtils.randomNumeric(6).toString();
	public static String workAddressState = RandomStringUtils.randomAlphanumeric(6).toString();
	public static String workMobile = RandomStringUtils.randomNumeric(6).toString();
	public static String workTelephone  = RandomStringUtils.randomNumeric(6).toString(); 
	 
    public static String businessType = "Medicine";
    public static String yearEstablished = "1977";
    public static String placeOfIncorporation = "Bombay";
    public static String numberOfEmployees	= "4";
    public static String doingBusinessAs = "Start up";
    public static String grossAnnualIncome	= "40000";
    public static String netAnnualIncome = "50000";
    public static String taxID = "87681";
    public static String stateTax= "09087";
    public static String naicsCode= "1234";
    public static String businessDescription= "Family Business";
    public static String primaryFirmContact = "";
    
    public static String employeeID = RandomStringUtils.randomAlphanumeric(6).toString();
    public static String department = "Research";
    
    public static String corporation= "";
    public static String hireDate = "01/07/2019";
    public static String hireDateHRP = "Jan 7 2019";
    public static String Designation = "Senior Analysis";
    public static String annualSalary = "900,000.00";
    public static String responsibilities = "Analysis data";
    public static String supervisorName = "";
    public static String rehireDate= "06/02/2019";
    public static String rehireDateHRP = "Jun 2 2019";
    public static String terminationDate = "09/21/2023";
    public static String terminationDateHRP = "Sep 21 2023";
    public static String currencyType = "Dollar";
    
	 public static String supervisorTitle = "Supervisor Head";
	 public static String noOfSubOrdinates = "45" ;
	 public static String proposedJobTitle = "Engineering head" ; 
	 public static String jobDuties = "Manage engineering team";
	 public static String jobType = "Full Time";
	 public static String socCode = "456234";
	 public static String noOfHoursPerWeek = "48";
	 public static String qualification = "B.Tech";
	 public static String majorFieldOfStudy = "Computer Science";
	 public static String experienceInJobOfferedYears = "3";
	 public static String experienceInJobOfferedMonths = "8";
	 public static String experienceInRelatedOccupationYears = "1";
	 public static String experienceInRelatedOccupationMonths = "11";
	 public static String relatedOccupation = "hacking";
	 
	 public static String gender = "Female" ; 
	 public static String placeOfBirth = "Guntur";
	 public static String citizenship = "Indian";
	 public static String dob = "Feb 8 1997";
	 
	 public static String clientEmailID = "client@inszoom.com";
	 
	 public static String fileNumber = RandomStringUtils.randomNumeric(6).toString();
	 public static String prospectiveEmployeeId = RandomStringUtils.randomNumeric(6).toString();
	 
		public static String HeadQuarterName = "SakshamHQ";
		
	    public static List<String> corporationsUnderHeadQuarter = new ArrayList<String>();	//Added By Saksham Kapoor on 25/04/19
	    
	    public static HashMap<String,List<String>> corpToClientMap = new HashMap<>();	//Added By Saksham Kapoor on 4/03/19
	    
	    public static Multimap<String,String> corpUserToClientMap = HashMultimap.create();	//Added By Kuchiand Shashank on 4/03/19
	    
	    public static List<String> corpUsersUnderCorporation = new ArrayList<String>();	//Added By Kuchiand Shashank on 4/03/19
	    
	    public static List<String> corporationBranchesOfHQ = new ArrayList<String>();	//Added By Kuchiand Shashank on 4/03/19
	    
	    public static String corporationNameWithHQ = "Corp1WithHQ" ;	//Added By Kuchiand Shashank on 4/03/19
	    
	    public static List<String> corporationUsersUnderCorportion = new ArrayList<String>();	//Added By Nitisha Sinha on 4/03/19

//		************** Document Management ********************
	    
	    
	    public static String clientFile5Path = "\\src\\main\\resources\\AdditionalClientDocumentFNPNew.txt";
	    public static String clientFile5Initial = "AdditionalClientDocumentFNPNew.txt";
	    public static String clientFile5NameWithoutExtension= "AdditionalClientDocumentFNPNew";
	    
	    public static String clientFile6Path = "\\src\\main\\resources\\AdditionalClientDocumentHRPNew.txt";
	    public static String clientFile6Initial = "AdditionalClientDocumentHRPNew.txt";
	    public static String clientFile6NameWithoutExtension= "AdditionalClientDocumentHRPNew";
	    
	    public static String corpFile5Path = "\\src\\main\\resources\\AdditionalCorpDocumentNew.txt";
	    public static String corpFile5Initial = "AdditionalCorpDocumentNew.txt";
	    public static String corpFile5NameWithoutExtension= "AdditionalCorpDocumentNew";
	    
	    public static String clientFile1Path = "\\src\\main\\resources\\ClientDocumentManagement.txt";
	    public static String clientFile1Initial = "ClientDocumentManagement.txt";
	    public static String caseFile1Path = "\\src\\main\\resources\\CaseDocumentManagement.txt";
	    public static String caseFile1Initial = "CaseDocumentManagement.txt";
	    
	    public static String caseFile5Path = "\\src\\main\\resources\\CaseCheckList1DocumentNew.txt";
	    public static String caseFile5Initial = "CaseCheckList1DocumentNew.txt";
	    public static String caseFile5NameWithoutExtension= "CaseCheckList1DocumentNew";
	    
	    public static String caseFile6Path = "\\src\\main\\resources\\CaseCheckList2DocumentNew.txt";
	    public static String caseFile6Initial = "CaseCheckList2DocumentNew.txt";
	    public static String caseFile6NameWithoutExtension= "CaseCheckList2DocumentNew";
	    
		//public static String clientFile1Name = "ClientDocumentManagement.txt";
		//public static String clientFile1NameWithoutExtension = "ClientDocumentManagement";
		public static String corpFile1Path = "\\src\\main\\resources\\CorpDocumentManagement.txt";
		public static String corpFile1Initial = "CorpDocumentManagement.txt";
		//public static String corpFile1Name = "CorpDocumentManagement.txt";
		//public static String corpFile1NameWithoutExtension = "CorpDocumentManagement";
		public static String deleteFilePath = "\\src\\main\\resources\\FileForDelete.txt";
		public static String deleteFileName = "FileForDelete.txt";
		public static List<String> listOfTags = Arrays.asList("Approvals", "ASC Appointment Notice", "Job Description", "Denial", "Application", "Education");
		public static List<String> listOfQuickActions = Arrays.asList("Accept", "Pending Review", "Need Clarification", "Print", "Activity", "Delete");
		public static List<String> listOfQuickActionsClient = Arrays.asList("Accept", "Pending Review", "Need Clarification", "Print", "Email", "Activity", "Delete");
		public static String corpFile2Path = "\\src\\main\\resources\\AdditionalCorpDocument.txt";
		public static String corpFile3Path = "\\src\\main\\resources\\CorpDocumentExtra.txt";
		public static String corpFile4Path = "\\src\\main\\resources\\ExtraCorpDocument.txt";
		public static String corpFile2Name = "AdditionalCorpDocument.txt";
		public static String corpFile3Name = "CorpDocumentExtra.txt";
		public static String corpFile4Name = "ExtraCorpDocument.txt";
		public static String corpFile2NameWithoutExtension = "AdditionalCorpDocument";
		public static String corpFile3NameWithoutExtension = "CorpDocumentExtra";
		public static String corpFile4NameWithoutExtension = "ExtraCorpDocument";
		public static String clientFile2Path = "\\src\\main\\resources\\AdditionalClientDocument.txt";
		public static String clientFile3Path = "\\src\\main\\resources\\ClientDocumentExtra.txt";
		public static String clientFile4Path = "\\src\\main\\resources\\ExtraClientDocument.txt";
		public static String clientFile2Name = "AdditionalClientDocument.txt";
		public static String clientFile3Name = "ClientDocumentExtra.txt";
		public static String clientFile4Name = "ExtraClientDocument.txt";
		public static String clientFile2NameWithoutExtension = "AdditionalClientDocument";
		public static String clientFile3NameWithoutExtension = "ClientDocumentExtra";
		public static String clientFile4NameWithoutExtension = "ExtraClientDocument";
		public static String caseFile2Path = "\\src\\main\\resources\\AdditionalCaseDocument.txt";
		public static String caseFile3Path = "\\src\\main\\resources\\CaseDocumentExtra.txt";
		public static String caseFile4Path = "\\src\\main\\resources\\ExtraCaseDocument.txt";
		public static String caseFile2Name = "AdditionalCaseDocument.txt";
		public static String caseFile3Name = "CaseDocumentExtra.txt";
		public static String caseFile4Name = "ExtraCaseDocument.txt";
		public static String caseFile2NameWithoutExtension = "AdditionalCaseDocument";
		public static String caseFile3NameWithoutExtension = "CaseDocumentExtra";
		public static String caseFile4NameWithoutExtension = "ExtraCaseDocument";
		public static String caseRenamedFile = "Renamed" + RandomStringUtils.randomAlphanumeric(3).toString();
		
		public static String tagFolderNameCorp = "Tag " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String tagFolderNameClient = "Tag " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String tagFolderNameCase = "Tag " + RandomStringUtils.randomAlphanumeric(3).toString();
	    
//	    *******************************************************
		
		public static String newLetterTemplateName = "Letter Template" +  RandomStringUtils.randomAlphanumeric(6).toString() ;
	    public static String docName = "Demo for Aspire.docx"; 
	    public static String docName2 = "New Microsoft Word Document";
	    public static String corpEmailID = "corp@inszoom.com" ; 
	    
	    public static String letterHTMLTemplateName = "My HTML Letter Template " + RandomStringUtils.randomNumeric(6).toString();
	    public static String letterHTMLTemplateDescription = "My HTML Letter Template Description" + RandomStringUtils.randomNumeric(6).toString();
	    public static String accessDate = "1/25/2030";
	    public static String accessDateVerification = "Jan 25 2030";
	    public static String letterHTMLName = "Local letter name " +  RandomStringUtils.randomNumeric(6).toString(); 
	    public static String letterHTMLDescription = "Local letter description " +  RandomStringUtils.randomNumeric(6).toString();
	    public static String editLetterHTMLName = "Edit local letter name " +  RandomStringUtils.randomNumeric(6).toString(); 
	    public static String editLetterHTMLDescription = "Edit local letter description " +  RandomStringUtils.randomNumeric(6).toString();
	
        //	    ***********************Package For Filing********************************
		
		public static String formNamePackageForFiling = "I-589_Instr";
		
		public static String documentNamePackageForFiling = "TestFileForUpload";
		
		public static String packageName = "Automation ";
		
		public static String blankPageName = "BlankPage";

		public static List<String> formsUnderCase = new ArrayList<>();
		
		public static String filePathForPFF = "\\src\\main\\resources\\TestFileForUpload.pdf";

		public static String fileNameForPFF = "TestFileForUpload.pdf";
		
		//**********************Access Rights************************************
		
		public static String superUserCorporationName = "ASCorp";
		
		public static String superUserClientName = "Sergio";
		
		//********************Contracts***************
		
		public static String newCaseContractTemplateName = "Case new " + RandomStringUtils.randomNumeric(2).toString();
	    public static String newClientContractTemplateName = "Client new " + RandomStringUtils.randomNumeric(2).toString();
	    public static String newCorpContractTemplateName = "Corp new" + RandomStringUtils.randomNumeric(2).toString();
	    public static String newContractTemplateDescription = "Description Verify Add Template funtionality";
        public static String renameCaseContractTemplateName = "Rename Case " +  RandomStringUtils.randomNumeric(2).toString();
        public static String renameClientContractTemplateName = "Rename Client " +  RandomStringUtils.randomNumeric(2).toString();
    	public static String renameCorpContractTemplateName = "Rename Corp " +  RandomStringUtils.randomNumeric(2).toString();
		
		public static String contractsTemplateNameAddedInKB = "KB Template " +  RandomStringUtils.randomAlphanumeric(6).toString() ;
	    public static String contractsTemplateDescriptionAddedInKB = "Contracts Template Description " +  RandomStringUtils.randomAlphanumeric(6).toString() ;
	    public static String contractFilePath = "\\src\\main\\resources\\Demo for Aspire.docx";
	    public static String contractFilePathForUploadSignedCopy = "\\src\\main\\resources\\New Microsoft Word Document.docx";
	    public static String contractsKBTemplate = "Static KB Template";
		
		public static String emailSubjectForCaseContracts = "Email Subject for case contracts " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String emailSubjectForClientContracts = "Email Subject for client contracts " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String emailSubjectForCorpContracts = "Email Subject for corp contracts " + RandomStringUtils.randomAlphanumeric(3).toString();
		
		public static String emailSubjectForSignedCaseContracts = "Email Subject for signed case contracts " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String emailSubjectForSignedClientContracts = "Email Subject for signed client contracts " + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String emailSubjectForSignedCorpContracts = "Email Subject for signed corp contracts " + RandomStringUtils.randomAlphanumeric(3).toString();

		//********************CMP Search***************
		
		public static String cmpSearchCorporationName = "CMPCorp" + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchsigningPersonFirstName = "CMPSigning First" + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchsigningPersonLastName = "CMPSigning Last" + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchsigningPersonEmailID = "cmpsigning" + RandomStringUtils.randomAlphanumeric(3).toString() + "@inszoom.com";
		public static String cmpSearchEdittedCorporationName = "EdittedCMPCorp" + RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchIndividualClientFirstName = "ClientF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchIndividualClientLastName = "ClientL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchClientEmail = "client" + RandomStringUtils.randomAlphanumeric(3).toString() + "@inszoom.com";
		public static String cmpSearchClientEmailID = "client" + RandomStringUtils.randomAlphanumeric(3).toString() + "@inszoom.com";
		
		public static String cmpSearchClientFileNumber = RandomStringUtils.randomNumeric(5);
		public static String cmpSearchEdittedClientFileNumber = "1" + RandomStringUtils.randomNumeric(5);
		public static String cmpSearchIndividualClientAlienNumber = RandomStringUtils.randomNumeric(8);
		public static String cmpSearchCorporateClientAlienNumber = RandomStringUtils.randomNumeric(8);
		public static String clientLoginID = null ;
		public static String cmpSearchCorporationClientFirstName = "ClientF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchCorporationClientLastName = "ClientL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchCorporationNameForCorpClient = "Corp_for_CMP";
		public static String cmpSearchClientFirstNameForCase = "ClientF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchClientLastNameForCase = "ClientL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String caseFileNumber = RandomStringUtils.randomNumeric(5);
		public static String caseDescription = RandomStringUtils.randomAlphabetic(6);
		public static String cmpToDoCorpQuestionnaire = "Automation-I-941: Application for Entrepreneur Parole";
		public static String cmpProspectiveClientFirstName = "ProspectiveCF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveClientLastName = "ProspectiveCL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveClientFileNumber = RandomStringUtils.randomNumeric(5);
		public static String cmpProspectiveClientConsultationDate = "08/27/2020";
		public static String cmpProspectiveClientFirstNameForDelete = "ProspectiveCF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveClientLastNameForDelete = "ProspectiveCL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		
		public static String cmpProspectiveCorpName = "ProspectiveCorp"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveCorpFileNumber = RandomStringUtils.randomNumeric(5);
		public static String cmpProspectiveCorpContactPersonFirstName = "ContactF" +  RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveCorpContactPersonLastName = "ContactL" +  RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpProspectiveCorpEmailID = "proscorp" + RandomStringUtils.randomAlphanumeric(3).toString() + "@inszoom.com";
		public static String cmpProspectiveCorpNameForDelete = "ProspectiveCorp"+ RandomStringUtils.randomAlphanumeric(3).toString();
		
		public static String cmpSearchCorpUserFirstName = "CorpUserF"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchCorpUserLastName = "CorpUserL"+ RandomStringUtils.randomAlphanumeric(3).toString();
		public static String cmpSearchCorpUserEmail = "corpuser" + RandomStringUtils.randomAlphanumeric(3).toString() + "@inszoom.com";
		public static String cmpSearchCorpUserRole = "Admin";
		public static String cmpSearchCorpUserEditedEmail = "corpuser" + RandomStringUtils.randomAlphanumeric(3).toString() + "@mitratech.com";
		
			//********************Receipt Management***************

	    public static String mainReceipt = "1234";
	    public static String secondaryReceipt = "4321";
	    public static String rmDocument = "Receipt Document.docx";
	    public static String rmDocumentWithoutExtension = "Receipt Document";
	    
	    public static String receiptNumber = "";
	    public static String receiptNumber1 = "12345";
	    public static String receiptNumber2 = "54321";
	    public static String receiptNumber3 = RandomStringUtils.randomNumeric(6).toString();
	    public static String receiptType2 = "O";
	    
	    public static String emailReceiptNumber = "";
	    public static String receiptNumberForDataValidation = "";
	    public static String receiptType1 = "Other";
	    public static String receiptDate1 = "08/13/2020";
	    public static String receiptDate1Verification = "Aug 13 2020";
	    public static String receiptStatus1 = "Approved";
	    
	    public static String receiptNoticeDetails = "Edit Details";
	    public static String rmEmailSubject = "RM Email Subject";
	    
	    public static String rmFilePath = "\\src\\main\\resources\\Receipt Document.docx";
	    
	    
	  //********************Questionnaire***************
	    
	    public static String KBClientQuestionnaireName = "Client Questionnaire";
	    public static String KBCorporationQuestionnaireName = "Corporation Questionnaire";
	    
	    public static String zoomDefinedClientQuestionnaireName = "I-352: Immigration Bond";
	   // public static String firmDefinedClientQuestionnaireName = "I-352: Immigration Bond";
	    
	    public static String zoomDefinedCorporationQuestionnaireName = "I-907: Request for Premium Processing Service Questionnaire (From Corporation)";
	   // public static String firmDefinedCorporationQuestionnaireName = "I-907: Request for Premium Processing Service Questionnaire (From Corporation)";

	    public static String questionnaireClientFirstName = "Questionnaire Client";
	    public static String questionnaireClientFullName = "Questionnaire Client 1";
	    
	    public static String questionnaireCorporationName = "Questionnaire Corporation";
	    public static String questionnaireCorporationSigningPersonName = "Questionnaire Kuchinad";
	    
	    public static String questCaseId = "KBGRIT02480-1";
	    
	    public static String toDoTitleClient = "To do";
	    public static String toDoTitleCorp = "To do";
	    public static String toDoTitleCase = "To do";
	    
	    public static String CRTemplate = "Automation Case Request Template";
	    
	    public static HashSet<String> questionnaireUnderCRTemplate=new HashSet();
	    
	    //public static String KBQuestionnaireName = "Questionnaire";
	    
	    public static String questPetitionTemplate = "Questionnaire Template";
	    
	    //public static String questCorporation = "Questionnaire Corporation";
	    
	    //public static String questClient = "Questionnaire Client";
	    
	    public static String questCaseForKBEvent = "KBGRIT02480-2";
	    
	    public static String eventEmailSubject = "Event Email Questionnaire";
	    
	    public static String questAttachQuestionnaireToPetition = "Adjustment of Status Checklist";
	    
	    public static String questAttachQuestionnaireToCorporation = "ARE-AEP, Application for Entry Permit";
	    
	    public static String questGrantAccessCorporationQuestionnaire = "Company Profile";
	    
	    public static String questCaseRequestTemplatePetition = "Advanced Parole Application (I-131)";
	    
	    public static String questCaseRequestTemplateDescription = "Case Request Template Description";
	    
	    public static String questCaseRequestTemplateName = "Questionnaire template";
	    
	    public static String questCorporationCaseRequestTemplate = "Questionnaire CR template";
	    
	    public static String questKBEventQuestionnaire = "I-590: Registration for Classification as a Refugee";
	    
	    public static String questCaseEventsQuestionnaire = "I-243: Application for Removal Questionnaire";
	    
	    public static String questPetitionAttachedQuestionnaire = "Intake questionnaire for Family Based";

	    //**********************Org Settings**************
	    
	    public static int fontSize = new Random().nextInt(5) + 6;
	    public static int[] invalidAttempts = {3, 5, 10};
	    public static int randomInvalidAttempt = invalidAttempts[new Random().nextInt(3)];
	    public static int accessPeriod = new Random().nextInt(24) + 1;
	    
	    public static HashMap<String, String> defaultEmailAccess = new HashMap<String, String>() {{
            put("Firm Only", "O");
            put("Corporation Only", "P");
            put("Client Only", "B");
            put("All", "X");
        }};
        
        public static String formAccessType = "";
        public static HashMap<String, String> formAccessTypes = new HashMap<String, String>() {{
            put("Edit Form - PDF", "Edit Form");
            put("Email as Questionnaire", "Email as Questionnaire");
            put("Print Form - PDF", "Print Form");
            put("As Attachment (read only)", "As Attachment (read Only)");
        }};
        
        public static String trackChanges = "";
        public static String enableEfiling = "";
        public static String formForSignature = "G-28";
        
        //Todos Module related 
        public String toDoQuickTaskInstruction = "Instruction for To-Do quick task";
        
        public String toDoName = "To Do Quick Task Test";
}
