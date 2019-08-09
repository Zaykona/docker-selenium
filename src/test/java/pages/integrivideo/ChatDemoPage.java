package pages.integrivideo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ChatDemoPage extends BasePage {

    @FindBy(id = "invite-users-to-chat")
    private WebElement inviteUsersButton;

    public ChatDemoPage(WebDriver driver) {
        super(driver);
    }

}