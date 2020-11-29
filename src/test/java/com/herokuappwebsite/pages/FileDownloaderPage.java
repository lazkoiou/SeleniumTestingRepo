package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;

/**
 * Class for the File Downloader Page
 */
public class FileDownloaderPage extends BasePage{

    @FindBy(linkText = "not_empty.txt")
    private WebElement fileToDownload;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public FileDownloaderPage(WebDriver driver) {
        super(driver);
    }

    public boolean downloadFile() {
        fileToDownload.click();

        File file = new File(".\\resources\\" + fileToDownload.getText());
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(25))
                .pollingEvery(Duration.ofMillis(100));
        wait.until( x -> file.exists());

        return file.exists();
    }

    public boolean deleteFile() {
        File file = new File(".\\resources\\" + fileToDownload.getText());
        return file.delete();
    }

}
