package pageobjects.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static pageobjects.utility.SelenideHelper.goToIFrame;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class AlarmPage {
    private SelenideElement alarmHeader = $(By.xpath("//div[text()='Alarms']"));
    private SelenideElement alarms = $(By.xpath("//*[contains(@class,'AlarmManagement')]"));
    private SelenideElement acknowledgeAlarm = $(By.xpath("//span[@class='ack-button-text']"));
    private SelenideElement acknowledgeHeader = $(By.xpath("//div[@class='ack-commentModal-headline']"));
    private SelenideElement acknowledgeButton = $(By.xpath("//span[@class='modalAck-button-text']"));
    private SelenideElement alarmName =
            $(By.xpath("//table[@class='alarm-list-table']/tbody/tr[%d]/td[1]/div/div[text()='%s']"));

    private SelenideElement closeAlarm = $(By.xpath("//div[@class='btn-close-msg']"));
    private ElementsCollection alarmTable = $$(By.xpath("//table[@class='alarm-list-table']/tbody/tr"));
    private String alarmStatus = "//table[@class='alarm-list-table']/tbody/tr[%d]/td[5]//span[text()='Acknowledge']";
    // private String acknowButton = "//table[@class='alarm-list-table']/tbody/tr[%d]/td[5]//button";
    private String acknowledgedBy = "//table[@class='alarm-list-table']/tbody/tr[%d]/td[5]/div/div[3]";

    public void checkAlarmHeader() {
        alarmHeader.shouldBe(visible);
    }

    public void goToAlarms() {
        commonWaiter(alarms, visible).click();
        commonWaiter(alarms, visible);
    }

    public void acknowledgeAlarm() throws InterruptedException {
        // Selenide.sleep(5000);
        for (int j = 0; j < 1600; j++) {

            for (int i = 1; i <= alarmTable.size(); i++) {
                try {

                    if (($(By.xpath(String.format(alarmStatus, i))).isDisplayed())) {
                        System.out.println(j);
                        commonWaiter(($(By.xpath(String.format(alarmStatus, i)))), visible).click();
                        System.out.println(acknowledgeHeader.getText());
                        commonWaiter(acknowledgeButton, visible).click();
                        commonWaiter(closeAlarm, visible).click();
                        // commonWaiter(($(By.xpath(String.format(acknowledgedBy, i)))), visible)
                        // .shouldHave(text("Administrator Bio4C"));
                        break;
                        // Selenide.sleep(3000);
                    }
                } catch (NoSuchElementException e) {
                    System.out.print("Already done");
                } catch (StaleElementReferenceException e) {
                    System.out.print("Already done");
                }

            }

            Selenide.sleep(3000);
            // ElementsCollection alarmTable = $$(By.xpath("//table[@class='alarm-list-table']/tbody/tr"));
            System.out.println("alarmTable.size()_" + j + "_" + alarmTable.size());
            // commonWaiter(acknowledgeAlarm, visible).click();
        }
    }

    public void verifyAcknowledgedAlarm() {

        commonWaiter(acknowledgeAlarm, visible).click();
        System.out.println(acknowledgeHeader.getText());
        commonWaiter(acknowledgeButton, visible).click();
        /*
         * Selenide.switchTo() .parentFrame(); if ($(By.xpath("//label[@class=\"uop-label-critical\"]")).isDisplayed())
         * { $(By.xpath("//label[@class=\"uop-label-critical\"]")).click(); }
         */
    }

    public void switchToFrame() {
        goToIFrame();
    }
}
