package com.epam.steps;

import com.aventstack.extentreports.GherkinKeyword;
import com.epam.pages.HomePage;
import com.epam.runner.CovidReportRunner;
import com.epam.utility.extentreport.ExtentHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class StatesWithMoreActiveCases extends ExtentHelper {

    private RequestSpecification request;
    private Response response;
    private final String BASE_URI = "https://api.covid19india.org/data.json";
    List<String> topStates = new ArrayList<>();
    HomePage homePage;

    @When("I get response for covid19 cases")
    public void I_get_response_for_covid19_cases() {
        RestAssured.baseURI = BASE_URI;
        request = RestAssured.given();
        response = request.get();
    }

    @Then("I verify status code {int}")
    public void i_verify_status_code(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @And("I get top three states with active cases")
    public void I_get_top_three_states_with_active_cases() {
        List<Map<String, String>> statesData = response.jsonPath().getList("statewise");
        statesData.sort(Comparator.comparing(state -> Long.parseLong(state.get("active")), Comparator.nullsLast(Comparator.reverseOrder())));
        topStates.add(statesData.get(1).get("state"));
        topStates.add(statesData.get(2).get("state"));
        topStates.add(statesData.get(3).get("state"));
    }

    @Given("^User is on home page$")
    public void userIsOnHomePage() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Given"), "User is on home page");
        homePage = new HomePage(CovidReportRunner.getWebDriver());
        homePage.openPage();
    }

    @When("User sorts by active cases")
    public void userClicksOnActiveCount() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("When"), "User sorts by active cases");
        homePage.sortActiveCases();
    }

    @Then("User verifies the top three states with active")
    public void userVerifiesTopThreeStatesWithActive() throws ClassNotFoundException {
        stepDefinition = scenarioDefinition.createNode(new GherkinKeyword("Then"), "User verifies the top three states with active");
        homePage.verifyStatesWithData(3, topStates);
    }
}
