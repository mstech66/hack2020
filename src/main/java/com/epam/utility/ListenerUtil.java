package com.epam.utility;

import com.aventstack.extentreports.gherkin.model.Feature;
import com.epam.utility.extentreport.ExtentHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.epam.utility.extentreport.ExtentHelper.*;

public class ListenerUtil implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LoggerUtil.info("onTestSuccess " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LoggerUtil.startTestCase("Test started for " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LoggerUtil.endTestCase(iTestResult.getName());
        scenarioDefinition.fail(iTestResult.getThrowable().getMessage());
        stepDefinition.fail(iTestResult.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LoggerUtil.info("onTestSkipped " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LoggerUtil.info("onTestFailedButWithinSuccessPercentage " + getTestMethodName(result));
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.endTestCase(context.getName());
        feature = ExtentHelper.extentReports.createTest(Feature.class, context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("onFinish " + context.getName());
    }
}
