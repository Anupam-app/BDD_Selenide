package pageobjects.utility;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.switchTo;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class SelenideHelper {
    public static By byTestAttribute(String attributeValue) {
        return byAttribute("data-tid",attributeValue);
    }

    public static SelenideElement commonWaiter(SelenideElement element,Condition condition){
        return element.waitUntil(condition,20000l,2000l);
    }

    public static Wait<WebDriver> fluentWaiter() {

        return new FluentWait<>(WebDriverRunner.getWebDriver())
            .withTimeout(Duration.ofSeconds(60))
            .pollingEvery(Duration.ofSeconds(5))
            .ignoring(NoSuchElementException.class);
    }

    public static void goToIFrame(){
        switchTo().frame("CrossDomainiframeId");
    }

    public static void goParentFrame(){
        switchTo().parentFrame();
    }
}
