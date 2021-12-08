package pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class ReportsPage {

    private final String REPORT_TEMPLATE_STATUS_WITH_TEXT = "(//*[@id='template_status']//li)[text()='%s']";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS = "(//*[@class='active-label'])";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT = XPATH_ACTIVE_TEMPLATE_STATUS + "[text()='%s']";
    private final String XPATH_SIGNED_REPORT = "//tr[td='Signed' and td='%s' and td='%s']";
    private final String XPATH_REPORT_NAME = "//td[text()='%s']";
    private final String XPATH_TEMPLATE_CHECKBOX = "//div[@class='item_value'][text()='%s']/ancestor::li/div[@class='check_box']";
    private final String XPATH_CONSOLIDATED_REPORT = "//*[@class='tbl-row']//td[text()='%s']";
    private final String XPATH_CHECKBOX_CONSOLIDATED_REPORT = "//td[text()='%s']/ancestor::tr//*[@class='checkbox']";
    private final String XPATH_NOTIFICATION_TEXT = "//*[@class='notification-summary'][contains(text(),'%s')]";

    private final SelenideElement reportsManagementPage = $(By.id("ReportManagement"));
    private final SelenideElement runTab = $(By.xpath("//a[text()='Runs']"));
    private final SelenideElement templateTab = $(By.xpath("//a[text()='Templates']"));
    private final SelenideElement reportTab = $(By.xpath("//a[text()='Reports']"));

    private final SelenideElement selectReportDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement selectReportRunReportTemplateDropDown =
            $(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']"));
    private final ElementsCollection optionsReportTemplate = $$(By.xpath("//*[@class='run-templete-dropdown']//*[@class='custom-drop-down-container']//ul//li//option"));

    private final SelenideElement reportGenerateButton = $(By.xpath("//button[text()='Generate']"));
    private final SelenideElement reportViewButton = $(By.xpath("//button[text()='View']"));
    private final SelenideElement createButton = $(By.xpath("//button[text()='Create']"));
    private final SelenideElement saveTemplateButton = $(By.xpath("//b[text()='Save']"));
    private final SelenideElement openButton = $(By.xpath("//button[text()='Open']"));
    private final SelenideElement primaryButton = $(By.xpath("//button[text()='SIGN']"));
    private final SelenideElement reportEsignButton = $(By.xpath("//button[text()='e-sign']"));

    private final SelenideElement inputPassword = $(By.xpath("//input[@type='password']"));

    private final SelenideElement reportSearch = $(By.xpath("//input[@placeholder='Search...']"));

    private final SelenideElement templateNameTextBox = $(By.xpath("//input[@placeholder='Create a template name']"));

    private final SelenideElement reportTemplateStatusIcon = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement reportTemplateLoadingIcon = $(By.xpath("//div[@class='spinner-circle']"));

    private final SelenideElement absentReportText = $(By.xpath("//*[@id='Report_View']//h4[text()='Report is either not available or corrupted.']"));

    public void goToReports() {
        reportsManagementPage.click();
    }

    public void switchToFrame() {
        SelenideHelper.goToIFrame();
    }

    public void selectReport(String reportname) {
        SelenideHelper.commonWaiter(selectReportDropdown, visible).click();
        $(By.xpath(String.format("//option[text()='%s']/ancestor::li/a", reportname))).click();
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
        waitForReportGeneration(notificationText);
        return notificationText.text().split(": ")[1];
    }

    public void exists(String name) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, name))).isDisplayed();
    }

    public void checkSigned(String reportName, String username) {
        var reportSigned = $(By.xpath(String.format(XPATH_SIGNED_REPORT, reportName, username)));
        reportSigned.shouldBe(visible);
    }

    public void waitForReportGeneration(SelenideElement element){
        element.waitUntil(visible,5 * 60 * 1000l, 500l);
    }

    public void checkReportPdfInPage() {
        absentReportText.should(not(visible));
    }

    public void includeReport(String reportInclude) {
        $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).click();
    }

    public void saveReportTemplate() {
        saveTemplateButton.click();
    }

    public void selectRun(String run) {
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
}
