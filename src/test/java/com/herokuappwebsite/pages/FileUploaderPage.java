package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

/**
 * Class for the File Uploader Page
 */
public class FileUploaderPage extends BasePage{

    @FindBy(id = "file-upload")
    private WebElement fileUpload;

    @FindBy(id = "file-submit")
    private WebElement fileSubmit;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFile;

    public FileUploaderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Uploads the file by sending the absolute path to the "Choose File" field
     * and clicks on submit button
     * @param filePath: The file's absolute path
     * @return: The name of the uploaded file
     */
    public String uploadAFile(String filePath) {
        fileUpload.sendKeys(filePath);
        fileSubmit.click();

        return uploadedFile.getText();
    }

}
