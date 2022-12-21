package pageobjects.utility;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.util.DriverHooks;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return element.waitUntil(condition, 20000l, 500l);
    }

    public static Wait<WebDriver> fluentWaiter() {
        return new FluentWait<>(WebDriverRunner.getWebDriver())
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
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

    public static LocalDate dateParser(String value, String orgFormat) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(orgFormat).localizedBy(Locale.ENGLISH);
        LocalDate selectedDate = LocalDate.parse(value, formatter);

        return selectedDate;
    }
    
    public static LocalDateTime dateParserLocalDateTime(String value, String orgFormat) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(orgFormat).localizedBy(Locale.ENGLISH);
        LocalDateTime selectedDate = LocalDateTime.parse(value, formatter);

        return selectedDate;
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
}

