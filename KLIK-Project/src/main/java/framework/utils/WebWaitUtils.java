package framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebWaitUtils {

    private WebDriver driver;

    public WebWaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForVisible(WebElement webElement, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public List<WebElement> waitForPresenceOfAllElementsBy(By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public Boolean waitForTextToBePresentInElement(WebElement webElement, String text, long timeout) {
         return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public Boolean waitForPageReadyState(long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement waitForClickable(WebElement webElement, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForNotVisible(WebElement webElement, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOf(webElement));
    }
}
