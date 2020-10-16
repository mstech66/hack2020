package com.epam.framework.constants;

import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class LoggerConstants {
	public static final String FRAMEWORK_LOGGER = " Framework log : ";
	public static final String FRAMEWORK_EXCEPTION = "Framework Exception : ";
	public static final String TEST_EXECUTION_START = "Execution of the class : {} started";
	public static final String TEST_EXECUTION_ENDED = "Execution of the class : {} ended";
	public static final String SUCCESS_LOG_MESSAGE = "onTestSuccess: {}";
	public static final String FAILED_LOG_MESSAGE = "onTestFailure: {}";
	public static final String SKIPPED_LOG_MESSAGE = "onTestSkipped: {}";
	public static final String FAIL_WITH_SUCCESS_PERCENT_LOG_MESSAGE = "{} failed but within percentage";
	public static final String SCREENSHOT_SAVED_MESSAGE = "Screenshot is taken successfully and saved at {}";
	public static final String SCREENSHOT_ERROR_MESSAGE = "Exception occurred while taking screenshot!";
	
}
