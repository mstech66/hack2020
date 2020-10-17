package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static com.epam.utility.extentreport.ExtentHelper.scenarioDefinition;
import static com.epam.utility.extentreport.ExtentHelper.stepDefinition;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class CartSteps {
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private final String BASE_URI = "http://10.71.12.112:8080/cart/";

    @Given("User wants to get all products from cart")
    public void userWantsToGetAllProductsFromCart() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get all products from cart");
        request = given();
    }

    @When("User navigates to cart url")
    public void userNavigatesToCartUrl() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User navigates to cart url");
        response = request.when().get(BASE_URI);
    }

    @Then("^User gets the cart items$")
    public void userGetsTheListOfProducts(List<String> products) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the cart item");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> items = jsonPath.getList("data.product.name");
        assertThat(products, containsInAnyOrder(items.toArray()));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Given("User wants to get specific item from cart")
    public void userWantsToGetSpecificItemFromCart() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get specific item from cart");
        request = given();
    }

    @When("User provides the {int}")
    public void userProvidesTheCartItemId(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the cartItemId");
        String url = BASE_URI + id;
        response = request.when().put(url);
    }

    @When("User provides the {int} for cartItem")
    public void userProvidesTheCartItemIdForCartItem(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the cartItemId");
        String url = BASE_URI + id;
        response = request.when().get(url);
    }

    @Then("User gets the specific {string}")
    public void userGetsTheSpecificCartItem(String product) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the specific cartItem");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        String item = jsonPath.getString("data.product.name");
        assertThat(item, equalTo(product));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Given("User wants to update cartItem {int}")
    public void userWantsToUpdateCartItemQuantity(int quantity) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to update cartItem quantity");
        request = given().queryParam("quantity", quantity);
    }

    @When("^User provides the specific cartItem id$")
    public void userProvidesTheSpecificCartItemId(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the specific cartItemId");
        String url = BASE_URI + id;
        response = request.when().put(url);
    }

    @Then("User gets updated cartItem {int}")
    public void userGetsUpdatedCartItemQuantity(int quantity) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets updated cartItem quantity");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        int item = jsonPath.getInt("data.quantity");
        assertThat(quantity, equalTo(item));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Given("User wants to delete a cartItem")
    public void userWantsToDeleteCartItem() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to delete a cartItem");
        request = given();
    }

    @When("User provides the {int} to be deleted")
    public void userProvidesTheCartItemToBeDeleted(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the cartItemId to be deleted");
        String url = BASE_URI + id;
        response = request.when().delete(url);
    }

    @Then("User gets the updated cart with deleted {int}")
    public void userGetsUpdatedCartWithDeletedCartItem(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the updated cart with deleted cartItem id");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        int item = jsonPath.getInt("data.id");
        assertThat(id, equalTo(item));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Given("User wants to add a product to the cart")
    public void userWantsToAddAProductToTheCart() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to add a product to the cart");
        request = given();
    }

    @Then("User gets the updated cart with new {int}")
    public void userGetsTheUpdatedCartWithNewProduct(int id) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the updated cart with new productId");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        int item = jsonPath.getInt("data.product.id");
        assertThat(item, equalTo(id));
        response.then().assertThat().statusCode(201).body("message", equalTo("Successfull"));
    }

    @Then("User should get the {int}")
    public void userShouldGetTheStatusCode(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User should get the statusCode");
        response.then().assertThat().statusCode(statusCode);
    }

    @When("User provides the {int} and {int}")
    public void userProvidesTheProductidAndQuantity(int productId, int quantity) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the productId and quantity");
        response = request.queryParam("productId", productId).queryParam("quantity", quantity).when().post(BASE_URI);
    }

    @And("^User should get the message$")
    public void userShouldGetTheMessage(String message) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("And"), "User should get the message");
        response.then().assertThat().body("message", equalTo(message));
    }

    @Then("^User gets the statusCode$")
    public void userGetsTheStatusCode(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode");
        response.then().assertThat().statusCode(statusCode);
    }

    @When("User provides the invalid {int}")
    public void userProvidesTheInvalidCartItemId(int cartItemId) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the invalid cartItemId");
        String url = BASE_URI + cartItemId;
        response = request.when().get(url);
    }
}
