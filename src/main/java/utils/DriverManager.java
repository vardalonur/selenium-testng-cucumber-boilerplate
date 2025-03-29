package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import config.ConfigReader;

public class DriverManager {
    private static DriverManager instance;
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    // Overloaded setDriver methods
    public void setDriver() {
        // ConfigReader'dan browser değerini al
        setDriver(ConfigReader.getBrowser());
    }

    public void setDriver(String browserParam) {
        // TestNG parametresi null ise ConfigReader'dan al
        String browser = browserParam != null ? browserParam : ConfigReader.getBrowser();
        LogUtils.info("Initializing " + browser + " browser");

        WebDriver driver;
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            threadLocal.set(driver);
        } catch (Exception e) {
            LogUtils.error("Failed to initialize driver");
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public void removeDriver() {
        try {
            if (threadLocal.get() != null) {
                threadLocal.get().quit();
                threadLocal.remove();
                LogUtils.info("Driver successfully closed and removed");
            }
        } catch (Exception e) {
            LogUtils.error("Failed to close driver");
        }
    }
}