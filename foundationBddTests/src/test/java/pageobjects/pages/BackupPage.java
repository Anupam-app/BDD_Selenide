package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pageobjects.utility.SelenideHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class BackupPage {

    private String XPATH_NOTIFICATION_BACKUP_END = "//*[contains(@class,'custom-notification-bar')][contains(text(),'%s')]";

    private SelenideElement lastStatusText = $(By.xpath("(//*[contains(@class,'history-card')])[1]/div[5]"));

    private SelenideElement backupPageLinkText = $(By.id("BackupManagement"));
    private SelenideElement backupLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='Backup']"));
    private SelenideElement historyLinkText = $(By.xpath("//*[contains(@class,'sub-menu')][text()='History']"));

    private SelenideElement downArrow = $(By.xpath("//span[@class='icon-down-arrow']"));
    private SelenideElement backupNowButton = $(By.xpath("//span[contains(text(),'Backup Now')]"));
    private SelenideElement confirmButton = $(By.xpath("//button[contains(text(),'Confirm')]"));

    private SelenideElement dailyBackup = $(By.xpath("(//*[@class='ant-radio-group ant-radio-group-outline backup-radio-group']//div/label/span/input)[1]"));
    private SelenideElement dateInput = $(By.xpath("//input[@placeholder='DD/MMM/YYYY']"));
    private SelenideElement timeInput = $(By.xpath("(//input[@placeholder='Select time'])[1]"));
    private SelenideElement backupLocation = $(By.xpath("//div[@class='backup-location']"));

    private SelenideElement selectDate = $(By.xpath("//div[@aria-disabled='false']"));

    public void goToBackupPage() {
        backupPageLinkText.click();
    }

    public void triggerBackup() {
        downArrow.click();
        List<WebElement> options = backupLocation.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals("C://bio4c/backup1/")) {
                option.click();
                break;
            }
        }
        backupNowButton.click();
        confirmButton.click();
    }

    public String getLastStatusText() {
        return lastStatusText.getText();
    }

    public void scheduleBackup() {
        downArrow.click();
        List<WebElement> options = backupLocation.findElements(By.tagName("li"));
        for (WebElement option : options) {
            if (option.getText().equals("C://bio4c/backup1/")) {
                option.click();
                break;
            }
        }

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
        c.add(Calendar.MINUTE, 02);
        Date currentDatePlusOne = c.getTime();
        String d = dateFormat.format(currentDatePlusOne);
        timeInput.sendKeys(d);
        timeInput.sendKeys(Keys.ENTER);
        backupNowButton.click();
        confirmButton.click();
    }

    public void goToHistory() {
        historyLinkText.click();
    }

    public void waitForEndOfBackup() {
        $(By.xpath(String.format(XPATH_NOTIFICATION_BACKUP_END, "Backup of Server complete")))
                .waitUntil(Condition.visible, 5 * 60 * 1000);
    }

    public void goToBackupMode() {
        backupLinkText.click();
    }
}
