package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        tags = "@3",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)
public class Runner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)  // şimdilik false yapıyoruz
    public Object[][] scenarios() {
        return super.scenarios();
    }
}