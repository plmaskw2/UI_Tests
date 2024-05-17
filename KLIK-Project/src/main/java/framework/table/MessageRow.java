package framework.table;

import framework.model.Message;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class MessageRow extends WebPage {

    @FindBy(xpath = ".//h5")
    private WebElement username;

    public MessageRow(WebDriver driver, WebElement wrapper) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(wrapper), this);
    }

    public Message toModel() {
        return Message.builder()
                .username(username.getText())
                .usernameLink(username)
                .build();
    }
}
