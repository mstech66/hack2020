package com.epam.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

public class BrowserFactory {
    public static WebDriver getDriver(String browserName, String driverPath) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverPath);
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", driverPath);
                return new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                return new ChromeDriver();
        }
    }
}
