package tests;

import base.BaseTest;
import data_provider.DataProvider;
import framework.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Regression Tests")
@Feature("Chat Feature")
@Tag("US-02")
public class ChatFunctionalityTest extends BaseTest {

    User user1;
    User user2;


    @BeforeEach
    @Description("Prerequisites")
    public void prerequisites() {
        user1 = DataProvider.getNewUser();
        user2 = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(user1)
                .verifySignupSuccessful()
                .registerUser(user2)
                .verifySignupSuccessful();
    }

    @Test
    @Description("Chat Functionality Test")
    public void chatTest() {
        String messageContent = DataProvider.getMessageContent();
        String messageContent2 = DataProvider.getMessageContent();

        startupStepdefs
                .openApp()
                .logInToApplication(user1.getUserName(), user1.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user2.getUserName())
                .sentMessageToUser(messageContent)
                .verifySentMessage(messageContent);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user2.getUserName(), user2.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user1.getUserName())
                .verifyReceivedMessage(messageContent)
                .sentMessageToUser(messageContent2)
                .verifySentMessage(messageContent2);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user1.getUserName(), user1.getPassword());
        navigationStepdefs.navigateToMessages();
        messagesStepdefs
                .openChatWithUser(user2.getUserName())
                .verifyReceivedMessage(messageContent2);
    }
}
