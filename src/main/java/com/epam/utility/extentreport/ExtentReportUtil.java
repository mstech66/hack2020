package com.epam.utility.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.epam.utility.ConfigFileReader;

public class ExtentReportUtil extends ExtentHelper {
    private static final ConfigFileReader configFileReader = new ConfigFileReader();

    public static void setupExtentReport() {
        extentReports = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(configFileReader.getExtentReportPath());
        extentHtmlReporter.config().setDocumentTitle("eCommerce Project");
        extentHtmlReporter.config().setReportName("eCommerce Report");
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentHtmlReporter.config().setEncoding("utf-8");
        extentReports.attachReporter(extentHtmlReporter);
    }

    public static void flushReport(){
        extentReports.flush();
    }
}
