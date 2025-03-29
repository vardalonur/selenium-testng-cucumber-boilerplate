package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file!", e);
        }
    }

    public static String  getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout.seconds"));
    }

    public static String getBrowser() {
        return getProperty("browser");
    }
}