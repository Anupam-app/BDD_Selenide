package cucumber.steps;

import cucumber.util.I18nUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.pages.AlarmPage;
import pageobjects.utility.SelenideHelper;

public class AlarmPageStepsDefinition {

    private AlarmPage alarmPage;

    public AlarmPageStepsDefinition(AlarmPage alarmPage) {
        this.alarmPage = alarmPage;
    }

    @Given("I go to alarm")
    public void iGotoAlarm() {
        alarmPage.goToAlarms();
        SelenideHelper.goToIFrame();
    }

    @Then("I see expected texts from alarm module")
    public void iSeeTextsFromAlarmModule() {
        var expectedText = I18nUtils.getValueFromKey("alarm.alarmList.emptyList.errorMessage");
        alarmPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @Given("I navigate to alarms page")
    public void iNavigateToAlarmsPage() {
        alarmPage.goToAlarms();
        SelenideHelper.goToIFrame();
    }

    @Given("I acknowledge the alarm")
    public void iAcknowledgeAlarm() {
        alarmPage.acknowledgeAlarm();
    }

    @Given("Alarm is acknowledged")
    public void iVerifyAcknowledgeAlarm() {
        alarmPage.verifyAcknowledgedAlarm();
    }
}

