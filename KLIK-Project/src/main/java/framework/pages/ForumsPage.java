package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebListUtils;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class ForumsPage extends WebPage {

    @FindBy(xpath = "//h1[.='KLiK Forums']/ancestor::main[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//a[@href='create-topic.php']")
    private WebElement createForumButton;

    @FindBy(xpath = ".//a[contains(@href, 'posts.php')]")
    private List<WebElement> forumsList;

    public ForumsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public ForumsPage isAt() {
        webWaitUtils.waitForClickable(createForumButton, Timeouts.LOW);
        return this;
    }

    public CreateAForumPage clickCreateForumButton() {
        scrollToElement(createForumButton);
        jsClickElement(createForumButton);
        return new CreateAForumPage(driver).isAt();
    }

    public ForumPage navigateToForumBySubject(String subject) {
        scrollToElement(forumsList.get(forumsList.size() - 1));
        webWaitUtils.waitForClickable(WebListUtils.getElementFromListByTextContains(forumsList, subject), Timeouts.VERY_LOW);
        jsClickElement(WebListUtils.getElementFromListByTextContains(forumsList, subject));
        return new ForumPage(driver).isAt();
    }
}
