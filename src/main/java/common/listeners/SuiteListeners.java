package common.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners implements ISuiteListener {

    private final static Logger LOGGER = LogManager.getLogger(TestListeners.class.getName());


    @Override
    public void onStart(ISuite suite) {
        LOGGER.info("========SUITE START========");
    }

    @Override
    public void onFinish(ISuite suite) {
        LOGGER.info("========SUITE FINISH========");
    }
}
