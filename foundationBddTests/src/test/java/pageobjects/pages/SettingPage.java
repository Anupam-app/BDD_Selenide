package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Selenide.$;

public class SettingPage {

    private final String XPATH_COMPONENT_TEXT = "//div[text()='%s']";
    private final String XPATH_LANGUAGE_TEXT ="//*[@class='system-connect-dropdown-container']//*//li[text()='%s']";
    private final String XPATH_LANGUAGE_ACTIVE_TEXT =
            "//*[@class='system-connect-dropdown-container']//*[@class='active-label' and text()='%s']";

    private SelenideElement settingsPageLinkText = $(By.id("ConfigurationManagement"));
    private SelenideElement systemComponentText = $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "System Components")));
    private SelenideElement generalText = $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "General")));

    private SelenideElement languageDropDown = $(By.className("system-connect-dropdown-container"));

    private SelenideElement inputCustomLabel = $(By.id("customerShortDescription"));
    private SelenideElement applyButton = $(By.xpath("//button[contains(text(),'Apply')]"));
    private SelenideElement customLabelText = $(By.xpath("//input[@id='customerShortDescription']"));

    public void goToSettingsPage() {
        SelenideHelper.commonWaiter(settingsPageLinkText, Condition.visible).click();
    }

    public void goToSystemComponents() {
        systemComponentText.click();
    }

    public void changeSettings(String customLabelName) {
        $(By.id("customerShortDescription")).clear();
        inputCustomLabel.setValue(customLabelName);
        applyButton.click();
        customLabelText.waitUntil(Condition.visible, 120001);
        String s = customLabelText.getValue();
        System.out.println(s);
    }

    public String getCustomLabelNameText() {
        return customLabelText.getValue();
    }

    public void goToGeneralComponent() {
        generalText.click();
    }

    public void changeLanguage(String language) {
        languageDropDown.click();
        $(By.xpath(String.format(XPATH_LANGUAGE_TEXT, language))).click();
    }

    public void applySettings() {
        applyButton.click();
    }

    public void seeLanguageActivated(String languageName) {
       SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, languageName))),Condition.visible);
    }
}
