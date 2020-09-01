package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    
    public String getTitle() {
        return driver.getTitle();
    }

    
    public void stop() {
        driver.quit();
    }


    public void type(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    
    public boolean isElementPresent(WebElement element) {
        if (element.isEnabled()) {
            return true;
        } else return false;
    }

//    
//    public void waitUntilElementVisibilityOf(WebElement element, int timeout) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }

//    
//    public void waitUntilElementIsClickable(WebElement element, int timeout) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }

//    
//    public void waitUntilPageIsLoaded(int timeout, String pageTitle) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
//        wait.until(ExpectedConditions.titleContains(pageTitle));
//    }


    
    public void currentPageCheckByTitle(String pageTitle, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 500);
        wait.until(ExpectedConditions.titleContains(pageTitle));
        Assert.assertEquals(getTitle(), pageTitle, "It is not right page");
    }

    
    public void getText() {
    }
}
