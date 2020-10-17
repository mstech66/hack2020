Feature: Covid19ActiveCount

  Scenario: Opening covid19 tracker home page
    Given User is on home page
    When User clicks on active count
    And User click on active column sort
    Then User verifies the top three states with active
