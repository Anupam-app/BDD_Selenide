package pageobjects.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class AlarmPage {
    private SelenideElement alarmHeader = $(By.xpath("//div[text()='Alarms']"));

    public void checkAlarmHeader() {
        alarmHeader.shouldBe(visible);
    }
}
