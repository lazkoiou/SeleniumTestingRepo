package com.herokuappwebsite.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonUtils {

    /**
     * Navigates to a specific page
     * @param driver : webdriver
     */
    public static void openLink(WebDriver driver, String link) {
        driver.findElement(By.linkText(link)).click();
    }

}
