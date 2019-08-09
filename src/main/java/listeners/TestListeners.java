package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    private final static Logger LOGGER = LogManager.getLogger(TestListeners.class.getName());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("================================== TEST " + iTestResult.getName()
                + " STARTED ==================================");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        teardownTest(iTestResult);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        teardownTest(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info("================================== TEST " + iTestContext.getName().toUpperCase()
                + " STARTED ==================================");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private void teardownTest(ITestResult result) {
        String status = result.isSuccess() ? "SUCCESS" : "FAILURE";
        LOGGER.info("======" + status + "======");
        LOGGER.info("Test: " + result.getInstanceName() + "." + result.getName());

    }
}