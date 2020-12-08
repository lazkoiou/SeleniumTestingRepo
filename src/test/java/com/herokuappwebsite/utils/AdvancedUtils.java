package com.herokuappwebsite.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.print.attribute.Attribute;

/**
 * Class which contains some advanced techniques
 */
public class AdvancedUtils {

    /**
     * Highlights an element with yellow background
     * @param driver: Webdriver
     * @param element: Our target element
     * @param duration: Duration of the highlight
     * @throws InterruptedException:
     */
    public static void highlight(WebDriver driver, WebElement element, int duration) throws InterruptedException {

        // store original attribute so it can be reset later
        String originalStyle = element.getAttribute("style");
        System.out.println("***************" + originalStyle);

        // style element with yellow border
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].setAttribute('style', 'background: yellow; border: 3px solid yellow;');",
                element);

        // keep element highlighted for the duration specified and revert
        if(duration > 0) {
            Thread.sleep(duration * 1000);
            js.executeScript(
                    "arguments[0].setAttribute('style', '" +
                    originalStyle +
                    "');",
                    element
            );
        }
    }
}
