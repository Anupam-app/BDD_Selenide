package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pageobjects.utility.TimeHelper;
import dataobjects.BackupStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pageobjects.components.SpinnerComponent;
import pageobjects.utility.SelenideHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BackupPage {

	private final int BACKUP_FINISH_TIME_TO_WAIT = 7 * TimeHelper.ONE_MINUTE;
	private final int BACKUP_SCHEDULED_TIME_TO_WAIT = 4 * TimeHelper.ONE_MINUTE;
	private final int BACKUP_IMMEDIATE_TIME_TO_WAIT = TimeHelper.ONE_MINUTE;

	private final SpinnerComponent spinnerComponent = new SpinnerComponent();
	private String XPATH_NOTIFICATION_BACKUP_END = "//*[contains(@class,'custom-notification-bar')][contains(text(),'%s')]";
	private SelenideElement lastStatusText = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[5]"));
	private SelenideElement backupPageLinkText = $(By.id("BackupManagement"));
	private SelenideElement backupLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='Backup']"));
	private SelenideElement historyLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='History']"));
	private SelenideElement scheduleTextBox = $(By.xpath("//input[@class ='schedule-text-box']"));
	private SelenideElement downArrow = $(By.xpath("//span[@class='icon-down-arrow']"));
	private SelenideElement backupNowButton = $(By.xpath("//span[contains(text(),'Backup Now')]"));
	private SelenideElement backupScheduleButton = $(By.className("btn-schedule"));
	private SelenideElement confirmButton = $(By.xpath("//button[contains(text(),'Confirm')]"));
	private SelenideElement scheduleOkButton = $(By.className("btn-ok"));
	private SelenideElement dailyBackup = $(By.xpath("(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[1]"));
	private SelenideElement dateInput = $(By.xpath("//input[@placeholder='DD/MMM/YYYY']"));
	private SelenideElement timeInput = $(By.xpath("(//input[@placeholder='Select time'])[1]"));
	private SelenideElement backupLocation = $(By.xpath("//div[@class='backup-location']"));
	private SelenideElement selectDate = $(By.xpath("//div[@aria-disabled='false']"));




	public void goToBackupPage() {
		backupPageLinkText.click();
	}

	public void triggerBackup() {
		chooseBackupPath();
		backupNowButton.click();
		confirmButton.click();
		SelenideHelper.commonWaiter(spinnerComponent.spinnerIcon, visible);
	}

    public String getLastStatusText() {
		SelenideHelper.commonWaiter(lastStatusText, visible);
        return lastStatusText.getText();
    }

	private void chooseBackupPath() {
		downArrow.click();
		List<WebElement> options = backupLocation.findElements(By.tagName("li"));
		options.stream().findFirst().get().click();
	}

	public void scheduleBackup() {
		chooseBackupPath();

		dailyBackup.click();
		dateInput.click();
		selectDate.click();
		SelenideHelper.commonWaiter(timeInput, Condition.visible).click();
		timeInput.sendKeys(Keys.LEFT_CONTROL + "a");
		timeInput.sendKeys(Keys.BACK_SPACE);
		SelenideHelper.commonWaiter(timeInput, Condition.visible).click();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int minuteToWait = 1;
		c.add(Calendar.MINUTE, minuteToWait);
		Date currentDatePlusOne = c.getTime();
		String d = dateFormat.format(currentDatePlusOne);
		timeInput.sendKeys(d);
		timeInput.sendKeys(Keys.ENTER);

		backupScheduleButton.click();
		SelenideHelper.commonWaiter(scheduleTextBox, visible);
		scheduleTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
		scheduleOkButton.click();
	}

	public void goToHistory() {
		historyLinkText.click();
	}

	public void waitForEndOfBackup() {
		$(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, "Backup of Server complete")))
		.waitUntil(Condition.visible, BACKUP_FINISH_TIME_TO_WAIT);
	}

	public void goToBackupMode() {
		backupLinkText.click();
	}

	private void waitForScheduledBackupState(List<BackupStatus> status, int timeToWait) {
		int timeWaited = 0;
		int deltaTime = 30 * 1000;//every 30 seconds
		while (timeWaited < timeToWait) {
			goToBackupMode();
			goToHistory();
			if (status.contains(BackupStatus.valueOf(getLastStatusText()))) {
				break;
			} else {
				timeWaited += deltaTime;
				Selenide.sleep(deltaTime);
			}
		}
	}

	public void waitForScheduledBackupRunning() {
		waitForScheduledBackupState(List.of(BackupStatus.Running), BACKUP_SCHEDULED_TIME_TO_WAIT);
	}

	public void waitForScheduledBackupFinished() {
		waitForScheduledBackupState(List.of(BackupStatus.Success, BackupStatus.Aborted), BACKUP_FINISH_TIME_TO_WAIT);
	}

	public void waitForImmediateBackupRunning() {
		waitForScheduledBackupState(List.of(BackupStatus.Running), BACKUP_IMMEDIATE_TIME_TO_WAIT);
	}

	public void scheduleBackup(String name) {
		chooseBackupPath();

		dailyBackup.click();
		dateInput.click();
		selectDate.click();
		SelenideHelper.commonWaiter(timeInput, Condition.visible).click();
		timeInput.sendKeys(Keys.LEFT_CONTROL + "a");
		timeInput.sendKeys(Keys.BACK_SPACE);
		SelenideHelper.commonWaiter(timeInput, Condition.visible).click();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int minuteToWait = 1;
		c.add(Calendar.MINUTE, minuteToWait);
		Date currentDatePlusOne = c.getTime();
		String d = dateFormat.format(currentDatePlusOne);
		timeInput.sendKeys(d);
		timeInput.sendKeys(Keys.ENTER);

		backupScheduleButton.click();
		SelenideHelper.commonWaiter(scheduleTextBox, visible);
		scheduleTextBox.setValue(name);
		scheduleOkButton.click();
	}

	public void notificationMessage(String message) {
		$(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, message))).
		waitUntil(Condition.visible, BACKUP_FINISH_TIME_TO_WAIT);

	}
	
}
