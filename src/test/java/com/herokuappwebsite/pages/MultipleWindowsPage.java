package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for the Multiple Windows Page
 */
public class MultipleWindowsPage {

    private final WebDriver driver;
    private String initWindow = "";
    private Set<String> windows = new HashSet<>();

    @FindBy(linkText = "Click Here")
    private WebElement openWindowElement;

    public MultipleWindowsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        windows = driver.getWindowHandles();

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
