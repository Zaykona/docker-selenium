package common.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.Constants.Browser.CHROME;
import static constants.Constants.Browser.FIREFOX;

public class DriverFactory {

    private static final String HUB_URL = "http://127.0.0.1:4444/wd/hub";
    private static RemoteWebDriver driver;
    private static  ThreadLocal<AbstractDriverOptions> options = new ThreadLocal<>();;

    protected static RemoteWebDriver createDriver(String browser) {
        switch (browser) {
            case FIREFOX:
                options.set(new FirefoxOptions());
                break;
            case CHROME:
            default:
                options.set(new ChromeOptions());
                break;
        }
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), options.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

