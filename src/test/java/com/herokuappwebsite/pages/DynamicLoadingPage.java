package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for the Dynamic Loading Page
 */
public class DynamicLoadingPage extends BasePage{

    @FindBy(css = "#content > div > a:nth-child(5)")
    private WebElement example1;

    @FindBy(css = "#content > div > a:nth-child(8)")
    private WebElement example2;

    @FindBy(css = "#start > button")
    private WebElement startButton;

    @FindBy(id = "finish")
    private WebElement appearingElement;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Choose the example that you want to click to
     * @param chosenExample: can take values "Example 1" or "Example 2"
     */
    public void startExample(String chosenExample) {
        if(chosenExample.equals("Example 1")) {
            example1.click();
        }
        else if (chosenExample.equals("Example 2")){
            example2.click();
        }
        else {
            throw new RuntimeException();
        }
    }

    /**
     * Clicks at the start button
     */
    public void clickStartButton() {
        startButton.click();
    }

    /**
     * Waits for the element to be loaded and gets its text
     * @return: The dynamic loaded elements' text or "" if not displayed
     */
    public String getTextFromDynamicElement() {
        if (waitForElement(appearingElement)) {
            return appearingElement.getText();
        }
        else return "";
    }

}
