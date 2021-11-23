package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.pages.BackupPage;

public class BackupStepsDefinition {

	private BackupPage backupPage;

	public BackupStepsDefinition(BackupPage backupPage) {
		this.backupPage = backupPage;
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

	@Then("I see backup is triggered")
	public void iSeeBackupRunning() {
		Assert.assertEquals("Running",this.backupPage.getLastStatusText());
	}

	@Then("I verify backup history details")
	public void iVerifyBackup() {
		Assert.assertEquals("Success",this.backupPage.getLastStatusText());
	}

	@Then("I wait the end of backup")
	public void iWaitTheEndOfBackup() {
		this.backupPage.waitForEndOfBackup();
	}

	@When("I schedule backup")
	public void iScheduleBackup() {
		backupPage.scheduleBackup();
	}

}
