package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

public class RecipeDashboardPage {

    private final SelenideElement recipeDashboardLinkText = $(By.id("RecipeDashboard"));
    private SelenideElement recipeDashboardHeader = $(By.xpath("//*[@class='page-header']"));

    public void goToRecipeDashboard() {
        SelenideHelper.commonWaiter(recipeDashboardLinkText, visible).click();
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter(recipeDashboardHeader, visible)
                .shouldHave(Condition.text(expectedText));
    }
}