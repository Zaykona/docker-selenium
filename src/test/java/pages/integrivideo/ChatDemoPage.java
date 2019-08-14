package pages.integrivideo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.DriverUtils;
import utils.ElementUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.TimeConstants.TIME_1000;

public class ChatDemoPage extends BasePage {

    final static Logger LOGGER = LogManager.getLogger(ChatDemoPage.class.getName());
    private static List<String> uiChatElementsErrors;

    @FindBy(id = "invite-users-to-chat")
    private WebElement inviteUsersButton;

    @FindBy(id = "integri-component-chat")
    private WebElement chatWindow;

    @FindBy(xpath = "//span[@class='iv-icon iv-icon-user']")
    private WebElement userIcon;

    @FindBy(xpath = "//span[@class='integri-session-user-name']")
    private WebElement userName;

    @FindBy(xpath = "//div[@class='integri-watermark']")
    private WebElement integrityWatermark;

    @FindBy(xpath = "//div[@class='integri-chat-messages']")
    private WebElement messageArea;

    @FindBy(xpath = "//div[@class='integri-chat-input']//textarea")
    private WebElement inputTextArea;

    @FindBy(xpath = "//button[contains(@class, 'integri-chat-send-message')]")
    private WebElement sendButton;

    @FindBy(xpath = "//button[contains(@class, 'integri-chat-start-video')]")
    private WebElement videoButton;

    @FindBy(xpath = "//span[contains(@class, 'integri-chat-settings')]")
    private WebElement settingsButton;

    @FindBy(xpath = "//div[@class='integri-chat-message-text']")
    private List<WebElement> messagesList;

    @FindBy(xpath = "//span[@class='integri-chat-message-date']")
    private List<WebElement> dateList;


    public ChatDemoPage(WebDriver driver) {
        super(driver);
    }

    public void waitChatDisplayed() {
        LOGGER.info("Waiting for chat window displayed.");
        ElementUtils.waitForElementDisplayed(driver, chatWindow);
        ElementUtils.waitForElementDisplayed(driver, integrityWatermark);
    }

    public String validateUiComponents() {
        String error = "";
        error += userName.isDisplayed() ? "" : "User name is not displayed. \n";
        error += userIcon.isDisplayed() ? "" : "User icon is not displayed. \n";
        error += messageArea.isDisplayed() ? "" : "Message area is not displayed. \n";
        error += inputTextArea.isDisplayed() ? "" : "Input text area is not displayed. \n";
        error += sendButton.isDisplayed() ? "" : "Send button is not displayed. \n";
        error += videoButton.isDisplayed() ? "" : "Video button is not displayed. \n";
        error += settingsButton.isDisplayed() ? "" : "Setting button is not displayed. \n";
        return error;
    }

    public void sendMessage(String message) {
        inputTextArea.sendKeys(message);
        sendButton.click();
        DriverUtils.wait(TIME_1000);
    }

    public boolean IsMessageInChatWindow(String message) {
        List<String> requiredMessages = new ArrayList();
        requiredMessages = messagesList.stream().map(el -> el.getText()).filter(el ->
                el.equals(message)).collect(Collectors.toList());
        return !requiredMessages.isEmpty();
    }


    public String getDateOfMessage() {
        return dateList.get(0).getText();
    }
}