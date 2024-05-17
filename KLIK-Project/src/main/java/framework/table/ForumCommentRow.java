package framework.table;

import framework.model.ForumComment;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ForumCommentRow extends WebPage {

    @FindBy(xpath = ".//div[@class='col-sm-9 post-content']/p")
    private WebElement content;

    @FindBy(xpath = ".//i[contains(@class, 'fa fa-trash')]")
    private WebElement trash;

    public ForumCommentRow(WebDriver driver, WebElement wrapper) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(wrapper), this);
    }

    public ForumComment toModel() {
        return ForumComment.builder()
                .content(content.getText())
                .deleteButton(trash)
                .build();
    }
}
