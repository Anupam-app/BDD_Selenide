package cucumber.steps;

import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.pages.AlarmPage;
import pageobjects.pages.DeviceManagementPage;
import pageobjects.utility.SelenideHelper;

public class DeviceManagementStepsDefinition {

    private DeviceManagementPage deviceManagementPage;

    public DeviceManagementStepsDefinition(DeviceManagementPage deviceManagementPage) {
        this.deviceManagementPage = deviceManagementPage;
    }

    @Given("I go to device dashboard page")
    public void iGotoDeviceDashboard() {
        deviceManagementPage.goToDeviceDashboard();
        SelenideHelper.goToIFrame();
    }

    @Then("I see expected texts from device dashboard module")
    public void iSeeTextsFromAlarmModule() {
        var expectedText = I18nUtils.getValueFromKey("device.dashboardHeader");
        deviceManagementPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }
}
