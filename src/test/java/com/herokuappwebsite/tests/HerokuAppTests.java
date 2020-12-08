package com.herokuappwebsite.tests;

import com.herokuappwebsite.pages.*;
import com.herokuappwebsite.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.herokuappwebsite.pages.AddRemoveElementsPage.*;
import static org.junit.jupiter.api.Assertions.*;

// TODO: check how to get selenium logs (selenium standalone server necessary)
// TODO: check how to retry failed tests
// TODO: add tag names so that you can avoid running test suites (like http://elementalselenium.com/tips/58-tagging)

@DisplayName("HerokuApp Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HerokuAppTests {

    private final static Logger logger = LogManager.getLogger(HerokuAppTests.class);
    public static WebDriver driver = null;

    @BeforeAll
    static void setup() {
        logger.info("Starting HerokuAppTests...");
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");

        // chrome options that are necessary to setup for the fileDownloadTest
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", System.getProperty("user.dir") + "\\src\\test\\resources\\");
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);
        // if we need a faster run of the tests we addArguments
        // "--headless", "--disable-gpu"
        // to save resources
        options.addArguments("start-maximized"); //

        driver = new ChromeDriver(options);
    }

    @AfterAll
    static void tearDown() {
        driver.close();
        driver.quit();
        logger.info("Finished HerokuAppTests...\n");
    }

    @BeforeEach
    void beforeEach() {
        // go to the start page
        driver.navigate().to("http://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        assertEquals("The Internet", title);
    }


    /**
     * Level: Beginner
     * Add or Remove elements by clicking on boxes
     */
    @Test
    @DisplayName("Add/Remove Element Test")
    @Order(1)
    @Disabled
    void addRemoveElementTest() {
        logger.info("addRemoveElement...");
        // open to the desired link
        CommonUtils.openLink(driver, "Add/Remove Elements");

        // create element and assert created
        AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage(driver);
        addRemoveElementsPage.createElement();
        assertEquals("Delete", addRemoveElementsPage.checkIfExistingElements());

        // delete element and assert deleted
        addRemoveElementsPage.deleteElement();
        assertEquals("NoSuchElementException", checkIfDeleted(driver));
        logger.info("addRemoveElementTest ...");
    }

    /**
     *  Level: Beginner
     *  Basic HTTP Authentication
     *  In order to access you'll need to pass credentials to the site when requesting a page
     *  This can be achieved by specifying the username and password in the URL
     */
    @Test
    @DisplayName("Basic Auth Test")
    @Order(1)
    @Disabled
    void basicAuthTest() {
        logger.info("basicAuthTest...");
        // send the username and password in the header to access the page
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        assertTrue(basicAuthPage.getPageText().contains(
                "Congratulations! You must have the proper credentials"
        ));
        logger.info("basicAuthTest ...");
    }

    /**
     * Level: Beginner
     * When trying to automate uploading a file you get prompted with a dialog box that is out of reach for Selenium.
     * A work-around for this is to side-step the system dialog box by inserting the full path of the file we want
     * to upload (as text) into the form and then submit the form.
     */
    @Test
    @DisplayName("Upload File Test")
    @Order(1)
    @Disabled
    void uploadFileTest() {
        logger.info("uploadFileTest...");
        CommonUtils.openLink(driver, "File Upload");
        FileUploaderPage fileUploaderPage = new FileUploaderPage(driver);

        File file = new File(".\\src\\test\\resources\\testfile.txt");
        System.out.println("File to be uploaded: " + file.getName() +
                " from path: " + file.getAbsolutePath());

        // send the file's absolute path
        assertEquals("testfile.txt", fileUploaderPage.uploadAFile(file.getAbsolutePath()));

        logger.info("uploadFileTest ...");
    }

    /**
     * Level: Beginner
     * Easily work with the elements in a frame by telling Selenium to switch to that frame first
     */
    @Test
    @DisplayName("Nested Frames Test")
    @Order(1)
    @Disabled
    void nestedFramesTest() {
        logger.info("nestedFramesTest...");
        CommonUtils.openLink(driver, "Nested Frames");
        NestedFramesPage nestedFramesPage = new NestedFramesPage(driver);

        // switch to each frame and verify the name
        assertEquals("LEFT", nestedFramesPage.switchToTopFrames("frame-left"));
        assertEquals("MIDDLE", nestedFramesPage.switchToTopFrames("frame-middle"));
        assertEquals("RIGHT", nestedFramesPage.switchToTopFrames("frame-right"));
        assertEquals("BOTTOM", nestedFramesPage.switchToBottomFrame("frame-bottom"));

        logger.info("nestedFramesTest ...");
    }

    /**
     * Level: Beginner
     * Grab the list by it's element and select an item within it based on the text you want.
     * While it sounds pretty straightforward, there is a bit more finesse to it.
     */
    @Test
    @DisplayName("Drop Down List Test")
    @Order(1)
    @Disabled
    void dropDownListTest() {
        logger.info("dropDownListTest...");
        CommonUtils.openLink(driver, "Dropdown");
        DropdownPage dropdownPage = new DropdownPage(driver);

        // 1st way - select option and assert
        assertEquals("Option 1", dropdownPage.selectOptionByClick("Option 1"));
        assertEquals("Option 2", dropdownPage.selectOptionByClick("Option 2"));

        // 2nd way - select option and assert
        assertEquals("Option 1", dropdownPage.selectOptionBySelect("1"));
        assertEquals("Option 2", dropdownPage.selectOptionBySelect("2"));

        logger.info("dropDownListTest ...");
    }

    /**
     * Level: Beginner
     * By leveraging Selenium's Action Builder we can handle more complex user interactions like hovers.
     * This is done by telling Selenium which element we want to move the mouse to, and then performing
     * what we need to after.
     */
    @Test
    @DisplayName("Hovers Test")
    @Order(1)
    @Disabled
    void hoverTest() {
        logger.info("hoverTest...");
        CommonUtils.openLink(driver, "Hovers");
        HoversPage hoversPage = new HoversPage(driver);

        // assert by using a click
        assertTrue(hoversPage.getCaptionByClicking());

        // refresh to start over
        hoversPage.refresh();

        // assert by hover
        assertTrue(hoversPage.getCaptionByAction());

        logger.info("hoverTest ...");
    }

    /**
     * Level: Beginner
     * There are two ways to approach this -- by seeing if an element has a checked attribute
     * (a.k.a. performing an attribute lookup), or by asking an element if it has been selected
     */
    @Test
    @DisplayName("Checkboxes Test")
    @Order(1)
    @Disabled
    void checkboxesTest() {
        logger.info("checkboxesTest...");
        CommonUtils.openLink(driver, "Checkboxes");
        CheckboxPage checkboxPage = new CheckboxPage(driver);

        // select, deselect checkboxes and assert
        assertTrue(checkboxPage.selectCheckbox1());
        assertFalse(checkboxPage.deselectCheckbox1());

        logger.info("checkboxesTest ...");
    }

    /**
     * Level: Beginner
     * Built into Selenium is the ability to switch to an alert window and either accept or dismiss it.
     */
    @Test
    @DisplayName("JavaScript Alerts Test")
    @Order(1)
    @Disabled
    void javaScriptAlertsTest() {
        logger.info("javaScriptAlertsTest...");
        CommonUtils.openLink(driver, "JavaScript Alerts");
        JavaScriptAlertsPage javaScriptAlertsPage = new JavaScriptAlertsPage(driver);

        // click on the first alert button, accept and check result
        javaScriptAlertsPage.clickForJSAlertAndAccept();
        assertEquals("You successfuly clicked an alert", javaScriptAlertsPage.getResult());

        // click on the second alert button, confirm and check result
        javaScriptAlertsPage.clickForJSConfirmAndConfirm();
        assertEquals("You clicked: Ok", javaScriptAlertsPage.getResult());

        // click on the third alert button, type "Test" and check result
        javaScriptAlertsPage.clickForJSPromptAndSubmit("Test");
        assertEquals("You entered: Test", javaScriptAlertsPage.getResult());

        logger.info("javaScriptAlertsTest ...");
    }

    /**
     * Level: Beginner
     * Apart from the .sendKeys function, there is another way to press keys.
     * That is, by using the Robot class
     */
    @Test
    @DisplayName("Keyboard Keys Test")
    @Order(1)
//    @Disabled
    void keyboardKeysTest() throws AWTException {
        logger.info("keyboardKeysTest...");
        CommonUtils.openLink(driver, "Key Presses");
        KeyPressesPage keyPressesPage = new KeyPressesPage(driver);

        assertEquals("You entered: " + "ENTER", keyPressesPage.pressKeyAndCheckResult(KeyEvent.VK_ENTER));
        assertEquals("You entered: " + "CONTROL", keyPressesPage.pressKeyAndCheckResult(KeyEvent.VK_CONTROL));
        assertEquals("You entered: " + "A", keyPressesPage.pressKeyAndCheckResult(KeyEvent.VK_A));
        assertEquals("You entered: " + "6", keyPressesPage.pressKeyAndCheckResult(KeyEvent.VK_6));

        logger.info("keyboardKeysTest ...");
    }


    /**
     * Level: Intermediate
     * The application of testing opens a new window. In order to work with both the new and originating windows
     * we need to switch between them. It is a straightforward concept, but watch out for that it might work
     * in some browsers and not others.
     */
    @Test
    @DisplayName("Multiple Windows Test")
    @Order(2)
    @Disabled
    void multipleWindowsTest() {
        logger.info("multipleWindowsTest...");
        CommonUtils.openLink(driver, "Multiple Windows");
        MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage(driver);

        // save the initial window handle and open a new one
        multipleWindowsPage.saveInitWindowHandle();
        multipleWindowsPage.openWindow();

        // select the new window and assert
        assertEquals("New Window", multipleWindowsPage.selectNewlyOpenedWindow());

        // select the initial window and assert
        assertEquals("The Internet", multipleWindowsPage.selectInitWindow());

        logger.info("multipleWindowsTest ...");
    }

    /**
     * Level: Intermediate
     * Just like with uploading files we hit the same issue with downloading them. A dialog box out
     * of Selenium's reach. With some configuration when setting up the Selenium driver we can side-step the dialog box.
     * This is done by instructing the browser to download files to a specific location without being prompted.
     */
    @Test
    @DisplayName("Download File Test")
    @Order(2)
    @Disabled
    void downloadFileTest() throws InterruptedException {
        logger.info("downloadFileTest...");
        CommonUtils.openLink(driver, "File Download");
        FileDownloaderPage fileDownloaderPage = new FileDownloaderPage(driver);
        Thread.sleep(2000);
        // download file and assert it exists
        assertTrue(fileDownloaderPage.downloadFile());

        // giving it some time to visually confirm that the download is successful
        Thread.sleep(300);

        // cleanup - delete the downloaded file
        assertTrue(fileDownloaderPage.deleteFile());

        logger.info("downloadFileTest ...");
    }

    /**
     * Level: Intermediate
     * Split testing is a simple way to experiment with an application's features
     * (how the application looks and behaves or changes that could include changing text
     * or images on the page, element positioning, color of the submit button, etc).
     */
    @Test
    @DisplayName("A/B Testing")
    @Order(2)
    @Disabled
    void abTest() {
        logger.info("abTest...");
        // open to the desired link
        CommonUtils.openLink(driver, "A/B Testing");
        ABTestingPage abTestingPage = new ABTestingPage(driver);

        // 1st way
        // delete cookies to ensure there are none
        abTestingPage.deleteCookies();
        assertTrue(
                abTestingPage.getPagetitle().contains("A/B Test Control") ||
                abTestingPage.getPagetitle().contains("A/B Test Variation 1"));

        // add cookie and refresh to get another version of the page
        abTestingPage.addOptOutCookie();
        abTestingPage.refresh();
        assertEquals("No A/B Test", abTestingPage.getPagetitle());


        // 2nd way
        // delete cookies to ensure there are none and refresh
        abTestingPage.deleteCookies();
        abTestingPage.refresh();
        assertTrue(
                abTestingPage.getPagetitle().contains("A/B Test Control") ||
                        abTestingPage.getPagetitle().contains("A/B Test Variation 1"));

        // add optOut request to header and close alert opened
        abTestingPage.addOptOutRequest();
        assertEquals("No A/B Test", abTestingPage.getPagetitle());
        logger.info("abTest ...");
    }

    /**
     * Level: Intermediate
     * Retry Actions
     * Tests might occasionally fail when the application is dependent upon third-party service providers
     * (e.g. payment providers, social networks, etc). Rather than have the tests fail for reasons that
     * don't reflect an issue, we trigger a context specific retry for a specific set of actions (rather
     * than the whole test) that will back-off after a few attempts.
     *
     * If it finds what it needs, the tests passes. If it doesn't, the test fails there is a legitimate problem
     * in the application.
     */
    @Test
    @DisplayName("Retry Actions Test")
    @Order(2)
    @Disabled
    public void retryActionsTest() {
        logger.info("retryActionsTest...");
        CommonUtils.openLink(driver, "Notification Messages");
        NotificationMessagesPage notificationMessagesPage = new NotificationMessagesPage(driver);

        // retries up until maxTimes to check if the success notification appears
        assertTrue(notificationMessagesPage.retryUntilSuccessOrMaxTimes(3));

        logger.info("retryActionsTest ...");
    }

    /**
     *  Level: Intermediate
     *  A simple way to gain insight into your test failures is to capture screenshots at the moment of failure.
     */
    @Test
    @DisplayName("Take Screenshot If Failure Test")
    @Order(2)
    @Disabled
    void takeScreenshotIfFailureTest() throws IOException {
        logger.info("takeScreenshotIfFailureTest...");

        // Perform an operation that will fail in a try-catch block
        try {
            driver.findElement(By.id("I want this to fail!"));
        }
        catch (Exception e) {
            // clean "screenshots" directory
            CommonUtils.cleanDirectory(".\\screenshots");

            // take screenshot and save it in "screenshots" folder
            CommonUtils.getScreenshot(driver, ".\\screenshots\\failure_" + CommonUtils.getDateTime());
        }

        logger.info("takeScreenshotIfFailureTest ...");
    }

    /**
     *  Level: Intermediate
     *  When a web application loads things dynamically use Explicit Waits
     *  Specify a timeout and an action. Selenium will repeatedly try that action until it can either complete it
     *  successfully or it will throw a timeout exception (causing the test to error)
     */
    @DisplayName("Dynamic Loaded Elements Test")
    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = { "Example 1", "Example 2"})
    @Disabled
    void dynamicLoadedElementsTest(String exampleNumber) {
        logger.info("dynamicLoadedElementsTest " + exampleNumber + "...");
        CommonUtils.openLink(driver, "Dynamic Loading");
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        // click on "Example #" and click on "Start"
        dynamicLoadingPage.startExample(exampleNumber);
        dynamicLoadingPage.clickStartButton();

        // get the text of the element when visible and assert
        assertEquals("Hello World!", dynamicLoadingPage.getTextFromDynamicElement());

        logger.info("dynamicLoadedElementsTest ...");
    }

    /**
     * Level: Intermediate
     * Easily traverse a table through the use of CSS Pseudo-classes
     * CSS Pseudo-classes work by walking through the structure of an object and targeting a
     * specific part of it based on a relative number.
     */
    @Test
    @DisplayName("Table Data Test")
    @Order(2)
    @Disabled
    void tableDataTest() {
        logger.info("tableDataTest...");
        CommonUtils.openLink(driver, "Sortable Data Tables");
        SortedDataTablesPage sortedDataTablesPage = new SortedDataTablesPage(driver);

        // assert that the data table is not sorted at the beginning
        assertFalse(sortedDataTablesPage.checkIfDueSorted());

        // click on "Due" to sort according to "Due" values
        sortedDataTablesPage.clickOnDueToSortHead();

        // assert it is sorted
        assertTrue(sortedDataTablesPage.checkIfDueSorted());

        logger.info("tableDataTest ...");
    }


}
