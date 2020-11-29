package com.herokuappwebsite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class for the Notification Messages Page which is used for
 * the retryActionsTest
 */
public class NotificationMessagesPage extends BasePage {

    @FindBy(id = "flash")
    private WebElement successStatus;

    @FindBy(css = "#content > div > p > a")
    private WebElement retryButton;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public NotificationMessagesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get the notification message and check if it contains the success text
     * @return: boolean that is true if it contains the success text
     */
    public boolean getMessage() {
        return successStatus.getText().contains("Action successful");
    }

    /**
     * Retries until either we get the success message or we reach the maxTries
     * @param maxTries: Max times to try to get the success message
     * @return: boolean which is true if we get the success notification
     */
    public boolean retryUntilSuccessOrMaxTimes(int maxTries) {
        for (int i = 0; i < maxTries; i++) {
            retryButton.click();
            if (getMessage()) {
                return true;
            }
        }

        return false;
    }

}
