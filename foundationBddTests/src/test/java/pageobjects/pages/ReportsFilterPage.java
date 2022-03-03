package pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

public class ReportsFilterPage {

    private final String XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@class='table']//td[text()='%s']";
    private final String XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@id='foundationRunListTable']//td[text()='%s']";
    private final String XPATH_TEMPLATE_COLUMNS_BY_TEXT = "//table[@id='templateListTable']//td[text()='%s']";
    private final String XPATH_REPORT_COLUMNS_BY_TEXT = "//table[@id='reportListTable']//td[text()='%s']";
    private final String XPATH_DROPDOWN = "//span[text()='%s']/ancestor::div[@class='custom-drop-down']";
    private final String XPATH_OPTION_DROPDOWN = "//option[@value='%s']/ancestor::li";

    private final SelenideElement reportSearch = $(By.xpath("//input[@placeholder='Search...']"));
    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));

    public void searchTemplate(String templateName) {
        SelenideHelper.commonWaiter(reportSearch, visible).setValue(templateName);
    }

    public void selectTemplateStatus(String templateStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        $(By.xpath(String.format("//span[text()='%s']", templateStatus))).click();
        applyFilterButton.click();
    }

    public void searchReport(String reportName) {
        SelenideHelper.commonWaiter(reportSearch, visible).setValue(reportName);
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
    }

    public void checkTableContainsTemplate(String templateName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_TEMPLATE_COLUMNS_BY_TEXT, templateName))), visible);
    }

    public void selectSignedBy(String user) {
        commonWaiter($(By.xpath(String.format(XPATH_DROPDOWN, "Signed by"))), visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_OPTION_DROPDOWN, user))), visible).click();
    }
}
