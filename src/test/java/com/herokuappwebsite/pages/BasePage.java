package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    /**
     * Refresh the page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Gets the title of the page
     * @return: String with the title
     */
    public String getTitle() { return driver.getTitle(); }

    /**
     * Waits for element to be visible
     * @param element: target element to wait for
     * @return: boolean true if element is visible
     */
    public boolean waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
}
