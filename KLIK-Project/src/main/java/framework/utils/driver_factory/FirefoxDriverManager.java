package framework.utils.driver_factory;

import framework.utils.ConfigurationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverManager extends DriverManager {

    //TODO: develop Firefox for Remote Grid and Selenoid, like Chrome.
    @Override
    protected WebDriver createDriver() {
        WebDriver driver = null;
        switch (ConfigurationUtils.properties.getProperty("driverType")) {
            case "REMOTE":
                System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
                try {
                    driver = new RemoteWebDriver(new URL(ConfigurationUtils.GRID_HUB), getFirefoxOptions());
                }
                catch (MalformedURLException ignore) {}
            case "LOCAL":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver(getFirefoxOptions());
        }
        return driver;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("-headless");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }
}
