package cucumber.steps;

import cucumber.util.I18nUtils;
import dataobjects.BackupStatus;
import dataobjects.Backupsetting;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.BackupPage;
import pageobjects.utility.SelenideHelper;

public class BackupStepsDefinition {

    private BackupPage backupPage;

    private Backupsetting backupsetting;


    public BackupStepsDefinition(BackupPage backupPage, Backupsetting backupsetting) {
        this.backupPage = backupPage;
        this.backupsetting = backupsetting;
    }

    @Given("I goto backup page")
    public void iGotoBackupPage() {
        backupPage.goToBackupPage();
    }

    @When("I trigger a immediate backup")
    public void iTriggerBackup() {
        backupPage.triggerBackup();
    }

    @When("I go to backup history")
    public void iGoToHistory() {
        backupPage.goToHistory();
    }

    @When("I go to backup mode")
    public void iGoToBackup() {
        backupPage.goToBackupMode();
    }

    @Then("I see backup scheduled is triggered")
    public void iSeeBackupScheduledRunning() {
        backupPage.waitForScheduledBackupRunning();
        Assert.assertEquals(BackupStatus.Running.toString(), this.backupPage.getLastStatusText());
    }

    @Then("I see backup is triggered")
    public void iSeeBackupRunning() {
        backupPage.waitForImmediateBackupRunning();
        Assert.assertEquals(BackupStatus.Running.toString(), this.backupPage.getLastStatusText());
    }

    @Then("I verify backup history details")
    public void iVerifyBackup() {
        Assert.assertEquals(BackupStatus.Success.toString(), this.backupPage.getLastStatusText());
    }

    @Then("I wait the end of backup")
    public void iWaitTheEndOfBackup() {
        this.backupPage.waitForEndOfBackup();
    }

    @When("I schedule backup")
    public void iScheduleBackup() {
        backupsetting.setBackupName(RandomStringUtils.randomAlphabetic(10));
        backupPage.scheduleBackup(backupsetting.getBackupName());
    }

    @Then("I wait the end of scheduled backup")
    public void iWaitTheEndOfScheduledBackup() {
        //backupPage.waitForScheduledBackupFinished();
    	backupPage.waitForScheduledBackupFinished(backupsetting.getBackupName());
    }


    @Then("I see expected texts from backup module")
    public void iSeeExpectedTextsFromBackupModule() {
        var expectedText = I18nUtils.getValueFromKey("backupmanagement.main.header.title");
        backupPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @When("I schedule backup with existing name")
    public void iScheduleBackupWithExistingName() {
        backupPage.scheduleBackup(this.backupsetting.getBackupName());
    }

    @Then("I see the notification message {string}")
    public void iVerifyNotificationMessage(String message) {
        backupPage.notificationMessage(message);
    }
    @Then("I see the notification message")
    public void iSeeErrorMessagedisplayed() {
        var message = String.format("Failed to schedule backup. %s already exists", backupsetting.getBackupName());
        backupPage.notificationMessage(message);
    }
}
