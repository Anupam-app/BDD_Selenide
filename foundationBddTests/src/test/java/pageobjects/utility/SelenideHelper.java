package pageobjects.utility;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.util.DriverHooks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class SelenideHelper {
    public static By byTestAttribute(String attributeValue) {
        return byAttribute("data-tid", attributeValue);
    }

    public static SelenideElement commonWaiter(SelenideElement element, Condition condition) {
        return element.waitUntil(condition, 20000L, 500L);
    }

    public static Wait<WebDriver> fluentWaiter() {
        return new FluentWait<>(WebDriverRunner.getWebDriver())
                .withTimeout(Duration.ofSeconds(60 * 2))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);
    }

    public static void goToIFrame() {
        switchTo().frame("CrossDomainiframeId");
    }

    public static void goParentFrame() {
        switchTo().parentFrame();
    }

    public static String removeLastCharOptional(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }

    public static void takePicture() {
        byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        DriverHooks.currentScenario.attach(screenshot, "image/png", "name");
    }

    public static LocalDate dateParser(String value, String orgFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(orgFormat).localizedBy(Locale.ENGLISH);
        return LocalDate.parse(value, formatter);
    }

    public static void appRefresh() {
        refresh();
    }

    public static void maximize() {
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }

    public static void goToDefault() {
        switchTo().defaultContent();
    }

    public static void selectButton(SelenideElement locator) {
        locator.waitUntil(visible, 10000).isEnabled();
        locator.click();
    }

    public static boolean dateFormatCheck(String dateStr, String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

