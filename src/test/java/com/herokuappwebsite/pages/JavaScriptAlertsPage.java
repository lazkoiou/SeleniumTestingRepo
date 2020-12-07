package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class for the JavaScript Alerts Page
 */
public class JavaScriptAlertsPage extends BasePage {

    @FindBy(css = "#content > div > ul > li:nth-child(1) > button")
    private WebElement jsAlertButton1;

    @FindBy(css = "#content > div > ul > li:nth-child(2) > button")
    private WebElement jsAlertButton2;

    @FindBy(css = "#content > div > ul > li:nth-child(3) > button")
    private WebElement jsAlertButton3;

    @FindBy(id = "result")
    private WebElement result;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public void clickForJSAlertAndAccept() {
        jsAlertButton1.click();
        driver.switchTo().alert().accept();
    }

    public void clickForJSConfirmAndConfirm() {
        jsAlertButton2.click();
        driver.switchTo().alert().accept();
    }

    public void clickForJSPromptAndSubmit(String promptText) {
        jsAlertButton3.click();
        driver.switchTo().alert().sendKeys(promptText);
        driver.switchTo().alert().accept();
    }

    public String getResult() {
        return result.getText();
    }

}
