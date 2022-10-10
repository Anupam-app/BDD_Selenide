package cucumber.steps;

import java.text.ParseException;

import com.codeborne.selenide.Selenide;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.ReportsPage;

public class ReportsFilterStepsDefinition {

    private ReportsPage reportPage;

    public ReportsFilterStepsDefinition(ReportsPage reportPage) {
        this.reportPage = reportPage;
    }

    @When("I search the report template {string}")
    public void iSearchTheReportTemplate(String templateName) {
        reportPage.searchReportOrTemplate(templateName);
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
    
    @Then("I see Audit logs are displayed for date range and {string}")
    public void iVerifyAuditLogsUser(String userid) throws InterruptedException, ParseException {
        reportPage.checkTableContainsUserAndDateRange(userid);
    }
    
    @When("I select template sort by {string} in {string}")
    public void iSelectSortTemplate(String columnName, String sortMode) {
		reportPage.sortList(columnName,Boolean.parseBoolean(sortMode));
    }
    
    @Then("{string} list should be sorted in {string} order")
    public void templatesShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
		reportPage.checkSortedElement(columnName,Boolean.parseBoolean(sortMode));
		Selenide.sleep(6000);
    }
    
    @When("I click on filter icon and select report type {string}")
    public void iSelectReportType(String reportType) {
        reportPage.selectReportType(reportType);
    }

    @When("I search the recipe run {string}")
    public void isearchRunReport(String recipeRunName) {
        reportPage.searchReportOrTemplate(recipeRunName);
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

}
