package com.epam.utility;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredHelper {
    RequestSpecification request;
    ResponseSpecification responseSpecification;
    Response response;
    ValidatableResponse json;

    public void givenStatement() {
        request = given();
    }

    public void whenStatement() {
        request = request.when();
    }

    public void thenStatement() {
        responseSpecification = request.then();
    }

}
