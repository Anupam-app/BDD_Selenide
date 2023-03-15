package pageobjects.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import dataobjects.BackupStatus;
import pageobjects.components.SpinnerComponent;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.TimeHelper;

public class BackupPage {

    private final int BACKUP_FINISH_TIME_TO_WAIT = 7 * TimeHelper.ONE_MINUTE;
    private final int BACKUP_SCHEDULED_TIME_TO_WAIT = 4 * TimeHelper.ONE_MINUTE;
    private final int BACKUP_IMMEDIATE_TIME_TO_WAIT = TimeHelper.ONE_MINUTE;

    private final SpinnerComponent spinnerComponent = new SpinnerComponent();
    private final String scheduledBackupDetails = "//div[@class='scheduled-row']/div[%d]";
    private final String XPATH_NOTIFICATION_BACKUP_END =
            "//*[contains(@class,'custom-notification-bar')][contains(text(),'%s')]";
    private final SelenideElement lastStatusText = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[5]"));

    private final SelenideElement backupName = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[1]"));
    private final String historyColumnValue = "(//*[contains(@class,'history-card')])[1]/div[%d]";
    private final String historyColumnName = "//div[text()='%s']";
    private final String restoreColumnValue = "(//*[contains(@class,'restore-card')])[1]/div[%d]";
    private final String restoreColumnName = "//div[text()='%s']";


    private final SelenideElement backupNameOnRestorePage =
            $(By.xpath("(//*[contains(@class,'restore-card')])[1]/div[2]"));
    private final SelenideElement backupPageLinkText = $(By.id("BackupManagement"));

    private final String backupSubMenu = "//*[contains(@class,'sub-menu')][text()='%s']";
    private final SelenideElement restoreButton = $(By.xpath("//button[@class='btn-restore']"));
    private final SelenideElement successButton = $(By.xpath("//button[@class='rectangle-1']"));
    private final SelenideElement failureButton = $(By.xpath("//button[@class='rectangle-2']"));

    private final SelenideElement scheduleTextBox = $(By.xpath("//input[@class ='schedule-text-box']"));
    private final SelenideElement downArrow = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement backupNowButton = $(By.xpath("//span[contains(text(),'Backup Now')]"));
    private final SelenideElement backupScheduleButton = $(By.className("btn-schedule"));
    private final SelenideElement confirmButton = $(By.xpath("//button[contains(text(),'Confirm')]"));
    private final SelenideElement scheduleOkButton = $(By.className("btn-ok"));
    private final SelenideElement dailyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[1]"));

    private final SelenideElement weeklyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[2]"));

    private final SelenideElement monthlyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[3]"));
    private final SelenideElement dateInput = $(By.xpath("//input[@placeholder='DD/MMM/YYYY']"));
    private final SelenideElement dateInputMonthly = $(By.xpath("(//input[@placeholder='DD/MMM/YYYY'])[2]"));

    private final SelenideElement weekInput = $(By.xpath("//span[text()='Select Day']"));

    private final SelenideElement timeInput = $(By.xpath("(//input[@placeholder='Select time'])[1]"));
    private final SelenideElement timeInputMonthly = $(By.xpath("(//input[@placeholder='Select time'])[3]"));
    private final SelenideElement timeInputWeekly = $(By.xpath("(//input[@placeholder='Select time'])[2]"));
    private final SelenideElement backupLocation = $(By.xpath("//div[@class='backup-location']"));
    private final SelenideElement selectDate = $(By.xpath("//div[@aria-disabled='false']"));
    private final SelenideElement backupHeader = $(By.xpath("//div[text()='Backup and Restore']"));
    private final String selectDay = "//div[@class='day-dropdown']/following-sibling::ul/div/li[text()='%s']";
    private final SelenideElement trashIcon =
            $(By.xpath("//div[@class='scheduled-row']/div[5]/div[@class='trash-icon']"));

    private final SelenideElement datePicker = $(By.xpath("//input[@name='dateRange']"));

    public void goToBackupPage() {
        backupPageLinkText.click();
    }

    public void triggerBackup() {
        chooseBackupPath();
        backupNowButton.click();
        confirmButton.click();
        SelenideHelper.commonWaiter(spinnerComponent.spinnerIcon, visible);
    }

    public void iAmNotAbleTriggerBackup() {
        backupNowButton.shouldNotBe(visible);
        backupScheduleButton.shouldNotBe(visible);
    }

    public String getLastStatusText() {
        if (lastStatusText.isDisplayed()) {
            return lastStatusText.getText();
        }
        return "";
    }

    public void backupHistoryTab() {
        datePicker.shouldBe(visible);
        $(By.xpath(String.format(historyColumnName, "Backup Name"))).shouldBe(visible);
        $(By.xpath(String.format(historyColumnName, "Backed up by"))).shouldBe(visible);
        $(By.xpath(String.format(historyColumnName, "Backup/Restore"))).shouldBe(visible);
        $(By.xpath(String.format(historyColumnName, "Status"))).shouldBe(visible);
        for (int i = 1; i < 6; i++) {
            Assert.assertNotNull($(By.xpath(String.format(historyColumnValue, i))).getText());
        }
        Assert.assertEquals("User is not from the list", "Bio4CAdmin",
                $(By.xpath(String.format(historyColumnValue, 3))).getText());

        String[] backupDate = $(By.xpath(String.format(historyColumnValue, 4))).getText()
                .split(": ");
        Assert.assertTrue(
                backupDate[1].matches(("([0-9]{2})/([aA-zZ]{3})/([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2})")));
        String status = $(By.xpath(String.format(historyColumnValue, 5))).getText();
        Assert.assertTrue("Backup Status is not correct", (status.equalsIgnoreCase("Running")
                || status.equalsIgnoreCase("success") || status.equalsIgnoreCase("Aborted")));
    }

    public void backupRestoreTab() {
        datePicker.shouldBe(visible);
        restoreButton.waitUntil(visible, 2000);
        failureButton.waitUntil(visible, 2000);
        successButton.waitUntil(visible, 2000);
        $(By.xpath(String.format(restoreColumnName, "Backup Name"))).shouldBe(visible);
        $(By.xpath(String.format(restoreColumnName, "Backed up by"))).shouldBe(visible);
        $(By.xpath(String.format(restoreColumnName, "Size"))).shouldBe(visible);
        $(By.xpath(String.format(restoreColumnName, "Backup Date"))).shouldBe(visible);
        for (int i = 2; i <= 6; i++) {
            Assert.assertNotNull($(By.xpath(String.format(restoreColumnValue, i))).getText());
        }
    }

    public String getBackUpName() {
        if (backupName.isDisplayed()) {
            return backupName.getText();
        }
        return "";
    }

    public String getBackUpNameOnRestorePage() {
        if (backupNameOnRestorePage.isDisplayed()) {
            return backupNameOnRestorePage.getText();
        }
        return "";
    }

    private void chooseBackupPath() {
        downArrow.click();
        List<WebElement> options = backupLocation.findElements(By.tagName("li"));
        options.stream()
                .findFirst()
                .get()
                .click();
    }

    public void scheduleBackup(String name, String occurrence) {
        chooseBackupPath();
        switch (occurrence) {
            case "Daily":
                dailyBackup.click();
                dateInput.click();
                selectDate.click();
                SelenideHelper.commonWaiter(timeInput, Condition.visible)
                        .click();
                timeInput.sendKeys(Keys.LEFT_CONTROL + "a");
                timeInput.sendKeys(Keys.BACK_SPACE);
                SelenideHelper.commonWaiter(timeInput, Condition.visible)
                        .click();
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
                break;
            case "Monthly":
                monthlyBackup.click();
                dateInputMonthly.click();
                selectDate.click();
                SelenideHelper.commonWaiter(timeInputMonthly, Condition.visible)
                        .click();
                timeInputMonthly.sendKeys(Keys.LEFT_CONTROL + "a");
                timeInputMonthly.sendKeys(Keys.BACK_SPACE);
                SelenideHelper.commonWaiter(timeInputMonthly, Condition.visible)
                        .click();
                SimpleDateFormat dateFormatMonthly = new SimpleDateFormat("HH:mm");
                Date date2 = new Date();
                Calendar c2 = Calendar.getInstance();
                c2.setTime(date2);
                int minuteToWaitMonthly = 1;
                c2.add(Calendar.MINUTE, minuteToWaitMonthly);
                Date currentDatePlusOneMonthly = c2.getTime();
                String dateMonthly = dateFormatMonthly.format(currentDatePlusOneMonthly);
                timeInputMonthly.sendKeys(dateMonthly);
                timeInputMonthly.sendKeys(Keys.ENTER);
                break;
            case "Weekly":
                weeklyBackup.click();
                weekInput.click();
                DayOfWeek dayOfWeek = LocalDate.now()
                        .getDayOfWeek();
                String day = dayOfWeek.toString();
                day = day.charAt(0) + day.substring(1)
                        .toLowerCase();
                System.out.println(day);
                $(By.xpath(String.format(selectDay, day))).click();
                SelenideHelper.commonWaiter(timeInputWeekly, Condition.visible)
                        .click();
                timeInputWeekly.sendKeys(Keys.LEFT_CONTROL + "a");
                timeInputWeekly.sendKeys(Keys.BACK_SPACE);
                SelenideHelper.commonWaiter(timeInputWeekly, Condition.visible)
                        .click();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
                Date date1 = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                int minuteToWaitForWeekly = 1;
                cal.add(Calendar.MINUTE, minuteToWaitForWeekly);
                Date currentDatePlusOneWeekly = cal.getTime();
                String dat = dateFormat1.format(currentDatePlusOneWeekly);
                timeInputWeekly.sendKeys(dat);
                timeInputWeekly.sendKeys(Keys.ENTER);
                break;
            default:
        }
        backupScheduleButton.click();
        SelenideHelper.commonWaiter(scheduleTextBox, visible);
        scheduleTextBox.setValue(name);
        scheduleOkButton.click();
    }

    public void goToHistory() {
        $(By.xpath(String.format(backupSubMenu, "History"))).click();
    }

    public void goToRestore() {
        $(By.xpath(String.format(backupSubMenu, "Restore"))).click();
    }

    public void waitForEndOfBackup() {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, "Backup of Server complete")))
                .waitUntil(Condition.visible, BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void goToBackupMode() {
        $(By.xpath(String.format(backupSubMenu, "Backup"))).click();
    }

    private void waitForScheduledBackupState(List<BackupStatus> status, int timeToWait) {
        int timeWaited = 0;
        int deltaTime = 30 * 1000;// every 30 seconds
        while (timeWaited < timeToWait) {
            goToBackupMode();
            goToHistory();
            if (!getLastStatusText().equals("") && status.contains(BackupStatus.valueOf(getLastStatusText()))) {
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

    public void verifyScheduledBackupDetails(String backupName, String occurrence) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        Calendar cal = Calendar.getInstance();
        String startTime = (dateFormat.format(cal.getTime())).toString();
        Selenide.sleep(4000);
        Assert.assertEquals("backup name is not correct",
                ($(By.xpath(String.format(scheduledBackupDetails, 1)))).getText(), backupName);
        Assert.assertEquals("repeat is not as expected",
                ($(By.xpath(String.format(scheduledBackupDetails, 3)))).getText(), occurrence);
        Assert.assertTrue("Start Date / Time is not correct",
                (($(By.xpath(String.format(scheduledBackupDetails, 4)))).getText()).contains(startTime));
        trashIcon.shouldBe(visible);
    }

    public void waitForScheduledBackupFinished() {
        waitForScheduledBackupState(List.of(BackupStatus.Success, BackupStatus.Aborted), BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void waitForImmediateBackupRunning() {
        waitForScheduledBackupState(List.of(BackupStatus.Running), BACKUP_IMMEDIATE_TIME_TO_WAIT);
    }

    public void notificationMessage(String message) {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, message))).waitUntil(Condition.visible,
                BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void verifyBackupHeader() {
        backupHeader.shouldBe(visible);
    }
}
