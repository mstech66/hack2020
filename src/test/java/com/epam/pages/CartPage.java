package com.epam.pages;

import com.epam.utility.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CartPage extends BasePage {
    private WebDriver webDriver;
    @FindBy(xpath = "//input[@value=\"Checkout\"]")
    WebElement checkoutButton;
    private String productName;
    private int quantity;
    AjaxElementLocatorFactory ajaxElementLocatorFactory;

    public CartPage(WebDriver webDriver, String productName, int quantity) {
        super(webDriver);
        this.webDriver = webDriver;
        this.productName = productName;
        this.quantity = quantity;
        this.ajaxElementLocatorFactory = new AjaxElementLocatorFactory(webDriver, 20);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
    }

    public OrdersPage checkout() {
        getElement(checkoutButton);
        click();
        return new OrdersPage(webDriver, productName, quantity);
    }
}
