package cucumber.steps;

import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageobjects.pages.TrendsPage;
import pageobjects.utility.SelenideHelper;

public class TrendsPageStepsDefinition {

    private TrendsPage trendsPage;

    public TrendsPageStepsDefinition(TrendsPage trendsPage) {
        this.trendsPage = trendsPage;
    }

    @Given("I go to trends")
    public void iGotoAlarm() {
        trendsPage.goToTrends();
        SelenideHelper.goToIFrame();
    }

    @Then("I see expected texts from trend module")
    public void iSeeTextsFromTrendModule() {
        var expectedText = I18nUtils.getValueFromKey("trends.collection.starred");
        trendsPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @Then("I see expected texts from trend module parameters")
    public void iSeeTextsFromTrendModuleParameters() {
        var deviceShapeElementNotTranslated = trendsPage.getDeviceShapeElementNotLoaded();
        Assert.assertTrue("deviceShapeElementNotTranslated:" + deviceShapeElementNotTranslated.toString(),
                deviceShapeElementNotTranslated.isEmpty());
        SelenideHelper.goParentFrame();
    }
}
