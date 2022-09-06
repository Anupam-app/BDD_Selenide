package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.util.I18nUtils;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TrendsPage {

    private final SelenideElement alarmManagementLinkText = $(By.id("Trends"));
    private SelenideElement starredLabel = $(By.xpath("(//button//label)[1]"));
    private SelenideElement defaultButton = $(By.xpath("(//button[@class='trends-parameters']//input)[2]"));
    private ElementsCollection deviceShapeElements = $$(By.xpath("(//div[@class='trends-sidebar']//ul//li//label)"));

    public void goToTrends() {
        SelenideHelper.commonWaiter(alarmManagementLinkText, Condition.visible).click();
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter(starredLabel, Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        SelenideHelper.commonWaiter(defaultButton, Condition.visible).click();
        return I18nUtils.getElementsNotI18N(deviceShapeElements);
    }
}
