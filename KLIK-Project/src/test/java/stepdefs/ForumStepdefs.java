package stepdefs;

import base.BaseStepdefs;
import framework.model.Forum;
import framework.pages.CreateAForumPage;
import framework.pages.ForumPage;
import framework.pages.ForumsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ForumStepdefs extends BaseStepdefs {

    public ForumStepdefs(WebDriver driver) {
        super(driver);
    }

    @Step("User creates new Topic {forum.topicSubject}")
    public ForumStepdefs createNewTopic(Forum forum) {
        new ForumsPage(driver)
                .clickCreateForumButton()
                .enterTopicSubject(forum.getTopicSubject())
                .selectCategory(forum.getCategory())
                .enterForumQuestion(forum.getQuestion())
                .clickCreateForumButton();
        return this;
    }

    @Step("User verifies that new Forum is created")
    public ForumStepdefs verifyNewForumCreated() {
        new CreateAForumPage(driver).verifyNewForumCreated();
        return this;
    }

    @Step("User navigates to View Forums")
    public ForumStepdefs navigateToViewForumsFromCreateForumView() {
        new CreateAForumPage(driver).navigateToViewForums();
        return this;
    }

    @Step("User opens forum {0}")
    public ForumStepdefs openForumBySubject(String subject) {
        new ForumsPage(driver).navigateToForumBySubject(subject);
        return this;
    }

    @Step("User add reply {0}")
    public ForumStepdefs addReply(String subject) {
        new ForumPage(driver)
                .enterReply(subject)
                .clickSubmitReplyButton();
        return this;
    }

    @Step("User removes reply {0}")
    public ForumStepdefs removeReplyByText(String comment) {
        new ForumPage(driver).clickTrashButtonByComment(comment);
        return this;
    }

    @Step("User verifies that comment {0} is visible")
    public ForumStepdefs verifyCommentIsVisible(String text) {
        new ForumPage(driver).verifyCommentIsVisible(text);
        return this;
    }

    @Step("User verifies that comment {0} is not visible")
    public ForumStepdefs verifyCommentIsNotVisible(String text) {
        new ForumPage(driver).verifyCommentIsNotVisible(text);
        return this;
    }
}
