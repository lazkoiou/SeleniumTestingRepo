package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
}
