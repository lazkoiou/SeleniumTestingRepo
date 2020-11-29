package com.herokuappwebsite.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class for the A/B Testing page
 */
public class ABTestingPage extends BasePage{


    @FindBy(css = "#content > div > h3")
    private WebElement pageTitle;

    /**
     * Constructor
     * @param driver: webdriver
     */
    public ABTestingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the title of the page
     * @return: The title as a string
     */
    public String getPagetitle() {
        return pageTitle.getText();
    }

    /**
     * Deletes all cookies
     */
    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * Adds optOut cookie (1st way of making the page display another version)
     */
    public void addOptOutCookie() {
        Cookie optOutCookie = new Cookie("optimizelyOptOut", "true");
        driver.manage().addCookie(optOutCookie);
    }

    /**
     * Refresh the page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Add an optOut request to the url (2nd way of making the page display another version)
     * and close the alert that opens
     */
    public void addOptOutRequest() {
        driver.navigate().to("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
        driver.switchTo().alert().dismiss();
    }

}
