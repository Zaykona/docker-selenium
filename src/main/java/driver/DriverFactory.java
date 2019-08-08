package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.Constants.Browser.CHROME;
import static constants.Constants.Browser.FIREFOX;

public class DriverFactory {

    private static final String HUB_URL = "http://127.0.0.1:4444/wd/hub";
    private static RemoteWebDriver driver;
    private static ThreadLocal<DesiredCapabilities> capabilities = new ThreadLocal<>();


    protected static RemoteWebDriver createDriver(String browser) {
        switch (browser) {
            case FIREFOX:
                capabilities.set(DesiredCapabilities.firefox());
                break;
            case CHROME:
            default:
                capabilities.set(DesiredCapabilities.chrome());
                break;
        }
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        capabilities.get().setPlatform(Platform.LINUX);
        capabilities.get().setVersion("");
        return driver;
    }
}

