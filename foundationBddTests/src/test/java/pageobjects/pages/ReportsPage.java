package pageobjects.pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.collections.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Attribute;
import com.codeborne.selenide.conditions.Disabled;
import com.codeborne.selenide.conditions.Enabled;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.collections.SizeGreaterThan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import pageobjects.utility.SelenideHelper;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.select.Evaluator.IndexGreaterThan;
import org.junit.Assert;
import dataobjects.ReportTemplate;

public class ReportsPage {

    private final String REPORT_TEMPLATE_STATUS_WITH_TEXT = "(//*[@id='template_status']//li)[text()='%s']";
    private final String PDF_VIEWER_IFRAME = "//iframe[@id='ReportViewIFrame']";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS = "(//*[@class='active-label'])";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT = XPATH_ACTIVE_TEMPLATE_STATUS + "[text()='%s']";
    private final String XPATH_SIGNED_REPORT = "//tr[td='Signed' and td='%s' and td='%s']";
    private final String XPATH_REPORT_NAME = "//td[text()='%s']";
    private final String XPATH_TEMPLATE_CHECKBOX = "//div[@class='item_value'][text()='%s']/ancestor::li/div[@class='check_box']";
    private final String XPATH_TEMPLATE_EYEICON = "//div[@class='item_value'][text()='%s']/ancestor::li/div[@class='eye_icon']";    
    private final String XPATH_CONSOLIDATED_REPORT = "//*[@class='tbl-row']//td[text()='%s']";
    private final String XPATH_CHECKBOX_CONSOLIDATED_REPORT = "//td[text()='%s']/ancestor::tr//*[@class='checkbox']";
    private final String XPATH_NOTIFICATION_TEXT = "//*[@class='notification-summary'][contains(text(),'%s')]";
    private final String XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@class='table']//td[text()='%s']";
    private final String XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@id='foundationRunListTable']//td[text()='%s']";
    private final String XPATH_TEMPLATE_COLUMNS_BY_TEXT = "//table[@id='templateListTable']//td[text()='%s']";
    private final String XPATH_REPORT_COLUMNS_BY_TEXT = "//table[@id='reportListTable']//td[text()='%s']";
    private final String XPATH_DROPDOWN = "//span[text()='%s']/ancestor::div[@class='custom-drop-down']";
    private final String XPATH_OPTION_DROPDOWN = "//option[@value='%s']/ancestor::li";
    private final String XPATH_RunsColumnName = "//*[@id=\"foundationRunListTable\"]/thead/tr/th[%d]";
    private final String XPATH_RunsColumnName_Value = "//*[@id=\"foundationRunListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_TemplateColumnName = "//*[@id=\"templateListTable\"]/thead/tr/th[%d]";
    private final String XPATH_TemplateColumnName_Value = "//*[@id=\"templateListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_ReportColumnName = "//*[@id=\"reportListTable\"]/thead/tr/th[%d]";
    private final String XPATH_ReportColumnName_Value = "//*[@id=\"reportListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_TRENDS_PARAMETERS = "//*[@id='%s']/div[1]";
    private final String XPATH_TRENDS_PARAMS = "//*[@class='item_value'][text()='%s']/preceding-sibling::div[@class='check_box']";
    
    private final SelenideElement reportsManagementPage = $(By.id("ReportManagement"));
    private final SelenideElement runTab = $(By.xpath("//a[text()='Runs']"));
    private final SelenideElement templateTab = $(By.xpath("//a[text()='Templates']"));
    private final SelenideElement reportTab = $(By.xpath("//a[text()='Reports']"));
    private final SelenideElement selectReportDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement selectReportRunReportTemplateDropDown =
            $(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']"));
    private final ElementsCollection optionsReportTemplate = $$(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']//ul//li//option"));
    private final ElementsCollection foundationRunListTable = $$(By.xpath("//*[@id='foundationRunListTable']/tbody/tr"));
    private final ElementsCollection templateListTable = $$(By.xpath("//*[@id='templateListTable']/tbody/tr"));
    private final ElementsCollection reportListTable = $$(By.xpath("//*[@id='reportListTable']/tbody/tr"));    
    private final SelenideElement reportGenerateButton = $(By.xpath("//button[text()='Generate']"));
    private final SelenideElement reportViewButton = $(By.xpath("//button[text()='View']"));
    private final SelenideElement createButton = $(By.xpath("//button[text()='Create']"));
    private final SelenideElement saveTemplateButton = $(By.xpath("//b[text()='Save']"));
    private final SelenideElement saveAlarmButton = $(By.xpath("//button[text()='Save']"));
    private final SelenideElement openButton = $(By.xpath("//button[text()='Open']"));
    private final SelenideElement primaryButton = $(By.xpath("//button[text()='SIGN']"));
    private final SelenideElement reportEsignButton = $(By.xpath("//button[text()='e-sign']"));
    private final SelenideElement inputPassword = $(By.xpath("//input[@type='password']"));
    private final SelenideElement reportSearch = $(By.xpath("//input[@placeholder='Search...']"));
    private final SelenideElement templateNameTextBox = $(By.xpath("//input[@placeholder='Create a template name']"));
    private final SelenideElement reportTemplateStatusIcon = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement reportTemplateLoadingIcon = $(By.xpath("//div[@class='spinner-circle']"));
    private final SelenideElement absentReportText = $(By.xpath("//*[@id='Report_View']//h4[text()='Report is either not available or corrupted.']"));
    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement trendsAddButton = $(By.xpath("//*[@id='add_btn']"));
    private SelenideElement trendsCancelButton = $(By.xpath("//*[@id='cancel_btn']"));
    private SelenideElement trendsSaveButton = $(By.xpath("//*[@id='done_btn']"));
    private SelenideElement trendsName = $(By.xpath("//*[@id='trend_name']/input"));
    private final SelenideElement errorMsgSameTemplateName = $(By.xpath("//div[contains(@class,'alert_msg')]"));
    private final SelenideElement errorMsgTemplateApproval = $(By.xpath("//span[@class='validate-error']"));
    private final ElementsCollection checkBoxTemplate = $$(By.xpath("//ul[@id='checkbox_list']/li"));
    private final SelenideElement XPATH_ERRORNOTIFICATION = $(By.xpath("//*[text()='Maximum of 5 sensors allowed']"));
    private final String duplicateNameNotification = "Failed to create report template because %s already exists. Use a different name.";

    
    public void goToReports() {
        reportsManagementPage.click();
    }

    public void switchToFrame() {
        SelenideHelper.goToIFrame();
    }
    
    public void verifyList(String tab) throws InterruptedException { 
    	switch (tab){
    	case "runs": 
    		$$(foundationRunListTable).shouldHave(CollectionCondition.size($$(foundationRunListTable).size()));   	 
    		break;  
        case "templates": 
        	$$(templateListTable).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));   	 
    		break;  
        case "reports": 
        	Thread.sleep(5000);
        	$$(reportListTable).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
    		break;  
    	}
    	}
    
    public void verifyTabs() {  	
    	runTab.shouldBe(visible);
    	templateTab.shouldBe(visible);
    	reportTab.shouldBe(visible);
    }
    
    public void verifyColoumn(String columnName, String tab, int columnIndex) {
    	switch (tab){
    	case "runs": 
    		$(By.xpath(String.format(XPATH_RunsColumnName, columnIndex))).shouldHave(text(columnName));
    		Assert.assertFalse($(By.xpath(String.format(XPATH_RunsColumnName_Value, columnIndex))).getText().isBlank()); 
    		break;  
        case "templates": 
        	$(By.xpath(String.format(XPATH_TemplateColumnName, columnIndex))).shouldHave(text(columnName));
        	Assert.assertFalse($(By.xpath(String.format(XPATH_TemplateColumnName_Value, columnIndex))).getText().isBlank()); 
        	break;  
        case "reports": 
        	$(By.xpath(String.format(XPATH_ReportColumnName, columnIndex))).shouldHave(text(columnName));
        	Assert.assertFalse($(By.xpath(String.format(XPATH_ReportColumnName_Value, columnIndex))).getText().isBlank()); 
        	break;  
    	}
    	}
    

    public void selectReport(String reportname) {
        SelenideHelper.commonWaiter(selectReportDropdown, visible).click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, reportname))).click();
    }

    public void generateReport() {
        reportGenerateButton.waitUntil(visible, 10000l);
        reportGenerateButton.click();
    }

    public void gotoReportsTab() {
        reportTab.click();
    }

    public void gotoRunTab() {
        runTab.click();
    }

    public void viewReports(String reportName) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, reportName))).click();
        reportViewButton.click();
    }

    public void esignReports(String reportName, String password) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, reportName))).click();
        reportEsignButton.click();
        inputPassword.sendKeys(password);
        commonWaiter(primaryButton, visible).click();
    }

    public void gotoTemplate() {
        templateTab.click();
    }

    public void createTemplate(String templateName) {
        createButton.click();
        templateNameTextBox.setValue(templateName);

    }

    public void approveTemplate(String templateName, String password, String status) {
        openReportTemplate(templateName);
        SelenideHelper.commonWaiter(reportTemplateStatusIcon, visible).click();
        changeStatus(status);
        saveReportTemplate();
        inputPassword.shouldBe(visible);
        inputPassword.sendKeys(password);
        primaryButton.click();
    }

    public void putReportTemplateToReview(String templateName, String status) {
        openReportTemplate(templateName);
        reportTemplateStatusIcon.click();
        changeStatus(status);
    }

    private void changeStatus(String status) {
        var reviewStatus = $(By.xpath(String.format(REPORT_TEMPLATE_STATUS_WITH_TEXT, status)));
        SelenideHelper.commonWaiter(reviewStatus, visible).click();
        var activeStatus = $(By.xpath(String.format(XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT, status)));
        SelenideHelper.commonWaiter(activeStatus, visible).click();
    }

    public void searchReportOrTemplate(String templateName) {
        SelenideHelper.commonWaiter(reportSearch, visible).setValue(templateName);
        SelenideHelper.commonWaiter(reportTemplateLoadingIcon, not(visible));
    }
    
    public void editReportOrTemplate(String templateName) {
    	openReportTemplate(templateName);

        
    }

    public String getStatus() {
        return $(By.xpath(XPATH_ACTIVE_TEMPLATE_STATUS)).getText();
    }

    public void openReportTemplate(String templateName) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, templateName))).click();
        openButton.click();
        SelenideHelper.commonWaiter(reportTemplateLoadingIcon, not(visible));
    }

    public String waitAndGetGeneratedNameFromNotificationWhenFileGenerated() {
        return waitAndGetGeneratedNameFromNotification("Report file generated");
    }

    public String waitAndGetGeneratedNameFromNotificationWhenFileSigned() {
        return waitAndGetGeneratedNameFromNotification("Report file signed");
    }

    private String waitAndGetGeneratedNameFromNotification(String text) {
        switchTo().parentFrame();
        SelenideElement notificationText = $(By.xpath(String.format(XPATH_NOTIFICATION_TEXT, text)));
        waitForReportGeneration(notificationText, visible);
        var name = notificationText.text().split(": ")[1];
        waitForReportGeneration(notificationText, not(visible));
        return name;
    }

    public void exists(String name) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, name))).shouldBe(visible);
    }

    public void checkSigned(String reportName, String username) {
        var reportSigned = $(By.xpath(String.format(XPATH_SIGNED_REPORT, reportName, username)));
        reportSigned.shouldBe(visible);
    }

    public void waitForReportGeneration(SelenideElement element, Condition condition) {
        element.waitUntil(condition, 3 * 60 * 1000l, 500l);
    }

    public void checkReportPdfInPage() {
        absentReportText.should(not(visible));
    }
    
    public void selectParams(String parameter) throws InterruptedException {
    	
    	commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))),visible);
    	$(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))).click();
    }
    
    public void createTrends(){
      		
    	for (int j=0; j<5; j++) {
    		
    		for(int i=1; i<6; i++) {
    			commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_")+i)))),visible);
    			$(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_")+i)))).click();
    	}
			SelenideHelper.commonWaiter(trendsName,visible);         
    		trendsName.setValue(RandomStringUtils.randomAlphabetic(10));
    		trendsSaveButton.click();
    		trendsAddButton.click();
    	
    	}
    }
    
    public void verifySixthChartNotAllowed() {
    	trendsAddButton.click();
    	Assert.assertFalse(trendsAddButton.isEnabled());
    	trendsCancelButton.click();
    	
    }
    
    
    public void isGeneratedNotificationWhenMoreThanSixParams(String message) {   	
        XPATH_ERRORNOTIFICATION.shouldHave(text(message));  
    }

    public void includeReport(String reportInclude) {
    	if(reportInclude=="Alarms") {
    		$(By.xpath(String.format(XPATH_TEMPLATE_EYEICON, reportInclude))).click();    		
    		$(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Process"))).click();
    		$(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "System"))).click();
    		saveAlarmButton.click();
    		$(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).isSelected();
    		
    	}
    	else if(reportInclude.contains("Trends")){
    		SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_EYEICON, reportInclude))), visible);
    		$(By.xpath(String.format(XPATH_TEMPLATE_EYEICON, reportInclude))).click();
    		trendsAddButton.click();
    		
    	}
    	else {
    		$(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).click();
    	}
         }

    public void saveReportTemplate() {
        saveTemplateButton.click();
    }

    public void selectRun(String run) {
        $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).click();
    }

    public void selectRunWithWaiting(String reportTemplateName, String run) throws InterruptedException {

        // after finished a recipe, it takes some times to have the run in page
        // polling report run page
        SelenideHelper.fluentWaiter().until((webDriver) -> {
            boolean isRunVisible = $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).is(visible);

            if(!isRunVisible) {
                WebDriverRunner.getWebDriver().switchTo().parentFrame();
                goToReports();
                switchToFrame();
                selectReport(reportTemplateName);
            }

            return isRunVisible;
        });

        // select corresponding run
        $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).click();
    }

    public void chooseReportTemplate(String template) {
        selectReportRunReportTemplateDropDown.click();
        for (var option : optionsReportTemplate) {
            if (option.getValue().equals(template)) {
                option.parent().click();
                break;
            }
        }
    }

    public void selectForConsolidationRun(String run) {
        $(By.xpath(String.format(XPATH_CHECKBOX_CONSOLIDATED_REPORT, run))).click();
    }

    public void selectTemplateStatus(String templateStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        $(By.xpath(String.format("//span[text()='%s']", templateStatus))).click();
        applyFilterButton.click();
    }

    public void selectReportType(String reportType) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        $(By.xpath(String.format("//span[text()='%s']", reportType))).click();
        applyFilterButton.click();
    }

    public void selectRunStatus(String runReportStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        $(By.xpath(String.format("//span[text()='%s']", runReportStatus))).click();
        applyFilterButton.click();
    }

    public void selectCreatedBy(String user) {
        commonWaiter($(By.xpath(String.format(XPATH_DROPDOWN, "Created by"))), visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))), visible).click();
    }

    public void checkTableContainsRecipeRun(String recipeRun) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT, recipeRun))), visible);
    }

    public void checkConsolidatedTableContainsRecipeRun(String recipeRun) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT, recipeRun))), visible);
    }

    public void checkTableContainsReport(String reportName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_REPORT_COLUMNS_BY_TEXT, reportName))), visible);
        for (int i=1;i<7;i++) {
        Assert.assertFalse($(By.xpath(String.format(XPATH_ReportColumnName_Value, i))).getText().isBlank());
            }
        }
        

    public void checkTableContainsTemplate(String templateName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_COLUMNS_BY_TEXT, templateName))), visible);
    }

    public void selectSignedBy(String user) {
        commonWaiter($(By.xpath(String.format(XPATH_DROPDOWN, "Signed by"))), visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))), visible).click();
    }

    public String getPdfUrl() {
        return $(By.xpath(PDF_VIEWER_IFRAME)).getAttribute("src");
    }
    
    public void errorMessage(String name) {
    	               commonWaiter(errorMsgSameTemplateName, visible);
    	               String expectedNotificationText = String.format(duplicateNameNotification, name);
    	               errorMsgSameTemplateName.shouldHave(text(expectedNotificationText));
    	       }
    	
    	    public void errorMessageValidation(String name) {
    	       commonWaiter(errorMsgTemplateApproval,visible);
    	       errorMsgTemplateApproval.shouldHave(text(name));
        }
    	
    	    public void approvedTemplateValidation(){
    	       SelenideHelper.commonWaiter(templateNameTextBox, disabled);
    	         for (var option : checkBoxTemplate) {
    	                
    	              if (option.getAttribute("class").contains("disabled")) {
    	                  Assert.assertTrue(true);
    	                  break;
                  }
    	              else
    	                 Assert.assertTrue(false);
    	          }
    	    }

}
