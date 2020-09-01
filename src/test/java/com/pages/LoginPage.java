package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


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
        driver.get(value);
    }

    public void inputLogin(String value) {
        type(loginField, value);
    }

    public void inputPassword(String value) {
        type(passwordField, value);
    }

    public void checkInRememberMe() {
        rememberCheckbox.click();
    }

    public void clickOnEnterButton() {
        enterButton.click();
    }

    public void errorMessageCheck() {
        Boolean check = isElementPresent(errorWarning);
        Assert.assertTrue(check);
    }

    public void userNameCheck(String value) {
        String user = userName.getText();
        System.out.println("User: " + userName.getText());
        Assert.assertEquals(user, value, "Wrong user name");
    }

    public void openSiteInNewTab(String value) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(value);
    }

//  public void waitUntilElementIsClickable(WebElement element, int timeout) {
//    WebDriverWait wait = new WebDriverWait(driver, timeout);
//    wait.until(ExpectedConditions.elementToBeClickable(element));
//  }

    public void clickOnAnotherAccountButton() {
        anotherAccount.click();
    }

    public void clickOnCurrentAccountButton() {
        currentAccount.click();
    }
}
