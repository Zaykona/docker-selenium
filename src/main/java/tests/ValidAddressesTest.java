package tests;

import common.driver.DriverManager;
import db.dao.AddressDao;
import models.ValidAddresses;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.integrivideo.ChatDemoPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static constants.Constants.Application.APP_URL;

public class ValidAddressesTest {

    private RemoteWebDriver driver;

    @DataProvider(name = "emailDataProvider")
    public Iterator<Object[]> readDBData() {
        AddressDao addressDao = new AddressDao();
        List<ValidAddresses> addreses = addressDao.getAddresses();
        Collection<Object[]> data = new ArrayList<Object[]>();
        addreses.forEach(item -> data.add(new Object[]{item}));
        return data.iterator();

    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser, ITestContext context) {
        driver = DriverManager.getDriver(browser);
        context.setAttribute("common/driver", driver);
        driver.get(APP_URL);

    }

    @Test(dataProvider = "emailDataProvider")
    public void checkEmailTest(ValidAddresses address) {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        Assert.assertTrue(chatPage.setEmailInSettings(address.getAddress().trim()),
                String.format("Unable to enter valid address - %s - %s",
                        address.getAddress().trim(), address.getReason()));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        DriverManager.stopDriver();
    }
}

