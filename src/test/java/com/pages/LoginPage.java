package com.pages;

import com.Listener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


public class LoginPage extends PageBase {

    @FindBy(how = How.TAG_NAME, using = "h1")
    @CacheLookup
    public WebElement header;

    @FindBy(id = "username")
    private WebElement loginField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "remember")
    private WebElement rememberCheckbox;
    @FindBy(id = "login_button")
    private WebElement enterButton;
    @FindBy(id = "login_button_domain")
    private WebElement anotherAccount;
    @FindBy(id = "login_button_current")
    private WebElement currentAccount;
    @FindBy(id = "error")
    private WebElement errorWarning;
    @FindBy(className = "svg-bg-path")
    private WebElement icon;
    @FindBy(id = "userName")
    private WebElement userName;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSite(String value) {
        logger.info("openSite - " + value);
        driver.get(value);
    }

    public void inputLogin(String value) {
        logger.info("inputLogin - " + value);
        type(loginField, value);
    }

    public void inputPassword(String value) {
        logger.info("inputPassword - " + value);
        type(passwordField, value);
    }

    public void checkInRememberMe() {
        logger.info("checkInRememberMe --->|");
        rememberCheckbox.click();
    }

    public void clickOnEnterButton() {
        logger.info("clickOnEnterButton --->|");
        enterButton.click();
    }

    public void errorMessageCheck() {
        Boolean check = isElementPresent(errorWarning);
        Assert.assertTrue(check);
        logger.info("errorMessageCheck ---> OK!");

    }

    public void userNameCheck(String value) {
        logger.info("userNameCheck - " + "'" + value +"'");
        String user = userName.getText();
        Assert.assertEquals(user, value, "Wrong user name");
        logger.info("User - " + user + " ---> OK! ");
    }

    public void openSiteInNewTab(String value) throws AWTException {
        logger.info("openSiteInNewTab - " + value);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(value);
    }

    public void currentPageCheckByTitle(String value, int timeout) {
        logger.info("currentPageCheckByTitle - " + "'" + value +"'");
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.titleContains(value));
        Assert.assertEquals(getTitle(), value, "It is not right page");
        logger.info("Title - " + value + " ---> OK! ");
    }


    public void clickOnAnotherAccountButton() {
        logger.info("clickOnAnotherAccountButton --->|");

        anotherAccount.click();
    }

    public void clickOnCurrentAccountButton() {
        logger.info("clickOnCurrentAccountButton --->|");
        currentAccount.click();
    }
}
