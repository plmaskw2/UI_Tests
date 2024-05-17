package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebListUtils;
import framework.utils.WebPage;
import io.qameta.allure.Param;
import io.qameta.allure.model.Parameter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class RegisterPage extends WebPage {

    @FindBy(xpath = "//h1[.='Signup and Lets Go!']/ancestor::div[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//input[@value='Signup']")
    private WebElement signupButton;

    @FindBy(xpath = ".//a[@href='login.php']")
    private WebElement loginButton;

    @FindBy(xpath = ".//input[@id='imgInp']")
    private WebElement setAvatarButton;

    @FindBy(xpath = ".//div[@class='alert alert-success']")
    private WebElement successfulAlert;

    @FindBy(xpath = ".//*[@placeholder]")
    private List<WebElement> inputList;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public RegisterPage isAt() {
        webWaitUtils.waitForVisible(signupButton, Timeouts.MEDIUM);
        return this;
    }

    public RegisterPage enterUsername(String username) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Username"), username);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Email"), email);
        return this;
    }

    public RegisterPage enterPassword(@Param(mode=Parameter.Mode.HIDDEN) String password) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Password"), password, true);
        return this;
    }

    public RegisterPage enterConfirmation(String password) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Repeat Password"), password);
        return this;
    }

    public RegisterPage enterFirstName(String firstName) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "First name"), firstName);
        return this;
    }

    public RegisterPage enterLastName(String lastName) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Last name"), lastName);
        return this;
    }

    public RegisterPage enterHeadline(String headline) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Your profile headline"), headline);
        return this;
    }

    public RegisterPage enterDescription(String description) {
        enterText(WebListUtils.getElementFromListByAttributeEquals(inputList, "placeholder", "Tell people about yourself"), description);
        return this;
    }

    public RegisterPage uploadAvatar(String avatarPath) {
        uploadFile(setAvatarButton, avatarPath);
        return this;
    }

    public StartupPage clickLoginButton() {
        clickElement(loginButton);
        return new StartupPage(driver).isAt();
    }

    public RegisterPage clickSignupButton() {
        clickElement(signupButton);
        webWaitUtils.waitForVisible(successfulAlert, Timeouts.LOW);
        return this;
    }

    public RegisterPage verifySignupSuccessful() {
        Assertions
                .assertThat(isDisplayed(successfulAlert))
                .as("User has been successfully registered")
                .isTrue();
        return this;
    }
}
