package com.epam.pages;

import com.epam.utility.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends BasePage {

    private WebDriver webDriver;
    private final String url = "http://10.71.12.112:8080/getMainCategories";

    public CategoryPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void openPage() {
        get(url);
    }

    public SubcategoryPage selectCategory(String category) {
        System.out.println("Category is " + category);
        By categoryLink = By.xpath("//a[text()=\" " + category + "\"]");
        waitUntilElementIsVisibleFor(categoryLink, 10);
        getElement(categoryLink);
        click();
        return new SubcategoryPage(webDriver);
    }

}
