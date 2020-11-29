package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for the Multiple Windows Page
 */
public class MultipleWindowsPage extends BasePage{

    private String initWindow = "";

    @FindBy(linkText = "Click Here")
    private WebElement openWindowElement;

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Saves the init window handle
     */
    public void saveInitWindowHandle() {
        initWindow = driver.getWindowHandle();
    }

    /**
     * Opens window by clicking on the element
     */
    public void openWindow() {
        openWindowElement.click();
    }

    public String selectNewlyOpenedWindow() {
        // get all the windows
        Set<String> windows = driver.getWindowHandles();

        // find the newly opened window
        windows.forEach(window -> {
            if (!window.equals(initWindow)) {
                driver.switchTo().window(window);
            }
        });

        return driver.getTitle();
    }

    public String selectInitWindow() {
        return driver.switchTo().window(initWindow).getTitle();
    }

}
