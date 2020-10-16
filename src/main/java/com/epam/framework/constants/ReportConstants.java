package com.epam.framework.constants;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.testng.ITestResult;

public class ReportConstants {

	public static final String REPORT_FILE_NAME = getReportName();
	public static final String REPORT_TITLE = "RD-FP-AUTO-REPORT (" + generateTimeStamp() + ")";
	public static final String REPORT_NAME = "RD-FP-PORTAL (" + generateTimeStamp() + ")";
	public static final String ORGANIZATION = "EPAM";
	public static final String BROWSER_USER = "Chrome";
	public static final Theme THEME = Theme.DARK;
	public static final String ENCODING_SCHEME = "utf-8";

	public static final String TEST_METHOD = "<b> Test Method ";
	public static final String TEST_SUCCESSFUL = " Successful </b>";
	public static final String TEST_FAILED = " Failed </b>";
	public static final String TEST_SKIPPED = " Skipped </b>";
	public static final String VIEW_SCREENSHOT_MESSAGE = "<b><font color=red>"
			+ "Screenshot of failure, click below to view :-" + "</font></b>";
	public static final String VIEW_SCREENSHOT_ERROR_MESSAGE = "<b><font color=red>"
			+ "Test Failed, Cannot attach screenshot" + "</font></b>";

	public static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static String onTestFailureExceptionMessage(ITestResult result) {
		String exceptionMessage = result.getThrowable().getMessage() == null ? result.getThrowable().toString()
				: result.getThrowable().getMessage();
		return exceptionMessage.replace(",", "<br>");
	}

	public static String onTestFailureDetailedMessage(ITestResult result) {
		return "<details><summary><b><font color=red>" + "Test Case Failed, Click to see the details:- "
				+ "</font></b></summary>" + onTestFailureExceptionMessage(result) + "</details> \n";
	}

	public static final Markup reportsMarkupHelper(ITestResult result, ExtentColor color) {
		String logText = "";
		if (color == ExtentColor.GREEN)
			logText = TEST_METHOD + getTestMethodName(result) + TEST_SUCCESSFUL;
		else if (color == ExtentColor.YELLOW)
			logText = TEST_METHOD + getTestMethodName(result) + TEST_SKIPPED;
		else if (color == ExtentColor.RED)
			logText = TEST_METHOD + getTestMethodName(result) + TEST_FAILED;
		return MarkupHelper.createLabel(logText, color);
	}

	public static String getReportName() {
		String date = generateTimeStamp().replace(" ", "T");
		return "RD-FP-AUTO-REPORT_" + date + ".html";
	}

	public static String generateTimeStamp() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		return dateTime.format(dateTimeFormatter);
	}

	public static String getScreenShotName(String methodName) {
		Date date = new Date();
		return methodName + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
	}

}
