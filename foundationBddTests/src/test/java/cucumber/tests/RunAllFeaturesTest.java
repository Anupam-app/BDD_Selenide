package cucumber.tests;

import com.xceptance.neodymium.NeodymiumCucumberRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/cucumber/features/", glue = "cucumber", monochrome = true,tags="@test",
        plugin = {"pretty", "html:target/cucumber-report.html"})

public class RunAllFeaturesTest {
}
