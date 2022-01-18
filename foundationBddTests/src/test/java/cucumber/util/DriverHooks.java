package cucumber.util;

import com.codeborne.selenide.WebDriverRunner;
import com.xceptance.neodymium.util.WebDriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class DriverHooks {
    @Given("^the browser \"([^\"]*)\" is open$")
    public static void setUp(String browserProfileName) {
        WebDriverUtils.setUp(browserProfileName);
    }

    @Before
    public void before(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
    }

    @After(order = 100)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        WebDriverUtils.tearDown(scenario);
    }
}
