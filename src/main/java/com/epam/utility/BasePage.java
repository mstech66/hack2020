package com.epam.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    private WebDriver webDriver;
    private WebElement webElement;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void get(String url) {
        webDriver.manage().window().maximize();
        webDriver.get(url);
    }

    public WebElement getElement(By selector) {
        this.webElement = webDriver.findElement(selector);
        return webElement;
    }

    public WebElement getElement(WebElement webElement) {
        this.webElement = webElement;
        return webElement;
    }
    
    public void waitUntilElementIsVisibleFor(WebElement selector, long timeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds);
        webDriverWait.until(ExpectedConditions.visibilityOf(selector));
    }

    public void explicitWait(long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}