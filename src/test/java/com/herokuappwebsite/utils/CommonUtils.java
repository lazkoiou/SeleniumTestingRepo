package com.herokuappwebsite.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    /**
     * Navigates to a specific page
     * @param driver : webdriver
     */
    public static void openLink(WebDriver driver, String link) {
        driver.findElement(By.linkText(link)).click();
    }

    /**
     * Get the time of the failure (to add in the filename)
     * @return: date time in the specified format
     */
    public static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss'.png'").format(new Date());
    }

    /**
     * Clean the directory of previous screenshots - doing this for cleanup reasons here
     * @param pathName: String with the pathname of the folder in which cleanup will be performed
     * @throws IOException:
     */
    public static void cleanDirectory(String pathName) throws IOException {
        File dir = new File(pathName);
        FileUtils.cleanDirectory(dir);
    }

    /**
     * Take screenshot and save it in "screenshots" folder
     * @param driver: Webdriver
     * @param screenshotPathName: path and name of the sceenshot
     * @throws IOException:
     */
    public static void getScreenshot(WebDriver driver, String screenshotPathName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotPathName));
    }
}
