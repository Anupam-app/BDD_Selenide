package cucumber.util;

import com.xceptance.neodymium.util.WebDriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverHooks {
    @Given("^The browser \"([^\"]*)\" is open$")
    public static void setUp(String browserProfileName) {
        WebDriverUtils.setUp(browserProfileName);
    }

    @Before
    public void before(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
    }

    @After(order = 100)
    public void tearDown(Scenario scenario) {
        WebDriverUtils.tearDown(scenario);
    }
}
