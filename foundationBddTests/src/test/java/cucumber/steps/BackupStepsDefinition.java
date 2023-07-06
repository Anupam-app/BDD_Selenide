package cucumber.steps;

import io.cucumber.java.en.And;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.BackupStatus;
import dataobjects.Backupsetting;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.BackupPage;
import pageobjects.utility.SelenideHelper;

public class BackupStepsDefinition {

    private final BackupPage backupPage;

    private final Backupsetting backupsetting;


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
        backupPage.waitForImmediateBackupSuccess();
        Assert.assertEquals(BackupStatus.Success.toString(), this.backupPage.getLastStatusText());
        SelenideHelper.goToDefault();
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
        backupPage.waitForScheduledBackupFinished(backupsetting.getBackupName());
    }


    @Then("I see expected texts from backup module")
    public void iSeeExpectedTextsFromBackupModule() {
        var expectedText = I18nUtils.getValueFromKey("backupmanagement.main.header.title");
        backupPage.seeContent(expectedText);
        SelenideHelper.goParentFrame();
    }

    @When("I schedule backup with existing name {string}")
    public void iScheduleBackupWithExistingName(String occurrence) {
        backupPage.scheduleBackup(this.backupsetting.getBackupName(), occurrence);
    }

    @Then("I see the notification message {string}")
    public void iVerifyNotificationMessage(String message) {
        backupPage.notificationMessage(message);
    }

    @Then("I see the notification message")
    public void iSeeErrorMessageDisplayed() {
        var message = String.format("Failed to schedule backup. %s already exists", backupsetting.getBackupName());
        backupPage.notificationMessage(message);
    }

    @When("I verify scheduled backup details for {string}")
    public void iVerifyScheduledBackupDetails(String occurrence) {
        backupPage.verifyScheduledBackupDetails(backupsetting.getBackupName(), occurrence);
    }

    @When("I delete the backup")
    public void iDeleteBackup() {
        backupPage.deleteBackUp(backupsetting.getBackupName());
    }

    @And("I verify backup {string} permission")
    public void verifyPermissionAccess(String permission) {
        switch (permission) {
            case "View Backup and Restore History":
                backupPage.viewHistoryDetails();
                backupPage.verifyHistoryData();
                break;
            case "Trigger on-demand backup":
                backupPage.backUpHistoryDetails();
                iTriggerBackup();
                iVerifyNotificationMessage("On-demand backup job executed");
                break;
            case "Schedule periodic backup":
                iScheduleBackup("Daily");
                iVerifyNotificationMessage("Backup job scheduled");
                iVerifyScheduledBackupDetails("Daily");
                break;
            default:
        }

    }

    @And("I wait until Backup is success for {string}")
    public void waitUntilBackupSuccess(String permission) {
        if (permission.equalsIgnoreCase("Schedule periodic backup")) {
            iGotoBackupPage();
            iGoToHistory();
            iSeeBackupScheduledRunning();
            iWaitTheEndOfScheduledBackup();
            iVerifyBackup();
            iGoToBackup();
            iDeleteBackup();
        } else if (permission.equalsIgnoreCase("Trigger on-demand backup")) {
            iGotoBackupPage();
            iGoToHistory();
            iSeeBackupScheduledRunning();
            iVerifyBackup();
        }
    }

}
