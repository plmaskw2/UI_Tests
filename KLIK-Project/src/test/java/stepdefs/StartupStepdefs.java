package stepdefs;

import base.BaseStepdefs;
import framework.model.User;
import framework.pages.DashboardPage;
import framework.pages.RegisterPage;
import framework.pages.StartupPage;
import framework.utils.ConfigurationUtils;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import org.openqa.selenium.WebDriver;

public class StartupStepdefs extends BaseStepdefs {

    public StartupStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step("User open application")
    public StartupStepdefs openApp() {
        driver.get(ConfigurationUtils.properties.getProperty("url"));
        new StartupPage(driver).isAt();
        return this;
    }

    @Step("User log in to application")
    public StartupStepdefs logInToApplication(String username, @Param(mode= Parameter.Mode.HIDDEN) String password) {
        new StartupPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    @Step("User navigates to register form")
    public StartupStepdefs navigateToRegisterForm() {
        new StartupPage(driver).clickSignupButton();
        return this;
    }

    @Step("User register new user {user.userName}")
    public StartupStepdefs registerUser(@Param(mode=Parameter.Mode.HIDDEN) User user) {
        new RegisterPage(driver)
                .enterUsername(user.getUserName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmation(user.getPassword())
                .enterFirstName(user.getFirstName())
                .enterLastName(user.getLastName())
                .enterHeadline(user.getHeadline())
                .enterDescription(user.getAboutYourself())
                .uploadAvatar(user.getAvatarPath())
                .clickSignupButton();
        return this;
    }

    @Step("User verifies that he is signed up successfully")
    public StartupStepdefs verifySignupSuccessful() {
        new RegisterPage(driver).verifySignupSuccessful();
        return this;
    }

    @Step("user verifies that he is logged in successfully")
    public StartupStepdefs verifyLoggedInSuccessful() {
        new DashboardPage(driver).verifyLoggedInSuccessful();
        return this;
    }

    @Step("User navigates to startup page")
    public StartupStepdefs navigateToStartupPageFromRegistrationForm() {
        new RegisterPage(driver).clickLoginButton();
        return this;
    }
}
