package framework.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationUtils {

    public static Properties properties = new Properties();
    public static boolean isRemote;
    public static boolean isSelenoid;
    public static String DRIVER_TYPE;
    public static String SELENOID_HUB;
    public static String GRID_HUB;
    public static boolean isEnableVideo;

    @SneakyThrows
    public static Properties loadProperties() {
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "configuration.properties"));
        isRemote = getProperty("driverType").equals("REMOTE");
        isSelenoid = getProperty("selenoid").equals("true");
        DRIVER_TYPE = getProperty("driverType");
        SELENOID_HUB = getProperty("selenoidHub");
        GRID_HUB = getProperty("gridHub");
        isEnableVideo = getProperty("enableVideo").equals("true");
        return properties;
    }

    private static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
