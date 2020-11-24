package com.herokuappwebsite.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class for the Basic Auth Page
 */
public class BasicAuthPage {

    private final WebDriver driver;

    @FindBy(css = "#content > div > p")
    private WebElement pageTextElement;

    /**
     * Constructor
     * @param driver: webdriver
     */
    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the text confirming the login
     * @return: The confirmation text
     */
    public String getPageText() {
        return pageTextElement.getText();
    }


}
