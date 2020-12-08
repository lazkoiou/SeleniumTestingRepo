package com.herokuappwebsite.pages;

import com.herokuappwebsite.utils.JSFunctionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

/**
 * Class for the Key Presses Page
 */
public class KeyPressesPage extends BasePage {

    @FindBy(id = "result")
    private WebElement result;

    /**
     * Constructor
     * @param driver: Webdriver
     */
    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Presses a button and returns a text result that states the button pressed
     * @param pressButton: The button to be pressed
     * @return: Text that states which button was pressed
     * @throws AWTException:
     */
    public String pressKeyAndCheckResult(int pressButton) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(pressButton);
        robot.keyRelease(pressButton);

        return result.getText();
    }

    /**
     * USED FOR THE * highlightElementTest *
     * Presses a button and returns a text result that states the button pressed
     * @param pressButton: The button to be pressed
     * @return: Text that states which button was pressed
     * @throws AWTException:
     */
    public String pressKeyAndCheckResultHighlight(int pressButton) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(pressButton);
        robot.keyRelease(pressButton);

        JSFunctionUtils.highlight(driver, result, 2);

        return result.getText();
    }

}
