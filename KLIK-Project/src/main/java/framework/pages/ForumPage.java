package framework.pages;

import framework.model.ForumComment;
import framework.table.ForumCommentRow;
import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ForumPage extends WebPage {

    @FindBy(xpath = "//div[@class='container']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//input[@value='Submit reply']")
    private WebElement submitReplyButton;

    @FindBy(xpath = ".//textarea[@name='reply-content']")
    private WebElement replyContentField;

    @FindBy(xpath = ".//div[@class='row']//p")
    private List<WebElement> commentsList;

    @FindBy(xpath = ".//div[@class='card post']")
    private List<WebElement> commentList;

    private List<ForumComment> listOfComments ;

    public ForumPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public ForumPage isAt() {
        webWaitUtils.waitForVisible(submitReplyButton, Timeouts.MEDIUM);
        return this;
    }

    public ForumPage enterReply(String text) {
        enterText(replyContentField, text);
        return this;
    }

    public ForumPage clickSubmitReplyButton() {
        clickElementAndWait(submitReplyButton, 1000);
        return new ForumPage(driver).isAt();
    }

    public ForumPage verifyCommentIsNotVisible(String text) {
        Assertions
                .assertThat(commentsList.stream().noneMatch(webElement -> webElement.getText().equals(text)))
                .as("Comment \'" + text + "\' is not visible.")
                .isTrue();
        return this;
    }

    public ForumPage verifyCommentIsVisible(String text) {
        Assertions
                .assertThat(commentsList.stream().anyMatch(webElement -> webElement.getText().equals(text)))
                .as("Comment \'" + text + "\' is visible.")
                .isTrue();
        return this;
    }

    public ForumPage clickTrashButtonByComment(String text) {
        clickElementAndWait(getForumCommentByContent(text).getDeleteButton(), 1000);
        return new ForumPage(driver).isAt();
    }

    private ForumComment getForumCommentByContent(String text) {
        listOfComments = commentList.stream().map(webElement -> new ForumCommentRow(driver, webElement).toModel()).collect(Collectors.toList());
        return listOfComments.stream()
                .filter(comment -> comment.getContent().equals(text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such comment!"));
    }
}
