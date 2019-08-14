package tests.ui;

import driver.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.integrivideo.ChatDemoPage;

import static constants.Constants.Application.APP_URL;
import static org.testng.Assert.assertTrue;

public class UiTests {
    final static Logger LOGGER = LogManager.getLogger(TestListeners.class.getName());
    private static final String TEST_MESSAGE = "It's test message #1";

    private RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser, ITestContext context) {
        driver = new DriverManager().getDriver(browser);
        context.setAttribute("driver", driver);
        driver.get(APP_URL);

    }

    @Description("Check ui elements presents on chat window.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void uiDisplayedTest() {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.checkChatDisplayed();
        String uiComponentsErrors = chatPage.validateUiComponents();
        assertTrue(uiComponentsErrors.isEmpty(), uiComponentsErrors);

    }

    @Description("Check send message displayed in chat area.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkSentMessageDisplayed() {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.checkChatDisplayed();
        chatPage.sendMessage(TEST_MESSAGE);
        assertTrue(chatPage.IsMessageInChatWindow(TEST_MESSAGE),
                String.format("There are no message - %s - in chat window.", TEST_MESSAGE));

    }

    @AfterMethod
    public void tearDown() throws Exception {
        DriverManager.stopDriver();
    }
}