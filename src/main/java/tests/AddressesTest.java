package tests;

import db.dao.AddressDao;
import models.Addresses;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.integrivideo.ChatDemoPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AddressesTest extends BaseTest {


    @DataProvider(name = "emailDataProvider")
    public Iterator<Object[]> getInvalidEmails() {
        return getEmails(false);

    }

    @DataProvider(name = "validEmailDataProvider")
    public Iterator<Object[]> getValidEmails() {
        return getEmails(true);

    }

    public Iterator<Object[]> getEmails(boolean isValid) {
        AddressDao addressDao = new AddressDao();
        List<Addresses> addreses = isValid ? addressDao.getValidAddresses()
                : addressDao.getInvalidAddresses();
        Collection<Object[]> data = new ArrayList<Object[]>();
        addreses.forEach(item -> data.add(new Object[]{item}));
        return data.iterator();

    }


    @Test(dataProvider = "emailDataProvider")
    public void checkInvalidEmailTest(Addresses address) {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        Assert.assertFalse(chatPage.setEmailInSettings(address.getAddress().trim()),
                String.format("No error message for invalid email - %s - %s",
                        address.getAddress().trim(), address.getReason()));
    }

    @Test(dataProvider = "validEmailDataProvider")
    public void checkValidEmailTest(Addresses address) {
        ChatDemoPage chatPage = new ChatDemoPage(driver);
        chatPage.waitChatDisplayed();
        Assert.assertTrue(chatPage.setEmailInSettings(address.getAddress().trim()),
                String.format("Unable to set valid email - %s - %s",
                        address.getAddress().trim(), address.getReason()));
    }

}

