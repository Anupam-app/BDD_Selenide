package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.ReportsFilterPage;

public class ReportsFilterStepsDefinition {
	

    private ReportsFilterPage reportFilter;

    public ReportsFilterStepsDefinition(ReportsFilterPage reportFilter) {
        this.reportFilter = reportFilter;
    }

    @When("I search the report template {string}")
    public void iSearchTheReportTemplate(String templateName) {
    	reportFilter.searchTemplate(templateName);
}
    
    @When("I click on filter icon and select template status {string}")
    public void iSelectTemplateStatus(String templateStatus) {
    	reportFilter.selectTemplateStatus(templateStatus);
    }
    
    @Then("I should see template {string}")
    public void iVerifyTemplate(String templateName) {
        reportFilter.checkTableContainsTemplate(templateName);
    }

    @When("I search the report name {string}")
    public void iSearchReport(String reportName) {
    	reportFilter.searchReport(reportName);
    }

    @Then("I should see report {string}")
    public void iVerifyReport(String reportName) {
        reportFilter.checkTableContainsReport(reportName);
    }
    
    @When("I click on filter icon and select report type {string}")
    public void iSelectReportType(String reportType) {
    	reportFilter.selectReportType(reportType);
    }

    @When("I search the recipe run {string}")
    public void isearchRunReport(String recipeRunName) {
    	reportFilter.searchReport(recipeRunName);
    }

    @Then("I should see recipe run {string}")
    public void iSeeRunReport(String recipeRunName) {
        reportFilter.checkTableContainsRecipeRun(recipeRunName);
    }

    @Then("I should see recipe run {string} from consolidated report")
    public void iSeeRunFromConsolidatedReport(String recipeRunName) {
        reportFilter.checkConsolidatedTableContainsRecipeRun(recipeRunName);
    }

    @When("I click on filter icon and select runs status {string}")
    public void iSelectRunsStatus(String runStatus) {
    	reportFilter.selectRunStatus(runStatus);
    }

    @When("I select report user from dropdown created by {string}")
    public void iSelectReportCreatedByUserStatus(String user) {
        reportFilter.selectCreatedBy(user);
    }

    @When("I select report user from dropdown signed by {string}")
    public void iSelectReportSignedByUserStatus(String user) {
        reportFilter.selectSignedBy(user);
    }

}
