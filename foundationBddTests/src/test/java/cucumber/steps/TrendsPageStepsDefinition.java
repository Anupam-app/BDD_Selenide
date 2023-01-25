package cucumber.steps;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import dataobjects.Trends;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.TrendsPage;

public class TrendsPageStepsDefinition {

    private final TrendsPage trendsPage;
    private final Trends trends;

    public TrendsPageStepsDefinition(TrendsPage trendsPage, Trends trends) {
        this.trendsPage = trendsPage;
        this.trends = trends;
    }

    @Given("I navigate to trends page")
    public void i_navigate_to_trends_page() {
        trendsPage.goToTrends();
        trendsPage.switchToFrame();
    }

    @Then("I see availability of Trends panel and chart area message {string}")
    public void i_see_availability_of_trends_panel_and_chart_area(String message) {
        trendsPage.chartAreaGraph(message);
    }

    @Then("footer section is not displayed.")
    public void footer_section_is_not_displayed() {
        trendsPage.noFooterAvailable();
    }

    @When("I collapse {string}")
    public void i_collapse_trends_area_panel(String name) {
        trendsPage.arrowCollapse(name);
    }

    @Then("I see the {string} collapsed")
    public void i_see_the_trends_area_panel_collapsed(String name) {
        trendsPage.trendsPanelValidation(name);
        trendsPage.arrowCollapseValidation(name);
    }

    @When("I expand {string}")
    public void i_expand_trends_area_panel(String name) {
        trendsPage.arrowExpand(name);
    }

    @Then("I see the {string} expanded")
    public void i_see_the_trends_area_panel_expanded(String name) {
        trendsPage.arrowExpandValidation(name);
    }

    @Then("^I see the availability of below$")
    public void i_see_the_availability_of_below_options(DataTable table) {
        List<String> list = table.asList(String.class);
        for (int i = 0; i < list.size(); i++) {
            trendsPage.trendsPanelValidation(list.get(i));
        }
    }

    @When("I choose {string},{string} parameters as default collection")
    public void i_choose_parameters_as_default_collection(String param1, String param2) throws Exception {
        trendsPage.selectMultipleCheckbox(param1, param2);
    }

    @When("^I see the availability of below footer$")
    public void i_see_the_availability_of_below_footer(DataTable table) {
        List<String> list = table.asList(String.class);
        for (int i = 0; i < list.size(); i++) {
            trendsPage.footerValidation(list.get(i));
        }
    }

    @When("I see the Live graph is display")
    public void i_see_the_live_option_displays_last_minutes_data() throws ParseException {
        trendsPage.validateGraph();
        trendsPage.graphTime();
    }

    @When("I save as trends collections")
    public void iSaveTrendsCollection() {
        this.trends.setCollectionName(RandomStringUtils.randomAlphabetic(5));
        trendsPage.collectionName(this.trends.getCollectionName());
    }

    @When("I save as trends collections as in step 4")
    public void iSaveCollectionName() {
        trendsPage.saveCollection(this.trends.getCollectionName());
    }

    @When("I choose collection")
    public void iChooseCollection() {
        trendsPage.chooseCollection(this.trends.getCollectionName());
    }

    @When("I see the graph is plotted for selected parameters in chart area{string},{string}")
    public void i_see_the_graph_is_plotted_for_selected_parameters_in_chart_area(String param1, String param2)
            throws ParseException {
        trendsPage.ledgerParameterOnChartArea(param1);
        trendsPage.ledgerParameterOnChartArea(param2);
        trendsPage.validateGraph();
        trendsPage.graphTime();
    }


    @When("I select the {string} collection")
    public void i_select_the_default_collection(String btn) {
        trendsPage.selectRadioButton(btn);
    }

    @When("I choose {string},{string} parameters")
    public void i_choose_parameters(String param1, String param2) throws Exception {
        trendsPage.selectMultipleCheckbox(param1, param2);
    }

    @Then("I verify default list of {string}")
    public void i_verify_default_list_of_parameters(String parameters) {
        trendsPage.defaultCollectionTagsValidation(parameters);

    }

    @When("I select star icons for {string},{string} parameters")
    public void i_select_star_icons_for_parameters(String param1, String param2) {
        trendsPage.iSelectStarIcon(param1);
        trendsPage.iSelectStarIcon(param2);

    }

    @When("I unselect the star icons for {string},{string} parameters")
    public void i_uselect_the_star_icons_for_parameters(String param1, String param2) throws InterruptedException {
        trendsPage.unSelectStarParam(param1, param2);
    }

    @Then("I validate no parameters are present in starred collection")
    public void i_validate_no_parameters_are_present_in_starred_collection() {
        trendsPage.noParametersStarred();
    }

    @Then("I delete the collection name")
    public void delete_collection_name() {
        trendsPage.deleteCollection(trends.getCollectionName());
    }

    @When("I see {string},{string} parameters displayed")
    public void iSeeParametersDisplayed(String param1, String param2) throws Exception {
        trendsPage.iSeeParametersDisplayed(this.trends.getCollectionName(), param1, param2);
    }

    @Then("I see the error message {string} on collection name window")
    public void iSeeErrorMessageDisplayedOnCollectionNameWindow(String message) {
        trendsPage.isGeneratedNotificationWhenCreateExistingCollection(message, trends.getCollectionName());
    }

    @Then("I go to list of collection")
    public void iGoToListOfCollection() {
        trendsPage.listOfCollection(trends.getCollectionName());
    }

    @When("I remove {string} and save collections")
    public void iRemoveParam1AndSaveCollection(String param1) {
        this.trends.setCollectionName(RandomStringUtils.randomAlphabetic(5));
        trendsPage.removeParam1AndSaveCollection(param1, this.trends.getCollectionName());
    }

    @When("I see {string} parameters displayed")
    public void iSeeParameterIsDisplayed(String param2) throws Exception {
        trendsPage.iSeeParameterDisplayed(this.trends.getCollectionName(), param2);
    }

    @When("I uncheck {string}")
    public void iUnCheckParam(String param1) throws Exception {
        trendsPage.unCheckParameter(this.trends.getCollectionName(), param1);
    }

}
