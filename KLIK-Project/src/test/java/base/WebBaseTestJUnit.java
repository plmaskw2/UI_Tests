package base;

import framework.utils.ConfigurationUtils;
import framework.utils.driver_factory.DriverFactory;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import stepdefs.*;

@ExtendWith(AfterTestExecutionCallbackBase.class)
public abstract class WebBaseTestJUnit {
    public WebDriver driver;
    protected StartupStepdefs startupStepdefs;
    protected NavigationStepdefs navigationStepdefs;
    protected MessagesStepdefs messagesStepdefs;
    protected DashboardStepdefs dashboardStepdefs;
    protected ForumStepdefs forumStepdefs;
    @SneakyThrows
    @Step("Initialize stepdefs classes")
    public void initializeStepdefs() {
        //TODO: Properties and driver initialization move to Before BaseTest class
        ConfigurationUtils.loadProperties();
        driver = DriverFactory.valueOf(ConfigurationUtils.properties.getProperty("driver")).getDriverManager().getDriver();
        startupStepdefs = new StartupStepdefs(driver);
        navigationStepdefs = new NavigationStepdefs(driver);
        messagesStepdefs = new MessagesStepdefs(driver);
        dashboardStepdefs = new DashboardStepdefs(driver);
        forumStepdefs = new ForumStepdefs(driver);
    }
}
