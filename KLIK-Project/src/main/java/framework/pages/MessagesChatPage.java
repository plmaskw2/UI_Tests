package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;


public class MessagesChatPage extends WebPage {

    @FindBy(xpath = "//div[@class='messaging']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//textarea[@class='write_msg form-control-plaintext']")
    private WebElement textArea;

    @FindBy(xpath = ".//button[@id='reply']")
    private WebElement sendButton;

    @FindBy(xpath = ".//div[contains(@class,'ing_msg')][last()]")
    private WebElement newestMessage;

    @FindBy(xpath = ".//div[@class='sent_msg']/p")
    private List<WebElement> sentMessages;

    @FindBy(xpath = ".//div[@class='received_withd_msg']/p")
    private List<WebElement> receivedMessages;

    public MessagesChatPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public MessagesChatPage isAt() {
        webWaitUtils.waitForVisible(textArea, Timeouts.LOW);
        return this;
    }

    public MessagesChatPage enterMessage(String text) {
        enterText(textArea, text);
        clickElementAndWait(sendButton, 1000);
        return this;
    }

    public MessagesChatPage verifySentMessageIsVisible(String text) {
        scrollToElement(newestMessage);
        Assertions
                .assertThat(sentMessages.stream().parallel().anyMatch(webElement -> webElement.getText().equals(text)))
                .as("Message with text: \'"+ text + "\' exists.")
                .isTrue();
        return this;
    }

    public MessagesChatPage verifyReceivedMessageIsVisible(String text) {
        Assertions
                .assertThat(receivedMessages.stream().parallel().anyMatch(webElement -> webElement.getText().equals(text)))
                .as("Message with text: \'"+ text + "\' exists.")
                .isTrue();
        return this;
    }
}
