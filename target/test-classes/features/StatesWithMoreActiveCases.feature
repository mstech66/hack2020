Feature: Covid19Count for States with Top 3 Active cases

  Scenario Outline: Covid19Count for States with Top 3 Active cases
    When I get response for covid19 cases
    Then I verify status code <statusCode>
    And I get top three states with active cases
    Given User is on home page
    When User sorts by active cases
    Then User verifies the top three states with active

    Examples:
    |statusCode|
    |200       |
