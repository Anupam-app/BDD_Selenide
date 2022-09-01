package pageobjects.pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Attribute;
import com.codeborne.selenide.conditions.Disabled;
import com.codeborne.selenide.conditions.Enabled;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.SortHelper;

import org.junit.Assert;
import dataobjects.ReportTemplate;
import java.util.function.Function;
import java.text.ParseException;


public class ReportsPage {

    private final String REPORT_TEMPLATE_STATUS_WITH_TEXT = "(//*[@id='template_status']//li)[text()='%s']";
    private final String PDF_VIEWER_IFRAME = "//iframe[@id='ReportViewIFrame']";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS = "(//*[@class='active-label'])";
    private final String XPATH_ACTIVE_TEMPLATE_STATUS_WITH_TEXT = XPATH_ACTIVE_TEMPLATE_STATUS + "[text()='%s']";
    private final String XPATH_SIGNED_REPORT = "//tr[td='Signed' and td='%s' and td='%s']";
    private final String XPATH_REPORT_NAME = "//td[text()='%s']";
    private final String XPATH_TEMPLATE_CHECKBOX = "//div[@class='item_value'][text()='%s']/ancestor::li/div[@class='check_box']";
    private final String XPATH_CONSOLIDATED_REPORT = "//*[@class='tbl-row']//td[text()='%s']";
    private final String XPATH_CHECKBOX_CONSOLIDATED_REPORT = "//td[text()='%s']/ancestor::tr//*[@class='checkbox']";
    private final String XPATH_NOTIFICATION_TEXT = "//*[@class='notification-summary'][contains(text(),'%s')]";
    private final String XPATH_CONSOLIDATED_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@class='table']//td[text()='%s']";
    private final String XPATH_REPORT_RECIPE_RUN_COLUMNS_BY_TEXT = "//table[@id='foundationRunListTable']//td[text()='%s']";
    private final String XPATH_TEMPLATE_COLUMNS_BY_TEXT = "//table[@id='templateListTable']//td[text()='%s']";
    private final String XPATH_REPORT_COLUMNS_BY_TEXT = "//table[@id='reportListTable']//td[text()='%s']";
    private final String XPATH_DROPDOWN = "//span[text()='%s']/ancestor::div[@class='custom-drop-down']";
    private final String XPATH_OPTION_DROPDOWN = "//option[@value='%s']/ancestor::li";

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
    //private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
    //private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));

    private final SelenideElement errorMsgSameTemplateName = $(By.xpath("//div[contains(@class,'alert_msg')]"));
    private final SelenideElement errorMsgTemplateApproval = $(By.xpath("//span[@class='validate-error']"));
    private final ElementsCollection checkBoxTemplate = $$(By.xpath("//ul[@id='checkbox_list']/li"));

    private final String duplicateNameNotification = "Failed to create report template because %s already exists. Use a different name.";
    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));
	private SelenideElement arrowIcon = $(By.xpath("//div[(@class='down-icon')]"));
	private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
	private ElementsCollection dateRange = $$(By.xpath("//div[(@class='ranges')]//li[i]"));
	private final SelenideElement dateColumn=$(By.xpath("//input[@name='dateRange']"));
	private final SelenideElement datePopup=$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright') and contains(@style,'block')]"));
	private ElementsCollection dateOptions = $$(By.xpath("//div[contains(@class,'daterangepicker ltr auto-apply show-ranges opensright')]/div/ul/li"));
	private final SelenideElement noDatamsg = $(By.xpath("//h4[text()='No runs matching with the applied filter.']"));
	private final SelenideElement startDateDesendingArrow=$(By.xpath("//th[text()='Start Date']/span[@class='order']"));
	private final SelenideElement startDateAsendingArrow=$(By.xpath("//th[text()='Start Date']/span[@class='react-bootstrap-table-sort-order dropup']"));
	private final SelenideElement statusColumn=$(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[4]"));
	private final SelenideElement startDate=$(By.xpath("//table[@id='foundationRunListTable']/tbody/tr[1]/td[2]"));
	private final SelenideElement clearAllFilters=$(By.xpath("//div[text()='Clear All']"));
	private final SelenideElement previousMonth = $(By.xpath("//div[@class='drp-calendar left']//th[@class='prev available']"));
	private ElementsCollection availableDates=$$(By.xpath("//div[@class='drp-calendar left']/div/table/tbody/tr/td[@class='available']"));
	private final String XPATH_ORDER_ICON = "//span[@class='%s']";
    private final String XPATH_USER_TABLE = "//table[@id='foundationRunListTable']";
	private final String XPATH_COLUMN_HEADER = "//th[text()='%s']";
    private final String XPATH_REPORT_COLUMNS = "//table[@id='foundationRunListTable']//td[%s]";
	Function<Integer, List<String>> getReportColumns = (index) -> {
        var users = $$(By.xpath(String.format(XPATH_REPORT_COLUMNS, index))).texts();
        users.removeIf(e -> StringUtils.isEmpty(e.trim()));
        return users;
    };
	private SelenideElement date;
	
    
    public void goToReports() {
        reportsManagementPage.click();
    }

    public void switchToFrame() {
        SelenideHelper.goToIFrame();
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

    public void includeReport(String reportInclude) {
        $(By.xpath(String.format(XPATH_TEMPLATE_CHECKBOX, reportInclude))).click();
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
		commonWaiter(errorMsgTemplateApproval, visible);
		errorMsgTemplateApproval.shouldHave(text(name));
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
		if (!statusColumn.isDisplayed()) {
			isTrue = noDatamsg.isDisplayed();
		} else {
			isTrue = statusColumn.getText().equalsIgnoreCase(status);
		}
		return isTrue;
	}

	public void selectDate(String daterange) {

		ElementsCollection options = dateRange;
		options.shouldBe(CollectionCondition.size(7));
		for (SelenideElement d : options) {
			for (int i = 0; i < options.size(); i++) {
				date.click();
				options.get(i).click();
			}
		}
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
			Thread.sleep(1000);
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
			Thread.sleep(3000);
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
    

}
