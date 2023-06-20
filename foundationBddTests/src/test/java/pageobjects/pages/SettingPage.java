package pageobjects.pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

import org.junit.Assert;
import org.openqa.selenium.By;

import pageobjects.components.SpinnerComponent;
import pageobjects.utility.SelenideHelper;

public class SettingPage {

    private final String XPATH_COMPONENT_TEXT = "//div[text()='%s']";
    private final String XPATH_LANGUAGE_TEXT = "//*[@class='system-connect-dropdown-container']//*//li[text()='%s']";
    private final String XPATH_LANGUAGE_ACTIVE_TEXT =
            "//*[@class='system-connect-dropdown-container']//*[@class='active-label' and text()='%s']";
    private final String XPATH_HEADER = "//div[contains(@class,'header')]";

    private final SelenideElement settingsPageLinkText = $(By.id("ConfigurationManagement"));
    private final SelenideElement systemComponentText =
            $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "System Components")));
    private final SelenideElement generalText = $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "General")));

    private final SelenideElement languageDropDown = $(By.className("system-connect-dropdown-container"));

    private final SelenideElement inputCustomLabel = $(By.id("customerShortDescription"));
    private final SelenideElement applyButton = $(By.xpath("//button[contains(text(),'Apply')]"));
    private final SelenideElement customLabelText = $(By.xpath("//input[@id='customerShortDescription']"));
    private final SelenideElement settingsHeader =
            $(By.xpath("//div[text()='Settings' and @class='setting-header-title']"));
    public static final String DATE_FORMAT = "MMM d, yyyy";
    public static final String TIME_FORMAT = "kk:mm:ss";
    private final String generalFormat = "(//div[@class='batch-id']/div)[%d]";
    private final SelenideElement decimalFormat = $(By.xpath("(//div[@class='batch-id'])[4]"));
    private String dateFormat = null;
    private String timeFormat = null;
    private final SpinnerComponent spinnerComponent = new SpinnerComponent();

    public void goToSettingsPage() {
        SelenideHelper.commonWaiter(settingsPageLinkText, Condition.visible)
                .click();
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
        SelenideHelper.commonWaiter(spinnerComponent.spinnerIcon, visible);
        SelenideHelper.commonWaiter(spinnerComponent.spinnerIcon, not(visible));
    }

    public void seeLanguageActivated(String languageName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, languageName))),
                Condition.visible);
    }

    public void seeContent(String expectedText) {
        commonWaiter($(By.xpath(XPATH_HEADER)), text(expectedText));
    }

    public void verifySettingHeader() {
        settingsHeader.shouldBe(visible);
    }

    public void zoomOut() throws AWTException {
        Robot robot = new Robot();
        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Selenide.sleep(2000);
        }
    }

    public void verifyGeneralTab(String options) {
        switch (options) {
            case "Language":
                languageDropDown.shouldBe(visible);
                $(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "English (USA)"))).shouldBe(visible);
                languageDropDown.click();
                $(By.xpath(String.format(XPATH_LANGUAGE_TEXT, "Deutsch (Deutschland)"))).click();
                break;
            case "Date Format":
                dateFormat = $(By.xpath(String.format(generalFormat, 2))).getText();
                Assert.assertTrue(SelenideHelper.dateFormatCheck(dateFormat, DATE_FORMAT));
                break;
            case "Time Format":
                timeFormat = $(By.xpath(String.format(generalFormat, 3))).getText();
                Assert.assertTrue(SelenideHelper.dateFormatCheck(timeFormat, TIME_FORMAT));
                break;
            case "Number Format":
                decimalFormat.shouldHave(text("1,000.123"));
                break;
            case "Session Timeout":
                $(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "60 min"))).shouldBe(visible);
                break;
            default:
        }
    }

}
