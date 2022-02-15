package cucumber.steps;

import dataobjects.Report;
import dataobjects.ReportTemplate;
import dataobjects.ReportTemplateStatus;
import dataobjects.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.ReportsFilter;

public class ReportsFilterStepsDefinition {
	
	private Report report;
    private ReportsFilter reportFilter;
    private ReportTemplate reportTemplate;
    private User user;

    public ReportsFilterStepsDefinition(ReportsFilter reportFilter, Report report, ReportTemplate reportTemplate, User user) {
        this.reportFilter = reportFilter;
        this.reportTemplate = reportTemplate;
        this.user = user;
        this.report = report;
    }
    
    @When("I click on filter icon and select template status {string}")
    public void iSelectTemplateStatus(String templateStatus) {
    	reportFilter.selectTemplateStatus(templateStatus);
    }
    
    @Then("I should see template {string}")
    public void iVerifyTemplate(String templateName) {
    	Assert.assertEquals(templateName,reportFilter.getTemplateName());
    }

    @When("I search the report name {string}")
    public void iSearchReport(String reportName) {
    	reportFilter.searchReport(reportName);
    }

    @Then("I should see report {string}")
    public void iVerifyReport(String reportName) {
    	Assert.assertEquals(reportName, reportFilter.getReportName());
    }
    
    @When("I click on filter icon and select report type {string}")
    public void iSelectReportType(String reportType) {
    	reportFilter.selectReportType(reportType);
    }

    @When("I search the recipe run{string}")
    public void isearchRunReport(String recipeRunName) {
    	reportFilter.searchReport(recipeRunName);
    }

    @Then("I should see recipe run {string}")
    public void iSeeRunReport(String recipeRunName) {
    	Assert.assertEquals(recipeRunName, reportFilter.getRecipeName());
    }

    @When("I click on filter icon and select runs status {string}")
    public void iSelectRunsStatus(String runStatus) {
    	reportFilter.selectRunStatus(runStatus);
    }

}
