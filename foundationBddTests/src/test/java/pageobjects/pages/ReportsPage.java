package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class ReportsPage {

    private final String REPORT_TEMPLATE_STATUS_WITH_TEXT = "(//*[@id='template_status']//li)[text()='%s']";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS = "(//*[@class='active-label'])";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT = XPATH_ACTIVE_TEMPLATE_STATUS + "[text()='%s']";
    private final String XPATH_SIGNED_REPORT = "//tr[td='Signed' and td='%s' and td='%s']";
    private final String XPATH_REPORT_NAME = "//td[text()='%s']";
    private final String XPATH_TEMPLATE_CHECKBOX = "//div[@class='item_value'][text()='%s']/ancestor::li/div[@class='check_box']";

    private final SelenideElement reportsManagementPage = $(By.id("ReportManagement"));
    private final SelenideElement reportTab = $(By.xpath("//a[text()='Reports']"));
    private final SelenideElement templateTab = $(By.xpath("//a[text()='Templates']"));

    private final SelenideElement selectReportDropdownArrow = $(By.xpath("//span[@class='icon-down-arrow']"));

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
    private final SelenideElement reportEmbed = $(By.xpath("//embed[@type='application/pdf']"));

    private final SelenideElement reportTemplateStatusIcon = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement reportTemplateLoadingIcon = $(By.xpath("//div[@class='spinner-circle']"));

    private final SelenideElement notificationText = $(By.className("notification-summary"));

    public void goToReports() {
        reportsManagementPage.click();
    }

    public void switchToFrame() {
        switchTo().frame("CrossDomainiframeId");
    }

    public void selectReport(String reportname) {
        SelenideHelper.commonWaiter(selectReportDropdownArrow, visible).click();
        $(By.xpath(String.format("//option[text()='%s']/ancestor::li/a", reportname))).click();
    }

    public void generateReport() {
        reportGenerateButton.waitUntil(visible, 10000l);
        reportGenerateButton.click();
    }

    public void gotoReportsTab() {
        reportTab.click();
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
        SelenideHelper.commonWaiter(reportTemplateStatusIcon,visible).click();
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

    public String getGeneratedName() {
        switchTo().parentFrame();
        notificationText.waitUntil(visible, 30000l, 500l);
        notificationText.shouldHave(Condition.ownText("Report file generated"));
        return notificationText.text().split(": ")[1];
    }

    public void exists(String name) {
        $(By.xpath(String.format(XPATH_REPORT_NAME, name))).isDisplayed();
    }

    public void checkSigned(String reportName, String username) {
        String reportNameModifiedAfterSignature = String.format("%s_%s", reportName, username);
        var reportSigned=$(By.xpath(String.format(XPATH_SIGNED_REPORT, reportNameModifiedAfterSignature, username)));
        SelenideHelper.commonWaiter(reportSigned,visible);
    }

    public void goToReportView() {
        switchTo().frame("ReportViewIFrame");
    }

    public void checkReportPdfInPage() {
        reportEmbed.shouldBe(visible);
    }

    public void includeReport(String reportInclude) {
        $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).click();
    }

    public void saveReportTemplate() {
        saveTemplateButton.click();
    }
}
