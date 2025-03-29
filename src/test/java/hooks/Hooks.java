package hooks;

import utils.DriverManager;
import utils.LogUtils;
import utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {
    private ScreenshotUtils screenshotUtils;

    @Before
    public void setup(Scenario scenario) {
        LogUtils.info("Starting scenario: " + scenario.getName());
        DriverManager.getInstance().setDriver();
        screenshotUtils = new ScreenshotUtils(DriverManager.getInstance().getDriver());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            LogUtils.info("Scenario failed: " + scenario.getName());
            String screenshotPath = screenshotUtils.takeScreenshot(scenario.getName());
            LogUtils.info("Screenshot saved: " + screenshotPath);
        }
        DriverManager.getInstance().removeDriver();
        LogUtils.info("Finished scenario: " + scenario.getName());
    }

    @BeforeMethod
    public void beforeMethod() {
        // TestNG specific setup if needed
    }

    @AfterMethod
    public void afterMethod() {
        // TestNG specific cleanup if needed
    }
}