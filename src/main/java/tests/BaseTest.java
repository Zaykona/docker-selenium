package tests;

import common.driver.DriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static constants.Constants.Application.APP_URL;

public class BaseTest {

    protected RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser, ITestContext context) {
        driver = new DriverManager().getDriver(browser);
        context.setAttribute("driver", driver);
        driver.get(APP_URL);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.stopDriver();
    }

}
