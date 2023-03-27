package cucumber.steps;


import java.util.List;

import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

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

    @And("I create analytics aggregate {string} if not done before")
    public void iCreateAnAnalyticsAggregate(String aggregateName) {
        analytics.setName(aggregateName);
        iGotoAnalytics();
        makeAnalyticsParameter("PI101 PV", "psi", "x");
        makeAnalyticsParameter("PI102 PV", "psi", "y");
        makeAnalyticsParameter("PI103 PV", "psi", "y");

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
}
