package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Load config file once at suite start
    public static void loadConfig() {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Fetch property by key
    public static String getProperty(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties not loaded. Call loadConfig() first.");
        }
        return properties.getProperty(key);
    }
}
