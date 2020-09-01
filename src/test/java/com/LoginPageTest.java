package com;

import com.pages.LoginPage;
import com.pages.MainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class LoginPageTest extends TestBase {
  private LoginPage login;
  private MainPage main;
//  private static Logger logger = LoggerFactory.getLogger(LoginPageTest.class);

  String baseUrl = "https://gdcloud.ru/release-17/";
  String loginPositive = "tester";
  String loginNegative = "stranger";
  String passwordPositive = "K35G3U";
  String passwordNegative = "12345678";
  String loginPage = "WorkFlow";
  String mainPage = "Лента - WorkFlow";
  String userName = "Скворцофф А. И. (Jinior QA. Dptt)";

  @BeforeMethod(alwaysRun = true)
  public void initPageObjects() {
    login = PageFactory.initElements(driver, LoginPage.class);
    main = PageFactory.initElements(driver, MainPage.class);
    login.openSite(baseUrl);
  }

  @Test(groups = {"LoginPageTests", "PositiveTests", "InterfaceTests"}, priority = 1)
  public void loginPageTitleTest(){
    login.currentPageCheckByTitle(loginPage, 60);
  }

  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 0)
  public void loginPositiveTest(){
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.clickOnEnterButton();
    login.currentPageCheckByTitle(mainPage, 60);
    login.userNameCheck(userName);

  }
  @Test(groups = {"LoginTests", "PositiveTests"}, priority = 1)
  public void loginPositiveRememberCheckBoxTest() throws AWTException {
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.checkInRememberMe();
    login.clickOnEnterButton();
    login.currentPageCheckByTitle(mainPage, 60);
    login.openSiteInNewTab(baseUrl);
    login.currentPageCheckByTitle(mainPage, 60);
    login.userNameCheck(userName);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongPasswordTest(){
    login.inputLogin(loginPositive);
    login.inputPassword(passwordNegative);
    login.clickOnEnterButton();
    login.errorMessageCheck();
    login.currentPageCheckByTitle(loginPage, 0);
  }

  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeWrongLoginTest(){
    login.inputLogin(loginNegative);
    login.inputPassword(passwordPositive);
    login.clickOnEnterButton();
    login.errorMessageCheck();
    login.currentPageCheckByTitle(loginPage, 60);
  }

// enter positive login & password, don't check in rememberMe checkBox, click on Enter  Button.
// Open new tab, open site there, expect something wrong. But everything is O'key
  @Test(groups = {"LoginTests", "Negative"}, priority = 1)
  public void loginNegativeRememberCheckBoxTest() throws AWTException {
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.clickOnEnterButton();
    login.currentPageCheckByTitle(mainPage, 60);
    login.openSiteInNewTab(baseUrl);
    login.currentPageCheckByTitle(mainPage, 60);
    login.userNameCheck(userName);
  }

  @Test(groups = {"LoginTests", "Negative"}, priority = 1)
  public void loginPositiveRememberCheckBox1Test() throws AWTException {
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.clickOnEnterButton();
    login.currentPageCheckByTitle(mainPage, 60);
    login.openSiteInNewTab(baseUrl);
    login.currentPageCheckByTitle(mainPage, 60);
    login.userNameCheck(userName);
  }

// enter positive login & password, click on Another Account Button, expect error message
  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 2)
  public void loginNegativeAnotherAccountTest(){
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.clickOnAnotherAccountButton();
    login.errorMessageCheck();
    login.currentPageCheckByTitle(loginPage, 0);
  }

// enter positive login & password, click on Current Account Button, expect error message
//  I don't know, but I think it can work on different browsers at the same time
  @Test(groups = {"LoginTests", "NegativeTests"}, priority = 1)
  public void loginNegativeCurrentAccountTest(){
    login.inputLogin(loginPositive);
    login.inputPassword(passwordPositive);
    login.clickOnCurrentAccountButton();
    login.errorMessageCheck();
    login.currentPageCheckByTitle(loginPage, 0);
  }

  @AfterMethod(alwaysRun = true)
  public void TearDown(){
    login.stop();
  }
}
