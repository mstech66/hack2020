package com.epam.framework.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {

    private BrowserFactory() {
    }

    public static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().version("0.24.0").setup();
                return new FirefoxDriver(getDesiredCapabilitiesFor("firefox"));
            case "ie":
                WebDriverManager.iedriver().version("3.1").setup();
                return new InternetExplorerDriver(getDesiredCapabilitiesFor("ie"));
            default:
                WebDriverManager.chromedriver().version("86.0.4240.22").setup();
                return new ChromeDriver(getDesiredCapabilitiesFor("chrome"));
        }
    }

    private static DesiredCapabilities getDesiredCapabilitiesFor(String browser) {
        DesiredCapabilities desiredCapabilities;
        switch (browser) {
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            case "ie":
                desiredCapabilities = DesiredCapabilities.internetExplorer();
                break;
            case "chrome":
            default:
                desiredCapabilities = DesiredCapabilities.chrome();
        }
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return desiredCapabilities;
    }
}
