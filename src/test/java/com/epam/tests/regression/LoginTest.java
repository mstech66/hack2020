package com.epam.tests.regression;

import com.epam.framework.webdriver.ui.pages.HomePage;
import com.epam.framework.webdriver.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    HomePage homePage;

    @BeforeClass
    public void setup() {
        homePage = new HomePage(getWebDriver());
        homePage.openPage();
    }

    @Test(priority = 1)
    private void enterInfo(){
        String name = "Adithya";
        homePage.enterName(name);
        Assert.assertEquals(homePage.getTextAfterEntering(), "Hello " + name + "!");
    }

}
