package com.epam.framework.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.epam.framework.webdriver.BaseTest;
import com.epam.framework.constants.ReportConstants;
import com.epam.framework.utilities.ConfigFileReader;
import com.epam.framework.utilities.ExtentManager;
import com.epam.framework.utilities.LoggerUtils;
import com.epam.framework.constants.LoggerConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

public class TestListener implements ITestListener {

	private static final ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + " :: " + ReportConstants.getTestMethodName(result));
		extentTest.set(test);
		LoggerUtils.startTestCase(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, ReportConstants.reportsMarkupHelper(result, ExtentColor.GREEN));
		LoggerUtils.info(LoggerConstants.SUCCESS_LOG_MESSAGE, ReportConstants.getTestMethodName(result));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(ReportConstants.onTestFailureDetailedMessage(result));
		String path = takeScreenShot(BaseTest.getWebDriver(), ReportConstants.getTestMethodName(result));
		try {
			extentTest.get().fail(ReportConstants.VIEW_SCREENSHOT_MESSAGE,
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			extentTest.get().fail(ReportConstants.VIEW_SCREENSHOT_ERROR_MESSAGE);
		}
		extentTest.get().log(Status.FAIL, ReportConstants.reportsMarkupHelper(result, ExtentColor.RED));
		LoggerUtils.error(LoggerConstants.FAILED_LOG_MESSAGE, ReportConstants.getTestMethodName(result));
		LoggerUtils.error(Arrays.toString(result.getThrowable().getStackTrace()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, ReportConstants.reportsMarkupHelper(result, ExtentColor.YELLOW));
		LoggerUtils.info(LoggerConstants.SKIPPED_LOG_MESSAGE, ReportConstants.getTestMethodName(result));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LoggerUtils.warn(LoggerConstants.FAIL_WITH_SUCCESS_PERCENT_LOG_MESSAGE,
				ReportConstants.getTestMethodName(result));
	}

	@Override
	public void onStart(ITestContext context) {
		LoggerUtils.info(LoggerConstants.TEST_EXECUTION_START, context.getClass());
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null)
			extent.flush();
		LoggerUtils.info(LoggerConstants.TEST_EXECUTION_ENDED, context.getClass());
	}

	public String takeScreenShot(WebDriver driver, String methodName) {
		String fileName = ReportConstants.getScreenShotName(methodName);
		String directory = System.getProperty("user.dir") + ConfigFileReader.getScreenshotsDirectoryPath();
		new File(directory);
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			LoggerUtils.info(LoggerConstants.SCREENSHOT_SAVED_MESSAGE, path);
		} catch (Exception e) {
			LoggerUtils.error(LoggerConstants.SCREENSHOT_ERROR_MESSAGE);
		}
		return path;
	}

}
