package tests;

import base.BaseTest;
import data_provider.DataProvider;
import framework.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Regression Tests")
@Feature("Registration Feature")
@Tag("US-03")
public class RegistrationTest extends BaseTest {

    @Test
    @Description("Registration Test")
    public void registrationTest() {
        User newUser = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(newUser)
                .verifySignupSuccessful()
                .navigateToStartupPageFromRegistrationForm()
                .logInToApplication(newUser.getUserName(), newUser.getPassword())
                .verifyLoggedInSuccessful();
    }
}
