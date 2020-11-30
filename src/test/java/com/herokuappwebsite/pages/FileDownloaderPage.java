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

    /**
     * Downloads a file and saves it to the project's "resources" path
     * The driver waits until the file actually exists in the filesystem
     * @return: boolean which is true if the file has been downloaded
     */
    public boolean downloadFile() {
        fileToDownload.click();

        File file = new File(".\\resources\\" + fileToDownload.getText());
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(25))
                .pollingEvery(Duration.ofMillis(100));
        wait.until( x -> file.exists());

        return file.exists();
    }

    /**
     * Performs the cleanup by deleting the file
     * @return: true if delete operation is performed
     */
    public boolean deleteFile() {
        File file = new File(".\\resources\\" + fileToDownload.getText());
        return file.delete();
    }

}
