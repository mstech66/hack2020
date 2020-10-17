package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.utility.DatabaseHelper;
import com.epam.utility.extentreport.ExtentHelper;
import io.cucumber.datatable.DataTable;
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

public class CategorySteps extends ExtentHelper {
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private final String BASE_URI = "http://10.71.12.112:8080/category";
    private int categoryId;

    @Given("User wants to get all categories")
    public void userWantsToGetAllCategories() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to get all categories");
        request = given();
    }

    @When("User navigates to category url")
    public void userNavigatesToUrl() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User navigates to category url");
        response = request.when().get(BASE_URI);
    }

    @Then("User gets the <categories>")
    public void userGetsTheListOfCategories(DataTable data) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the categories");
        json = response.then();
        List<String> categories = data.asList();
        System.out.println("Categories: " + categories);
        String json = response.jsonPath().prettify();
        JsonPath jsonPath = new JsonPath(json);
        List<String> items = jsonPath.getList("data.name");
        System.out.println("items " + items);
        assertThat(categories, containsInAnyOrder(items.toArray()));
        response.then().assertThat().statusCode(200).body("message", equalTo("Successfull"));
    }

    @Then("^User gets the statusCode for invalid operation$")
    public void userGetsTheStatusCodeForInvalidOperation(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the statusCode for invalid operation");
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("User wants to add a category")
    public void userWantsToAddACategory() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to add a category");
        request = given();
    }

    @When("^User provides the category name$")
    public void userProvidesTheCategoryName(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the category name");
        response = request.queryParam("categoryName", categoryName).when().post(BASE_URI);
    }

    @Then("^User gets the response$")
    public void userGetsTheResponse(String message) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the response");
        response.then().assertThat().statusCode(201).body("message", equalTo(message));
    }

    @Given("User wants to delete a category")
    public void userWantsToDeleteACategory() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to delete a category");
        request = given();
    }

    @When("^User provides the category name to be deleted$")
    public void userProvidesTheCategoryNameToBeDeleted(String categoryName) throws ClassNotFoundException {
        scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the category name to be deleted");
        String url = BASE_URI + "/" + DatabaseHelper.getCategoryId(categoryName);
        response = request.when().delete(url);
    }

    @Then("^User gets the status code in response$")
    public void userGetsTheStatusCodeInResponse(int statusCode) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User gets the status code in response");
        response.then().assertThat().statusCode(statusCode);
    }

    @Given("^User wants to update a category$")
    public void userWantsToUpdateACategory(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to update a category");
        categoryId = DatabaseHelper.getCategoryId(categoryName);
        request = given();
    }

    @When("^User provides the category name to be updated$")
    public void userProvidesTheCategoryNameToBeUpdated(String categoryName) throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the category name to be updated");
        String url = BASE_URI + "/" + categoryId;
        response = request.queryParam("categoryId", categoryId).queryParam("categoryName", categoryName).when().put(url);
    }


    @When("^User provides the deleted category$")
    public void userProvidesTheDeletedCategory() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User provides the deleted category");
        String url = BASE_URI + "/" + categoryId;
        response = request.when().delete(url);
    }

    @Given("^User wants to update a category that doesn't exist$")
    public void userWantsToUpdateACategoryThatDoesnTExist() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User wants to update a category that doesn't exist");
        categoryId = 34;
        request = given();
    }
}
