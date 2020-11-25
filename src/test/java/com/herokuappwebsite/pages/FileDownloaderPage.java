package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;

/**
 * Class for the File Downloader Page
 */
public class FileDownloaderPage {

    @FindBy(linkText = "not_empty.txt")
    private WebElement fileToDownload;

    private final WebDriver driver;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public FileDownloaderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
