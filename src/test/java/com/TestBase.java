package com;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
  }
  @BeforeMethod
  public void startLogger(Method method, Object[] par){
    logger.info("start method " + method.getName() + " parameters: " + Arrays.asList().toString() + " |---------->");
  }

  @AfterMethod
  public void stopLogger(Method method, Object[] par){
    logger.info("stop method " + method.getName() + " parameters: " + Arrays.asList().toString() + " <----------|");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
