package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.pages.HomePage;
import com.epam.runner.SeleniumRunner;
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

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class HomePageSteps extends ExtentHelper {

    HomePage homePage;

//    @Given("^User is on home page$")
//    public void userIsOnHomePage() throws ClassNotFoundException {
//        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User is on home page");
//        homePage = new HomePage(SeleniumRunner.getWebDriver());
//        homePage.openPage();
//    }
//
//    @When("User sorts by active cases")
//    public void userClicksOnActiveCount() throws ClassNotFoundException {
//        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User sorts by active cases");
//        homePage.sortActiveCases();
//    }
//
//    @Then("User verifies the top three states with active")
//    public void userVerifiesTopThreeStatesWithActive() throws ClassNotFoundException {
//        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User verifies the top three states with active");
//        homePage.verifyStatesWithData(3, Arrays.asList("Maharashtra", "Karnataka", "Kerala"));
//    }
}
