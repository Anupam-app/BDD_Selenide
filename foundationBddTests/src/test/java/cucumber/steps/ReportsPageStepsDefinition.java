package cucumber.steps;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import dataobjects.Recipe;
import dataobjects.Report;
import dataobjects.ReportTemplate;
import dataobjects.ReportTemplateStatus;
import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.ReportsPage;

public class ReportsPageStepsDefinition {

    private final Report report;
    private final ReportsPage reportPage;
    private final ReportTemplate reportTemplate;
    private final User user;
    private final Recipe recipe;

    public ReportsPageStepsDefinition(ReportsPage reportPage, Report report, ReportTemplate reportTemplate, User user,
            Recipe recipe) {
        this.reportPage = reportPage;
        this.reportTemplate = reportTemplate;
        this.user = user;
        this.report = report;
        this.recipe = recipe;
    }

    @Given("I goto report management page")
    public void iGotoReportManagementPage() {
        reportPage.goToReports();
        reportPage.switchToFrame();
    }

    @Given("I select report from dropdown {string}")
    public void iSelectReportFromDropdown(String report) {
        this.reportTemplate.setName(report);
        reportPage.selectReport(report);
    }

    @When("I choose recipe run {string}")
    public void iChooseRun(String run) {
        reportPage.selectRun(run);
    }

    @And("I choose corresponding recipe run")
    public void iChooseCorrespondingRecipeRun() throws InterruptedException {
        reportPage.selectRunWithWaiting(reportTemplate.getName(), recipe.getRunId());
    }

    @When("I choose recipe run {string} for consolidation")
    public void iChooseRunForConsolidation(String run) {
        reportPage.selectForConsolidationRun(run);
    }

    @When("I choose template {string}")
    public void iChooseTemplate(String template) {
        reportPage.chooseReportTemplate(template);
    }

    @When("I click on generate button")
    public void iClickOnGenerateButton() {
        reportPage.generateReport();
        report.setName(reportPage.waitAndGetGeneratedNameFromNotificationWhenFileGenerated());
    }

    @When("I click on view button")
    public void iClickOnViewButton() {
        reportPage.viewReports(this.report.getName());
    }

    @When("I trigger report mode")
    public void iTriggerReportMode() {
        reportPage.gotoReportsTab();
    }

    @Then("I should see the report signed")
    public void iShouldSeeTheReportSigned() {
        reportPage.switchToFrame();
        reportPage.gotoRunTab();
        reportPage.gotoReportsTab();
        reportPage.checkSigned(this.report.getName(), this.user.getUserName());
    }

    @Then("I see the report")
    public void reportIsGenerated() {
        reportPage.exists(this.report.getName());
    }

    @When("I should see the report file presence")
    public void iShouldSeeTheReportFilePresence() {
        reportPage.viewReports(this.report.getName());
        reportPage.checkReportPdfInPage();
    }

    @Then("I check report content")
    public void iCheckReportContent() throws Exception {

        this.report.checkEventTable(reportPage.getPdfUrl());
        this.report.checkRunId(reportPage.getPdfUrl(),this.recipe.getRunId());
    }

    @Then("I verify that user information are consistent")
    public void iVerifyThatUserInformationAreConsistent() throws Exception {

        this.report.checkUserInformation(reportPage.getPdfUrl());
    }

    @When("I search report {string}")
    public void iSearchReports(String report) {
        this.report.setName(report);
        reportPage.searchReportOrTemplate(report);
    }

    @Then("I esign the report")
    public void iEsignReports() {
        reportPage.esignReports(this.report.getName(), this.user.getPassword());
        report.setName(reportPage.waitAndGetGeneratedNameFromNotificationWhenFileSigned());
    }

    @Given("I trigger report template mode")
    public void iTriggerTemplateMode() {
        reportPage.gotoTemplate();
    }

    @When("I create random report template")
    public void iCreateRandomReportTemplate() {
        this.reportTemplate.setName(RandomStringUtils.randomAlphabetic(10));
        this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
        reportPage.createTemplate(this.reportTemplate.getName());
    }

    @When("I select report include {string}")
    public void iSelectReportInclude(String reportInclude) {
        reportPage.includeReport(reportInclude);
    }

    @When("I save the report template")
    public void iSaveReportTemplate() {
        reportPage.saveReportTemplate();
    }

    @When("I search the report template")
    public void iSearchTheReportTemplate() {
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
    }

    @When("I put the report template in review")
    public void iPutTheReportTemplateInReview() {
        this.reportTemplate.setStatus(ReportTemplateStatus.IN_REVIEW);
        reportPage.putReportTemplateToReview(this.reportTemplate.getName(), this.reportTemplate.getStatus());
    }

    @Then("I approve the report template")
    public void iApproveTheReportTemplate() {
        this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
        reportPage.approveTemplate(this.reportTemplate.getName(), this.user.getPassword(),
                this.reportTemplate.getStatus());
    }

    @Then("I verify the report template")
    public void iVerifyTheReportTemplate() {
        reportPage.openReportTemplate(this.reportTemplate.getName());
        Assert.assertEquals(this.reportTemplate.getStatus(), this.reportPage.getStatus());
    }

    @When("I generate audit trail report")
    public void iGenerateAuditTrailReport() {
        iGotoReportManagementPage();
        iSelectReportFromDropdown("Audit Trail");
        iClickOnGenerateButton();
    }

    @When("I check the audit trail report")
    public void iVerifyTheAuditTrailReport() {
        iGotoReportManagementPage();
        iTriggerReportMode();
        iShouldSeeTheReportFilePresence();
    }
    
    @And("I create new report template with existing name")
    public void iEnterExistingReportTemplateName() {
        reportPage.createTemplate(this.reportTemplate.getName());
    }
    
    @Then("I verify the template name error message")
    public void iVerifyErrorMessage() {
    	reportPage.errorMessage(this.reportTemplate.getName());
    }
    
    @Then("I verify the password error message {string}")
    public void iVerifyPasswordErrorMessage(String value) {
    	reportPage.errorMessageValidation(value);
    }
    
    @And("I try to approve the report template with wrong password {string}")
    public void iApproveTemplateWrongPassword(String value) {
    	this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
    	reportPage.approveTemplate(this.reportTemplate.getName(), value,  this.reportTemplate.getStatus());
    }
    
    @Then("I esign the report with wrong password {string}")
    public void iEsignReportsWrongPassword(String value) {
        reportPage.esignReports(this.report.getName(),value);
    }
    
    @Then("I verify template is not editable")
    public void iVerifyTemplateIsEditable() {
    	reportPage.approvedTemplateValidation();
    }
}
