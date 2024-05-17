package stepdefs;

import base.BaseStepdefs;
import framework.pages.DashboardPage;
import framework.pages.NavigationBarPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class NavigationStepdefs extends BaseStepdefs {

    public NavigationStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step("User navigates to Messages tab")
    public NavigationStepdefs navigateToMessages() {
        new NavigationBarPage(driver).navigateToMessages();
        return this;
    }

    @Step("User navigates to Forums tab")
    public NavigationStepdefs navigateToForums() {
        new DashboardPage(driver).navigateToForums();
        return this;
    }

    @Step("User logged out")
    public NavigationStepdefs logout() {
        new NavigationBarPage(driver).clickLogoutButton();
        return this;
    }
}
