package cucumber.steps;

import static com.codeborne.selenide.Selenide.switchTo;

import dataobjects.Login;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import dataobjects.Recipe;
import dataobjects.Report;
import dataobjects.ReportTemplate;
import dataobjects.ReportTemplateStatus;
import dataobjects.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.LoginPage;
import pageobjects.pages.ReportsPage;
import pageobjects.utility.SelenideHelper;

public class ReportsPageStepsDefinition {

    private final Report report;
    private final ReportsPage reportPage;
    private final ReportTemplate reportTemplate;
    private final User user;
    private final Recipe recipe;
    private final LoginPage loginPage;
    private final Login login;

    public ReportsPageStepsDefinition(LoginPage loginPage, ReportsPage reportPage, Report report, ReportTemplate reportTemplate, User user,
                                      Recipe recipe, Login login) {
        this.reportPage = reportPage;
        this.reportTemplate = reportTemplate;
        this.user = user;
        this.report = report;
        this.recipe = recipe;
        this.loginPage = loginPage;
        this.login = login;
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
    public void verifyColumn(String tab,DataTable table) {
    	List<List<String>> list = table.asLists(String.class);
        for (int i=1; i<list.size(); i++) {
        reportPage.verifyColoumn(list.get(i).get(0),tab, i); }
    }

    @Given("I select report from dropdown {string}")
    public void iSelectReportFromDropdown(String report) {
        this.reportTemplate.setName(report);
        reportPage.selectReport(report);
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
        this.report.setReportName(template);

    }
	
	@Then("I check audit trial report content")
    public void iCheckAuditTrialReportContent() throws Exception {

        this.report.checkAuditTable(reportPage.getPdfUrl());
        this.report.checkUserInformation(reportPage.getPdfUrl(),this.user.getName());
        this.report.checkEventTimeInformation(reportPage.getPdfUrl());

    }

    @When("I dont see the presence of generate button")
    public void iDontSeeGenerateButton() {
        reportPage.verifyGenerateButton();
    }
    
    @Then("I click on generate button")
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
        reportPage.checkSigned(this.report.getName(), this.login.getLogin());
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

        this.report.checkUserInformation(reportPage.getPdfUrl(),this.user.getName());
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
        this.report.checkUserIsDisabled(reportPage.getPdfUrl(),userName,this.login.getLogin());
        switchTo().parentFrame();
    }
    
    @Then("I see the {string} user enabled in report")
    public void iVerifyThatUserIsEnabled(String user) throws Exception {
        this.report.checkUserIsEnabled(reportPage.getPdfUrl(),user);
        switchTo().parentFrame();
    }

    @When("I search report {string}")
    public void iSearchReports(String report) {
        this.report.setName(report);
        reportPage.searchReportOrTemplate(report);
    }

    @Then("I esign the report")
    public void iEsignReports() {
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
   
    @When("I choose {string} trends {string}")
	public void iSelectTrendsParameters(String noOfParams, String parameters) throws Exception {
    	reportPage.selectParameters(noOfParams,parameters);
	}
    
    @And ("I create five trends chart")
    public void iCreate5TrendsCharts() {
        reportPage.create5Trends();
    }
    
    @And ("I save trends")
    public void iCreateTrendsCharts() {
        reportPage.createTrends();
    }
    
    @Then ("I verify that sixth chart is not allowed")
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
    
    @Given("I select the report template")
    public void i_select_report_template() throws InterruptedException {
    	reportPage.openReportTemplate(this.reportTemplate.getName());
       //Assert.assertEquals(this.reportTemplate.getStatus(), this.reportPage.getStatus());
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
    public void i_verify_run_summary_report() throws Exception {    	
    	this.report.checkEventTable(reportPage.getPdfUrl());    	
        this.report.checkFiledIds(reportPage.getPdfUrl(),this.recipe.getMachineName(),this.recipe.getBatchId(),this.recipe.getProductId(),this.recipe.getRecipeName(),
        		this.recipe.getBeforeComments(),this.recipe.getAfterComments(),
        		this.recipe.getStartDate(),this.recipe.getEndDate(),this.report.getReportName());
        this.report.checkPreRunColumnsInReport(reportPage.getPdfUrl());
        this.report.checkRecipeColumnsInReport(reportPage.getPdfUrl());
    }
    @When("I enter {string} as username and {string} password")
    public void i_Enter_Username_Password(String username1,String password1) {
    	loginPage.setUser(username1);
        loginPage.setPassword(password1);
    }
    

    
}