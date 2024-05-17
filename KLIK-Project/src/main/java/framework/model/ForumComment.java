package framework.model;


import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Builder
@Getter
public class ForumComment {
    private String content;
    private WebElement deleteButton;
}
