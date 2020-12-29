$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/StatesWithMoreActiveCases.feature");
formatter.feature({
  "name": "Covid19Count for States with Top 3 Active cases",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Covid19Count for States with Top 3 Active cases",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I get response for covid19 cases",
  "keyword": "When "
});
formatter.step({
  "name": "I verify status code \u003cstatusCode\u003e",
  "keyword": "Then "
});
formatter.step({
  "name": "I get top three states with active cases",
  "keyword": "And "
});
formatter.step({
  "name": "User is on home page",
  "keyword": "Given "
});
formatter.step({
  "name": "User sorts by active cases",
  "keyword": "When "
});
formatter.step({
  "name": "User verifies the top three states with active",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "statusCode"
      ]
    },
    {
      "cells": [
        "200"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Covid19Count for States with Top 3 Active cases",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I get response for covid19 cases",
  "keyword": "When "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.I_get_response_for_covid19_cases()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify status code 200",
  "keyword": "Then "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.i_verify_status_code(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get top three states with active cases",
  "keyword": "And "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.I_get_top_three_states_with_active_cases()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User is on home page",
  "keyword": "Given "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.userIsOnHomePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sorts by active cases",
  "keyword": "When "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.userClicksOnActiveCount()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User verifies the top three states with active",
  "keyword": "Then "
});
formatter.match({
  "location": "com.epam.steps.StatesWithMoreActiveCases.userVerifiesTopThreeStatesWithActive()"
});
formatter.result({
  "status": "passed"
});
});