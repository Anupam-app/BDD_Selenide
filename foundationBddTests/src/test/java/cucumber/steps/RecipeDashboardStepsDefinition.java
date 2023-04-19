package cucumber.steps;

import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.pages.DeviceManagementPage;
import pageobjects.pages.RecipeDashboardPage;
import pageobjects.utility.SelenideHelper;

public class RecipeDashboardStepsDefinition {

    private RecipeDashboardPage recipeDashboardPage;

    public RecipeDashboardStepsDefinition(RecipeDashboardPage recipeDashboardPage) {
        this.recipeDashboardPage = recipeDashboardPage;
    }

    @Given("I go to recipe dashboard page")
    public void iGotoRecipeDashboard() {
        recipeDashboardPage.goToRecipeDashboard();
        SelenideHelper.goToIFrame();
    }

    @Then("I see expected texts from recipe dashboard module")
    public void iSeeTextsFromAlarmModule() {
        var expectedText = I18nUtils.getValueFromKey("recipe.dashboardHeader");
        recipeDashboardPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }
}
