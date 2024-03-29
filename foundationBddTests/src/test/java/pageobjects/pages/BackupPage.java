package pageobjects.pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

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
import pageobjects.utility.ContextHelper;
import pageobjects.utility.SelenideHelper;
import pageobjects.utility.TimeHelper;

public class BackupPage {

    public static final String BACKUP_DATE_FORMAT = "MMM d, yyyy, h:mm:ss a";

    private final int BACKUP_FINISH_TIME_TO_WAIT = 7 * TimeHelper.ONE_MINUTE;
    private final int BACKUP_SCHEDULED_TIME_TO_WAIT = 4 * TimeHelper.ONE_MINUTE;
    private final int BACKUP_IMMEDIATE_TIME_TO_WAIT = TimeHelper.ONE_MINUTE;

    private final SpinnerComponent spinnerComponent = new SpinnerComponent();
    private final String XPATH_NOTIFICATION_BACKUP_END =
            "//*[contains(@class,'notification-bar')][contains(text(),'%s')]";
    private final String XPATH_HEADER = "//div[@class='header-title']";
    private final String XPATH_ORCHESTRATOR_HEADER = "//div[contains(@class,'BackupRestore_header-title')]";

    private final SelenideElement lastStatusText = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[5]"));
    private final SelenideElement scheduleTextBox = $(By.xpath("//input[@class ='schedule-text-box']"));
    private final SelenideElement downArrow = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final SelenideElement backupNowButton = $(By.xpath("//span[contains(text(),'Backup Now')]"));
    private final SelenideElement backupScheduleButton = $(By.className("btn-schedule"));
    private final SelenideElement confirmButton = $(By.xpath("//button[contains(text(),'Confirm')]"));
    private final SelenideElement scheduleOkButton = $(By.className("btn-ok"));
    private final SelenideElement dailyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[1]"));
    private final SelenideElement dateInput = $(By.xpath("//input[@placeholder='DD/MMM/YYYY']"));
    private final SelenideElement timeInput = $(By.xpath("(//input[@placeholder='Select time'])[1]"));
    private final SelenideElement backupLocation = $(By.xpath("//div[@class='backup-location']"));
    private final SelenideElement selectDate = $(By.xpath("//div[@aria-disabled='false']"));
    private final SelenideElement confirmationModal = $(By.xpath("//div[@class='modal-msg-block']"));
    private final SelenideElement okButton = $(By.xpath("//button[@class='btn-ok']"));
    private final SelenideElement lastScheduledBackUpName =
            $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[1]"));

    private final SelenideElement backupHeader = $(By.xpath("//div[text()='Backup and Restore']"));
    private final String scheduledBackupDetails = "//div[@class='scheduled-row']/div[%d]";
    private final SelenideElement noData = $(By.xpath("//div[@class='no-data-based-on-search']"));

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
    private final SelenideElement successButton = $(By.xpath("//button[@class='rectangle-1 disabled_btn']"));
    private final SelenideElement failureButton = $(By.xpath("//button[@class='rectangle-2 disabled_btn']"));

    private final SelenideElement weeklyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[2]"));

    private final SelenideElement monthlyBackup = $(By.xpath(
            "(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[3]"));
    private final SelenideElement dateInputMonthly = $(By.xpath("(//input[@placeholder='DD/MMM/YYYY'])[2]"));

    private final SelenideElement weekInput = $(By.xpath("//span[text()='Select']"));

    private final SelenideElement timeInputMonthly = $(By.xpath("(//input[@placeholder='Select time'])[3]"));
    private final SelenideElement timeInputWeekly = $(By.xpath("(//input[@placeholder='Select time'])[2]"));
    private final String selectDay = "//div[@class='day-dropdown']/following-sibling::ul/div/li[text()='%s']";
    private final String trashIcon =
            "//div[@class='scheduled-row']/div[text()='%s']//following-sibling::div/div[@class='trash-icon']";

    private final SelenideElement datePicker = $(By.xpath("//input[@name='dateRange']"));
    private final String backupTabs = "//div[contains(@class,'sub-menu')][contains(text(),'%s')]";

    public void goToBackupPage() {
        backupPageLinkText.click();
    }

    public void triggerBackup() {
        // chooseBackupPath(); //removed in IVI
        backupNowButton.click();
        confirmButton.click();
        commonWaiter(spinnerComponent.spinnerIcon, not(visible));
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

    public String getLastBackupName() {
        // SelenideHelper.commonWaiter(lastScheduledBackUpName, visible);
        return lastScheduledBackUpName.getText();
    }

    public void backupHistoryTab() {
        datePicker.shouldBe(visible);
        if (!(noData.isDisplayed())) {
            $(By.xpath(String.format(historyColumnName, "Backed up by"))).shouldBe(visible);
            $(By.xpath(String.format(historyColumnName, "Backup/Restore"))).shouldBe(visible);
            $(By.xpath(String.format(historyColumnName, "Status"))).shouldBe(visible);
            for (int i = 1; i < 6; i++) {
                Assert.assertNotNull($(By.xpath(String.format(historyColumnValue, i))).getText());
            }
            Assert.assertEquals("User is not from the list", "Bio4CAdmin",
                    $(By.xpath(String.format(historyColumnValue, 2))).getText());

            String backupDate = $(By.xpath(String.format(historyColumnValue, 4))).getText();
            Assert.assertTrue(SelenideHelper.dateFormatCheck(backupDate, BACKUP_DATE_FORMAT));

            String status = $(By.xpath(String.format(historyColumnValue, 5))).getText();
            Assert.assertTrue("Backup Status is not correct", (status.equalsIgnoreCase("Running")
                    || status.equalsIgnoreCase("success") || status.equalsIgnoreCase("Aborted")));
        }
    }

    public void backupRestoreTab() {
        datePicker.shouldBe(visible);
        restoreButton.waitUntil(visible, 2000);
        failureButton.waitUntil(visible, 2000);
        successButton.waitUntil(visible, 2000);
        if (!(noData.isDisplayed())) {
            $(By.xpath(String.format(restoreColumnName, "Backup Name"))).shouldBe(visible);
            $(By.xpath(String.format(restoreColumnName, "Backed up by"))).shouldBe(visible);
            $(By.xpath(String.format(restoreColumnName, "Size"))).shouldBe(visible);
            $(By.xpath(String.format(restoreColumnName, "Backup Date"))).shouldBe(visible);
            for (int i = 2; i < 6; i++) {
                Assert.assertNotNull($(By.xpath(String.format(restoreColumnValue, i))).getText());
            }
        }
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
        // chooseBackupPath();
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
                int minuteToWait = 3;
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
                int minuteToWaitMonthly = 3;
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
                int minuteToWaitForWeekly = 3;
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
        confirmationPopUpAccept();
    }

    public void goToHistory() {
        $(By.xpath(String.format(backupSubMenu, "History"))).click();
    }

    public void goToRestore() {
        $(By.xpath(String.format(backupSubMenu, "Restore"))).click();
    }

    public void waitForEndOfBackup() {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, "On-demand backup job executed")))
                .waitUntil(Condition.visible, BACKUP_IMMEDIATE_TIME_TO_WAIT);
    }

    public void goToBackupMode() {
        $(By.xpath(String.format(backupSubMenu, "Backup"))).click();
    }

    private void waitForScheduledBackupState(List<BackupStatus> status, int timeToWait) {
        if (status.get(0)
                .toString()
                .equalsIgnoreCase("Running")) {
            Selenide.sleep(180 * 1000);
        }
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

    private void waitForImmediateBackupState(List<BackupStatus> status, int timeToWait) {
        int timeWaited = 0;
        int deltaTime = 15 * 1000;// every 30 seconds
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

    public void seeContent(String expectedText) {
        if (ContextHelper.isOrchestrator()) {
            commonWaiter($(By.xpath(XPATH_ORCHESTRATOR_HEADER)), text(expectedText));
        } else {
            commonWaiter($(By.xpath(XPATH_HEADER)), text(expectedText));
        }
    }

    public void verifyScheduledBackupDetails(String backupName, String occurrence) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        Calendar cal = Calendar.getInstance();
        String startTime = (dateFormat.format(cal.getTime()));
        Selenide.sleep(4000);
        Assert.assertEquals("backup name is not correct",
                ($(By.xpath(String.format(scheduledBackupDetails, 1)))).getText(), backupName);
        Assert.assertEquals("repeat is not as expected",
                ($(By.xpath(String.format(scheduledBackupDetails, 3)))).getText(), occurrence);
        // this will be uncommented once bug BIOCRS-9281 is fixed
        // Assert.assertTrue("Start Date / Time is not correct",
        // (($(By.xpath(String.format(scheduledBackupDetails, 4)))).getText()).contains(startTime));
        $(By.xpath(String.format(trashIcon, backupName))).shouldBe(visible);
    }

    public void waitForImmediateBackupRunning() {
        waitForImmediateBackupState(List.of(BackupStatus.Running), BACKUP_IMMEDIATE_TIME_TO_WAIT);
    }

    public void waitForImmediateBackupSuccess() {
        waitForScheduledBackupState(List.of(BackupStatus.Success), BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void notificationMessage(String message) {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, message))).waitUntil(Condition.visible,
                BACKUP_FINISH_TIME_TO_WAIT, 500);
    }

    public void confirmationPopUpAccept() {
        SelenideHelper.commonWaiter(confirmationModal, visible);
        okButton.click();
    }

    public void waitForScheduledBackupFinished() {
        waitForScheduledBackupState(List.of(BackupStatus.Success, BackupStatus.Aborted), BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void verifyBackupHeader() {
        backupHeader.shouldBe(visible);
    }

    public void deleteBackUp(String backupName) {
        $(By.xpath(String.format(trashIcon, backupName))).waitUntil(visible, 2000)
                .click();
        confirmationPopUpAccept();
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, (backupName + " job is deactivated"))))
                .waitUntil(Condition.visible, BACKUP_FINISH_TIME_TO_WAIT);
    }

    public void verifyHistoryData() {
        if (!(noData.isDisplayed())) {
            for (int i = 1; i < 6; i++) {
                Assert.assertNotNull($(By.xpath(String.format(historyColumnValue, i))).getText());
            }
        } else {
            noData.shouldBe(visible);
        }
    }

    public void viewHistoryDetails(String tabs) {
        $(By.xpath(String.format(backupTabs, tabs))).shouldNotBe(visible);
    }

    public void backUpHistoryDetails(String shedulebackup) {
        $(By.xpath(String.format(restoreColumnName, shedulebackup))).shouldNotBe(visible);

    }

}
