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

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void click() {
        webElement.click();
    }

    public String getAttribute(String attributeName) {
        return webElement.getAttribute(attributeName);
    }

    public void sendKeys(String value) {
        webElement.sendKeys(value);
    }

    public void clearField() {
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public void waitUntilElementIsVisibleFor(By selector, long timeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void waitUntilElementIsVisibleFor(WebElement selector, long timeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeInSeconds);
        webDriverWait.until(ExpectedConditions.visibilityOf(selector));
    }

    public boolean isElementPresent(By selector) {
        return !webDriver.findElements(selector).isEmpty();
    }

    public boolean isElementDisplayed(WebElement selector) {
        return selector.isDisplayed();
    }

    public boolean isValidPageLoaded(String expectedPageTitle) throws PageException {
        boolean isValidPage = webDriver.getTitle().equals(expectedPageTitle);
        if (isValidPage) {
            return isValidPage;
        } else throw new PageException("The expected title doesn't match!");
    }

    public void explicitWait(long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectItem(WebElement dropdown, String value) {
        this.webElement = dropdown;
        click();
        dropdown.findElement(By.cssSelector("li[value=" + value + "]")).click();
    }
}