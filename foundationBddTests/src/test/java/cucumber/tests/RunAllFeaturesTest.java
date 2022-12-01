package cucumber.tests;

import com.xceptance.neodymium.NeodymiumCucumberRunner;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/cucumber/features/", glue = "cucumber", monochrome = true, plugin =
        {
                "pretty",
                "html:target/cucumber-report.html"
        })
public class RunAllFeaturesTest {
}