package com.epam.pages;

import com.epam.utility.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubcategoryPage extends BasePage {
    private WebDriver webDriver;
    private final String url = "http://10.71.12.112:8080/getSubCategories";

    public SubcategoryPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public ProductPage selectSubcategory(String subcategory) {
        By subcategoryLink = By.xpath("//a[text()=\" " + subcategory + "\"]");
        waitUntilElementIsVisibleFor(subcategoryLink, 10);
        getElement(subcategoryLink);
        click();
        return new ProductPage(webDriver);
    }

}
