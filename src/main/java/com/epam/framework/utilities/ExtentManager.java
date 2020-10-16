package com.epam.framework.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.epam.framework.constants.ReportConstants;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static ExtentReports createInstance() {
        String reportsDirectory = ConfigFileReader.getExtentReportsDirectoryPath();
        new File(reportsDirectory);
        String path = reportsDirectory + ReportConstants.REPORT_FILE_NAME;
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setEncoding(ReportConstants.ENCODING_SCHEME);
        htmlReporter.config().setReportName(ReportConstants.REPORT_NAME);
        htmlReporter.config().setDocumentTitle(ReportConstants.REPORT_TITLE);
        htmlReporter.config().setTheme(ReportConstants.THEME);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization", ReportConstants.ORGANIZATION);
        extent.setSystemInfo("Browser", ReportConstants.BROWSER_USER);
        extent.setSystemInfo("Application URL", ConfigFileReader.getHomepageURL());
        extent.attachReporter(htmlReporter);
        return extent;
    }
}
