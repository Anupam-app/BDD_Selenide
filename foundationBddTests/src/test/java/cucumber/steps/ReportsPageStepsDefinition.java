package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;
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
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import com.codeborne.selenide.Selenide;

import pageobjects.pages.LoginPage;
import pageobjects.pages.ReportsPage;
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
			reportPage.verifyColumn(list.get(i).get(0), tab, i);
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
		report.getRecipes().forEach(recipe -> reportPage.selectRunWithWaiting(reportTemplate.getName(), recipe.getRunId()));
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
	}

	@When("I don't see the presence of run mode")
	public void iDontSeeGenerateButton() {
		reportPage.verifyRunMode();
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
        report.getRecipes().forEach(recipe -> reportPage.checkRecipeCTRLOperationLogs(recipe.getBatchId(), recipe.getRecipeName()));
    }

	@When("I should see the report file presence")
	public void iShouldSeeTheReportFilePresence() {
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
	public void iGenerateTheAuditTrailReport(String report, String user) throws Exception {
		reportPage.goToReports();
		reportPage.switchToFrame();
		this.reportTemplate.setName(report);
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
		reportPage.esignReports(this.report.getName(), this.login.getPassword());
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
	public void iSelectTrendsParameters(DataTable table) throws InterruptedException {
		List<List<String>> list = table.asLists(String.class);
		for (int i = 1; i < list.size(); i++) {
			reportPage.selectParams(list.get(i).get(0));
		}
	}

	@When("I choose {string} trends {string}")
	public void iSelectTrendsParameters(String noOfParams, String parameters) throws Exception {
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
	public void iSaveReportTemplate() {
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

	@Then("I verify the report template")
	public void iVerifyTheReportTemplate() {
		reportPage.openReportTemplate(this.reportTemplate.getName());
		Assert.assertEquals(this.reportTemplate.getStatus(), this.reportPage.getStatus());
	}

	@When("I generate audit trail report")
	public void iGenerateAuditTrailReport() throws InterruptedException {
		iGotoReportManagementPage();
		iSelectReportFromDropdown("Audit Trail");
		iClickOnGenerateButton();
	}
	
	@When("I verify audit logs for user update")
	public void iVerifyAuditLogs4UsrUpdate() throws InterruptedException {
		reportPage.switchToFrame();
		reportPage.verifyAuditLogs4UsrUpdate(this.user.getName());
		switchTo().parentFrame();
	}
	
	@When("I verify audit logs for role update")
	public void iVerifyAuditLogs4RoleUpdate() throws InterruptedException {
		reportPage.switchToFrame();
		reportPage.verifyAuditLogs4RoleUpdate(this.role.getRoleName());
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
		reportPage.approveTemplate(this.reportTemplate.getName(), value, this.reportTemplate.getStatus());
	}

	@Then("I esign the report with wrong password {string}")
	public void iEsignReportsWrongPassword(String value) {
		reportPage.esignReports(this.report.getName(), value);
	}

	@Then("I verify template is not editable")
	public void iVerifyTemplateIsEditable() {
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
			waiting = !report.getRecipes().stream().allMatch(recipe -> reportPage.isRunDisplayed(recipe.getRunId()));
		}
	}

	@When("I choose recipes from consolidation run")
	public void iChooseRecipeRunForConsolidation() {
		report.getRecipes().forEach(r -> reportPage.selectForConsolidationRun(r.getRunId()));
	}

	@Given("I select the report template")
	public void i_select_report_template() {
		reportPage.openReportTemplate(this.reportTemplate.getName());
	}

	@When("I save As the report template")
	public void i_save_as_the_report_template() {
		reportPage.iSaveAs();
	}

	@Then("I see SaveTemplate popup window")
	public void i_see_save_template_popup_window() {
		reportPage.ivalidateWindow();
	}

	@When("I modify the Existing template")
	public void i_modify_the_existing_template() throws InterruptedException {
		this.reportTemplate.setSaveAsName(RandomStringUtils.randomAlphabetic(10));
		this.reportTemplate.setStatus(ReportTemplateStatus.DRAFT);
		reportPage.iRename(this.reportTemplate.getSaveAsName());
	}

	@When("I change the templete status Approved to Inactive")
	public void i_change_template_status_approved_to_inactive() {
		this.reportTemplate.setStatus(ReportTemplateStatus.IN_ACTIVE);
		reportPage.putReportTemplateToinactive(this.reportTemplate.getName(), this.reportTemplate.getStatus());
	}

	@Then("I see {string} button enable and save As the report template")
	public void i_see_button_enable_and_save_as_the_report_template(String string) {
		reportPage.iValidation();
		reportPage.iSaveAs();
	}

	@And("I save report template")
	public void i_save_report_template() {
		reportPage.isave();
	}

	@Then("I see {string} successfully message")
	public void i_see_successfully_message(String message) {
		reportPage.iCheckNotifactionMsg(message);
	}

	@When("I search modified the template")
	public void i_search_modified_template() throws InterruptedException {
		reportPage.iSearchrepo(this.reportTemplate.getSaveAsName());
	}

	@And("I see report template status Draft in template page")
	public void i_see_report_template_status_draft_template_page() {
		reportPage.iValidationdraft();
	}

	@Then("I verify run summary report report")
	public void i_verify_run_summary_report() throws IOException {
		this.report.checkEventTable(reportPage.getPdfUrl());
		this.report.checkFiledIds(reportPage.getPdfUrl(), report.getRecipes(), report.getReportName());
		this.report.checkPreRunColumnsInReport(reportPage.getPdfUrl());
		this.report.checkRecipeColumnsInReport(reportPage.getPdfUrl());
	}

	@When("I enter {string} as username and {string} password")
	public void i_Enter_Username_Password(String username, String password) {
		loginPage.setUser(username);
		loginPage.setPassword(password);
	}

}