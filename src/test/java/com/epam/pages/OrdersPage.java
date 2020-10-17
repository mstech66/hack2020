package com.epam.pages;

import com.epam.utility.BasePage;
import com.epam.utility.PageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrdersPage extends BasePage {
    private WebDriver webDriver;
    private final String url = "http://10.71.12.112:8080/getOrders";
    private String productName;
    private int quantity;

    public OrdersPage(WebDriver webDriver, String productName, int quantity) {
        super(webDriver);
        this.webDriver = webDriver;
        this.productName = productName;
        this.quantity = quantity;
        if (!getTitle().equals("Orders")) {
            throw new PageException("Orders page is not loaded");
        }
    }

    public void validateOrder() {
        By itemIdLabel = By.xpath("//td[text()=\"" + productName + "\"]/parent::tr/td[text()=\"" + this.quantity + "\"]");
        int quantity = Integer.parseInt(getElement(itemIdLabel).getText());
        assertThat(quantity, equalTo(this.quantity));
    }
}
