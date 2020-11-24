package com.herokuappwebsite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class for Add/Remove Elements Page
 */
public class AddRemoveElementsPage {

    private final WebDriver driver;

    @FindBy(css = "#content > div > button")
    private WebElement createElementButton;

    @FindBy(className = "added-manually")
    private WebElement deleteElementButton;

    @FindBy(className = "added-manually")
    private WebElement existingElement;

    /**
     * Constructor
     * @param driver: webdriver
     */
    public AddRemoveElementsPage (WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    /**
     * Create element by clicking in the Add Element button
     */
    public void createElement() {
        createElementButton.click();
    }

    /**
     * Checks if element exists by classname
     * @return: element's text
     */
    public String checkIfExistingElements() {
        return existingElement.getText();
    }

    /**
     * Deletes an existing element
     */
    public void deleteElement() {
        deleteElementButton.click();
    }

    /**
     * Checks if there are no elements present
     * @param driver: webdriver
     * @return: returns the exception name "NoSuchElementException"
     *          if no elements are found
     */
    public static String checkIfDeleted(WebDriver driver) {
        String errorClassName = null;

        try {
            driver.findElement(By.className("added-manually"));
        }
        catch (Exception e) {
            errorClassName = e.getClass().getSimpleName();
        }

        return errorClassName;
    }
}
