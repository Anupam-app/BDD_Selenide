package cucumber.steps;

import dataobjects.Recipe;
import dataobjects.Report;
import dataobjects.ReportTemplate;
import dataobjects.ReportTemplateStatus;
import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.ReportsPage;
import technology.tabula.Table;
import utils.pdf.PdfTableExtractUtils;
import utils.ssl.SSLUtils;

public class ReportsPageStepsDefinition {

    private static final String USER_COLUMN_FORMAT = "[aA1-zZ9]+\\([aA1-zZ9\\-]+(\\s[aA1-zZ9\\-]+)*\\)";

    private Report report;
    private ReportsPage reportPage;
    private ReportTemplate reportTemplate;
    private User user;
    private Recipe recipe;

    public ReportsPageStepsDefinition(ReportsPage reportPage, Report report, ReportTemplate reportTemplate, User user, Recipe recipe) {
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
        reportPage.checkSigned(this.report.getName(),this.user.getUserName());
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

    @Then("I verify the table with headers {string} contains at least one row")
    public void iVerifyTheTableContainsAtLeastOneRow(String tableHeaders) throws IOException {
        SSLUtils.disableSslVerification();
        URL url = new URL(reportPage.getPdfUrl());

        Table table = PdfTableExtractUtils.getTableFromTableHeader(url.openStream(), tableHeaders);
        Assert.assertNotNull("No table found", table);
        Assert.assertTrue("Table must contains at least one row", table.getRows().size() > 1);
    }

    @Then("I verify the {string} in {string}")
    public void iVerifyTheRunId(String fieldId, String tableTitle) throws IOException {

        SSLUtils.disableSslVerification();
        URL url = new URL(reportPage.getPdfUrl());

        // get table from table title and check is not null and contains rows
        Table table = PdfTableExtractUtils.getTableFromTableTitle(url.openStream(), tableTitle);
        Assert.assertNotNull("No table found", table);
        Assert.assertTrue("Table contains no data", table.getRows().size() > 1);

        // get field value and check is not null
        String fieldValue = PdfTableExtractUtils.getTableFieldValue(table, fieldId);
        Assert.assertNotNull("No field with id " + fieldId, fieldValue);
        Assert.assertEquals("Unexpected run id value", recipe.getRunId(), fieldValue);
    }

    @Then("I verify the user data details are consistent")
    public void iVerifyTheUserDataDetailsAreConsistent() throws IOException {

        SSLUtils.disableSslVerification();
        URL url = new URL(reportPage.getPdfUrl());

        // get all tables of the report
        List<Table> reportTables = PdfTableExtractUtils.getTables(url.openStream());

        // for each table, check user data details format
        for(Table reportTable : reportTables) {

            int userColumnIndex = PdfTableExtractUtils.getColumnIndex(reportTable, "User");

            if(userColumnIndex>0) {

                for (int i = 0; i < reportTable.getRowCount(); i++) {

                    if (i > 0) {

                        String userColumnValue = reportTable.getRows().get(i).get(userColumnIndex).getText(false);

                        if (!StringUtils.isEmpty(userColumnValue)) {
                            Assert.assertTrue(String.format(
                                "User format error. Value : %s. Expected pattern : Login(Firstname Lastname)",
                                userColumnValue), userColumnValue.matches(USER_COLUMN_FORMAT));

                        }
                    }
                }
            }
        }
    }

    @When("I search report {string}")
    public void iSearchReports(String report) {
        this.report.setName(report);
        reportPage.searchReportOrTemplate(report);
    }

    @Then("I esign the report")
    public void iEsignReports() {
        reportPage.esignReports(this.report.getName(),this.user.getPassword());
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
        reportPage.approveTemplate(this.reportTemplate.getName(), this.user.getPassword(), this.reportTemplate.getStatus());
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
}
