package com.epam.pages;

import com.epam.utility.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private WebDriver webDriver;
    private final String url = "https://www.covid19india.org/";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void openPage() {
        get(url);
    }



}
