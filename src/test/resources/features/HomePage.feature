Feature: Covid19ActiveCount

  Scenario: Opening covid19 tracker home page
    Given User is on home page
    When User sorts by active cases
    Then User verifies the top three states with active