package framework.pages;

import framework.utils.WebListUtils;
import framework.utils.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class NavigationBarPage extends WebPage {

    @FindBy(xpath = "//div[@class='collapse navbar-collapse justify-content-right']")
    private WebElement pageContainer;

    @FindBy(xpath = ".//li/a")
    private List<WebElement> navigationOptions;

    public NavigationBarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(pageContainer),this);
    }

    public MessagesHomePage navigateToMessages() {
        clickElement(WebListUtils.getElementFromListByAttributeContains(navigationOptions, "href", "message.php"));
        return new MessagesHomePage(driver).isAt();
    }

    public StartupPage clickLogoutButton() {
        clickElement(WebListUtils.getElementFromListByAttributeContains(navigationOptions, "href", "includes/logout.inc.php"));
        return new StartupPage(driver).isAt();
    }
}
