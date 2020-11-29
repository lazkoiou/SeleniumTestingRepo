package com.herokuappwebsite.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Class for the Nested Frames Page
 */
public class NestedFramesPage extends BasePage{

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Switch to the top frame of choice which is inside another frame
     * First needs to switch to the parent frame (top) and afterwards to the designated one
     * Before returning what we need from the frame, the driver needs to switch to the default content
     * so that it is ready for reuse
     * @param frameName: frame's name as a text
     */
    public String switchToTopFrames(String frameName) {

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame(frameName);

        String bodyText = "";

        if(frameName.equals("frame-left") || frameName.equals("frame-right")) {
            bodyText = driver.findElement(By.cssSelector("body")).getText();
        }
        else {
            bodyText =  driver.findElement(By.id("content")).getText();
        }

        driver.switchTo().defaultContent();

        return bodyText;
    }

    /**
     * Switch to the bottom frame
     * @param frameName: frame's name as a text
     */
    public String switchToBottomFrame(String frameName) {
        driver.switchTo().frame(frameName);
        return driver.findElement(By.cssSelector("body")).getText();
    }

}
