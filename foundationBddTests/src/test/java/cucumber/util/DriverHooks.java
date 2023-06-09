package cucumber.util;

import com.codeborne.selenide.WebDriverRunner;
import com.xceptance.neodymium.util.WebDriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;

import pageobjects.pages.LoginPage;
import pageobjects.utility.SelenideHelper;
import utils.TimezoneUtils;

public class DriverHooks {

    public static Scenario currentScenario;
    private final LoginPage loginPage;

    public DriverHooks(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

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
        currentScenario=scenario;
    }

    @AfterStep
    public void afterStep() {
        SelenideHelper.takePicture();
    }

    @After(order = 100)
    public void tearDown(Scenario scenario) {
        SelenideHelper.takePicture();
        try {
            loginPage.iLogout();
        }
        finally {
            WebDriverUtils.tearDown(scenario);
            WebDriverRunner.getWebDriver().quit();
        }
    }
}
