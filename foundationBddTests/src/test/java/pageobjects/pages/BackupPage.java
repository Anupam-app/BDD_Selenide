package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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

    private final SpinnerComponent spinnerComponent = new SpinnerComponent();
    private final int backupTimeWait = 7 * 60 * 1000; // 7 minutes
    private String XPATH_NOTIFICATION_BACKUP_END = "//*[contains(@class,'custom-notification-bar')][contains(text(),'%s')]";
    private SelenideElement lastStatusText = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[5]"));
    private SelenideElement backupPageLinkText = $(By.id("BackupManagement"));
    private SelenideElement backupLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='Backup']"));
    private SelenideElement historyLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='History']"));
    private SelenideElement scheduleTextBox = $(By.className("schedule-text-box"));
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

    private void chooseBackupPath() {
        downArrow.click();
        List<WebElement> options = backupLocation.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals("C://bio4c/backup1/")) {
                option.click();
                break;
            }
        }
    }

    public String getLastStatusText() {
        return lastStatusText.getText();
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
        c.add(Calendar.MINUTE, 01);
        Date currentDatePlusOne = c.getTime();
        String d = dateFormat.format(currentDatePlusOne);
        timeInput.sendKeys(d);
        timeInput.sendKeys(Keys.ENTER);

        backupScheduleButton.click();
        scheduleTextBox.setValue(RandomStringUtils.randomAlphabetic(10));
        scheduleOkButton.click();
        //WAIT ONE MINUTE TO BE SURE THE BACKUP WILL BE LAUNCHED
        Selenide.sleep(60 * 1000);
    }

    public void goToHistory() {
        historyLinkText.click();
    }

    public void waitForEndOfBackup() {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, "Backup of Server complete")))
                .waitUntil(Condition.visible, backupTimeWait);
    }

    public void goToBackupMode() {
        backupLinkText.click();
    }

    public void waitForEndOfScheduledBackup() {
        int timeWaited = 0;
        int deltaTime = 30 * 1000;//every 30 seconds
        while (timeWaited < backupTimeWait) {
            goToBackupMode();
            goToHistory();
            if (getLastStatusText().equals(BackupStatus.Running.toString())) {
                timeWaited += deltaTime;
                Selenide.sleep(deltaTime);
            } else {
                break;
            }
        }
    }
}
