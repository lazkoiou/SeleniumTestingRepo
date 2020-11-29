package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class for the Basic Auth Page
 */
public class BasicAuthPage extends BasePage{

    @FindBy(css = "#content > div > p")
    private WebElement pageTextElement;

    /**
     * Constructor
     * @param driver: webdriver
     */
    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the text confirming the login
     * @return: The confirmation text
     */
    public String getPageText() {
        return pageTextElement.getText();
    }


}
