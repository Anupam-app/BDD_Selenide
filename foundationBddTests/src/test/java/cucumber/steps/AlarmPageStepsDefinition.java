package cucumber.steps;

import io.cucumber.java.en.Given;
import pageobjects.pages.AlarmPage;

public class AlarmPageStepsDefinition {
    private final AlarmPage alarmPage;

    public AlarmPageStepsDefinition(AlarmPage alarmPage) {
        this.alarmPage = alarmPage;
    }

    @Given("I navigate to alarms page")
    public void iNavigateToAlarmsPage() {
        alarmPage.goToAlarms();
        alarmPage.switchToFrame();
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
