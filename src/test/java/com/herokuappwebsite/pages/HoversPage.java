package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Class for the Forgot Password Page
 */
public class HoversPage extends BasePage{

    @FindBy(css = "#content > div > div:nth-child(3) > img")
    private WebElement figure1;

    @FindBy(css = "#content > div > div:nth-child(3) > div > h5")
    private WebElement caption1;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public HoversPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the caption by clicking in the image so that the text appears
     * @return: boolean - true if caption displayed
     */
    public boolean getCaptionByClicking() {
        figure1.click();
        return caption1.isDisplayed();
    }


    /**
     * Gets the caption by using action to hover above the image so that the text appears
     * @return: boolean - true if caption displayed
     */
    public boolean getCaptionByAction() {
        Actions actions = new Actions(driver);
        actions.moveToElement(figure1).perform();

        return caption1.isDisplayed();
    }

}
