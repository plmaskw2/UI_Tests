package framework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.WebElement;

@Getter
@Builder
@ToString
public class Message {
    private String username;
    private WebElement usernameLink;
}
