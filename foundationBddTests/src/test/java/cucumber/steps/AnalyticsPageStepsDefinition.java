package cucumber.steps;

import dataobjects.Analytics;
import dataobjects.AnalyticsInterval;
import dataobjects.AnalyticsMode;
import dataobjects.Recipe;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import pageobjects.pages.AnalyticsPage;

public class AnalyticsPageStepsDefinition {

    private AnalyticsPage analyticsPage;
    private Analytics analytics;
    private Recipe recipe;

    public AnalyticsPageStepsDefinition(AnalyticsPage analyticsPage, Analytics analytics, Recipe recipe) {
        this.analyticsPage = analyticsPage;
        this.analytics = analytics;
        this.recipe = recipe;
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
        String xparameterName = analytics.getXParameters();
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
        analytics.getYParameters().forEach(parameter -> analyticsPage.selectYAxisParameter(parameter));
    }

    @When("I apply the analytics settings")
    public void iApplyTheAnalyticsSettings() {
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
        createAnalytics(false);
    }

    @And("I create an analytics aggregate using button")
    public void iCreateAnAnalyticsAggregateWithButton() {
        createAnalytics(true);
    }

    private void createAnalytics(boolean usingButton) {
        if (StringUtils.isEmpty(analytics.getName())) {
            analytics.setName(RandomStringUtils.randomAlphabetic(10));
        }
        analyticsPage.startAggregateCreation(analytics.getName(), usingButton);
    }

    @And("I choose {string} analytics parameter as {string} axis")
    public void iChooseAnalyticsParameter(String param, String axis) {
        if (axis.equalsIgnoreCase("x")) {
            analytics.setXParameters(param);
        }
        if (axis.equalsIgnoreCase("y")) {
            analytics.getYParameters().add(param);
        }
        analyticsPage.chooseParameter(param);
    }

    @And("I validate the analytics creation")
    public void iValidateTheAnalyticsCreation() {
        analyticsPage.validateCreation();
    }

    @And("I see my changes in analytics aggregate")
    public void iSeeMyChangesInAnalyticsAggregate() {
        analyticsPage.clickCheckBox(analytics.getName());
        analytics.getYParameters().forEach(p -> analyticsPage.checkParamater(p));
        analyticsPage.checkParamater(analytics.getXParameters());
    }

    @And("I use the recipe for this analytics aggregate with interval {string}")
    public void iUseTheRecipeForThisAnalyticsAggregate(String interval) {
        analyticsPage.createAggregate(recipe, interval);
    }


    @And("I create analytics aggregate {string}")
    public void iCreateAnAnalyticsAggregate(String aggregateName) {
        iGotoAnalytics();
        analytics.setName(aggregateName);
        analyticsPage.deleteIfExists(analytics.getName());
        iCreateAnAnalyticsAggregate();
        iUseTheRecipeForThisAnalyticsAggregate(AnalyticsInterval.WEEKLY);
        iChooseAnalyticsParameter("PI101 PV", "x");
        iChooseAnalyticsParameter("PI102 PV", "y");
        iChooseAnalyticsParameter("PI103 PV", "y");
        iValidateTheAnalyticsCreation();
    }
}