package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class for the Dropdown Page
 */
public class DropdownPage {

    private final WebDriver driver;

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    @FindAll(
            @FindBy(tagName = "option")
    )
    private List<WebElement> drpOptions;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Chooses the option that we want from the dropdown list by locating the options
     * and click the one that has the text we want
     * @param choice: String with the text of the option that we want
     * @return: Returns the String of the selected option
     */
    public String selectOptionByClick(String choice) {
        // we need to use an AtomicReference wrapper to save the value from inside the
        // lambda expression
        AtomicReference<String> returnString = new AtomicReference<>("");

        // if the option is the same as the choice that we want then click it
        drpOptions.forEach(option -> {
            if (option.getText().equals(choice)) {
                option.click();
                returnString.set(option.getText());
            }
        });

        return returnString.get();
    }

    /**
     * Selects the option we want by finding the dropdown list and selecting by value the
     * option that we want
     * @return: Returns the select option's text
     */
    public String selectOptionBySelect(String valueToSelect) {
        Select drpSelectList = new Select(dropdown);
        drpSelectList.selectByValue(valueToSelect);

        return drpSelectList.getFirstSelectedOption().getText();
    }

}
