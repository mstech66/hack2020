package com.epam.utility.hooks;

import com.epam.utility.extentreport.ExtentHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends ExtentHelper {

    @Before
    public void initializeTest(Scenario scenario) {
        scenarioDefinition = feature.createNode(scenario.getName());
    }
}
