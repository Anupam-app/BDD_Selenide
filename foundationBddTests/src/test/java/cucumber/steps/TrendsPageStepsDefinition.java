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
    public void iNavigateToTrendsPage() {
        trendsPage.goToTrends();
        trendsPage.switchToFrame();
    }

    @Then("I see availability of Trends panel and chart area message {string}")
    public void iSeeAvailabilityOfTrendsPanelAndChartArea(String message) {
        trendsPage.chartAreaGraph(message);
    }

    @Then("footer section is not displayed.")
    public void footerSectionIsNotDisplayed() {
        trendsPage.noFooterAvailable();
    }

    @When("I collapse {string}")
    public void iCollapseTrendsAreaPanel(String name) {
        trendsPage.arrowCollapse(name);
    }

    @Then("I see the {string} collapsed")
    public void iSeeTheTrendsAreaPanelCollapsed(String name) {
        trendsPage.trendsPanelValidation(name);
        trendsPage.arrowCollapseValidation(name);
    }

    @When("I expand {string}")
    public void iExpandTrendsAreaPanel(String name) {
        trendsPage.arrowExpand(name);
    }

    @Then("I see the {string} expanded")
    public void iSeeTrendsAreaPanelExpanded(String name) {
        trendsPage.arrowExpandValidation(name);
    }

    @Then("^I see the availability of below$")
    public void iSeeTheAvailabilityOfBelowOptions(DataTable table) {
        List<String> list = table.asList(String.class);
        for (int i = 0; i < list.size(); i++) {
            trendsPage.trendsPanelValidation(list.get(i));
        }
    }

    @When("I choose {string},{string} parameters as default collection")
    public void iChooseParametersAsDefaultCollection(String param1, String param2) {
        trendsPage.selectMultipleCheckbox(param1, param2);
    }

    @When("^I see the availability of below footer$")
    public void iSeeTheAvailabilityOfBelowFooter(DataTable table) {
        List<String> list = table.asList(String.class);
        for (int i = 0; i < list.size(); i++) {
            trendsPage.footerValidation(list.get(i));
        }
    }

    @When("I see the Live graph is display")
    public void iSeeTheLiveOptionDisplaysLastMinutesData() throws ParseException {
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

    @When("I see the graph is plotted for selected parameters in chart area {string},{string}")
    public void iSeeTheGraphIsPlottedForSelectedParametersInChartArea(String param1, String param2)
            throws ParseException {
        trendsPage.ledgerParameterOnChartArea(param1);
        trendsPage.ledgerParameterOnChartArea(param2);
        trendsPage.validateGraph();
        trendsPage.graphTime();
    }


    @When("I select the {string} collection")
    public void iSelectTheDefaultCollection(String btn) {
        trendsPage.selectRadioButton(btn);
    }

    @When("I choose {string},{string} parameters")
    public void iChooseParameters(String param1, String param2) {
        trendsPage.selectMultipleCheckbox(param1, param2);
    }

    @Then("I verify default list of {string}")
    public void iVerifyDefaultListOfParameters(String parameters) {
        trendsPage.defaultCollectionTagsValidation(parameters);

    }

    @When("I select star icons for {string},{string} parameters")
    public void iSelectStarIconsForParameters(String param1, String param2) {
        trendsPage.iSelectStarIcon(param1);
        trendsPage.iSelectStarIcon(param2);

    }

    @When("I unselect the star icons for {string},{string} parameters")
    public void iUnselectTheStarIconsForParameters(String param1, String param2) {
        trendsPage.unSelectStarParam(param1, param2);
    }

    @Then("I validate no parameters are present in starred collection")
    public void iValidateNoParametersArePresentInStarredCollection() {
        trendsPage.noParametersStarred();
    }

    @Then("I delete the collection name")
    public void deleteCollectionName() {
        trendsPage.deleteCollection(trends.getCollectionName());
    }

    @When("I see {string},{string} parameters displayed")
    public void iSeeParametersDisplayed(String param1, String param2) {
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
    public void iSeeParameterIsDisplayed(String param2) {
        trendsPage.iSeeParameterDisplayed(this.trends.getCollectionName(), param2);
    }

    @When("I uncheck {string}")
    public void iUnCheckParam(String param1) {
        trendsPage.unCheckParameter(this.trends.getCollectionName(), param1);
    }

}
