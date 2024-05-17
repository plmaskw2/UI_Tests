package tests;

import base.BaseTest;
import data_provider.DataProvider;
import framework.model.Forum;
import framework.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Regression Tests")
@Feature("Forum Feature")
@Tag("US-01")
public class ForumInteractionsTest extends BaseTest {

    User user;
    User user2;

    @BeforeEach
    @Description("Prerequisites")
    public void prerequisites() {
        user = DataProvider.getNewUser();
        user2 = DataProvider.getNewUser();

        startupStepdefs
                .openApp()
                .navigateToRegisterForm()
                .registerUser(user)
                .verifySignupSuccessful()
                .registerUser(user2)
                .verifySignupSuccessful();
    }

    @Test
    @Description("Forum Integrations Test")
    public void forumInteractionsTest() {
        Forum forum = DataProvider.getNewForum();
        String replyUser = DataProvider.getMessageContent();
        String replyUser2 = DataProvider.getMessageContent();

        startupStepdefs
                .openApp()
                .logInToApplication(user.getUserName(), user.getPassword());
        navigationStepdefs.navigateToForums();
        forumStepdefs
                .createNewTopic(forum)
                .verifyNewForumCreated()
                .navigateToViewForumsFromCreateForumView()
                .openForumBySubject(forum.getTopicSubject())
                .addReply(replyUser)
                .verifyCommentIsVisible(replyUser)
                .removeReplyByText(replyUser)
                .verifyCommentIsNotVisible(replyUser);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user2.getUserName(), user2.getPassword());
        navigationStepdefs.navigateToForums();
        forumStepdefs
                .navigateToViewForumsFromCreateForumView()
                .openForumBySubject(forum.getTopicSubject())
                .addReply(replyUser2)
                .verifyCommentIsVisible(replyUser2);
        navigationStepdefs.logout();
        startupStepdefs.logInToApplication(user.getUserName(), user.getPassword());
        navigationStepdefs.navigateToForums();
        forumStepdefs
                .navigateToViewForumsFromCreateForumView()
                .openForumBySubject(forum.getTopicSubject())
                .verifyCommentIsVisible(replyUser2);
    }
}
