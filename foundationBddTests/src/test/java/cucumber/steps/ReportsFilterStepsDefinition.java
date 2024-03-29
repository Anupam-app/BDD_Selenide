package cucumber.steps;


import java.text.ParseException;
import java.util.List;

import org.junit.Assert;

import dataobjects.ReportTemplate;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.ReportsPage;

public class ReportsFilterStepsDefinition {

    private final ReportsPage reportPage;
    private final ReportTemplate reportTemplate;

    public ReportsFilterStepsDefinition(ReportsPage reportPage, ReportTemplate reportTemplate) {
        this.reportPage = reportPage;
        this.reportTemplate = reportTemplate;
    }

    @When("I search the report template {string}")
    public void iSearchTheReportTemplate(String templateName) {
        this.reportTemplate.setName(templateName);
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
    }

    @When("I click on filter icon and select template status {string}")
    public void iSelectTemplateStatus(String templateStatus) {
        reportPage.selectTemplateStatus(templateStatus);
    }

    @Then("I should see template {string}")
    public void iVerifyTemplate(String templateName) {
        reportPage.checkTableContainsTemplate(templateName);
    }

    @When("I search the report name {string}")
    public void iSearchReport(String reportName) {
        reportPage.searchReportOrTemplate(reportName);
    }

    @Then("I should see report {string}")
    public void iVerifyReport(String reportName) {
        reportPage.checkTableContainsReport(reportName);
    }

    @When("I click on filter icon and select report type {string}")
    public void iSelectReportType(String reportType) {
        reportPage.selectReportType(reportType);
    }

    @When("I search the recipe run {string}")
    public void iSearchRunReport(String recipeRunName) {
        reportPage.searchReportOrTemplate(recipeRunName);
    }

    @When("I select template sort by {string} in {string}")
    public void iSelectSortTemplate(String columnName, String sortMode) {
        reportPage.sortListTemplate(columnName, Boolean.parseBoolean(sortMode));
    }

    @Then("{string} list should be sorted in {string} order")
    public void templatesShouldBeDisplayedInSortedOrder(String columnName, String sortMode) {
        reportPage.checkSortedElementTemplate(columnName, Boolean.parseBoolean(sortMode));
    }

    @Then("I should see recipe run {string}")
    public void iSeeRunReport(String recipeRunName) {
        reportPage.checkTableContainsRecipeRun(recipeRunName);
    }

    @Then("I should see recipe run {string} from consolidated report")
    public void iSeeRunFromConsolidatedReport(String recipeRunName) {
        reportPage.checkConsolidatedTableContainsRecipeRun(recipeRunName);
    }

    @When("I click on filter icon and select runs status {string}")
    public void iSelectRunsStatus(String runStatus) {
        reportPage.selectRunStatus(runStatus);
    }

    @When("I select report user from dropdown created by {string}")
    public void iSelectReportCreatedByUserStatus(String user) {
        reportPage.selectCreatedBy(user);
    }

    @When("I select report user from dropdown signed by {string}")
    public void iSelectReportSignedByUserStatus(String user) {
        reportPage.selectSignedBy(user);
    }

    @When("I filter on icon and select run status as {string}")
    public void selectRunStatus(String status1) {
        reportPage.selectrunStatus(status1);
    }

    @Then("I should see run status as {string}")
    public void iSeeRunStatus(String status) {
        Assert.assertTrue(reportPage.verifyRunStatus(status));
    }

    @Then("^I should see recipe run list displayed based on date range dropdown$")
    public void recipeRunListDisplayed(DataTable datatable) throws ParseException, InterruptedException {
        List<String> options = datatable.asList();
        for (String dateRange : options) {
            reportPage.selectDateRange(dateRange);
            Assert.assertTrue(reportPage.verifyDateRanges(dateRange));
        }
    }

    @Then("^Report columns should be sorted in ascending order$")
    public void reportColumnsSortedAscending(DataTable datatable) {
        List<String> options = datatable.asList();
        for (String columnName : options) {
            reportPage.sortList(columnName, false);
            reportPage.checkSortedElement(columnName, false);
        }
    }

    @Then("I see Audit logs are displayed for date range {int} and {string} for at least {int} results")
    public void iVerifyAuditLogsUser(int days, String userid, int results) {
        reportPage.checkTableContainsUserAndDateRange(days, userid, results);
    }

    @Then("^Report columns should be sorted in descending order$")
    public void reportColumnsSortedDescending(DataTable datatable) {
        List<String> options = datatable.asList();
        for (String columnName : options) {
            reportPage.sortList(columnName, true);
            reportPage.checkSortedElement(columnName, true);
        }
    }

    @Then("^I should see recipe report list displayed based on date range dropdown$")
    public void iViewRecipeReportListDateRange(DataTable datatable) throws ParseException, InterruptedException {
        List<String> options = datatable.asList();
        for (String dateRange : options) {
            reportPage.selectDateRangeReport(dateRange);
            reportPage.verifyDateRangesReport(dateRange);
        }
    }

    @Then("^Reports columns should be sorted in ascending order$")
    public void reportsColumnsSortedAscendingOrder(DataTable datatable) {
        List<String> options = datatable.asList();
        for (String columnName : options) {
            reportPage.sortList(columnName, false);
            reportPage.checkSortedElements(columnName, false);
        }
    }

    @Then("^Reports columns should be sorted in descending order$")
    public void reportsColumnsSortedDescendingOrder(DataTable datatable) {
        List<String> options = datatable.asList();
        for (String columnName : options) {
            reportPage.sortList(columnName, true);
            reportPage.checkSortedElement(columnName, true);
        }
    }

    @Then("^I verify consolidated columns and columns should be sorted$")
    public void iVerifyConsolidatedColumnsSorted(DataTable datatable) {
        List<String> options = datatable.asList();
        for (String columnName : options) {
            reportPage.sortListConsolidated(columnName, false);
            reportPage.checkSortedElementConsolidate(columnName, false);
        }
    }

    @Then("I should see consolidated status as {string}")
    public void iSeeConsolidatedStatus(String status) {
        Assert.assertTrue(reportPage.verifyConsolidatedStatus(status));
    }

    @When("I click on filter icon and select eSignStatus {string}")
    public void iSelectESignStatus(String status) {
        reportPage.selectESignStatus(status);
    }

    @Then("I should see reports with eSignStatus {string}")
    public void iSeeESignedStatus(String status) {
        reportPage.verifyESignedStatus(status);
    }

    @Then("I should see consolidated runs list displayed based on date range dropdown")
    public void iSeeConsolidatedRunsBasedOnDate(DataTable datatable) throws ParseException, InterruptedException {
        List<String> options = datatable.asList();
        for (String dateRange : options) {
            reportPage.selectConsolidatedDateRange(dateRange);
            Assert.assertTrue(reportPage.verifyConsolidateDateRanges(dateRange));
        }
    }

}
