package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.AWTException;

import org.junit.Assert;
import pageobjects.pages.SettingPage;

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

    @When("I change custom label {string}")
    public void iChangeSettings(String customLabelName) throws AWTException {
        settingPage.changeSettings(customLabelName);
    }

    @Then("New {string} is applied")
    public void newSettingsApplied(String customLabelName) {
        Assert.assertEquals(customLabelName, settingPage.getCustomLabelNameText());
    }
}
