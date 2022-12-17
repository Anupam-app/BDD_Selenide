package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

public class AlarmPage {

    private final SelenideElement alarmManagementLinkText = $(By.id("AlarmManagement"));
    private SelenideElement alarmHeader = $(By.xpath("//div[text()='Alarms']"));

    public void goToAlarm() {
        SelenideHelper.commonWaiter(alarmManagementLinkText, visible).click();
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter($(By.id("alarm-list-body")), visible)
                .shouldHave(Condition.text(expectedText));
    }

    public void checkAlarmHeader() {
        alarmHeader.shouldBe(visible);
    }

}