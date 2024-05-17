package framework.pages;

import framework.utils.Timeouts;
import framework.utils.WebPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAForumPage extends WebPage {

    @FindBy(xpath = "//*[contains(@class, 'container')]")
    private WebElement pageContainer;

    @FindBy(xpath = ".//input")
    private WebElement forumSubjectField;

    @FindBy(xpath = ".//select")
    private WebElement categorySelect;

    @FindBy(xpath = ".//textarea[@name='post-content']")
    private WebElement forumQuestionField;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement createForumButton;

    @FindBy(xpath = ".//h5[@class='text-success']")
    private WebElement alertSuccessfull;

    @FindBy(xpath = ".//a[@href='topics.php']")
    private WebElement viewForumsButton;

    public CreateAForumPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public CreateAForumPage isAt() {
        webWaitUtils.waitForVisible(createForumButton, Timeouts.LOW);
        return this;
    }

    public CreateAForumPage enterTopicSubject(String text) {
        enterText(forumSubjectField, text);
        return this;
    }

    public CreateAForumPage selectCategory(String option) {
        new Select(categorySelect).selectByVisibleText(option);
        return this;
    }

    public CreateAForumPage enterForumQuestion(String text) {
        enterText(forumQuestionField, text);
        return this;
    }

    public CreateAForumPage clickCreateForumButton() {
        clickElement(createForumButton);
        webWaitUtils.waitForVisible(alertSuccessfull, Timeouts.LOW);
        return this;
    }

    public CreateAForumPage verifyNewForumCreated() {
        Assertions
                .assertThat(isDisplayed(alertSuccessfull))
                .as("User has created a new forum successfully")
                .isTrue();
        return this;
    }

    public ForumsPage navigateToViewForums() {
        clickElement(viewForumsButton);
        return new ForumsPage(driver).isAt();
    }
}
