package tests;

import driver.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static constants.Constants.Application.APP_URL;

public class DockerSeleniumExampleTest {

    final static Logger LOGGER = LogManager.getLogger(TestListeners.class.getName());

    private RemoteWebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        driver = new DriverManager().getDriver(browser);
    }

    @Description("Open page test")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void openPage() {
        driver.get(APP_URL);
        LOGGER.info(driver.getCurrentUrl());

    }

    @AfterMethod
    public void tearDown() throws Exception {
        DriverManager.stopDriver();
    }
}


