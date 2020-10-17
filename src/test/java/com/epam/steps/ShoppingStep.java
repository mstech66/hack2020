package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.model.Product;
import com.epam.pages.*;
import com.epam.runner.SeleniumRunner;
import com.epam.utility.DatabaseHelper;
import com.epam.utility.extentreport.ExtentHelper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingStep extends ExtentHelper {
    HomePage homePage;
    SubcategoryPage subcategoryPage;
    ProductPage productPage;
    CartPage cartPage;
    OrdersPage ordersPage;
    private List<Product> productList;

    @DataTableType
    public Product productEntry(Map<String, String> entry) {
        return new Product(
                entry.get("category"),
                entry.get("subcategory"),
                entry.get("product"),
                parseInt(entry.get("quantity"))
        );
    }

    @When("^User selects the category and chooses the subcategory and adds the product with quantity to the cart$")
    public void userSelectsTheCategoryAndChoosesTheSubcategoryAndAddsTheProductWithQuantityToTheCart(List<Product> products) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User selects the category and chooses the subcategory and adds the product with quantity to the cart");
        productList = new ArrayList<>();
        productList.addAll(products);
        for (Product product : products) {
            homePage = new HomePage(SeleniumRunner.getWebDriver());
            homePage.openPage();
            subcategoryPage = homePage.selectCategory(product.getCategory());
            productPage = subcategoryPage.selectSubcategory(product.getSubcategory());
            productPage.bargainProduct(product.getProduct(), product.getQuantity());
        }
        cartPage = productPage.navigateToCartPage();
    }

    @And("User checkout the cart")
    public void userCheckoutTheCart() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("And"), "User checkout the cart");
        ordersPage = cartPage.checkout();
    }

    @Then("Cart should be empty")
    public void cartShouldBeEmpty() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "Cart should be empty");
        assertThat(DatabaseHelper.isCartEmpty(), is(equalTo(true)));
    }


    @And("Order should be placed")
    public void orderShouldBePlaced() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("And"), "Order should be placed");
        ordersPage.validateOrder();
        for (Product product : productList) {
            assertThat(DatabaseHelper.isProductCheckedOut(product.getProduct(), product.getQuantity()), is(equalTo(true)));
        }
    }
}
