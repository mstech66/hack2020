package com.epam.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class OrdersSteps {
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private static final String BASE_URI = "http://10.71.12.112:8080/";

    @Given("User wants to get all orders")
    public void userWantsToGetAllOrders() {
        request = given();
    }

    @When("User navigates to orders url")
    public void userNavigatesToOrdersUrl() {
        String url = BASE_URI + "orders";
        response = request.when().get(url);
    }

    @Then("^User gets all the orders$")
    public void userGetsAllTheOrders(List<String> orders) {
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> items = jsonPath.getList("data.product.name");
        assertThat(orders, containsInAnyOrder(items.toArray()));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Given("User wants to place order")
    public void userWantsToPlaceOrder() {
        request = given();
    }

    @When("User navigates to place order url")
    public void userNavigatesToPlaceOrderUrl() {
        String url = BASE_URI + "checkout";
        response = request.when().post(url);
    }

    @Then("Order has been placed")
    public void orderHasBeenPlaced() {
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Then("^User should get the statusCode$")
    public void userShouldGetTheStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

}
