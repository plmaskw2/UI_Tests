package framework.pages;

import framework.model.Message;
import framework.table.MessageRow;
import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesHomePage extends WebPage {

    @FindBy(xpath = "//div[@class='messaging']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//h1[.='Click on a person to start chatting']")
    private WebElement messagesHeader;

    @FindBy(xpath = ".//div[@class='chat_people']")
    private WebElement firstMessage;

    @FindBy(xpath = ".//div[@class='inbox_chat']/a")
    private List<WebElement> messagesList;

    private List<Message> listOfMessages = new ArrayList<>();

    public MessagesHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public MessagesHomePage isAt() {
        webWaitUtils.waitForVisible(messagesHeader, Timeouts.LOW);
        webWaitUtils.waitForPageReadyState(Timeouts.LOW);
        return this;
    }

    public MessagesChatPage openUserChat(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(Timeouts.HIGH))
                .pollingEvery(Duration.ofMillis(3000))
                .until(waitUntilAllUsersLoaded());

        Message userMessage = listOfMessages
                .stream()
                .filter(message -> message.getUsername().contains(username))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No message!"));
        clickElement(userMessage.getUsernameLink());
        return new MessagesChatPage(driver).isAt();
    }

    private ExpectedCondition<Boolean> waitUntilAllUsersLoaded() {
        return new ExpectedCondition<Boolean>() {
            int countUsers = 0;
            public Boolean apply(WebDriver driver) {
                countUsers = messagesList.size();
                scrollToElement(messagesList.get(messagesList.size() - 1));
                if (listOfMessages != null) {
                    listOfMessages.clear();
                }
                listOfMessages = messagesList.stream().map(webElement -> new MessageRow(driver, webElement).toModel()).collect(Collectors.toList());
                return countUsers == messagesList.size();
            }
        };
    }
}
