package cucumber.steps;


import io.cucumber.datatable.DataTable;
import com.codeborne.selenide.Selenide;
>>>>>>> a337921ad0b881f05b61d8ca9272e87bffe98617
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
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

	@When("I click on filter icon and select report type {string}")
	public void iSelectReportType(String reportType) {
		reportPage.selectReportType(reportType);
	}

	@When("I search the recipe run {string}")
	public void isearchRunReport(String recipeRunName) {
		reportPage.searchReportOrTemplate(recipeRunName);
	}
	
	@When("I select template sort by {string} in {string}")
    public void iSelectSortTemplate(String columnName, String sortMode) {
		reportPage.sortListTemplate(columnName,Boolean.parseBoolean(sortMode));
    }
    
    @Then("{string} list should be sorted in {string} order")
    public void templatesShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
		reportPage.checkSortedElementTemplate(columnName,Boolean.parseBoolean(sortMode));
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
	public void i_filter_on_icon_and_select_run_status(String status1) {
		reportPage.selectrunStatus(status1);
	}

	@Then("I should see run status as {string}")
	public void i_should_see_run_status(String status) {
		Assert.assertTrue(reportPage.verifyRunStatus(status));
	}

	@Then("^I should see recipe run list displayed based on date range dropdown$")
	public void i_should_see_recipe_run_list_displayed_based_on_option(DataTable datatable)
			throws ParseException, InterruptedException {
		List<String> options = datatable.asList();
		for (String datarange : options) {
			reportPage.selectDateRange(datarange);
			//Assert.assertTrue(reportPage.verifyDateRanges(datarange));
		}
	}

	@Then("^Report columns should be sorted in ascending order$")
	public void report_columns_sorted_in_asc_order(DataTable datatable) {
		List<String> options = datatable.asList();
		for (String columnName : options) {
			reportPage.sortList(columnName, false);
			reportPage.checkSortedElement(columnName, false);
		}
	}

    @Then("I see Audit logs are displayed for date range and {string}")
    public void iVerifyAuditLogsUser(String userid) throws InterruptedException, ParseException {
        reportPage.checkTableContainsUserAndDateRange(userid);
    }
    

	@Then("^Report columns should be sorted in descending order$")
	public void report_columns_sorted_in_desc_order(DataTable datatable) {
		List<String> options = datatable.asList();
		for (String columnName : options) {
			reportPage.sortList(columnName, true);
			reportPage.checkSortedElement(columnName, true);
		}
	}
	
	@Then("^I should see recipe report list displayed based on date range dropdown$")
	public void iviewrecipereportlistdaterange(DataTable datatable)
		throws ParseException, InterruptedException {
			List<String> options = datatable.asList();
			for (String datarange : options) {
				reportPage.selectDateRangeRprt(datarange);
				reportPage.verifyDateRangesRprt(datarange);
			}
	}
	@Then("^Reports columns should be sorted in ascending order$")
	public void reports_columns_sorted_ascending_order(DataTable datatable) {
		List<String> options = datatable.asList();
		for (String columnName : options) {
			reportPage.sortList(columnName, false);
			reportPage.checkSortedElements(columnName, false);
		}
	}
	
	@Then("^Reports columns should be sorted in descending order$")
	public void reports_columns_sorted_descending_order(DataTable datatable) {
		List<String> options = datatable.asList();
		for (String columnName : options) {
			reportPage.sortList(columnName, true);
			reportPage.checkSortedElement(columnName, true);
		}
	}

	@Then("^I verify consolidated columns and columns should be sorted$")
	public void iVerifyConsilidatedColumnsSorted(DataTable datatable) {
		List<String> options = datatable.asList();
		for (String columnName : options) {
			reportPage.sortListConsolidated(columnName, true);			
			reportPage.checkSortedElementConsolidate(columnName, true);			
		}
	}
	@Then("I should see consolidated status as {string}")
	public void i_hould_see_consolidated_status(String status) {
		Assert.assertTrue(reportPage.verifyConsolidatedStatus(status));
	}
}
