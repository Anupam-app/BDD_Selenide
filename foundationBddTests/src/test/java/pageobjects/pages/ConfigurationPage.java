package pageobjects.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ConfigurationPage {
    private SelenideElement configurationHeader = $(By.xpath("//h4[contains(text(),'Process Control')]"));

    public void verifyConfigurationHeader() {
        configurationHeader.shouldBe(visible);
    }
}
