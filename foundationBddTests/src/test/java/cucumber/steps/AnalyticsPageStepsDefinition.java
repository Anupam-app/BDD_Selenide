package cucumber.steps;

import com.codeborne.selenide.Selenide;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Analytics;
import dataobjects.AnalyticsInterval;
import dataobjects.AnalyticsMode;
import dataobjects.AnalyticsParameter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.AnalyticsPage;
import pageobjects.utility.SelenideHelper;

public class AnalyticsPageStepsDefinition {

    private final AnalyticsPage analyticsPage;
    private final Analytics analytics;

    public AnalyticsPageStepsDefinition(AnalyticsPage analyticsPage, Analytics analytics) {
        this.analyticsPage = analyticsPage;
        this.analytics = analytics;
    }

    @Given("I go to analytics")
    public void iGotoAnalytics() {
        analyticsPage.goToAnalytics();
        analyticsPage.switchToFrame();
    }

    @Given("I select the aggregate")
    public void iSelectAggregate() {
        analyticsPage.selectAggregate(analytics.getName());
    }

    @When("I select {string} graph in x axis")
    public void iselectGraph(String graphName) {
        String xparameterName = analytics.getXParameters()
                .getName();
        switch (graphName) {
            case "line":
                analytics.setAnalyticsMode(AnalyticsMode.RELATIONAL);
                analyticsPage.lineGraph(xparameterName);
                break;
            case "scatter":
                analytics.setAnalyticsMode(AnalyticsMode.RELATIONAL);
                analyticsPage.scatterGraph(xparameterName);
                break;
            case "logarithmic":
                analytics.setAnalyticsMode(AnalyticsMode.RELATIONAL);
                analyticsPage.logarithmicGraph(xparameterName);
                break;
            case "OLS":
                analytics.setAnalyticsMode(AnalyticsMode.REGRESSION);
                analyticsPage.olsGraph(xparameterName);
                break;
            case "LOESS":
                analytics.setAnalyticsMode(AnalyticsMode.REGRESSION);
                analyticsPage.loessGraph(xparameterName);
                break;
            default:
                System.out.println("Page not found");
        }
    }

    @When("I select the y axis")
    public void parametersInYAxis() {
        analytics.getYParameters()
                .forEach(parameter -> analyticsPage.selectYAxisParameter(parameter.getName()));
    }

    @When("I apply the analytics settings")
    public void iApplyTheAnalyticsSettings() throws InterruptedException {
        // wait before apply, if it is too fast to apply is not done
        Selenide.sleep(1000L);
        switch (analytics.getAnalyticsMode()) {
            case REGRESSION:
                analyticsPage.regressionApplySettings();
                break;
            case RELATIONAL:
                analyticsPage.relationalApplySettings();
                break;
        }
    }

    @Then("Values for parameter should be displayed in Data section of aggregate")
    public void verifyParametersDataSection() {
        analyticsPage.verifyParameters();
    }

    @Then("Graph should be plotted")
    public void graphIsPlotted() {
        analyticsPage.verifyGraph();
    }

    @And("I create an analytics aggregate")
    public void iCreateAnAnalyticsAggregate() {
        iGotoAnalytics();
        createAnalytics();
    }

    private void createAnalytics() {
        if (StringUtils.isEmpty(analytics.getName())) {
            analytics.setName(RandomStringUtils.randomAlphabetic(10));
        }
        analyticsPage.startAggregateCreation(analytics.getName());
    }

    @And("I choose {string} analytics parameter with unit {string} as {string} axis")
    public void iChooseAnalyticsParameter(String parameter, String unit, String axis) {
        makeAnalyticsParameter(parameter, unit, axis);
        analyticsPage.chooseParameter(parameter);
    }

    private void makeAnalyticsParameter(String parameter, String unit, String axis) {
        var analyticsParameter = new AnalyticsParameter(parameter, unit);
        if (axis.equalsIgnoreCase("x")) {
            analytics.setXParameters(analyticsParameter);
        }
        if (axis.equalsIgnoreCase("y")) {
            analytics.getYParameters()
                    .add(analyticsParameter);
        }
    }

    @And("I validate the analytics creation")
    public void iValidateTheAnalyticsCreation() {
        analyticsPage.validateCreation();
    }

    @And("I see my changes in analytics aggregate")
    public void iSeeMyChangesInAnalyticsAggregate() {
        analyticsPage.selectAggregate(analytics.getName());
        analytics.getYParameters()
                .forEach(p -> analyticsPage.checkParameter(p.getName(), p.getUnit()));
        analyticsPage.checkParameter(analytics.getXParameters()
                .getName(),
                analytics.getXParameters()
                        .getUnit());
        analyticsPage.verifyTimestampColumn();
    }

    @And("I verify analytics status {string},BatchID, RunID, Timestamp")
    public void iSeeInProgressChangesInAnalyticsAggregate(String status) {
        analyticsPage.selectAggregate(analytics.getName());
        analytics.getYParameters()
                .forEach(p -> analyticsPage.checkParameter(p.getName(), p.getUnit()));
        analyticsPage.checkParameter(analytics.getXParameters()
                .getName(),
                analytics.getXParameters()
                        .getUnit());
        analyticsPage.verifyTimestampColumn();
        analyticsPage.verifyTimestamp();
        analyticsPage.verifyAggregateTab(analytics.getName());
        analyticsPage.verifyBatchIDAndRunIDAndStatus(analytics.getRecipes()
                .stream()
                .findFirst()
                .get(), status);
    }

    @And("I use the recipe for this analytics aggregate with interval {string}")
    public void iUseTheRecipeForThisAnalyticsAggregate(String interval) {
        analyticsPage.createAggregate(analytics.getRecipes()
                .stream()
                .findFirst()
                .get(), interval);
    }

    @And("I create analytics aggregate {string} with {string} if not done before")
    public void iCreateAnAnalyticsAggregate(String aggregateName, String analyticsParams) {
        analytics.setName(aggregateName);
        iGotoAnalytics();

        var config = ConfigFactory.parseResourcesAnySyntax(analyticsParams, ConfigParseOptions.defaults());
        var params = config.getConfigList("Params.list");

        for (var param : params) {
            makeAnalyticsParameter(param.getString("value"), param.getString("unit"), param.getString("axis"));
        }

        var recipeOptional = analytics.getRecipes()
                .stream()
                .findFirst();

        if (recipeOptional.isPresent() && StringUtils.isNotEmpty(recipeOptional.get()
                .getRecipeName())) {
            analyticsPage.deleteIfExists(analytics.getName());
            createAnalytics();
            iUseTheRecipeForThisAnalyticsAggregate(AnalyticsInterval.SECOND);
            analyticsPage.chooseParameter(analytics.getXParameters()
                    .getName());
            for (var yparam : analytics.getYParameters()) {
                analyticsPage.chooseParameter(yparam.getName());
            }
            iValidateTheAnalyticsCreation();
        }
    }

    @Then("I see expected texts from analytics module")
    public void iSeeExpectedTextsFromAnalyticsModule() {
        var expectedText = I18nUtils.getValueFromKey("analytics.label.aggregates");
        analyticsPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @Then("I see expected texts from analytics module parameters")
    public void iSeeExpectedTextsFromAnalyticsModuleParameters() {
        var deviceShapeElementNotTranslated = analyticsPage.getDeviceShapeElementNotLoaded();
        Assert.assertTrue("deviceShapeElementNotTranslated:" + deviceShapeElementNotTranslated.toString(),
                deviceShapeElementNotTranslated.isEmpty());
        SelenideHelper.goParentFrame();
    }

    @When("^I verify presence of below options in Left Panel$")
    public void iSeeTheAvailabilityOfOptions(DataTable table) {
        List<String> list = table.asList(String.class);
        for (String s : list) {
            analyticsPage.panelValidation(s);
        }
    }

    @Then("I verify the message in Right Panel {string}")
    public void iSeeMessageDisplayedOnRightPanel(String message) {
        analyticsPage.verifyMessageDisplayedOnRightPanel(message);
    }

    @Then("I choose aggregate {string}")
    public void iChooseAggregate(String aggregate) {
        analytics.setName(aggregate);
        analyticsPage.chooseAggregate(aggregate);
    }

    @Then("I verify default list of {string} in analytics")
    public void iVerifyDefaultListOfParameters(String parameters) {
        analyticsPage.defaultCollectionTagsValidation(parameters);
    }

    @And("I verify analytics icon is not visible")
    public void analyticsIconPresence() {
        analyticsPage.analyticsIconNotPresent();
    }

}
