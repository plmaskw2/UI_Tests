package stepdefs;

import base.BaseStepdefs;
import framework.pages.MessagesChatPage;
import framework.pages.MessagesHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class MessagesStepdefs extends BaseStepdefs {

    public MessagesStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step("Users opens chat with {0}")
    public MessagesStepdefs openChatWithUser(String username) {
        new MessagesHomePage(driver).openUserChat(username);
        return this;
    }

    @Step("Users sent message: {0}")
    public MessagesStepdefs sentMessageToUser(String text) {
        new MessagesChatPage(driver).enterMessage(text);
        return this;
    }

    @Step("User verifies that message {0} has been sent")
    public MessagesStepdefs verifySentMessage(String text) {
        new MessagesChatPage(driver).verifySentMessageIsVisible(text);
        return this;
    }

    @Step("Users verifies that he is received a message {0}")
    public MessagesStepdefs verifyReceivedMessage(String text) {
        new MessagesChatPage(driver).verifyReceivedMessageIsVisible(text);
        return this;
    }
}
