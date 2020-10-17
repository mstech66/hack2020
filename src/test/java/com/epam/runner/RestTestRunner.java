package com.epam.runner;

import com.epam.utility.extentreport.ExtentReportUtil;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        strict = true,
        plugin = {
                "json:target/Reports/RestAssured/json/cucumber-report.json",
                "html:target/Reports/RestAssured/html/cucumber-report"
        },
        features = {"src/test/resources/features"},
        glue = {"com.epam.steps", "com.epam.utility.hooks"},
        tags = {"@Subcategory"},
        dryRun = false
)
public class RestTestRunner extends AbstractTestNGCucumberTests {
    @BeforeSuite
    public void setupSuite() {
        ExtentReportUtil.setupExtentReport();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportUtil.flushReport();
    }
}
