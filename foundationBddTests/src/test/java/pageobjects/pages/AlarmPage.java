package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Selenide.$;

public class AlarmPage {

    private final SelenideElement alarmManagementLinkText = $(By.id("AlarmManagement"));

    public void goToAlarm() {
        SelenideHelper.commonWaiter(alarmManagementLinkText,Condition.visible).click();
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter($(By.id("alarm-list-body")),Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }
}
