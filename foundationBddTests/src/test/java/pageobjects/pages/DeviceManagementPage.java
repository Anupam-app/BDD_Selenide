package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

public class DeviceManagementPage {

    private final SelenideElement deviceManagementLinkText = $(By.id("DeviceManagement"));
    private SelenideElement dashboardHeader = $(By.xpath("//*[@class='dashboard']"));

    public void goToDeviceDashboard() {
        SelenideHelper.commonWaiter(deviceManagementLinkText, visible).click();
    }

    public void seeContent(String expectedText) {
        SelenideHelper.commonWaiter(dashboardHeader, visible)
                .shouldHave(Condition.text(expectedText));
    }
}