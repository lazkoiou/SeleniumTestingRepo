package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for the Sorted Data Tables Page
 */
public class SortedDataTablesPage extends BasePage {

    // using the CSS Pseudocode to access the table data elements
    @FindBy(css = "#table1 thead tr th:nth-of-type(4)")
    private WebElement dueHead;

    @FindAll(
            @FindBy(css = "#table1 tbody tr td:nth-of-type(4)")
    )
    private List<WebElement> dueValues;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public SortedDataTablesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks once to sort by ascending value,
     * twice for descending
     */
    public void clickOnDueToSortHead() {
        dueHead.click();
    }

    /**
     * Checks if the list of "Due" values is sorted
     * @return: boolean value - true if sorted
     */
    public boolean checkIfDueSorted() {
        // create list of dues to retain original values - remove $
        List<Float> dues = new ArrayList<>();
        dueValues.forEach(due -> {
            dues.add(Float.parseFloat(due.getText().replace("$", "")));
        });

        // create a second list of dues to be sorted by Collections.sort() - remove $
        List<Float> toSortList =  new ArrayList<>();
        dueValues.forEach(due -> {
            toSortList.add(Float.parseFloat(due.getText().replace("$", "")));
        });

        Collections.sort(toSortList);

        // check original and sortedList to check if the original has been sorted
        return dues.equals(toSortList);
    }

}
