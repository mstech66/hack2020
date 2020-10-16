package com.epam.framework.webdriver.ui.pages;

import com.epam.framework.webdriver.PageWrapper;
import com.epam.framework.utilities.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageWrapper {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@ng-model=\"yourName\"]")
    WebElement name;

    @FindBy(xpath = "//input[@ng-model=\"yourName\"]//following-sibling::h1")
    WebElement text;

    private static final String url = ConfigFileReader.getHomepageURL();


    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openPage() {
        get(url);
    }

    public void enterName(String name){
        this.name.sendKeys(name);
    }

    public String getTextAfterEntering(){
        return this.text.getText();
    }
}
