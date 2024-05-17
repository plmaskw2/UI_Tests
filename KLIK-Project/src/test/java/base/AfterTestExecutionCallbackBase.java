package base;

import framework.utils.ConfigurationUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AfterTestExecutionCallbackBase implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = ((WebBaseTestJUnit) testInstance).driver;
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(driver);
        }
        if (ConfigurationUtils.isRemote) {
            attachVideo(sessionId);
        }
        driver.quit();
    }

    public static void attachVideo(SessionId sessionId) {
        String htmlContent = String.format("<html><body><video width=\"320\" height=\"240\" controls><source src=\"%s/video/%s.mp4\" type=\"video/mp4\"></video></body></html>", ConfigurationUtils.SELENOID_HUB, sessionId);
        byte[] htmlBytes = htmlContent.getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(htmlBytes);
        Allure.addAttachment("Video", inputStream);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
