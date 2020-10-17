package com.epam.pages;

import com.epam.utility.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    private WebDriver webDriver;
    private String productName;
    private int quantity;
    private final String url = "http://10.71.12.112:8080/getSubCategories?categoryId=3";
    @FindBy(xpath = "//input[@name=\"quantity\"]")
    WebElement quantityInput;
    @FindBy(xpath = "//input[@value=\"AddToCart\"]")
    WebElement addToCartButton;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void selectProduct(String productName) {
        this.productName = productName;
        By product = By.xpath("//td[text()=\"" + productName + "\"]//parent::tr//input");
        waitUntilElementIsVisibleFor(product, 10);
        getElement(product);
        click();
    }

    public void setQuantity(int quantity) {
        getElement(quantityInput);
        this.quantity = quantity;
        sendKeys(Integer.toString(quantity));
    }

    public void addToCart() {
        getElement(addToCartButton);
        click();
    }

    public void bargainProduct(String productName, int quantity) {
        selectProduct(productName);
        setQuantity(quantity);
        addToCart();
    }

    public CartPage navigateToCartPage() {
        By cartLink = By.xpath("//a[text()=\" Cart\"]");
        getElement(cartLink);
        click();
        return new CartPage(webDriver, productName, quantity);
    }
}
