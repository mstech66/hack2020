package com.epam.runner;
import com.epam.utility.BrowserFactory;
import com.epam.utility.extentreport.ExtentReportUtil;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@CucumberOptions(
        strict = true,
        plugin = {
                "json:target/Reports/Selenium/json/cucumber-report.json",
                "html:target/Reports/Selenium/html/cucumber-report"
        },
        features = {"src/test/resources/features/HomePage.feature"},
        glue = {"com.epam.steps", "com.epam.utility.hooks"},
        dryRun = false
)
public class SeleniumRunner extends AbstractTestNGCucumberTests {
    public static WebDriver webDriver;

    @BeforeSuite
    public void setupSuite(){
        ExtentReportUtil.setupExtentReport();
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        webDriver = BrowserFactory.getDriver(browser);
    }

    @AfterClass
    public void terminate() {
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportUtil.flushReport();
    }
}
