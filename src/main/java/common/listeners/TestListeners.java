package common.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import reporting.AllureManager;

public class TestListeners implements ITestListener, IInvokedMethodListener {

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
        AllureManager.attachScreenshot((WebDriver) iTestResult.getTestContext().
                getAttribute("driver"));
        teardownTest(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        teardownTest(iTestResult);
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


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }
}