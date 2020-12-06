package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class for the Checkbox Page
 */
public class CheckboxPage extends BasePage{

    @FindBy(css = "#checkboxes > input[type=checkbox]:nth-child(1)")
    private WebElement checkbox1;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects the checkbox 1 and returns boolean if
     * it is selected
     * @return: boolean - true if selected
     */
    public boolean selectCheckbox1() {
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
        return checkbox1.isSelected();
    }

    /**
     * Deselects the checkbox 1 and returns boolean if
     * it is unselected
     * @return: boolean - true if selected
     */
    public boolean deselectCheckbox1() {
        if (checkbox1.isSelected()) {
            checkbox1.click();
        }
        return checkbox1.isSelected();
    }

}
