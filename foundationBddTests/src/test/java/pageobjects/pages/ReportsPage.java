package pageobjects.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

import dataobjects.Recipe;
import dataobjects.Report;
import pageobjects.components.SpinnerComponent;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;

public class ReportsPage {

    private final String REPORT_TEMPLATE_STATUS_WITH_TEXT = "(//*[@id='template_status']//li)[contains(text(),'%s')]";
    private final String PDF_VIEWER_IFRAME = "//iframe[@id='ReportViewIFrame']";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS = "(//*[@class='active-label'])";

    private final String XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT =
        XPATH_ACTIVE_TEMPLATE_STATUS + "[contains(text(),'%s')]";
    private final String XPATH_SIGNED_REPORT =
        "//tr//td[contains(text(),'Signed')]/ancestor::tr//td[contains(text(),'%s')]";
    private final String XPATH_REPORT_NAME = "//td[contains(text(),'%s')]";
    private final String XPATH_TEMPLATE_CHECKBOX =
        "//div[@class='item_value'][contains(text(),'%s')]/ancestor::li/div[@class='check_box']";
    private final String XPATH_TEMPLATE_CHECKBOX_CHECKED =
        "//li[@class=\"checkbox_item checkbox_focus \"]/div[@class='item_value'][contains(text(),'%s')]";
    private final String XPATH_TEMPLATE_EYE_ICON =
        "//div[@class='item_value'][contains(text(),'%s')]/ancestor::li/div[@class='eye_icon']";
    private final String XPATH_CONSOLIDATED_REPORT = "//*[@class='tbl-row']//td[contains(text(),'%s')]";
    private final String XPATH_CHECKBOX_CONSOLIDATED_REPORT =
        "//td[contains(text(),'%s')]/ancestor::tr//*[@class='checkbox']";
    private final String XPATH_NOTIFICATION_TEXT = "//*[@class='notification-summary'][contains(text(),'%s')]";

    private final String XPATH_NAV = "//div[@class='navWrapper']//h2";
    private final String XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT =
        "//table[@class='table']//td[contains(text(),'%s')]";
    private final String XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT =
        "//table[@id='foundationRunListTable']//td[contains(text(),'%s')]";
    private final String XPATH_TEMPLATE_COLUMNS_BY_TEXT = "//table[@id='templateListTable']//td[contains(text(),'%s')]";
    private final String XPATH_REPORT_COLUMNS_BY_TEXT = "//table[@id='reportListTable']//td[contains(text(),'%s')]";
    private final String XPATH_DROPDOWN = "//span[contains(text(),'%s')]/ancestor::div[@class='custom-drop-down']";
    private final String XPATH_OPTION_DROPDOWN = "//option[contains(text(),'%s')]/ancestor::li";
    private final String XPATH_DEVICE_OPTION_DROPDOWN = "//li[text()='%s']";
    private final String XPATH_Date_DROPDOWN = "//li[text()='%s']";
    private final String XPATH_RunsColumnName = "//*[@id=\"foundationRunListTable\"]/thead/tr/th[%d]";
    private final String XPATH_RunsColumnName_Value = "//*[@id=\"foundationRunListTable\"]/tbody/tr[%d]/td[%d]";
    private final String XPATH_AUDITLOGS_VALUE = "//*[@id='auditListTable']/tbody/tr[%d]/td[%d]";
    private final String XPATH_TemplateColumnName = "//*[@id=\"templateListTable\"]/thead/tr/th[%d]";
    private final String XPATH_TemplateColumnName_Value = "//*[@id=\"templateListTable\"]/tbody/tr/td[%d]";
    private final String XPATH_ReportColumnName = "//*[@id=\"reportListTable\"]/thead/tr/th[%d]";
    private final String XPATH_ReportColumnName_Value = "//*[@id=\"reportListTable\"]/tbody/tr[%d]/td[%d]";
    private final String XPATH_TRENDS_PARAMETERS = "//*[@id='%s']/div[1]";
    private final String XPATH_TRENDS_PARAMS =
        "//*[@class='item_value'][text()='%s']/preceding-sibling::div[@class='check_box']";

    private final String XPATH_TEMPLATE_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_TEMPLATE_TABLE = "//table[@id='templateListTable']";
    private final String XPATH_TEMPLATE_COLUMNS = XPATH_TEMPLATE_TABLE + "//td[%s]";
    private final String XPATH_UserColumnName_Value = "//*[@id=\"auditListTable\"]/tbody/tr[%d]/td[4]";
    private final String XPATH_DateColumnName_Value = "//*[@id=\"auditListTable\"]/tbody/tr[%d]/td[1]";

    private final SelenideElement reportsManagementPage = $(By.id("ReportManagement"));
    private final SelenideElement runTab = $(By.xpath("//a[contains(text(),'Runs')]"));
    private final SelenideElement templateTab = $(By.xpath("//a[contains(text(),'Templates')]"));
    private final SelenideElement reportTab = $(By.xpath("//a[contains(text(),'Reports')]"));

    private final SelenideElement selectReportDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement selectDeviceDropdown =
        $(By.xpath("//div[@class='restore-custom-drop-down']//span[@class='active-label']"));
    private final SelenideElement selectUserDropdownRunPage =
        $(By.xpath("//span[contains(text(),'Select User')]/following-sibling::*"));
    private final SelenideElement selectDateDropdownRunPage = $(By.xpath("//*[@id='calendar']"));
    private final SelenideElement selectReportRunReportTemplateDropDown =
        $(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']"));
    private final ElementsCollection optionsReportTemplate =
        $$(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']//ul//li//option"));

    private final SelenideElement reportGenerateButton = $(By.xpath("//button[contains(text(),'Generate')]"));
    private final SelenideElement reportViewButton = $(By.xpath("//button[contains(text(),'View')]"));
    private final SelenideElement createButton = $(By.xpath("//button[contains(text(),'Create')]"));
    private final SelenideElement saveTemplateButton = $(By.xpath("//b[contains(text(),'Save')]"));
    private final SelenideElement saveAlarmButton = $(By.xpath("//button[contains(text(),'Save')]"));
    private final SelenideElement openButton = $(By.xpath("//button[contains(text(),'Open')]"));
    private final SelenideElement primaryButton = $(By.xpath("//button[contains(text(),'SIGN')]"));
    private final SelenideElement reportESignButton = $(By.xpath("//button[contains(text(),'e-sign')]"));

    private final ElementsCollection foundationRunListTable =
        $$(By.xpath("//*[@id='foundationRunListTable']/tbody/tr"));
    private final ElementsCollection auditListTable = $$(By.xpath("//*[@id='auditListTable']/tbody/tr"));
    private final ElementsCollection templateListTable = $$(By.xpath("//*[@id='templateListTable']/tbody/tr"));
    private final ElementsCollection reportListTable = $$(By.xpath("//*[@id='reportListTable']/tbody/tr"));


    private final SelenideElement inputPassword = $(By.xpath("//input[@type='password']"));
    private final SelenideElement reportSearch = $(By.xpath("//input[contains(@placeholder,'Search...')]"));
    private final SelenideElement templateNameTextBox =
        $(By.xpath("//input[contains(@placeholder,'Create a Template Name')]"));
    private final SelenideElement reportTemplateStatusIcon = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement reportTemplateLoadingIcon = $(By.xpath("//div[@class='spinner-circle']"));
    private final SelenideElement absentReportText =
        $(By.xpath("//*[@id='Report_View']//h4[text()='Report is either not available or corrupted.']"));
    private final SelenideElement applyFilterButton = $(By.xpath("//span[contains(text(),'Apply Filters')]"));
    private final SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));


    private final SelenideElement saveAs_btn = $(By.xpath("//*[text()='Save As']"));
    private final SelenideElement saveTemplateAs = $(By.xpath("//div[@class='input-wrapper']/input"));
    private final SelenideElement saveTemplateTxt = $(By.xpath("//div[text()='Save Template As']"));
    private final SelenideElement saveBtn = $(By.xpath("//button[text()='Save']"));
    private final SelenideElement notificationMsg = $(By.xpath("//div[@role='alert']"));
    private final SelenideElement column_temp = $(By.xpath("//table[@id='templateListTable']/tbody/tr[1]/td[2]"));

    private final String recipeAuditLogs =
        "//*[@id='auditListTable']/tbody/tr/td[5][contains(text(),'%s') and contains(text(),'%s') and contains(text(),'%s')]";
    private final String userAuditLogs =
        "//*[@id='auditListTable']/tbody/tr/td[5][contains(text(),'%s') and contains(text(),'%s') and contains(text(),'%s')]";

    private final SelenideElement trendsAddButton = $(By.xpath("//*[@id='add_btn']"));
    private final SelenideElement trendsCancelButton = $(By.xpath("//*[@id='cancel_btn']"));
    private final SelenideElement trendsSaveButton = $(By.xpath("//*[@id='done_btn']"));
    private final SelenideElement trendsName = $(By.xpath("//input[@class='trendName_textbox']"));

    private final SelenideElement errorMsgSameTemplateName = $(By.xpath("//div[contains(@class,'alert_msg')]"));
    private final SelenideElement errorMsgTemplateApproval = $(By.xpath("//span[@class='validate-error']"));
    private final ElementsCollection checkBoxTemplate = $$(By.xpath("//ul[@id='checkbox_list']/li"));
    private final SelenideElement XPATH_ERROR_NOTIFICATION = $(By.xpath("//*[text()='Maximum of 5 sensors allowed']"));
    private final String duplicateNameNotification =
        "Failed to create report template because %s already exists. Use a different name.";


    private final SelenideElement arrowIcon = $(By.xpath("//div[(@class='down-icon')]"));
    private final SelenideElement dateColumn = $(By.xpath("//input[@name='dateRange']"));
    private final ElementsCollection dateOptionsReport =
        $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opens')]/div/ul/li"));
    private final ElementsCollection dateOptions =
        $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright')]/div/ul/li"));

    private final ElementsCollection dateOptionsConsolidated =
        $$(By.xpath("//div[contains(@class,'ranges')]/div/ul/li"));

    private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter']"));

    private final SelenideElement startDateRep = $(By.xpath("//table[@id='reportListTable']/tbody/tr[1]/td[2]"));
    private final SelenideElement statusColumn = $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[4]"));
    private final String COLUMN_DATA = "//table[@class='table']/tbody/tr[%d]/td[%d]";
    private final SelenideElement startDate = $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[2]"));
    private final SelenideElement consolidateStartDate = $(By.xpath("//table[@class='table']/tbody/tr[1]/td[5]"));

    private final SelenideElement clearAllFilters = $(By.xpath("//div[text()='Clear All']"));
    private final SelenideElement previousMonth =
        $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
    private final SelenideElement currentMonth =
        $(By.xpath("//div[@class='drp-calendar right']//th[@class='prev available']"));
    private final ElementsCollection availableDates =
        $$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
    private final String selectedDatePreviousMonth =
        "//div[@class='drp-calendar left']/div/table/tbody/tr/td[contains(@class,'available') and (text()='%d') and not(contains(@class,'off'))]";
    private final String selectedDateCurrentMonth =
        "//div[@class='drp-calendar right']/div/table/tbody/tr/td[contains(@class,'today') and (text()='%d')]";

    private final SelenideElement processType = $(By.xpath("//div[text()='Process Type']"));
    private final SelenideElement status = $(By.xpath("//div[text()='Status']"));
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";
    private final String XPATH_USER_TABLE = "//table[@id='foundationRunListTable']";
    private final String XPATH_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_REPORT_COLUMNS = "//table[@id='foundationRunListTable']//td[%s]";
    private final String XPATH_REPORTS_COLUMNS = "//table[@id='reportListTable']//td[%s]";
    private final String XPATH_ESIGN_STATUS = "//span[text()='%s']";

    private final String XPATH_CONSOLIDATED_COLUMNS = "//table[@class='table table-hover']//th[text()='%s']";
    private final SelenideElement filterSelection = $(By.xpath("//div[@class='filter-criteria-tag']"));
    private final SelenideElement requestNotification =
        $(By.xpath("//div[@class='alert_msg alert alert-info alert-dismissible fade show']"));
    private final SelenideElement runColumn = $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[1]"));
    private final SelenideElement processTypeValue =
        $(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[3]"));
    private final SelenideElement eventTime = $(By.xpath("//table[@id='auditListTable']/tbody/tr[1]/td[1]"));
    private final SelenideElement comment = $(By.xpath("//table[@id='auditListTable']/tbody/tr[1]/td[5]"));
    private final SelenideElement record = $(By.xpath("//table[@id='auditListTable']/tbody/tr[1]/td[3]"));

    private final SelenideElement reportManagementHeader = $(By.xpath("//h2[text()='Report Management']"));
    private final SelenideElement XPATH_ERROR_NOTIFICATION_TEXT = $(By.xpath("//*[@class='trend_name_error_text']"));
    private final SpinnerComponent spinnerComponent = new SpinnerComponent();
    private final SelenideElement searchTextReportManagement = $(By.id("search"));
    private final SelenideElement templateCancelButton = $(By.xpath("//b[contains(text(),'Cancel')]"));

    private final SelenideElement eSignedStatus = $(By.xpath("//table[@id='reportListTable']//th[5]"));
    private final SelenideElement signatureText = $(By.xpath("//div[text()='Signature Needed']"));
    private final SelenideElement signatureValue = $(By.xpath("//input[contains(@placeholder,'0-1')]"));
    private final SelenideElement selectBatchIDDropdown =
        $(By.xpath("//div[@class='batch-id-drop-down']//span[@class='icon-down-arrow']"));
    private final String XPATH_BATCH_ID_DROPDOWN = "//li[text()='%s']";
    private final String dateFormat = "M/d/yyyy";
    private final ElementsCollection checkBoxes = $$(By.xpath("//div[@class='multi-check-box']"));
    private final SelenideElement selectBatch = $(By.xpath("//div[@class='restore-custom-drop-down-container']"));
    private final SelenideElement selectBatchIcon = $(By.xpath("//div[@class='restore-custom-drop-down']//span[2]"));
    List<String> dateColumns = List.of("Last Modified On", "Start Date", "Date Generated");
    private final SelenideElement applyButton = $(By.xpath("//button[@class='applyBtn btn btn-sm btn-primary']"));

    private final ElementsCollection tableCount = $$(By.xpath("//table[@class='table']//tr"));
    private final String SELECTRUN = "//tr[@class='tbl-row']//td[text()='%s']";

    private final Recipe recipe;

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
        var users = $$(By.xpath(String.format(XPATH_CONSOLIDATED_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };

    Function<Integer, List<String>> getTemplateColumns = (index) -> {
        var templates = $$(By.xpath(String.format(XPATH_TEMPLATE_COLUMNS, index))).texts();
        templates.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return templates;
    };

    public ReportsPage(Recipe recipe) {
        this.recipe = recipe;
    }

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
                $$(reportListTable).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
                break;
        }
    }

    public void verifyTabs() {
        runTab.shouldBe(visible);
        templateTab.shouldBe(visible);
        reportTab.shouldBe(visible);
    }


    public void verifyColumn(String columnName, String tab, int columnIndex) {
        switch (tab) {
            case "runs":
                $(By.xpath(String.format(XPATH_RunsColumnName, columnIndex))).shouldHave(text(columnName));
                Assert.assertFalse($(By.xpath(String.format(XPATH_RunsColumnName_Value, 1, columnIndex))).getText()
                    .isBlank());
                break;
            case "templates":
                $(By.xpath(String.format(XPATH_TemplateColumnName, columnIndex))).shouldHave(text(columnName));
                Assert.assertFalse($(By.xpath(String.format(XPATH_TemplateColumnName_Value, columnIndex))).getText()
                    .isBlank());
                break;
            case "reports":
                $(By.xpath(String.format(XPATH_ReportColumnName, columnIndex))).waitUntil(visible, 5000)
                    .shouldHave(text(columnName));
                if (columnIndex < 6) {
                    Assert.assertFalse($(By.xpath(String.format(XPATH_ReportColumnName_Value, 1, columnIndex)))
                        .waitUntil(visible, 5000)
                        .getText()
                        .isBlank());
                    break;
                }
        }
    }

    public void selectReport(String reportName) {
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> {
                SelenideHelper.commonWaiter(selectReportDropdown, visible)
                    .click();
                return $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, reportName))).isDisplayed();
            });

        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, reportName))).waitUntil(visible, 5000)
            .click();
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

    public void checkRecipeCTRLOperationLogs(String batchId, String recipeName) {
        $(By.xpath(String.format(recipeAuditLogs, "acknowledged", recipeName, batchId))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "aborted", recipeName, batchId))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "step jump", recipeName, batchId))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "resumed", recipeName, batchId))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "paused", recipeName, batchId))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "Bio4CAdmin", "system", "restart"))).shouldBe(visible);
        $(By.xpath(String.format(recipeAuditLogs, "Bio4CAdmin", "system", "hold"))).shouldBe(visible);
    }

    public void checkSortedElementTemplate(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getTemplateColumnHeaders(), columnName, descending, getTemplateColumns,
            dateColumns.contains(columnName), Report.RECIPE_DATE_FORMAT);
    }

    public List<String> getTemplateColumnHeaders() {
        return $$(By.xpath(XPATH_TEMPLATE_TABLE + "//th")).texts();
    }

    public void selectUser(String user) {
        SelenideHelper.commonWaiter(selectReportDropdown, visible)
            .click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))).click();
    }

    public void selectUserOnRunPage(String user) {
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> {
                SelenideHelper.commonWaiter(selectUserDropdownRunPage, visible)
                    .click();
                return $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))).isDisplayed();
            });
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))), visible)
            .click();
    }

    public void selectDateFilterOnRunPage(String dateFilter) {
        SelenideHelper.commonWaiter(selectDateDropdownRunPage, visible)
            .click();
        $(By.xpath(String.format(XPATH_Date_DROPDOWN, dateFilter))).click();
        if (dateFilter.equals("Custom range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            LocalDate currentDate = LocalDate.now();
            int currentDay = currentDate.getDayOfMonth();
            $(By.xpath(String.format(selectedDatePreviousMonth, currentDay))).waitUntil(visible, 10000)
                .click();
            currentMonth.waitUntil(visible, 2000)
                .click();
            $(By.xpath(String.format(selectedDateCurrentMonth, currentDay))).waitUntil(visible, 10000)
                .click();
            commonWaiter(applyButton, visible).click();
        }
    }

    public void generateReport() {
        reportGenerateButton.waitUntil(visible, 10000L);
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

    public void eSignReports(String reportName, String password) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, reportName))).click();
        reportESignButton.click();
        inputPassword.sendKeys(password);
        commonWaiter(primaryButton, visible).click();
    }

    public void gotoTemplate() {
        templateTab.waitUntil(visible, 10000)
            .click();
    }

    public void createTemplate(String templateName) {
        createButton.click();
        templateNameTextBox.setValue(templateName);
    }

    public void verifyAuditLogsForUserUpdate(String username, String loggedInUserName) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " updated", username))).shouldBe(visible);
    }

    public void verifyAuditLogsForUserCreate(String username, String loggedInUserName) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " added", username))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 2))).shouldHave(text("ID Management"));
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 3))).shouldHave(text("User - " + username));
    }

    public void verifyAuditLogsForRoleUpdate(String role, String loggedInUserName) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " created new Role ", role))).shouldBe(visible);
    }

    public void approveTemplate(String templateName, String password, String status) {
        openReportTemplate(templateName);
        SelenideHelper.commonWaiter(reportTemplateStatusIcon, visible)
            .click();
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
        SelenideHelper.commonWaiter(reviewStatus, visible)
            .click();
        var activeStatus = $(By.xpath(String.format(XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT, status)));
        SelenideHelper.commonWaiter(activeStatus, visible)
            .click();
    }

    public void searchReportOrTemplate(String templateName) {
        SelenideHelper.commonWaiter(reportSearch, visible)
            .setValue(templateName);
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
        var name = notificationText.text()
            .split(": ")[1];
        waitForReportGeneration(notificationText, not(visible));
        return name;
    }

    public void createTrends() {
        trendsName.waitUntil(visible, 10000)
            .setValue(RandomStringUtils.randomAlphabetic(10));
        trendsSaveButton.click();
        trendsCancelButton.click();
    }

    public void cancelButton() {
        SelenideHelper.commonWaiter(templateCancelButton, visible)
            .click();
    }

    public void waitForReportGeneration(SelenideElement element, Condition condition) throws InterruptedException {
        element.waitUntil(condition, 180000L, 200L);
    }

    public void exists(String name) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, name))).shouldBe(visible);
    }

    public void checkSigned(String reportName, String username) {
        var reportSigned = $(By.xpath(String.format(XPATH_SIGNED_REPORT, reportName, username)));
        reportSigned.shouldBe(visible);
    }

    public void checkReportPdfInPage() {
        absentReportText.should(not(visible));
    }

    public void saveTrends() {
        trendsName.waitUntil(visible, 10000)
            .setValue(RandomStringUtils.randomAlphabetic(10));
        trendsSaveButton.click();
        trendsCancelButton.click();
    }

    public void create5Trends() {
        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < 6; i++) {
                commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_") + i)))), visible);
                $(By.xpath(String.format(XPATH_TRENDS_PARAMETERS, (("checkbox_item_") + i)))).click();
            }
            trendsName.waitUntil(visible, 10000)
                .setValue(RandomStringUtils.randomAlphabetic(10));
            trendsSaveButton.click();
            trendsAddButton.click();
        }
    }

    public void selectParameters(String noOfParams, String parameters) {
        int count = Integer.parseInt(noOfParams);
        List<String> list = new ArrayList<>();
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

    public void verifySixthChartNotAllowed() {
        trendsAddButton.click();
        Assert.assertFalse(trendsAddButton.isEnabled());
        trendsCancelButton.click();
    }

    public void isGeneratedNotificationWhenMoreThanSixParams(String message) {
        XPATH_ERROR_NOTIFICATION.shouldHave(text(message));
    }

    public void includeReport(String reportInclude) {
        if (reportInclude.contains("Alarms")) {
            $(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, "Alarms"))).click();
            if (reportInclude.equalsIgnoreCase("ProcessAlarms")) {
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Process"))).click();
            } else if (reportInclude.equalsIgnoreCase("SystemAlarms")) {
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "System"))).click();
            } else {
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Process"))).click();
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "System"))).click();
            }
            saveAlarmButton.click();
            $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Alarms"))).isSelected();
        } else if (reportInclude.contains("Trends")) {
            SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, reportInclude))), visible);
            $(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, reportInclude))).click();
        } else {
            $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).click();
        }
    }

    public void isReportIncludeSelected(String reportInclude) {
        $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX_CHECKED, reportInclude))).shouldBe(visible);
    }

    public void saveReportTemplate() {
        saveTemplateButton.click();
    }

    public void selectRun(String run) {
        $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).click();
    }

    public void selectRunWithWaiting(String reportTemplateName, String run) {

        // after finished a recipe, it takes some times to have the run in page
        // polling report run page
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> {
                boolean isRunVisible = $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT, run))).is(visible);

                if (!isRunVisible) {
                    WebDriverRunner.getWebDriver()
                        .switchTo()
                        .parentFrame();
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
            if (option.getValue()
                .equals(template)) {
                option.parent()
                    .click();
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
        commonWaiter($(By.xpath(String.format(XPATH_DROPDOWN, "Created By"))), visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))), visible).click();
    }

    public void checkTableContainsRecipeRun(String recipeRun) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT, recipeRun))),
            visible);
    }

    public void checkConsolidatedTableContainsRecipeRun(String recipeRun) {
        SelenideHelper.commonWaiter(
            $(By.xpath(String.format(XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT, recipeRun))), visible);
    }

    public void checkTableContainsReport(String reportName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_REPORT_COLUMNS_BY_TEXT, reportName))), visible);
        for (int row = 1; row <= reportListTable.size(); row++) {
            if ($(By.xpath(String.format(XPATH_ReportColumnName_Value, row, 1))).getText()
                .equalsIgnoreCase(reportName)) {
                for (int i = 1; i < 7; i++) {
                    Assert.assertFalse($(By.xpath(String.format(XPATH_ReportColumnName_Value, row, i))).getText()
                        .isBlank());
                }
                break;
            }
        }
    }

    public void checkTableContainsUserAndDateRange(int days, String userid, int results) {
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> auditListTable.size() > 0);
        Assert.assertTrue("auditListTable should contains values", auditListTable.size() > 0);
        var dateInPast = LocalDateTime.now()
            .plusDays(-days);
        var formatter = DateTimeFormatter.ofPattern(Report.RECIPE_DATE_FORMAT)
            .localizedBy(Locale.US);

        // limit the results to avoid test to take too much time
        for (int i = 0; i < auditListTable.size() && i < results; i++) {
            validateAuditTrail(userid, dateInPast, formatter, i + 1);
            validateAuditTrail(userid, dateInPast, formatter, auditListTable.size() - i);
        }
    }

    private void validateAuditTrail(String userid, LocalDateTime dateInPast, DateTimeFormatter formatter,
                                    int iterator) {
        String user = $(By.xpath(String.format(XPATH_UserColumnName_Value, iterator))).getText();
        String eventDate = $(By.xpath(String.format(XPATH_DateColumnName_Value, iterator))).getText();
        Assert.assertTrue(user.toLowerCase()
            .contains(userid));
        LocalDateTime eventDateTime = LocalDateTime.parse(eventDate, formatter);
        Assert.assertTrue(String.format("eventDateTime:%s is not in the expected range", eventDateTime),
            eventDateTime.compareTo(dateInPast) >= 0);
    }

    public void checkTableContainsTemplate(String templateName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_COLUMNS_BY_TEXT, templateName))), visible);
    }

    public void selectSignedBy(String user) {
        commonWaiter($(By.xpath(String.format(XPATH_DROPDOWN, "Signed By"))), visible).click();
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
        commonWaiter(errorMsgTemplateApproval, visible);
        errorMsgTemplateApproval.shouldHave(text(name));
    }

    public void approvedTemplateValidation() {
        SelenideHelper.commonWaiter(templateNameTextBox, disabled);
        for (var option : checkBoxTemplate) {
            Assert.assertTrue(option.getAttribute("class")
                .contains("disabled"));
        }
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
        boolean isTrue;
        if (!(commonWaiter(statusColumn, appear).isDisplayed())) {
            isTrue = noDatamsg.isDisplayed();
        } else {
            isTrue = commonWaiter(statusColumn, appear).getText()
                .equalsIgnoreCase(status);
        }
        return isTrue;
    }

    public void selectDateRange(String option) {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        ElementsCollection options = dateOptions;
        for (SelenideElement element : options) {
            if (element.getText()
                .equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index)
                .click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index)
                .click();

        }

    }

    public boolean verifyDateRanges(String dateRange) throws ParseException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                String dateValue = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate = SelenideHelper.dateParser(dateValue, dateFormat);

                if (startDate.isDisplayed()) {
                    sortList("Start Date", false);
                    Selenide.sleep(1000);
                    String startDateRow1 = startDate.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    sortList("Start Date", true);
                    Selenide.sleep(1000);
                    startDateRow1 = startDate.getText();
                    LocalDate selectedDescendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    if (selectedAscendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()
                        && selectedDescendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()) {
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
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                commonWaiter(dateColumn, visible);
                String dateValue1 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate1 = SelenideHelper.dateParser(dateValue1, dateFormat);// M is used as actual value
                // is without 0 prefix
                String dateValue2 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[1].trim();
                LocalDate selectedDate2 = SelenideHelper.dateParser(dateValue2, dateFormat);
                if (startDate.isDisplayed()) {
                    sortList("Start Date", false);
                    String startDateRow = startDate.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow, Report.RECIPE_DATE_FORMAT);
                    sortList("Start Date", true);
                    String endDateRow = startDate.getText();
                    LocalDate selectedDescendingDate = SelenideHelper.dateParser(endDateRow, Report.RECIPE_DATE_FORMAT);
                    if ((selectedAscendingDate.getDayOfMonth() == selectedDate1.getDayOfMonth()
                        || selectedAscendingDate.isAfter(selectedDate1))
                        && (selectedDescendingDate.getDayOfMonth() == selectedDate2.getDayOfMonth()
                        || selectedDescendingDate.isBefore(selectedDate2))) {
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

    public void sortConsolidatedList(String columnName, boolean descending) {
        SelenideElement sortAction = getReportColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, getReportColumns,
            dateColumns.contains(columnName), Report.RECIPE_DATE_FORMAT);
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

    public void iValidateWindow() {
        saveTemplateTxt.shouldBe(visible);
    }

    public void iRename(String templateName) {
        saveTemplateAs.click();
        saveTemplateAs.clear();
        saveTemplateAs.setValue(templateName);
        iSave();
    }

    public void iSave() {
        saveBtn.click();
    }

    public void putReportTemplateToInactive(String templateName, String status) {
        openReportTemplate(templateName);
        reportTemplateStatusIcon.click();
        changeStatus(status);
        saveReportTemplate();
    }

    public void iCheckNotificationMsg(String status) {
        SelenideHelper.commonWaiter(notificationMsg, visible);
    }

    public void iSearchReportTemplate(String templateName) {
        SelenideHelper.commonWaiter(reportSearch, visible)
            .setValue(templateName);
        SelenideHelper.commonWaiter(reportTemplateLoadingIcon, not(visible));
    }

    public void iValidateStatus(String status) {
        Assert.assertTrue(column_temp.getText()
            .equalsIgnoreCase(status));
    }

    public void selectDateRangeReport(String option) {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        for (SelenideElement element : dateOptionsReport) {
            if (element.getText()
                .equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index)
                .click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index)
                .click();

        }

    }

    public boolean verifyDateRangesReport(String dateRange) throws ParseException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                String dateValue = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate = SelenideHelper.dateParser(dateValue, dateFormat);// M is used as actual value
                // is without 0 prefix

                if (startDateRep.isDisplayed()) {
                    sortList("Date Generated", false);
                    Selenide.sleep(1000);
                    String startDateRow1 = startDateRep.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    sortList("Date Generated", true);
                    Selenide.sleep(1000);
                    startDateRow1 = startDateRep.getText();
                    LocalDate selectedDescendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    if (selectedAscendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()
                        && selectedDescendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()) {
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
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                commonWaiter(dateColumn, visible);
                String dateValue1 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate1 = SelenideHelper.dateParser(dateValue1, dateFormat);// M is used as actual value
                // is without 0 prefix
                String dateValue2 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[1].trim();
                LocalDate selectedDate2 = SelenideHelper.dateParser(dateValue2, dateFormat);
                if (startDateRep.isDisplayed()) {
                    sortList("Date Generated", false);
                    String startDateRow = startDateRep.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow, Report.RECIPE_DATE_FORMAT);
                    sortList("Date Generated", true);
                    String endDateRow = startDateRep.getText();
                    LocalDate selectedDescendingDate = SelenideHelper.dateParser(endDateRow, Report.RECIPE_DATE_FORMAT);
                    if ((selectedAscendingDate.getDayOfMonth() == selectedDate1.getDayOfMonth()
                        || selectedAscendingDate.isAfter(selectedDate1))
                        && (selectedDescendingDate.getDayOfMonth() == selectedDate2.getDayOfMonth()
                        || selectedDescendingDate.isBefore(selectedDate2))) {
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
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, getReportsColumns,
            dateColumns.contains(columnName), Report.REPORT_DATE_FORMAT);
    }

    public void checkSortedElementConsolidate(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllReportsColumnHeaders(), columnName, descending, consolidatedColumns,
            dateColumns.contains(columnName), Report.REPORT_DATE_FORMAT);
    }

    public void sortListConsolidated(String columnName, boolean descending) {
        SelenideElement sortAction = getReportColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void selectConsolidatedStatus(String consolidatedReportStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        processType.shouldBe(visible);
        status.shouldBe(visible);
        if (clearAllFilters.isDisplayed()) {
            clearAllFilters.click();
        }
        if (!$(By.xpath(String.format("//span[text()='%s']", consolidatedReportStatus))).isSelected()) {
            $(By.xpath(String.format("//span[text()='%s']", consolidatedReportStatus))).click();
        }
        applyFilterButton.click();
    }

    public boolean verifyConsolidatedStatus(String status) {
        boolean result = false;
        SelenideElement columnData;
        commonWaiter(filterSelection, visible);
        for (int i = 1; i <= tableCount.size(); i++) {
            if (status.equalsIgnoreCase("Completed") || status.equalsIgnoreCase("Aborted")) {
                columnData = $(By.xpath(String.format(COLUMN_DATA, i, 7)));
            } else {
                columnData = $(By.xpath(String.format(COLUMN_DATA, i, 6)));
            }
            if (!columnData.isDisplayed()) {
                result = noDatamsg.isDisplayed();
            } else {
                result = columnData.getText()
                    .equalsIgnoreCase(status);
            }
        }
        return result;
    }

    public void reportRequestNotificationVisibility() {
        commonWaiter(requestNotification, not(visible));
    }

    public void seeContent(String expectedText) {
        commonWaiter($(By.xpath(XPATH_NAV)), text(expectedText));
    }

    public void selectParams(String parameter) {
        commonWaiter($(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))), visible);
        $(By.xpath(String.format(XPATH_TRENDS_PARAMS, parameter))).click();
    }

    public boolean isRunDisplayed(String run) {
        return $(By.xpath(String.format(XPATH_CHECKBOX_CONSOLIDATED_REPORT, run))).isDisplayed();
    }

    public boolean verifyRunDetails(String recipeName, String processType, String status) throws ParseException {
        boolean result = false;
        if (startDate.isDisplayed()) {
            SimpleDateFormat sdf = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT);
            String currentDateAndTime = sdf.format(new Date());
            Date dateAndTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(currentDateAndTime);
            Date eventEntriesTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(startDate.getText());
            long diff = dateAndTime.getTime() - eventEntriesTime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            if (diffMinutes < 5 && statusColumn.getText()
                .equalsIgnoreCase(status) && processTypeValue.getText()
                .equals(processType)
                && runColumn.getText()
                .contains(recipeName)) {
                result = true;
            }
        }
        return result;
    }

    public void verifyReportsHeader() {
        reportManagementHeader.shouldBe(visible);
    }

    public void verifyNewUser(String user) {
        SelenideHelper.commonWaiter(selectUserDropdownRunPage, visible)
            .click();
        $(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))).shouldBe(visible);
    }

    public boolean verifyAuditTrail(String message) throws ParseException {
        boolean result = false;
        if (eventTime.isDisplayed()) {
            SimpleDateFormat sdf = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT);
            String currentDateAndTime = sdf.format(new Date());
            Date dateAndTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(currentDateAndTime);
            Date eventEntriesTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(eventTime.getText());
            long diff = dateAndTime.getTime() - eventEntriesTime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            if (diffMinutes < 5 && comment.getText()
                .equalsIgnoreCase(message)) {
                result = true;
            }
        }
        return result;
    }

    public void verifyAuditTrailRecord(String message, String recordRole) throws ParseException {
        if (eventTime.isDisplayed()) {
            SimpleDateFormat sdf = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT);
            String currentDateAndTime = sdf.format(new Date());
            Date dateAndTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(currentDateAndTime);
            Date eventEntriesTime = new SimpleDateFormat(Report.RECIPE_DATE_FORMAT).parse(eventTime.getText());
            long diff = dateAndTime.getTime() - eventEntriesTime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            if (diffMinutes < 10 && comment.getText()
                .contains(message)) {
                record.shouldHave(text(recordRole));
            }
        }
    }

    public void verifyRunMode() {
        commonWaiter(runTab, not(visible));
    }

    public void verifyGenerateButton() {
        Assert.assertFalse(reportGenerateButton.is(visible));
    }

    public void selectESignStatus(String eSignStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        arrowIcon.click();
        commonWaiter($(By.xpath(String.format(XPATH_ESIGN_STATUS, eSignStatus))), visible).click();
        $(By.xpath(String.format(XPATH_ESIGN_STATUS, "Signed"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_ESIGN_STATUS, "Not Signed"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_ESIGN_STATUS, "Not Required"))).shouldBe(visible);
        applyFilterButton.click();
    }

    public void verifyESignedStatus(String status) {
        commonWaiter(filterSelection, visible);
        if (!eSignedStatus.isDisplayed()) {
            noDatamsg.shouldBe(visible);
        } else {
            for (int i = 1; i <= reportListTable.size(); i++) {
                Assert.assertTrue($(By.xpath(String.format(XPATH_ReportColumnName_Value, i, 5))).getText()
                    .equalsIgnoreCase(status));
            }
        }
    }

    public void saveTrendsButton() {
        commonWaiter(trendsSaveButton, visible).click();
    }

    public void isGeneratedNotificationWhenCreateExistingUsername(String message) {
        XPATH_ERROR_NOTIFICATION_TEXT.waitUntil(visible, 5001)
            .shouldHave(text(message));
    }

    public void selectDevice(String device) {
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> {
                SelenideHelper.commonWaiter(selectDeviceDropdown, visible)
                    .click();
                return $(By.xpath(String.format(XPATH_DEVICE_OPTION_DROPDOWN, device))).isDisplayed();
            });
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_DEVICE_OPTION_DROPDOWN, device))), visible)
            .click();
    }

    public void iVerifyReportPageLoaded() {
        searchTextReportManagement.waitUntil(visible, 5000L, 500L);
    }

    public void createDuplicateTemplate(String templateName) {
        templateNameTextBox.setValue(templateName);
    }

    public void verifyAuditLogsForTemplateCreate(String templateName, String status) {
        int n = 4;
        if (status.equalsIgnoreCase("Inactive")) {
            n = n + 1;
        }
        for (int rowNo = 1; rowNo < n; rowNo++) {
            for (int columnNo = 2; columnNo < 6; columnNo++) {
                if (columnNo == 2) {
                    Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                        .equalsIgnoreCase("Report Management"));
                } else if (columnNo == 3) {
                    Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                        .equalsIgnoreCase("Report Template - " + templateName));
                } else if (columnNo == 4) {
                    Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                        .equalsIgnoreCase("Bio4CAdmin (Administrator Bio4C)"));
                }
                if (status.equalsIgnoreCase("Approved")) {
                    if (columnNo == 5 && rowNo == 1) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin approved and signed Report Template"));
                    } else if (columnNo == 5 && rowNo == 2) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin updated Report Template"));
                    } else if (columnNo == 5 && rowNo == 3) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin created Report Template"));
                    }
                } else if (status.equalsIgnoreCase("Inactive")) {
                    if (columnNo == 5 && rowNo == 1) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin updated Report Template"));
                    } else if (columnNo == 5 && rowNo == 2) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin approved and signed Report Template"));
                    } else if (columnNo == 5 && rowNo == 3) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin updated Report Template"));
                    } else if (columnNo == 5 && rowNo == 4) {
                        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, rowNo, columnNo))).getText()
                            .equalsIgnoreCase("Bio4CAdmin created Report Template"));
                    }
                }
            }
        }
    }

    public void createTemplateAndCustomPageValidation(String options) {
        switch (options) {
            case "Template Name":
                templateNameTextBox.shouldBe(visible);
                break;
            case "Status":
                reportTemplateStatusIcon.shouldBe(visible)
                    .click();
                var reviewStatus = $(By.xpath(String.format(REPORT_TEMPLATE_STATUS_WITH_TEXT, "Draft")));
                SelenideHelper.commonWaiter(reviewStatus, visible);
                break;
            case "Signature Needed":
                signatureText.waitUntil(visible, 5000);
                signatureValue.shouldBe(visible);
                break;
            case "Cancel":
                templateCancelButton.shouldBe(visible);
                break;
            case "Save":
                saveTemplateButton.shouldBe(visible);
                break;
            case "Save As":
                saveAs_btn.shouldBe(visible);
                break;
            case "Report Include":
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Audit Trail"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Run Summary"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Alarms"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Trends"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Event Summary"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Calibration Summary"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Run Header"))).shouldBe(visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Run Header"))).isSelected();
                break;
            case "Generate Button":
                reportGenerateButton.shouldBe(visible);
            case "Date Range":
                SelenideHelper.commonWaiter(selectDateDropdownRunPage, visible)
                    .click();
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Today"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Yesterday"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Last 7 Days"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Last 30 Days"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "This Month"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Last Month"))).waitUntil(enabled, 2000);
                $(By.xpath(String.format(XPATH_Date_DROPDOWN, "Custom range"))).waitUntil(enabled, 2000);
                break;
            case "Alarm Eye Icon":
                $(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, "Alarms"))).click();
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "Process"))).click();
                $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, "System"))).click();
                saveAlarmButton.click();
                break;
            case "Trends Eye Icon":
                SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, "Trends"))), visible);
                $(By.xpath(String.format(XPATH_TEMPLATE_EYE_ICON, "Trends"))).click();
                commonWaiter(trendsCancelButton, visible).click();
                break;
            default:
        }
    }

    public void selectBatchId(String batchID) {
        SelenideHelper.fluentWaiter()
            .until((webDriver) -> {
                SelenideHelper.commonWaiter(selectBatchIDDropdown, visible)
                    .click();
                return $(By.xpath(String.format(XPATH_BATCH_ID_DROPDOWN, batchID))).isDisplayed();
            });

        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_BATCH_ID_DROPDOWN, batchID))), visible)
            .click();
    }

    public void selectConsolidatedDateRange(String option) {
        commonWaiter(dateColumn, visible);
        dateColumn.click();
        ElementsCollection options = dateOptionsConsolidated;
        for (SelenideElement element : options) {
            if (element.getText()
                .equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }
        if (option.equalsIgnoreCase("Custom Range")) {
            commonWaiter(previousMonth, visible);
            previousMonth.click();
            commonWaiter(previousMonth, visible);
            int index = getRandomNumber(0, availableDates.size() / 2);
            availableDates.get(index)
                .click();
            index = getRandomNumber(availableDates.size() / 2, availableDates.size());
            availableDates.get(index)
                .click();
        }
    }

    public boolean verifyConsolidateDateRanges(String dateRange) throws ParseException {
        boolean isTrue = false;
        switch (dateRange) {
            case "Today":
            case "Yesterday":
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                String dateValue = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate = SelenideHelper.dateParser(dateValue, dateFormat);
                if (consolidateStartDate.isDisplayed()) {
                    sortConsolidatedList("Start Date", false);
                    Selenide.sleep(1000);
                    String startDateRow1 = consolidateStartDate.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    sortConsolidatedList("Start Date", true);
                    Selenide.sleep(1000);
                    startDateRow1 = consolidateStartDate.getText();
                    LocalDate selectedDescendingDate =
                        SelenideHelper.dateParser(startDateRow1, Report.RECIPE_DATE_FORMAT);
                    if (selectedAscendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()
                        && selectedDescendingDate.getDayOfMonth() == selectedDate.getDayOfMonth()) {
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
                commonWaiter(spinnerComponent.spinnerIcon, not(visible));
                commonWaiter(dateColumn, visible);
                String dateValue1 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[0].trim();
                LocalDate selectedDate1 = SelenideHelper.dateParser(dateValue1, dateFormat);// M is used as actual value
                String dateValue2 = Objects.requireNonNull(dateColumn.getAttribute("value"))
                    .split("to")[1].trim();
                LocalDate selectedDate2 = SelenideHelper.dateParser(dateValue2, dateFormat);
                if (consolidateStartDate.isDisplayed()) {
                    sortConsolidatedList("Start Date", false);
                    String startDateRow = consolidateStartDate.getText();
                    LocalDate selectedAscendingDate =
                        SelenideHelper.dateParser(startDateRow, Report.RECIPE_DATE_FORMAT);
                    sortConsolidatedList("Start Date", true);
                    String endDateRow = consolidateStartDate.getText();
                    LocalDate selectedDescendingDate = SelenideHelper.dateParser(endDateRow, Report.RECIPE_DATE_FORMAT);
                    if ((selectedAscendingDate.getDayOfMonth() == selectedDate1.getDayOfMonth()
                        || selectedAscendingDate.isAfter(selectedDate1))
                        && (selectedDescendingDate.getDayOfMonth() == selectedDate2.getDayOfMonth()
                        || selectedDescendingDate.isBefore(selectedDate2))) {
                        isTrue = true;
                    }
                } else if (noDatamsg.isDisplayed()) {
                    isTrue = true;
                }
                break;
        }
        return isTrue;
    }

    public void verifyAuditLogsForResetChangePassword(String username, String passwordAction, String loggedInUser) {
        if (passwordAction.equals("reset")) {
            $(By.xpath(String.format(userAuditLogs, loggedInUser, " reset password for User Account ", username)))
                .shouldBe(visible);
        } else if (passwordAction.equals("temp")) {
            $(By.xpath(String.format(userAuditLogs, loggedInUser,
                " changed the account temporary password on first login", ""))).shouldBe(visible);
        } else {
            $(By.xpath(String.format(userAuditLogs, loggedInUser, " changed the account password", "")))
                .shouldBe(visible);
        }
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 2))).shouldHave(text("ID Management"));
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 3))).shouldHave(text("User - " + username));
    }

    public void verifyAuditLogsForRecipe(String recipeName, String loggedInUserName, String recipeAction) {
        if (recipeAction.equals("created")) {
            $(By.xpath(String.format(userAuditLogs, loggedInUserName, " created a new recipe ", recipeName)))
                .shouldBe(visible);
        } else {
            $(By.xpath(String.format(userAuditLogs, loggedInUserName, " edited recipe ", recipeName)))
                .shouldBe(visible);
        }
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 2))).shouldHave(text("Recipe Management"));
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 3))).shouldHave(text("Recipe Name - " + recipeName));
    }

    public void verifyAuditLogsForBackUp(String backUpName, String loggedInUserName, String recipeAction) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " triggered data backup", ""))).shouldBe(visible);
        Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 3))).getText()
            .contains("Backup Job Id - "));
        $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, 1, 2))).shouldHave(text("Backup Management"));
    }

    public void verifyAuditLogsForScheduleBackUp(String backUpName, String loggedInUserName, String occurrence) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " deactivated backup schedule named ", backUpName)))
            .shouldBe(visible);
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, (" scheduled " + occurrence + " backup named "),
            backUpName))).shouldBe(visible);
        for (int i = 1; i < 3; i++) {
            Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 3))).getText()
                .contains("Backup Schedule Name - " + backUpName));
            $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 2))).shouldHave(text("Backup Management"));
        }
    }

    public void isGenerateButtonEnabled() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i)
                .isDisplayed()
                && checkBoxes.get(i)
                .isEnabled()) {
                reportGenerateButton.isEnabled();
            }
        }
    }

    public void verifySelectBatchDropdown() {
        commonWaiter(selectBatch, visible);
        commonWaiter(selectBatchIcon, visible).click();
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_BATCH_ID_DROPDOWN, "All Batches"))), visible)
            .click();
    }

    public void expandReportFilter() {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter(arrowIcon, visible).click();
        processType.shouldBe(visible);
        status.shouldBe(visible);
    }

    public void reportFilterOptions(String name) {
        if ($(By.xpath(String.format(XPATH_ESIGN_STATUS, name))).isDisplayed()) {
            if (!$(By.xpath(String.format(XPATH_ESIGN_STATUS, name))).isSelected()) {
                $(By.xpath(String.format(XPATH_ESIGN_STATUS, name))).click();
            }
            applyFilterButton.click();
            commonWaiter(filterIcon, visible);
            filterIcon.click();
            commonWaiter(arrowIcon, visible).click();
        } else if (!filterIcon.isDisplayed()) {
            expandReportFilter();
            $(By.xpath(String.format(XPATH_ESIGN_STATUS, name))).click();
        }
    }

    public void selectRunId() {
        $(By.xpath(String.format(SELECTRUN, this.recipe.getRunId()))).waitUntil(visible, 5000L, 1000L)
            .click();
    }

    public void verifyAuditLogsForHoldAndRestart(String username, String loggedInUserName) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " triggered a system restart action on the IVI", "")))
            .shouldBe(visible);
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " triggered a system hold action on the IVI", "")))
            .shouldBe(visible);
        for (int i = 1; i < 3; i++) {
            $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 2))).shouldHave(text("Recipe Management"));
            $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 3))).shouldHave(text("User - " + username));
        }
    }

    public void verifyAuditLogsForScheduleBackUp(String backUpName, String loggedInUserName, String scheduledUser,String occurrence) {
        $(By.xpath(String.format(userAuditLogs, loggedInUserName, " deactivated backup schedule named ", backUpName)))
            .shouldBe(visible);
        $(By.xpath(String.format(userAuditLogs, scheduledUser, (" scheduled " + occurrence + " backup named "),
            backUpName))).shouldBe(visible);
        for (int i = 1; i < 3; i++) {
            Assert.assertTrue($(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 3))).getText()
                .contains("Backup Schedule Name - " + backUpName));
            $(By.xpath(String.format(XPATH_AUDITLOGS_VALUE, i, 2))).shouldHave(text("Backup Management"));
        }
    }

    public void createReportPermission(String permission){
        reportGenerateButton.waitUntil(visible, 10000L, 5000L);
        templateTab.shouldNot(visible);
    }
    public void viewReportPermission(){
        reportViewButton.waitUntil(visible, 10000L, 5000L).shouldNot(selected);
        runTab.shouldNot(visible);
        templateTab.shouldNot(visible);
    }

}

