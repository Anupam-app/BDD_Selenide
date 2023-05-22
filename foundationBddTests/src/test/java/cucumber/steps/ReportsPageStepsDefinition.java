package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Login;
import dataobjects.Report;
import dataobjects.ReportTemplate;
import dataobjects.ReportTemplateStatus;
import dataobjects.Role;
import dataobjects.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.LoginPage;
import pageobjects.pages.ReportsPage;
import pageobjects.utility.ContextHelper;
import pageobjects.utility.SelenideHelper;

public class ReportsPageStepsDefinition {

    private final Report report;
    private final ReportsPage reportPage;
    private final ReportTemplate reportTemplate;
    private final User user;
    private final Role role;
    private final LoginPage loginPage;
    private final Login login;

    public ReportsPageStepsDefinition(LoginPage loginPage, ReportsPage reportPage, Report report,
            ReportTemplate reportTemplate, User user, Login login, Role role) {
        this.loginPage = loginPage;
        this.reportPage = reportPage;
        this.report = report;
        this.reportTemplate = reportTemplate;
        this.user = user;
        this.login = login;
        this.role = role;
    }

    @Given("I goto report management page")
    public void iGotoReportManagementPage() {
        reportPage.goToReports();
        reportPage.switchToFrame();
    }

    @Then("I see Runs, Templates, Reports tabs are displayed")
    public void iSeeTabs() {
        reportPage.verifyTabs();
    }

    @And("I see list of {string} are displayed")
    public void iSeeListOfRuns(String tab) throws InterruptedException {
        reportPage.verifyList(tab);
    }

    @And("below {string} columns are displayed")
    public void verifyColumn(String tab, DataTable table) {
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            reportPage.verifyColumn(list.get(i)
                    .get(0), tab, i);
        }
    }

    @Given("I select report from dropdown {string}")
    public void iSelectReportFromDropdown(String reportName) {
        this.reportTemplate.setName(reportName);
        reportPage.selectReport(reportName);
    }

    @Given("I select user from dropdown {string}")
    public void iSelectUserFromDropdown(String user) {
        this.user.setName(user);
        reportPage.selectUser(user);
    }

    @Given("I select user in dropdown {string}")
    public void iSelectUserInDropdown(String user) {
        this.user.setName(user);
        reportPage.selectUserOnRunPage(user);
    }

    @Given("I select date range as {string}")
    public void iSelectDateRange(String dateFilter) {
        reportPage.selectDateFilterOnRunPage(dateFilter);
    }

    @When("I choose recipe run {string}")
    public void iChooseRun(String run) {
        reportPage.selectRun(run);
    }

    @And("I choose corresponding recipe run")
    public void iChooseCorrespondingRecipeRun() {
        report.getRecipes()
                .forEach(recipe -> reportPage.selectRunWithWaiting(reportTemplate.getName(), recipe.getRunId()));
    }

    @When("I choose recipe run {string} for consolidation")
    public void iChooseRunForConsolidation(String run) {
        reportPage.selectForConsolidationRun(run);
    }

    @When("I choose template {string}")
    public void iChooseTemplate(String template) {
        reportPage.chooseReportTemplate(template);
        this.report.setReportName(template);
    }

    @Then("I check audit trial report content")
    public void iCheckAuditTrialReportContent() throws Exception {
        this.report.checkAuditTable(reportPage.getPdfUrl());
        this.report.checkUserInformation(reportPage.getPdfUrl(), this.user.getName());
        this.report.checkEventTimeInformation(reportPage.getPdfUrl());
        this.report.checkSignturesTable(reportPage.getPdfUrl());
    }

    @When("I don't see the presence of run mode")
    public void iDontSeeRunLink() {
        reportPage.verifyRunMode();
    }

    @Then("I don't see the presence of generate button")
    public void iDontSeeGenerateButton() {
        reportPage.verifyGenerateButton();
    }

    @When("I select device {string}")
    public void iSelectDefaultDevice(String device) {
        if (ContextHelper.isOrchestrator()) {
            reportPage.selectDevice(device);
        }
    }

    @Then("I click on generate button")
    public void iClickOnGenerateButton() throws InterruptedException {
        reportPage.generateReport();
        report.setName(reportPage.waitAndGetGeneratedNameFromNotificationWhenFileGenerated());
        reportPage.reportRequestNotificationVisibility();
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
        reportPage.checkSigned(this.report.getName(), this.login.getLogin());
    }

    @Then("I see the report")
    public void reportIsGenerated() {
        reportPage.exists(this.report.getName());
    }

    @When("I check audit trial logs")
    public void iCheckAudiTrialLogs() {
        report.getRecipes()
                .forEach(
                        recipe -> reportPage.checkRecipeCTRLOperationLogs(recipe.getBatchId(), recipe.getRecipeName()));
    }

    @When("I should see the report file presence")
    public void iShouldSeeTheReportFilePresence() {
        reportPage.viewReports(this.report.getName());
        reportPage.checkReportPdfInPage();
    }

    @When("I see the report file is generated")
    public void iShouldSeeTheReportFileGenerated() {
        iGotoReportManagementPage();
        iTriggerReportMode();
        reportPage.viewReports(this.report.getName());
        reportPage.checkReportPdfInPage();
    }

    @Then("I check report content")
    public void iCheckReportContent() throws Exception {
        this.report.checkEventTable(reportPage.getPdfUrl());
        this.report.checkRunId(reportPage.getPdfUrl(), report.getRecipes());
    }

    @Then("I verify that user information are consistent")
    public void iVerifyThatUserInformationAreConsistent() throws Exception {
        this.report.checkUserInformation(reportPage.getPdfUrl(), this.user.getName());
    }

    @Then("I generate the {string} Report for {string} user")
    public void iGenerateTheAuditTrailReport(String report, String user) {
        reportPage.goToReports();
        reportPage.switchToFrame();
        reportPage.iVerifyReportPageLoaded();
        reportPage.selectReport(report);
        reportPage.selectUserOnRunPage(user);
    }

    @Then("I see the {string} user disabled in report")
    public void iVerifyThatUserIsDisabled(String userName) throws Exception {
        this.report.checkUserIsEnabledOrDisabled(reportPage.getPdfUrl(), userName, true, this.login.getLogin());
        switchTo().parentFrame();
    }

    @Then("I see the {string} is changed to {string} in report")
    public void iverifyRecipeStatus(String recipeName, String status) throws Exception {
        this.report.checkRecipeStatus(reportPage.getPdfUrl(), recipeName, status, this.login.getLogin());
        switchTo().parentFrame();
    }

    @Then("I see the {string} user enabled in report")
    public void iVerifyThatUserIsEnabled(String userName) throws Exception {
        this.report.checkUserIsEnabledOrDisabled(reportPage.getPdfUrl(), userName, false, this.login.getLogin());
        switchTo().parentFrame();
    }

    @When("I search report {string}")
    public void iSearchReports(String report) {
        this.report.setName(report);
        reportPage.searchReportOrTemplate(report);
    }

    @Then("I esign the report")
    public void iEsignReports() throws InterruptedException {
        reportPage.eSignReports(this.report.getName(), this.login.getPassword());
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

    @When("I select below parameters")
    public void iSelectTrendsParameters(DataTable table) {
        List<List<String>> list = table.asLists(String.class);
        for (int i = 1; i < list.size(); i++) {
            reportPage.selectParams(list.get(i)
                    .get(0));
        }
    }

    @When("I choose {string} trends {string}")
    public void iSelectTrendsParameters(String noOfParams, String parameters) {
        reportPage.selectParameters(noOfParams, parameters);
    }

    @And("I create five trends chart")
    public void iCreate5TrendsCharts() {
        reportPage.create5Trends();
    }

    @And("I save trends")
    public void iCreateTrendsCharts() {
        reportPage.saveTrends();
    }

    @Then("I verify that sixth chart is not allowed")
    public void iSixthTrendsChartNotAllowed() {
        reportPage.verifySixthChartNotAllowed();
    }


    @Then("I verify the error message {string}")
    public void iverifyTheErrMsg(String message) {
        reportPage.isGeneratedNotificationWhenMoreThanSixParams(message);
    }


    @When("I save the report template")
    public void iSaveTheReportTemplate() {
        reportPage.saveReportTemplate();
    }

    @When("I search the report template")
    public void iSearchTheReportTemplate() {
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
    }

    @When("I edit the report template")
    public void ieditTheReportTemplate() {
        reportPage.editReportOrTemplate(this.reportTemplate.getName());
    }

    @When("I put the report template in review")
    public void iPutTheReportTemplateInReview() {
        this.reportTemplate.setStatus(ReportTemplateStatus.IN_REVIEW);
        reportPage.putReportTemplateToReview(this.reportTemplate.getName(), this.reportTemplate.getStatus());
    }

    @Then("I approve the report template")
    public void iApproveTheReportTemplate() {
        this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
        reportPage.approveTemplate(this.reportTemplate.getName(), this.login.getPassword(),
                this.reportTemplate.getStatus());
    }

    @Then("I search and verify the report template")
    public void iSearchAndVerifyTheReportTemplate() {
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
        reportPage.openReportTemplate(this.reportTemplate.getName());
        Assert.assertEquals(this.reportTemplate.getStatus(), this.reportPage.getStatus());
        reportPage.cancelButton();
        reportPage.gotoRunTab();
        switchTo().parentFrame();
    }

    @When("I generate audit trail report")
    public void iGenerateAuditTrailReport() throws InterruptedException {
        iGotoReportManagementPage();
        iSelectReportFromDropdown("Audit Trail");
        iClickOnGenerateButton();
    }

    @When("I verify audit logs for user update")
    public void iVerifyAuditLogsForUserUpdate() {
        reportPage.switchToFrame();
        reportPage.verifyAuditLogsForUserUpdate(this.user.getUserName());
        switchTo().parentFrame();
    }

    @When("I verify audit logs for role update")
    public void iVerifyAuditLogsForRoleUpdate() {
        reportPage.switchToFrame();
        reportPage.verifyAuditLogsForRoleUpdate(this.role.getRoleName());
        switchTo().parentFrame();
    }

    @Then("I see the role added in report")
    public void iVerifyThatRoleIsAdded() throws Exception {
        this.report.checkAddedRole(reportPage.getPdfUrl(), this.role.getRoleName(), this.login.getLogin(),
                this.role.getPermissions());
        switchTo().parentFrame();
    }

    @When("I check the audit trail report")
    public void iVerifyTheAuditTrailReport() {
        iGotoReportManagementPage();
        iTriggerReportMode();
        iShouldSeeTheReportFilePresence();
    }

    @Then("I see expected texts from report module")
    public void iSeeExpectedTextsFromReportModule() {
        var expectedText = I18nUtils.getValueFromKey("report.reportNavbar.menu.reportManagement");
        reportPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @And("I provide the existing template name")
    public void iEnterExistingReportTemplateName() {
        this.reportTemplate.setName("testReportTemplate");
        reportPage.createDuplicateTemplate(this.reportTemplate.getName());
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
    public void iApproveTemplateWrongPassword(String password) {
        this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
        reportPage.approveTemplate(this.reportTemplate.getName(), password, this.reportTemplate.getStatus());
    }

    @Then("I eSign the report with wrong password {string}")
    public void iESignReportsWrongPassword(String value) {
        reportPage.eSignReports(this.report.getName(), value);
    }

    @Then("I verify template is not editable")
    public void iVerifyTemplateIsEditable() {
        reportPage.openReportTemplate(this.reportTemplate.getName());
        reportPage.approvedTemplateValidation();
    }

    @Then("I verify consolidate summary report")
    public void iVerifyConsolidateSummaryReport() throws Exception {
        this.report.validateConsolidateRunSummary(reportPage.getPdfUrl(), report.getRecipes());
    }

    @When("I wait for recipes in runs")
    public void iWaitForRecipesInRuns() throws InterruptedException {
        long tMax = 30000;
        long delay = 500;
        boolean waiting = true;
        while (waiting) {
            tMax -= delay;
            if (tMax < 0) {
                break;
            }
            Thread.sleep(delay);
            iTriggerReportMode();
            reportPage.gotoRunTab();
            iSelectReportFromDropdown("Consolidated");
            waiting = !report.getRecipes()
                    .stream()
                    .allMatch(recipe -> reportPage.isRunDisplayed(recipe.getRunId()));
        }
    }

    @When("I choose recipes from consolidation run")
    public void iChooseRecipeRunForConsolidation() {
        report.getRecipes()
                .forEach(r -> reportPage.selectForConsolidationRun(r.getRunId()));
    }

    @Given("I select the report template")
    public void iSelectTemplate() {
        reportPage.openReportTemplate(this.reportTemplate.getName());
    }

    @When("I save As the report template")
    public void iSaveAsReportTemplate() {
        reportPage.iSaveAs();
    }

    @Then("I see SaveTemplate popup window")
    public void iSeeSavePopUpWindow() {
        reportPage.iValidateWindow();
    }

    @When("I change the template status Approved to Inactive")
    public void iChangeTemplateStatusApprovedToInactive() {
        this.reportTemplate.setStatus(ReportTemplateStatus.IN_ACTIVE);
        reportPage.putReportTemplateToInactive(this.reportTemplate.getName(), this.reportTemplate.getStatus());
    }

    @When("I should see new template created with {string} status")
    public void ISearchModifiedTemplate(String status) {
        reportPage.iSearchReportTemplate(this.reportTemplate.getSaveAsName());
        reportPage.iValidateStatus(status);
    }

    @When("Existing template should also be present with {string}")
    public void ISearchExistingTemplate(String status) {
        reportPage.iSearchReportTemplate(this.reportTemplate.getName());
        reportPage.iValidateStatus(status);
    }

    @Then("I verify run summary report report")
    public void iVerifyRunSummaryReport() throws IOException {
        this.report.checkEventTable(reportPage.getPdfUrl());
        this.report.checkFiledIds(reportPage.getPdfUrl(), report.getRecipes(), report.getReportName());
        this.report.checkPreRunColumnsInReport(reportPage.getPdfUrl());
        this.report.checkRecipeColumnsInReport(reportPage.getPdfUrl());
    }

    @When("I enter {string} as username and {string} password")
    public void iEnterUsernamePassword(String username, String password) {
        loginPage.setUser(username);
        loginPage.setPassword(password);
    }

    @Then("I verify recipe details captured in report run tab {string}")
    public void iVerifyRunReportWithRecipeEntries(String recipeName) throws ParseException {
        reportPage.verifyRunDetails(recipeName, "Operation", "Completed");
    }

    @And("I should see newly created user {string} present in report")
    public void iSeeNewlyCreatedUserPresentInReport(String user) {
        reportPage.verifyNewUser(user);
    }

    @Then("I should see change password entries in audit trail report for {string}")
    public void iverifyAuditTrailReport(String username) throws ParseException {
        var message = String.format("%s changed the account password", username);
        Assert.assertTrue(reportPage.verifyAuditTrail(message));
        SelenideHelper.goToDefault();
    }

    @Then("I verify custom role updated details captured in audit trail for user {string}")
    public void iVerifyAuditTrailReportWithEntries(String username) throws ParseException {
        var message = String.format("%s updated Role %s", username, this.role.getRoleName());
        var message1 = String.format("Role -%s", this.role.getUpdatedRoleName());
        reportPage.verifyAuditTrailRecord(message, message1);
        SelenideHelper.goToDefault();
    }

    @Then("I verify custom role disabled details captured in audit trail for user {string}")
    public void iVerifyAuditTrailReportCustomRole(String username) throws ParseException {
        var message = String.format("%s disabled role %s", username, this.role.getRoleName());
        reportPage.verifyAuditTrailRecord(message, this.role.getRoleName());
        SelenideHelper.goToDefault();
    }

    @Then("I verify custom role enabled details captured in audit trail for user {string}")
    public void iVerifyAuditTrailReportCustomRoleEnabled(String username) throws ParseException {
        var message = String.format("%s enabled role %s", username, this.role.getRoleName());
        reportPage.verifyAuditTrailRecord(message, this.role.getRoleName());
        SelenideHelper.goToDefault();
    }

    @Then("I verify custom role permissions details captured in audit trail for user")
    public void iVerifyAuditTrailReportRolePermissions() throws IOException {
        iVerifyTheAuditTrailReport();
        this.report.checkModifiedRolePermission(reportPage.getPdfUrl(), this.role.getUpdatedRoleName(),
                this.role.getRoleName(), this.login.getLogin(), this.role.getPermissions(),
                this.role.getOldPermissions());
        SelenideHelper.goToDefault();
    }

    @Then("I see the role deleted in report")
    public void iVerifyThatRoleIsDeleted() throws Exception {
        this.report.checkDeletedRole(reportPage.getPdfUrl(), this.role.getRoleName(), this.login.getLogin());
        switchTo().parentFrame();
    }

    @Then("I verify consolidate manual run summary report")
    public void iVerifyConsolidateManualSummaryReport() throws Exception {
        this.report.verifyConsolidateManualSummaryReport(reportPage.getPdfUrl());
    }

    @And("I save Trends without name")
    public void iCreateTrendsChartsWithoutName() {
        reportPage.saveTrendsButton();
    }

    @Then("I see error message displayed {string}")
    public void iSeeErrorMessageIsDisplayed(String message) {
        reportPage.isGeneratedNotificationWhenCreateExistingUsername(message);
    }

    @When("I create report template")
    public void iCreateReportTemplate() {
        iGotoReportManagementPage();
        iTriggerTemplateMode();
        this.reportTemplate.setName(RandomStringUtils.randomAlphabetic(10));
        this.reportTemplate.setStatus(ReportTemplateStatus.APPROVED);
        reportPage.createTemplate(this.reportTemplate.getName());
    }

    @And("I select below report include sections")
    public void selectReportInclude(DataTable table) {
        List<List<String>> reportInclude = table.asLists(String.class);
        for (List<String> strings : reportInclude) {
            reportTemplate.setReportIncludeOption(strings.get(0));
            reportPage.includeReport(strings.get(0));
        }
    }

    @When("I search and approve the report template")
    public void iSearchAndApproveTheReportTemplate() {
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
        iPutTheReportTemplateInReview();
        reportPage.saveReportTemplate();
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
        iApproveTheReportTemplate();
    }

    @When("I open the report template {string}")
    public void iSearchAndOpenTheReportTemplate(String templateName) {
        reportPage.searchReportOrTemplate(templateName);
        reportPage.openReportTemplate(templateName);
    }

    @Given("I open the report template dashboard")
    public void iOpenTemplateDashboard() {
        iGotoReportManagementPage();
        reportPage.gotoTemplate();
    }

    @Then("I verify the audit trail for template")
    public void iCheckAuditTrialReportContentForTemplate() throws Exception {
        this.report.checkAuditTable(reportPage.getPdfUrl());
        this.report.checkTemplateStatus(reportPage.getPdfUrl(), this.reportTemplate.getName(),
                this.reportTemplate.getStatus(), this.login.getLogin());
        this.report.checkCreatedTemplate(reportPage.getPdfUrl(), this.reportTemplate.getName(),
                this.reportTemplate.getStatus(), this.login.getLogin());
    }

    @When("I verify audit logs for template update")
    public void iVerifyAuditLogsForTemplateCreate() {
        reportPage.verifyAuditLogsForTemplateCreate(this.reportTemplate.getName());
    }

    @When("I verify below options availability")
    public void iSeeTheAvailabilityOfOptions(DataTable table) {
        List<String> list = table.asList(String.class);
        for (String s : list) {
            reportPage.createTemplatePageValidation(s);
        }
    }

    @When("I search and open the report template {string}")
    public void iSearchOpenTheReportTemplate(String templateName) {
        this.reportTemplate.setName(templateName);
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
        reportPage.openReportTemplate(this.reportTemplate.getName());
    }

    @Then("I search and verify the updated report template")
    public void iSearchAndVerifyTheUpdatedReportTemplate() {
        reportPage.searchReportOrTemplate(this.reportTemplate.getName());
        reportPage.openReportTemplate(this.reportTemplate.getName());
        reportPage.isReportIncludeSelected(reportTemplate.getReportIncludeOption());
    }

    @Then("I clone the report template")
    public void iCloneTheReportTemplate() {
        reportPage.iValidation();
        reportPage.iSaveAs();
        reportPage.iValidateWindow();
        this.reportTemplate.setSaveAsName(RandomStringUtils.randomAlphabetic(10));
        this.reportTemplate.setStatus(ReportTemplateStatus.DRAFT);
        reportPage.iRename(this.reportTemplate.getSaveAsName());
        reportPage.iCheckNotificationMsg("Report template created");
    }

    @Given("I select batchID from dropdown {string}")
    public void iSelectBatchIDFromDropdown(String batchID) {
        reportPage.selectBatchId(batchID);
    }

    @Then("I see the role disabling enabling events in report")
    public void iVerifyThatRoleIsDisabledAndEnabled() throws Exception {
        this.report.verifyAuditReportForRoleDisableEnable(reportPage.getPdfUrl(), this.role.getRoleName(),
                this.login.getLogin());
        switchTo().parentFrame();
    }

}
