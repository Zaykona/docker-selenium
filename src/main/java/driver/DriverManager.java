package driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();

    public static RemoteWebDriver getDriver(String browser) {
        if (webDriver.get() == null) {
            webDriver.set(DriverFactory.createDriver(browser));
        }
        return webDriver.get();
    }

    public static void stopDriver() {
        webDriver.get().quit();
        webDriver.set(null);
    }
}