package com.epam.framework.webdriver;

import com.epam.framework.utilities.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private static WebDriver webDriver;

    @BeforeSuite
    @Parameters({"browser"})
    public static void setupDriver(String browser) {
        webDriver = BrowserFactory.getDriver(browser);
    }

    @AfterSuite
    public static void terminate() {
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}
