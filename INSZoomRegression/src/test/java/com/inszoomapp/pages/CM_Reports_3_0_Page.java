package com.inszoomapp.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import ContentAutomation.GetDataFromExcelAndVerify;

import com.inszoomapp.globalVariables.globalVariables;
import com.support.files.ExcelToDataProvider;
import com.support.files.Log;
import com.support.files.RowCountColumnCount;
import com.support.files.Utils;

public class CM_Reports_3_0_Page extends LoadableComponent<CM_Reports_3_0_Page>
{
	
	private final WebDriver driver;
	private boolean isPageLoaded;
	
	@FindBy(xpath="//table[@class='body-table']/..")
	WebElement tbl_records;
	
	@FindBy(id="DEDateFilter")
	WebElement dropdown_datePickerExpirations;
	
	@FindBy(xpath="//li[contains(text(),'Next 180 Days')]")
	WebElement option_next180DaysExpiration;
	
	@FindBy(xpath = "(//div[@id='d52a63c0-cef8-5670-49bd-dea4527a6faa']//select[@class='dropDownList-select'])")
	WebElement dropdown_prospectsDate;
	
	@FindBy(xpath="(//li[contains(text(),'Next 60 Days')])[2]")
	WebElement option_next60DaysUpcomingCaseActivity;
	
	@FindBy(xpath="//div[contains(@style, 'display: block')]//li[contains(text(),'Next 60 Days')]")
	WebElement option_next60DaysUpcomingCaseActivityPL;
	
	@FindBy(id="CL2DateFilterUpcoming")
	WebElement dropdown_datePickerUpcomingCaseActivity;
	
	@FindBy(id="CL3DateFilter")
	WebElement dropdown_datePickerUpcomingCaseActivityPL;
	
	@FindBy(xpath="//li[contains(text(),'Last 180 Days')]")
	WebElement option_last180DaysPastCaseActivity;
	
	@FindBy(xpath="//div[@id='d5914ae2-1108-d12e-871a-a087a6426860']/div/select")
	WebElement dropdown_datePickerPERM;
	
	@FindBy(xpath="//div[contains(text(), 'PARALEGAL / CASE WORKER REPORTS')]/../../../../../following-sibling::section//div[@id='d5914ae2-1108-d12e-871a-a087a6426860']//select")
	WebElement dropdown_datePickerPERMPL;
	
	@FindBy(id="CL2DateFilter")
	WebElement dropdown_datePickerPastCaseActivity;
	
	@FindBy(xpath="//div[contains(text(), 'Export to Excel')]")
	WebElement btn_exportToExcel;
	
	@FindBy(id="IframeLoader")
	WebElement loader_reportsPage;
	
	@FindBy(xpath = "//div[@class='dots']")
    WebElement loader_Dots;
	
	@FindBy(xpath="//div[@id='2df986e8-58f6-0040-e078-532a5c8c57c8']//*[name()='g']/*[name()='path']")
	WebElement btn_greencardProcess;
	
	@FindBy(id="33c536ae-c1c5-ac5c-53b6-41438a609630")
	WebElement img_toggleArrow;
	
	@FindBy(xpath="//h4[contains(text(), 'HR / Corporation Portal Reports')]")
	WebElement tab_HRPortalReports;
	
	@FindBy(xpath="//h4[contains(text(), 'Paralegal / Case Worker Reports')]")
	WebElement tab_PLReports;
	
	@FindBy(xpath="(//*[name()='svg']/*[name()='g']/*[name()='path' and @fill='#E03615'])[2]")
	WebElement btn_undefined;
	
	@FindBy(xpath="//div[contains(text(), '21 Years Ageout')]")
	WebElement btn_21YearsAgeout;
	
	@FindBy(xpath="//div[@id='0ddafb90-1f6c-daf7-d6a8-d988569a073e']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_clientDocExpiration;
	
	@FindBy(xpath="//div[@id='affe61ef-3052-7b9d-d442-f2c86eb6c715']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_I797PL;
	
	@FindBy(xpath="//div[contains(text(), 'I-797')]")
	WebElement btn_clientDocExpiration;
	
	@FindBy(id="7531fa04-98da-f3c0-0fbd-43c4be478a52")
	WebElement btn_I797PL;
	
	@FindBy(xpath="//div[contains(text(), 'View By Case')]")
	WebElement tab_viewByCase;
	
	@FindBy(xpath="//div[contains(text(), 'View By Corporation')]")
	WebElement tab_viewByCorporation;
	
	@FindBy(xpath="//div[contains(text(), 'Work Summary')]")
	WebElement btn_workSummary;
	
	@FindBy(xpath="//div[contains(text(), 'View By Invoice')]")
	WebElement tab_viewByInvoice;
	
	@FindBy(xpath="//div[@id='dc4f339f-84c1-d324-8814-c5a1a8635749']//*[name() = 'path' and @fill='#008CC9']")
	WebElement btn_permFiled;
	
	@FindBy(xpath="//div[@id='dc4f339f-84c1-d324-8814-c5a1a8635749']//*[name()='text' and contains(text(), 'Filed')]")
	WebElement text_permFiled;
	
	@FindBy(xpath="//div[contains(text(), 'PARALEGAL / CASE WORKER REPORTS')]/../../../../../following-sibling::section/div[@id='dc4f339f-84c1-d324-8814-c5a1a8635749']//*[name()='text' and contains(text(), 'Filed')]")
	WebElement text_permFiledPL;
	
	@FindBy(xpath="//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='dc4f339f-84c1-d324-8814-c5a1a8635749']//*[name()='text' and contains(text(), 'Filed')]")
	WebElement text_permFiledHR;
	
	@FindBy(xpath="//div[@id='a016add8-b5c5-0723-1767-4405311d8b27']//*[name() = 'g' and contains(@clip-path, 'url')]/*[name()='g']/*[name()='text']")
	WebElement text_clientByCurrentImmigrationStatusHR;
	
	@FindBy(xpath="//div[@id='6cffd63e-b5af-a0f3-bdec-12c90d6f300a']//*[name() = 'g' and @class='data-label']//*[name() = 'text']")
	WebElement text_openCasesByType;
	
	@FindBy(xpath="//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='6cffd63e-b5af-a0f3-bdec-12c90d6f300a']//*[name() = 'g' and @class='data-label']//*[name() = 'text']")
	WebElement text_openCasesByTypeHR;
	
	@FindBy(xpath="//div[contains(text(), 'PARALEGAL / CASE WORKER REPORTS')]/../../../../../following-sibling::section//div[@id='6cffd63e-b5af-a0f3-bdec-12c90d6f300a']//*[name() = 'g' and @class='data-label']//*[name() = 'text']")
	WebElement text_openCasesByTypePL;

	@FindBy(xpath="//div[@id='63b141dd-af7a-e51d-a1c2-10daa92aff4f']//tr[@ro='0']//div[contains(@class, 'gridCellTemplate gridCellTextTemplate')]")
	WebElement text_clientDocExpirationHR;
	
	@FindBy(xpath="//div[@id='d3114e0d-8a30-a11e-41a5-5831cf0aa409']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_eligibleForFilingVisaHR;
	
	@FindBy(xpath="//div[@id='6f83d397-b978-70f6-d01a-1a0de2602916']//*[name() = 'text' and contains(text(), 'Canada')]")
	WebElement text_clientByCitizenshipHR;
	
	@FindBy(xpath="//div[@id='74bdb10c-8346-5806-4a7e-f37b0f1cdb8c']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_eligibleForFilingVisaPL;
	
	@FindBy(xpath="//div[@id='f90472ca-ad33-4344-f299-3f4d00fdecbe']//tr[@ro='0']//div[contains(@class, 'gridCellTemplate gridCellTextTemplate')]")
	WebElement text_caseExpirationHR;
	
	@FindBy(xpath="//div[contains(text(),'HR / Corporation Portal Reports')]/../../../../../following-sibling::div//div[contains(text(), 'I-797')]")
	WebElement btn_clientDocExpirationHR;
	
	@FindBy(xpath="//div[contains(text(),'HR / Corporation Portal Reports')]/../../../../../following-sibling::div//div[contains(text(), 'Case Expirations')]")
	WebElement btn_caseExpirationHR;
	
	@FindBy(id="f776233e-5513-8ebc-319b-232777df9026")
	WebElement btn_appointment;
	
	@FindBy(xpath="(//div[contains(text(), 'UPCOMING CASE ACTIVITY')])[2]")
	WebElement tab_upcomingCaseActivity;
	
	@FindBy(id="7ed236a3-ced3-6f98-e49b-eca96111ee96")
	WebElement tab_upcomingCaseActivityPL;
	
	@FindBy(xpath="//div[contains(text(), 'Invoice')]")
	WebElement btn_invoice;
	
	@FindBy(xpath="//div[contains(text(), 'Client Details')]")
	WebElement btn_clientDetails;
	
	@FindBy(css="path[name='Antarctica']")
	WebElement btn_Antarctica;
	
	@FindBy(xpath="//div[contains(text(), 'H-1B Registration')]")
	WebElement btn_H1BRegistration;
	
	@FindBy(xpath="//div[contains(text(), 'Non-Immigrant Visa')]")
	WebElement btn_NIV;
	
	@FindBy(xpath="//div[contains(text(), 'Immigrant Visa')]")
	WebElement btn_IV;
	
	@FindBy(xpath="//div[contains(text(), 'Login Statistics')]")
	WebElement btn_LoginStatistics;
	
	@FindBy(xpath="//div[contains(text(), 'Corporation Users')]")
	WebElement btn_CorpUser;
	
	@FindBy(xpath="//div[contains(text(), 'Citizenship')]")
	WebElement btn_Citizenship;
	
	@FindBy(xpath="//div[contains(text(), 'Case Request')]")
	WebElement btn_CaseRequest;
	
	@FindBy(xpath="//div[contains(text(), 'e-Consent') and @class='buttonControl-labelCell']")
	WebElement btn_EConsent;
	
	@FindBy(xpath="//div[contains(text(), 'PERM')]")
	WebElement btn_PERM;
	
	@FindBy(xpath="//div[contains(text(), 'LCA Report')]")
	WebElement btn_caseLCA;
	
	@FindBy(xpath="//div[contains(text(), 'J-1')]")
	WebElement btn_J1;
	
	@FindBy(xpath="//div[contains(text(), 'Priority Dates')]")
	WebElement btn_priorityDates;
	
	@FindBy(xpath="//div[contains(text(), 'Case Status')]")
	WebElement btn_caseStatus;
	
	@FindBy(xpath="//div[contains(text(), 'Life Cycle')]")
	WebElement btn_lifeCycle;
	
	@FindBy(id="3648031c-cde3-020f-f723-4a192e09b029")
	WebElement btn_eligibleForFilingVisa;
	
	@FindBy(xpath="//div[@id='fa595c8e-bf9a-5167-3ae0-aafe8bab521d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_eligibleForFilingVisa;
	
	@FindBy(xpath="//div[@id='10adf4e2-9425-1234-bbe2-bc7e835b1f59']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_21YearsAgeoutPL;
	
	@FindBy(xpath="//div[@id='e0a4ac9f-6fa8-1e2c-7eb0-c9e3e776a5ec']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_prospectsPL;
	
	@FindBy(id="4206265b-626f-37c2-7956-e5a1953a7ba2")
	WebElement btn_21YearsAgeoutPL;
	
	@FindBy(id="b7d9da57-faa5-88c0-0676-4296a08e0cb8")
	WebElement btn_prospectsPL;
	
	@FindBy(xpath="//div[contains(text(), 'Case Expiration')]")
	WebElement btn_caseExpiration;
	
	@FindBy(xpath="//div[@id='f90472ca-ad33-4344-f299-3f4d00fdecbe']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_caseExpiration;
	
	@FindBy(xpath="//div[@id='cec2d4ee-3871-b040-078d-f1e2dfce61c0']//div[@class='labelControlLabelCell']")
	WebElement text_appointmentsPL;
	
	@FindBy(xpath="//div[@id='424c0455-a8f0-76d7-943c-26fd2217d23d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_caseStepRemindersPL;
	
	@FindBy(xpath="//div[@id='99fe17d4-c08c-0203-9813-73824a435625']//div[@class='labelControlLabelCell']")
	WebElement text_courtDatesPL;
	
	@FindBy(xpath="//div[@id='0d04b1cb-a6e0-7bb7-1bbc-f3605a5841ce']//div[@class='labelControlLabelCell']")
	WebElement text_interviewsPL;
	
	@FindBy(xpath="//div[@id='cb8f452a-4a23-bb54-0a2a-9517db6c2336']//div[@class='labelControlLabelCell']")
	WebElement text_taskToDoPL;
	
	@FindBy(xpath="//div[@id='79910c8c-6552-51a4-7a98-a2ccc39a39b8']//div[@class='labelControlLabelCell']")
	WebElement text_rfeDuePL;
	
	@FindBy(xpath="//div[contains(text(), 'All Prospects')]")
	WebElement btn_prospects;
	
	@FindBy(xpath="//div[@id='e0a4ac9f-6fa8-1e2c-7eb0-c9e3e776a5ec']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_prospects;

	@FindBy(xpath = "//div[contains(text(), 'Denied')]/../../../../following-sibling::div[1]/div")
    WebElement btn_denied;
	
	@FindBy(xpath = "//div[contains(text(), 'Withdrawn')]/../../../../following-sibling::div[1]/div")
    WebElement btn_withdrawn;
	
	@FindBy(xpath = "//div[contains(text(), 'Approved')]/../../../../following-sibling::div[1]/div")
    WebElement btn_approved;
	
	@FindBy(id="c8bc6c07-8a49-fa73-2c62-54ddb983be3f")
    WebElement btn_rfeSubmitted;
	
	@FindBy(id="a671386d-7a10-27e2-dffb-6207e5116481")
    WebElement btn_rfeReceived;
	
	@FindBy(xpath = "//div[contains(text(), 'Filed')]/../../../../following-sibling::div[1]/div")
    WebElement btn_filed;
	
	@FindBy(id="21ceaea9-ce04-d690-494b-bd4d25e66745")
    WebElement btn_newCases;
	
	@FindBy(id="988ecd8a-e3af-bc13-f994-c70570aa9cd5")
    WebElement btn_initiation;
	
	@FindBy(xpath = "//div[contains(text(), 'Preparation')]/../../../../following-sibling::div[1]/div")
    WebElement btn_preparation;
	
	@FindBy(xpath = "//div[contains(text(), 'Review')]/../../../../following-sibling::div[1]/div")
    WebElement btn_review;
	
	@FindBy(xpath = "//div[contains(text(), 'RFE / Audit')]/../../../../following-sibling::div[1]/div")
    WebElement btn_rfeAudit;
	
	@FindBy(id="1fcc8b6b-a0a0-d4fa-9bcd-1649b535611c")
    WebElement btn_filing;
	
	@FindBy(id="78e7de96-4da9-8413-3e90-46177f01b418")
    WebElement btn_dataCollection;
	
	@FindBy(id="ef0a4230-0727-f2bb-3225-f5b4c460c3e2")
    WebElement btn_waitingForDecision;
	
    @FindBy(id="6b3a8f2a-6117-8b73-3762-4e7aacb403f5")
    WebElement btn_openCases;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='14d9c8c7-05fb-368f-3b5e-6a54a37c533b']")
    WebElement btn_openCasesHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='35ee40ff-30d3-f2b4-5fe1-84b530aa58bf']")
    WebElement btn_initiationHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='78e7de96-4da9-8413-3e90-46177f01b418']")
    WebElement btn_dataCollectionHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='325eb3c6-0856-5290-70f1-9c9598cee3c0']")
    WebElement btn_preparationHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='7e6e4d68-7e87-5fc3-b357-c767df125025']")
    WebElement btn_reviewHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='1fcc8b6b-a0a0-d4fa-9bcd-1649b535611c']")
    WebElement btn_filingHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='9ccde41d-dd47-aff8-ad7e-a5d9b779098e']")
    WebElement btn_rfeAuditHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='0511c16c-e4de-171a-b28f-0ca6174d2a82']")
    WebElement btn_yetToFileHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='547a6ae6-f162-36eb-ebcc-ba9d5099c094']")
    WebElement btn_pendingWithGovtHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='694452cc-7b6b-ac40-5425-888c9ab3356b']")
    WebElement btn_inRFEAuditHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='728d7f35-a439-6e7b-e87b-047d7b518e6e']")
    WebElement btn_deniedHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='e4c93ad9-c589-7f66-033e-ca44eb70465b']")
    WebElement btn_withdrawnHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='4fcf7b51-d206-3580-1a08-bb0f9263f6b4']")
    WebElement btn_approvedHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='6c18ff25-3ffa-52f8-6f6b-c279064da0ad']")
    WebElement btn_casesOpenedHR;
    
    @FindBy(xpath = "//div[contains(text(), 'HR / Corporation Portal Reports')]/../../../../../following-sibling::section/div[@id='af1563ed-a2e5-ac2e-428c-409eafaa2e9f']")
    WebElement btn_waitingForDecisionHR;
  
	@FindBy(xpath = "//div[@id='424528a9-fa7d-be7c-870f-b27e3a3db48e']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_filingHR;
	
	@FindBy(xpath = "//div[@id='811b7199-221f-c39f-14e8-216f200faa2d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_withdrawnHR;
	
	@FindBy(xpath = "//div[@id='5192705d-d523-edc1-6636-0bc5ba1b1e12']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_deniedHR;
	
	@FindBy(xpath = "//div[@id='2dfa7f52-3dcd-619f-a2ea-e765fe0a38c2']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_approvedHR;
	
	@FindBy(xpath = "//div[@id='b98a6344-9317-c0bd-21ff-fa167857a595']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_inRFEAuditHR;
	
	@FindBy(xpath = "//div[@id='37c3771e-e92e-50ea-45b5-d33668af3588']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_pendingWithGovtHR;
	
	@FindBy(xpath = "//div[@id='55b545fb-d6a3-40f0-b0c9-2267042e70c4']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_yetToFileHR;
	
	@FindBy(xpath = "//div[@id='497f5734-0194-f144-08a7-ba807bab52e5']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_casesOpenedHR;
    
	@FindBy(xpath = "//div[@id='9bb11bb6-2d3e-e8aa-252c-65274ba04087']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_initiationHR;
    
	@FindBy(xpath = "//div[@id='641d83db-e6af-d8a7-f95a-c4f78842652f']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_preparationHR;
	
	@FindBy(xpath = "//div[@id='49782946-6630-7aa8-1674-5e720ecd76fd']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_reviewHR;
	
	@FindBy(xpath = "//div[@id='e59593b8-730e-f03e-e71b-d4e36d44a9f2']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeAuditHR;
	
	@FindBy(xpath = "//div[@id='efa6ab85-4ecc-7fe1-c587-0cc9343ac97e']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_waitingForDecisionHR;
    
	@FindBy(xpath = "//div[@id='57c40a23-f76a-0a48-6451-c18927176368']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_dataCollectionHR;
	
	@FindBy(xpath = "//div[@id='712e9d49-5228-a811-71c6-2ca50cf4e85e']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_openCasesHR;
	
	@FindBy(xpath = "//div[@id='0cba2402-9ec0-4a48-6b94-b059576bd94d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_openCasesPL;
	
	@FindBy(xpath = "//div[@id='8b703f85-59da-b17f-9e17-ac99df914e30']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_initiationPL;
	
	@FindBy(xpath = "//div[@id='b6e9531c-12f5-a5cb-bc32-e230800b325d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_dataCollectionPL;
	
	@FindBy(xpath = "//div[@id='720e8300-f4f6-9071-74ff-39e4564109ac']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_preparationPL;
	
	@FindBy(xpath = "//div[@id='5d285281-7f6c-d9f6-d02d-5be4d11e76fc']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_reviewPL;
	
	@FindBy(xpath = "//div[@id='da4a540a-f99c-b48f-9187-0b64644c0018']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_filingPL;
	
	@FindBy(xpath = "//div[@id='b14cc38f-0117-2e2a-2603-312c7585b2a5']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeAuditPL;
	
	@FindBy(id="7a3a62ba-43ad-74ff-e746-e1bda07b4a09")
	WebElement btn_initiationPL;
	
	@FindBy(id="78e7de96-4da9-8413-3e90-46177f01b418")
	WebElement btn_dataCollectionPL;
	
	@FindBy(id="325eb3c6-0856-5290-70f1-9c9598cee3c0")
	WebElement btn_preparationPL;
	
	@FindBy(id="7e6e4d68-7e87-5fc3-b357-c767df125025")
	WebElement btn_reviewPL;
	
	@FindBy(id="1fcc8b6b-a0a0-d4fa-9bcd-1649b535611c")
	WebElement btn_filingPL;
	
	@FindBy(id="35ee40ff-30d3-f2b4-5fe1-84b530aa58bf")
	WebElement btn_rfeAuditPL;
	
	@FindBy(id="6c18ff25-3ffa-52f8-6f6b-c279064da0ad")
	WebElement btn_newCasesPL;
	
	@FindBy(xpath="//div[@id='7aefa9ca-5fa2-bf2a-fe59-25b697a88e6c']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_newCasesPL;
	
	@FindBy(xpath="//div[@id='d520175b-e941-b6ba-d45b-53d07cb3a283']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_withdrawnPL;
	
	@FindBy(xpath="//div[@id='67f49f0e-5171-e105-83d2-b56a7b5e1953']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_deniedPL;
	
	@FindBy(xpath="//div[@id='3c571890-0dd5-de61-d18b-afc0f9b7704b']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_approvedPL;
	
	@FindBy(xpath="//div[@id='888b29fa-ba91-2711-a4a3-292d02677038']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeSubmittedPL;
	
	@FindBy(xpath="//div[@id='9de82f95-e921-b375-fdb7-e7121144d00b']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeReceivedPL;
	
	@FindBy(xpath = "//div[contains(text(), 'PARALEGAL / CASE WORKER REPORTS')]/../../../../../following-sibling::div//div[contains(text(), 'All Open Case(s)')]")
	WebElement btn_openCasesPL;
	
	@FindBy(xpath = "//div[@id='fbe5504e-d86c-ad52-be61-973be1cd6b0f']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeAudit;
	
	@FindBy(xpath = "//div[@id='9f4ec165-55cc-ad24-3f6e-6deba1238f97']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_waitingForDecision;
	
	@FindBy(xpath = "//div[@id='55c0ce48-d83d-6488-3a15-ab6f851d22ce']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_filing;
	
	@FindBy(xpath = "//div[@id='29d26bbf-a54d-fa22-5d7f-1c6e83bc9cf7']//div[@class='labelControlLabelCell']")
	WebElement text_interview;
	
	@FindBy(id="6544d1fc-b3c2-277e-c26a-84293431b28b")
	WebElement btn_interview;
	
	@FindBy(xpath = "(//div[@id='31ed8a37-f064-4de0-d4d1-8e2af8c881b8']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate'])")
	WebElement text_taskToDo;
	
	@FindBy(id="e8df99a4-97f0-b950-2f7a-1ca00ba6d366")
	WebElement btn_taskToDo;
	
	@FindBy(xpath = "//div[@id='ec5d156f-4c81-6102-a0d7-a800141f97f0']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_courtDates;
	
	@FindBy(id="bd1dbdf6-8bf4-ba82-302f-dc0d1b65a941")
	WebElement btn_courtDates;
	
	@FindBy(xpath = "//div[@id='1d5bea19-f505-e8d4-1f98-e9ef961e68f9']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_appointment;
	
	@FindBy(xpath = "//div[@id='d56eb8b8-d1b9-1dce-ebe8-9ddeeb0b59a2']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_RFEDue;
	
	@FindBy(id="f776233e-5513-8ebc-319b-232777df9026")
	WebElement btn_RFEDue;
	
	@FindBy(xpath = "//div[@id='b843b169-fcfe-1579-1be5-5ebee58c5005']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_caseStepReminders;
	
	@FindBy(id="2bf37c01-2253-6c25-dc0a-7bab9ae70cc9")
	WebElement btn_caseStepReminders;
	
	@FindBy(xpath = "//div[@id='b3382b1e-06b9-818e-8062-e66b4bb2c155']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_review;
	
	@FindBy(xpath = "//div[@id='23947462-b63c-d8df-597c-c99985db76d5']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_withdrawn;
	
	@FindBy(xpath = "//div[@id='ac0d2d7e-4c3d-d498-8d2d-80dab605ccee']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_denied;
	
	@FindBy(xpath = "//div[@id='241e05d1-7d7a-65cd-ee84-f16f34fe934e']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_approved;
	
	@FindBy(xpath = "//div[@id='3d6801bf-4b9b-5be4-d5cc-d7463f83ed43']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeSubmitted;
	
	@FindBy(xpath = "//div[@id='eba6ade6-8149-aaa3-3f2d-06ab88c4246d']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_rfeReceived;
	
	@FindBy(xpath = "//div[@id='ec7cb607-02e6-d04a-8790-12d23f6aa174']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_newCases;
	
	@FindBy(xpath = "//div[@id='2e321a06-0544-402f-6881-60871ca6fee2']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_preparation;
	
	@FindBy(xpath = "//div[@id='bd0fa872-e758-bada-b676-1fd82ae05cf7']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_dataCollection;
	
	@FindBy(xpath = "//div[contains(text(), 'Initiation')]/../../../../preceding-sibling::div[2]//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_initiation;
	
	@FindBy(xpath = "//div[@id='1019833a-cdb6-6412-a6a1-04f6c114a754']//tr[@ro='0']//div[@class='gridCellTemplate gridCellTextTemplate']")
	WebElement text_allOpenCases;
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
	
	
	public CM_Reports_3_0_Page(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(finder, this);
	}

	
	public void switchToDundasFrame()
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 23 July 2020
         */
		
		 Utils.waitForElement(driver, loader_Dots);
		 Utils.waitForElementNotVisible(driver, "//div[@class='dots']", "xpath");
		 Utils.waitForElement(driver, "dundasFrame", 60, "id");
		 driver.switchTo().frame("dundasFrame");
	}
	
	
	public void switchToDundasHRPFrame()
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 23 July 2020
         */
		
		 Utils.waitForElement(driver, loader_Dots);
		 Utils.waitForElementNotVisible(driver, "//div[@class='dots']", "xpath");
		 Utils.waitForElement(driver, "dundasFrameHR", 50, "id");
		 driver.switchTo().frame("dundasFrameHR");
	}
	
	
	public void switchToDundasParalegalFrame()
	{
		/*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
		
		 Utils.waitForElement(driver, loader_Dots);
		 Utils.waitForElementNotVisible(driver, "//div[@class='dots']", "xpath");
		 Utils.waitForElement(driver, "dundasFrameParalegal", 50, "id");
		 driver.switchTo().frame("dundasFrameParalegal");
	}
	
	
    public int getTotalOpenCases() throws Exception
    {
         /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	int total = 0;
    	
    	try
    	{
	        Utils.waitForElement(driver, text_allOpenCases);
	        total = Integer.parseInt(text_allOpenCases.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}
    	catch(Exception e)
    	{
    		Log.fail("Records not found", driver, true);
    	}
        
        driver.switchTo().defaultContent();
        

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
 
    public int countAndclickAllOpenCases() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_allOpenCases);
    		total = Integer.parseInt(text_allOpenCases.getText().replace(",", ""));
	    	Log.message("Number of records fetched from UI: " + total, driver, true);
    	}
    	catch(Exception e){
    		Log.fail("Records not found");
    	}
    	
    	if(total == 0)
        	Log.fail("There are 0 records in the report");
       
        Utils.clickElement(driver, btn_openCases, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public void clickAllOpenCases() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
    	Utils.clickUsingAction(driver, btn_openCases);
    	Log.message("Clicked on 'All Open Cases'");
 
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
      
    }
    
    
    public void clickWaitingForDecision() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();

    	Utils.clickUsingAction(driver, btn_waitingForDecision);
    	Log.message("Clicked on 'Waiting for Decision'");
  
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickNewCases() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_newCases);
	        total = Integer.parseInt(text_newCases.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_newCases, "New Case(s)");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickNewCases() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_newCases);
        Log.message("Clicked on 'New Case(s)'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickInitiation() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_initiation);
    		total = Integer.parseInt(text_initiation.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
        
        Utils.clickElement(driver, btn_initiation, "Initiation");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickInitiation() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
       
    	switchToDundasFrame();
    	
        Utils.clickUsingAction(driver, btn_initiation);
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int clickDataCollection() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
			Utils.waitForElement(driver, text_dataCollection);
		    total = Integer.parseInt(text_dataCollection.getText().replace(",", ""));
		    Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_dataCollection, "Data Collection");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPreparation() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_preparation);
	        total = Integer.parseInt(text_preparation.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_preparation, "Preparation");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickFiling() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{    
	    	Utils.waitForElement(driver, text_filing);
	        total = Integer.parseInt(text_filing.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_filing, "Filing");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFEAudit() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeAudit);
	        total = Integer.parseInt(text_rfeAudit.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_rfeAudit, "RFE/Audit");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickReview() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_review);
	        total = Integer.parseInt(text_review.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_review, "Review");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickFiled() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_filed);
        Log.message("Clicked on Filed");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickRFEReceived() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeReceived);
	        total = Integer.parseInt(text_rfeReceived.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_rfeReceived, "RFE Received");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickRFEReceived() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_rfeReceived);
        Log.message("Clicked on RFE Received");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickRFESubmitted() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeSubmitted);
	        total = Integer.parseInt(text_rfeSubmitted.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.waitForElement(driver, btn_rfeSubmitted);
        Utils.clickUsingAction(driver, btn_rfeSubmitted);
        Log.message("Clicked on RFE Submitted");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickRFESubmitted() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
    	Utils.waitForElement(driver, btn_rfeSubmitted);
        Utils.clickUsingAction(driver, btn_rfeSubmitted);
        Log.message("Clicked on RFE Submitted");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int clickPERMFiled() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 7 Sep 2020
         */
       
    	switchToDundasFrame();
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
       
    	Utils.selectOptionFromDropDownByVal(driver, "180:D", dropdown_datePickerPERM);
    	int total = 0;
    	
    	try{
	    	Utils.waitForElement(driver, text_permFiled);
	    	String temp[] = text_permFiled.getText().split(" ");
	        total = Integer.parseInt(temp[0]);
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}
    	catch(Exception e)
    	{
    		Log.fail("Records not found");
    	}

        Utils.clickUsingAction(driver, text_permFiled);
        Log.message("Clicked on 'PERM Filed'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPERMFiledHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 7 Sep 2020
         */
    	
    	int total = 0;
    	
    	String temp[];
		try {
			Utils.waitForElement(driver, text_permFiledHR);
			temp = text_permFiledHR.getText().split("-"); 
			total = Integer.parseInt(temp[0]);
			Log.message("Number of records fetched from UI: " + total, driver, true);
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to fetch records");
		}

        Utils.waitForElement(driver, text_permFiledHR);
        Utils.clickUsingAction(driver, text_permFiled);
        Log.message("Clicked on 'PERM Filed'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickClientByCurrentImmigrationStatusHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 9 Sep 2020
         */
    	
    	int total = 0;
    	
    	try {
			Utils.waitForElement(driver, text_clientByCurrentImmigrationStatusHR);
			total = Integer.parseInt(text_clientByCurrentImmigrationStatusHR.getText());
			Log.message("Number of records fetched from UI: " + total, driver, true);

		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to. ERROR :\n\n " + e.getMessage());
		}


		Utils.waitForElement(driver, text_clientByCurrentImmigrationStatusHR);
        Utils.clickUsingAction(driver, text_clientByCurrentImmigrationStatusHR);
        Log.message("Clicked on 'Client By Current Immigration Status'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPERMFiledPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 7 Sep 2020
         */
       
    	switchToDundasParalegalFrame();
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
       
    	Utils.selectOptionFromDropDownByVal(driver, "180:D", dropdown_datePickerPERMPL);
    	
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_permFiledPL);
	    	String temp[] = text_permFiledPL.getText().split(" ");
	        total = Integer.parseInt(temp[0]);
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}
    	
        Utils.waitForElement(driver, text_permFiledPL);
        Utils.clickUsingAction(driver, text_permFiled);
        Log.message("Clicked on 'PERM Filed'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickOpenCasesByType() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 7 Sep 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_openCasesByType);
	    	total = Integer.parseInt(text_openCasesByType.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
        
	        Utils.waitForElement(driver, text_openCasesByType);
	        Utils.clickUsingAction(driver, text_openCasesByType);
	        Log.message("Clicked on 'Open Cases by Type'");
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickOpenCasesByTypeHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 7 Sep 2020
         */
    	
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_openCasesByTypeHR);
	    	total = Integer.parseInt(text_openCasesByTypeHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}
    	
        Utils.waitForElement(driver, text_openCasesByTypeHR);
        Utils.clickUsingAction(driver, text_openCasesByTypeHR);
        Log.message("Clicked on 'Open Cases by Type'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickOpenCasesByTypePL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 8 Sep 2020
         */
       
    	switchToDundasParalegalFrame();
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_openCasesByTypePL);
	    	total = Integer.parseInt(text_openCasesByTypePL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}
    	
        Utils.waitForElement(driver, text_openCasesByTypePL);
        Utils.clickUsingAction(driver, text_openCasesByTypePL);
        Log.message("Clicked on 'Open Cases by Type'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int countAndClickDenied() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_denied);
	        total = Integer.parseInt(text_denied.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_denied, "Denied");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickDenied() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
	       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_denied);
        Log.message("Clicked on Denied");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickWithdrawn() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_withdrawn);
	        total = Integer.parseInt(text_withdrawn.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_withdrawn, "Withdrawn");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickWithdrawn() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_withdrawn);
        Log.message("Clicked on Withdrawn");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickApproved() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_approved);
	        total = Integer.parseInt(text_approved.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_approved, "Approved");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickApproved() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_approved);
        Log.message("Clicked on 'Approved'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

    }
    
    
    public int countAndClickProspects() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	Utils.selectOptionFromDropDownByVal(driver, "180:D", dropdown_prospectsDate);
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_prospects);
	        total = Integer.parseInt(text_prospects.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
       
        Utils.clickElement(driver, btn_prospects, "Prospects");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public void clickProspects() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 Sep 2020
         */
       
    	switchToDundasFrame();
    	
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickElement(driver, btn_prospects, "Prospects");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickCaseExpiration() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	Utils.clickElement(driver, dropdown_datePickerExpirations, "Date Picker for Expirations");
    	Utils.clickElement(driver, option_next180DaysExpiration, "Next 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_caseExpiration);
	        total = Integer.parseInt(text_caseExpiration.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
       
        Utils.clickElement(driver, btn_caseExpiration, "Case Expiration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public void clickCaseExpiration() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	checkTimeoutOnDashboardPage();
       
        Utils.clickElement(driver, btn_caseExpiration, "Case Expiration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int clickI797() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	Utils.clickElement(driver, dropdown_datePickerExpirations, "Date Picker for Expirations");
    	Utils.clickElement(driver, option_next180DaysExpiration, "Next 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_clientDocExpiration);
	        total = Integer.parseInt(text_clientDocExpiration.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
       
        Utils.clickElement(driver, btn_clientDocExpiration, "Client Doc Expiration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public int clickI797PL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 01 Sep 2020
         */
       
    	switchToDundasParalegalFrame();
    	
    	Utils.waitForElement(driver, text_I797PL);
    	
    	Utils.clickElement(driver, dropdown_datePickerExpirations, "Date Picker for Expirations");
    	Utils.clickElement(driver, option_next180DaysExpiration, "Next 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	
    	try
    	{
        	Utils.waitForElement(driver, text_I797PL);
            total = Integer.parseInt(text_I797PL.getText().replace(",", ""));
            Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}  	
    	
    	Utils.waitForElement(driver, text_I797PL);
        total = Integer.parseInt(text_I797PL.getText().replace(",", ""));
        Log.message("Number of records fetched from UI: " + total, driver, true);
       
        Utils.clickElement(driver, btn_I797PL, "I-797");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public int click21YearsAgeoutPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 01 Sep 2020
         */
       
    	switchToDundasParalegalFrame();
    	
    	Utils.waitForElement(driver, text_21YearsAgeoutPL);
    	
    	Utils.clickElement(driver, dropdown_datePickerExpirations, "Date Picker for Expirations");
    	Utils.clickElement(driver, option_next180DaysExpiration, "Next 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_21YearsAgeoutPL);
	        total = Integer.parseInt(text_21YearsAgeoutPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}
    
        Utils.clickElement(driver, btn_21YearsAgeoutPL, "21 Years Ageout");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public int clickProspectsPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 01 Sep 2020
         */
       
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	
    	try
    	{
	    	Utils.waitForElement(driver, text_prospectsPL);
	        total = Integer.parseInt(text_prospectsPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	}catch(Exception e)
    	{
    		Log.fail("Unable to find records");
    	}  	
       
        Utils.clickElement(driver, btn_prospectsPL, "Prospects");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public int countAndClickEligibleForFilingVisa() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_eligibleForFilingVisa);
	        total = Integer.parseInt(text_eligibleForFilingVisa.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
       
        Utils.clickElement(driver, btn_eligibleForFilingVisa, "Eligible For Filing Visa");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
        
        return total;
    }
    
    
    public void clickEligibleForFilingVisa() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
    	
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickElement(driver, btn_eligibleForFilingVisa, "Eligible For Filing Visa");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickLifeCycle() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_lifeCycle, "Life Cycle");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickCaseStatus() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_caseStatus, "Case status");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickPriorityDates() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_priorityDates, "Priority Dates");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickJ1() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_J1, "J-1");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickCaseLCA() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_caseLCA, "Case LCA");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickH1BRegistration() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_H1BRegistration, "H1-B Registration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickNIV() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_NIV, "NIV");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickIV() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_IV, "IV");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickLoginStatistics() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_LoginStatistics, "Login Statistics");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickCorpUser() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_CorpUser, "Corp User");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickCitizenship() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_Citizenship, "Citizenship");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickCountry()
    {
    	 /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         * 
         */
    	
    	switchToDundasFrame();
    	
		Utils.verifyIfDataNotPresent(driver, "div[class='elementInlineError']", btn_Antarctica, "css", "Timeout Error", "World Map Page");    	
    	
    	Utils.clickElement(driver, btn_Antarctica, "Antarctica");
    }
    
    
    public void clickCaseRequest() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_CaseRequest, "Case Request");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickPERM() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_PERM, "PERM");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickEConsent() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_EConsent, "e-Consent");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickOpenCaseByTypeUndefined() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickElement(driver, btn_undefined, "Undefined");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void selectAllHeadquarters() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasHRPFrame();
       
    	Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
    	
//        Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
//    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
        
        Utils.waitForElement(driver, img_toggleArrow);
        Utils.clickUsingAction(driver, img_toggleArrow);
        Log.message("Clicked on Arrow to toggle HQ/Corporation");
        
        Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
      
    }
    
    
    public void clickHRPortalReportsTab() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	switchToDundasFrame();
    	driver.switchTo().defaultContent();
       
        Utils.clickElement(driver, tab_HRPortalReports, "HR Portal Reports tab");
    }
    
    
    public void clickPLReportsTab() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasFrame();
    	driver.switchTo().defaultContent();
       
        Utils.clickElement(driver, tab_PLReports, "Paralegal / Case Worker Reports tab");
    }
    
    
    public void clickGreencardProcess() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	checkTimeoutOnDashboardPage();
    	
    	Utils.waitForElement(driver, btn_greencardProcess);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    	
    	Utils.clickUsingAction(driver, btn_greencardProcess);
        Log.message("Clicked on Greencard Process");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void click21YearsAgeout() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickElement(driver, btn_21YearsAgeout, "21 Years Ageout");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickUpcomingCaseActivity() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, tab_upcomingCaseActivity, "Upcoming Case Activity");
        
        Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    }
    
    
    public void clickUpcomingCaseActivityPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasParalegalFrame();
       
        Utils.clickElement(driver, tab_upcomingCaseActivityPL, "Upcoming Case Activity");
    }
    
    
    public int countAndClickTaskToDo() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
 
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_taskToDo);
	        total = Integer.parseInt(text_taskToDo.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_taskToDo);
        Log.message("Clicked on 'Task To/Do'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickTaskToDo() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
 
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_taskToDo);
        Log.message("Clicked on 'Task To/Do'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int clickAllOpenCasesHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_openCasesHR);
	        total = Integer.parseInt(text_openCasesHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}

    	
        Utils.clickElement(driver, btn_openCasesHR, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickAllOpenCasesPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	int total = 0;
    	switchToDundasParalegalFrame();
    	try
    	{
	    	Utils.waitForElement(driver, text_openCasesPL);
	        total = Integer.parseInt(text_openCasesPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_openCasesPL);
        Log.message("Clicked on All Open Case(s)");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickInitiationPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_initiationPL);
	        total = Integer.parseInt(text_initiationPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_initiationPL);
        Log.message("Clicked on Initiation");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickFilingPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_filingPL);
	        total = Integer.parseInt(text_filingPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_filingPL);
        Log.message("Clicked on Filing");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFEAuditPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeAuditPL);
	        total = Integer.parseInt(text_rfeAuditPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_rfeAuditPL);
        Log.message("Clicked on RFE Audit");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickNewCasesPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_newCasesPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_newCasesPL);
	        total = Integer.parseInt(text_newCasesPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_newCasesPL);
        Log.message("Clicked on New Cases");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFESubmittedPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_rfeSubmittedPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeSubmittedPL);
	        total = Integer.parseInt(text_rfeSubmittedPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_rfeSubmittedPL);
        Log.message("Clicked on RFE Submitted");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFEReceivedPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_rfeReceivedPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_rfeReceivedPL);
	        total = Integer.parseInt(text_rfeReceivedPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_rfeReceivedPL);
        Log.message("Clicked on RFE Received");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickApprovedPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_approvedPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_approvedPL);
	        total = Integer.parseInt(text_approvedPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_approvedPL);
        Log.message("Clicked on Approved");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickDeniedPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_deniedPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_deniedPL);
	    	total = Integer.parseInt(text_deniedPL.getText().replace(",", ""));
	    	Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_deniedPL);
        Log.message("Clicked on Denied");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickWithdrawnPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	switchToDundasParalegalFrame();

    	Utils.waitForElement(driver, text_withdrawnPL);
    	Utils.clickElement(driver, dropdown_datePickerPastCaseActivity, "Date Picker for Past Case Activity");
    	Utils.clickElement(driver, option_last180DaysPastCaseActivity, "Last 180 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_withdrawnPL);
	    	total = Integer.parseInt(text_withdrawnPL.getText().replace(",", ""));
	    	Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_withdrawnPL);
        Log.message("Clicked on Withdrawn");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFEDuePL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 26 August 2020
         */
    	
    	Utils.waitForElement(driver, text_rfeDuePL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeDuePL);
	        total = Integer.parseInt(text_rfeDuePL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_rfeDuePL);
        Log.message("Clicked on RFE Due");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickCaseStepRemindersPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 31 August 2020
         */
    	
    	Utils.waitForElement(driver, text_caseStepRemindersPL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_caseStepRemindersPL);
	        total = Integer.parseInt(text_caseStepRemindersPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_caseStepRemindersPL);
        Log.message("Clicked on Case Step Reminders");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickAppointmentsPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 31 August 2020
         */
    	
    	Utils.waitForElement(driver, text_appointmentsPL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_appointmentsPL);
	        total = Integer.parseInt(text_appointmentsPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_appointmentsPL);
        Log.message("Clicked on Appointments");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickCourtDatesPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 31 August 2020
         */
    	
    	Utils.waitForElement(driver, text_courtDatesPL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_courtDatesPL);
	        total = Integer.parseInt(text_courtDatesPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_courtDatesPL);
        Log.message("Clicked on Court Dates");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickInterviewsPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 31 August 2020
         */
    	
    	Utils.waitForElement(driver, text_interviewsPL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_interviewsPL);
	        total = Integer.parseInt(text_interviewsPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_interviewsPL);
        Log.message("Clicked on Interviews");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickTaskToDoPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 31 August 2020
         */
    	
    	Utils.waitForElement(driver, text_taskToDoPL);
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivityPL, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivityPL, "next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_taskToDoPL);
	    	total = Integer.parseInt(text_taskToDoPL.getText().replace(",", ""));
	    	Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_taskToDoPL);
        Log.message("Clicked on Task/To-Do");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickDataCollectionPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_dataCollectionPL);
	        total = Integer.parseInt(text_dataCollectionPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_dataCollectionPL);
        Log.message("Clicked on Data Collection");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPreparationPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_preparationPL);
	        total = Integer.parseInt(text_preparationPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_preparationPL);
        Log.message("Clicked on RFE Audit");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickReviewPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 25 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_reviewPL);
	        total = Integer.parseInt(text_reviewPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, btn_reviewPL);
        Log.message("Clicked on Review");
        //Utils.clickElement(driver, btn_openCasesPL, "All Open Cases");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickInitiationHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_initiationHR);
	    	total = Integer.parseInt(text_initiationHR.getText().replace(",", ""));
	    	Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_initiationHR, "Initiation");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPreparationHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_preparationHR);
	        total = Integer.parseInt(text_preparationHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_preparationHR, "Preparation");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickFilingHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{	
	    	Utils.waitForElement(driver, text_filingHR);
	        total = Integer.parseInt(text_filingHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_filingHR, "Filing");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickReviewHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{	
    		Utils.waitForElement(driver, text_reviewHR);
	        total = Integer.parseInt(text_reviewHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_reviewHR, "Review");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickDataCollectionHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_dataCollectionHR);
	        total = Integer.parseInt(text_dataCollectionHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_dataCollectionHR, "Data Collection");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickWaitingForDecisionHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_waitingForDecisionHR);
	        total = Integer.parseInt(text_waitingForDecisionHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_waitingForDecisionHR, "Waiting for Decision");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickRFEAuditHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_rfeAuditHR);
	        total = Integer.parseInt(text_rfeAuditHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_rfeAuditHR, "RFE / Audit");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickWithdrawnHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_withdrawnHR);
	        total = Integer.parseInt(text_withdrawnHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_withdrawnHR, "Withdrawn");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickCaseExpirationHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 24 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_caseExpirationHR);
            total = Integer.parseInt(text_caseExpirationHR.getText().replace(",", ""));
            Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_caseExpirationHR, "Case Expiration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickClientExpirationHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 24 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_clientDocExpirationHR);
            total = Integer.parseInt(text_clientDocExpirationHR.getText().replace(",", ""));
            Log.message("Number of records fetched from UI: " + total, driver, true);
        	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_clientDocExpirationHR, "Client Doc Expiration");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickEligibleForFilingVisaHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 24 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_eligibleForFilingVisaHR);
	        total = Integer.parseInt(text_eligibleForFilingVisaHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_eligibleForFilingVisaHR);
        Log.message("Clicked on 'Eligible for Filing Visa'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickClientByCitizenshipHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 08 Sep 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_clientByCitizenshipHR);
	    	String temp[] = text_clientByCitizenshipHR.getText().split(" ");
	        total = Integer.parseInt(temp[1]);
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
        
        Utils.clickUsingAction(driver, text_clientByCitizenshipHR);
        Log.message("Clicked on Canada in 'Client by Citizenship'");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickEligibleForFilingVisaPL() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 24 August 2020
         */
    	
    	switchToDundasParalegalFrame();
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_eligibleForFilingVisaPL);
	        total = Integer.parseInt(text_eligibleForFilingVisaPL.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_eligibleForFilingVisaPL);
        Log.message("Clicked on Eligible for Filing Visa");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickDeniedHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_deniedHR);
	        total = Integer.parseInt(text_deniedHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_deniedHR, "Denied");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickApprovedHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_approvedHR);
	        total = Integer.parseInt(text_approvedHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_approvedHR, "Approved");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickPendingWithGovtHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_pendingWithGovtHR);
	        total = Integer.parseInt(text_pendingWithGovtHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_pendingWithGovtHR, "Pending With Govt.");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickYetToFileHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_yetToFileHR);
	        total = Integer.parseInt(text_yetToFileHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_yetToFileHR, "Yet To File");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickInRFEAuditHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_inRFEAuditHR);
	        total = Integer.parseInt(text_inRFEAuditHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_inRFEAuditHR, "In RFE / Audit");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int clickCasesOpenedHR() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 18 August 2020
         */
    	
    	int total = 0;
    	try
    	{
    		Utils.waitForElement(driver, text_casesOpenedHR);
	        total = Integer.parseInt(text_casesOpenedHR.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
    	
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_casesOpenedHR, "Cases Opened");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public int countAndClickCourtDates() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    	
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try{
	    	Utils.waitForElement(driver, text_courtDates);
	        total = Integer.parseInt(text_courtDates.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    
        Utils.clickUsingAction(driver, text_courtDates);
        Log.message("Clicked on Court Dates");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickCourtDates() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
    	
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_courtDates);
        Log.message("Clicked on Court Dates");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickInterviews() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
    
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_interview);
	        total = Integer.parseInt(text_interview.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_interview);
        Log.message("Clicked on Interviews");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickInterviews() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         */
    
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_interview);
        Log.message("Clicked on Interviews");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickAppointment() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
      
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	
    	try{
	    	Utils.waitForElement(driver, text_appointment);
	        total = Integer.parseInt(text_appointment.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickElement(driver, btn_appointment, "Appointment");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickAppointment() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
      
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_appointment);
        Log.message("Clicked on Appointment");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickCaseStepReminders() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
        try 
        {
			Utils.waitForElement(driver, text_caseStepReminders);
			total = Integer.parseInt(text_caseStepReminders.getText().replace(",", ""));
			Log.message("Number of records fetched from UI: " + total, driver, true);
		} catch (Exception e) {
			Log.message("", driver, true);
			Log.fail("Unable to find records");
		}

		Utils.clickUsingAction(driver, text_caseStepReminders);
        Log.message("Clicked on Case Step Reminders");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickCaseStepReminders() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, btn_caseStepReminders);
        Log.message("Clicked on Case Step Reminders");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public int countAndClickRFEDue() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	Utils.clickElement(driver, dropdown_datePickerUpcomingCaseActivity, "Date Picker for Upcoming Case Activity");
    	Utils.clickElement(driver, option_next60DaysUpcomingCaseActivity, "Next 60 Days");
    	
    	Utils.waitForElement(driver, "div[class='element-loading-block']", globalVariables.elementWaitTime, "css");
    	Utils.waitForElementNotVisible(driver, "div[class='element-loading-block']", "css");
    	
    	int total = 0;
    	try
    	{
	    	Utils.waitForElement(driver, text_RFEDue);
	        total = Integer.parseInt(text_RFEDue.getText().replace(",", ""));
	        Log.message("Number of records fetched from UI: " + total, driver, true);
	        
	    }
		catch(Exception e){
			Log.fail("Records not found / Error while fetching records");
		}
    	
        Utils.clickUsingAction(driver, text_RFEDue);
        Log.message("Clicked on RFE Due");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

        if(total == 0)
        	Log.fail("There are 0 records in the report");
        
        return total;
    }
    
    
    public void clickRFEDue() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	checkTimeoutOnDashboardPage();
    	
        Utils.clickUsingAction(driver, text_RFEDue);
        Log.message("Clicked on RFE Due");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();

    }
    
    
    public void clickClientDetails() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_clientDetails, "Client Details");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickInvoice() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_invoice, "Invoice");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickWorkSummary() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	switchToDundasFrame();
       
        Utils.clickElement(driver, btn_workSummary, "Work Summary");
        
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickViewByInvoiceTab() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	Utils.waitForElement(driver, "dundasFrame", 60, "id");
		driver.switchTo().frame("dundasFrame");
       
        Utils.clickElement(driver, tab_viewByInvoice, "View By Invoice");
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickViewByCaseTab() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	Utils.waitForElement(driver, "dundasFrame", 60, "id");
		driver.switchTo().frame("dundasFrame");
       
        Utils.clickElement(driver, tab_viewByCase, "View By Case");
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void clickViewByCorporationTab() throws Exception
    {
        /*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         */
       
    	Utils.waitForElement(driver, "dundasFrame", 60, "id");
		driver.switchTo().frame("dundasFrame");
       
        Utils.clickElement(driver, tab_viewByCorporation, "View By Corporation");
        
        Utils.waitForElement(driver, loader_reportsPage);
        Utils.waitForElementNotVisible(driver, "IframeLoader", "id");
        
        driver.switchTo().defaultContent();
    }
    
    
    public void verifyNumberOfRowsInExcel(int noOfRowsInUI, String sheetName) throws Exception
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 27 July 2020
         * 
         */
    	
    	File[] files = Utils.getAllDownloadedFiles();
    	HashSet<String> setOfFiles= new HashSet<String>();
    	for(int i = 0; i < files.length; i ++)
    	{
    		setOfFiles.add(files[i].toString());
    	}
    	
    	driver.switchTo().frame("dundasFrame");
    	
    	Utils.clickElement(driver, btn_exportToExcel, "Export to Excel");
    	
    	Utils.waitForElement(driver, "//p[contains(text(), 'Working on it')]", globalVariables.elementWaitTime, "xpath");
    	Utils.waitForElementNotVisible(driver, "//p[contains(text(), 'Working on it')]", "xpath");
    	
    	Thread.sleep(30000);
    	
    	String excelFile = "";
    	files = Utils.getAllDownloadedFiles();
    	for(int i = 0; i < files.length; i ++)
    	{
    		if(!setOfFiles.contains(files[i].toString()))
    		{
    			excelFile = files[i].toString();
    			break;
    		}
    	}
		
    	GetDataFromExcelAndVerify getDataFromExcel = new GetDataFromExcelAndVerify(driver);
    
		int noOfRows = getDataFromExcel.excelXSSF(excelFile, sheetName);
		
		if((noOfRows - 1) == noOfRowsInUI)
			Log.pass("No of Rows Verified.... Records on UI: " + noOfRowsInUI + "... Records on Excel: " + (noOfRows-1));
		else
			Log.fail("No of Rows NOT Verified.... Records on UI: " + noOfRowsInUI + "... Records on Excel: " + (noOfRows-1));
    }
    
    
    public void checkTimeout() throws InterruptedException
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 15 Sep 2020
         * 
         */
    	
    	driver.switchTo().defaultContent();
    	Utils.waitForElement(driver, "dundasFrame", globalVariables.elementWaitTime, "id");
    	driver.switchTo().frame("dundasFrame");
		Utils.verifyIfDataNotPresent(driver, "div[class='elementInlineError']", tbl_records, "css", "Timeout Error", "Detailed Report Page");    	
    }
    
    
    public void verifyColumns(String report) throws Exception
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 06 May 2020
         * 
         */
    	
    	checkTimeout();
    	
    	String workbookName = ".\\src\\main\\resources\\testdata\\data\\ReportsOutputColumns.xls";
    	String sheetName = "Output Columns";
    	
/*    	Actions action = new Actions(driver);
		//action.moveToElement(element).sendKeys(Keys.DOWN).build().perform();
		
    	Log.message("Scrolling start");
		for(int i = 0; i<2500; i++)
		{
			//element = driver.findElement(By.xpath("//table[@class='body-table']/.."));
			action.moveToElement(element).sendKeys(Keys.PAGE_DOWN).build().perform();
			//element.sendKeys(Keys.DOWN);s
		}
    	
		Log.message("Scrolling Complete");
		
//    	JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,10000)");
*/    	
    	ExcelToDataProvider reader = new ExcelToDataProvider();
		Object[][] data = reader.userFormData(workbookName, sheetName);
		RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		
		for(int i = 0 ; i < noOfCol-1 ; i++)
		{
			if(data[0][i].equals(report))
			{
				for(int j = 1; j < noOfRows-1; j++)
				{
					if(!data[j][i].toString().equals("") && !data[j][i].toString().equals(" "))
					{
						if(Utils.isElementPresent(driver, "//td[@datatoshow='" + data[j][i].toString() + "']//div[contains(text(), '" + data[j][i] + "')]", "xpath"))                     
							Log.pass("Verified "+data[j][i], driver);
						else
						{
							Log.failsoft(data[j][i] + " is NOT Present in the UI");
						}
					}
				}
				break;
		    }
		}
	
    }

    
    public void verifyInvoiceColumns(String report) throws Exception
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 06 May 2020
         * 
         */
    	driver.switchTo().defaultContent();
    	Utils.waitForElement(driver, "dundasFrame", 60, "id");
    	driver.switchTo().frame("dundasFrame");
    	String workbookName = ".\\src\\main\\resources\\testdata\\data\\ReportsOutputColumns.xls";
    	String sheetName = "Output Columns";

    	Utils.waitForElement(driver, "(//table[@class='cornerHeaderTable'])[1]", globalVariables.elementWaitTime, "xpath");
    	WebElement element = driver.findElement(By.xpath("(//table[@class='cornerHeaderTable'])[1]"));
 	
    	ExcelToDataProvider reader = new ExcelToDataProvider();
		Object[][] data = reader.userFormData(workbookName, sheetName);
		RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		
		for(int i = 0 ; i < noOfCol-1 ; i++)
		{
			if(data[0][i].equals(report))
			{
				for(int j = 1; j < noOfRows-1; j++)
				{
					if(!data[j][i].toString().equals("") && !data[j][i].toString().equals(" "))
					{
						if(Utils.isElementPresent(driver, "(//table[@class='cornerHeaderTable']//div[contains(text(), '" + data[j][i] + "')])[1]", "xpath"))                     
							Log.pass("Verified "+data[j][i], driver);
						else
						{
							Log.failsoft(data[j][i] + " is NOT Present in the UI");
						}
					}
				}
				break;
		    }
		}
    }
    
    
    public void verifyWorkSummaryColumns(String report) throws Exception
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 06 May 2020
         * 
         */
    	driver.switchTo().defaultContent();
    	Utils.waitForElement(driver, "dundasFrame", 60, "id");
    	driver.switchTo().frame("dundasFrame");
    	String workbookName = ".\\src\\main\\resources\\testdata\\data\\ReportsOutputColumns.xls";
    	String sheetName = "Output Columns";

    	Utils.waitForElementPresent(driver, "//table[@class='column-header-table']", "xpath");
    	List<WebElement> listOfTables = driver.findElements(By.xpath("//table[@class='column-header-table']"));
    	
    	int index = 0;
    	
    	for(; index < listOfTables.size();)
    	{
    		if(listOfTables.get(index).isDisplayed())
    		{
    			break;
    		}
    		index += 1;
    	}
    	
    	index += 1;
    	
    	ExcelToDataProvider reader = new ExcelToDataProvider();
		Object[][] data = reader.userFormData(workbookName, sheetName);
		RowCountColumnCount count = new RowCountColumnCount(workbookName);
		int noOfRows = count.getRowCount(sheetName);
		int noOfCol = count.getColumnCount(sheetName);
		
		for(int i = 0 ; i < noOfCol-1 ; i++)
		{
			if(data[0][i].equals(report))
			{
				for(int j = 1; j < noOfRows-1; j++)
				{
					if(!data[j][i].toString().equals("") && !data[j][i].toString().equals(" "))
					{
						if(Utils.isElementPresent(driver, "(//table[@class='column-header-table'])[" + index + "]//div[contains(text(), '" + data[j][i] + "')]", "xpath"))                     
							Log.pass("Verified "+data[j][i], driver);
						else
						{
							Log.failsoft(data[j][i] + " is NOT Present in the UI");
						}
					}
				}
				break;
		    }
		}
    }

    
    public void verifyNumberOfRowsInExcel(int noOfRowsInUI, String sheetName, String parameter) throws Exception
    {
    	/*
         * Created By : Saksham Kapoor
         * Created On : 13 Aug 2020
         * 
         */
    	
    	File[] files = Utils.getAllDownloadedFiles();
    	HashSet<String> setOfFiles = new HashSet<String>();
    	HashSet<String> uniqueRecords = new HashSet<String>();
    	for(int i = 0; i < files.length; i ++)
    	{
    		setOfFiles.add(files[i].toString());
    	}
    	
    	driver.switchTo().frame("dundasFrame");
    	
    	Utils.clickElement(driver, btn_exportToExcel, "Export to Excel");
    	
    	Utils.waitForElement(driver, "//p[contains(text(), 'Working on it')]", globalVariables.elementWaitTime, "xpath");
    	Utils.waitForElementNotVisible(driver, "//p[contains(text(), 'Working on it')]", "xpath");
    	
    	Thread.sleep(60000);
    	
    	String excelFile = "";
    	files = Utils.getAllDownloadedFiles();
    	for(int i = 0; i < files.length; i ++)
    	{
    		if(!setOfFiles.contains(files[i].toString()))
    		{
    			excelFile = files[i].toString();
    			break;
    		}
    	}
		
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(0);
		int colNum = row.getLastCellNum();
		int rowNum = sheet.getLastRowNum();
		
		int colPosition = 0;
		for(int i = 0; i <= colNum; i++)
		{
			XSSFRow rows = sheet.getRow(0);
			
			if(rows.getCell(i).getStringCellValue().equals(parameter))
			{
				colPosition = i;
				break;
			}
		}
		
		for(int i = 1; i <= rowNum; i++)
		{
			XSSFRow rows = sheet.getRow(i);
			
			uniqueRecords.add(rows.getCell(colPosition).getStringCellValue());
			if(i == 1 || i == rowNum)
				System.out.println(rows.getCell(colPosition).getStringCellValue());
		}
		
		if(uniqueRecords.size() == noOfRowsInUI)
			Log.pass("No of Rows Verified.... Records on UI: " + noOfRowsInUI + "... Records on Excel: " + uniqueRecords.size());
		else
			Log.fail("No of Rows NOT Verified.... Records on UI: " + noOfRowsInUI + "... Records on Excel: " + uniqueRecords.size());
     }

    
    public void checkTimeoutOnDashboardPage()
    {
    	
    	/*
         * Created By : Saksham Kapoor
         * Created On : 17 Sep 2020
         * 
         */
    	
    	if(Utils.isElementPresent(driver, "div[class='elementInlineError']", "css"))
    	{
    		Log.failsoft("'Timeout Error' present on Dashboard Page");
    	}
    	else
    	{
    		Log.pass("'Timeout Error' not present on Dashboard Page");
    	}
    }
}
