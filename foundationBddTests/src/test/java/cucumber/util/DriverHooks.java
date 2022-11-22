package cucumber.util;

import com.codeborne.selenide.WebDriverRunner;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.WebDriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.TimezoneUtils;

public class DriverHooks {

    @Before
    public void before(Scenario scenario) throws IOException {
        var driverVersion = System.getenv("DRIVER_VERSION");
        var browserVersion = System.getenv("BROWSER_VERSION");

        if(driverVersion != null){
            WebDriverManager.chromedriver().driverVersion(driverVersion).browserVersion(browserVersion).setup();
        }else {
            WebDriverManager.chromedriver().setup();
        }
        WebDriverUtils.setUp("Chrome");
        TimezoneUtils.setTimezoneDiffInSecondsFromProperties();
        TrustAllCertificates.install();
    }

    @After(order = 100)
    public void tearDown(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "name");
        WebDriverUtils.tearDown(scenario);
    }
}
