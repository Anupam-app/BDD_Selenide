package cucumber.steps;

import com.xceptance.neodymium.util.Neodymium;
import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.pages.SettingPage;
import pageobjects.utility.SelenideHelper;

public class SettingsStepsDefinition {

    private SettingPage settingPage;

    public SettingsStepsDefinition(SettingPage settingPage) {
        this.settingPage = settingPage;
    }

    @Given("I goto settings page")
    public void iGotoSettingsPage() {
        settingPage.goToSettingsPage();
    }

    @Given("I goto system components")
    public void iGotoSystemComponents() {
        settingPage.goToSystemComponents();
    }

    @Given("I goto general components")
    public void iGotoGeneralComponents() {
        settingPage.goToGeneralComponent();
    }

    @When("I change custom label {string}")
    public void iChangeSettings(String customLabelName) {
        settingPage.changeSettings(customLabelName);
    }

    @When("I change language to {string}")
    public void iChangeLanguage(String language) {
        I18nUtils.changeLanguage(language);
        String languageName = I18nUtils.getLanguageName();
        settingPage.changeLanguage(languageName);
    }

    @When("I apply settings")
    public void iApplySetting() {
        settingPage.applySettings();
    }

    @Then("New {string} is applied")
    public void newSettingsApplied(String customLabelName) {
        Assert.assertEquals(customLabelName, settingPage.getCustomLabelNameText());
    }

    @Then("I see the expected language activated")
    public void iSeeLanguageActivated() {
        String languageName = I18nUtils.getLanguageName();
        settingPage.seeLanguageActivated(languageName);
    }

    @Then("I see expected texts from setting module")
    public void iSeeExpectedTextsFromSettingModule() {
        var expectedText= I18nUtils.getValueFromKey("configmgmt.systems.header.title");
        settingPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }
}
