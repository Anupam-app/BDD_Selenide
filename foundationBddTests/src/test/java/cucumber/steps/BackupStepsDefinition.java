package cucumber.steps;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import dataobjects.BackupStatus;
import dataobjects.Backupsetting;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.BackupPage;

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

    @When("I am not able to trigger a backup")
    public void iAmNotAbleTriggerBackup() {
        backupPage.iAmNotAbleTriggerBackup();
    }

    @When("I go to backup history")
    public void iGoToHistory() {
        backupPage.goToHistory();
    }

    @When("I go to backup restore")
    public void iGoToRestore() {
        backupPage.goToRestore();
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

    @Then("I verify backup in restore tab")
    public void iVerifyBackUpInRestoreTab() {
        backupPage.goToRestore();
        Assert.assertEquals(backupsetting.getBackupName(), this.backupPage.getBackUpNameOnRestorePage());
    }

    @Then("I see backup is triggered")
    public void iSeeBackupRunning() {
        backupPage.waitForImmediateBackupRunning();
        Assert.assertEquals(BackupStatus.Running.toString(), this.backupPage.getLastStatusText());
    }

    @Then("I verify backup history details")
    public void iVerifyBackup() {
        Assert.assertEquals(BackupStatus.Success.toString(), this.backupPage.getLastStatusText());
        this.backupsetting.setBackupName(backupPage.getBackUpName());
    }

    @Then("I verify backup history tab")
    public void iVerifyBackupHistoryTab() {
        backupPage.backupHistoryTab();
    }

    @Then("I verify backup restore tab")
    public void iVerifyBackupRestoreTab() {
        backupPage.backupRestoreTab();
    }

    @Then("I wait the end of backup")
    public void iWaitTheEndOfBackup() {
        this.backupPage.waitForEndOfBackup();
    }

    @When("I schedule backup {string}")
    public void iScheduleBackup(String occurrence) {
        backupsetting.setBackupName(RandomStringUtils.randomAlphabetic(10));
        backupPage.scheduleBackup(backupsetting.getBackupName(), occurrence);
    }

    @Then("I wait the end of scheduled backup")
    public void iWaitTheEndOfScheduledBackup() {
        backupPage.waitForScheduledBackupFinished();
    }

    @When("I schedule backup with existing name {string}")
    public void iScheduleBackupWithExistingName(String occurrence) {
        backupPage.scheduleBackup(this.backupsetting.getBackupName(), occurrence);
    }

    @Then("I see the notification message {string}")
    public void iVerifyNotificationMessage(String message) {
        backupPage.notificationMessage(message);
    }

    @When("I verify scheduled backup details for {string}")
    public void iVerifyScheduledBackupDetails(String occurrence) {
        backupPage.verifyScheduledBackupDetails(backupsetting.getBackupName(), occurrence);
    }
}
