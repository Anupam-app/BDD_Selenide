package pageobjects.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;


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
    private final String XPATH_Date_DROPDOWN = "//li[text()='%s']";
    private final String XPATH_RunsColumnName = "//*[@id=\"foundationRunListTable\"]/thead/tr/th[%d]";
    private final String XPATH_RunsColumnName_Value = "//*[@id=\"foundationRunListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_TemplateColumnName = "//*[@id=\"templateListTable\"]/thead/tr/th[%d]";
    private final String XPATH_TemplateColumnName_Value = "//*[@id=\"templateListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_ReportColumnName = "//*[@id=\"reportListTable\"]/thead/tr/th[%d]";
    private final String XPATH_ReportColumnName_Value = "//*[@id=\"reportListTable\"]/tbody/tr[%d]/td[%d]";
    private final String XPATH_UserColumnName_Value = "//*[@id=\"auditListTable\"]/tbody/tr[%d]/td[4]";
    private final String XPATH_DateColumnName_Value = "//*[@id=\"auditListTable\"]/tbody/tr[%d]/td[1]";
    private final String XPATH_TRENDS_PARAMETERS = "//*[@id='%s']/div[1]";
    private final String XPATH_TRENDS_PARAMS = "//*[@class='item_value'][text()='%s']/preceding-sibling::div[@class='check_box']";
    private final String XPATH_TEMPLATE_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_TEMPLATE_TABLE = "//table[@id='templateListTable']";
    private final String XPATH_TEMPLATE_COLUMNS = XPATH_TEMPLATE_TABLE + "//td[%s]";

    private final SelenideElement reportsManagementPage = $(By.id("ReportManagement"));
    private final SelenideElement runTab = $(By.xpath("//a[text()='Runs']"));
    private final SelenideElement templateTab = $(By.xpath("//a[text()='Templates']"));
    private final SelenideElement reportTab = $(By.xpath("//a[text()='Reports']"));
    private final SelenideElement selectReportDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement selectUserDropdownRunPage = $(By.xpath("//span[text()='Select User']/following-sibling::*"));
    private final SelenideElement selectDateDropdownRunPage = $(By.xpath("//*[@id='calendar']"));
    private final SelenideElement selectUserDropdown = $(By.xpath("//div[@class='user-list-drop-down']/div/div/span[2]"));
    private final SelenideElement selectReportRunReportTemplateDropDown =
            $(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']"));
    private final ElementsCollection optionsReportTemplate = $$(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']//ul//li//option"));
    private final ElementsCollection foundationRunListTable = $$(By.xpath("//*[@id='foundationRunListTable']/tbody/tr"));
    private final ElementsCollection auditListTable = $$(By.xpath("//*[@id='auditListTable']/tbody/tr"));
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
    private SelenideElement trendsName = $(By.xpath("//input[@class='trendName_textbox']"));
    private final SelenideElement errorMsgSameTemplateName = $(By.xpath("//div[contains(@class,'alert_msg')]"));
    private final SelenideElement errorMsgTemplateApproval = $(By.xpath("//span[@class='validate-error']"));
    private final ElementsCollection checkBoxTemplate = $$(By.xpath("//ul[@id='checkbox_list']/li"));
    private final SelenideElement XPATH_ERRORNOTIFICATION = $(By.xpath("//*[text()='Maximum of 5 sensors allowed']"));
    private final String duplicateNameNotification = "Failed to create report template because %s already exists. Use a different name.";

    private final SelenideElement saveAs_btn = $(By.xpath("//*[text()='Save As']"));
    private final SelenideElement saveTemplateAs = $(By.xpath("//div[@class='input-wrapper']/input"));
    private final SelenideElement saveTemplateTxt = $(By.xpath("//div[text()='Save Template As']"));
    private final SelenideElement saveBtn = $(By.xpath("//button[text()='Save']"));
    private final SelenideElement notificationMsg = $(By.xpath("//div[@role='alert']"));
    private final SelenideElement column_temp = $(By.xpath("//table[@id='templateListTable']/tbody/tr[1]/td[2]"));
    private final SelenideElement selectTemp = $(By.xpath("//tr[@class='tbl-row selected_row']/td"));

    private SelenideElement arrowIcon = $(By.xpath("//div[(@class='down-icon')]"));
    private ElementsCollection dateRange = $$(By.xpath("//div[(@class='ranges')]//li[i]"));
    private final SelenideElement dateColumn = $(By.xpath("//input[@name='dateRange']"));
    private final SelenideElement datePopup = $(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright') and contains(@style,'block')]"));
    private ElementsCollection dateOptionsRprt = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opens')]/div/ul/li"));
    private ElementsCollection dateOptions = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright')]/div/ul/li"));
    private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter.']"));
    private final SelenideElement startDateDesendingArrow = $(By.xpath("//th[text()='Start Date']/span[@class='order']"));
    private final SelenideElement startDateAsendingArrow = $(By.xpath("//th[text()='Start Date']/span[@class='react-bootstrap-table-sort-order dropup']"));
    private final SelenideElement startDateRep = $(By.xpath("//table[@id='reportListTable']/tbody/tr[1]/td[2]"));
    private final SelenideElement statusColumn = $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[4]"));
    private final SelenideElement consolidateColumn = $(By.xpath("//table[@class='table']/tbody/tr[1]/td[6]"));
    private final SelenideElement startDate = $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[2]"));
    private final SelenideElement clearAllFilters = $(By.xpath("//div[text()='Clear All']"));
    private final SelenideElement previousMonth = $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
    private ElementsCollection availableDates = $$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
    private final SelenideElement processType = $(By.xpath("//div[text()='Process Types']"));
    private final SelenideElement status = $(By.xpath("//div[text()='Status']"));
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";
    private final String XPATH_USER_TABLE = "//table[@id='foundationRunListTable']";
    private final String XPATH_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_REPORT_COLUMNS = "//table[@id='foundationRunListTable']//td[%s]";
    private final String XPATH_REPORTS_COLUMNS = "//table[@id='reportListTable']//td[%s]";
    private final String recipeAuditLogs ="//*[@id='auditListTable']/tbody/tr/td[5][contains(text(),'%s') and contains(text(),'%s') and contains(text(),'%s')]";
    private final String XAPATH_CONSOLIDATED_COLUMNS = "//table[@class='table table-hover']//th[text()='%s']";

    Function<Integer, List<String>> getReportColumns = (index) -> {
        var users = $$(By.xpath(String.format(XPATH_REPORT_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };

    Function<Integer, List<String>> getReportsColumns = (index) -> {
        var users = $$(By.xpath(String.format(XPATH_REPORTS_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };

    Function<Integer, List<String>> consolidatedColumns = (index) -> {
        var users = $$(By.xpath(String.format(XAPATH_CONSOLIDATED_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };

    Function<Integer, List<String>> getTemplateColumns = (index) -> {
        var templates = $$(By.xpath(String.format(XPATH_TEMPLATE_COLUMNS, index))).texts();
        templates.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return templates;
    };

    private SelenideElement date;


    public void goToReports() {
        commonWaiter(reportsManagementPage, visible);
        reportsManagementPage.click();
    }

    public void switchToFrame() {
        SelenideHelper.goToIFrame();
    }

    public void verifyList(String tab) throws InterruptedException {
        switch (tab) {
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
        	break;  
    	}
    }

    public void selectReport(String reportname) {
        SelenideHelper.commonWaiter(selectReportDropdown, visible).click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, reportname))).click();
    }

    public void selectUser(String user) {
        SelenideHelper.commonWaiter(selectReportDropdown, visible).click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))).click();
    }

    public void selectUserOnRunPage(String user) {
        SelenideHelper.commonWaiter(selectUserDropdownRunPage, visible).click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))).click();
    }

    public void selectDateFilterOnRunPage(String dateFilter) {
        SelenideHelper.commonWaiter(selectDateDropdownRunPage, visible).click();
        $(By.xpath(String.format(XPATH_Date_DROPDOWN, dateFilter))).click();
    }

    public void generateReport() {
        reportGenerateButton.waitUntil(visible, 10000l);
        reportGenerateButton.click();
    }

    public void verifyGenerateButton() {
        Assert.assertFalse(reportGenerateButton.is(visible));
    }
    
    public void checkRecipeCTRLOperationLogs(String batchId, String recipeName) {
    	$(By.xpath(String.format(recipeAuditLogs, "acknowledged", recipeName, batchId))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "aborted", recipeName, batchId))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "step jump", recipeName, batchId))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "resumed", recipeName, batchId))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "paused", recipeName, batchId))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "Bio4CAdmin", "system", "restart"))).shouldBe(visible);
    	$(By.xpath(String.format(recipeAuditLogs, "Bio4CAdmin", "system", "hold"))).shouldBe(visible);
    }

    public void gotoReportsTab() {
        reportTab.click();
    }

    public void gotoRunTab() {
        runTab.click();
    }

    public void sortListTemplate(String columnName, boolean descending) {
        SelenideElement sortAction = getTemplateColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public SelenideElement getTemplateColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_TEMPLATE_COLUMN_HEADER, columnName)));
    }

    public void checkSortedElementTemplate(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getTemplateColumnHeaders(), columnName, descending, getTemplateColumns);
    }

    public List<String> getTemplateColumnHeaders() {
        return $$(By.xpath(XPATH_TEMPLATE_TABLE + "//th")).texts();
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

    public String waitAndGetGeneratedNameFromNotificationWhenFileGenerated() throws InterruptedException {
        return waitAndGetGeneratedNameFromNotification("Report file generated");
    }

    public String waitAndGetGeneratedNameFromNotificationWhenFileSigned() throws InterruptedException {
        return waitAndGetGeneratedNameFromNotification("Report file signed");
    }

    private String waitAndGetGeneratedNameFromNotification(String text) throws InterruptedException {
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

    public void waitForReportGeneration(SelenideElement element, Condition condition) throws InterruptedException {
        element.waitUntil(condition, 3 * 60 * 1000l, 500l);
        Thread.sleep(1000);
    }

    public void checkReportPdfInPage() {
        absentReportText.should(not(visible));
    }

    public void selectParams(String parameter) throws InterruptedException {

        commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))), visible);
        $(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))).click();
    }

    public void saveTrends() {
        trendsName.waitUntil(visible, 10000).setValue(RandomStringUtils.randomAlphabetic(10));
        trendsSaveButton.click();
        trendsCancelButton.click();
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

    public void create5Trends() {
        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < 6; i++) {
                commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_") + i)))), visible);
                $(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_") + i)))).click();
            }
            trendsName.waitUntil(visible, 10000).setValue(RandomStringUtils.randomAlphabetic(10));
            trendsSaveButton.click();
            trendsAddButton.click();
        }
    }

    public void selectParameters(String noOfParams, String parameters) {
        int count = Integer.parseInt(noOfParams);
        List<String> list = new ArrayList<String>();
        var config = ConfigFactory.parseResourcesAnySyntax(parameters, ConfigParseOptions.defaults());
        var params = config.getConfigList("Params.list");
        for (var param : params) {
            list.add(param.getString("value"));
        }
        if (trendsAddButton.exists()) {
            trendsAddButton.click();
        }
        for (int i = 0; i < count; i++) {
            commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMS, list.get(i)))), visible);
            $(By.xpath(String.format(XPATH_TRENDS_PARAMS, list.get(i)))).click();
        }
    }

    public void selectRunWithWaiting(String reportTemplateName, String run) {
        // after finished a recipe, it takes some times to have the run in page
        // polling report run page
        SelenideHelper.fluentWaiter().until((webDriver) -> {
            boolean isRunVisible = $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).is(visible);

            if (!isRunVisible) {
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
        commonWaiter($(By.xpath(String.format(XPATH_CHECKBOX_CONSOLIDATED_REPORT, run))), visible).click();
    }

    public boolean isRunDisplayed(String run) {
        return $(By.xpath(String.format(XPATH_CHECKBOX_CONSOLIDATED_REPORT, run))).isDisplayed();
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
        for(int row=1;row<=reportListTable.size();row++) {
        	if($(By.xpath(String.format(XPATH_ReportColumnName_Value,row, 1))).getText().equalsIgnoreCase(reportName)) {
        		for (int i=1;i<7;i++) {
        	        Assert.assertFalse($(By.xpath(String.format(XPATH_ReportColumnName_Value,row, i))).getText().isBlank());
        	    }
        	break;
        	}
        }
    }
    
    public void checkTableContainsUserAndDateRange(String userid) throws InterruptedException, ParseException {
    	Thread.sleep(5000);
        for (int i=1;i<=(auditListTable.size());i++) {
        	Assert.assertTrue($(By.xpath(String.format(XPATH_UserColumnName_Value, i))).getText().toLowerCase().contains(userid));
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        	Date currentdate = new Date();
        	String eventDate = ($(By.xpath(String.format(XPATH_DateColumnName_Value, i))).getText().replaceAll("\\s.*", ""));
        	Date eventDateTime = formatter.parse(eventDate);
        	int diffInDays = (int)( (currentdate.getTime() - eventDateTime.getTime())
        	        / (1000 * 60 * 60 * 24) );
        	if (diffInDays <= 7) {
        		
        		Assert.assertTrue(true);
        	}
        	else {
        		Assert.assertTrue(false);
        	}
        	
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

    public void approvedTemplateValidation() {
        SelenideHelper.commonWaiter(templateNameTextBox, disabled);
        for (var option : checkBoxTemplate) {

            if (option.getAttribute("class").contains("disabled")) {
                Assert.assertTrue(true);
                break;
            } else
                Assert.assertTrue(false);
        }
    }

    public void errorMessageValidation(String name) {
        commonWaiter(errorMsgTemplateApproval, visible);
        errorMsgTemplateApproval.shouldHave(text(name));
    }

    public void selectrunStatus(String status) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        if (clearAllFilters.isDisplayed()) {
            clearAllFilters.click();
        }
        arrowIcon.click();
        if (!$(By.xpath(String.format("//span[text()='%s']", status))).isSelected()) {
            $(By.xpath(String.format("//span[text()='%s']", status))).click();
        }
        applyFilterButton.click();
    }

    public boolean verifyRunStatus(String status) {
        boolean isTrue = false;
        if (!(commonWaiter(statusColumn, appear).isDisplayed())) {
            isTrue = noDatamsg.isDisplayed();
        } else {
            isTrue = commonWaiter(statusColumn, appear).getText().equalsIgnoreCase(status);
        }
        return isTrue;
    }

    public void selectDateRange(String option) throws InterruptedException {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        ElementsCollection options = dateOptions;
        for (SelenideElement element : options) {
            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index).click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index).click();

        }

    }

    public boolean verifyDateRanges(String dateRange) throws ParseException, InterruptedException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                String dateValue = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue);
                if (startDate.isDisplayed()) {
                    sortList("Start Date", false);
                    String startDateRow1 = startDate.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    sortList("Start Date", true);
                    startDateRow1 = startDate.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    if (selectedAsendingDate.equals(selectedDate) && selectedDesendingDate.equals(selectedDate)) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
            case "Last 7 Days":
            case "Last 30 Days":
            case "This Month":
            case "Last Month":
            case "Custom Range":
                commonWaiter(dateColumn, visible);
                String dateValue1 = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate1 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue1);
                String dateValue2 = dateColumn.getAttribute("value").split("to")[1].trim();
                Date selectedDate2 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue2);
                if (startDate.isDisplayed()) {
                    sortList("Start Date", false);
                    String startDateRow = startDate.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow);
                    sortList("Start Date", true);
                    String endDateRow = startDate.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(endDateRow);
                    if ((selectedAsendingDate.equals(selectedDate1) || selectedAsendingDate.after(selectedDate1))
                            && (selectedDesendingDate.equals(selectedDate2)
                            || selectedDesendingDate.before(selectedDate2))) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
        }
        return isTrue;
    }

    public SelenideElement getReportColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_COLUMN_HEADER, columnName)));
    }

    public List<String> getAllReportsColumnHeaders() {
        return $$(By.xpath(XPATH_USER_TABLE + "//th")).texts();
    }

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getReportColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, getReportColumns);
    }


    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void iValidation() {
        if (saveAs_btn.isEnabled()) {
            saveAs_btn.shouldBe(visible);
        }
    }

    public void iSaveAs() {
        saveAs_btn.click();
    }

    public void ivalidateWindow() {
        saveTemplateTxt.shouldBe(visible);
    }

    public void iRename(String templateName) throws InterruptedException {
        saveTemplateAs.click();
        saveTemplateAs.clear();
        saveTemplateAs.setValue(templateName);
        isave();
    }

    public void isave() {
        saveBtn.click();
    }

    public void putReportTemplateToinactive(String templateName, String status) {
        openReportTemplate(templateName);
        reportTemplateStatusIcon.click();
        changeStatus(status);
        saveReportTemplate();
    }

    public void iCheckNotifactionMsg(String status) {
        SelenideHelper.commonWaiter(notificationMsg, visible);
    }

    public void iSearchrepo(String templateName) {
        SelenideHelper.commonWaiter(reportSearch, visible).setValue(templateName);
        SelenideHelper.commonWaiter(reportTemplateLoadingIcon, not(visible));
    }

    public boolean iValidationdraft() {
        boolean isTrue = false;
        if (!column_temp.isDisplayed()) {
            column_temp.shouldNotBe(visible);
        } else {
            column_temp.shouldBe(visible);
        }
        return isTrue;
    }

    public void selectDateRprt(String daterange) {

        ElementsCollection options = dateRange;
        options.shouldBe(CollectionCondition.size(7));
        for (SelenideElement d : options) {
            for (int i = 0; i < options.size(); i++) {
                date.click();
                options.get(i).click();
            }
        }
    }

    public void selectDateRangeRprt(String option) throws InterruptedException {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        ElementsCollection options = dateOptionsRprt;
        for (SelenideElement element : options) {
            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index).click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index).click();

        }

    }

    public boolean verifyDateRangesRprt(String dateRange) throws ParseException, InterruptedException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                String dateValue = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue);
                if (startDateRep.isDisplayed()) {
                    sortList("Date Generated", false);
                    String startDateRow1 = startDateRep.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    sortList("Date Generated", true);
                    startDateRow1 = startDateRep.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow1);
                    if (selectedAsendingDate.equals(selectedDate) && selectedDesendingDate.equals(selectedDate)) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
            case "Last 7 Days":
            case "Last 30 Days":
            case "This Month":
            case "Last Month":
            case "Custom Range":
                commonWaiter(dateColumn, visible);
                String dateValue1 = dateColumn.getAttribute("value").split("to")[0].trim();
                Date selectedDate1 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue1);
                String dateValue2 = dateColumn.getAttribute("value").split("to")[1].trim();
                Date selectedDate2 = new SimpleDateFormat("dd/MMM/yyyy").parse(dateValue2);
                if (startDateRep.isDisplayed()) {
                    sortList("Date Generated", false);
                    String startDateRow = startDateRep.getText().split(" ")[0].trim();
                    Date selectedAsendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(startDateRow);
                    sortList("Date Generated", true);
                    String endDateRow = startDateRep.getText().split(" ")[0].trim();
                    Date selectedDesendingDate = new SimpleDateFormat("dd/MMM/yyyy").parse(endDateRow);
                    if ((selectedAsendingDate.equals(selectedDate1) || selectedAsendingDate.after(selectedDate1))
                            && (selectedDesendingDate.equals(selectedDate2)
                            || selectedDesendingDate.before(selectedDate2))) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
        }
        return isTrue;
    }

    public void checkSortedElements(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, getReportsColumns);
    }

    public void checkSortedElementConsolidate(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, consolidatedColumns);
    }

    public void sortListConsolidated(String columnName, boolean descending) {
        SelenideElement sortAction = getReportColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void selectConsolidatedStatus(String consolidatedreportstatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        processType.shouldBe(visible);
        status.shouldBe(visible);
        if (clearAllFilters.isDisplayed()) {
            clearAllFilters.click();
        }
        if (!$(By.xpath(String.format("//span[text()='%s']", consolidatedreportstatus))).isSelected()) {
            $(By.xpath(String.format("//span[text()='%s']", consolidatedreportstatus))).click();
        }
        applyFilterButton.click();
    }

    public boolean verifyConsolidatedStatus(String status) {
        boolean isTrue = false;
        if (!consolidateColumn.isDisplayed()) {
            isTrue = noDatamsg.isDisplayed();
        } else {
            isTrue = consolidateColumn.getText().equalsIgnoreCase(status);
        }
        return isTrue;
    }
}