package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.utility.DatabaseHelper;
import com.epam.utility.extentreport.ExtentHelper;
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

public class SubcategorySteps extends ExtentHelper {
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private final String BASE_URI = "http://10.71.12.112:8080/category/";
    private int categoryId;

    @Given("User wants to get all subcategories for a category")
    public void User_wants_to_get_all_subcategories_for_a_category() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get all subcategories for a category");
        request = given();
    }

    @When("User navigates to subcategory url by {int}")
    public void user_Navigates_To_Subcategory_Url_By_SubcategoryId(int subcategoryId) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User navigates to subcategory url by subcategoryId");
        String url = BASE_URI + subcategoryId + "/subcategory";
        response = request.when().get(url);
    }

    @Then("User gets the list of {string}")
    public void User_gets_the_list_of_subcategories(String subcategories) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the list of subcategories");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> items = jsonPath.getList("data.name");
        List<String> subcategoryList = Arrays.asList(subcategories.split(","));
        assertThat(subcategoryList, containsInAnyOrder(items.toArray()));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @And("{string} is received")
    public void error_message_Is_Received(String message) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("And"), "message is received");
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        String jsonMessage = jsonPath.getString("message");
        assertThat(jsonMessage, equalTo(message));
    }

    @Then("User gets the {int} for invalid operation")
    public void User_Gets_The_Status_Code_For_Invalid_Operation(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode for invalid operation");
        response.then().assertThat().statusCode(statusCode);
    }


    @When("^User provides the subcategory name$")
    public void userProvidesTheSubcategoryName(String subcategoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the subcategory name");
        String url = BASE_URI + "subcategory";
        response = request.when().post(url);
    }

    @Given("^User specifies category for subcategory$")
    public void userSpecifiesCategoryForSubcategory(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies category for subcategory");
        request = given().contentType("application/json");
        categoryId = DatabaseHelper.getCategoryId(categoryName);
    }

    @When("^User provides the subcategory$")
    public void userProvidesTheSubcategory(String subcategoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the subcategory");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", subcategoryName);
        jsonObject.put("parentCategoryId", categoryId);
        String jsonString = jsonObject.toString();
        String url = BASE_URI + "subcategory";
        response = request.body(jsonString).when().post(url);
    }

    @Given("^User specifies category for subcategory which doesn't exist$")
    public void userSpecifiesCategoryForSubcategoryWhichDoesnTExist(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User specifies category for subcategory which doesn't exist");
        request = given().contentType("application/json");
        categoryId = 0;
    }

    @Then("^User gets the status code$")
    public void userGetsTheStatusCode(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the status code");
        response.then().assertThat().statusCode(statusCode);
    }
}
