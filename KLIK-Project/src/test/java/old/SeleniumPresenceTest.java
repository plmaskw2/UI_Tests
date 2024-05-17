package old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Disabled
public class SeleniumPresenceTest {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.popups", 1);
        prefs.put("intl.accept_languages", "en-US");
        options.addArguments("--lang=en-US");
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void seleniumRegistrationFormTest() {
        String avatarPath = System.getProperty("user.dir") + "\\src\\test\\java\\data_provider\\pictures\\avatar.png";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String url = "http://localhost/index.php";

        driver.get(url);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='login-submit']"))));

        driver.findElement(By.xpath("//a[@href='signup.php']")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("headline"))));

        driver.findElement(By.id("name")).sendKeys("TestUsername");
        driver.findElement(By.id("email")).sendKeys("email@test.com");
        driver.findElement(By.id("pwd")).sendKeys("password");
        driver.findElement(By.id("pwd-repeat")).sendKeys("password");
        driver.findElement(By.id("f-name")).sendKeys("TestFirstName");
        driver.findElement(By.id("l-name")).sendKeys("TestLastName");
        driver.findElement(By.id("headline")).sendKeys("TestBio");
        driver.findElement(By.id("headline")).sendKeys("Test some description");
        driver.findElement(By.id("imgInp")).sendKeys(avatarPath);
        driver.findElement(By.xpath("//label[.='F']")).click();
    }

    @Test
    public void startupPageFieldsValidationTest() {
        driver.get("http://localhost/index.php");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='login-submit']"))));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(driver.findElement(By.xpath("//input[@placeholder='Username']")).isDisplayed()).isTrue();
        softAssertions.assertThat(driver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed()).isTrue();
        softAssertions.assertThat(driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed()).isTrue();
        softAssertions.assertThat(driver.findElement(By.xpath("//input[@name='login-submit']")).isDisplayed()).isTrue();
        softAssertions.assertThat(driver.findElement(By.xpath("//i[@class='fa fa-envelope fa-2x social-icon']")).isDisplayed()).isTrue();
        softAssertions.assertThat(driver.findElement(By.xpath("//i[@class='fa fa-github fa-2x social-icon']")).isDisplayed()).isTrue();
        softAssertions.assertAll();
    }

}
