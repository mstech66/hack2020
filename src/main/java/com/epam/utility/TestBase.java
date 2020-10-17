package com.epam.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class TestBase {
    private WebDriver webDriver;
    public static ConfigFileReader configFileReader;

//    @Before
    public void setup() {
        configFileReader = new ConfigFileReader();
        String browser = configFileReader.getBrowser();
        String driverPath = configFileReader.getDriverPath();
        webDriver = BrowserFactory.getDriver(browser, driverPath);
    }

//    @After
    public void terminate() {
        webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
