package com;

import org.testng.ITestListener ;
import org.testng.ITestResult ;

public class Listener extends TestBase implements ITestListener{

    // When Test case get failed with timeout, this method is called.
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.info("************ " + result.getName() + " ************ test failed with timeout");
    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("************ " +result.getName() + " ************ test failed");
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("************ " +result.getName() + " ************ test skipped");
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("************ " +result.getName() + " ************ test started");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("************ " +result.getName() + " ************ test passed");
    }
}