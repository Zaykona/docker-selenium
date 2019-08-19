package tests.ui;

import common.driver.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.integrivideo.ChatDemoPage;
import utils.DateUtils;

import static constants.Constants.Application.APP_URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UiTests {

    private static final String TEST_MESSAGE = "It's test message #1";

    private RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser, ITestContext context) {
        driver = new DriverManager().getDriver(browser);
        context.setAttribute("common/driver", driver);
        driver.get(APP_URL);

    }

    @Description("Check ui elements presents on chat window.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void uiDisplayedTest() {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        String uiComponentsErrors = chatPage.validateUiComponents();
        assertTrue(uiComponentsErrors.isEmpty(), uiComponentsErrors);


    }

    @Description("Check send message displayed in chat area.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkSentMessageDisplayed() {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        chatPage.sendMessage(TEST_MESSAGE);
        assertTrue(chatPage.IsMessageInChatWindow(TEST_MESSAGE),
                String.format("There are no message - %s - in chat window.", TEST_MESSAGE));

    }


    @Description("Check send message date display correctly.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkMessageDate() {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        chatPage.sendMessage(TEST_MESSAGE);
        assertEquals(chatPage.getDateOfMessage(), DateUtils.getCurrentDateAsString("UTC"),
                "Uncorrect message time.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()  {
        DriverManager.stopDriver();
    }
}