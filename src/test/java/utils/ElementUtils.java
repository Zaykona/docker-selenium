package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Function;

import static constants.Constants.TimeConstants.TIME_30;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ElementUtils {

    public static void waitForElementDisplayed(WebDriver driver, WebElement el) {
        waitForElementDisplayed(driver, el, TIME_30);
    }

    public static void waitForElementDisplayed(WebDriver driver, WebElement el, int timeInSec) {
        new FluentWait<WebDriver>(driver).withTimeout(timeInSec, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class)
                .withMessage(String.format("Element not visible after %s sec.", timeInSec))
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return el.isDisplayed();
                    }
                });
    }


}
