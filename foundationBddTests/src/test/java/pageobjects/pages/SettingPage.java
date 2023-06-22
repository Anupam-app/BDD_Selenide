package pageobjects.pages;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private final SelenideElement systemComponentText = $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "System")));
    private final SelenideElement generalText = $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "General")));

    private final SelenideElement languageDropDown = $(By.className("system-connect-dropdown-container"));

    private final SelenideElement inputCustomLabel = $(By.id("customerShortDescription"));
    private final SelenideElement applyButton = $(By.xpath("//button[contains(text(),'Apply')]"));
    private final SelenideElement customLabelText = $(By.xpath("//input[@id='customerShortDescription']"));
    private final SelenideElement settingsHeader =
            $(By.xpath("//div[text()='Settings' and @class='setting-header-title']"));
    public static final String DATE_FORMAT = "MMM d, yyyy";
    public static final String TIME_FORMAT = "kk:mm:ss";
    private final String GENERAL_FORMAT = "(//div[@class='batch-id']/div)[%d]";
    private final SelenideElement decimalFormat = $(By.xpath("(//div[@class='batch-id'])[4]"));
    private String DATE_SETTINGS_PAGE = null;
    private final String SYSTEM_PAGE_KEYS = "(//div[@class='system-connectivity-block']/*//label)[%d]";
    private final String SYSTEM_PAGE_VALUES = "(//div[@class='system-connectivity-block']/div/div)[%d]";
    private final String INPUT_FIELDS = "//input[@name='%s']";
    private final String SYSTEM_PAGE_FIELDS = "//span[text()='%s']";
    private final SelenideElement companyName = $(By.xpath("//li[@class='company-name']/div"));
    private final SelenideElement lastMaintenanceDate = $(By.xpath("//button[text()='Reset Last Maintenance Date']"));
    private final SpinnerComponent spinnerComponent = new SpinnerComponent();
    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

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
                DATE_SETTINGS_PAGE = $(By.xpath(String.format(GENERAL_FORMAT, 2))).getText();
                Assert.assertTrue(SelenideHelper.dateFormatCheck(DATE_SETTINGS_PAGE, DATE_FORMAT));
                break;
            case "Time Format":
                DATE_SETTINGS_PAGE = $(By.xpath(String.format(GENERAL_FORMAT, 3))).getText();
                Assert.assertTrue(SelenideHelper.dateFormatCheck(DATE_SETTINGS_PAGE, TIME_FORMAT));
                break;
            case "Number Format":
                decimalFormat.shouldHave(text("1,000.123"));
                break;
            case "Session Timeout":
                $(By.xpath(String.format(XPATH_LANGUAGE_ACTIVE_TEXT, "60 min"))).shouldBe(visible);
                break;
            case "System Name":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 1))).shouldHave(text("System Name"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 2))).shouldHave(text("IVI"));
                break;
            case "Custom System Name":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 2))).shouldHave(text("Custom System Name"));
                break;
            case "System Family Name":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 3))).shouldHave(text("System Family Name"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 6))).shouldHave(text("IVIFamily"));
                break;
            case "System Serial No.":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 4))).shouldHave(text("System Serial No."));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 8))).shouldHave(text("123"));
                break;
            case "System Size":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 5))).shouldHave(text("System Size"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 10))).shouldBe(empty);
                break;
            case "Location":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 6))).shouldHave(text("Location"));
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 8))).shouldHave(text("Site"));
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 9))).shouldHave(text("Area"));
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 10))).shouldHave(text("Process Cell"));
                $(By.xpath(String.format(INPUT_FIELDS, "site"))).setValue("site");
                $(By.xpath(String.format(INPUT_FIELDS, "area"))).setValue("area");
                $(By.xpath(String.format(INPUT_FIELDS, "processCell"))).setValue("processCell");
                break;
            case "Display Label Setting":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 11))).shouldHave(text("Display Label Setting"));
                $(By.xpath(String.format(SYSTEM_PAGE_FIELDS, "Custom Label"))).shouldBe(visible);
                $(By.xpath(String.format(SYSTEM_PAGE_FIELDS, "Factory Tag"))).shouldBe(visible);
                break;
            case "Last Maintenance Date":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 1))).shouldHave(text("Last Maintenance Date"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 2))).shouldNotBe(empty);
                break;
            case "Next Maintenance Date":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 2))).shouldHave(text("Next Maintenance Date"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 4))).shouldNotBe(empty);
                break;
            case "Scheduled Maintenance":
                $(By.xpath(String.format(SYSTEM_PAGE_KEYS, 3))).shouldHave(text("Scheduled Maintenance"));
                $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 6))).shouldNotBe(empty);
                break;
            case "Report Issue":
                $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "Report Issue"))).shouldBe(visible);
                break;
            case "Maintenance":
                $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "Maintenance"))).shouldBe(visible);
                break;
            default:
        }
    }

    public void updateSystemName(String customName) {
        $(By.xpath(String.format(INPUT_FIELDS, "externalSystemName"))).setValue(customName);
    }

    public void iVerifyCustomSystemName(String customName) {
        companyName.shouldHave(text(customName));
    }

    public void goToServiceCard() {
        $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "Service"))).click();
    }

    public void goToMaintenanceTab() {
        $(By.xpath(String.format(XPATH_COMPONENT_TEXT, "Maintenance"))).click();
    }

    public void resetLastMaintenanceDate() {
        lastMaintenanceDate.click();
    }

    public void verifyMaintenanceDetails() {
        lastMaintenanceDate.waitUntil(enabled, 5000);
        $(By.xpath(String.format(SYSTEM_PAGE_VALUES, 6))).shouldHave(text("730"));
        Assert.assertTrue($(By.xpath(String.format(SYSTEM_PAGE_VALUES, 2))).getText()
                .equals(dateObj.format(formatter)));
        Assert.assertTrue($(By.xpath(String.format(SYSTEM_PAGE_VALUES, 4))).getText()
                .equals((dateObj.plusDays(730)
                        .format(formatter))));
    }

}
