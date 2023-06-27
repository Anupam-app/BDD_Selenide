package cucumber.steps;

import java.awt.AWTException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Setting;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.SettingPage;
import pageobjects.utility.SelenideHelper;

public class SettingsStepsDefinition {

    private final SettingPage settingPage;
    private final Setting setting;

    public SettingsStepsDefinition(SettingPage settingPage, Setting setting) {
        this.settingPage = settingPage;
        this.setting = setting;
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
        var expectedText = I18nUtils.getValueFromKey("configmgmt.systems.header.title");
        settingPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @And("below information is displayed")
    public void verifyGeneralTab(DataTable table) throws AWTException {
        List<String> list = table.asList(String.class);
        settingPage.zoomOut();
        for (int i = 0; i < list.size(); i++) {
            settingPage.verifyGeneralTab(list.get(i));
        }
    }

    @And("I provide random name to custom system name")
    public void iUpdateCustomSystemName() {
        this.setting.setCustomName(RandomStringUtils.randomAlphabetic(3));
        settingPage.updateSystemName(this.setting.getCustomName());
    }

    @Then("I see system name is updated in portal")
    public void iVerifyCustomSystemName() {
        SelenideHelper.goParentFrame();
        settingPage.iVerifyCustomSystemName(this.setting.getCustomName());
        settingPage.updateSystemName("IVI");
        iApplySetting();
    }

    @Given("I goto about components")
    public void iGotoAboutComponents(){
        settingPage.goToAboutComponent();
    }

    @Then("I verify softwareInformation details")
    public void iVerifySoftwareInformationDetails(){
        settingPage.softwareInformation();
    }

    @And("I verify Third-party Licence Information")
    public void iVerifyThirdPartyLicenceInformation(){
        settingPage.thirdPartyLicenceInformation();
    }

    @And("I verify End User Licence Agreement Information")
    public void iVerifyEndUserLicenceAgreementInformation(){
        settingPage.endUserLicenceInformation();
    }

    @And("I go to hamburger symbol")
    public void iGoToHumburgerSymbol(){
        settingPage.hamburger();
    }

    @Given("I goto service card")
    public void iGotoServiceCard() {
        settingPage.goToServiceCard();
    }

    @Given("I goto maintenance tab")
    public void iGotoMaintenanceTab() {
        settingPage.goToMaintenanceTab();
    }

    @Given("I reset the last maintenance date")
    public void iResetLastMaintenanceDate() {
        settingPage.resetLastMaintenanceDate();
    }

    @Given("I verify the Maintenance details updated")
    public void iVerifyMaintenanceDetails() {
        settingPage.verifyMaintenanceDetails();
    }

}
