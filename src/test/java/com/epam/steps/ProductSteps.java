package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.utility.DatabaseHelper;
import com.epam.utility.extentreport.ExtentHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ProductSteps extends ExtentHelper {
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private static final String BASE_URI = "http://10.71.12.112:8080/";
    private static final String contentType = "application/json";
    private int subcategoryId;
    private int productId;

    @Given("User wants to get all products for a subcategory")
    public void userWantsToGetAllProductsForASubcategory() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get all products for a subcategory");
        request = given();
    }

    @When("User navigates to product url by {int}")
    public void userNavigatesToProductUrl(int subcategoryId) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User navigates to product url by subcategoryId");
        String url = BASE_URI + "subcategory/" + subcategoryId + "/products";
        response = request.when().get(url);
    }

    @Then("User retrieves the list of {string}")
    public void userGetsTheListOfProducts(String products) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User retrieves the list of products");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> items = jsonPath.getList("data.name");
        List<String> productsList = Arrays.asList(products.split(","));
        assertThat(productsList, containsInAnyOrder(items.toArray()));
        response.then().assertThat().statusCode(200).body("message", equalTo("successful"));
    }

    @Then("User gets the {int} for invalid product operation")
    public void userGetsTheStatusCodeForInvalidProductOperation(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode for invalid operation");
        response.then().assertThat().statusCode(statusCode);
    }

    @And("{string} is received while requesting products")
    public void errorMessageIsReceivedWhileRequestingProducts(String message) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("And"), "Message is received while requesting products");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        String jsonMessage = jsonPath.getString("message");
        assertThat(jsonMessage, equalTo(message));
    }

    @Given("User specifies the {string}")
    public void userSpecifiesTheSubcategory(String subcategoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies the subcategoryName");
        subcategoryId = DatabaseHelper.getSubcategoryId(subcategoryName);
        request = given().contentType(contentType);
    }


    @When("User provides the {string} {int} and {double}")
    public void userProvidesTheNameQuantityAndPrice(String productName, int quantity, double price) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the productName quantity and price");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("price", price);
        jsonObject.put("quantity", quantity);
        jsonObject.put("subCategoryId", subcategoryId);
        String jsonString = jsonObject.toString();
        String url = BASE_URI + "product";
        response = request.body(jsonString).when().post(url);
    }

    @Then("User gets the {int} in response")
    public void userGetsTheStatuscodeInResponse(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode in response");
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("User specifies the {string} which doesn't exist")
    public void userSpecifiesTheSubcategoryWhichDoesnTExist(String subcategoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies the subcategoryName which doesn't exist");
        subcategoryId = 0;
        request = given().contentType(contentType);
    }

    @Given("^User provides the product name$")
    public void userProvidesTheProductName(String productName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User provides the product name");
        productId = DatabaseHelper.getProductId(productName);
        request = given().contentType(contentType).queryParam("productId", productId);
    }


    @When("User deletes the product")
    public void userDeletesTheProduct() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User deletes the product");
        String url = BASE_URI + "product/" + productId;
        response = request.when().delete(url);
    }

    @Then("User gets the statuscode as {int}")
    public void userGetsTheStatuscodeAs(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode");
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("^User provides the product name that doesn't exist$")
    public void userProvidesTheProductNameThatDoesnTExist(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User provides the product name that doesn't exist");
        request = given().contentType(contentType).queryParam("productId", 0);
    }

    @Given("User wants to get all products")
    public void userWantsToGetAllProducts() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get all products");
        request = given().contentType(contentType);
    }

    @When("User navigates to product url")
    public void userNavigatesToProductUrl() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User navigates to product url");
        String url = BASE_URI + "product";
        response = request.when().get(url);
    }

    @Then("User retrieves the list of <products>")
    public void userRetrievesTheListOfProducts(DataTable dataTable) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User retrieves the list of products");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> products = dataTable.asList();
        List<String> productList = jsonPath.getList("data.name");
        assertThat(products, containsInAnyOrder(productList.toArray()));
        response.then().assertThat().statusCode(200);
    }

    @Given("User specifies the {string} and {string}")
    public void userSpecifiesTheSubcategoryAndProduct(String subcategory, String product) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies the subcategory and product");
        productId = DatabaseHelper.getProductId(product);
        subcategoryId = DatabaseHelper.getSubcategoryId(subcategory);
        request = given().contentType(contentType).queryParam("productId", productId);
    }

    @When("User provides the {string} {int} and {double} to update")
    public void userProvidesTheNameQuantityAndPriceToUpdate(String productName, int quantity, double price) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the productName quantity and price to update");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("price", price);
        jsonObject.put("quantity", quantity);
        jsonObject.put("subCategoryId", subcategoryId);
        String jsonString = jsonObject.toString();
        String url = BASE_URI + "product/" + productId;
        response = request.body(jsonString).when().put(url);
    }

    @Given("User specifies the {string} and {string} which doesn't exist")
    public void userSpecifiesTheSubcategoryAndProductWhichDoesnTExist(String subcategory, String product) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies the subcategory and product which doesn't exist");
        subcategoryId = 0;
        request = given().contentType(contentType).queryParam("productId", 0);
    }

    @When("User provides the details {string} {int} {double} and {string}")
    public void userUpdatesSubcategory(String productName, int quantity, double price, String newSubcategory) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the details productName quantity price and newSubcategory");
        subcategoryId = DatabaseHelper.getSubcategoryId(newSubcategory);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("price", price);
        jsonObject.put("quantity", quantity);
        jsonObject.put("subCategoryId", subcategoryId);
        String jsonString = jsonObject.toString();
        String url = BASE_URI + "product/" + productId;
        response = request.body(jsonString).when().put(url);
    }

    @When("User provides the details {string} {int} {double} and {string} which doesn't exist")
    public void userUpdatesSubcategoryWhichDoesnTExist(String productName, int quantity, double price, String newSubcategory) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the details productName quantity price and newSubcategory which doesn't exist");
        subcategoryId = 0;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("price", price);
        jsonObject.put("quantity", quantity);
        jsonObject.put("subCategoryId", subcategoryId);
        String jsonString = jsonObject.toString();
        String url = BASE_URI + "product/" + productId;
        response = request.body(jsonString).when().put(url);
    }
}
