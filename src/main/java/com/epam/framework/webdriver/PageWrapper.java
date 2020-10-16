
package com.epam.framework.webdriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public abstract class PageWrapper {
	public WebDriver webDriver;
	private WebElement webElement;
	private WebDriverWait webDriverWait;
	public JavascriptExecutor javascriptExecutor;
	private FluentWait<WebDriver> fluentWait;

	  @FindBy(xpath = "//button[@class='btn btn-primary bootbox-accept']")  WebElement autoSaveAlertOkButton;
	  @FindBy(xpath = "//input[@type='search']")  WebElement searchfield;
      @FindBy(xpath = "//td[@data-dt-row='0']/a[1]")  WebElement editButton;                   
      @FindBy(xpath = "//td[@data-dt-row='0']/a[2]")  WebElement deleteBtn;
	  @FindBy(xpath = "//a[@href='/Employee/GeneralData/Index']") WebElement employeeTab;
	   	
	public PageWrapper(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriverWait = new WebDriverWait(webDriver, 10);
		javascriptExecutor = (JavascriptExecutor) webDriver;
		fluentWait = new FluentWait<WebDriver>(webDriver).withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
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

	public void jsclick(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].click();", element);
	}

	public void sendKeys(Keys key) {
		webElement.sendKeys(key);
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

	public void waitUntilElementIsVisibleFor(By selector, long timeInMilliSeconds) {
		webDriverWait = new WebDriverWait(webDriver,timeInMilliSeconds);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}

	public void waitUntilElementIsVisibleFor(WebElement selector, long timeInSeconds) {
		webDriverWait = new WebDriverWait(webDriver,timeInSeconds);
		webDriverWait.until(ExpectedConditions.visibilityOf(selector));
	}

	public void waitUntilClickable(WebElement element, long timeInSeconds) {
		webDriverWait = new WebDriverWait(webDriver,timeInSeconds);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilPageIsFullyLoaded( long timeInSeconds) {
		webDriverWait = new WebDriverWait(webDriver,timeInSeconds);
		webDriverWait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}

	public boolean isElementPresent(By selector) {
		return !webDriver.findElements(selector).isEmpty();
	}

	public boolean isElementDisplayed(WebElement selector) {
		return selector.isDisplayed();
	}

	public void implicitWait(long timeInMs) {
		webDriver.manage().timeouts().implicitlyWait(timeInMs, TimeUnit.MILLISECONDS);
	}

	public void fluentWait(final String xpath, int timeInSeconds) {
		fluentWait.withTimeout(timeInSeconds, TimeUnit.SECONDS);
		fluentWait.until(driver -> driver.findElement(By.xpath(xpath)));
	}

	public void fluentWait(WebElement webElement, int timeInSeconds) {
		fluentWait.withTimeout(timeInSeconds, TimeUnit.SECONDS);
		fluentWait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void selectItemFromDropdown(WebElement dropdown) throws InterruptedException {
        this.webElement = dropdown;
        Thread.sleep(1000);
        click();
       // dropdown.findElement(By.cssSelector("li[value=" + value + "]")).click();
    }

	public void scrollInView(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
		javascriptExecutor.executeScript("window.scrollBy(0,-80)"); 
	}

	public void clickElementAndHighlight(WebElement element) {
		javascriptExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
		element.click();
	}

	public void selectDate(String dateString, String monthString, String yearString) {
		// First click on the specific date element then call this function.
		// Format Example ("23","May","2000").

		By date = By.xpath("//div[@id='ui-datepicker-div']//a[@class='ui-state-default' and contains(text(),'"+dateString+"')]");
    	By month = By.xpath("//select[@class='ui-datepicker-month']//option[contains(text(),'"+monthString+"')]");
    	By year = By.xpath("//div[@id='ui-datepicker-div']//option[@value='"+yearString+"']");
    	webDriver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();
    	webDriver.findElement(year).click();
    	webDriver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();
    	webDriver.findElement(month).click();
    	webDriver.findElement(date).click();
	}

	public void selectFromList(WebElement parentElement, String value) {
		// Pass the element and value to select form the element.

		parentElement.click();
		parentElement.findElement(By.xpath(".//option[contains(text(),'" + value + "')]")).click();
	}

	public void highlightElement(WebElement element) {
    	javascriptExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void clickElement(WebElement element) {
              element.click();
    }

    public void selectElement(WebElement element, String value) {
    	Select items = new Select(element);
		items.selectByVisibleText(value);
	}

    public void checkForAutoSaveAlert() {
		By autoSaveAlert = By.xpath("//h5[contains(text(),'AutoSave Alert')]");
		if(isElementPresent(autoSaveAlert)) {
			autoSaveAlertOkButton.click();
		}
	}

    public void goToEmployeesTab() {
		highlightElement(employeeTab);
		clickElement(employeeTab);
		waitUntilPageIsFullyLoaded(5000);
	}
    public void preRequirementToGOToEmployee(String employeeName) throws InterruptedException {
		highlightElement(employeeTab);clickElement(employeeTab);
		waitUntilPageIsFullyLoaded(5000);
		waitUntilElementIsVisibleFor(searchfield,5000);
		searchfield.clear();
		implicitWait(1000);highlightElement(searchfield);
		searchfield.sendKeys(employeeName);Thread.sleep(1000);highlightElement(editButton);
		waitUntilElementIsVisibleFor(editButton,3000);clickElement(editButton);
		checkForAutoSaveAlert();
	}
    
    public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

}
