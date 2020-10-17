package com.epam.pages;

import com.epam.utility.BasePage;
import com.epam.utility.LoggerUtil;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BasePage {

  private WebDriver webDriver;
  private final String url = "https://www.covid19india.org/";
  @FindBy(xpath = "//div[text()=\"Active\"]//parent::div[@class=\"cell heading\"]")
  WebElement activeTab;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this);
  }

  public void openPage() {
    get(url);
  }

  public void sortActiveCases() {
    waitUntilElementIsVisibleFor(activeTab, 5);
    getElement(activeTab).click();
  }

  public void verifyStatesWithData(int num, List<String> expected) {
    explicitWait(3000);
    for (int i = 1; i <= num; i++) {
      String xpath = "//div[@class=\"table-container\"]//div[@class=\"row\"][" + i
          + "]//div[@class=\"state-name fadeInUp\"][1]";
      LoggerUtil.info(
          "NUM: " + i + "current: " + getElement(By.xpath(xpath)).getText() + " Expected: "
              + expected.get(i - 1));
      Assert.assertEquals(getElement(By.xpath(xpath)).getText(), expected.get(i - 1));
    }
  }
}
