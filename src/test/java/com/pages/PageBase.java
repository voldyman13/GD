package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class PageBase {
    Logger logger = LoggerFactory.getLogger(PageBase.class);

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() { return driver.getTitle(); }

    public void stop() { driver.quit(); }

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
}
