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
import pageobjects.pages.ReportsPage;

public class ReportsPageStepsDefination {

    private Report report;
    private ReportsPage reportPage;
    private ReportTemplate reportTemplate;
    private User user;

    public ReportsPageStepsDefination(ReportsPage reportPage, Report report, ReportTemplate reportTemplate, User user) {
        this.reportPage = reportPage;
        this.reportTemplate = reportTemplate;
        this.user = user;
        this.report = report;
    }

    @Given("I goto report management page")
    public void iGotoReportManagementPage() {
        reportPage.goToReports();
        reportPage.switchToFrame();
    }

    @Given("I select from dropdown {string}")
    public void iSelectFromDropdown(String report) {
        this.reportTemplate.setName(report);
        reportPage.selectReport(report);
    }

    @When("I click on generate button")
    public void iClickOnGenerateButton() {
        reportPage.generateReport();
        report.setName(reportPage.getGeneratedName());
    }

    @When("I trigger report mode")
    public void iTriggerReportMode() {
        reportPage.gotoReportsTab();
    }

    @Then("I see the report signed")
    public void iSeeTheReportSigned() {
        this.report.setName(String.format("%s_%s", this.report.getName(), this.user.getUserName()));
        reportPage.checkSigned(this.report.getName(),this.user.getUserName());
    }

    @Then("I see the report")
    public void reportIsGenerated() {
        reportPage.exists(this.report.getName());
    }

    @When("I view the report")
    public void iViewReports() {
        reportPage.viewReports(this.report.getName());
    }

    @When("I go to report view")
    public void iGoToReportView() {
        reportPage.goToReportView();
    }

    @Then("I check the report presence")
    public void iCheckTheReportPresence() {
        reportPage.checkReportPdfInPage();
    }

    @When("I search report {string}")
    public void iSearchReports(String report) {
        this.report.setName(report);
        reportPage.searchReportOrTemplate(report);
    }

    @Then("I esign the report")
    public void iEsignReports() {
        reportPage.esignReports(this.report.getName(),this.user.getPassword());
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
        reportPage.approveTemplate(this.reportTemplate.getName(), this.user.getPassword(), this.reportTemplate.getStatus());
    }

    @Then("I verify the report template")
    public void iVerifyTheReportTemplate() {
        reportPage.openReportTemplate(this.reportTemplate.getName());
        Assert.assertEquals(this.reportTemplate.getStatus(), this.reportPage.getStatus());
    }
}
